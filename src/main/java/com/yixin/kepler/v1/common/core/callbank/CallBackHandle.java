package com.yixin.kepler.v1.common.core.callbank;

import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.v1.datapackage.dto.CallBackDTO;

/**
 * 银行回调处理
 * Package : com.yixin.kepler.v1.common.core.callbank
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年9月25日 下午12:00:40
 *
 */
public interface CallBackHandle {

	
	/**
	 * 对回调数据进行校验
	 * @param callBack
	 * @return 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月25日 下午12:09:42
	 */
	BaseMsgDTO checkData(CallBackDTO callBack);
	

	/**
	 * 适配到具体的回调方法并实现
	 * @param resultDTO
	 * @return 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月25日 下午12:05:57
	 */
	CallBackDTO respResultHandle(CallBackDTO callBack);
}
