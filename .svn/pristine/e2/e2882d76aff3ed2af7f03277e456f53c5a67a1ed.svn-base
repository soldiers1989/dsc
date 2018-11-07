package com.yixin.dsc.enumpackage;

import org.apache.commons.lang3.StringUtils;

/**
 * 是否生效枚举类
 * @author YixinCapital -- chenjiacheng
 *         2018年08月06日 14:21
 **/

public enum DscIsValidEnum {
	YES("是","0"),
	NO("否","1")
	;

	DscIsValidEnum(String name, String type) {
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
		for (DscIsValidEnum obj : DscIsValidEnum.values()) {
			if (type.equals(obj.getType())) {
				return obj.getName();
			}
		}
		return null;
	}

	private String name;

	private String type;

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
