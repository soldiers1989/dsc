package com.yixin.web.service.impl;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;

import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.common.utils.InvokeResult;
import com.yixin.common.utils.Page;
import com.yixin.dsc.dto.order.DscFileAttachmentDTO;
import com.yixin.dsc.dto.order.DscSalesApplyCarDTO;
import com.yixin.dsc.dto.order.DscSalesApplyCustDTO;
import com.yixin.dsc.dto.order.DscSalesApplyMainDTO;
import com.yixin.dsc.dto.order.DscSyncDTO;
import com.yixin.dsc.dto.rule.DscMatchResultDto;
import com.yixin.dsc.entity.order.DscFileAttachment;
import com.yixin.dsc.entity.order.DscSalesApplyCar;
import com.yixin.dsc.entity.order.DscSalesApplyCust;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.dsc.util.HideDataUtil;
import com.yixin.kepler.common.enums.AssetPhaseEnum;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.core.domain.cmbc.AttachmentSupplyStrategy;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.enity.AssetBankTran;
import com.yixin.kepler.enity.OsbFileLog;
import com.yixin.web.common.AssetStatusType;
import com.yixin.web.common.MogoTemplateUtil;
import com.yixin.web.dto.AssetMainInfoDto;
import com.yixin.web.dto.ConditionBaseDto;
import com.yixin.web.service.AssetMainInfoService;
import com.yixin.web.service.base.BaseService;
import com.yixin.web.service.dao.EntityCommonDao;

/**
 * @author sukang
 */
@Service
public class AssetMainInfoServiceImpl extends BaseService implements AssetMainInfoService{


    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Resource
    private EntityCommonDao entityCommonDao;

    @Resource
    private AttachmentComponent attachmentComponent;


    @Override
    public Page<Map<String, Object>> getAssetMainList(AssetMainInfoDto assetMainInfoDto) {

        //优先以订单号查询
//        String applyNo = entityCommonDao.getApplyNoFromCust(assetMainInfoDto);

        ArrayList<Object> parameters = new ArrayList<>(6);

        StringBuilder hql = getSql();
        hql.append(" WHERE 1=1 ");

        int index = 0;
        if (StringUtils.isNotBlank(assetMainInfoDto.getApplyNo())){
            hql.append(" AND dsam.applyNo = ?".concat(String.valueOf(++index)));
            parameters.add(assetMainInfoDto.getApplyNo().trim());
        }
        if (assetMainInfoDto.getStartDate() != null) {
            hql.append(" AND dsam.createTime >= ?".concat(String.valueOf(++index)));
            parameters.add(assetMainInfoDto.getStartDate());
        }
        if (assetMainInfoDto.getEndDate() != null) {
            hql.append(" AND dsam.createTime <= ?".concat(String.valueOf(++index)));
            parameters.add(assetMainInfoDto.getEndDate());
        }

        if (StringUtils.isNotBlank(assetMainInfoDto.getFinancialCode())){
            hql.append(" AND ami.financialCode = ?".concat(String.valueOf(++index)));
            parameters.add(assetMainInfoDto.getFinancialCode());
        }

        if (StringUtils.isNotBlank(assetMainInfoDto.getLastTrialStatus())){
            hql.append(" AND ami.lastState = ?".concat(String.valueOf(++index)));
            parameters.add(Integer.parseInt(assetMainInfoDto.getLastTrialStatus()));
        }

        if (StringUtils.isNotBlank(assetMainInfoDto.getPaymentStatus())){
            hql.append(" AND ami.paymentState = ?".concat(String.valueOf(++index)));
            parameters.add(Integer.parseInt(assetMainInfoDto.getPaymentStatus()));
        }

        
        if (StringUtils.isNotBlank(assetMainInfoDto.getIdentity())){
            hql.append(" AND (dsac.asjhm = ?".concat(String.valueOf(++index)));
            hql.append(" OR dsac.azjhm = ?".concat(String.valueOf(++index)));
            hql.append(" OR dsac.akhxm = ?".concat(String.valueOf(++index)));
            hql.append(")");
            parameters.add(assetMainInfoDto.getIdentity());
            parameters.add(assetMainInfoDto.getIdentity());
            parameters.add(assetMainInfoDto.getIdentity());
        }
        
        hql.append(" ORDER BY ami.createTime DESC ");
        String sql = hql.toString();
        logger.debug("查询文件上传的结果sql为{}",sql);
        @SuppressWarnings("unchecked")
        Page<Map<String,Object>> pagedList = queryChannel.createJpqlQuery(sql)
                .setParameters(parameters)
                .setPage(assetMainInfoDto.getCurrentPage(),10)
                .pagedList();

        //添加结果的状态描述
        List<Map<String, Object>> maps = pagedList.getData();

        dealStatus(maps);
        //证件号、手机号中间部分隐藏显示 add by wangshuaiqiang -- 2018/10/18  13:04
        for (Map<String, Object> map : maps) {
            map.put("idNo",HideDataUtil.hideIdNo(map.get("idNo").toString()));
            map.put("phoneNo",HideDataUtil.hidePhoneNo(map.get("phoneNo").toString()));
        }
        pagedList.setData(maps);
        return pagedList;
    }


