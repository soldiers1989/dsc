package com.yixin.kepler.v1.service.capital.yntrust;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.dsc.dto.DscCreditfrontBusinessDto;
import com.yixin.dsc.dto.DscFlowResultForAlixDto;
import com.yixin.dsc.enumpackage.DscAlixLinkEnum;
import com.yixin.dsc.service.async.AsyncTaskManagerService;
import com.yixin.dsc.v1.service.capital.yntrust.YTCommonService;
import com.yixin.kepler.common.enums.AssetStateEnum;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.common.enums.CreditfrontResultEnum;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.core.listener.ResultNotifyEvent;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.enity.AssetBankTran;
import com.yixin.kepler.enity.AssetMainInfo;
import com.yixin.kepler.enity.OsbFileLog;
import com.yixin.kepler.v1.common.constants.BankRequestConstant;
import com.yixin.kepler.v1.common.core.bankReq.BankRespResultHandle;
import com.yixin.kepler.v1.common.enumpackage.YNTrustUrlEnum;
import com.yixin.kepler.v1.datapackage.dto.BankRespResultHandleDTO;
import com.yixin.kepler.v1.datapackage.dto.yntrust.YTCommonStatusDTO;
import com.yixin.kepler.v1.datapackage.dto.yntrust.YTCreateOrderResponseDto;
import com.yixin.kepler.v1.datapackage.entity.AssetBankRequest;
import com.yixin.web.service.monitor.NoticeService;

/**
 * 废弃-没有用
 * Package : com.yixin.kepler.v1.service.capital.yntrust
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年10月11日 下午8:21:46
 *
 */
@Service("YNTRUSTRespResultHandle")
public class YTRespResultHandle implements BankRespResultHandle {
	
	private static final Logger LOGGER = LoggerFactory.getLogger(YTRespResultHandle.class);
	
	/**
	 * 云南信托公共处理类
	 */
	@Resource
    private YTCommonService commonService;
	
	/**
	 * 云南信托取消订单处理类
	 */
	@Resource
	private YTOrderCancelRequest ytOrderCancelRequest;
	
	/**
	 * 异步处理类
	 */
	@Resource(name = "asyncTaskManagerSpringImpl")
	private AsyncTaskManagerService asyncTaskManagerService;
	
	@Resource
	private NoticeService noticeService;
	
	@Resource
	private ApplicationContext ioc;
	
	/**
	 * 银行响应结果处理
	 * @param resultDTO
	 * @return 结果对象
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月17日 下午5:28:55
	 */
	@Override
	public BaseMsgDTO respResultHandle(BankRespResultHandleDTO resultDTO) {
		//获取资产发起银行请求信息
		AssetBankRequest bankRequest = AssetBankRequest.getById(resultDTO.getBankReqId());
		String applyNo = bankRequest.getApplyNo();
		String bankResultJosn = resultDTO.getBankResultJosn(); //接口响应报文
		LOGGER.info("订单编号:{},云南信托请求信审创建订单(终审)返回结果处理,返回报文为{}",applyNo,bankResultJosn);
		//获取银行交易记录
		AssetBankTran bankTran = AssetBankTran.get(AssetBankTran.class, resultDTO.getTranNo());
		
		LOGGER.info("订单编号:{},云南信托请求信审创建订单(终审)返回结果处理,获取银行交互记录,id:{}",applyNo,bankTran.getId());
		
		String resultJson = commonService.parseResponse(bankResultJosn);
		
		YTCreateOrderResponseDto responseDTO = (YTCreateOrderResponseDto) JsonObjectUtils.jsonToObject(resultJson, new YTCreateOrderResponseDto());
		LOGGER.info("订单编号:{},云南信托请求信审创建订单(终审)返回结果处理,解析返回报文:{}",applyNo,JSON.toJSONString(responseDTO));
		if(responseDTO == null || responseDTO.getStatus() == null){
			this.updateBankTran(bankResultJosn, null, "云南信托请求信审创建订单(终审)返回结果处理，返回结果为空", bankTran);
			retry(bankRequest,null,false);
			return BaseMsgDTO.failureData("信审收妥失败");
		}
		YTCommonStatusDTO status = responseDTO.getStatus();
		if(status.getIsSuccess() == null){
			this.updateBankTran(bankResultJosn, null, "云南信托请求信审创建订单(终审)返回结果处理，返回结果为空", bankTran);
			retry(bankRequest,null,false);
			return BaseMsgDTO.failureData("信审收妥失败");
		}
		this.updateBankTran(bankResultJosn, status.getResponseCode(), status.getResponseMessage(), bankTran);
		AssetMainInfo mainInfo = AssetMainInfo.getByApplyNo(applyNo);
		if(mainInfo == null){
			LOGGER.error("订单编号:{},云南信托请求信审创建订单(终审)返回结果处理,订单资方交互AssetMainInfo为空",applyNo);
			return BaseMsgDTO.failureData("信审收妥失败");
		}
		if(status.getIsSuccess()){
			mainInfo.setLastState(AssetStateEnum.SUCCESS.getState()); //终审成功
			mainInfo.setAssetNo(responseDTO.getProductCode()); //云信返回的产品代码
			mainInfo.setBankOrderNo(responseDTO.getSubAccount()); //虚拟子账户
			mainInfo.setVenusApplyNo(responseDTO.getUniqueId()); //VenusApplyNo
			mainInfo.update();
			
			ioc.publishEvent(new ResultNotifyEvent(this.createForAliDto(
					applyNo, CreditfrontResultEnum.SUCCESS.getCode(),CreditfrontResultEnum.SUCCESS.getMsg(),mainInfo)));
			
		} else if("6030099".equals(status.getResponseCode())
				|| "6030147".equals(status.getResponseCode())
				|| "6030148".equals(status.getResponseCode())
				|| "6030149".equals(status.getResponseCode())
				|| "6030150".equals(status.getResponseCode())) {
			/**
			 * 云信风控："6030099", "验证借款人未通过"
			 * ABS风控："6030147", "ABS风控查询错误[%s]!"
			 * ABS风控："6030148", "ABS风控查询异常!"
			 * ABS风控："6030149", "ABS风控未查询到用户信息!"
			 * ABS风控："6030150", "ABS风控查询该用户不通过!"
			 */
			mainInfo.setLastState(AssetStateEnum.FAILD.getState());
			mainInfo.update();

			ioc.publishEvent(new ResultNotifyEvent(this.createForAliDto(
					mainInfo.getApplyNo(), CreditfrontResultEnum.REFUSE.getCode(),status.getResponseMessage(),mainInfo)));
			
			
		} else {
			retry(bankRequest,mainInfo.getId(),true);
		}
		return BaseMsgDTO.successData("success");
	}
	
