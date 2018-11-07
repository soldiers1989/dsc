package com.yixin.kepler.v1.service.impl.icbc;

import java.util.Arrays;
import java.util.Date;
import java.util.HashSet;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.yixin.common.exception.BzException;
import com.yixin.common.utils.DateUitls;
import com.yixin.dsc.dto.DscFlowResultForAlixDto;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.dsc.util.DateUtil;
import com.yixin.dsc.v1.service.common.NoticeApplyDealFactory;
import com.yixin.kepler.common.enums.AssetStateEnum;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.core.listener.ResultNotifyEvent;
import com.yixin.kepler.core.listener.SettleOrderEvent;
import com.yixin.kepler.enity.AssetMainInfo;
import com.yixin.kepler.enity.AssetOrderFlow;
import com.yixin.kepler.v1.common.constants.IcbcConstant;
import com.yixin.kepler.v1.common.constants.IcbcErrorConstant;
import com.yixin.kepler.v1.common.enumpackage.BankPhaseEnum;
import com.yixin.kepler.v1.common.enumpackage.ICBCTrxCodeEnum;
import com.yixin.kepler.v1.datapackage.dto.icbc.IcbcAckDataDTO;
import com.yixin.kepler.v1.datapackage.dto.icbc.IcbcApplyDTO;
import com.yixin.kepler.v1.datapackage.dto.icbc.IcbcBackDataDTO;
import com.yixin.kepler.v1.datapackage.dto.icbc.IcbcCommonDTO;
import com.yixin.kepler.v1.service.capital.icbc.ICBCCallbackService;
import com.yixin.kepler.v1.utils.JacksonUtils;

/**
 * 工行-银行侧回调实现类
 * Package : com.yixin.kapler_v1.service.capital.icbc
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年9月12日 上午10:05:42
 *
 */
@Service("ICBCCallbackService")
public class ICBCCallbackServiceImpl extends ICBCCallbackService {

	private static final Logger LOGGER = LoggerFactory.getLogger(ICBCCallbackServiceImpl.class);

