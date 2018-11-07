package com.yixin.dsc.dto;

import java.io.Serializable;

import org.apache.commons.collections.CollectionUtils;

/**
 * 分流决策动作
 * Package : com.yixin.nssp.dto.dsc
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月4日 上午10:07:41
 *
 */
public class DscActionResultDTO implements Serializable{

	private static final long serialVersionUID = 8960518749157856773L;

	/**
	 * 动作返回结果码值，
	 * 成功：100000，失败：100001，转自营：100002 ,驳回：100005
	 */
	private String resultCode;
	
	/**
	 * 动作返回提示
	 */
	private String resultMessage;
	
	/**
	 * 补充信息
	 */
	private DscSupplyDto dscSupply;
	
	/**
	 * 是否需要补充信息
	 */
	private Boolean needSupply;
	
	static class RejectCode {
		//附件驳回
		public static final String REJECT_ATTACHMENT = "10000501";
		//字段项驳回
		public static final String REJECT_FIELD = "10000502";
		//字段项和附件同步驳回
		public static final String REJECT_ALL = "10000503";
	}
	
	/**
	 *  实例化驳回码值
	 * @author YixinCapital -- wangwenlong
	 *	       2018年9月8日 下午4:37:24
	 */
	public DscActionResultDTO initRejectCode(DscSupplyDto dscSupply){
		if(dscSupply != null){
			this.dscSupply = dscSupply;
		}
		this.needSupply = false;
		if(this.dscSupply != null){
			if(CollectionUtils.isNotEmpty(this.dscSupply.getAttList()) 
					&& CollectionUtils.isNotEmpty(this.dscSupply.getFieldList()) ){
				this.resultCode = RejectCode.REJECT_ALL; //字段项和附件同步驳回码值
				this.needSupply = true;
				this.resultMessage = "字段项和附件驳回";
			} else if(CollectionUtils.isNotEmpty(this.dscSupply.getAttList()) ){
				this.resultCode = RejectCode.REJECT_ATTACHMENT; //附件驳回码值
				this.needSupply = true;
				this.resultMessage = "附件驳回";
			} else if(CollectionUtils.isNotEmpty(this.dscSupply.getFieldList()) ){
				this.resultCode = RejectCode.REJECT_FIELD; //字段项驳回
				this.needSupply = true;
				this.resultMessage = "字段项驳回";
			}
		}
		return this;
	}
	
	public DscSupplyDto getDscSupply() {
		return dscSupply;
	}

	public void setDscSupply(DscSupplyDto dscSupply) {
		this.dscSupply = dscSupply;
	}

	public Boolean getNeedSupply() {
		return needSupply;
	}

	public void setNeedSupply(Boolean needSupply) {
		this.needSupply = needSupply;
	}

	public String getResultCode() {
		return resultCode;
	}

	public void setResultCode(String resultCode) {
		this.resultCode = resultCode;
	}

	public String getResultMessage() {
		return resultMessage;
	}

	public void setResultMessage(String resultMessage) {
		this.resultMessage = resultMessage;
	}
	
}
