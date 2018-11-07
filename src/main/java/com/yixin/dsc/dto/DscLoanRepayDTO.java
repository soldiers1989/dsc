package com.yixin.dsc.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yixin.common.utils.serializer.JsonDateDeserializer;
import com.yixin.common.utils.serializer.JsonDateTimeSerializer;

/**
 * @author sukang
 */
public class DscLoanRepayDTO implements Serializable {

	private static final long serialVersionUID = -4112174202600780172L;

	/**
     * 贷款服务费
     */
    private BigDecimal loanServiceFee;

    /**
     * 其他费用
     */
    private BigDecimal otherFee;

    /**
     * 当期还款金额
     */
    private BigDecimal repayAmount;

    /**
     * 还款日期
     */
    @JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
    private Date repayDate;

    /**
     * 还款本金
     */
    private BigDecimal repayPrincipal;

    /**
     * 还款利息
     */
    private BigDecimal repayProfit;

    /**
     * 技术服务费
     */
    private BigDecimal techMaintenanceFee;

    /**
     * 期数
     */
    private Integer term;

	public BigDecimal getLoanServiceFee() {
		return loanServiceFee;
	}

	public void setLoanServiceFee(BigDecimal loanServiceFee) {
		this.loanServiceFee = loanServiceFee;
	}

	public BigDecimal getOtherFee() {
		return otherFee;
	}

	public void setOtherFee(BigDecimal otherFee) {
		this.otherFee = otherFee;
	}

	public BigDecimal getRepayAmount() {
		return repayAmount;
	}

	public void setRepayAmount(BigDecimal repayAmount) {
		this.repayAmount = repayAmount;
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

	public Integer getTerm() {
		return term;
	}

	public void setTerm(Integer term) {
		this.term = term;
	}

}
