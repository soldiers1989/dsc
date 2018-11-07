package com.yixin.kepler.dto.webank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

/**
 * 微众业务主键信息DTO
 * Package : com.yixin.kepler.dto.webank
 *
 * @author YixinCapital -- wanghonglin
 * 2018/7/9 13:45
 */
public class WBElectronContractSaveRequstDTO extends WBCommonReqDTO implements Serializable{
    /**
     * 平台订单号
     */
    @JsonProperty("MER_ORDER_NO")
    private String merOrderNo;

    /**
     * 微众订单号
     */
    @JsonProperty("NBS_ORDER_NO")
    private String nbsOrderNo;
    /**
     * 产品结构编号
     */
    @JsonProperty("PS_CODE")
    private String psCode;
    /**
     * 用户姓名
     */
    @JsonProperty("NAME")
    private String name;
    /**
     * 证件类型
     */
    @JsonProperty("ID_TYPE")
    private String idType;
    /**
     * 证件号码
     */
    @JsonProperty("ID_NO")
    private String idNo;
    /**
     * 微信OpenId
     */
    @JsonProperty("WX_OPENID")
    private String wxOpenId;
    /**
     * 应用提交类型
     */
    @JsonProperty("APP_TYPE")
    private String appType;
    /**
     * 应用ID
     */
    @JsonProperty("APP_ID")
    private String appId;
    /**
     * 微信 唯一id
     */
    @JsonProperty("WX_UNION_ID")
    private String wxUnionId;
    /**
     * 每期还款金额
     */
    @JsonProperty("TERM_AMOUNT")
    private String termAmount;
    /**
     * 还款日
     */
    @JsonProperty("REPAY_DATE")
    @JsonFormat(pattern = "yyyyMMddHHmmss",timezone="GMT+8")
    private Date repayDate;
    /**
     * 核查方式
     *   FS：刷脸
     *   FC：面核
     *   EC：电核
     */
    @JsonProperty("CHECK_TYPE")
    private String checkType;


    /*** ++++++++++++合同域  +++++++++++++***********/
    @JsonProperty("CONTRACT_BASE")
    private List<WBContractDTO> wbContractList;




    /***++++++++客户行为存证域++++++++++***/
    /**
     *签约日
     */
    @JsonProperty("SIGN_DATE")
    @JsonFormat(pattern = "yyyyMMdd")
    private Date signDate;
    /**
     *签署渠道
     */
    @JsonProperty("SIGN_CHANNEL")
    private String signChannel;
    /**
     *确认借据时间
     */
    @JsonProperty("CONFIRM_LOAN_TIME")
    @JsonFormat(pattern = "yyyyMMddHHmmss",timezone="GMT+8")
    private Date congirmLoanTime;
    /**
     *点击“申请额度”时间
     */
    @JsonProperty("CLICK_APPLY_LIMIT_TIME")
    @JsonFormat(pattern = "yyyyMMddHHmmss",timezone="GMT+8")
    private Date clickApplyLimitTime;
    /**
     *点击“获取验证码”时间
     */
    @JsonProperty("CLICK_SMS_TIME")
    @JsonFormat(pattern = "yyyyMMddHHmmss",timezone="GMT+8")
    private Date clickSmsTime;
    /**
     *系统发送验证码时间
     */
    @JsonProperty("SYS_SEND_SMS_TIME")
    @JsonFormat(pattern = "yyyyMMddHHmmss",timezone="GMT+8")
    private Date sysSendSmsTime;
    /**
     *接收验证码手机号码
     */
    @JsonProperty("CHECK_SMS_MOBILE")
    private String checkSmsMobile;
    /**
     *验短通过时间
     */
    @JsonProperty("CHECK_SMS_SUC_TIME")
    @JsonFormat(pattern = "yyyyMMddHHmmss",timezone="GMT+8")
    private Date checkSmsSucTime;
    /**
     *验密通过时间
     */
    @JsonProperty("CHECK_PWD_SUC_TIME")
    @JsonFormat(pattern = "yyyyMMddHHmmss",timezone="GMT+8")
    private Date checkPwdSucTime;
    /**
     *提交申请时间
     */
    @JsonProperty("APPLY_TIME")
    @JsonFormat(pattern = "yyyyMMddHHmmss",timezone="GMT+8")
    private Date applyTime;
    /**
     *OTP验证发送次数
     */
    @JsonProperty("OTP_SEND_TIME")
    private Integer otpSendTime;
    /**
     *OTP验证失败次数
     */
    @JsonProperty("OTP_ERR_TIME")
    private Integer otpErrTime;

    /**++++++++++++++++++++用户系统环境域+++++++++++++++++**/
    /**
     *操作系统
     */
    @JsonProperty("OS_TYPE")
    private String osType;
    /**
     *手机品牌
     */
    @JsonProperty("MOBILE_BRANDS")
    private String mobileBrands;
    /**
     *ios设备必须填写 idfa
     */
    @JsonProperty("IOS_IDFA")
    private String iosIdFa;
    /**
     *andriod设备必须填写
     */
    @JsonProperty("ANDROID_IMEI")
    private String androidImei;
    /**
     *IP
     */
    @JsonProperty("IP")
    private String ip;
    /**
     *mac地址
     */
    @JsonProperty("MAC_ADDR")
    private String macAddr;

