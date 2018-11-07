package com.yixin.dsc.dto;

import java.io.Serializable;
import java.util.Date;

/**
 * 用户行为DTO
 * Package : com.yixin.dsc.dto
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月4日 下午5:44:53
 *
 */
public class DscUserActionDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -22633305246590156L;
	
	/**
	 * 银行预留手机号
	 */
	private String bankReservedPhone;
	
	/**
	 * 扣款银行
	 */
	private String bankCode;

	/**
	 * 扣款银行名称
	 */
	private String bankName;
	
	/**
	 * 账户号码/银行卡号
	 */
	private String accountNum;
	
	/**
	 * 点击“申请额度”时间
	 */
	private Date clickApplyLimitTime;
	/**
	 * 点击“获取验证码”时间
	 */
	private Date clickSmsTime;
	/**
	 * 系统发送验证码时间
	 */
	private Date sysSendSmsTime;
	/**
	 * 接收验证码手机号码
	 */
	private String checkSmsMobile;
	/**
	 * 验短通过时间
	 */
	private Date checkSmsSucTime;
	/**
	 * 验密通过时间
	 */
	private Date checkPwdSucTime;
	/**
	 * 提交申请时间
	 */
	private Date applyTime;
	/**
	 * 点击"纳税居民申明"时间
	 */
	private Date clickTaxableTime;
	/**
	 * OTP验证发送次数
	 */
	private Integer otpSendTime;
	/**
	 * OTP验证失败次数
	 */
	private Integer otpErrTime;

	/**
	 * 签约日
	 */
	private Date signDate;
	/**
	 * 签署渠道（新增）
	 */
	private String signChannel;	
	/**
	 * 确认借据时间
	 */
	private Date confirmLoanTime;
	
	public String getBankReservedPhone() {
		return bankReservedPhone;
	}
	public void setBankReservedPhone(String bankReservedPhone) {
		this.bankReservedPhone = bankReservedPhone;
	}
	public String getBankCode() {
		return bankCode;
	}
	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	public Date getClickApplyLimitTime() {
		return clickApplyLimitTime;
	}
	public void setClickApplyLimitTime(Date clickApplyLimitTime) {
		this.clickApplyLimitTime = clickApplyLimitTime;
	}
	public Date getClickSmsTime() {
		return clickSmsTime;
	}
	public void setClickSmsTime(Date clickSmsTime) {
		this.clickSmsTime = clickSmsTime;
	}
	public Date getSysSendSmsTime() {
		return sysSendSmsTime;
	}
	public void setSysSendSmsTime(Date sysSendSmsTime) {
		this.sysSendSmsTime = sysSendSmsTime;
	}
	public String getCheckSmsMobile() {
		return checkSmsMobile;
	}
	public void setCheckSmsMobile(String checkSmsMobile) {
		this.checkSmsMobile = checkSmsMobile;
	}
	public Date getCheckSmsSucTime() {
		return checkSmsSucTime;
	}
	public void setCheckSmsSucTime(Date checkSmsSucTime) {
		this.checkSmsSucTime = checkSmsSucTime;
	}
	public Date getCheckPwdSucTime() {
		return checkPwdSucTime;
	}
	public void setCheckPwdSucTime(Date checkPwdSucTime) {
		this.checkPwdSucTime = checkPwdSucTime;
	}
	public Date getApplyTime() {
		return applyTime;
	}
	public void setApplyTime(Date applyTime) {
		this.applyTime = applyTime;
	}
	public Date getClickTaxableTime() {
		return clickTaxableTime;
	}
	public void setClickTaxableTime(Date clickTaxableTime) {
		this.clickTaxableTime = clickTaxableTime;
	}
	public Integer getOtpSendTime() {
		return otpSendTime;
	}
	public void setOtpSendTime(Integer otpSendTime) {
		this.otpSendTime = otpSendTime;
	}
	public Integer getOtpErrTime() {
		return otpErrTime;
	}
	public void setOtpErrTime(Integer otpErrTime) {
		this.otpErrTime = otpErrTime;
	}
	public Date getSignDate() {
		return signDate;
	}
	public void setSignDate(Date signDate) {
		this.signDate = signDate;
	}
	public String getSignChannel() {
		return signChannel;
	}
	public void setSignChannel(String signChannel) {
		this.signChannel = signChannel;
	}
	public Date getConfirmLoanTime() {
		return confirmLoanTime;
	}
	public void setConfirmLoanTime(Date confirmLoanTime) {
		this.confirmLoanTime = confirmLoanTime;
	}
}
