package com.yixin.dsc.entity.order;

import java.util.ArrayList;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yixin.common.system.domain.BZBaseEntiy;

/**
 * 合同申请收款信息表
 * Package : com.yixin.dsc.entity
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月4日 下午3:59:09
 *
 */
@Entity
@Table(name = "DSC_sales_Apply_Payee")
public class DscSalesApplyPayee extends BZBaseEntiy {

	private static final long serialVersionUID = -5047039794324320584L;

	@Column(name = "asqdmmc", columnDefinition = "varchar(512) comment '申请店面名称'")
	private String asqdmmc;
	
	@Column(name = "askflx", columnDefinition = "varchar(64) comment '收款方类型'")
	private String askflx;
		
	@Column(name = "askflxname", columnDefinition = "varchar(64) comment '收款方类型'")
	private String askflxname;
	
	@Column(name = "askfmc", columnDefinition = "varchar(512) comment '收款方名称'")
	private String askfmc;
		
	@Column(name = "askfyh", columnDefinition = "varchar(512) comment '收款方银行'")
	private String askfyh;
		
	@Column(name = "askfhm", columnDefinition = "varchar(512) comment '收款方户名'")
	private String askfhm;
	
	@Column(name = "askfzh", columnDefinition = "varchar(64) comment '收款方帐号'")
	private String askfzh;
		
	@Column(name = "askdzlhh", columnDefinition = "varchar(64) comment '收款联行号'")
	private String askdzlhh;
		
	@Column(name = "akhsf", columnDefinition = "varchar(64) comment '开户省份'")
	private String akhsf;
		
	@Column(name = "akhcs", columnDefinition = "varchar(64) comment '开户城市'")
	private String akhcs;
	
	@Column(name = "khhsf_name1", columnDefinition = "varchar(64) comment '开户省份'")
	private String khhsfName1;
		
	@Column(name = "khhcs_name1", columnDefinition = "varchar(64) comment '开户城市'")
	private String khhcsName1;
		
	@Column(name = "ackrsjh", columnDefinition = "varchar(64) comment '持卡人手机号'")
	private String ackrsjh;
		
	@Column(name = "asfzh", columnDefinition = "varchar(64) comment '身份证'")
	private String asfzh;

	/**
	 * 订单主表ID
	 */
	@Column(name = "main_id", columnDefinition = "varchar(50) comment '订单主表ID'")
	private String mainId;


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



	/**
	 * 通过订单主表ID删除
	 * @param mainId
	 */
	public static void deleteBymainId(String mainId){
		List<DscSalesApplyPayee> lis = DscSalesApplyPayee.findByProperty(DscSalesApplyPayee.class, "mainId", mainId);
		if(lis!=null && !lis.isEmpty()) {
			for (DscSalesApplyPayee att : lis) {
				att.remove();
			}
		}

	}
	
	
	 public static DscSalesApplyPayee getByMainId(String mainId) {

		 StringBuilder jpql = new StringBuilder("select dsap from DscSalesApplyPayee dsap where dsap.mainId =?1");
		 List<Object> filter = new ArrayList<Object>() {{
			 add(mainId);
		 }};
		 return getRepository().createJpqlQuery(jpql.toString()).setParameters(filter).singleResult();
	 }
}
