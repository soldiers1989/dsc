package com.yixin.dsc.service.impl.rule.engine;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.script.Compilable;
import javax.script.CompiledScript;
import javax.script.ScriptEngineManager;
import javax.script.SimpleBindings;

import org.apache.commons.collections.MapUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.yixin.dsc.common.exception.BzException;
import com.yixin.dsc.dto.field.DscFieldFDto;
import com.yixin.dsc.dto.rule.DscRuleDetailDto;
import com.yixin.dsc.dto.rule.engine.DscMatchResult;
import com.yixin.dsc.service.rule.engine.RuleEngineService;
import com.yixin.dsc.util.DateUtil;

/**
 * JS 规则引擎 -- CompiledScript
 * Package : com.yixin.dsc.service.impl.rule.engine
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年8月16日 上午11:35:23
 *
 */
@Service("jsRuleCompiledEngineImpl")
public class JsRuleCompiledEngineImpl implements RuleEngineService {

	private Compilable compilable;
	
	public JsRuleCompiledEngineImpl() {
		super();
		ScriptEngineManager engineManager = new ScriptEngineManager();
		compilable = (Compilable) engineManager.getEngineByName("javascript");    
	}

	@SuppressWarnings("unchecked")
	@Override
	public DscMatchResult eval(DscRuleDetailDto dscRuleDetailDto, Map<String, Object> sourceMap,
			List<DscFieldFDto> fieldFDtoList, String type) {
		//规则串
		String jsStr = dscRuleDetailDto.getRuleFormula();
		if(StringUtils.isBlank(jsStr)){
			throw new BzException("规则串为空,规则匹配失败");
		}
		DscMatchResult dscMatchResult = new DscMatchResult(); 
		dscMatchResult.setMach(true);
		try {
			SimpleBindings bindings = new SimpleBindings();
			Map<String,Object> params = Maps.newHashMap();
			for (DscFieldFDto fieldDto : fieldFDtoList) {
				Object obj = getVlue(fieldDto,sourceMap);
				if(obj!=null && (obj.getClass().equals(Date.class) || obj.getClass().equals(Timestamp.class))) {
					obj = DateUtil.dateToString((Date) obj, DateUtil.DEFAULT_DATE_TIME_FORMAT);
				}
				params.put(fieldDto.getFieldCode(), obj);
			}
			bindings.put("params", params);
			CompiledScript JSFunction = compilable.compile(jsStr); //解析编译脚本函数
			Object result = JSFunction.eval(bindings);
			if(result == null){
				throw new BzException("规则匹配异常");
			}
			if(result instanceof Map){ //匹配结果为MAP类型
				Map<String,Object> resultMap = (Map<String,Object>)result;
				if(MapUtils.isNotEmpty(resultMap)){
					dscMatchResult.setMach(false);
				}
				dscMatchResult.setMatchResultMap(resultMap);
			} else if(result instanceof String){ //匹配结果为String类型
				String resultStr = (String)result;
				if(StringUtils.isNotBlank(resultStr)){
					dscMatchResult.setMach(false);
				}
				dscMatchResult.setRuleMessage(resultStr);
			} else if (result instanceof Boolean){ //匹配结果为Boolean类型
				Boolean resultBoolean = (Boolean)result;
				dscMatchResult.setMach(resultBoolean);
			}
		} catch (Exception e) {
			logger.error("规则匹配异常",e);
			throw new BzException("规则匹配异常");
		}
		
		return dscMatchResult;
	}

}
