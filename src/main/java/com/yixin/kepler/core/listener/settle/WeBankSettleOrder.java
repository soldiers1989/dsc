package com.yixin.kepler.core.listener.settle;

import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.common.utils.DateUitls;
import com.yixin.dsc.entity.order.*;
import com.yixin.dsc.enumpackage.DscActionEnum;
import com.yixin.dsc.enumpackage.PenaltyRateTypeEnum;
import com.yixin.dsc.service.common.DscWbCommonService;
import com.yixin.dsc.util.DateUtil;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.common.enums.WBCarTypeEnum;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.dto.settle.SettleFinanceProjectDTO;
import com.yixin.kepler.dto.settle.SettleOrderDTO;
import com.yixin.kepler.dto.webank.WBMongoDTO;
import com.yixin.kepler.dto.webank.WBPaymentLoanReceiptDTO;
import com.yixin.kepler.dto.webank.WBPaymentRespDTO;
import com.yixin.kepler.enity.AssetBankTran;
import com.yixin.kepler.enity.AssetFinanceInfo;
import com.yixin.kepler.enity.AssetMainInfo;
import com.yixin.kepler.enity.AssetSettleInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Component(value = "WeBankSettleOrder")
public class WeBankSettleOrder extends AbstractSettleOrder {

	private static final Logger logger = LoggerFactory.getLogger(WeBankSettleOrder.class);

