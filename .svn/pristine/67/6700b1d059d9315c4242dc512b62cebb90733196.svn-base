package com.yixin.dsc.dto;

import com.yixin.dsc.dto.order.DscFileAttachmentDTO;
import com.yixin.dsc.dto.rule.DscContractDto;

import java.io.Serializable;
import java.util.List;

/**
 * 资方支持DTO
 * Package : com.yixin.dsc.dto
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月5日 上午11:50:38
 *
 */
public class DscCapitalSupportDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 4373257096388339324L;

	/**
	 * 资方信息
	 */
	private DscCapitalDto dscCapitalDto;
	
	/**
	 * 银行卡列表
	 */
	private List<DscBankCardDto> bankCardList;

	/**
	 * 合同列表
	 */
	private List<DscContractDto> contractList;
	
	
	/**
	 * 需要签章的合同小类集合
	 */
	private List<DscFileAttachmentDTO> signContractList;


	public DscCapitalDto getDscCapitalDto() {
		return dscCapitalDto;
	}

	public void setDscCapitalDto(DscCapitalDto dscCapitalDto) {
		this.dscCapitalDto = dscCapitalDto;
	}

	public List<DscBankCardDto> getBankCardList() {
		return bankCardList;
	}

	public void setBankCardList(List<DscBankCardDto> bankCardList) {
		this.bankCardList = bankCardList;
	}

	public List<DscContractDto> getContractList() {
		return contractList;
	}

	public void setContractList(List<DscContractDto> contractList) {
		this.contractList = contractList;
	}

	public List<DscFileAttachmentDTO> getSignContractList() {
		return signContractList;
	}

	public void setSignContractList(List<DscFileAttachmentDTO> signContractList) {
		this.signContractList = signContractList;
	}
}
