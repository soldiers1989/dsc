package com.yixin.dsc.dto.query;

/**
 * @author YixinCapital -- chenjiacheng
 *         2018年08月23日 16:35
 **/

public class DscPaymentErrorDto {

	/**
	 * 申请编号
	 */
	private String applyNo;

	/**
	 * 所属资方
	 */
	private String financialCode;

	/**
	 * 发起请款时间
	 */
	private String createTime;

	/**
	 * 银行返回信息
	 */
	private String desc;

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getFinancialCode() {
		return financialCode;
	}

	public void setFinancialCode(String financialCode) {
		this.financialCode = financialCode;
	}

	public String getCreateTime() {
		return createTime;
	}

	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}
}
