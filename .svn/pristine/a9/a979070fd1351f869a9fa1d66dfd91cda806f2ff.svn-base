package com.yixin.kepler.dto.webank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;
import java.util.List;

/**
 * 微众一审请求入参
 * Package : com.yixin.kepler.dto.webank
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月9日 上午11:43:33
 *
 */
public class WBFirstRequestDTO extends WBCommonReqDTO {

	private static final long serialVersionUID = 8694439798348172851L;

	/**
	 * 姓名/申请人姓名
	 */
	@JsonProperty("NAME")
	private String akhxm;
	
	/**
	 * 证件提交类型/证件类型
	 */
	@JsonProperty("ID_TYPE")
	private String azjlx;
	
	/**
	 * 证件号码/身份证号码
	 */
	@JsonProperty("ID_NO")
	private String azjhm;
	
	/**
	 * 注册手机号/注册手机号
	 */
	@JsonProperty("REG_MOBILE")
	private String asjhm;
	
	/**
	 * 应用提交类型
	 * O:  qq open ID
	 * W:  微信ID
	 * M:  手机号
	 * C :  卡号
	 * A：应用账号
	 * 无符合选项时默认填 A
	 */
	@JsonProperty("APP_TYPE")
	private String appType = "A";
	
	/**
	 * 用户平台id 填合作方平台系统中的客户唯一编号，如无则填写身份证号码/身份证号码
	 */
	@JsonProperty("REG_USERID")
	private String azjhm2;
	
	/**
	 * 月均收入水平 /税后年薪(万元)/12，映射范围内
	 * 二审传数值
	 */
	@JsonProperty("MONTHLY_INCOME_RANGE")
	private String fshnx;
	
	/**
	 * 纳税居民申明
	 * 1.仅为中国税收居民
	 * 2.仅为非居民
	 * 3.即是中国税收居民又是其他国家（地区）税收居民\
	 * 默认1，2和3微众会拒绝
	 */
	@JsonProperty("TAXABLE_RESIDENTS")
	private String taxableResidents = "1";
	
	/**
	 * 代理人用户id / 提报账号
	 */
	@JsonProperty("AGENT_ID")
	private String salesDomainAccount;
	
	/**
	 * 代理人姓名 / 分公司金融经理
	 */
	@JsonProperty("AGENT_NAME")
	private String financialManagerName;
	
	/**
	 * 代理人手机号 / 销售预留手机
	 */
	@JsonProperty("AGENT_PHONE")
	private String salesPhone;
	
	/**
	 * SP代理人公司所在省 / 经销商渠道所在省名称
	 */
	@JsonProperty("AGENT_COMPANY_PROV")
	private String dealerChannelProvinceName;
	
	/**
	 * SP代理人公司所在市 / 经销商渠道所在市名称
	 */
	@JsonProperty("AGENT_COMPANY_CITY")
	private String dealerChannelCityName;
	
	/**
	 * SP代理人公司详细地址 / 经销商渠道详细地址
	 */
	@JsonProperty("AGENT_COMPANY_ADDR")
	private String dealerChannelDetailedAddress;
	
	/**
	 * SP公司ID / 经销商渠道id
	 */
	@JsonProperty("AGENT_COMPANY_ID")
	private String dealerChannelId;
	
	/**
	 * SP代理人企业名称 / 所属机构
	 */
	@JsonProperty("AGENT_COMPANY_NAME")
	private String affiliatedInstitutions;
	
	/**
	 * 他行银行卡
	 */
	@JsonProperty("BANK_CARD_ACCT")
	private List<WBBankCardDTO> bankCardAcct;
	
	/**
	 * 合同协议
	 */
	@JsonProperty("CONTRACT_BASE")
	private List<WBContractDTO> contractBase;
	
	/***++++++++客户行为存证域++++++++++***/
    
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
     *验密通过时间
     */
    @JsonProperty("CHECK_PWD_SUC_TIME")
    @JsonFormat(pattern = "yyyyMMddHHmmss",timezone="GMT+8")
    private Date checkPwdSucTime;
    
    /**
	 * 验短通过时间
	 */
    @JsonProperty("CHECK_SMS_SUC_TIME")
    @JsonFormat(pattern = "yyyyMMddHHmmss",timezone="GMT+8")
	private Date checkSmsSucTime;
    /**
     *提交申请时间
     */
    @JsonProperty("APPLY_TIME")
    @JsonFormat(pattern = "yyyyMMddHHmmss",timezone="GMT+8")
    private Date applyTime;
    
    /**
     *点击"纳税居民申明"时间
     */
    @JsonProperty("CLICK_TAXABLE_TIME")
    @JsonFormat(pattern = "yyyyMMddHHmmss",timezone="GMT+8")
    private Date clickTaxableTime;
    
