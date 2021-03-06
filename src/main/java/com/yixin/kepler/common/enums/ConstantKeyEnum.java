package com.yixin.kepler.common.enums;

/**
 * 静态枚举类
 * Package : com.yixin.kepler.common.enums
 *
 * @author YixinCapital -- wanghonglin
 * 2018/6/21 17:21
 */
public enum ConstantKeyEnum {

    //========= 民生银行相关 =============//
    LAST_TRIAL("last_trial_flag","民生银行终审接口"),
    LAST_TRIAL_RESULT("last_trial_result_flag","民生银行终审结果查询接口"),
    PAYMENT("payment_flag","民生银行请款接口"),
    PAYMENT_RESULT("payment_result_flag","民生银行请款结果查询接口"),
    STATE_CHANGE_RESULT("state_change_result_flag","民生银行状态变更接口"),
    APPLY_STATE_RESULT("apply_state_result_flag","民生银行申请状态查询接口"),
    CREADIT_STATE_RESULT("creadit_state_result_flag","民生银行征信授权状态查询"),
    CMBC_INTERFACE_FLAG("cmbc_interface_flag","民生银行总开关"),

    //========= 民生银行相关 =============//

    //========= 微众银行相关 =============//
    WEBANK_INTERFACE_FLAG("webank_interface_flag","微众银行总开关"),
    WEBANK_FIRST_TRIAL_RESPONSE_JSON("webank_first_trial_response_json","微众银行初审响应报文"),
    WEBANK_FIRST_TRIAL_CALLBACK_JSON("webank_first_trial_callback_json","微众银行初审回调报文"),
    WEBANK_LAST_TRIAL_RESPONSE_JSON("webank_last_trial_response_json","微众银行复审响应报文"),
    WEBANK_PAYMENT_RESPONSE_JSON("webank_payment_response_json","微众银行请款响应报文"),
    WEBANK_PICKUP_CAR_RESPONSE_JSON("webank_pickup_car_response_json","微众银行订单提车响应报文"),
    WEBANK_ORDER_CANCEL_RESPONSE_JSON("webank_order_cancel_response_json","微众银行订单终止响应报文"),
    WEBANK_ELECTRON_CONTRACT_RESPONSE_JSON("webank_electron_contract_response_json","微众银行电子签约存证响应报文"),
    WEBANK_COMPUTER_RESPONSE_JSON("webank_computer_response_json","微众银行借据试算响应报文"),
    WB_PICK_UP_CAR_FLAG("wb_pick_up_car_flag","微众银行订单提车开关"),

    WB_COMPUTER_FLAG("wb_computer_flag","微众银行试算开关"),

    //========= 微众银行相关 =============//

    //========= 工商银行相关 =============//
    ICBC_INTERFACE_FLAG("icbc_interface_flag","工行银行总开关"),
    ICBC_FIRST_TRIAL_RESPONSE_JSON("icbc_first_trial_response_json","工行银行初审响应报文"),
    ICBC_FIRST_TRIAL_CALLBACK_JSON("icbc_first_trial_callback_json","工行银行初审回调报文"),
    ICBC_LAST_TRIAL_RESPONSE_JSON("icbc_last_trial_response_json","工行银行复审响应报文"),
    ICBC_PAYMENT_RESPONSE_JSON("icbc_payment_response_json","工行银行请款响应报文"),
    ICBC_ORDER_CANCEL_RESPONSE_JSON("icbc_order_cancel_response_json","工行银行订单终止响应报文"),
    ICBC_PICK_UP_CAR_FLAG("icbc_pick_up_car_flag","工行银行订单提车开关"),
    //========= 工商银行相关 =============//

    //========= 云信相关 =============//
    YT_INTERFACE_FLAG("yt_interface_flag","云信银行总开关"),
    YT_FIRST_TRIAL_RESPONSE_JSON("yt_create_order_flag","云信终审响应"),
    YT_CANCEL_LOAN_RESPONSE_JSON("yt_cancel_order_flag","云信取消订单响应"),
    YT_CONTRACT_RESPONSE_JSON("yt_contract_flag","云信签约响应"),
    YT_VERIFICATION_RESPONSE_JSON("yt_verification_flag","云信签约绑定响应"),
    YT_PAYMENT_RESPONSE_JSON("yt_payment_flag","云信请款响应"),
    YT_QUERY_TRADING_STATUS_RESPONSE_JSON("yt_query_trading_status_flag","云信放款查询响应"),
    YT_QUERY_BATCH_TRADING_STATUS_RESPONSE_JSON("yt_query_batch_trading_status_flag","云信批量放款查询响应"),
    YT_PAYMENTBANKCARDS("yt_paymentBankCards","云南信托-易鑫放款账号信息"),
    //========= 云信相关 =============//

    STOP_SERVICE_FLAG("stop_service_flag_","资方对外服务开关,注意生产环境严禁随意配置");

    private  String key;

    private  String name;


    private ConstantKeyEnum(String key,String name) {
        this.key = key;
        this.name = name;
    }

    public String getKey() {
        return key;
    }


    public String getName() {
        return name;
    }

}
