package com.yixin.web.controller;

import com.yixin.common.utils.InvokeResult;
import com.yixin.common.utils.Page;
import com.yixin.dsc.dto.rule.DscMatchResultDto;
import com.yixin.kepler.common.enums.AssetStateEnum;
import com.yixin.kepler.core.domain.LoanDomain;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.web.dto.AssetMainInfoDto;
import com.yixin.web.dto.ConditionBaseDto;
import com.yixin.web.service.AssetMainInfoService;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import java.util.*;

/**
 * @author sukang
 */
@RestController
@RequestMapping(value = "/sys/main-info")
public class AssetMainInfoController {


    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private AssetMainInfoService assetMainInfoService;

    @Resource
    private LoanDomain loanDomain;



    @ExceptionHandler
    @ResponseBody
    public InvokeResult<Object> exception(Exception e, HttpServletRequest request){

        logger.error("请求路径为{},程序异常",request.getRequestURL(),e);
        InvokeResult<Object> objectInvokeResult = new InvokeResult<>();
        objectInvokeResult.failure("程序异常");
        return objectInvokeResult;
    }


    @PostMapping(value = "/get-all")
    public InvokeResult<Page<Map<String, Object>>> getAssetMainList(
            @RequestBody AssetMainInfoDto assetMainInfoDto){

        InvokeResult<Page<Map<String, Object>>> invokeResult = new InvokeResult<>();

        Page<Map<String, Object>> data = assetMainInfoService.getAssetMainList(assetMainInfoDto);

        invokeResult.success().setData(data);
        return invokeResult;
    }


    @PostMapping(value = "/bank-status-info")
    public InvokeResult<JSONObject> statusInfo(){
        InvokeResult<JSONObject> invokeResult = new InvokeResult<>();
        JSONObject jsonObject = new JSONObject();
        Map<String, String> statusMap = new HashMap<>(4);
        Arrays.asList(AssetStateEnum.values()).forEach(
                t -> statusMap.put(AssetStateEnum.getNameByState(t.getState()),t.getState().toString())
        );

        jsonObject.put("statusInfo",statusMap);

        invokeResult.success().setData(jsonObject);

        return invokeResult;
    }


    @GetMapping(value = "/detail/{applyNo}")
    public InvokeResult<Object> getBankTransByApplyNo(
            @PathVariable("applyNo") String applyNo){

        InvokeResult<Object> invokeResult = new InvokeResult<>();

        if (StringUtils.isBlank(applyNo)){
            return invokeResult.failure("订单编号为空");
        }

        List<Map<String, Object>> assetMainDetail = assetMainInfoService.getAssetMainDetail(applyNo);
        invokeResult.success().setData(assetMainDetail);
        return invokeResult;
    }


    @PostMapping(value = "/attachment-info")
    public InvokeResult<Page<Map<String,Object>>> getAttachmentInfo(
            @RequestBody ConditionBaseDto conditionBaseDto){
        InvokeResult<Page<Map<String,Object>>> invokeResult = new InvokeResult<>();

        if (StringUtils.isBlank(conditionBaseDto.getApplyNo())){
            return invokeResult.failure("申请编号为空");
        }
        Page<Map<String, Object>> attachmentInfo = assetMainInfoService.getAttachmentInfo(conditionBaseDto);
        invokeResult.success().setData(attachmentInfo);
        return invokeResult;
    }


    /**
     * 订单同步记录
     */
    @PostMapping(value = "syn-log-info")
    public InvokeResult<List<Map<String,Object>>> getSynLogInfo(
            @RequestBody ConditionBaseDto conditionBaseDto){
        InvokeResult<List<Map<String,Object>>> invokeResult = new InvokeResult<>();

        if (StringUtils.isBlank(conditionBaseDto.getApplyNo())){
            return invokeResult.failure("申请编号为空");
        }
        List<Map<String, Object>> synLogInfo = assetMainInfoService.getSynLogInfo(conditionBaseDto);

        invokeResult.success().setData(synLogInfo);
        return invokeResult;
    }


    /**
     * 准入记录
     */
    @PostMapping(value = "/match-all")
    public InvokeResult<List<DscMatchResultDto>> getMatchResults(
            @RequestBody ConditionBaseDto conditionBaseDto){
        InvokeResult<List<DscMatchResultDto>> invokeResult = new InvokeResult<>();

        if (StringUtils.isBlank(conditionBaseDto.getApplyNo())){
            return invokeResult.failure("申请编号为空");
        }

        List<DscMatchResultDto> matchResults = assetMainInfoService.getMatchResults(conditionBaseDto);
        invokeResult.success().setData(matchResults);
        return invokeResult;
    }
    
    @PostMapping(value = "/table/info/{applyNo}/{tableInfo}")
    public InvokeResult<List<Object>> getMatchResults(
            @PathVariable("applyNo") String applyNo,
            @PathVariable("tableInfo") String tableInfo){
        InvokeResult<List<Object>> invokeResult = new InvokeResult<>();

        if (StringUtils.isBlank(applyNo)){
            return invokeResult.failure("申请编号为空");
        }
        if (StringUtils.isBlank(tableInfo)){
            return invokeResult.failure("表名为空");
        }
        List<Object> jsonObject = assetMainInfoService.getInfoByTable(applyNo,tableInfo);
        invokeResult.success().setData(jsonObject);
        return invokeResult;
    }
    
    

    @PostMapping(value = "/after-loan")
    public InvokeResult<List<BaseMsgDTO>> pushAfterLoan(
            @RequestParam("applyNos[]") String[] applyNos){

        InvokeResult<List<BaseMsgDTO>> result = new InvokeResult<>();
        List<BaseMsgDTO> baseMsgDTOS = new ArrayList<>();

        logger.info("贷后接口接收到的参数为{}",String.valueOf(applyNos));

        for (int i = 0,len = applyNos.length; i < len; i++) {

            InvokeResult<BaseMsgDTO> loanInfo = loanDomain.syncLoanInfo(applyNos[i]);
            baseMsgDTOS.add((BaseMsgDTO) loanInfo.getData());
        }
        result.success().setData(baseMsgDTOS);
        return result;
    }


    /**
     * 资料补传
     */

    @PostMapping(value = "/supply/attachment")
    public InvokeResult<BaseMsgDTO> supplyAttachment(
            @RequestBody ConditionBaseDto conditionBaseDto){
        InvokeResult<BaseMsgDTO> invokeResult = new InvokeResult();

        //查找时间范围所有的民生已终审的单子
        BaseMsgDTO baseMsgDTO = assetMainInfoService.supplyAttachment(conditionBaseDto);
        invokeResult.success().setData(baseMsgDTO);
        return invokeResult;
    }

    @PostMapping(value = "/supply/attachment/bank")
    public InvokeResult<List<BaseMsgDTO>> attachmentSupply(
            @RequestBody ConditionBaseDto conditionBaseDto){

        InvokeResult<List<BaseMsgDTO>> invokeResult = new InvokeResult<>();

        List<BaseMsgDTO> result = assetMainInfoService.supplyAttachmentBank(
                conditionBaseDto);

        invokeResult.success().setData(result);
        return invokeResult;
    }



}
