package com.yixin.kepler.common.enums;

public enum CreditfrontResultEnum {

	SUCCESS("100000","审批通过"),
	REFUSE("100001","审批拒绝"), 
	REJECT("100002","审批驳回"),
	TIMEOUT("100003","信审请求超时"),
	CHANGE("100004","验四失败，换卡"),
	ACCFAIL("100005","银行受理失败"),//TODO

	
	REJECT_ATTACHMENT("10000201","附件驳回"),
	REJECT_FIELD("10000202","字段项驳回"),
	REJECT_ALL("10000203","字段项和附件同步驳回"),
	REJECT_BANK_UNCHK("1000020401","银行驳回 - 验四失败"),
	REJECT_BANK_VIN("1000020402","银行驳回 - VIN码格式错误"),
	REJECT_BANK_MONEY("1000020403","银行驳回 - 开户拒绝-负债收入比不准入"),
	REJECT_BANK_FILE("1000020404","银行驳回-修改影像件");

	private String code;

    private String msg;
    
    private CreditfrontResultEnum(String code,String msg){
        this.code = code;
        this.msg = msg;
    }

	public String getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
    
}
