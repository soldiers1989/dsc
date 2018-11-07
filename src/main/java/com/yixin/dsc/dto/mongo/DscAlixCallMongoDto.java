package com.yixin.dsc.dto.mongo;


import com.yixin.dsc.util.DateUtil;

import java.util.Date;

/**
 * Created by wangxulong on 2018/7/2.
 */
public class DscAlixCallMongoDto {

    /**
     * 申请编号
     */
    private String applyNo;
    /**
     * 创建时间 精确到毫秒
     */
    private String createTime = DateUtil.dateToString(new Date(), DateUtil.DEFAULT_TIMESTAMP_FORMAT);
    /**
     * 环节
     */
    private String link;

    /**
     * 方法
     */
    private String method;

    /**
     * 调用开始时间 精确到毫秒
     */
    private String startTime;

    /**
     * 调用结束时间 精确到毫秒
     */
    private String endTime;
    
    /**
     * 调用结束时间 精确到毫秒
     */
    private Long intervalTime;

    /**
     * 执行结果
     */
    private Object result;

    /**
     * 备注
     */
    private String  remarks;

    /**
     * 执行ip
     */
    private String ip;

    /**
     * alix   或者    bank
     */
    private String type;

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getCreateTime() {
        return createTime;
    }

    public void setCreateTime(String createTime) {
        this.createTime = createTime;
    }

    public String getLink() {
        return link;
    }

    public void setLink(String link) {
        this.link = link;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public Long getIntervalTime() {
		return intervalTime;
	}

	public void setIntervalTime(Long intervalTime) {
		this.intervalTime = intervalTime;
	}

	public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    public String getRemarks() {
        return remarks;
    }

    public void setRemarks(String remarks) {
        this.remarks = remarks;
    }

    public String getIp() {
        return ip;
    }

    public void setIp(String ip) {
        this.ip = ip;
    }

    public Object getResult() {
        return result;
    }

    public void setResult(Object result) {
        this.result = result;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getMethod() {
        return method;
    }

    public void setMethod(String method) {
        this.method = method;
    }
}
