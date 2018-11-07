package com.yixin.dsc.dto;

import java.io.Serializable;

import com.yixin.dsc.enumpackage.DscAlixLinkEnum;
import com.yixin.kepler.common.enums.CreditfrontResultEnum;

/**
 * 工作流结果通知
 * 
 * Package : com.yixin.dsc.dto
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月3日 下午9:50:15
 *
 */
public class DscFlowResultForAlixDto implements Serializable{
	
	
	private static final long serialVersionUID = 3009867790586624217L;

	/**
	 * 环节
	 * CREDITFRONT--信审 REQUEST_FUNDS--请款
	 */
	private String link;
	
	/**
	 * 订单编号
	 */
	private String applyNo;
	
	/**
	 * 结果code   通过：100000，拒绝：100001 ，驳回：100002
	 */
	private String code;
	
	/**
	 * 结果提示
	 */
	private String message;
	
	/**
	 * 是否需要补充信息
	 */
	private Boolean needSupply;
	
	/**
	 * 补充信息
	 */
	private DscSupplyDto dscSupply;
	
	private RejectDetail rejectDetail;
	
	/**
	 * 业务信息
	 */
	private DscCreditfrontBusinessDto businessInfo;
	
	

	/**
	 * 驳回详细信息
	 */
	static class RejectDetail {
		
		private String rejectCode = "";
		
		private String rejectMessage = "";

		
		public RejectDetail(String rejectCode, String rejectMessage) {
			super();
			this.rejectCode = rejectCode;
			this.rejectMessage = rejectMessage;
		}

		public String getRejectCode() {
			return rejectCode;
		}

		public void setRejectCode(String rejectCode) {
			this.rejectCode = rejectCode;
		}

		public String getRejectMessage() {
			return rejectMessage;
		}

		public void setRejectMessage(String rejectMessage) {
			this.rejectMessage = rejectMessage;
		}
		
	}
	
	static class RejectCode {
		//附件驳回
		public static final String REJECT_ATTACHMENT = "10000201";
		//字段项驳回
		public static final String REJECT_FIELD = "10000202";
		//字段项和附件同步驳回
		public static final String REJECT_ALL = "10000203";
		//银行驳回 - 验四失败
		public static final String REJECT_BANK_UNCHK = "1000020401";
		//银行驳回 - VIN码格式错误
		public static final String REJECT_BANK_VIN = "1000020402";
		//银行驳回 - 开户拒绝-负债收入比不准入
		public static final String REJECT_BANK_MONEY = "1000020403";
	}

	public DscCreditfrontBusinessDto getBusinessInfo() {
		return businessInfo;
	}

	public void setBusinessInfo(DscCreditfrontBusinessDto businessInfo) {
		this.businessInfo = businessInfo;
	}

	public void setRejectDetailMsg(String code, String msg) {
		this.rejectDetail = new RejectDetail(code, msg);
	}
	
	public RejectDetail getRejectDetail() {
		return rejectDetail;
	}

	public void setRejectDetail(RejectDetail rejectDetail) {
		this.rejectDetail = rejectDetail;
	}

	public Boolean getNeedSupply() {
		return needSupply;
	}

	public void setNeedSupply(Boolean needSupply) {
		this.needSupply = needSupply;
	}

	public DscSupplyDto getDscSupply() {
		return dscSupply;
	}

	public void setDscSupply(DscSupplyDto dscSupply) {
		this.dscSupply = dscSupply;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}
	
	public static DscFlowResultForAlixDto createForAliDto(String applyNo,
			Boolean isSuccess,String msg,DscAlixLinkEnum type) {
		
		DscFlowResultForAlixDto alixDto = new DscFlowResultForAlixDto();
		alixDto.setApplyNo(applyNo);
		alixDto.setCode(isSuccess ? DscAlixLinkEnum.SUCCES_CODE.getCode()
				: DscAlixLinkEnum.FAILURE_CODE.getCode());
		alixDto.setLink(type.getCode());
		alixDto.setMessage(msg);
		return alixDto;
	}
	
	/**
	 * 创建信息返回结果
	 * @param applyNo
	 * @param code
	 * @param message
	 * @return 
	 *	       2018年9月8日 下午6:18:37
	 * @author YixinCapital -- wangwenlong
	 */
	public static DscFlowResultForAlixDto createCreditfrontResult(String applyNo,CreditfrontResultEnum resultEnum,CreditfrontResultEnum detailEnum){
		DscFlowResultForAlixDto alixDto = new DscFlowResultForAlixDto();
		alixDto.setApplyNo(applyNo);
		alixDto.setCode(resultEnum.getCode());
		alixDto.setLink(DscAlixLinkEnum.CREDITFRONT.getCode());
		alixDto.setMessage(resultEnum.getMsg());
		if(detailEnum != null){
			alixDto.setRejectDetail(new RejectDetail(detailEnum.getCode(), detailEnum.getMsg()));
		}
		return alixDto;
	}
}