package com.yixin.kepler.v1.service.capital.icbc;



import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.yixin.kepler.enity.AssetMainInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.yixin.common.exception.BzException;
import com.yixin.common.utils.DateUitls;
import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.dto.DscSupplyAttachmentsDto;
import com.yixin.dsc.dto.DscSupplyDto;
import com.yixin.dsc.entity.order.DscFileAttachment;
import com.yixin.dsc.entity.order.DscSalesApplyCar;
import com.yixin.dsc.entity.order.DscSalesApplyCost;
import com.yixin.dsc.entity.order.DscSalesApplyCust;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.dsc.util.FileUploadUtils;
import com.yixin.kepler.common.JacksonUtil;
import com.yixin.kepler.common.ScriptEngineUtil;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.core.attachment.AttachmentRuleParamMap;
import com.yixin.kepler.core.attachment.UploadAttachmentDomain;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.dto.OsbAttachmentDTO;
import com.yixin.kepler.enity.AssetAttachmentRule;
import com.yixin.kepler.enity.AssetFinanceInfo;
import com.yixin.kepler.enity.OsbFileLog;
import com.yixin.kepler.v1.common.constants.IcbcConstant;
import com.yixin.kepler.v1.common.core.compress.CompressDomain;
import com.yixin.kepler.v1.datapackage.dto.CompressHandleDTO;

/**
 * 工行上传文件流程
 * Package : com.yixin.kepler.core.attachment
 *
 * @author YixinCapital -- wanghonglin
 * 2018/9/13 14:32
 */
@Service(value = "iCBCUploadAttachment")
public class ICBCUploadAttachment extends UploadAttachmentDomain<OsbAttachmentDTO>{

    static final String FILEID = "fileId";
    static final String FILENAME = "fileName";
    private static final String INDEX_FILE_NAME = "FILELIST.txt";
    private final Logger logger = LoggerFactory.getLogger(getClass());

    private static final String BANK_CODE = "ICBC";

    private static final String FILE_PATH = "/upload/%s";

    private static final String PACKAGE_NAME = "MEDIA_MEDIAFILE_%s_%s_%s";

    private static final String CHANNAL_CODE = "TG";

    private static Map<String,List<String>> attachCodeMap;

    private static Map<String, String> alixAttachCodeMap;

    /**
     * 文件服务通过url进行上传的地址
     */
    @Value("${spring.fileserver.http.upload.url}")
    private String fileHttpUpload;
    
    @Autowired
    private CompressDomain compressDomain;
    


