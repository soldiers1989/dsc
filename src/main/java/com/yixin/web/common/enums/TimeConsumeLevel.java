package com.yixin.web.common.enums;

/**
 * 耗时级别枚举
 *
 * @description:
 * @date: 2018-10-08 18:34
 */
public enum TimeConsumeLevel {

    SHORT(0, "低耗时"),
    MEDIUM(1, "中等耗时"),
    LONG(2, "长耗时");


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

    TimeConsumeLevel(int value, String name) {
        this.value = value;
        this.name = name;
    }
}
