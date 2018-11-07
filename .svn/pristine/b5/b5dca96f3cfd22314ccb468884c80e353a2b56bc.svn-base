package com.yixin.dsc.service.common;

import java.math.BigDecimal;
import java.util.Date;

import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.dto.DscContractCancelDto;
import com.yixin.dsc.dto.query.DscFundsQueryDto;
import com.yixin.dsc.dto.query.DscSettleCalculationResultDto;
import com.yixin.dsc.dto.query.DscSettleLoanInfoDto;

/**
 * 调用结算通用service
 *
 * @author YixinCapital -- chenjiacheng
 *         2018年09月10日 10:11
 **/
public interface DscSettleCommonService {


	/**
	 * 退车试算接口
	 *
	 * @param applyNo 申请编号
	 * @param apptdt 试算申请日期
	 * @param bankSymbol 银行标志
	 * @return 出参
	 * @author YixinCapital -- chenjiacheng
	 * 2018/9/10 10:20
	 */
	DscSettleCalculationResultDto settleComputer(String applyNo, Date apptdt, String bankSymbol);

	/**
	 * 获取贷款信息
	 * @param applyNo 申请编号
	 * @return
	 * @author YixinCapital -- wangwenlong
	 *	       2018年9月10日 下午7:51:04
	 */
	DscSettleLoanInfoDto getLoanInfo(String applyNo);

	/**
	 * 获取取消标识
	 * @param applyNo 申请编号
	 * @param financeCode 资方编码
	 * @return
	 * @author YixinCapital -- wangwenlong
	 *	       2018年9月10日 下午8:09:04
	 */
	DscContractCancelDto getCancelInfo(String applyNo,String financeCode);


	/**
	 * 查询银行扣补息款、退回补息款、分润信息
	 *
	 * @param queryParam
	 * @return
	 */
	InvokeResult queryBankFunds(DscFundsQueryDto queryParam);
	
	/**
	 * 向结算推送放款信息
	 * @param applyNo 订单编号
	 * @param bankSeq 银行流水
	 * @param loanDime 放款时间
	 * @param loanAmount 放款金额
 	 * @param loanSuccess 是否放款成功
	 * @return 是否推送成功
	 * @author YixinCapital -- wangwenlong
	 *	       2018年10月15日 下午1:34:41
	 */
	Boolean pushFk(String applyNo,String bankSeq,Date loanDime,BigDecimal loanAmount,Boolean loanSuccess);
}