    @Override
    public List<Map<String, Object>> getAssetMainDetail(String applyNo) {

        StringBuilder hql = getSql();
        hql.append( " WHERE  dsam.applyNo = ?1");

        List<String> params = Collections.singletonList(applyNo);

        String sql = hql.toString();
        logger.debug("订单{},查询sql为{}",applyNo,sql);
        @SuppressWarnings("unchecked")
        List<Map<String,Object>> result = queryChannel.createJpqlQuery(sql)
                .setParameters(params).list();
        dealStatus(result);

        getSettleOsbInfo(result);
        //证件号、手机号中间部分隐藏显示 add by wangshuaiqiang -- 2018/10/26  11:26
        for (Map<String, Object> map : result) {
            map.put("idNo",HideDataUtil.hideIdNo(map.get("idNo").toString()));
            map.put("phoneNo",HideDataUtil.hidePhoneNo(map.get("phoneNo").toString()));
        }
        return result;
    }

    @Override
    public Page<Map<String, Object>> getAttachmentInfo(ConditionBaseDto conditionBaseDto) {

        String sql= " SELECT new map( dsam.applyNo as applyNo, " +
                            " DATE_FORMAT(dfa.createTime,'%Y-%m-%d %H:%i:%s') AS createTime, " +
                            " dfa.fileId AS fileId, dfa.fileUrl AS fileUrl, dfa.attachSubClass AS attachSubClass, " +
                            " dfa.fileName AS fileName ) " +
                            " FROM DscFileAttachment dfa" +
                            " LEFT JOIN DscSalesApplyMain dsam ON dsam.id = dfa.mainId " +
                            " WHERE dsam.applyNo = ?1 ";
        List<String> params = Collections.singletonList(conditionBaseDto.getApplyNo());

        logger.debug("查询附件订单{}列表的sql为{}",conditionBaseDto.getApplyNo(),sql);
        @SuppressWarnings("unchecked")
        Page<Map<String,Object>> pagedList = queryChannel.createJpqlQuery(sql)
                .setParameters(params)
                .setPage(conditionBaseDto.getCurrentPage(),10)
                .pagedList();
        return pagedList;
    }


    @Override
    public List<Map<String,Object>> getSynLogInfo(ConditionBaseDto conditionBaseDto) {

        List<DscSyncDTO> info = MogoTemplateUtil.getInfo(conditionBaseDto);

        List<Map<String,Object>> result = new ArrayList<>();
        if (!CollectionUtils.isEmpty(info)) {
            info.forEach(t -> {
                Map<String, Object> hashMap = new HashMap<>(2);
                hashMap.put("applyNo", t.getApplyNo());
                hashMap.put("data", t);
                hashMap.put("createTime", t.getSign());
                result.add(hashMap);
            });
        }
        return result;
    }

    @Override
    public List<DscMatchResultDto> getMatchResults(ConditionBaseDto conditionBaseDto) {
        return MogoTemplateUtil.getMatchResults(conditionBaseDto);
    }

    private void getSettleOsbInfo(List<Map<String,Object>> result){
        result.forEach(t -> {
            String applyNo = String.valueOf(t.get("applyNo"));
            t.put("lastTrialOsbStatus",OsbFileLog.isOk(applyNo, BankPhaseEnum.LAST_TRIAL.getPhase()));
            t.put("paymentOsbStatus",OsbFileLog.isOk(applyNo, BankPhaseEnum.PAYMENT.getPhase()));
            t.put("afterLoanOsbStatus",OsbFileLog.isOk(applyNo, BankPhaseEnum.AFTER_LOAN.getPhase()));

            List<AssetBankTran> assetBankTrans = AssetBankTran.getByApplyNo(applyNo, BankPhaseEnum.PUSHFILE.getPhase());

            List<AssetBankTran> success = assetBankTrans
                    .stream()
                    .filter(v -> "true".equals(v.getApprovalCode()))
                    .collect(Collectors.toList());
            t.put("pushFileStatus",CollectionUtils.isNotEmpty(success));
        });
    }


