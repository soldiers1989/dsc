package com.yixin.kepler.v1.common.core.bankReq;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.kepler.core.listener.AbstractEvent;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.v1.datapackage.dto.BankRespResultHandleDTO;

/**
 * 处理银行返回结果的事件监听
 * Package : com.yixin.kepler.v1.job
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年9月17日 下午4:59:13
 *
 */
public class BankRespResultHandleEvent extends AbstractEvent{


	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public static final String SUFFIX = "RespResultHandle";


	public BankRespResultHandleEvent(Object source) {
		super(source);
		this.beanClass = getClass();
	}
	
	
	@Override
	public void execute(Object source) {
		BankRespResultHandleDTO resultDTO = (BankRespResultHandleDTO) source;
		logger.info("异步处理银行返回结果开始，param{}", JsonObjectUtils.objectToJson(resultDTO));
		// 路由到具体的实现
		BankRespResultHandle bean = SpringContextUtil.getBean(
				resultDTO.getAssetNo().concat(SUFFIX), BankRespResultHandle.class);
		// 处理结果
		BaseMsgDTO result = bean.respResultHandle(resultDTO);
		logger.info("异步处理银行返回结果结束，result{}", JsonObjectUtils.objectToJson(result));
	}

}
