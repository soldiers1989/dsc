package com.yixin.kepler.core.listener.settle;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Component;

import com.yixin.common.utils.DateUitls;
import com.yixin.dsc.entity.order.DscRepayPlan;
import com.yixin.dsc.entity.order.DscSalesApplyCost;
import com.yixin.dsc.entity.order.DscSalesApplyCust;
import com.yixin.dsc.entity.order.DscSalesApplyFinancing;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.dsc.enumpackage.DscActionEnum;
import com.yixin.dsc.enumpackage.PenaltyRateTypeEnum;
import com.yixin.dsc.util.DateUtil;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.dto.settle.PlanDTO;
import com.yixin.kepler.dto.settle.SettleFinanceProjectDTO;
import com.yixin.kepler.dto.settle.SettleOrderDTO;
import com.yixin.kepler.enity.AssetMainInfo;

/**
 * @author sukang
 */
@Component(value = "YNTRUSTSettleOrder")
public class YNTRUSTSettleOrder extends AbstractSettleOrder{

    @Override
    protected SettleOrderDTO createSettleOrderDTO(String applyNo) {
        SettleOrderDTO settleOrderDTO = new SettleOrderDTO();

        AssetMainInfo mainInfo = AssetMainInfo.getByApplyNo(applyNo);

        DscSalesApplyMain dscSalesApplyMain = DscSalesApplyMain.getByApplyNo(applyNo);
        // 客户信息
        DscSalesApplyCust cust = DscSalesApplyCust.getApplyCostByMianId(dscSalesApplyMain.getId());
        // 订单费用信息
        DscSalesApplyCost cost = DscSalesApplyCost.getByMainId(dscSalesApplyMain.getId());

        //合同申请融资额明细表  融资明细信息
        List<DscSalesApplyFinancing> salesApplyFinancings = DscSalesApplyFinancing.getByMainId(dscSalesApplyMain.getId());

        @SuppressWarnings("serial")
        ArrayList<SettleFinanceProjectDTO> settleFinanceProjectDTOS = new ArrayList<SettleFinanceProjectDTO>() {{
            salesApplyFinancings.forEach(salesApplyFinancing -> {
				SettleFinanceProjectDTO settleFinanceProjectDTO = new SettleFinanceProjectDTO();
				settleFinanceProjectDTO.setFinanceProjectCode(salesApplyFinancing.getArzxmid()); // 融资项目编号
				settleFinanceProjectDTO.setIsDirect(""); // 是否直放
				settleFinanceProjectDTO.setIsMustFinance(""); // 是否必融
				settleFinanceProjectDTO.setFinanceProjectLoanAmount(new BigDecimal(0)); // 融资项目放款金额
				if (CommonConstant.FinanceType.F010.equals(salesApplyFinancing.getArzxmid())) {
					settleFinanceProjectDTO.setFinanceProjectAmount(salesApplyFinancing.getFkhrzje() ==null?null:salesApplyFinancing.getFkhrzje().subtract(cost.getFsfje())); // 融资项目金额(车款融资项需要减去首付金额)
				} else {
					settleFinanceProjectDTO.setFinanceProjectAmount(salesApplyFinancing.getFkhrzje()); // 融资项目金额
				}

				add(settleFinanceProjectDTO);
			});
        }};

        settleOrderDTO.setFinanceProjectDTOs(settleFinanceProjectDTOS);
        settleOrderDTO.setApplyNo(applyNo);
        //资金方
        settleOrderDTO.setStakeHolderCode(mainInfo.getFinancialCode());

        //资方id
        settleOrderDTO.setStakeHolderId(mainInfo.getFinancialId());
        //借据编号
        settleOrderDTO.setLncfnos(null);
        // 还款卡号
        settleOrderDTO.setRepayCardNumber(dscSalesApplyMain.getAhkrjjkzh());
        // 还款方式
        settleOrderDTO.setRepayMethod(dscSalesApplyMain.getAhkfs());
        //公司主体
        settleOrderDTO.setCompanyBodyCode(dscSalesApplyMain.getRentingCompanyCode());
        //公司主体名称
        settleOrderDTO.setCompanyBodyName(dscSalesApplyMain.getAdyqr());
        // 融资期限
        settleOrderDTO.setFinanceMaturity(Integer.valueOf(dscSalesApplyMain.getArzqx()));
        // Alix放款金额  ---经金帅确认取经销商收款金额
        settleOrderDTO.setAlixLoanAmount(dscSalesApplyMain.getDealerCollectAmount());
        // Alix放款日期
        settleOrderDTO.setAlixLoanDate(DateUitls.dateToStr(dscSalesApplyMain.getAlixLoanDate(),"yyyyMMdd"));

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

        // 银行放款日期
        settleOrderDTO.setBankLoanDate(null);
        // 合同生效日期
        settleOrderDTO.setContractEffectiveDate(settleOrderDTO.getBankLoanDate());

        //订单状态
        settleOrderDTO.setStatus(DscActionEnum.REQUEST_FUNDS_SUCCESS.getCode());

        // 提前还款限制期数--固定值
        settleOrderDTO.setEarlyRepayLimitPeriod(null);
        // 提前还款最小间隔天数--固定值
        settleOrderDTO.setEarlyRepayMinDay(null);
        // 逾期宽限期 ---固定值
        settleOrderDTO.setGracePeriod(null);
        // 提前还款违约金收取比例--固定值
        settleOrderDTO.setEarlyRepayPenaltyProp(null);
        // 罚息比率类型--固定值
        settleOrderDTO.setPenaltyRateType(PenaltyRateTypeEnum.PENALTY_RATE_DAY.getCode());
        // 罚息率--固定值
        settleOrderDTO.setPenaltyRate(null);
        // 银行资金成本费率 fsxfl --固定值
        settleOrderDTO.setBankCostRate(null);
        // 客户费率  -- 固定值
        settleOrderDTO.setCustRate(null);

        settleOrderDTO.setBankCostInterestRate(null);
        
        //组装还款计划
        settleOrderDTO.setVenuApplyNo(mainInfo.getVenusApplyNo());
        //银行产品编号
        settleOrderDTO.setBankProductCode(mainInfo.getAssetNo());

        List<PlanDTO> planDTOS = createPlanDtos(dscSalesApplyMain);
        settleOrderDTO.setPlanDTOs(planDTOS);
        return settleOrderDTO;
    }

