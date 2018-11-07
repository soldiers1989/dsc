package com.yixin.kepler.common.enums;

/**
 * @author  liushuai2 on 2018/6/7.
 */
public enum BankPhaseEnum {
    //预审
    PRE_TRIAL("pre_trial","预审"),
    //初审
    FIRST_TRIAL("first_trial","初审"),
    //终审
    LAST_TRIAL("last_trial","终审"),
    // 请款
    PAYMENT("payment","请款"),
    //推送结算
    PUSHFILE("push_file","推送结算"),
    //征信授权书签署状态查询
    GREDIT_STATE("gredit_state_query","征信授权书签署状态查询"),
    //合同签署状态查询
    PAYMENT_QUERY("contract_state_query","合同签署状态查询"),
    //贷后
    AFTER_LOAN("after_loan","贷后"),
	ORDER_CANCEL("order_cancel","订单取消"),
    //电子签约存正
    WB_ELECONTRACT_SAVE("wb_elecontract_save","电子签约存正"),
    //微众订单撤销
    WB_ORDER_CANCEL("wb_order_cancel","微众订单撤销"),

    REPAY_SCHEDULES_HANDLE("repay_schedules_handle","还款计划处理"),
	
	REPAY_SCHEDULES_QUERY("repay_schedules_query","还款计划查询"),
	
	//请款结果查询
	PAYMENT_RESULT_QUERY("payment_result_query","请款结果查询"),

    SIGN_CONTRACT("sign_contract","签章合同");


    private String phase;
    private String name;

    private BankPhaseEnum(String phase,String name) {
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


    public static String getNameByValue(String val) {
        BankPhaseEnum[] values = BankPhaseEnum.values();
        for (BankPhaseEnum bankPhaseEnum : values) {
            if (val.equals(bankPhaseEnum.getPhase())) {
                return bankPhaseEnum.getName();
            }
        }
        return null;
    }
}
