package com.yixin.web.dto.monitor;

import java.util.Date;

/**
 * @description:
 * @date: 2018-10-24 15:17
 */
public class BzOvertimeOrderInfoDto {

    /**
     * 单号
     */
    private String applyNo;
    /**
     * 基准时间
     */
    private Date markDate;
    /**
     * 资方编码
     */
    private String financialCode;


    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public Date getMarkDate() {
        return markDate;
    }

    public void setMarkDate(Date markDate) {
        this.markDate = markDate;
    }

    public String getFinancialCode() {
        return financialCode;
    }

    public void setFinancialCode(String financialCode) {
        this.financialCode = financialCode;
    }
}
