package com.yixin.kepler.common;

import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.dsc.common.exception.BzException;
import com.yixin.dsc.v1.service.common.CommonService;
import com.yixin.kepler.v1.utils.JacksonUtils;
import com.yixin.web.anocation.MethodMonitor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;
import org.springframework.http.*;
import org.springframework.http.client.SimpleClientHttpRequestFactory;
import org.springframework.util.Assert;
import org.springframework.web.client.RestTemplate;

import java.io.IOException;
import java.io.InputStream;

/**
 * Package : com.yixin.system.utils Rest通讯
 *
 * @author YixinCapital -- wangdianxiang 2016年12月12日 下午1:43:26
 */
public class RestTemplateUtil {

    private static final Logger logger = LoggerFactory.getLogger(RestTemplateUtil.class);

    private static RestTemplate template;
    private static HttpHeaders headers;
    private static String SUFFIX = "CommonService";

    static {
        SimpleClientHttpRequestFactory requestFactory = new SimpleClientHttpRequestFactory();
        requestFactory.setConnectTimeout(10000);
        requestFactory.setReadTimeout(10000);
        template = new RestTemplate(requestFactory);
        headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.add("Accept", MediaType.APPLICATION_JSON_UTF8_VALUE);
    }

/*	public String sendRequest(String url, Object parameter) {
        logger.info("===sendRequest开始===" + url);
		String resultStr = null;
		try {
			if (null != parameter) {// 如果入参不为空
				logger.info("===接口===");
				String jsonStr = JacksonUtil.fromObjectToJson(parameter);
				logger.info("===jsonStr===" + jsonStr);
				HttpEntity<String> formEntity = new HttpEntity<String>(jsonStr, headers);
				resultStr = this.template.postForObject(url, formEntity, String.class);
			}
		} catch (Exception e) {
			logger.error("<<<<<Rest通讯异常>>>>>", e);
		}
		logger.info("===sendRequest结束===" + resultStr);
		return resultStr;
	}*/

    /**
     * 请求rest
     *
     * @param url
     * @param parameter
     * @return
     * @auther zhangkai
     */
    @MethodMonitor(keyParam = {"url::0"})
    public static String sendRequestV2(String url, Object parameter) {

        String baffleJsonStr = BaffleUtil.baffleCheck(url, parameter);
        if (StringUtils.isNotBlank(baffleJsonStr)) {
            logger.info("==挡板数据是===" + baffleJsonStr);
            return baffleJsonStr;
        }

        String jsonStr = null;
        if ((jsonStr = isJson(parameter)) == null) jsonStr = parameter.toString();

//		logger.info("\n发送rest请求 url:{} param:{}", url, jsonStr);
        HttpEntity<String> formEntity = new HttpEntity<>(jsonStr, headers);
        String resultStr = template.postForObject(url, formEntity, String.class);
//		logger.info("\n发送rest请求响应" + resultStr);
        return resultStr;
    }


    /**
     * 请求rest ，包含挡板
     *
     * @param url
     * @param parameter
     * @param financialCode
     * @return
     * @author YixinCapital -- wangwenlong
     * 2018年7月12日 下午11:04:06
     */
    @MethodMonitor(keyParam = {"url::0"})
    public static String sendRequest(String url, Object parameter, String financialCode) {
        if (StringUtils.isNotBlank(financialCode)) {
            CommonService bean = SpringContextUtil.getBean(financialCode.concat(SUFFIX), CommonService.class);
            String baffleJsonStr = bean.baffleCheckCommon(parameter, financialCode);
            if (StringUtils.isNotBlank(baffleJsonStr)) {
                logger.info("资方code:{}挡板数据是:{}", financialCode, baffleJsonStr);
                return baffleJsonStr;
            }
        }
        String paramJson = "";
        if (parameter instanceof String) {
            paramJson = (String) parameter;
        } else {
            paramJson = JsonObjectUtils.objectToJson(parameter);
        }
        HttpEntity<String> formEntity = new HttpEntity<>(paramJson, headers);
        String resultStr = template.postForObject(url, formEntity, String.class);
        return resultStr;
    }


    /**
     * 请求rest ，不包含挡板
     *
     * @param url
     * @param parameter
     * @return
     */
    public static String sendRequestNoBaffle(String url, Object parameter) {

        HttpEntity<String> formEntity = new HttpEntity<>(JsonObjectUtils.objectToJson(parameter), headers);
        String resultStr = template.postForObject(url, formEntity, String.class);
        return resultStr;
    }

    private static String isJson(Object param) {
        try {
            return net.sf.json.JSONObject.fromObject(param).toString();
        } catch (Exception e) {
        }
        return null;
    }

    /**
     * 解析参数
     *
     * @param url
     * @param parameter
     * @return
     */
    public static String post(String url, Object parameter) {
        Assert.notNull(url, " url is empty");
        Assert.notNull(parameter, "parameter is empty");
        logger.info("[rest请求]rest请求开始url={}，param={}", url, JacksonUtils.fromObjectToJson(parameter));
        String result = null;
        try {
            logger.info("[rest请求]rest请求转换后url={}", url);
            HttpEntity<String> formEntity = new HttpEntity<>(JsonObjectUtils.objectToJson(parameter), headers);
            result = template.postForObject(url, formEntity, String.class);
        } catch (Exception e) {
            logger.error("[rest请求]rest通讯异常，请检查配置信息。默认返回null", e);
        } finally {
            logger.info("[rest请求]rest请求结束 result={}", result);
            return result;
        }
    }


    /**
     * @param url           请求路径
     * @param parameter     数据
     * @param financialCode 资方
     * @autor sukang
     */

    public static String sendRequestByFile(String url, Object parameter,
                                           String financialCode) {
        if (StringUtils.isNotBlank(financialCode)) {
            CommonService bean = SpringContextUtil.getBean(financialCode.concat(SUFFIX), CommonService.class);
            String baffleJsonStr = bean.baffleCheckCommon(parameter, financialCode);
            if (StringUtils.isNotBlank(baffleJsonStr)) {
                logger.info("资方code:{}挡板数据是:{}", financialCode, baffleJsonStr);
                return baffleJsonStr;
            }
        }
        String paramJson = "";
        if (parameter instanceof String) {
            paramJson = (String) parameter;
        } else {
            paramJson = JsonObjectUtils.objectToJson(parameter);
        }
        HttpEntity<String> formEntity = new HttpEntity<>(paramJson, headers);

        RestTemplate fileRestTemplate = SpringContextUtil.getBean("fileRestTemplate", RestTemplate.class);
        return fileRestTemplate.postForObject(url, formEntity, String.class);
    }


    /**
     * 下载指定url的文件
     *
     * @param fileUrl
     * @return
     */
    public static InputStream downloadFile(String fileUrl) throws BzException {
        logger.info("下载指定url的文件 fileUrl：{}", fileUrl);

        RestTemplate template = new RestTemplate();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.add("Accept", MediaType.ALL_VALUE);
        ResponseEntity<Resource> responseEntity = template.exchange(fileUrl, HttpMethod.GET, null, Resource.class);

        try {
            InputStream responseInputStream = responseEntity.getBody().getInputStream();
            return responseInputStream;
        } catch (IOException e) {
            logger.error("附件下载失败， fileUrl:{}", fileUrl, e);
            throw new BzException("附件下载失败", e);
        }
    }
}
