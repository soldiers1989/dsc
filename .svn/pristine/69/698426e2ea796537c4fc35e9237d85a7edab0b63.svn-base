package com.yixin.kepler.dto.webank;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 微众响应报文公共头信息
 * @author YixinCapital -- chenjiacheng
 *         2018年07月09日 10:56
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class WBCommonRespDTO implements Serializable{

	private static final long serialVersionUID = 5662828485803869102L;

	/**
	 * 错误码
	 */
	@JsonProperty("CODE")
	private String code;

	/**
	 * 错误描述
	 */
	@JsonProperty("DESC")
	private String desc;

	/**
	 * 交易服务码
	 */
	@JsonProperty("TXN_ID")
	private String txnId;

	/**
	 * 平台ID
	 */
	@JsonProperty("MERCHANT_ID")
	private String merchantId;

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
	 * 业务规则拒绝码
	 */
	@JsonProperty("ERR_CODES")
	private String errCodes;

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
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

	public String getErrCodes() {
		return errCodes;
	}

	public void setErrCodes(String errCodes) {
		this.errCodes = errCodes;
	}
}
