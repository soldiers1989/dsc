package com.yixin.kepler.enity;

import javax.persistence.*;

import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.common.system.util.Label;

import java.sql.Timestamp;

/**
 * Created by sukang on 2018/6/8.
 */
@Entity
@Table(name = "k_asset_result_task_log")
public class AssetResultTaskLog extends BZBaseEntiy{
	

	@Label(name = "任务id")
	@Column(name = "task_id")
    private String taskId;

	@Label(name = "申请编号")
    @Column(name = "apply_no")
    private String applyNo;

	@Label(name = "资产编号")
    @Column(name = "asset_no")
    private String assetNo;
    

	@Label(name = "交易id")
    @Column(name = "tran_id")
    private String tranId;

	@Label(name = "受理交易的流水号")
    @Column(name = "tran_no")
    private String tranNo;

	@Label(name = "需要请求的接口地址")
    @Column(name = "req_url")
    private String reqUrl;

	@Label(name = "需要请求的报文")
    @Column(name = "req_body")
    private String reqBody;

	@Label(name = "请求后返回的报文")
    @Column(name = "rep_body")
    private String repBody;
    

	@Label(name = "本次执行时间")
    @Column(name = "exec_time")
    private Timestamp execTime;

	@Label(name = "下次执行时间")
    @Column(name = "next_time")
    private Timestamp nextTime;

	@Label(name = "执行次数")
    @Column(name = "exec_times")
    private Integer execTimes;

	@Label(name = "是否执行结束，当可以拿到结果时表示成功")
    @Column(name = "is_end")
    private Boolean isEnd;


    
    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    
    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

   
    public String getAssetNo() {
        return assetNo;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
    }

    
    public String getTranId() {
        return tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }

    
    public String getTranNo() {
        return tranNo;
    }

    public void setTranNo(String tranNo) {
        this.tranNo = tranNo;
    }

    
    public String getReqUrl() {
        return reqUrl;
    }

    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
    }

    
    public String getReqBody() {
        return reqBody;
    }

    public void setReqBody(String reqBody) {
        this.reqBody = reqBody;
    }

    
    public String getRepBody() {
        return repBody;
    }

    public void setRepBody(String repBody) {
        this.repBody = repBody;
    }


    public Timestamp getExecTime() {
        return execTime;
    }

    public void setExecTime(Timestamp execTime) {
        this.execTime = execTime;
    }

    
    public Timestamp getNextTime() {
        return nextTime;
    }

    public void setNextTime(Timestamp nextTime) {
        this.nextTime = nextTime;
    }

    
    public Integer getExecTimes() {
        return execTimes;
    }

    public void setExecTimes(Integer execTimes) {
        this.execTimes = execTimes;
    }

    
    public Boolean getEnd() {
        return isEnd;
    }

    public void setEnd(Boolean end) {
        isEnd = end;
    }

    
}
