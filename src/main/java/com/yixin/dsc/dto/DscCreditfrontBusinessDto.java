package com.yixin.dsc.dto;

import java.io.Serializable;

/**
 * 消息结果通知DTO
 * Package : com.yixin.dsc.dto
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年10月11日 下午7:58:28
 *
 */
public class DscCreditfrontBusinessDto implements Serializable {

	private static final long serialVersionUID = 5665162930791827416L;

	/** 资方产品编码  */
	private String capitalProductCode;
	
	/** 资方子账号  */
	private String capitalSubAccount;
	
	/** venus订单编号  */
	private String venusApplyNo;

	public String getCapitalProductCode() {
		return capitalProductCode;
	}

	public void setCapitalProductCode(String capitalProductCode) {
		this.capitalProductCode = capitalProductCode;
	}

	public String getCapitalSubAccount() {
		return capitalSubAccount;
	}

	public void setCapitalSubAccount(String capitalSubAccount) {
		this.capitalSubAccount = capitalSubAccount;
	}

	public String getVenusApplyNo() {
		return venusApplyNo;
	}

	public void setVenusApplyNo(String venusApplyNo) {
		this.venusApplyNo = venusApplyNo;
	}
}
