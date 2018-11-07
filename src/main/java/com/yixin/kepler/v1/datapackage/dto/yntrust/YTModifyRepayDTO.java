package com.yixin.kepler.v1.datapackage.dto.yntrust;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author YixinCapital -- chenjiacheng
 *         2018年09月26日 14:59
 **/

public class YTModifyRepayDTO extends YTCommonRequestDTO {

	private static final long serialVersionUID = 5217684408059122950L;

	@JsonProperty("UniqueId")
	private String uniqueId;

	@JsonProperty("BankCode")
	private String bankCode;

	@JsonProperty("BankAccount")
	private String bankAccount;

	@JsonProperty("BankName")
	private String bankName;

	@JsonProperty("BankProvince")
	private String bankProvince;

	@JsonProperty("BankCity")
	private String bankCity;

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankAccount() {
		return bankAccount;
	}

	public void setBankAccount(String bankAccount) {
		this.bankAccount = bankAccount;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getBankProvince() {
		return bankProvince;
	}

	public void setBankProvince(String bankProvince) {
		this.bankProvince = bankProvince;
	}

	public String getBankCity() {
		return bankCity;
	}

	public void setBankCity(String bankCity) {
		this.bankCity = bankCity;
	}
}
