package com.yixin.web.common.enums;

/**
 * 申请单操作枚举
 *
 * @description:
 * @date: 2018-10-08 18:34
 */
public enum OrderOperate {

    REC_ALIX_AUDIT_APPLY(0, "alix发起信审，收妥"),
    WAIT_BANK_AUDIT(1, "提交银行信审，银行收妥"),
    REC_BANK_AUDIT_RESULT(2, "收到银行信审结果"),
    PUSH_ALIX_AUDIT_RESULT(3, "银行信审结果推送给alix");


    private int value;

    private String name;

    public int getValue() {
        return value;
    }

    public void setValue(int value) {
        this.value = value;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    OrderOperate(int value, String name) {
        this.value = value;
        this.name = name;
    }


}
