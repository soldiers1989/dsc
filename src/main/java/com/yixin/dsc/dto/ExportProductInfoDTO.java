package com.yixin.dsc.dto;

/**
 * 导出产品信息使用DTO
 * @author zongzhiping
 *
 */
public class ExportProductInfoDTO {
	/**
	 * 产品code
	 */
	private String productCode;
	/**
	 * 产品name
	 */
	private String productName;
	/**
	 * 产品关联资方
	 */
	private String financialCode;
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getProductName() {
		return productName;
	}
	public void setProductName(String productName) {
		this.productName = productName;
	}
	public String getFinancialCode() {
		return financialCode;
	}
	public void setFinancialCode(String financialCode) {
		this.financialCode = financialCode;
	}
	
	
	

}
