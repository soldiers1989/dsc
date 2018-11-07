package com.yixin.dsc.entity.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yixin.common.system.domain.BZBaseEntiy;

/**
 * 风控部分银行准入信息
 * Package : com.yixin.dsc.entity
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月4日 下午2:15:38
 *
 */
@Entity
@Table(name = "dsc_sales_apply_risk")
public class DscSalesApplyRisk extends BZBaseEntiy {
	
	private static final long serialVersionUID = -1210400886704222974L;
	/**
	 * 订单主表ID
	 */
	@Column(name = "main_id", columnDefinition = "varchar(50) comment '订单主表ID'")
	private String mainId;
	/**
	 * 申请编号
	 */
	@Column(name = "apply_no", columnDefinition = "varchar(50) comment '申请编号'")
	private String applyNo;

	/**
	 * 银行编码
	 */
	@Column(name = "bank_code", columnDefinition = "varchar(50) comment '银行编码'")
	private String bankCode;



	/**
	 * 银行名称
	 */
	@Column(name = "bank_name", columnDefinition = "varchar(64) comment '银行名称\t'")
	private String bankName;
	
	
	
	/**
	 * 风控码值
	 */
	@Column(name = "risk_code", columnDefinition = "varchar(64) comment '风控码值'")
	private String riskCode;
	/**
	 * 风控描述
	 */
	@Column(name = "risk_desc", columnDefinition = "varchar(500) comment '风控描述'")
	private String riskDesc;

	/**
	 * 对应资方code
	 */
	@Column(name = "code", columnDefinition = "varchar(50) comment '对应资方code'")
	private String code;

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBankName() {
		return bankName;
	}

	public void setBankName(String bankName) {
		this.bankName = bankName;
	}

	public String getRiskCode() {
		return riskCode;
	}

	public void setRiskCode(String riskCode) {
		this.riskCode = riskCode;
	}

	public String getRiskDesc() {
		return riskDesc;
	}

	public void setRiskDesc(String riskDesc) {
		this.riskDesc = riskDesc;
	}

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}
	/**
	 * 通过订单主表ID删除
	 * @param mainId
	 */
	public static void deleteBymainId(String mainId){
		List<DscSalesApplyRisk> lis = DscFileAttachment.findByProperty(DscSalesApplyRisk.class, "mainId", mainId);
		if(lis!=null && !lis.isEmpty()) {
			for (DscSalesApplyRisk att : lis) {
				att.remove();
			}
		}
	}

	public static List<DscSalesApplyRisk> getByMainId(String mainId) {
		StringBuilder jpql = new StringBuilder("select risk from DscSalesApplyRisk risk where risk.deleted = false and risk.mainId = ?1");
		List<Object> filter = new ArrayList<Object>() {{
			add(mainId);
		}};
		return getRepository().createJpqlQuery(jpql.toString()).setParameters(filter).list();
	}
}