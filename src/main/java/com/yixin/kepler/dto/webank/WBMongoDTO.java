package com.yixin.kepler.dto.webank;

import java.io.Serializable;
import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.yixin.kepler.core.constant.CommonConstant;

/**
 * 微众银行存放mongoDTO
 * Package : com.yixin.kepler.dto.webank
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月20日 下午1:26:21
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WBMongoDTO implements Serializable{

	private static final long serialVersionUID = -4394518448063608647L;

	private String id;
	
	/** 标签 */
	private String sign;
	
	/** 初审状态 */
	private Integer firstState;
	
	/** 初审成功时间 */
	private String firstSuccessTime;
	
	/** 终审状态 */
	private Integer lastState;
	
	/** 终审成功时间 */
	private String lastSuccessTime;

	/** 申请编号  */
	private String applyNo;
	
	/** 授权书签署状态 */
    private Integer creditSignState;
    
    /**  合同签署状态 */
    private Integer contractSignState;
	
	/** 微众申请编号 */
	private String appNo;
	
	/** 微众订单编号 */
	private String nbsOrderNo;
	
	/** 申请人姓名 */
	private String akhxm;
	
	/** 证件号码*/
	private String azjhm;
	
	/** 手机号码*/
	private String asjhm;
	
	/** 借记卡帐号 /易鑫验证四要素还款卡号 */
	private String ahkrjjkzh;
	
	/** 易鑫验证四要素银行预留手机号码 */
	private String ackrsjhm;
	

	/**
     * 资金成本
     */
    private BigDecimal capitalCost;
    
    /**
     * 客户利率
     */
    private BigDecimal fkhll;
    
    /**
     * 浮动利率/费率
     */
    private BigDecimal floatingRate;
    
    /**
     * 罚息率
     */
    private BigDecimal penaltyRate;
    
    /**
     * 是否直接跳转到二审
     */
    private String skipToLast = CommonConstant.FALSE;
    
	public String getSkipToLast() {
		return skipToLast;
	}

	public void setSkipToLast(String skipToLast) {
		this.skipToLast = skipToLast;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public Integer getCreditSignState() {
		return creditSignState;
	}

	public void setCreditSignState(Integer creditSignState) {
		this.creditSignState = creditSignState;
	}

	public Integer getContractSignState() {
		return contractSignState;
	}

	public void setContractSignState(Integer contractSignState) {
		this.contractSignState = contractSignState;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getAppNo() {
		return appNo;
	}

	public BigDecimal getCapitalCost() {
		return capitalCost;
	}

	public void setCapitalCost(BigDecimal capitalCost) {
		this.capitalCost = capitalCost;
	}

	public BigDecimal getFkhll() {
		return fkhll;
	}

	public void setFkhll(BigDecimal fkhll) {
		this.fkhll = fkhll;
	}

	public BigDecimal getFloatingRate() {
		return floatingRate;
	}

	public void setFloatingRate(BigDecimal floatingRate) {
		this.floatingRate = floatingRate;
	}

	public BigDecimal getPenaltyRate() {
		return penaltyRate;
	}

	public void setPenaltyRate(BigDecimal penaltyRate) {
		this.penaltyRate = penaltyRate;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getNbsOrderNo() {
		return nbsOrderNo;
	}

	public void setNbsOrderNo(String nbsOrderNo) {
		this.nbsOrderNo = nbsOrderNo;
	}

	public String getAkhxm() {
		return akhxm;
	}

	public void setAkhxm(String akhxm) {
		this.akhxm = akhxm;
	}

	public String getAzjhm() {
		return azjhm;
	}

	public void setAzjhm(String azjhm) {
		this.azjhm = azjhm;
	}

	public String getAsjhm() {
		return asjhm;
	}

	public void setAsjhm(String asjhm) {
		this.asjhm = asjhm;
	}

	public String getAhkrjjkzh() {
		return ahkrjjkzh;
	}

	public void setAhkrjjkzh(String ahkrjjkzh) {
		this.ahkrjjkzh = ahkrjjkzh;
	}

	public String getAckrsjhm() {
		return ackrsjhm;
	}

	public void setAckrsjhm(String ackrsjhm) {
		this.ackrsjhm = ackrsjhm;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public Integer getFirstState() {
		return firstState;
	}

	public void setFirstState(Integer firstState) {
		this.firstState = firstState;
	}

	public String getFirstSuccessTime() {
		return firstSuccessTime;
	}

	public void setFirstSuccessTime(String firstSuccessTime) {
		this.firstSuccessTime = firstSuccessTime;
	}

	public Integer getLastState() {
		return lastState;
	}

	public void setLastState(Integer lastState) {
		this.lastState = lastState;
	}

	public String getLastSuccessTime() {
		return lastSuccessTime;
	}

	public void setLastSuccessTime(String lastSuccessTime) {
		this.lastSuccessTime = lastSuccessTime;
	}
	
}