	@Override
	protected SettleOrderDTO createSettleOrderDTO(String applyNo) {

		DscWbCommonService dscWbCommonService = SpringContextUtil.getApplicationContext().getBean(DscWbCommonService.class);
		MongoTemplate mongoTemplate = SpringContextUtil.getApplicationContext().getBean(MongoTemplate.class);
		AssetMainInfo mainInfo = AssetMainInfo.getByApplyNo(applyNo);
		DscSalesApplyMain dscSalesApplyMain = DscSalesApplyMain.getByApplyNo(applyNo);
		SettleOrderDTO settleOrderDTO = new SettleOrderDTO();

		String mainId = dscSalesApplyMain.getId(); // 订单主表id
		// 客户信息
		DscSalesApplyCust cust = DscSalesApplyCust.getApplyCostByMianId(mainId);
		// 订单费用信息
		DscSalesApplyCost cost = DscSalesApplyCost.getByMainId(mainId);
		//合同申请融资额明细表  融资明细信息
		List<DscSalesApplyFinancing> salesApplyFinancings = DscSalesApplyFinancing.getByMainId(mainId);
		//主表
		AssetMainInfo assetMainInfo = AssetMainInfo.getByApplyNo(applyNo);

		@SuppressWarnings("serial")
		ArrayList<SettleFinanceProjectDTO> settleFinanceProjectDTOS = new ArrayList<SettleFinanceProjectDTO>() {{
			salesApplyFinancings.forEach(salesApplyFinancing -> {
				SettleFinanceProjectDTO settleFinanceProjectDTO = new SettleFinanceProjectDTO();
				settleFinanceProjectDTO.setFinanceProjectCode(salesApplyFinancing.getArzxmid()); // 融资项目编号
				settleFinanceProjectDTO.setIsDirect(""); // 是否直放
				settleFinanceProjectDTO.setIsMustFinance(""); // 是否必融
				settleFinanceProjectDTO.setFinanceProjectLoanAmount(new BigDecimal(0)); // 融资项目放款金额
				if (CommonConstant.FinanceType.F010.equals(salesApplyFinancing.getArzxmid())) {
					settleFinanceProjectDTO.setFinanceProjectAmount(salesApplyFinancing.getFkhrzje().subtract(cost.getFsfje())); // 融资项目金额(车款融资项需要减去首付金额)
				} else {
					settleFinanceProjectDTO.setFinanceProjectAmount(salesApplyFinancing.getFkhrzje()); // 融资项目金额
				}

				add(settleFinanceProjectDTO);
			});
		}};

		settleOrderDTO.setFinanceProjectDTOs(settleFinanceProjectDTOS);
		settleOrderDTO.setApplyNo(applyNo);
		// 资金方
		settleOrderDTO.setStakeHolderCode(dscSalesApplyMain.getCapitalId());

		if (assetMainInfo != null) {
			// 借据编号
			List<String> list = new ArrayList<>();
			Query query = new Query();
			Criteria criteria = Criteria.where("merOrderNo").is(dscSalesApplyMain.getApplyNo());
			query.addCriteria(criteria);
			WBPaymentRespDTO paymentRespDTO = mongoTemplate.findOne(query, WBPaymentRespDTO.class);
			if (paymentRespDTO != null && CollectionUtils.isNotEmpty(paymentRespDTO.getLoanReceiptNbrList())) {
				for (WBPaymentLoanReceiptDTO receiptDTO : paymentRespDTO.getLoanReceiptNbrList()) {
					list.add(receiptDTO.getLoanReceiptNbr());
				}
			}
			settleOrderDTO.setLncfnos(list);

		}

		// 还款卡号
		settleOrderDTO.setRepayCardNumber(dscSalesApplyMain.getAhkrjjkzh());
		// 还款方式
		settleOrderDTO.setRepayMethod(dscSalesApplyMain.getAhkfs());
		// 融资期限
		settleOrderDTO.setFinanceMaturity(Integer.valueOf(
				dscSalesApplyMain.getArzqx()));
		// Alix放款金额  ---经金帅确认取经销商收款金额
		settleOrderDTO.setAlixLoanAmount(dscSalesApplyMain.getDealerCollectAmount());
		// Alix放款日期
		String alixLoanDateStr = "";
		if (dscSalesApplyMain.getAlixLoanDate() != null) {
			Date date = dscSalesApplyMain.getAlixLoanDate();
			if (date != null) {
				alixLoanDateStr = DateUtil.dateToString(date, "yyyyMMdd");
			}
		}
		settleOrderDTO.setAlixLoanDate(alixLoanDateStr);

		if (cust != null) {
			// 客户名称
			settleOrderDTO.setCustName(cust.getAkhxm());
			// 证件类型 azjlx
			settleOrderDTO.setCertType(cust.getAzjlx());
			settleOrderDTO.setCertNumber(cust.getAzjhm());
			// 是否贴息产品
			settleOrderDTO.setIsDiscount(cost.getAtxfs());
			// 是否加融资产品
			settleOrderDTO.setIsAddFinance(cost.getAsfrbx());
			// 结算利率
			settleOrderDTO.setSettleInterestRate(getStr(cost.getFjsll()));
			// 结算费率
			settleOrderDTO.setSettleRate(getStr(cost.getFjssxfl()));
			// 客户利率
			settleOrderDTO.setCustInterestRate(getStr(cost.getFkhll()));
			// 融资金额
			settleOrderDTO.setFinanceAmount(getBigDecimal(cost.getFrze()));
			// 银行请款金额
			settleOrderDTO.setBankLoanAmount(getBigDecimal(cost.getFrze()));
		}

		AssetBankTran bankTran = AssetBankTran.getByApplNoAndType(applyNo, BankPhaseEnum.PAYMENT.getPhase());
		if (bankTran != null) {
			Date date = AssetBankTran.getByApplNoAndType(applyNo, BankPhaseEnum.PAYMENT.getPhase()).getCreateTime();
			// 银行放款日期
			settleOrderDTO.setBankLoanDate(DateUitls.dateToStr(date, "yyyyMMdd"));
			// 合同生效日期
			settleOrderDTO.setContractEffectiveDate(settleOrderDTO.getBankLoanDate());
		}

		//订单状态
		settleOrderDTO.setStatus(DscActionEnum.REQUEST_FUNDS_SUCCESS.getCode());

		AssetSettleInfo settleInfo = AssetSettleInfo.getAssetSettleInfoByBankCode(
				CommonConstant.BankName.WB_BANK);

		// 提前还款限制期数--固定值
		settleOrderDTO.setEarlyRepayLimitPeriod(
				settleInfo.getEarlyRepayLimitPeriod());
		// 提前还款最小间隔天数--固定值 0
		settleOrderDTO.setEarlyRepayMinDay(
				settleInfo.getEarlyRepayMinDay());
		// 提前还款违约金收取比例--固定值 0
		settleOrderDTO.setEarlyRepayPenaltyProp(
				settleInfo.getEarlyRepayPenaltyProp());
		// 罚息比率类型--固定值
		settleOrderDTO.setPenaltyRateType(
				PenaltyRateTypeEnum.PENALTY_RATE_DAY.getCode());

		// 银行资金成本费率 fsxfl --固定值
		settleOrderDTO.setBankCostRate(
				settleInfo.getBankCostRate());
		// 客户费率  -- 固定值
		settleOrderDTO.setCustRate(
				settleInfo.getCustRate());
		DscSalesApplyCar dscSalesApplyCar = DscSalesApplyCar.getBymainId(mainId);
		
		//if (dscSalesApplyCar != null) {
		//	//新车
		//	if (StringUtils.equals(dscSalesApplyCar.getAcllx(), "1")) {
		//		// 银行资金成本利率 --新车
		//		settleOrderDTO.setBankCostInterestRate("6.5");
		//		//二手车
		//	} else if (StringUtils.equals(dscSalesApplyCar.getAcllx(), "2")) {
		//		// 银行资金成本利率 --二手车
		//		settleOrderDTO.setBankCostInterestRate("7");
		//	}
		//}
		//微众银行新增-----
		settleOrderDTO.setMerchantId(dscWbCommonService.getMerchantIdByCompanyCode(dscSalesApplyMain.getRentingCompanyCode())); //平台id
		settleOrderDTO.setOpId(dscSalesApplyMain.getCreatorId()); //操作员号
		settleOrderDTO.setChannel(dscWbCommonService.getWbChannelByOrderSource(dscSalesApplyMain.getOrderSource())); //渠道
		settleOrderDTO.setPsCode(dscWbCommonService.getPsCodeByApplyNo(dscSalesApplyMain.getApplyNo())); //产品结构编号
		settleOrderDTO.setCarId(dscWbCommonService.getSpiltCarId(dscSalesApplyCar != null ? dscSalesApplyCar.getCarId() : null)); //车辆id
		settleOrderDTO.setDealerId(""); //车商id
		settleOrderDTO.setCompanyBodyCode(dscSalesApplyMain.getRentingCompanyCode()); //融资公司主体
		settleOrderDTO.setCompanyBodyName(dscSalesApplyMain.getAdyqr()); //公司名称
		settleOrderDTO.setStakeHolderCode(mainInfo.getFinancialCode()); //资方code
		settleOrderDTO.setStakeHolderId(mainInfo.getFinancialId()); //资方id
		settleOrderDTO.setSettleInterestRate(cost.getFjsll() == null ? null : cost.getFjsll().toString()); //结算利率
		settleOrderDTO.setTotalDiscountAmount(cost.getFtxze()); //总贴息金额
		settleOrderDTO.setFirmDiscountAmount(cost.getFcstxze()); //厂家贴息金额
		settleOrderDTO.setDistributorDiscountAmount(cost.getFdlstxze()); //经销商贴息金额
		settleOrderDTO.setIsDiscount(cost.getAtxfs()); //贴息标识
		settleOrderDTO.setIsDistributorDiscount(cost.getJxstxFlag()); //是否经销商贴息
		settleOrderDTO.setIsFirmDiscount(cost.getCstxFlag()); //是否厂商贴息
		settleOrderDTO.setVinNo(dscSalesApplyCar != null ? dscSalesApplyCar.getAcjh() : null);//机动车架号
		settleOrderDTO.setBankOrderNo(assetMainInfo == null ? null : assetMainInfo.getBankOrderNo());//微众订单号

		this.initBankRate(mainInfo.getFinancialId(), applyNo, cost.getFkhll(), settleOrderDTO);

		return settleOrderDTO;
	}


