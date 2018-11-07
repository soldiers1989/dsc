package com.yixin.kepler.enity;

import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.common.system.util.Label;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


@Entity
@Table(name = "k_asset_afterloan_task")
public class AssetAfterLoanTask extends BZBaseEntiy{
	

	private static final long serialVersionUID = 1L;

	@Label(name = "资方code")
	@Column(name = "financial_code",length = 32)
	private String financialCode;
	
	@Label(name = "申请编号")
	@Column(name = "apply_no", length = 32)
	private String applyNo;
	
	@Label(name = "是否成功")
	@Column(name = "is_success", length = 1)
	private Boolean isSuccess;
	
	@Label(name = "是否结束")
	@Column(name = "is_end", length = 1)
	private Boolean isEnd;
	
	@Label(name = "执行状态")
	@Column(name = "exec_state", length = 2)
	private Integer execState;
	
	@Label(name = "执行次数")
	@Column(name = "exec_times", length = 12)
	private Integer execTimes;

	public String getFinancialCode() {
		return financialCode;
	}

	public void setFinancialCode(String financialCode) {
		this.financialCode = financialCode;
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

	public static AssetAfterLoanTask getByApplyNo(String applyNo) {

		String jpql = "SELECT apt FROM AssetAfterLoanTask AS apt WHERE"
	                + " apt.deleted = 0 AND apt.applyNo = ?1";

		List<Object> params = new ArrayList<Object>(1) {{
			add(applyNo);
		}};
		return getRepository().createJpqlQuery(jpql.toString())
				.setParameters(params).singleResult();
	   
	}

	public static List<AssetAfterLoanTask> getCurrentTask() {
		
		String jpql = "SELECT apt FROM AssetAfterLoanTask AS apt WHERE"
                + " apt.deleted = 0 AND apt.isEnd = 0";
		
		return getRepository().createJpqlQuery(jpql).list();
	}
	
	
	
	
	

}
