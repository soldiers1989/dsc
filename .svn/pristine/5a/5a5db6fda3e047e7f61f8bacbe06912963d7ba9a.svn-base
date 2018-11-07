package com.yixin.web.service;

import java.util.List;
import java.util.Map;

import com.yixin.common.utils.InvokeResult;
import com.yixin.common.utils.Page;
import com.yixin.dsc.dto.ExportProductInfoDTO;
import com.yixin.kepler.enity.AssetProductFinancialRel;

/**
 * @author sukang
 */
public interface ProductService {

    /**
     * 创建产品
     *
     * @param assetProductFinancialRel 产品对象
     * @return 是否创建成功
     */
    Boolean createFinancialProduct(AssetProductFinancialRel assetProductFinancialRel);


    /**
     * 获取产品详细信息
     * @param productCode  产品code
     * @param financialCode 资方code
     * @param enable  是否启用
     * @param currentPage  当前页面
     * @return 集合
     */
    Page<List<Map<String, Object>>> getFinancialProduct(String productCode,
                                   String financialCode,
                                   String enable,
                                   String currentPage);

    /**
     * 更新产品信息
     * @param aRel 产品对象
     * @return 是否更新成功
     */
    InvokeResult<Boolean> updateFinancialProduct(AssetProductFinancialRel aRel);
    
    /**
     * 获取选中的产品列表,导出使用
     * 
     * @param productCode
     * @param financialCode
     * @param enable
     * @return 
     * @author YixinCapital -- zongzhiping
     *	       2018年10月26日 上午10:33:49
     */
	List<ExportProductInfoDTO> getSelectFinancialProduct(String productCode, String financialCode, String enable);
    
    


}
