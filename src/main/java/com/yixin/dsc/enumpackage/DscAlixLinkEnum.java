package com.yixin.dsc.enumpackage;

/**
 * 返回给alix的消息类型枚举
 * Package : com.yixin.dsc.enumpackage
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月10日 下午2:41:42
 *
 */
public enum DscAlixLinkEnum {
	
	
	
	SUCCES_CODE("100000","成功"),
	FAILURE_CODE("100001","失败"),
	CREDITFRONT("CREDITFRONT","信审结果消息"),
	REQUEST_FUNDS("REQUEST_FUNDS","请款受理结果消息");
	
	
	

	private String code;
    private String describe;
    
	private DscAlixLinkEnum() {}
	
	private DscAlixLinkEnum(String code, String describe) {
		this.code = code;
		this.describe = describe;
	}

	public String getCode() {
		return code;
	}

	public String getDescribe() {
		return describe;
	}
    
}
