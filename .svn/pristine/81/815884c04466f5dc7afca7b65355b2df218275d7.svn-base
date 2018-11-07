package com.yixin.kepler.dto.webank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 微众请求报文公共头信息
 * Package : com.yixin.kepler.dto.webank
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月6日 下午1:38:06
 *
 */
public class WBCommonReqDTO implements Serializable{

	private static final long serialVersionUID = 9041652504429904654L;
	/**
	 * 交易服务码, 通过这个字段标识调用微众哪个接口，每个接口对应一个编码
	 */
	@JsonProperty("TXN_ID")  
	private String txnId;
	/**
	 * 平台ID 微众分配的固定值
	 * 	850130008290001（上海易鑫融资租赁有限公司）
	 *  850130008110001（天津恒通嘉合融资租赁有限公司）
	 */
	@JsonProperty("MERCHANT_ID")  
	private String merchantId;
	/**
	 * 操作员号，请求方发起交易操作员标识号（如：柜员号，客服操作员号）
	 */
	@JsonProperty("OP_ID")  
	private String opId;
	/**
	 * 渠道 ，线索来源=app传04平台APP终端，否则传01PC_WEB
	 */
	@JsonProperty("CHANNEL")  
	private String channel = "";
	/**
	 * 请求时间  yyyyMMddHHmmss
	 */
	@JsonFormat(pattern = "yyyyMMddHHmmss",timezone="GMT+8")
	@JsonProperty("REQ_TIME")
	private Date reqTime;
	
	
	public String getTxnId() {
		return txnId;
	}
	public void setTxnId(String txnId) {
		this.txnId = txnId;
	}
	public String getMerchantId() {
		return merchantId;
	}
	public void setMerchantId(String merchantId) {
		this.merchantId = merchantId;
	}
	public String getOpId() {
		return opId;
	}
	public void setOpId(String opId) {
		this.opId = opId;
	}
	public String getChannel() {
		return channel;
	}
	public void setChannel(String channel) {
		this.channel = channel;
	}
	public Date getReqTime() {
		return reqTime;
	}
	public void setReqTime(Date reqTime) {
		this.reqTime = reqTime;
	}
	
	@Override
	public String toString() {
		return "WBCommonReqDTO [txnId=" + txnId + ", merchantId=" + merchantId + ", opId=" + opId + ", channel=" + channel
				+ ", reqTime=" + reqTime + "]";
	}
	
	/*public static void main(String[] args) {
		
		String j = "{\"reqTime\":\"2018071900107483\",\"channel\":\"\",\"txn_ID\":null,\"merchant_ID\":\"850130008110001\",\"OP_ID\":\"1111111111111\",\"CHANNEL\":\"\",\"COMPANY_PHONE\":null}";
		WBCommonReqDTO co = new WBCommonReqDTO();
		co.setOpId("1111111111111");
		co.setReqTime(new Date());
		
		//System.out.println(JsonObjectUtils.objectToJson(co));
		System.out.println(JsonObjectUtils.jsonToObject(j, co));
		System.out.println(co.getOpId());

		System.out.println(co.getReqTime());
	}*/
}
