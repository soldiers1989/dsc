package com.yixin.kepler.v1.service.capital.yillion;

import com.yixin.common.exception.BzException;
import com.yixin.common.utils.InvokeResult;
import com.yixin.kepler.core.base.AbstractBaseDealDomain;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.dto.BaseMsgDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * @description:
 * @date: 2018-11-04 16:21
 */
@Service("YILLIONPaymentTrialCheckPrepose")
public class PaymentTrialCheckPrepose extends AbstractBaseDealDomain<Object, BaseMsgDTO> {

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


    /**
     * 银行请款请求前置
     *
     * @param applyNo     申请编号
     * @param financeCode 资方编码
     * @return
     */
    @Override
    public BaseMsgDTO requestPrepose(String applyNo, String financeCode) {
        BaseMsgDTO baseMsgDTO = new BaseMsgDTO();
        baseMsgDTO.setCode(CommonConstant.PreposeResultCode.NORMAL);
        baseMsgDTO.setMessage("校验通过，判断流程为正常-新定时");

        return baseMsgDTO;
    }
}
