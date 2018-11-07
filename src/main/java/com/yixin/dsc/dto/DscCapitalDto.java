package com.yixin.dsc.dto;

import java.io.Serializable;

import com.yixin.kepler.dto.PretrialDTO;

/**
 * 资方信息dto
 * 
 * Package : com.yixin.dsc.dto
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月3日 下午9:50:15
 *
 */
public class DscCapitalDto implements Serializable{
	
	
	private static final long serialVersionUID = -1822437101084135072L;
	
	/**
	 * 资方id
	 */
	private String capitalId;
	
	/**
	 * 资方名称
	 */
	private String capitalName;
	
	/**
	 * 资方code
	 */
	private  String capitalCode;

	/**
	 * 预审信息
	 */
	private PretrialDTO pretrialParam;
	
	/**
	 * 是否通过预审
	 */
	private Boolean  pretrialResult;
	
	/**
	 * 银行预审返回信
	 */
	private String    pretrialMsg;

	/**
	 * 银行成本费率
	 */
	private String bankRate;
	
	
	public DscCapitalDto() {
		super();
	}

	public DscCapitalDto(String capitalId, String capitalName, String capitalCode) {
		super();
		this.capitalId = capitalId;
		this.capitalName = capitalName;
		this.capitalCode = capitalCode;
	}

	
	public PretrialDTO getPretrialParam() {
		return pretrialParam;
	}

	public void setPretrialParam(PretrialDTO pretrialParam) {
		this.pretrialParam = pretrialParam;
	}

	public Boolean getPretrialResult() {
		return pretrialResult;
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

	public String getCapitalId() {
		return capitalId;
	}

	public void setCapitalId(String capitalId) {
		this.capitalId = capitalId;
	}

	public String getCapitalName() {
		return capitalName;
	}

	public void setCapitalName(String capitalName) {
		this.capitalName = capitalName;
	}

	public String getCapitalCode() {
		return capitalCode;
	}

	public void setCapitalCode(String capitalCode) {
		this.capitalCode = capitalCode;
	}

	public String getBankRate() {
		return bankRate;
	}

	public void setBankRate(String bankRate) {
		this.bankRate = bankRate;
	}
}

