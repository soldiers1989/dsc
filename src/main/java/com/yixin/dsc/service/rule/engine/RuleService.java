package com.yixin.dsc.service.rule.engine;

import java.util.List;
import java.util.Map;

import com.yixin.dsc.dto.DscSupplyDto;
import com.yixin.dsc.dto.DscSupplyFieldsDto;
import com.yixin.dsc.dto.rule.DscRuleDto;
import com.yixin.dsc.dto.rule.engine.BatchMatchResult;
import com.yixin.kepler.common.enums.BankPhaseEnum;

/**
 * 规则service
 * Package : com.yixin.dsc.service.rule.engine
 * 
 * @author YixinCapital -- huguoxing
 *		   2018年6月6日 下午5:29:17
 *
 */
public interface RuleService {
	
	
	/**
	 * 准入规则匹配
	 * 
	 * @param ruleDtoList 规则集合
	 * @param sourceMap 
	 * @author YixinCapital -- huguoxing
	 *	       2018年6月6日 下午7:09:47
	 */
	BatchMatchResult shuntRuleMatch(List<DscRuleDto> ruleDtoList,Map<String,Object> sourceMap,String type);


	/**
	 *
	 * 补充规则判断
	 * 没有规则时， 不需要补充返回false
	 *
	 * @param ruleDtoList
	 * @param sourceMap
	 * @return
	 */
	Boolean supplyRuleMatch(List<DscRuleDto> ruleDtoList,Map<String,Object> sourceMap,String type);

	/**
	 *
	 * 补充规则判断-并返回不满足规则的属性列表
	 *
	 * @param ruleDtoList
	 * @param sourceMap
	 * @return 不满足规则的属性字段列表
	 *
	 * @author YixinCapital -- xjt
	 *	       2018年9月25日 下午7:09:47
	 */
	List<DscSupplyFieldsDto> supplyRuleMatchByFieldResult(List<DscRuleDto> ruleDtoList, Map<String, Object> sourceMap, String type);


	/**
	 * 阶段性校验
	 * @param capitalId 资方ID
	 * @param phaseEnum 阶段
	 * @param sourceMap 数据来源
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年9月6日 下午12:59:26
	 */
	DscSupplyDto phaseCheck(String capitalId,BankPhaseEnum phaseEnum,Map<String, Object> sourceMap);
}