	/**
	 * 重试
	 * @param bankRequest
	 * @param mainInfo 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年10月20日 下午3:05:56
	 */
	public void retry(AssetBankRequest bankRequest,String mainInfoId,boolean cancel){
		if(cancel && StringUtils.isNotBlank(bankRequest.getBankOrderNo())){
			LOGGER.info("云南信托请求信审创建订单(终审)返回结果处理，重试之前取消订单，订单编号:{}",bankRequest.getApplyNo());
			this.asyncTaskManagerService.syncOrderCancel(bankRequest.getBankOrderNo(), bankRequest.getApplyNo(),mainInfoId);
			bankRequest.setBankOrderNo(""); //将和银行交互的唯一ID清空，重试时重新生成
		}
		bankRequest.setReqState(BankRequestConstant.RETRY_REQ_BANK);
		int retryMark = bankRequest.getRetryMark();
		bankRequest.setRetryMark(++retryMark);
		bankRequest.update();
	}

	@Override
	public String assembleOsb(AssetBankRequest requestDTO, OsbFileLog osbFileLog) {
		
		return null;
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
		LOGGER.info("订单编号:{},云南信托请求信审创建订单(终审),更新银行交互结果,code:{},errorMsg:{}",assetBankTran.getApplyNo(),code,errorMsg);
		assetBankTran.setRepBody(result); //响应报文
        assetBankTran.setApprovalCode(code); //错误码
        assetBankTran.setApprovalDesc(errorMsg); //错误描述
        assetBankTran.update();
	}
	
	
	public String requestBeforeHandle(AssetBankRequest task){
		LOGGER.info("云南信托信审(创建订单)发送请求前数据处理开始，订单编号:{}",task.getApplyNo());
		JSONObject jsonObject = JSONObject.parseObject(task.getAssetReqBody());
		if(task.getRetryMark() != BankRequestConstant.RETRY_INIT_COUNT){ //重试
			LOGGER.info("云南信托信审(创建订单)发送请求前数据处理开始，订单编号:{}",task.getApplyNo());
			String requestId = commonService.getRequestId();
			jsonObject.put("RequestId", requestId); //重新生成请求ID
			
			if(StringUtils.isBlank(task.getBankOrderNo())){  //将和银行交互的唯一ID为空，重试时重新生成
				String uniqueId = commonService.getUniqueId();
				jsonObject.put("UniqueId", uniqueId); //重新生成 uniqueId
				LOGGER.info("云南信托信审(创建订单)发送请求前数据处理开始，订单编号:{},重新生成UniqueId/VenusApplyNo:{}",task.getApplyNo(),uniqueId);
				task.setBankOrderNo(uniqueId); //重新生成 uniqueId
				//再更换贷款合同编号
				JSONObject jsonObjectLoan = jsonObject.getJSONObject("Loan");
				if(jsonObjectLoan != null){
					jsonObjectLoan.put("LoanContractNumber", uniqueId);
				}
			}
		}
		//========= 银行交互保存 ===================================
		LOGGER.info("云南信托信审发送请求前数据处理-创建银行交互记录，订单编号:{}",task.getApplyNo());
		AssetBankTran assetBankTran = new AssetBankTran();
		assetBankTran.setReqBody(jsonObject.toString());
		assetBankTran.setApplyNo(task.getApplyNo());
		assetBankTran.setAssetId(task.getBzId());
		assetBankTran.setAssetNo(task.getAssetNo());
		assetBankTran.setPhase(BankPhaseEnum.LAST_TRIAL.getPhase()); //终审
		assetBankTran.setReqUrl(task.getReqUrl());
		assetBankTran.setApiCode(YNTrustUrlEnum.CREATE_ORDER.getUrl());
		assetBankTran.setSender(CommonConstant.SenderType.YIXIN);
		assetBankTran.setTranNo(jsonObject.getString("RequestId"));
		assetBankTran.setBankOrderNo(task.getBankOrderNo());
		String id = assetBankTran.create();
		
		task.setTranNo(id); //银行交互表记录ID
		LOGGER.info("云南信托信审发送请求前数据处理结束，订单编号:{}",task.getApplyNo());
		return jsonObject.toString();
	};

	
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
}
