package com.yixin.dsc.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 后台取消返回DTO
 * Package : com.yixin.dsc.dto
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年9月10日 下午5:07:56
 *
 */
public class DscContractCancelDto implements Serializable {

	private static final long serialVersionUID = -3606567105346889643L;

	/**
	 * 取消码值 参考枚举 CancelResultEnum
	 */
	private String cancelResult;
	
	private String message;
	
	private BigDecimal bankInterest;

	/**
	 * 第一还款日
	 */
	private Date firstRepayDate;
	
	/**
	 * 当前日期
	 */
	private Date currentDate;
	
	/**
	 * 是否放款 0:未放款，1:放款成功
	 */
	private String isLoan;
	
	/**
	 * 1:允许推车，0:不允许推车
	 */
	private String isAllowed;
	
	public String getIsAllowed() {
		return isAllowed;
	}

	public void setIsAllowed(String isAllowed) {
		this.isAllowed = isAllowed;
	}

	public Date getFirstRepayDate() {
		return firstRepayDate;
	}

	public void setFirstRepayDate(Date firstRepayDate) {
		this.firstRepayDate = firstRepayDate;
	}

	public Date getCurrentDate() {
		return currentDate;
	}

	public void setCurrentDate(Date currentDate) {
		this.currentDate = currentDate;
	}

	public String getIsLoan() {
		return isLoan;
	}

	public void setIsLoan(String isLoan) {
		this.isLoan = isLoan;
	}

	public String getCancelResult() {
		return cancelResult;
	}

	public void setCancelResult(String cancelResult) {
		this.cancelResult = cancelResult;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public BigDecimal getBankInterest() {
		return bankInterest;
	}

	public void setBankInterest(BigDecimal bankInterest) {
		this.bankInterest = bankInterest;
	}
}
