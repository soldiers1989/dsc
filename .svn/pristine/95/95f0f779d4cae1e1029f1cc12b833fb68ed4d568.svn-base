package com.yixin.kepler.core.domain.webank;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yixin.common.exception.BzException;
import com.yixin.common.utils.InvokeResult;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.dsc.entity.order.DscFileAttachment;
import com.yixin.dsc.entity.order.DscSalesApplyCar;
import com.yixin.dsc.entity.order.DscSalesApplyCust;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.dsc.service.common.DscWbCommonService;
import com.yixin.dsc.util.PropertiesManager;
import com.yixin.kepler.common.RestTemplateUtil;
import com.yixin.kepler.common.UrlConstant;
import com.yixin.kepler.common.enums.AssetPhaseEnum;
import com.yixin.kepler.common.enums.AssetStateEnum;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.common.enums.WBTransCodeEnum;
import com.yixin.kepler.core.base.AbstractBaseDealDomain;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.dto.webank.WBPickUpCarDTO;
import com.yixin.kepler.dto.webank.WBPickUpCarProofsDTO;
import com.yixin.kepler.enity.AssetAttachmentRule;
import com.yixin.kepler.enity.AssetBankTran;
import com.yixin.kepler.enity.AssetMainInfo;

/**
 * 请求银行贷后资料同步策略实现类-微众
 * @author YixinCapital -- chenjiacheng
 *         2018年07月12日 16:35
 **/
@Service("WeBankAfterLoanRequestStrategy")
public class WBAfterLoanRequestStrategy extends AbstractBaseDealDomain<Object, BaseMsgDTO> {

	private static final Logger logger = LoggerFactory.getLogger(WBAfterLoanRequestStrategy.class);


	private ThreadLocal<AssetMainInfo> paymentThreadLocal = new ThreadLocal<>();

	@Resource
	private PropertiesManager propertiesManager;

	@Resource
	private DscWbCommonService dscWbCommonService;


	@Override
	protected void getData() throws BzException {
		String applyNo = (String) inputDto.get();
		logger.info("订单提车===开始组装数据====applyNo={}", applyNo);
		DscSalesApplyMain main = DscSalesApplyMain.getByApplyNo(applyNo);
		if (main == null) {
			throw new BzException("客户申请数据为空");
		}
		DscSalesApplyCust cust = DscSalesApplyCust.getApplyCostByMianId(main.getId());
		if (cust == null) {
			throw new BzException("客户信息不存在");
		}
		DscSalesApplyCar car = DscSalesApplyCar.getBymainId(main.getId());
		AssetMainInfo assetMainInfo = AssetMainInfo.getByApplyNo(applyNo);
		List<DscFileAttachment> attachmentList = DscFileAttachment.lists(main.getId());
		paymentThreadLocal.set(assetMainInfo);

		WBPickUpCarDTO wbPickUpCarDTO = new WBPickUpCarDTO();

		//业务报文头
		wbPickUpCarDTO.setTxnId(WBTransCodeEnum.WB_PICKUP_CAR.getTransCode());//交易服务码
		wbPickUpCarDTO.setMerchantId(dscWbCommonService.getMerchantIdByCompanyCode(main.getRentingCompanyCode())); //平台id
		wbPickUpCarDTO.setOpId(main.getTlrNo()); //操作员号
		wbPickUpCarDTO.setChannel(dscWbCommonService.getWbChannelByOrderSource(main.getOrderSource())); //渠道
		wbPickUpCarDTO.setReqTime(new Date()); //请求时间

		//订单业务主键
		wbPickUpCarDTO.setMerOrderNo(main.getApplyNo()); //平台订单号
		wbPickUpCarDTO.setNbsOrderNo(assetMainInfo.getBankOrderNo()); //微众订单号
		wbPickUpCarDTO.setPsCode(dscWbCommonService.getPsCodeByApplyNo(main.getApplyNo())); //产品结构编号
		wbPickUpCarDTO.setName(cust.getAkhxm()); //姓名
		wbPickUpCarDTO.setIdType(dscWbCommonService.codeConvert("azjlx", cust.getAzjlx())); //证件类型
		wbPickUpCarDTO.setIdNo(cust.getAzjhm()); //证件号码

		//订单车辆主键
		//wbPickUpCarDTO.setCarId(car.getCarId()); //车辆id
		//modify by wangwenlong on 2018-09-28 贷后阶段车辆ID 未取第四级ID
		wbPickUpCarDTO.setCarId(dscWbCommonService.getSpiltCarId(car.getCarId())); //车辆id
		wbPickUpCarDTO.setVehicleId(car.getAcjh()); //车架号

		//交易信息域
		wbPickUpCarDTO.setTxnCode("TC010"); //交易码-抵押
		wbPickUpCarDTO.setTxnDesc("抵押成功提车"); //交易描述
		wbPickUpCarDTO.setTxnDate(new Date()); //交易时间

		//抵押信息域
		wbPickUpCarDTO.setMortgageOwner(main.getAdyqr()); //押权所有人
		wbPickUpCarDTO.setMortgageCity(main.getAcldycs()); //抵押办理城市
		wbPickUpCarDTO.setRegistrateTime(car.getRegistrateTime()); //上牌日期
		wbPickUpCarDTO.setPlateNumber(car.getAcarnojc()); //车牌号
		wbPickUpCarDTO.setMortgageRegDate(car.getMortgageRegDate()); //抵押登记日期
		wbPickUpCarDTO.setIsMortgageReg("Y"); //抵押完成指令
		wbPickUpCarDTO.setEngineCode(car.getAfdjh()); //发动机号

		List<AssetAttachmentRule> ruleList = AssetAttachmentRule.getAttrRule(CommonConstant.BankName.WB_BANK);
		if (CollectionUtils.isNotEmpty(attachmentList) && CollectionUtils.isNotEmpty(ruleList)) {
			List<WBPickUpCarProofsDTO> proofsDTOList = new ArrayList<>();
			for (DscFileAttachment attachment : attachmentList) {
				for (AssetAttachmentRule rule : ruleList) {
					WBPickUpCarProofsDTO proofsDTO = new WBPickUpCarProofsDTO();
					if (attachment.getAttachSubClass().equals(rule.getAttachMainType())) {
						proofsDTO.setProofType(rule.getNameFormat()==null? null : rule.getNameFormat().substring(3,6)); //材料类型
						proofsDTO.setProofId(rule.getNameFormat()==null? null : rule.getNameFormat().substring(3,6));//材料编号
						proofsDTOList.add(proofsDTO);
						break;
					}
				}
			}
			wbPickUpCarDTO.setProofs(proofsDTOList);
		}

		inputDto.set(wbPickUpCarDTO);
		logger.info("数据组装完毕,applyNo={},dto={}", applyNo, JSON.toJSONString(wbPickUpCarDTO));
	}


