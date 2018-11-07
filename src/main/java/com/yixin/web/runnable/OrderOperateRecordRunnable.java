package com.yixin.web.runnable;

import com.google.common.collect.Maps;
import com.yixin.web.common.enums.OrderOperate;
import com.yixin.web.entity.MOrderFlow;
import com.yixin.web.entity.MOrderInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

/**
 * @description:
 * @date: 2018-10-18 17:42
 */
public class OrderOperateRecordRunnable implements Runnable {
    private Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * 单号
     */
    private String applyNo;
    /**
     * 操作
     */
    private OrderOperate operate;
    /**
     * 备注
     */
    private String remark;

    public OrderOperateRecordRunnable(String applyNo, OrderOperate operate, String remark) {
        this.applyNo = applyNo;
        this.operate = operate;
        this.remark = remark;
    }

    @Override
    public void run() {

        logger.info("record order operate start, applyNo:{}, operate:{}, remark:{}", applyNo, operate, remark);
        if (StringUtils.isEmpty(applyNo) || operate == null) {
            return;
        }
        //更新申请单数据主表状态
        MOrderInfo orderInfo = MOrderInfo.findFirstByProperty(MOrderInfo.class, "applyNo", applyNo);
        int status = operate.getValue();

        if (orderInfo == null) {
            orderInfo = new MOrderInfo();
            orderInfo.setApplyNo(applyNo);
            orderInfo.setStatus(status);
            orderInfo.create();
        } else {
            orderInfo.setStatus(status);
            orderInfo.update();
        }

        MOrderFlow orderFlow = new MOrderFlow();

        Map<String, Object> params = Maps.newConcurrentMap();
        params.put("applyNo", applyNo);
        params.put("latest", 0);
        MOrderFlow latestOrderFlow = MOrderFlow.findFirstByProperties(MOrderFlow.class, params);

        if (latestOrderFlow != null) {
            latestOrderFlow.setLatest(1);
            latestOrderFlow.update();

            orderFlow.setPreviousId(latestOrderFlow.getId());
        }


        //添加申请单流程记录
        orderFlow.setApplyNo(applyNo);
        orderFlow.setStatus(status);
        orderFlow.setRemark(remark);
        orderFlow.setLatest(0);
        orderFlow.create();

        logger.info("record order operate end, applyNo:{}, operate:{}, remark:{}", applyNo, operate, remark);
    }
}
