package com.yixin.kepler.v1.service.capital.icbc;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.dsc.common.DscContant;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.v1.common.constants.IcbcConstant;
import com.yixin.kepler.v1.common.core.callbank.CallBackHandle;
import com.yixin.kepler.v1.datapackage.dto.CallBackDTO;
import com.yixin.kepler.v1.datapackage.dto.icbc.IcbcAckDataDTO;
import com.yixin.kepler.v1.datapackage.dto.icbc.IcbcApplyDTO;
import com.yixin.kepler.v1.datapackage.dto.icbc.IcbcBackDataDTO;
import com.yixin.kepler.v1.utils.JacksonUtils;

/**
 * 工行-银行侧回调实现类
 * Package : com.yixin.kapler_v1.service.capital.icbc
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年9月12日 上午10:05:42
 *
 */
public abstract class ICBCCallbackService implements CallBackHandle {

	
	private static final Logger logger = LoggerFactory.getLogger(ICBCCallbackService.class);

	
	/**
	 * 对回调数据进行校验
	 * @param callBack
	 * @return 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月25日 下午12:09:42
	 */
	@Override
	public BaseMsgDTO checkData(CallBackDTO callBack) {
		try {
			if (StringUtils.isBlank(callBack.getCallBackType())) {
				return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[回调未收妥]回调类型为空");
			}
			if (StringUtils.isBlank(callBack.getSourceCode())) {
				return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[回调未收妥]资方来源为空");
			}
			if (StringUtils.isBlank(callBack.getCapitalOrderNO())) {
				return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[回调未收妥]资方申请编号为空");
			}
			String businessData = JsonObjectUtils.objectToJson(callBack.getBusinessData());
			if (StringUtils.isBlank(businessData)) {
				return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[回调未收妥]业务数据不合法");
			}
			//是否可转化为目标业务数据
			IcbcApplyDTO<IcbcBackDataDTO> feedback = (IcbcApplyDTO<IcbcBackDataDTO>)JacksonUtils.getObjectFromJson(businessData, IcbcApplyDTO.class, IcbcBackDataDTO.class);
			return new BaseMsgDTO(DscContant.KeplerCode.SUCCESS, "SUCCESS");
		} catch (Exception e) {
			logger.error("对回调数据进行校验失败，申请编号={}，错误={}", callBack.getCapitalOrderNO(), e);
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[回调未收妥]对回调数据进行校验失败");
		}
	}


	/**
	 * 适配到具体的回调方法并实现
	 * callBackType：
	 * 			letterReview	信审
	 * 			faceTackReview  面签
	 * 			requestFundsReview 请款
	 * @param resultDTO
	 * @return 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月25日 下午12:05:57
	 */
	@Override
	public CallBackDTO respResultHandle(CallBackDTO callBack) {
		IcbcApplyDTO<IcbcAckDataDTO> businessData = null;
		String data = JsonObjectUtils.objectToJson(callBack.getBusinessData());
		try {
			if(IcbcConstant.letterReview.equals(callBack.getCallBackType())){
				businessData = letterReview(data);
			}else if(IcbcConstant.faceTackReview.equals(callBack.getCallBackType())){
				businessData = faceTackReview(data);
			}else if(IcbcConstant.requestFundsReview.equals(callBack.getCallBackType())){
				businessData = requestFundsReview(data);
			}
			callBack.setBusinessData(businessData);
		} catch (Exception e) {
			logger.error("适配到具体的回调方法并实现失败，申请编号={}，错误={}", callBack.getCapitalOrderNO(), e);
			callBack.setBusinessData(businessData);
		}
		return callBack;
	}
	
	
	/**
	 * 工行信审结果回调
	 * @param IcbcJson
	 * @return 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月12日 上午10:26:02
	 */
	protected abstract IcbcApplyDTO<IcbcAckDataDTO> letterReview(String IcbcJson);

	
	
	/**
	 * 工行面签结果回调
	 * @param IcbcJson
	 * @return 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月12日 上午10:26:02
	 */
	protected abstract IcbcApplyDTO<IcbcAckDataDTO> faceTackReview(String IcbcJson);
	
	
	
	/**
	 * 工行请款结果回调
	 * @param IcbcJson
	 * @return 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月12日 上午10:26:02
	 */
	protected abstract IcbcApplyDTO<IcbcAckDataDTO> requestFundsReview(String IcbcJson);
	
	
	
	/**
	 * 工行抵押确认回调
	 * @param IcbcJson
	 * @return 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月12日 上午10:26:02
	 */
	protected abstract IcbcApplyDTO<IcbcAckDataDTO> mortgageReview(String IcbcJson);

}
