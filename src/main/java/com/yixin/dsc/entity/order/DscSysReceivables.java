package com.yixin.dsc.entity.order;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yixin.common.system.domain.BZBaseEntiy;

/**
 * 店面收款方信息表
 * Package : com.yixin.dsc.entity
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月4日 下午4:00:41
 *
 */
@Entity
@Table(name = "DSC_Sys_receivables")
public class DscSysReceivables extends BZBaseEntiy {

	private static final long serialVersionUID = -8326284236481772329L;

	@Column(name = "zhlx", columnDefinition = "varchar(64) comment '账户类型'")
	private String zhlx;
	
	@Column(name = "skf_name", columnDefinition = "varchar(512) comment '收款方名称'")
	private String skfName;
		
	@Column(name = "skfzhangh", columnDefinition = "varchar(64) comment '收款方账号'")
	private String skfzhangh;
	
	@Column(name = "skfyh", columnDefinition = "varchar(512) comment '收款方银行(支行)'")
	private String skfyh;
		
	@Column(name = "skfdzlhh", columnDefinition = "varchar(64) comment '收款方电子联行号'")
	private String skfdzlhh;
		
	@Column(name = "khhsf", columnDefinition = "varchar(64) comment '开户行省份'")
	private String khhsf;
		
	@Column(name = "khhsf_name", columnDefinition = "varchar(64) comment '开户行省份'")
	private String khhsfName;
		
	@Column(name = "khhcs", columnDefinition = "varchar(64) comment '开户行城市'")
	private String khhcs;
		
	@Column(name = "khhcs_name", columnDefinition = "varchar(64) comment '开户行城市'")
	private String khhcsName;

	/**
	 *  订单主表ID
	 */
	@Column(name = "main_id", columnDefinition = "varchar(50) comment '订单主表ID'")
	private String mainId;



	
	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	public String getZhlx() {
		return zhlx;
	}

	public void setZhlx(String zhlx) {
		this.zhlx = zhlx;
	}

	public String getSkfName() {
		return skfName;
	}

	public void setSkfName(String skfName) {
		this.skfName = skfName;
	}

	public String getSkfzhangh() {
		return skfzhangh;
	}

	public void setSkfzhangh(String skfzhangh) {
		this.skfzhangh = skfzhangh;
	}

	public String getSkfyh() {
		return skfyh;
	}

	public void setSkfyh(String skfyh) {
		this.skfyh = skfyh;
	}

	public String getSkfdzlhh() {
		return skfdzlhh;
	}

	public void setSkfdzlhh(String skfdzlhh) {
		this.skfdzlhh = skfdzlhh;
	}

	public String getKhhsf() {
		return khhsf;
	}

	public void setKhhsf(String khhsf) {
		this.khhsf = khhsf;
	}

	public String getKhhsfName() {
		return khhsfName;
	}

	public void setKhhsfName(String khhsfName) {
		this.khhsfName = khhsfName;
	}

	public String getKhhcs() {
		return khhcs;
	}

	public void setKhhcs(String khhcs) {
		this.khhcs = khhcs;
	}

	public String getKhhcsName() {
		return khhcsName;
	}

	public void setKhhcsName(String khhcsName) {
		this.khhcsName = khhcsName;
	}

	/**
	 * 通过订单主表ID删除
	 * @param mainId
	 */
	public static void deleteBymainId(String mainId){
		List<DscSysReceivables> lis = DscSysReceivables.findByProperty(DscSysReceivables.class, "mainId", mainId);
		if(lis!=null && !lis.isEmpty()) {
			for (DscSysReceivables att : lis) {
				att.remove();
			}
		}
	}
}
