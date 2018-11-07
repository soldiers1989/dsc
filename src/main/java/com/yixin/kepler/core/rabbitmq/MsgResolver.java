package com.yixin.kepler.core.rabbitmq;

import java.util.Map;

/**
 * 消息通知解析类
 */
public abstract class MsgResolver<T> {

    /**
     * 解析器
     * @param msg   接收到的kafka消息
     * @return
     *
     *      key value 对应的消息
     */
    public abstract Map<String, String> resolve(final T msg);

}