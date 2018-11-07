package com.yixin.kepler.core.listener.settle;

import com.yixin.common.utils.DateUitls;
import com.yixin.dsc.entity.order.*;
import com.yixin.dsc.enumpackage.DscActionEnum;
import com.yixin.dsc.enumpackage.PenaltyRateTypeEnum;
import com.yixin.dsc.util.DateUtil;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.dto.settle.SettleFinanceProjectDTO;
import com.yixin.kepler.dto.settle.SettleOrderDTO;
import com.yixin.kepler.enity.AssetMainInfo;
import com.yixin.kepler.enity.AssetSettleInfo;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @date: 2018-11-04 17:13
 */
public class YILLIONSettleOrder extends AbstractSettleOrder {

    @Override
    protected SettleOrderDTO createSettleOrderDTO(String applyNo) {

        SettleOrderDTO settleOrderDTO = new SettleOrderDTO();

        AssetMainInfo mainInfo = AssetMainInfo.getByApplyNo(applyNo);

        DscSalesApplyMain dscSalesApplyMain = DscSalesApplyMain.getByApplyNo(applyNo);
        //客户信息
        DscSalesApplyCust cust = DscSalesApplyCust.getApplyCostByMianId(dscSalesApplyMain.getId());
        //费用信息
        DscSalesApplyCost cost = DscSalesApplyCost.getByMainId(dscSalesApplyMain.getId());

        DscSalesApplyCar dscSalesApplyCar = DscSalesApplyCar.getBymainId(dscSalesApplyMain.getId());

        //合同申请融资额明细表  融资明细信息
        List<DscSalesApplyFinancing> salesApplyFinancings = DscSalesApplyFinancing.getByMainId(dscSalesApplyMain.getId());

        @SuppressWarnings("serial")
        ArrayList<SettleFinanceProjectDTO> settleFinanceProjectDTOS = new ArrayList<SettleFinanceProjectDTO>() {{
            salesApplyFinancings.forEach(salesApplyFinancing -> {
                SettleFinanceProjectDTO settleFinanceProjectDTO = new SettleFinanceProjectDTO();
                settleFinanceProjectDTO.setFinanceProjectCode(salesApplyFinancing.getArzxmid()); // 融资项目编号
                settleFinanceProjectDTO.setIsDirect(""); // 是否直放
                settleFinanceProjectDTO.setFinanceProjectLoanAmount(BigDecimal.ZERO); // 融资项目放款金额
                settleFinanceProjectDTO.setIsMustFinance(""); // 是否必融

                if (CommonConstant.FinanceType.F010.equals(salesApplyFinancing.getArzxmid())) {
                    // 融资项目金额(车款融资项需要减去首付金额)
                    settleFinanceProjectDTO.setFinanceProjectAmount(salesApplyFinancing.getFkhrzje().subtract(cost.getFsfje()));
                } else {
                    // 融资项目金额
                    settleFinanceProjectDTO.setFinanceProjectAmount(salesApplyFinancing.getFkhrzje());
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
        settleOrderDTO.setLncfnos(Collections.singletonList(mainInfo.getCmbcLoanNo()));

        // 融资期限
        settleOrderDTO.setFinanceMaturity(Integer.valueOf(dscSalesApplyMain.getArzqx()));
        // Alix放款金额  ---经金帅确认取经销商收款金额
        settleOrderDTO.setAlixLoanAmount(dscSalesApplyMain.getDealerCollectAmount());
        // Alix放款日期
        settleOrderDTO.setAlixLoanDate(DateUitls.dateToStr(dscSalesApplyMain.getAlixLoanDate(), "yyyyMMdd"));


        // 还款卡号
        settleOrderDTO.setRepayCardNumber(dscSalesApplyMain.getAhkrjjkzh());
        // 还款方式
        settleOrderDTO.setRepayMethod(dscSalesApplyMain.getAhkfs());
        //公司主体
        settleOrderDTO.setCompanyBodyCode(dscSalesApplyMain.getRentingCompanyCode());
        //公司主体名称
        settleOrderDTO.setCompanyBodyName(dscSalesApplyMain.getAdyqr());


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

        Date bankLoanDate = mainInfo.getBankLoanTime();
        String bankLoanDateStr = DateUtil.dateToString(bankLoanDate, "yyyy-MM-dd");
        // 银行放款日期
        settleOrderDTO.setBankLoanDate(bankLoanDateStr);
        // 合同生效日期
        settleOrderDTO.setContractEffectiveDate(bankLoanDateStr);

        //对客利率
        settleOrderDTO.setToCustInterestRate(dscSalesApplyMain.getAdklv());
        //订单状态
        settleOrderDTO.setStatus(DscActionEnum.REQUEST_FUNDS_SUCCESS.getCode());


        AssetSettleInfo settleInfo = AssetSettleInfo.getAssetSettleInfoByBankCode(CommonConstant.BankName.YILLION_BANK);
        /**
         * 固定字段
         */
        // 提前还款限制期数--固定值
        settleOrderDTO.setEarlyRepayLimitPeriod(settleInfo.getEarlyRepayLimitPeriod());
        // 提前还款最小间隔天数--固定值
        settleOrderDTO.setEarlyRepayMinDay(settleInfo.getEarlyRepayMinDay());
        // 逾期宽限期 ---固定值
        settleOrderDTO.setGracePeriod(settleInfo.getGracePeriod());
        // 提前还款违约金收取比例--固定值
        settleOrderDTO.setEarlyRepayPenaltyProp(settleInfo.getEarlyRepayPenaltyProp());
        // 罚息比率类型--固定值
        settleOrderDTO.setPenaltyRateType(PenaltyRateTypeEnum.PENALTY_RATE_DAY.getCode());
        // 罚息率--固定值
        settleOrderDTO.setPenaltyRate(settleInfo.getPenaltyRate());
        // 银行资金成本费率 fsxfl --固定值
        settleOrderDTO.setBankCostRate(settleInfo.getBankCostRate());
        // 客户费率  -- 固定值
        settleOrderDTO.setCustRate(settleInfo.getCustRate());

        settleOrderDTO.setBankCostInterestRate("1".equals(dscSalesApplyCar.getAcllx()) ? settleInfo.getBankCostInterestRateNew() : settleInfo.getBankCostInterestRateOld());

        return settleOrderDTO;
    }
}
