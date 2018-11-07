package com.yixin.dsc.dto.rule;

import java.util.Date;
import java.util.List;

import javax.persistence.Version;

import com.yixin.dsc.dto.DscBaseDto;
import com.yixin.dsc.dto.field.DscFieldFDto;

/**
 * 规则dto
 * Package : com.yixin.dsc.dto.rule
 * 
 * @author YixinCapital -- huguoxing
 *		   2018年6月6日 下午7:07:27
 *
 */
public class DscRuleDto extends DscBaseDto{

	/**
	 * 
	 * @author YixinCapital -- huguoxing
	 *		   2018年6月6日 下午7:57:55
	 *
	 */
	private static final long serialVersionUID = 1L;

	@Version
    private Integer version = 0;
	
    /***
     * 创建人ID
     */
    private String creatorId = "admin";

    /***
     * 创建时间
     */
    private Date createTime;

    /***
     * 修改人域账号
     */
    private String updatorId;

    /***
     * 修改时间
     */
    private Date updateTime;
    
	/**
	 * 规则名称
	 */
	private String ruleName;
	
	/**
	 * 规则匹配类型
	 * reg正则;fiel   RuleEvalEnum
	 */
	private String ruleMatchingType;
	
	/**
	 * 规则类型
	 * 准入规则、补充资料规则
	 */
	private String ruleType;
	
	//规则明细
	private DscRuleDetailDto dscRuleDetailDto;
	
	// 规则明细
	private List<DscRuleDetailDto> dscRuleDetailDtoList;
	//字段集合
	private List<DscFieldFDto> dscFieldFDtoList;

	public Integer getVersion() {
		return version;
	}

	public void setVersion(Integer version) {
		this.version = version;
	}

	public String getCreatorId() {
		return creatorId;
	}

	public void setCreatorId(String creatorId) {
		this.creatorId = creatorId;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public String getUpdatorId() {
		return updatorId;
	}

	public void setUpdatorId(String updatorId) {
		this.updatorId = updatorId;
	}

	public Date getUpdateTime() {
		return updateTime;
	}

	public void setUpdateTime(Date updateTime) {
		this.updateTime = updateTime;
	}

	public String getRuleName() {
		return ruleName;
	}

	public void setRuleName(String ruleName) {
		this.ruleName = ruleName;
	}

	public String getRuleMatchingType() {
		return ruleMatchingType;
	}

	public void setRuleMatchingType(String ruleMatchingType) {
		this.ruleMatchingType = ruleMatchingType;
	}

	public String getRuleType() {
		return ruleType;
	}

	public void setRuleType(String ruleType) {
		this.ruleType = ruleType;
	}

	public List<DscRuleDetailDto> getDscRuleDetailDtoList() {
		return dscRuleDetailDtoList;
	}

	public void setDscRuleDetailDtoList(List<DscRuleDetailDto> dscRuleDetailDtoList) {
		this.dscRuleDetailDtoList = dscRuleDetailDtoList;
	}

	public List<DscFieldFDto> getDscFieldFDtoList() {
		return dscFieldFDtoList;
	}

	public void setDscFieldFDtoList(List<DscFieldFDto> dscFieldFDtoList) {
		this.dscFieldFDtoList = dscFieldFDtoList;
	}

	public DscRuleDetailDto getDscRuleDetailDto() {
		return dscRuleDetailDto;
	}

	public void setDscRuleDetailDto(DscRuleDetailDto dscRuleDetailDto) {
		this.dscRuleDetailDto = dscRuleDetailDto;
	}
	
}
