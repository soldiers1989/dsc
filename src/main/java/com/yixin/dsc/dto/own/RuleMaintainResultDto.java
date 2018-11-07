package com.yixin.dsc.dto.own;

import java.io.Serializable;

/**
 * 规则维护查询结果dto
 * @author YixinCapital -- chenjiacheng
 *         2018年08月06日 14:46
 **/

public class RuleMaintainResultDto implements Serializable{

	/**
	 * 规则id
	 */
	private String ruleId;

	/**
	 * 规则名称
	 */
	private String ruleName;

	/**
	 * 规则类型
	 */
	private String ruleType;

	/**
	 * 匹配类型
	 */
	private String matchType;

	/**
	 * 资方名称
	 */
	private String capticalName;

	/**
	 * 是否生效
	 */
	private String isValid;

	/**
	 * 明细个数
	 */
	private Integer detailNums;

	/**
	 * 资方个数
	 */
	private Integer capticalNums;

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

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

	public String getCapticalName() {
		return capticalName;
	}

	public void setCapticalName(String capticalName) {
		this.capticalName = capticalName;
	}

	public String getIsValid() {
		return isValid;
	}

	public void setIsValid(String isValid) {
		this.isValid = isValid;
	}

	public Integer getDetailNums() {
		return detailNums;
	}

	public void setDetailNums(Integer detailNums) {
		this.detailNums = detailNums;
	}

	public Integer getCapticalNums() {
		return capticalNums;
	}

	public void setCapticalNums(Integer capticalNums) {
		this.capticalNums = capticalNums;
	}
}
