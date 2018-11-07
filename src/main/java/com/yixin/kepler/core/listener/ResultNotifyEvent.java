package com.yixin.kepler.core.listener;



import com.yixin.web.service.monitor.OrderOperateService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.common.utils.InvokeResult;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.dsc.dto.DscFlowResultForAlixDto;
import com.yixin.dsc.service.impl.flow.DscFlowImpl;

public class ResultNotifyEvent extends AbstractEvent{

	private static final long serialVersionUID = 1L;

	private Logger logger = LoggerFactory.getLogger(getClass());


	public ResultNotifyEvent(Object source) {
		super(source);
		this.beanClass = getClass();
	}

	@Override
	public void execute(Object source) {
		logger.info("订单结果异步通知的消息为{}",JsonObjectUtils.objectToJson(source));
		DscFlowResultForAlixDto resultDto = (DscFlowResultForAlixDto) source;

		DscFlowImpl dscFlowImpl = SpringContextUtil.getApplicationContext()
				.getBean(DscFlowImpl.class);

		InvokeResult<DscFlowResultForAlixDto> forAlix = dscFlowImpl.flowResultNoticeForAlix(resultDto);

		// 记录通知alix信审结果
		try {
            OrderOperateService orderOperateService = SpringContextUtil.getBean(OrderOperateService.class);
            orderOperateService.recordOrderOperate(resultDto);
		}catch (Exception e){
			logger.error("记录通知alix信审结果失败");
		}

		logger.info("订单编号:{},通知alix获取的结果为{}",resultDto.getApplyNo(),JsonObjectUtils.objectToJson(forAlix));
	}

}
