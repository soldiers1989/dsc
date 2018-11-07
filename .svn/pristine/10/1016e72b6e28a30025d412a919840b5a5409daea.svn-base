package com.yixin.dsc.service.rule.engine;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yixin.dsc.dto.field.DscFieldFDto;
import com.yixin.dsc.dto.rule.DscRuleDetailDto;
import com.yixin.dsc.dto.rule.engine.DscMatchResult;


/**
 * 规则引擎接口
 * Package : com.yixin.dsc.service.rule.engine
 * 
 * @author YixinCapital -- huguoxing
 *		   2018年6月6日 下午2:02:34
 *
 */
public interface RuleEngineService {
	
	Logger logger = LoggerFactory.getLogger(RuleEngineService.class);
	
	/**
	 * 规则执行方法
	 * 
	 * @param dscRuleDetailDto 规则明细
	 * @param sourceMap 数据源
	 * @param fieldFDtoList 字段list
	 * @return 
	 * @author YixinCapital -- huguoxing
	 *	       2018年6月6日 下午4:42:21
	 */
	DscMatchResult eval(DscRuleDetailDto dscRuleDetailDto,Map<String,Object> sourceMap,List<DscFieldFDto>
			fieldFDtoList,String type);


	/**
	 * 反射获取对象字段数据
	 * @param dscFieldFDto
	 * @param sourceMap
	 * @return
	 */
	 default Object getVlue(DscFieldFDto dscFieldFDto,Map<String, Object> sourceMap){
		 Object returnValue = null;
		 try {
			 Object object = sourceMap.get(dscFieldFDto.getFieldSource());
			 Class sourceClass = Class.forName(dscFieldFDto.getFieldSource());
			 Method m = sourceClass.getMethod(dscFieldFDto.getFieldGetMethod());
			 returnValue = m.invoke(object);
		 } catch (Exception e) {
			logger.error("反射获取对象字段数据",e);
		 }
		 return  returnValue;
	 }

}
