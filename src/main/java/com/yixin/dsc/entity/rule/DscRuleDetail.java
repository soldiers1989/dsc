package com.yixin.dsc.entity.rule;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

import com.yixin.common.system.domain.BZBaseEntiy;

/**
 * 规则 明细表
 * 
 * Package : com.yixin.dsc.entity.rule
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月4日 下午7:32:00
 *
 */
@Entity
@Table(name = "dsc_rule_detail")
public class DscRuleDetail extends BZBaseEntiy {

	private static final long serialVersionUID = 9167094018193298906L;

	
	/**
	 * 规则ID
	 */
	@Column(name = "rule_id", columnDefinition = "varchar(64) comment '规则ID'")
	private String ruleId;	
	
	/**
	 * 规则公式定义
	 */
	@Column(name = "rule_formula", columnDefinition = "varchar(64) comment '规则公式定义'")
	private String ruleFormula;	
	
	/**
	 * 未匹配提示
	 */
	@Column(name = "message", columnDefinition = "varchar(64) comment ' 未匹配提示'")
	private String message;	

	/**
	 * 通过规则ID获取 规则明细记录 集合
	 * @param ruleId 规则ID
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年6月8日 下午4:36:54
	 */
	public static List<DscRuleDetail> getListByRuleId(String ruleId){
		List<DscRuleDetail> capitalList = Lists.newArrayList();
		if(StringUtils.isBlank(ruleId)){
			return capitalList;
		}
		Map<String,Object> param = new HashMap<>();
		if(StringUtils.isNotBlank(ruleId)){  //规则ID
			param.put("ruleId",ruleId);
		}
		param.put("deleted", false);
		return DscRuleDetail.findByProperties(DscRuleDetail.class, param);
	}
    
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