    @Override
    @SuppressWarnings("unchecked")
    public List<Object> getInfoByTable(String applyNo, String tableInfo) {

        if (DscSalesApplyMain.class.getSimpleName().equals(tableInfo)){
            DscSalesApplyMain byApplyNo = DscSalesApplyMain.getByApplyNo(applyNo);
            DscSalesApplyMainDTO dto = new DscSalesApplyMainDTO();
            BeanUtils.copyProperties(byApplyNo,dto);
            dto.setAckrzjhm(HideDataUtil.hideIdNo(byApplyNo.getAckrzjhm()));
            return Collections.singletonList(dto);
        }


        String hql = " select t from ".concat(tableInfo)
                .concat(" AS t LEFT JOIN DscSalesApplyMain AS dsam ON t.mainId = dsam.id " +
                        " WHERE dsam.applyNo = ?1 ");
        List list = queryChannel.createJpqlQuery(hql).setParameters(
                Collections.singletonList(applyNo)).list();
        //证件号、手机号中间部分隐藏显示 add by wangshuaiqiang -- 2018/10/18  13:04
        if(DscSalesApplyCust.class.getSimpleName().equals(tableInfo)){
            List<DscSalesApplyCust> list1 = list;
            List<DscSalesApplyCustDTO> result = new ArrayList<>();
            for (DscSalesApplyCust dscSalesApplyCust : list1) {
                DscSalesApplyCustDTO dto = new DscSalesApplyCustDTO();
                BeanUtils.copyProperties(dscSalesApplyCust,dto);
                //借款人
                dto.setAzjhm(HideDataUtil.hideIdNo(dscSalesApplyCust.getAzjhm()));
                dto.setAsjhm(HideDataUtil.hidePhoneNo(dscSalesApplyCust.getAsjhm()));
                //配偶
                dto.setApozjhm(HideDataUtil.hideIdNo(dscSalesApplyCust.getApozjhm()));
                dto.setAposjhm(HideDataUtil.hidePhoneNo(dscSalesApplyCust.getAposjhm()));
                //法人证件号
                dto.setAqyfrsfz(HideDataUtil.hideIdNo(dscSalesApplyCust.getAqyfrsfz()));
                result.add(dto);

            }
            list=result;
        }else if(DscSalesApplyCar.class.getSimpleName().equals(tableInfo)){//车辆信息
            List<DscSalesApplyCar> list1 = list;
            List<DscSalesApplyCarDTO> result = new ArrayList<>();
            for (DscSalesApplyCar dscSalesApplyCar : list1) {
                DscSalesApplyCarDTO dto = new DscSalesApplyCarDTO();
                BeanUtils.copyProperties(dscSalesApplyCar,dto);
                result.add(dto);
            }
            list=result;
        }else if(DscFileAttachment.class.getSimpleName().equals(tableInfo)){//附件
            List<DscFileAttachment> list1 = list;
            List<DscFileAttachmentDTO> result = new ArrayList<>();
            for (DscFileAttachment dscFileAttachment : list1) {
                DscFileAttachmentDTO dto = new DscFileAttachmentDTO();
                BeanUtils.copyProperties(dscFileAttachment,dto);
                result.add(dto);
            }
            list=result;
        }
        return list;
    }

    private void dealStatus(List<Map<String, Object>> maps){
        maps.forEach(t -> {
            t.put("creditSignStateInfo",
                    getStatusMsg(t.get("creditSignState"), AssetStatusType.creditSignStateInfo));
            t.put("contractSignStateInfo",
                    getStatusMsg(t.get("contractSignState"),AssetStatusType.contractSignStateInfo));
            t.put("preStateInfo",
                    getStatusMsg(t.get("preState"),AssetStatusType.bankStatusInfo));
            t.put("firstStateInfo",
                    getStatusMsg(t.get("firstState"),AssetStatusType.bankStatusInfo));
            t.put("lastStateInfo",
                    getStatusMsg(t.get("lastState"),AssetStatusType.bankStatusInfo));
            t.put("paymentStateInfo",
                    getStatusMsg(t.get("paymentState"),AssetStatusType.bankStatusInfo));
        });
    }
    
    
    