    @Autowired
    private ApplicationContext ioc;

    
	/**
	 * 工行信审结果回调
	 * @param IcbcJson
	 * @return 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月12日 上午10:26:02
	 */
	@Override
	public IcbcApplyDTO<IcbcAckDataDTO> letterReview(String IcbcJson) throws BzException {
		// 声明返回结果
        IcbcApplyDTO<IcbcAckDataDTO> response = new IcbcApplyDTO<>();
        IcbcCommonDTO responseCommon = new IcbcCommonDTO();
        IcbcAckDataDTO responseData = new IcbcAckDataDTO();
        response.setComm(responseCommon);
        response.setData(responseData);
		// 校验为空时返回收妥异常
	    if (!StringUtils.hasText(IcbcJson)){
            LOGGER.error("工行信审反馈结果数据为空!!![{}]", IcbcJson);
            assemberCommHead(responseCommon);// 组装返回公共信息
            responseData.setProcflag(IcbcConstant.PROCFLAG_CODE_OTHER_ERROR);
            responseData.setErrmsg("数据为空");
            return response;
        }
		// 转化为实体类
		IcbcApplyDTO<IcbcBackDataDTO> feedback = (IcbcApplyDTO<IcbcBackDataDTO>)JacksonUtils.getObjectFromJson(IcbcJson, IcbcApplyDTO.class,IcbcBackDataDTO.class);
		if(feedback == null || feedback.getData() == null){
            LOGGER.error("工行信审反馈结果报文异常!转换报文到实体异常![{}]", IcbcJson);
            assemberCommHead(responseCommon);// 组装返回公共信息
            responseData.setProcflag(IcbcConstant.PROCFLAG_CODE_OTHER_ERROR);
            responseData.setErrmsg("报文内容异常");
            return response;
        }
        // --------------校验并入库
        response = CallbackICBC4Check.checkAndSaveMsg(feedback, IcbcJson, ICBCTrxCodeEnum.TX_10201);

        // 校验资产状态
        AssetMainInfo assetMainInfo = AssetMainInfo.getByVenusApplyNo(feedback.getData().getOrderno());
        // --------------kepler内部资产数据处理
        // 更新资产信息
        if(IcbcConstant.PROCFLAG_CODE_OTHER_ERROR.equals(response.getData().getProcflag())){//回调收妥失败
        	if(!AssetStateEnum.SUCCESS.getState().equals(assetMainInfo.getLastState())){
        		assetMainInfo.setLastState(AssetStateEnum.ACCEPT_FAILD.getState()); 
        		assetMainInfo.update();
        	}
            LOGGER.info("工行回调结束，信审回调失败[{}]", assetMainInfo.getApplyNo());
            return response;
        } else if (IcbcConstant.APPROVAL_CODE_SUCCESS.equals(feedback.getData().getApprovalcode())) {//信审成功
        	assetMainInfo.setLastState(AssetStateEnum.SUCCESS.getState()); 
            LOGGER.info("工行回调结束，信审成功[{}]", assetMainInfo.getApplyNo());
		} else if(new HashSet<>(Arrays.asList(IcbcErrorConstant.REJECT_CODE)).contains(feedback.getData().getApprovalcode())){//信审驳回
        	assetMainInfo.setLastState(AssetStateEnum.REJECT.getState()); 
			assetMainInfo.setAssetPhase(BankPhaseEnum.LAST_TRIAL_REJECT.getPhase());
            LOGGER.info("工行回调结束，信审驳回[{}]", assetMainInfo.getApplyNo());
		} else if(new HashSet<>(Arrays.asList(IcbcErrorConstant.REFUSE_CODE)).contains(feedback.getData().getApprovalcode())){//信审拒绝
        	assetMainInfo.setLastState(AssetStateEnum.FAILD.getState()); 
            LOGGER.info("工行回调结束，信审拒绝[{}]", assetMainInfo.getApplyNo());
        }  
        assetMainInfo.update();
        // --------------调用异步通知alix逻辑，不阻塞返回工行结果
        asyncNotifyResult(feedback);
        return response;
	}

	
	/**
	 * 工行面签结果回调
	 * @param IcbcJson
	 * @return 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月12日 上午10:26:02
	 */
	@Override
	public IcbcApplyDTO<IcbcAckDataDTO> faceTackReview(String IcbcJson) throws BzException {
		// 声明返回结果
        IcbcApplyDTO<IcbcAckDataDTO> response = new IcbcApplyDTO<>();
        IcbcCommonDTO responseCommon = new IcbcCommonDTO();
        IcbcAckDataDTO responseData = new IcbcAckDataDTO();
        response.setComm(responseCommon);
        response.setData(responseData);
		// 校验为空时返回收妥异常
	    if (!StringUtils.hasText(IcbcJson)){
            LOGGER.error("工行面签反馈结果数据为空!!![{}]", IcbcJson);
            assemberCommHead(responseCommon);// 组装返回公共信息
            responseData.setProcflag(IcbcConstant.PROCFLAG_CODE_OTHER_ERROR);
            responseData.setErrmsg("数据为空");
            return response;
        }
		// 转化为实体类
		IcbcApplyDTO<IcbcBackDataDTO> feedback = (IcbcApplyDTO<IcbcBackDataDTO>)JacksonUtils.getObjectFromJson(IcbcJson, IcbcApplyDTO.class,IcbcBackDataDTO.class);
		if(feedback == null || feedback.getData() == null){
            LOGGER.error("工行面签反馈结果报文异常!转换报文到实体异常![{}]", IcbcJson);
            assemberCommHead(responseCommon);// 组装返回公共信息
            responseData.setProcflag(IcbcConstant.PROCFLAG_CODE_OTHER_ERROR);
            responseData.setErrmsg("报文内容异常");
            return response;
        }
        // --------------校验并入库
        response = CallbackICBC4Check.checkAndSaveMsg(feedback, IcbcJson, ICBCTrxCodeEnum.TX_30101);

        // 校验资产状态
        AssetMainInfo assetMainInfo = AssetMainInfo.getByVenusApplyNo(feedback.getData().getOrderno());
        // --------------kepler内部资产数据处理
        // 更新资产信息
        if(IcbcConstant.PROCFLAG_CODE_OTHER_ERROR.equals(response.getData().getProcflag())){//回调收妥失败
        	if(!AssetStateEnum.SUCCESS.getState().equals(assetMainInfo.getContractSignState())){
        		assetMainInfo.setContractSignState(AssetStateEnum.ACCEPT_FAILD.getState()); 
        		assetMainInfo.update();
        	}
            LOGGER.info("工行回调结束，面签回调失败[{}]", assetMainInfo.getApplyNo());
            return response;
        } else if (IcbcConstant.APPROVAL_CODE_SUCCESS.equals(feedback.getData().getApprovalcode())) {//面签成功
        	assetMainInfo.setContractSignState(AssetStateEnum.SUCCESS.getState()); 
            LOGGER.info("工行回调结束，面签成功[{}]", assetMainInfo.getApplyNo());
        } else if(IcbcConstant.APPROVAL_CODE_CONTRACT_REJECT.equals(feedback.getData().getApprovalcode())){//面签拒绝
        	assetMainInfo.setContractSignState(AssetStateEnum.FAILD.getState()); 
            LOGGER.info("工行回调结束，面签拒绝[{}]", assetMainInfo.getApplyNo());
        } 
		assetMainInfo.setAssetPhase(BankPhaseEnum.FACE_SIGN.getPhase());
        assetMainInfo.update();
        return response;
	}
	
	
	/**
	 * 工行请款结果回调
	 * @param IcbcJson
	 * @return 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月12日 上午10:26:02
	 */
	@Override
	public IcbcApplyDTO<IcbcAckDataDTO> requestFundsReview(String IcbcJson) throws BzException {
        // 声明返回结果
        IcbcApplyDTO<IcbcAckDataDTO> response = new IcbcApplyDTO<>();
        IcbcCommonDTO responseCommon = new IcbcCommonDTO();
        IcbcAckDataDTO responseData = new IcbcAckDataDTO();
        response.setComm(responseCommon);
        response.setData(responseData);
		// 校验为空时返回收妥异常
	    if (!StringUtils.hasText(IcbcJson)){
            LOGGER.error("工行请款反馈结果数据为空!!![{}]", IcbcJson);
            assemberCommHead(responseCommon);// 组装返回公共信息
            responseData.setProcflag(IcbcConstant.PROCFLAG_CODE_OTHER_ERROR);
            responseData.setErrmsg("数据为空");
            return response;
        }
		// 转化为实体类
		IcbcApplyDTO<IcbcBackDataDTO> feedback = (IcbcApplyDTO<IcbcBackDataDTO>)JacksonUtils.getObjectFromJson(IcbcJson, IcbcApplyDTO.class,IcbcBackDataDTO.class);
		if(feedback == null || feedback.getData() == null){
            LOGGER.error("工行请款反馈结果报文异常!转换报文到实体异常![{}]", IcbcJson);
            assemberCommHead(responseCommon);// 组装返回公共信息
            responseData.setProcflag(IcbcConstant.PROCFLAG_CODE_OTHER_ERROR);
            responseData.setErrmsg("报文内容异常");
            return response;
        }
        // --------------校验并入库
        response =CallbackICBC4Check.checkAndSaveMsg(feedback, IcbcJson, ICBCTrxCodeEnum.TX_40201);
        // 校验资产状态
        AssetMainInfo assetMainInfo = AssetMainInfo.getByVenusApplyNo(feedback.getData().getOrderno());
        // --------------kepler内部资产数据处理
        // 更新资产信息
        if(IcbcConstant.PROCFLAG_CODE_OTHER_ERROR.equals(response.getData().getProcflag())){//回调收妥失败
        	if(!AssetStateEnum.SUCCESS.getState().equals(assetMainInfo.getPaymentState())){
        		assetMainInfo.setPaymentState(AssetStateEnum.ACCEPT_FAILD.getState()); 
        		assetMainInfo.update();
        	}
            LOGGER.info("工行回调结束，请款回调失败[{}]", assetMainInfo.getApplyNo());
            return response;
        } else if (IcbcConstant.APPROVAL_CODE_SUCCESS.equals(feedback.getData().getApprovalcode())) {//放款成功
        	assetMainInfo.setPaymentState(AssetStateEnum.SUCCESS.getState()); 
        	//============== 更新k_asset_order_flow 放款时间 ===============
        	Date loanTime = DateUtil.StringToDate(feedback.getData().getLendate(), DateUtil.DEFAULT_DATE_FORMAT);
			AssetOrderFlow.recordLoanInfo(assetMainInfo.getApplyNo(), IcbcJson, loanTime);
			//发送推送结算事件
			ioc.publishEvent(new SettleOrderEvent(assetMainInfo.getApplyNo()));
            LOGGER.info("工行回调结束，请款成功[{}]", assetMainInfo.getApplyNo());
		} else if(new HashSet<>(Arrays.asList(IcbcErrorConstant.REJECT_CODE)).contains(feedback.getData().getApprovalcode())){//放款驳回
        	assetMainInfo.setPaymentState(AssetStateEnum.REJECT.getState()); 
			assetMainInfo.setAssetPhase(BankPhaseEnum.PAYMENT_REJECT.getPhase());
            LOGGER.info("工行回调结束，请款驳回[{}]", assetMainInfo.getApplyNo());
		} else if(new HashSet<>(Arrays.asList(IcbcErrorConstant.REFUSE_CODE)).contains(feedback.getData().getApprovalcode())){//放款拒绝
        	assetMainInfo.setPaymentState(AssetStateEnum.FAILD.getState()); 
            LOGGER.info("工行回调结束，请款拒绝[{}]", assetMainInfo.getApplyNo());
        }  
        assetMainInfo.update();
        // --------------调用异步通知alix逻辑，不阻塞返回工行结果
        asyncNotifyResult(feedback);
        return response;
	}
	
	
	/**
	 * 工行抵押确认回调
	 * @param IcbcJson
	 * @return 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月12日 上午10:26:02
	 */
	@Override
	public IcbcApplyDTO<IcbcAckDataDTO> mortgageReview(String IcbcJson) throws BzException {
		return null;
	}
	

