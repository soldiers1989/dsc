package com.yixin.web.controller;

import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.dto.match.DscMatchData2AlixDto;
import com.yixin.dsc.dto.match.DscOrderTrackDto;
import com.yixin.dsc.dto.match.DscRuleMatchRecordDto;
import com.yixin.dsc.dto.query.DscQueryDto;
import com.yixin.dsc.service.match.MatchRecordService;

/**
 * @author sukang
 */
@RestController
@RequestMapping(value = "/sys/match")
public class MatchRecordController {

    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    @Resource
    private MatchRecordService matchRecordService;

    /**
     * 准入记录
     * 
     * @param applyNo
     * @return 
     * @author YixinCapital -- wangxulong
     *	       2018年9月25日 下午4:24:26
     */
    @RequestMapping(value = "/getMatchListByApplyNo")
    public InvokeResult<List<DscOrderTrackDto>> getMatchListByApplyNo(String applyNo){

        InvokeResult<List<DscOrderTrackDto>> invokeResult = new InvokeResult<>();
        List<DscOrderTrackDto> result = matchRecordService.getMatchListByApplyNo(applyNo);

        invokeResult.setData(result);
        return invokeResult;
    }
    /**
     * 资方 准入明细
     * 
     * @param dscRuleMatchRecordDto
     * @return 
     * @author YixinCapital -- wangxulong
     *	       2018年9月25日 下午4:24:37
     */
    @RequestMapping(value = "/getDistinctCapitalListByApplyNo")
    public InvokeResult<List<DscRuleMatchRecordDto>> getDistinctCapitalListByApplyNo(DscRuleMatchRecordDto dscRuleMatchRecordDto){
    	
    	InvokeResult<List<DscRuleMatchRecordDto>> invokeResult = new InvokeResult<>();
    	List<DscRuleMatchRecordDto> result = matchRecordService.getDistinctCapitalListByApplyNo(dscRuleMatchRecordDto.getApplyNo(),dscRuleMatchRecordDto.getMatchTrackId());
    	
    	invokeResult.setData(result);
    	return invokeResult;
    }
    
    /**
     * 规则准入明细
     * 
     * @param dscRuleMatchRecordDto
     * @return 
     * @author YixinCapital -- wangxulong
     *	       2018年9月25日 下午4:25:02
     */
    @RequestMapping(value = "/getDscRuleMatchRecordListByApplyNo")
    public InvokeResult<List<DscRuleMatchRecordDto>> getDscRuleMatchRecordListByApplyNo(DscRuleMatchRecordDto dscRuleMatchRecordDto){
    	InvokeResult<List<DscRuleMatchRecordDto>> invokeResult = new InvokeResult<>();
    	List<DscRuleMatchRecordDto> result = matchRecordService.getDscRuleMatchRecordListByApplyNo(dscRuleMatchRecordDto.getApplyNo(),dscRuleMatchRecordDto.getMatchTrackId(),dscRuleMatchRecordDto.getCapitalId());
    	invokeResult.setData(result);
    	return invokeResult;
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
    public InvokeResult<DscMatchData2AlixDto> findMatchDataByApplyNo(DscQueryDto dto){
    	InvokeResult<DscMatchData2AlixDto> result = new InvokeResult<>();
        result.setData(matchRecordService.findMatchDataByApplyNo(dto.getApplyNo()));
        return result;
    }
    
    

}
