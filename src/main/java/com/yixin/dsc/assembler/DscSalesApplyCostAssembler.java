package com.yixin.dsc.assembler;

import com.yixin.common.system.util.BaseAssembler;
import com.yixin.dsc.dto.order.DscSalesApplyCostDTO;
import com.yixin.dsc.entity.order.DscSalesApplyCost;

/**
 * 转换类
 * Package : com.yixin.dsc.assembler
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月6日 下午3:22:03
 *
 */
public class DscSalesApplyCostAssembler extends BaseAssembler{

	
	public static DscSalesApplyCost dto2Entity(DscSalesApplyCostDTO dscSalesApplyCostDTO) {
		if(dscSalesApplyCostDTO==null) {
			return null;
		}
		DscSalesApplyCost result = new DscSalesApplyCost();
		mapObjWithoutNull(dscSalesApplyCostDTO, result);
		result.setId(null);
		return result;
	}
	
	
	

	public static DscSalesApplyCostDTO entity2Dto(DscSalesApplyCost dscSalesApplyCost) {
		if(dscSalesApplyCost==null) {
			return null;
		}
		DscSalesApplyCostDTO result = new DscSalesApplyCostDTO();
		mapObj(dscSalesApplyCost, result);
		return result;
	}
	
}
