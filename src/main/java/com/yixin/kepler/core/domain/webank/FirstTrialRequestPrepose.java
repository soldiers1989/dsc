package com.yixin.kepler.core.domain.webank;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yixin.common.exception.BzException;
import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.common.DscContant;
import com.yixin.dsc.dto.DscAdmittanceDto;
import com.yixin.dsc.dto.DscBankCardDto;
import com.yixin.dsc.dto.DscCapitalSupportDto;
import com.yixin.dsc.dto.DscFlowResultForAlixDto;
import com.yixin.dsc.entity.order.DscSalesApplyCust;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.dsc.enumpackage.DscAdmittanceEnum;
import com.yixin.dsc.enumpackage.DscAlixLinkEnum;
import com.yixin.dsc.service.common.DscQueryService;
import com.yixin.kepler.common.enums.AssetPhaseEnum;
import com.yixin.kepler.common.enums.AssetStateEnum;
import com.yixin.kepler.common.enums.CreditfrontResultEnum;
import com.yixin.kepler.core.base.AbstractBaseDealDomain;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.core.listener.ResultNotifyEvent;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.dto.webank.WBMongoDTO;
import com.yixin.kepler.enity.AssetMainInfo;
import com.yixin.kepler.enity.SysCardBin;

/**
 * 微众一审前置处理
 * Package : com.yixin.kepler.core.domain.webank
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月20日 下午1:13:20
 *
 */
@Service("WeBankFirstTrialRequestPrepose")
public class FirstTrialRequestPrepose extends AbstractBaseDealDomain<Object, BaseMsgDTO>{

	public static final Logger LOGGER = LoggerFactory.getLogger(FirstTrialRequestPrepose.class);
	
	@Autowired
	private MongoTemplate mongoTemplate;
	
	@Resource
	private DscQueryService dscQueryService;
	
	@Autowired
	private ApplicationContext ioc;
	
	@Override
	protected void getData() throws BzException {
	}

	@Override
	protected void assembler() throws BzException {
	}

	@Override
	protected InvokeResult<BaseMsgDTO> message() throws BzException {
		return null;
	}
	
	/**
	 * 银行请求前置
	 * @param applyNo 申请编号
	 * @param financeCode 资方编码
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年7月16日 下午7:18:17
	 */
	public BaseMsgDTO requestPrepose(String applyNo,String financeCode){
		DscSalesApplyMain mainInfo = DscSalesApplyMain.getByApplyNo(applyNo);
		if(mainInfo == null){
			LOGGER.error("申请单号为{},申请编号错误，查询不到订单信息",applyNo);
        	return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "申请编号错误，查询不到订单信息");
        }

		/*LOGGER.info("微众银行一审前置处理，检查信审阶段的附件是否上传完成开始.订单编号:{}",applyNo);
		BaseMsgDTO checkFile = CommonDomainUtil.checkAttachmentFile(applyNo, BankPhaseEnum.LAST_TRIAL);
		LOGGER.info("微众银行一审前置处理，检查信审阶段的附件是否上传完成结束.订单编号:{},检查结果:{}",
				applyNo, JSONObject.fromObject(checkFile).toString());

		if(!checkFile.isSuccess()){
			//LOGGER.info("微众银行一审前置处理，检查信审阶段的附件,未上传完成.订单编号:{},为alix通过MQ推送结果  100002/信审驳回 ,",applyNo);
			ioc.publishEvent(new ResultNotifyEvent(
					DscFlowResultForAlixDto.createForAliDto(
							applyNo,false,checkFile.getMessage(),DscAlixLinkEnum.CREDITFRONT)));
			return BaseMsgDTO.successData("信审请求收妥成功");
		}*/


