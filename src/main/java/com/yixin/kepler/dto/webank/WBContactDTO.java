package com.yixin.kepler.dto.webank;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 联系人
 * Package : com.yixin.kepler.dto.webank
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月9日 下午8:24:43
 *
 */
public class WBContactDTO implements Serializable {

	private static final long serialVersionUID = -4397787306651068988L;

	/**
	 * 联系人关系/与申请人关系
	 */
	@JsonProperty("CONTACT_RELA")
	private String aysqrgx;
	
	/**
	 * 联系人姓名/姓名
	 */
	@JsonProperty("CONTACT_NAME")
	private String apoxm;
	
	/**
	 * 联系人手机号
	 */
	@JsonProperty("CONTACT_PHONE_NO")
	private String aposjhm;
	
	/**
	 * 联系人证件号码
	 */
	@JsonProperty("CONTACT_ID_NO")
	private String apozjhm;

	public String getAysqrgx() {
		return aysqrgx;
	}

	public void setAysqrgx(String aysqrgx) {
		this.aysqrgx = aysqrgx;
	}

	public String getApoxm() {
		return apoxm;
	}

	public void setApoxm(String apoxm) {
		this.apoxm = apoxm;
	}

	public String getAposjhm() {
		return aposjhm;
	}

	public void setAposjhm(String aposjhm) {
		this.aposjhm = aposjhm;
	}

	public String getApozjhm() {
		return apozjhm;
	}

	public void setApozjhm(String apozjhm) {
		this.apozjhm = apozjhm;
	}
}
