package com.yixin.dsc.assembler;

import com.yixin.common.system.util.BaseAssembler;
import com.yixin.dsc.dto.order.DscSalesInsureInfoDTO;
import com.yixin.dsc.entity.order.DscSalesInsureInfo;

/**
 * 转换类
 * Package : com.yixin.dsc.assembler
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月6日 下午3:22:03
 *
 */
public class DscSalesInsureInfoAssembler extends BaseAssembler{

	
	public static DscSalesInsureInfo dto2Entity(DscSalesInsureInfoDTO dscSalesInsureInfoDTO) {
		if(dscSalesInsureInfoDTO==null) {
			return null;
		}
		DscSalesInsureInfo result = new DscSalesInsureInfo();
		mapObjWithoutNull(dscSalesInsureInfoDTO, result);
		result.setId(null);
		return result;
	}
	
	
	

	public static DscSalesInsureInfoDTO entity2Dto(DscSalesInsureInfo dscSalesInsureInfo) {
		if(dscSalesInsureInfo==null) {
			return null;
		}
		DscSalesInsureInfoDTO result = new DscSalesInsureInfoDTO();
		mapObj(dscSalesInsureInfo, result);
		return result;
	}
	
}
