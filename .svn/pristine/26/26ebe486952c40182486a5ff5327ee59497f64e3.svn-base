package com.yixin.dsc.service.impl.rule.engine;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.script.Invocable;
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.google.common.collect.Maps;
import com.yixin.dsc.common.exception.BzException;
import com.yixin.dsc.dto.field.DscFieldFDto;
import com.yixin.dsc.dto.rule.DscRuleDetailDto;
import com.yixin.dsc.dto.rule.engine.DscMatchResult;
import com.yixin.dsc.enumpackage.DscRuleTypeEnum;
import com.yixin.dsc.service.rule.engine.RuleEngineService;
import com.yixin.dsc.util.DateUtil;

/**
 * JS 规则引擎
 * Package : com.yixin.dsc.service.impl.engine
 * 
 * @author YixinCapital -- huguoxing
 *		   2018年6月6日 下午2:20:46
 *
 */
@Service("jsRuleEngineImpl")
public class JsRuleEngineImpl implements RuleEngineService {

	private final static Logger logger = LoggerFactory.getLogger(JsRuleEngineImpl.class);


	@Override
	public DscMatchResult eval(DscRuleDetailDto dscRuleDetailDto, Map<String, Object> sourceMap,List<DscFieldFDto>
			fieldFDtoList,String type) {
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
			ScriptEngineManager m = new ScriptEngineManager();
			//获取JavaScript执行引擎
			ScriptEngine engine = m.getEngineByName("JavaScript");
			//执行JavaScript代码
			engine.eval(jsStr);
			Invocable invocable=(Invocable)engine;

			Map<String,Object> fieldValues = Maps.newHashMap();
			Object[] paramOBject = new Object[fieldFDtoList.size()];
			for (int i = 0; i < fieldFDtoList.size(); i++) {
				Object obj = getVlue(fieldFDtoList.get(i),sourceMap);
				if(obj!=null && (obj.getClass().equals(Date.class) || obj.getClass().equals(Timestamp.class))) {
					paramOBject[i] = DateUtil.dateToString((Date) obj, DateUtil.DEFAULT_DATE_TIME_FORMAT);
				}else {
					paramOBject[i] = obj;
				}
				fieldValues.put(fieldFDtoList.get(i).getFieldName(), paramOBject[i]);
			}
			dscMatchResult.setFieldValues(fieldValues);
			
			Object object = invocable.invokeFunction("toDo",paramOBject);
			/**
			 * modify by 王文龙 on 2018-08-28 调整代码结构并增加返回字段
			 * 预计2018-08-30 上线
			 */
			if(object == null){
				logger.info("JS规则匹配 ruleId:{},function:{},入参:{},返回结果为空",dscRuleDetailDto.getRuleId(),jsStr,paramOBject);
				dscMatchResult.setMach(false);
				dscMatchResult.setReturnStr("校验返回为空");
				return dscMatchResult;
			}
			//如果是信审补充准入校验
			if(StringUtils.equals(type, DscRuleTypeEnum.CREDITFRONT_SHUNT.getType())){
				String  returnStr = (String) object;
				Boolean flag = StringUtils.equals("",returnStr);
				if(flag){
					dscMatchResult.setMach(flag);
				}else{
					dscMatchResult.setMach(flag);
					dscMatchResult.setReturnStr("需要补充字段是："+returnStr);
				}
				logger.info("JS信审补充规则校验 ruleId:{},function:{},入参:{},返回结果：{},需要补充字段是：{}",dscRuleDetailDto
						.getRuleId(),jsStr,paramOBject,flag,returnStr);
				return dscMatchResult;
			}
			
			Boolean flag = (Boolean) object;
			dscMatchResult.setMach(flag);
			//返回的资料类型未初始化
			if(!flag) {
				logger.info("JS规则匹配 ruleId:{},message:{},function:{},入参:{},返回结果：{}",dscRuleDetailDto.getRuleId(),dscRuleDetailDto.getMessage(),jsStr,paramOBject,flag);
				dscMatchResult.setReturnStr(dscRuleDetailDto.getMessage());
			}
		}catch (Exception e){
			logger.error("规则匹配异常",e);
			throw new BzException("规则匹配异常");
		}
		return dscMatchResult;
	}
	
}
