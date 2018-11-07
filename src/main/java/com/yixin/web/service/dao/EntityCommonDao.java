package com.yixin.web.service.dao;

import com.yixin.dsc.entity.order.DscSalesApplyCust;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.kepler.enity.AssetMainInfo;
import com.yixin.web.dto.AssetMainInfoDto;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

/**
 * @author sukang
 */

@Component
public class EntityCommonDao {



    public String getApplyNoFromCust(AssetMainInfoDto assetMainInfoDto){

        if (StringUtils.isNotBlank(assetMainInfoDto.getApplyNo())){
            return assetMainInfoDto.getApplyNo();
        }else if (StringUtils.isNotBlank(assetMainInfoDto.getIdentity())){
            String hql = " SELECT dsac FROM  DscSalesApplyCust as dsac " +
                    " WHERE dsac.asjhm = ?1 OR dsac.azjhm = ?2 OR dsac.akhxm = ?3";

            ArrayList<Object> params = new ArrayList<Object>(3){{
                add(assetMainInfoDto.getIdentity());
                add(assetMainInfoDto.getIdentity());
                add(assetMainInfoDto.getIdentity());
            }};
            DscSalesApplyCust dscSalesApplyCust = DscSalesApplyCust.getRepository()
                                                                    .createJpqlQuery(hql)
                                                                    .setParameters(params)
                                                                    .singleResult();
            if (dscSalesApplyCust == null ){
                return "";
            }
            DscSalesApplyMain dscSalesApplyMain = DscSalesApplyMain.get(DscSalesApplyMain.class, dscSalesApplyCust.getMainId());
            return dscSalesApplyMain.getApplyNo();

        }
        return "";
    }





}
