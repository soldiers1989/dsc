package com.yixin.web.service.monitor.impl;

import com.alibaba.fastjson.JSON;
import com.yixin.dsc.dto.DscFlowResultForAlixDto;
import com.yixin.dsc.enumpackage.DscAlixLinkEnum;
import com.yixin.dsc.util.DateUtil;
import com.yixin.web.common.enums.OrderOperate;
import com.yixin.web.dto.monitor.OrderStepDurationDto;
import com.yixin.web.entity.MOrderFlow;
import com.yixin.web.runnable.OrderOperateRecordRunnable;
import com.yixin.web.service.monitor.OrderOperateService;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.Comparator;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.concurrent.Executor;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @description:
 * @date: 2018-10-11 10:51
 */
@Service
public class OrderOperateServiceImpl implements OrderOperateService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private Executor executor = new ThreadPoolExecutor(10, 10, 0L, TimeUnit.MILLISECONDS, new LinkedBlockingQueue<Runnable>());

    @Override
    public void recordOrderOperate(String applyNo, OrderOperate operate, String remark) {

        try {
            executor.execute(new OrderOperateRecordRunnable(applyNo, operate, remark));
        } catch (Exception e) {
            logger.error("record order operate error, applyNo:{}, operate:{}, remark:{}", applyNo, operate, remark);
        }
    }

    @Override
    public void recordOrderOperate(DscFlowResultForAlixDto resultForAlix) {
        if (resultForAlix == null) {
            return;
        }

        try {
            String applyNo = resultForAlix.getApplyNo();
            String link = resultForAlix.getLink();
            String message = resultForAlix.getMessage();

            if (DscAlixLinkEnum.CREDITFRONT.getCode().equals(link)) {
                recordOrderOperate(applyNo, OrderOperate.PUSH_ALIX_AUDIT_RESULT, message);
            }

        } catch (Exception e) {
            logger.error("record order operate error");

        }

    }

    @Override
    public OrderStepDurationDto queryOrderStepDurationInfo(Integer statsBefore, Integer statusAfter, Date queryDate) {

        List<Map<String, Date>> operateTimeBetweenStatus = MOrderFlow.queryOperateTimeBetweenStatus(statsBefore, statusAfter, DateUtil.getStartOfDay(queryDate), DateUtil.getEndOfDay(queryDate));

        if (operateTimeBetweenStatus == null || operateTimeBetweenStatus.size() <= 0) {
            return null;
        }

        OrderStepDurationDto result = new OrderStepDurationDto();

        List<Long> durations = Lists.newArrayList();

        long totalDuration = 0;

        for (Map<String, Date> operateDurationItem : operateTimeBetweenStatus) {

            Date dateBefore = operateDurationItem.get("dateBefore");
            Date dateAfter = operateDurationItem.get("dateAfter");

            if (dateAfter == null || dateBefore == null) {
                continue;
            }

            long durationHours = DateUtil.getMinutesBetweentDates(dateBefore, dateAfter);
            totalDuration = totalDuration + durationHours;

            durations.add(durationHours);
        }

        durations.sort(Comparator.naturalOrder());

        int durationCount = durations.size();

        result.setMin(durations.get(0));
        result.setMax(durations.get(durationCount - 1));
        result.setMid(durations.get(durationCount / 2));
        result.setAverage(totalDuration / durationCount);

        logger.info(JSON.toJSONString(result));

        return result;
    }
}
