package com.yixin.web.job;

import com.yixin.common.system.job.JobExecutor;
import com.yixin.common.utils.JobParamDTO;
import com.yixin.web.service.monitor.BusinessMonitorService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 按分钟定时执行的业务数据检查、预警
 *
 * @description:
 * @date: 2018-10-17 16:17
 */
@Component
public class BusinessMonitorForMinuteJobScheduler implements JobExecutor {
    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Resource
    private BusinessMonitorService businessMonitorService;


    @Override
    public JobParamDTO execute(JobParamDTO jobParamDTO) {
        JobParamDTO result = new JobParamDTO();

        logger.info("按分钟定时执行的业务数据检查、预警，开始.");

        try {
            businessMonitorService.creditAuditCheck();
        } catch (Exception e) {
            logger.error("资方信审卡单情况检查异常", e);
        }

        try {
            businessMonitorService.fundsRequestCheck();
        } catch (Exception e) {
            logger.error("资方请款卡单情况检查异常", e);
        }

        try {
            businessMonitorService.creditAuditCheck();
        } catch (Exception e) {
            logger.error("合同签约失败情况检查异常", e);
        }

        logger.info("按分钟定时执行的业务数据检查、预警，结束.");

        result.setResultCode("200");
        result.setResultCode("贷后任务执行结束");
        return result;
    }


}
