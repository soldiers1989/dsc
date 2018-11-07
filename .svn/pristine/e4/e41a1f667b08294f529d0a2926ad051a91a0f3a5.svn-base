package com.yixin.dsc.mq;

import com.yixin.common.utils.JsonObjectUtils;
import org.springframework.amqp.core.AmqpTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yixin.dsc.util.PropertiesManager;


@Component
public class QueuesSender {
	
	@Autowired
	private AmqpTemplate firstRabbitTemplate;
	
	/**
	 * 第一个 默认是config里有注解@Primary
	@Resource(name="secondRabbitTemplate")
	private AmqpTemplate secondRabbitTemplate;
	 */
	
	@Autowired
	private PropertiesManager propertiesManager;

	
	public void send(Object obj) {
		this.firstRabbitTemplate.convertAndSend(propertiesManager.getDscKeplerQueue(), JsonObjectUtils.objectToJson(obj));
	}
	/**
	public void send2(Object obj) {
		this.secondRabbitTemplate.convertAndSend(propertiesManager.getHelloQueue(), obj);
	}
	*/

}