    private StringBuilder getSql(){
        return  new StringBuilder(
                " SELECT new map(dsam.applyNo as applyNo, " +
                        " DATE_FORMAT(dsam.createTime,'%Y-%m-%d %H:%i:%s') as createTime, " +
                        " ami.financialCode as financialCode, ami.deleted as deleted, " +
                        " dsac.asjhm as phoneNo, dsac.azjhm as idNo, dsac.akhxm as name, " +
                        " ami.creditSignState as creditSignState, ami.contractSignState as contractSignState, " +
                        " ami.preState as preState, ami.firstState as firstState,ami.lastState as lastState, " +
                        " ami.paymentState as paymentState) " +
                        " FROM DscSalesApplyMain dsam " +
                        " LEFT JOIN AssetMainInfo ami ON ami.applyNo = dsam.applyNo and ami.deleted = false" +
                        " LEFT JOIN DscSalesApplyCust dsac ON dsam.id = dsac.mainId and dsac.deleted = false");
    }


    @Override
    public BaseMsgDTO supplyAttachment(ConditionBaseDto conditionBaseDto){

        List<String> attachmentList = getAttachmentList(conditionBaseDto);

        attachmentComponent.dealAsyncTask(attachmentList);
        return BaseMsgDTO.successData(attachmentList.toString());
    }


    @Override
    public List<BaseMsgDTO> supplyAttachmentBank(ConditionBaseDto conditionBaseDto) {

        AttachmentSupplyStrategy supplyStrategy = SpringContextUtil.getBean(
                "CMBCAttachmentSupplyStrategy",AttachmentSupplyStrategy.class);

        List<BaseMsgDTO> result = new ArrayList<>();
        if (StringUtils.isNotBlank(conditionBaseDto.getApplyNo())){
            InvokeResult<BaseMsgDTO> sendResult = supplyStrategy.sendResult(
                    conditionBaseDto.getApplyNo(),BankPhaseEnum.PAYMENT);
            result.add((BaseMsgDTO) sendResult.getData());
        }else {
            List<String> attachmentList = getAttachmentList(conditionBaseDto);
            for (String applyNo:attachmentList){

                //查询最近一次的osb_file_log
                OsbFileLog osbFileLog = getOsbFileLogCurrent(applyNo);

                if (osbFileLog == null || osbFileLog.getDealStatus() != 2){
                    result.add(BaseMsgDTO.failureData(applyNo+"未找到文件"));
                    continue;
                }
                InvokeResult<BaseMsgDTO> sendResult = supplyStrategy.sendResult(applyNo, BankPhaseEnum.PAYMENT);
                BaseMsgDTO baseMsgDTO = (BaseMsgDTO) sendResult.getData();
                baseMsgDTO.setMessage("订单号"+applyNo+baseMsgDTO.getMessage());
                result.add(baseMsgDTO);
            }
        }

        return result;
    }



    private OsbFileLog getOsbFileLogCurrent(String applyNo){
        return OsbFileLog.getOsbFilesByApplyNoAndRequestType(applyNo,BankPhaseEnum.PAYMENT.getPhase());
    }




    private List<String> getAttachmentList(ConditionBaseDto conditionBaseDto) {

        StringBuilder hql = new StringBuilder(
                "SELECT DISTINCT ami.applyNo FROM AssetMainInfo ami WHERE ami.deleted = 0 "
                        + " AND ami.paymentState = 1 AND financialCode = 'CMBC' ");
        ArrayList<Object> parameters = new ArrayList<>(6);

        int index = 0;
        if (conditionBaseDto.getStartDate() != null) {
            hql.append(" AND ami.createTime >= ?".concat(String.valueOf(++index)));
            parameters.add(conditionBaseDto.getStartDate());
        }

        if (conditionBaseDto.getEndDate() != null) {
            hql.append(" AND ami.createTime <= ?".concat(String.valueOf(++index)));
            parameters.add(conditionBaseDto.getEndDate());
        }
        hql.append(" ORDER BY ami.createTime ASC ");
        String sql = hql.toString();
        logger.info("资料补传获取历史订单的sql为{}",sql);
        List<String> list = queryChannel.createJpqlQuery(sql)
                .setParameters(parameters).list();
        logger.info("获取到订单的记录为{}",list);
        return  list;
    }


