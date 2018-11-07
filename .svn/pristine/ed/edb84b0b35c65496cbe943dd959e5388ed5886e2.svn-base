package com.yixin.kepler.dto.webank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 一审响应DTO
 * Package : com.yixin.kepler.dto.webank
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月9日 下午4:35:28
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WBFirstResponseDTO extends WBCommonRespDTO{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7660385450702835804L;

	/**
	 * 业务流水号
	 */
	@JsonProperty("BIZ_SEQ_NO")
	private String bizSeqNo;
	
	public String getBizSeqNo() {
		return bizSeqNo;
	}

	public void setBizSeqNo(String bizSeqNo) {
		this.bizSeqNo = bizSeqNo;
	}
}
