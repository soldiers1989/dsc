package com.yixin.kepler.v1.service.capital.yntrust;

import com.alibaba.fastjson.JSON;
import com.yixin.common.exception.BzException;
import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.dto.DscBankContractDTO;
import com.yixin.dsc.dto.DscBankContractSignInfoDTO;
import com.yixin.dsc.util.PropertiesManager;
import com.yixin.dsc.v1.service.capital.yntrust.YTCommonService;
import com.yixin.kepler.common.RestTemplateUtil;
import com.yixin.kepler.common.UrlConstant;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.core.base.AbstractBaseDealDomain;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.enity.AssetBankTran;
import com.yixin.kepler.v1.common.enumpackage.YNTrustUrlEnum;
import com.yixin.kepler.v1.datapackage.dto.yntrust.YTCommonStatusDTO;
import com.yixin.kepler.v1.datapackage.dto.yntrust.YTContractRequestDTO;
import com.yixin.kepler.v1.datapackage.dto.yntrust.YTGetContractRespDTO;
import net.sf.json.JSONObject;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;

/**
 * 签约接口，同步四要素信息生成协议流水号
 * @author sukang
 */
@Service(value = "YNTRUSTBankContractStrategy")
public class BankContractStrategy extends AbstractBaseDealDomain<DscBankContractDTO,BaseMsgDTO>{


    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Resource
    private PropertiesManager propertiesManager;

    @Resource
    private YTCommonService commonService;

    private ThreadLocal<YTContractRequestDTO> ytContractRequestDtoThreadLocal = new ThreadLocal<>();

    @Override
    protected void getData() throws BzException {

        DscBankContractDTO dscBankContractDTO = inputDto.get();

        DscBankContractSignInfoDTO signInfo = dscBankContractDTO.getSignInfo();

        YTContractRequestDTO ytContractRequestDto = new YTContractRequestDTO();
        ytContractRequestDto.setUrl(YNTrustUrlEnum.GET_BANK_CONTRACT_ID.getUrl());
        ytContractRequestDto.setRequestId(commonService.getRequestId());
        ytContractRequestDto.setName("");
        ytContractRequestDto.setIDCardNO("");
        ytContractRequestDto.setChannel("");
        ytContractRequestDto.setBankCode(signInfo.getBankCode());
        ytContractRequestDto.setTelephoneNo(signInfo.getBankReservePhone());
        ytContractRequestDto.setAccountNo(signInfo.getAccountNo());
        ytContractRequestDto.setProductCode("");
        ytContractRequestDtoThreadLocal.set(ytContractRequestDto);
    }

    @Override
    protected void assembler() throws BzException {
    }

    @Override
    protected InvokeResult<BaseMsgDTO> message() throws BzException {

        YTContractRequestDTO ytContractRequestDto = ytContractRequestDtoThreadLocal.get();
        DscBankContractDTO dscBankContractDTO = inputDto.get();

        ytContractRequestDtoThreadLocal.remove();
        inputDto.remove();
        AssetBankTran bankTran = new AssetBankTran();
        bankTran.setApplyNo(dscBankContractDTO.getApplyNo());
        bankTran.setPhase(BankPhaseEnum.PAYMENT.getPhase().concat("GetBankContract"));
        bankTran.setReqBody(JSONObject.fromObject(ytContractRequestDto).toString());

        String url = propertiesManager.getOsbEnvironment()
                .concat(UrlConstant.OsbSystemUrl.ynTrustFileInterface);

        bankTran.setReqUrl(url);
        bankTran.setTranNo(ytContractRequestDto.getRequestId());
        bankTran.setSender(CommonConstant.SenderType.YIXIN);
        bankTran.create();

        logger.info("订单号{}请求签约接口请求路径为{},报文为{}",
                dscBankContractDTO.getApplyNo(),
                bankTran.getReqUrl(),bankTran.getReqBody());


        String result = RestTemplateUtil.sendRequest(bankTran.getReqUrl(),
                ytContractRequestDto, CommonConstant.BankName.YNTRUST_BANK);
        bankTran.setRepBody(result);

        logger.info("订单{}签约接口返回报文为{}",dscBankContractDTO.getApplyNo(),
                result);

        BaseMsgDTO baseMsgDTO = BaseMsgDTO.failureData("接口异常");
        if (StringUtils.isNotEmpty(result)){
            YTGetContractRespDTO ytGetContractRespDTO = JSON.parseObject(result,
                    YTGetContractRespDTO.class);

            YTCommonStatusDTO status = ytGetContractRespDTO.getStatus();
            if (status.success()){

                bankTran.setApprovalCode(status.getResponseCode());
                bankTran.setApprovalDesc(status.getResponseMessage());
                baseMsgDTO = BaseMsgDTO.successData(
                        ytGetContractRespDTO.getTransactionNo());
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
