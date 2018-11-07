package com.yixin.kepler.core.service;

import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.dsc.dto.DscFlowResultForAlixDto;
import com.yixin.kepler.core.listener.ResultNotifyEvent;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.enity.AssetResultTask;

/**
 * @author sukang  on 2018/8/31.
 */
public interface BankResultService {


    /**
     * 银行结果查询任务
     * @param assetResultTask  订单对应的结果查询任务对象
     * @return 结果对象
     */

    BaseMsgDTO selectResult(AssetResultTask assetResultTask);

    /**
     * 发送结果推送事件默认实现
     * @param dscFlowResultForAlixDto  发送事件参数
     */
    default void publishEvent(DscFlowResultForAlixDto dscFlowResultForAlixDto){
        SpringContextUtil.getApplicationContext().publishEvent(
                new ResultNotifyEvent(dscFlowResultForAlixDto)
        );
    }
}
