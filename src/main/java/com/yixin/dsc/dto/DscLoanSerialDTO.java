package com.yixin.dsc.dto;

/**
 * @author sukang
 */
public class DscLoanSerialDTO {

    /**
     *批次号
     */
    private String batchNo;

    /**
     *业务参考号
     */
    private String businessRefNo;


    public String getBatchNo() {
        return batchNo;
    }

    public void setBatchNo(String batchNo) {
        this.batchNo = batchNo;
    }

    public String getBusinessRefNo() {
        return businessRefNo;
    }

    public void setBusinessRefNo(String businessRefNo) {
        this.businessRefNo = businessRefNo;
    }
}
