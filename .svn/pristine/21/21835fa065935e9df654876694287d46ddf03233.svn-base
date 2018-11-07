package com.yixin.dsc.aop;

import org.apache.commons.lang3.ArrayUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yixin.dsc.annotation.DscOrderFlowLog;
import com.yixin.kepler.enity.AssetOrderFlow;

/**
 * 订单全流程环绕AOP
 * Package : com.yixin.dsc.aop
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年8月30日 下午6:18:36
 *
 */
@Aspect
@Component
public class DscOrderFlowAop {

	private static Logger LOGGER = LoggerFactory.getLogger(DscOrderFlowAop.class);
	
	@Pointcut("@annotation(com.yixin.dsc.annotation.DscOrderFlowLog)")  //发起信审和发起请款
    public void sendBankRequest(){

    }
	
    /**
     * 环绕通知
     * @param joinPoint 可用于执行切点的类
     * @return
     * @throws Throwable 
     * @author YixinCapital -- wangwenlong
     *	       2018年8月30日 下午6:20:25
     */
	@Around(value="@annotation(orderFlowLog)")
    public Object around(ProceedingJoinPoint joinPoint, DscOrderFlowLog orderFlowLog) throws Throwable {
		String methodName = "";
		String applyNo = "";
		try{
    		methodName = joinPoint.getSignature().getName();
            Object[] args = joinPoint.getArgs();
            JSONObject jsonObject = null;
            if (ArrayUtils.isNotEmpty(args)) {
                jsonObject = JSONObject.parseObject(JSON.toJSONString(args[0]));
            }
            if (jsonObject != null) {
                applyNo = jsonObject.get("applyNo")==null?"":jsonObject.get("applyNo").toString();
            }
    		AssetOrderFlow.recordSendTimeByAOP(applyNo,methodName);
    	} catch (Exception e) {
            LOGGER.error("发起银行请求[信审/请款]，AOP记录发起时间异常",e);
        }
    	
        Object obj= (Object) joinPoint.proceed();
        LOGGER.info("发起银行请求[信审/请款]，结果:{}",JSON.toJSONString(obj));
        //记录结果时间在给alix 异步结果，如果受理失败的话，则在此记录
        try{
    		AssetOrderFlow.recordEndTimeByAOP(applyNo,methodName,obj);
    	} catch (Exception e) {
            LOGGER.error("发起银行请求[信审/请款]，AOP记录受理失败结果时间异常",e);
        }
        return obj;
    }
}
