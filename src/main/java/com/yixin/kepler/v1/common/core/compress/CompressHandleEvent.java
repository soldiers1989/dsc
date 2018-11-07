package com.yixin.kepler.v1.common.core.compress;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.kepler.core.listener.AbstractEvent;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.v1.datapackage.dto.CompressHandleDTO;

/**
 * 压缩结果处理的事件监听
 * Package : com.yixin.kepler.v1.common.core.compress
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年9月27日 下午8:29:34
 *
 */
public class CompressHandleEvent extends AbstractEvent{


	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(getClass());
	
	private static final String SUFFIX = "CompressHandle";


	public CompressHandleEvent(Object source) {
		super(source);
		this.beanClass = getClass();
	}
	
	
	@Override
	public void execute(Object source) {
		CompressHandleDTO handleDTO = (CompressHandleDTO) source;
		logger.info("压缩结果处理开始，param{}", JsonObjectUtils.objectToJson(handleDTO));
		// 路由到具体的实现
		CompressHandle bean = SpringContextUtil.getBean(
				handleDTO.getAssetNo().concat(SUFFIX), CompressHandle.class);
		// 处理结果
		BaseMsgDTO result = bean.handle(handleDTO);
		logger.info("压缩结果处理结束，result{}", JsonObjectUtils.objectToJson(result));
	}

}
