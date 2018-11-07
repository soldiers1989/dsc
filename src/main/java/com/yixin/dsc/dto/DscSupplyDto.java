package com.yixin.dsc.dto;

import java.io.Serializable;
import java.util.List;

public class DscSupplyDto  implements Serializable{
	
	private static final long serialVersionUID = -7002262716775968870L;
	
	/**
    * 需补充字段
    */
	private List<DscSupplyFieldsDto> fieldList;
	
	/**
	 * 需补充附件
	 */
	private List<DscSupplyAttachmentsDto> attList;
	
	/**
	 * 补充提示
	 */
	private String supplyMessage;

	public DscSupplyDto setSupplyFields(List<DscSupplyFieldsDto> fieldList){
		this.setFieldList(fieldList);
		return this;
	}

	public DscSupplyDto setSupplyAttachments(List<DscSupplyAttachmentsDto> attList){
		this.setAttList(attList);
		return this;
	}

	public String getSupplyMessage() {
		return supplyMessage;
	}

	public void setSupplyMessage(String supplyMessage) {
		this.supplyMessage = supplyMessage;
	}

	public List<DscSupplyFieldsDto> getFieldList() {
		return fieldList;
	}

	public void setFieldList(List<DscSupplyFieldsDto> fieldList) {
		this.fieldList = fieldList;
	}

	public List<DscSupplyAttachmentsDto> getAttList() {
		return attList;
	}

	public void setAttList(List<DscSupplyAttachmentsDto> attList) {
		this.attList = attList;
	}
		
}
