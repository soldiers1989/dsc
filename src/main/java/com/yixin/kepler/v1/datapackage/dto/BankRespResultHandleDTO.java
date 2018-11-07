package com.yixin.kepler.v1.datapackage.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 处理银行返回结果DTO
 * Package : com.yixin.kepler.v1.datapackage.dto
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年9月17日 下午5:08:41
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class BankRespResultHandleDTO implements Serializable{


	/**
	 * 
	 * @author YixinCapital -- chen.lin
	 *		   2018年9月17日 下午5:13:13
	 *
	 */
	private static final long serialVersionUID = -1316628299491549956L;

	/**
     * 发起银行请求id
     */
	private String bankReqId;
	
	/**
     * 申请编号
     */
    private String applyNo;
	
	/**
	 * 流水号
	 */
    private String tranNo;
    
	/**
	 * 资方code
	 */
    private String assetNo;
    
    /**
     * 资管请求报文
     */
    private String VenusRespJosn;
    
    /**
     * 银行返回的结果报文
     */
    private String bankResultJosn;
    
    /**
     * 所属阶段
     */
    private String phase;

    
	public String getAssetNo() {
		return assetNo;
	}

	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}

	public String getBankResultJosn() {
		return bankResultJosn;
	}

	public void setBankResultJosn(String bankResultJosn) {
		this.bankResultJosn = bankResultJosn;
	}

	public String getPhase() {
		return phase;
	}

	public void setPhase(String phase) {
		this.phase = phase;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getTranNo() {
		return tranNo;
	}

	public void setTranNo(String tranNo) {
		this.tranNo = tranNo;
	}

	public String getBankReqId() {
		return bankReqId;
	}

	public void setBankReqId(String bankReqId) {
		this.bankReqId = bankReqId;
	}

	public String getVenusRespJosn() {
		return VenusRespJosn;
	}

	public void setVenusRespJosn(String venusRespJosn) {
		VenusRespJosn = venusRespJosn;
	}

}
