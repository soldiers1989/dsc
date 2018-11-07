package com.yixin.kepler.v1.service.impl.common;

import com.yixin.common.exception.BzException;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.v1.service.capital.yntrust.DscCommonService;

import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author YixinCapital -- chenjiacheng
 *         2018年09月19日 17:48
 **/
@Service("dscCommonService")
public class DscCommonServiceImpl implements DscCommonService {




	@Override
	public BigDecimal convertBigDecimal(BigDecimal source, String type) {
		if (source == null) throw new BzException("数据为空");
		switch (type) {
			case CommonConstant.TO_POIOT_TWO:
				return source.setScale(2, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal("100"),BigDecimal.ROUND_HALF_UP);
			case CommonConstant.TO_POIOT_FOUR:
				return source.setScale(4, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal("100"),BigDecimal.ROUND_HALF_UP);
		}

		return source.setScale(4, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal("100"),BigDecimal.ROUND_HALF_UP);
	}
}
