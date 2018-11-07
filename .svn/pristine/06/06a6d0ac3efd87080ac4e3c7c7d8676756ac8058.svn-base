package com.yixin.dsc.entity.rule;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yixin.common.system.domain.BZBaseEntiy;

/**
 * 规则  字段关系表
 * 
 * Package : com.yixin.dsc.entity.capital
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月4日 下午6:33:56
 *
 */
@Entity
@Table(name = "dsc_rule_field_relation")
public class DscRuleFieldRelation extends BZBaseEntiy {

	private static final long serialVersionUID = 6591171938373714332L;

	/**
	 * 规则ID
	 */
	@Column(name = "rule_id", columnDefinition = "varchar(64) comment '规则ID'")
	private String ruleId;
	
	/**
	 * 字段code
	 */
	@Column(name = "field_code", columnDefinition = "varchar(64) comment '字段code'")
	private String fieldCode;
	
	/**
	 * 排序字段
	 */
	@Column(name = "sequence", columnDefinition = "int(5) comment '排序字段'")
	private Integer sequence;

	
	
    /**
	 * 通过规则ID获取 字段关系记录 集合
	 * @param ruleId 规则ID
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年6月8日 下午4:36:54
	 */
	public static List<DscRuleFieldRelation> getListByRuleId(String ruleId){
		String jqpl = "from DscRuleFieldRelation _relation where _relation.ruleId = ?1 order by _relation.sequence asc";
		@SuppressWarnings("serial")
		List<Object> params = new ArrayList<Object>(1){{
    		add(ruleId);
    	}};
		return getRepository().createJpqlQuery(jqpl).setParameters(params).list();
	}
	
	
	
	
	
	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getFieldCode() {
		return fieldCode;
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}

	public Integer getSequence() {
		return sequence;
	}

	public void setSequence(Integer sequence) {
		this.sequence = sequence;
	}

}
