package com.yixin.kepler.v1.datapackage.dto.yntrust;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

/**
 * 云南信托公共请求DTO
 * Package : com.yixin.kepler.v1.datapackage.dto.yntrust
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年9月18日 上午11:37:42
 *
 */
public class YTCommonRequestDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = -5242743777834565154L;

	/**
	 * 非银行字段，通过该字段，传给OSB区分云南信托接口
	 */
	private String url = "";
	
	/**
	 * 标示请求唯一性的值,不能为空和重复的值，可以防止重复请求和追踪和排查问题。
	 */
	@JsonProperty("RequestId")
	private String requestId = "";


	public String getRequestId() {
		return requestId;
	}

	public void setRequestId(String requestId) {
		this.requestId = requestId;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}
}
