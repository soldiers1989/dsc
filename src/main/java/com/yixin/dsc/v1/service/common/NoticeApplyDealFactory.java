package com.yixin.dsc.v1.service.common;

import org.springframework.stereotype.Component;

import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.dsc.util.CommonUtil;
import com.yixin.kepler.enity.AssetFinanceInfo;

/**
 * 通知提报端处理类工厂
 * Package : com.yixin.kepler.v1.common
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年10月16日 上午11:19:59
 */
@Component
public class NoticeApplyDealFactory {
	
	
	private static final String SUFFIX = "NoticeApplyDeal";

	
	/**
	 * 根据资方Code获取不同的准入后处理类
	 * @return
	 */
	public static NoticeApplyDeal getDealClassByApply(String applyNo){
		DscSalesApplyMain applyMain = DscSalesApplyMain.getByApplyNo(applyNo);
		return getDealClassByApply(applyMain);
	}

	
	/**
	 * 根据资方Code获取不同的准入后处理类
	 * @return
	 */
	public static NoticeApplyDeal getDealClassByApply(DscSalesApplyMain applyMain ){
		//申请编号
		AssetFinanceInfo financeInfo = AssetFinanceInfo.get(AssetFinanceInfo.class, applyMain.getCapitalId());
		return getDealClassByFinancialCode(financeInfo.getCode());
	}

	
	/**
	 * 根据资方Code获取不同的准入后处理类
	 * @return
	 */
	public static NoticeApplyDeal getDealClassByFinancialCode(String financialCode){
		return SpringContextUtil.getApplicationContext().getBean(CommonUtil.getBeanNameByFinancialCode(financialCode, SUFFIX), NoticeApplyDeal.class);
	}
}
