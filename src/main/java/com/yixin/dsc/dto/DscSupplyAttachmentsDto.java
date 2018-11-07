package com.yixin.dsc.dto;

import java.io.Serializable;

/**
 * 补充附件信息
 * 
 * Package : com.yixin.dsc.dto
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月3日 下午9:50:15
 *
 */
public class DscSupplyAttachmentsDto implements Serializable{
	
	private static final long serialVersionUID = -9153881318276002687L;
	/**
	 * 附件类型
	 */
	private String attType;
	/**
	 * 附件名称
	 */
	private String attName;
	
	/**
	 * 最少
	 */
	private Integer minNum;
	
	/**
	 * 最多
	 */
	private Integer maxNum;


	public DscSupplyAttachmentsDto() {}

	public DscSupplyAttachmentsDto(Integer minNum, Integer maxNum) {
		this.minNum = minNum;
		this.maxNum = maxNum;
	}

	public static DscSupplyAttachmentsDto createNewObject(){
		return new DscSupplyAttachmentsDto();
	}

	public static DscSupplyAttachmentsDto createNewObject(Integer minNum, Integer maxNum){
		return new DscSupplyAttachmentsDto(minNum, maxNum);
	}

	public DscSupplyAttachmentsDto setAttachmentType(String attType){
		this.attType = attType;
		return this;
	}

	public DscSupplyAttachmentsDto setAttachmentName(String attName){
		this.attName = attName;
		return this;
	}

	public String getAttType() {
		return attType;
	}

	public void setAttType(String attType) {
		this.attType = attType;
	}

	public String getAttName() {
		return attName;
	}

	public void setAttName(String attName) {
		this.attName = attName;
	}

	public Integer getMinNum() {
		return minNum;
	}

	public void setMinNum(Integer minNum) {
		this.minNum = minNum;
	}

	public Integer getMaxNum() {
		return maxNum;
	}

	public void setMaxNum(Integer maxNum) {
		this.maxNum = maxNum;
	}
	
	

	
}

