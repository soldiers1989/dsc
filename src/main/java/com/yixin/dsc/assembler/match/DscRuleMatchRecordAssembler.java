package com.yixin.dsc.assembler.match;

import java.util.ArrayList;
import java.util.List;

import com.yixin.common.system.util.BaseAssembler;
import com.yixin.dsc.dto.match.DscRuleMatchRecordDto;
import com.yixin.dsc.entity.kepler.DscRuleMatchRecord;

/**
 * 准入明细
 * Package : com.yixin.web.assembler.kepler
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年9月25日 下午3:08:37
 *
 */
public class DscRuleMatchRecordAssembler extends BaseAssembler {

	
	public static DscRuleMatchRecordDto entity2Dto(DscRuleMatchRecord dscRuleMatchRecord) {
		if(dscRuleMatchRecord==null) {
			return null;
		}
		DscRuleMatchRecordDto result = new DscRuleMatchRecordDto();
		mapObjWithoutNull(dscRuleMatchRecord, result);
		return result;
	}
	public static List<DscRuleMatchRecordDto> ListEntity2Dto(List<DscRuleMatchRecord> dscRuleMatchRecordLis) {
		if(dscRuleMatchRecordLis==null || dscRuleMatchRecordLis.isEmpty()) {
			return null;
		}
		List<DscRuleMatchRecordDto> result = new ArrayList<>();
		for (DscRuleMatchRecord dscRuleMatchRecord : dscRuleMatchRecordLis) {
			result.add(entity2Dto(dscRuleMatchRecord));
		}
		return result;
	}
	
	
	
	
	public static DscRuleMatchRecord dto2Entity(DscRuleMatchRecordDto dscRuleMatchRecordDto) {
		if(dscRuleMatchRecordDto==null) {
			return null;
		}
		DscRuleMatchRecord result = new DscRuleMatchRecord();
		mapObjWithoutNull(dscRuleMatchRecordDto, result);
		return result;
	}
	public static List<DscRuleMatchRecord> ListDto2Entity(List<DscRuleMatchRecordDto> dscRuleMatchRecordDtoLis) {
		if(dscRuleMatchRecordDtoLis==null || dscRuleMatchRecordDtoLis.isEmpty()) {
			return null;
		}
		List<DscRuleMatchRecord> result = new ArrayList<>();
		for (DscRuleMatchRecordDto dscRuleMatchRecordDto : dscRuleMatchRecordDtoLis) {
			result.add(dto2Entity(dscRuleMatchRecordDto));
		}
		return result;
	}
}