		//=============== step1 校验还款卡开始 ==================
		DscAdmittanceDto queryParam = new DscAdmittanceDto();
		queryParam.setApplyNo(applyNo);
		queryParam.setCapitalCode(financeCode);
		queryParam.setDataType(DscAdmittanceEnum.BANK_CARD_LIST.getValue());
		DscCapitalSupportDto supportDto = dscQueryService.getCapitalSupportData(queryParam);
		List<DscBankCardDto> bankCardList = supportDto.getBankCardList();
		boolean isSupportBankCard = false;
		if(CollectionUtils.isNotEmpty(bankCardList)){
			SysCardBin cardBin = SysCardBin.getCardBin(mainInfo.getAhkrjjkzh(), CommonConstant.BankName.WB_BANK);
			if(cardBin !=null){
				for(DscBankCardDto cardDto:bankCardList){
					if(cardDto.getCode().equals(cardBin.getCardCode())){
						isSupportBankCard = true;
					}
				}
			} else {
				isSupportBankCard = false;
			}
			
		} else {
			isSupportBankCard = false;
		}
		if(!isSupportBankCard){
			LOGGER.error("申请单号为{},一审前置提交信审校验不通过,扣款卡不在微众支持的银行列表中,推送Alix结果: 100001/信审拒绝",applyNo);
			ioc.publishEvent(new ResultNotifyEvent(this.createForAliDto(applyNo, CreditfrontResultEnum.REFUSE)));
			return new BaseMsgDTO(DscContant.KeplerCode.SUCCESS, "信审请求收妥成功");
		}
		//=============== step1 校验还款卡结束 ==================
		Query query = new Query();
		query.addCriteria(Criteria.where("applyNo").is(applyNo));
		List<WBMongoDTO> mongoDTOList = mongoTemplate.findAllAndRemove(query, WBMongoDTO.class);
		// PS:WBMongoDTO 的信息，一审成功后存进去，，所以只要存在mongo信息存在就代表一审通过了，就不需要再走银行一审
		LOGGER.info("申请单号为{},提交信审校验,查询mongo中订单信息：{}",applyNo,JSON.toJSONString(mongoDTOList));
		 
