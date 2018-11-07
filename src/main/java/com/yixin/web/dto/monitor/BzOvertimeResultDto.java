package com.yixin.web.dto.monitor;

/**
 * @description:
 * @date: 2018-10-24 15:17
 */
public class BzOvertimeResultDto {

    /**
     * 资方编码
     */
    private String financialCode;
    /**
     * 超时配置
     */
    private String overtime;
    /**
     * 超时申请，单号
     */
    private String orders;


    public String getFinancialCode() {
        return financialCode;
    }

    public void setFinancialCode(String financialCode) {
        this.financialCode = financialCode;
    }

    public String getOvertime() {
        return overtime;
    }

    public void setOvertime(String overtime) {
        this.overtime = overtime;
    }

    public String getOrders() {
        return orders;
    }

    public void setOrders(String orders) {
        this.orders = orders;
    }
}
