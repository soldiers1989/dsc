package com.yixin.kepler.v1.datapackage.dto.yntrust;


/**
 * @author sukang
 */
public class YTGetContractRespDTO extends YTCommonResponseDTO{


    /**
     * 绑定流水号
     */
    private String TransactionNo;

    /**
     *是否已签约:首次签约返回1，重复签约返回2
     */
    private String IsSigned;

    /**
     *签约协议编号（IsSigned返回2：才会返回已签约的签约协议号）
     */
    private String SignNo;



    public String getTransactionNo() {
        return TransactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        TransactionNo = transactionNo;
    }

    public String getIsSigned() {
        return IsSigned;
    }

    public void setIsSigned(String isSigned) {
        IsSigned = isSigned;
    }

    public String getSignNo() {
        return SignNo;
    }

    public void setSignNo(String signNo) {
        SignNo = signNo;
    }
}
