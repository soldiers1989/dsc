package com.yixin.kepler.core.listener;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEvent;
import org.springframework.util.Assert;

import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author sukang
 */
public abstract class AbstractEvent extends ApplicationEvent{

	private static final long serialVersionUID = 1L;

	private final Logger logger = LoggerFactory.getLogger(this.getClass());
	
	protected Class<?> beanClass;

	public AbstractEvent(Object source) {
		super(source);
	}
	
	public void doIt(){

		List<Method> methods = Arrays.stream(beanClass.getDeclaredMethods())
				.filter(m -> "execute".equals(m.getName()))
				.collect(Collectors.toList());
		
		Assert.isTrue(methods.size() > 0, "未找到目标方法");
		
		try {
			methods.get(0).invoke(this, source);
		} catch (Exception e) {
			logger.error("执行出错：",e);
		} 
	}

	/**
	 * 事件执行
	 * @param source 事件参数
	 */
	protected abstract void execute(Object source);

}
