package com.yixin.dsc.enumpackage;


import org.apache.commons.lang3.StringUtils;

public enum RuleEvalEnum {


	REG("正则表达式", "reg"), //正则
	FEL("Fel", "fel"), //fel
	JS("javaScript", "js"),//js
	JSCOM("javaScriptCompiled", "jsCompiled");//jsCompiled

	private String name;
	private String type;

	RuleEvalEnum(String name, String type) {
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
		for (RuleEvalEnum obj : RuleEvalEnum.values()) {
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
