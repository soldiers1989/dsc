package com.yixin.web.dto.monitor;

/**
 * 业务异常采集信息
 *
 * @description:
 * @date: 2018-10-22 16:17
 */
public class BzErrorInfoDto {

    /**
     * 接口名称，业务异常唯一标识
     * 推荐  className/methodName
     */
    private String interfaceName;
    /**
     * 业务主键
     */
    private String bzId;
    /**
     * 提示消息
     */
    private String message;
    /**
     * 异常堆栈
     */
    private String eStackTrace;


    public String getInterfaceName() {
        return interfaceName;
    }

    public void setInterfaceName(String interfaceName) {
        this.interfaceName = interfaceName;
    }

    public String getBzId() {
        return bzId;
    }

    public void setBzId(String bzId) {
        this.bzId = bzId;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String geteStackTrace() {
        return eStackTrace;
    }

    public void seteStackTrace(String eStackTrace) {
        this.eStackTrace = eStackTrace;
    }
}
