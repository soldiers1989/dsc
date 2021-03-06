package com.yixin.dsc.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yixin.common.utils.serializer.JsonDateDeserializer;
import com.yixin.common.utils.serializer.JsonDateTimeSerializer;

/**
 * 同步放款信息
 * Package : com.yixin.dsc.dto
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年9月26日 下午3:38:23
 *
 */
public class DscRepayScheduleDTO implements Serializable {

	private static final long serialVersionUID = -1906220726180501800L;

	/**
	 * 订单编号
	 */
	private String applyNo;
	
    /**
     * 实际放款金额
     */
    private BigDecimal actualLoanAmount;

    /**
     * 放款时间
     */
    @JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
    private Date loanTime;

    /**
     * 放款流水集合
     */
    private List<DscLoanSerialDTO> loanSerialList;

    /**
     * 还款计划明细
     */
    private List<DscLoanRepayDTO> repaySchedules;

    public BigDecimal getActualLoanAmount() {
		return actualLoanAmount;
	}

	public void setActualLoanAmount(BigDecimal actualLoanAmount) {
		this.actualLoanAmount = actualLoanAmount;
	}

	public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public List<DscLoanSerialDTO> getLoanSerialList() {
        return loanSerialList;
    }

    public void setLoanSerialList(List<DscLoanSerialDTO> loanSerialList) {
        this.loanSerialList = loanSerialList;
    }

    public Date getLoanTime() {
		return loanTime;
	}

	public void setLoanTime(Date loanTime) {
		this.loanTime = loanTime;
	}

	public List<DscLoanRepayDTO> getRepaySchedules() {
        return repaySchedules;
    }

    public void setRepaySchedules(List<DscLoanRepayDTO> repaySchedules) {
        this.repaySchedules = repaySchedules;
    }
}