    /**
     * 文件map  
     * 合并成银行需要的文件
     */
    static {
        if (attachCodeMap == null){
            attachCodeMap = new HashMap<>();
            //身份证
            attachCodeMap.put("applicantOrderIdPositive",Arrays.asList(
                    "applicantOrderIdPositive",//正面
                    "applicantOrderIdReverse"));//反面
            //购车人查询征信授权书
            attachCodeMap.put("applicantCreditAuthorization",Arrays.asList(
                    //不传易鑫 "applicantCreditAuthorization",//易鑫
                    "applicantCreditAuthorizationICBC"//工行
                   ));
            //购车人查询征信授权书客户签字
            attachCodeMap.put("applyForSurfaceSign",Arrays.asList(
            		//不传易鑫 "applyForSurfaceSign",//易鑫
                    "applyForSurfaceSignICBC"//工行
            ));
            //个人婚姻证明材料
            attachCodeMap.put("marriageCertificate",Arrays.asList(
                    "marriageCertificate",//结婚证
                    "divorceCertificate",//离婚证
                    "singleCertificate"));//单身证明
            //配偶身份证正反面  正面：consortIDPositive  反面：consortIDReverse
            attachCodeMap.put("consortIDPositive",Arrays.asList(
                    "consortIDPositive",//易鑫
                    "consortIDReverse"//工行
            ));
            //共同偿债人身份证正反面
            attachCodeMap.put("guarantorIDPositive", Arrays.asList(
                    "guarantorIDPositive",//正面
                    "guarantorIDReverse"));//反面
            //共同偿债人查询征信授权书  易鑫：guarantorCreditReport 工行：guarantorCreditReportICBC
            attachCodeMap.put("guarantorCreditReport", Arrays.asList(
            		//不传易鑫  "guarantorCreditReport",//易鑫
                    "guarantorCreditReportICBC"//工行
            ));
            //共同偿债人查询征信授权书签署照片  易鑫：guarantorAuthorizationPhoto 工行：guarantorAuthorizationPhotoICBC
            attachCodeMap.put("guarantorAuthorizationPhoto", Arrays.asList(
            		//不传易鑫  "guarantorAuthorizationPhoto",//易鑫
                    "guarantorAuthorizationPhotoICBC"//工行
            ));
            //购车合同 leaseMainContract  contractReverse
            attachCodeMap.put("leaseMainContract", Arrays.asList(
                    "leaseMainContract",//正面
                    "contractReverse"));//反面
            /*
		            第一年商业险保单  firstYearCommercialInsurance
		            第二年商业险保单  secondYearCommercialInsurance
		            第三年商业险保单  thirdYearCommercialInsurance
		            第四年商业险保单  fourthYearCommercialInsurance
		            第五年商业险保单  fifthYearCommercialInsurance
		            交强险保单       compulsoryInsurance
		            无忧盗抢险保单    worryFreeTheft
		            盗抢险保单       noWorryFreeTheft
		            商业险保单       commercialInsurance
		            保险押金凭证      insuranceDepositCertificate
		    */
		    attachCodeMap.put("carInsurancepolicy", Arrays.asList(
		           "firstYearCommercialInsurance",//正面
		           "secondYearCommercialInsurance",
		           "thirdYearCommercialInsurance",
		           "fourthYearCommercialInsurance",
		           "fifthYearCommercialInsurance",
		           "compulsoryInsurance",
		           "worryFreeTheft",
		           "noWorryFreeTheft",
		           "commercialInsurance",
		           "insuranceDepositCertificate"
		           ));
            //抵押登记证  抵押登记证(首页) mortgageRegistrationCertificate   抵押登记证(其他页) mortgageRegistrationCertificateOtherPages
            attachCodeMap.put("mortgageRegistrationCertificate", Arrays.asList(
                    "mortgageRegistrationCertificate",//正面
                    "mortgageRegistrationCertificateOtherPages"));//反面
            
            

            // ==========放入alix码值名称映射
            alixAttachCodeMap = new HashMap<>();
            alixAttachCodeMap.put("applicantCreditAuthorization", "申请人征信授权书");
            alixAttachCodeMap.put("applicantCreditAuthorizationICBC", "申请人征信授权书工行");
            alixAttachCodeMap.put("applyForSurfaceSign", "申请表面签照");
            alixAttachCodeMap.put("applyForSurfaceSignICBC", "申请表面签照工行");
            alixAttachCodeMap.put("applicationForInstallment", "信用卡及分期申请表");
            alixAttachCodeMap.put("statementOfIncome", "收入申明书");
            alixAttachCodeMap.put("proofOfEarnings", "收入证明");
            alixAttachCodeMap.put("marriageCertificate", "结婚证");
            alixAttachCodeMap.put("consortIDPositive", "申请人配偶/直接亲属身份证正面");
            alixAttachCodeMap.put("consortIDReverse", "申请人配偶/直接亲属身份证反面");
            alixAttachCodeMap.put("consortApplicantCreditAuthorization", "配偶查询征信授权书");
            alixAttachCodeMap.put("consortApplyForSurfaceSign", "配偶查询征信授权书签署照片");
            alixAttachCodeMap.put("guarantorIDPositive", "担保人身份证正面");
            alixAttachCodeMap.put("guarantorIDReverse", "担保人身份证反面");
            alixAttachCodeMap.put("guarantorCreditReport", "担保人征信报告");
            alixAttachCodeMap.put("guarantorCreditReportICBC", "担保人征信报告工行");
            alixAttachCodeMap.put("guarantorAuthorizationPhoto", "担保人征信授权面签照");
            alixAttachCodeMap.put("guarantorAuthorizationPhotoICBC", "担保人征信授权面签照工行");
            alixAttachCodeMap.put("applicantAssessmentXT", "二手车评估报告（系统默认）");
            alixAttachCodeMap.put("applicantAssessment", "二手车评估报告");
            alixAttachCodeMap.put("vehicleDrivingLicense", "车辆行驶证");
            alixAttachCodeMap.put("vehicleRegistration", "车辆登记本");
            alixAttachCodeMap.put("leaseOrMortgageOrautoRetailOrSalesCarContracts", "借款合同/抵押合同/汽车零售合同/汽车买卖合同");
            alixAttachCodeMap.put("leaseMainContract", "合同正面");
            alixAttachCodeMap.put("contractReverse", "合同反面");
            alixAttachCodeMap.put("downPaymentVoucher", "首付款凭证");
            alixAttachCodeMap.put("carPurchaseInvoice", "购车发票");
            alixAttachCodeMap.put("firstYearCommercialInsurance", "第一年商业险保单");
            alixAttachCodeMap.put("secondYearCommercialInsurance", "第二年商业险保单");
            alixAttachCodeMap.put("thirdYearCommercialInsurance", "第三年商业险保单");
            alixAttachCodeMap.put("fourthYearCommercialInsurance", "第四年商业险保单");
            alixAttachCodeMap.put("fifthYearCommercialInsurance", "第五年商业险保单");
            alixAttachCodeMap.put("compulsoryInsurance", "交强险保单");
            alixAttachCodeMap.put("worryFreeTheft", "无忧盗抢险保单");
            alixAttachCodeMap.put("noWorryFreeTheft", "盗抢险保单");
            alixAttachCodeMap.put("commercialInsurance", "商业险保单");
            alixAttachCodeMap.put("insuranceDepositCertificate", "保险押金凭证");
            alixAttachCodeMap.put("mortgageContractICBC", "抵押合同/个人-汽车租赁抵押合同");
            alixAttachCodeMap.put("mortgageRegistrationCertificate", "抵押登记证(首页)");
            alixAttachCodeMap.put("mortgageRegistrationCertificateOtherPages", " 抵押登记证(其他页)");
        }
    }

    
    @Override
    protected void getData() throws BzException {
        super.getData();
        uploadFileByUrl(getAttachments(this.inputDto.get().getBzId()));
        getCumstomer(this.inputDto.get().getBzId());
    }


