package com.yixin.dsc.service.impl.rule.engine;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.google.common.collect.Maps;
import com.greenpineyu.fel.FelEngine;
import com.greenpineyu.fel.FelEngineImpl;
import com.greenpineyu.fel.context.FelContext;
import com.yixin.dsc.common.exception.BzException;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yixin.dsc.dto.field.DscFieldFDto;
import com.yixin.dsc.dto.rule.DscRuleDetailDto;
import com.yixin.dsc.dto.rule.engine.DscMatchResult;
import com.yixin.dsc.service.rule.engine.RuleEngineService;

/**
 * fel 规则引擎
 * Package : com.yixin.dsc.service.impl.engine
 * 
 * @author YixinCapital -- huguoxing
 *		   2018年6月6日 下午2:26:53
 *
 */
@Service("felRuleEngineImpl")
public class FelRuleEngineImpl implements RuleEngineService{

	private final static Logger logger = LoggerFactory.getLogger(FelRuleEngineImpl.class);


	@Override
	public DscMatchResult eval(DscRuleDetailDto dscRuleDetailDto, Map<String, Object> sourceMap,List<DscFieldFDto> fieldFDtoList,String type){
		if(dscRuleDetailDto==null || sourceMap==null || fieldFDtoList==null){
			throw new BzException("入参为空,规则匹配失败");
		}
		//规则串
		String jsStr = dscRuleDetailDto.getRuleFormula();
		if(StringUtils.isBlank(jsStr)){
			throw new BzException("规则串为空,规则匹配失败");
		}
		DscMatchResult dscMatchResult = new DscMatchResult();
		dscMatchResult.setRuleFormula(jsStr);
		dscMatchResult.setRuleDetailId(dscRuleDetailDto.getRuleDetailId()); //规则明细ID
		dscMatchResult.setRuleId(dscRuleDetailDto.getRuleId()); //规则ID
		dscMatchResult.setRuleMessage(dscRuleDetailDto.getMessage()); //规则message
		dscMatchResult.setMatchTime(new Date()); //匹配时间
		try {
			FelEngine fel = new FelEngineImpl();
			FelContext ctx = fel.getContext();
			Object val = null;
			Map<String,Object> fieldValues = Maps.newHashMap();
			for(DscFieldFDto dscFieldFDto:fieldFDtoList){
				val = getVlue(dscFieldFDto,sourceMap);
				ctx.set(dscFieldFDto.getFieldCode(), val);
				fieldValues.put(dscFieldFDto.getFieldName(), val==null?"":val);
			}
			boolean isMach = (Boolean)fel.eval(jsStr);
			if(!isMach){
				logger.info("fel 规则匹配 ruleId:{},fel:{},入参:{},返回结果：{}",dscRuleDetailDto.getRuleId(),jsStr,val,isMach);
			}
			dscMatchResult.setMach(isMach);
			dscMatchResult.setReturnStr(jsStr);
			dscMatchResult.setFieldValues(fieldValues);
		}catch (Exception e){
			logger.error("规则匹配异常",e);
			throw new BzException("规则匹配异常");
		}
		return dscMatchResult;
	}

}
