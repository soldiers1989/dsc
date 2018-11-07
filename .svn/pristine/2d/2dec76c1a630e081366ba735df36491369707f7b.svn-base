package com.yixin.web.service.monitor;

import com.yixin.dsc.dto.DscFlowResultForAlixDto;
import com.yixin.web.common.enums.OrderOperate;
import com.yixin.web.dto.monitor.OrderStepDurationDto;

import java.util.Date;

/**
 * @description:
 * @date: 2018-10-11 10:50
 */
public interface OrderOperateService {

    /**
     * 记录申请单的操作
     *
     * @param applyNo 申请单号
     * @param operate 操作枚举
     * @param remark  备注（可为空）
     */
    void recordOrderOperate(String applyNo, OrderOperate operate, String remark);

    /**
     * 记录申请单的操作-通知alix结果
     *
     * @param resultForAlix
     */
    void recordOrderOperate(DscFlowResultForAlixDto resultForAlix);


    /**
     * 查询申请单阶段耗时信息
     *
     * @param statsBefore
     * @param statusAfter
     * @param queryDate
     * @return
     */
    OrderStepDurationDto queryOrderStepDurationInfo(Integer statsBefore, Integer statusAfter, Date queryDate);


}
