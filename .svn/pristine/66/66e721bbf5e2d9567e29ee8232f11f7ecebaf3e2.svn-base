package com.yixin.kepler.v1.common.util;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import com.yixin.kepler.v1.utils.JacksonUtils;

/**
 * 通讯基础类（无需转换入参对象）
 * Package : com.yixin.kepler.v1.common.util
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年9月26日 下午4:27:25
 *
 */
public class RestAskUtils {

	private static Logger logger = LoggerFactory.getLogger(RestAskUtils.class);

	
	private RestAskUtils(){}

	private static HttpHeaders defaultHeaders(){
	    HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
        return headers;
    }

	
    /**
     * 获取默认的模板
     * @return
     */
    private static RestTemplate defaultHttpTemplate(){
        if(logger.isDebugEnabled()) logger.debug("[rest请求]获取http对应的RestTemplate");
	    return new RestTemplate();
    }

  
    /**
     * json转化参数
     * @param parameter
     * @return
     */
    private static String getParameter(Object parameter){
        String jsonStr = "";
        if (null != parameter) {// 如果入参不为空
            if (parameter instanceof String) {
                logger.info("[rest请求]rest请求参数是字符串类型，不需要通过JacksonUtils.fromObjectToJson()转化");
                jsonStr = (String) parameter;
            } else {
                logger.info("[rest请求]rest请求参数是对象类型，需要通过JacksonUtils.fromObjectToJson()转化");
                jsonStr = JacksonUtils.fromObjectToJson(parameter);
            }
        }
        logger.info("[rest请求]rest请求参数json {}", jsonStr);
        return jsonStr;
    }

    
    /**
     * 发送post请求
     * @param url
     * @param parameter
     * @return 
     * @author YixinCapital -- chen.lin
     *	       2018年9月26日 下午4:30:01
     */
    public static String post(String url, Object parameter) {
        Assert.notNull(url, " url is empty");
        Assert.notNull(parameter, "parameter is empty");
        logger.info("[rest请求]rest请求开始url={}", url);
        String result = null;
        try {
            logger.info("[rest请求]rest请求转换后url={}", url);
            HttpEntity<String> formEntity = new HttpEntity<>(getParameter(parameter), defaultHeaders());
            result = defaultHttpTemplate().postForObject(url, formEntity, String.class);
        } catch (Exception e) {
            logger.error("[rest请求]rest通讯异常，请检查配置信息。默认返回null", e);
        }finally {
            logger.info("[rest请求]rest请求结束 result={}", result);
            return result;
        }
    }

    
    /**
     * 发送get请求
     * @param url
     * @param parameter
     * @return 
     * @author YixinCapital -- chen.lin
     *	       2018年9月26日 下午4:30:01
     */
    public static String get(String url, Object... parameter) {
    	Assert.notNull(url, " url is empty");
        Assert.notNull(parameter, "parameter is empty");
        logger.info("[rest请求]rest请求开始url={}", url);
        String result = null;
        try {
            logger.info("[rest请求]rest请求转换后url={}", url);
            result = defaultHttpTemplate().getForObject(url, String.class, parameter);
        } catch (Exception e) {
            logger.error("[rest请求]rest通讯异常，请检查配置信息。默认返回null", e);
        }finally {
            logger.info("[rest请求]rest请求结束 result={}", result);
            return result;
        }
    }
}
