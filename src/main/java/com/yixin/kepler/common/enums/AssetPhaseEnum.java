package com.yixin.kepler.common.enums;

/**
 * Created by liushuai2 on 2018/6/7.
 */
public enum AssetPhaseEnum {
    PRE_TRIAL("pre_trial","预审"), //预审
    TRIAL("trial","信审"), //信审
    PAYMENT_TRIAL("payment_trial","请款"),//请款
    AFTER_LOAN("after_loan","贷后");//贷后


    private String phase;
    private String name;

    private AssetPhaseEnum(String phase,String name){
        this.phase = phase;
        this.name = name;
    }

	public String getPhase() {
		return phase;
	}

	public String getName() {
		return name;
	}

	/**
	 * 获取当前阶段
	 * @param phase 阶段
	 * @return 阶段对应的中文名
	 * @author YixinCapital -- chenjiacheng
	 *             2018/7/19 17:16
	 */
	public static String getPhaseName(String phase) {
		AssetPhaseEnum[] values = AssetPhaseEnum.values();
		for (AssetPhaseEnum aEnum : values) {
			if (phase.equals(aEnum.getPhase())) {
				return aEnum.getName();
			 }
		}
		return null;
	}

}
