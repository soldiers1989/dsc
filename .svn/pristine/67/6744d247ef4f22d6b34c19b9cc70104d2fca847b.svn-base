package com.yixin.kepler.core.service.impl.cmbc;

import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.core.service.AfterLoanService;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.enity.AssetAfterLoanTask;
import org.springframework.stereotype.Service;

/**
 * @author sukang on 2018/9/6.
 */
@Service(value = "CMBCAfterLoanService")
public class CMBCAfterLoanServiceImpl implements AfterLoanService{

    @Override
    public BaseMsgDTO afterLoan(String applyNo) {

        AssetAfterLoanTask assetAfterLoanTask = new AssetAfterLoanTask();
        assetAfterLoanTask.setFinancialCode(CommonConstant.BankName.CMBC);
        assetAfterLoanTask.setIsEnd(false);
        assetAfterLoanTask.setIsSuccess(false);
        assetAfterLoanTask.setExecState(0);
        assetAfterLoanTask.setApplyNo(applyNo);
        assetAfterLoanTask.setExecTimes(0);
        assetAfterLoanTask.create();
        return BaseMsgDTO.successData("贷后资料同步成功");
    }
}
