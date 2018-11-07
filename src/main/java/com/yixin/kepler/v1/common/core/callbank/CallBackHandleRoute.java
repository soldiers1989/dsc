package com.yixin.kepler.v1.common.core.callbank;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.dsc.common.DscContant;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.v1.datapackage.dto.CallBackDTO;

/**
 * 各银行回调实现（路由）
 * Package : com.yixin.kepler.v1.common.core.callbank
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年9月25日 下午2:40:39
 *
 */
@Service
public class CallBackHandleRoute {

	private static final Logger logger = LoggerFactory.getLogger(CallBackHandleRoute.class);
	
	private static final String SUFFIX = "CallbackService";
	
	
	public CallBackDTO execute(CallBackDTO callBack){
		// 路由到具体的实现
		CallBackHandle bean = SpringContextUtil.getBean(
				callBack.getSourceCode().concat(SUFFIX), CallBackHandle.class);
		// 对回调数据进行校验
		BaseMsgDTO resultFlag = bean.checkData(callBack);
		if(DscContant.KeplerCode.SUCCESS.equals(resultFlag.getCode())){
			// 适配到具体的回调方法并实现
			callBack = bean.respResultHandle(callBack);
		}else{
			callBack.setBusinessData(resultFlag.getMessage());
		}
		logger.info("各银行回调实现，result{}", JsonObjectUtils.objectToJson(callBack));
		return callBack;
	}

}
