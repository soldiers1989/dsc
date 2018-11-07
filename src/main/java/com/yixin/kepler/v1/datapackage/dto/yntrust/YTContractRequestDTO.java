package com.yixin.kepler.v1.datapackage.dto.yntrust;

/**
 * @author sukang
 */
public class YTContractRequestDTO extends YTCommonRequestDTO{


    /**
	 * 
	 */
	private static final long serialVersionUID = 4291344101155313033L;

	/**
     *
     */
    private String RequestId;

    /**
     * 借款人身份证号
     */
    private String IDCardNO;

    /**
     * 客户签约绑定手机号
     */
    private String TelephoneNo;

    /**
     * 客户姓名
     */
    private String Name;

    /**
     * 还款卡号
     */
    private String AccountNo;

    /**
     * 产品编号
     */
    private String ProductCode;

    /**
     * 银行编码
     */
    private String BankCode;

    /**
     * 渠道
     *  1.	中金
        2.	通联
        3.	广银联
     */
    private String Channel;


    public String getRequestId() {
        return RequestId;
    }

    public void setRequestId(String requestId) {
        RequestId = requestId;
    }

    public String getIDCardNO() {
        return IDCardNO;
    }

    public void setIDCardNO(String IDCardNO) {
        this.IDCardNO = IDCardNO;
    }

    public String getTelephoneNo() {
        return TelephoneNo;
    }

    public void setTelephoneNo(String telephoneNo) {
        TelephoneNo = telephoneNo;
    }

    public String getName() {
        return Name;
    }

    public void setName(String name) {
        Name = name;
    }

    public String getAccountNo() {
        return AccountNo;
    }

    public void setAccountNo(String accountNo) {
        AccountNo = accountNo;
    }

    public String getProductCode() {
        return ProductCode;
    }

    public void setProductCode(String productCode) {
        ProductCode = productCode;
    }

    public String getBankCode() {
        return BankCode;
    }

    public void setBankCode(String bankCode) {
        BankCode = bankCode;
    }

    public String getChannel() {
        return Channel;
    }

    public void setChannel(String channel) {
        Channel = channel;
    }
}
