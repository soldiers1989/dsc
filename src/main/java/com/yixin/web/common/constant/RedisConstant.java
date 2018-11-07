package com.yixin.web.common.constant;

/**
 * redis缓存相关常量
 *
 * @description:
 * @date: 2018-10-08 18:32
 */
public interface RedisConstant {

    /**
     * 耗时级别配置 redis key 前缀
     */
    String TIME_CONSUME_LEVEL_PREFIX = "time_consume_level_";

    /**
     * 预警通知频率  redis key 前缀
     */
    String NOTICE_FREQUENCY_PREFIX = "notice_frequency_";

    /**
     * 预警通知频率配置
     */
    String NOTICE_FREQUENCY_RULE_KEY = "notice_frequency_rule";

    /**
     * error message  白名单
     */
    String ERROR_MESSAGE_WHITE_LIST_KEY = "error_message_white_list";

    /**
     * 银行信审超时配置key
     */
    String CREDIT_AUDIT_OVERTIME_KEY = "credit_audit_overtime";

    /**
     * 银行请款超时配置key
     */
    String FUNDS_REQUEST_OVERTIME_KEY = "funds_request_overtime";

    /**
     * 资方信托额度占用超时配置key
     */
    String BANK_TRUST_LIMIT_OCCUPY_KEY = "bank_trust_limit_occupy";

    /**
     * 合同签章未成功超时配置key
     */
    String CONTRACT_SIGN_UNSUCCESS_KEY = "contract_sign_unsuccess";
}
