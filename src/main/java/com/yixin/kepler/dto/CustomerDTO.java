package com.yixin.kepler.dto;/**
 * Created by liushuai2 on 2018/6/13.
 */

import java.io.Serializable;

/**
 * Package : com.yixin.kepler.dto
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月13日 14:16
 */
public class CustomerDTO implements Serializable {
    private String name;
    private String idNo;
    public CustomerDTO(){}

    public CustomerDTO(String name, String idNo){
        this.name = name;
        this.idNo = idNo;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }
}
