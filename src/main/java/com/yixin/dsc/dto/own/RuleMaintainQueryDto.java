package com.yixin.dsc.dto.own;

import com.yixin.common.utils.BaseDTO;

import java.io.Serializable;

/**
 * 规则维护页面查询dto
 * @author YixinCapital -- chenjiacheng
 *         2018年08月06日 14:43
 **/

public class RuleMaintainQueryDto extends BaseDTO implements Serializable{

	/**
	 * 规则名称
	 */
	private String ruleName;

	/**
	 * 规则类型   com.yixin.dsc.enumpackage.DscRuleTypeEnum
	 */
	private String ruleType;

	/**
	 * 匹配类型   com.yixin.dsc.enumpackage.RuleEvalEnum
	 */
	private String matchType;

	/**
	 * 规则id
	 */
	private String ruleId;

	/**
	 * 资方名称
	 */
	private String capitalName;

	/**
	 * 是否生效     com.yixin.dsc.enumpackage.DscIsValidEnum
	 */
	private String isValid;

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public String getMatchType() {
		return matchType;
	}

	public void setMatchType(String matchType) {
		this.matchType = matchType;
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getCapitalName() {
		return capitalName;
	}

	public void setCapitalName(String capitalName) {
		this.capitalName = capitalName;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}
}
