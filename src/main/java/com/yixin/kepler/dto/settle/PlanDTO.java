package com.yixin.kepler.dto.settle;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 还款计划DTO
 * Package : com.yixin.settle.dto.order
 * 
 * @author YixinCapital -- Xin.Li
 *		   2018年9月28日 下午6:27:24
 *
 */
public class PlanDTO implements Serializable {

	/**
	 * 
	 * @author YixinCapital -- Xin.Li
	 *		   2018年9月28日 下午6:23:02
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	//客户还款总期数
	private Integer totalPeriod;
	
	//客户当期还款期数
	private Integer currentPeriod;
	
	//客户计划还款日期  时间格式 yyyymmdd
	private String planRepayDate;
	
	//客户本期应还本金
	private BigDecimal planRepayPrincipal;
	
	//客户本期应还利息
	private BigDecimal planRepayInterest;
	
	//客户本期应还总金额
	private BigDecimal planTotalRepayment;
	
	/** 还款计划每期唯一标识 */
	private String scheduleNo;

	public String getScheduleNo() {
		return scheduleNo;
	}

	public void setScheduleNo(String scheduleNo) {
		this.scheduleNo = scheduleNo;
	}

	public Integer getTotalPeriod() {
		return totalPeriod;
	}

	public void setTotalPeriod(Integer totalPeriod) {
		this.totalPeriod = totalPeriod;
	}

	public Integer getCurrentPeriod() {
		return currentPeriod;
	}

	public void setCurrentPeriod(Integer currentPeriod) {
		this.currentPeriod = currentPeriod;
	}

	public String getPlanRepayDate() {
		return planRepayDate;
	}

	public void setPlanRepayDate(String planRepayDate) {
		this.planRepayDate = planRepayDate;
	}

	public BigDecimal getPlanRepayPrincipal() {
		return planRepayPrincipal;
	}

	public void setPlanRepayPrincipal(BigDecimal planRepayPrincipal) {
		this.planRepayPrincipal = planRepayPrincipal;
	}

	public BigDecimal getPlanRepayInterest() {
		return planRepayInterest;
	}

	public void setPlanRepayInterest(BigDecimal planRepayInterest) {
		this.planRepayInterest = planRepayInterest;
	}

	public BigDecimal getPlanTotalRepayment() {
		return planTotalRepayment;
	}

	public void setPlanTotalRepayment(BigDecimal planTotalRepayment) {
		this.planTotalRepayment = planTotalRepayment;
	}

}

