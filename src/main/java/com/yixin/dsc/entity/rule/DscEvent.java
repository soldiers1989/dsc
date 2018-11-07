package com.yixin.dsc.entity.rule;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yixin.common.system.domain.BZBaseEntiy;

/**
 * 事件表
 * 
 * Package : com.yixin.dsc.entity.rule
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月4日 下午7:31:35
 *
 */
@Entity
@Table(name = "dsc_event")
public class DscEvent extends BZBaseEntiy {

	private static final long serialVersionUID = -5348012179941607423L;

	
	/**
	 * 事件code
	 */
	@Column(name = "event_code", columnDefinition = "varchar(64) comment '事件code'")
	private String eventCode;
	
	/**
	 * 事件描述
	 */
	@Column(name = "event_detail", columnDefinition = "varchar(255) comment '事件描述'")
	private String eventDetail;
	
	/**
	 * 校验
	 */
	@Column(name = "event_check", columnDefinition = "varchar(255) comment '校验'")
	private String eventCheck;

	
	public String getEventCode() {
		return eventCode;
	}

	public void setEventCode(String eventCode) {
		this.eventCode = eventCode;
	}

	public String getEventDetail() {
		return eventDetail;
	}

	public void setEventDetail(String eventDetail) {
		this.eventDetail = eventDetail;
	}

	public String getEventCheck() {
		return eventCheck;
	}

	public void setEventCheck(String eventCheck) {
		this.eventCheck = eventCheck;
	}

}
