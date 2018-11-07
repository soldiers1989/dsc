package com.yixin.dsc.entity.rule;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yixin.common.system.domain.BZBaseEntiy;

/**
 * 补充规则 动作 信息表
 * 
 * Package : com.yixin.dsc.entity.rule
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月4日 下午7:16:54
 *
 */
@Entity
@Table(name = "dsc_supply_rule_act_Info")
public class DscSupplyRuleActInfo extends BZBaseEntiy {

	private static final long serialVersionUID = 247656126985752723L;
	

	/**
	 * 规则ID
	 */
	@Column(name = "rule_id", columnDefinition = "varchar(64) comment '规则ID'")
	private String ruleId;	
	
	/**
	 * 规则明细ID
	 */
	@Column(name = "rule_detail_id", columnDefinition = "varchar(64) comment '规则明细ID'")
	private String ruleDetailId;	
	
	/**
	 * 补充动作类型
	 */
	@Column(name = "supply_act_type", columnDefinition = "varchar(64) comment '补充动作类型'")
	private String supplyActType;	
	
	/**
	 * 补充值
	 */
	@Column(name = "supply_value", columnDefinition = "varchar(64) comment '补充值'")
	private String supplyValue;	
	
	
	/**
	 * 校验规则公式
	 */
	@Column(name = "validate_formula", columnDefinition = "varchar(64) comment '校验规则公式'")
	private String validateFormula;	

    
    
	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getRuleDetailId() {
		return ruleDetailId;
	}

	public void setRuleDetailId(String ruleDetailId) {
		this.ruleDetailId = ruleDetailId;
	}

	public String getSupplyActType() {
		return supplyActType;
	}

	public void setSupplyActType(String supplyActType) {
		this.supplyActType = supplyActType;
	}

	public String getSupplyValue() {
		return supplyValue;
	}

	public void setSupplyValue(String supplyValue) {
		this.supplyValue = supplyValue;
	}

	public String getValidateFormula() {
		return validateFormula;
	}

	public void setValidateFormula(String validateFormula) {
		this.validateFormula = validateFormula;
	}

}
