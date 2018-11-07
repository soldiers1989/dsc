package com.yixin.web.dto;

/**
 * @author sukang
 */
public class AssetMainInfoDto extends  ConditionBaseDto{


    private String financialCode;

    private String lastTrialStatus;

    private String paymentStatus;

    private String identity;


    public String getIdentity() {
        return identity;
    }

    public void setIdentity(String identity) {
        this.identity = identity == null ? null : identity.trim();
    }

    public String getFinancialCode() {
        return financialCode;
    }

    public void setFinancialCode(String financialCode) {
        this.financialCode = financialCode == null ? null : financialCode.trim();
    }

    public String getLastTrialStatus() {
        return lastTrialStatus;
    }

    public void setLastTrialStatus(String lastTrialStatus) {
        this.lastTrialStatus = lastTrialStatus == null ? null:lastTrialStatus.trim();
    }

    public String getPaymentStatus() {
        return paymentStatus;
    }

    public void setPaymentStatus(String paymentStatus) {
        this.paymentStatus = paymentStatus == null ? null:paymentStatus.trim();
    }
}
