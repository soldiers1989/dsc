package com.yixin.web.aop;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.dsc.common.exception.BzException;
import com.yixin.dsc.util.DateUtil;
import com.yixin.web.anocation.MethodMonitor;
import com.yixin.web.dto.monitor.InterfaceMonitorInfoDto;
import com.yixin.web.service.monitor.MonitorService;
import org.apache.commons.lang3.exception.ExceptionUtils;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.net.InetAddress;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @date: 2018-10-08 14:04
 */
@Aspect
@Component
public class MethodMonitorAop {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private MonitorService monitorService;

    /**
     * 配置切入点
     */
    @Pointcut("@annotation(com.yixin.web.anocation.MethodMonitor)")
    public void executeService() {
    }


    /**
     * 配置环绕通知
     */
    @Around(value = "@annotation(methodMonitor)")
    public Object aroundExec(ProceedingJoinPoint pjp, MethodMonitor methodMonitor) throws Throwable {

        Date startTime = new Date();

        InterfaceMonitorInfoDto monitorInfo = new InterfaceMonitorInfoDto();
        monitorInfo.setType("method");

        //目标方法执行前数据收集
        collectBeforeInfo(methodMonitor, pjp, monitorInfo, startTime);

        Object result = null;

        try {
            //目标方法执行
            result = pjp.proceed();

        } catch (BzException e) {
            //目标方法执行异常数据收集
            collectErrorInfo(e, monitorInfo);
            throw e;

        } catch (Throwable e) {
            //目标方法执行异常数据收集
            collectErrorInfo(e, monitorInfo);
            throw e;

        } finally {
            //目标方法执行后数据收集
            collectAfterInfo(result, monitorInfo, startTime);

            //记录 monitor info、检查异常和耗时问题
            monitorService.interfaceMonitor(monitorInfo);
        }
        //返回
        return result;
    }


    /**
     * 执行前数据收集
     *
     * @param methodMonitor
     * @param pjp
     * @param monitorInfo
     * @param startTime
     */
    private void collectBeforeInfo(MethodMonitor methodMonitor, ProceedingJoinPoint pjp, InterfaceMonitorInfoDto monitorInfo, Date startTime) {
        try {
            //服务器ip
            String ip = InetAddress.getLocalHost().getHostAddress();
            monitorInfo.setServerHost(ip);
            //类名
            Class clazz = pjp.getTarget().getClass();
            String className = clazz.getName();
            monitorInfo.setClassName(className);
            //方法名
            String methodName = pjp.getSignature().getName();
            monitorInfo.setMethodName(methodName);
            //开始时间
            String startTimeStr = DateUtil.dateToString(startTime, DateUtil.DEFAULT_TIMESTAMP_FORMAT);
            monitorInfo.setStartTime(startTimeStr);
            //参数
            Object[] args = pjp.getArgs();
            if (args != null && args.length > 0) {
                monitorInfo.setArgs(JsonObjectUtils.objectToJson(args));
            }

            List<Map<String, String>> keyParameters = getKeyParams(methodMonitor, args);
            monitorInfo.setKeyParameters(keyParameters);

            String keyParamtersStr = extractKeyParamters(keyParameters);
            monitorInfo.setKeyParametersStr(keyParamtersStr);

        } catch (Exception e) {
            logger.error("method monitor AOP before 异常", e);
            monitorInfo.setBeforeError(e.getMessage());
        }
    }


    /**
     * 异常信息收集
     *
     * @param e
     * @param monitorInfo
     */
    private void collectErrorInfo(Throwable e, InterfaceMonitorInfoDto monitorInfo) {

        try {
            //是否有异常
            monitorInfo.setHasError(true);
            //异常message
            monitorInfo.setMessage(e.getMessage());
            //异常堆栈
            monitorInfo.seteStackTrace(ExceptionUtils.getStackTrace(e));
        } catch (Exception e1) {
            monitorInfo.setExceptionError(e.getMessage());
        }
    }


    /**
     * 方法执行后数据收集
     *
     * @param result
     * @param monitorInfo
     * @param startTime
     */
    private void collectAfterInfo(Object result, InterfaceMonitorInfoDto monitorInfo, Date startTime) {
        try {
            //返回结果
            monitorInfo.setResult(JsonObjectUtils.objectToJson(result));
            //结束时间
            Date endTime = new Date();
            monitorInfo.setEndTime(DateUtil.dateToString(endTime, DateUtil.DEFAULT_TIMESTAMP_FORMAT));
            //接口耗时 /ms
            monitorInfo.setTimeConsumed(endTime.getTime() - startTime.getTime());

        } catch (Exception e) {
            logger.error("method monitor AOP after 异常", e);
            monitorInfo.setAfterError(e.getMessage());
        }
    }


    private List<Map<String, String>> getKeyParams(MethodMonitor methodMonitor, Object[] args) {

        try {
            String[] keyParam = methodMonitor.keyParam();

            if (keyParam == null || keyParam.length <= 0) {
                return null;
            }

            List<Map<String, String>> keyParams = Lists.newArrayList();

            for (String item : keyParam) {

                try {
                    String[] paramConf = item.split("::");
                    String paramName = paramConf[0];

                    String paramValue = parseParam(paramConf[1], args);

                    Map<String, String> param = Maps.newHashMap();
                    param.put(paramName, paramValue);
                    keyParams.add(param);
                } catch (Exception e) {
                    logger.error("parse param error");
                }
            }


            return keyParams;

        } catch (Exception e) {
            logger.error("get key parameters error");
            return null;
        }
    }


    private String parseParam(String paramPosition, Object[] args) {
        String[] keys = paramPosition.split("_");
        boolean firstLevel = keys.length == 1;

        int argPosition = Integer.parseInt(keys[0]);
        Object arg = args[argPosition];

        if (firstLevel) {
            return arg.toString();
        }

        JSONObject jsonObject = parseJSONObject(arg);

        Object propValue = null;

        if (jsonObject != null) {

            for (int i = 1; i < keys.length; i++) {
                String prop = keys[i];

                propValue = jsonObject.get(prop);

                jsonObject = parseJSONObject(propValue);
            }

            return propValue.toString();
        } else {
            return null;
        }
    }

    private JSONObject parseJSONObject(Object arg) {
        if (arg instanceof String) {
            try {
                return JSON.parseObject((String) arg);
            } catch (Exception e) {
                return null;
            }
        } else {
            return JSON.parseObject(JSON.toJSONString(arg));
        }
    }


    private String extractKeyParamters(List<Map<String, String>> parameters) {
        if (parameters == null || parameters.size() <= 0) {
            return "";
        }

        StringBuilder sb = new StringBuilder("");

        for (Map<String, String> item : parameters) {

            for (Map.Entry<String, String> entry : item.entrySet()) {
                sb.append(entry.getKey());
                sb.append("=");
                sb.append(entry.getValue());
            }

            sb.append("&");
        }

        if (sb.length() > 0) {
            sb.deleteCharAt(sb.length() - 1);
        }

        return sb.toString();
    }


}
