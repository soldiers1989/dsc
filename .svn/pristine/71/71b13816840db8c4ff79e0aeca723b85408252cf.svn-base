package com.yixin.kepler.core.listener.settle;


import java.math.BigDecimal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.dsc.service.common.DscSettleCommonService;
import com.yixin.dsc.util.PropertiesManager;
import com.yixin.kepler.common.RestUtil;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.dto.settle.SettleOrderDTO;
import com.yixin.kepler.enity.AssetBankTran;
import com.yixin.kepler.enity.AssetProductFinancialRel;
import com.yixin.kepler.v1.datapackage.dto.other.LoanInfoDTO;

import net.sf.json.JSONObject;

/**
 * @author sukang
 */
public abstract class AbstractSettleOrder {
	
	private Logger logger = LoggerFactory.getLogger(getClass());
	
	public void sendSettleOrder(String applyNo){

		//创建settleOrderDTO对象
		SettleOrderDTO settleOrderDTO = createSettleOrderDTO(applyNo);

		/**
		 * add by wangwenlong on 2018-10-19 装配公共字段
		 */
		this.commonAssembleInfo(settleOrderDTO);
		
		String settleOrderStr = JSONObject.fromObject(settleOrderDTO).toString();
		PropertiesManager propertiesManager = SpringContextUtil.getBean(PropertiesManager.class);

		//保存交易记录
		AssetBankTran saveTransData = saveTransData(settleOrderStr,applyNo,propertiesManager.getPushSettleUrl());

		//发送接口请求
		logger.info("订单号{}反推结算信息请求url为：{},推送数据:{}",applyNo, propertiesManager.getPushSettleUrl(),settleOrderStr);
		String jsonResult = RestUtil.postJson(propertiesManager.getPushSettleUrl(),settleOrderStr);
		logger.info("订单号{}反推结算信息响应报文：{}",applyNo, JSONObject.fromObject(jsonResult));

		//返回处理
		JSONObject restResult = JSONObject.fromObject(jsonResult);
		saveTransData.setRepBody(jsonResult);
		saveTransData.setApprovalCode(restResult.isNullObject() ? null: restResult.getString("success"));
		saveTransData.setApprovalDesc(restResult.isNullObject() ? null :restResult.getString("errorMessage"));
		saveTransData.update();

		//失败处理

	}
	
	/**
	 * 推送结算信息组装公共信息
	 * @param settleOrderDTO 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年10月19日 上午11:19:12
	 */
	public void commonAssembleInfo(SettleOrderDTO settleOrderDTO){
		String applyNo = settleOrderDTO.getApplyNo();
		logger.info("订单编号:{},推送结算信息组装公共信息开始");
		DscSalesApplyMain dscSalesApplyMain = DscSalesApplyMain.getByApplyNo(applyNo);
		if(dscSalesApplyMain != null){
			settleOrderDTO.setProductCode(dscSalesApplyMain.getProductNo()); //产品编码
			settleOrderDTO.setProductName(AssetProductFinancialRel.getProductNameByProductCode(dscSalesApplyMain.getProductNo()));//产品名称
		}
		logger.info("订单编号:{},推送结算信息组装公共信息结束");
	}
	
	
	/**
	 * 推送结算放款信息
	 * @param loanInfo 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年10月15日 下午6:27:23
	 */
	public void pushLoanInfo(LoanInfoDTO loanInfo){
		DscSettleCommonService settleCommonService = SpringContextUtil.getBean("dscSettleCommonService",DscSettleCommonService.class);
		settleCommonService.pushFk(loanInfo.getApplyNo(), loanInfo.getBankSeq(), loanInfo.getLoanDime(), loanInfo.getLoanAmount(), loanInfo.getLoanSuccess());
	}
	
	
	private AssetBankTran saveTransData(String settleOrderDTO,String applyNo,String url){
		//记录交易信息
        AssetBankTran assetBankTran = new AssetBankTran();
        assetBankTran.setApplyNo(applyNo);
        assetBankTran.setReqBody(settleOrderDTO);
        assetBankTran.setReqUrl(url);
        assetBankTran.setPhase(BankPhaseEnum.PUSHFILE.getPhase());
        assetBankTran.create();
        return assetBankTran;
	}

	/**
	 * 根据订单号创建SettleOrderDTO
	 * @param applyNo 申请单号
	 * @return SettleOrderDTO
	 */
	protected abstract SettleOrderDTO createSettleOrderDTO(String applyNo);
	
	
	
	String getStr(Object object) {
		return object == null ? null : String.valueOf(object);
	}
	
	BigDecimal getBigDecimal(Object object) {
		if (object == null ) {return BigDecimal.ZERO;}

		try {
			return new BigDecimal(String.valueOf(object));
		} catch (Exception e) {
			logger.error("创建bigDecimal对象异常",e);
		}
		return BigDecimal.ZERO;
	}

}
