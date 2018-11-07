package com.yixin.kepler.v1.datapackage.dto.yntrust;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yixin.kepler.core.constant.CommonConstant;

import java.util.Objects;

@JsonIgnoreProperties(ignoreUnknown = true)
public class YTCommonStatusDTO {

	/**
	 * 是否成功，此次操作是否成功完成，如果为false就代表操作失败
	 */
	@JsonProperty("IsSuccess")
	private Boolean isSuccess;
	
	/**
	 * 响应信息，主要包含错误的详细信息，比如”身份证号不能为空”等
	 */
	@JsonProperty("ResponseMessage")
	private String responseMessage;
	
	/**
	 * 响应码
	 */
	@JsonProperty("ResponseCode")
	private String responseCode;
	
	/**
	 * 警告信息
	 */
	@JsonProperty("WarningMessage")
	private String warningMessage;

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getResponseMessage() {
		return responseMessage;
	}

	public void setResponseMessage(String responseMessage) {
		this.responseMessage = responseMessage;
	}

	public String getResponseCode() {
		return responseCode;
	}

	public void setResponseCode(String responseCode) {
		this.responseCode = responseCode;
	}

	public String getWarningMessage() {
		return warningMessage;
	}

	public void setWarningMessage(String warningMessage) {
		this.warningMessage = warningMessage;
	}

	public boolean success(){
		return  this.isSuccess &&
				Objects.equals(CommonConstant.YNTrustErrorCode.SUCCESS,this.getResponseCode());
	}
}
