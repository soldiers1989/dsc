package com.yixin.dsc.dto.rule;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yixin.common.utils.serializer.JsonDateDeserializer;
import com.yixin.common.utils.serializer.JsonDateTimeSerializer;

import java.util.Date;

/**
 * 规则匹配结果
 * Package : com.yixin.dsc.dto.rule.engine
 *
 * @author YixinCapital -- huguoxing
 *		   2018年6月6日 下午7:23:20
 *
 */
public class DscMatchResultDto {

	//规则id
	private String ruleId;

	//是否匹配
	private boolean isMach ;

	//规则返回
	private String returnStr;

	//规则返回数据类别(材料、字段等 SupplyTypeEnum)
	private String supplyType;

	/**
	 * 未匹配提示
	 */
	private String message;

	/**
	 * 申请编号
	 */
	private String applyNo;


	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	@JsonFormat(pattern = "yyyyMMddHHmmss",timezone="GMT+8")
	private Date createTime;
	
	/**
	 * 规则公式定义
	 */
	private String ruleFormula;
	
	/**
	 * 匹配的资方编码
	 */
	private String matchCapitalCode;
	
	public String getMatchCapitalCode() {
		return matchCapitalCode;
	}

	public void setMatchCapitalCode(String matchCapitalCode) {
		this.matchCapitalCode = matchCapitalCode;
	}

	public String getRuleFormula() {
		return ruleFormula;
	}

	public void setRuleFormula(String ruleFormula) {
		this.ruleFormula = ruleFormula;
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public boolean isMach() {
		return isMach;
	}

	public void setMach(boolean isMach) {
		this.isMach = isMach;
	}

	public String getReturnStr() {
		return returnStr;
	}

	public void setReturnStr(String returnStr) {
		this.returnStr = returnStr;
	}

	public String getSupplyType() {
		return supplyType;
	}

	public void setSupplyType(String supplyType) {
		this.supplyType = supplyType;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}
}
