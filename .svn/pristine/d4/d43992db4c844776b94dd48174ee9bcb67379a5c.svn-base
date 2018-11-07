package com.yixin.kepler.core.domain.cmbc;

import com.fasterxml.jackson.databind.deser.Deserializers;
import com.yixin.common.utils.DateUitls;
import com.yixin.common.utils.InvokeResult;
import com.yixin.kepler.common.RestTemplateUtil;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.common.enums.CMBCTransCodeEnum;
import com.yixin.kepler.core.config.CMBCConfig;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.core.domain.cmbc.util.CMBCUtil;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.dto.cmbc.CMBCAfterLoanDataSyncDTO;
import com.yixin.kepler.dto.cmbc.CMBCRequestDTO;
import com.yixin.kepler.enity.AssetBankTran;
import com.yixin.kepler.enity.AssetMainInfo;
import com.yixin.kepler.enity.OsbFileLog;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author sukang
 */
@Service(value = "CMBCAttachmentSupplyStrategy")
public class AttachmentSupplyStrategy {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    private CMBCConfig cmbcConfig;


    public InvokeResult<BaseMsgDTO> sendResult(String applyNo, BankPhaseEnum bankPhaseEnum){

        logger.info("订单号{},开始补传资料，阶段为{}",applyNo,bankPhaseEnum.getPhase());
        AssetMainInfo assetMainInfo = AssetMainInfo.getByApplyNo(applyNo);
        BaseMsgDTO baseMsgDTO = null;

        //组装报文
        CMBCRequestDTO cmbcInstance = CMBCRequestDTO.getCMBCInstance(
                CMBCTransCodeEnum.AFTER_LOAN);
        cmbcInstance.setMerchantNum(cmbcConfig.getMerchantNum());
        cmbcInstance.setSystemCode(cmbcConfig.getSystemCode());
        cmbcInstance.setTransType("000010");
        cmbcInstance.setReqSeq(CMBCUtil.createReqSeq());
        cmbcInstance.setBody(getCmbcAfterLoanBody(assetMainInfo,bankPhaseEnum));

        AssetBankTran assetBankTran = new AssetBankTran();
        //添加银行交易信息
        assetBankTran.setReqBody(JSONObject.fromObject(cmbcInstance).toString());
        assetBankTran.setApplyNo(assetMainInfo.getApplyNo());
        assetBankTran.setAssetId(assetMainInfo.getId());

        //资产编号对应银行交易流水
        assetBankTran.setAssetNo(assetMainInfo.getAssetNo());
        assetBankTran.setReqUrl(cmbcConfig.getOsbReqUrl());
        assetBankTran.setPhase(BankPhaseEnum.AFTER_LOAN.getPhase()
                                .concat("_attachment"));
        assetBankTran.setTranNo(cmbcInstance.getReqSeq());
        assetBankTran.setSender(1);
        assetBankTran.create();

        //请求银行
        logger.info("\n发送附件资料补传请求的参数为{}",assetBankTran.getReqBody());
        String result = RestTemplateUtil.sendRequestV2(cmbcConfig.getOsbReqUrl(),
                cmbcInstance);
        assetBankTran.setRepBody(result);

        logger.info("\n发送附件资料补传返回参数为{}",result);

        //银行请款申请成功创建请求结果查询任务
        assetBankTran.setRepBody(result);

        JSONObject resultJson = JSONObject.fromObject(result);
        JSONObject body;
        //业务执行
        if (!resultJson.isNullObject() && resultJson.containsKey("body")) {
            body = JSONObject.fromObject(resultJson.getString("body"));

            if (body.containsKey("appvStatus") && "S".equals(body.getString("appvStatus")) ) {
                assetBankTran.setApprovalDesc(body.getString("appvResult"));
                assetBankTran.setApprovalCode(body.getString("appvCode"));
                baseMsgDTO = BaseMsgDTO.successData(assetBankTran.getApprovalDesc());
            }else {
                assetBankTran.setApprovalDesc(body.getString("appvResult"));
                assetBankTran.setApprovalCode(body.getString("appvCode"));
                baseMsgDTO = BaseMsgDTO.failureData(assetBankTran.getApprovalDesc());
            }
            assetBankTran.update();
            return new InvokeResult<>(baseMsgDTO);
        }
        //接口执行
        assetBankTran.setApprovalDesc(resultJson.getString("respDesc"));
        assetBankTran.setApprovalCode(resultJson.getString("respStatus"));
        assetBankTran.update();
        return new InvokeResult<>(BaseMsgDTO.failureData("同步资料接口失败"));
    }


    private CMBCAfterLoanDataSyncDTO getCmbcAfterLoanBody(AssetMainInfo assetMainInfo,
                                            BankPhaseEnum bankPhaseEnum) {

        CMBCAfterLoanDataSyncDTO dataSyncDTO = new CMBCAfterLoanDataSyncDTO();

        OsbFileLog osbFileLog = OsbFileLog.getOk(assetMainInfo.getApplyNo(),
                bankPhaseEnum.getPhase());

        dataSyncDTO.setApplyNo(assetMainInfo.getCmbcApplyNo());
        dataSyncDTO.setFileName(osbFileLog.getCompressName().concat(".").concat(
                osbFileLog.getCompressFormat()));
        dataSyncDTO.setReserve("");
        dataSyncDTO.setFileDate(DateUitls.dateToStr(osbFileLog.getCreateTime(),
                "yyyyMMdd"));
        dataSyncDTO.setHasFile("Y");

        //01申请02放款03贷后 不传默认为贷后
        if (bankPhaseEnum.getPhase().equals(BankPhaseEnum.LAST_TRIAL.getPhase())){
            dataSyncDTO.setStage("01");
        }
        if (bankPhaseEnum.getPhase().equals(BankPhaseEnum.PAYMENT.getPhase())){
            dataSyncDTO.setStage("02");
        }
        return dataSyncDTO;
    }


}
