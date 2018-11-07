package com.yixin.dsc.dto;

import java.io.Serializable;

/**
 * 试算参数dto
 * Package : com.yixin.dsc.dto
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月4日 下午8:02:22
 *
 */
public class DscComputerParamDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5081149254558985151L;
	
	/**
	 * 申请编号/订单编号
	 */
	private String applyNo; 

	/**
	 * 操作人域账号
	 */
	private String userAccount;

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getUserAccount() {
		return userAccount;
	}

	public void setUserAccount(String userAccount) {
		this.userAccount = userAccount;
	}
	
	
}
