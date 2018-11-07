package com.yixin.dsc.dto;

import java.io.Serializable;

/**
 * 电子征信存证DTO
 * Package : com.yixin.dsc.dto
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月4日 下午5:07:36
 *
 */
public class DscElecCreditEvidenceDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5030493951012867198L;

	/**
	 * 申请编号/订单编号
	 */
	private String applyNo;
	
	/**
	 * 征信结果
	 */
	private Boolean creditResult;
	
	/**
	 * 征信类型 1/微众  2/易鑫+微众
	 */
	private String creditType;
	
	/**
	 * 征信信息
	 */
	private DscElecActionInfoDto creditInfo;
	/**
	 * 0/电子征信 1/电子合同签约
	 */
	private String type;
	

	public String getCreditType() {
		return creditType;
	}

	public void setCreditType(String creditType) {
		this.creditType = creditType;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public Boolean getCreditResult() {
		return creditResult;
	}

	public void setCreditResult(Boolean creditResult) {
		this.creditResult = creditResult;
	}

	public DscElecActionInfoDto getCreditInfo() {
		return creditInfo;
	}

	public void setCreditInfo(DscElecActionInfoDto creditInfo) {
		this.creditInfo = creditInfo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
