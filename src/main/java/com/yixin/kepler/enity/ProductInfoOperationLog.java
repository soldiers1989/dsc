package com.yixin.kepler.enity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;

import org.apache.commons.lang.StringUtils;

import com.alibaba.fastjson.JSON;
import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.common.system.util.Label;
import com.yixin.web.utils.CurrentUser;
/**
 * 产品信息变更日志类
 * @author zongzhiping
 *
 */
@Entity
@Table(name = "k_product_info_operation_log")
public class ProductInfoOperationLog extends BZBaseEntiy {
	
	private static final long serialVersionUID = 1L;
	/**
	 * 产品CODE
	 */
	@Label(name = "产品CODE")
	@Column(name = "product_code", length = 20)
	private String productCode;
	/**
	 * 操作
	 */
	@Label(name = "操作")
	@Column(name = "action", length = 64)
	private String action;
	/**
	 * 操作ip
	 */
	@Label(name = "操作ip")
	@Column(name = "ip", length = 32)
	private String ip;
	/**
	 * 操作前的值
	 */
	@Label(name = "操作前的值")
	@Column(name = "old_value", columnDefinition = "LONGTEXT")
	private String oldValue;
	/**
	 * 操作后的值
	 */
	@Label(name = "操作后的值")
	@Column(name = "new_value", columnDefinition = "LONGTEXT")
	private String newValue;
	
	
	public String getProductCode() {
		return productCode;
	}
	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}
	public String getAction() {
		return action;
	}
	public void setAction(String action) {
		this.action = action;
	}
	public String getIp() {
		return ip;
	}
	public void setIp(String ip) {
		this.ip = ip;
	}
	public String getOldValue() {
		return oldValue;
	}
	public void setOldValue(String oldValue) {
		this.oldValue = oldValue;
	}
	public String getNewValue() {
		return newValue;
	}
	public void setNewValue(String newValue) {
		this.newValue = newValue;
	}
	/**
	 * 创建日志
	 * 
	 * @param user
	 * @param operate
	 * @param productInfo
	 * @return 
	 * @author YixinCapital -- zongzhiping
	 *	       2018年10月29日 上午10:17:51
	 */
	public static String createLog(AssetProductFinancialRel productInfo,String operate,String productCode){
		ProductInfoOperationLog log = new ProductInfoOperationLog();
		if(productInfo!=null){
			log.setCreatorId(productInfo.getCreatorId());
			log.setCreatorName(productInfo.getCreatorName());
			log.setCreatorDepartmentId(productInfo.getCreatorDepartmentId());
			log.setCreatorDepartmentName(productInfo.getCreatorDepartmentName());
		}
		log.setAction(operate);
		log.setProductCode(productCode);
		log.setNewValue(JSON.toJSONString(productInfo));
		AssetProductFinancialRel productInfoByProductCode = AssetProductFinancialRel.getProductInfoByProductCode(productCode);
		if(productInfoByProductCode==null){
			log.setOldValue(null);
		}else{
			log.setOldValue(JSON.toJSONString(productInfoByProductCode));
		}
		return log.create();
	}
	
	
	
	
	
	
	

}
