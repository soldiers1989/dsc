package com.yixin.dsc.service.impl.async;

import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yixin.common.exception.BzException;
import com.yixin.dsc.dto.DscFlowResultForAlixDto;
import com.yixin.dsc.dto.order.DscSyncDTO;
import com.yixin.dsc.dto.rule.DscMatchResultDto;
import com.yixin.dsc.dto.rule.engine.BatchMatchResult;
import com.yixin.dsc.dto.rule.engine.DscMatchResult;
import com.yixin.dsc.entity.kepler.DscRuleMatchRecord;
import com.yixin.dsc.service.async.AsyncTaskManagerService;
import com.yixin.dsc.v1.datapackage.dto.notice.DscAsyncMessageDto;
import com.yixin.dsc.v1.service.common.VenusRescueJobService;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.enity.AssetMainInfo;
import com.yixin.kepler.enity.AssetOrderFlow;
import com.yixin.kepler.v1.service.capital.yntrust.YTOrderCancelRequest;
import com.yixin.kepler.v1.service.capital.yntrust.YTRequestDataHandle;

/**
 * 异步任务管理 -- spring 实现
 * Package : com.yixin.dsc.service.impl.async
 *
 * @author YixinCapital -- wangwenlong
 *         2018年8月28日 下午12:57:19
 */
@Service("asyncTaskManagerSpringImpl")
public class AsyncTaskManagerSpringImpl implements AsyncTaskManagerService {

    private static final Logger LOGGER = LoggerFactory.getLogger(AsyncTaskManagerSpringImpl.class);

    @Autowired
    private MongoTemplate mongoTemplate;

    @Resource
    private YTRequestDataHandle requestDataHandle;
    
    @Resource
	private YTOrderCancelRequest ytOrderCancelRequest;
    
    @Resource
    private VenusRescueJobService venusRescueJobService;

    /**
     * 记录资方规则匹配结果
     *
     * @param matchResult  匹配规则的结果包括规则通过和不通过的
     * @param matchTrackId 匹配痕迹ID即批次
     * @param applyNo      订单编号
     * @author YixinCapital -- wangwenlong
     * 2018年8月28日 上午9:49:09
     */
    @Async
    public void recordMatchRuleList(List<BatchMatchResult> batchMatchResultList, String matchTrackId, String applyNo) {
        LOGGER.info("异步任务【spring】-记录资方规则匹配结果开始,订单编号:{},匹配痕迹ID：{}", applyNo, matchTrackId);
        DscRuleMatchRecord.recordMatchRuleList(batchMatchResultList, matchTrackId, applyNo);
        LOGGER.info("异步任务【spring】-记录资方规则匹配结果结束,订单编号:{},匹配痕迹ID：{}", applyNo, matchTrackId);
    }

    /**
     * 记录发起信审/请款的结果通知
     *
     * @param dscFlowResultForAlixDto
     * @author YixinCapital -- wangwenlong
     * 2018年9月4日 上午11:07:27
     */
    @Async
    public void recordflowResultNotice(DscFlowResultForAlixDto dscFlowResultForAlixDto) {
        LOGGER.info("异步任务【spring】-记录发起信审/请款的结果通知开始,消息:{}", JSON.toJSONString(dscFlowResultForAlixDto));
        try {
            AssetOrderFlow.recordflowResultNotice(dscFlowResultForAlixDto);
        } catch (Exception e) {
            LOGGER.error("异步任务【spring】-记录发起信审/请款的结果通知异常", e);
        }
        LOGGER.info("异步任务【spring】-记录发起信审/请款的结果通知结束,消息:{}", JSON.toJSONString(dscFlowResultForAlixDto));
    }

    /**
     * 记录订单同步信息
     *
     * @param dscSyncDTO
     * @author YixinCapital -- wangwenlong
     * 2018年9月8日 上午11:05:52
     */
    @Async
    public void recordOrderSyncInfo(DscSyncDTO dscSyncDTO, String dateStr) {
        try {
            dscSyncDTO.setSign(dateStr); //为了在mongo中，日志好查询 add by wangwenlong on 2018/07/24
            //批量同步 保存 mongo
            mongoTemplate.save(dscSyncDTO);
        } catch (Exception e) {
            LOGGER.error("订单同步记录mogo信息异常，订单编号:{}", dscSyncDTO.getApplyNo(), e);
        }
    }

    /**
     * 云南信托签约导入
     *
     * @param mainInfo
     * @author YixinCapital -- wangwenlong
     * 2018年9月27日 下午9:20:12
     */
    @Async
    public void YTImportProtocol(AssetMainInfo mainInfo) {
        //================== 签约导入 ============================
        try {
            this.requestDataHandle.importProtocol(mainInfo);
        } catch (BzException e) {
            LOGGER.error("订单编号:{},请款前置-签约导入错误，错误信息：{}", mainInfo.getApplyNo(), e.getMessage());
        } catch (Exception e) {
            LOGGER.error("订单编号:{},请款前置-签约导入异常", mainInfo.getApplyNo(), e);
        }
    }

