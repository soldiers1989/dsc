package com.yixin.dsc.assembler;

import com.yixin.common.system.util.BaseAssembler;
import com.yixin.dsc.dto.order.DscSalesApplyMainDTO;
import com.yixin.dsc.entity.order.DscSalesApplyMain;

/**
 * 订单主类 转换类
 * Package : com.yixin.dsc.assembler
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月6日 下午3:22:03
 *
 */
public class DscSalesApplyMainAssembler extends BaseAssembler{

	
	public static DscSalesApplyMain dto2Entity(DscSalesApplyMainDTO dscSalesApplyMainDTO) {
		if(dscSalesApplyMainDTO==null) {
			return null;
		}
		DscSalesApplyMain result = new DscSalesApplyMain();
		mapObjWithoutNull(dscSalesApplyMainDTO, result);
		result.setId(null);
		return result;
	}
	
	
	

	public static DscSalesApplyMainDTO entity2Dto(DscSalesApplyMain dscSalesApplyMain) {
		if(dscSalesApplyMain==null) {
			return null;
		}
		DscSalesApplyMainDTO result = new DscSalesApplyMainDTO();
		mapObj(dscSalesApplyMain, result);
		return result;
	}
	
}
