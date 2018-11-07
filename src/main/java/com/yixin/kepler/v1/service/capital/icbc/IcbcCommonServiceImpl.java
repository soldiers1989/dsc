package com.yixin.kepler.v1.service.capital.icbc;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.fasterxml.jackson.databind.util.JSONPObject;
import com.yixin.kepler.common.JacksonUtil;
import com.yixin.kepler.v1.common.constants.IcbcConstant;
import com.yixin.kepler.v1.datapackage.dto.icbc.ICBCFirstTrialDataDTO;
import com.yixin.kepler.v1.datapackage.dto.icbc.IcbcAckDataDTO;
import com.yixin.kepler.v1.datapackage.dto.icbc.IcbcApplyDTO;
import com.yixin.kepler.v1.datapackage.dto.icbc.IcbcBackDataDTO;
import com.yixin.kepler.v1.utils.JacksonUtils;
import org.apache.poi.ss.formula.functions.T;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yixin.dsc.dto.DscComputerParamDto;
import com.yixin.dsc.dto.DscComputerResultDto;
import com.yixin.dsc.v1.service.common.CommonService;
import com.yixin.kepler.common.enums.ConstantKeyEnum;
import com.yixin.kepler.enity.ConstantConfig;

/**
 * @author YixinCapital -- chenjiacheng
 *         2018年09月26日 14:27
 **/
@Service("ICBCCommonService")
public class IcbcCommonServiceImpl implements CommonService {
	private static final Logger LOGGER = LoggerFactory.getLogger(IcbcCommonServiceImpl.class);

	@Override
	public DscComputerResultDto keplerComputer(DscComputerParamDto paramDto) {
		return null;
	}

	@Override
	public String baffleCheckCommon(Object parameter, String financialCode) {
		LOGGER.info("工行挡板判断开始入参:{},financialCode:{}", JacksonUtil.fromObjectToJson(parameter),financialCode);
		JSONObject jsonObject = JSON.parseObject(parameter.toString());
		String txnId =JSON.parseObject(jsonObject.getString("comm")).getString("trxcode");

		 //交易服务码, 通过这个字段标识调用工行哪个接口，每个接口对应一个编码
		LOGGER.info("工行挡板判断阶段txnId:{}", txnId);
		//开关  0/请求银行  1不校验       为1时 返回直接通过
		String flag = ConstantConfig.getStringValeByKey(ConstantKeyEnum.ICBC_INTERFACE_FLAG.getKey());
		String jsonStr = "";
		if("0".equals(flag)){
			return "";
		}else if("1".equals(flag)){
			if(IcbcConstant.TRXCODE_10101.equals(txnId)||IcbcConstant.TRXCODE_10181.equals(txnId)){ //终审
				jsonStr = ConstantConfig.getStringValeByKey(ConstantKeyEnum.ICBC_LAST_TRIAL_RESPONSE_JSON.getKey());
			} else if(IcbcConstant.TRXCODE_40101.equals(txnId)||IcbcConstant.TRXCODE_40181.equals(txnId)){ //请款
				jsonStr = ConstantConfig.getStringValeByKey(ConstantKeyEnum.ICBC_PAYMENT_RESPONSE_JSON.getKey());
			} else if(IcbcConstant.TRXCODE_20101.equals(txnId)){//订单终止
				jsonStr = ConstantConfig.getStringValeByKey(ConstantKeyEnum.ICBC_ORDER_CANCEL_RESPONSE_JSON.getKey());
			}
			String orderNo=JSON.parseObject(jsonObject.getString("comm")).getString("orderno");
			LOGGER.info("orderNo:{}", orderNo);
			IcbcApplyDTO<IcbcAckDataDTO> icbcAckDataDTO = null;
			icbcAckDataDTO = (IcbcApplyDTO<IcbcAckDataDTO>) JacksonUtils.getObjectFromJson(jsonStr, IcbcApplyDTO.class, IcbcAckDataDTO.class);
			icbcAckDataDTO.getComm().setOrderno(orderNo);
			icbcAckDataDTO.getData().setOrderno(orderNo);
			jsonStr=JacksonUtils.fromObjectToJson(icbcAckDataDTO).toString();
			LOGGER.info("工行挡板判断结束返回:{},financialCode:{}", JacksonUtil.fromObjectToJson(jsonStr),financialCode);
		}
		LOGGER.info("工行挡板判断结束返回:{}", JacksonUtil.fromObjectToJson(jsonStr));
		return "0".equals(jsonStr)?"":jsonStr;
	}
}
