package com.yixin.kepler.v1.common.core.bankReq;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yixin.dsc.dto.DscLoanRepayDTO;
import com.yixin.dsc.entity.order.DscRepayPlan;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.enity.AssetMainInfo;

/**
 * 银行请求数据处理
 * Package : com.yixin.kepler.v1.common.core.bankReq
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年9月27日 上午11:41:27
 *
 */
public interface BankRequestDataHandle {

	/**
	 *  还款计划处理
	 * @author YixinCapital -- wangwenlong
	 *	       2018年9月27日 上午11:43:32
	 */
	default void repayPlanHandle(List<DscLoanRepayDTO> repayPlanList,AssetMainInfo assetMainInfo,DscSalesApplyMain saleMain){
		if(CollectionUtils.isNotEmpty(repayPlanList)){
			DscRepayPlan.saveRepayPlan(repayPlanList, saleMain.getId(), assetMainInfo.getApplyNo(), assetMainInfo.getFinancialCode(), CommonConstant.BankName.YX);
		}
	};
	
	/**
	 * 更换还款卡信息
	 * @param mainInfo
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年10月12日 下午6:08:07
	 */
	default BaseMsgDTO modifyRepayInfo(AssetMainInfo mainInfo){
		return BaseMsgDTO.successData("操作成功");
	}
}
