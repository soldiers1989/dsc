package com.yixin.web.entity;

import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.common.system.util.Label;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @description:
 * @date: 2018-10-11 10:56
 */
@Entity
@Table(name = "m_order_info")
public class MOrderInfo extends BZBaseEntiy {


    @Label(name = "申请编号")
    @Column(name = "apply_no", length = 64)
    private String applyNo;

    @Label(name = "申请单状态")
    @Column(name = "status", length = 11)
    private Integer status;


    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