    /**
     * 上传文件
     * @param bzId
     * @param bzType
     * @param renamedList
     */
    @Override
    public void upLoadTask(String bzId, String bzType, List<Map<String, String>> renamedList) {

        try {
            // 先根据renameList生成索引文件，上传文件服务，然后放入renameList一并送入OSB--20180919-xjt
            StringBuffer fileNameBuffer = new StringBuffer();
            renamedList.forEach(map->{
                fileNameBuffer.append(map.get(FILENAME)).append("\n");
            });
            logger.info("工行补充数据索引文件上传文件服务-{},数据为-{}",fileHttpUpload,fileNameBuffer);
            String fileId = FileUploadUtils.upload(fileNameBuffer.toString().getBytes(), INDEX_FILE_NAME, fileHttpUpload);
            logger.info("工行补充数据索引文件上传文件服务结果-{}",fileId);

            renamedList.add(new HashMap<String, String>(){{
                put(FILEID, fileId);
                put(FILENAME, INDEX_FILE_NAME);
            }});

            // 此处入参bankCode为获取配置文件中ftp相关配置，key需为小写--20180918-xjt
            OsbFileLog osbFileLog = osbFileDomain.createOsbFileLog(bzId,bzType,CommonConstant.BankName.ICBC_BANK.toLowerCase(),
                    true,CommonConstant.ZIP_COMPRESS_FORMAT,getPackageName(),true,
                    JacksonUtil.fromObjectToJson(renamedList),getServerFilePath());
            
            //开始压缩
            CompressHandleDTO dto = new CompressHandleDTO();
            dto.setApplyNo(bzId);
            dto.setOsbFileLogId(osbFileLog.getId());
            String tranNo = compressDomain.compressExe4FileId(dto, IcbcConstant.scale, IcbcConstant.maxSize);
            logger.info("请求压缩完成，压缩流水号tranNo={}，申请编号applyNo={}", tranNo, bzId);
        } catch (Exception e) {
            logger.error("文件上传异常，异常信息为",e);
        }
    }


