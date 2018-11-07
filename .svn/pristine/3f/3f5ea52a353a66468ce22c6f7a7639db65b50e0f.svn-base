package com.yixin.kepler.v1.service.capital.yntrust;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Lists;
import com.yixin.common.exception.BzException;
import com.yixin.common.utils.InvokeResult;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.dsc.common.DscContant;
import com.yixin.dsc.dto.DscCreditfrontBusinessDto;
import com.yixin.dsc.dto.DscFlowResultForAlixDto;
import com.yixin.dsc.dto.DscLoanRepayDTO;
import com.yixin.dsc.dto.own.YNTrustMaintenanceDTO;
import com.yixin.dsc.entity.order.DscRepayPlan;
import com.yixin.dsc.entity.order.DscSalesApplyCost;
import com.yixin.dsc.entity.order.DscSalesApplyCust;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.dsc.enumpackage.DscAlixLinkEnum;
import com.yixin.dsc.util.DateUtil;
import com.yixin.dsc.util.PropertiesManager;
import com.yixin.dsc.v1.service.capital.yntrust.YTCommonService;
import com.yixin.kepler.common.RestTemplateUtil;
import com.yixin.kepler.common.UrlConstant;
import com.yixin.kepler.common.enums.AssetBankTransEnum;
import com.yixin.kepler.common.enums.AssetStateEnum;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.common.enums.CreditfrontResultEnum;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.core.listener.ResultNotifyEvent;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.enity.AssetBankTran;
import com.yixin.kepler.enity.AssetMainInfo;
import com.yixin.kepler.v1.common.constants.BankRequestConstant;
import com.yixin.kepler.v1.common.core.bankReq.BankRequestDataHandle;
import com.yixin.kepler.v1.common.enumpackage.YNTrustUrlEnum;
import com.yixin.kepler.v1.datapackage.dto.yntrust.YTCommonResponseDTO;
import com.yixin.kepler.v1.datapackage.dto.yntrust.YTCommonStatusDTO;
import com.yixin.kepler.v1.datapackage.dto.yntrust.YTCreateOrderResponseDto;
import com.yixin.kepler.v1.datapackage.dto.yntrust.YTImportProtocolInfoDetailDTO;
import com.yixin.kepler.v1.datapackage.dto.yntrust.YTImportProtocolRequestDTO;
import com.yixin.kepler.v1.datapackage.dto.yntrust.YTLoanRepaySchedulesDTO;
import com.yixin.kepler.v1.datapackage.dto.yntrust.YTPaymentBankResultRequestDTO;
import com.yixin.kepler.v1.datapackage.dto.yntrust.YTQueryRepayScheduleDTO;
import com.yixin.kepler.v1.datapackage.dto.yntrust.YTRepaySchedulesDTO;
import com.yixin.kepler.v1.datapackage.dto.yntrust.YTRepaySchedulesReqDTO;
import com.yixin.kepler.v1.datapackage.entity.AssetBankRequest;

/**
 * 云南信托数据处理类
 * Package : com.yixin.kepler.v1.service.capital.yntrust
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年9月27日 下午1:47:46
 *
 */
@Service("YNTRUSTRequestDataHandle")
public class YTRequestDataHandle implements BankRequestDataHandle {

	private static final Logger LOGGER = LoggerFactory.getLogger(YTRequestDataHandle.class);
	
	@Resource
    private YTCommonService commonService;
	
	
	@Resource
	private ModifyRepayStrategy ytModifyRepayStrategy;
	
	@Resource
	private YTRequestDataHandle requestDataHandle;
	
	@Resource
    private PropertiesManager propertiesManager;
	
	@Resource
	private ApplicationContext ioc;
	
