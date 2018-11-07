package com.yixin.dsc.dto;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 费用明细
 * Package : com.yixin.dsc.dto
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月4日 下午7:39:26
 *
 */
public class DscFeeScheduleDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -6109026944182109746L;

	/** 应还款日期  */
	private String pmtDueDate;
	/** 应还金额  */
	private BigDecimal pmtDueAmt;
	/** 应还本金  */
	private BigDecimal pmtDuePrin;
	/** 应还利息  */
	private BigDecimal pmtDueInt;
	/** 应还费用  */
	private BigDecimal pmtDueFee;
	/** 期数  */
	private Integer pmtTerm;

	public String getPmtDueDate() {
		return pmtDueDate;
	}

	public void setPmtDueDate(String pmtDueDate) {
		this.pmtDueDate = pmtDueDate;
	}

	public BigDecimal getPmtDueAmt() {
		return pmtDueAmt;
	}
	public void setPmtDueAmt(BigDecimal pmtDueAmt) {
		this.pmtDueAmt = pmtDueAmt;
	}
	public BigDecimal getPmtDuePrin() {
		return pmtDuePrin;
	}
	public void setPmtDuePrin(BigDecimal pmtDuePrin) {
		this.pmtDuePrin = pmtDuePrin;
	}
	public BigDecimal getPmtDueInt() {
		return pmtDueInt;
	}
	public void setPmtDueInt(BigDecimal pmtDueInt) {
		this.pmtDueInt = pmtDueInt;
	}
	public BigDecimal getPmtDueFee() {
		return pmtDueFee;
	}
	public void setPmtDueFee(BigDecimal pmtDueFee) {
		this.pmtDueFee = pmtDueFee;
	}
	public Integer getPmtTerm() {
		return pmtTerm;
	}
	public void setPmtTerm(Integer pmtTerm) {
		this.pmtTerm = pmtTerm;
	}
}
