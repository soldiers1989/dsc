package com.yixin.dsc.entity.shunt;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

import com.yixin.common.system.domain.BZBaseEntiy;

/**
 * 订单补充信息表
 * 
 * Package : com.yixin.dsc.entity.rule
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月4日 下午7:31:35
 *
 */
@Entity
@Table(name = "dsc_order_supplement_info")
public class DscOrderSupplementInfo extends BZBaseEntiy {

	private static final long serialVersionUID = 6388630227218591394L;

	/**
	 * 订单编号
	 */
	@Column(name = "apply_no", columnDefinition = "varchar(64) comment '订单编号'")
	private String applyNo;
    
	/**
	 * 阶段
	 */
	@Column(name = "link", columnDefinition = "varchar(64) comment '阶段'")
	private String link;
	
	/**
	 * 补充规则 动作信息 id
	 */
	@Column(name = "act_id", columnDefinition = "varchar(64) comment '补充规则 动作信息 id'")
	private String ActId;
    
    
    /**
     * 补充动作类型
     */
	@Column(name = "supply_act_type", columnDefinition = "varchar(64) comment '补充动作类型'")
	private String supplyActType;	
	
    
	/**
	 * 校验规则公式
	 */
	@Column(name = "validate_formula", columnDefinition = "varchar(64) comment '校验规则公式'")
	private String validateFormula;	
	
	/**
	 * 校验类型
	 */
	@Column(name = "validate_type", columnDefinition = "varchar(500) comment '校验类型'")
	private String validateType;
	
	
    
    /**
	 * 通过订单编号获取 订单补充信息 集合
	 * @param applyNo 订单编号
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年6月8日 下午1:56:43
	 */
	public static List<DscOrderSupplementInfo> getListByParms(String applyNo,Boolean deleted){
		List<DscOrderSupplementInfo> supplementList = Lists.newArrayList();
		if(StringUtils.isBlank(applyNo)){
			return supplementList;
		}
		Map<String,Object> capitalParm = new HashMap<>();
		capitalParm.put("applyNo",applyNo); //订单编号
		if(deleted !=null ){
			capitalParm.put("deleted",deleted); //是否删除状态，true为已删除，false为未删除
		}
		return DscOrderSupplementInfo.findByProperties(DscOrderSupplementInfo.class, capitalParm);
	}
    

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getLink() {
		return link;
	}

	public void setLink(String link) {
		this.link = link;
	}

	public String getActId() {
		return ActId;
	}

	public void setActId(String actId) {
		ActId = actId;
	}

	public String getSupplyActType() {
		return supplyActType;
	}

	public void setSupplyActType(String supplyActType) {
		this.supplyActType = supplyActType;
	}

	public String getValidateFormula() {
		return validateFormula;
	}

	public void setValidateFormula(String validateFormula) {
		this.validateFormula = validateFormula;
	}

	public String getValidateType() {
		return validateType;
	}

	public void setValidateType(String validateType) {
		this.validateType = validateType;
	}

}
