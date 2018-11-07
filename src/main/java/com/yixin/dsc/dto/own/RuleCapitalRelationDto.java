package com.yixin.dsc.dto.own;

/**
 * @author YixinCapital -- chenjiacheng
 *         2018年08月06日 17:27
 **/

public class RuleCapitalRelationDto {

	/**
	 * 资方id
	 */
	private String capitalId;

	/**
	 * 资方code
	 */
	private String capitalCode;

	/**
	 * 绑定时间
	 */
	private String CreateTime;

	public String getCapitalId() {
		return capitalId;
	}

	public void setCapitalId(String capitalId) {
		this.capitalId = capitalId;
	}

	public String getCreateTime() {
		return CreateTime;
	}

	public void setCreateTime(String createTime) {
		CreateTime = createTime;
	}

	public String getCapitalCode() {
		return capitalCode;
	}

	public void setCapitalCode(String capitalCode) {
		this.capitalCode = capitalCode;
	}
}
