package com.yixin.dsc.dto;

import javax.validation.constraints.NotBlank;

/**
 * @author sukang
 */
public class DscBankContractSignInfoDTO {

    @NotBlank(message = "客户还款银行卡号不能为空")
    private String accountNo;
    @NotBlank(message = "扣款银行码值不能为空")
    private String bankName;
    @NotBlank(message = "扣款银行名称不能为空")
    private String bankCode;
    @NotBlank(message = "验四银行预留手机号不能为空")
    private String bankReservePhone;

    public String getAccountNo() {
        return accountNo;
    }

    public void setAccountNo(String accountNo) {
        this.accountNo = accountNo;
    }

    public String getBankName() {
        return bankName;
    }

    public void setBankName(String bankName) {
        this.bankName = bankName;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankReservePhone() {
        return bankReservePhone;
    }

    public void setBankReservePhone(String bankReservePhone) {
        this.bankReservePhone = bankReservePhone;
    }
}
