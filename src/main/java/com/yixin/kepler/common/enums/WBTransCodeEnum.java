package com.yixin.kepler.common.enums;

/**
 * 微众银行交易Code枚举
 * Package : com.yixin.kepler.common.enums
 *
 * @author YixinCapital -- wanghonglin
 * 2018/7/9 14:58
 */
public enum WBTransCodeEnum {

    /**
     * 订单终止
     */

    WB_ORDER_CANCEL("90114","订单终止"),

    /**
     * 借据试算
     */
    WB_COMPUTER("90201","借据试算"),

	WB_PAYMENT("90112","请款"),
	
	WB_LOAN_RESULT("90210","放款结果查询"),

    WB_PICKUP_CAR("90113","订单提车"),

    FIRST_TRIAL("90031","一审"),
    
    FIRST_TRIAL_CALLBACK("92002","一审回调"),

    /**
     * 终审/二审/复审
     */

    LAST_TRIAL("90032","二审"),

    /**
     * 电子签约存证
     */
    WB_ELECTRON_CONTRACT_SAVE("90202","电子签约存证");


    private String transCode;

    private String name;

    WBTransCodeEnum(String transCode, String name) {
        this.transCode = transCode;
        this.name = name;
    }

    public String getTransCode() {
        return transCode;
    }

    public String getName() {
        return name;
    }
}
