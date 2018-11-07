package com.yixin.web.job;


import com.yixin.common.system.job.JobExecutor;
import com.yixin.common.utils.JobParamDTO;
import com.yixin.web.common.constant.NoticeConst;
import com.yixin.web.common.enums.OrderOperate;
import com.yixin.web.dto.monitor.OrderStepDurationDto;
import com.yixin.web.service.monitor.NoticeService;
import com.yixin.web.service.monitor.OrderOperateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 申请单指定阶段持续时长统计
 *
 * @description:
 * @date: 2018-10-11 10:56
 */
@Component
public class OrderDurationStatisticsJobScheduler implements JobExecutor {

    private static final Logger logger = LoggerFactory.getLogger(OrderDurationStatisticsJobScheduler.class);

    @Resource
    private OrderOperateService orderOperateService;

    @Resource
    private NoticeService noticeService;


    @Override
    public JobParamDTO execute(JobParamDTO jobParamDTO) {
        JobParamDTO result = new JobParamDTO();
        logger.info("申请单指定阶段持续时长统计,开始。");

        Date queryDate = new Date();

        OrderStepDurationDto alixAudit2WaitBank = orderOperateService.queryOrderStepDurationInfo(OrderOperate.REC_ALIX_AUDIT_APPLY.getValue(), OrderOperate.WAIT_BANK_AUDIT.getValue(), queryDate);
        String alixAudit2WaitBankStr = buildSubStr(alixAudit2WaitBank);

        OrderStepDurationDto waitBank2BankResult = orderOperateService.queryOrderStepDurationInfo(OrderOperate.WAIT_BANK_AUDIT.getValue(), OrderOperate.REC_BANK_AUDIT_RESULT.getValue(), queryDate);
        String waitBank2BankResultStr = buildSubStr(waitBank2BankResult);

        OrderStepDurationDto bankResult2PushAlix = orderOperateService.queryOrderStepDurationInfo(OrderOperate.REC_BANK_AUDIT_RESULT.getValue(), OrderOperate.PUSH_ALIX_AUDIT_RESULT.getValue(), queryDate);
        String bankResult2PushAlixStr = buildSubStr(bankResult2PushAlix);

        noticeService.noticeOrderDurationStatistics(alixAudit2WaitBankStr, waitBank2BankResultStr, bankResult2PushAlixStr);

        result.setResultCode("200");
        result.setResultContent("申请单指定阶段持续时长统计结束");

        logger.info("申请单指定阶段持续时长统计,结束。");
        return result;
    }


    /**
     * 统计数据转成描述字符串
     *
     * @param data
     * @return
     */
    private String buildSubStr(OrderStepDurationDto data) {

        if (data == null) {
            return "";
        }

        return String.format(NoticeConst.ORDER_DURATION_SUB_TEMPLATE, turnLongToString(data.getMin()), turnLongToString(data.getMax()), turnLongToString(data.getMid()), turnLongToString(data.getAverage()));
    }


    private String turnLongToString(Long value) {
        return value == null ? "" : value.toString();
    }


}
