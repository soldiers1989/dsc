package com.yixin.dsc.dto;

import java.io.Serializable;

/**
 * 用户操作环境dto
 * Package : com.yixin.dsc.dto
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月4日 下午6:04:20
 *
 */
public class DscOperateEnvDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5108281119988535019L;

	/**
	 * 操作系统
	 */
	private String osType;
	
	/**
	 * 手机品牌
	 */
	private String mobileBrands;
	
	/**
	 * ios设备必须填写idfa
	 */
	private String iosIdfa;
	
	/**
	 * andriod设备必须填写
	 */
	private String androidIMEI;
	
	/**
	 * IP地址, 如易贷通没有，则传后台服务器地址
	 */
	private String ip;
	
	/**
	 * mac地址
	 */
	private String macAddr;

	public String getOsType() {
		return osType;
	}

	public void setOsType(String osType) {
		this.osType = osType;
	}

	public String getMobileBrands() {
		return mobileBrands;
	}

	public void setMobileBrands(String mobileBrands) {
		this.mobileBrands = mobileBrands;
	}

	public String getIosIdfa() {
		return iosIdfa;
	}

	public void setIosIdfa(String iosIdfa) {
		this.iosIdfa = iosIdfa;
	}

	public String getAndroidIMEI() {
		return androidIMEI;
	}

	public void setAndroidIMEI(String androidIMEI) {
		this.androidIMEI = androidIMEI;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getMacAddr() {
		return macAddr;
	}

	public void setMacAddr(String macAddr) {
		this.macAddr = macAddr;
	}
	
}