	/**
	 *  还款计划处理
	 * @author YixinCapital -- wangwenlong
	 *	       2018年9月27日 上午11:43:32
	 */
	@Transactional
	public void repayPlanHandle(List<DscLoanRepayDTO> repayPlanList,AssetMainInfo assetMainInfo,DscSalesApplyMain saleMain){
		if(CollectionUtils.isNotEmpty(repayPlanList)){
			//校验还款计划总数与订单期数是否一致，还款计划总金额和订单融资额是否一致
			DscSalesApplyCost cost = DscSalesApplyCost.getByMainId(saleMain.getId());
			
			BigDecimal frze = cost.getFrze(); //客户融资额
			Integer arzqx = Integer.valueOf(saleMain.getArzqx()); //融资期限
			
			Integer total = 0;
			BigDecimal totalAmount = BigDecimal.ZERO;
			for(DscLoanRepayDTO repayDto:repayPlanList){
				if(repayDto.getTerm() != 0){
					total +=1;
					totalAmount = totalAmount.add(repayDto.getRepayPrincipal());
				}
			}
			if(total != arzqx){
				throw new BzException("还款计划总期数与订单融资期限不一致");
			}
			if(totalAmount.compareTo(frze) != 0 ){
				throw new BzException("还款计划本金总和与订单客户融资额不一致");
			}
			
			//逻辑删除还款计划
			DscRepayPlan.logicRemove(assetMainInfo.getApplyNo());
			
			//==================== 还款计划处理 开始======================
			DscRepayPlan repayPlan = null;
			for(DscLoanRepayDTO repayDto:repayPlanList){
				if(repayDto.getTerm()==null){
					throw new BzException("期数为空");
				}
				if(repayDto.getRepayPrincipal() == null){
					throw new BzException(String.format("还款期数%s,还款本金为空", repayDto.getTerm()));
				}
				if(repayDto.getRepayProfit() == null){
					throw new BzException(String.format("还款期数%s,还款利息为空", repayDto.getTerm()));
				}
				if(repayDto.getRepayAmount() == null){
					throw new BzException(String.format("还款期数%s,还款总金额为空", repayDto.getTerm()));
				}
				repayPlan = new DscRepayPlan();
				repayPlan.setApplyNo(assetMainInfo.getApplyNo());//订单编号
				repayPlan.setMainId(saleMain.getId()); //业务主表ID
				repayPlan.setCapitalCode(assetMainInfo.getFinancialCode()); //资方CODE
				repayPlan.setBelongCapitalCode(CommonConstant.BankName.YX); //所属资方code
				repayPlan.setCreateTime(new Date()); //创建时间
				repayPlan.setTerm(repayDto.getTerm()); //期数
				repayPlan.setRepayAmount(repayDto.getRepayAmount()); //还款金额
				repayPlan.setRepayDate(repayDto.getRepayDate()); //还款日期
				repayPlan.setRepayPrincipal(repayDto.getRepayPrincipal()); //还款本金
				repayPlan.setRepayProfit(repayDto.getRepayProfit()); //还款利息
				repayPlan.setLoanServiceFee(repayDto.getLoanServiceFee()); //贷款服务费
				repayPlan.setTechMaintenanceFee(repayDto.getTechMaintenanceFee()); //技术服务费
				repayPlan.setOtherFee(repayDto.getOtherFee()); //其他费用
				
				if(repayDto.getTerm() != 0){
					/**
					 * 每期还款计划的唯一标识,和贷后系统约定的规则
					 * UniqueID&下划线&期数（01，02等两位数字）
					 */
					repayPlan.setScheduleNo(assetMainInfo.getApplyNo().concat("_").concat(String.format("%02d", repayPlan.getTerm()))); //还款计划唯一标识
				}
				
				repayPlan.create();
			}
			//==================== 还款计划处理 结束 ======================
		}
	};
	
	
	
