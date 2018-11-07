package com.yixin.dsc.entity.kepler;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.annotations.GenericGenerator;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.Lists;
import com.yixin.common.system.domain.BaseEntity;
import com.yixin.dsc.dto.rule.engine.BatchMatchResult;
import com.yixin.dsc.dto.rule.engine.DscMatchResult;

/**
 * 规则匹配记录表
 * Package : com.yixin.dsc.entity.kepler
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年8月27日 下午5:16:12
 *
 */
@Entity
@Table(name = "dsc_rule_match_record")
public class DscRuleMatchRecord extends BaseEntity {

	private static final long serialVersionUID = 7614386879265574714L;
	
	private static final Logger LOGGER = LoggerFactory.getLogger(DscRuleMatchRecord.class);

	@Id
    @Column(name = "ID", length = 50)
    @GeneratedValue(generator = "system-uuid")
    @GenericGenerator(name = "system-uuid", strategy = "uuid")
    private String id;
	
	/**
	 * 订单编号
	 */
	@Column(name = "apply_no", nullable = false,columnDefinition = "varchar(64) comment '订单编号'")
	private String applyNo;
	
	/**
	 * 规则ID
	 */
	@Column(name = "rule_id", nullable = false,columnDefinition = "varchar(64) comment '规则ID'")
	private String ruleId;
	
	/**
	 * 规则明细ID
	 */
	@Column(name = "rule_detail_id", nullable = false,columnDefinition = "varchar(64) comment '规则明细ID'")
	private String ruleDetailId;
	
	/**
	 * 规则message
	 */
	@Column(name = "rule_message", nullable = false,columnDefinition = "varchar(1024) comment '规则message'")
	private String ruleMessage;
	
	/**
	 * 是否匹配
	 */
	@Column(name = "match_flag", nullable = false,columnDefinition = "bit(1) comment '是否匹配'")
	private Boolean matchFlag;
	
	/**
	 * 规则公式定义
	 */
	@Column(name = "rule_formula",columnDefinition = "varchar(2048) comment '规则公式定义'")
	private String ruleFormula;
	
	/**
	 * 匹配时间
	 */
	@Column(name = "match_time", nullable = false,columnDefinition = "datetime comment '匹配时间'")
	private Date matchTime;
	
	/**
	 * 匹配字段值
	 */
	@Column(name = "field_values",columnDefinition = "varchar(1024) comment '匹配字段值'")
	private String fieldValues;
	
	/**
	 * 备注
	 */
	@Column(name = "remark",columnDefinition = "varchar(1024) comment '备注'")
	private String remark;
	
	/**
	 * 匹配标记
	 */
	@Column(name = "match_track_Id",columnDefinition = "varchar(64) comment '匹配标记'")
	private String matchTrackId;
	
	/**
	 * 资方ID
	 */
	@Column(name = "capital_id",columnDefinition = "varchar(64) comment '资方ID'")
	private String capitalId;
	
	/**
	 * 资方编号
	 */
	@Column(name = "capital_code",columnDefinition = "varchar(64) comment '资方编号'")
	private String capitalCode;
	
	
	/**
	 * 记录资方规则匹配结果
	 * @param matchResult 匹配规则的结果包括规则通过和不通过的
	 * @param matchTrackId 匹配痕迹ID即批次
	 * @param applyNo 订单编号
	 * @author YixinCapital -- wangwenlong
	 *	       2018年8月28日 上午9:49:09
	 */
	public static void recordMatchRule(BatchMatchResult matchResult,String matchTrackId,String applyNo){
		List<DscMatchResult> machRuleList = matchResult.getMachRuleList();
		List<DscMatchResult> noMachRuleList = matchResult.getNoMachRuleList();
		if(machRuleList == null){
			machRuleList = Lists.newArrayList();
		}
		machRuleList.addAll(noMachRuleList);
		DscRuleMatchRecord record = null;
		for(DscMatchResult result:machRuleList){
			record = new DscRuleMatchRecord();
			record.setApplyNo(applyNo); //订单编号
			record.setMatchTrackId(matchTrackId); //匹配标记
			record.setRuleId(result.getRuleId()); //规则ID
			record.setRuleDetailId(result.getRuleDetailId()); //规则明细ID
			record.setMatchFlag(result.isMach()); //匹配结果
			record.setRuleFormula(result.getRuleFormula()); //规则公式
			record.setMatchTime(result.getMatchTime() == null?result.getMatchTime():new Date()); //匹配时间
			record.setRuleMessage(result.getRuleMessage()); //匹配message
			record.setFieldValues(result.getFieldValues()==null?"":JSON.toJSONString(result.getFieldValues())); //匹配字段值
			record.setCapitalId(matchResult.getCapitalId()); //资方ID
			record.setCapitalCode(matchResult.getCapitalCode()); //资方code
			record.create();
		}
	}
	
	/**
	 * 记录资方规则匹配结果
	 * @param matchResult 匹配规则的结果包括规则通过和不通过的
	 * @param matchTrackId 匹配痕迹ID即批次
	 * @param applyNo 订单编号
	 * @author YixinCapital -- wangwenlong
	 *	       2018年8月28日 上午9:49:09
	 */
	public static void recordMatchRuleList(List<BatchMatchResult> batchMatchResultList,String matchTrackId,String applyNo){
		if(StringUtils.isBlank(matchTrackId) || StringUtils.isBlank(applyNo)){
			LOGGER.info("记录资方规则匹配结果,匹配痕迹ID即批次为空或者订单编号为空，不予记录");
			return;
		}
		if(CollectionUtils.isNotEmpty(batchMatchResultList)){
			for(BatchMatchResult matchResult:batchMatchResultList){
				try {
					recordMatchRule(matchResult, matchTrackId, applyNo);
				} catch (Exception e) {
					LOGGER.error("记录资方规则匹配结果异常,订单编号:{},matchTrackId:{}",applyNo,matchTrackId,e);
				}
			}
		}
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

	/**
     * 创建方法
     * 
     * @return
     */
    public String create() {
        this.save();
        return this.id;
    }

	@Override
	public Serializable getId() {
		return this.id;
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

	public void setId(String id) {
		this.id = id;
	}
	
}
