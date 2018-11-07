package com.yixin.dsc.enumpackage;

/**
 * Package : com.yixin.dsc.enumpackage
 *
 * @author YixinCapital -- wanghonglin
 * 2018/7/3 15:36
 */
public enum PenaltyRateTypeEnum {

    PENALTY_RATE_DAY("D000001", "日罚息");

    private String code;
    private String describe;

    private PenaltyRateTypeEnum() {
    }

    private PenaltyRateTypeEnum(String code, String describe) {
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
