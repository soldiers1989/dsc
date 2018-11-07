package com.yixin.dsc.entity.field;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.dsc.entity.rule.DscRuleFieldRelation;

/**
 * 字段信息表
 * 
 * Package : com.yixin.dsc.entity.capital
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月4日 下午6:33:56
 *
 */
@Entity
@Table(name = "dsc_field")
public class DscField extends BZBaseEntiy{


	private static final long serialVersionUID = -7013473568781640401L;

	
	/**
	 * 字段code
	 */
	@Column(name = "field_code", columnDefinition = "varchar(64) comment '字段code'")
	private String fieldCode;
	
	/**
	 * 字段名称
	 */
	@Column(name = "field_name", columnDefinition = "varchar(64) comment '字段名称'")
	private String fieldName;
	
	/**
	 * 字段类型
	 */
	@Column(name = "field_type", columnDefinition = "varchar(64) comment '字段类型'")
	private String fieldType;
	
	/**
	 * 字段来源
	 */
	@Column(name = "field_source", columnDefinition = "varchar(64) comment '字段来源'")
	private String fieldSource;
	
	/**
	 * 字段获取方法
	 */
	@Column(name = "field_get_method", columnDefinition = "varchar(64) comment '字段获取方法'")
	private String fieldGetMethod;

	/**
	 * 通过规则ID获取 字段关系记录 集合
	 * @param ruleId 规则ID
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年6月8日 下午4:36:54
	 */
	public static List<DscField> getListByRuleId(String ruleId){
		List<DscRuleFieldRelation> relationList = DscRuleFieldRelation.getListByRuleId(ruleId);
		if(CollectionUtils.isEmpty(relationList)){
			return Lists.newArrayList();
		}
		DscField field = null;
		List<DscField> fieldList = Lists.newArrayList();
		for(DscRuleFieldRelation relation : relationList){
			field = getOneByParms(relation.getFieldCode(),null);
			if(field != null){
				fieldList.add(field);
			}
		}
		return fieldList;
	}
	
	/**
	 * 获取字段集合
	 * @param fieldCode 字段code
	 * @param fieldType 字段类型
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年6月8日 下午4:54:56
	 */
	public static List<DscField> getListByParms(String fieldCode,String fieldType){
		List<DscField> capitalList = Lists.newArrayList();
		if(StringUtils.isBlank(fieldCode)){
			return capitalList;
		}
		Map<String,Object> fieldParm = new HashMap<>();
		if(StringUtils.isNoneBlank(fieldCode)){ //字段code
			fieldParm.put("fieldCode",fieldCode);
		}
		if(StringUtils.isNotBlank(fieldType)){ //字段类型
			fieldParm.put("fieldType",fieldType); 
		}
		return DscField.findByProperties(DscField.class, fieldParm);
	}
	
	public static DscField getOneByParms(String fieldCode,String fieldType){
		List<DscField> capitalList =getListByParms(fieldCode, fieldType);
		if(CollectionUtils.isEmpty(capitalList)){
			return null;
		} else {
			return capitalList.get(0);
		}
		
	}

	public String getFieldType() {
		return fieldType;
	}

	public void setFieldType(String fieldType) {
		this.fieldType = fieldType;
	}

	public String getFieldName() {
		return fieldName;
	}

	public void setFieldName(String fieldName) {
		this.fieldName = fieldName;
	}

	public String getFieldCode() {
		return fieldCode;
	}

	public void setFieldCode(String fieldCode) {
		this.fieldCode = fieldCode;
	}

	public String getFieldSource() {
		return fieldSource;
	}

	public void setFieldSource(String fieldSource) {
		this.fieldSource = fieldSource;
	}

	public String getFieldGetMethod() {
		return fieldGetMethod;
	}

	public void setFieldGetMethod(String fieldGetMethod) {
		this.fieldGetMethod = fieldGetMethod;
	}

}
