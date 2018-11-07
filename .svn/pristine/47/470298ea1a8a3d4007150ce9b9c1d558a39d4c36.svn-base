package com.yixin.kepler.core.config;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.SimpleApplicationEventMulticaster;
/**
 * 创建spring内部bean的配置类
 * @author sukang
 * @date 2018-08-09 10:52:49
 */
@Configuration
public class SpringBeanConfig {
	
	
	
	 /**
	  * 处理mq消息的线程池
	  * @return
	  */
	 @Bean(name = "taskExecutor")
	    public ThreadPoolExecutor getTaskExecutor(){
	        /**
	         * 定义线程池，核心线程数为2，
	         * 最大线程数为20，
	         * 空闲线程的存活时间为5秒，
	         * 使用LinkedBlockingDeque队列
	         */
	        return new ThreadPoolExecutor(
	                        5,
	                        20,
	                        5,
	                        TimeUnit.SECONDS,
	                        new LinkedBlockingDeque<Runnable>()
	                );
	    }
	
	/**
	 * 给处理监听事件的多路广播配置线程池
	 * @param taskExecutor
	 * @return
	 */
	@Bean(value = "applicationEventMulticaster")
	public SimpleApplicationEventMulticaster gEventMulticaster(){
		SimpleApplicationEventMulticaster multicaster = new SimpleApplicationEventMulticaster();
		ThreadPoolExecutor eventPool = new ThreadPoolExecutor(
				2,
				20,
				5, TimeUnit.SECONDS,
				new LinkedBlockingQueue<Runnable>(100));
		
		multicaster.setTaskExecutor(eventPool);
		return multicaster;
	}

}
