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

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yixin.common.utils.InvokeResult;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.dsc.annotation.DscAlixCallTimeLog;
import com.yixin.dsc.dto.mongo.DscAlixCallMongoDto;
import com.yixin.dsc.util.DateUtil;

/**
 * alix访问接口时间统计
 *
 * Created by wangxulong on 2018/7/2.
 */
@Aspect
@Component
public class DscAlixCallTimeAop {
    private static Logger logger = LoggerFactory.getLogger(DscAlixCallTimeAop.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Pointcut("@annotation(com.yixin.dsc.annotation.DscAlixCallTimeLog)")
    public void executeService(){

    }

    @Around(value="@annotation(alixCallTimeLog)")
    public InvokeResult<Object> aroundMethod(ProceedingJoinPoint jp, DscAlixCallTimeLog alixCallTimeLog) throws Throwable{
        InvokeResult<Object> result = null;
        DscAlixCallMongoDto mongoDto = new DscAlixCallMongoDto();
        //执行前
      //执行前
        Date startDate = null;
        try{
            String mothodName = jp.getSignature().getName();
            String type = alixCallTimeLog.methodType();
            String ip = InetAddress.getLocalHost().getHostAddress();
            startDate = new Date();
            String startDateStr = DateUtil.dateToString(startDate,DateUtil.DEFAULT_TIMESTAMP_FORMAT);
            String applyNo = "";
            Object[] args = jp.getArgs();
            JSONObject jsonObject = null;
            if (args != null && args.length > 0 && args[0] != null) {
                if(String.class.equals(args[0].getClass())){
                    applyNo = args[0].toString();
                }
                jsonObject = JSONObject.parseObject(JSON.toJSONString(args[0]));
            }
            // 获取主键id值
            if (jsonObject != null) {
                applyNo = jsonObject.get("applyNo")==null?"":jsonObject.get("applyNo").toString();
            }

            mongoDto.setApplyNo(applyNo);
            mongoDto.setMethod(mothodName);
            mongoDto.setIp(ip);
            mongoDto.setStartTime(startDateStr);
            mongoDto.setType(type);
        } catch (Exception e) {
            logger.error("访问接口 AOP 异常",e);
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
                logger.error("访问接口 AOP 异常",e);
            }
        }
        //返回
        return result;
    }



}
