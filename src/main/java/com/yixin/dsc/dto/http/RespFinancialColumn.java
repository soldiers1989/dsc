package com.yixin.dsc.dto.http;/**
 * Created by liushuai2 on 2018/6/9.
 */

/**
 * Package : com.yixin.kepler.dto
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月09日 19:38
 */
public class RespFinancialColumn {

    private String dependFields;	//依赖字段列表
    private String rule;	//校验规则
    private String ruleType;	//校验规则类型
    private String script;	//输出类脚本


    public RespFinancialColumn() {
		super();
	}
	public RespFinancialColumn(String dependFields, String rule, String ruleType, String script) {
        super();
        this.dependFields = dependFields;
        this.rule = rule;
        this.ruleType = ruleType;
        this.script = script;
    }
    public String getDependFields() {
        return dependFields;
    }
    public void setDependFields(String dependFields) {
        this.dependFields = dependFields;
    }
    public String getRule() {
        return rule;
    }
    public void setRule(String rule) {
        this.rule = rule;
    }
    public String getRuleType() {
        return ruleType;
    }
    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }
    public String getScript() {
        return script;
    }
    public void setScript(String script) {
        this.script = script;
    }
}
