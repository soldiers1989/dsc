package com.yixin.web.common.aop;

import com.yixin.web.anocation.SysLog;
import com.yixin.web.common.entity.DscSysLog;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.aspectj.lang.reflect.MethodSignature;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.Method;
import java.net.URLDecoder;

/**
 * @author sukang
 */

@Aspect
@Component
public class SysLogAop {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Pointcut("@annotation(com.yixin.web.anocation.SysLog)")
    public void beforeLog(){}

    @Before(value = "beforeLog()")
    public void doBefore(JoinPoint joinpoint){

        try {
			ServletRequestAttributes requestAttributes =
			        (ServletRequestAttributes) RequestContextHolder.getRequestAttributes();

			HttpServletRequest request = requestAttributes.getRequest();
			MethodSignature methodSignature = (MethodSignature) joinpoint.getSignature();
			Method method = methodSignature.getMethod();

			String userName = request.getParameter("userName");

			if (method.getAnnotation(SysLog.class) != null){
			    String decodeName = "";
			    try {
			        decodeName = URLDecoder.decode(userName == null ? "" : userName, "utf-8");
			    }catch (Exception e){
			        logger.error("{}解码异常",e);
			    }

			    logger.info("用户名{},进行方法操作{}",decodeName,method.getName());

			    Object[] args = joinpoint.getArgs();

			    StringBuilder stringBuilder = new StringBuilder();

			    for(Object object:args ){
			        String params;
			        if ((params = isJson(object)) != null){
			            stringBuilder.append(params);
			        }else {
			            stringBuilder.append(String.valueOf(object)).append("::");
			        }
			    }

			    String resultParams = stringBuilder.toString();
			    String ip = getIp(request);
			    logger.info("入参为{}，ip为{}",resultParams,ip);

			    //保存操作日志
			    saveSysLog(method,decodeName,resultParams,ip,method.getAnnotation(SysLog.class));
			}
		} catch (Exception e) {
			logger.error("SysLogAop 前置异常，错误信息:{}",e.getMessage(),e);
		}
    }

    private void saveSysLog(Method method, String decodeName,
                            String resultParams, String ip,
                            SysLog annotation) {

        DscSysLog dscSysLog = new DscSysLog();
        dscSysLog.setAction(annotation.action());
        dscSysLog.setMethod(method.getName());
        dscSysLog.setIpAddr(ip);
        dscSysLog.setName(decodeName);
        dscSysLog.setParams(resultParams);
        dscSysLog.create();
    }


    /**
     * 使用Nginx等反向代理软件， 则不能通过request.getRemoteAddr()获取IP地址
     * @return ip
     */

    private String getIp(HttpServletRequest request){
        String ip = "";
        try {
            ip = request.getHeader("x-forwarded-for");
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("WL-Proxy-Client-IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_CLIENT_IP");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getHeader("HTTP_X_FORWARDED_FOR");
            }
            if (StringUtils.isEmpty(ip) || "unknown".equalsIgnoreCase(ip)) {
                ip = request.getRemoteAddr();
            }

        }catch (Exception e){
            logger.error("获取ip地址异常",e);
        }
        return ip;
    }



    private String isJson(Object object){
        try {

            return JSONObject.fromObject(object).toString();
        }catch (Exception e){
            //忽略
        }
        return null;
    }
}
