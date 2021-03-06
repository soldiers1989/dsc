package com.yixin.dsc.entity.order;

import com.yixin.common.system.domain.BZBaseEntiy;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.List;

/**
 * 合同申请联系人信息表
 * Package : com.yixin.dsc.entity
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月4日 下午5:53:23
 *
 */
@Entity
@Table(name = "DSC_SALES_APPLY_CONTACT")
public class DscSalesApplyContact extends BZBaseEntiy{

	private static final long serialVersionUID = 1847550744719307292L;

	@Column(name = "ayjkrgx", columnDefinition = "varchar(64) comment '与借款人关系'")
	private String ayjkrgx;
	
	@Column(name = "alxrxm", columnDefinition = "varchar(128) comment '联系人姓名'")
	private String alxrxm;
	
	@Column(name = "asjhm", columnDefinition = "varchar(64) comment '手机号码'")
	private String asjhm;

	/**
	 * 订单主表ID
	 */
	@Column(name = "main_id", columnDefinition = "varchar(50) comment '订单主表ID'")
	private String mainId;

	/**
	 * 客户ID
	 */
	@Column(name = "cust_id", columnDefinition = "varchar(50) comment '客户ID'")
	private String custId;

	@Column(name = "ayjkrjtgx", columnDefinition = "varchar(64) comment '与借款人具体关系'")
	private String ayjkrjtgx;


	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	public String getCustId() {
		return custId;
	}

	public void setCustId(String custId) {
		this.custId = custId;
	}

	public String getAyjkrgx() {
		return ayjkrgx;
	}

	public void setAyjkrgx(String ayjkrgx) {
		this.ayjkrgx = ayjkrgx;
	}

	public String getAlxrxm() {
		return alxrxm;
	}

	public void setAlxrxm(String alxrxm) {
		this.alxrxm = alxrxm;
	}

	public String getAsjhm() {
		return asjhm;
	}

	public void setAsjhm(String asjhm) {
		this.asjhm = asjhm;
	}

	public String getAyjkrjtgx() {
		return ayjkrjtgx;
	}

	public void setAyjkrjtgx(String ayjkrjtgx) {
		this.ayjkrjtgx = ayjkrjtgx;
	}

	/**
	 * 通过订单主表ID删除
	 * @param mainId
	 */
	public static void deleteBymainId(String mainId){
		List<DscSalesApplyContact> lis = DscSalesApplyContact.findByProperty(DscSalesApplyContact.class, "mainId", mainId);
		if(lis!=null && !lis.isEmpty()) {
			for (DscSalesApplyContact att : lis) {
				att.remove();
			}
		}
	}
	
	/**
	 * 通过主信息ID获取客户信息
	 * @param mainId 主信息ID
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年6月8日 下午5:47:54
	 */
	public static List<DscSalesApplyContact> getListByParms(String mainId){
		List<DscSalesApplyContact> applyCustList = Lists.newArrayList();
		if(StringUtils.isBlank(mainId)){
			return applyCustList;
		}
		return DscSalesApplyContact.findByProperty(DscSalesApplyContact.class, "mainId", mainId);
	}
		
}
