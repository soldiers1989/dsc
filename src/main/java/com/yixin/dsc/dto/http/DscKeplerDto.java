package com.yixin.dsc.dto.http;

import java.io.Serializable;

/**
 * 解析资管专用DTO
 * Package : com.yixin.dsc.dto.http
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月6日 下午7:43:12
 *
 */
public class DscKeplerDto implements Serializable {

	private static final long serialVersionUID = -5921018861111623059L;

	/**
	 * 状态：1 OK，2：FALSE
	 */
	private String status;
	
	/**
	 * 查询的状态类别 1: 授权书签署状态，2： 信审状态，3：合同签署状态，4：请款状态
	 */
	private String type;
	
	/**
	 * 响应码值
	 */
	private String code;
	
	/**
	 * 响应消息
	 */
	private String message;
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	@Override
	public String toString() {
		return "DscKeplerDto [status=" + status + ", type=" + type + ", code=" + code + "]";
	}
}

