package com.yixin.dsc.dto.query;

import java.io.Serializable;


/**
 * @description:
 * @date: 2018-09-25 11:58
 */
public class DscFundsQueryDto implements Serializable {


    private String tranType;

    private String applyNo;

    private String tranDate;


    public String getTranType() {
        return tranType;
    }

    public void setTranType(String tranType) {
        this.tranType = tranType;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getTranDate() {
        return tranDate;
    }

    public void setTranDate(String tranDate) {
        this.tranDate = tranDate;
    }
}
