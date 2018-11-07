package com.yixin.kepler.dto.settle;

import java.io.Serializable;

/**
 * Package : com.yixin.kepler.dto.settle
 *
 * @author YixinCapital -- wanghonglin
 * 2018/10/25 11:09
 */
public class CalculationServiceFeeParam implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;
    /**
     * 总服务费
     */
    private String totalServiceFee;

    /**
     * 客户融资总金额
     */
    private String custFinaceAmount;

    /**
     * 结算利率
     */
    private String settleInterestRate;

    /**
     * 车辆利率
     */
    private String carInterestRate;

    /**
     * 融资期限
     */
    private String financingMaturity;

    public String getTotalServiceFee() {
        return totalServiceFee;
    }

    public void setTotalServiceFee(String totalServiceFee) {
        this.totalServiceFee = totalServiceFee;
    }

    public String getCustFinaceAmount() {
        return custFinaceAmount;
    }

    public void setCustFinaceAmount(String custFinaceAmount) {
        this.custFinaceAmount = custFinaceAmount;
    }

    public String getSettleInterestRate() {
        return settleInterestRate;
    }

    public void setSettleInterestRate(String settleInterestRate) {
        this.settleInterestRate = settleInterestRate;
    }

    public String getCarInterestRate() {
        return carInterestRate;
    }

    public void setCarInterestRate(String carInterestRate) {
        this.carInterestRate = carInterestRate;
    }

    public String getFinancingMaturity() {
        return financingMaturity;
    }

    public void setFinancingMaturity(String financingMaturity) {
        this.financingMaturity = financingMaturity;
    }

    @Override
    public String toString() {
        return "CalculationServiceFeeParam{" +
                "totalServiceFee='" + totalServiceFee + '\'' +
                ", custFinaceAmount='" + custFinaceAmount + '\'' +
                ", settleInterestRate='" + settleInterestRate + '\'' +
                ", carInterestRate='" + carInterestRate + '\'' +
                ", financingMaturity='" + financingMaturity + '\'' +
                '}';
    }
}
