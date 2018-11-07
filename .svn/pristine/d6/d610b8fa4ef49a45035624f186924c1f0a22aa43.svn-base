package com.yixin.web.controller.monitor;

import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.util.StrUtil;
import com.yixin.web.service.monitor.MonitorRuleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @date: 2018-10-10 11:03
 */
@RestController
@RequestMapping("/api/monitor/rule")
public class MonitorRuleController {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Resource
    private MonitorRuleService monitorRuleService;


    /**
     * 设置接口超时阈值
     *
     * @param consumeLevel 耗时等级
     * @param time         时间上限 /ms
     * @return
     */
    @RequestMapping(value = "/interfaceTimeThreshold/add", method = RequestMethod.POST)
    public InvokeResult addInterfaceTimeThreshold(String consumeLevel, String time) {

        logger.info("add interface time threshold, consume level:{}, time:{}", consumeLevel, time);
        InvokeResult result;

        if (StringUtils.isEmpty(consumeLevel) || StringUtils.isEmpty(time)) {
            result = new InvokeResult();
            result.failure("参数错误");
            return result;
        }

        result = monitorRuleService.addInterfaceTimeThreshold(consumeLevel, time);

        return result;
    }


    /**
     * 添加业务超时阈值配置
     *
     * @param type
     * @param financialCode
     * @param overtime
     * @return
     */
    @RequestMapping(value = "/overtimeConfig/add", method = RequestMethod.POST)
    public InvokeResult addOvertimeConfig(String type, String financialCode, String overtime) {

        logger.info("add overtime config, type:{}, financialCode:{}, overtime:{}", type, financialCode, overtime);
        InvokeResult result;

        if (StrUtil.isEmpty(type) || StrUtil.isEmpty(financialCode) || StrUtil.isEmpty(overtime)) {
            result = new InvokeResult();
            result.failure("参数错误");
            return result;
        }

        result = monitorRuleService.addOvertimeConfig(type, financialCode, overtime);
        return result;
    }


    /**
     * 查业务超时阈值配置
     *
     * @param type
     * @return
     */
    @RequestMapping(value = "/overtimeConfig/get", method = RequestMethod.POST)
    public InvokeResult getOvertimeConfig(String type) {

        logger.info("get overtime config, type:{}", type);
        InvokeResult result;

        if (StrUtil.isEmpty(type)) {
            result = new InvokeResult();
            result.failure("参数错误");
            return result;
        }

        result = monitorRuleService.getOvertimeConfig(type);
        return result;
    }


    /**
     * 删除业务超时阈值配置
     *
     * @param type
     * @param financialCode
     * @return
     */
    @RequestMapping(value = "/overtimeConfig/del", method = RequestMethod.POST)
    public InvokeResult delOvertimeConfig(String type, String financialCode) {

        logger.info("delete overtime config, type:{}", type);
        InvokeResult result;

        if (StrUtil.isEmpty(type)) {
            result = new InvokeResult();
            result.failure("参数错误");
            return result;
        }

        result = monitorRuleService.delOvertimeConfig(type, financialCode);
        return result;
    }


}