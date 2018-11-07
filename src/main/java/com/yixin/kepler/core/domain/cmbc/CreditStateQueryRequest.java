package com.yixin.kepler.core.domain.cmbc;

import com.yixin.common.exception.BzException;
import com.yixin.common.utils.DateUitls;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.dsc.entity.order.DscSalesApplyCust;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.kepler.common.RestTemplateUtil;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.common.enums.CMBCTransCodeEnum;
import com.yixin.kepler.core.config.CMBCConfig;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.core.domain.SysDIcMapped;
import com.yixin.kepler.core.domain.cmbc.util.CMBCUtil;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.dto.cmbc.CMBCPreTrialBodyDTO;
import com.yixin.kepler.dto.cmbc.CMBCReceiveMsgDTO;
import com.yixin.kepler.dto.cmbc.CMBCRequestDTO;
import com.yixin.kepler.enity.AssetBankTran;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;

/**
 * 查询客户征信授权书状态
 * Package : com.yixin.kepler.core.domain.cmbc
 *
 * @author YixinCapital -- wanghonglin
 * 2018/6/25 10:00
 */
@Service
public class CreditStateQueryRequest {
    private static final Logger logger = LoggerFactory.getLogger(CreditStateQueryRequest.class);
    @Autowired
    private CMBCConfig cmbcConfig;
    @Resource
    private SysDIcMapped sysDIcMapped;

    public boolean sendResult(String  applyNo) throws BzException {
        DscSalesApplyMain dscSalesApplyMain = DscSalesApplyMain.getByApplyNo(applyNo);
        // 客户信息
        DscSalesApplyCust cust = DscSalesApplyCust.getApplyCostByMianId(dscSalesApplyMain.getId());

        CMBCRequestDTO cmbcRequestDTO = CMBCRequestDTO.getCMBCInstance(
                CMBCTransCodeEnum.CREADIT_STATE_RESULT);
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
        cmbcPreTrialDTO.setIdNo(cust.getAzjhm());
        cmbcPreTrialDTO.setIdType(sysDIcMapped.getCmbcMappingValue("azjlx",
                cust.getAzjlx()));
        cmbcPreTrialDTO.setMerchantNum(cmbcConfig.getMerchantNum());
        /**
         *
         * 设置body
         */
        cmbcRequestDTO.setBody(cmbcPreTrialDTO);
        //存储银行交易信息
        AssetBankTran assetBankTran = new AssetBankTran();
        assetBankTran.setAssetId(dscSalesApplyMain.getId());
        assetBankTran.setReqBody(JSONObject.fromObject(cmbcRequestDTO).toString());
        assetBankTran.setAssetNo(chnlTxNo);
        assetBankTran.setApplyNo(applyNo);
        assetBankTran.setPhase(BankPhaseEnum.GREDIT_STATE.getPhase());
        assetBankTran.setReqUrl(cmbcConfig.getOsbReqUrl());
        assetBankTran.setTranNo(CMBCTransCodeEnum.APPLY_STATE_RESULT.getTransCode());
        assetBankTran.setSender(1);
        assetBankTran.create();
        
        
        logger.info("[民生银行]查询征信授权请求报文，{}", assetBankTran.getReqBody());
        String jsonResult = RestTemplateUtil.sendRequestV2(cmbcConfig.getOsbReqUrl(), cmbcRequestDTO);
        logger.info("[民生银行]查询征信授权返回报文:{}",jsonResult);
        assetBankTran.setRepBody(jsonResult);
        
        
        JSONObject bankResult = JSONObject.fromObject(jsonResult);
        Boolean isSuccess = false;
        if (bankResult != null && bankResult.containsKey("body")) {
            JSONObject body = JSONObject.fromObject(bankResult.getString("body"));
            JSONObject returnMsg = body.containsKey("returnMg")
                    ? body.getJSONObject("returnMg") : null;
            if(returnMsg == null){
                assetBankTran.setApprovalCode(body.getString("rtnSts"));
                assetBankTran.setApprovalDesc(body.getString("rtnMsg"));
            }else if("E".equals(returnMsg.getString("rtnSts"))){
                assetBankTran.setApprovalCode(returnMsg.getString("rtnSts"));
                assetBankTran.setApprovalDesc(returnMsg.getString("rtnMsg"));
            }else if("S".equals(returnMsg.getString("rtnSts"))
                    && "AAAAA".equals(returnMsg.getString("rtnCode"))){
                assetBankTran.setApprovalCode(returnMsg.getString("rtnCode"));
                assetBankTran.setApprovalDesc(returnMsg.getString("rtnSts"));
                if("Y".equals(body.getString("IsCreditCheckAgree"))){
                    isSuccess = true;
                }
            }
        }
        assetBankTran.update();
        return isSuccess;
    }
}
