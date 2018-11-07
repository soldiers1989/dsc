package com.yixin.kepler.v1.service.capital.icbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yixin.common.exception.BzException;
import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.enity.AssetMainInfo;
import com.yixin.kepler.v1.common.constants.IcbcConstant;


/**
 * 取消订单
 * Package : com.yixin.kapler_v1.service.capital.icbc
 *
 * @author YixinCapital -- gumanxue
 * 2018/9/13 18:28
 */
@Service
public class OrderCancelRequest {

    public static final Logger logger = LoggerFactory.getLogger(OrderCancelRequest.class);
    @Autowired
    private IcbcOrderCancelRequest icbcOrderCancelRequest;
    /**
     * 请求银行取消订单
     * @param assetMainInfo
     * @param notifyType
     * @return
     * @throws BzException
     */
    public BaseMsgDTO sendResult(AssetMainInfo assetMainInfo, String notifyType) throws BzException {
        DscSalesApplyMain mainInfo = DscSalesApplyMain.getByApplyNo(assetMainInfo.getApplyNo());
        if(mainInfo == null){
            throw new BzException("申请编号错误，查询不到订单信息");
        }
        logger.info("通知工行结果原因:{}", IcbcConstant.ICBC_CHECK_RESULT_ALLSTATUS_AT);
        icbcOrderCancelRequest.setCheckRs(IcbcConstant.ICBC_CHECK_RESULT_ALLSTATUS_AT);
        InvokeResult<BaseMsgDTO> iResult = icbcOrderCancelRequest.doIt(mainInfo.getApplyNo());
        return (BaseMsgDTO) iResult.getData();
    }
}
