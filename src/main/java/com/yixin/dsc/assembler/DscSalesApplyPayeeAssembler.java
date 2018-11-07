package com.yixin.dsc.assembler;

import com.yixin.common.system.util.BaseAssembler;
import com.yixin.dsc.dto.order.DscSalesApplyPayeeDTO;
import com.yixin.dsc.entity.order.DscSalesApplyPayee;

/**
 * 转换类
 * Package : com.yixin.dsc.assembler
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月6日 下午3:22:03
 *
 */
public class DscSalesApplyPayeeAssembler extends BaseAssembler{

	
	public static DscSalesApplyPayee dto2Entity(DscSalesApplyPayeeDTO dscSalesApplyPayeeDTO) {
		if(dscSalesApplyPayeeDTO==null) {
			return null;
		}
		DscSalesApplyPayee result = new DscSalesApplyPayee();
		mapObjWithoutNull(dscSalesApplyPayeeDTO, result);
		result.setId(null);
		return result;
	}
	
	
	

	public static DscSalesApplyPayee entity2Dto(DscSalesApplyPayee dscSalesApplyPayee) {
		if(dscSalesApplyPayee==null) {
			return null;
		}
		DscSalesApplyPayee result = new DscSalesApplyPayee();
		mapObj(dscSalesApplyPayee, result);
		return result;
	}
	
}
