package com.yixin.kepler.v1.datapackage.dto.yntrust;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.Lists;

/**
 * 云南信托请款DTO
 * Package : com.yixin.kepler.v1.datapackage.dto.yntrust
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年9月19日 上午11:19:22
 *
 */
public class YTPaymentRequestDTO extends YTCommonRequestDTO {

	private static final long serialVersionUID = 4789626990274732962L;

	/**
	 * 贷款唯一标示集合 每次请求只能传单笔借款合同uniqueid
	 */
	@JsonProperty("UniqueIdList")
	private List<String> uniqueIdList = Lists.newArrayList();
	
	/**
	 * 实际放款金额（易鑫实际放款金额）
	 */
	@JsonProperty("ActExcutedAmount")
	private String actExcutedAmount;
	
	/**
	 * 银行放款流水号
	 */
	private List<YTPaymentSerial> serialNumberList = Lists.newArrayList();
	
	public void addSerialNumber(YTPaymentSerial serialNumber){
		this.serialNumberList.add(serialNumber);
	}
	
	public void addUniqueId(String uniqueId){
		this.uniqueIdList.add(uniqueId);
	}

	public List<String> getUniqueIdList() {
		return uniqueIdList;
	}

	public void setUniqueIdList(List<String> uniqueIdList) {
		this.uniqueIdList = uniqueIdList;
	}

	public String getActExcutedAmount() {
		return actExcutedAmount;
	}

	public void setActExcutedAmount(String actExcutedAmount) {
		this.actExcutedAmount = actExcutedAmount;
	}

	public List<YTPaymentSerial> getSerialNumberList() {
		return serialNumberList;
	}

	public void setSerialNumberList(List<YTPaymentSerial> serialNumberList) {
		this.serialNumberList = serialNumberList;
	}
	
}
