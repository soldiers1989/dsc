package com.yixin.kepler.dto.cmbc;


/**
 * 民生银行请求结果查询接口Body DTO
 * @author sukang
 * @date 2018-06-11 15:44:03
 */
public class CMBCResultSelectDTO {
	
	
	/**
	 * 通用请求参数域  参数形式为json的字符串形式
	 * 
	 * "{\"tacNo\":\"tacNo\",\"idNo\":\idNo",\"idType\":\"idType}"
	 */
	private String receiveMg; 	 
	
	/**
	 * 流水号  true
	 */
	private String channelJnlNo;

	public String getReceiveMg() {
		return receiveMg;
	}

	public void setReceiveMg(String receiveMg) {
		this.receiveMg = receiveMg;
	}

	public String getChannelJnlNo() {
		return channelJnlNo;
	}

	public void setChannelJnlNo(String channelJnlNo) {
		this.channelJnlNo = channelJnlNo;
	}
	
	
	
	
	

}