	@Override
	protected void assembler() throws BzException {
		//WBPickUpCarDTO pickUpCarDTO = (WBPickUpCarDTO) inputDto.get();
		//inputDto.remove();
		//
		//if (StringUtils.isBlank(pickUpCarDTO.getTxnId())) {
		//	throw new BzException("交易服务码为空");
		//}
		//if (StringUtils.isBlank(pickUpCarDTO.getMerchantId())) {
		//	throw new BzException("平台id为空");
		//}
		//if (StringUtils.isBlank(pickUpCarDTO.getOpId())) {
		//	throw new BzException("操作号为空");
		//}
		//if (StringUtils.isBlank(pickUpCarDTO.getChannel())) {
		//	throw new BzException("渠道为空");
		//}
		//if (pickUpCarDTO.getReqTime() == null) {
		//	throw new BzException("请求时间为空");
		//}
		//if (StringUtils.isBlank(pickUpCarDTO.getMerOrderNo())) {
		//	throw new BzException("平台订单号为空");
		//}
		//if (StringUtils.isBlank(pickUpCarDTO.getNbsOrderNo())) {
		//	throw new BzException("微众订单号为空");
		//}
		//if (StringUtils.isBlank(pickUpCarDTO.getPsCode())) {
		//	throw new BzException("产品结构编号");
		//}
		//if (StringUtils.isBlank(pickUpCarDTO.getName())) {
		//	throw new BzException("姓名为空");
		//}
		//if (StringUtils.isBlank(pickUpCarDTO.getIdType())) {
		//	throw new BzException("证件类型为空");
		//}
		//if (StringUtils.isBlank(pickUpCarDTO.getIdNo())) {
		//	throw new BzException("证件号码为空");
		//}
		//if (StringUtils.isBlank(pickUpCarDTO.getCarId())) {
		//	throw new BzException("车辆id为空");
		//}
		//if (StringUtils.isBlank(pickUpCarDTO.getVehicleId())) {
		//	throw new BzException("车架号为空");
		//}
		//if (StringUtils.isBlank(pickUpCarDTO.getTxnCode())) {
		//	throw new BzException("交易码为空");
		//}
		//if (StringUtils.isBlank(pickUpCarDTO.getTxnDesc())) {
		//	throw new BzException("交易描述为空");
		//}
		//if (pickUpCarDTO.getTxnDate() == null) {
		//	throw new BzException("交易时间为空");
		//}
		//if (StringUtils.isBlank(pickUpCarDTO.getMortgageOwner())) {
		//	throw new BzException("押权所有人为空");
		//}
		//if (StringUtils.isBlank(pickUpCarDTO.getMortgageCity())) {
		//	throw new BzException("抵押办理城市为空");
		//}
		//if ("TC010".equals(pickUpCarDTO.getTxnCode())) {
		//	if (StringUtils.isBlank(pickUpCarDTO.getPlateNumber())) {
		//		throw new BzException("车牌号为空");
		//	}
		//	if (pickUpCarDTO.getMortgageRegDate() == null) {
		//		throw new BzException("抵押登记日期为空");
		//	}
		//	if (StringUtils.isBlank(pickUpCarDTO.getIsMortgageReg())) {
		//		throw new BzException("抵押登记完成指令为空");
		//	}
		//	if (StringUtils.isBlank(pickUpCarDTO.getEngineCode())) {
		//		throw new BzException("发动机号为空");
		//	}
		//}
		//if (CollectionUtils.isEmpty(pickUpCarDTO.getProofs())) {
		//	throw new BzException("材料清单为空");
		//}
		//
		//inputDto.set(pickUpCarDTO);
	}


