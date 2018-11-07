package com.yixin.kepler.dto.webank;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class WBExtraInfo implements Serializable {

	private static final long serialVersionUID = 2843872320468409409L;
	/**
	 * 人行客户分类
	 */
	@JsonProperty("PBC_CUST_TYPE")  
	private String pbcCustType;
	/**
	 * 最低首付比例
	 */
	@JsonProperty("MIN_PAY_RATIO")  
	private String minPayRatio;
	/**
	 * 最高额度上限
	 */
	@JsonProperty("MAX_CREDIT_LIMIT")  
	private String maxCreditLimit;
	
	
	public WBExtraInfo() {
		super();
	}
	public String getPbcCustType() {
		return pbcCustType;
	}
	public void setPbcCustType(String pbcCustType) {
		this.pbcCustType = pbcCustType;
	}
	public String getMinPayRatio() {
		return minPayRatio;
	}
	public void setMinPayRatio(String minPayRatio) {
		this.minPayRatio = minPayRatio;
	}
	public String getMaxCreditLimit() {
		return maxCreditLimit;
	}
	public void setMaxCreditLimit(String maxCreditLimit) {
		this.maxCreditLimit = maxCreditLimit;
	}
}
