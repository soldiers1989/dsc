package com.yixin.kepler.enity;/**
 * Created by liushuai2 on 2017/5/23.
 */

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.*;

import org.springframework.util.StringUtils;

import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.common.system.domain.EntityRepository;
import com.yixin.common.system.domain.JpqlQuery;
import com.yixin.kepler.core.constant.CommonConstant;

/**
 * RabbitMq消息暂存类
 * bzId：目前用来存储申请编号以后会兼容其他唯一标识
 * <p>
 * Package : com.yixin.creditfront.core.job
 *
 * @author YixinCapital -- liushuai2
 * 2017年05月23日 14:11
 */
@Entity
@Table(name = "k_mq_msg")
public class MQMsg extends BZBaseEntiy {

    private static final long serialVersionUID = -4779856742668614948L;

    public MQMsg() {
    }

    public MQMsg(String topic, String bzId, String msg) {
        this.topic = topic;
        this.msg = msg;
        this.setBzId(bzId);
    }

    /**
     * mq queue
     */
    @Column(name = "topic", length = 64, nullable = false)
    private String topic;
    /**
     * mq消息
     */
    @Basic(fetch = FetchType.EAGER)
    @Column(name = "msg", columnDefinition = "LONGTEXT", nullable = false)
    private String msg;

    @Column(name = "deal_status", length = 10, nullable = false)
    private int dealStatus;


    public String getTopic() {
        return topic;
    }

    public void setTopic(String topic) {
        this.topic = topic;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getDealStatus() {
        return dealStatus;
    }

    public void setDealStatus(int dealStatus) {
        this.dealStatus = dealStatus;
    }

    @Override
    public String update() {
        this.setUpdateTime(new Date());
        this.save();
        return super.getId();
    }

    /**
     * 获取分钟以前的数据
     *
     * @param beforeMinute
     * @return
     */
    public static List<MQMsg> getBefore(int beforeMinute) {
        return get(null, beforeMinute);
    }


    /**
     * 根据申请编号获取所有相关的消息
     *
     * @param topic kafka topic
     * @return
     */
    public static List<MQMsg> getUntreated(String topic) {
        return get(topic, 0, CommonConstant.DEAL_STATUS_UNDEAL);
    }

    /**
     * @param topic
     * @param beforeMinutes
     * @return
     */
    public static List<MQMsg> getUntreated(String topic, int beforeMinutes) {
        return get(topic, beforeMinutes, CommonConstant.DEAL_STATUS_UNDEAL);
    }

    public static List<MQMsg> getByBzId(String bzId) {
        StringBuilder jpql = new StringBuilder("select k from MQMsg as k where k.deleted is false ");
        List<Object> parameters = new ArrayList<Object>();

        if (StringUtils.hasText(bzId)) {
            jpql.append(" and k.bzId = ?1 ");
            parameters.add(bzId);
        }
        return getQueryChannel().list(getQueryChannel().createJpqlQuery(jpql.toString()).setParameters(parameters));

    }


    /**
     * 根据参数获取没有处理的数据
     *
     * @param topic
     * @param beforeMinutes
     * @return
     */
    public static List<MQMsg> get(String topic, int beforeMinutes,
    		Integer dealStatus) {
    	
        StringBuilder jpql = new StringBuilder("select k from MQMsg as k where "
        		+ "deleted is false ");
        
        List<Object> params = new ArrayList<>();
        int i = 0;

        if (StringUtils.hasText(topic)) {
            jpql.append(" and k.topic = ?".concat(String.valueOf(++i)));
           // createJpqlQuery.addParameter("topic", topic);
            params.add(topic);
        }
      /*  if (beforeMinutes > 0) {
            //yyyy-MM-dd HH:mm:ss
            jpql.append(" and k.createTime = :createTime ");
            createJpqlQuery.addParameter("createTime",
            		new Date(System.currentTimeMillis() - beforeMinutes * 60 * 1000));
        }
        */
        if (dealStatus != null) {
            jpql.append(" and k.dealStatus = ?".concat(String.valueOf(++i)));
            params.add(dealStatus);
        }
        return getRepository().createJpqlQuery(jpql.toString())
        		.setParameters(params).list();
    }

    /**
     * 根据ID获取实体类
     *
     * @param id
     * @return
     */
    public static MQMsg findById(String id) {
        String jpql = "select k from MQMsg as k where k.deleted is false and k.id=?1";
        List<Object> parameters = new ArrayList<Object>();
        parameters.add(id);
        return getQueryChannel().getSingleResult(getQueryChannel().createJpqlQuery(jpql).setParameters(parameters));

    }

    /**
     * 批量更新状态为处理中
     * 处理中 = 1
     *
     * @param msgs
     */
    public static void dealing(List<MQMsg> msgs) {
        for (MQMsg msg : msgs) {
            msg.dealing();
        }
    }

    /**
     * 批量更新状态为已处理
     * 已处理 = 2
     *
     * @param msgs
     */
    public static void dealed(List<MQMsg> msgs) {
        for (MQMsg msg : msgs) {
            msg.dealed();
        }
    }

    /**
     * 批量更新状态为无效的数据
     * 无效的数据(流程状态不对) = 3
     *
     * @param msgs
     */
    public static void dealInvalid(List<MQMsg> msgs) {
        for (MQMsg msg : msgs) {
            msg.dealInvalid();
        }
    }

    /**
     * 批量更新状态为未处理
     * 未处理 = 0
     *
     * @param msgs
     */
    public static void untreated(List<MQMsg> msgs) {
        for (MQMsg msg : msgs) {
            msg.untreated();
        }
    }

    /**
     * 更新状态为正在处理
     * 处理中 = 1
     */

    public void dealing() {
        this.dealStatus = CommonConstant.DEAL_STATUS_DEALING;
        this.update();
    }

    /**
     * 根据id来更新
     *
     * @param id
     */
    public static void dealing(String id) {
        MQMsg cfKafkaMsg = MQMsg.findById(id);
        cfKafkaMsg.dealing();
    }

    /**
     * 更新状态为已处理
     * 已处理 = 2
     */
    public void dealed() {
        this.dealStatus = CommonConstant.DEAL_STATUS_DEALED;
        this.update();
    }

    public static void dealed(String id) {
        MQMsg cfKafkaMsg = MQMsg.findById(id);
        cfKafkaMsg.dealed();
    }

    /**
     * 更新状态为未处理
     * 未处理 = 0
     */
    public void untreated() {
        this.dealStatus = CommonConstant.DEAL_STATUS_UNDEAL;
        this.update();
    }

    public static void untreated(String id) {
        MQMsg cfKafkaMsg = MQMsg.findById(id);
        cfKafkaMsg.untreated();
    }

    /**
     * 更新状态为无效的数据
     * 无效的数据(流程状态不对) = 3
     */
    public void dealInvalid() {
        this.dealStatus = CommonConstant.DEAL_STATUS_INVALID;
        this.update();
    }

    public static void dealInvalid(String id) {
        MQMsg cfKafkaMsg = MQMsg.findById(id);
        cfKafkaMsg.dealInvalid();
    }

    public static void delete(List<MQMsg> msgs) {
        for (MQMsg msg : msgs) {
            msg.setDeleted(true);
            msg.update();
        }
    }

}
