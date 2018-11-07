package com.yixin.kepler.core.job;

import java.util.Map;

import com.yixin.kepler.core.mqmessage.AbstractMqMsgHandlerStrategy;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yixin.common.system.job.JobExecutor;
import com.yixin.common.utils.JobParamDTO;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * MQ消息处理类
 *
 * Package : com.yixin.creditfront.core.job
 *
 * @author YixinCapital -- xuejingtao
 *         2017年08月09日 13:27
 */
@Service
public class DealMQMsgJobScheduler implements JobExecutor {
	private static final Logger logger = LoggerFactory.getLogger(DealMQMsgJobScheduler.class);


	/**
	 * 根据不同类型的消息，有不同的处理类
	 */
	@Autowired
	private Map<String, AbstractMqMsgHandlerStrategy> mqJobStrategyMap;


    private boolean initFlag = true;

	/**
	 * 处理rabbitMq消息定时调度任务
	 * 
	 * @param jobParamDTO JobParamDTO
	 * @return JobParamDTO
	 */
	@Override
	public JobParamDTO execute(JobParamDTO jobParamDTO) {
	    logger.info("[处理MQ消息]开始初始化RabbitMq消息处理调度 initFlag：{}", initFlag);

		long start = System.currentTimeMillis();
        JobParamDTO result = new JobParamDTO();
        if(!initFlag){
            logger.info("[处理MQ消息]调度禁止启动");
            result.setResultCode("200");
            result.setResultContent("[处理MQ消息]initFlag = false 禁止启动调度监听");
            return result;
        }
		// 根据消息策略key值，处理不同类型的消息（mqJobStrategyMap不为空）
		for (String topic : mqJobStrategyMap.keySet()) {
	        logger.info("[处理MQ消息]开始处理topic={}的RabbitMq消息", topic);
			AbstractMqMsgHandlerStrategy service = mqJobStrategyMap.get(topic);
			if(service == null){
				logger.error("找不到对应的mq消息处理策略 topic:{}", topic);
				continue;
			}
			service.handlerThread(service.getQueue());
		}
		long useTime = System.currentTimeMillis() - start;
		result.setResultCode("200");
		result.setResultContent("[处理MQ消息]用时[" + (useTime) + "]毫秒 ");
		logger.info("[处理MQ消息]处理结束,用时[{}]", useTime);
		return result;
	}

    public void setInitFlag(boolean initFlag) {
        this.initFlag = initFlag;
    }


	public void setMqJobStrategyMap(Map<String, AbstractMqMsgHandlerStrategy> mqJobStrategyMap) {
		this.mqJobStrategyMap = mqJobStrategyMap;
	}
}
