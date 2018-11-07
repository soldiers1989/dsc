package com.yixin.dsc.dto.query;

import java.io.Serializable;
import java.util.List;

import com.google.common.collect.Lists;

/**
 * 银行交互信息结果DTO
 * Package : com.yixin.dsc.dto.query
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月31日 下午1:30:51
 *
 */
public class DscBankInteractiveResultDto implements Serializable{

	private static final long serialVersionUID = -6771787961264815407L;

	/**
	 * 申请编号		
	 */
	private String applyNo;

	/**
	 * 资方ID
	 */
	private String capitalId;

	/**
	 * 资方Code
	 */
	private String capitalCode;
	
	/**
	 * 订单所属阶段
	 */
	private String orderPhase;
	
	/**
	 * 订单所属阶段描述
	 */
	private String orderPhaseDesc;
	
	private List<DscInteractiveDto> detail = Lists.newArrayList();

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getCapitalId() {
		return capitalId;
	}

	public void setCapitalId(String capitalId) {
		this.capitalId = capitalId;
	}

	public String getCapitalCode() {
		return capitalCode;
	}

	public void setCapitalCode(String capitalCode) {
		this.capitalCode = capitalCode;
	}

	public String getOrderPhase() {
		return orderPhase;
	}

	public void setOrderPhase(String orderPhase) {
		this.orderPhase = orderPhase;
	}

	public String getOrderPhaseDesc() {
		return orderPhaseDesc;
	}

	public void setOrderPhaseDesc(String orderPhaseDesc) {
		this.orderPhaseDesc = orderPhaseDesc;
	}

	public List<DscInteractiveDto> getDetail() {
		return detail;
	}

	public void setDetail(List<DscInteractiveDto> detail) {
		this.detail = detail;
	}
	
}
