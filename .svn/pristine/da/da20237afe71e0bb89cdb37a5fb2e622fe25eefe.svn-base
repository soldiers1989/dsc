package com.yixin.kepler.enity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.common.system.util.Label;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.Set;



/**
 * @author sukang
 * @date 2018-08-01 14:46:13
 */
@Entity
@Table(name = "k_asset_settle_info")
public class AssetSettleInfo extends BZBaseEntiy {

    /**
     * 资方id
     */
	@Column(name = "financial_code", length = 64)
    private String financialCode;

    /**
     * 资方code
     */
	@Column(name = "financial_id", length = 64)
    private String financialId;

    /**
     * 提前还款限制期数
     */
	@Column(name = "early_repay_limit_period", length = 11)
    private Integer earlyRepayLimitPeriod;

    /**
     * 提前还款最小间隔天数
     */
	@Column(name = "early_repay_min_day", length = 11)
    private Integer earlyRepayMinDay;

    /**
     * 逾期宽限期
     */
	@Column(name = "grace_period", length = 11)
    private Integer gracePeriod;

    /**
     * 提前还款违约金收取比例
     */
	@Column(name = "early_repay_penalty_prop", length = 32)
    private String earlyRepayPenaltyProp;

    /**
     * 罚息比率类型
     */
	@Column(name = "penalty_rate_type", length = 32)
    private String penaltyRateType;

    /**
     * 罚息率
     */
	@Column(name = "penalty_rate", length = 32)
    private String penaltyRate;

    /**
     * 银行资金成本费率
     */
	@Column(name = "bank_cost_rate", length = 32)
    private String bankCostRate;

    /**
     * 客户费率
     */
	@Column(name = "cust_rate", length = 32)
    private String custRate;

    /**
     * 新车银行资金成本利率
     */
	@Column(name = "bank_cost_interest_rate_new", length = 32)
    private String bankCostInterestRateNew;

    /**
     * 二手车银行资金成本利率
     */
	@Column(name = "bank_cost_interest_rate_old", length = 32)
    private String bankCostInterestRateOld;

    /**
     * 保留字段1
     */
	@Column(name = "reserve1", length = 225)
    private String reserve1;

    /**
     * 保留字段2
     */
	@Column(name = "reserve2", length = 225)
    private String reserve2;

    /**
     * 保留字段3
     */
	@Column(name = "reserve3", length = 225)
    private String reserve3;

   

    public String getFinancialCode() {
        return financialCode;
    }

    public void setFinancialCode(String financialCode) {
        this.financialCode = financialCode == null ? null : financialCode.trim();
    }

    public String getFinancialId() {
        return financialId;
    }

    public void setFinancialId(String financialId) {
        this.financialId = financialId == null ? null : financialId.trim();
    }

    public Integer getEarlyRepayLimitPeriod() {
        return earlyRepayLimitPeriod;
    }

    public void setEarlyRepayLimitPeriod(Integer earlyRepayLimitPeriod) {
        this.earlyRepayLimitPeriod = earlyRepayLimitPeriod;
    }

    public Integer getEarlyRepayMinDay() {
        return earlyRepayMinDay;
    }

    public void setEarlyRepayMinDay(Integer earlyRepayMinDay) {
        this.earlyRepayMinDay = earlyRepayMinDay;
    }

    public Integer getGracePeriod() {
        return gracePeriod;
    }

    public void setGracePeriod(Integer gracePeriod) {
        this.gracePeriod = gracePeriod;
    }

    public String getEarlyRepayPenaltyProp() {
        return earlyRepayPenaltyProp;
    }

    public void setEarlyRepayPenaltyProp(String earlyRepayPenaltyProp) {
        this.earlyRepayPenaltyProp = earlyRepayPenaltyProp == null ? null : earlyRepayPenaltyProp.trim();
    }

    public String getPenaltyRateType() {
        return penaltyRateType;
    }

    public void setPenaltyRateType(String penaltyRateType) {
        this.penaltyRateType = penaltyRateType == null ? null : penaltyRateType.trim();
    }

    public String getPenaltyRate() {
        return penaltyRate;
    }

    public void setPenaltyRate(String penaltyRate) {
        this.penaltyRate = penaltyRate == null ? null : penaltyRate.trim();
    }

    public String getBankCostRate() {
        return bankCostRate;
    }

    public void setBankCostRate(String bankCostRate) {
        this.bankCostRate = bankCostRate == null ? null : bankCostRate.trim();
    }

    public String getCustRate() {
        return custRate;
    }

    public void setCustRate(String custRate) {
        this.custRate = custRate == null ? null : custRate.trim();
    }

    public String getBankCostInterestRateNew() {
        return bankCostInterestRateNew;
    }

    public void setBankCostInterestRateNew(String bankCostInterestRateNew) {
        this.bankCostInterestRateNew = bankCostInterestRateNew == null ? null : bankCostInterestRateNew.trim();
    }

    public String getBankCostInterestRateOld() {
        return bankCostInterestRateOld;
    }

    public void setBankCostInterestRateOld(String bankCostInterestRateOld) {
        this.bankCostInterestRateOld = bankCostInterestRateOld == null ? null : bankCostInterestRateOld.trim();
    }

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1 == null ? null : reserve1.trim();
    }

    public String getReserve2() {
        return reserve2;
    }

    public void setReserve2(String reserve2) {
        this.reserve2 = reserve2 == null ? null : reserve2.trim();
    }

    public String getReserve3() {
        return reserve3;
    }

    public void setReserve3(String reserve3) {
        this.reserve3 = reserve3 == null ? null : reserve3.trim();
    }
    
    public static AssetSettleInfo getAssetSettleInfoByBankCode(String code){
    	String hql = "SELECT asi FROM AssetSettleInfo asi WHERE asi.financialCode = ?1 AND asi.deleted = false";
    	
    	ArrayList<Object> params = new ArrayList<Object>(1){{
    		add(code);
    	}};
    	return getRepository().createJpqlQuery(hql).setParameters(params).singleResult();
    }
    
    
    
    

   

} 