package com.yixin.kepler.v1.service.capital.webank;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yixin.dsc.dto.DscComputerParamDto;
import com.yixin.dsc.dto.DscComputerResultDto;
import com.yixin.dsc.service.common.DscKeplerCommonService;
import com.yixin.dsc.v1.service.common.CommonService;
import com.yixin.kepler.common.enums.ConstantKeyEnum;
import com.yixin.kepler.common.enums.WBTransCodeEnum;
import com.yixin.kepler.dto.webank.WBCommonReqDTO;
import com.yixin.kepler.enity.ConstantConfig;

/**
 * @author YixinCapital -- chenjiacheng
 *         2018年09月26日 14:27
 **/
@Service("WeBankCommonService")
public class WeBankCommonServiceImpl implements CommonService {
	
	@Autowired
	private DscKeplerCommonService dscKeplerCommonService;
	
	@Override
	public DscComputerResultDto keplerComputer(DscComputerParamDto paramDto) {
		return dscKeplerCommonService.keplerComputer(paramDto);
	}

	@Override
	public String baffleCheckCommon(Object parameter, String financialCode) {
		WBCommonReqDTO webankReqDTO;
		try {
			webankReqDTO = (WBCommonReqDTO)parameter;
		}catch (Exception Exception){
			return "";
		}
		String txnId = webankReqDTO.getTxnId(); //交易服务码, 通过这个字段标识调用微众哪个接口，每个接口对应一个编码
		//开关  0/请求银行  1不校验       为1时 返回直接通过
		String flag = ConstantConfig.getStringValeByKey(ConstantKeyEnum.WEBANK_INTERFACE_FLAG.getKey());
		if("0".equals(flag)){
			return "";
		}
		String jsonStr = "";
		if(WBTransCodeEnum.FIRST_TRIAL.getTransCode().equals(txnId)){ //初审/一审
			jsonStr = ConstantConfig.getStringValeByKey(ConstantKeyEnum.WEBANK_FIRST_TRIAL_RESPONSE_JSON.getKey());

		} else if(WBTransCodeEnum.LAST_TRIAL.getTransCode().equals(txnId)){ //复审 /二审
			jsonStr = ConstantConfig.getStringValeByKey(ConstantKeyEnum.WEBANK_LAST_TRIAL_RESPONSE_JSON.getKey());

		} else if(WBTransCodeEnum.WB_PAYMENT.getTransCode().equals(txnId)){ //请款
			jsonStr = ConstantConfig.getStringValeByKey(ConstantKeyEnum.WEBANK_PAYMENT_RESPONSE_JSON.getKey());

		} else if(WBTransCodeEnum.WB_PICKUP_CAR.getTransCode().equals(txnId)){  //订单提车
			jsonStr = ConstantConfig.getStringValeByKey(ConstantKeyEnum.WEBANK_PICKUP_CAR_RESPONSE_JSON.getKey());

		} else if(WBTransCodeEnum.WB_ORDER_CANCEL.getTransCode().equals(txnId)){//订单终止
			jsonStr = ConstantConfig.getStringValeByKey(ConstantKeyEnum.WEBANK_ORDER_CANCEL_RESPONSE_JSON.getKey());

		} else if(WBTransCodeEnum.WB_ELECTRON_CONTRACT_SAVE.getTransCode().equals(txnId)){//电子签约存证
			jsonStr = ConstantConfig.getStringValeByKey(ConstantKeyEnum.WEBANK_ELECTRON_CONTRACT_RESPONSE_JSON.getKey());

		} else if(WBTransCodeEnum.WB_COMPUTER.getTransCode().equals(txnId)){ //借据试算
			jsonStr = ConstantConfig.getStringValeByKey(ConstantKeyEnum.WEBANK_COMPUTER_RESPONSE_JSON.getKey());

		}

		return "0".equals(jsonStr)?"":jsonStr;
	}
}