    /**
     * 查询银行请款通过,但是贷后资料未传给银行信息列表
     * @param applyNo
     * @param financialId
     * @param currentPage
     * @return 分页List
     */
    @SuppressWarnings("unchecked")
	@Override
    public Page<List<Map<String, Object>>> getLoanDataNotSynList(String applyNo, String financialId, String currentPage) {
        logger.info("getLoanDataNotSynList查询入参:applyNo={},financialId={},currentPage={}", applyNo, financialId, currentPage);
        ArrayList<Object> parameters = new ArrayList<>();
        StringBuilder jpql = new StringBuilder(
                "SELECT new map(a.applyNo as applyNo," +
                        " DATE_FORMAT(b.updateTime,'%Y-%m-%d %H:%i:%s') as requtestTime," +
                        " d.code as assetCode," +
                        " DATEDIFF(now(),b.updateTime) as betDays  ) " +
                        " FROM AssetMainInfo a " +
                        " left join AssetPaymentTask b on a.applyNo = b.applyNo " +
                        " left join AssetFinanceInfo d on a.financialId = d.id " +
                        " WHERE 1=1  and a.paymentState = 1 " +
                        " and  (a.afterLoanState= 2 or a.afterLoanState is null) " );
        int index = 0;
        if (StringUtils.isNotBlank(applyNo)) {
        	jpql.append(" AND a.applyNo = ?".concat(String.valueOf(++index)));
            parameters.add(applyNo);
        }
        if (StringUtils.isNotBlank(financialId)) {
        	jpql.append(" AND a.financialId = ?".concat(String.valueOf(++index)));
            parameters.add(financialId);
        }
        jpql.append(" ORDER BY betDays DESC");
        return queryChannel.createJpqlQuery(jpql.toString())
                .setParameters(parameters)
                .setPage(dealPageIndex(currentPage), 10)
                .pagedList();
    }

    
    /**
     * 查询不同阶段审核结果
     * @param applyNo
     * @param phase
     * @param currentPage
     * @return
     */
    @SuppressWarnings({ "unchecked", "rawtypes" })
	@Override
    public Page<List<Map<String, Object>>> getPhaseQueryResultsList(String applyNo, String phase, String currentPage) {
        logger.info("getPhaseQueryResultsList查询入参:applyNo={},phase={},currentPage={}", applyNo, phase, currentPage);
        ArrayList<Object> parameters = new ArrayList<>();
        StringBuilder jpql = new StringBuilder(
                "SELECT new map(a.applyNo as applyNo," +
                	" a.financialCode as assetCode," +
                    " a.assetPhase as assetPhase," +
                    " DATE_FORMAT(a.updateTime,'%Y-%m-%d %H:%i:%s') as resultTime," +
                    " DATEDIFF(a.updateTime, NOW()) * 24 as betHoursTime) " +
                    " FROM AssetMainInfo a " +
                    " WHERE a.deleted=0 " );
        int index = 0;
        if (StringUtils.isNotBlank(applyNo)) {
        	jpql.append(" AND a.applyNo = ?".concat(String.valueOf(++index)));
            parameters.add(applyNo);
        }
        if (StringUtils.isNotBlank(phase)) {
        	if(AssetPhaseEnum.TRIAL.getPhase().equals(phase)){
        		//信审结果还没返回的
        		jpql.append("and (a.firstState = '3' or a.lastState = '3')");
        	}
        }
        jpql.append(" ORDER BY TIMESTAMPDIFF(HOUR, a.updateTime, NOW()) DESC");
        logger.info("getPhaseQueryResultsList查询sql为{}", jpql.toString());
        Page page = queryChannel.createJpqlQuery(jpql.toString()).setParameters(parameters)
                .setPage(dealPageIndex(currentPage), 10)
                .pagedList();
        List<Map<String, Object>> data = page.getData();
        if (CollectionUtils.isEmpty(data)) {
        	return page;
        }
        try {
            for (Map<String, Object> map : data) {
            	map.put("assetPhase", AssetPhaseEnum.getPhaseName((String) map.get("assetPhase")));
            }
            page.setData(data);
        } catch (Exception e) {
        	logger.error("getPhaseQueryResultsList查询异常:{}",applyNo,e);
        }
        return page;
    }



}
