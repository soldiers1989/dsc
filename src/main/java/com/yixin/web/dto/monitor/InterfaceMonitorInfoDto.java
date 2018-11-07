package com.yixin.web.dto.monitor;

import java.util.List;
import java.util.Map;

/**
 * 接口监控采集信息
 *
 * @description:
 * @date: 2018-10-08 15:00
 */
public class InterfaceMonitorInfoDto {

    /**
     * 类型
     */
    private String type;


    /**
     * 服务器host
     */
    private String serverHost;
    /**
     * 类
     */
    private String className;
    /**
     * 方法
     */
    private String methodName;
    /**
     * 开始时间
     */
    private String startTime;
    /**
     * 结束时间
     */
    private String endTime;
    /**
     * 参数
     */
    private String args;
    /**
     * 返回结果
     */
    private String result;
    /**
     * 异常message
     */
    private String message;
    /**
     * 异常堆栈
     */
    private String eStackTrace;
    /**
     * 耗时
     */
    private long timeConsumed;
    /**
     * 接口配置的耗时阈值级别
     */
    private int timeConsumeLevel;

    private String beforeError;

    private String afterError;

    private Boolean hasError;

    private String exceptionError;

    /**
     * 关键参数集合
     */
    private List<Map<String, String>> keyParameters;

    /**
     * 关键参数
     */
    private String keyParametersStr;


    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getKeyParametersStr() {
        return keyParametersStr;
    }

    public void setKeyParametersStr(String keyParametersStr) {
        this.keyParametersStr = keyParametersStr;
    }

    public List<Map<String, String>> getKeyParameters() {
        return keyParameters;
    }

    public void setKeyParameters(List<Map<String, String>> keyParameters) {
        this.keyParameters = keyParameters;
    }

    public String getExceptionError() {
        return exceptionError;
    }

    public void setExceptionError(String exceptionError) {
        this.exceptionError = exceptionError;
    }

    public String getServerHost() {
        return serverHost;
    }

    public void setServerHost(String serverHost) {
        this.serverHost = serverHost;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getArgs() {
        return args;
    }

    public void setArgs(String args) {
        this.args = args;
    }

    public String getResult() {
        return result;
    }

    public void setResult(String result) {
        this.result = result;
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

    public long getTimeConsumed() {
        return timeConsumed;
    }

    public void setTimeConsumed(long timeConsumed) {
        this.timeConsumed = timeConsumed;
    }


    public String getBeforeError() {
        return beforeError;
    }

    public void setBeforeError(String beforeError) {
        this.beforeError = beforeError;
    }

    public String getAfterError() {
        return afterError;
    }

    public void setAfterError(String afterError) {
        this.afterError = afterError;
    }

    public Boolean getHasError() {
        return hasError;
    }

    public void setHasError(Boolean hasError) {
        this.hasError = hasError;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public int getTimeConsumeLevel() {
        return timeConsumeLevel;
    }

    public void setTimeConsumeLevel(int timeConsumeLevel) {
        this.timeConsumeLevel = timeConsumeLevel;
    }
}