    public String getMerOrderNo() {
        return merOrderNo;
    }

    public void setMerOrderNo(String merOrderNo) {
        this.merOrderNo = merOrderNo;
    }

    public String getNbsOrderNo() {
        return nbsOrderNo;
    }

    public void setNbsOrderNo(String nbsOrderNo) {
        this.nbsOrderNo = nbsOrderNo;
    }

    public String getPsCode() {
        return psCode;
    }

    public void setPsCode(String psCode) {
        this.psCode = psCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getWxOpenId() {
        return wxOpenId;
    }

    public void setWxOpenId(String wxOpenId) {
        this.wxOpenId = wxOpenId;
    }

    public String getAppType() {
        return appType;
    }

    public void setAppType(String appType) {
        this.appType = appType;
    }

    public String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        this.appId = appId;
    }

    public String getWxUnionId() {
        return wxUnionId;
    }

    public void setWxUnionId(String wxUnionId) {
        this.wxUnionId = wxUnionId;
    }

    public String getTermAmount() {
        return termAmount;
    }

    public void setTermAmount(String termAmount) {
        this.termAmount = termAmount;
    }

    public Date getRepayDate() {
        return repayDate;
    }

    public void setRepayDate(Date repayDate) {
        this.repayDate = repayDate;
    }

    public String getCheckType() {
        return checkType;
    }

    public void setCheckType(String checkType) {
        this.checkType = checkType;
    }

    public List<WBContractDTO> getWbContractList() {
        return wbContractList;
    }

    public void setWbContractList(List<WBContractDTO> wbContractList) {
        this.wbContractList = wbContractList;
    }

    public Date getSignDate() {
        return signDate;
    }

    public void setSignDate(Date signDate) {
        this.signDate = signDate;
    }

    public String getSignChannel() {
        return signChannel;
    }

    public void setSignChannel(String signChannel) {
        this.signChannel = signChannel;
    }

    public Date getCongirmLoanTime() {
        return congirmLoanTime;
    }

    public void setCongirmLoanTime(Date congirmLoanTime) {
        this.congirmLoanTime = congirmLoanTime;
    }

    public Date getClickApplyLimitTime() {
        return clickApplyLimitTime;
    }

    public void setClickApplyLimitTime(Date clickApplyLimitTime) {
        this.clickApplyLimitTime = clickApplyLimitTime;
    }

    public Integer getOtpSendTime() {
        return otpSendTime;
    }

    public void setOtpSendTime(Integer otpSendTime) {
        this.otpSendTime = otpSendTime;
    }

    public Integer getOtpErrTime() {
        return otpErrTime;
    }

    public void setOtpErrTime(Integer otpErrTime) {
        this.otpErrTime = otpErrTime;
    }

    public Date getClickSmsTime() {
        return clickSmsTime;
    }

    public void setClickSmsTime(Date clickSmsTime) {
        this.clickSmsTime = clickSmsTime;
    }

    public Date getSysSendSmsTime() {
        return sysSendSmsTime;
    }

    public void setSysSendSmsTime(Date sysSendSmsTime) {
        this.sysSendSmsTime = sysSendSmsTime;
    }

    public String getCheckSmsMobile() {
        return checkSmsMobile;
    }

    public void setCheckSmsMobile(String checkSmsMobile) {
        this.checkSmsMobile = checkSmsMobile;
    }

    public Date getCheckSmsSucTime() {
        return checkSmsSucTime;
    }

    public void setCheckSmsSucTime(Date checkSmsSucTime) {
        this.checkSmsSucTime = checkSmsSucTime;
    }

    public Date getCheckPwdSucTime() {
        return checkPwdSucTime;
    }

    public void setCheckPwdSucTime(Date checkPwdSucTime) {
        this.checkPwdSucTime = checkPwdSucTime;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }
    public void setOtpErrTime(String optErrTime) {
        this.otpErrTime = otpErrTime;
    }

    public String getOsType() {
        return osType;
    }

    public void setOsType(String osType) {
        this.osType = osType;
    }

    public String getMobileBrands() {
        return mobileBrands;
    }

    public void setMobileBrands(String mobileBrands) {
        this.mobileBrands = mobileBrands;
    }

    public String getIosIdFa() {
        return iosIdFa;
    }

    public void setIosIdFa(String iosIdFa) {
        this.iosIdFa = iosIdFa;
    }

    public String getAndroidImei() {
        return androidImei;
    }

    public void setAndroidImei(String androidImei) {
        this.androidImei = androidImei;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public String getMacAddr() {
        return macAddr;
    }

    public void setMacAddr(String macAddr) {
        this.macAddr = macAddr;
    }

}
