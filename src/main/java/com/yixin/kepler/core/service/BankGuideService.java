package com.yixin.kepler.core.service;

/**
 * 银行指引到具体实现类
 * Package : com.yixin.kepler.core.service
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月10日 下午2:00:40
 *
 */
public interface BankGuideService {

	/**
	 * 微众回调接口
	 * @param weBankjson
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年7月10日 下午2:16:59
	 */
	String keplerWeBank(String weBankjson);
	
	/**
	 * 资方回调接口
	 * @param callBackjson
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年9月19日 下午4:48:11
	 */
	String keplerTrust(String callBackjson);
}
