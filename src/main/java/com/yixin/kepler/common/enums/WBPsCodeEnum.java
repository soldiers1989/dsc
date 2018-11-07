package com.yixin.kepler.common.enums;

/**
 * 微众银行产品结构枚举类
 * @author YixinCapital -- chenjiacheng
 *         2018年07月09日 17:19
 **/
public enum WBPsCodeEnum {

	NEW_CAR_DISCOUNT("PS000801","新车贴息"),
	NEW_CAR_NOT_DICOUNT("PS000802","新车不贴息"),
	USED_CAR_NOT_DISCOUNT("PS000803","二手车不贴息")

	;


	private String value;

	private String name;

	WBPsCodeEnum(String value, String name) {
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
