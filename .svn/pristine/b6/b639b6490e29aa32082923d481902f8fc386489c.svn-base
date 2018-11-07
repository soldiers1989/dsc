package com.yixin.kepler.v1.datapackage.dto.yntrust;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 返款流水
 * Package : com.yixin.kepler.v1.datapackage.dto.yntrust
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年9月19日 下午3:40:49
 *
 */
public class YTPaymentSerial implements Serializable{
	
	private static final long serialVersionUID = -3961086724490546668L;

	/**
	 * 银行指令包序列号 / 业务参考号
	 */
	@JsonProperty("SerialNumber")
	private String serialNumber;
	
	/**
	 * 指令包顺序号 / 批次号
	 */
	@JsonProperty("SequenceNumber")
	private String sequenceNumber;
	

	public YTPaymentSerial() {
		super();
	}

	public YTPaymentSerial(String serialNumber, String sequenceNumber) {
		super();
		this.serialNumber = serialNumber;
		this.sequenceNumber = sequenceNumber;
	}

	public String getSerialNumber() {
		return serialNumber;
	}

	public void setSerialNumber(String serialNumber) {
		this.serialNumber = serialNumber;
	}

	public String getSequenceNumber() {
		return sequenceNumber;
	}

	public void setSequenceNumber(String sequenceNumber) {
		this.sequenceNumber = sequenceNumber;
	}
	
}
