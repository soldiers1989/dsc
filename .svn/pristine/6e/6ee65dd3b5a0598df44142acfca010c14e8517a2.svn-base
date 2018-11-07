package com.yixin.dsc.runnable;

import java.util.List;
import java.util.Map;
import java.util.concurrent.CountDownLatch;

import com.yixin.dsc.dto.DscCapitalDto;
import com.yixin.dsc.dto.rule.DscRuleDto;
import com.yixin.dsc.dto.rule.engine.BatchMatchResult;
import com.yixin.dsc.entity.rule.DscRule;
import com.yixin.dsc.enumpackage.DscRuleTypeEnum;
import com.yixin.dsc.service.rule.engine.RuleService;

/**
 * 准入 多线程并发执行
 *
 * Created by wangxulong on 2018/7/5.
 */
public class DscShuntRunnable implements Runnable {

    private RuleService ruleService;

    CountDownLatch latch = null;

    //返回信息 List
    List<DscCapitalDto> matchCapitalDtoList;
    //资方信息
    DscCapitalDto capitalDto;
    //属性取值来源
    Map<String,Object> sourceMap;

    public DscShuntRunnable(List<DscCapitalDto> matchCapitalDtoList,DscCapitalDto capitalDto, Map<String,Object> sourceMap,CountDownLatch latch,RuleService ruleService){
        this.matchCapitalDtoList = matchCapitalDtoList;
        this.capitalDto = capitalDto;
        this.sourceMap = sourceMap;
        this.latch = latch;
        this.ruleService = ruleService;
    }


    @Override
    public void run() {

        //====== 通过资方ID获取准入规则明细
        List<DscRuleDto> ruleDtoList = DscRule.getListByCapitalId(capitalDto.getCapitalId(), DscRuleTypeEnum.SHUNT.getType());
        BatchMatchResult matchResult = ruleService.shuntRuleMatch(ruleDtoList, sourceMap,DscRuleTypeEnum.SHUNT.getType());
        if(matchResult.isFlag()){ //规则全部匹配
            matchCapitalDtoList.add(capitalDto);
        }
        latch.countDown();
    }


}
