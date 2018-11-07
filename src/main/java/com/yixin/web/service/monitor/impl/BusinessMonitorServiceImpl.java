package com.yixin.web.service.monitor.impl;

import com.google.common.base.Joiner;
import com.google.common.collect.Maps;
import com.yixin.dsc.util.StrUtil;
import com.yixin.kepler.common.RedisClientUtil;
import com.yixin.kepler.enity.AssetBankTran;
import com.yixin.kepler.enity.AssetContractTask;
import com.yixin.kepler.enity.AssetMainInfo;
import com.yixin.web.common.constant.NoticeConst;
import com.yixin.web.common.constant.RedisConstant;
import com.yixin.web.dto.monitor.BzOvertimeOrderInfoDto;
import com.yixin.web.dto.monitor.BzOvertimeResultDto;
import com.yixin.web.service.monitor.BusinessMonitorService;
import com.yixin.web.service.monitor.NoticeService;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @description:
 * @date: 2018-10-17 16:21
 */
@Service
public class BusinessMonitorServiceImpl implements BusinessMonitorService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private RedisClientUtil redisClientUtil;

    @Resource
    private NoticeService noticeService;

    @Override
    public void creditAuditCheck() {
        logger.info("银行信审卡单请款检查，开始.");

        //获取原始申请单数据
        List<AssetMainInfo> orderList = AssetMainInfo.getWaitBankAuditOrderList();
        if (orderList == null || orderList.size() <= 0) {
            return;
        }

        //获取超时配置
        Map<String, Long> overtimeConfigMap = getOvertimeConfig(RedisConstant.CREDIT_AUDIT_OVERTIME_KEY);
        if (overtimeConfigMap == null || overtimeConfigMap.size() <= 0) {
            return;
        }

        //转换成超时检查统一的数据类型
        List<BzOvertimeOrderInfoDto> originCheckData = originDataPreprocress(orderList);

        //超时检查
        List<BzOvertimeResultDto> overtimeDataList = buildOvertimeData(originCheckData, overtimeConfigMap, "minute");

        //组织超时邮件数据
        Object[] emailContentValue = buildOvertimeNoticeData(overtimeDataList);

        //预警邮件提醒
        if (emailContentValue != null) {
            noticeService.commomBzNotice(NoticeConst.CREDIT_AUDIT_OVERTIME_CHECK_SUBJECT, NoticeConst.BANK_AUDIT_OVERTIME_CHECK_TEMPLATE, emailContentValue);
        }
        logger.info("银行信审卡单请款检查，结束.");
    }


    @Override
    public void fundsRequestCheck() {
        logger.info("银行请款卡单请款检查，开始.");

        //获取原始申请单数据
        List<AssetMainInfo> orderList = AssetMainInfo.getWaitFundsRequestOrderList();
        if (orderList == null || orderList.size() <= 0) {
            return;
        }

        //获取超时配置
        Map<String, Long> overtimeConfigMap = getOvertimeConfig(RedisConstant.FUNDS_REQUEST_OVERTIME_KEY);
        if (overtimeConfigMap == null || overtimeConfigMap.size() <= 0) {
            return;
        }

        //转换成超时检查统一的数据类型
        List<BzOvertimeOrderInfoDto> originCheckData = originDataPreprocress(orderList);

        //超时检查
        List<BzOvertimeResultDto> overtimeDataList = buildOvertimeData(originCheckData, overtimeConfigMap, "minute");

        //组织超时邮件数据
        Object[] emailContentValue = buildOvertimeNoticeData(overtimeDataList);

        if (emailContentValue != null) {
            noticeService.commomBzNotice(NoticeConst.FUNDS_REQUEST_OVERTIME_CHECK_SUBJECT, NoticeConst.BANK_AUDIT_OVERTIME_CHECK_TEMPLATE, emailContentValue);
        }

        logger.info("银行请款卡单请款检查，结束.");
    }


    /**
     * 申请单原始数据预处理
     *
     * @param orderList
     * @return
     */
    private List<BzOvertimeOrderInfoDto> originDataPreprocress(List<AssetMainInfo> orderList) {

        List<BzOvertimeOrderInfoDto> result = Lists.newArrayList();

        for (AssetMainInfo mainInfo : orderList) {
            BzOvertimeOrderInfoDto orderInfo = new BzOvertimeOrderInfoDto();
            orderInfo.setApplyNo(mainInfo.getApplyNo());
            orderInfo.setFinancialCode(mainInfo.getFinancialCode());
            orderInfo.setMarkDate(mainInfo.getUpdateTime());
            result.add(orderInfo);
        }

        return result;
    }


    @Override
    public void contractSignCheck() {
        logger.info("合同签章情况检查，开始.");

        List<AssetContractTask> contractTaskList = AssetContractTask.getUnsuccessedTask();
        if (contractTaskList == null || contractTaskList.size() <= 0) {
            return;
        }

        //获取超时配置
        Map<String, Long> overtimeConfigMap = getOvertimeConfig(RedisConstant.CONTRACT_SIGN_UNSUCCESS_KEY);
        if (overtimeConfigMap == null || overtimeConfigMap.size() <= 0) {
            return;
        }

        //转换成超时检查统一的数据类型
        List<BzOvertimeOrderInfoDto> originCheckData = contractSignOriginDataPreprocress(contractTaskList);

        //超时检查
        List<BzOvertimeResultDto> overtimeDataList = buildOvertimeData(originCheckData, overtimeConfigMap, "minute");

        //组织超时邮件数据
        Object[] emailContentValue = buildOvertimeNoticeData(overtimeDataList);

        //预警邮件提醒
        if (emailContentValue != null) {
            noticeService.commomBzNotice(NoticeConst.CONTRACT_SIGN_CHECK_SUBJECT, NoticeConst.BANK_AUDIT_OVERTIME_CHECK_TEMPLATE, emailContentValue);
        }

        logger.info("合同签章情况检查，结束.");
    }


    /**
     * 签约超时数据预处理
     *
     * @param contractTaskList
     * @return
     */
    private List<BzOvertimeOrderInfoDto> contractSignOriginDataPreprocress(List<AssetContractTask> contractTaskList) {
        List<BzOvertimeOrderInfoDto> result = Lists.newArrayList();

        for (AssetContractTask task : contractTaskList) {

            String applyNo = task.getApplyNo();
            AssetMainInfo mainInfo = AssetMainInfo.getByApplyNo(applyNo);
            if (mainInfo == null) {
                continue;
            }

            BzOvertimeOrderInfoDto orderInfo = new BzOvertimeOrderInfoDto();
            orderInfo.setApplyNo(task.getApplyNo());
            orderInfo.setFinancialCode(mainInfo.getFinancialCode());
            orderInfo.setMarkDate(task.getUpdateTime());
            result.add(orderInfo);
        }
        return result;
    }

    @Override
    public void bankTrustLimitOccupyCheck() {
        logger.info("未取消且银行未放款，超时（银行信托计划额度占用）预警，开始.");

        //获取原始申请单数据
        List<AssetMainInfo> orderList = AssetMainInfo.getSubmitFundsRequestOrderList();
        if (orderList == null || orderList.size() <= 0) {
            return;
        }

        //获取超时配置
        Map<String, Long> overtimeConfigMap = getOvertimeConfig(RedisConstant.BANK_TRUST_LIMIT_OCCUPY_KEY);
        if (overtimeConfigMap == null || overtimeConfigMap.size() <= 0) {
            return;
        }

        //转换成超时检查统一的数据类型
        List<BzOvertimeOrderInfoDto> originCheckData = bankTrustLimitOccupyOriginDataPreprocress(orderList);

        //超时检查
        List<BzOvertimeResultDto> overtimeDataList = buildOvertimeData(originCheckData, overtimeConfigMap, "day");

        //组织超时邮件数据
        Object[] emailContentValue = buildOvertimeNoticeData(overtimeDataList);

        //预警邮件提醒
        if (emailContentValue != null) {
            noticeService.commomBzNotice(NoticeConst.BANK_TRUST_LIMIT_OCCUPY_CHECK_SUBJECT, NoticeConst.BANK_AUDIT_OVERTIME_CHECK_TEMPLATE, emailContentValue);
        }
        logger.info("未取消且银行未放款，超时（银行信托计划额度占用）预警，结束.");
    }


    /**
     * @param orderList
     * @return
     */
    private List<BzOvertimeOrderInfoDto> bankTrustLimitOccupyOriginDataPreprocress(List<AssetMainInfo> orderList) {

        List<BzOvertimeOrderInfoDto> result = Lists.newArrayList();

        for (AssetMainInfo mainInfo : orderList) {

            String applyNo = mainInfo.getApplyNo();
            AssetBankTran lastAuditRecord = AssetBankTran.getLastBankAuditRecord(applyNo);
            if (lastAuditRecord == null) {
                continue;
            }

            BzOvertimeOrderInfoDto orderInfo = new BzOvertimeOrderInfoDto();
            orderInfo.setApplyNo(applyNo);
            orderInfo.setFinancialCode(mainInfo.getFinancialCode());
            orderInfo.setMarkDate(lastAuditRecord.getCreateTime());

            result.add(orderInfo);
        }

        return result;
    }


    /**
     * 组织超时数据
     *
     * @param originCheckData
     * @param overtimeConfigMap
     * @param timeUnit          超时时间单位 minute/分钟  hour/小时  day/天
     * @return
     */
    private List<BzOvertimeResultDto> buildOvertimeData(List<BzOvertimeOrderInfoDto> originCheckData, Map<String, Long> overtimeConfigMap, String timeUnit) {

        Map<String, List<BzOvertimeOrderInfoDto>> orderMap = originCheckData.stream().collect(Collectors.groupingBy(BzOvertimeOrderInfoDto::getFinancialCode));

        long timeNow = System.currentTimeMillis();
        List<BzOvertimeResultDto> overtimeDataList = Lists.newArrayList();
        //超时数据检查，按照资方分别检查
        for (String financialCode : orderMap.keySet()) {

            List<BzOvertimeOrderInfoDto> orders = orderMap.get(financialCode);
            if (orders == null || orders.size() <= 0) {
                continue;
            }

            Long overtimeConfig = overtimeConfigMap.get(financialCode);
            if (overtimeConfig == null) {
                continue;
            }

            String overtimeOrder = gatherOvertimeOrderData(orders, overtimeConfig, timeNow);

            if (StrUtil.isEmpty(overtimeOrder)) {
                continue;
            }

            BzOvertimeResultDto overtimeData = new BzOvertimeResultDto();

            String overtimeDesc;
            if ("day".equals(timeUnit)) {
                Long overtimeDay = overtimeConfig / (60 * 24);
                overtimeDesc = StrUtil.fixNull(overtimeDay) + "天";
            } else {
                //默认使用分钟为单位
                overtimeDesc = StrUtil.fixNull(overtimeConfig) + "分钟";
            }

            overtimeData.setFinancialCode(financialCode);
            overtimeData.setOrders(overtimeOrder);
            overtimeData.setOvertime(overtimeDesc);

            overtimeDataList.add(overtimeData);
        }

        return overtimeDataList;
    }


    /**
     * 收集超时单号数据
     *
     * @param orderList
     * @param overtimeConfig
     * @return
     */
    private String gatherOvertimeOrderData(List<BzOvertimeOrderInfoDto> orderList, Long overtimeConfig, long timeNow) {

        //超时时间以分钟为单位配置
        overtimeConfig = overtimeConfig * 1000 * 60;

        List<String> applyNoList = Lists.newArrayList();

        for (BzOvertimeOrderInfoDto orderInfo : orderList) {

            Date updateTime = orderInfo.getMarkDate();
            String applyNo = orderInfo.getApplyNo();

            long timeUpdate = updateTime.getTime();
            long intervalTime = timeNow - timeUpdate;

            if (intervalTime > overtimeConfig) {
                applyNoList.add(applyNo);
            }
        }

        if (applyNoList == null || applyNoList.size() <= 0) {
            return null;
        }

        return Joiner.on(",").join(applyNoList);
    }

    /**
     * 银行信审、请款超时检查数据组织
     *
     * @param overtimeData
     * @return
     */
    private Object[] buildOvertimeNoticeData(List<BzOvertimeResultDto> overtimeData) {

        if (overtimeData == null || overtimeData.size() <= 0) {
            return null;
        }


        StringBuilder sb = new StringBuilder();

        for (BzOvertimeResultDto item : overtimeData) {

            String financialCode = item.getFinancialCode();
            String overtime = item.getOvertime();
            String orders = item.getOrders();

            String noticeContent = String.format(NoticeConst.BANK_AUDIT_OVERTIME_CHECK_SUB_TEMPLATE, financialCode, overtime, orders);
            sb.append(noticeContent);
        }


        List<String> emailContents = Lists.newArrayList(sb.toString());
        return emailContents.toArray();
    }


    /**
     * 获取超时配置
     *
     * @param type
     * @return
     */
    private Map<String, Long> getOvertimeConfig(String type) {

        Map<String, Long> result = Maps.newHashMap();

        if (StrUtil.isEmpty(type)) {
            return result;
        }

        Map<String, String> overtimeConfig = redisClientUtil.hGetAll(type);

        if (overtimeConfig == null || overtimeConfig.size() <= 0) {
            return result;
        }

        for (String key : overtimeConfig.keySet()) {

            String value = overtimeConfig.get(key);
            Long longValue = StrUtil.toLong(value);

            if (longValue != null) {
                result.put(key, longValue);
            }
        }
        return result;
    }
}
