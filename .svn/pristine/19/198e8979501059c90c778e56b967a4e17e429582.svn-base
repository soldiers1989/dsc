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
 * 准入资方记录表
 * 
 * Package : com.yixin.dsc.entity.rule
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月4日 下午7:31:35
 *
 */
@Entity
@Table(name = "dsc_access_capital")
public class DscAccessCapital extends BZBaseEntiy {

	private static final long serialVersionUID = -66524672665671309L;

	
	/**
	 * 订单编号
	 */
	@Column(name = "apply_no", columnDefinition = "varchar(64) comment '订单编号'")
	private String applyNo;
    
	/**
	 * 资方ID
	 */
	@Column(name = "capital_id", columnDefinition = "varchar(64) comment '资方ID'")
	private String capitalId;
    
    /**
     * 顺序
     */
    @Column(name = "access_order", columnDefinition = "varchar(500) comment '顺序'")
    private String accessOrder;
    
    /**
     * 是否当前生效资方
     */
    @Column(name = "take_efect", columnDefinition = "varchar(500) comment '是否当前生效资方'")
    private String takeEfect;
    
    /**
	 * 备注
	 */
	@Column(name = "remark", columnDefinition = "varchar(500) comment '备注'")
	private String remark;
	

	/**
	 * 通过订单编号获取 准入资方记录 集合
	 * @param applyNo 订单编号
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年6月8日 下午1:56:43
	 */
	public static List<DscAccessCapital> getListByParms(String applyNo,String takeEfect,Boolean deleted){
		List<DscAccessCapital> capitalList = Lists.newArrayList();
		if(StringUtils.isBlank(applyNo)){
			return capitalList;
		}
		Map<String,Object> capitalParm = new HashMap<>();
		capitalParm.put("applyNo",applyNo); //订单编号
		if(StringUtils.isNotBlank(takeEfect)){
			capitalParm.put("takeEfect",takeEfect); //是否生效
		}
		if(deleted !=null ){
			capitalParm.put("deleted",deleted); //是否删除状态，true为已删除，false为未删除
		}
		return DscAccessCapital.findByProperties(DscAccessCapital.class, capitalParm);
	}
	
	public static void logicRemoveOldRecord(String applyNo){
		
	}
	
	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getCapitalId() {
		return capitalId;
	}

	public void setCapitalId(String capitalId) {
		this.capitalId = capitalId;
	}

	public String getAccessOrder() {
		return accessOrder;
	}

	public void setAccessOrder(String accessOrder) {
		this.accessOrder = accessOrder;
	}

	public String getTakeEfect() {
		return takeEfect;
	}

	public void setTakeEfect(String takeEfect) {
		this.takeEfect = takeEfect;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}



}
