package com.yixin.dsc.entity.capital;

import com.yixin.common.system.domain.BZBaseEntiy;
import org.apache.commons.lang3.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 资方   规则关系表
 * 
 * Package : com.yixin.dsc.entity.capital
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月4日 下午6:33:56
 *
 */
@Entity
@Table(name = "dsc_capital_rule_relation")
public class DscCapitalRuleRelation extends BZBaseEntiy{

	private static final long serialVersionUID = 6591171938373714332L;

	/**
	 * 规则ID
	 */
	@Column(name = "rule_id", columnDefinition = "varchar(64) comment '规则ID'")
	private String ruleId;
	
	/**
	 * 资方ID
	 */
	@Column(name = "capital_id", columnDefinition = "varchar(64) comment '资方ID'")
	private String capitalId;

	/**
	 * 获取规则与资方的关系集合
	 * @param ruleId 规则ID
	 * @param capitalId 资方ID
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年6月8日 下午4:27:53
	 */
	public static List<DscCapitalRuleRelation> getListByParms(String ruleId,String capitalId){
		Map<String,Object> param = new HashMap<>();
		if(StringUtils.isNotBlank(capitalId)){  //资方ID
			param.put("capitalId",capitalId);
		}
		if(StringUtils.isNotBlank(ruleId)){  //规则ID
			param.put("ruleId",ruleId);
		}
		param.put("deleted", false);
		return DscCapitalRuleRelation.findByProperties(DscCapitalRuleRelation.class, param);
	}
	
	
	public String getRuleId() {
		return ruleId;
	}

	public void setRuleId(String ruleId) {
		this.ruleId = ruleId;
	}

	public String getCapitalId() {
		return capitalId;
	}

	public void setCapitalId(String capitalId) {
		this.capitalId = capitalId;
	}

}
