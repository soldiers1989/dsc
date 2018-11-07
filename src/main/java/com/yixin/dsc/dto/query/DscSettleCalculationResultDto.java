package com.yixin.dsc.dto.query;

import java.io.Serializable;

/**
 * 民生银行试算结果dto
 * @author YixinCapital -- chenjiacheng
 *         2018年09月10日 10:06
 **/

public class DscSettleCalculationResultDto implements Serializable {

	private static final long serialVersionUID = 5294181309828473964L;

	/**
	 * 申请编号
	 */
	private String applyNo;

	/**
	 * 试算申请日期
	 */
	private String apptdt;

	/**
	 * 业务日期
	 */
	private String businessDate;

	/**
	 * 费用
	 */
	private String fee;

	/**
	 * 	滞纳金
	 */
	private String forfeit;

	/**
	 * 利息
	 */
	private String interest;

	/**
	 * 逾期金额
	 */
	private String overdueAmount;

	/**
	 * 罚息
	 */
	private String penalty;

	/**
	 * 期数
	 */
	private String period;

	/**
	 * 本金
	 */
	private String principal;

	/**
	 * 剩余本金
	 */
	private String remainPrincipal;

	/**
	 * 总金额
	 */
	private String totalAmount;

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getApptdt() {
		return apptdt;
	}

	public void setApptdt(String apptdt) {
		this.apptdt = apptdt;
	}

	public String getBusinessDate() {
		return businessDate;
	}

	public void setBusinessDate(String businessDate) {
		this.businessDate = businessDate;
	}

	public String getFee() {
		return fee;
	}

	public void setFee(String fee) {
		this.fee = fee;
	}

	public String getForfeit() {
		return forfeit;
	}

	public void setForfeit(String forfeit) {
		this.forfeit = forfeit;
	}

	public String getInterest() {
		return interest;
	}

	public void setInterest(String interest) {
		this.interest = interest;
	}

	public String getOverdueAmount() {
		return overdueAmount;
	}

	public void setOverdueAmount(String overdueAmount) {
		this.overdueAmount = overdueAmount;
	}

	public String getPenalty() {
		return penalty;
	}

	public void setPenalty(String penalty) {
		this.penalty = penalty;
	}

	public String getPeriod() {
		return period;
	}

	public void setPeriod(String period) {
		this.period = period;
	}

	public String getPrincipal() {
		return principal;
	}

	public void setPrincipal(String principal) {
		this.principal = principal;
	}

	public String getRemainPrincipal() {
		return remainPrincipal;
	}

	public void setRemainPrincipal(String remainPrincipal) {
		this.remainPrincipal = remainPrincipal;
	}

	public String getTotalAmount() {
		return totalAmount;
	}

	public void setTotalAmount(String totalAmount) {
		this.totalAmount = totalAmount;
	}
}
