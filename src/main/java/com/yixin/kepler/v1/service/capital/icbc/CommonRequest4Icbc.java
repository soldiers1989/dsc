package com.yixin.kepler.v1.service.capital.icbc;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.transaction.annotation.Transactional;

import com.yixin.common.exception.BzException;
import com.yixin.common.utils.DateUitls;
import com.yixin.common.utils.InvokeResult;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.dsc.util.PropertiesManager;
import com.yixin.kepler.common.enums.AssetStateEnum;
import com.yixin.kepler.core.base.AbstractBaseDealDomain;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.enity.AssetBankTran;
import com.yixin.kepler.enity.AssetMainInfo;
import com.yixin.kepler.v1.common.constants.BankRequestConstant;
import com.yixin.kepler.v1.common.constants.IcbcConstant;
import com.yixin.kepler.v1.common.constants.UrlConstant;
import com.yixin.kepler.v1.common.enumpackage.BankPhaseEnum;
import com.yixin.kepler.v1.common.util.SerialNumberUtil;
import com.yixin.kepler.v1.datapackage.dto.icbc.IcbcApplyDTO;
import com.yixin.kepler.v1.datapackage.dto.icbc.IcbcCommonDTO;
import com.yixin.kepler.v1.datapackage.entity.AssetBankRequest;


/**
 * 工商银行通用请求策略
 * Package : com.yixin.kapler_v1.service.capital.icbc
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年9月11日 上午11:56:37
 *
 */

public abstract class CommonRequest4Icbc<T> extends AbstractBaseDealDomain<String, BaseMsgDTO> {
	
	private static final Logger logger = LoggerFactory.getLogger(CommonRequest4Icbc.class);

	protected ThreadLocal<IcbcApplyDTO<T>> dataThreadLocal = new ThreadLocal<>();//工行申请传输
	
	protected ThreadLocal<String> ordernoLocal = new ThreadLocal<>();//工行传入银行侧的申请编号
		
	@Resource
	private PropertiesManager propertiesManager;
	
	@Resource
	private SerialNumberUtil serialNumberUtil;

	

	/**
	 * 初始化公共报文COMM域字段要素
	 * @throws BzException 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月11日 下午5:07:11
	 */
	protected void init() throws BzException {
		final String applyNo = (String) this.inputDto.get();
		logger.info("[工商银行]初始化请求数据开始 applyNo：{}", applyNo);
		IcbcCommonDTO commonDataDTO = new IcbcCommonDTO();// 组装common域
		// 合作机构代码
		commonDataDTO.setUnitcode(IcbcConstant.UNITCODE);
		// 业务编号
		commonDataDTO.setOrderno(this.ordernoLocal.get());
		// 发送时间
		commonDataDTO.setSendtime(DateUitls.dateToStr(new Date(), "yyyyMMdd HH:mm:ss"));
		// 交易流水
		commonDataDTO.setSendserno(serialNumberUtil.getTranNo4Idfactory(IcbcConstant.TRAN_BIZCODE, IcbcConstant.TRAN_SYSCODE));
		// 业务发起渠道
		commonDataDTO.setChannel(IcbcConstant.CHANNEL_PC);
		// 请求方标识码
		commonDataDTO.setSendcode(IcbcConstant.SENDCODE_YXCD);
		// 数字签名域
		commonDataDTO.setSigncode(IcbcConstant.SIGNCODE);
		IcbcApplyDTO<T> data = new IcbcApplyDTO<>();
		data.setComm(commonDataDTO);
		dataThreadLocal.set(data);
		setTxtCode();// 初始化接口代码
		logger.info("[工商银行]初始化请求数据结束 applyNo：{}", applyNo);
	}
	

