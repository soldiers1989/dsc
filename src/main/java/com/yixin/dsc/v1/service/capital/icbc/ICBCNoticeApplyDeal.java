package com.yixin.dsc.v1.service.capital.icbc;

import java.util.Arrays;
import java.util.HashSet;
import java.util.List;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.dto.DscFlowResultForAlixDto;
import com.yixin.dsc.dto.DscSupplyDto;
import com.yixin.dsc.dto.DscSupplyFieldsDto;
import com.yixin.dsc.dto.rule.DscRuleDto;
import com.yixin.dsc.entity.rule.DscRule;
import com.yixin.dsc.enumpackage.DscAlixLinkEnum;
import com.yixin.dsc.enumpackage.DscRuleTypeEnum;
import com.yixin.dsc.service.impl.shunt.DscShuntImpl;
import com.yixin.dsc.service.rule.engine.RuleService;
import com.yixin.dsc.v1.service.common.NoticeApplyDeal;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.common.enums.CreditfrontResultEnum;
import com.yixin.kepler.core.domain.CommonDomainUtil;
import com.yixin.kepler.v1.common.constants.IcbcConstant;
import com.yixin.kepler.v1.common.constants.IcbcErrorConstant;
import com.yixin.kepler.v1.utils.JacksonUtils;

/**
 * 工行回调后处理通知提报端实现类
 * Package : com.yixin.dsc.v1.service.capital.icbc
 * @author YixinCapital -- chen.lin
 *		   2018年10月16日 上午11:12:57
 */
@Component("iCBCNoticeApplyDeal")
public class ICBCNoticeApplyDeal extends NoticeApplyDeal {

    private static final Logger LOGGER = LoggerFactory.getLogger(ICBCNoticeApplyDeal.class);

    @Resource
    private RuleService ruleService;

    @Resource
    private DscShuntImpl dscShunt;

    
    /**
     * 为提报方组装返回消息体
     * @return 
     * @author YixinCapital -- chen.lin
     *	       2018年10月16日 上午11:01:54
     */
    @Override
    public DscFlowResultForAlixDto deal() {
        DscFlowResultForAlixDto alixDto = new DscFlowResultForAlixDto();
        alixDto.setApplyNo(threadLocalApplyMain.get().getApplyNo());
        if (IcbcConstant.APPROVAL_CODE_SUCCESS.equals(threadLocalICBCFeedBack.get().getData().getApprovalcode())) {//成功
			//通过消息体
        	alixDto.setCode(CreditfrontResultEnum.SUCCESS.getCode());
            alixDto.setMessage(CreditfrontResultEnum.SUCCESS.getMsg());
		} else if(new HashSet<>(Arrays.asList(IcbcErrorConstant.REJECT_CODE)).contains(threadLocalICBCFeedBack.get().getData().getApprovalcode())){//驳回
			//驳回消息体
            alixDto.setCode(CreditfrontResultEnum.REJECT.getCode());
            alixDto.setMessage(CreditfrontResultEnum.REJECT.getMsg());
            alixDto.setRejectDetailMsg(CreditfrontResultEnum.REJECT_BANK_FILE.getCode(), threadLocalICBCFeedBack.get().getData().getApprovalmsg());
		} else if(new HashSet<>(Arrays.asList(IcbcErrorConstant.REFUSE_CODE)).contains(threadLocalICBCFeedBack.get().getData().getApprovalcode())){//拒绝
			//拒绝消息体
            alixDto.setCode(CreditfrontResultEnum.REFUSE.getCode());
            alixDto.setMessage(threadLocalICBCFeedBack.get().getData().getApprovalmsg());
        }
        if (IcbcConstant.TRXCODE_10201.equals(threadLocalICBCFeedBack.get().getComm().getTrxcode())){//信审回调
            alixDto.setLink(DscAlixLinkEnum.CREDITFRONT.getCode());
            if (IcbcConstant.APPROVAL_CODE_SUCCESS.equals(threadLocalICBCFeedBack.get().getData().getApprovalcode())) {//成功
            	// 信审回调如果通过则进行请款校验,将请款缺失的属性、文档回传给alix
            	checkRule(alixDto);
            }
        }else if(IcbcConstant.TRXCODE_40201.equals(threadLocalICBCFeedBack.get().getComm().getTrxcode())){//请款回调
            alixDto.setLink(DscAlixLinkEnum.REQUEST_FUNDS.getCode());
        }
		LOGGER.info("工商银行为提报方组装返回消息体完成，data:{}", JacksonUtils.fromObjectToJson(alixDto));
        return alixDto;
    }


    /**
     * 信审通过后，校验请款必要属性及附件
     * @param alixDto
     */
    private void checkRule(DscFlowResultForAlixDto alixDto){
        //====== 通过资方ID获取补充规则明细规则明细
        List<DscRuleDto> ruleDtoList = DscRule.getListByCapitalId(threadLocalApplyMain.get().getCapitalId(), DscRuleTypeEnum.REQUEST_FUNDS_CHECK.getType());
        //====== 校验规则并接收返回值，用以确定是否需要补充属性字段
        List<DscSupplyFieldsDto> fieldsDtoList = ruleService.supplyRuleMatchByFieldResult(ruleDtoList, dscShunt.getOrderDataSource(threadLocalApplyMain.get().getApplyNo()), DscRuleTypeEnum.REQUEST_FUNDS_CHECK.getType());
        if (null != fieldsDtoList && !fieldsDtoList.isEmpty()){
            alixDto.setNeedSupply(true);
            if (null == alixDto.getDscSupply()) {
                alixDto.setDscSupply(new DscSupplyDto().setSupplyFields(fieldsDtoList));
            } else {
                alixDto.getDscSupply().setFieldList(fieldsDtoList);
            }
        }

        //====== 校验文档是否完善(仅限于信审阶段校验)
        InvokeResult<DscSupplyDto> checkAttachmentFile = CommonDomainUtil.checkAttachmentFile(threadLocalApplyMain.get().getApplyNo(), BankPhaseEnum.PAYMENT);
        if (null != checkAttachmentFile && checkAttachmentFile.isHasErrors()){
            alixDto.setNeedSupply(true);
            if (null == alixDto.getDscSupply()) {
                alixDto.setDscSupply(new DscSupplyDto().setSupplyAttachments(((DscSupplyDto)checkAttachmentFile.getData()).getAttList()));
            } else {
                alixDto.getDscSupply().setAttList(((DscSupplyDto)checkAttachmentFile.getData()).getAttList());
            }
        }
    }
}
