package com.yixin.kepler.api;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.kepler.core.service.BankGuideService;
import com.yixin.kepler.v1.common.core.callbank.CallBackHandleRoute;
import com.yixin.kepler.v1.datapackage.dto.CallBackDTO;

/**
 * 资管对外（银行）api
 * Package : com.yixin.kepler.api
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月10日 下午1:50:26
 *
 */
@RestController
@RequestMapping(value="/api/keplerBankAPI")
public class KeplerBankAPI {

	private static final Logger LOGGER = LoggerFactory.getLogger(KeplerBankAPI.class);
	
	/**
	 * 银行指引到具体实现类
	 */
	@Resource
	private BankGuideService bankGuideService;
	
	/**
	 * 各银行回调实现（路由）
	 */
	@Resource
	private CallBackHandleRoute callBackHandleRoute;
	
	
	/**
	 * 接受微众回调
	 * @param weBankjson
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年7月10日 下午2:12:49
	 */
	@RequestMapping("/keplerWeBank")
	public String keplerWeBank(@RequestBody String weBankjson){
		LOGGER.info("微众回调接口 keplerWeBank入参：{}",weBankjson);
		String result = bankGuideService.keplerWeBank(weBankjson);
		LOGGER.info("微众回调接口 keplerWeBank出参：{}",result);
		return result;
	}
	
	/**
	 * 接受云南信托异步回调
	 * @param weBankjson
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年8月24日 上午10:29:50
	 */
	@RequestMapping("/keplerYNTrust")
	public String keplerYNTrust(@RequestBody String ynTrustjson){
		LOGGER.info("云南信托回调接口 keplerYNTrust入参：{}",ynTrustjson);
		String result = bankGuideService.keplerTrust(ynTrustjson);
		LOGGER.info("云南信托回调接口 keplerYNTrust出参：{}",result);
		return result;
	}
	
	
	/**
	 * 银行结果回调----标准通用
	 * @param callBack
	 * @return 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月25日 上午11:44:56
	 */
	@RequestMapping("/keplerCallBack")
	public CallBackDTO keplerCallBack(@RequestBody CallBackDTO callBack){
		LOGGER.info("银行结果回调开始，入参：{}", JsonObjectUtils.objectToJson(callBack));
		CallBackDTO result = callBackHandleRoute.execute(callBack);
		LOGGER.info("银行结果回调结束，出参：{}",JsonObjectUtils.objectToJson(result));
		return result;
	}
}	