    /**
     *OTP验证发送次数
     */
    @JsonProperty("OTP_SEND_TIME")
    private String otpSendTime;
    /**
     *OTP验证失败次数
     */
    @JsonProperty("OTP_ERR_TIME")
    private String optErrTime;

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
    
    /**
     * 平台流水
     */
    @JsonProperty("MER_BIZ_NO")
    private String merBizNo;
    
    
	public String getMerBizNo() {
		return merBizNo;
	}
	public void setMerBizNo(String merBizNo) {
		this.merBizNo = merBizNo;
	}
	public String getAkhxm() {
		return akhxm;
	}
	public void setAkhxm(String akhxm) {
		this.akhxm = akhxm;
	}
	public String getAzjlx() {
		return azjlx;
	}
	public void setAzjlx(String azjlx) {
		this.azjlx = azjlx;
	}
	public String getAzjhm() {
		return azjhm;
	}
	public void setAzjhm(String azjhm) {
		this.azjhm = azjhm;
	}
	public String getAsjhm() {
		return asjhm;
	}
	public void setAsjhm(String asjhm) {
		this.asjhm = asjhm;
	}
	public String getAppType() {
		return appType;
	}
	public void setAppType(String appType) {
		this.appType = appType;
	}
	public String getAzjhm2() {
		return azjhm2;
	}
	public void setAzjhm2(String azjhm2) {
		this.azjhm2 = azjhm2;
	}
	public String getFshnx() {
		return fshnx;
	}
	public void setFshnx(String fshnx) {
		this.fshnx = fshnx;
	}
	public String getTaxableResidents() {
		return taxableResidents;
	}
	public void setTaxableResidents(String taxableResidents) {
		this.taxableResidents = taxableResidents;
	}
	public String getSalesDomainAccount() {
		return salesDomainAccount;
	}
	public void setSalesDomainAccount(String salesDomainAccount) {
		this.salesDomainAccount = salesDomainAccount;
	}
	public String getFinancialManagerName() {
		return financialManagerName;
	}
	public void setFinancialManagerName(String financialManagerName) {
		this.financialManagerName = financialManagerName;
	}
	public String getSalesPhone() {
		return salesPhone;
	}
	public void setSalesPhone(String salesPhone) {
		this.salesPhone = salesPhone;
	}
	public String getDealerChannelProvinceName() {
		return dealerChannelProvinceName;
	}
	public void setDealerChannelProvinceName(String dealerChannelProvinceName) {
		this.dealerChannelProvinceName = dealerChannelProvinceName;
	}
	public String getDealerChannelCityName() {
		return dealerChannelCityName;
	}
	public void setDealerChannelCityName(String dealerChannelCityName) {
		this.dealerChannelCityName = dealerChannelCityName;
	}
	public String getDealerChannelDetailedAddress() {
		return dealerChannelDetailedAddress;
	}
	public void setDealerChannelDetailedAddress(String dealerChannelDetailedAddress) {
		this.dealerChannelDetailedAddress = dealerChannelDetailedAddress;
	}
	public String getDealerChannelId() {
		return dealerChannelId;
	}
	public void setDealerChannelId(String dealerChannelId) {
		this.dealerChannelId = dealerChannelId;
	}
	public String getAffiliatedInstitutions() {
		return affiliatedInstitutions;
	}
	public void setAffiliatedInstitutions(String affiliatedInstitutions) {
		this.affiliatedInstitutions = affiliatedInstitutions;
	}
	public List<WBBankCardDTO> getBankCardAcct() {
		return bankCardAcct;
	}
	public void setBankCardAcct(List<WBBankCardDTO> bankCardAcct) {
		this.bankCardAcct = bankCardAcct;
	}
	public List<WBContractDTO> getContractBase() {
		return contractBase;
	}
	public void setContractBase(List<WBContractDTO> contractBase) {
		this.contractBase = contractBase;
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
	public Date getClickTaxableTime() {
		return clickTaxableTime;
	}
	public void setClickTaxableTime(Date clickTaxableTime) {
		this.clickTaxableTime = clickTaxableTime;
	}
	public String getOtpSendTime() {
		return otpSendTime;
	}
	public void setOtpSendTime(String otpSendTime) {
		this.otpSendTime = otpSendTime;
	}
	public String getOptErrTime() {
		return optErrTime;
	}
	public void setOptErrTime(String optErrTime) {
		this.optErrTime = optErrTime;
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
	public Date getCheckSmsSucTime() {
		return checkSmsSucTime;
	}
	public void setCheckSmsSucTime(Date checkSmsSucTime) {
		this.checkSmsSucTime = checkSmsSucTime;
	}
}
