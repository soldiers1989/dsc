package com.yixin.kepler.dto.webank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 请款操作返回数据载体dto
 * @author YixinCapital -- chenjiacheng
 *         2018年07月09日 19:42
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class WBPaymentRespDTO extends WBCommonRespDTO {

	private String id;


	@JsonProperty("LOAN_RECEIPT_NBR_LIST")
	private List<WBPaymentLoanReceiptDTO> loanReceiptNbrList;


	public List<WBPaymentLoanReceiptDTO> getLoanReceiptNbrList() {
		return loanReceiptNbrList;
	}

	public void setLoanReceiptNbrList(List<WBPaymentLoanReceiptDTO> loanReceiptNbrList) {
		this.loanReceiptNbrList = loanReceiptNbrList;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}
}
