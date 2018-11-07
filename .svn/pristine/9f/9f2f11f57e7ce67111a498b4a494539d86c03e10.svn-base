package com.yixin.dsc.assembler;

import com.yixin.common.system.util.BaseAssembler;
import com.yixin.dsc.dto.order.DscSalesApplyCustDTO;
import com.yixin.dsc.dto.order.DscSalesInsureInfoDTO;
import com.yixin.dsc.entity.order.DscSalesApplyCust;

/**
 * 转换类
 * Package : com.yixin.dsc.assembler
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月6日 下午3:22:03
 *
 */
public class DscSalesApplyCustAssembler extends BaseAssembler{

	
	public static DscSalesApplyCust dto2Entity(DscSalesApplyCustDTO dscSalesApplyCustDTO) {
		if(dscSalesApplyCustDTO==null) {
			return null;
		}
		DscSalesApplyCust result = new DscSalesApplyCust();
		mapObjWithoutNull(dscSalesApplyCustDTO, result);
		result.setId(null);
		return result;
	}
	
	
	

	public static DscSalesInsureInfoDTO entity2Dto(DscSalesApplyCust dscSalesApplyCust) {
		if(dscSalesApplyCust==null) {
			return null;
		}
		DscSalesInsureInfoDTO result = new DscSalesInsureInfoDTO();
		mapObj(dscSalesApplyCust, result);
		return result;
	}
	
}
