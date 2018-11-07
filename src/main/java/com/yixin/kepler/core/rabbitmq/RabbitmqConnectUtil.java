package com.yixin.kepler.core.rabbitmq;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.log;

import java.io.IOException;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import com.rabbitmq.client.*;
import com.yixin.common.exception.BzException;

/**
 * rabbitmq消费者工具类
 * 
 * <p>
 * 
 * Package : com.yixin.wfpt.util.rabbitmq
 * 
 * @author YixinCapital -- shaoml
 *		   2017年5月17日 上午11:25:53
 *
 */
public class RabbitmqConnectUtil {

    private static final Logger LOGGER = LoggerFactory.getLogger(RabbitmqConnectUtil.class);

    private static final int MAXIMUM_POOL_SIZE = 20;

    private static final long KEEP_ALIVE_TIME = 5000;

    private static Map<String, Connection> containers = new ConcurrentHashMap();

    private static Map<String, Channel> channleContainers = new ConcurrentHashMap<>();


    /**
     * 定义线程池，核心线程数为2，最大线程数为20，线程存活时间为5秒，使用LinkedBlockingDeque队列
     */
    private static ThreadPoolExecutor executor =
            new ThreadPoolExecutor(
                    2,
                    MAXIMUM_POOL_SIZE,
                    KEEP_ALIVE_TIME,
                    TimeUnit.MILLISECONDS,
                    new LinkedBlockingDeque<Runnable>()
            );

    /**
     * 构造方法私有化
     *
     * @author YixinCapital -- wuwenlong
     * 2017年6月20日 下午9:09:47
     */
    private RabbitmqConnectUtil() {

    }


    /**
     * 获取消费者connection
     *
     * @param virtualHost
     * @param passWord
     * @param userName
     * @param address
     * @return Channel
     * @author YixinCapital -- shaoml
     * 2017年5月19日 下午2:14:29
     */
    @Deprecated
    public static Connection getConnection(String address, String userName, String passWord, String virtualHost) {
        if(StringUtils.hasText(address) && StringUtils.hasText(userName) && StringUtils.hasText(passWord) && StringUtils.hasText(virtualHost)){
            String key = address + virtualHost + userName + passWord;
            Connection connection = containers.get(key);
            if(connection == null){
                synchronized (RabbitmqConnectUtil.class){
                    connection = containers.get(key);
                    if(connection == null){
                        try {
                            ConnectionFactory factory = new ConnectionFactory();
                            factory.setUsername(userName);
                            factory.setPassword(passWord);
                            factory.setVirtualHost(virtualHost);
                            Address[] addressMQ = Address.parseAddresses(address);
                            connection = factory.newConnection(executor, addressMQ);
                            containers.put(key, connection);
                        } catch (Exception e) {
                            LOGGER.error("连接RabbitMQ失败！", e);
                            throw new BzException("RabbitMQ连接失败-消费端创建RabbitMQ连接失败, 请检查RabbitMQ连接参数配置！", e);
                        }
                    }
                }
            }

            return connection;
        }
        return null;
    }





    /**
     * 应答
     * @param tag
     * @param channel
     */
    public static void ack(final long tag, final Channel channel){
        try {
            channel.basicAck(tag, false);
        } catch (IOException e) {
            LOGGER.error("[RabbitMQ消息]应答成功tag：{}",tag, e);
        }
    }

    public static void close(){
        synchronized (RabbitmqConnectUtil.class){
            for(Connection connection : containers.values()){
                try {
                    // Close this connection and all its channels with the AMQP.REPLY_SUCCESS close code and message 'OK'.
                    connection.close();
                } catch (IOException e) {
                    LOGGER.error("Close this connection error",e);
                }
            }
        }
    }

    /**
     * 失败应答，  直接抛弃消息
     *
     * 注意：这个会将失败的消息删除掉
     *
     * @param tag
     * @param channel
     */
    public static void nackRequeue(final long tag, final Channel channel){
       nack(tag, channel, false, true);
    }


    public static void nackNoRequeue(final long tag, final Channel channel){
        nack(tag, channel, false, false);
    }
    /**
     * 失败应答，  直接抛弃消息
     * @param tag
     * @param channel
     */
    public static void nack(final long tag, final Channel channel, final boolean multiple, final boolean requeue){
        try {
            channel.basicNack(tag, multiple, requeue);
        } catch (IOException e) {
            LOGGER.error("[RabbitMQ消息]应答失败tag：{}", tag, e);
        }
    }
    public static void rejectAck(final long tag, final Channel channel){
        try {
            channel.basicReject(tag, false);
        } catch (IOException e) {
            LOGGER.error("[RabbitMQ消息]应答失败tag：{}", tag, e);
        }
    }
}