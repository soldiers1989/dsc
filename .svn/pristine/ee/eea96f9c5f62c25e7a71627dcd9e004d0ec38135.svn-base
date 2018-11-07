package com.yixin.web.service.monitor.impl;

import com.yixin.common.utils.InvokeResult;
import com.yixin.web.dto.monitor.InterfaceMonitorInfoDto;
import com.yixin.web.runnable.InterfaceMonitorRunnable;
import com.yixin.web.service.monitor.MonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @date: 2018-10-08 16:45
 */
@Service("monitorService")
public class MonitorServiceImpl implements MonitorService {
    private Logger logger = LoggerFactory.getLogger(getClass());


    private Executor executor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());


    public static boolean INTERFACE_MONITOR_SWITCH = true;


    /**
     * 落地monitor info数据、检查异常、耗时情况
     *
     * @param monitorInfo
     */
    @Override
    public void interfaceMonitor(InterfaceMonitorInfoDto monitorInfo) {

        if (!INTERFACE_MONITOR_SWITCH) {
            logger.info("interface monitor switch off.");
            return;
        }

        try {
            //启动异步任务
            InterfaceMonitorRunnable interfaceMonitorTask = new InterfaceMonitorRunnable(monitorInfo);
            executor.execute(interfaceMonitorTask);
        } catch (Exception e) {
            logger.error("接口监控任务开启异常", e);
        }

    }

    @Override
    public InvokeResult monitorSwitch(String type, String status) {

        logger.info("monitor switch, type:{}, status:{}", type, status);

        if ("monitor".equals(type)) {
            INTERFACE_MONITOR_SWITCH = "on".equals(status);
        } else if ("notice".equals(type)) {
            NoticeServiceImpl.BZ_ERROR_NOTICE_SWITCH = "on".equals(status);
        }

        InvokeResult result = new InvokeResult();
        result.success();

        return result;
    }
}
