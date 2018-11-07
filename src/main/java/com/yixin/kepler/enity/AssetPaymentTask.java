package com.yixin.kepler.enity;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.collections.CollectionUtils;
import org.assertj.core.util.Lists;

import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.common.system.util.Label;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.core.constant.CommonConstant;


@Entity
@Table(name = "k_asset_payment_task")
public class AssetPaymentTask extends BZBaseEntiy{
	

	private static final long serialVersionUID = 1L;
	
	@Label(name = "申请编号")
	@Column(name = "apply_no", length = 32)
	private String applyNo;
	
	@Label(name = "是否成功")
	@Column(name = "is_success", length = 1)
	private Boolean isSuccess;
	
	@Label(name = "是否结束")
	@Column(name = "is_end", length = 1)
	private Boolean isEnd;
	


	@Label(name = "资方code")
	@Column(name = "asset_code", length = 32)
	private String assetCode;
	
	@Label(name = "执行状态")
	@Column(name = "exec_state", length = 2)
	private Integer execState;
	
	@Label(name = "执行次数")
	@Column(name = "exec_times", length = 12)
	private Integer execTimes;


	@Label(name = "文件是否上传成功")
	@Column(name = "file_status",length = 1)
	private Integer fileStatus;


	public Integer getFileStatus() {
		return fileStatus;
	}

	public void setFileStatus(Integer fileStatus) {
		this.fileStatus = fileStatus;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public Boolean getIsSuccess() {
		return isSuccess;
	}

	public void setIsSuccess(Boolean isSuccess) {
		this.isSuccess = isSuccess;
	}

	public String getAssetCode() {
		return assetCode;
	}

	public void setAssetCode(String assetCode) {
		this.assetCode = assetCode;
	}
	
	public Boolean getIsEnd() {
		return isEnd;
	}

	public void setIsEnd(Boolean isEnd) {
		this.isEnd = isEnd;
	}


	public Integer getExecState() {
		return execState;
	}

	public void setExecState(Integer execState) {
		this.execState = execState;
	}

	public Integer getExecTimes() {
		return execTimes;
	}

	public void setExecTimes(Integer execTimes) {
		this.execTimes = execTimes;
	}

	public static AssetPaymentTask getByApplyNo(String applyNo) {

		String jpql = "SELECT apt FROM AssetPaymentTask AS apt WHERE"
	                + " apt.deleted = 0 AND apt.applyNo = ?1";

		List<Object> params = new ArrayList<Object>(1) {{
			add(applyNo);
		}};
		return getRepository().createJpqlQuery(jpql.toString())
				.setParameters(params).singleResult();
	   
	}

	public static List<AssetPaymentTask> getCurrentTask() {
		// add by wanghonglin 修改查询条数，一次最多执行50条数据。2018-09-26
		/*String jpql = "SELECT apt FROM AssetPaymentTask AS apt WHERE"
                + " apt.deleted = 0 AND apt.isEnd = 0 LIMIT 50";*/
		
		String sql = " SELECT id FROM k_asset_payment_task "
				+ " WHERE is_deleted = FALSE AND is_end = FALSE "
				+ "	AND file_status = ?1 "
				+ " ORDER BY create_time ASC "
				+ " LIMIT 50 ";
		List<Object> params = Lists.newArrayList(CommonConstant.ONE);
		
		List<Object> resultList = getRepository().createSqlQuery(sql).setParameters(params).list();
		if(CollectionUtils.isEmpty(resultList)){
			return Lists.newArrayList();
		}
		List<AssetPaymentTask> taskList = Lists.newArrayList();
		for(Object result:resultList){
			String id = (String)result; //主键
			if(id == null){
				continue;
			}
			taskList.add(AssetPaymentTask.get(AssetPaymentTask.class, id));
		}
		return taskList;
	}
	
	
	/**
	 * 更新任务记录结束状态
	 * @param id 任务主键
	 * @param isSuccess 是否成功 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年10月9日 下午4:21:04
	 */
	public void updateTaskEnd(boolean isSuccess,String errorCode,String errorMessage){
		this.setIsSuccess(isSuccess);
		this.setIsEnd(true);
		this.update();
		
		if(!isSuccess){ //失败，则记录错误日志
			/**
			 * 情况异常记录表
			 */
			AssetPaymentFail failLog = new AssetPaymentFail();
			failLog.setApplyNo(this.getApplyNo());
			failLog.setAssetCode(this.getAssetCode());
			failLog.setBankCode(errorCode);
			failLog.setBankMessage(errorMessage);
			failLog.setIsSuccess(false);
			failLog.setPhase(BankPhaseEnum.PAYMENT.getPhase());
			failLog.create();
		}
	}
	
	/**
	 *  
	 * @author YixinCapital -- wangwenlong
	 *	       2018年10月12日 下午2:14:55
	 */
	public void addOneTimes(){
		this.setExecTimes((this.getExecTimes() == null?1:this.getExecTimes())+1);
		this.update();
	}

}
