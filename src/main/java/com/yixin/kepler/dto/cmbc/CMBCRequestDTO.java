package com.yixin.kepler.dto.cmbc;

import com.yixin.common.utils.DateUitls;
import com.yixin.kepler.common.enums.CMBCTransCodeEnum;

import java.util.Date;

/**
 * cmbc银行请求体DTO
 * @author sukang
 * @date 2018-06-11 10:49:00
 */
public class CMBCRequestDTO {
	
	/**
	 * 交易代码
	 */
	private String transCode;
	
	/**
	 * 交易类型
	 */
	private String transType;
	
	/**
	 * 版本号
	 */
	private String version = "02";
	
	
	private String productCode;
	
	/**
	 * 
	 */
	private String reqSeq;
	
	
	/**
	 * 请求时间  YYYYmmddHHmmss
	 */
	private String reqTime;
	
	private String systemCode;
	
	/**
	 * YYYYmmdd
	 */
	private String transDate;
	
	/**
	 * HHmmss
	 */
	private String transTime;
	
	
	
	
	private String merchantNum;
	
	private String sgntr;
	
	private String charset = "utf-8";
	
	private String remark = "";
	private String reserve = "";
	private String reserve2 = "";
	
	
	/**
	 * 请求值对象json形式加密的密文
	 */
	private Object body;
	
	
	
	
	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}

	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	public void setVersion(String version) {
		this.version = version;
	}

	public void setCharset(String charset) {
		this.charset = charset;
	}

	public String getCharset() {
		return charset;
	}
	
	public String getTransCode() {
		return transCode;
	}


	public void setTransCode(String transCode) {
		this.transCode = transCode;
	}


	public String getTransType() {
		return transType;
	}


	public void setTransType(String transType) {
		this.transType = transType;
	}


	public String getVersion() {
		return version;
	}


	public String getReqSeq() {
		return reqSeq;
	}


	public void setReqSeq(String reqSeq) {
		this.reqSeq = reqSeq;
	}


	public String getReqTime() {
		return reqTime;
	}


	public void setReqTime(String reqTime) {
		this.reqTime = reqTime;
	}


	public String getSystemCode() {
		return systemCode;
	}


	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}


	public String getTransDate() {
		return transDate;
	}


	public void setTransDate(String transDate) {
		this.transDate = transDate;
	}


	public String getTransTime() {
		return transTime;
	}


	public void setTransTime(String transTime) {
		this.transTime = transTime;
	}


	public String getMerchantNum() {
		return merchantNum;
	}


	public void setMerchantNum(String merchantNum) {
		this.merchantNum = merchantNum;
	}


	public String getSgntr() {
		return sgntr;
	}


	public void setSgntr(String sgntr) {
		this.sgntr = sgntr;
	}


	public Object getBody() {
		return body;
	}


	public void setBody(Object body) {
		this.body = body;
	}
	
	
	
	/**
	 * 
	 * @param transCode 交易编码(民生银行不同的交易对应不同的交易编码)
	 * @return
	 */
	
	public static CMBCRequestDTO getCMBCInstance(CMBCTransCodeEnum transCode){
		CMBCRequestDTO cmbcRequestDTO = new CMBCRequestDTO();
		cmbcRequestDTO.setTransCode(transCode.getTransCode());
		cmbcRequestDTO.setReqTime(DateUitls.dateToStr(new Date(),"yyyyMMddHHmmssSSS"));
		cmbcRequestDTO.setTransDate(DateUitls.dateToStr(new Date(),"yyyyMMdd"));
		cmbcRequestDTO.setTransTime(DateUitls.dateToStr(new Date(),"HHmmssSSS"));
		cmbcRequestDTO.setProductCode("10030011");
		return cmbcRequestDTO;
	}


	


}
