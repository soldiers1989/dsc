package com.yixin.kepler.v1.service.capital.yillion;

import com.yixin.common.exception.BzException;
import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.common.DscContant;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.kepler.core.base.AbstractBaseDealDomain;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.dto.BaseMsgDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @date: 2018-11-01 14:46
 */
@Service("YILLIONFirstTrialRequestPrepose")
public class FirstTrialRequestPrepose extends AbstractBaseDealDomain<Object, BaseMsgDTO> {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Override
    protected void getData() throws BzException {

    }

    @Override
    protected void assembler() throws BzException {

    }

    @Override
    protected InvokeResult<BaseMsgDTO> message() throws BzException {
        return null;
    }

    @Override
    public BaseMsgDTO requestPrepose(String applyNo, String financeCode) {
        DscSalesApplyMain mainInfo = DscSalesApplyMain.getByApplyNo(applyNo);
        if (mainInfo == null) {
            logger.error("申请单号为{},申请编号错误，查询不到订单信息", applyNo);
            return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "申请编号错误，查询不到订单信息");
        }
        logger.info("申请单号为{},一审前置提交信审校验,直接跳转二审", applyNo);
        return new BaseMsgDTO(CommonConstant.PreposeResultCode.SKIP, "跳转");
    }
}
