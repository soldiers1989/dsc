package com.yixin.kepler.enity;

import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.common.system.util.Label;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Package : com.yixin.kepler.enity
 *
 * @author YixinCapital -- wanghonglin
 * 2018/7/6 10:14
 */
@Entity
@Table(name = "k_asset_payment_fail")
public class AssetPaymentFail extends BZBaseEntiy {
    private static final long serialVersionUID = 8882145542383345037L;
    /**
     * 申请编号
     */
    @Label(name = "申请编号")
    @Column(name = "apply_no", length = 32)
    private String applyNo;
    /**
     * 错误类型
     */
    @Label(name = "错误类型")
    @Column(name = "bank_code", length = 32)
    private String bankCode;
    /**
     * 错误信息
     */
    @Label(name = "错误信息")
    @Column(name = "bank_Message", length = 100)
    private String bankMessage;
    /**
     * 阶段
     */
    @Label(name = "阶段")
    @Column(name = "phase", length = 32)
    private String phase;
    /**
     * 资方code
     */
    @Label(name = "资方code")
    @Column(name = "asset_code", length = 32)
    private String assetCode;
    /**
     * 是否执行成功
     */
    @Label(name = "是否成功")
    @Column(name = "is_success", length = 1)
    private Boolean isSuccess;


    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }

    public String getBankMessage() {
        return bankMessage;
    }

    public void setBankMessage(String bankMessage) {
        this.bankMessage = bankMessage;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getAssetCode() {
        return assetCode;
    }

    public void setAssetCode(String assetCode) {
        this.assetCode = assetCode;
    }

    public Boolean getIsSuccess() {
        return isSuccess;
    }

    public void setIsSuccess(Boolean isSuccess) {
        this.isSuccess = isSuccess;
    }

    public static AssetPaymentFail getByApplyNo(String applyNo) {

        String jpql = "SELECT apt FROM AssetPaymentFail AS apt WHERE"
                + " apt.deleted = 0 AND apt.applyNo = ?1 AND apt.isSuccess = 0";

        List<Object> params = new ArrayList<Object>(1) {{
            add(applyNo);
        }};
        System.out.println(jpql.toString());
        return getRepository().createJpqlQuery(jpql.toString())
                .setParameters(params).singleResult();

    }
}
