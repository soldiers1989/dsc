package com.yixin.kepler.dto.settle;

import java.io.Serializable;
import java.math.BigDecimal;

public class SettleFinanceProjectDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * 融资项目编码
	 */
	private String financeProjectCode;
	/**
	 * 融资项目金额
	 */
	private BigDecimal financeProjectAmount;
	/**
	 * 融资项目放款金额
	 */
	private BigDecimal financeProjectLoanAmount;
	/**
	 * 是否必融
	 */
	private String isMustFinance;
	/**
	 * 是否直放
	 */
	private String isDirect;

	public String getFinanceProjectCode() {
		return financeProjectCode;
	}

	public void setFinanceProjectCode(String financeProjectCode) {
		this.financeProjectCode = financeProjectCode;
	}

	public BigDecimal getFinanceProjectAmount() {
		return financeProjectAmount;
	}

	public void setFinanceProjectAmount(BigDecimal financeProjectAmount) {
		this.financeProjectAmount = financeProjectAmount;
	}

	public BigDecimal getFinanceProjectLoanAmount() {
		return financeProjectLoanAmount;
	}

	public void setFinanceProjectLoanAmount(BigDecimal financeProjectLoanAmount) {
		this.financeProjectLoanAmount = financeProjectLoanAmount;
	}

	public String getIsMustFinance() {
		return isMustFinance;
	}

	public void setIsMustFinance(String isMustFinance) {
		this.isMustFinance = isMustFinance;
	}

	public String getIsDirect() {
		return isDirect;
	}

	public void setIsDirect(String isDirect) {
		this.isDirect = isDirect;
	}

}
