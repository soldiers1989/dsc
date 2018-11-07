package com.yixin.kepler.core.mqmessage;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;

import com.alibaba.fastjson.JSON;
import com.yixin.common.exception.BzException;
import com.yixin.kepler.core.job.JobStrategyService;
import com.yixin.kepler.enity.MQMsg;

/**
 *
 * 异步消息处理策略接口
 *
 * @author yixin zhangkai on 2017-06-20 .
 */
public abstract class AbstractMqMsgHandlerStrategy extends JobStrategyService {
	private Logger logger = LoggerFactory.getLogger(AbstractMqMsgHandlerStrategy.class);

	/**
	 * redis锁的key值
	 */
	public static final String LOCK_KEY_PREFIX = "creditfront.mq.msg.lock.";

	// 默认消息处理的延迟时间(分钟)
	private static final int DEFAULT_DELAY_TIME = 2;
	// 消息处理的延迟时间(分钟)
	private int delayTime;


	public AbstractMqMsgHandlerStrategy() {
		this(DEFAULT_DELAY_TIME);
	}

	public AbstractMqMsgHandlerStrategy(int delayTime) {

		// 设置锁前缀
		super(LOCK_KEY_PREFIX);
		this.delayTime = delayTime;
	}

	/**
	 * 处理异步消息
	 */
	@Override
	public void execute() {
		logger.info("跑批处理mq消息开始 type:{}", msgType);
		int size = 0; // 总条数
		int successNum = 0; // 成功数
		List<MQMsg> untreatedMsgList = MQMsg.getUntreated(msgType, delayTime);
		logger.info("跑批处理mq消息需要处理的消息个数：{}", untreatedMsgList.size());
		if (!CollectionUtils.isEmpty(untreatedMsgList)) {
			size = untreatedMsgList.size();
			for (MQMsg mqMsg : untreatedMsgList) {
				try {
					boolean result = execute(mqMsg);
					if (result) {
						successNum++;
					}
				} catch (Exception e) {
					// 当前任务出现何种异常，都不影响之后的任务
					logger.error("[异步mq处理] 异常", e);
				}
			}
		}
		logger.info("跑批处理mq异步消息结束 type:{}, totalNum:{}, succeedNum:{}", msgType, size,
				successNum);
	}

	/**
	 * 处理异步消息-单个
     *
     *  注意：这里千万不加事务，因为涉及到工作流，需要在业务处理的加上事务
     *
	 * 
	 * @param msg
	 *            异步mq消息处理实体
	 * @return
	 */
	protected boolean execute(MQMsg msg) {

		logger.info("[异步mq处理] 开始 MQMsg：{}", JSON.toJSONString(msg));
		if(!isValid(msg)){
            logger.info("[异步mq处理]mq消息无效，id：{}", msg.getId());
			msg.dealInvalid();
		    return false;
        }
        try{
            process(msg);
			msg.dealed();
        }catch (BzException e2){
            logger.error("[异mq步处理]业务处理失败，错误信息：{}", e2.getMessage());
			msg.untreated();
            return false;
        }
        // 流程处理
        boolean wfResult = dealWorkflow0(msg);
        if (!wfResult) {
            logger.error("[异mq步处理] 结束 流程处理失败 BzId:{}", msg.getBzId());
        }

		logger.info("[异步mq处理] 结束 处理成功 BzId:{}", msg.getBzId());
		return true;
	}

    /**
     * 检查mq消息是否有效
     *      默认为有效。
     *      业务系统需要实现该方法来检查是否所有的业务数据都存在。
     *
     * @param msg
     * @return
     *         true：消息有效
     *         false：消息无效
     */
	protected boolean isValid(MQMsg msg){
	    return true;
    }

	/**
	 * 业务处理
	 * 
	 * @param msg
	 *            异步mq消息处理实体
	 * @return true/成功； false/失败
	 */
	@Transactional
	protected abstract void process(MQMsg msg) throws IllegalArgumentException;


    protected abstract MqMsgDealResult dealWorkflow(MQMsg msg);


	public abstract String getQueue();



    /**
     * 流程处理
     * @param msg
     *          rabbitmq消息
     * @return true/成功； false/失败
     */
	private boolean dealWorkflow0(MQMsg msg) {
        return true;
	}

}
