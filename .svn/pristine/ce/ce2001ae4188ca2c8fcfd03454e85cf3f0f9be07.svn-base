package com.yixin.dsc.util;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.exception.ExceptionUtils;

/**
 * @description:
 * @date: 2018-10-18 14:37
 */
public class StrUtil extends StringUtils {


    /**
     * 获取异常堆栈信息
     *
     * @param e
     * @return
     */
    public static String getStackTrace(Throwable e) {
        String eStackTrace = "";
        if (e != null) {
            eStackTrace = ExceptionUtils.getStackTrace(e);
        }
        return eStackTrace;
    }


    /**
     * 处理NULL值,NULL返回空字符串
     *
     * @param o
     * @return
     */
    public static String fixNull(Object o) {
        return o == null ? "" : o.toString().trim();
    }


    /**
     * 字符串转 int
     *
     * @param intVal
     * @return
     */
    public static Integer toInt(String intVal) {
        Integer value = null;
        try {
            value = Integer.parseInt(intVal);
        } catch (Exception e) {
            //ignored exception
        }
        return value;
    }


    /**
     * 字符串转 long
     *
     * @param intVal
     * @return
     */
    public static Long toLong(String intVal) {
        Long value = null;
        try {
            value = Long.parseLong(intVal);
        } catch (Exception e) {
            //ignored exception
        }
        return value;
    }
}
