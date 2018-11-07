package com.yixin.dsc.dto.match;

import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yixin.common.utils.BaseDTO;
import com.yixin.common.utils.serializer.JsonDateDeserializer;
import com.yixin.common.utils.serializer.JsonDateTimeSerializer;

/**
 * 规则匹配记录表
 * Package : com.yixin.dsc.entity.kepler
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年8月27日 下午5:16:12
 *
 */
public class DscRuleMatchRecordDto extends BaseDTO {

	private static final long serialVersionUID = 7792002853713240079L;

	/**
	 * 订单编号
	 */
	private String applyNo;
	
	/**
	 * 规则ID
	 */
	private String ruleId;
	
	/**
	 * 规则明细ID
	 */
	private String ruleDetailId;
	
	/**
	 * 规则message
	 */
	private String ruleMessage;
	
	/**
	 * 是否匹配
	 */
	private Boolean matchFlag;
	
	/**
	 * 规则公式定义
	 */
	private String ruleFormula;
	
	/**
	 * 匹配时间
	 */
    @JsonSerialize(using = JsonDateTimeSerializer.class)
    @JsonDeserialize(using = JsonDateDeserializer.class)
	private Date matchTime;
	
	/**
	 * 匹配字段值
	 */
	private String fieldValues;
	
	/**
	 * 备注
	 */
	private String remark;
	
	/**
	 * 匹配标记
	 */
	private String matchTrackId;
	
	/**
	 * 资方ID
	 */
	private String capitalId;
	
	/**
	 * 资方编号
	 */
	private String capitalCode;
	
	
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

	public String getRuleMessage() {
		return ruleMessage;
	}

	public void setRuleMessage(String ruleMessage) {
		this.ruleMessage = ruleMessage;
	}

	public String getFieldValues() {
		return fieldValues;
	}

	public void setFieldValues(String fieldValues) {
		this.fieldValues = fieldValues;
	}

	public Boolean getMatchFlag() {
		return matchFlag;
	}

	public void setMatchFlag(Boolean matchFlag) {
		this.matchFlag = matchFlag;
	}

	public String getRuleFormula() {
		return ruleFormula;
	}

	public void setRuleFormula(String ruleFormula) {
		this.ruleFormula = ruleFormula;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}

	public String getMatchTrackId() {
		return matchTrackId;
	}

	public void setMatchTrackId(String matchTrackId) {
		this.matchTrackId = matchTrackId;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getRuleDetailId() {
		return ruleDetailId;
	}

	public void setRuleDetailId(String ruleDetailId) {
		this.ruleDetailId = ruleDetailId;
	}

	public Date getMatchTime() {
		return matchTime;
	}

	public void setMatchTime(Date matchTime) {
		this.matchTime = matchTime;
	}
}
