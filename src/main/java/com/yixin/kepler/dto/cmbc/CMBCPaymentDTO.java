package com.yixin.kepler.dto.cmbc;

/**
 * cmbc请款DTO  (民生银行贷款发放接口)
 * @author sukang
 * @date 2018-06-11 11:31:49
 */
public class CMBCPaymentDTO {
	
	/**
	 * 接收信息 	参数是否必须：true
	 */
	private Object receiveMg;
	
	/**
	 * 借据号 	         参数是否必须：true 
	 */
	private String loanNo;		
	
	/**
	 * 申请书号 	参数是否必须：true
	 */
	private String applyNo; 	 
	
	/**
	 * 放/还款账户 	参数是否必须：true 
	 */
	private String eacNo;	
	
	/**
	 * 证件类型 	参数是否必须：true 
	 */
	private String idType; 		
	
	/**
	 * 证件号码 	参数是否必须：true 
	 */
	private String idNo;		
	
	/**
	 * 客户名称 	参数是否必须：true
	 */
	private String custName; 	 
	
	/**
	 * 扣款日 		参数是否必须：true 
	 */
	private String atrsDueDay;	
	
	/**
	 * 备用字段　 	参数是否必须: false
	 */
	private String reserve1;	 
	
	/**
	 * 备用字段　 	参数是否必须: false
	 */
	private String reserve2; 	 
	
	/**
	 * 备用字段　 	参数是否必须: false
	 */
	private String reserve3; 	
	
	/**
	 * 备用字段　 	参数是否必须: false
	 */
	private String reserve4; 	 	
	
	/**
	 * 备用字段　 	参数是否必须: false 
	 */
	private String reserve5; 	
	
	/**
	 * 放款卡号        参数是否必须：true   他行绑定卡
	 */
	private String tacNo;

	public Object getReceiveMg() {
		return receiveMg;
	}

	public void setReceiveMg(Object receiveMg) {
		this.receiveMg = receiveMg;
	}

	public String getLoanNo() {
		return loanNo;
	}

	public void setLoanNo(String loanNo) {
		this.loanNo = loanNo;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getEacNo() {
		return eacNo;
	}

	public void setEacNo(String eacNo) {
		this.eacNo = eacNo;
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

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getAtrsDueDay() {
		return atrsDueDay;
	}

	public void setAtrsDueDay(String atrsDueDay) {
		this.atrsDueDay = atrsDueDay;
	}

	public String getReserve1() {
		return reserve1;
	}

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	public String getReserve3() {
		return reserve3;
	}

	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}

	public String getReserve4() {
		return reserve4;
	}

	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}

	public String getReserve5() {
		return reserve5;
	}

	public void setReserve5(String reserve5) {
		this.reserve5 = reserve5;
	}

	public String getTacNo() {
		return tacNo;
	}

	public void setTacNo(String tacNo) {
		this.tacNo = tacNo;
	}
	
	
	
	

}
