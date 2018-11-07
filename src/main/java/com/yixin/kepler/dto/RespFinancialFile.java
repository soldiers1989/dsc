package com.yixin.kepler.dto;/**
 * Created by liushuai2 on 2018/6/9.
 */

/**
 * Package : com.yixin.kepler.dto
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月09日 19:39
 */
public class RespFinancialFile {

    private String dependFields;//依赖字段列表
    private String fileMainType;// 文件大类
    private String fileSubType;	//文件小类
    private String rule;	//校验规则
    private String ruleType;	//规则类型
    private String script;	//输出类脚本
    private String fields;	//字段



    public RespFinancialFile(String dependFields, String fileMainType, String fileSubType, String rule,
                             String ruleType, String script,String fields) {
        super();
        this.dependFields = dependFields;
        this.fileMainType = fileMainType;
        this.fileSubType = fileSubType;
        this.rule = rule;
        this.ruleType = ruleType;
        this.script = script;
        this.fields = fields;
    }
    public String getDependFields() {
        return dependFields;
    }
    public void setDependFields(String dependFields) {
        this.dependFields = dependFields;
    }
    public String getFileMainType() {
        return fileMainType;
    }
    public void setFileMainType(String fileMainType) {
        this.fileMainType = fileMainType;
    }
    public String getFileSubType() {
        return fileSubType;
    }
    public void setFileSubType(String fileSubType) {
        this.fileSubType = fileSubType;
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

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }
}
