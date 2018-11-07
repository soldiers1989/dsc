package com.yixin.kepler.enity;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.dsc.dto.DscFlowResultForAlixDto;
import com.yixin.dsc.enumpackage.DscAlixLinkEnum;
import com.yixin.dsc.v1.common.enumpackage.NoticeBusiTypeEnum;
import com.yixin.dsc.v1.datapackage.dto.notice.DscAsyncMessageDto;
import org.apache.commons.collections.CollectionUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * 订单流程表
 * Package : com.yixin.kepler.enity
 *
 * @author YixinCapital -- wangwenlong
 *         2018年9月3日 下午1:50:07
 */
@Entity
@Table(name = "k_asset_order_flow")
public class AssetOrderFlow extends BZBaseEntiy {

    private static final long serialVersionUID = 3532485901179873937L;

    /**
     * 申请编号
     */
    @Column(name = "apply_no", unique = true, nullable = false, columnDefinition = "varchar(64) comment '申请编号'")
    private String applyNo;

    /**
     * 信审发起时间
     */
    @Column(name = "creditfront_send_time", columnDefinition = "datetime comment '信审发起时间'")
    private Date creditfrontSendTime;

    /**
     * 信审结束时间
     */
    @Column(name = "creditfront_end_time", columnDefinition = "datetime comment '信审结束时间'")
    private Date creditfrontEndTime;

    /**
     * 信审阶段给提报端的消息
     */
    @Column(name = "creditfront_message", columnDefinition = "LONGTEXT comment '信审阶段给提报端的消息'")
    private String creditfrontMessage;

    /**
     * 请款发起时间
     */
    @Column(name = "request_funds_send_time", columnDefinition = "datetime comment '请款发起时间'")
    private Date requestFundsSendTime;

    /**
     * 请款结束时间
     */
    @Column(name = "request_funds_end_time", columnDefinition = "datetime comment '请款结束时间'")
    private Date requestFundsEndTime;

    /**
     * 请款阶段给提报端的消息
     */
    @Column(name = "request_funds_message", columnDefinition = "LONGTEXT comment '请款阶段给提报端的消息'")
    private String requestFundsMessage;

    /**
     * 放款时间
     */
    @Column(name = "loan_time", columnDefinition = "datetime comment '放款时间'")
    private Date loanTime;

    /**
     * 放款详情
     */
    @Column(name = "loan_info", columnDefinition = "varchar(1024) comment '放款详情'")
    private String loanInfo;


    /**
     * 合同签署，通知alix时间
     */
    @Column(name = "contract_sign_end_time", columnDefinition = "datetime comment '合同签署，通知alix时间'")
    private Date contractSignEndTime;

    /**
     * 请款阶段给提报端的消息
     */
    @Column(name = "contract_sign_message", columnDefinition = "LONGTEXT comment '合同签署，通知alix消息'")
    private String contractSignMessage;

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public Date getCreditfrontSendTime() {
        return creditfrontSendTime;
    }

    public void setCreditfrontSendTime(Date creditfrontSendTime) {
        this.creditfrontSendTime = creditfrontSendTime;
    }

    public Date getCreditfrontEndTime() {
        return creditfrontEndTime;
    }

    public void setCreditfrontEndTime(Date creditfrontEndTime) {
        this.creditfrontEndTime = creditfrontEndTime;
    }

    public String getCreditfrontMessage() {
        return creditfrontMessage;
    }

    public void setCreditfrontMessage(String creditfrontMessage) {
        this.creditfrontMessage = creditfrontMessage;
    }

    public Date getRequestFundsSendTime() {
        return requestFundsSendTime;
    }

    public void setRequestFundsSendTime(Date requestFundsSendTime) {
        this.requestFundsSendTime = requestFundsSendTime;
    }

    public Date getRequestFundsEndTime() {
        return requestFundsEndTime;
    }

    public void setRequestFundsEndTime(Date requestFundsEndTime) {
        this.requestFundsEndTime = requestFundsEndTime;
    }

