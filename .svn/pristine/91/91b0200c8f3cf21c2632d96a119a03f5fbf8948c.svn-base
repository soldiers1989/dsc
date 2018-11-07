package com.yixin.dsc.dto.query;

import java.io.Serializable;
import java.util.Date;

/**
 * 结算放款信息DTO
 * Package : com.yixin.dsc.dto.query
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年9月10日 下午7:48:48
 *
 */
public class DscSettleLoanInfoDto implements Serializable {

	private static final long serialVersionUID = -5949694255890191496L;

	/**
	 * 第一个还款日
	 */
	private String firstRepayDate;
	
	/**
	 * 0:未放款，1:放款成功 
	 */
	private String isLoan;
	
	/**
	 * 1:允许推车，0:不允许推车
	 */
	private String isAllowed;
	
	private Date firstRepayDateTime;
	
	public String getIsAllowed() {
		return isAllowed;
	}

	public void setIsAllowed(String isAllowed) {
		this.isAllowed = isAllowed;
	}

	public Date getFirstRepayDateTime() {
		return firstRepayDateTime;
	}

	public void setFirstRepayDateTime(Date firstRepayDateTime) {
		this.firstRepayDateTime = firstRepayDateTime;
	}

	public String getFirstRepayDate() {
		return firstRepayDate;
	}

	public void setFirstRepayDate(String firstRepayDate) {
		this.firstRepayDate = firstRepayDate;
	}

	public String getIsLoan() {
		return isLoan;
	}

	public void setIsLoan(String isLoan) {
		this.isLoan = isLoan;
	}
}
