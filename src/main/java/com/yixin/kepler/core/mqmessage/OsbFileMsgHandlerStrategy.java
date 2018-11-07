package com.yixin.kepler.core.mqmessage;/**
 * Created by liushuai2 on 2017/11/13.
 */

import java.util.Iterator;
import java.util.Map;

import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.core.attachment.OsbFileDomain;
import com.yixin.kepler.core.base.IDealDomain;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.enity.MQMsg;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.yixin.kepler.enity.OsbFileLog;

import javax.inject.Inject;

/**
 * Package : com.yixin.creditfront.core.job.mqmessage
 *
 * @author YixinCapital -- liushuai2
 *         2017年11月13日 14:11
 */
@Service
public class OsbFileMsgHandlerStrategy extends AbstractMqMsgHandlerStrategy {
    private Logger logger = LoggerFactory.getLogger(OsbFileMsgHandlerStrategy.class);
    @Inject
    private OsbFileDomain osbFileDomain;

    @Value("${spring.rabbitmq.osb.file.queue}")
    private String queue;



    @Override
    protected MqMsgDealResult dealWorkflow(MQMsg msg) {
        return null;
    }

    @Override
    public String getQueue() {
        return queue;
    }

    /**
     *
     * 不同的业务类型对应不同的服务
     *
     */
    @Autowired
    private Map<String, IMqMsgBusinessHandler> businessHandlerMap;

    @Transactional
    @Override
    protected void process(MQMsg MQMsg) {
        logger.info("[osb处理mq消息] osb文件处理 params:{}", JSON.toJSONString(MQMsg));
        OsbFileLog log = OsbFileLog.getByTranNo(MQMsg.getBzId());

        if(log.getType() == CommonConstant.UPLOAD_TYPE){
            logger.info("文件上传");
            //上传任务
            osbFileDomain.dealUploadMQMsg(MQMsg);
        }else if(log.getType() == CommonConstant.DOWNLOAD_TYPE){
            logger.info("文件下载");
            //下载任务
            osbFileDomain.dealDownloadMQMsg(MQMsg);
        }else {
            logger.error("无法识别文件类型");
        }
        logger.info("[处理mq消息] osb文件处理成功");

    }

    @Override
    protected boolean isValid(MQMsg MQMsg) {
        logger.info("[osb处理mq消息]检查mq消息是否有效");
        OsbFileLog log = OsbFileLog.getByTranNo(MQMsg.getBzId());
        if(log == null) {
            logger.info("[osb处理mq消息]检查mq消息是否有效，OsbFileLog不存在。tranNo：{}", MQMsg.getBzId());
        }
        return log != null;
    }


    public boolean isOk(String applyNo){
        OsbFileLog log = OsbFileLog.getByApplyNo(applyNo);
        if(log != null){
            logger.info("[处理mq消息]申请单下有正在等待处理的任务，不能处理流程，applyNo：{}， osbFileLog.id：{}", applyNo, log.getId());
            return false;
        }
        return true;

    }
    /**
     * @return
     */
    public IMqMsgBusinessHandler get(String bzType){
        logger.info("获取银行交易处理类bzType：{}",bzType);
        String key = bzType + "UploadMsgHandler";
        logger.info("需要获取的银行实体信息类key：{}", key);
        IMqMsgBusinessHandler handler = businessHandlerMap.get(key);
        if(handler == null){
            logger.error("[处理mq消息]根据业务类型找不到对应的处理类 bzType：{}", bzType);
        }
        return handler;
    }

    /**
     * 获取驼峰配置的类名
     * @param val
     * @return
     */
    public static String getCamalName(String val) {
        StringBuilder className = new StringBuilder();
        boolean isUpper = false;
        char[] tbChars = val.toLowerCase().toCharArray();
        for (int i = 0; i < tbChars.length; i++) {
            String clas = String.valueOf(tbChars[i]);
            if (clas.equals("_")) {
                isUpper = true;
            } else {
                className.append(isUpper ? clas.toUpperCase() : clas);
                isUpper = false;
            }
        }
        return className.toString();
    }

    public static String getCamalNameWithFirstUpper(String val){
        String camalName = getCamalName(val);
        String upper = camalName.substring(0,1).toUpperCase() + camalName.substring(1, camalName.length());
        return upper;
    }

}
