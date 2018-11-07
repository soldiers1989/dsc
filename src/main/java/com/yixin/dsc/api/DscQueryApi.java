package com.yixin.dsc.api;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yixin.common.exception.BzException;
import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.dto.DscAdmittanceDto;
import com.yixin.dsc.dto.DscCapitalSupportDto;
import com.yixin.dsc.dto.match.DscMatchData2AlixDto;
import com.yixin.dsc.dto.query.DscBankInteractiveResultDto;
import com.yixin.dsc.dto.query.DscFundsQueryDto;
import com.yixin.dsc.dto.query.DscQueryDto;
import com.yixin.dsc.service.common.DscQueryService;
import com.yixin.dsc.service.common.DscSettleCommonService;
import com.yixin.dsc.service.match.MatchRecordService;
import com.yixin.web.anocation.InterfaceMonitor;

/**
 * 查询API
 * Package : com.yixin.dsc.api
 *
 * @author YixinCapital -- wangwenlong
 *         2018年7月5日 上午11:10:24
 */
@RestController
@RequestMapping(value = "/api/dscQueryApi")
public class DscQueryApi {

    private static final Logger LOGGER = LoggerFactory.getLogger(DscQueryApi.class);

    @Resource
    private DscQueryService dscQueryService;
    @Resource
    private DscSettleCommonService dscSettleCommonService;
    @Resource
    private MatchRecordService matchRecordService;

    /**
     * 获取资方支持的数据信息
     *
     * @param queryParam
     * @return
     * @author YixinCapital -- wangwenlong
     * 2018年7月5日 下午12:00:41
     */
    @InterfaceMonitor(errorMsg = "查询资方支持的数据信息失败", keyParam = {"applyNo::0_applyNo", "dataType::0_dataType"})
    @RequestMapping("/getCapitalSupportData")
    public InvokeResult<DscCapitalSupportDto> getCapitalSupportData(@RequestBody DscAdmittanceDto queryParam) {
        InvokeResult<DscCapitalSupportDto> result = new InvokeResult<>();
        if (queryParam == null) {
            return result.failure("参数为空，请确认");
        }
        if (StringUtils.isBlank(queryParam.getDataType())) {
            return result.failure("数据类型为空，请确认");
        }
        LOGGER.info("获取资方支持的数据信息开始,params={}", JSON.toJSONString(queryParam));
        try {
            result.setData(dscQueryService.getCapitalSupportData(queryParam));
        } catch (Exception e) {
            LOGGER.error("查询资方支持的数据信息出错,applyNo={}", queryParam.getApplyNo(), e);
            result.failure("查询资方支持的数据信息出错");
        }
        LOGGER.info("获取资方支持的数据信息结束,result={}", JSON.toJSONString(result));
        return result;
    }


