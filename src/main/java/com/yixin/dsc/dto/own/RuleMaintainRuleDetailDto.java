package com.yixin.dsc.dto.own;

import com.yixin.common.utils.BaseDTO;

/**
 * @author YixinCapital -- chenjiacheng
 *         2018年08月06日 18:30
 **/

public class RuleMaintainRuleDetailDto extends BaseDTO{


	private String ruleId;

	/**
	 * 规则公式定义
	 */
	private String ruleFormula;

	/**
	 * 未匹配提示
	 */
	private String message;

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getRuleFormula() {
		return ruleFormula;
	}

	public void setRuleFormula(String ruleFormula) {
		this.ruleFormula = ruleFormula;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}
}
