package com.yixin.kepler.v1.datapackage.dto.yntrust;

/**
 * @author sukang
 */
public class YTVerificationCodeRequestDTO extends YTCommonRequestDTO{


    /**
	 * 
	 */
	private static final long serialVersionUID = 6763322931812611633L;


    private String ProductCode;


    /**
     * 签约接口生成的流水号
     */
    private String TransactionNo;

    /**
     * 短信验证码
     */
    private String VerificationCode;

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public String getTransactionNo() {
        return TransactionNo;
    }

    public void setTransactionNo(String transactionNo) {
        TransactionNo = transactionNo;
    }

    public String getVerificationCode() {
        return VerificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        VerificationCode = verificationCode;
    }
}
