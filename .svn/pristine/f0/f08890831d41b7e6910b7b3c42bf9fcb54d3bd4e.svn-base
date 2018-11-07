package com.yixin.dsc.dto;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

/**
 * 试算DTO
 * Package : com.yixin.dsc.dto
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月4日 下午7:42:11
 *
 */
public class DscComputerResultDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 5249451569455534322L;

	/**
	 * 申请编号/订单编号
	 */
	private String applyNo;
	
	/**
	 * 	FRZE	客户融资额
	 */
	private BigDecimal frze;
	
	/**
	 * 试算明细
	 */
	private List<DscFeeScheduleDto> schedules;

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public BigDecimal getFrze() {
		return frze;
	}

	public void setFrze(BigDecimal frze) {
		this.frze = frze;
	}

	public List<DscFeeScheduleDto> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<DscFeeScheduleDto> schedules) {
		this.schedules = schedules;
	}
}
