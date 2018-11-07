package com.yixin.kepler.core.service.impl;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yixin.kepler.core.service.RulerService;
import com.yixin.kepler.enity.AssetFieldRule;

/**
 * 
 * @author sukang
 * @date 2018-06-08 11:45:59
 */

@Service
public class RulerServiceImpl implements RulerService {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Override
	public List<Map<String, Object>> getAccessRules() {
		
		List<Map<String, Object>> lists = new ArrayList<>();
		List<String> financialIds = AssetFieldRule.getAssetFieldFinancialIds();
		
		logger.info("获取的资方id集合为{}",financialIds);
		
		//根据不同的资方id获取不同的准入规则
		if (!financialIds.isEmpty()) {
			for (String id : financialIds) {
				Map<String, Object> result = new HashMap<>(4);
			    List<AssetFieldRule> assetFieldRules = AssetFieldRule.getAssetRuleByFinancialId(id);
			    
			    ArrayList<Map<String, Object>> rules = new ArrayList<>();
			    if (!assetFieldRules.isEmpty()) {
			    	for (AssetFieldRule assetFieldRule : assetFieldRules) {
			    		Map<String, Object> map = new HashMap<>(4);
			    		map.put("fieldName", assetFieldRule.getFieldName());
			    		map.put("fieldCode", assetFieldRule.getFieldCode());
			    		map.put("rule", assetFieldRule.getRule());
			    		map.put("ruleType",assetFieldRule.getRuleType());
			    		rules.add(map);
					}
			    	
			    	result.put("financialCode",assetFieldRules.get(0).getFinancialCode());
			    	result.put("financialId", id);
			    	result.put("rules", rules);
				}
					
				
				lists.add(result);
			}
		}
		
		return lists;
	}

}
