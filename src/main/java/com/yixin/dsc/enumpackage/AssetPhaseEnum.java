package com.yixin.dsc.enumpackage;

/**
 * Created by liushuai2 on 2018/6/7.
 */
public enum AssetPhaseEnum {
    PRE_TRIAL("pre_trial"), //预审
    TRIAL("trial"), //信审
    PAYMENT_TRIAL("payment_trial");//请款


    private String phase;

    private AssetPhaseEnum(String phase){
        this.phase = phase;
    }

	public String getPhase() {
		return phase;
	}

}
