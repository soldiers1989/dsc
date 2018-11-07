package com.yixin.web.controller;

import com.yixin.common.utils.InvokeResult;
import com.yixin.common.utils.Page;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.web.service.AssetMainInfoService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 阶段结果查询
 * @author YixinCapital -- zongzhiping
 *         2018年10月18日18:04:18
 **/

@RestController
@RequestMapping(value = "/queryResults")
public class QueryResultsController {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private AssetMainInfoService assetMainInfoService;


    /**
     * 获取所有阶段
     *
     * @return
     * @author YixinCapital -- zongzhiping
     *         2018年10月18日18:06:16
     */
    @RequestMapping(value = "/getAllphase", method = RequestMethod.POST)
    public InvokeResult<List<Map<String, Object>>> getAllphase() {
        InvokeResult<List<Map<String, Object>>> invokeResult = new InvokeResult<>();
        try {
            List<Map<String,Object>> result = new ArrayList<>();
            for (BankPhaseEnum bankPhaseEnum : BankPhaseEnum.values()) {
                    Map<String,Object> map = new HashMap<>(2);
                    map.put("phase",bankPhaseEnum.getPhase());
                    map.put("name",bankPhaseEnum.getName());
                result.add(map);
            }
            invokeResult.setData(result);
        } catch (Exception e) {
            logger.info("获取所有阶段queryResults/getAllphase出错{}",e);
        }
        return invokeResult;
    }

    /**
     * 阶段结果查询
     * @param applyNo
     * @param phase
     * @param currentPage
     * @return
     * @author YixinCapital -- zongzhiping
     *         2018年10月18日18:04:21
     */
    @RequestMapping(value = "/list", method = RequestMethod.POST)
    public InvokeResult<Page<List<Map<String, Object>>>> getQueryResultsList(
            @RequestParam(value = "applyNo", required = false) String applyNo,
            @RequestParam(value = " phase", required = false) String phase,
            @RequestParam(value = "currentPage", defaultValue = "1", required = false) String currentPage) {
        InvokeResult<Page<List<Map<String, Object>>>> invokeResult = new InvokeResult<>();
        phase= "trial";// 暂时查询微众信审,先写死
        Page<List<Map<String, Object>>> resultData = assetMainInfoService.getPhaseQueryResultsList(applyNo, phase, currentPage);
        invokeResult.setData(resultData);
        return invokeResult;
    }
}
