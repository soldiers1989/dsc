package com.yixin.kepler.dto.webank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 请款操作返回数据载体子属性dto
 * @author YixinCapital -- chenjiacheng
 *         2018年07月09日 19:46
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class WBPaymentLoanReceiptDTO {

	/**
	 * 借据号
	 */
	@JsonProperty("LOAN_RECEIPT_NBR")
	private String loanReceiptNbr;

	/**
	 * 借据类型  C--车贷；F--附加贷
	 */
	@JsonProperty("LOAN_RECEIPT_TYPE")
	private String loanReceiptType;




	public String getLoanReceiptNbr() {
		return loanReceiptNbr;
	}

	public void setLoanReceiptNbr(String loanReceiptNbr) {
		this.loanReceiptNbr = loanReceiptNbr;
	}

	public String getLoanReceiptType() {
		return loanReceiptType;
	}

	public void setLoanReceiptType(String loanReceiptType) {
		this.loanReceiptType = loanReceiptType;
	}

}
