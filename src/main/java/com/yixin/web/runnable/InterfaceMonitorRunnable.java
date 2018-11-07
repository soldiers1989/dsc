package com.yixin.web.runnable;

import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.web.dto.monitor.InterfaceMonitorInfoDto;
import com.yixin.web.service.monitor.MonitorRuleService;
import com.yixin.web.service.monitor.NoticeService;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * 接口监控 task
 *
 * @description:
 * @date: 2018-10-08 16:50
 */
public class InterfaceMonitorRunnable implements Runnable {


    private NoticeService noticeService;

    private MongoTemplate mongoTemplate;

    private MonitorRuleService monitorRuleService;

    private InterfaceMonitorInfoDto monitorInfo;


    public InterfaceMonitorRunnable(InterfaceMonitorInfoDto monitorInfo) {
        this.monitorInfo = monitorInfo;
    }

    @Override
    public void run() {

        //初始化
        init();

        //采集到的监控数据落地
        mongoTemplate.save(monitorInfo);

        //检查异常并通知
        Boolean hasError = monitorInfo.getHasError();

        if (hasError != null && hasError) {
            //异常通知
            noticeService.noticeInterfaceError(monitorInfo);
        }

        //检查耗时并通知
        int timeConsumeLevel = monitorInfo.getTimeConsumeLevel();

        //获取配置的耗时阈值
        Long configTimeValue = monitorRuleService.getInterfaceTimeThreshold(timeConsumeLevel);

        if (configTimeValue != null) {
            long timeConsumed = monitorInfo.getTimeConsumed();

            if (timeConsumed > configTimeValue) {
                //耗时超长通知
                noticeService.noticeTimeConsumed(monitorInfo);
            }
        }
    }

    private void init() {
        noticeService = SpringContextUtil.getBean(NoticeService.class);
        mongoTemplate = SpringContextUtil.getBean(MongoTemplate.class);
        monitorRuleService = SpringContextUtil.getBean(MonitorRuleService.class);
    }


}
