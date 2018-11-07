package com.yixin.kepler.common.enums;

/**
 * Created by liushuai2 on 2018/6/7.
 */
public enum RuleTypeEnum {
    REG("reg"), //正则
    FEL("fel"), //fel
    JS("js");//js

    private String type;
    private RuleTypeEnum(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
