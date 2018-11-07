package com.yixin.dsc.enumpackage;

/**
 * 准入拒绝类型
 * Package : com.yixin.dsc.enumpackage
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年8月27日 下午3:13:31
 *
 */
public enum ShuntRefuseTypeEnum {

	PRODUCT_NO_CAPITAL("product_no_capital","产品编号未关联任何有效资方"),
	RULE_NO_MATCH("rule_no_match","规则未全部匹配"),
	MATCH_SUCCESS("match_success","准入通过");
	
	

	private String type;
	
	private String name;


	ShuntRefuseTypeEnum(String type,String name) {
		this.type = type;
		this.name = name;
	}


	public String getType() {
		return type;
	}
	public String getName() {
		return name;
	}

	public static String getEnumNameByType(String type) {
		ShuntRefuseTypeEnum[] values = ShuntRefuseTypeEnum.values();
		for (ShuntRefuseTypeEnum aEnum : values) {
			if (type.equals(aEnum.getType())) {
				return aEnum.getName();
			 }
		}
		return null;
	}

}
