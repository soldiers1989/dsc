package com.yixin.kepler.v1.service.capital.yntrust;

import com.alibaba.fastjson.JSON;
import com.yixin.common.exception.BzException;
import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.dto.DscBankContractDTO;
import com.yixin.kepler.common.RestTemplateUtil;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.core.base.AbstractBaseDealDomain;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.enity.AssetBankTran;
import com.yixin.kepler.v1.common.enumpackage.YNTrustUrlEnum;
import com.yixin.kepler.v1.datapackage.dto.yntrust.YTCommonResponseDTO;
import com.yixin.kepler.v1.datapackage.dto.yntrust.YTCommonStatusDTO;
import com.yixin.kepler.v1.datapackage.dto.yntrust.YTVerificationCodeRequestDTO;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

/**
 * 同步短信验证码、协议编号进行绑定
 * @author sukang
 */

@Service(value = "YNTRUSTVerificationCodeStrategy")
public class VerificationCodeStrategy extends AbstractBaseDealDomain<DscBankContractDTO,BaseMsgDTO>{


    private final Logger logger = LoggerFactory.getLogger(getClass());
    
    private ThreadLocal<YTVerificationCodeRequestDTO> codeRequestDTOThreadLocal = new ThreadLocal<>();


    @Override
    protected void getData() throws BzException {

        DscBankContractDTO dscBankContractDTO = inputDto.get();

        YTVerificationCodeRequestDTO ytVerificationCode =
                new YTVerificationCodeRequestDTO();
        ytVerificationCode.setUrl(YNTrustUrlEnum.UPLOAD_VERIFICATION_CODE.getUrl());
        ytVerificationCode.setRequestId("");
        ytVerificationCode.setProductCode("");
        ytVerificationCode.setTransactionNo("");
        ytVerificationCode.setVerificationCode("");

        codeRequestDTOThreadLocal.set(ytVerificationCode);
    }

    @Override
    protected void assembler() throws BzException {

    }

    @Override
    protected InvokeResult<BaseMsgDTO> message() throws BzException {


        DscBankContractDTO dscBankContractDTO = inputDto.get();
        YTVerificationCodeRequestDTO ytVerificationCode = codeRequestDTOThreadLocal.get();

        codeRequestDTOThreadLocal.remove();
        inputDto.remove();

        AssetBankTran bankTran = new AssetBankTran();
        bankTran.setApplyNo(dscBankContractDTO.getApplyNo());
        bankTran.setPhase(BankPhaseEnum.PAYMENT.getPhase().concat("VerificationCode"));
        bankTran.setReqBody(JSONObject.fromObject(ytVerificationCode).toString());
        bankTran.setReqUrl(ytVerificationCode.getUrl());
        bankTran.setTranNo(ytVerificationCode.getRequestId());
        bankTran.setSender(CommonConstant.SenderType.YIXIN);
        bankTran.create();

        logger.info("\n单号{}同步短信验证码、协议编号进行绑定请求报文{}","",
                bankTran.getReqBody());

        String result = RestTemplateUtil.sendRequest(bankTran.getReqUrl(),
                ytVerificationCode, CommonConstant.BankName.YNTRUST_BANK);
        bankTran.setRepBody(result);
        logger.info("\n同步短信验证码、协议编号进行绑定接口返回报文为{}",result);

        BaseMsgDTO baseMsgDTO = BaseMsgDTO.failureData("接口异常");
        if (StringUtils.isNotEmpty(result)){
            YTCommonResponseDTO ytCommonResponseDTO = JSON.parseObject(result, YTCommonResponseDTO.class);
            YTCommonStatusDTO status = ytCommonResponseDTO.getStatus();
            if (status.success()){

                bankTran.setApprovalCode(status.getResponseCode());
                bankTran.setApprovalDesc(status.getResponseMessage());
                bankTran.setRemark("");
                baseMsgDTO = BaseMsgDTO.successData(status.getResponseCode());
            }else {
                bankTran.setApprovalDesc(status.getResponseCode());
                bankTran.setApprovalDesc(status.getWarningMessage());
                baseMsgDTO = BaseMsgDTO.failureData(bankTran.getApprovalDesc());
            }
        }

        bankTran.update();
        return new InvokeResult<>(baseMsgDTO);
    }
}
