package com.yixin.dsc.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yixin.common.utils.serializer.JsonDateDeserializer;
import com.yixin.common.utils.serializer.JsonDateTimeSerializer;

/**
 * 分流决策动作 参数DTO
 * Package : com.yixin.nssp.dto.dsc
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月4日 上午10:07:41
 *
 */
public class DscActionParamDTO implements Serializable{

	private static final long serialVersionUID = 2214961489498289170L;
	
	
	/**
	 * 申请编号/订单编号
	 */
	private String applyNo;

	/***
	 * 经销商收款额
	 */
	private BigDecimal dealerCollectAmount;

	/**
	 * alix放款日期
	 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date alixLoanDate;

	public String getApplyNo() {
		return applyNo;
	}


	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public BigDecimal getDealerCollectAmount() {
		return dealerCollectAmount;
	}

	public void setDealerCollectAmount(BigDecimal dealerCollectAmount) {
		this.dealerCollectAmount = dealerCollectAmount;
	}

	public Date getAlixLoanDate() {
		return alixLoanDate;
	}

	public void setAlixLoanDate(Date alixLoanDate) {
		this.alixLoanDate = alixLoanDate;
	}
}
