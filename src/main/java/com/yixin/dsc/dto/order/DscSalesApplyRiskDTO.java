package com.yixin.dsc.dto.order;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 风控部分银行准入信息
 * 
 * Package : com.yixin.dsc.entity
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月4日 下午5:42:31
 *
 */
public class DscSalesApplyRiskDTO implements Serializable {

	private static final long serialVersionUID = -3758786999572146178L;


	private String id;

	/**
	 * 申请编号
	 */
	private String applyNo;

	/**
	 * 银行编码
	 */
	private String bankCode;


	/**
	 * 银行名称
	 */
	private String bankName;

	/**
	 * 风控码值
	 */
	private String riskCode;

	/**
	 * 风控描述
	 */
	private String riskDesc;

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getRiskCode() {
		return riskCode;
	}

	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	public String getRiskDesc() {
		return riskDesc;
	}

	public void setRiskDesc(String riskDesc) {
		this.riskDesc = riskDesc;
	}
}

