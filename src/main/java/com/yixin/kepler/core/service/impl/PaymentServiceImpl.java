package com.yixin.kepler.core.service.impl;

import java.util.Objects;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.common.DscContant;
import com.yixin.dsc.enumpackage.RequestPreposeEnum;
import com.yixin.kepler.common.enums.AssetPhaseEnum;
import com.yixin.kepler.common.enums.AssetStateEnum;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.core.domain.AsyncTask;
import com.yixin.kepler.core.domain.AttachmentUploadFactory;
import com.yixin.kepler.core.service.PaymentService;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.dto.OsbAttachmentDTO;
import com.yixin.kepler.enity.AssetMainInfo;
import com.yixin.kepler.enity.AssetPaymentTask;

/**
 * @author sukang
 * @date 2018-06-08 11:45:54
 */
@Service
public class PaymentServiceImpl implements PaymentService {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private AsyncTask asyncTask;

    @Resource
    private AttachmentUploadFactory attachmentUploadFactory;


    /**
     * 重构请款实现
     * -----【统一标准】
     *
     * @param applyNo
     * @return
     * @author YixinCapital -- chen.lin
     * 2018年9月21日 下午7:58:47
     */
    @Override
    public BaseMsgDTO paymentRequest(String applyNo) {
        logger.info("订单号为{}，发送请款请求开始", applyNo);
        AssetMainInfo assetMainInfo = AssetMainInfo.getByApplyNoPayment(applyNo);
        //*****************校验请款合法性
        if (assetMainInfo == null) {
            return BaseMsgDTO.failureData("订单号未经信审");
        }
        if (!Objects.equals(assetMainInfo.getContractSignState(),
                AssetStateEnum.SUCCESS.getState())) {
            return BaseMsgDTO.failureData("未签署合同");
        }

        if (Objects.equals(AssetPhaseEnum.PAYMENT_TRIAL.getPhase(),
                assetMainInfo.getAssetPhase())) {
            if (Objects.equals(AssetStateEnum.SUCCESS.getState(),
                    assetMainInfo.getPaymentState())) {
                return BaseMsgDTO.successData("已请款成功");
            } else if (Objects.equals(AssetStateEnum.DOING.getState(),
                    assetMainInfo.getPaymentState())) {
                return BaseMsgDTO.successData("请款申请处理中");

            }
        }
        if (AssetPaymentTask.getByApplyNo(applyNo) != null) {
            return BaseMsgDTO.successData("订单" + applyNo + "已受理");

        }

        //*****************银行请款请求前置
        //1.请款前校验		2.校验通过，判断流程（正常-实时or新定时，跳转-老定时）
        BaseMsgDTO preposeMsg = asyncTask.requestPrepose(applyNo, assetMainInfo.getFinancialCode(), RequestPreposeEnum.PAYMENT_TRIAL);
        if (DscContant.KeplerCode.FAILD.equals(preposeMsg.getCode())) {
            return BaseMsgDTO.failureData(preposeMsg.getMessage());
        }

        //*****************向银行发起请款请求
        BaseMsgDTO resultDto = null;
        if (CommonConstant.PreposeResultCode.NORMAL.equals(preposeMsg.getCode())) {
            //正常-实时or新定时
            InvokeResult<BaseMsgDTO> result = asyncTask.requestBank(applyNo, assetMainInfo.getFinancialCode(), BankPhaseEnum.PAYMENT);
            resultDto = (BaseMsgDTO) result.getData();
        } else {
            //跳转-老定时
            AssetPaymentTask assetPaymentTask = new AssetPaymentTask();
            assetPaymentTask.setApplyNo(applyNo);
            assetPaymentTask.setExecState(0);
            assetPaymentTask.setExecTimes(0);
            assetPaymentTask.setFileStatus(AssetStateEnum.INIT.getState()); //创建请款任务，影像件默认为初始化
            assetPaymentTask.setIsEnd(false);
            assetPaymentTask.setIsSuccess(false);
            assetPaymentTask.setAssetCode(assetMainInfo.getFinancialCode());
            assetPaymentTask.create();
            resultDto = BaseMsgDTO.successData("请款受理成功");
        }

        //*****************请款阶段附件上传
        if (resultDto.successed()) {
            OsbAttachmentDTO attachmentDTO = new OsbAttachmentDTO();
            attachmentDTO.setBzId(applyNo);
            attachmentDTO.setBzType(BankPhaseEnum.PAYMENT.getPhase());
            logger.info("订单号{}，请款阶段影像件文件上传任务", applyNo);
            asyncTask.uploadAttachment(attachmentDTO);
        }
        logger.info("订单号为{}，发送请款请求结束", applyNo);
        return resultDto;
    }

    private boolean fileStatus(AssetMainInfo assetMainInfo) {
        if (Objects.equals(CommonConstant.BankName.WB_BANK,
                assetMainInfo.getFinancialCode())) {
            return false;
        }
        return true;
    }


    @Override
    public boolean isExitByApplyNo(String applyNo) {
        return AssetMainInfo.isExitByApplyNo(applyNo);
    }

}
