package com.yixin.dsc.service.settle;

import com.yixin.dsc.dto.order.DscComputeDTO;

import java.math.BigDecimal;
import java.util.Map;

/**
 * 结算服务dia
 * Package : com.yixin.dsc.service.settle
 *
 * @author YixinCapital -- wanghonglin
 * 2018/10/25 13:08
 */
public interface SettleService {

    /**
     * 调用结算计算服务费
     * @param sourceMap
     * @param applyNo
     * @return
     */
    DscComputeDTO computeServiceFee(Map<String,Object> sourceMap, String applyNo);
}
