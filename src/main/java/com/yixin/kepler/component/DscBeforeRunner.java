package com.yixin.kepler.component;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

/**
 * 启动后加载初始化缓存数据
 * @author sukang
 * @date 2018-07-31 16:15:48
 */
@Component
public class DscBeforeRunner implements CommandLineRunner{
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	@Resource
	private RedisCacheUtil redisCacheUtil;

	@Override
	public void run(String... args) throws Exception {
		logger.info("开始加载缓存数据");
		long start = System.currentTimeMillis();
		
		try {
			redisCacheUtil.initSysDictData();
		} catch (Exception e) {
			logger.error("初始化数据异常，异常信息为:",e);
		}
		logger.info("数据加载完毕，耗时："+ (System.currentTimeMillis() - start)+"ms");
	}

}
