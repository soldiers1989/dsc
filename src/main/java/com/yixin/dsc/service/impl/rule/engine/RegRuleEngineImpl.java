package com.yixin.dsc.service.impl.rule.engine;

import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.regex.Pattern;

import com.google.common.collect.Maps;
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
 * 正则规则引擎
 * Package : com.yixin.dsc.service.impl.engine
 * 
 * @author YixinCapital -- huguoxing
 *		   2018年6月6日 下午2:45:47
 *
 */
@Service("regRuleEngineImpl")
public class RegRuleEngineImpl implements RuleEngineService{

	private final static Logger logger = LoggerFactory.getLogger(RegRuleEngineImpl.class);

	@Override
	public DscMatchResult eval(DscRuleDetailDto dscRuleDetailDto, Map<String, Object> sourceMap,List<DscFieldFDto> fieldFDtoList,String type) {
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

			DscFieldFDto dscFieldFDto = fieldFDtoList.get(0);
			Object vlue = getVlue(dscFieldFDto, sourceMap);
			boolean isMach = Pattern.matches(jsStr, String.valueOf(vlue));
			if(!isMach){
				logger.info("reg 规则匹配 ruleId:{},正则:{},入参:{},返回结果：{}",dscRuleDetailDto.getRuleId(),jsStr,vlue,isMach);
			}
			dscMatchResult.setMach(isMach);
			dscMatchResult.setReturnStr(jsStr);
			//add by wangwenlong on 2018-08-28
			Map<String,Object> fieldValues = Maps.newHashMap();
			fieldValues.put(dscFieldFDto.getFieldName(), vlue);
			dscMatchResult.setFieldValues(fieldValues);
		}catch (Exception e){
			logger.error("规则匹配异常",e);
			throw new BzException("规则匹配异常");
		}
		return dscMatchResult;
	}

}
