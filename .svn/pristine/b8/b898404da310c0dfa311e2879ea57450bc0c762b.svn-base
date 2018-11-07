package com.yixin.kepler.core.job;

import javax.inject.Inject;

import com.yixin.kepler.common.RedisClientUtil;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.yixin.common.exception.BzException;


/**
 * 定时消息入口处理
 * @author yixin zhangkai  on 2017-09-04 .
 */
public abstract class JobStrategyService implements Runnable {


    private Logger logger = LoggerFactory.getLogger(JobStrategyService.class);
    /**
     * redis锁的key值，支持不同的锁前缀
     */
    private String lockKeyPrefix;
    /**
     * 持有锁的最大锁时长(单位s)，防止线程以外打断，到时锁无法释放
     */
    private int lockActiveTime ;
    /**
     * 持有锁的最小时间(单位s)
     */
    private static int LOCK_ACTIVE_TIME_MIN = 10;

    /**
     * 默认最大的持有锁时长
     */
    private static int LOCK_ACTIVE_TIME_DEFAULT = 60;

    /**
     * 消息类型
     */
    protected String msgType;

    @Inject
    private RedisClientUtil redisClientUtil;

    /**
     * 
     * @param lockKeyPrefix 锁前缀
     */
    public JobStrategyService(String lockKeyPrefix){
        this(lockKeyPrefix, LOCK_ACTIVE_TIME_DEFAULT);
    }

    /**
     *
     * @param lockKeyPrefix 锁前缀
     * @param lockActiveTime 持有锁的最大锁时长(单位s),超过该时间会自动释放锁
     */
    public JobStrategyService(String lockKeyPrefix, int lockActiveTime){
        if(StringUtils.isEmpty(lockKeyPrefix)){
            logger.error("锁前缀参数异常 lockKeyPrefix:{}", lockKeyPrefix);
            throw new BzException("锁前缀参数错误");
        }
        this.lockKeyPrefix = lockKeyPrefix;

        if(lockActiveTime < LOCK_ACTIVE_TIME_MIN){
            logger.error("持有锁最大时长参数错误 实际{}， 期望:>={}", lockKeyPrefix, LOCK_ACTIVE_TIME_MIN);
            throw new BzException("持有锁最大时长错误");
        }
        this.lockActiveTime = lockActiveTime;
    }

    /**
     * 处理某个类型的任务，对外的主入口
     * @param type 任务类型
     */
    public void handlerThread(String type){
        this.msgType = type;
        checkLockKey();
        boolean isLock = getLock(lockKeyPrefix + msgType);
        // 获取锁成功
        if (isLock) {
            Thread thread = new Thread(this, "job-" + msgType);
            thread.start();
        }
    }


    @Override
    public void run() {
        checkLockKey();//防止不通过handlerThread()方法，直接启用线程
        long startTime = System.currentTimeMillis();
        logger.info("异步{}线程开始", msgType);
        try {
            // 处理业务
            execute();
        } finally {
            // 释放锁
            releaseLock(lockKeyPrefix + msgType);
            logger.info("异步{}线程结束 cost:{}ms", msgType,
                    System.currentTimeMillis() - startTime);
        }
    }

    /**
     * 处理异步消息
     *
     * @param
     * @return
     */
    protected abstract void execute();



    /**
     *
     * 获取redis分布式锁
     * （为了避免redis中key的有效时间失效，采用自主控室失效时间）
     *
     * @param key
     * @return
     */
    private boolean getLock(String key) {
        boolean result = false;
        String lockValue = redisClientUtil.getValueByKey(key);
        logger.info("开始获取异步消息锁 key:{} ,value:{}", key, lockValue);
        // 获取上次锁的时间
        long lockValueLong = StringUtils.hasText(lockValue) ? Long.parseLong(lockValue) : 0L;
        long  nowTime = System.currentTimeMillis();
        //锁是否空闲
        boolean isFreeLock = nowTime > lockValueLong;
        if (isFreeLock) {
            logger.info("获得竞争锁的机会 key:{} value:{}", key, lockValueLong);
            String value = System.currentTimeMillis() + lockActiveTime * 1000 + "";
            String oldValue = redisClientUtil.getSet(key, value);
            long oldValueLong = StringUtils.hasText(oldValue) ? Long.parseLong(oldValue) : 0L;
            //避免并发时同时设置
            if (nowTime > oldValueLong) {
                result = true;
                logger.info("竞争成功，获取锁 key:{} value:{}", key, lockValueLong);
            } else {
                logger.info("竞争失败，未获取锁 key:{} value:{}", key, lockValueLong);
            }
        } else {
            logger.info("获取锁失败，已被锁定 key:{}, value:{}", key, lockValueLong);
        }
        return result;
    }

    /**
     * 释放redis分布式锁
     *
     * @param key
     * @return
     */
    private void releaseLock(String key) {
        String lockValue = redisClientUtil.getValueByKey(key);
        logger.info("开始释放异步消息锁 key:{}, value:{}", key, lockValue);
        long lockValueLong = StringUtils.hasText(lockValue) ? Long.parseLong(lockValue) : 0L;
        //如未超时，释放持有锁
        if(System.currentTimeMillis() < lockValueLong){
            redisClientUtil.delByKey(key);
            logger.info("释放异步消息锁成功 key:{}", key);
        } else {
            logger.warn("释放异步消息锁-持有超时，已自动释放 key:{}", key);
        }
    }

    /**
     * 检查锁的标识，如果msgType或者lockKeyPrefix为空，抛异常BzException；
     *
     */
    private void checkLockKey(){
        if(StringUtils.isEmpty(this.msgType) || StringUtils.isEmpty(lockKeyPrefix)){
            logger.error("锁标识为空 lockKeyPrefix:[{}], msgType:[{}]", lockKeyPrefix, msgType);
            throw new BzException("锁标识为空");
        }
    }

}
