package com.yixin.kepler.dto.webank;

import com.fasterxml.jackson.annotation.JsonProperty;


/**
 * 微众银行请款结果查询DTO
 * Package : com.yixin.kepler.dto.webank
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月30日 上午10:53:19
 *
 */
public class WBLoanResultReqDTO extends WBCommonReqDTO {

	private static final long serialVersionUID = 8450401239939611491L;

	//-------订单业务主键 开始
	/**
	 * 平台订单号
	 */
	@JsonProperty("MER_ORDER_NO")
	private String merOrderNo;

	/**
	 * 微众订单号
	 */
	@JsonProperty("NBS_ORDER_NO")
	private String nbsOrderNo;

	/**
	 * 产品结构编号
	 */
	@JsonProperty("PS_CODE")
	private String psCode;

	/**
	 * 姓名
	 */
	@JsonProperty("NAME")
	private String name;

	/**
	 * 证件提交类型
	 */
	@JsonProperty("ID_TYPE")
	private String idType;

	/**
	 * 证件号码
	 */
	@JsonProperty("ID_NO")
	private String idNo;
	//-------订单业务主键 结束

	public String getMerOrderNo() {
		return merOrderNo;
	}

	public void setMerOrderNo(String merOrderNo) {
		this.merOrderNo = merOrderNo;
	}

	public String getNbsOrderNo() {
		return nbsOrderNo;
	}

	public void setNbsOrderNo(String nbsOrderNo) {
		this.nbsOrderNo = nbsOrderNo;
	}

	public String getPsCode() {
		return psCode;
	}

	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

}
