package com.yixin.web.service.monitor;

import com.yixin.common.utils.InvokeResult;
import com.yixin.web.dto.monitor.InterfaceMonitorInfoDto;

/**
 * 监控模块
 *
 * @description:
 * @date: 2018-10-08 11:51
 */
public interface MonitorService {


    /**
     * 接口监控逻辑
     *
     * @param monitorInfo
     */
    void interfaceMonitor(InterfaceMonitorInfoDto monitorInfo);


    InvokeResult monitorSwitch(String type, String status);
}
