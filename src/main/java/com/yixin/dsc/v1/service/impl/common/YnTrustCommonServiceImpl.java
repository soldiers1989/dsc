package com.yixin.dsc.v1.service.impl.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yixin.dsc.dto.DscComputerParamDto;
import com.yixin.dsc.dto.DscComputerResultDto;
import com.yixin.dsc.v1.service.common.CommonService;
import com.yixin.kepler.common.enums.ConstantKeyEnum;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.enity.ConstantConfig;
import com.yixin.kepler.v1.common.enumpackage.YNTrustUrlEnum;
import com.yixin.kepler.v1.datapackage.dto.yntrust.YTCommonRequestDTO;

/**
 * @author YixinCapital -- chenjiacheng
 *         2018年09月19日 18:21
 **/
@Service("YNTRUSTCommonService")
public class YnTrustCommonServiceImpl implements CommonService {

	private static final Logger logger = LoggerFactory.getLogger(YnTrustCommonServiceImpl.class);


	@Override
	public DscComputerResultDto keplerComputer(DscComputerParamDto paramDto) {
		return null;
	}

	@Override
	public String baffleCheckCommon(Object parameter, String financialCode) {
		if (CommonConstant.BankName.YNTRUST_BANK.equals(financialCode)) { //微众
			String url = "";  //判断是哪个接口
			try {
				if(parameter instanceof YTCommonRequestDTO){
					YTCommonRequestDTO ytCommonRequestDTO = (YTCommonRequestDTO) parameter;
					url = ytCommonRequestDTO.getUrl();
				} else if (parameter instanceof String){
					JSONObject jsonObj = JSONObject.parseObject((String)parameter);
					url = jsonObj.getString("url");
				}
			} catch (Exception Exception) {
				logger.info("银行请求类型解析错误（不是银行的请求，直接返回）");
				return "";
			}
			//开关  0/请求银行  1不校验       为1时 返回直接通过
			String flag = ConstantConfig.getStringValeByKey(ConstantKeyEnum.YT_INTERFACE_FLAG.getKey());
			if ("0".equals(flag)) {
				return "";
			}
			String jsonStr = "";
			if (YNTrustUrlEnum.CREATE_ORDER.getUrl().equals(url)) { //一审
				jsonStr = ConstantConfig.getStringValeByKey(ConstantKeyEnum.YT_FIRST_TRIAL_RESPONSE_JSON.getKey());
			} else if (YNTrustUrlEnum.CANCEL_LOAN.getUrl().equals(url)) {
				jsonStr = ConstantConfig.getStringValeByKey(ConstantKeyEnum.YT_CANCEL_LOAN_RESPONSE_JSON.getKey());
			} else if (YNTrustUrlEnum.YX_CONFIRM_PAYMENT.getUrl().equals(url)) {
				jsonStr = ConstantConfig.getStringValeByKey(ConstantKeyEnum.YT_PAYMENT_RESPONSE_JSON.getKey());
			} else if (YNTrustUrlEnum.QUERY_TRADING_STATUS.getUrl().equals(url)) {
				jsonStr = ConstantConfig.getStringValeByKey(ConstantKeyEnum.YT_QUERY_TRADING_STATUS_RESPONSE_JSON.getKey());
			} else if (YNTrustUrlEnum.QUERY_BATCH_TRADING_STATUS.getUrl().equals(url)) {
				jsonStr = ConstantConfig.getStringValeByKey(ConstantKeyEnum.YT_QUERY_BATCH_TRADING_STATUS_RESPONSE_JSON.getKey());
			} else if (YNTrustUrlEnum.GET_BANK_CONTRACT_ID.getUrl().equals(url)) {
				jsonStr = ConstantConfig.getStringValeByKey(ConstantKeyEnum.YT_CONTRACT_RESPONSE_JSON.getKey());
			} else if (YNTrustUrlEnum.UPLOAD_VERIFICATION_CODE.getUrl().equals(url)) {
				jsonStr = ConstantConfig.getStringValeByKey(ConstantKeyEnum.YT_VERIFICATION_RESPONSE_JSON.getKey());
			}
			return "0".equals(jsonStr) ? "" : jsonStr;
		}
		return "";
	}
}
