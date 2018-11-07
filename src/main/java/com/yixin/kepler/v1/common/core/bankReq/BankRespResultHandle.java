package com.yixin.kepler.v1.common.core.bankReq;

import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.enity.OsbFileLog;
import com.yixin.kepler.v1.datapackage.dto.BankRespResultHandleDTO;
import com.yixin.kepler.v1.datapackage.entity.AssetBankRequest;

/**
 * 银行响应结果任务处理
 * Package : com.yixin.kepler.v1.job
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年9月17日 下午5:31:21
 *
 */
public interface BankRespResultHandle {


	/**
	 * 银行响应结果处理
	 * @param resultDTO
	 * @return 结果对象
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月17日 下午5:28:55
	 */
    BaseMsgDTO respResultHandle(BankRespResultHandleDTO resultDTO);
    
    
	/**
	 * 组装osb信息，返回最后的请求json
	 * 		--适用于文件上传成功后跑批发起银行请求的业务
     * @param requestDTO
     * @param osbFileLog
     * @return 
     * @author YixinCapital -- chen.lin
     *	       2018年9月19日 上午10:44:10
     */
	public String assembleOsb(AssetBankRequest requestDTO, OsbFileLog osbFileLog);
	
	
	/**
	 * 发起请求前数据处理，默认为空
	 * @param task 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年10月8日 下午2:33:53
	 */
	default String requestBeforeHandle(AssetBankRequest task){
		return null;
	};
}