		AssetMainInfo assetMainInfo = AssetMainInfo.getByApplyNo(applyNo);
		if(assetMainInfo == null){
			if(CollectionUtils.isEmpty(mongoDTOList)){ //mono中信息为空
				return new BaseMsgDTO(CommonConstant.PreposeResultCode.NORMAL, "正常流转");
			} else { //mono中信息不为空
				LOGGER.info("申请单号为{},提交信审校验,mono中信息不为空，alix取消订单重新发起信审",applyNo);
				assetMainInfo = this.createAssertMainInfo(mainInfo,mongoDTOList);
			}
		}
		if(AssetStateEnum.DOING.getState().equals(assetMainInfo.getFirstState())){ //一审进行中
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "正在信审中,不可重复提交信审");
		}
		if(AssetPhaseEnum.PAYMENT_TRIAL.getPhase().equals(assetMainInfo.getAssetPhase())){ //请款阶段
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "已完成信审,不可再次发起信审请求");
		}
		
		if(AssetStateEnum.SUCCESS.getState().equals(assetMainInfo.getFirstState()) //一审成功
				&& AssetStateEnum.INIT.getState().equals(assetMainInfo.getLastState())){ //二审成功
			LOGGER.info("微众银行一审前置处理-一审和二审都请求成功，再次发起信审直接跳转二审，不在请求一审. 申请编号:{}",applyNo);
			//================== 进行校验四要素逻辑 =========================
			if(CollectionUtils.isEmpty(mongoDTOList)){
				LOGGER.error("申请单号为{},一审交互信息未查询到",applyNo);
				return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "一审交互信息未查询到");
			}
			WBMongoDTO wbMongoDTO = mongoDTOList.get(0);
			DscSalesApplyCust applyCust = DscSalesApplyCust.getOneByMainId(mainInfo.getId());
			if(applyCust == null){
				LOGGER.error("申请单号为{},申请编号错误，客户信息不存在,为alix通过MQ推送结果  100002/信审驳回 ",applyNo);
				ioc.publishEvent(new ResultNotifyEvent(this.createForAliDto(applyNo, CreditfrontResultEnum.REJECT)));
				return new BaseMsgDTO(DscContant.KeplerCode.SUCCESS, "信审请求收妥成功");
	        }
			if(!wbMongoDTO.getAkhxm().equals(applyCust.getAkhxm())){
				LOGGER.error("申请单号为{},重复提交信审校验不通过,申请人姓名不一致,为alix通过MQ推送结果  100002/信审驳回 ",applyNo);
				ioc.publishEvent(new ResultNotifyEvent(this.createForAliDto(applyNo, CreditfrontResultEnum.REJECT)));
				return new BaseMsgDTO(DscContant.KeplerCode.SUCCESS, "信审请求收妥成功");
			}
			if(!wbMongoDTO.getAzjhm().equals(applyCust.getAzjhm())){
				LOGGER.error("申请单号为{},重复提交信审校验不通过,身份证号码不一致,为alix通过MQ推送结果  100002/信审驳回 ",applyNo);
				ioc.publishEvent(new ResultNotifyEvent(this.createForAliDto(applyNo, CreditfrontResultEnum.REJECT)));
				return new BaseMsgDTO(DscContant.KeplerCode.SUCCESS, "信审请求收妥成功");
			}
			if(!wbMongoDTO.getAsjhm().equals(applyCust.getAsjhm())){
				LOGGER.error("申请单号为{},重复提交信审校验不通过,注册手机号不一致,为alix通过MQ推送结果  100002/信审驳回 ",applyNo);
				ioc.publishEvent(new ResultNotifyEvent(this.createForAliDto(applyNo, CreditfrontResultEnum.REJECT)));
				return new BaseMsgDTO(DscContant.KeplerCode.SUCCESS, "信审请求收妥成功");
			}
			if(!wbMongoDTO.getAhkrjjkzh().equals(mainInfo.getAhkrjjkzh())){ //易鑫验证四要素还款卡号
				LOGGER.error("申请单号为{},重复提交信审校验不通过,易鑫验证四要素还款卡号不一致,为alix通过MQ推送结果  100002/信审驳回 ",applyNo);
				ioc.publishEvent(new ResultNotifyEvent(this.createForAliDto(applyNo, CreditfrontResultEnum.REJECT)));
				return new BaseMsgDTO(DscContant.KeplerCode.SUCCESS, "信审请求收妥成功");
			}
			if(!wbMongoDTO.getAckrsjhm().equals(mainInfo.getAckrsjhm())){ //易鑫验证四要素银行预留手机号码
				LOGGER.error("申请单号为{},重复提交信审校验不通过,易鑫验证四要素银行预留手机号码不一致,为alix通过MQ推送结果  100002/信审驳回 ",applyNo);
				ioc.publishEvent(new ResultNotifyEvent(this.createForAliDto(applyNo, CreditfrontResultEnum.REJECT)));
				return new BaseMsgDTO(DscContant.KeplerCode.SUCCESS, "信审请求收妥成功");
			}
			wbMongoDTO.setSkipToLast(CommonConstant.TRUE); //标记直接跳转到二审，在二审成功后向alix返回信息
			mongoTemplate.save(wbMongoDTO);
			LOGGER.info("申请单号为{},一审前置提交信审校验,直接跳转二审更新标记重新保存mongo信息：{}",applyNo,JSON.toJSONString(wbMongoDTO));
			return new BaseMsgDTO(CommonConstant.PreposeResultCode.SKIP, "跳转");
		}
		return new BaseMsgDTO(CommonConstant.PreposeResultCode.NORMAL, "正常流转");
	};
	
	private AssetMainInfo createAssertMainInfo(DscSalesApplyMain applyMainInfo,List<WBMongoDTO> mongoDTOList) {
		WBMongoDTO wbMongoDTO = mongoDTOList.get(0);
		
		AssetMainInfo mainInfo = new AssetMainInfo();
        mainInfo.setApplyNo(applyMainInfo.getApplyNo()); // 申请编号
        mainInfo.setAssetPhase(AssetPhaseEnum.TRIAL.getPhase());
        mainInfo.setCreditSignState(AssetStateEnum.SUCCESS.getState());
        mainInfo.setContractSignState(AssetStateEnum.INIT.getState());
		if (StringUtils.isNotBlank(mongoDTOList.get(0).getAppNo())) {
			//appNo不为空，说明之前取消的订单已经通过一审
			mainInfo.setFirstState(AssetStateEnum.SUCCESS.getState());
		} else {
			mainInfo.setFirstState(AssetStateEnum.INIT.getState());
		}
		mainInfo.setLastState(AssetStateEnum.INIT.getState());
		mainInfo.setPreState(null);
        mainInfo.setPaymentState(AssetStateEnum.INIT.getState());
        mainInfo.setAssetNo(wbMongoDTO.getAppNo()); //微众申请编号
        mainInfo.setBankOrderNo(wbMongoDTO.getNbsOrderNo()); //微众订单编号
        mainInfo.setChannelCode(applyMainInfo.getDealerChannelCode()); // 渠道编号
        mainInfo.setChannelName(applyMainInfo.getDealerChannelName()); // 渠道名称
        mainInfo.setCompanyCode(applyMainInfo.getRentingCompanyCode()); // 发起融资公司编号
        mainInfo.setFinancialCode(CommonConstant.BankName.WB_BANK); //微众
        mainInfo.setFinancialId(applyMainInfo.getCapitalId());
        mainInfo.create();
        return mainInfo;
	}
	
	
	
	private DscFlowResultForAlixDto createForAliDto(String applyNo,CreditfrontResultEnum resultEnum) {
		
		DscFlowResultForAlixDto alixDto = new DscFlowResultForAlixDto();
		alixDto.setApplyNo(applyNo);
		alixDto.setCode(resultEnum.getCode());
		alixDto.setLink(DscAlixLinkEnum.CREDITFRONT.getCode());
		alixDto.setMessage(resultEnum.getMsg());
		return alixDto;
	}
}
