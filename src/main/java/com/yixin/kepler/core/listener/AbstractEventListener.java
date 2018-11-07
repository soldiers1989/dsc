package com.yixin.kepler.core.listener;

import org.springframework.context.ApplicationListener;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * 统一事件监听器
 * @author sukang
 */
@Component
public class AbstractEventListener implements ApplicationListener<AbstractEvent>{

	@Override
	@Async
	public void onApplicationEvent(AbstractEvent event) {
		event.doIt();
	}

}
