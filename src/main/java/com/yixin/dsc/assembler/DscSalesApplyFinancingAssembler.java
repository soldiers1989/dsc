package com.yixin.dsc.assembler;

import com.yixin.common.system.util.BaseAssembler;
import com.yixin.dsc.dto.order.DscSalesApplyFinancingDTO;
import com.yixin.dsc.entity.order.DscSalesApplyFinancing;

/**
 * 转换类
 * Package : com.yixin.dsc.assembler
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月6日 下午3:22:03
 *
 */
public class DscSalesApplyFinancingAssembler extends BaseAssembler{

	
	public static DscSalesApplyFinancing dto2Entity(DscSalesApplyFinancingDTO dscSalesApplyFinancingDTO) {
		if(dscSalesApplyFinancingDTO==null) {
			return null;
		}
		DscSalesApplyFinancing result = new DscSalesApplyFinancing();
		mapObjWithoutNull(dscSalesApplyFinancingDTO, result);
		result.setId(null);
		return result;
	}
	
	
	

	public static DscSalesApplyFinancingDTO entity2Dto(DscSalesApplyFinancing dscSalesApplyFinancing) {
		if(dscSalesApplyFinancing==null) {
			return null;
		}
		DscSalesApplyFinancingDTO result = new DscSalesApplyFinancingDTO();
		mapObj(dscSalesApplyFinancing, result);
		return result;
	}
	
}