    /**
     * 获取打包的名字
     * MEDIA_[业务标识]_[渠道标识]_[批次日期]_[3 位分包编号].zip
     * MEDIA_MEDIAFILE_xxx_20170622_001.zip
     */
    @Override
    public String getPackageName() {

        AssetMainInfo mainInfo = AssetMainInfo.getByApplyNo(this.inputDto.get().getBzId());
        if (null == mainInfo) {
            logger.error("无法找到资产,无法定位到资方申请编号,上传文件失败!");
            throw new BzException("无法找到资产,无法定位到资方申请编号,上传文件失败!");
        }
        String packageName = mainInfo.getVenusApplyNo() + DateUitls.dateToStr(new Date(), "yyyyMMddHHmmss");
        logger.info("[icbc文件处理]获取到的压缩包名称packageName：{}", packageName);
        return packageName;

//        String dateS = DateUitls.dateToStr(new Date(), "yyyyMMdd");
//        Integer num = OsbFileLog.getCurrentDayNum();
//
//        String format = String.format("%03d", num == 0 ? 1 : ++num);
//
//        return String.format(PACKAGE_NAME, CHANNAL_CODE,dateS,format);
    }

    @Override
    public String getServerFilePath() {
        return String.format(FILE_PATH,
                DateUitls.dateToStr(new Date(), "yyyyMMdd"));
    }

    @Override
    protected String getFinancialId() {
        AssetFinanceInfo assetFinanceInfo = AssetFinanceInfo.getByCode(BANK_CODE);
        Assert.notNull(assetFinanceInfo,"根据资方code"+BANK_CODE+"未找到资方id");
        return assetFinanceInfo.getId();
    }

    @Override
    public String getBankCode() {
        return BANK_CODE;
    }

