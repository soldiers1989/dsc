package com.yixin.kepler.v1.datapackage.dto.yntrust;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 创建订单-合同信息
 *
 * @author YixinCapital -- chenjiacheng
 *         2018年09月19日 11:03
 **/

public class YTCreateOrderLoanDto {

	/**
	 * 贷款合同号
	 */
	@JsonProperty("LoanContractNumber")
	private String loanContractNumber;

	/**
	 * 合同金额  (15,2)
	 */
	@JsonProperty("ContractAmount")
	private BigDecimal contractAmount;

	/**
	 * 签约日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
	@JsonProperty("SignDate")
	private Date signDate;

	/**
	 * 开始日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
	@JsonProperty("BeginDate")
	private Date beginDate;

	/**
	 * 到期日期
	 */
	@JsonFormat(pattern = "yyyy-MM-dd hh:mm:ss", timezone = "GMT+8")
	@JsonProperty("EndDate")
	private Date endDate;

	/**
	 * 签约利率 保留四位小数
	 */
	@JsonProperty("SignRate")
	private BigDecimal signRate;

	/**
	 * 付息周期
	 */
	@JsonProperty("RepaymentCycle")
	private String repaymentCycle;

	/**
	 * 还款方式
	 */
	@JsonProperty("RepaymentMode")
	private String repaymentMode;

	/**
	 * 还款期数
	 */
	@JsonProperty("RepaymentPeriod")
	private Integer repaymentPeriod;

	/**
	 * 合作平台产品名称
	 */
	@JsonProperty("PartnerProductName")
	private String partnerProductName;

	/**
	 * 贷款备注
	 */
	@JsonProperty("LoanPurpose")
	private String loanPurpose;

	/**
	 * 合作平台评级
	 */
	@JsonProperty("PlatformRating")
	private String platformRating;

	/**
	 * 贷款用途
	 */
	@JsonProperty("LoanUsage")
	private String loanUsage;


	/**
	 * 还款计划
	 */
	@JsonProperty("RepaySchedules")
	private List<YTCreateOrderRepaySchedulesDto> repaySchedules;


	/**
	 * 放款信息结合
	 */
	@JsonProperty("PaymentBankCards")
	private List<YTCreateOrderPaymentBankCardsDto> paymentBankCards;


	public String getLoanContractNumber() {
		return loanContractNumber;
	}

	public void setLoanContractNumber(String loanContractNumber) {
		this.loanContractNumber = loanContractNumber;
	}

	public BigDecimal getContractAmount() {
		return contractAmount;
	}

	public void setContractAmount(BigDecimal contractAmount) {
		this.contractAmount = contractAmount;
	}

	public Date getSignDate() {
		return signDate;
	}

	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}

	public Date getBeginDate() {
		return beginDate;
	}

	public void setBeginDate(Date beginDate) {
		this.beginDate = beginDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public BigDecimal getSignRate() {
		return signRate;
	}

	public void setSignRate(BigDecimal signRate) {
		this.signRate = signRate;
	}

	public String getRepaymentCycle() {
		return repaymentCycle;
	}

	public void setRepaymentCycle(String repaymentCycle) {
		this.repaymentCycle = repaymentCycle;
	}

	public String getRepaymentMode() {
		return repaymentMode;
	}

	public void setRepaymentMode(String repaymentMode) {
		this.repaymentMode = repaymentMode;
	}

	public Integer getRepaymentPeriod() {
		return repaymentPeriod;
	}

	public void setRepaymentPeriod(Integer repaymentPeriod) {
		this.repaymentPeriod = repaymentPeriod;
	}

	public String getPartnerProductName() {
		return partnerProductName;
	}

	public void setPartnerProductName(String partnerProductName) {
		this.partnerProductName = partnerProductName;
	}

	public String getLoanPurpose() {
		return loanPurpose;
	}

	public void setLoanPurpose(String loanPurpose) {
		this.loanPurpose = loanPurpose;
	}

	public String getPlatformRating() {
		return platformRating;
	}

	public void setPlatformRating(String platformRating) {
		this.platformRating = platformRating;
	}

	public String getLoanUsage() {
		return loanUsage;
	}

	public void setLoanUsage(String loanUsage) {
		this.loanUsage = loanUsage;
	}

	public List<YTCreateOrderRepaySchedulesDto> getRepaySchedules() {
		return repaySchedules;
	}

	public void setRepaySchedules(List<YTCreateOrderRepaySchedulesDto> repaySchedules) {
		this.repaySchedules = repaySchedules;
	}

	public List<YTCreateOrderPaymentBankCardsDto> getPaymentBankCards() {
		return paymentBankCards;
	}

	public void setPaymentBankCards(List<YTCreateOrderPaymentBankCardsDto> paymentBankCards) {
		this.paymentBankCards = paymentBankCards;
	}
}
