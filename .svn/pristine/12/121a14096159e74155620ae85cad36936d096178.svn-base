package com.yixin.web.service.base;

import com.yixin.common.system.domain.BaseEntity;
import com.yixin.common.system.querychannel.QueryChannelService;
import com.yixin.web.common.AssetStatusType;

import java.io.Serializable;

/**
 * service 基础类
 * @author sukang
 */
public class BaseService {

    protected final QueryChannelService queryChannel = BaseEntity.getQueryChannel();

    protected Integer dealPageIndex(String currentPage){
        Integer page;
        try {
            page =  Integer.parseInt(currentPage);
        }catch (Exception e){
            page = 0;
        }
        return page <= 0 ? 0 : page - 1;
    }


    /**
     * 获取状态的中文描述
     * @return 中文描述
     */
    protected  String getStatusMsg(Object status, AssetStatusType assetStatusType){
        Integer integer = 4;
        if (status instanceof Integer){
            integer = (Integer) status;
        }else {
            try {
                integer = Integer.parseInt(String.valueOf(status));
            }catch (Exception e){
                //不做处理
            }
        }
        return assetStatusType.getMsg(integer);
    }

}
