package com.yixin.dsc.service.impl.rule.engine;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.yixin.dsc.enumpackage.RuleEvalEnum;
import com.yixin.dsc.service.rule.engine.RuleEngineService;

/**
 * 规则引擎管理类
 * Package : com.yixin.dsc.service.impl.engine
 * 
 * @author YixinCapital -- huguoxing
 *		   2018年6月6日 下午2:28:08
 *
 */
@Service
public class RuleEngineManager {
	
	@Resource(name = "felRuleEngineImpl")
	private RuleEngineService felRuleEngineImpl;
	
	@Resource(name = "jsRuleEngineImpl")
	private RuleEngineService jsRuleEngineImpl;
	
	@Resource(name = "regRuleEngineImpl")
	private RuleEngineService regRuleEngineImpl;
	
	@Resource(name = "jsRuleCompiledEngineImpl")
	private RuleEngineService JsRuleCompiledEngineImpl;
	
	
	/**
	 * 通过匹配类型获取规则引擎对象
	 * 
	 * @param paramType
	 * @return 
	 * @author YixinCapital -- huguoxing
	 *	       2018年6月6日 下午2:48:37
	 */
	public RuleEngineService getEngineByMatchType(String matchType){
		if(matchType  == null || StringUtils.isBlank(matchType)){
			return null;
		}
		if(matchType.equals(RuleEvalEnum.JS.getType())){
			return jsRuleEngineImpl;
		}else if (matchType.equals(RuleEvalEnum.FEL.getType())){
			return felRuleEngineImpl;
		}else if (matchType.equals(RuleEvalEnum.REG.getType())){
			return regRuleEngineImpl;
		}else if (matchType.equals(RuleEvalEnum.JSCOM.getType())){
			return JsRuleCompiledEngineImpl;
		}else {
			return null;
		}
	}
	
}
