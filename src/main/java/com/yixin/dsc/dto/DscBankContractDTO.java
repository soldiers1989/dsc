package com.yixin.dsc.dto;

import javax.validation.constraints.*;
import java.io.Serializable;

/**
 * @author sukang
 */
public class DscBankContractDTO implements Serializable{

    @NotBlank(message = "申请编号不能为空")
    private String applyNo;
    @NotNull(message = "签约信息不能为null")
    private DscBankContractSignInfoDTO signInfo;

    @Min(value = 0,message = "请选择0或者1")
    @Max(value = 1,message = "请选择0或者1")
    private String type;

    private String verificationCode;

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public DscBankContractSignInfoDTO getSignInfo() {
        return signInfo;
    }

    public void setSignInfo(DscBankContractSignInfoDTO signInfo) {
        this.signInfo = signInfo;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getVerificationCode() {
        return verificationCode;
    }

    public void setVerificationCode(String verificationCode) {
        this.verificationCode = verificationCode;
    }
}

