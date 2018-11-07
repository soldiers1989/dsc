package com.yixin.kepler.enity;

import com.alibaba.fastjson.JSON;
import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.common.system.util.Label;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.kepler.common.enums.AssetStateEnum;
import com.yixin.kepler.dto.webank.WBMongoDTO;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;


/**
 * <B>功能简述</B><br>
 * KAssetMainInfo实体类
 *
 * @author liuhongshen
 * @date 2018年06月06日 14:14:54
 */
@Entity
@Table(name = "k_asset_main_info")
public class AssetMainInfo extends BZBaseEntiy {

    private static final long serialVersionUID = 1022293680677354522L;

    private static final Logger LOGGER = LoggerFactory.getLogger(AssetMainInfo.class);

    /**
     * 申请编号
     */
    @Label(name = "申请编号")
    @Column(name = "apply_no", length = 64)
    private String applyNo;

    /**
     * 银行交易流水号，每次交易必须唯一，一个申请编号可能对应多个交易号
     */
    @Label(name = "银行交易流水号，每次交易必须唯一，一个申请编号可能对应多个交易号")
    @Column(name = "asset_no", length = 64)
    private String assetNo;
    /**
     * 资产当前所处的状态
     */
    @Label(name = "资产当前所处的状态")
    @Column(name = "asset_state", length = 10)
    private Integer assetState;
    /**
     * 资产所属阶段
     */
    @Label(name = "资产所属阶段")
    @Column(name = "asset_phase", length = 16)
    private String assetPhase;
    /**
     * 资产对应的行方状态
     */
    @Label(name = "资产对应的行方状态")
    @Column(name = "asset_bank_state", length = 10)
    private Integer assetBankState;
    /**
     * 银行信审前置状态
     * 原含义[授权书签署状态]
     * remarks:18/09/13后重新规划该字段含义，该字段统一用作为银行信审前的个性化前置状态[eg：授权书、四要素...] by chenlin
     */
    @Label(name = "授权书签署状态")
    @Column(name = "credit_sign_state", length = 10)
    private Integer creditSignState;
    /**
     * 银行请款前置状态
     * 原含义[合同签署状态]
     * remarks:18/09/13后重新规划该字段含义，该字段统一用作为银行请款前的个性化前置状态[eg：合同点子签、面签...] by chenlin
     */
    @Label(name = "合同签署状态")
    @Column(name = "contract_sign_state", length = 10)
    private Integer contractSignState;
    /**
     * 资方id
     */
    @Label(name = "资方id")
    @Column(name = "financial_id", length = 32)
    private String financialId;
    /**
     * 资方code
     */
    @Label(name = "资方code")
    @Column(name = "financial_code", length = 64)
    private String financialCode;
    /**
     * 预审状态
     */
    @Label(name = "预审状态")
    @Column(name = "pre_state", length = 10)
    private Integer preState;
    /**
     * 初审状态
     */
    @Label(name = "初审状态")
    @Column(name = "first_state", length = 10)
    private Integer firstState;
    /**
     * 终审状态
     * remarks:18/09/13后重新规划该字段含义，若银行只进行一次审核，则该审核即为终审  by chenlin
     */
    @Label(name = "终审状态")
    @Column(name = "last_state", length = 10)
    private Integer lastState;
    /**
     * 请款状态
     */
    @Label(name = "请款状态")
    @Column(name = "payment_state", length = 10)
    private Integer paymentState;

    /**
     * 贷后状态
     */
    @Label(name = "贷后资料同步状态")
    @Column(name = "after_loan_state", length = 10)
    private Integer afterLoanState;

    /**
     * 附件下载状态
     */
    @Label(name = "附件下载状态")
    @Column(name = "attach_download_state", length = 10)
    private Integer attachDownloadState;

    @Label(name = "渠道编号")
    @Column(name = "channel_code", length = 64)
    private String channelCode;

    @Label(name = "渠道名称")
    @Column(name = "channel_name", length = 64)
    private String channelName;

    @Label(name = "发起融资公司编号")
    @Column(name = "company_code", length = 64)
    private String companyCode;

    @Label(name = "发起融资公司编号")
    @Column(name = "cmbc_apply_no", length = 64)
    private String cmbcApplyNo;

    @Label(name = "发起融资公司编号")
    @Column(name = "cmbc_loan_no", length = 64)
    private String cmbcLoanNo;

    /**
     * 订单编号
     */
    @Label(name = "银行订单编号，每次交易必须唯一，一个申请编号可能对应多个订单编号")
    @Column(name = "bank_order_no", length = 64)
    private String bankOrderNo;

