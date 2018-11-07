package com.yixin.web.controller.monitor;

import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.util.StrUtil;
import com.yixin.web.service.monitor.NoticeRuleService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @date: 2018-10-10 15:57
 */
@RestController
@RequestMapping("/api/notice/rule")
public class NoticeRuleController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private NoticeRuleService noticeRuleService;

    /**
     * 添加预警通知规则
     *
     * @param rule 通知规则
     *             规则格式  x_y_z  x为持续时间长度（单位：秒），y为持续时间长度内的通知次数,z表示停止通知时长
     *             例：5_10_100 表示5秒钟内通知10次后停止通知,停止通知时长为100秒
     * @return
     */
    @RequestMapping(value = "/add", method = RequestMethod.POST)
    public InvokeResult addNoticeRule(String rule) {

        InvokeResult result;

        if (StringUtils.isEmpty(rule)) {
            result = new InvokeResult();
            result.failure("参数错误");
            return result;
        }

        result = noticeRuleService.addNoticeRule(rule);

        return result;
    }

    @RequestMapping(value = "/errorMessageWhiteList/query", method = RequestMethod.GET)
    public InvokeResult queryErrorMessageWhiteList() {

        logger.info("query error message white list");
        InvokeResult result;

        result = noticeRuleService.queryErrorMessageWhiteList();

        return result;
    }


    /**
     * 修改预警 error message 白名单
     *
     * @param type
     * @param message
     * @return
     */
    @RequestMapping(value = "/errorMessageWhiteList/update", method = RequestMethod.POST)
    public InvokeResult updateErrorMessageWhiteList(Integer type, String message) {

        logger.info("update error message white list, type:{}, message:{}", type, message);
        InvokeResult result;

        if (type == null || StrUtil.isEmpty(message)) {
            result = new InvokeResult();
            result.failure("参数错误");
            return result;
        }

        if (type == 0) {
            result = noticeRuleService.addErrorMessageWhiteList(message);
        } else if (type == 1) {
            result = noticeRuleService.delErrorMessageWhiteList(message);
        } else {
            result = new InvokeResult();
            result.failure("参数错误");
        }

        return result;
    }

}