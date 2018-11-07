package com.yixin.kepler.dto.webank;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 微众放款DTO
 * Package : com.yixin.kepler.dto.webank
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月30日 上午11:19:12
 *
 */
public class WBLoanDTO implements Serializable{

	private static final long serialVersionUID = 5249148881817493984L;

	@JsonProperty("LOAN_RECEIPT_NBR")
	private String loanReceiptNbr;		//借据号
	
	@JsonProperty("LOAN_TYPE")
	private String loanType;			//	借据类型
	
	@JsonProperty("LOAN_INIT_PRIN")
	private BigDecimal loanInitPrin;	//	放款金额
	
	@JsonProperty("LOAN_STATUS")
	private String loanStatus;			//	放款结果
	
	@JsonProperty("LOAN_RESULT_DESC")
	private String loanResultDesc;		//	放款结果描述
	
	@JsonProperty("UPDATE_TIME")
	@JsonFormat(pattern = "yyyyMMddHHmmss",timezone="GMT+8")
	private Date updateTime;			//	放款处理时间

	public String getLoanReceiptNbr() {
		return loanReceiptNbr;
	}

	public void setLoanReceiptNbr(String loanReceiptNbr) {
		this.loanReceiptNbr = loanReceiptNbr;
	}

	public String getLoanType() {
		return loanType;
	}

	public void setLoanType(String loanType) {
		this.loanType = loanType;
	}

	public BigDecimal getLoanInitPrin() {
		return loanInitPrin;
	}

	public void setLoanInitPrin(BigDecimal loanInitPrin) {
		this.loanInitPrin = loanInitPrin;
	}

	public String getLoanStatus() {
		return loanStatus;
	}

	public void setLoanStatus(String loanStatus) {
		this.loanStatus = loanStatus;
	}

	public String getLoanResultDesc() {
		return loanResultDesc;
	}

	public void setLoanResultDesc(String loanResultDesc) {
		this.loanResultDesc = loanResultDesc;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

}
