package com.yixin.kepler.common.enums;

/**
 *	微众回调枚举类
 */
public enum WBCallbackEnum {

	CODE_0000("0000","系统处理成功"),

	CODE_0001("0001","报文格式错误"),
	
	CODE_0002("0002","未满足接口输入要求"),
	
	CODE_0103("0103","报文解析失败"),
	
	CODE_0010("0010","签名验证失败"),
	
	CODE_0012("0012","时间戳过期"),
	
	CODE_0016("0016","SSL 认证失败");


	private String code;

	private String desc;

	WBCallbackEnum(String code, String desc) {
		this.code = code;
		this.desc = desc;
	}

	public String getCode() {
		return code;
	}

	public String getDesc() {
		return desc;
	}

}
