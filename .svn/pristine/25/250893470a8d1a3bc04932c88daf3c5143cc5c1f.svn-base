package com.yixin.dsc.aop;

import java.net.InetAddress;
import java.util.Date;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.stereotype.Component;

import com.yixin.common.utils.InvokeResult;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.dsc.annotation.DscCallBankTimeLog;
import com.yixin.dsc.dto.mongo.DscRestMongoDto;
import com.yixin.dsc.util.DateUtil;

/**
 *
 * 调用银行 接口 时间统计
 *
 * Created by wangxulong on 2018/7/2.
 */
@Aspect
@Component
public class DscCallBankTimeAop {
    private static Logger logger = LoggerFactory.getLogger(DscAlixCallTimeAop.class);


    @Autowired
    private MongoTemplate mongoTemplate;

    @Pointcut("@annotation(com.yixin.dsc.annotation.DscCallBankTimeLog)")
    public void executeService(){

    }


    @Around(value="@annotation(callBankTimeLog)")
    public Object aroundMethod(ProceedingJoinPoint jp, DscCallBankTimeLog callBankTimeLog) throws Throwable{
        InvokeResult<Object> result = null;
        DscRestMongoDto mongoDto = new DscRestMongoDto();
        //执行前
        Date startDate = null;
        try{
            String mothodName = jp.getSignature().getName();
            String ip = InetAddress.getLocalHost().getHostAddress();
            startDate = new Date();
            String startDateStr = DateUtil.dateToString(startDate,DateUtil.DEFAULT_TIMESTAMP_FORMAT);

            mongoDto.setMethod(mothodName);
            mongoDto.setIp(ip);
            mongoDto.setStartTime(startDateStr);
        } catch (Exception e) {
            logger.error("alix访问接口 AOP 异常",e);
            mongoDto.setRemarks(e.getMessage());
        }

        try {
            //执行目标方法
            result = (InvokeResult<Object>)jp.proceed();
        } catch (Throwable e) {
            //异常
            mongoDto.setRemarks(mongoDto.getRemarks()+"-"+e.getMessage());
            throw e;
        } finally {
            //执行后
            try {
                //执行后
            	Date endDate = new Date();
                String endDateStr = DateUtil.dateToString(endDate,DateUtil.DEFAULT_TIMESTAMP_FORMAT);
                mongoDto.setResult(JsonObjectUtils.objectToJson(result));
                mongoDto.setEndTime(endDateStr);
                if(startDate != null){
                	mongoDto.setIntervalTime(endDate.getTime() - startDate.getTime());
                }
                mongoTemplate.save(mongoDto);
            }catch (Exception e){
                logger.error("alix访问接口 AOP 异常",e);
            }
        }
        //返回
        return result;






    }



}
