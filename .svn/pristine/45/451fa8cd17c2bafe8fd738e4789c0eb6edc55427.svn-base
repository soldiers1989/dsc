package com.yixin.kepler.core.rabbitmq;/**
 * Created by liushuai2 on 2017/8/10.
 */

import java.util.Map;

import net.sf.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.google.common.collect.Maps;

/**
 *
 * RabbitMQ消息解析类，基础类，
 *      这里暂时没有做任何事情
 *
 * Package : com.yixin.creditfront.core.job
 *
 * @author YixinCapital -- liushuai2
 *         2017年08月10日 8:52
 */
public class MsgResolverMQ<T> extends MsgResolver<T> {
    private static final Logger logger = LoggerFactory.getLogger(MsgResolverMQ.class);

    @Override
    public Map<String, String> resolve(final T msg){
        String message = (String) msg;
        Map<String, String> msgMap = Maps.newHashMap();
        logger.info("[rabbitMQConsumer]解析消息:{}", message);
        JSONObject jsonObject = JSONObject.fromObject(message);
        String transId = null;
        if(jsonObject.containsKey("transId")){
            transId = jsonObject.getString("transId");
        }else if(jsonObject.containsKey("serialNumber")){
            transId = jsonObject.getString("serialNumber");
        }else if(jsonObject.containsKey("tranNo")){
            transId = jsonObject.getString("tranNo");
        }
        if(StringUtils.hasText(transId)){
            msgMap.put(transId, message);
        }else{
            //无法解析交易流水号，消息认为是无主的，discard
            logger.error("[rabbitMQConsumer]消息入库失败!无法解析到业务编号!消息内容:{}", message);
        }
        return msgMap;
    }
}
