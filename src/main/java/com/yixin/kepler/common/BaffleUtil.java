package com.yixin.kepler.common;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.druid.util.StringUtils;
import com.yixin.kepler.common.enums.CMBCTransCodeEnum;
import com.yixin.kepler.common.enums.ConstantKeyEnum;
import com.yixin.kepler.common.enums.WBTransCodeEnum;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.core.domain.cmbc.util.CMBCUtil;
import com.yixin.kepler.dto.cmbc.CMBCRequestDTO;
import com.yixin.kepler.dto.webank.WBCommonReqDTO;
import com.yixin.kepler.enity.ConstantConfig;

import net.sf.json.JSONObject;

/**
 * 挡板工具类
 * Package : com.yixin.kepler.common
 *
 * @author YixinCapital -- wanghonglin
 * 2018/6/21 16:45
 */

public class BaffleUtil {
    private static final Logger logger = LoggerFactory.getLogger(BaffleUtil.class);
    
    public static String baffleCheck(String url, Object parameter){
        CMBCRequestDTO cmbcRequestDTO;
        try {
            cmbcRequestDTO = (CMBCRequestDTO)parameter;
        }catch (Exception Exception){
            logger.info("银行请求类型解析错误（不是银行的请求，直接返回）");
            return "";
        }

        String transCode = cmbcRequestDTO.getTransCode();
        //开关  0校验   1不校验       为1时 返回直接通过
        String flag = ConstantConfig.getStringValeByKey(ConstantKeyEnum.CMBC_INTERFACE_FLAG.getKey());
        if("0".equals(flag)){
            return "";
        }
        //终审
        if(StringUtils.equals(CMBCTransCodeEnum.LAST_TRIAL.getTransCode(),transCode)){
            ConstantConfig Config = ConstantConfig.getConstantConfigByKey(ConstantKeyEnum.LAST_TRIAL.getKey());
            String jsonStr = Config.getValueString();
//            jsonStr = jsonStr.replace("random", CMBCUtil.getRandom(14));
            JSONObject body = JSONObject.fromObject(JSONObject.fromObject(jsonStr).getString("body"));
            String ss = body.getString("applyNo");
            jsonStr = jsonStr.replace(ss,CMBCUtil.getRandom(14));
            return jsonStr;
        }else if(StringUtils.equals(CMBCTransCodeEnum.LAST_TRIAL_RESULT.getTransCode(),transCode)){//终审结果查询
            ConstantConfig Config = ConstantConfig.getConstantConfigByKey(ConstantKeyEnum.LAST_TRIAL_RESULT.getKey());
            return Config.getValueString();
        }else if(StringUtils.equals(CMBCTransCodeEnum.PAYMENT.getTransCode(),transCode)){// 请款
            ConstantConfig Config = ConstantConfig.getConstantConfigByKey(ConstantKeyEnum.PAYMENT.getKey());
            return Config.getValueString();
        }else if(StringUtils.equals(CMBCTransCodeEnum.PAYMENT_RESULT.getTransCode(),transCode)){
            ConstantConfig Config = ConstantConfig.getConstantConfigByKey(ConstantKeyEnum.PAYMENT_RESULT.getKey());
            return Config.getValueString();
        }else if(StringUtils.equals(CMBCTransCodeEnum.STATE_CHANGE_RESULT.getTransCode(),transCode)){//银行状态变更接口
            ConstantConfig Config = ConstantConfig.getConstantConfigByKey(ConstantKeyEnum.STATE_CHANGE_RESULT.getKey());
            return Config.getValueString();
        }else if(StringUtils.equals(CMBCTransCodeEnum.APPLY_STATE_RESULT.getTransCode(),transCode)){//申请状态查询接口
            ConstantConfig Config = ConstantConfig.getConstantConfigByKey(ConstantKeyEnum.APPLY_STATE_RESULT.getKey());
            return Config.getValueString();
        }else if(StringUtils.equals(CMBCTransCodeEnum.CREADIT_STATE_RESULT.getTransCode(),transCode)){
            ConstantConfig Config = ConstantConfig.getConstantConfigByKey(ConstantKeyEnum.CREADIT_STATE_RESULT.getKey());
            return Config.getValueString();
        }
        return "";
    }
    
    
    public static String baffleCheckCommon(Object parameter,String financialCode){
    	if(CommonConstant.BankName.WB_BANK.equals(financialCode)){ //微众
    		WBCommonReqDTO webankReqDTO;
            try {
            	webankReqDTO = (WBCommonReqDTO)parameter;
            }catch (Exception Exception){
                logger.info("银行请求类型解析错误（不是银行的请求，直接返回）");
                return "";
            }
            String txnId = webankReqDTO.getTxnId(); //交易服务码, 通过这个字段标识调用微众哪个接口，每个接口对应一个编码
            //开关  0/请求银行  1不校验       为1时 返回直接通过
            String flag = ConstantConfig.getStringValeByKey(ConstantKeyEnum.WEBANK_INTERFACE_FLAG.getKey());
            if("0".equals(flag)){
                return "";
            }
            String jsonStr = "";
            if(WBTransCodeEnum.FIRST_TRIAL.getTransCode().equals(txnId)){ //初审/一审
            	jsonStr = ConstantConfig.getStringValeByKey(ConstantKeyEnum.WEBANK_FIRST_TRIAL_RESPONSE_JSON.getKey());
            	
            } else if(WBTransCodeEnum.LAST_TRIAL.getTransCode().equals(txnId)){ //复审 /二审
            	jsonStr = ConstantConfig.getStringValeByKey(ConstantKeyEnum.WEBANK_LAST_TRIAL_RESPONSE_JSON.getKey());
            	
            } else if(WBTransCodeEnum.WB_PAYMENT.getTransCode().equals(txnId)){ //请款
            	jsonStr = ConstantConfig.getStringValeByKey(ConstantKeyEnum.WEBANK_PAYMENT_RESPONSE_JSON.getKey());
            	
            } else if(WBTransCodeEnum.WB_PICKUP_CAR.getTransCode().equals(txnId)){  //订单提车
            	jsonStr = ConstantConfig.getStringValeByKey(ConstantKeyEnum.WEBANK_PICKUP_CAR_RESPONSE_JSON.getKey());
            	
            } else if(WBTransCodeEnum.WB_ORDER_CANCEL.getTransCode().equals(txnId)){//订单终止
            	jsonStr = ConstantConfig.getStringValeByKey(ConstantKeyEnum.WEBANK_ORDER_CANCEL_RESPONSE_JSON.getKey());
            	
            } else if(WBTransCodeEnum.WB_ELECTRON_CONTRACT_SAVE.getTransCode().equals(txnId)){//电子签约存证
            	jsonStr = ConstantConfig.getStringValeByKey(ConstantKeyEnum.WEBANK_ELECTRON_CONTRACT_RESPONSE_JSON.getKey());
            	
            } else if(WBTransCodeEnum.WB_COMPUTER.getTransCode().equals(txnId)){ //借据试算
            	jsonStr = ConstantConfig.getStringValeByKey(ConstantKeyEnum.WEBANK_COMPUTER_RESPONSE_JSON.getKey());
            	
            }
            
            return "0".equals(jsonStr)?"":jsonStr;
    	}
        
        return "";
    }
}