    public String getRequestFundsMessage() {
        return requestFundsMessage;
    }

    public void setRequestFundsMessage(String requestFundsMessage) {
        this.requestFundsMessage = requestFundsMessage;
    }

    public Date getLoanTime() {
        return loanTime;
    }

    public void setLoanTime(Date loanTime) {
        this.loanTime = loanTime;
    }


    public String getLoanInfo() {
        return loanInfo;
    }

    public void setLoanInfo(String loanInfo) {
        this.loanInfo = loanInfo;
    }

    public Date getContractSignEndTime() {
        return contractSignEndTime;
    }

    public void setContractSignEndTime(Date contractSignEndTime) {
        this.contractSignEndTime = contractSignEndTime;
    }

    public String getContractSignMessage() {
        return contractSignMessage;
    }

    public void setContractSignMessage(String contractSignMessage) {
        this.contractSignMessage = contractSignMessage;
    }

    /**
     * 通过aop 记录发起信审/请款时间
     *
     * @param joinPoint
     * @author YixinCapital -- wangwenlong
     * 2018年9月3日 下午3:37:27
     */
    public static void recordSendTimeByAOP(String applyNo, String methodName) {
        if (methodName == null) {
            return;
        }
        AssetOrderFlow orderFLow = getAssetOrderFlowByApplyNo(applyNo);
        orderFLow.setApplyNo(applyNo);
        if (methodName.contains("sendBankCreditfront")) { //发起信审
            orderFLow.setCreditfrontSendTime(new Date()); //信审发起时间
        } else if (methodName.contains("sendBankRequestFunds")) { //发起请款
            orderFLow.setRequestFundsSendTime(new Date()); //请款发起时间
        }
        orderFLow.update();
    }

    ;

    /**
     * 通过aop 记录发起信审/请款时间的结束时间
     *
     * @param obj
     * @author YixinCapital -- wangwenlong
     * 2018年9月4日 下午3:21:38
     */
    public static void recordEndTimeByAOP(String applyNo, String methodName, Object obj) {
        JSONObject jsonObject = null;
        if (obj != null) {
            jsonObject = JSONObject.parseObject(JSON.toJSONString(obj));
        }
        //{"errorMessage":"没有对应申请信息","hasErrors":true,"success":false}
        if (jsonObject != null) {
            Boolean success = jsonObject.get("success") == null ? false : jsonObject.getBoolean("success");
            if (!success) {
                AssetOrderFlow orderFLow = getAssetOrderFlowByApplyNo(applyNo);
                orderFLow.setApplyNo(applyNo);
                if (methodName.contains("sendBankCreditfront")) { //发起信审
                    orderFLow.setCreditfrontEndTime(new Date()); //信审发起时间
                    orderFLow.setCreditfrontMessage(JSON.toJSONString(obj));
                } else if (methodName.contains("sendBankRequestFunds")) { //发起请款
                    orderFLow.setRequestFundsEndTime(new Date()); //请款发起时间
                    orderFLow.setRequestFundsMessage(JSON.toJSONString(obj));
                }
                orderFLow.update();
            }
        }
    }

    /**
     * 通过订单编号获取记录
     *
     * @param applyNo 订单编号
     * @return
     * @author YixinCapital -- wangwenlong
     * 2018年9月3日 下午3:40:34
     */
    public static AssetOrderFlow getAssetOrderFlowByApplyNo(String applyNo) {
        Map<String, Object> propValues = Maps.newHashMap();
        propValues.put("applyNo", applyNo);
        List<AssetOrderFlow> orderFlowList = AssetOrderFlow.findByProperties(AssetOrderFlow.class, propValues);
        if (CollectionUtils.isNotEmpty(orderFlowList)) {
            return orderFlowList.get(0);
        } else {
            return new AssetOrderFlow();
        }
    }

