package com.yixin.kepler.enity;/**
 * Created by liushuai2 on 2018/6/7.
 */

import com.google.common.collect.Lists;
import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.common.system.util.Label;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Package : com.yixin.kepler.enity
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月07日 9:56
 */
@Entity
@Table(name = "k_asset_field_rule")
public class AssetFieldRule extends BZBaseEntiy{

	private static final long serialVersionUID = 1466757152395287691L;
	
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

    @Column(name = "field_code", length=256)
    private String fieldCode;

    @Column(name = "field_name", length=256)
    private String fieldName;



    @Column(name = "field_value_type", length=256)
    private String fieldValueType;

    @Column(name = "field_value_dict", length=256)
    private String fieldValueDict;

    @Column(name = "phase", length=256)
    private String phase;

    @Column(name = "field_version", length=256)
    private String fieldVersion;

    @Column(name = "is_access", length=1)
    private boolean isAccess;

    @Column(name = "financial_id", length=256)
    private String financialId;

    @Column(name = "financial_code", length=256)
    private String financialCode;

    /**
     *
     *    id                   varchar(32) not null comment 'ID',
     field_code           varchar(64) comment '资产名称',
     field_name           varchar(64) comment '资产名称',
     field_value_type     varchar(16) comment '资产值类型（数字、字符串、日期，url）',
     field_value_dict     varchar(255) comment '资产值字典（可输入的值）',
     phase                varchar(16) comment '所属阶段',
     rule                 varchar(255) comment '规则',
     rule_type            varchar(16) comment '规则类型',
     script               text comment '输出类脚本',
     remark               varchar(255) comment '备注',
     field_version        varchar(32) comment '合作版本号',
     is_access            bit comment '是否准入',
     financial_id         varchar(32) comment '资方id',
     financial_code       varchar(64) comment '资方code',
     dep_field_codes      text comment '规则依赖的字段，使用逗号分隔',
     primary key (id)
     *
     *
     * @return
     */





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
	
	

    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldValueType() {
        return fieldValueType;
    }

    public void setFieldValueType(String fieldValueType) {
        this.fieldValueType = fieldValueType;
    }

    public String getFieldValueDict() {
        return fieldValueDict;
    }

    public void setFieldValueDict(String fieldValueDict) {
        this.fieldValueDict = fieldValueDict;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getFieldVersion() {
        return fieldVersion;
    }

    public void setFieldVersion(String fieldVersion) {
        this.fieldVersion = fieldVersion;
    }

    public boolean getIsAccess() {
        return isAccess;
    }

    public void setIsAccess(Boolean isAccess) {
        this.isAccess = isAccess;
    }

    public String getFinancialId() {
        return financialId;
    }

    public void setFinancialId(String financialId) {
        this.financialId = financialId;
    }

    public String getFinancialCode() {
        return financialCode;
    }

    public void setFinancialCode(String financialCode) {
        this.financialCode = financialCode;
    }
    

	
	/**
     * 根据资方id获取规则
     * @param id
     * @return
     */
    public static List<AssetFieldRule> getAssetRuleByFinancialId(String id){
    	
    	StringBuilder sb = new StringBuilder(" SELECT afr FROM AssetFieldRule AS afr WHERE afr.financialId = ?1");
    	List<Object> params = Lists.newArrayList();
        params.add(id);
        return getRepository().createJpqlQuery(sb.toString()).setParameters(params).list();
    }

	public static List<String> getAssetFieldFinancialIds() {
		
		StringBuilder sb = new StringBuilder(" SELECT DISTINCT(afr.financialId) FROM AssetFieldRule AS afr where afr.isAccess = ?1");
		
		ArrayList<Object> arrayList = new ArrayList<Object>(1){{
			add(true);
		}};
		
		return getRepository().createJpqlQuery(sb.toString()).setParameters(arrayList).list();
	}

	
}
