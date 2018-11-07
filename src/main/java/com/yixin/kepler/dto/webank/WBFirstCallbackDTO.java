package com.yixin.kepler.dto.webank;

import java.io.Serializable;
import java.util.Date;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yixin.common.utils.JsonObjectUtils;

/**
 * 微众一审回调结果入参
 * Package : com.yixin.kepler.dto.webank
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月10日 上午10:52:34
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WBFirstCallbackDTO implements Serializable {

	private static final long serialVersionUID = -3021773764215995066L;

	/**
	 * 交易服务码, 通过这个字段标识调用微众哪个接口，每个接口对应一个编码
	 */
	@JsonProperty("TXN_ID")
	private String txnId;
	/**
	 * 平台ID 微众分配的固定值
	 * 	850130008290001（上海易鑫融资租赁有限公司）
	 *  850130008110001（天津恒通嘉合融资租赁有限公司）
	 */
	@JsonProperty("MERCHANT_ID")  
	private String merchantId;
	
	/**
	 * 申请编号
	 */
	@JsonProperty("APP_NO")
	private String appNo;
	
	/**
	 * 审批状态
	 */
	@JsonProperty("APS_STATUS")  
	private String apsStatus;
	
	/**
	 * 审批拒绝原因
	 */
	@JsonProperty("APS_CODE")  
	private String apsCode;
	
	/**
	 * 审批描述
	 */
	@JsonProperty("APS_DESC")  
	private String apsDesc;
	
	/**
	 * 贷款卡号
	 */
	@JsonProperty("CNC_CARD_NO")  
	private String cncCardNo;
	
	/**
	 * 姓名
	 */
	@JsonProperty("NAME")  
	private String name;
	
	/**
	 * 证件提交类型 身份证类型：01
	 */
	@JsonProperty("ID_TYPE")  
	private String idType;
	
	/**
	 * 证件号码
	 */
	@JsonProperty("ID_NO")  
	private String idNo;
	
	/**
	 * 审批有效日期
	 */
	@JsonProperty("APP_EXPIRY_DATE")
	@JsonFormat(pattern = "yyyyMMdd")
	private Date appExpiryDate;
	
	/**
	 * 业务流水号 对应90031初审同步应答个合作方的BIZ_SEQ_NO
	 */
	@JsonProperty("BIZ_SEQ_NO")  
	private String bizSeqNo;
	
	/**
	 * 平台流水 对应90031初审合作方上送的MER_BIZ_NO
	 */
	@JsonProperty("MER_BIZ_NO")  
	private String merBizNo;
	
	/**
	 * 规则结果集及人行衍生变量 EXTRA_INFO对象序列化后的JSON串
	 */
	@JsonProperty("PSB_PARAMS")  
	private String psbParams;
	
	/**
	 * 解析 psbParams JSON
	 */ 
	private WBExtraInfo extraInfo;
	
	public WBExtraInfo getExtraInfo() {
		if(StringUtils.isNotBlank(psbParams) && extraInfo == null){
			extraInfo = (WBExtraInfo) JsonObjectUtils.jsonToObject(psbParams, new WBExtraInfo());
		}
		return extraInfo;
	}
	
	public String getTxnId() {
		return txnId;
	}

	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}

	public String getMerchantId() {
		return merchantId;
	}

	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}

	public void setExtraInfo(WBExtraInfo extraInfo) {
		this.extraInfo = extraInfo;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getApsStatus() {
		return apsStatus;
	}

	public void setApsStatus(String apsStatus) {
		this.apsStatus = apsStatus;
	}

	public String getApsCode() {
		return apsCode;
	}

	public void setApsCode(String apsCode) {
		this.apsCode = apsCode;
	}

	public String getApsDesc() {
		return apsDesc;
	}

	public void setApsDesc(String apsDesc) {
		this.apsDesc = apsDesc;
	}

	public String getCncCardNo() {
		return cncCardNo;
	}

	public void setCncCardNo(String cncCardNo) {
		this.cncCardNo = cncCardNo;
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

	public Date getAppExpiryDate() {
		return appExpiryDate;
	}

	public void setAppExpiryDate(Date appExpiryDate) {
		this.appExpiryDate = appExpiryDate;
	}

	public String getBizSeqNo() {
		return bizSeqNo;
	}

	public void setBizSeqNo(String bizSeqNo) {
		this.bizSeqNo = bizSeqNo;
	}

	public String getMerBizNo() {
		return merBizNo;
	}

	public void setMerBizNo(String merBizNo) {
		this.merBizNo = merBizNo;
	}

	public String getPsbParams() {
		return psbParams;
	}

	public void setPsbParams(String psbParams) {
		this.psbParams = psbParams;
	}
}
