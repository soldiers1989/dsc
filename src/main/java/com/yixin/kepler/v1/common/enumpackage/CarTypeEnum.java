package com.yixin.kepler.v1.common.enumpackage;

/**
 *银行车辆类型枚举类
 * @author YixinCapital -- chenjiacheng
 *         2018年07月09日 17:17
 **/
public enum CarTypeEnum {

	NEW_CAR("1","新车"),

	USED_CAR("2","二手车");


	private String value;

	private String name;

	CarTypeEnum(String value, String name) {
		this.value = value;
		this.name = name;
	}

	public String getValue() {
		return value;
	}

	public String getName() {
		return name;
	}
}
