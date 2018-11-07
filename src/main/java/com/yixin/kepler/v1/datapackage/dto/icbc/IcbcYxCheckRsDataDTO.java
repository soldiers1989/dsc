package com.yixin.kepler.v1.datapackage.dto.icbc;/**
 * Created by liushuai2 on 2017/8/21.
 */

import java.io.Serializable;


/**
 * 易鑫审核结果DTO
 *
 * Package : com.yixin.creditfront.dto.syndicate.icbc
 *
 * @author YixinCapital -- liushuai2
 *         2017年08月21日 11:38
 */
public class IcbcYxCheckRsDataDTO implements Serializable{
    /**
     * 业务订单号
     */
    private String orderno;
    /**
     * 姓名
     */
    private String name;
    /**
     * 风控电核结果
     */
    private String chkstatus;
    /**
     * 说明
     */
    private String reason;


    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getChkstatus() {
        return chkstatus;
    }

    public void setChkstatus(String chkstatus) {
        this.chkstatus = chkstatus;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }
}
