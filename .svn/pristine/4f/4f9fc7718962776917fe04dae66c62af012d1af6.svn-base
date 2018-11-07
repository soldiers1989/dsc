package com.yixin.dsc.assembler;

import com.yixin.common.system.util.BaseAssembler;
import com.yixin.dsc.dto.order.DscSalesInsureFinancingDTO;
import com.yixin.dsc.entity.order.DscSalesInsureFinancing;

/**
 * 转换类
 * Package : com.yixin.dsc.assembler
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月6日 下午3:22:03
 *
 */
public class DscSalesInsureFinancingAssembler extends BaseAssembler{

	
	public static  DscSalesInsureFinancing dto2Entity( DscSalesInsureFinancingDTO dscSalesInsureFinancingDTO) {
		if(dscSalesInsureFinancingDTO==null) {
			return null;
		}
		DscSalesInsureFinancing result = new DscSalesInsureFinancing();
		mapObjWithoutNull(dscSalesInsureFinancingDTO, result);
		result.setId(null);
		return result;
	}
	
	
	

	public static DscSalesInsureFinancingDTO entity2Dto( DscSalesInsureFinancing  dscSalesInsureFinancing) {
		if(dscSalesInsureFinancing==null) {
			return null;
		}
		DscSalesInsureFinancingDTO result = new DscSalesInsureFinancingDTO();
		mapObj(dscSalesInsureFinancing, result);
		return result;
	}
	
}
