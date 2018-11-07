package com.yixin.kepler.v1.datapackage.dto.yillion;

import java.math.BigDecimal;

/**
 * @description:
 * @date: 2018-11-01 14:22
 */
public class YILLIONPaymentRespDTO {

    /**
     * 订单编号
     */
    private String appno;
    /**
     * 合同号
     */
    private String contrno;
    /**
     * 借据号
     */
    private String billno;
    /**
     * 合同状态
     */
    private String contrstatus;
    /**
     * 贷款状态
     */
    private String loanstatus;
    /**
     * 放款时间
     */
    private String pushtime;
    /**
     * 放款金额
     */
    private BigDecimal loanamt;


    public String getAppno() {
        return appno;
    }

    public void setAppno(String appno) {
        this.appno = appno;
    }

    public String getContrno() {
        return contrno;
    }

    public void setContrno(String contrno) {
        this.contrno = contrno;
    }

    public String getBillno() {
        return billno;
    }

    public void setBillno(String billno) {
        this.billno = billno;
    }

    public String getContrstatus() {
        return contrstatus;
    }

    public void setContrstatus(String contrstatus) {
        this.contrstatus = contrstatus;
    }

    public String getLoanstatus() {
        return loanstatus;
    }

    public void setLoanstatus(String loanstatus) {
        this.loanstatus = loanstatus;
    }

    public String getPushtime() {
        return pushtime;
    }

    public void setPushtime(String pushtime) {
        this.pushtime = pushtime;
    }

    public BigDecimal getLoanamt() {
        return loanamt;
    }

    public void setLoanamt(BigDecimal loanamt) {
        this.loanamt = loanamt;
    }
}
