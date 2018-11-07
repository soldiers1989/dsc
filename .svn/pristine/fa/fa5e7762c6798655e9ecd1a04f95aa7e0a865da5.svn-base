package com.yixin.kepler.core.domain;

import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.dsc.util.CommonUtil;
import com.yixin.dsc.v1.service.capital.AfterShuntDeal;
import com.yixin.kepler.core.attachment.AttachmentDomain;
import com.yixin.kepler.dto.OsbAttachmentDTO;
import com.yixin.kepler.enity.AssetFinanceInfo;
import org.springframework.stereotype.Component;

/**
 * 准入后处理工厂类
 * @author YixinCapital -- xjt
 *		   2018年9月28日 下午2:53:40
 */
@Component
public class AfterShuntDealFactory {
	
	private static final String SUFFIX = "AfterShuntDeal";

	/**
	 * 根据资方Code获取不同的准入后处理类
	 * @return
	 */
	public AfterShuntDeal getDealClassByApply(String applyNo){
		DscSalesApplyMain applyMain = DscSalesApplyMain.getByApplyNo(applyNo);
		return this.getDealClassByApply(applyMain);
	}

	/**
	 * 根据资方Code获取不同的准入后处理类
	 * @return
	 */
	public AfterShuntDeal getDealClassByApply(DscSalesApplyMain applyMain ){
		//申请编号
		AssetFinanceInfo financeInfo = AssetFinanceInfo.get(AssetFinanceInfo.class, applyMain.getCapitalId());
		return getDealClassByFinancialCode(financeInfo.getCode());
	}

	/**
	 * 根据资方Code获取不同的准入后处理类
	 * @return
	 */
	public AfterShuntDeal getDealClassByFinancialCode(String financialCode){
		return SpringContextUtil.getApplicationContext().getBean(CommonUtil.getBeanNameByFinancialCode(financialCode, SUFFIX), AfterShuntDeal.class);
	}

}
