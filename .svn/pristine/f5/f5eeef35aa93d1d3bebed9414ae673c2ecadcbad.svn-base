package com.yixin.common.system.ioc;/**
 * Created by liushuai2 on 2018/5/4.
 */

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * Package : com.yixin.common.system.ioc
 *
 * @author YixinCapital -- liushuai2
 *         2018年05月04日 19:49
 */
@Component
public class SpringContextUtil implements ApplicationContextAware {
    private static ApplicationContext applicationContext; // Spring应用上下文环境

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
      SpringContextUtil.applicationContext = applicationContext;
    }

    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }
    
    /**
     * 根据类型获取bean
     * @param clazz
     * @return
     */
    public static <T> T getBean(Class<T> clazz){
    	return applicationContext.getBean(clazz);
    }
    
    public static Object getBean(String beanName){
    	return applicationContext.getBean(beanName);
    }
    
    public static <T> T getBean(String beanName,Class<T> clazz){
    	return applicationContext.getBean(beanName, clazz);
    }
    
    
    
    
    
}
