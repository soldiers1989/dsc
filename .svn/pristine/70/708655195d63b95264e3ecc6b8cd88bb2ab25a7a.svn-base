package com.yixin.kepler.dto.webank;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 90032 复审平台审批结果回传(4) 入参
 * Package : com.yixin.kepler.dto.webank
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月9日 下午9:32:31
 *
 */
public class WBLastRequestCancleDTO extends WBCommonReqDTO {

	private static final long serialVersionUID = -3033600470898166534L;
	
	/**
	 * 用户平台id/身份证号码
	 */
	@JsonProperty("REG_USERID")
	private String azjhm;
	
	/**
	 * 申请编号 / 申请书编号 二审回传关联一审异步回调的APP_NO
	 */
	@JsonProperty("APP_NO")
	private String appNo;
	
	/**
	 * 姓名/申请人姓名
	 */
	@JsonProperty("NAME")
	private String akhxm;
	
	/**
	 * 证件提交类型/证件类型
	 */
	@JsonProperty("ID_TYPE")
	private String azjlx;
	
	/**
	 * 证件号码/身份证号码
	 */
	@JsonProperty("ID_NO")
	private String azjhm2;
	
	/**
	 * 平台审批结果 平台审批结果  Y 通过 N 拒绝
	 */
	@JsonProperty("MER_APPLY_RESULT")
	private String merApplyResult = "N";
	
	/**
	 * 平台审批意见
	 */
	@JsonProperty("MER_APPLY_VIEW")
	private String merApplyView = "银行降额，首付比例不符合";
	
	/**
	 * 平台拒绝原因分类(银行最接受传10个字符)
	 */
	@JsonProperty("MER_REFUSE_REASON")
	private String merRefuseReason = "银行降额,首付不符合";
	
	/**
	 * 交易处理码  == 提交类型  4-平台审批结果
	 */
	@JsonProperty("TXN_TYPE")
	private String txnType = "4";

	public String getAzjhm() {
		return azjhm;
	}

	public void setAzjhm(String azjhm) {
		this.azjhm = azjhm;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getAkhxm() {
		return akhxm;
	}

	public void setAkhxm(String akhxm) {
		this.akhxm = akhxm;
	}

	public String getAzjlx() {
		return azjlx;
	}

	public void setAzjlx(String azjlx) {
		this.azjlx = azjlx;
	}

	public String getAzjhm2() {
		return azjhm2;
	}

	public void setAzjhm2(String azjhm2) {
		this.azjhm2 = azjhm2;
	}

	public String getMerApplyResult() {
		return merApplyResult;
	}

	public void setMerApplyResult(String merApplyResult) {
		this.merApplyResult = merApplyResult;
	}

	public String getMerApplyView() {
		return merApplyView;
	}

	public void setMerApplyView(String merApplyView) {
		this.merApplyView = merApplyView;
	}

	public String getMerRefuseReason() {
		return merRefuseReason;
	}

	public void setMerRefuseReason(String merRefuseReason) {
		this.merRefuseReason = merRefuseReason;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}
}
