package com.yixin.dsc.v1.datapackage.dto.notice;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.yixin.dsc.util.DateUtil;

/**
 * venus通过队列异步将处理结果通知到提报端
 * Package : com.yixin.dsc.dto.notice
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年9月26日 上午10:36:46
 *
 */
public class DscAsyncMessageDto<T> implements Serializable {

	private static final long serialVersionUID = -3323626266509787884L;

	/**
	 * 订单编号
	 */
	private String applyNo;
	
	@JsonFormat(pattern = DateUtil.DEFAULT_TIMESTAMP_FORMAT_D)
	private Date timeStamp = new Date();
	
	/**
	 * 消息类型 参考 NoticeBusiTypeEnum
	 */
	private String messageType;
	
	/**
	 * 通知业务详细信息
	 */
	private T messageBody;
	
	/**
	 * 阶段，和消息类型同值
	 */
	@SuppressWarnings("unused")
	private String link;
	
	public DscAsyncMessageDto() {
		super();
	}

	public DscAsyncMessageDto(String applyNo, String messageType, T messageBody) {
		super();
		this.applyNo = applyNo;
		this.messageType = messageType;
		this.messageBody = messageBody;
		this.link = messageType; //阶段，和消息类型同值
	}
	
	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public Date getTimeStamp() {
		return timeStamp;
	}

	public void setTimeStamp(Date timeStamp) {
		this.timeStamp = timeStamp;
	}

	public String getMessageType() {
		return messageType;
	}

	public void setMessageType(String messageType) {
		this.messageType = messageType;
	}

	public T getMessageBody() {
		return messageBody;
	}

	public void setMessageBody(T messageBody) {
		this.messageBody = messageBody;
	}

	public String getLink() {
		return this.messageType;
	}

	public void setLink(String link) {
		this.link = link;
	}
}
