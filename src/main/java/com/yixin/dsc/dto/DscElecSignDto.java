package com.yixin.dsc.dto;

import java.io.Serializable;

/**
 * 电子签约dto
 * Package : com.yixin.dsc.dto
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月4日 下午7:23:01
 *
 */
public class DscElecSignDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7705213421994141589L;

	/**
	 * 申请编号/订单编号
	 */
	private String applyNo;
	
	/**
	 * 签约信息
	 */
	private DscElecActionInfoDto signInfo;

	/**
	 * 0/电子征信 1/电子合同签约
	 */
	private String type;

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public DscElecActionInfoDto getSignInfo() {
		return signInfo;
	}

	public void setSignInfo(DscElecActionInfoDto signInfo) {
		this.signInfo = signInfo;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
