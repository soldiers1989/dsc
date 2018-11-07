package com.yixin.web.service.impl;

import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.core.domain.AsyncTask;
import com.yixin.kepler.dto.OsbAttachmentDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * 资料补传异步任务类
 * @author sukang
 */
@Component
public class AttachmentComponent {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Async
    public void dealAsyncTask(List<String> applyNos){

        //异步上传文件
        AsyncTask asyncTask = SpringContextUtil.getBean(AsyncTask.class);

        int loopNum = 1;
        for (String applyNo : applyNos){
            logger.info("订单{},开始执行异步上传文件任务{}",applyNo,loopNum);
            if (loopNum >= 1 && loopNum % 10 == 0){

                try {
                    TimeUnit.MINUTES.sleep(5);
                } catch (InterruptedException e) {
                    logger.error("订单{}执行任务线程休眠异常，异常信息为:",applyNo,e);
                }
            }

            OsbAttachmentDTO osbAttachmentDTO = new OsbAttachmentDTO();
            osbAttachmentDTO.setBzType(BankPhaseEnum.PAYMENT.getPhase());
            osbAttachmentDTO.setBzId(applyNo);
            asyncTask.uploadAttachment(osbAttachmentDTO);

            ++loopNum;
        }

    }





}
