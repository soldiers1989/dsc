package com.yixin.dsc.dto.query;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yixin.common.utils.serializer.JsonDateDeserializer;
import com.yixin.common.utils.serializer.JsonDateTimeSerializer;

/**
 * 交互信息
 * Package : com.yixin.dsc.dto.query
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月31日 下午1:33:32
 *
 */
public class DscInteractiveDto implements Serializable{

	private static final long serialVersionUID = 7494599677464068333L;

	/** 创建时间 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date createTime;
	
	/** 阶段  */
	private String phase;
	
	/** apiCode  */
	private String apiCode;
	
	/** approvalCode  */
	private String approvalCode;
	
	/** approvalDesc  */
	private String approvalDesc;
	
	/** repBody  */
	private String repBody;

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getApiCode() {
		return apiCode;
	}

	public void setApiCode(String apiCode) {
		this.apiCode = apiCode;
	}

	public String getApprovalCode() {
		return approvalCode;
	}

	public void setApprovalCode(String approvalCode) {
		this.approvalCode = approvalCode;
	}

	public String getApprovalDesc() {
		return approvalDesc;
	}

	public void setApprovalDesc(String approvalDesc) {
		this.approvalDesc = approvalDesc;
	}

	public String getRepBody() {
		return repBody;
	}

	public void setRepBody(String repBody) {
		this.repBody = repBody;
	}
}
