package com.yixin.kepler.dto;

import java.io.Serializable;

/**
 * 信审请求接口，请求对象
 * @author sukang
 *
 */
public class LoanApplyDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	/**
	 * 申请信息
	 */
	private String applyNo;

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
	
	

}
