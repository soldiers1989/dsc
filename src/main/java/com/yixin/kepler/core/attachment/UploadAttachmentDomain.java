package com.yixin.kepler.core.attachment;

import java.util.*;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.google.common.collect.Lists;
import com.yixin.common.exception.BzException;
import com.yixin.dsc.entity.order.DscFileAttachment;
import com.yixin.dsc.entity.order.DscSalesApplyCust;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.dsc.util.MailMessageUtils;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.dto.CustomerDTO;
import com.yixin.kepler.dto.OsbAttachmentDTO;
import com.yixin.kepler.enity.AssetAttachmentRule;


/**
 * @author YixinCapital -- liushuai2
 *         2017年11月13日 9:10
 */

@Component
public abstract class UploadAttachmentDomain<T extends OsbAttachmentDTO> extends AttachmentDomain<OsbAttachmentDTO> {
    private static final Logger logger = LoggerFactory.getLogger(UploadAttachmentDomain.class);


    @Autowired
    protected OsbFileDomain osbFileDomain;
    
    @Autowired
    protected SftpConfig sftpConfig;
    
    ThreadLocal<Map<BankPhaseEnum, Map<String,AssetAttachmentRule>>> ruleMapThreadLocal = new ThreadLocal<>();
    
  

    public void readRule() throws BzException {
        String financialId = getFinancialId();
        logger.info("根据资方code获取到的资方id为:{}", financialId);
        ruleMapThreadLocal.set(AssetAttachmentRule.getRule(financialId));
    }
    
    public void getCumstomer(String bzId){
        logger.info("根据业务编号获取对应的客户信息bzId：{}", bzId);
        DscSalesApplyMain dscSalesApplyMain = DscSalesApplyMain.getByApplyNo(bzId);
        // 客户信息
        DscSalesApplyCust cust = DscSalesApplyCust.getApplyCostByMianId(dscSalesApplyMain.getId());
        String akhxm = cust.getAkhxm();
        String azjhm = cust.getAzjhm();
        logger.info("获取到的客户信息：申请人姓名：{}, 证件号码：{}", akhxm, azjhm);
        CustomerDTO customerDTO = new CustomerDTO(akhxm, azjhm);
        super.customerInfo.set(customerDTO);
    }

    private Map<String, AssetAttachmentRule> getRule(String bzType){
        return ruleMapThreadLocal.get().get(BankPhaseEnum.get(bzType));
    }

    @Override
    protected void getData() throws BzException {}


    @Override
    public void doIt(OsbAttachmentDTO inputDto) throws BzException {
        this.inputDto.set(inputDto);
        try{
            doIt0();
        }catch (Exception e){
            String errorInfo = String.format("订单号(%s)," +
                    "阶段为(%s)上传文件发生异常",inputDto.getBzId(),inputDto.getBzType());
        	logger.error(errorInfo,e);
            MailMessageUtils.sendException(e,errorInfo);
            
        }
    }

    /**
     *  重命名文件信息
     * @param paramMap null
     * @param rules 规则列表
     * @param renamedList 未重命名后的文件列表
     * @return  重命名后的文件列表
     */
    public abstract String rename(AttachmentRuleParamMap paramMap,
                                  Map<String, AssetAttachmentRule> rules,
                                  List<Map<String, String>> renamedList);


    private void doIt0() throws Exception{
        readRule();
        String bzId = this.inputDto.get().getBzId();
        String bzType = this.inputDto.get().getBzType();
        logger.info("[文件处理]附件列表上传,申请单号为{}, 阶段类型为{}",bzId, bzType);
        getData();


        List<Map<String, String>> renamedList = Lists.newArrayList();
        //获取规则
        Map<String, AssetAttachmentRule> required = getRule(bzType);
        logger.info("[文件处理]获取到的校验规则为{}", required);
        if(MapUtils.isEmpty(required)){
        	logger.info("[文件处理]附件列表上传,申请单号为{}, 阶段类型为{},不需上传影像件",bzId, bzType);
        	return;
        } 
        //重命名
        String fileListsContent = rename(null, required, renamedList);
        logger.info("[文件处理]最终上传的的文件列表:\n{}", fileListsContent);
        
        //开始请求文件上传
        upLoadTask(bzId,bzType,renamedList);
    }

    /**
     * 上传附件任务
     * @param bzId 申请编号
     * @param bzType 阶段
     * @param renamedList 重命名后的文件列表
     */
    public abstract void upLoadTask(String bzId, String bzType,
    		List<Map<String, String>> renamedList);

    /**
     * 获取包的名字
     * @return string
     */
    public abstract String getPackageName();

    /**
     * 获取文件全路径
     * @return string
     */

    public abstract String getServerFilePath();
    
    /**
     * 获取资金方id
     * @return string
     */
    protected abstract String getFinancialId();
    
    
    protected List<DscFileAttachment> getValues(List<String> keys,
			Map<String, List<DscFileAttachment>> map){
		
		List<DscFileAttachment> arrayList = new ArrayList<>();
		
		for (String key : keys) {
			arrayList.addAll(map.get(key) == null ? Collections.emptyList()
					: map.get(key));
		}
		return arrayList;
	}

    /**
     * 合并处理
     * @param rules 规则记录
     * @param attachmentMap 文件记录
     */
    protected void dealAttachmentMap(Map<String, AssetAttachmentRule> rules,
                                   Map<String, List<DscFileAttachment>> attachmentMap) {

        for (Map.Entry<String, AssetAttachmentRule> entry : rules.entrySet()) {
            String mergerAttachType = entry.getValue().getMergerAttachType();

            if (StringUtils.isNotBlank(mergerAttachType)){
                String[] split = mergerAttachType.split(",");
                logger.info("{}需要合并的文件类型有{}",entry.getKey(),mergerAttachType);
                attachmentMap.put(entry.getKey(),getValues(
                        Arrays.asList(split),attachmentMap));
            }
        }
    }


}
