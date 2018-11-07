package com.yixin.web.service.monitor;

import com.yixin.common.utils.InvokeResult;

/**
 * @description:
 * @date: 2018-10-10 11:40
 */
public interface MonitorRuleService {

    /**
     * 设置接口超时阈值
     *
     * @param consumeLevel 耗时等级
     * @param time         耗时上限 /ms
     * @return
     */
    InvokeResult addInterfaceTimeThreshold(String consumeLevel, String time);


    /**
     * 查询接口超时阈值
     *
     * @param consumeLevel
     * @return
     */
    Long getInterfaceTimeThreshold(int consumeLevel);

    /**
     * 设置业务超时阈值配置
     *
     * @param type
     * @param financialCode
     * @param overtime
     * @return
     */
    InvokeResult addOvertimeConfig(String type, String financialCode, String overtime);

    /**
     * 查询业务超时阈值配置
     *
     * @param type
     * @return
     */
    InvokeResult getOvertimeConfig(String type);

    /**
     * 删除业务超时阈值配置
     *
     * @param type
     * @param financialCode
     * @return
     */
    InvokeResult delOvertimeConfig(String type, String financialCode);
}
