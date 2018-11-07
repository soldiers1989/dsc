package com.yixin.dsc.dto.query;

/**
 * 民生银行试算入参dto
 * @author YixinCapital -- chenjiacheng
 *         2018年09月10日 10:00
 **/

public class DscSettleCalculationParamDto {

	/**
	 * 申请编号
	 */
	private String applyNo;

	/**
	 * 试算申请日期
	 */
	private String apptdt;

	/**
	 * 银行标志
	 */
	private String bankSymbol;

	/**
	 * 业务标志
	 */
	private String businessFlag;
	
	public String getBusinessFlag() {
		return businessFlag;
	}

	public void setBusinessFlag(String businessFlag) {
		this.businessFlag = businessFlag;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getApptdt() {
		return apptdt;
	}

	public void setApptdt(String apptdt) {
		this.apptdt = apptdt;
	}

	public String getBankSymbol() {
		return bankSymbol;
	}

	public void setBankSymbol(String bankSymbol) {
		this.bankSymbol = bankSymbol;
	}
}
