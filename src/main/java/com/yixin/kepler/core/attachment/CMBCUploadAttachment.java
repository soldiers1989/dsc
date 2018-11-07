package com.yixin.kepler.core.attachment;/**
 * Created by liushuai2 on 2018/6/13.
 */

import com.google.common.collect.Maps;
import com.yixin.common.exception.BzException;
import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.dto.DscSupplyAttachmentsDto;
import com.yixin.dsc.dto.DscSupplyDto;
import com.yixin.dsc.entity.order.DscFileAttachment;
import com.yixin.dsc.entity.order.DscSalesApplyCar;
import com.yixin.dsc.entity.order.DscSalesApplyFinancing;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.kepler.common.JacksonUtil;
import com.yixin.kepler.common.ScriptEngineUtil;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.dto.OsbAttachmentDTO;
import com.yixin.kepler.enity.AssetAttachmentRule;
import com.yixin.kepler.enity.AssetFinanceInfo;
import com.yixin.kepler.enity.OsbFileLog;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;
import org.springframework.util.StringUtils;

import java.math.BigDecimal;
import java.util.*;

/**
 * 
 *民生银行上上传文件流程
 * @author YixinCapital -- liushuai2
 *         2018年06月13日 13:44
 */
@Service("cMBCUploadAttachment")
public class CMBCUploadAttachment extends UploadAttachmentDomain<OsbAttachmentDTO>{
    public static final Logger logger = LoggerFactory.getLogger(CMBCUploadAttachment.class);

    private static final String BANK_CODE = "cmbc";
    private static final String DEFAULT_IMG_TYPE = ".jpg";
    /**
     *  产品营销代码
     */
    public static final String YXDM = "A202008";


    public static final String APPLY_CODE = "APPLY";

    public static final String LOAN_CODE = "LOAN";

    public static final String LOANAFTER_CODE = "LOANAFTER";

    /**
     * 命名规则：CMBC_产品营销代码_身份证号_阶段.tar
     */
    private static final String PACKAGE_NAME_FORMAT = "CMBC_%s_%s_%s";

    private static Map<String,List<String>> attachCodeMap;

    static {
        if (attachCodeMap == null) {
        	attachCodeMap = new HashMap<String, List<String>>(5);
   
            attachCodeMap.put("applicantOrderIdPositive",Arrays.asList(
                    "applicantOrderIdPositive","applicantOrderIdReverse"));

            attachCodeMap.put("leaseOrMortgageOrautoRetailOrSalesCarContracts",
                    Arrays.asList("leaseMainContract","contractReverse"));

            attachCodeMap.put("commercialInsurance",
                    Arrays.asList("compulsoryInsurance","commercialInsurance",
                            "firstYearCommercialInsurance","worryFreeTheft","noWorryFreeTheft"));
            attachCodeMap.put("vehicleRegistration",Arrays.asList(
                    "mortgageRegistrationCertificate","mortgageRegistrationCertificateOtherPages"));

            attachCodeMap.put("applicantAssessmentXT",Arrays.asList(
                        "applicantAssessment","applicantAssessmentXT"));

        }
    }

 
    @Override
    public String getBankCode() {
        return BANK_CODE;
    }

    @Override
    protected String getFinancialId() {
        String code = BANK_CODE.toUpperCase();
        AssetFinanceInfo info = AssetFinanceInfo.getByCode(code);
        
        Assert.notNull(info, "根据资方code"+code+"未找到资方id");
        return info.getId();
    }

    @Override
    public String getPackageName() {
        String idNo = customerInfo.get().getIdNo();
        String bzType = this.inputDto.get().getBzType();
        BankPhaseEnum phaseEnum = BankPhaseEnum.get(bzType);
        String jd = APPLY_CODE;
        switch (phaseEnum){
            case AFTER_LOAN:
                jd = LOANAFTER_CODE;
                break;
            case FIRST_TRIAL:
                jd = APPLY_CODE;
                break;
            case LAST_TRIAL:
                jd = APPLY_CODE;
                break;
            case PAYMENT:
                jd = LOAN_CODE;
                break;
        }


        logger.info("获取对应的文件名称，yxdm：{}，idNo：{}，phase：{}", YXDM, idNo, jd);

        //CMBC_%s_%s_%s
        String packageName = String.format(PACKAGE_NAME_FORMAT, YXDM, idNo, jd);
        logger.info("获取到的文件名称：{}", packageName);
        return packageName;
    }

