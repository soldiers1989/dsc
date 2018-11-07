package com.yixin.dsc.mq;

import org.springframework.amqp.core.Queue;
import org.springframework.amqp.rabbit.config.SimpleRabbitListenerContainerFactory;
import org.springframework.amqp.rabbit.connection.CachingConnectionFactory;
import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.amqp.SimpleRabbitListenerContainerFactoryConfigurer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import com.yixin.dsc.util.PropertiesManager;

@Configuration
@PropertySource("classpath:application.properties")
public class RabbitConfig {

	@Autowired
	private PropertiesManager propertiesManager;

//	@Primary
    @Bean(name="firstConnectionFactory")
    public ConnectionFactory firstConnectionFactory(
    		@Value("${spring.rabbitmq.address}") String host,
    		@Value("${spring.rabbitmq.port}") int port,
    		@Value("${spring.rabbitmq.username}") String username,
    		@Value("${spring.rabbitmq.password}") String password,
    		@Value("${spring.rabbitmq.virtualHost}") String virtualHost){
        CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
        connectionFactory.setHost(host);
        connectionFactory.setPort(port);
        connectionFactory.setUsername(username);
        connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
        return connectionFactory;
    }
	 /**
    @Bean(name="secondConnectionFactory")
    public ConnectionFactory secondConnectionFactory(
    		@Value("${spring.rabbitmq.hello.host}") String host,
    		@Value("${spring.rabbitmq.hello.port}") int port,
    		@Value("${spring.rabbitmq.hello.username}") String username,
    		@Value("${spring.rabbitmq.hello.password}") String password,
    		@Value("${spring.rabbitmq.hello.virtualHost}") String virtualHost){
    	CachingConnectionFactory connectionFactory = new CachingConnectionFactory();
    	connectionFactory.setHost(host);
    	connectionFactory.setPort(port);
    	connectionFactory.setUsername(username);
    	connectionFactory.setPassword(password);
        connectionFactory.setVirtualHost(virtualHost);
    	return connectionFactory;
    }
	  */
//    @Primary
    @Bean(name="firstRabbitTemplate")
    public RabbitTemplate firstRabbitTemplate(
    		@Qualifier("firstConnectionFactory") ConnectionFactory connectionFactory){
        RabbitTemplate firstRabbitTemplate = new RabbitTemplate(connectionFactory);
        return firstRabbitTemplate;
    }
    /**
    @Bean(name="secondRabbitTemplate")
    public RabbitTemplate secondRabbitTemplate(
    		@Qualifier("secondConnectionFactory") ConnectionFactory connectionFactory){
    	RabbitTemplate secondRabbitTemplate = new RabbitTemplate(connectionFactory);
    	return secondRabbitTemplate;
    }
     */
    @Bean(name="firstFactory")
    public SimpleRabbitListenerContainerFactory firstFactory(
    		SimpleRabbitListenerContainerFactoryConfigurer configurer,
    		@Qualifier("firstConnectionFactory") ConnectionFactory connectionFactory) {
        SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
        configurer.configure(factory, connectionFactory);
        return factory;
    }

    /**
    @Bean(name="secondFactory")
    public SimpleRabbitListenerContainerFactory secondFactory(
    		SimpleRabbitListenerContainerFactoryConfigurer configurer,
    		@Qualifier("secondConnectionFactory") ConnectionFactory connectionFactory) {
    	SimpleRabbitListenerContainerFactory factory = new SimpleRabbitListenerContainerFactory();
    	configurer.configure(factory, connectionFactory);
    	return factory;
    }
    */

    @Bean
    public Queue firstQueue() {
        return new Queue(propertiesManager.getDscKeplerQueue());
    }

    /**
    @Bean
    public Object secondQueue() {
        return new Queue(propertiesManager.getHelloQueue());
    }
    */
}