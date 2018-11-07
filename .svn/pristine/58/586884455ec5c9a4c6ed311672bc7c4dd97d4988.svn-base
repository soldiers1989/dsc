package com.yixin.dsc.dto.own;

import java.io.Serializable;
import java.util.List;

import org.assertj.core.util.Lists;

/**
 * 规则验证返回DTO
 * Package : com.yixin.dsc.dto.own
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年8月2日 下午3:25:07
 *
 */
public class DscRuleVerifyResultDto implements Serializable{

	private static final long serialVersionUID = 337268696006557400L;

	/** 申请编号 */
	private String applyNo;
	

	private List<DscRuleVerifyCapitalDto> capitalList = Lists.newArrayList();

	
	public DscRuleVerifyResultDto() {
		super();
	}

	public DscRuleVerifyResultDto(String applyNo) {
		super();
		this.applyNo = applyNo;
	}


	public String getApplyNo() {
		return applyNo;
	}


	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}


	public List<DscRuleVerifyCapitalDto> getCapitalList() {
		return capitalList;
	}


	public void setCapitalList(List<DscRuleVerifyCapitalDto> capitalList) {
		this.capitalList = capitalList;
	}
}
