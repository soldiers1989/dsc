package com.yixin.kepler.v1.service.capital.yntrust;

import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.dto.DscContractSignFileDTO;

/**
 * @author sukang
 */
public interface ContractSignService {

    /**
     * 合同签署
     * @param dscContractSignFileDTO 请求数据
     * @return InvokeResult
     */
    InvokeResult<Object> contractSign(DscContractSignFileDTO dscContractSignFileDTO);

}
