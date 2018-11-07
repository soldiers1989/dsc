package com.yixin.dsc.dto.order;

import java.io.Serializable;

/**
 * 合同申请联系人信息表
 * Package : com.yixin.dsc.entity
 *
 * @author YixinCapital -- wangwenlong
 *         2018年6月4日 下午5:53:23
 */
public class DscSalesApplyContactDTO implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 1L;


    private String id;

    /**
     * 订单主表ID
     */
    private String mainId;

    /**
     * 客户ID
     */
    private String custId;

    /**
     * 与借款人关系
     */
    private String ayjkrgx;

    /**
     * 联系人姓名
     */
    private String alxrxm;

    /**
     * 手机号码
     */
    private String asjhm;

    /**
     * 与借款人具体关系
     */
    private String ayjkrjtgx;

    public String getAyjkrjtgx() {
        return ayjkrjtgx;
    }

    public void setAyjkrjtgx(String ayjkrjtgx) {
        this.ayjkrjtgx = ayjkrjtgx;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getMainId() {
        return mainId;
    }

    public void setMainId(String mainId) {
        this.mainId = mainId;
    }

    public String getCustId() {
        return custId;
    }

    public void setCustId(String custId) {
        this.custId = custId;
    }

    public String getAyjkrgx() {
        return ayjkrgx;
    }

    public void setAyjkrgx(String ayjkrgx) {
        this.ayjkrgx = ayjkrgx;
    }

    public String getAlxrxm() {
        return alxrxm;
    }

    public void setAlxrxm(String alxrxm) {
        this.alxrxm = alxrxm;
    }

    public String getAsjhm() {
        return asjhm;
    }

    public void setAsjhm(String asjhm) {
        this.asjhm = asjhm;
    }

}
