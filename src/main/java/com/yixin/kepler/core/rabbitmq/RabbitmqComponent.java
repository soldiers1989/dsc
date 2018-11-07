package com.yixin.kepler.core.rabbitmq;/**
 * Created by liushuai2 on 2018/5/4.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.amqp.core.Message;

/**
 *
 * @author YixinCapital -- liushuai2
 *         2018年05月04日 16:00
 */
@Component
public class RabbitmqComponent {
    public static final Logger logger = LoggerFactory.getLogger(RabbitmqComponent.class);
    @Autowired
    private AmqpTemplate rabbitTemplate;

    private final String DEFAULT_EXCHANGE = "compressor-exchange";


    public void send(String routingKey, String body){
        logger.info("发送rabbitmq消息，routingKey={},body={}", routingKey, body);
        Message msg = MessageBuilder.withBody(body.getBytes()).build();
        rabbitTemplate.convertAndSend(DEFAULT_EXCHANGE,routingKey, msg);
        logger.info("发送rabbitmq消息结束，routingKey={},body={}", routingKey, body);

    }


    public void send(String exchange, String routingKey, String body){
        logger.info("发送rabbitmq消息，exchange={},routingKey={},body={}", exchange, routingKey, body);
        Message msg = MessageBuilder.withBody(body.getBytes()).build();
        rabbitTemplate.convertAndSend(DEFAULT_EXCHANGE, routingKey, msg);
        logger.info("发送rabbitmq消息结束，exchange={},routingKey={},body={}", exchange, routingKey, body);
    }
}