    /**
     * 检查文件js规则
     * @param osbAttachmentDTO
     * @return
     */
    @Override
    public InvokeResult<DscSupplyDto> checkAttachmentFile(OsbAttachmentDTO osbAttachmentDTO) {

        InvokeResult<DscSupplyDto> invokeResult = new InvokeResult<>();

        DscSalesApplyMain dscSalesApplyMain = DscSalesApplyMain.getByApplyNo(osbAttachmentDTO.getBzId());

        Map<String, List<DscFileAttachment>> attachmentMap = DscFileAttachment.get(dscSalesApplyMain.getId());
        dealNameFormat(attachmentMap);

        Map<String, AssetAttachmentRule> rules = AssetAttachmentRule.getRule(dscSalesApplyMain.getCapitalId())
                .get(BankPhaseEnum.get(osbAttachmentDTO.getBzType()));
        DscSalesApplyCar salesApplyCar = DscSalesApplyCar.getBymainId(dscSalesApplyMain.getId());
        DscSalesApplyCost cost = DscSalesApplyCost.getByMainId(dscSalesApplyMain.getId());
        DscSalesApplyCust salesApplyCust = DscSalesApplyCust.getOneByMainId(dscSalesApplyMain.getId());
        DscSupplyDto dscSupplyDto = null;
        List<DscSupplyAttachmentsDto> results = null;

        for (Map.Entry<String, AssetAttachmentRule> rule: rules.entrySet()) {
            List<DscFileAttachment> list = attachmentMap.get(rule.getKey());
            //js规则入参
            boolean isNew = "1".equals(salesApplyCar.getAcllx());
            BigDecimal frze = cost.getFrze();//总融资额
            int size = list == null ? 0 : list.size();
            // 是否已婚(20W以上会校验配偶征信)
            boolean isMarried = "1".equals(salesApplyCust.getAhyzk());
            // 抵押权
            String mortgage = dscSalesApplyMain.getMortgage();
            logger.info("校验附件[{}],张数[{}],是否新车[{}],客户融资额[{}],是否已婚[{}],抵押权[{}]", rule.getValue().getAttachName(), size, isNew, frze, isMarried, mortgage);
            boolean checkRs;
            try {
                checkRs = (boolean) ScriptEngineUtil.eval(rule.getValue().getRule(),
                        "toDo", size,isNew,frze, isMarried, mortgage);
            } catch (Exception e) {
                logger.error("校验附件[{}]异常,function[{}]", rule.getValue().getRule(), e);
                checkRs = false;
            }

            if (!checkRs) {
                //组装errorInfo;
                if (dscSupplyDto == null) {
                    dscSupplyDto = new DscSupplyDto();
                    results = new ArrayList<>();
                }
                // 20180927-xjt-此处需要根据组装起来的文件key进行拆分，否则提报端(alix)无法对应
                // 只有提报端多对规则一的情况下，codeMap才会存储，所以需要进行空判断
                String subType = rule.getValue().getAttachMainType();
                logger.info("附件校验信息结果为results：{}", subType);

                if (null == attachCodeMap.get(subType) || attachCodeMap.get(subType).isEmpty()){
                    results.add(DscSupplyAttachmentsDto.createNewObject(1,-1).setAttachmentName(alixAttachCodeMap.get(subType)).setAttachmentType(subType));
                } else {
                    List<DscSupplyAttachmentsDto> finalResults = results;
                    attachCodeMap.get(subType).forEach(entryAttachmentCode->{
                        // 遍历提报端文档code，name取规则name
                        finalResults.add(DscSupplyAttachmentsDto.createNewObject(1,-1).setAttachmentName(alixAttachCodeMap.get(entryAttachmentCode)).setAttachmentType(entryAttachmentCode));
                    });
                    results = finalResults;
                }
            }

        }
        logger.info("附件校验信息结果为results：{}", JacksonUtil.fromObjectToJson(results));
        if (dscSupplyDto != null){
            dscSupplyDto.setAttList(results);
            invokeResult.failure("缺少必要附件类型").setData(dscSupplyDto);
            return invokeResult;
        }
        return invokeResult.success();
    }
    /**
     * 文件合并成银行需要的格式
     * @param attachmentMap
     */
    private void dealNameFormat(Map<String, List<DscFileAttachment>>
                                        attachmentMap) {
        //身份证
        List<DscFileAttachment> var1 = getValues(Arrays.asList("applicantOrderIdPositive","applicantOrderIdReverse"), attachmentMap);
        attachmentMap.put("applicantOrderIdPositive", var1);
        //购车人查询征信授权书
        // 20180929-xjt-工行要求征信授权书只上传银行侧，无需关注易鑫附件
//        List<DscFileAttachment> var2 = getValues(Arrays.asList("applicantCreditAuthorization","applicantCreditAuthorizationICBC"), attachmentMap);
        List<DscFileAttachment> var2 = getValues(Arrays.asList("applicantCreditAuthorizationICBC"), attachmentMap);
        attachmentMap.put("applicantCreditAuthorization", var2);
        //购车人查询征信授权书客户签字
        // 20180929-xjt-工行要求征信授权书只上传银行侧，无需关注易鑫附件
//        List<DscFileAttachment> var4 = getValues(Arrays.asList("applyForSurfaceSign", "applyForSurfaceSignICBC"), attachmentMap);
        List<DscFileAttachment> var4 = getValues(Arrays.asList("applyForSurfaceSignICBC"), attachmentMap);
        attachmentMap.put("applyForSurfaceSign", var4);
        //个人婚姻证明材料
        List<DscFileAttachment> var5 = getValues(Arrays.asList("marriageCertificate", "divorceCertificate","singleCertificate"), attachmentMap);
        attachmentMap.put("marriageCertificate", var5);

        //配偶身份证正反面  正面：consortIDPositive  反面：consortIDReverse
        List<DscFileAttachment> var6 = getValues(Arrays.asList("consortIDPositive", "consortIDReverse"), attachmentMap);
        attachmentMap.put("consortIDPositive", var6);
        //共同偿债人身份证正反面
        List<DscFileAttachment> var7 = getValues(Arrays.asList("guarantorIDPositive", "guarantorIDReverse"), attachmentMap);
        attachmentMap.put("guarantorIDPositive", var7);

        //共同偿债人查询征信授权书  易鑫：guarantorCreditReport 工行：guarantorCreditReportICBC
        // 20180929-xjt-工行要求征信授权书只上传银行侧，无需关注易鑫附件
//        List<DscFileAttachment> var8 = getValues(Arrays.asList("guarantorCreditReport", "guarantorCreditReportICBC"), attachmentMap);
        List<DscFileAttachment> var8 = getValues(Arrays.asList("guarantorCreditReportICBC"), attachmentMap);
        attachmentMap.put("guarantorCreditReport", var8);
        //共同偿债人查询征信授权书签署照片  易鑫：guarantorAuthorizationPhoto 工行：guarantorAuthorizationPhotoICBC
        // 20180929-xjt-工行要求征信授权书只上传银行侧，无需关注易鑫附件
//        List<DscFileAttachment> var9 = getValues(Arrays.asList("guarantorAuthorizationPhoto", "guarantorAuthorizationPhotoICBC"), attachmentMap);
        List<DscFileAttachment> var9 = getValues(Arrays.asList("guarantorAuthorizationPhotoICBC"), attachmentMap);
        attachmentMap.put("guarantorAuthorizationPhoto", var9);

        //二手车评估报告  applicantAssessmentXT、applicantAssessment
        List<DscFileAttachment> var10 = getValues(Arrays.asList("applicantAssessmentXT", "applicantAssessment"), attachmentMap);
        attachmentMap.put("applicantAssessment", var10);


        //抵购车合同 leaseMainContract  contractReverse
        List<DscFileAttachment> var11 = getValues(Arrays.asList("leaseMainContract", "contractReverse"), attachmentMap);
        attachmentMap.put("leaseMainContract", var11);
        //购车保单
        /**
		     第一年商业险保单  firstYearCommercialInsurance
		     第二年商业险保单  secondYearCommercialInsurance
		     第三年商业险保单  thirdYearCommercialInsurance
		     第四年商业险保单  fourthYearCommercialInsurance
		     第五年商业险保单  fifthYearCommercialInsurance
		     交强险保单       compulsoryInsurance
		     无忧盗抢险保单    worryFreeTheft
		     盗抢险保单       noWorryFreeTheft
		     商业险保单       commercialInsurance
		     保险押金凭证      insuranceDepositCertificate
         */
        List<DscFileAttachment> var12 = getValues(Arrays.asList("firstYearCommercialInsurance",
                "secondYearCommercialInsurance","thirdYearCommercialInsurance",
                "fourthYearCommercialInsurance","fifthYearCommercialInsurance",
                "compulsoryInsurance","worryFreeTheft","noWorryFreeTheft","commercialInsurance","insuranceDepositCertificate"),
                attachmentMap);
        attachmentMap.put("carInsurancepolicy", var12);
        //抵押登记证  抵押登记证(首页) mortgageRegistrationCertificate   抵押登记证(其他页) mortgageRegistrationCertificateOtherPages
        List<DscFileAttachment> var13 = getValues(Arrays.asList("mortgageRegistrationCertificate", "mortgageRegistrationCertificateOtherPages"), attachmentMap);
        attachmentMap.put("mortgageRegistrationCertificate", var13);
    }

