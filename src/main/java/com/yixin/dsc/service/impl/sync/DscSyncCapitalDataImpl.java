package com.yixin.dsc.service.impl.sync;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yixin.dsc.dto.DscCapitalDto;
import com.yixin.dsc.service.sync.DscSyncCapitalDataService;
import com.yixin.kepler.enity.AssetFieldRule;

/**
 * 同步资管数据接口实现
 * Package : com.yixin.dsc.service.impl.sync
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月13日 下午4:37:07
 *
 */
@Service("dscSyncCapitalDataService")
public class DscSyncCapitalDataImpl implements DscSyncCapitalDataService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DscSyncCapitalDataImpl.class);
	
	/**
	 * 规则同步
	 * @param capitalDto 入参如果有资管ID 按照指定资管同步，否则同步全量
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年6月13日 下午4:36:17
	 */
	@Override
	public Boolean syncAccessRules(DscCapitalDto capitalDto) {
		List<String> financialIds = Lists.newArrayList();
		//入参如果有资管ID 按照指定资管同步，否则同步全量
		if(capitalDto != null && StringUtils.isNotBlank(capitalDto.getCapitalId())){
			financialIds.add(capitalDto.getCapitalId());
		} else {
			financialIds= AssetFieldRule.getAssetFieldFinancialIds();
		}
		if(CollectionUtils.isNotEmpty(financialIds)){
			for(String financialId:financialIds){
				
			}
		}
		return null;
	}

}
