package com.yixin.dsc.assembler;

import java.util.List;

import org.assertj.core.util.Lists;

import com.yixin.common.system.util.BaseAssembler;
import com.yixin.dsc.dto.field.DscFieldFDto;
import com.yixin.dsc.dto.rule.DscRuleDetailDto;
import com.yixin.dsc.dto.rule.DscRuleDto;
import com.yixin.dsc.entity.field.DscField;
import com.yixin.dsc.entity.rule.DscRule;
import com.yixin.dsc.entity.rule.DscRuleDetail;

/**
 * 规则装配类
 * Package : com.yixin.dsc.assembler
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月8日 下午4:40:44
 *
 */
public class DscRuleAssembler extends BaseAssembler {

	public static List<DscRuleDto> assemblerDtoByRuleDetail(DscRule dscRule,List<DscRuleDetail> ruleDetailList,List<DscField> fieldList) {
		List<DscRuleDto> ruleDtoList = Lists.newArrayList();
		
		List<DscFieldFDto> dscFieldFDtoList = Lists.newArrayList();
		DscFieldFDto filedDto = null;
		for(DscField field : fieldList){
			filedDto = new DscFieldFDto();
			filedDto.setFieldCode(field.getFieldCode()); //字段code
			filedDto.setFieldGetMethod(field.getFieldGetMethod()); //字段获取方法
			filedDto.setFieldName(field.getFieldName()); //字段名称
			filedDto.setFieldSource(field.getFieldSource()); //字段来源
			filedDto.setFieldType(field.getFieldType()); //字段类型
			dscFieldFDtoList.add(filedDto);
		}
		
		DscRuleDto ruleDto = null;
		for(DscRuleDetail detail:ruleDetailList){
			ruleDto = new DscRuleDto();
			ruleDto.setRuleName(dscRule.getRuleName()); //规则名称
			ruleDto.setRuleType(dscRule.getRuleType()); //规则类型
			ruleDto.setRuleMatchingType(dscRule.getRuleMatchingType()); //规则匹配类型

			// ============ 规则明细 ==============
			DscRuleDetailDto dscRuleDetailDto = new DscRuleDetailDto();
			dscRuleDetailDto.setRuleId(detail.getRuleId()); //规则ID
			dscRuleDetailDto.setRuleDetailId(detail.getId()); //规则明细ID
			dscRuleDetailDto.setRuleFormula(detail.getRuleFormula()); //规则公式定义
			dscRuleDetailDto.setMessage(detail.getMessage()); //未匹配提示
			ruleDto.setDscRuleDetailDto(dscRuleDetailDto); 
			
			ruleDto.setDscFieldFDtoList(dscFieldFDtoList);
			
			ruleDtoList.add(ruleDto);
		}
		return ruleDtoList;
	}
	
	public static DscRuleDto assemblerRuleDTO(DscRule dscRule,List<DscRuleDetail> ruleDetailList,List<DscField> fieldList) {
		DscRuleDto ruleDto = new DscRuleDto();
		ruleDto.setRuleName(dscRule.getRuleName()); //规则名称
		ruleDto.setRuleType(dscRule.getRuleType()); //规则类型
		ruleDto.setRuleMatchingType(dscRule.getRuleMatchingType()); //规则匹配类型
		
		//-------------- 装配字段开始 ----------------------------------
		List<DscFieldFDto> dscFieldFDtoList = Lists.newArrayList();
		DscFieldFDto filedDto = null;
		for(DscField field : fieldList){
			filedDto = new DscFieldFDto();
			filedDto.setFieldCode(field.getFieldCode()); //字段code
			filedDto.setFieldGetMethod(field.getFieldGetMethod()); //字段获取方法
			filedDto.setFieldName(field.getFieldName()); //字段名称
			filedDto.setFieldSource(field.getFieldSource()); //字段来源
			filedDto.setFieldType(field.getFieldType()); //字段类型
			dscFieldFDtoList.add(filedDto);
		}
		ruleDto.setDscFieldFDtoList(dscFieldFDtoList);
		//-------------- 装配字段结束 ----------------------------------
		//-------------- 装配明细开始 ----------------------------------
		List<DscRuleDetailDto> dscRuleDetailDtoList = Lists.newArrayList();
		DscRuleDetailDto dscRuleDetailDto = null;
		for(DscRuleDetail detail:ruleDetailList){
			dscRuleDetailDto = new DscRuleDetailDto();
			dscRuleDetailDto.setRuleId(detail.getRuleId()); //规则ID
			dscRuleDetailDto.setRuleDetailId(detail.getId()); //规则明细ID
			dscRuleDetailDto.setRuleFormula(detail.getRuleFormula()); //规则公式定义
			dscRuleDetailDto.setMessage(detail.getMessage()); //未匹配提示
			
			dscRuleDetailDtoList.add(dscRuleDetailDto);
		}
		ruleDto.setDscRuleDetailDtoList(dscRuleDetailDtoList);
		//-------------- 装配明细结束 ----------------------------------
		return ruleDto;
	}
}
