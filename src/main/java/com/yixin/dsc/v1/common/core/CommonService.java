package com.yixin.dsc.v1.common.core;

import com.yixin.dsc.dto.DscComputerParamDto;
import com.yixin.dsc.dto.DscComputerResultDto;

/**
 * Package : com.yixin.dsc_v1.service.common
 *
 * @author YixinCapital -- wanghonglin
 * 2018/9/14 9:13
 */
public interface CommonService {
    /**
     * 借据试算
     * @param paramDto
     * @return
     */
    DscComputerResultDto keplerComputer(DscComputerParamDto paramDto);

    /**
     * 挡板信息
     * @param paramDto
     * @return
     */
    String baffleCheckCommon(Object parameter, String financialCode);
}
