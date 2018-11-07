package com.yixin.dsc.entity.rule;

import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.dsc.assembler.DscRuleAssembler;
import com.yixin.dsc.dto.rule.DscRuleDto;
import com.yixin.dsc.entity.capital.DscCapitalRuleRelation;
import com.yixin.dsc.entity.field.DscField;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 规则信息表
 * 
 * Package : com.yixin.dsc.entity.rule
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月4日 下午7:31:51
 *
 */
@Entity
@Table(name = "dsc_rule")
public class DscRule extends BZBaseEntiy {

	private static final long serialVersionUID = 9167094018193298906L;

	/**
	 * 规则名称
	 */
	@Column(name = "rule_name", columnDefinition = "varchar(64) comment '规则名称'")
	private String ruleName;
	
	/**
	 * 规则匹配类型
	 * reg正则;fiel
	 */
	@Column(name = "rule_matching_type", columnDefinition = "varchar(64) comment '规则匹配类型'")
	private String ruleMatchingType;
	
	/**
	 * 规则类型
	 * 准入规则、补充资料规则
	 */
	@Column(name = "rule_type", columnDefinition = "varchar(64) comment '规则类型'")
	private String ruleType;

	
	public static List<DscRuleDto> getListByCapitalId(String capitalId,String ruleType){
		List<DscCapitalRuleRelation> relationList = DscCapitalRuleRelation.getListByParms(null, capitalId);
		if(CollectionUtils.isEmpty(relationList)){
			return Lists.newArrayList();
		}
		List<DscRuleDto> ruleDtoList = Lists.newArrayList();
		for(DscCapitalRuleRelation relation:relationList){
			if(StringUtils.isBlank(relation.getRuleId())){
				continue;
			}
			//获取匹配规则实体
			DscRule dscRule = DscRule.get(DscRule.class, relation.getRuleId());
			
			if(dscRule == null || !dscRule.getRuleType().equals(ruleType)){
				continue;
			}
			//获取匹配规则明细实体
			List<DscRuleDetail> ruleDetailList = DscRuleDetail.getListByRuleId(relation.getRuleId());
			if(CollectionUtils.isEmpty(ruleDetailList)){
				continue;
			}
			//获取关联字段
			List<DscField> fieldList = DscField.getListByRuleId(relation.getRuleId());
			
			ruleDtoList.addAll(DscRuleAssembler.assemblerDtoByRuleDetail(dscRule,ruleDetailList,fieldList));
		}
		return ruleDtoList;
	}
	
	@SuppressWarnings("unchecked")
	public static List<DscRule> getDscRuleList(String capitalId,String ruleType){
		String jpql = "select rule from DscRule rule join DscCapitalRuleRelation rel on rule.id = rel.ruleId"
				+ " where rel.capitalId =?1 and rule.ruleType =?2 and rel.deleted = false";
		List<Object> params = Lists.newArrayList();
		params.add(capitalId); //资方id 
		params.add(ruleType); //规则类型
		return getQueryChannel().createJpqlQuery(jpql).setParameters(params).list();
	}
	
	public static List<DscRuleDto> getListByCapitalIdRuleType(String capitalId,String ruleType){
		List<DscRule> ruleList = getDscRuleList(capitalId,ruleType);
		if(CollectionUtils.isEmpty(ruleList)){
			return null;
		}
		List<DscRuleDto> ruleDtoList = Lists.newArrayList();
		for(DscRule rule:ruleList){
			//获取匹配规则明细实体
			List<DscRuleDetail> ruleDetailList = DscRuleDetail.getListByRuleId(rule.getId());
			if(CollectionUtils.isEmpty(ruleDetailList)){
				continue;
			}
			//获取关联字段
			List<DscField> fieldList = DscField.getListByRuleId(rule.getId());
			ruleDtoList.addAll(DscRuleAssembler.assemblerDtoByRuleDetail(rule,ruleDetailList,fieldList));
		}
		return ruleDtoList;
	}

	/**
	 * 通过资方id、规则id、明细id、规则类型获取指定的规则信息
	 * @param capitalId 资方id
	 * @param ruleId 规则id
	 * @param ruleDetailId 规则明细id
	 * @param ruleType 规则类型
	 * @return 规则信息
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/7 17:48
	 */
	public static List<DscRuleDto> getListByCapitalId(String capitalId,String ruleId,String ruleDetailId, String ruleType){
		Map<String, Object> paramMap = new HashMap<>();
		if (StringUtils.isNotBlank(ruleId)) {
			paramMap.put("ruleId", ruleId);
		}
		if (StringUtils.isNotBlank(capitalId)) {
			paramMap.put("capitalId", capitalId);
		}
		paramMap.put("deleted", false);
		List<DscCapitalRuleRelation> relationList = DscCapitalRuleRelation.findByProperties(DscCapitalRuleRelation.class,paramMap);
		if(CollectionUtils.isEmpty(relationList)){
			return Lists.newArrayList();
		}
		List<DscRuleDto> ruleDtoList = Lists.newArrayList();
		for(DscCapitalRuleRelation relation:relationList){
			if(StringUtils.isBlank(relation.getRuleId())){
				continue;
			}
			//获取匹配规则实体
			DscRule dscRule = DscRule.get(DscRule.class, relation.getRuleId());

			if(dscRule == null || !dscRule.getRuleType().equals(ruleType)){
				continue;
			}
			//获取匹配规则明细实体
			Map<String, Object> dataMap = new HashMap<>();
			dataMap.put("ruleId", relation.getRuleId());
			if (StringUtils.isNotBlank(ruleDetailId)) {
				dataMap.put("id", ruleDetailId);
			}
			dataMap.put("deleted", false);
			List<DscRuleDetail> ruleDetailList = DscRuleDetail.findByProperties(DscRuleDetail.class, dataMap);
			if(CollectionUtils.isEmpty(ruleDetailList)){
				continue;
			}
			//获取关联字段
			List<DscField> fieldList = DscField.getListByRuleId(relation.getRuleId());

			ruleDtoList.addAll(DscRuleAssembler.assemblerDtoByRuleDetail(dscRule,ruleDetailList,fieldList));
		}
		return ruleDtoList;

	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleMatchingType() {
		return ruleMatchingType;
	}

	public void setRuleMatchingType(String ruleMatchingType) {
		this.ruleMatchingType = ruleMatchingType;
	}

	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

}
