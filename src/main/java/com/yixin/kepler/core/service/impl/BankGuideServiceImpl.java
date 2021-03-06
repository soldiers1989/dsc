package com.yixin.kepler.core.service.impl;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.yixin.kepler.core.domain.webank.FirstTrialCallback;
import com.yixin.kepler.core.service.BankGuideService;
import com.yixin.kepler.v1.service.capital.yntrust.PaymentCallBackStrategy;

/**
 * 银行指引到具体实现类
 * Package : com.yixin.kepler.core.service.impl
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月10日 下午2:01:44
 *
 */
@Service("bankGuideService")
public class BankGuideServiceImpl implements BankGuideService{

	@Resource
	private FirstTrialCallback firstTrialCallback;
	
	@Resource
	private PaymentCallBackStrategy paymentCallBackStrategy;
	
	/**
	 * 微众回调接口
	 * @param weBankjson
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年7月10日 下午2:16:59
	 */
	@Override
	public String keplerWeBank(String weBankjson) {
		return firstTrialCallback.execute(weBankjson);
	}

	/**
	 * 资方回调接口
	 * @param callBackjson
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年9月19日 下午4:48:11
	 */
	@Override
	public String keplerTrust(String callBackjson) {
		return paymentCallBackStrategy.execute(callBackjson);
	}
}
