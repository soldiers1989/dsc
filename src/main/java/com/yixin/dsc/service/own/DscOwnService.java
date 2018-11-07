package com.yixin.dsc.service.own;

import com.yixin.dsc.dto.own.YNTrustMaintenanceDTO;
import com.yixin.kepler.dto.BaseMsgDTO;

/**
 * 运维接口service
 * Package : com.yixin.dsc.service.own
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年10月17日 下午1:57:19
 *
 */
public interface DscOwnService {

    /**
     * 导出该资方的所有准入sql
     * @param capitalId
     * @return
     */
    String exportSql(String capitalId);

	/**
	 * 云南信托后台维护接口，主要用来人工干预的情况
	 * @param maintenanceDto
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年10月20日 下午4:15:28
	 */
	BaseMsgDTO YNTrustMaintenance(YNTrustMaintenanceDTO maintenanceDto);
}
