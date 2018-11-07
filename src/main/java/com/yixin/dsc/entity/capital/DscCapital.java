package com.yixin.dsc.entity.capital;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.yixin.common.system.domain.BZBaseEntiy;

/**
 * 资方信息表
 * 
 * Package : com.yixin.dsc.entity.capital
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月4日 下午6:33:56
 *
 */
@Entity
@Table(name = "dsc_capital")
public class DscCapital extends BZBaseEntiy{

	private static final long serialVersionUID = 6591171938373714332L;

	/**
	 * 资方名称
	 */
	@Column(name = "capital_name", columnDefinition = "varchar(64) comment '资方名称'")
	private String capitalName;
	
	/**
	 * 资方编码
	 */
	@Column(name = "capital_code", columnDefinition = "varchar(64) comment '资方编码'")
	private String capitalCode;

	
	public String getCapitalCode() {
		return capitalCode;
	}

	public void setCapitalCode(String capitalCode) {
		this.capitalCode = capitalCode;
	}

	public String getCapitalName() {
		return capitalName;
	}

	public void setCapitalName(String capitalName) {
		this.capitalName = capitalName;
	}
	
	
	/**
	 * 获取资方名称
	 * 
	 * @param capitalCode
	 * @return 
	 * @author YixinCapital -- wangxulong
	 *	       2018年10月25日 下午6:17:07
	 */
	public static String getCapitalNameByCapitalCode (String capitalCode) {
		if(StringUtils.isBlank(capitalCode)) {
			return null;
		}
		DscCapital dscCapital = DscCapital.findFirstByProperty(DscCapital.class, "capitalCode", capitalCode);
		if(dscCapital!=null) {
			return dscCapital.getCapitalName(); 
		}
		return null;
	}
}
