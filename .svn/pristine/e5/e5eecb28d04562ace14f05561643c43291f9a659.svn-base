package com.yixin.kepler.core.service.impl.webank;

import com.yixin.common.utils.InvokeResult;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.core.domain.AsyncTask;
import com.yixin.kepler.core.service.AfterLoanService;
import com.yixin.kepler.dto.BaseMsgDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


/**
 * @author sukang on 2018/9/6.
 */
@Service(value = "WeBankAfterLoanService")
public class WeBankAfterLoanServiceImpl implements AfterLoanService{
	
	@Autowired
    private AsyncTask asyncTask;


    @Override
    public BaseMsgDTO afterLoan(String applyNo) {

        InvokeResult<BaseMsgDTO> invokeResult = asyncTask
        		.requestBank(applyNo, BankPhaseEnum.AFTER_LOAN);
        if (invokeResult != null && invokeResult.getData() != null){
            return (BaseMsgDTO) invokeResult.getData();
        }
        return BaseMsgDTO.failureData("贷后资料同步失败");
    }
}
