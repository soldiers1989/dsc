package com.yixin.kepler.common;/**
 * Created by liushuai2 on 2018/6/7.
 */

import com.yixin.kepler.common.enums.RuleTypeEnum;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.Map;

/**
 * Package : com.yixin.kepler.common
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月07日 10:14
 */
public class RuleUtil {
    private static final Logger logger = LoggerFactory.getLogger(RuleUtil.class);

    public static Object exec(RuleTypeEnum ruleType, String rule, Object val){
        Assert.notNull(ruleType, "ruleType is empty");
        Assert.notNull(rule, "rule is empty");
        Assert.notNull(val, "val is empty");
        logger.info("规则校验结果，ruleType:{}, rule:{}, val:{}", ruleType, rule, JacksonUtil.fromObjectToJson(val));

        Object out = null;
        switch (ruleType){
            case REG:
                return execRegex(rule, val);
            case JS:
                return execScript(rule, val);
            default:

        }
        logger.info("规则校验结果 out:{}", out);
        return out;
    }

    //  todo 执行javascript
    private static Object execScript(String rule, Object val){
        Assert.notNull(rule, "rule is empty");
        Assert.isTrue( !(val instanceof Map), "参数异常。val 不是map格式的数据");
        logger.info("执行script脚本规则 rule:{}, val:{}", rule, val);
        Map<String, Object> valMap = (Map<String,Object>) val;
        Object obj = ScriptEngineUtil.eval(rule, valMap);
        logger.info("执行script脚本规则 rule:{},val:{},result:{}", rule, val, obj);
        return obj;
    }

    private static boolean execRegex(String rule, Object val){
        Assert.notNull(rule, "rule is empty");
        Assert.notNull(val, "val is empty");
        logger.info("执行regex脚本规则 rule:{} val:{}");
        boolean matched = RegexUtil.pattern(rule, val);
        logger.info("执行regex脚本规则 rule:{} val:{} matched:{}", matched);
        return matched;
    }


}
