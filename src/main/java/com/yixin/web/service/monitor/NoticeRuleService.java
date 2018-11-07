package com.yixin.web.service.monitor;

import com.yixin.common.utils.InvokeResult;

/**
 * 预警通知规则
 *
 * @description:
 * @date: 2018-10-10 14:30
 */
public interface NoticeRuleService {

    /**
     * 判断是否需要发送预警邮件
     *
     * @param noticeId
     * @return
     */
    Boolean judgeNeedNotice(String noticeId, String errorMessage);

    /**
     * 添加预警规则
     * 规则格式  x_y_z  x为持续时间长度（单位：秒），y为持续时间长度内的通知次数,z表示停止通知时长
     * 例：5_10_100 表示5秒钟内通知10次后停止通知,停止通知时长为100秒
     *
     * @param rule
     * @return
     */
    InvokeResult addNoticeRule(String rule);


    /**
     * 添加error message 白名单
     *
     * @param message
     * @return
     */
    InvokeResult addErrorMessageWhiteList(String message);


    /**
     * 移除 error message 白名单
     *
     * @param message
     * @return
     */
    InvokeResult delErrorMessageWhiteList(String message);

    /**
     * 查询error message 白名单列表
     *
     * @return
     */
    InvokeResult queryErrorMessageWhiteList();
}
