package com.yixin.dsc.enumpackage;

/**
 * 订单追踪记录
 * Package : com.yixin.dsc.enumpackage
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年8月24日 下午3:42:48
 *
 */
public enum OrderTrackTypeEnum {

	SYNC_ORDER_ALL_DATA("SyncOrderAllData","同步全量数据"),
	SHUNT("shunt","订单准入"),
	CREDITFRONT_SEND("creditfront_send","发起信审"),
	REQUEST_FUNDS_SEND("requestFundsSend","发起请款"),
	AFTER_LOAN_SEND("afterLoanSend","发起同步/贷后订单提车"),

	NOTICE_ALIX_CREDITFRONT("noticeAlixCreditfront","信审异步通知"),
	NOTICE_ALIX_REQUEST_FUNDS("noticeAlixRequestFunds","请款异步通知"),
	
	//type：1：取消，2：提回 ,3：退回 , 4 拒绝
	CANCLE("cancle","取消"),
	REBACK("reback","提回"),
	RETURN("return","退回"),
	REFUSE("refuse","拒绝"),
	
	SYNC_REQUEST_FUNDS_ATTACHMENT("syncRequestFundsAttachment","同步请款材料"),
	SYNC_AFTER_LOAN_ATTACHMENT("syncAfterLoanAttachment","同步贷后材料"),

	PUSH_SETTLE_ORDER("pushSettleOrder","推送结算订单"),
	
	OSB_CREDITFRONT_ATTACHMENT("osbCreditfrontAttachment","信审阶段通过OSB同步影像件给银行"),
	OSB_REQUEST_FUNDS_ATTACHMENT("osbRequestFundsAttachment","请款阶段通过OSB同步影像件给银行"),
	OSB_AFTER_LOAN_ATTACHMENT("osbAfterLoanAttachment","贷后阶段通过OSB同步影像件给银行");
	
	

	private String type;
	
	private String name;


	OrderTrackTypeEnum(String type,String name) {
		this.type = type;
		this.name = name;
	}


	public String getType() {
		return type;
	}
	public String getName() {
		return name;
	}
}
