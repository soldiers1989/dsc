package com.yixin.kepler.core.rabbitmq;/**
 * Created by liushuai2 on 2018/6/14.
 */

import java.io.UnsupportedEncodingException;
import java.util.concurrent.Future;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import com.yixin.common.system.ioc.SpringContextUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.core.ChannelAwareMessageListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.alibaba.fastjson.JSONObject;
import com.rabbitmq.client.Channel;
import com.yixin.kepler.common.JacksonUtil;


/**
 * Package : com.yixin.kepler.core.rabbitmq
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月14日 9:48
 */
@Component
@EnableAutoConfiguration
public class RabbitmqConsumer implements ChannelAwareMessageListener {

    private static final Logger logger = LoggerFactory.getLogger(RabbitmqConsumer.class);

    private static final String GROUP = "kepler";

    private MsgResolverMQ<String> msgResolver = new MsgResolverMQ<>();


    /**
     * 定义线程池，核心线程数为2，最大线程数为20，线程存活时间为5秒，使用LinkedBlockingDeque队列
     */
    @Autowired
    private ThreadPoolExecutor taskExecutor;

    @Override
    public void onMessage(final Message message, final Channel channel) {
        String body = null;
        try {
            body = new String(message.getBody(), "UTF-8");
        } catch (Exception e) {
        	logger.error("转换string异常",e);
        }
        logger.info("[接收mq消息]接收到的mq消息内容：{}", body);

        String queue = message.getMessageProperties().getConsumerQueue();
        long deliveryTag = message.getMessageProperties().getDeliveryTag();
        
        logger.info("[rabbitMQConsumer]接收到消息:properties->{},body->{}",
        		JSONObject.toJSON(message.getMessageProperties()), body);
        
        if (StringUtils.hasText(body)) {
            long start = System.currentTimeMillis();
            logger.info("接收RabbitMQ消息groupId={},queue={},message={},start={}",
            		GROUP, queue, body, start);
            
            try {
                if(taskExecutor==null){
                    taskExecutor = (ThreadPoolExecutor) SpringContextUtil.getApplicationContext().getBean("taskExecutor");
                }
                Future future = taskExecutor.submit(new DealMessageMQ(GROUP, queue, body, msgResolver));
                
                logger.info("调用RabbitMQ消息解析入库服务结束消息入库成功groupId={},queue={}",
                		GROUP, queue);
                
                RabbitmqConnectUtil.ack(deliveryTag, channel);
            } catch (Exception e) {
                logger.error("RabbitMQ消息入库成功groupId={},queue={}", GROUP, queue, e);
                //消息解析失败，需要重新推送
                RabbitmqConnectUtil.nackRequeue(deliveryTag, channel);
            }
        }else{
            logger.error("[rabbitMQConsumer]接收到的消息体为空");
            RabbitmqConnectUtil.ack(deliveryTag, channel);
        }

    }

}
