package com.yixin.dsc.service.sync;

import com.yixin.dsc.dto.DscCapitalDto;

/**
 * 同步资管数据接口
 * Package : com.yixin.dsc.service.sync
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月13日 下午4:35:28
 *
 */
public interface DscSyncCapitalDataService {

	/**
	 * 规则同步
	 * @param capitalDto 入参如果有资管ID 按照指定资管同步，否则同步全量
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年6月13日 下午4:36:17
	 */
	Boolean syncAccessRules(DscCapitalDto capitalDto);
	
}
