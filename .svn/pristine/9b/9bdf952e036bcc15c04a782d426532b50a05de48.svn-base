package com.yixin.web.anocation;

import com.yixin.web.common.enums.TimeConsumeLevel;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * @description:
 * @date: 2018-10-08 14:20
 */
@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface InterfaceMonitor {

    /**
     * AOP拦截非BZException异常后的提示
     *
     * @return
     */
    String errorMsg() default "操作失败";

    /**
     * 接口耗时级别
     *
     * @return
     */
    TimeConsumeLevel timeConsume() default TimeConsumeLevel.MEDIUM;

    /**
     * 关键参数
     * <p>
     * applyNo::0_data_field  第0个参数的字段data下的字段field,参数的名字是applyNo
     *
     * @return
     */
    String[] keyParam() default {};
}
