package com.yixin.web.service.monitor;

import com.yixin.web.dto.monitor.InterfaceMonitorInfoDto;

/**
 * 通知模块
 *
 * @description:
 * @date: 2018-10-08 16:50
 */
public interface NoticeService {

    /**
     * 业务异常预警通知
     *
     * @param interfaceName className + / + methodName，用于标识"唯一"的预警通知，可为空，如无，用message标识"唯一"
     * @param bzId          单号或者其他有用的业务编码，可为空
     * @param message       通知描述
     * @param e             异常，可为空
     */
    void noticeBzError(String interfaceName, String bzId, String message, Throwable e);

    /**
     * 接口异常预警通知
     *
     * @param monitorInfo
     */
    void noticeInterfaceError(InterfaceMonitorInfoDto monitorInfo);

    /**
     * 接口耗时超长预警通知
     *
     * @param monitorInfo
     */
    void noticeTimeConsumed(InterfaceMonitorInfoDto monitorInfo);


    /**
     * 申请单指定阶段耗时统计通知
     *
     * @param content1
     * @param content2
     * @param content3
     */
    void noticeOrderDurationStatistics(String content1, String content2, String content3);


    /**
     * 通用的业务通知方法
     *
     * @param subject
     * @param template
     * @param values
     */
    void commomBzNotice(String subject, String template, Object[] values);
}