    /**
     * 重命名并检查规则
     * @param paramMap
     * @param rules
     * @param renamedList
     * @return
     */
    @Override
    public String rename(AttachmentRuleParamMap paramMap, Map<String, AssetAttachmentRule> rules, List<Map<String, String>> renamedList) {

        //获取订单对应的附件信息
        DscSalesApplyMain main = DscSalesApplyMain.getByApplyNo(
                this.inputDto.get().getBzId());

        Map<String, List<DscFileAttachment>> attachmentMap = DscFileAttachment.get(
                main.getId());
        DscSalesApplyCost cost = DscSalesApplyCost.getByMainId(main.getId());
        dealNameFormat(attachmentMap);

        DscSalesApplyCar salesApplyCar = DscSalesApplyCar.getBymainId(main.getId());
        DscSalesApplyCust salesApplyCust = DscSalesApplyCust.getOneByMainId(main.getId());
        for (Map.Entry<String, AssetAttachmentRule> rule: rules.entrySet()) {
            List<DscFileAttachment> list = attachmentMap.get(rule.getKey());

            //js规则入参
            boolean isNew = "1".equals(salesApplyCar.getAcllx());
            int size = list == null ? 0 : list.size();
//            boolean isF050 = isExistFinanceItem(main.getApplyNo(), CommonConstant.FinanceType.F050); //购置税发票

            BigDecimal frze = cost.getFrze();//总融资额

            // 是否已婚(20W以上会校验配偶征信)
            boolean isMarried = "1".equals(salesApplyCust.getAhyzk());
            // 抵押权
            String mortgage = main.getMortgage();
            logger.info("校验附件[{}],张数[{}],是否新车[{}],客户融资额[{}],是否已婚[{}],抵押权[{}]", rule.getValue().getAttachName(), size, isNew, frze, isMarried, mortgage);
            boolean checkRs;
            try {
                checkRs = (boolean) ScriptEngineUtil.eval(rule.getValue().getRule(),
                        "toDo", size,isNew,frze, isMarried, mortgage);
            } catch (Exception e) {
                logger.error("校验附件[{}]异常,function[{}]", rule.getValue().getRule(), e);
                checkRs = false;
            }

            if (!checkRs) {
                throw new BzException("订单号("+main.getApplyNo()+")"+rule.getValue().getAttachMainType()+"缺少必要文件,规则为"+rule.getValue().getRule()+"入参为("
                        +size+","+isNew+")");
            }else {
                int index = 1;

                if (CollectionUtils.isNotEmpty(list)) {
                    for (DscFileAttachment dscFileAttachment : list) {
                        Map<String, String> renameMap = new HashMap<>(4);
                        renameMap.put(FILEID, dscFileAttachment.getFileId());
                        //获取命名format
                        String nameFormat = rule.getValue().getNameFormat();
                        renameMap.put(FILENAME, getRename(nameFormat,index,main));
                        renamedList.add(renameMap);
                        ++index;
                    }
                }
            }
        }
        return renamedList.toString();
    }
    /**
     *
     * @param nameFormat  /[业务主键]_[文件类型编号]_[3 位分页编号].jpg
     * @param index
     * @param main
     * @return
     */
    private String getRename(String nameFormat, int index,
                             DscSalesApplyMain main) {
        String format = String.format("%03d", index);
        return String.format(nameFormat,format);
    }
   
    public List<DscFileAttachment> getAttachments(String bzId){
        // 获取对应的附件信息
        DscSalesApplyMain main = DscSalesApplyMain.getByApplyNo(this.inputDto.get().getBzId());
        return DscFileAttachment.lists(main.getId());
    }
}
