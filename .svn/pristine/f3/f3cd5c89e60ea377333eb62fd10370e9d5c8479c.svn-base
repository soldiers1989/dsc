package com.yixin.dsc.assembler;

import com.yixin.common.system.util.BaseAssembler;
import com.yixin.dsc.dto.order.DscSalesApplyContactDTO;
import com.yixin.dsc.entity.order.DscSalesApplyContact;

/**
 * 转换类
 * Package : com.yixin.dsc.assembler
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月6日 下午3:22:03
 *
 */
public class DscSalesApplyContactAssembler extends BaseAssembler{

	
	public static DscSalesApplyContact dto2Entity(DscSalesApplyContactDTO dscSalesApplyContactDTO) {
		if(dscSalesApplyContactDTO==null) {
			return null;
		}
		DscSalesApplyContact result = new DscSalesApplyContact();
		mapObjWithoutNull(dscSalesApplyContactDTO, result);
		result.setId(null);
		return result;
	}
	
	
	

	public static DscSalesApplyContactDTO entity2Dto(DscSalesApplyContact dscSalesApplyContact) {
		if(dscSalesApplyContact==null) {
			return null;
		}
		DscSalesApplyContactDTO result = new DscSalesApplyContactDTO();
		mapObj(dscSalesApplyContact, result);
		return result;
	}
	
}
