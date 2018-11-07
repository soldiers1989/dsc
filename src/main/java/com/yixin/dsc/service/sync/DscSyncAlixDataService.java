package com.yixin.dsc.service.sync;

import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.dto.DscContractSignFileDTO;
import com.yixin.dsc.dto.DscRepayScheduleDTO;
import com.yixin.dsc.dto.order.DscSyncDTO;
import com.yixin.kepler.dto.BaseMsgDTO;

import java.util.Map;

/***
 * 同步alix数据
 * 
 * Package : com.yixin.dsc.service
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月6日 下午2:31:01
 *
 */
public interface DscSyncAlixDataService {

	/**
	 * 同步alix全量数据
	 * 
	 * @param dscSyncDTO
	 * @return 
	 * @author YixinCapital -- wangxulong
	 *	       2018年6月6日 下午2:31:58
	 */
	Boolean syncAlixAllData(DscSyncDTO dscSyncDTO);
	
	
	
	/**
	 * 同步贷后资料接口
	 * @param dscSyncDTO
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年6月6日 下午3:53:22
	 */
	Boolean syncLloanData(DscSyncDTO dscSyncDTO);


	/**
	 * 同步请款附件信息  接口
	 * @param dscSyncDTO
	 * @return
	 * @author YixinCapital -- wangwenlong
	 *	       2018年6月6日 下午3:53:22
	 */
	Boolean syncRequestFundsAndAttachmentData(DscSyncDTO dscSyncDTO);

	/**
	 * 云信合同处理接口
	 * @param dscContractSignFileDTO
	 * @return
	 */
    InvokeResult<Object> dealContractFile(DscContractSignFileDTO dscContractSignFileDTO);

	/**
	 * 同步易鑫放款信息
	 * @param dscRepayScheduleDTO
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年9月26日 下午3:50:24
	 */
	BaseMsgDTO syncYXLoanInfo(DscRepayScheduleDTO dscRepayScheduleDTO);
}
