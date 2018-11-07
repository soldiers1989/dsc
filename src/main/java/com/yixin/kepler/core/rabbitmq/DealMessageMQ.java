package com.yixin.kepler.core.rabbitmq;/**
 * Created by liushuai2 on 2017/5/24.
 */

import java.util.List;

import com.yixin.kepler.enity.MQMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  处理RabbitMq消息
 *
 * Package : com.yixin.creditfront.core.job
 *
 * @author YixinCapital -- liushuai2
 *         2017年05月24日 11:30
 */

public class DealMessageMQ extends DealMessage {
    private static final Logger logger = LoggerFactory.getLogger(DealMessageMQ.class);

    public DealMessageMQ(final String group, final String topic, final String msg, final MsgResolver  resolver){
        super(group, topic, msg, resolver);
    }

    /**
     * 实例化数据
     *
     *      注意：如果消息的唯一id重复，那么将会删除历史消息，并插入最新消息。
     *
     * @param group     group
     * @param topic     topic
     * @param bzId      业务id
     * @param singleMsg 单条消息
     */
    @Override
    public void persist(String group, String topic, String bzId, 
    		String singleMsg){
        /**
         * 注意：风险预警
         *      如果osb持续向mq中扔消息，数据库中会存在更多的垃圾数据。
         *
         *      但是：理论上一个序列号只会有一个消息
         *
         */
        List<MQMsg> msgs = MQMsg.getByBzId(bzId);
        if(msgs.size() > 0){
            logger.error("【RabbitMQ风险】存储RabbitMQ消息异常，对应的bzId已经存在，删除原有消息记录topic={},bzId={},msg={}", group, topic, bzId, singleMsg);
            MQMsg.delete(msgs);
        }
        logger.info("存储RabbitMQ消息topic={},bzId={},msg={}",
        		topic, bzId, singleMsg);
        
        MQMsg MQMsg = new MQMsg(topic, bzId, singleMsg);
        MQMsg.create();
    }
}
