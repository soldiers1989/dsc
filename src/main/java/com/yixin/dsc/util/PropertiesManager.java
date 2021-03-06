package com.yixin.dsc.util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.stereotype.Component;

/**
 * 配置文件管理类
 * Package : com.yixin.dsc.util
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月6日 下午7:10:18
 *
 */
@Component("propertiesManager")
@PropertySource("classpath:business.properties")
public class PropertiesManager {



	/**
	 *--------------- MQ Queue start
	 */
	@Value("${rabbit.mq.dscKeplerQueue}")
	private String dscKeplerQueue;

	/**
	 * 反推结算数据的接口地址 
	 */
	@Value("${push.settle.url}")
	private String pushSettleUrl;
	
	@Value("${osb.file.routing.key}")
	private String osbFileRoutingKey;
	
	@Value("${osb.environment}")
	private String osbEnvironment;

	@Value("${settle-web.environment}")
	private String settleWebEnvironment;

	@Value("${osb.icbc.url}")
	private String osbIcbcUrl;
	
	@Value("${idfactory.url}")
    private String idfactoryUrl;


	@Value("${spring.fileserver.urlupload}")
	private String fileServerUrlUpload;

	@Value("${spring.filestore.url}")
	private String fileStoreUrl;

	public String getFilestoreUrl() {
		return fileStoreUrl;
	}

	public void setFilestoreUrl(String fileStoreUrl) {
		this.fileStoreUrl = fileStoreUrl;
	}

	public String getFileServerUrlUpload() {
		return fileServerUrlUpload;
	}

	public void setFileServerUrlUpload(String fileServerUrlUpload) {
		this.fileServerUrlUpload = fileServerUrlUpload;
	}

	public String getIdfactoryUrl() {
		return idfactoryUrl;
	}

	public void setIdfactoryUrl(String idfactoryUrl) {
		this.idfactoryUrl = idfactoryUrl;
	}

	public String getSettleWebEnvironment() {
		return settleWebEnvironment;
	}

	public void setSettleWebEnvironment(String settleWebEnvironment) {
		this.settleWebEnvironment = settleWebEnvironment;
	}

	public String getOsbEnvironment() {
		return osbEnvironment;
	}

	public void setOsbEnvironment(String osbEnvironment) {
		this.osbEnvironment = osbEnvironment;
	}

	public String getOsbFileRoutingKey() {
		return osbFileRoutingKey;
	}

	public void setOsbFileRoutingKey(String osbFileRoutingKey) {
		this.osbFileRoutingKey = osbFileRoutingKey;
	}

	public String getPushSettleUrl() {
		return pushSettleUrl;
	}

	public void setPushSettleUrl(String pushSettleUrl) {
		this.pushSettleUrl = pushSettleUrl;
	}

	public String getDscKeplerQueue() {
		return dscKeplerQueue;
	}

	public void setDscKeplerQueue(String dscKeplerQueue) {
		this.dscKeplerQueue = dscKeplerQueue;
	}
	
	public String getOsbIcbcUrl() {
		return osbIcbcUrl;
	}

	public void setOsbIcbcUrl(String osbIcbcUrl) {
		this.osbIcbcUrl = osbIcbcUrl;
	}
}