    /**
     * 银行放款状态查询
     *
     * @param queryParam
     * @return
     * @author YixinCapital -- wangwenlong
     * 2018年7月30日 下午1:28:19
     */
    @InterfaceMonitor(errorMsg = "银行放款状态查询失败", keyParam = {"applyNo::0_applyNo"})
    @RequestMapping("/loanStatusQuery")
    public InvokeResult<Object> loanStatusQuery(@RequestBody DscQueryDto queryDto) {
        LOGGER.info("DscQueryApi银行放款状态查询,入参:{}", JSON.toJSONString(queryDto));
        InvokeResult<Object> result = new InvokeResult<>();
        if (queryDto == null || StringUtils.isBlank(queryDto.getApplyNo())) {
            return result.failure("申请编号为空");
        }
        try {
            result.setData(dscQueryService.loanStatusQuery(queryDto.getApplyNo()));
        } catch (BzException e) {
            LOGGER.error("DscQueryApi银行放款状态查询失败,订单编号:{},错误信息:{}", queryDto.getApplyNo(), e.getMessage());
            return result.failure(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("DscQueryApi银行放款状态查询失败,订单编号:{},", queryDto.getApplyNo(), e);
            return result.failure("银行放款状态查询失败");
        }
        return result;
    }

    /**
     * 查询订单银行交互信息
     *
     * @param queryDto
     * @return
     * @author YixinCapital -- wangwenlong
     * 2018年7月31日 下午1:38:12
     */
    @InterfaceMonitor(errorMsg = "查询订单银行交互信息失败", keyParam = {"applyNo::0_applyNo"})
    @RequestMapping("/queryBankInteractive")
    public InvokeResult<DscBankInteractiveResultDto> queryBankInteractive(@RequestBody DscQueryDto queryDto) {
        LOGGER.info("DscQueryApi查询订单银行交互信息,入参:{}", JSON.toJSONString(queryDto));
        InvokeResult<DscBankInteractiveResultDto> result = new InvokeResult<>();
        if (queryDto == null || StringUtils.isBlank(queryDto.getApplyNo())) {
            return result.failure("申请编号为空");
        }
        try {
            result.setData(dscQueryService.queryBankInteractive(queryDto.getApplyNo(), queryDto.getPhaseType()));
        } catch (BzException e) {
            LOGGER.error("DscQueryApi查询订单银行交互信息失败,订单编号:{},错误信息:{}", queryDto.getApplyNo(), e.getMessage());
            return result.failure(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("DscQueryApi查询订单银行交互信息失败,订单编号:{},", queryDto.getApplyNo(), e);
            return result.failure("查询订单银行交互信息失败");
        }
        return result;
    }


    /**
     * 查询银行扣补息款、退回补息款、分润信息
     * 目前只是微众银行
     *
     * @param queryParam
     * @return
     */
    @InterfaceMonitor(errorMsg = "查询银行款项信息失败", keyParam = {"applyNo::0_applyNo", "tranType::0_tranType", "tranDate::0_tranDate"})
    @RequestMapping("/queryBankFunds")
    public InvokeResult queryBankFunds(@RequestBody DscFundsQueryDto queryParam) {
        LOGGER.info("DscQueryApi查询扣补息款、退回补息款、分润信息,入参:{}", JSON.toJSONString(queryParam));
        InvokeResult result = new InvokeResult<>();

        try {
            result = dscSettleCommonService.queryBankFunds(queryParam);
        } catch (BzException e) {
            LOGGER.error("DscQueryApi查询扣补息款、退回补息款、分润信息失败,错误信息:{}", e.getMessage());
            return result.failure(e.getMessage());
        } catch (Exception e) {
            LOGGER.error("DscQueryApi查询扣补息款、退回补息款、分润信息失败", e);
            return result.failure("查询银行款项信息失败");
        }
        return result;
    }

    /**
     * 获取venus业务订单号对应的申请编号
     *
     * @param queryParam
     * @return
     * @author YixinCapital -- gumanxue
     * 2018年9月25日 下午12:00:41
     */
    @InterfaceMonitor(errorMsg = "查询venus业务订单号对应的申请编号出错", keyParam = {"applyNo::0"})
    @RequestMapping("/getVenusNoByApplyNo")
    public InvokeResult<String> getVenusNoByApplyNo(@RequestBody String applyNo) {
        InvokeResult<String> result = new InvokeResult<>();
        if (applyNo == null || StringUtils.isBlank(applyNo)) {
            return result.failure("参数为空，请确认");
        }
        LOGGER.info("获取venus业务订单号对应的申请编号开始,params={}", applyNo);
        try {
            result.setData(dscQueryService.getVenusNoByApplyNo(applyNo));
        } catch (Exception e) {
            LOGGER.error("查询venus业务订单号对应的申请编号出错,applyNo={}", applyNo, e.getMessage());
            result.failure("查询venus业务订单号对应的申请编号出错");
        }
        LOGGER.info("获取venus业务订单号对应的申请编号结束,result={}", JSON.toJSONString(result));
        return result;
    }
    
    
    
    
    /**
     * 根据订单编号查询 订单准入不匹配的 规则匹配结果
     * 
     * @param applyNo
     * @return 
     * @author YixinCapital -- wangxulong
     *	       2018年10月26日 上午10:18:39
     */
    @RequestMapping(value = "/findMatchDataByApplyNo")
    public InvokeResult<DscMatchData2AlixDto> findMatchDataByApplyNo(@RequestBody DscQueryDto dto){
        InvokeResult<DscMatchData2AlixDto> result = new InvokeResult<>();
        result.setData(matchRecordService.findMatchDataByApplyNo(dto.getApplyNo()));
        return result;
    }

}
