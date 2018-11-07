package com.yixin.kepler.core.rabbitmq;/**
 * Created by liushuai2 on 2017/5/24.
 */

import java.util.Iterator;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 *  处理消息
 *
 * Package : com.yixin.creditfront.core.job
 *
 * @author YixinCapital -- liushuai2
 *         2017年05月24日 11:30
 */

public abstract class DealMessage implements Runnable {
    private static final Logger logger = LoggerFactory.getLogger(DealMessage.class);
    protected static final String RABBITMQ_GROUP = "creditfront";

    /**
     * kafka    为 groupId
     * rabbitMq 为 固定字符串 creditfront
     */
    protected String group;

    /**
     * kafka    为  topic
     * rabbitMq 为  queueName
     * 这里统一处理叫做topic
     */
    protected String topic;
    
    /**
     * 消息体
     */
    protected String msg;
    
    /**
     * 消息体解析类
     */
    protected MsgResolver resolver;

    public DealMessage(final String group, final String topic, final String msg, final MsgResolver  resolver){
        this.group = group;
        this.topic = topic;
        this.msg = msg;
        this.resolver = resolver;
    }

    @Override
    public void run(){
        try {
            logger.info("消息入库开始msg={}", msg);
            Map<String, String> msgMap = resolver.resolve(msg);
            logger.info("解析后的消息msgMap={}", msgMap);
            persist(msgMap);
        } catch (Exception e){
            //如果入库失败，需要通过日志找回消息
            logger.error("消息入库失败msg={}", msg, e);
        }
    }

    /**
     * 实例化数据
     * @param msgMap    解析的参数
     */
    public void persist(Map<String, String> msgMap){
        try{
            logger.info("回调函数解析的消息msgMap={}", msgMap);
            if(msgMap != null && !msgMap.isEmpty()){
                Iterator<Map.Entry<String, String>> it = msgMap.entrySet().iterator();
                while(it.hasNext()){
                    Map.Entry<String, String> entry = it.next();
                    String bzId = entry.getKey();
                    String singleMsg = entry.getValue();
                    persist(group, topic, bzId, singleMsg);
                }
            }
        }catch (Exception e){
            logger.error("DealMessage.persist异常", e);
        }

    }

    /**
     * 实例化数据
     * @param group     group
     * @param topic     topic
     * @param bzId      业务id
     * @param singleMsg 单条消息
     */
    public abstract void persist(String group, String topic, String bzId, String singleMsg);
}
