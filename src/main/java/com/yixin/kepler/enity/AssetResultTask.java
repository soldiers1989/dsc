package com.yixin.kepler.enity;

import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.springframework.transaction.annotation.Transactional;

import com.google.common.collect.Lists;
import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.common.system.util.Label;

/**
 * <B>功能简述</B><br>
 * KAssetResultTask实体类
 *
 * @author liuhongshen
 * @date 2018年06月06日 14:14:54
 */
@Entity
@Table(name = "k_asset_result_task")
public class AssetResultTask extends BZBaseEntiy {

	private static final long serialVersionUID = 3597712012440140070L;
	/**
     * 申请编号
     */
    @Label(name = "申请编号")
    @Column(name = "apply_no", length = 64)
    private String applyNo;
    /**
     * 资产编号
     */
    @Label(name = "资产编号")
    @Column(name = "asset_no", length = 64)
    private String assetNo;
    /**
     * 交易id
     */
    @Label(name = "交易id")
    @Column(name = "tran_id", length = 32)
    private String tranId;
    /**
     * 受理交易的流水号
     */
    @Label(name = "受理交易的流水号")
    @Column(name = "tran_no", length = 64)
    private String tranNo;
    /**
     * 需要请求的接口地址
     */
    @Label(name = "需要请求的接口地址")
    @Column(name = "req_url", length = 255)
    private String reqUrl;
    /**
     * 需要请求的报文
     */
    @Label(name = "需要请求的报文")
    @Column(name = "req_body", length = 255)
    private String reqBody;
    /**
     * 请求后返回的报文
     */
    @Label(name = "请求后返回的报文")
    @Column(name = "rep_body", length = 255)
    private String repBody;
    /**
     * 执行次数
     */
    @Label(name = "执行次数")
    @Column(name = "next_time", length = 19)
    private Date nextTime;
    /**
     * 执行次数
     */
    @Label(name = "执行次数")
    @Column(name = "exec_times", length = 10)
    private Integer execTimes;
    /**
     * 是否执行结束，当可以拿到结果时表示成功
     */
    @Label(name = "是否执行结束，当可以拿到结果时表示成功")
    @Column(name = "is_end", length = 1)
    private Integer isEnd;
    /**
     * 执行结果
     */
    @Label(name = "执行结果")
    @Column(name = "exec_state", length = 10)
    private Integer execState;


    public String getApplyNo() {
        return this.applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getAssetNo() {
        return this.assetNo;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
    }

    public String getTranId() {
        return this.tranId;
    }

    public void setTranId(String tranId) {
        this.tranId = tranId;
    }

    public String getTranNo() {
        return this.tranNo;
    }

    public void setTranNo(String tranNo) {
        this.tranNo = tranNo;
    }

    public String getReqUrl() {
        return this.reqUrl;
    }

    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
    }

    public String getReqBody() {
        return this.reqBody;
    }

    public void setReqBody(String reqBody) {
        this.reqBody = reqBody;
    }

    public String getRepBody() {
        return this.repBody;
    }

    public void setRepBody(String repBody) {
        this.repBody = repBody;
    }

    public Date getNextTime() {
        return this.nextTime;
    }

    public void setNextTime(Date nextTime) {
        this.nextTime = nextTime;
    }

    public Integer getExecTimes() {
        return this.execTimes;
    }

    public void setExecTimes(Integer execTimes) {
        this.execTimes = execTimes;
    }

    public Integer getIsEnd() {
        return this.isEnd;
    }

    public void setIsEnd(Integer isEnd) {
        this.isEnd = isEnd;
    }

    public Integer getExecState() {
        return this.execState;
    }

    public void setExecState(Integer execState) {
        this.execState = execState;
    }


    /**
     * 无参构造
     */
    public AssetResultTask() {

    }

    @SuppressWarnings({ "serial", "unchecked" })
	public static AssetResultTask getByApplyNo(String applyNo) {
        String jpql = "SELECT art FROM AssetResultTask AS art"
        		+ " WHERE art.applyNo = ?1 and isEnd = 0 and deleted = 0";

        List<Object> filter = new ArrayList<Object>() {{
            add(applyNo);
        }};
        return getQueryChannel().getSingleResult(getQueryChannel().createJpqlQuery(jpql).setParameters(filter));
    }
    
    
    /**
     * 查询出需要执行的任务
     * @param localDateTime
     * @param hours 小时
     * @return
     */
    @SuppressWarnings("serial")
	public static List<AssetResultTask> getCurrentTask(Date localDateTime
    		,int hours){
    	
    	String jpql = " SELECT art FROM AssetResultTask AS art"
    			+ " WHERE art.nextTime <= ?1"
    			+ " and art.isEnd = 0 and art.deleted = 0"
    			+ " and art.createTime >= ?2";
    	
    	LocalDateTime minusHours = LocalDateTime.now().minusHours(hours);
    	Instant instant = minusHours.atZone(ZoneId.systemDefault()).toInstant();
    	
    	ArrayList<Object> params = new ArrayList<Object>(2){{
    		add(localDateTime);
    		add(Date.from(instant));
    	}};
    	
    	List<AssetResultTask> tasks = getRepository().createJpqlQuery(jpql).setParameters(params).list();
    	return tasks;
    }
    
    /**
     * 通过订单编号停止查询任务
     * @param applyNo
     * @param phase 
     * @author YixinCapital -- wangwenlong
     *	       2018年9月19日 下午6:06:31
     */
    @Transactional
    public static Boolean stopTaskByApplyNo(String applyNo){
    	String sql = "UPDATE k_asset_result_task SET is_deleted = TRUE WHERE apply_no = ?1 ";
    	List<Object> params = Lists.newArrayList(applyNo); //订单编号
    	
    	int result = -1;
		try {
			result = getRepository().createSqlQuery(sql).setParameters(params).executeUpdate();
		} catch (Exception e) {
			logger.error("通过订单编号停止查询任务异常，订单编号:{}",applyNo,e);
		}
    	return result >=0;
    }
} 