	@Override
	protected InvokeResult<BaseMsgDTO> message() throws BzException {
		AssetMainInfo assetMainInfo = paymentThreadLocal.get();
		WBPickUpCarDTO wbPickUpCarDTO = (WBPickUpCarDTO) inputDto.get();
		paymentThreadLocal.remove();
		inputDto.remove();

		String osbReqUrl = propertiesManager.getOsbEnvironment() + UrlConstant.OsbSystemUrl.weBankInterface + WBTransCodeEnum.WB_PICKUP_CAR.getTransCode();

		AssetBankTran assetBankTran = new AssetBankTran();
		//添加银行交易信息
		assetBankTran.setReqBody(JsonObjectUtils.objectToJson(wbPickUpCarDTO));
		assetBankTran.setApplyNo(assetMainInfo.getApplyNo());
		assetBankTran.setAssetId(assetMainInfo.getId());
		assetBankTran.setApiCode(wbPickUpCarDTO.getTxnCode());

		//资产编号对应银行交易流水
		assetBankTran.setAssetNo(assetMainInfo.getAssetNo());
		assetBankTran.setReqUrl(osbReqUrl);
		assetBankTran.setPhase(BankPhaseEnum.AFTER_LOAN.getPhase());
		assetBankTran.setTranNo(wbPickUpCarDTO.getNbsOrderNo());
		assetBankTran.setSender(CommonConstant.SenderType.YIXIN);
		assetBankTran.create();

		logger.info("微众银行向银行发起提车请求开始,订单编号：{},url:{},入参:{}", assetMainInfo.getApplyNo(),osbReqUrl, assetBankTran.getReqBody());
		String result = RestTemplateUtil.sendRequest(osbReqUrl, wbPickUpCarDTO, assetMainInfo.getFinancialCode());
		logger.info("微众银行向银行发起提车请求结束,申请编号：{},银行返回:{},", assetMainInfo.getApplyNo(),result);

		assetBankTran.setRepBody(result);
		JSONObject resultJson = JSONObject.parseObject(result);
		if (resultJson != null) {
			if (!resultJson.getString("code").contains(CommonConstant.WB_RESP_CODE)) {
				assetMainInfo.setAfterLoanState(AssetStateEnum.FAILD.getState());
			} else{
				assetMainInfo.setAfterLoanState(AssetStateEnum.SUCCESS.getState());
				assetMainInfo.setAssetPhase(AssetPhaseEnum.AFTER_LOAN.getPhase());
				assetMainInfo.update();
			}
			assetBankTran.setApprovalCode(resultJson.getString("code"));
			assetBankTran.setApprovalDesc(resultJson.getString("msg"));
			assetBankTran.update();
			return new InvokeResult<>(new BaseMsgDTO(CommonConstant.SUCCESS,"success"));
		}
		return new InvokeResult<>(new BaseMsgDTO(CommonConstant.FAILURE,"failure"));
	}
}
