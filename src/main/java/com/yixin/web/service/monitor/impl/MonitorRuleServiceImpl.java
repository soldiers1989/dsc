package com.yixin.web.service.monitor.impl;

import com.yixin.common.utils.InvokeResult;
import com.yixin.kepler.common.RedisClientUtil;
import com.yixin.web.common.constant.RedisConstant;
import com.yixin.web.service.monitor.MonitorRuleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Map;

/**
 * @description:
 * @date: 2018-10-10 11:47
 */
@Service
public class MonitorRuleServiceImpl implements MonitorRuleService {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Resource
    private RedisClientUtil redisClientUtil;


    @Override
    public InvokeResult addInterfaceTimeThreshold(String consumeLevel, String time) {

        String timeThresholdKey = buildTimeThresholdKey(consumeLevel);

        redisClientUtil.set(timeThresholdKey, time);

        InvokeResult result = new InvokeResult();
        result.success();

        return result;
    }

    @Override
    public Long getInterfaceTimeThreshold(int consumeLevel) {

        String redisKey = buildTimeThresholdKey(consumeLevel + "");

        String configTime = redisClientUtil.getValueByKey(redisKey);
        if (StringUtils.isEmpty(configTime)) {
            return null;
        }

        Long configTimeValue = Long.valueOf(configTime);
        return configTimeValue;
    }

    @Override
    public InvokeResult addOvertimeConfig(String type, String financialCode, String overtime) {
        redisClientUtil.hSet(type, financialCode, overtime);
        InvokeResult result = new InvokeResult();
        result.success();

        return result;
    }

    @Override
    public InvokeResult getOvertimeConfig(String type) {
        Map<String, String> data = redisClientUtil.hGetAll(type);

        InvokeResult result = new InvokeResult();
        result.success();
        result.setData(data);
        return result;
    }

    @Override
    public InvokeResult delOvertimeConfig(String type, String financialCode) {
        redisClientUtil.hDel(type, financialCode);
        InvokeResult result = new InvokeResult();
        result.success();

        return result;
    }


    private String buildTimeThresholdKey(String consumeLevel) {
        return RedisConstant.TIME_CONSUME_LEVEL_PREFIX + consumeLevel;
    }
}
