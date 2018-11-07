package com.yixin.dsc.assembler;

import com.yixin.common.system.util.BaseAssembler;
import com.yixin.dsc.dto.own.RuleCapitalRelationDto;
import com.yixin.dsc.dto.own.RuleMaintainFieldDto;
import com.yixin.dsc.dto.own.RuleMaintainResultDto;
import com.yixin.dsc.dto.own.RuleMaintainRuleDetailDto;
import com.yixin.dsc.entity.capital.DscCapitalRuleRelation;
import com.yixin.dsc.entity.field.DscField;
import com.yixin.dsc.entity.rule.DscRule;
import com.yixin.dsc.entity.rule.DscRuleDetail;
import com.yixin.dsc.enumpackage.DscIsValidEnum;
import com.yixin.dsc.enumpackage.DscRuleTypeEnum;
import com.yixin.dsc.enumpackage.RuleEvalEnum;
import com.yixin.kepler.enity.AssetFinanceInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YixinCapital -- chenjiacheng
 *         2018年08月06日 16:17
 **/

public class DscRuleMaintainAssembler extends BaseAssembler {



	public static List<RuleMaintainResultDto> toRuleList(List<Object[]> list) {
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<RuleMaintainResultDto> resultDtoList = new ArrayList<>();
		for (Object[] obj : list) {
			RuleMaintainResultDto resultDto = new RuleMaintainResultDto();
			resultDto.setRuleId(obj[0] == null ? null : obj[0].toString());
			resultDto.setRuleName(obj[1] == null ? null : obj[1].toString());
			resultDto.setRuleType(obj[2] == null ? null : DscRuleTypeEnum.getNameByType(obj[2].toString()));
			resultDto.setMatchType(obj[3] == null ? null : RuleEvalEnum.getNameByType(obj[3].toString()));
			resultDto.setCapticalName(obj[4] == null ? null : obj[4].toString());
			resultDto.setIsValid(obj[5] == null ? null : DscIsValidEnum.getNameByType("true".equalsIgnoreCase(obj[5].toString()) ? "1" : "0"));
			resultDto.setCapticalNums(obj[6] == null ? null : Integer.parseInt(obj[6].toString()));
			resultDto.setDetailNums(obj[7] == null ? null : Integer.parseInt(obj[7].toString()));

			resultDtoList.add(resultDto);
		}
		return resultDtoList;
	}

	public static List<RuleCapitalRelationDto> toCapitalList(List<DscCapitalRuleRelation> list) {
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<RuleCapitalRelationDto> dataList = new ArrayList<>();
		for (DscCapitalRuleRelation relation : list) {
			RuleCapitalRelationDto relationDto = new RuleCapitalRelationDto();
			AssetFinanceInfo assetFinanceInfo = AssetFinanceInfo.getById(relation.getCapitalId());
			if (assetFinanceInfo != null && StringUtils.isNotBlank(assetFinanceInfo.getCode())) {
				relationDto.setCapitalId(relation.getCapitalId());
				relationDto.setCapitalCode(assetFinanceInfo.getCode());
				relationDto.setCreateTime(relation.getCreateTime() == null ? null : new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(relation.getCreateTime()));
				dataList.add(relationDto);
			}
		}

		return dataList;
	}

	public static List<RuleMaintainFieldDto> toFieldList(List<Object[]> list) {
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<RuleMaintainFieldDto> dataList = new ArrayList<>();
		for (Object[] obj : list) {
			RuleMaintainFieldDto fieldDto = new RuleMaintainFieldDto();
			fieldDto.setFieldId(obj[0] == null ? null : obj[0].toString());
			fieldDto.setFieldCode(obj[1] == null ? null : obj[1].toString());
			fieldDto.setFieldName(obj[2] == null ? null : obj[2].toString());
			fieldDto.setFieldGetMethod(obj[3] == null ? null : obj[3].toString());
			fieldDto.setFieldType(obj[4] == null ? null : obj[4].toString());
			fieldDto.setFieldSource(obj[5] == null ? null : obj[5].toString());
			fieldDto.setSequence(obj[6] == null ? null : obj[6].toString());
			fieldDto.setRelationId(obj[7] == null ? null : obj[7].toString());
			dataList.add(fieldDto);
		}
		return dataList;
	}


	public static List<RuleMaintainFieldDto> toFieldList2(List<DscField> list) {
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<RuleMaintainFieldDto> dataList = new ArrayList<>();
		for (DscField obj : list) {
			RuleMaintainFieldDto fieldDto = new RuleMaintainFieldDto();
			fieldDto.setFieldId(obj.getId());
			fieldDto.setFieldCode(obj.getFieldCode());
			fieldDto.setFieldName(obj.getFieldName());
			fieldDto.setFieldGetMethod(obj.getFieldGetMethod());
			fieldDto.setFieldType(obj.getFieldType());
			fieldDto.setFieldSource(obj.getFieldSource());

			dataList.add(fieldDto);
		}
		return dataList;
	}

	public static List<RuleMaintainRuleDetailDto> toRuleDetailList(List<DscRuleDetail> list) {
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<RuleMaintainRuleDetailDto> dataList = new ArrayList<>();
		for (DscRuleDetail detail : list) {
			RuleMaintainRuleDetailDto dto = new RuleMaintainRuleDetailDto();
			mapObj(detail,dto);
			dataList.add(dto);
		}
		return dataList;
	}

	public static RuleMaintainResultDto toRuleDto(DscRule rule) {
		if (rule == null) {
			return null;
		}
		RuleMaintainResultDto resultDto = new RuleMaintainResultDto();
		resultDto.setRuleName(rule.getRuleName());
		resultDto.setRuleType(rule.getRuleType());
		resultDto.setMatchType(rule.getRuleMatchingType());
		resultDto.setRuleId(rule.getId());
		resultDto.setIsValid(rule.isDeleted()?"1":"0");
		return resultDto;
	}
}
