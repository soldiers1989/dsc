package com.yixin.dsc.assembler;

import com.yixin.common.system.util.BaseAssembler;
import com.yixin.dsc.dto.order.DscSalesInsureInfoDTO;
import com.yixin.dsc.dto.order.DscSysReceivablesDTO;
import com.yixin.dsc.entity.order.DscSysReceivables;

/**
 * 转换类
 * Package : com.yixin.dsc.assembler
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月6日 下午3:22:03
 *
 */
public class DscSysReceivablesAssembler extends BaseAssembler{

	
	public static DscSysReceivables dto2Entity(DscSysReceivablesDTO dscSysReceivablesDTO) {
		if(dscSysReceivablesDTO==null) {
			return null;
		}
		DscSysReceivables result = new DscSysReceivables();
		mapObjWithoutNull(dscSysReceivablesDTO, result);
		result.setId(null);
		return result;
	}
	
	
	

	public static DscSalesInsureInfoDTO entity2Dto(DscSysReceivables dscSysReceivables) {
		if(dscSysReceivables==null) {
			return null;
		}
		DscSalesInsureInfoDTO result = new DscSalesInsureInfoDTO();
		mapObj(dscSysReceivables, result);
		return result;
	}
	
}