    @Override
    public String getServerFilePath() {
    	String key = getBankCode() + "2yx.server.filepath";
        String val = sftpConfig.getSftp().get(key);
        logger.info("获取到的文件上传目录：{}", val);
        return val;
    }
    public List<DscFileAttachment> getAttachments(String bzId){
        // 获取对应的附件信息
        DscSalesApplyMain main = DscSalesApplyMain.getByApplyNo(this.inputDto.get().getBzId());
        return DscFileAttachment.lists(main.getId());
    }
    @Override
    public String rename(AttachmentRuleParamMap paramMap, Map<String, AssetAttachmentRule> rules, List<Map<String, String>> renamedList) {
        Set<String> attachmentCodeSet = rules.keySet();
        // 获取对应的附件信息
        DscSalesApplyMain main = DscSalesApplyMain.getByApplyNo(this.inputDto.get().getBzId());
        Map<String, List<DscFileAttachment>> attachmentMap = DscFileAttachment.get(main.getId());
        
        //由于民生银行不区分身份证正反面 所以单独处理    
        
        dealNameFormat(attachmentMap);
        
        StringBuilder fileNames = new StringBuilder("");
        DscSalesApplyCar salesApplyCar = DscSalesApplyCar.getBymainId(main.getId());
        

        for(String fileTypeCode : attachmentCodeSet){
            List<DscFileAttachment> attachments = attachmentMap.get(fileTypeCode);
            
            //if (attachments == null ) continue;
            int attachmentNum = attachments == null ? 0 : attachments.size();
            boolean isNew = "1".equals(salesApplyCar.getAcllx());
            logger.info("附件类型fileTypeCode：{}，附件数量：{}", fileTypeCode,attachmentNum);
            boolean isF012 = isExistFinanceItem(main.getApplyNo(), CommonConstant.FinanceType.F012); //精品加装
            
            AssetAttachmentRule attachmentRule = rules.get(fileTypeCode);
            String rule = attachmentRule.getRule();
            boolean checkRs = (boolean)ScriptEngineUtil.eval(rule,"toDo",
            		attachmentNum,isNew, isF012);
            if(!checkRs){
                String errorInfo = String.format("民生订单编号(%s)缺少必要文件,文件类型(%s)"
                        ,main.getApplyNo(),attachmentRule.getAttachName());
                throw new BzException(errorInfo);
            }else{
            	
                String nameFormat = attachmentRule.getNameFormat();
                //这里需要做特殊处理，如果附件的数量大于最大可传附件数量，那么需要按照指定的最大值取文件
                for(int i = 0; i < attachmentNum; i++){
                	DscFileAttachment dscTfileAttachment = attachments.get(i);
                    //开始取附件信息
                    Map<String, String> renamedMap = Maps.newHashMap();
                    renamedMap.put(FILEID, dscTfileAttachment.getFileId());

                    String fileName ;
                    int index = i;
                    do {
                        //判断重名，如果重名，递增1（eg:多个易鑫编码对应一个目标编码）
                        fileName = format(nameFormat, index, dscTfileAttachment.getFileName());
                        index++;
                    }while (fileNames.indexOf(fileName + "\n") != -1);
                    renamedMap.put(FILENAME, fileName);
                    logger.info("[cmbc文件处理]文件内容fileId：{}，fileName：{}", dscTfileAttachment.getFileId(), fileName);
                    renamedList.add(renamedMap);
                    fileNames.append(fileName).append("\n");
                }
            }
        }
        //返回fileLists内容
        return fileNames.toString();
    }

