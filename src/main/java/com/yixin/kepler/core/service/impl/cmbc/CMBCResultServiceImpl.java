package com.yixin.kepler.core.service.impl.cmbc;

import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.dto.DscFlowResultForAlixDto;
import com.yixin.dsc.enumpackage.DscAlixLinkEnum;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.core.domain.cmbc.SelectResultStrategy;
import com.yixin.kepler.core.service.BankResultService;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.enity.AssetBankTran;
import com.yixin.kepler.enity.AssetResultTask;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author sukang  on 2018/8/31.
 */
@Service("CMBCResultService")
public class CMBCResultServiceImpl implements BankResultService{

    private final Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * 民生银行查询交易结果策略
     */
    private SelectResultStrategy selectResultStrategy;

    @Autowired
    public CMBCResultServiceImpl(SelectResultStrategy selectResultStrategy) {
        this.selectResultStrategy = selectResultStrategy;
    }

    @Override
    public BaseMsgDTO selectResult(AssetResultTask assetResultTask) {

        InvokeResult<BaseMsgDTO> result = selectResultStrategy.doIt(assetResultTask);

        //获取该订单的银行交易记录
        AssetBankTran bankTran = AssetBankTran.get(AssetBankTran.class,
                assetResultTask.getTranId());
        logger.info("订单号{},阶段为{},获取到的任务执行结果为{}",assetResultTask.getApplyNo()
                            ,bankTran.getPhase(), JSONObject.fromObject(result).toString());

        //民生银行信审结果发送alix结果通知
        BaseMsgDTO baseMsgDTO = (BaseMsgDTO) result.getData();

        DscFlowResultForAlixDto dscFlowResultForAlixDto = null;
        if (baseMsgDTO.successed()){
            dscFlowResultForAlixDto = createForAliDto(bankTran,true,"success");
        }else if (CommonConstant.FAILURE.equals(baseMsgDTO.getCode())){
            dscFlowResultForAlixDto = createForAliDto(bankTran,false,baseMsgDTO.getMessage());
        }

        if (dscFlowResultForAlixDto != null){
            publishEvent(dscFlowResultForAlixDto);
        }
        return baseMsgDTO;
    }




    private DscFlowResultForAlixDto createForAliDto(AssetBankTran bankTran,
                                                    Boolean isSuccess, String msg) {

        if (BankPhaseEnum.PAYMENT.getPhase().equals(bankTran.getPhase())) {
            return null;
        }
        return DscFlowResultForAlixDto.createForAliDto(bankTran.getApplyNo(),
                isSuccess, msg, DscAlixLinkEnum.CREDITFRONT);
    }



}




















