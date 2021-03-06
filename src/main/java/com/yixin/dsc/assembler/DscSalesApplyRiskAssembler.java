package com.yixin.dsc.assembler;

import com.yixin.common.system.util.BaseAssembler;
import com.yixin.dsc.dto.order.DscSalesApplyFinancingInfoDTO;
import com.yixin.dsc.dto.order.DscSalesApplyRiskDTO;
import com.yixin.dsc.dto.order.DscSalesApplyRiskInfoDTO;
import com.yixin.dsc.entity.order.DscSalesApplyFinancing;
import com.yixin.dsc.entity.order.DscSalesApplyRisk;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

/**
 * 转换类
 * Package : com.yixin.dsc.assembler
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月6日 下午3:22:03
 *
 */
public class DscSalesApplyRiskAssembler extends BaseAssembler{

	private static final Logger LOGGER = LoggerFactory.getLogger(DscSalesApplyRiskAssembler.class);
	
	public static DscSalesApplyRisk dto2Entity(DscSalesApplyRiskDTO dscSalesApplyRiskDTO) {
		if(dscSalesApplyRiskDTO==null) {
			return null;
		}
		DscSalesApplyRisk result = new DscSalesApplyRisk();
		mapObjWithoutNull(dscSalesApplyRiskDTO, result);
		result.setId(null);
		return result;
	}
	
	
	

	public static DscSalesApplyRiskDTO entity2Dto(DscSalesApplyRisk dscSalesApplyRisk) {
		if(dscSalesApplyRisk==null) {
			return null;
		}
		DscSalesApplyRiskDTO result = new DscSalesApplyRiskDTO();
		mapObj(dscSalesApplyRisk, result);
		return result;
	}


	/**
	 *
	 * @param riskList
	 * @return
	 * @author YixinCapital -- xjt
	 *	       2018年10月11日 上午9:55:48
	 */
	public static DscSalesApplyRiskInfoDTO FinancingList2InfoDto(List<DscSalesApplyRisk> riskList) {
		if(CollectionUtils.isEmpty(riskList)){
			return null;
		}
		DscSalesApplyRiskInfoDTO infoDto = new DscSalesApplyRiskInfoDTO();
		for(DscSalesApplyRisk risk : riskList){
			if(StringUtils.isNotBlank(risk.getBankCode())) {
				String methodName = "setF"+risk.getBankCode();
				Method method;
				try {
					method = infoDto.getClass().getDeclaredMethod(methodName, String.class);
					if (method != null) {
						method.invoke(infoDto, risk.getRiskCode());
					}
				} catch (Exception e) {
					LOGGER.error("准入Risk 列表 转换  异常",e);
				}
			}
		}
		return infoDto;
	}
	
}
