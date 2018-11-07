package com.yixin.kepler.component;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;

import com.yixin.dsc.util.MailMessageUtils;
import com.yixin.kepler.common.RedisClientUtil;

import java.util.concurrent.TimeUnit;


/**
 * redis分布式🔒
 * @author sukang
 * @date 2018-07-04 19:00:13
 */
@Component
public class RedisDistributedLock implements DistributedLock{

	private Logger logger = LoggerFactory.getLogger(getClass());

	private RedisClientUtil redisClientUtil;

	private ApplicationContext ioc;


	@Autowired
	public RedisDistributedLock(RedisClientUtil redisClientUtil, ApplicationContext applicationContext) {
		this.redisClientUtil = redisClientUtil;
		this.ioc = applicationContext;
	}

	@Override
	public Boolean lock(String lockKey, TimeUnit timeUnit, long timeout) {

		logger.info("键值{}开始添加redis锁",lockKey);
		long currentTimeMillis = System.currentTimeMillis();

		//获取超时时间的ms
		long lockTimeMs = timeUnit.toMillis(timeout);

		long setNx;
		try {
			setNx = redisClientUtil.setnx(lockKey,
                    String.valueOf(currentTimeMillis + lockTimeMs));
		} catch (Exception e) {
			logger.error("{}设置锁异常",lockKey,e);
			pubEvent(lockKey,e);
			return true;
		}

		if (setNx == 1 ) {
			logger.info("键值{}获取redis锁成功",lockKey);
			try {
				redisClientUtil.expire(lockKey, (int)timeUnit.toSeconds(timeout));
			} catch (Exception e) {
				logger.error("{}设置超时时间异常，异常信息为",lockKey,e);
			}
			return true;
		}
			
		String lockTime = redisClientUtil.getValueByKey(lockKey);
			
		if (lockTime != null && Long.valueOf(lockTime)
				< System.currentTimeMillis() ) {
			String oldValue = redisClientUtil.getSet(lockKey,
					String.valueOf(currentTimeMillis + lockTimeMs));

			//将两次获取的值对比
			if (oldValue != null && oldValue.equals(lockTime)) {
				return true;
			}
		}	
		return false;
	}
	
	

	@Override
	public Boolean unLock(String lockKey) {

		try {
			redisClientUtil.delByKey(lockKey);
			logger.info("{}解锁成功",lockKey);
		} catch (Exception e) {
			logger.error("解锁{}异常，异常原因为",lockKey,e);
			pubEvent(lockKey.concat("解锁异常"),e);
		}
		return true;
	}

	private void pubEvent(String context,Exception exception){
		//发送邮件事件
		MailMessageUtils.sendException(exception, context);
	}


}
