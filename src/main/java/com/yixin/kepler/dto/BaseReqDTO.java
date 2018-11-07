package com.yixin.kepler.dto;/**
 * Created by liushuai2 on 2018/6/10.
 */

import java.io.Serializable;

/**
 * Package : com.yixin.kepler.dto
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月10日 10:56
 */
public class BaseReqDTO implements Serializable {

    private String applyNo;

    private String phase;

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }
}
