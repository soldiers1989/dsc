package com.yixin.kepler.core.domain.cmbc;

import org.springframework.stereotype.Service;

import com.yixin.common.exception.BzException;
import com.yixin.common.utils.InvokeResult;
import com.yixin.kepler.core.base.AbstractBaseDealDomain;
import com.yixin.kepler.dto.BaseMsgDTO;

@Service("CMBCPaymentTrialCheckRequestPrepose")
public class PaymentTrialCheckPrepose extends AbstractBaseDealDomain<Object, BaseMsgDTO>{

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

	public BaseMsgDTO requestPrepose(String applyNo,String financeCode){
		
		return BaseMsgDTO.successData("success");
	}
}
