package com.yixin.web.service.monitor.impl;

import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.util.StrUtil;
import com.yixin.kepler.common.RedisClientUtil;
import com.yixin.web.common.constant.NoticeConst;
import com.yixin.web.common.constant.RedisConstant;
import com.yixin.web.service.monitor.NoticeRuleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Set;

/**
 * @description:
 * @date: 2018-10-10 14:30
 */
@Service
public class NoticeRuleServiceImpl implements NoticeRuleService {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Resource
    private RedisClientUtil redisClientUtil;

    @Override
    public Boolean judgeNeedNotice(String noticeId, String errorMessage) {


        boolean inWhiteList = checkWhiteList(errorMessage);

        if (inWhiteList) {
            return false;
        }

        boolean checkFrequency = checkFrequency(noticeId);

        return checkFrequency;
    }


    /**
     * 检查errorMessage是否在白名单，在白名单的errorMessage不用邮件预警
     *
     * @param errorMessage
     * @return
     */
    private boolean checkWhiteList(String errorMessage) {

        if (StrUtil.isEmpty(errorMessage)) {
            return false;
        }

        Set<String> whiteList = redisClientUtil.smembers(RedisConstant.ERROR_MESSAGE_WHITE_LIST_KEY);

        if (whiteList == null || whiteList.size() <= 0) {
            return false;
        }

        return whiteList.contains(errorMessage);
    }

    /**
     * 检查noticeId对应的预警频率
     *
     * @param noticeId
     * @return
     */
    private boolean checkFrequency(String noticeId) {

        if (StrUtil.isEmpty(noticeId)) {
            return true;
        }

        String noticeRule = getNoticeRule();
        String[] ruleValues = noticeRule.split("_");

        long frequencyRule = Long.parseLong(ruleValues[1]);
        int timeRule = Integer.parseInt(ruleValues[0]);
        int timeStop = Integer.parseInt(ruleValues[2]);

        String noticeFrequencyKey = buildNoticeFrequencyKey(noticeId);

        long frequency = redisClientUtil.incr(noticeFrequencyKey);
        logger.info("judge need notice, notice id :{}, frequency:{}", noticeId, frequency);

        if (frequency > frequencyRule) {
            logger.warn("judge need notice, notice stop too many notice, notice id :{}, frequency:{}", noticeId, frequency);

            if (frequency == (frequencyRule + 1)) {
                redisClientUtil.expire(noticeFrequencyKey, timeStop);
            }

            return false;
        }

        if (frequency == 1) {
            redisClientUtil.expire(noticeFrequencyKey, timeRule);
        }

        return true;
    }


    @Override
    public InvokeResult addNoticeRule(String rule) {
        logger.info(" add notice rule, rule :{}", rule);
        redisClientUtil.set(RedisConstant.NOTICE_FREQUENCY_RULE_KEY, rule);

        InvokeResult result = new InvokeResult();
        result.success();

        return result;
    }

    @Override
    public InvokeResult addErrorMessageWhiteList(String message) {
        InvokeResult result = new InvokeResult();

        if (StrUtil.isEmpty(message)) {
            result.failure("message 不能为空");
            return result;
        }

        redisClientUtil.sadd(RedisConstant.ERROR_MESSAGE_WHITE_LIST_KEY, message);

        result.success();
        return result;
    }


    @Override
    public InvokeResult delErrorMessageWhiteList(String message) {
        InvokeResult result = new InvokeResult();

        if (StrUtil.isEmpty(message)) {
            result.failure("message 不能为空");
            return result;
        }

        redisClientUtil.srem(RedisConstant.ERROR_MESSAGE_WHITE_LIST_KEY, message);

        result.success();
        return result;
    }

    @Override
    public InvokeResult queryErrorMessageWhiteList() {
        InvokeResult result = new InvokeResult();

        Set<String> data = redisClientUtil.smembers(RedisConstant.ERROR_MESSAGE_WHITE_LIST_KEY);

        result.setData(data);
        result.success();
        return result;
    }


    /**
     * 查询预警通知规则,如无配置预警规则使用默认规则
     *
     * @return
     */
    private String getNoticeRule() {
        String noticeRule = redisClientUtil.getValueByKey(RedisConstant.NOTICE_FREQUENCY_RULE_KEY);
        if (StringUtils.isEmpty(noticeRule)) {
            noticeRule = NoticeConst.DEFAULT_NOTICE_RULE;
        }
        logger.info("get notice rule, rule:{}", noticeRule);
        return noticeRule;
    }


    private String buildNoticeFrequencyKey(String noticeId) {

        noticeId = noticeId.replaceAll("\\s*", "");

        return RedisConstant.NOTICE_FREQUENCY_PREFIX + noticeId;
    }
}
