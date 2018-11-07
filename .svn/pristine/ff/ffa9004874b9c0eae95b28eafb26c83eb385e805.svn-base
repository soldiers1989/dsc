package com.yixin.kepler.v1.service.capital.icbc;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yixin.common.exception.BzException;
import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.common.DscContant;
import com.yixin.dsc.entity.order.DscSalesApplyCar;
import com.yixin.dsc.entity.order.DscSalesApplyCost;
import com.yixin.dsc.entity.order.DscSalesApplyCust;
import com.yixin.dsc.entity.order.DscSalesApplyFinancing;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.kepler.core.base.AbstractBaseDealDomain;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.enity.AssetMainInfo;

/**
 * 银行请款请求前置
 * Package : com.yixin.kepler.v1.service.capital.icbc
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年9月21日 下午7:48:17
 */
@Service("ICBCPaymentTrialCheckRequestPrepose")
public class PaymentTrialCheckPrepose extends AbstractBaseDealDomain<Object, BaseMsgDTO> {

	private static final Logger logger = LoggerFactory.getLogger(PaymentTrialCheckPrepose.class);

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
	 * 银行请款请求前置
	 * 1.请款前校验		2.校验通过，判断流程（正常-实时or新定时，跳转-老定时）
	 * @param applyNo
	 * @param financeCode
	 * @return 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月21日 下午7:50:50
	 */
	public BaseMsgDTO requestPrepose(String applyNo, String financeCode){
		BaseMsgDTO baseMsgDTO = new BaseMsgDTO();
		baseMsgDTO.setCode(CommonConstant.PreposeResultCode.NORMAL);
		baseMsgDTO.setMessage("校验通过，判断流程为正常-新定时");
		logger.info("发起请款前对数据进行校验============applyNo={}", applyNo);
		DscSalesApplyMain main = DscSalesApplyMain.getByApplyNo(applyNo);
		if (main == null) {
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "订单不存在，请确认订单编号");
		}
		DscSalesApplyCust cust = DscSalesApplyCust.getApplyCostByMianId(main.getId());
		if (cust == null) {
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "客户信息不存在，请确认订单编号");
		}
		DscSalesApplyCar car = DscSalesApplyCar.getBymainId(main.getId());
		if (car == null) {
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "车辆信息不存在，请确认订单编号");
		}
		DscSalesApplyCost cost = DscSalesApplyCost.getByMainId(main.getId());
		if (cost == null) {
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "合同信息不存在，请确认订单编号");
		}
		AssetMainInfo assetMainInfo = AssetMainInfo.getByApplyNo(applyNo);
		if (assetMainInfo == null) {
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "该订单还未提交信审，无法请款，请确认订单编号");
		}
		List<DscSalesApplyFinancing> financingList = DscSalesApplyFinancing.getByMainId(main.getId());
		if (CollectionUtils.isEmpty(financingList)) {
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[融资项]不能为空");
		}
		if (cost.getFrze() == null || cost.getFrze().compareTo(BigDecimal.ZERO) <= 0) {
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[客户融资额]不能为空或小于等于0");
		}
		return baseMsgDTO;
	}

}
