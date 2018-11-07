package com.yixin.kepler.enity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.common.system.util.Label;


/**
 * <B>功能简述</B><br>
 * KRabbitmqMsgLog实体类
 *
 * @author liuhongshen
 * @date 2018年06月06日 14:14:54
 */
@Entity
@Table(name = "k_rabbitmq_msg_log")
public class RabbitmqMsgLog extends BZBaseEntiy{

    /**
     * 消息内容
     */
    @Label(name = "消息内容")
    @Column(name = "msg", columnDefinition = "LONGTEXT")
    private String msg;
    /**
     * 处理结果
     */
    @Label(name = "处理结果")
    @Column(name = "deal_status", length = 10)
    private Integer dealStatus;
    /**
     * 是否需要确认结果
     */
    @Label(name = "是否需要确认结果")
    @Column(name = "is_need_ack", length = 64)
    private String isNeedAck;
    /**
     * 交换器名称
     */
    @Label(name = "交换器名称")
    @Column(name = "exchange", length = 64)
    private String exchange;
    /**
     * 队列名称
     */
    @Label(name = "队列名称")
    @Column(name = "queue", length = 64)
    private String queue;
    /**
     * 消息topic
     */
    @Label(name = "消息topic")
    @Column(name = "topic", length = 64)
    private String topic;
    /**
     * 服务器地址信息
     */
    @Label(name = "服务器地址信息")
    @Column(name = "server_address", length = 64)
    private String serverAddress;


    public String getMsg() {
        return this.msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Integer getDealStatus() {
        return this.dealStatus;
    }

    public void setDealStatus(Integer dealStatus) {
        this.dealStatus = dealStatus;
    }

    public String getIsNeedAck() {
        return this.isNeedAck;
    }

    public void setIsNeedAck(String isNeedAck) {
        this.isNeedAck = isNeedAck;
    }

    public String getExchange() {
        return this.exchange;
    }

    public void setExchange(String exchange) {
        this.exchange = exchange;
    }

    public String getQueue() {
        return this.queue;
    }

    public void setQueue(String queue) {
        this.queue = queue;
    }

    public String getTopic() {
        return this.topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getServerAddress() {
        return this.serverAddress;
    }

    public void setServerAddress(String serverAddress) {
        this.serverAddress = serverAddress;
    }


    /**
     * 无参构造
     */
    public RabbitmqMsgLog() {

    }

} 