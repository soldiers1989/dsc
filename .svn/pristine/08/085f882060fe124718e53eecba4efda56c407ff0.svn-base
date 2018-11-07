package com.yixin.dsc.dto;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yixin.common.utils.serializer.JsonDateDeserializer;
import com.yixin.common.utils.serializer.JsonDateTimeSerializer;

/**
 * 签约合同
 * Package : com.yixin.dsc.dto
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月4日 下午5:28:11
 *
 */
public class DscSignContractDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -5653922569511127424L;

	/**
	 * 合同名称
	 */
	private String contractName;
	
	/**
	 * 合同版本号
	 */
	private String contractVersion;
	
	/**
	 * 勾选时间
	 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date checkTime;

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractVersion() {
		return contractVersion;
	}

	public void setContractVersion(String contractVersion) {
		this.contractVersion = contractVersion;
	}

	public Date getCheckTime() {
		return checkTime;
	}

	public void setCheckTime(Date checkTime) {
		this.checkTime = checkTime;
	}

	@Override
	public String toString() {
		return "DscSignContractDto [contractName=" + contractName + ", contractVersion=" + contractVersion
				+ ", checkTime=" + checkTime + "]";
	}
}