	/**
	 * 初始化接口代码
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月11日 下午5:07:33
	 */
	protected abstract void setTxtCode();
	
	
	/**
	 * 执行入口
	 * @param applyNo
	 * @return 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月11日 下午5:13:54
	 */
	@Override
	public InvokeResult<BaseMsgDTO> doIt(String applyNo) {
		this.inputDto.set(applyNo);
		// 特殊数据提前处理
		this.dataFirst();
		// 初始化请求头信息
		this.init();
		// 执行数据
		return super.doIt(applyNo);
	}
	
	
	/**
	 * 对特殊数据提前进行处理
	 * @throws BzException 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月12日 下午3:43:48
	 */
	protected void dataFirst() throws BzException {
		final String applyNo = (String) this.inputDto.get();
		String orderno = null;//银行申请编号
		//获取资产信息，判断银行申请编号
		AssetMainInfo mainInfo = AssetMainInfo.getByApplyNo(applyNo);
		if(mainInfo != null && mainInfo.getVenusApplyNo() != null){
			orderno = mainInfo.getVenusApplyNo();
		}else{
			orderno = serialNumberUtil.getTranNo4Idfactory(IcbcConstant.ORDERNO_BIZCODE , IcbcConstant.ORDERNO_SYSCODE);
		}
		if (StringUtils.isBlank(orderno)) {
			logger.error("获取银行申请编号为空，系统中第三方的申请编号为={}", applyNo);
			throw new BzException("获取银行申请编号为空");
		}
		this.ordernoLocal.set(orderno);
	}
	
	
	@Transactional
	@Override
	protected InvokeResult<BaseMsgDTO> message() throws BzException {
		try {
			final IcbcApplyDTO<T> requestDto = this.dataThreadLocal.get();
			String trxcode = requestDto.getComm().getTrxcode();

			String osbUrl = this.propertiesManager.getOsbIcbcUrl() + UrlConstant.OsbSystemUrl.icbcBankInterface;
			//-------------获取流程信息数据
			AssetMainInfo assetMainInfo = AssetMainInfo.getByApplyNo(inputDto.get().toString());
			if (assetMainInfo == null) {
				logger.info("申请单号为{}，订单还未发起信审", inputDto.get().toString());
				throw new BzException("订单还未发起信审");
			}
			
			//-------------判断是否在进行中，进行最后拦截
			if(AssetStateEnum.DOING.getState().equals(assetMainInfo.getLastState()) || AssetStateEnum.DOING.getState().equals(assetMainInfo.getPaymentState())){
				logger.error("受理失败！！！ 订单编号:{}该申请，正在进行中", inputDto.get().toString());
				return new InvokeResult<>(new BaseMsgDTO(CommonConstant.FAILURE, "受理失败，该申请正在进行中"));
			}
			
			//-------------判断该申请是否审核通过，进行最后拦截
			if(AssetStateEnum.SUCCESS.getState().equals(assetMainInfo.getLastState()) && (trxcode.equals(IcbcConstant.TRXCODE_10101) || trxcode.equals(IcbcConstant.TRXCODE_10181))){
				logger.error("受理失败！！！ 订单编号:{}该申请，信审审核已通过", inputDto.get().toString());
				return new InvokeResult<>(new BaseMsgDTO(CommonConstant.FAILURE, "受理失败，信审审核已通过"));
			} else if(AssetStateEnum.SUCCESS.getState().equals(assetMainInfo.getPaymentState()) && (trxcode.equals(IcbcConstant.TRXCODE_40101) || trxcode.equals(IcbcConstant.TRXCODE_40181))){
				logger.error("受理失败！！！ 订单编号:{}该申请，请款审核已通过", inputDto.get().toString());
				return new InvokeResult<>(new BaseMsgDTO(CommonConstant.FAILURE, "受理失败，请款审核已通过"));
			}


			//-------------存储上送银行报文
			AssetBankTran assetBankTran = new AssetBankTran();
			assetBankTran.setReqBody(JsonObjectUtils.objectToJson(requestDto));
			assetBankTran.setApplyNo(assetMainInfo.getApplyNo());
			assetBankTran.setBankOrderNo(assetMainInfo.getVenusApplyNo());
			assetBankTran.setTranNo(requestDto.getComm().getSendserno()); //交易流水号
			assetBankTran.setAssetId(assetMainInfo.getFinancialId()); //资方id
			assetBankTran.setAssetNo(assetMainInfo.getFinancialCode()); //资方code
			assetBankTran.setApiCode(trxcode); //接口代码
			
			if (trxcode.equals(IcbcConstant.TRXCODE_10101)){//信审
				assetBankTran.setPhase(BankPhaseEnum.LAST_TRIAL.getPhase());
				assetMainInfo.setAssetPhase(BankPhaseEnum.LAST_TRIAL.getPhase());
			}else if(trxcode.equals(IcbcConstant.TRXCODE_10181)){//信审驳回
				assetBankTran.setPhase(BankPhaseEnum.LAST_TRIAL_REJECT.getPhase());
				assetMainInfo.setAssetPhase(BankPhaseEnum.LAST_TRIAL.getPhase());
			}else if(trxcode.equals(IcbcConstant.TRXCODE_40101)){//请款
				assetBankTran.setPhase(BankPhaseEnum.PAYMENT.getPhase());
				assetMainInfo.setAssetPhase(BankPhaseEnum.PAYMENT.getPhase());
			}else if(trxcode.equals(IcbcConstant.TRXCODE_40181)){//请款驳回
				assetBankTran.setPhase(BankPhaseEnum.PAYMENT_REJECT.getPhase());
				assetMainInfo.setAssetPhase(BankPhaseEnum.PAYMENT_REJECT.getPhase());
			}
			assetMainInfo.setAssetNo(requestDto.getComm().getSendserno()); //交易流水号
			assetBankTran.setReqUrl(osbUrl);
			assetBankTran.setSender(CommonConstant.SenderType.YIXIN);
			
			String examineState = null;//银行审核阶段 【信审，请款】
			//更新为处理中
			if (trxcode.equals(IcbcConstant.TRXCODE_10101) || trxcode.equals(IcbcConstant.TRXCODE_10181)){//信审
				assetMainInfo.setLastState(AssetStateEnum.DOING.getState()); 
				examineState = BankPhaseEnum.LAST_TRIAL.getPhase();
			}else if (trxcode.equals(IcbcConstant.TRXCODE_40101) || trxcode.equals(IcbcConstant.TRXCODE_40181)){//请款
				assetMainInfo.setPaymentState(AssetStateEnum.DOING.getState()); 
				examineState = BankPhaseEnum.PAYMENT.getPhase();
			}

			//-------------新增资产发起银行请求（任务）--唯一指定入口
			AssetBankRequest request = AssetBankRequest.getOnlyApplyOnWay(assetMainInfo.getApplyNo(), examineState);
			if (null == request) {
				request = new AssetBankRequest();
			} else {
				logger.error("受理失败！！！ 订单编号:{}", inputDto.get().toString());
				return new InvokeResult<>(new BaseMsgDTO(CommonConstant.FAILURE, "受理失败，该申请正在处理中"));
			}
			request.setApplyNo(assetMainInfo.getApplyNo());//申请编号
			request.setBankOrderNo(assetMainInfo.getVenusApplyNo());//银行申请编号
			request.setTranNo(requestDto.getComm().getSendserno());//流水号
			request.setAssetNo(assetMainInfo.getFinancialCode());//资产编号
			request.setAssetReqBody(JsonObjectUtils.objectToJson(requestDto));//资管请求报文
			request.setPhase(examineState);//所属阶段
			request.setReqUrl(osbUrl);//请求的url地址
			request.setReqState(BankRequestConstant.UN_REQ_BANK);//向银行发起请求状态 0-未发起
			request.setRetryMark(BankRequestConstant.RETRY_INIT_COUNT);//请求重试标记初始化

			//--------------更新数据
			assetBankTran.create();
			assetMainInfo.update();
			request.create();
			return new InvokeResult<>(new BaseMsgDTO(CommonConstant.SUCCESS, "SUCCESS"));
		} catch (Exception e) {
			logger.error("受理失败！！！ 订单编号:{}，错误信息:{}", inputDto.get().toString(), e);
			return new InvokeResult<>(new BaseMsgDTO(CommonConstant.FAILURE, "受理失败"));
		}
	}
}