    /**
     * 重命名文件
     * @param nameFormat
     * @param currentNumber
     * @return
     */
    private String format(String nameFormat, int currentNumber, String attachmentName){
        if(currentNumber == 0){
            nameFormat = nameFormat.replace("_%d", "");
        }
        logger.info("[cmbc]需要处理的文件名format：{}",nameFormat );
        String prefix = "A202008_" +customerInfo.get().getIdNo();
        String formated = String.format(nameFormat, prefix,currentNumber);
        logger.info("[cmbc文件处理]重命名后的文件信息nameFormat：{}，currentNumber：{}，formated：{}", nameFormat, currentNumber, formated);
        return formated;
    }

    @Override
    protected void getData() throws BzException {
        super.getData();
        uploadFileByUrl(getAttachments(this.inputDto.get().getBzId()));
        getCumstomer(this.inputDto.get().getBzId());


    }

	@Override
	public void upLoadTask(String bzId, String bzType,List<Map<String, String>> renamedList) {
		try {
			OsbFileLog osbFileLog = osbFileDomain.createOsbFileLog(bzId,bzType,getBankCode(),
	                true,CommonConstant.TAR_COMPRESS_FORMAT,getPackageName(),true,
	                JacksonUtil.fromObjectToJson(renamedList),getServerFilePath());
			//获取url
			String url = sftpConfig.getSftp().get(OsbFileDomain.OSB_UPLOAD_HOST)
					+ OsbFileUrl.CMBC_OSB_UPLOAD_URI;
			osbFileDomain.uploadOsbData(osbFileLog, getUploadData(osbFileLog),url);
		} catch (Exception e) {
			logger.error("文件上传异常，异常信息为",e);
		}
	}

    /**
     * 查询一个订单是否有某个融资项
     * @param applyNo 申请编号
     * @param item 融资项码值
     * @return true：存在
     * @author YixinCapital -- chenjiacheng
     *             2018/9/11 13:40
     */
    private boolean isExistFinanceItem(String applyNo, String item) {
        DscSalesApplyMain main = DscSalesApplyMain.getByApplyNo(applyNo);
        List<DscSalesApplyFinancing> financingList = DscSalesApplyFinancing.getByMainId(main.getId());
        if (CollectionUtils.isEmpty(financingList)) return false;
        for (DscSalesApplyFinancing financing : financingList) {
            if (item.equals(financing.getArzxmid())) {
                return BigDecimal.ZERO.compareTo(financing.getFkhrzje()) < 0;
            }
        }
        return false;
    }
	
	
	 /**
     * 获取上传请求数据
     * @param osbFileLog
     * @return
     */
    private String getUploadData(OsbFileLog osbFileLog){
    	
        logger.info("[osb文件处理]需要处理的osbFileLog：{}", JacksonUtil.fromObjectToJson(osbFileLog));
        JSONObject data = new JSONObject();
        data.put("archives", osbFileLog.isArchives());
        data.put("compress", osbFileLog.isCompress());
        data.put("compressPackageName", osbFileLog.getCompressName());
        data.put("filePath", osbFileLog.getServerFilePath());
        data.put("password", osbFileLog.getCompressPwd());
        data.put("serialNumber", osbFileLog.getTranNo());
        data.put("server", osbFileLog.getServerType());
        data.put("serverIP", osbFileLog.getServerIp());
        data.put("serverPassword", osbFileLog.getServerPwd());
        data.put("serverPort", osbFileLog.getServerPort());
        data.put("serverUserName", osbFileLog.getServerUname());
        data.put("systemId", osbFileLog.getRoutingKey());
        // 如果是压缩文件 向osb推送compressFileJsonText 否则向osb推送fileJson
        if (!StringUtils.isEmpty(osbFileLog.getCompressFileJson())) {
            data.put("files", osbFileLog.getCompressFileJson());
        } else {
            data.put("files", osbFileLog.getFileJson());
        }
        data.put("compressFormat", osbFileLog.getCompressFormat());
        logger.info("[osb文件处理]即将传递给osb的参数信息JSON：{}", data.toString());
        return data.toString();
    }





