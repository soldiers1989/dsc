package com.yixin.dsc.enumpackage;


import org.apache.commons.lang3.StringUtils;

public enum DscRuleTypeEnum {


	SHUNT("准入规则","shunt"), //准入规则
	CREDITFRONT_SHUNT("信审规则","creditfront_supply"), //信审规则
	CREDITFRONT_BEFORE("发起信审前规则","creditfront_before"), //发起信审前规则
	CREDITFRONT_CHECK("信审前校验规则","creditfront_check"),//信审前校验规则
	REQUEST_FUNDS_CHECK("请款前校验规则","request_funds_check"),//请款前校验规则
	AFTER_LOAN_CHECK("贷后校验规则","after_loan_check");//贷后校验规则

	private String name;

	private String type;

	DscRuleTypeEnum(String name,String type) {
		this.name = name;
		this.type = type;
	}

	/**
	 * 根据type获取对应的name
	 * @param type 类型
	 * @return 名称
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/6 16:42
	 */
	public static String getNameByType(String type) {
		if (StringUtils.isBlank(type)) {
			return null;
		}
		for (DscRuleTypeEnum obj : DscRuleTypeEnum.values()) {
			if (type.equals(obj.getType())) {
				return obj.getName();
			}
		}
		return null;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}
}
