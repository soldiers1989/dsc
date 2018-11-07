package com.yixin.dsc.assembler.match;

import java.util.ArrayList;
import java.util.List;

import com.yixin.common.system.util.BaseAssembler;
import com.yixin.dsc.dto.match.DscOrderTrackDto;
import com.yixin.dsc.entity.kepler.DscOrderTrack;

/**
 * 准入记录
 * Package : com.yixin.web.assembler.kepler
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年9月25日 下午3:08:37
 *
 */
public class DscOrderTrackAssembler extends BaseAssembler {

	
	public static DscOrderTrackDto entity2Dto(DscOrderTrack dscOrderTrack) {
		if(dscOrderTrack==null) {
			return null;
		}
		DscOrderTrackDto result = new DscOrderTrackDto();
		mapObjWithoutNull(dscOrderTrack, result);
		return result;
	}
	public static List<DscOrderTrackDto> ListEntity2Dto(List<DscOrderTrack> dscOrderTrackLis) {
		if(dscOrderTrackLis==null || dscOrderTrackLis.isEmpty()) {
			return null;
		}
		List<DscOrderTrackDto> result = new ArrayList<>();
		for (DscOrderTrack dscOrderTrack : dscOrderTrackLis) {
			result.add(entity2Dto(dscOrderTrack));
		}
		return result;
	}
	
	
	
	
	public static DscOrderTrack dto2Entity(DscOrderTrackDto dscOrderTrackDto) {
		if(dscOrderTrackDto==null) {
			return null;
		}
		DscOrderTrack result = new DscOrderTrack();
		mapObjWithoutNull(dscOrderTrackDto, result);
		return result;
	}
	public static List<DscOrderTrack> ListDto2Entity(List<DscOrderTrackDto> dscOrderTrackDtoLis) {
		if(dscOrderTrackDtoLis==null || dscOrderTrackDtoLis.isEmpty()) {
			return null;
		}
		List<DscOrderTrack> result = new ArrayList<>();
		for (DscOrderTrackDto dscOrderTrackDto : dscOrderTrackDtoLis) {
			result.add(dto2Entity(dscOrderTrackDto));
		}
		return result;
	}
}
