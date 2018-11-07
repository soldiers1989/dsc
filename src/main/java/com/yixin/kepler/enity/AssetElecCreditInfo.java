package com.yixin.kepler.enity;

import com.google.common.collect.Maps;
import com.yixin.common.system.domain.BZBaseEntiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 电子征信信息
 * Package : com.yixin.kepler.enity
 *
 * @author YixinCapital -- wanghonglin
 * 2018/7/10 11:21
 */
@Entity
@Table(name = "k_asset_elec_credit_info")
public class AssetElecCreditInfo extends BZBaseEntiy {
    /**
     * 订单编号
     */
    @Column(name = "apply_no", columnDefinition = "varchar(64) comment '订单编号'")
    private String applyNo;
    /**
     * 征信信息
     */
    @Column(name = "credit_info", columnDefinition = "varchar(500) comment '征信信息JSON串'")
    private String creditInfo;
    /**
     * 征信结果
     */
    @Column(name = "credit_result", columnDefinition = " bit(1) comment '征信结果'")
    private Boolean creditResult;
    /**
     * 征信类型 1/微众 2/易鑫+微众
     */
    @Column(name = "credit_type", columnDefinition = "varchar(10) comment '征信类型 1/微众 2/易鑫+微众'")
    private String creditType;
    /**
     * 0/电子征信 1/电子合同签约
     */
    @Column(name = "type", columnDefinition = "varchar(10) comment '0/电子征信 1/电子合同签约'")
    private String 	type;

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getCreditInfo() {
        return creditInfo;
    }

    public void setCreditInfo(String creditInfo) {
        this.creditInfo = creditInfo;
    }

    public Boolean getCreditResult() {
        return creditResult;
    }

    public void setCreditResult(Boolean creditResult) {
        this.creditResult = creditResult;
    }

    public String getCreditType() {
        return creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }


    public static AssetElecCreditInfo getByapplyNo(String applyNo){
        Map<String, Object> params = Maps.newHashMap();
        params.put("applyNo", applyNo);

        String jpql = "SELECT aeci FROM AssetElecCreditInfo AS aeci WHERE aeci.applyNo = ?1 and aeci.deleted =?2";

        List<Object> filter = new ArrayList<Object>() {{
            add(applyNo);
            add(false);
        }};
        return getRepository().createJpqlQuery(jpql).setParameters(filter).singleResult();
    }
    
    public static AssetElecCreditInfo getCreditSignByApplyNo(String applyNo){
        Map<String, Object> params = Maps.newHashMap();
        params.put("applyNo", applyNo);

        String jpql = "SELECT aeci FROM AssetElecCreditInfo AS aeci WHERE aeci.applyNo = ?1 and aeci.deleted =?2 and aeci.type =?3";

        List<Object> filter = new ArrayList<Object>() {{
            add(applyNo);
            add(false);
            add("0");
        }};
        return getRepository().createJpqlQuery(jpql).setParameters(filter).singleResult();
    }
    
    public static AssetElecCreditInfo getContractSignByApplyNo(String applyNo){
        Map<String, Object> params = Maps.newHashMap();
        params.put("applyNo", applyNo);

        String jpql = "SELECT aeci FROM AssetElecCreditInfo AS aeci WHERE aeci.applyNo = ?1 and aeci.deleted =?2 and aeci.type =?3";

        List<Object> filter = new ArrayList<Object>() {{
            add(applyNo);
            add(false);
            add("1");
        }};
        return getRepository().createJpqlQuery(jpql).setParameters(filter).singleResult();
    }
}