    /**
     * 记录发起信审/请款的结果通知
     *
     * @param dscFlowResultForAlixDto
     * @author YixinCapital -- wangwenlong
     * 2018年9月4日 上午11:10:13
     */
    public static void recordflowResultNotice(DscFlowResultForAlixDto dscFlowResultForAlixDto) {
        if (dscFlowResultForAlixDto != null) {
            String applyNo = dscFlowResultForAlixDto.getApplyNo();
            String link = dscFlowResultForAlixDto.getLink();
            AssetOrderFlow orderFLow = getAssetOrderFlowByApplyNo(applyNo);
            orderFLow.setApplyNo(applyNo);
            if (DscAlixLinkEnum.CREDITFRONT.getCode().equals(link)) { //信审结果异步通知
                orderFLow.setCreditfrontEndTime(new Date()); //信审发起时间
                orderFLow.setCreditfrontMessage(JSON.toJSONString(dscFlowResultForAlixDto));
            } else if (DscAlixLinkEnum.REQUEST_FUNDS.getCode().equals(link)) { //请款结果异步通知
                orderFLow.setRequestFundsEndTime(new Date()); //请款发起时间
                orderFLow.setRequestFundsMessage(JSON.toJSONString(dscFlowResultForAlixDto));
            }
            orderFLow.update();
        }
    }


    /**
     * 记录发起信审/请款的结果通知
     *
     * @param dscFlowResultForAlixDto
     * @author YixinCapital -- wangwenlong
     * 2018年9月4日 上午11:10:13
     */
    public static void record2flowResultNotice(DscFlowResultForAlixDto dscFlowResultForAlixDto) {
        if (dscFlowResultForAlixDto != null) {
            String applyNo = dscFlowResultForAlixDto.getApplyNo();
            String link = dscFlowResultForAlixDto.getLink();
            AssetOrderFlow orderFLow = getAssetOrderFlowByApplyNo(applyNo);
            orderFLow.setApplyNo(applyNo);
            if (DscAlixLinkEnum.CREDITFRONT.getCode().equals(link)) { //信审结果异步通知
                orderFLow.setCreditfrontEndTime(new Date()); //信审发起时间
                orderFLow.setCreditfrontMessage(JSON.toJSONString(dscFlowResultForAlixDto));
            } else if (DscAlixLinkEnum.REQUEST_FUNDS.getCode().equals(link)) { //请款结果异步通知
                orderFLow.setRequestFundsEndTime(new Date()); //请款发起时间
                orderFLow.setRequestFundsMessage(JSON.toJSONString(dscFlowResultForAlixDto));
            }
            orderFLow.update();
        }
    }


    /**
     * 记录放款信息
     *
     * @param applyNo  订单编号
     * @param loanInfo 放款信息
     * @param loanTime 放款时间
     * @author YixinCapital -- wangwenlong
     * 2018年9月4日 下午1:31:50
     */
    public static void recordLoanInfo(String applyNo, Object loanInfo, Date loanTime) {
        AssetOrderFlow orderFLow = getAssetOrderFlowByApplyNo(applyNo);
        orderFLow.setApplyNo(applyNo);
        orderFLow.setLoanInfo(JSON.toJSONString(loanInfo));
        orderFLow.setLoanTime(loanTime);
        orderFLow.update();
    }

    /**
     * 记录给alix的异步消息通知信息
     * <p>
     * 目前是合同签署消息
     *
     * @param dscAsyncMessageDto
     */
    public static void recordAsyncMessageNotice(DscAsyncMessageDto dscAsyncMessageDto) {

        if (dscAsyncMessageDto == null) {
            return;
        }

        String applyNo = dscAsyncMessageDto.getApplyNo();
        String messageType = dscAsyncMessageDto.getMessageType();

        AssetOrderFlow orderFLow = getAssetOrderFlowByApplyNo(applyNo);
        orderFLow.setApplyNo(applyNo);

        if (NoticeBusiTypeEnum.CONTRACT_SIGNATURE.getBusiType().equals(messageType)) {
            orderFLow.setContractSignEndTime(new Date());
            orderFLow.setContractSignMessage(JSON.toJSONString(dscAsyncMessageDto));
        }

        orderFLow.update();
    }
}
