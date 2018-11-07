package com.yixin.kepler.v1.common.enumpackage;

/**
 * Created by liushuai2 on 2018/6/7.
 */
public enum BankPhaseEnum {
    PRE_TRIAL("pre_trial","预审"),//预审
    FIRST_TRIAL("first_trial","初审"), //初审
    LAST_TRIAL("last_trial","终审"),//终审
    PAYMENT("payment","请款"),// 请款
    PUSHFILE("push_file","推送结算"),//推送结算
    GREDIT_STATE("gredit_state_query","征信授权书签署状态查询"),//征信授权书签署状态查询
    PAYMENT_QUERY("contract_state_query","合同签署状态查询"),//合同签署状态查询
    AFTER_LOAN("after_loan","贷后"),//贷后
    WB_ELECONTRACT_SAVE("wb_elecontract_save","电子签约存正"),//电子签约存正
    WB_ORDER_CANCEL("wb_order_cancel","微众订单撤销"),//微众订单撤销
    
    REPAY_SCHEDULES("repay_schedules","还款计划处理"),

    //增加于工行项目 by gumanxue
    ORDER_CANCEL("order_cancel","订单撤销"),//订单取消
    LAST_TRIAL_REJECT("last_trial_reject","信审驳回"), //终审驳回
    PAYMENT_REJECT("payment_reject","请款驳回"), //请款驳回	
    FACE_SIGN("face_sign_query","面签状态查询"); //面签状态查询


    private String phase;
    private String name;

    private BankPhaseEnum(String phase, String name) {
        this.phase = phase;
        this.name = name;
    }
    
    public static BankPhaseEnum get(String val){
    	BankPhaseEnum[] values = BankPhaseEnum.values();
    	
    	for (BankPhaseEnum bankPhaseEnum : values) {
			if (val.equals(bankPhaseEnum.getPhase())) {
				return bankPhaseEnum;
			}
		}
    	return null;
    }
    
    public String getPhase() {
        return phase;
    }

	public String getName() {
		return name;
	}
}
