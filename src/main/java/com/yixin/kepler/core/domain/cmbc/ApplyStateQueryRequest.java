package com.yixin.kepler.core.domain.cmbc;

import com.yixin.common.exception.BzException;
import com.yixin.common.utils.DateUitls;
import com.yixin.common.utils.InvokeResult;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.kepler.common.RestTemplateUtil;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.common.enums.CMBCTransCodeEnum;
import com.yixin.kepler.core.config.CMBCConfig;
import com.yixin.kepler.core.domain.cmbc.util.CMBCUtil;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.dto.cmbc.CMBCPreTrialBodyDTO;
import com.yixin.kepler.dto.cmbc.CMBCReceiveMsgDTO;
import com.yixin.kepler.dto.cmbc.CMBCRequestDTO;
import com.yixin.kepler.enity.AssetBankTran;
import com.yixin.kepler.enity.AssetMainInfo;
import net.minidev.json.JSONUtil;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * [民生银行]申请状态
 * Package : com.yixin.kepler.core.domain.cmbc
 *
 * @author YixinCapital -- wanghonglin
 * 2018/6/21 14:25
 */
@Service
public class ApplyStateQueryRequest {

    private static final Logger logger = LoggerFactory.getLogger(ApplyStateQueryRequest.class);
    @Autowired
    private CMBCConfig cmbcConfig;
    /**
     * 组装银行数据
     * @param applyNo
     * @return
     * @throws BzException
     */
    public String sendResult(String  applyNo) throws BzException {
        //主表
        AssetMainInfo assetMainInfo = AssetMainInfo.getByApplyNo(applyNo);
        CMBCRequestDTO cmbcRequestDTO = CMBCRequestDTO.getCMBCInstance(
                CMBCTransCodeEnum.APPLY_STATE_RESULT);
        // 1.设置请求头数据基
        cmbcRequestDTO.setReqSeq(CMBCUtil.createReqSeq()); // 请求方流水号，对应资产编号
        cmbcRequestDTO.setTransType("000010");
        cmbcRequestDTO.setMerchantNum(cmbcConfig.getMerchantNum());
        cmbcRequestDTO.setSystemCode(cmbcConfig.getSystemCode());

        //receiveMsg
        CMBCReceiveMsgDTO cmbcReceiveMsgDTO = new CMBCReceiveMsgDTO();
        String chnlTxNo = CMBCUtil.getTradeNo();// 渠道流水号
        cmbcReceiveMsgDTO.setChnlTxNo(chnlTxNo);
        cmbcReceiveMsgDTO.setTxDt(DateUitls.dateToStr(new Date(), "yyyyMMdd")); // 交易日期
        cmbcReceiveMsgDTO.setTxTm(DateUitls.dateToStr(new Date(), "HHmmss")); // 交易时间


        CMBCPreTrialBodyDTO cmbcPreTrialDTO = new CMBCPreTrialBodyDTO();
        cmbcPreTrialDTO.setReceiveMg(cmbcReceiveMsgDTO);
        cmbcPreTrialDTO.setApplyNo(assetMainInfo.getCmbcApplyNo());
        /**
         * 设置body
         */
        cmbcRequestDTO.setBody(cmbcPreTrialDTO);
        logger.info("[民生银行]申请状态数据组装结束，apply_No{}", ""/*mainInfo.getApplyNo()*/);
        //存储银行交易信息
        AssetBankTran assetBankTran = new AssetBankTran();
        assetBankTran.setAssetId(assetMainInfo.getId());
        assetBankTran.setApplyNo(applyNo);
        assetBankTran.setReqBody(com.alibaba.fastjson.JSONObject.toJSONString(cmbcRequestDTO));
        assetBankTran.setAssetNo(chnlTxNo);
        assetBankTran.setPhase(BankPhaseEnum.PAYMENT_QUERY.getPhase());
        assetBankTran.setReqUrl(cmbcConfig.getOsbReqUrl());
        assetBankTran.setTranNo(CMBCTransCodeEnum.APPLY_STATE_RESULT.getTransCode());
        assetBankTran.setSender(1);
        assetBankTran.create();
        String preTrailUrl = cmbcConfig.getOsbReqUrl();
        
        
        logger.info("申请状态请求银行报文：{}", assetBankTran.getReqBody());
        String jsonResult = RestTemplateUtil.sendRequestV2(preTrailUrl, cmbcRequestDTO);
        assetBankTran.setRepBody(jsonResult);
        logger.info("\n申请状态返回报文为:{}",jsonResult);
        JSONObject bankResult = JSONObject.fromObject(jsonResult);
        
        String resultStr = "";
        if (bankResult != null && bankResult.containsKey("body")) {
            JSONObject body = JSONObject.fromObject(bankResult.getString("body"));
            JSONObject returnMsg = body.containsKey("returnMg")
                    ? body.getJSONObject("returnMg") : null;

            if (returnMsg == null) {
                assetBankTran.setApprovalCode(body.getString("appvCode"));
                assetBankTran.setApprovalDesc(body.getString("appvStatus"));
                //COMP:可放款状态, CNCL:客户取消贷款,REJT:拒绝申请 ，AUTO：终审通过，REGR：审批中，WWSL ： 等待电子签约
                if("S".equals(body.getString("appvStatus"))){
                    resultStr =  body.getString("applSts");
                }
            }else {
                assetBankTran.setApprovalCode(returnMsg.getString("respCode"));
                assetBankTran.setApprovalDesc(returnMsg.getString("respStatus"));
            }
            assetBankTran.update();
        }
        return resultStr;
    }
}