	/**
	 * 实例化银行特殊配置利率/费率
	 *
	 * @param capitalId      资方id
	 * @param applyNo        申请编号
	 * @param fkhll          客户利率
	 * @param settleOrderDTO 推送结算DTO
	 * @author YixinCapital -- wangwenlong
	 * 2018年7月22日 下午10:06:31
	 */
	private void initBankRate(String capitalId, String applyNo, BigDecimal fkhll, SettleOrderDTO settleOrderDTO) {
		AssetFinanceInfo assetFinanceInfo = AssetFinanceInfo.get(AssetFinanceInfo.class, capitalId); //资方信息
		AssetSettleInfo settleInfo = AssetSettleInfo.findFirstByProperty(AssetSettleInfo.class, "financialId", capitalId);
		if (settleInfo==null) settleInfo = new AssetSettleInfo();
		if (assetFinanceInfo != null && CommonConstant.BankName.WB_BANK.equals(assetFinanceInfo.getCode())) { //微众

        	/* * 客户利率大于银行资金成本
        	 * 则用客户利率*1.5倍，
        	 * 客户利率小于银行资金成本
        	 * 则银行资金成本*1.5倍*/

			BigDecimal custInterestRate = fkhll == null ? BigDecimal.ZERO : fkhll; //客户利率
			DscSalesApplyMain main = DscSalesApplyMain.getByApplyNo(applyNo);
			DscSalesApplyCar car = DscSalesApplyCar.getBymainId(main.getId());
			BigDecimal capitalCost = BigDecimal.ZERO; //银行资金成本
			if (WBCarTypeEnum.NEW_CAR.getValue().equals(car.getAcllx())) {
				capitalCost = assetFinanceInfo.getCapitalCostNewCar() == null ? BigDecimal.ZERO : assetFinanceInfo.getCapitalCostNewCar(); //新车银行资金成本费率
			} else if (WBCarTypeEnum.USED_CAR.getValue().equals(car.getAcllx())) {
				capitalCost = assetFinanceInfo.getCapitalCostUsedCar() == null ? BigDecimal.ZERO : assetFinanceInfo.getCapitalCostUsedCar(); //二手车银行资金成本费率
			}
			BigDecimal floatingRate = assetFinanceInfo.getFloatingRate() == null ? BigDecimal.ZERO : assetFinanceInfo.getFloatingRate(); //浮动利率
			logger.info("结算推送数据-微众，客户利率:{},银行资金成本:{},浮动利率：{}", custInterestRate, capitalCost, floatingRate);
			BigDecimal max = custInterestRate.compareTo(capitalCost) >= 0 ? custInterestRate : capitalCost;
			logger.info("结算推送数据-微众，客户利率:{},银行资金成本:{},浮动利率：{},最大利率:{}", custInterestRate, capitalCost, floatingRate, max);
			BigDecimal penaltyRate = max.multiply(floatingRate);
			// ==================== 记录信息 开始=====================
			MongoTemplate mongoTemplate = SpringContextUtil.getApplicationContext()
					.getBean(MongoTemplate.class);
			Query query = new Query();
			query.addCriteria(Criteria.where("applyNo").is(applyNo));
			List<WBMongoDTO> mongoDTOList = mongoTemplate.findAllAndRemove(query, WBMongoDTO.class);
			if (CollectionUtils.isNotEmpty(mongoDTOList)) {
				WBMongoDTO wbMongo = mongoDTOList.get(0);
				wbMongo.setFkhll(fkhll); //客户利率
				wbMongo.setCapitalCost(capitalCost); //银行资金成本
				wbMongo.setFloatingRate(floatingRate); //浮动利率
				wbMongo.setPenaltyRate(penaltyRate); //罚息率
				mongoTemplate.save(wbMongo);

			}
			// ==================== 记录信息 结束=====================
			// 罚息率
			settleOrderDTO.setPenaltyRate(penaltyRate.toString());
			// 银行资金成本利率
			settleOrderDTO.setBankCostInterestRate(capitalCost.toString());
			// 逾期宽限期 ---固定值 0
			settleOrderDTO.setGracePeriod(
					settleInfo.getGracePeriod());
		}
	}

}
