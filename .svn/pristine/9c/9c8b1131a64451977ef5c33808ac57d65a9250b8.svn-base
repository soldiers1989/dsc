package com.yixin.kepler.v1.datapackage.dto;

import com.yixin.common.utils.BaseDTO;

/**
 * Package : com.yixin.kepler.v1.datapackage.dto
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年9月19日 下午12:06:11
 *
 */
public class QueryDTO extends BaseDTO{
	/**
	 * 
	 * @author YixinCapital -- wangcy
	 *		   2016年12月13日 下午11:01:40
	 *
	 */
	private static final long serialVersionUID = 8508395688836105502L;
	/**
	 * 
	 * 业务编码：流水号服务为各系统定义的一个唯一的业务编码
	 * @author YixinCapital -- wangcy
	 *		   2016年12月13日 下午8:14:19
	 *
	 */
	public String bizCode;
	/**
	 * 
	 * 系统编码：各系统传一个自己的、有语义的系统编码，如结算中心：Settle，此参数不参数程序判断，只是方便排查日志用
	 * @author YixinCapital -- wangcy
	 *		   2016年12月13日 下午8:14:19
	 *
	 */
	public String sysCode;
	
	

	/**
	 *  
	 * @author YixinCapital -- wangcy
	 *	       2016年12月13日 下午11:11:36
	 */
	public QueryDTO() {
		super();
	}

	/**
	 *  
	 * @author YixinCapital -- wangcy
	 *	       2016年12月13日 下午8:14:19
	 */
	public QueryDTO(String bizCode, String sysCode) {
		this.bizCode = bizCode;
		this.sysCode = sysCode;
	}

	/**
	 * @return the bizCode
	 */
	public String getBizCode() {
		return bizCode;
	}

	/**
	 * @param bizCode the bizCode to set
	 */
	public void setBizCode(String bizCode) {
		this.bizCode = bizCode;
	}

	/**
	 * @return the sysCode
	 */
	public String getSysCode() {
		return sysCode;
	}

	/**
	 * @param sysCode the sysCode to set
	 */
	public void setSysCode(String sysCode) {
		this.sysCode = sysCode;
	}
}