package com.yixin.kepler.dto.webank;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 银行卡DTO
 * Package : com.yixin.kepler.dto.webank
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月9日 下午3:37:40
 *
 */
public class WBBankCardDTO implements Serializable {
	
	private static final long serialVersionUID = -2016213900971901064L;

	/**
	 * 卡用途
	 * 选择输入为3、4
	 * 3、验三
	 * 4、验四
	 */
	@JsonProperty("BANK_CARD_USAGE")
	private String bankCardUsage = "4";
	
	/**
	 * 卡号 / 易鑫验证四要素还款卡号
	 */
	@JsonProperty("BANK_CARD_NO")
	private String bankCardNo;
	
	/**
	 * 合作方通过微众提供的卡号映射表获取
	 */
	@JsonProperty("BANK_CARD_BRNO")
	private String bankCardBrno;
	
	/**
	 * 合作方通过微众提供的卡号映射表获取
	 */
	@JsonProperty("BANK_CARD_BRNAME")
	private String bankCardBrname;
	
	/**
	 * 合作方通过微众提供的卡号映射表获取
	 */
	@JsonProperty("BANK_CARD_RESERVE_PHONE")
	private String bankCardReservePhone;

	public String getBankCardUsage() {
		return bankCardUsage;
	}

	public void setBankCardUsage(String bankCardUsage) {
		this.bankCardUsage = bankCardUsage;
	}

	public String getBankCardNo() {
		return bankCardNo;
	}

	public void setBankCardNo(String bankCardNo) {
		this.bankCardNo = bankCardNo;
	}

	public String getBankCardBrno() {
		return bankCardBrno;
	}

	public void setBankCardBrno(String bankCardBrno) {
		this.bankCardBrno = bankCardBrno;
	}

	public String getBankCardBrname() {
		return bankCardBrname;
	}

	public void setBankCardBrname(String bankCardBrname) {
		this.bankCardBrname = bankCardBrname;
	}

	public String getBankCardReservePhone() {
		return bankCardReservePhone;
	}

	public void setBankCardReservePhone(String bankCardReservePhone) {
		this.bankCardReservePhone = bankCardReservePhone;
	}
}
