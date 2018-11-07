package com.yixin.web.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yixin.common.utils.InvokeResult;
import com.yixin.common.utils.Page;
import com.yixin.dsc.dto.ExportProductInfoDTO;
import com.yixin.kepler.enity.AssetProductFinancialRel;
import com.yixin.web.service.ProductService;
import com.yixin.web.service.base.BaseService;

import net.sf.json.JSONObject;

/**
 * @author sukang
 */
@Service
public class ProductServiceImpl extends BaseService implements ProductService{

    private final Logger logger = LoggerFactory.getLogger(getClass());

    @Override
    public Boolean createFinancialProduct(AssetProductFinancialRel assetProductFinancialRel) {
        //新增时默认失效  add by wangshuaiqiang -- 2018/10/19  17:38
        String id = assetProductFinancialRel.ceateAndDisable();
        String json = JSONObject.fromObject(assetProductFinancialRel).toString();
        logger.info("创建产品成功id={}，{}",id, json);
        return true;
    }


    @Override
    @SuppressWarnings("unchecked")
    public Page<List<Map<String, Object>>> getFinancialProduct(String productCode, String financialCode, String enable, String currentPage) {

        ArrayList<Object> parameters = new ArrayList<>(3);

        StringBuilder hql = new StringBuilder(
                "SELECT new map(apf.financialCode as financialCode,apf.id as id,"
                        + " DATE_FORMAT(apf.createTime,'%Y-%m-%d %H:%i:%s') as createTime,"
                        + " case apf.deleted when 'true' then 1 else 0 end as enable,"
                        + " apf.productName as productName,apf.productCode as productCode, "
                        + " apf.productVersion as productVersion )"
                        + " FROM AssetProductFinancialRel apf WHERE 1=1");
        int index = 0;
        if (StringUtils.isNotBlank(productCode)) {
            hql.append(" AND apf.productCode = ?".concat(String.valueOf(++index)));
            parameters.add(productCode.trim());
        }
        if (StringUtils.isNotBlank(financialCode)) {
            hql.append(" AND apf.financialCode = ?".concat(String.valueOf(++index)));
            parameters.add(financialCode);
        }
        if (StringUtils.isNotBlank(enable)) {
            hql.append(" AND apf.deleted = ".concat("1".equals(enable) ? "false":"true"));
        }
        hql.append(" ORDER BY apf.createTime DESC");
        String sql = hql.toString();
        logger.debug("查询产品的sql为{}",sql);

        return queryChannel.createJpqlQuery(sql)
                .setParameters(parameters)
                .setPage(dealPageIndex(currentPage),10)
                .pagedList();
    }


    @Override
    public InvokeResult<Boolean> updateFinancialProduct(AssetProductFinancialRel aRel) {
        InvokeResult<Boolean> invokeResult = new InvokeResult<>();

        AssetProductFinancialRel financialRel = AssetProductFinancialRel.get(AssetProductFinancialRel.class, aRel.getId());

        if (financialRel == null) {
            return invokeResult.failure("产品不存在");
        }

        if (StringUtils.isNotBlank(aRel.getProductCode())){
            financialRel.setProductCode(aRel.getProductCode());
        }

        if (StringUtils.isNotBlank(aRel.getProductName())){
            financialRel.setProductName(aRel.getProductName());
        }

        financialRel.setDeleted(aRel.isDeleted());
        financialRel.update();
        return invokeResult.success();
    }
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
	@Override
	@SuppressWarnings("unchecked")
	public List<ExportProductInfoDTO> getSelectFinancialProduct(String productCode, String financialCode,String enable) {
		ArrayList<Object> parameters = new ArrayList<>(3);
		StringBuilder hql = new StringBuilder("SELECT "
				+ "apf.product_Code as productCode,"
				+ " apf.product_Name as productName," 
				+ " GROUP_CONCAT(apf.financial_Code) as financialCode "
				+ " FROM k_asset_product_financial_rel apf WHERE 1=1");
		int index = 0;
		if (StringUtils.isNotBlank(productCode)) {
			hql.append(" AND apf.product_Code = ?".concat(String.valueOf(++index)));
			parameters.add(productCode);
		}
		if (StringUtils.isNotBlank(financialCode)) {
			hql.append(" AND apf.financial_Code = ?".concat(String.valueOf(++index)));
			parameters.add(financialCode);
		}
		if (StringUtils.isNotBlank(enable)) {
			hql.append(" AND apf.is_deleted = ".concat("1".equals(enable) ? "1" : "0"));
		}
		hql.append(" group BY apf.product_Code ");
		hql.append(" ORDER BY apf.create_Time DESC");
		String sql = hql.toString();
		logger.info("getSelectFinancialProduct/查询产品的sql为{}", sql);
		List<Object[]> list = queryChannel.createSqlQuery(sql).setParameters(parameters).list();
		List<ExportProductInfoDTO> proList = new ArrayList<>();
		for(int i=0; list != null && i<list.size(); i++) {
			 Object[] objects = list.get(i);
			if(objects.length > 0) {
				ExportProductInfoDTO exportProductInfoDTO = new ExportProductInfoDTO();
				for(int j=0; j<objects.length; j++) {
					exportProductInfoDTO.setProductCode((String)objects[0]);
					exportProductInfoDTO.setProductName((String)objects[1]);
					exportProductInfoDTO.setFinancialCode((String)objects[2]);
				}
				proList.add(exportProductInfoDTO);
			}
		}
		return proList;

	}
}
