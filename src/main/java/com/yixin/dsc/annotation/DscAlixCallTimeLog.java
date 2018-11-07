package com.yixin.dsc.annotation;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 *
 * alix调用
 *
 * Created by wangxulong on 2018/7/2.
 */

@Target({ElementType.METHOD})
@Retention(RetentionPolicy.RUNTIME)
public @interface DscAlixCallTimeLog {

    public String methodType() default  "method" ;
}
