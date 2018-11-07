package com.yixin.dsc.enumpackage;

/**
 * 请求前置处枚举
 * Package : com.yixin.dsc.enumpackage
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月19日 下午12:51:05
 *
 */
public enum RequestPreposeEnum {


	SYNC_ORDER_ALL_DATA("SyncOrderAllData"), //同步全量数据
	FIRST_TRIAL("FirstTrial"), //初审
	AFTER_LOAN("AfterLoanCheck"), //贷后同步资料
	PAYMENT_TRIAL("PaymentTrialCheck"); //请款

	
	
    private String prepose;
    
    private RequestPreposeEnum(String prepose){
        this.prepose = prepose;
    }

	public String getPrepose() {
		return prepose;
	}

}
