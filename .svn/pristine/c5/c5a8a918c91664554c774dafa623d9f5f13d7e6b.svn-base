package com.yixin.dsc.dto;

import java.io.Serializable;

/**
 * 是否准入返回
 * 
 * Package : com.yixin.dsc.dto
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月3日 下午9:50:15
 *
 */
public class DscAdmittanceReturnDto implements Serializable{
	
	private static final long serialVersionUID = -8051597110693735983L;

	/**
	 * 环节
	 */
	private String link;
	
	/**
	 * 资方信息
	 */
	private DscCapitalDto dscCapitalDto;
	
	/**
	 * 是否需要补充信息
	 */
	private Boolean needSupply;
	
	
	/**
	 * 是否通过预审
	 */
	private Boolean  pretrialResult;
	
	/**
	 * 银行预审返回信
	 */
	private String pretrialMsg;
	
	/**
	 * 补充信息
	 */
	private DscSupplyDto dscSupply;
	
	/**
	 * 准入拒绝类型 参考 ShuntRefuseTypeEnum
	 */
	private String shuntRefuseType;
	
	
	public Boolean getPretrialResult() {
		return pretrialResult;
	}


	public String getShuntRefuseType() {
		return shuntRefuseType;
	}


	public void setShuntRefuseType(String shuntRefuseType) {
		this.shuntRefuseType = shuntRefuseType;
	}


	public void setPretrialResult(Boolean pretrialResult) {
		this.pretrialResult = pretrialResult;
	}


	public String getPretrialMsg() {
		return pretrialMsg;
	}


	public void setPretrialMsg(String pretrialMsg) {
		this.pretrialMsg = pretrialMsg;
	}


	public DscSupplyDto getDscSupply() {
		return dscSupply;
	}


	public void setDscSupply(DscSupplyDto dscSupply) {
		this.dscSupply = dscSupply;
	}


	public DscCapitalDto getDscCapitalDto() {
		return dscCapitalDto;
	}
	
	
	public void setDscCapitalDto(DscCapitalDto dscCapitalDto) {
		this.dscCapitalDto = dscCapitalDto;
	}


	public String getLink() {
		return link;
	}


	public void setLink(String link) {
		this.link = link;
	}


	public Boolean getNeedSupply() {
		return needSupply;
	}


	public void setNeedSupply(Boolean needSupply) {
		this.needSupply = needSupply;
	}

	
}