	@Override
	public InvokeResult<DscSupplyDto> checkAttachmentFile(OsbAttachmentDTO osbAttachmentDTO) {
        InvokeResult<DscSupplyDto> invokeResult = new InvokeResult<>();

        DscSalesApplyMain applyNo = DscSalesApplyMain.getByApplyNo(osbAttachmentDTO.getBzId());
        Map<String, List<DscFileAttachment>> attachmentMap = DscFileAttachment.get(applyNo.getId());

        Map<String, AssetAttachmentRule> rules = AssetAttachmentRule.getRule(applyNo.getCapitalId())
                .get(BankPhaseEnum.get(osbAttachmentDTO.getBzType()));

        //由于民生银行文件附件合并
        dealNameFormat(attachmentMap);

        DscSalesApplyCar salesApplyCar = DscSalesApplyCar.getBymainId(applyNo.getId());

        DscSupplyDto dscSupplyDto = null;
        List<DscSupplyAttachmentsDto> results = null;

        for (Map.Entry<String, AssetAttachmentRule> rule: rules.entrySet()) {
            List<DscFileAttachment> list = attachmentMap.get(rule.getKey());

            int size = CollectionUtils.isEmpty(list) ? 0 : list.size();
            boolean isNew = "1".equals(salesApplyCar.getAcllx());

            boolean checkRs = (boolean)ScriptEngineUtil.eval(rule.getValue().getRule(),
                    "toDo", size, isNew);
            if (!checkRs) {
                if (dscSupplyDto == null) {
                    dscSupplyDto = new DscSupplyDto();
                    results = new ArrayList<>();
                }
                DscSupplyAttachmentsDto attachmentsDto = new DscSupplyAttachmentsDto(1,-1);
                attachmentsDto.setAttName(rule.getValue().getAttachName());

                String ruleType = rule.getValue().getAttachMainType();

                List<String> strings = attachCodeMap.get(ruleType);
                attachmentsDto.setAttType(strings == null ? ruleType:strings.toString());
                results.add(attachmentsDto);

            }
        }
        if (dscSupplyDto != null){
            dscSupplyDto.setAttList(results);
            invokeResult.failure("缺少必要附件类型").setData(dscSupplyDto);
            return invokeResult;
        }
        return invokeResult.success();
	}



	
	
	private void dealNameFormat(Map<String, List<DscFileAttachment>> attachmentMap) {
		//身份证集合
		List<DscFileAttachment> var1 = getValues(Arrays.asList(
				"applicantOrderIdPositive","applicantOrderIdReverse"),attachmentMap);
		attachmentMap.put("applicantOrderIdPositive", 
					var1);
		
		//融资租赁合同（放款）
		List<DscFileAttachment> var2 = getValues(Arrays.asList(
				"leaseMainContract","contractReverse"),attachmentMap);
		attachmentMap.put("leaseOrMortgageOrautoRetailOrSalesCarContracts",
					var2);

		//保单（放款）
		List<DscFileAttachment> var3 = getValues(Arrays.asList(
				"compulsoryInsurance","commercialInsurance",
				"firstYearCommercialInsurance","worryFreeTheft","noWorryFreeTheft"),
				attachmentMap);
		attachmentMap.put("commercialInsurance",var3);

		
		//车辆登记证(贷后)
		List<DscFileAttachment> var4 = getValues(Arrays.asList(
				"mortgageRegistrationCertificate","mortgageRegistrationCertificateOtherPages"),attachmentMap);
		attachmentMap.put("vehicleRegistration",
					var4);


        //二手车评估报告
        List<DscFileAttachment> var5 = getValues(Arrays.asList(
                "applicantAssessment","applicantAssessmentXT"),attachmentMap);
        attachmentMap.put("applicantAssessmentXT",
                    var5);

	}


}