	/**
	 * 还款计划处理
	 * @param assetMainInfo
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年10月11日 下午9:00:59
	 */
	public BaseMsgDTO repaySchedulesHandle(AssetMainInfo assetMainInfo){
		String applyNo = assetMainInfo.getApplyNo(); //订单编号
		String uniqueId = assetMainInfo.getVenusApplyNo(); //VenusApplyNo
		List<DscRepayPlan> repayPlanList = DscRepayPlan.getYXRepayPlan(applyNo);
		if(CollectionUtils.isEmpty(repayPlanList)){
			throw new BzException(String.format("还款计划为空,订单编号:%s", applyNo));
		}
		List<YTRepaySchedulesDTO> repaySchedules = Lists.newArrayList(); //云南信托还款计划集合
		YTRepaySchedulesDTO schedulesDTO = null;
		for(DscRepayPlan repayPlan:repayPlanList){
			if(repayPlan.getTerm() == 0){
				continue;
			}
			schedulesDTO = new YTRepaySchedulesDTO();
			//========= 组装银行侧还款计划 ==============
			this.assemblerSchedulesDTO(repayPlan, schedulesDTO);
			repaySchedules.add(schedulesDTO);
		}
		
		//===================== 组装请求报文开始 ===========================
		YTRepaySchedulesReqDTO requestDTO = new YTRepaySchedulesReqDTO(); //银行请求dto
    	requestDTO.setUrl(YNTrustUrlEnum.LOAN_CREATE_REPAY_SCHEDULE.getUrl()); //推送还款计划
    	requestDTO.setRequestId(commonService.getRequestId()); //标示请求唯一性的值
    	
    	String osbUrl = propertiesManager.getOsbEnvironment().concat(UrlConstant.OsbSystemUrl.ynTrustInterface);
    	
    	LOGGER.info("云南信托推送还款计划组装数据开始,applyNo:{},uniqueId/VenusApplyNo:{}", applyNo,uniqueId);
    	if(StringUtils.isBlank(uniqueId)){
			LOGGER.error("云南信托推送还款计划创建定时任务k_asset_bank_request,订单唯一标识为空,applyNo={}", applyNo);
			throw new BzException(String.format("云南信托推送还款计划,订单唯一标识为空,订单编号:%s", applyNo));
		}
    	YTLoanRepaySchedulesDTO loanRepaySchedulesDTO = new YTLoanRepaySchedulesDTO(); //还款计划所属合同
    	loanRepaySchedulesDTO.setUniqueId(uniqueId); //合同唯一编号
    	loanRepaySchedulesDTO.setRepaySchedules(repaySchedules); //还款计划集合
    	
        requestDTO.setLoanRepaySchedules(Lists.newArrayList(loanRepaySchedulesDTO)); //还款计划所属合同集合
        
        String requestBody = JsonObjectUtils.objectToJson(requestDTO); //请求报文
        LOGGER.info("云南信托推送还款计划组装数据结束,applyNo:{},uniqueId/VenusApplyNo:{}", applyNo,uniqueId);
        //===================== 组装请求报文结束 ===========================
        //============= 添加银行交互记录 ===============================
        AssetBankTran bankTran = new AssetBankTran();
        bankTran.setApplyNo(applyNo); //订单编号
        bankTran.setAssetId(assetMainInfo.getId());
        bankTran.setPhase(BankPhaseEnum.REPAY_SCHEDULES_HANDLE.getPhase()); //还款计划推送
        bankTran.setReqBody(requestBody); //请求报文
        bankTran.setReqUrl(osbUrl);  //请求URL
        bankTran.setApiCode(requestDTO.getUrl());
        bankTran.setTranNo(requestDTO.getRequestId()); //请求唯一标识
        bankTran.setBankOrderNo(uniqueId); //Venus 申请编号
        bankTran.setSender(CommonConstant.SenderType.YIXIN); 
        bankTran.create();
        
        LOGGER.info("云南信托推送还款计划,请求资方开始,订单编号:{},请求参数为:{}",applyNo,requestBody);
        String result = RestTemplateUtil.sendRequest(osbUrl, requestBody, CommonConstant.BankName.YNTRUST_BANK);
        LOGGER.info("云南信托推送还款计划,请求资方结束,订单编号:{},返回参数为:{}",applyNo,result);
        
        if(StringUtils.isBlank(result)){
			this.updateBankTran(result, null, "云南信托推送还款计划返回结果为空", bankTran);
			LOGGER.error("云南信托推送还款计划返回结果为空，订单编号:{}",assetMainInfo.getApplyNo());
			throw new BzException("同步还款计划失败");
		}
        String jsonResponse = commonService.parseResponse(result);
        
        YTCommonResponseDTO response = null;
        try {
			response = JSON.parseObject(jsonResponse, YTCommonResponseDTO.class);
		} catch (Exception e) {
			this.updateBankTran(result, null, "云南信托推送还款计划返回报文错误，解析异常", bankTran);
			LOGGER.error("云南信托推送还款计划返回报文错误，解析异常,订单编号:{},异常信息:{}",applyNo,e.getMessage(),e);
			return BaseMsgDTO.failureData("同步还款计划失败,需要重试");
		}
        
        if(response == null || response.getStatus() == null){
        	this.updateBankTran(result, null, "云南信托推送还款计划返回报文错误，解析为空", bankTran);
        	LOGGER.error("云南信托推送还款计划返回报文错误，解析为空，订单编号:{}",assetMainInfo.getApplyNo());
        	return BaseMsgDTO.failureData("同步还款计划失败,需要重试");
        }
        YTCommonStatusDTO status = response.getStatus();
        this.updateBankTran(result, status.getResponseCode(), status.getResponseMessage(), bankTran);
        if(status.getIsSuccess()!=null && status.getIsSuccess()){ //请求成功
        	return BaseMsgDTO.successData("还款计划同步云信成功");
        } else if (CommonConstant.YNTrustErrorCode.SYNC_SUCCESS.equals(status.getResponseCode())){
        	//失败，UniqueId:d9c33ab17fbb4bab8a15bcc1ef0a1935对应的还款计划已存在，不可重复创建
        	/**
        	 * 接口返回0000操作成功或者返回0020 操作失败，同时错误码是60200039（联调时注意该情况），代表成功上传，不用重新上传。
        	 * 如果超时或者返回0040操作异常或者返回0020操作失败，除去60200039外的状态码，重新创建还款计划
        	 */
        	return BaseMsgDTO.successData("还款计划同步云信成功");
        } else {
        	return BaseMsgDTO.failureData("同步还款计划失败,需要重试");
        }
	}
	
