package com.yixin.kepler.enity;

import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;

import com.google.common.collect.Maps;
import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.common.system.util.Label;
import com.yixin.dsc.dto.DscCapitalDto;


/**
 * <B>功能简述</B><br>
 * KAssetProductFinancialRel实体类
 *
 * @author liuhongshen
 * @date 2018年06月06日 14:14:54
 */
@Entity
@Table(name = "k_asset_product_financial_rel")
public class AssetProductFinancialRel extends BZBaseEntiy {
    /**
	 * 
	 */
	private static final long serialVersionUID = 1973149043815375458L;
	/**
     * 资方id
     */
    @Label(name = "资方id")
    @Column(name = "financial_id", length = 32)
    private String financialId;
    /**
     * 资方code
     */
    @Label(name = "资方code")
    @Column(name = "financial_code", length = 64)
    private String financialCode;
    /**
     * 产品id
     */
    @Label(name = "产品id")
    @Column(name = "product_id", length = 32)
    private String productId;
    /**
     * 产品编号
     */
    @Label(name = "产品编号")
    @Column(name = "product_code", length = 64)
    private String productCode;
    /**
     * 产品版本号
     */
    @Label(name = "产品版本号")
    @Column(name = "product_version", length = 32)
    private String productVersion;
    /**
     * 产品名称
     */
    @Label(name = "产品名称")
    @Column(name = "product_name", length = 64)
    private String productName;


    public String getFinancialId() {
        return this.financialId;
    }

    public void setFinancialId(String financialId) {
        this.financialId = financialId;
    }

    public String getFinancialCode() {
        return this.financialCode;
    }

    public void setFinancialCode(String financialCode) {
        this.financialCode = financialCode;
    }

    public String getProductId() {
        return this.productId;
    }

    public void setProductId(String productId) {
        this.productId = productId;
    }

    public String getProductCode() {
        return this.productCode;
    }

    public void setProductCode(String productCode) {
        this.productCode = productCode;
    }

    public String getProductVersion() {
        return this.productVersion;
    }

    public void setProductVersion(String productVersion) {
        this.productVersion = productVersion;
    }

    public String getProductName() {
        return this.productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }


    /**
     * 无参构造
     */
    public AssetProductFinancialRel() {

    }
    
    
    /**
	 * 根据产品编号获取
	 * @param productCode 产品编号
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年6月11日 下午3:29:14
	 */
	public static List<DscCapitalDto> getCapitalListByProductCode(String productCode){
		if(StringUtils.isBlank(productCode)){
			return Lists.newArrayList();
		}
		Map<String,Object> params = Maps.newConcurrentMap();
		params.put("productCode", productCode);
		params.put("deleted", false);
		
		List<AssetProductFinancialRel> financialReList = AssetProductFinancialRel.findByProperties(AssetProductFinancialRel.class,params);
		if(CollectionUtils.isEmpty(financialReList)){
			return Lists.newArrayList();
		} else {
			List<DscCapitalDto> capitalDtoList = Lists.newArrayList();
			DscCapitalDto capitalDto = null;
			for(AssetProductFinancialRel rel:financialReList){
				capitalDto = new DscCapitalDto();
				capitalDto.setCapitalId(rel.getFinancialId()); //资方ID
				capitalDto.setCapitalCode(rel.getFinancialCode()); //资方CODE
				capitalDtoList.add(capitalDto);
			}
			return capitalDtoList;
		}
	}
	
	/**
	 * 根据产品编号获取 资方Codes
	 * @param productCode 产品编号
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年6月11日 下午3:29:14
	 */
	public static String getCapitalCodesByProductCode(String productCode){
		if(StringUtils.isBlank(productCode)){
			return null;
		}
		Map<String,Object> params = Maps.newConcurrentMap();
		params.put("productCode", productCode);
		params.put("deleted", false);
		
		List<AssetProductFinancialRel> financialReList = AssetProductFinancialRel.findByProperties(AssetProductFinancialRel.class,params);
		if(CollectionUtils.isEmpty(financialReList)){
			return null;
		} else {
			StringBuilder result = new StringBuilder();
			for(int i=0 ;i<financialReList.size();i++){
				AssetProductFinancialRel rel = financialReList.get(i);
				if(rel!=null) {
					result.append(rel.getFinancialCode());//资方CODE
					if(financialReList.size()-1>i) {
						result.append(",");
					}
				}
			}
			return result.toString();
		}
	}
	
	/**
	 * 根据产品编码获取产品名称，注意产品名称不准确
	 * @param productCode
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年10月19日 上午11:16:22
	 */
	public static String getProductNameByProductCode(String productCode){
		if(StringUtils.isBlank(productCode)){
			return "";
		}
		try {
			Map<String,Object> params = Maps.newConcurrentMap();
			params.put("productCode", productCode);
			params.put("deleted", false);
			
			List<AssetProductFinancialRel> financialReList = AssetProductFinancialRel.findByProperties(AssetProductFinancialRel.class,params);
			if(CollectionUtils.isEmpty(financialReList)){
				return "";
			}
			
			return financialReList.get(0).getProductName();
		} catch (Exception e) {
			return "";
		}
	}
	
	
	/**
	 * 根据产品编号获取产品信息
	 * 
	 * @param productCode
	 *            产品编号
	 * @return
	 * @author YixinCapital -- zongzhiping 2018年10月29日10:57:36
	 */
	public static AssetProductFinancialRel getProductInfoByProductCode(String productCode) {
		AssetProductFinancialRel assetProductFinancialRel = null;
		if (StringUtils.isBlank(productCode)) {
			return null;
		}
		Map<String, Object> params = Maps.newConcurrentMap();
		params.put("productCode", productCode);
		List<AssetProductFinancialRel> financialReList = AssetProductFinancialRel.findByProperties(AssetProductFinancialRel.class, params);
		if(CollectionUtils.isNotEmpty(financialReList)){
			
			 assetProductFinancialRel = financialReList.get(0);
		}
		return assetProductFinancialRel;
	}

    /**
     * 创建产品，默认失效
     * @author YixinCapital -- wangshuaiqiang
     *	       2018/10/26  14:14
     */
    public String ceateAndDisable() {
        this.setCreateTime(new Date());
        this.setDeleted(true);//不启用
        this.setUpdateTime(new Date());
        this.save();
        return this.getId();
    }
}