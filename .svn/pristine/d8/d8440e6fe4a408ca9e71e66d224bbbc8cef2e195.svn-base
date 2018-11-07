package com.yixin.kepler.dto.webank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;

/**
 * @author YixinCapital -- chenjiacheng
 *         2018年07月09日 13:45
 **/

public class WBComputerSchedulesDTO {

	/**
	 * 应还款日期
	 */
	@JsonProperty("PMT_DUE_DATE")
	@JsonFormat(pattern = "yyyyMMdd")
	private String pmtDueDate;

	/**
	 * 应还金额
	 */
	@JsonProperty("PMT_DUE_AMT")
	private BigDecimal pmtDueAmt;

	/**
	 * 应还本金
	 */
	@JsonProperty("PMT_DUE_PRIN")
	private BigDecimal pmtDuePrin;

	/**
	 * 应还利息
	 */
	@JsonProperty("PMT_DUE_INT")
	private BigDecimal pmtDueInt;

	/**
	 * 应还费用
	 */
	@JsonProperty("PMT_DUE_FEE")
	private BigDecimal pmtDueFee;

	/**
	 * 期数
	 */
	@JsonProperty("PMT_TERM")
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
