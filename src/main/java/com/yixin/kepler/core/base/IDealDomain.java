package com.yixin.kepler.core.base;

import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.common.exception.BzException;
import com.yixin.kepler.dto.BaseMsgDTO;

/**
 * Created by liushuai2 on 2018/6/10.
 */
public interface IDealDomain<T extends Object, B extends BaseMsgDTO> {
    /**
     * 执行任务
     * @param inputDTO
     */
    InvokeResult<B> doIt(T inputDTO) throws BzException;
    
    /**
	 * 银行请求前置
	 * @param applyNo 申请编号
	 * @param financeCode 资方编码
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年7月16日 下午7:18:17
	 */
    BaseMsgDTO requestPrepose(String applyNo,String financeCode);
}
