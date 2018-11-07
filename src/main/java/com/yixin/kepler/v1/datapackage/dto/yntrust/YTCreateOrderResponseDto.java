package com.yixin.kepler.v1.datapackage.dto.yntrust;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author YixinCapital -- chenjiacheng
 *         2018年09月20日 14:19
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class YTCreateOrderResponseDto extends YTCommonResponseDTO{

	private static final long serialVersionUID = 4170377561289280504L;

	/**
	 * 产品代码
	 */
	@JsonProperty("ProductCode")
	private String productCode;

	/**
	 * 每笔贷款唯一标识
	 */
	@JsonProperty("UniqueId")
	private String uniqueId;

	/**
	 * 虚拟子账户账号
	 */
	@JsonProperty("SubAccount")
	private String subAccount;

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getUniqueId() {
		return uniqueId;
	}

	public void setUniqueId(String uniqueId) {
		this.uniqueId = uniqueId;
	}

	public String getSubAccount() {
		return subAccount;
	}

	public void setSubAccount(String subAccount) {
		this.subAccount = subAccount;
	}

}
