package com.yixin.kepler.v1.datapackage.dto.other;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 推送结算放款信息DTO
 * Package : com.yixin.kepler.v1.datapackage.dto.other
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年10月15日 下午1:39:57
 *
 */
public class SettleFkDTO implements Serializable{


	/**
	 * 
	 * @author YixinCapital -- Xin.Li
	 *		   2018年10月12日 下午4:52:36
	 *
	 */
	private static final long serialVersionUID = 1L;
	
	/**
	 * 申请编号
	 */
	private String applyNo;
	/**
	 * 银行名称 例如：云信
	 */
	private String bankName;
	/**
	 * 银行流水号
	 */
	private String bankSeq;
	/**
	 * 放款日期yyyyMMdd
	 */
	private String loanDt;
	/**
	 * 放款金额
	 */
	private BigDecimal tranAm;
	/**
	 * 放款状态S：成功；E：失败
	 */
	private String appvResult;
	
	public String getApplyNo() {
		return applyNo;
	}
	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
	public String getBankName() {
		return bankName;
	}
	public void setBankName(String bankName) {
		this.bankName = bankName;
	}
	public String getBankSeq() {
		return bankSeq;
	}
	public void setBankSeq(String bankSeq) {
		this.bankSeq = bankSeq;
	}
	public String getLoanDt() {
		return loanDt;
	}
	public void setLoanDt(String loanDt) {
		this.loanDt = loanDt;
	}
	public BigDecimal getTranAm() {
		return tranAm;
	}
	public void setTranAm(BigDecimal tranAm) {
		this.tranAm = tranAm;
	}
	public String getAppvResult() {
		return appvResult;
	}
	public void setAppvResult(String appvResult) {
		this.appvResult = appvResult;
	}
}
