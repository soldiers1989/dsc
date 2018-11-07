package com.yixin.web.service.monitor;

/**
 * @description:
 * @date: 2018-10-17 16:21
 */
public interface BusinessMonitorService {

    /**
     * 资方信审情况检查
     * 资方信审卡单超过x分钟，需要预警
     */
    void creditAuditCheck();

    /**
     * 资方请款情况检查
     * 资方请款卡单超过x分钟，需要预警
     */
    void fundsRequestCheck();

    /**
     * 合同签署情况检查
     */
    void contractSignCheck();

    /**
     * 未取消且银行未放款的订单，从申请时间到当前时间超过x天预警
     */
    void bankTrustLimitOccupyCheck();
}