	/**
	 * 组装银行还款计划
	 * @param repayPlan
	 * @param schedulesDTO
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年9月27日 下午2:25:56
	 */
	private YTRepaySchedulesDTO assemblerSchedulesDTO(DscRepayPlan repayPlan,YTRepaySchedulesDTO schedulesDTO){
		schedulesDTO.setPartnerScheduleNo(repayPlan.getScheduleNo()); //每期还款计划的唯一标识
		schedulesDTO.setRepayDate(DateUtil.dateToString(repayPlan.getRepayDate(), DateUtil.DEFAULT_DATE_FORMAT)); //还款日期
		schedulesDTO.setRepayPrincipal(convertBigDecimal(repayPlan.getRepayPrincipal())); //还款本金
		schedulesDTO.setRepayProfit(convertBigDecimal(repayPlan.getRepayProfit())); //还款利息
		return schedulesDTO;
	}
	
	private String convertBigDecimal(BigDecimal source){
    	return source.setScale(2, BigDecimal.ROUND_HALF_UP).toString();
    }
	
	/**
	 * 查询云南信托还款计划
	 * @param mainInfo
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年9月27日 下午8:58:01
	 */
	public List<YTRepaySchedulesDTO> queryRepanPlan(AssetMainInfo mainInfo){
		YTPaymentBankResultRequestDTO requestDto = new YTPaymentBankResultRequestDTO();
		requestDto.setUrl(YNTrustUrlEnum.QUERY_REPAY_SCHEDULE.getUrl()); //查询最新还款计划
		requestDto.setUniqueId(mainInfo.getVenusApplyNo());//云南信托唯一标识
		requestDto.setRequestId(commonService.getRequestId()); //标示请求唯一性的值,不能为空和重复的值，可以防止重复请求和追踪和排查问题。
		
		String osbUrl = this.propertiesManager.getOsbEnvironment() + UrlConstant.OsbSystemUrl.ynTrustInterface;
		//=========== 银行交互 ========================
		AssetBankTran assetBankTran = new AssetBankTran();
		assetBankTran.setReqBody(JsonObjectUtils.objectToJson(requestDto));
		assetBankTran.setApplyNo(mainInfo.getApplyNo());
		assetBankTran.setAssetId(mainInfo.getId());
		assetBankTran.setPhase(BankPhaseEnum.REPAY_SCHEDULES_QUERY.getPhase());
		assetBankTran.setReqUrl(osbUrl);
		assetBankTran.setApiCode(requestDto.getUrl());
		assetBankTran.setSender(CommonConstant.SenderType.YIXIN);
		assetBankTran.setTranNo(requestDto.getRequestId());
		assetBankTran.setBankOrderNo(mainInfo.getVenusApplyNo());
		assetBankTran.create();
		
		//发送银行请求并获取返回结果json
		LOGGER.info("\n云南信托查询还款计划,申请编号:{},请求参数为{}", mainInfo.getApplyNo(), assetBankTran.getReqBody());
		String result = RestTemplateUtil.sendRequest(osbUrl, requestDto, CommonConstant.BankName.YNTRUST_BANK);
		LOGGER.info("\n云南信托查询还款计划,申请编号:{},返回结果为{}", mainInfo.getApplyNo(), result);

		if (StringUtils.isBlank(result)) {
			this.updateBankTran(result, null, "云南信托查询还款计划返回结果为空", assetBankTran);
			throw new BzException("云南信托查询还款计划失败");
		}
		
		String resultJson = commonService.parseResponse(result);
		YTQueryRepayScheduleDTO queryRepayDto = (YTQueryRepayScheduleDTO) JsonObjectUtils.jsonToObject(resultJson, new YTQueryRepayScheduleDTO());
		if (queryRepayDto  == null || queryRepayDto.getStatus() == null) {
			this.updateBankTran(result, null, "云南信托查询还款计划返回结果为空", assetBankTran);
			throw new BzException("云南信托查询还款计划失败");
		}
		if(Boolean.FALSE.equals(queryRepayDto.getStatus().getIsSuccess())){
        	this.updateBankTran(result, null, "云南信托查询还款计划接口返回错误", assetBankTran);
        	throw new BzException("云南信托查询还款计划接口返回错误");
        }
		this.updateBankTran(result, queryRepayDto.getStatus().getResponseCode(), queryRepayDto.getStatus().getResponseMessage(), assetBankTran);
		return queryRepayDto.getRepaySchedules();
	}
	