    /**
     * 还款计划
     * @param dscSalesApplyMain main表
     * @return List<PlanDTO>
     */
    private List<PlanDTO> createPlanDtos(DscSalesApplyMain dscSalesApplyMain) {
        List<DscRepayPlan> dscRepayPlans = DscRepayPlan.getYXRepayPlan(dscSalesApplyMain.getApplyNo());
       
        List<PlanDTO> planDTOArrayList = new ArrayList<>();
        PlanDTO planDTO = null;
        for (DscRepayPlan dscRepayPlan : dscRepayPlans) {
        	if(0 == dscRepayPlan.getTerm()){ //0期，不推送结算
				continue;
			}
            planDTO = new PlanDTO();
            //总期
            planDTO.setTotalPeriod(Integer.valueOf(dscSalesApplyMain.getArzqx())); //融资期限
            //当前期
            planDTO.setCurrentPeriod(dscRepayPlan.getTerm());
            //计划还款日期 DateUtil.DEFAULT_DATE_SMALL --  yyyyMMdd
            planDTO.setPlanRepayDate(DateUtil.dateToString(dscRepayPlan.getRepayDate(), DateUtil.DEFAULT_DATE_SMALL));
            //利息
            planDTO.setPlanRepayInterest(dscRepayPlan.getRepayProfit());
            //本金
            planDTO.setPlanRepayPrincipal(dscRepayPlan.getRepayPrincipal());

            //客户本期应还总金额
            planDTO.setPlanTotalRepayment(
                    dscRepayPlan.getRepayProfit()
                            .add(dscRepayPlan.getRepayPrincipal())
            );
            //还款计划每期的唯一标识
            planDTO.setScheduleNo(dscRepayPlan.getScheduleNo());
            
            planDTOArrayList.add(planDTO);
        }
        return planDTOArrayList;
    }


}