    /**
     * 组装工行公共头部信息
     * @param responseCommon
     * @return 
     * @author YixinCapital -- chen.lin
     *	       2018年9月13日 下午5:26:09
     */
    protected static IcbcCommonDTO assemberCommHead (IcbcCommonDTO responseCommon){
        // 组装返回公共信息
        responseCommon.setSendtime(DateUitls.dateToStr(new Date(), "yyyyMMdd HH:mm:ss"));
        responseCommon.setChannel(IcbcConstant.CHANNEL_PC);
        responseCommon.setSendcode(IcbcConstant.SENDCODE_YXCD);
        responseCommon.setUnitcode(IcbcConstant.UNITCODE);
		return responseCommon;
    }

    
	/**
	 * 异步通知给请求方银行结果
     * @param feedback 
     * @author YixinCapital -- chen.lin
     *	       2018年9月13日 下午8:42:25
     */
	@Async
	protected void asyncNotifyResult (IcbcApplyDTO<IcbcBackDataDTO> feedback){
		AssetMainInfo mainInfo = AssetMainInfo.getByVenusApplyNo(feedback.getData().getOrderno());
		if (mainInfo == null) {
			LOGGER.error("申请编号错误，资产信息为空，orderno={}", feedback.getData().getOrderno());
        	throw new BzException("申请编号错误，资产信息为空");
		}
        DscFlowResultForAlixDto dto = NoticeApplyDealFactory.getDealClassByFinancialCode(CommonConstant.BankName.ICBC_BANK)
                .setApplyMain(DscSalesApplyMain.getByApplyNo(mainInfo.getApplyNo()))
                .setICBCFeedBack(feedback)
                .deal();
        ioc.publishEvent(new ResultNotifyEvent(dto));
	}
}
