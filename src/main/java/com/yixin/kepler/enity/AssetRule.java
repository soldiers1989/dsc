package com.yixin.kepler.enity;/**
 * Created by liushuai2 on 2018/6/9.
 */

import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.common.system.util.Label;

import javax.persistence.Column;

/**
 * Package : com.yixin.kepler.enity
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月09日 19:11
 */
public class AssetRule extends BZBaseEntiy{
    /**
     * 规则
     */
    @Label(name = "规则")
    @Column(name = "rule", length = 255)
    protected String rule;
    /**
     * 规则类型
     */
    @Label(name = "规则类型")
    @Column(name = "rule_type", length = 16)
    protected String ruleType;
    /**
     * 输出类脚本
     */
    @Label(name = "输出类脚本")
    @Column(name = "script", columnDefinition = "LONGTEXT")
    protected String script;

    /**
     * 规则依赖的字段，使用逗号分隔
     */
    @Label(name = "规则依赖的字段，使用逗号分隔")
    @Column(name = "fields", length=256)
    protected String fields;


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
