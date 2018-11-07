package com.yixin.kepler.v1.datapackage.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang.StringUtils;

import com.yixin.common.system.domain.BZBaseEntiy;

/**
 * @author YixinCapital -- chenjiacheng
 *         2018年09月25日 15:34
 **/
@Entity
@Table(name = "sys_standard_city")
public class SysStandardCity extends BZBaseEntiy {

	private static final long serialVersionUID = -3383348780704942700L;

	/**
	 * 编号
	 */
	@Column(name = "number",columnDefinition = "varchar(20) comment '编号'")
	private String number;

	/**
	 * 省市名称
	 */
	@Column(name = "name", columnDefinition = "varchar(255) comment '名称'")
	private String name;
	
	/**
	 * 判断是否身份证号 如果身份证前4位可以在标准接口的城市编码中匹配到，就可以用身份证前4位
	 * @param azjhm 证件号码/身份证号
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年10月18日 下午8:27:20
	 */
	public static String checkNumber(String azjhm){
		if(StringUtils.isBlank(azjhm)){
			return null;
		}
		try {
			azjhm = azjhm.substring(0, 4); //取前四位
			List<SysStandardCity> standardCityList = SysStandardCity.findByProperty(SysStandardCity.class, "number", azjhm);
			if(CollectionUtils.isNotEmpty(standardCityList)){
				return azjhm;
			} else {
				return null;
			}
		} catch (Exception e) {
			logger.error("查询标准城市sys_standard_city异常",e);
			return null;
		}
	}

	public String getNumber() {
		return number;
	}

	public void setNumber(String number) {
		this.number = number;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}
}
