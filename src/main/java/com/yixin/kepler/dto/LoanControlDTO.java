package com.yixin.kepler.dto;

import java.io.Serializable;

/**
 * 取消、提回、退回订单
 * @author sukang
 *
 */
public class LoanControlDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 申请编号
	 */
	private String applyNo;
	
	/**
	 * 操作类型 1取消、2提回、3退回订单
	 */
	private String ctype;

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getCtype() {
		return ctype;
	}

	public void setCtype(String ctype) {
		this.ctype = ctype;
	}

}
