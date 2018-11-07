package com.yixin.dsc.enumpackage;

/**
 * 分流决策动作枚举类
 * Package : com.yixin.dsc.enumpackage
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月4日 下午1:49:02
 *
 */
public enum DscActionEnum {

	CREDITFRONT_SUCCESS("CREDITFRONT_00","信审受理成功"),
	
	CREDITFRONT_REFUSE("CREDITFRONT_01","信审受理拒绝"),
	
	CREDITFRONT_FAILD("CREDITFRONT_02","信审受理失败"),
	
	REQUEST_FUNDS_SUCCESS("REQUEST_FUNDS_00","请款受理成功"),
	
	REQUEST_FUNDS_REFUSE("REQUEST_FUNDS_01","请款受理拒绝"),
	
	REQUEST_FUNDS_FAILD("REQUEST_FUNDS_02","请款受理失败");
	
	private String code;
    private String describe;
    
	private DscActionEnum() {
	}

	private DscActionEnum(String code, String describe) {
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
