package com.yixin.kepler.v1.common.constants;

import com.yixin.kepler.common.enums.WBTransCodeEnum;

/**
 * URL 请求常量
 * Package : com.yixin.kepler.common
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月9日 下午5:37:10
 *
 */
public interface UrlConstant {


	/**
	 * OSB 请求接口
	 */
	interface OsbSystemUrl{
		
		/**微众银行请求接口*/
        String weBankInterface = "weizhong/";
		/**工商银行请求接口*/
		String icbcBankInterface = "/osb/ICBC-OSB/proxy/yixinRequestICBCOSBService";
	}

	/**
	 * 结算 请求接口
	 */
	interface SettleSystemUrl{
		/**退车试算接口*/
        String preRepaymentTrial = "api/bizBaseSettleRPCService/preRepaymentTrial";
        
        /**查询放款信息接口*/
        String getLoanInfo = "api/carBackRPCService/getLoanInfo";
	}

	/**
	 * 微众银行--电子签约存证
	 */
	String WB_ELECONTRACT_URL = "weizhong/" + WBTransCodeEnum.WB_ELECTRON_CONTRACT_SAVE.getTransCode();

	String WB_ORDERCANCEL_URL = "weizhong/" + WBTransCodeEnum.WB_ORDER_CANCEL.getTransCode();
}
