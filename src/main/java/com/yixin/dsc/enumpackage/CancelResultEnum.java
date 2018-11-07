package com.yixin.dsc.enumpackage;

/**
 * 取消码值
 * Package : com.yixin.dsc.enumpackage
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年9月10日 下午6:59:38
 *
 */
public enum CancelResultEnum {

	SUCCESS_REQUEST_BEFORE("0001","已取消,请款前取消"),
	
	SUCCESS_AFTER_LOAN("0002","请款后支付利息可取消,若取消请执行贷后逻辑"),
	
	FAILD_CREDITFRONT("1001","不可取消，信审中"),
	
	FAILD_REQUEST_FUNDS("1002","不可取消,请款中"),
	
	FAILD_OVER("1003","不可取消,超过第一还款日"),
	
	FAILD_OTHER("1004","不可取消,其他原因");
	
	private String code;
    private String describe;
    
	private CancelResultEnum() {
	}

	private CancelResultEnum(String code, String describe) {
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
