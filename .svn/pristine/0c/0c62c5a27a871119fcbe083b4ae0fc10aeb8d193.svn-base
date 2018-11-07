package com.yixin.dsc.entity.order;

import com.google.common.collect.Maps;
import com.yixin.common.exception.BzException;
import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.kepler.v1.utils.JacksonUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 合同申请融资额明细表
 * 融资明细信息
 * 
 * Package : com.yixin.dsc.entity
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月4日 下午5:42:31
 *
 */
@Entity
@Table(name = "dsc_sales_apply_financing")
public class DscSalesApplyFinancing extends BZBaseEntiy{
	
	private static final long serialVersionUID = 8583698596050270688L;

	/**
	 * 	ARZXMMC	融资项名称
	 */
	@Column(name = "arzxmmc", columnDefinition = "varchar(64) comment '融资项名称'")
	private String arzxmmc;

	/**
	 * 	arzxmid	融资项ID
	 */
	@Column(name = "arzxmid", columnDefinition = "varchar(20) comment '融资项ID'")
	private String arzxmid;

	/**
	 * 	FKHRZJE	融资项金额
	 */
	@Column(name = "fkhrzje", columnDefinition = "decimal(19,2) comment '融资项金额'")
	private BigDecimal fkhrzje;


	/**
	 * 订单主表ID
	 */
	@Column(name = "main_id", columnDefinition = "varchar(50) comment '订单主表ID'")
	private String mainId;


	/**
	 * 费用信息表ID
	 */
	@Column(name = "cost_id", columnDefinition = "varchar(50) comment '费用信息表ID'")
	private String costId;

	/**
	 * 是否添加首付 1：添加  0：未添加
	 */
	@Column(name = "asfjrsf", columnDefinition = "varchar(10) comment '是否添加首付'")
	private String asfjrsf;

	public String getAsfjrsf() {
		return asfjrsf;
	}

	public void setAsfjrsf(String asfjrsf) {
		this.asfjrsf = asfjrsf;
	}

	public String getArzxmmc() {
		return arzxmmc;
	}

	public void setArzxmmc(String arzxmmc) {
		this.arzxmmc = arzxmmc;
	}

	public BigDecimal getFkhrzje() {
		return fkhrzje;
	}

	public void setFkhrzje(BigDecimal fkhrzje) {
		this.fkhrzje = fkhrzje;
	}

	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	public String getCostId() {
		return costId;
	}

	public void setCostId(String costId) {
		this.costId = costId;
	}

	public String getArzxmid() {
		return arzxmid;
	}

	public void setArzxmid(String arzxmid) {
		this.arzxmid = arzxmid;
	}

	/**
	 * 通过订单主表ID删除
	 * @param mainId
	 */
	public static void deleteBymainId(String mainId){
		List<DscSalesApplyFinancing> lis = DscSalesApplyFinancing.findByProperty(DscSalesApplyFinancing.class, "mainId", mainId);
		if(lis!=null && !lis.isEmpty()) {
			for (DscSalesApplyFinancing att : lis) {
				att.remove();
			}
		}
	}
	
	 public static List<DscSalesApplyFinancing> getByMainId(String mainId) {

	        StringBuilder jpql = new StringBuilder("select dsaf from DscSalesApplyFinancing dsaf where dsaf.deleted = false and dsaf.mainId = ?1");
	        List<Object> filter = new ArrayList<Object>() {{
	            add(mainId);
	        }};
	        return getRepository().createJpqlQuery(jpql.toString()).setParameters(filter).list();
	}
	/**
	 *	获取融资项目
	 * @param applyNo	申请编号
	 * @return
	 */
	public static Map<String, DscSalesApplyFinancing> get(String mainId){
		logger.info("查询融资项目信息，入参mainId：{}", mainId);
		Map<String, DscSalesApplyFinancing> map = Maps.newHashMap();
		try {
			List<DscSalesApplyFinancing> items = getByMainId(mainId);
			if(null != items && !items.isEmpty()){
				for(DscSalesApplyFinancing item : items){
					map.put(item.getArzxmid(), item);
				}
			}else{
				logger.warn("没有查到任何融资项目信息");
			}
		} catch (BzException e) {
			logger.error("查询融资项目信息失败 mainId={}", mainId, e);
		}
		logger.info("查询融资项目信息 applyNo：{}，data：{}", mainId, JacksonUtils.fromObjectToJson(map));
		return map;
	}
}
