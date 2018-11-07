package com.yixin.kepler.v1.datapackage.dto.yntrust;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;

/**
 * @author YixinCapital -- chenjiacheng
 *         2018年09月19日 11:18
 **/

public class YTCreateOrderRepaySchedulesDto {

	/**
	 * 还款计划编号
	 */
	@JsonProperty("PartnerScheduleNo")
	private String partnerScheduleNo;

	/**
	 * 还款日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
	@JsonProperty("RepayDate")
	private Date repayDate;

	/**
	 * 还款本金 (18,2)
	 */
	@JsonProperty("RepayPrincipal")
	private BigDecimal repayPrincipal;

	/**
	 * 还款利息
	 */
	@JsonProperty("RepayProfit")
	private BigDecimal repayProfit;

	/**
	 * 技术维护费用
	 */
	@JsonProperty("TechMaintenanceFee")
	private BigDecimal techMaintenanceFee;

	/**
	 * 其他费用
	 */
	@JsonProperty("OtherFee")
	private BigDecimal otherFee;

	/**
	 * 贷款服务费
	 */
	@JsonProperty("LoanServiceFee")
	private BigDecimal loanServiceFee;

	public String getPartnerScheduleNo() {
		return partnerScheduleNo;
	}

	public void setPartnerScheduleNo(String partnerScheduleNo) {
		this.partnerScheduleNo = partnerScheduleNo;
	}

	public Date getRepayDate() {
		return repayDate;
	}

	public void setRepayDate(Date repayDate) {
		this.repayDate = repayDate;
	}

	public BigDecimal getRepayPrincipal() {
		return repayPrincipal;
	}

	public void setRepayPrincipal(BigDecimal repayPrincipal) {
		this.repayPrincipal = repayPrincipal;
	}

	public BigDecimal getRepayProfit() {
		return repayProfit;
	}

	public void setRepayProfit(BigDecimal repayProfit) {
		this.repayProfit = repayProfit;
	}

	public BigDecimal getTechMaintenanceFee() {
		return techMaintenanceFee;
	}

	public void setTechMaintenanceFee(BigDecimal techMaintenanceFee) {
		this.techMaintenanceFee = techMaintenanceFee;
	}

	public BigDecimal getOtherFee() {
		return otherFee;
	}

	public void setOtherFee(BigDecimal otherFee) {
		this.otherFee = otherFee;
	}

	public BigDecimal getLoanServiceFee() {
		return loanServiceFee;
	}

	public void setLoanServiceFee(BigDecimal loanServiceFee) {
		this.loanServiceFee = loanServiceFee;
	}
}
