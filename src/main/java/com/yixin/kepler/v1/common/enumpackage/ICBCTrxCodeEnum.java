package com.yixin.kepler.v1.common.enumpackage;


public enum ICBCTrxCodeEnum {

    TX_10101("10101","初审"),
    TX_10201("10201","初审返回"),
    TX_10181("10181","初审补充信息"),

    TX_20101("20101","风控审批信息通知"),
    TX_30101("30101","面签结果通知"),

    TX_40101("40101","请款"),
    TX_40201("40201","请款返回"),
    TX_40181("40181","请款资料重传");

    private String code;
    private String name;

    ICBCTrxCodeEnum(String code, String name) {
        this.code = code;
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

    public String getCode() {
        return code;
    }

    public String getName() {
        return name;
    }
}