    /**
     * 银行申请编号【维纳斯和银行侧交互申请编号】
     * remarks:18/09/21后新增该字段，该字段作用是为了剥离提报端与银行侧申请编号重复问题，故指定维纳斯和银行侧交互时使用该申请编号  by chenlin
     */
    @Label(name = "银行申请编号")
    @Column(name = "venus_apply_no", length = 64)
    private String venusApplyNo;

    @Column(name = "bank_loan_time", columnDefinition = "datetime comment '资方放款时间'")
    private Date bankLoanTime;

    @Label(name = "资方合同编号")
    @Column(name = "bank_contract_no", length = 64)
    private String bankContractNo;

    @Label(name = "资方合同状态")
    @Column(name = "bank_contract_status", length = 64)
    private String bankContractStatus;

    @Column(name = "bank_loan_amount", columnDefinition = "decimal(19,2) comment '资方放款金额'")
    private BigDecimal bankLoanAmount;

    @Column(name = "bank_audit_time", columnDefinition = "datetime comment '资方审批时间'")
    private Date bankAuditTime;

    public String getBankOrderNo() {
        return bankOrderNo;
    }

    public void setBankOrderNo(String bankOrderNo) {
        this.bankOrderNo = bankOrderNo;
    }

    public String getVenusApplyNo() {
        return venusApplyNo;
    }

    public void setVenusApplyNo(String venusApplyNo) {
        this.venusApplyNo = venusApplyNo;
    }

    public String getCmbcApplyNo() {
        return cmbcApplyNo;
    }

    public void setCmbcApplyNo(String cmbcApplyNo) {
        this.cmbcApplyNo = cmbcApplyNo;
    }

    public String getCmbcLoanNo() {
        return cmbcLoanNo;
    }

    public void setCmbcLoanNo(String cmbcLoanNo) {
        this.cmbcLoanNo = cmbcLoanNo;
    }

    public String getChannelCode() {
        return channelCode;
    }

    public void setChannelCode(String channelCode) {
        this.channelCode = channelCode;
    }

    public String getChannelName() {
        return channelName;
    }

    public void setChannelName(String channelName) {
        this.channelName = channelName;
    }

    public String getCompanyCode() {
        return companyCode;
    }

    public void setCompanyCode(String companyCode) {
        this.companyCode = companyCode;
    }

