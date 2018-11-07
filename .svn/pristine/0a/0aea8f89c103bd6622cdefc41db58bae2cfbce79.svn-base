package com.yixin.kepler.core.mqmessage;/**
 * Created by liushuai2 on 2017/11/13.
 */


import com.yixin.kepler.enity.MQMsg;

/**
 * Package : com.yixin.creditfront.core.job.mqmessage
 *
 * @author YixinCapital -- liushuai2
 *         2017年11月13日 16:35
 */
public interface IMqMsgBusinessHandler {

    /**
     * 处理流程
     * @param msg
     * @return
     */
    MqMsgDealResult dealWorkflow(MQMsg msg);

    /**
     * 判断是否可以执行
     * @param cfMQMsg
     * @return
     */
    boolean valid(MQMsg cfMQMsg);

    /**
     * 保存信息
     * @param cfMQMsg
     */
    void saveBusinessInfo(MQMsg cfMQMsg);

}
