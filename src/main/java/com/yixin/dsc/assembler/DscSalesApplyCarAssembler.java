package com.yixin.dsc.assembler;

import com.yixin.common.system.util.BaseAssembler;
import com.yixin.dsc.dto.order.DscSalesApplyCarDTO;
import com.yixin.dsc.entity.order.DscSalesApplyCar;

/**
 *  转换类
 * Package : com.yixin.dsc.assembler
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月6日 下午3:22:03
 *
 */
public class DscSalesApplyCarAssembler extends BaseAssembler{

	
	public static DscSalesApplyCar dto2Entity(DscSalesApplyCarDTO dscSalesApplyCarDTO) {
		if(dscSalesApplyCarDTO==null) {
			return null;
		}
		DscSalesApplyCar result = new DscSalesApplyCar();
		mapObjWithoutNull(dscSalesApplyCarDTO, result);
		result.setId(null);
		return result;
	}
	
	
	

	public static DscSalesApplyCarDTO entity2Dto(DscSalesApplyCar dscSalesApplyCar) {
		if(dscSalesApplyCar==null) {
			return null;
		}
		DscSalesApplyCarDTO result = new DscSalesApplyCarDTO();
		mapObj(dscSalesApplyCar, result);
		return result;
	}
	
}
