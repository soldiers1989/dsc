package com.yixin.kepler.core.domain.webank;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
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
import com.yixin.kepler.common.enums.WBCarTypeEnum;
import com.yixin.kepler.core.base.AbstractBaseDealDomain;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.enity.AssetMainInfo;

/**
 * @author YixinCapital -- chenjiacheng
 *         2018年07月20日 10:31
 **/
@Service("WeBankPaymentTrialCheckRequestPrepose")
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
	 * 发起请款前，对数据进行校验
	 * @param applyNo 申请编号
	 * @param financeCode 资方编码
	 * @return 校验结果
	 * @author YixinCapital -- chenjiacheng
	 *             2018/7/20 10:35
	 */
	public BaseMsgDTO requestPrepose(String applyNo,String financeCode){
		BaseMsgDTO baseMsgDTO = new BaseMsgDTO();
		baseMsgDTO.setCode(DscContant.KeplerCode.SUCCESS);
		baseMsgDTO.setMessage("SUCCESS");
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
		if (StringUtils.isBlank(main.getTlrNo())) {
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[提报人域账号]不能为空");
		}
		if (StringUtils.isBlank(main.getRentingCompanyCode())) {
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[发起融租公司编号]不能为空");
		}
		if (StringUtils.isBlank(main.getOrderSource())) {
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[订单来源]不能为空");
		}
		if (StringUtils.isBlank(assetMainInfo.getBankOrderNo())) {
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[银行订单编号]不能为空");
		}
		if (StringUtils.isBlank(cust.getAkhxm())) {
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[申请人姓名]不能为空");
		}
		if (StringUtils.isBlank(cust.getAzjlx())) {
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[证件类型]不能为空");
		}
		if (StringUtils.isBlank(cust.getAzjhm())) {
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[证件号码]不能为空");
		}
		if (StringUtils.isBlank(car.getCarId())) {
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[车辆ID]不能为空");
		}
		if (StringUtils.isBlank(car.getAcjh())) {
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[车架号]不能为空");
		}
		if (StringUtils.isBlank(car.getIsNewCar())) {
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[车辆类型]不能为空");
		}
		if (WBCarTypeEnum.USED_CAR.getValue().equals(car.getAcllx())) {
			if (StringUtils.isBlank(car.getAcarnojc())) {
				return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[当前牌照]不能为空");
			}
		}
		if (StringUtils.isBlank(car.getIsGpsActive())) {
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[GPS是否激活]不能为空");
		}
		if (car.getGpsActiveTime() == null) {
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[GPS激活时间]不能为空");
		}
		if (StringUtils.isBlank(car.getGpsCode1())) {
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[GPS设备码1]不能为空");
		}
		if (cost.getFrze() == null || cost.getFrze().compareTo(BigDecimal.ZERO) <= 0) {
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[客户融资额]不能为空或小于等于0");
		}
		if (cost.getFsfje() == null || cost.getFsfje().compareTo(BigDecimal.ZERO) <= 0) {
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[首付]不能为空或小于等于0");
		}
		if (cost.getFsfbl() == null || cost.getFsfbl().compareTo(BigDecimal.ZERO) <= 0) {
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[首付比例]不能为空或小于等于0");
		}
		if (StringUtils.isBlank(main.getArzqx())) {
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[贷款期限]不能为空");
		}
		if (cost.getFkhll() == null || cost.getFkhll().compareTo(BigDecimal.ZERO) < 0) {
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[年化利率/客户利率]不能为空或小于等于0");
		}
		if (StringUtils.isBlank(main.getAcsName())) {
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[交易城市]不能为空");
		}
		if (WBCarTypeEnum.USED_CAR.getValue().equals(car.getAcllx())) {
			if (car.getCarPassDate()==null) {
				return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[车辆过户完成日期]不能为空");
			}
		}

		List<DscSalesApplyFinancing> financingList = DscSalesApplyFinancing.getByMainId(main.getId());
		if (CollectionUtils.isEmpty(financingList)) {
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "[融资项]不能为空");
		}

		return baseMsgDTO;
	}

}