	/**
	 * 更新银行交易结果
	 * @param result
	 * @param code
	 * @param errorMsg
	 * @param assetBankTran 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年9月19日 下午3:28:05
	 */
	private void updateBankTran(String result,String code,String errorMsg,AssetBankTran assetBankTran){
		assetBankTran.setRepBody(result); //响应报文
        assetBankTran.setApprovalCode(code); //错误码
        assetBankTran.setApprovalDesc(errorMsg); //错误描述
        assetBankTran.update();
	}
	
	/**
	 * 签约导入前置准备
	 * @param applyNo 申请编号
	 * @return 导入结果
	 * @author YixinCapital -- chenjiacheng
	 *             2018/9/26 11:40
	 */
	public BaseMsgDTO importProtocol(AssetMainInfo mainInfo) {
		try {
			YTImportProtocolRequestDTO importProtocolDTO = prepareData(mainInfo);
			String osbUrl = this.propertiesManager.getOsbEnvironment() + UrlConstant.OsbSystemUrl.ynTrustInterface;
			//==============记录银行交互 ==================
			AssetBankTran assetBankTran = new AssetBankTran();
			assetBankTran.setReqBody(JsonObjectUtils.objectToJson(importProtocolDTO));
			assetBankTran.setApplyNo(mainInfo.getApplyNo());
			assetBankTran.setAssetId(mainInfo.getId());
			assetBankTran.setPhase(AssetBankTransEnum.YT_IMPORT_PROTOCOL.getPhase());
			assetBankTran.setReqUrl(osbUrl);
			assetBankTran.setApiCode(importProtocolDTO.getUrl());
			assetBankTran.setSender(CommonConstant.SenderType.YIXIN);
			assetBankTran.setTranNo(importProtocolDTO.getRequestId());
			assetBankTran.setBankOrderNo(mainInfo.getVenusApplyNo());
			assetBankTran.create();

			//发送银行请求并获取返回结果json
			LOGGER.info("\n云南信托签约导入开始,申请编号:{},请求参数为{}", mainInfo.getApplyNo(), assetBankTran.getReqBody());
			String result = RestTemplateUtil.sendRequest(osbUrl, importProtocolDTO, CommonConstant.BankName.YNTRUST_BANK);
			LOGGER.info("\n云南信托签约导入结束,申请编号:{},返回结果为{}", mainInfo.getApplyNo(), result);

			if (StringUtils.isBlank(result)) {
				this.updateBankTran(result, null, "云南信托签约导入返回结果为空", assetBankTran);
				return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "签约导入失败");
			}

			YTCommonResponseDTO responseDTO = JSONObject.parseObject(commonService.parseResponse(result), YTCommonResponseDTO.class);
			if (responseDTO == null || responseDTO.getStatus() == null ) {
				this.updateBankTran(result, null, "云南信托签约导入接口返回错误", assetBankTran);
				return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "签约导入失败");
			}
			YTCommonStatusDTO status = responseDTO.getStatus();
			this.updateBankTran(result, status.getResponseCode(), status.getResponseMessage(), assetBankTran);
			if(status.getIsSuccess() == null){
				return BaseMsgDTO.failureData("签约导入失败");
			}
			if(Boolean.TRUE.equals(status.getIsSuccess())){
				return BaseMsgDTO.successData("签约导入成功");
	        } else {
	        	JSONObject jsonObject = JSON.parseObject(result);
	        	JSONArray failDetails = jsonObject.getJSONArray("FailDetails");
	        	if(failDetails != null && !failDetails.isEmpty()){
	        		JSONObject failDetail = failDetails.getJSONObject(0);
	        		if(failDetail != null && "80800069".equals(failDetail.getString("Code"))){
	        			//{"FailDetails":[{"Code":"80800069","Message":"已存在相同的协议号导入成功!","SignNo":"2126662018101888801"}],
	        			return BaseMsgDTO.successData(String.format("协议编号：%s,导入成功", failDetail.getString("SignNo")));
	        		} else if (failDetail != null && "80800025".equals(failDetail.getString("Code"))){
	        			//FailDetails":[{"Code":"80800025","Message":"银行卡已签约!","SignNo":"2126662018101888802"}]
	        			return BaseMsgDTO.failureData(String.format("银行卡已签约!"));
	        		}
	        	}
	        	return BaseMsgDTO.successData("签约导入失败");
	        }
		} catch (Exception e) {
			LOGGER.error("云信签约接口异常,applyNo={}", mainInfo.getApplyNo(), e);
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "签约导入失败");
		}
	}
	
	private YTImportProtocolRequestDTO prepareData(AssetMainInfo mainInfo) {
		DscSalesApplyMain main = DscSalesApplyMain.getByApplyNo(mainInfo.getApplyNo());
		DscSalesApplyCust cust = DscSalesApplyCust.getOneByMainId(main.getId());
		
		
		YTImportProtocolRequestDTO importProtocolDTO = new YTImportProtocolRequestDTO();
		List<YTImportProtocolInfoDetailDTO> protocolInfoDetailDTOList = new ArrayList<>();
		YTImportProtocolInfoDetailDTO protocolInfoDetailDTO = new YTImportProtocolInfoDetailDTO();
		protocolInfoDetailDTOList.add(protocolInfoDetailDTO);
		importProtocolDTO.setProtocolInfoDetails(protocolInfoDetailDTOList);
		//主信息
		importProtocolDTO.setRequestId(commonService.getRequestId());
		importProtocolDTO.setUrl(YNTrustUrlEnum.IMPORT_PROTOCOL_INFO.getUrl());
		importProtocolDTO.setBatchNo(UUID.randomUUID().toString());//批次号
		importProtocolDTO.setProductCode(mainInfo.getAssetNo()); //产品编号
		//协议明细
		protocolInfoDetailDTO.setChannel(CommonConstant.YTChannelCode.TONGLIAN); //网关，默认通联
		protocolInfoDetailDTO.setSignNo(main.getSignNo()); //签约协议编号
		protocolInfoDetailDTO.setIdCardNO(cust.getAzjhm()); //身份证号
		protocolInfoDetailDTO.setTelephoneNo(main.getAckrsjhm()); //手机号
		protocolInfoDetailDTO.setName(cust.getAkhxm()); //姓名
		protocolInfoDetailDTO.setAccountNo(main.getAhkrjjkzh()); //还款卡号
		protocolInfoDetailDTO.setBankCode(commonService.parseBankCode(main.getBankCode()).get("bankCode"));

		return importProtocolDTO;
	}
	
	
	/**
	 * 更换还款卡信息
	 * @param mainInfo
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年10月12日 下午6:08:07
	 */
	public BaseMsgDTO modifyRepayInfo(AssetMainInfo mainInfo){
		//调用银行修改接口
		LOGGER.info("更换还款卡信息-调用云信修改还款信息开始 ，订单编号：{}",mainInfo.getApplyNo());
		InvokeResult<BaseMsgDTO> iResult = ytModifyRepayStrategy.doIt(mainInfo.getApplyNo());
		LOGGER.info("更换还款卡信息-调用云信修改还款信息结束 ，订单编号：{},返回:{}",mainInfo.getApplyNo(),JSON.toJSONString(iResult));
		if(iResult == null){
			return BaseMsgDTO.failureData("修改还款信息失败");
		}
		BaseMsgDTO msgDto = (BaseMsgDTO) iResult.getData();
		if(msgDto.successed()){
			LOGGER.info("更换还款卡信息-调用云信签约导入开始 ，订单编号：{}",mainInfo.getApplyNo());
			msgDto = this.requestDataHandle.importProtocol(mainInfo);
			LOGGER.info("更换还款卡信息-调用云信签约导入结束 ，订单编号：{},返回:{}",mainInfo.getApplyNo(),JSON.toJSONString(msgDto));
		}
		return msgDto;
	}
	
	
	/**
	 * 维护接口
	 * @param maintenanceDto
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年10月20日 下午4:24:59
	 */
	public BaseMsgDTO maintenance(YNTrustMaintenanceDTO maintenanceDto){
		String applyNo = maintenanceDto.getApplyNo();
		LOGGER.info("订单编号：{},维护入参:{}",JSON.toJSONString(maintenanceDto));
		if("last_trial".equals(maintenanceDto.getMaintenanceType())){ //终审
			AssetMainInfo mainInfo = AssetMainInfo.getByApplyNo(applyNo);
			if(StringUtils.isBlank(maintenanceDto.getBankResponseBody())){
				String productCode = maintenanceDto.getProductCode(); /** 产品代码   */
				String subAccount = maintenanceDto.getSubAccount(); /** 虚拟子账号   */
				String uniqueId = maintenanceDto.getUniqueId(); /** 每笔贷款唯一标示   */
				
				mainInfo.setLastState(AssetStateEnum.SUCCESS.getState()); //终审成功
				mainInfo.setAssetNo(productCode); //云信返回的产品代码
				mainInfo.setBankOrderNo(subAccount); //虚拟子账户
				mainInfo.setVenusApplyNo(uniqueId); //VenusApplyNo
				mainInfo.update();
				
				ioc.publishEvent(new ResultNotifyEvent(this.createForAliDto(
						applyNo, CreditfrontResultEnum.SUCCESS.getCode(),CreditfrontResultEnum.SUCCESS.getMsg(),mainInfo)));
				
			} else {
				String resultJson = commonService.parseResponse(maintenanceDto.getBankResponseBody());
				YTCreateOrderResponseDto responseDTO = (YTCreateOrderResponseDto) JsonObjectUtils.jsonToObject(resultJson, new YTCreateOrderResponseDto());
				YTCommonStatusDTO status = responseDTO.getStatus();
				
				if(status.getIsSuccess()){
					mainInfo.setLastState(AssetStateEnum.SUCCESS.getState()); //终审成功
					mainInfo.setAssetNo(responseDTO.getProductCode()); //云信返回的产品代码
					mainInfo.setBankOrderNo(responseDTO.getSubAccount()); //虚拟子账户
					mainInfo.setVenusApplyNo(responseDTO.getUniqueId()); //VenusApplyNo
					mainInfo.update();
					
					ioc.publishEvent(new ResultNotifyEvent(this.createForAliDto(
							applyNo, CreditfrontResultEnum.SUCCESS.getCode(),CreditfrontResultEnum.SUCCESS.getMsg(),mainInfo)));
				}
			}
		} else if("last_trial_refuse".equals(maintenanceDto.getMaintenanceType())){ //终审拒绝
			AssetMainInfo mainInfo = AssetMainInfo.getByApplyNo(applyNo);
			mainInfo.setLastState(AssetStateEnum.FAILD.getState()); //终审失败
			mainInfo.update();
			
			AssetBankRequest request = AssetBankRequest.getOnlyApplyOnWay(maintenanceDto.getApplyNo(), BankPhaseEnum.LAST_TRIAL.getPhase());
			if(request != null){
				request.setReqState(BankRequestConstant.REQ_BANK_INHAND);//报文是json默认发起成功，后续处理
				request.update();
			}
			ioc.publishEvent(new ResultNotifyEvent(this.createForAliDto(applyNo, CreditfrontResultEnum.REFUSE.getCode(),"人工介入-手动推送信审拒绝",mainInfo)));
		} else if("last_trial_retry".equals(maintenanceDto.getMaintenanceType())){ //终审重试
			AssetBankRequest request = AssetBankRequest.getOnlyApplyOnWay(applyNo, BankPhaseEnum.LAST_TRIAL.getPhase());
			if(request != null){
				if(request.getRetryMark() == BankRequestConstant.RETRY_INIT_COUNT){ //初始化次数
					request.setRetryMark(request.getRetryMark() + 1);
				}
				request.setBankOrderNo("");
				request.update();
			}
			
		} else if("test_replay_plan_query".equals(maintenanceDto.getMaintenanceType())){
			AssetMainInfo mainInfo = AssetMainInfo.getByApplyNo(applyNo);
			if(mainInfo == null){
				mainInfo = new AssetMainInfo();
				mainInfo.setApplyNo(applyNo);
				mainInfo.setVenusApplyNo(applyNo);
				mainInfo.setId(applyNo);
			}
			List<YTRepaySchedulesDTO> replayPlanList = this.queryRepanPlan(mainInfo);
			return BaseMsgDTO.successData(JSON.toJSONString(replayPlanList));
		} else if("payment_refuse".equals(maintenanceDto.getMaintenanceType())){ //请款拒绝
			AssetMainInfo mainInfo = AssetMainInfo.getByApplyNo(applyNo);
			mainInfo.setPaymentState(AssetStateEnum.FAILD.getState()); //请款状态为失败
			mainInfo.update();
			
			DscFlowResultForAlixDto forAlixDto = createForAliDto(applyNo,false, "请款拒绝");
			ioc.publishEvent(new ResultNotifyEvent(forAlixDto));
			
		} else if("payment_success".equals(maintenanceDto.getMaintenanceType())){ //请款成功
			AssetMainInfo mainInfo = AssetMainInfo.getByApplyNo(applyNo);
			mainInfo.setPaymentState(AssetStateEnum.SUCCESS.getState()); //请款状态为成功
			mainInfo.update();
			
			DscFlowResultForAlixDto forAlixDto = createForAliDto(applyNo,true, "请款成功");
			ioc.publishEvent(new ResultNotifyEvent(forAlixDto));
		}
		return BaseMsgDTO.successData("success");
	}
	
	private DscFlowResultForAlixDto createForAliDto(String applyNo, String code,String message,AssetMainInfo mainInfo) {
		DscFlowResultForAlixDto alixDto = new DscFlowResultForAlixDto();
		alixDto.setApplyNo(applyNo);
		alixDto.setCode(code);
		alixDto.setLink(DscAlixLinkEnum.CREDITFRONT.getCode());
		alixDto.setMessage(message);
		if(CreditfrontResultEnum.SUCCESS.getCode().equals(code)){
			DscCreditfrontBusinessDto businessInfo = new DscCreditfrontBusinessDto();
			businessInfo.setCapitalProductCode(mainInfo.getAssetNo()); //云信返回的产品代码
			businessInfo.setCapitalSubAccount(mainInfo.getBankOrderNo());//虚拟子账户
			businessInfo.setVenusApplyNo(mainInfo.getVenusApplyNo()); //venus订单编号
			alixDto.setBusinessInfo(businessInfo);
		}
		return alixDto;
	}
	
	private DscFlowResultForAlixDto createForAliDto(String applyNo, Boolean isSuccess, String msg) {
		return DscFlowResultForAlixDto.createForAliDto(
				applyNo, isSuccess, msg, DscAlixLinkEnum.REQUEST_FUNDS);
	}
}
