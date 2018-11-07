package com.yixin.kepler.v1.service.capital.yillion;

import com.alibaba.fastjson.JSON;
import com.yixin.dsc.util.StrUtil;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.enity.AssetBankTran;
import com.yixin.kepler.enity.AssetResultTask;
import com.yixin.kepler.enity.OsbFileLog;
import com.yixin.kepler.v1.common.constants.BankRequestConstant;
import com.yixin.kepler.v1.common.constants.IcbcConstant;
import com.yixin.kepler.v1.common.core.bankReq.BankRespResultHandle;
import com.yixin.kepler.v1.common.enumpackage.YILLIONUrlEnum;
import com.yixin.kepler.v1.common.util.SerialNumberUtil;
import com.yixin.kepler.v1.datapackage.dto.BankRespResultHandleDTO;
import com.yixin.kepler.v1.datapackage.dto.yillion.YILLIONRespDTO;
import com.yixin.kepler.v1.datapackage.entity.AssetBankRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * @description:
 * @date: 2018-11-01 16:30
 */
@Service("YILLIONRespResultHandle")
public class LastTrailResultHandle implements BankRespResultHandle {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private SerialNumberUtil serialNumberUtil;

    @Override
    public BaseMsgDTO respResultHandle(BankRespResultHandleDTO resultDTO) {

        String applyNo = resultDTO.getApplyNo();
        String resultJson = resultDTO.getBankResultJosn();
        String tranNo = resultDTO.getTranNo();
        String bankRequestId = resultDTO.getBankReqId();

        //更新交易信息
        updateBankTran(applyNo, resultJson, tranNo);

        //获取资产发起银行请求信息
        AssetBankRequest bankRequest = AssetBankRequest.getById(bankRequestId);

        //银行接口返回空
        if (StrUtil.isEmpty(resultJson)) {
            requestRetry(bankRequest);
            return BaseMsgDTO.failureData("进件收妥失败");
        }

        YILLIONRespDTO respInfo = JSON.parseObject(resultJson, YILLIONRespDTO.class);
        if (respInfo == null) {
            requestRetry(bankRequest);
            return BaseMsgDTO.failureData("进件收妥失败");
        }

        String respCode = respInfo.getRespcode();
        String respMsg = respInfo.getRespmsg();

        if (!"0000".equals(respCode)) {
            requestRetry(bankRequest);
            return BaseMsgDTO.failureData("进件收妥失败");
        }

        requestSuccess(bankRequest);
        return BaseMsgDTO.failureData("进件收妥成功");
    }


    /**
     * 发起进件成功，更新bankRequest状态为成功
     * <p>
     * 添加银行结果查询任务
     *
     * @param bankRequest
     */
    private void requestSuccess(AssetBankRequest bankRequest) {

        //发起进件成功，更新bankRequest状态为成功
        bankRequest.setReqState(BankRequestConstant.REQ_BANK_SUCCESS);
        bankRequest.update();

        String phase = bankRequest.getPhase();

        //添加银行结果查询任务
        AssetResultTask assetResultTask = new AssetResultTask();
        assetResultTask.setBzId(phase);
        assetResultTask.setApplyNo(bankRequest.getApplyNo());
        assetResultTask.setAssetNo(bankRequest.getAssetNo());
        assetResultTask.setTranNo(bankRequest.getTranNo());
        assetResultTask.setExecState(0);
        assetResultTask.setExecTimes(0);
        assetResultTask.setNextTime(new Date());
        assetResultTask.setIsEnd(0);
        assetResultTask.create();
    }


    /**
     * 银行进件任务置为可重试状态
     *
     * @param bankRequest
     */
    private void requestRetry(AssetBankRequest bankRequest) {
        bankRequest.setReqState(BankRequestConstant.RETRY_REQ_BANK);
        int retryMark = bankRequest.getRetryMark();
        bankRequest.setRetryMark(++retryMark);
        bankRequest.update();
    }

    /**
     * 更新银行交易记录，增加接口返回信息
     *
     * @param applyNo
     * @param result
     * @param tranNo
     */
    private void updateBankTran(String applyNo, String result, String tranNo) {

        AssetBankTran bankTran = AssetBankTran.getByTranNo(tranNo, CommonConstant.SenderType.YIXIN);
        logger.info("订单编号:{},亿联更新银行交互结果,result:{}", applyNo, result);
        //响应报文
        bankTran.setRepBody(result);

        if (StrUtil.isEmpty(result)) {
            bankTran.update();
            return;
        }

        YILLIONRespDTO respInfo = JSON.parseObject(result, YILLIONRespDTO.class);
        if (respInfo == null) {
            bankTran.update();
            return;
        }

        String respCode = respInfo.getRespcode();
        String respMsg = respInfo.getRespmsg();
        //错误码
        bankTran.setApprovalCode(respCode);
        //错误描述
        bankTran.setApprovalDesc(respMsg);
        bankTran.update();
    }


    @Override
    public String assembleOsb(AssetBankRequest requestDTO, OsbFileLog osbFileLog) {
        return null;
    }

    @Override
    public String requestBeforeHandle(AssetBankRequest task) {
        String applyNo = task.getApplyNo();
        logger.info("亿联发送请求前数据处理开始，订单编号:{}", applyNo);

        String reqBody = task.getAssetReqBody();
        //发起银行交互前生产tranNo，每次重试会生成不同的tran record
        String tranNo = serialNumberUtil.getTranNo4Idfactory(IcbcConstant.TRAN_BIZCODE, IcbcConstant.TRAN_SYSCODE);
        task.setTranNo(tranNo);
        task.update();

        //========= 银行交互保存记录 ===================================
        logger.info("亿联发送请求前数据处理-创建银行交互记录，订单编号:{}", applyNo);
        String phase = task.getPhase();

        AssetBankTran assetBankTran = new AssetBankTran();
        assetBankTran.setReqBody(reqBody);
        assetBankTran.setApplyNo(task.getApplyNo());
        assetBankTran.setAssetId(task.getBzId());
        //终审
        assetBankTran.setPhase(phase);
        assetBankTran.setReqUrl(task.getReqUrl());

        if (BankPhaseEnum.LAST_TRIAL.getPhase().equals(phase)) {
            assetBankTran.setApiCode(YILLIONUrlEnum.ENTRY_APPLY.getUrl());
        } else if (BankPhaseEnum.PAYMENT.getPhase().equals(phase)) {
            assetBankTran.setApiCode(YILLIONUrlEnum.LOAN_APPLY.getUrl());
        }

        assetBankTran.setSender(CommonConstant.SenderType.YIXIN);
        assetBankTran.setTranNo(tranNo);
        assetBankTran.setBankOrderNo(task.getBankOrderNo());
        assetBankTran.create();

        logger.info("亿联发送请求前数据处理结束，订单编号:{}", task.getApplyNo());
        return null;
    }
}
