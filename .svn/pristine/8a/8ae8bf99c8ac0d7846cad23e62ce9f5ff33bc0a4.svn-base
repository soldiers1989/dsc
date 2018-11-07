package com.yixin.web.common.entity;

import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.common.system.util.Label;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * @author sukang
 */

@Entity
@Table(name = "dsc_sys_log")
public class DscSysLog extends BZBaseEntiy{

	private static final long serialVersionUID = 33264589018723149L;

	@Label(name = "操作动作")
    @Column(name = "action",length = 50)
    private String action;

    @Label(name = "方法入参")
    @Column(name = "params",columnDefinition = "LONGTEXT")
    private String params;

    @Label(name = "方法名字")
    @Column(name = "method",length = 50)
    private String method;

    @Label(name = "操作人姓名")
    @Column(name = "name",length = 50)
    private String name;

    @Label(name = "ip地址")
    @Column(name = "ip_addr",length = 50)
    private String ipAddr;


    public String getAction() {
        return action;
    }

    public void setAction(String action) {
        this.action = action;
    }

    public String getParams() {
        return params;
    }

    public void setParams(String params) {
        this.params = params;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getIpAddr() {
        return ipAddr;
    }

    public void setIpAddr(String ipAddr) {
        this.ipAddr = ipAddr;
    }
}
