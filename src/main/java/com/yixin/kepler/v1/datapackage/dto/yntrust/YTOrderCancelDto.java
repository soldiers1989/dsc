package com.yixin.kepler.v1.datapackage.dto.yntrust;

/**
 * 取消订单dto
 * @author YixinCapital -- chenjiacheng
 *         2018年09月20日 10:02
 **/

public class YTOrderCancelDto extends YTCommonRequestDTO {

	private static final long serialVersionUID = -5679275408204258359L;
	/**
	 * 贷款唯一标识
	 */
	private String UniqueId;

	public String getUniqueId() {
		return UniqueId;
	}

	public void setUniqueId(String uniqueId) {
		UniqueId = uniqueId;
	}
}
