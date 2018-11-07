package com.yixin.web.controller.monitor;

import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.dto.DscAdmittanceDto;
import com.yixin.web.anocation.InterfaceMonitor;
import com.yixin.web.common.enums.OrderOperate;
import com.yixin.web.job.OrderDurationStatisticsJobScheduler;
import com.yixin.web.service.monitor.BusinessMonitorService;
import com.yixin.web.service.monitor.MonitorService;
import com.yixin.web.service.monitor.NoticeService;
import com.yixin.web.service.monitor.OrderOperateService;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * @description:
 * @date: 2018-10-10 15:57
 */
@RestController
@RequestMapping("/api/monitor")
public class MonitorController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private MonitorService monitorService;

    @Resource
    private NoticeService noticeService;

    @Resource
    private OrderOperateService orderOperateService;

    @Resource
    private OrderDurationStatisticsJobScheduler orderDurationStatisticsJobScheduler;

    @Resource
    private BusinessMonitorService businessMonitorService;
    /**
     * 监控、预警开关
     *
     * @param type   monitor表示监控  notice表示预警通知
     * @param status on 表示打开  off 表示关闭
     * @return
     */
    @RequestMapping(value = "/switch", method = RequestMethod.POST)
    public InvokeResult monitorSwitch(String type, String status) {

        InvokeResult result;

        if (StringUtils.isEmpty(type) || StringUtils.isEmpty(status)) {
            result = new InvokeResult();
            result.failure("参数错误");
            return result;
        }

        result = monitorService.monitorSwitch(type, status);

        return result;
    }


    @InterfaceMonitor(errorMsg = "接口异常测试", keyParam = {"applyNo::0_applyNo"})
    @RequestMapping(value = "/test")
    public InvokeResult testx(@RequestBody DscAdmittanceDto dscAdmittanceDto) throws Exception {

        InvokeResult result = new InvokeResult();

        try {

            Thread.sleep(2000);

        } catch (Exception e) {

        }

        int a = 0;

        try {
            if (a == 0) {
                throw new Exception("接口监控异常测试");
            }
        } catch (Exception e) {
            noticeService.noticeBzError("MonitorController/testx", "111111", e.getMessage(), e);
//            throw e;
        }

        result.failure("接口监控异常测试");
        return result;
    }


    @RequestMapping(value = "/common/test")
    public InvokeResult recordOrderOperate(String applyNo, OrderOperate operate, String remark) throws Exception {

        InvokeResult result = new InvokeResult();

//        orderOperateService.recordOrderOperate(applyNo, operate, remark);

        businessMonitorService.creditAuditCheck();
        businessMonitorService.fundsRequestCheck();
        businessMonitorService.contractSignCheck();

        businessMonitorService.bankTrustLimitOccupyCheck();

        result.success();
        return result;
    }

}