    public String getApplyNo() {
        return this.applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getAssetNo() {
        return this.assetNo;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
    }

    public Integer getAssetState() {
        return this.assetState;
    }

    public void setAssetState(Integer assetState) {
        this.assetState = assetState;
    }

    public String getAssetPhase() {
        return this.assetPhase;
    }

    public void setAssetPhase(String assetPhase) {
        this.assetPhase = assetPhase;
    }

    public Integer getAssetBankState() {
        return this.assetBankState;
    }

    public void setAssetBankState(Integer assetBankState) {
        this.assetBankState = assetBankState;
    }


    public Integer getCreditSignState() {
        return this.creditSignState;
    }

    public void setCreditSignState(Integer creditSignState) {
        this.creditSignState = creditSignState;
    }

    public Integer getContractSignState() {
        return this.contractSignState;
    }

    public void setContractSignState(Integer contractSignState) {
        this.contractSignState = contractSignState;
    }

    public String getFinancialId() {
        return this.financialId;
    }

    public void setFinancialId(String financialId) {
        this.financialId = financialId;
    }

    public String getFinancialCode() {
        return this.financialCode;
    }

    public void setFinancialCode(String financialCode) {
        this.financialCode = financialCode;
    }

    public Integer getPreState() {
        return this.preState;
    }

    public void setPreState(Integer preState) {
        this.preState = preState;
    }

    public Integer getFirstState() {
        return this.firstState;
    }

    public void setFirstState(Integer firstState) {
        this.firstState = firstState;
    }

    public Integer getLastState() {
        return this.lastState;
    }

    public void setLastState(Integer lastState) {
        this.lastState = lastState;
    }

    public Integer getPaymentState() {
        return this.paymentState;
    }

    public void setPaymentState(Integer paymentState) {
        this.paymentState = paymentState;
    }

    public Integer getAttachDownloadState() {
        return this.attachDownloadState;
    }

    public void setAttachDownloadState(Integer attachDownloadState) {
        this.attachDownloadState = attachDownloadState;
    }

    public Integer getAfterLoanState() {
        return afterLoanState;
    }

    public void setAfterLoanState(Integer afterLoanState) {
        this.afterLoanState = afterLoanState;
    }


    public Date getBankLoanTime() {
        return bankLoanTime;
    }

    public void setBankLoanTime(Date bankLoanTime) {
        this.bankLoanTime = bankLoanTime;
    }

    public String getBankContractNo() {
        return bankContractNo;
    }

    public void setBankContractNo(String bankContractNo) {
        this.bankContractNo = bankContractNo;
    }

    public String getBankContractStatus() {
        return bankContractStatus;
    }

    public void setBankContractStatus(String bankContractStatus) {
        this.bankContractStatus = bankContractStatus;
    }

    public BigDecimal getBankLoanAmount() {
        return bankLoanAmount;
    }

    public void setBankLoanAmount(BigDecimal bankLoanAmount) {
        this.bankLoanAmount = bankLoanAmount;
    }

    public Date getBankAuditTime() {
        return bankAuditTime;
    }

    public void setBankAuditTime(Date bankAuditTime) {
        this.bankAuditTime = bankAuditTime;
    }

    /**
     * 无参构造
     */
    public AssetMainInfo() {

    }

    public AssetMainInfo(String applyNo, String assetNo, Integer assetState, String assetPhase, Integer assetBankState, Integer creditSignState, Integer contractSignState, String financialId, String financialCode, Integer preState, Integer firstState, Integer lastState, Integer paymentState, Integer attachDownloadState) {
        this.applyNo = applyNo;
        this.assetNo = assetNo;
        this.assetState = assetState;
        this.assetPhase = assetPhase;
        this.assetBankState = assetBankState;
        this.creditSignState = creditSignState;
        this.contractSignState = contractSignState;
        this.financialId = financialId;
        this.financialCode = financialCode;
        this.preState = preState;
        this.firstState = firstState;
        this.lastState = lastState;
        this.paymentState = paymentState;
        this.attachDownloadState = attachDownloadState;
    }


    /**
     * 根据bankOrderNo获取实体类
     *
     * @param bankOrderNo
     * @return
     * @author YixinCapital -- chen.lin
     * 2018年9月13日 下午7:31:06
     */
    public static AssetMainInfo getByBankOrderNo(String bankOrderNo) {
        String jpql = "SELECT ami FROM AssetMainInfo AS ami WHERE"
                + " ami.deleted = 0 AND ami.bankOrderNo = ?1";

        List<Object> filter = new ArrayList<Object>() {{
            add(bankOrderNo);
        }};
        return getRepository().createJpqlQuery(jpql).setParameters(filter)
                .singleResult();

    }


    /**
     * 根据venusApplyNo获取实体类
     *
     * @param bankOrderNo
     * @return
     * @author YixinCapital -- chen.lin
     * 2018年9月13日 下午7:31:06
     */
    public static AssetMainInfo getByVenusApplyNo(String venusApplyNo) {
        String jpql = "SELECT ami FROM AssetMainInfo AS ami WHERE"
                + " ami.deleted = 0 AND ami.venusApplyNo = ?1";

        List<Object> filter = new ArrayList<Object>() {{
            add(venusApplyNo);
        }};
        return getRepository().createJpqlQuery(jpql).setParameters(filter)
                .singleResult();

    }


    /**
     * 通过申请编号获取 最后一条 k_main_info
     * 不管是不是 逻辑删除
     * 
     * @param applyNo
     * @return 
     * @author YixinCapital -- wangxulong
     *	       2018年10月26日 下午2:56:11
     */
    public static AssetMainInfo getLastMainInfoByApplyNo(String applyNo) {
        String jpql = "FROM AssetMainInfo AS ami WHERE"
                + " ami.applyNo = ?1 order by createTime desc";
        @SuppressWarnings("unchecked")
		List<AssetMainInfo> lis =  getQueryChannel().createJpqlQuery(jpql).setParameters(applyNo).list();
		
        if (CollectionUtils.isEmpty(lis)) {
        	return null;
        }
        return lis.get(0);
    }
    
    
    /**
     * 根据applyNo获取实体类
     *
     * @param applyNo
     * @return
     * @author liuhongshen
     */
    public static AssetMainInfo getByApplyNo(String applyNo) {
        String jpql = "SELECT ami FROM AssetMainInfo AS ami WHERE"
                + " ami.deleted = 0 AND ami.applyNo = ?1";

        List<Object> filter = new ArrayList<Object>() {{
            add(applyNo);
        }};
        return getRepository().createJpqlQuery(jpql).setParameters(filter)
                .singleResult();

    }


    /**
     * 根据applyNo获取信审通过的订单信息
     *
     * @return
     */
    public static AssetMainInfo getByApplyNoPayment(String applyNo) {
        String jpql = "SELECT ami FROM AssetMainInfo AS ami WHERE"
                + " ami.deleted = 0 AND ami.applyNo = ?1"
                + " and ami.lastState = ?2";

        List<Object> params = new ArrayList<Object>(2) {{
            add(applyNo);
            add(AssetStateEnum.SUCCESS.getState());
        }};
        return getRepository().createJpqlQuery(jpql.toString()).setParameters(params).singleResult();
    }


    /**
     * 根据applyNo获取请款通过的订单信息
     *
     * @return
     */
    public static AssetMainInfo getPaymentSuccessByApplyNo(String applyNo) {
        String jpql = "SELECT ami FROM AssetMainInfo AS ami WHERE"
                + " ami.deleted = 0 AND ami.applyNo = ?1"
                + " and ami.paymentState = ?2";

        List<Object> params = new ArrayList<Object>(2) {{
            add(applyNo);
            add(1);
        }};
        return getRepository().createJpqlQuery(jpql.toString()).setParameters(params).singleResult();
    }


    public static boolean isExitByApplyNo(String applyNo) {
        String jpql = "SELECT ami FROM AssetMainInfo AS ami WHERE"
                + " ami.deleted = 0 and ami.applyNo = ?1";
        List<Object> params = new ArrayList<Object>(1) {{
            add(applyNo);
        }};
        AssetMainInfo assetMainInfo = getRepository().createJpqlQuery(jpql.toString()).setParameters(params).singleResult();
        return assetMainInfo != null;
    }


    /**
     * 根据订单编号获取所属阶段
     *
     * @param applyNo 订单编号
     * @return
     * @author YixinCapital -- wangwenlong
     * 2018年6月11日 下午3:29:14
     */
    public static String getAssetPhaseByApplyNo(String applyNo) {
        if (StringUtils.isBlank(applyNo)) {
            return null;
        }
        AssetMainInfo mainInfo = AssetMainInfo.getByApplyNo(applyNo);
        return mainInfo == null ? null : mainInfo.getAssetPhase();
    }

    /**
     * 创建信审阶段
     *
     * @param applyNo 申请编号
     * @return
     * @author YixinCapital -- wangwenlong
     * 2018年7月23日 下午6:11:44
     */
    public static String createWBTrialCreditAssetMainInfo(String applyNo) {
        DscSalesApplyMain applyMainInfo = DscSalesApplyMain.getByApplyNo(applyNo);
        if (applyMainInfo == null || StringUtils.isBlank(applyMainInfo.getCapitalId())) {
            return "";
        }
        AssetFinanceInfo financeInfo = AssetFinanceInfo.get(AssetFinanceInfo.class, applyMainInfo.getCapitalId());
        if (financeInfo == null) {
            return "";
        }
        AssetMainInfo mainInfo = AssetMainInfo.getByApplyNo(applyNo);
        if (mainInfo == null) {
            mainInfo = new AssetMainInfo();

            mainInfo.setApplyNo(applyNo); // 申请编号
            mainInfo.setAssetPhase(null);
            mainInfo.setCreditSignState(AssetStateEnum.SUCCESS.getState());
            mainInfo.setContractSignState(AssetStateEnum.INIT.getState());
            mainInfo.setFirstState(AssetStateEnum.INIT.getState());
            mainInfo.setLastState(AssetStateEnum.INIT.getState());
            mainInfo.setPreState(null);
            mainInfo.setPaymentState(AssetStateEnum.INIT.getState());
            mainInfo.setAssetNo(null); //微众申请编号
            mainInfo.setBankOrderNo(null); //微众订单编号
            mainInfo.setChannelCode(applyMainInfo.getDealerChannelCode()); // 渠道编号
            mainInfo.setChannelName(applyMainInfo.getDealerChannelName()); // 渠道名称
            mainInfo.setCompanyCode(applyMainInfo.getRentingCompanyCode()); // 发起融资公司编号
            mainInfo.setFinancialCode(financeInfo.getCode()); //资金方编码
            mainInfo.setFinancialId(financeInfo.getId()); //资金方ID

            //查询mongo，如果mongo有值，说明此单子是之前取消重提的
            try {
                MongoTemplate mongoTemplate = SpringContextUtil.getApplicationContext().getBean(MongoTemplate.class);
                Query query = new Query();
                query.addCriteria(Criteria.where("applyNo").is(applyNo));
                List<WBMongoDTO> mongoDTOList = mongoTemplate.find(query, WBMongoDTO.class);
                LOGGER.info("保存客户电子征信信息,applyNo:{},查询mongo中订单的信息:{}", applyNo, JSON.toJSONString(mongoDTOList));
                if (CollectionUtils.isNotEmpty(mongoDTOList)) {
                    WBMongoDTO wbMongoDTO = mongoDTOList.get(0);
                    if (StringUtils.isNotBlank(wbMongoDTO.getAppNo())) {
                        LOGGER.info("保存客户电子征信信息,applyNo:{},mongo中订单信息微众申请编号AppNo:{} 不为空,代表一审已经通过", applyNo, wbMongoDTO.getAppNo());
                        mainInfo.setFirstState(AssetStateEnum.SUCCESS.getState());
                        mainInfo.setLastState(AssetStateEnum.INIT.getState());
                        mainInfo.setAssetNo(wbMongoDTO.getAppNo()); //微众申请编号
                        mainInfo.setBankOrderNo(wbMongoDTO.getNbsOrderNo()); //微众订单编号
                    }
                }
            } catch (Exception e) {
                LOGGER.error("微众保存客户电子征信信息，查询mongo订单信息异常,订单编号:{}", applyNo, e);
            }
            return mainInfo.create();
        } else {
            mainInfo.setCreditSignState(AssetStateEnum.SUCCESS.getState());
            return mainInfo.update();
        }
    }


    /**
     * 查询处于等待银行信审结果状态的订单
     *
     * @return
     */
    public static List<AssetMainInfo> getWaitBankAuditOrderList() {
        String jpql = "SELECT ami FROM AssetMainInfo AS ami WHERE"
                + " ami.deleted = 0 AND (ami.firstState = 3 or ami.lastState = 3)";
        return getRepository().createJpqlQuery(jpql).list();
    }


    /**
     * 查询处于等待银行放款结果状态的订单
     *
     * @return
     */
    public static List<AssetMainInfo> getWaitFundsRequestOrderList() {
        String jpql = "SELECT ami FROM AssetMainInfo AS ami WHERE"
                + " ami.deleted = 0 AND ami.paymentState=3";
        return getRepository().createJpqlQuery(jpql).list();
    }


    /**
     * 查询已完成信审但未完成请款的申请单
     *
     * @return
     */
    public static List<AssetMainInfo> getSubmitFundsRequestOrderList() {
        String jpql = "SELECT ami FROM AssetMainInfo AS ami WHERE"
                + " ami.deleted = 0 and ami.lastState = 1 AND (ami.paymentState is null or ami.paymentState = 0)";
        return getRepository().createJpqlQuery(jpql).list();
    }




    /**
     * 根据身份证号码.获取所有订单交易状态列表
     * @param IDNo
     * @return
     * @author xy
     */
    public static List<AssetMainInfo> getAssetByIDNo(String IDNo,String capitalId){


        String sql = "SELECT info FROM DscSalesApplyMain main LEFT JOIN DscSalesApplyCust cust ON cust.mainId = main.id LEFT JOIN AssetMainInfo info ON main.applyNo = info.applyNo WHERE cust.azjhm = :idNo AND main.capitalId = :capID";
        HashMap<String, Object> map = new HashMap<>();
        map.put("idNo",IDNo);
        map.put("capID",capitalId);

        try {
            return getRepository().createJpqlQuery(sql).setParameters(map).list();
        } catch (Exception e) {
            logger.error("通过身份证号查询异常:{}",IDNo,e);
        }
        return null;
    }

    /**
     * 根据身份证号码.获取所有订单交易状态列表
     * @param IDNo
     * @return
     * @author xy
     */
//    public static List<AssetMainInfo> getAssetByIDNo(String IDNo,String capitalId){
//
//
//        String sql = "SELECT info FROM DscSalesApplyMain main LEFT JOIN DscSalesApplyCust cust ON cust.mainId = main.id LEFT JOIN AssetMainInfo info ON main.applyNo = info.applyNo WHERE cust.azjhm =?1 AND main.capitalId =?2";
//        List<Object> params = new ArrayList<>();
//        params.add(IDNo);
//        params.add(capitalId);
//
//        try {
//            return getRepository().createJpqlQuery(sql).setParameters(params).list();
//        } catch (Exception e) {
//            logger.error("通过身份证号查询异常:{}",IDNo,e);
//        }
//        return null;
//    }
}