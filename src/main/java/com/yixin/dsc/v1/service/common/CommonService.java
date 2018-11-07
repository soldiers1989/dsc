package com.yixin.dsc.v1.service.common;

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
     * 挡板
     * @param parameter 入参
     * @param financialCode 资方code
     * @return 挡板数据
     * @author YixinCapital -- chenjiacheng
     *             2018/9/26 14:28
     */
    String baffleCheckCommon(Object parameter, String financialCode);
}
