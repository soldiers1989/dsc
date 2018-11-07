package com.yixin.kepler.v1.datapackage.dto.yntrust;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 签约导入dto
 *
 * @author YixinCapital -- chenjiacheng
 *         2018年09月26日 10:17
 **/

public class YTImportProtocolRequestDTO extends YTCommonRequestDTO {

	/**
	 * 导入批次号
	 */
	@JsonProperty("BatchNo")
	private String batchNo;

	/**
	 * 产品编号
	 */
	@JsonProperty("ProductCode")
	private String productCode;


	/**
	 * 协议明细
	 */
	@JsonProperty("ProtocolInfoDetails")
	private List<YTImportProtocolInfoDetailDTO> protocolInfoDetails;


	public String getBatchNo() {
		return batchNo;
	}

	public void setBatchNo(String batchNo) {
		this.batchNo = batchNo;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public List<YTImportProtocolInfoDetailDTO> getProtocolInfoDetails() {
		return protocolInfoDetails;
	}

	public void setProtocolInfoDetails(List<YTImportProtocolInfoDetailDTO> protocolInfoDetails) {
		this.protocolInfoDetails = protocolInfoDetails;
	}
}
