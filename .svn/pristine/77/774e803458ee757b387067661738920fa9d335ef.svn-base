package com.yixin.dsc.dto.order;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yixin.common.utils.serializer.JsonDateDeserializer;
import com.yixin.common.utils.serializer.JsonDateTimeSerializer;

/**
 * 增加的属性字段
 * Package : com.yixin.dsc.dto.order
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月4日 下午9:08:23
 *
 */
public class DscSaleApplyAttrDTO implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -7174874992728674604L;

	//====================== 同步贷后资料接口 开始==========================
	/**
	 * 上牌日期
	 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date registrateTime;
	
	/**
	 * 抵押登记日期
	 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date mortgageRegDate;
	
	/**
	 * 抵押办理城市码值
	 */
	private String mortgageCityCode;
	
	/**
	 * 抵押办理城市名称
	 */
	private String mortgageCityName;
	
	/**
	 * 当前牌照
	 */
	private String acarnojc;
	
	//====================== 同步贷后资料接口 结束==========================
	
	//====================== 同步请款资料接口 开始==========================
	/**
	 * 车辆过户完成日期
	 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date carPassDate;
	
	/**
	 * 二手车保险到期日期
	 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date insuranceExpireTime;
	
	/**
	 * GPS是否激活
	 * Y 是 N 否
	 */
	private String isGpsActive;
	
	/**
	 * GPS激活时间
	 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date gpsActiveTime;
	
	
	/**
	 * GPS设备码1
	 */
	private String gpsCode1;
	
	/**
	 * GPS设备码2
	 */
	private String gpsCode2;
	
	/**
	 * GPS设备码3
	 */
	private String gpsCode3;
	//====================== 同步请款资料接口 结束==========================
	
	
	public Date getRegistrateTime() {
		return registrateTime;
	}

	public String getAcarnojc() {
		return acarnojc;
	}

	public void setAcarnojc(String acarnojc) {
		this.acarnojc = acarnojc;
	}

	public Date getInsuranceExpireTime() {
		return insuranceExpireTime;
	}

	public void setInsuranceExpireTime(Date insuranceExpireTime) {
		this.insuranceExpireTime = insuranceExpireTime;
	}

	public void setRegistrateTime(Date registrateTime) {
		this.registrateTime = registrateTime;
	}

	public Date getMortgageRegDate() {
		return mortgageRegDate;
	}

	public void setMortgageRegDate(Date mortgageRegDate) {
		this.mortgageRegDate = mortgageRegDate;
	}

	public String getMortgageCityCode() {
		return mortgageCityCode;
	}

	public void setMortgageCityCode(String mortgageCityCode) {
		this.mortgageCityCode = mortgageCityCode;
	}

	public String getMortgageCityName() {
		return mortgageCityName;
	}

	public void setMortgageCityName(String mortgageCityName) {
		this.mortgageCityName = mortgageCityName;
	}

	public Date getCarPassDate() {
		return carPassDate;
	}

	public void setCarPassDate(Date carPassDate) {
		this.carPassDate = carPassDate;
	}

	public String getIsGpsActive() {
		return isGpsActive;
	}

	public void setIsGpsActive(String isGpsActive) {
		this.isGpsActive = isGpsActive;
	}

	public Date getGpsActiveTime() {
		return gpsActiveTime;
	}

	public void setGpsActiveTime(Date gpsActiveTime) {
		this.gpsActiveTime = gpsActiveTime;
	}

	public String getGpsCode1() {
		return gpsCode1;
	}

	public void setGpsCode1(String gpsCode1) {
		this.gpsCode1 = gpsCode1;
	}

	public String getGpsCode2() {
		return gpsCode2;
	}

	public void setGpsCode2(String gpsCode2) {
		this.gpsCode2 = gpsCode2;
	}

	public String getGpsCode3() {
		return gpsCode3;
	}

	public void setGpsCode3(String gpsCode3) {
		this.gpsCode3 = gpsCode3;
	}
	
	//====================== 同步请款资料接口 结束==========================
}
