package com.yixin.kepler.v1.common.enumpackage;


import com.google.common.base.Strings;
import org.apache.commons.lang.StringUtils;

import java.util.Objects;

/**
 * @author sukang
 */
public enum  YNTSignStatus {

    Sign_Status_1("1","未生成合同"),
    Sign_Status_2("2","生成合同成功"),
    Sign_Status_3("3","生成合同失败"),
    Sign_Status_4("4","合同签章成功"),
    Sign_Status_5("5","合同签章失败"),
    Sign_Status_6("6","发送放款指令成功"),
    Sign_Status_7("7","发送放款指令失败"),
    Sign_Status_8("8","签章处理中"),
    Sign_Status_9("9","签章人工处理");

    private String code;
    private String msg;


    public static String getSignStatusMsg(String status){
        YNTSignStatus[] values = YNTSignStatus.values();

        for (YNTSignStatus value : values) {
            if (Objects.equals(status,value.getCode())){
                return value.getMsg();
            }
        }
        return "null";
    }


    YNTSignStatus(String code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public String getMsg() {
        return msg;
    }
}
