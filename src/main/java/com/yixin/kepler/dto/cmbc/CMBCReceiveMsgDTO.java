package com.yixin.kepler.dto.cmbc;


/**
 * 民生银行 通用请求参数域
 * @author sukang
 * @date 2018-06-11 15:20:14
 */
public class CMBCReceiveMsgDTO {
	
	/**
	 * 账务机构号  否   0100
	 */
	private String acctBch = "0100";
	
	/**
	 * 营业机构号  否  0100
	 */
	private String bussBch = "0100";	
	
	
	/**
	 * 交易柜员  否   RCS6360001
	 */
	private String txUser = "RCS6360001"; 	
	
	/**
	 * 交易日期   yyyyMMdd
	 */
	private String txDt; 		 
	
	/**
	 * 交易时间  HHmmss  是
	 */
	private String txTm;		
	
	/**
	 * 渠道流水号   是
	 */
	private String chnlTxNo;	  
	
	/**
	 * 	系统编码  否    636
	 */
	private String sysCode = "636"; 	
	
	/**
	 * 渠道   否
	 */
	private String chnlCode;
	
	/** 
	 * 交易行为   	否
	 */
	private String txCode;	 	
	
	/**
	 * 合作商产品代码     否   HDDK
	 */
	private String cooprProdCd = "636YX01"; 	
	
	/**
	 * 合作商业务流水号   贷款发放和开户时必输
	 */
	private String cooprNo;

	public String getAcctBch() {
		return acctBch;
	}

	public void setAcctBch(String acctBch) {
		this.acctBch = acctBch;
	}

	public String getBussBch() {
		return bussBch;
	}

	public void setBussBch(String bussBch) {
		this.bussBch = bussBch;
	}

	public String getTxUser() {
		return txUser;
	}

	public void setTxUser(String txUser) {
		this.txUser = txUser;
	}

	public String getTxDt() {
		return txDt;
	}

	public void setTxDt(String txDt) {
		this.txDt = txDt;
	}

	public String getTxTm() {
		return txTm;
	}

	public void setTxTm(String txTm) {
		this.txTm = txTm;
	}

	public String getChnlTxNo() {
		return chnlTxNo;
	}

	public void setChnlTxNo(String chnlTxNo) {
		this.chnlTxNo = chnlTxNo;
	}

	public String getSysCode() {
		return sysCode;
	}

	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}

	public String getChnlCode() {
		return chnlCode;
	}

	public void setChnlCode(String chnlCode) {
		this.chnlCode = chnlCode;
	}

	public String getTxCode() {
		return txCode;
	}

	public void setTxCode(String txCode) {
		this.txCode = txCode;
	}

	public String getCooprProdCd() {
		return cooprProdCd;
	}

	public void setCooprProdCd(String cooprProdCd) {
		this.cooprProdCd = cooprProdCd;
	}

	public String getCooprNo() {
		return cooprNo;
	}

	public void setCooprNo(String cooprNo) {
		this.cooprNo = cooprNo;
	}
	
	
	
}