    /**
     * 记录匹配规则到mongo中，为运维提供查询规则匹配数据
     *
     * @param batchMatchResultList
     * @param applyNo
     * @author YixinCapital -- wangwenlong
     * 2018年10月16日 上午11:13:24
     */
    @Async
    public void recordMatchRuleToMongo(List<BatchMatchResult> batchMatchResultList, String applyNo) {
        if (CollectionUtils.isEmpty(batchMatchResultList)) {
            return;
        }
        try {
            LOGGER.info("异步任务【spring】-订单编号:{},记录匹配规则到mongo中，为运维提供查询规则匹配数据开始", applyNo);
            for (BatchMatchResult matchResult : batchMatchResultList) {
                List<DscMatchResult> noMatchRuleList = matchResult.getNoMachRuleList();
                if (CollectionUtils.isEmpty(noMatchRuleList)) {
                    continue;
                }
                String capitalCode = matchResult.getCapitalCode(); //资方编码
                DscMatchResultDto dscMatchResultDTO = null;
                for (DscMatchResult ruleResult : noMatchRuleList) {
                    dscMatchResultDTO = new DscMatchResultDto();
                    dscMatchResultDTO.setApplyNo(applyNo); //订单编号
                    dscMatchResultDTO.setMatchCapitalCode(capitalCode); //匹配的资方编码
                    dscMatchResultDTO.setRuleId(ruleResult.getRuleId()); //规则ID
                    dscMatchResultDTO.setMach(false); //未匹配
                    dscMatchResultDTO.setCreateTime(new Date()); //创建时间
                    dscMatchResultDTO.setMessage(ruleResult.getRuleMessage()); //规则消息
                    dscMatchResultDTO.setReturnStr(ruleResult.getReturnStr());

                    mongoTemplate.save(dscMatchResultDTO);
                }
            }
            LOGGER.info("异步任务【spring】-订单编号:{},记录匹配规则到mongo中，为运维提供查询规则匹配数据结束", applyNo);
        } catch (Exception e) {
            LOGGER.error("订单编号:{},记录匹配规则到mongo中，为运维提供查询规则匹配数据异常", applyNo, e);
        }
    }

    @SuppressWarnings("rawtypes")
    @Async
    public void recordAsyncMessageNotice(DscAsyncMessageDto dscAsyncMessageDto) {
        LOGGER.info("异步任务【spring】-记录给alix的异步消息通知信息开始,消息:{}", JSON.toJSONString(dscAsyncMessageDto));
        try {
            AssetOrderFlow.recordAsyncMessageNotice(dscAsyncMessageDto);
        } catch (Exception e) {
            LOGGER.error("异步任务【spring】-记录给alix的异步消息通知信息异常", e);
        }
        LOGGER.info("异步任务【spring】-记录给alix的异步消息通知信息结束,消息:{}", JSON.toJSONString(dscAsyncMessageDto));
    }

    
    /**
     * 异步订单取消处理
     * @param venusApplyNo
     * @param applyNo
     * @param assetMainId 
     * @author YixinCapital -- wangwenlong
     *	       2018年10月20日 下午2:39:40
     */
    @Async
	public void syncOrderCancel(String venusApplyNo,String applyNo,String assetMainId) {
		try {
			LOGGER.info("异步任务【spring】-云南信托异步订单取消处理开始,venusApplyNo:{},applyNo:{},assetMainId:{}",venusApplyNo,applyNo,assetMainId);
			BaseMsgDTO resultDto = ytOrderCancelRequest.sendResult(venusApplyNo, applyNo, assetMainId);
			LOGGER.info("异步任务【spring】-云南信托异步订单取消处理结束,venusApplyNo:{},applyNo:{},返回结果：{}",
					venusApplyNo,applyNo,assetMainId,JSON.toJSONString(resultDto));
		} catch (Exception e) {
			 LOGGER.error("异步任务【spring】-云南信托异步订单取消处理异常", e);
		}
	}

    /**
     * Venus 定时补救
     * @param rescueType 
     * @author YixinCapital -- wangwenlong
     *	       2018年10月22日 下午3:57:07
     */
    @Async
	public void venusRescueJob(String rescueType) {
		if(CommonConstant.ZERO.equals(rescueType)){
			LOGGER.info("异步任务【spring】-定时清理附件开始");
			venusRescueJobService.clearAttachment();
			LOGGER.info("异步任务【spring】-定时清理附件开始");
		}
		
	}
}
