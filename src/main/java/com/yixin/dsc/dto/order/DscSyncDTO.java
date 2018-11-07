package com.yixin.dsc.dto.order;

import java.io.Serializable;
import java.util.List;

/**
 * DSC同步DTO
 * Package : com.yixin.dsc.dto.order
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月4日 下午8:08:17
 *
 */
public class DscSyncDTO implements Serializable{

	private static final long serialVersionUID = -2464535684288435383L;

	private String sign = "";
	/**
	 * 订单编号
	 */
	private String applyNo;

	/**
	 * 产品编号
	 */
	private String productNo;

	/**
	 * 合同申请主表
	 */
	private DscSalesApplyMainDTO salesApplyMainDTO;
	
	/**
	 * 申请车辆信息
	 */
	private DscSalesApplyCarDTO salesApplyCarDTO;
	
	/**
	 * 合同申请费用信息表
	 */
	private DscSalesApplyCostDTO salesApplyCostDTO;
	
	/**
	 * 合同申请保险信息
	 */
	private DscSalesInsureInfoDTO salesInsureInfoDTO;
	
	/**
	 * 合同申请融资额明细表
	 */
	private List<DscSalesApplyFinancingDTO> salesApplyFinancingDTOList;
	
	/**
	 * 合同申请收款信息表
	 */
	private List<DscSalesApplyPayeeDTO> salesApplyPayeeDTOList;
	
	/**
	 * 店面收款方信息表
	 */
	private List<DscSysReceivablesDTO> sysReceivablesDTOList;
	
	/**
	 * 合同申请保险类别明细
	 */
	private List<DscSalesInsureFinancingDTO> salesInsureFinancingDTOList;
	
	
	/**
	 * 合同申请客户信息
	 */
	private DscSalesApplyCustDTO salesApplyCustDTO;
	
	/**
	 * 合同申请担保人信息
	 */
	private List<DscSalesApplyBondsmanDTO> salesApplyBondsmanDTOList;
	
	/**
	 * 合同申请联系人信息表
	 */
	private List<DscSalesApplyContactDTO> salesApplyContactDTOList;
	
	/**
	 * 申请提报附件表
	 */
	private List<DscFileAttachmentDTO> fileAttachmentDTOList;
	
	/**
	 * 增加的字段属性
	 */
	private DscSaleApplyAttrDTO attrDto;

	/**
	 * 风控部分银行准入信息0928工行迁移项目增加
	 */
	private List<DscSalesApplyRiskDTO> salesApplyRiskDTOList;

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public DscSaleApplyAttrDTO getAttrDto() {
		return attrDto;
	}

	public void setAttrDto(DscSaleApplyAttrDTO attrDto) {
		this.attrDto = attrDto;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public DscSalesApplyMainDTO getSalesApplyMainDTO() {
		return salesApplyMainDTO;
	}

	public void setSalesApplyMainDTO(DscSalesApplyMainDTO salesApplyMainDTO) {
		this.salesApplyMainDTO = salesApplyMainDTO;
	}

	public DscSalesApplyCarDTO getSalesApplyCarDTO() {
		return salesApplyCarDTO;
	}

	public void setSalesApplyCarDTO(DscSalesApplyCarDTO salesApplyCarDTO) {
		this.salesApplyCarDTO = salesApplyCarDTO;
	}

	public DscSalesApplyCostDTO getSalesApplyCostDTO() {
		return salesApplyCostDTO;
	}

	public void setSalesApplyCostDTO(DscSalesApplyCostDTO salesApplyCostDTO) {
		this.salesApplyCostDTO = salesApplyCostDTO;
	}

	public DscSalesInsureInfoDTO getSalesInsureInfoDTO() {
		return salesInsureInfoDTO;
	}

	public void setSalesInsureInfoDTO(DscSalesInsureInfoDTO salesInsureInfoDTO) {
		this.salesInsureInfoDTO = salesInsureInfoDTO;
	}

	public List<DscSalesApplyFinancingDTO> getSalesApplyFinancingDTOList() {
		return salesApplyFinancingDTOList;
	}

	public void setSalesApplyFinancingDTOList(List<DscSalesApplyFinancingDTO> salesApplyFinancingDTOList) {
		this.salesApplyFinancingDTOList = salesApplyFinancingDTOList;
	}

	public List<DscSalesApplyPayeeDTO> getSalesApplyPayeeDTOList() {
		return salesApplyPayeeDTOList;
	}

	public void setSalesApplyPayeeDTOList(List<DscSalesApplyPayeeDTO> salesApplyPayeeDTOList) {
		this.salesApplyPayeeDTOList = salesApplyPayeeDTOList;
	}

	public List<DscSysReceivablesDTO> getSysReceivablesDTOList() {
		return sysReceivablesDTOList;
	}

	public void setSysReceivablesDTOList(List<DscSysReceivablesDTO> sysReceivablesDTOList) {
		this.sysReceivablesDTOList = sysReceivablesDTOList;
	}

	public List<DscSalesInsureFinancingDTO> getSalesInsureFinancingDTOList() {
		return salesInsureFinancingDTOList;
	}

	public void setSalesInsureFinancingDTOList(List<DscSalesInsureFinancingDTO> salesInsureFinancingDTOList) {
		this.salesInsureFinancingDTOList = salesInsureFinancingDTOList;
	}

	public DscSalesApplyCustDTO getSalesApplyCustDTO() {
		return salesApplyCustDTO;
	}

	public void setSalesApplyCustDTO(DscSalesApplyCustDTO salesApplyCustDTO) {
		this.salesApplyCustDTO = salesApplyCustDTO;
	}

	public List<DscSalesApplyBondsmanDTO> getSalesApplyBondsmanDTOList() {
		return salesApplyBondsmanDTOList;
	}

	public void setSalesApplyBondsmanDTOList(List<DscSalesApplyBondsmanDTO> salesApplyBondsmanDTOList) {
		this.salesApplyBondsmanDTOList = salesApplyBondsmanDTOList;
	}

	public List<DscSalesApplyContactDTO> getSalesApplyContactDTOList() {
		return salesApplyContactDTOList;
	}

	public void setSalesApplyContactDTOList(List<DscSalesApplyContactDTO> salesApplyContactDTOList) {
		this.salesApplyContactDTOList = salesApplyContactDTOList;
	}

	public List<DscFileAttachmentDTO> getFileAttachmentDTOList() {
		return fileAttachmentDTOList;
	}

	public void setFileAttachmentDTOList(List<DscFileAttachmentDTO> fileAttachmentDTOList) {
		this.fileAttachmentDTOList = fileAttachmentDTOList;
	}

	public List<DscSalesApplyRiskDTO> getSalesApplyRiskDTOList() {
		return salesApplyRiskDTOList;
	}

	public void setSalesApplyRiskDTOList(List<DscSalesApplyRiskDTO> salesApplyRiskDTOList) {
		this.salesApplyRiskDTOList = salesApplyRiskDTOList;
	}
}
