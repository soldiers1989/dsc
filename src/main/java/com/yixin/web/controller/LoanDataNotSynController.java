package com.yixin.web.controller;

import com.yixin.common.utils.InvokeResult;
import com.yixin.common.utils.Page;
import com.yixin.web.service.AssetMainInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * 贷后资料未同步银行列表
 * @author YixinCapital -- zongzhiping
 *         2018年10月17日14:05:50
 **/

@RestController
@RequestMapping(value = "/loanDataNotSyn")
public class LoanDataNotSynController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private AssetMainInfoService assetMainInfoService;

    /**
     *
     * 查询请款成功,但是贷后资料未同步银行的信息列表
     *
     * //@param financialCode 资方code
     * @param applyNo 申请编号
     * @param currentPage 当前页码
     * @return
     * @author YixinCapital -- zongzhiping
     *         2018年10月17日14:05:50
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public InvokeResult<Page<List<Map<String, Object>>>> getLoanDataNotSynList(
            @RequestParam(value = "applyNo", required = false) String applyNo,
            @RequestParam(value = "financialId", required = false) String financialId,
            @RequestParam(value = "currentPage", defaultValue = "1", required = false) String currentPage) {
        InvokeResult<Page<List<Map<String, Object>>>> invokeResult = new InvokeResult<>();
        Page<List<Map<String, Object>>> resultData = assetMainInfoService.getLoanDataNotSynList(applyNo, financialId, currentPage);
        invokeResult.setData(resultData);
        return invokeResult;
    }
}
