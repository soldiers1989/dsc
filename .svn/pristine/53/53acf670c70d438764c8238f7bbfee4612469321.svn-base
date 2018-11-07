package com.yixin.dsc.assembler;

import java.lang.reflect.Method;
import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yixin.common.system.util.BaseAssembler;
import com.yixin.dsc.dto.order.DscSalesApplyFinancingInfoDTO;
import com.yixin.dsc.entity.order.DscSalesApplyFinancing;

/**
 * 转换类
 * Package : com.yixin.dsc.assembler
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月6日 下午3:56:06
 *
 */
public class DscSalesApplyFinancingInfoAssembler extends BaseAssembler {

	private static final Logger logger = LoggerFactory.getLogger(DscSalesApplyFinancingInfoAssembler.class);
	
	/**
	 * 
	 * @param financingList
	 * @return 
	 * @author YixinCapital -- wangxulong
	 *	       2018年6月21日 上午9:55:48
	 */
	public static DscSalesApplyFinancingInfoDTO FinancingList2InfoDto(List<DscSalesApplyFinancing> financingList) {
		if(CollectionUtils.isEmpty(financingList)){
			return null;
		}
		DscSalesApplyFinancingInfoDTO infoDto = new DscSalesApplyFinancingInfoDTO();
		for(DscSalesApplyFinancing financing:financingList){
			if(StringUtils.isNotBlank(financing.getArzxmid())) {
				String code = "set"+ financing.getArzxmid();
				logger.info("准入融资明细 列表 转换  code：{}", code);
				Method method;
				try {
					method = infoDto.getClass().getDeclaredMethod(code, BigDecimal.class);
					if (method != null) {
						method.invoke(infoDto,financing.getFkhrzje());
					}
				} catch (Exception e) {
					logger.error("准入融资明细 列表 转换  异常",e);
				} 
			}
		}
		return infoDto;
	}


}
