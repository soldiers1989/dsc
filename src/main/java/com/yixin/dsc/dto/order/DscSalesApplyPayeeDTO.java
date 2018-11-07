package com.yixin.dsc.dto.order;

import java.io.Serializable;

/**
 * 合同申请收款信息表
 * Package : com.yixin.dsc.entity
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月4日 下午3:59:09
 *
 */
public class DscSalesApplyPayeeDTO implements Serializable {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;


	private String id;

	/**
	 * 订单主表ID
	 */
	private String mainId;

	/**
	 * 申请店面名称
	 */
	private String asqdmmc;

	/**
	 * 收款方类型
	 */
	private String askflx;

	/**
	 * 收款方类型
	 */
	private String askflxname;

	/**
	 * 收款方名称
	 */
	private String askfmc;

	/**
	 * 收款方银行
	 */
	private String askfyh;

	/**
	 * 收款方户名
	 */
	private String askfhm;

	/**
	 * 收款方帐号
	 */
	private String askfzh;

	/**
	 * 收款联行号
	 */
	private String askdzlhh;

	/**
	 * 开户省份
	 */
	private String akhsf;

	/**
	 * 开户城市
	 */
	private String akhcs;

	/**
	 * 开户省份
	 */
	private String khhsfName1;

	/**
	 * 开户城市
	 */
	private String khhcsName1;

	/**
	 * 持卡人手机号 
	 */
	private String ackrsjh;

	/**
	 * 身份证
	 */
	private String asfzh;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	public String getAskflx() {
		return askflx;
	}

	public void setAskflx(String askflx) {
		this.askflx = askflx;
	}

	public String getAsqdmmc() {
		return asqdmmc;
	}

	public void setAsqdmmc(String asqdmmc) {
		this.asqdmmc = asqdmmc;
	}

	public String getAskflxname() {
		return askflxname;
	}

	public void setAskflxname(String askflxname) {
		this.askflxname = askflxname;
	}

	public String getAskfmc() {
		return askfmc;
	}

	public void setAskfmc(String askfmc) {
		this.askfmc = askfmc;
	}

	public String getAskfyh() {
		return askfyh;
	}

	public void setAskfyh(String askfyh) {
		this.askfyh = askfyh;
	}

	public String getAskfhm() {
		return askfhm;
	}

	public void setAskfhm(String askfhm) {
		this.askfhm = askfhm;
	}

	public String getAskfzh() {
		return askfzh;
	}

	public void setAskfzh(String askfzh) {
		this.askfzh = askfzh;
	}

	public String getAskdzlhh() {
		return askdzlhh;
	}

	public void setAskdzlhh(String askdzlhh) {
		this.askdzlhh = askdzlhh;
	}

	public String getAkhsf() {
		return akhsf;
	}

	public void setAkhsf(String akhsf) {
		this.akhsf = akhsf;
	}

	public String getAkhcs() {
		return akhcs;
	}

	public void setAkhcs(String akhcs) {
		this.akhcs = akhcs;
	}

	public String getKhhsfName1() {
		return khhsfName1;
	}

	public void setKhhsfName1(String khhsfName1) {
		this.khhsfName1 = khhsfName1;
	}

	public String getKhhcsName1() {
		return khhcsName1;
	}

	public void setKhhcsName1(String khhcsName1) {
		this.khhcsName1 = khhcsName1;
	}

	public String getAckrsjh() {
		return ackrsjh;
	}

	public void setAckrsjh(String ackrsjh) {
		this.ackrsjh = ackrsjh;
	}

	public String getAsfzh() {
		return asfzh;
	}

	public void setAsfzh(String asfzh) {
		this.asfzh = asfzh;
	}
}
