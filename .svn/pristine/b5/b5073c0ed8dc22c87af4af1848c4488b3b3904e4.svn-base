package com.yixin.dsc.service.impl.shunt;

import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.enity.AssetFinanceInfo;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import com.yixin.dsc.dto.DscCapitalDto;
import com.yixin.dsc.service.shunt.DscCapitalComparator;

/**
 * 资方排序实现
 * Package : com.yixin.dsc.service.impl.shunt
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月8日 下午5:58:37
 *
 */
@Service("dscCapitalComparator")
public class DscCapitalComparatorImpl implements DscCapitalComparator<DscCapitalDto> {
	/**
	 * 根据业务要求，资方是CMBC的
	 * @param o1
	 * @param o2
	 * @return
	 */
	@Override
	public int compare(DscCapitalDto o1, DscCapitalDto o2) {
		//根据资方ID查询银行
		AssetFinanceInfo info = AssetFinanceInfo.getById(o1.getCapitalId());
		//根据资方ID查询银行
		AssetFinanceInfo info1 = AssetFinanceInfo.getById(o2.getCapitalId());
		if(StringUtils.equals(info.getCode(), CommonConstant.BankName.CMBC)){
			return -1;
		}
		if(!StringUtils.equals(info.getCode(), CommonConstant.BankName.CMBC)){
			return 1;
		}
		if(StringUtils.equals(info1.getCode(), CommonConstant.BankName.CMBC)){
			return -1;
		}
		if(!StringUtils.equals(info1.getCode(), CommonConstant.BankName.CMBC)){
			return 1;
		}
		return 0;
	}



}
