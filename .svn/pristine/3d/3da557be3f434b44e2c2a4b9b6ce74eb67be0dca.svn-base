package com.yixin.web.common;

import com.yixin.kepler.common.enums.AssetStateEnum;

/**
 * @author sukang
 */
public enum AssetStatusType {




    //bank阶段状态
    bankStatusInfo{
        @Override
        public String getMsg(Integer status) {
            return AssetStateEnum.getNameByState(status);
        }
    },

    //合同签署状态
    contractSignStateInfo{
        @Override
        public String getMsg(Integer status) {
            return status == 1 ? "合同签署成功" : "合同未签署";
        }
    },

    //征信授权状态
    creditSignStateInfo{
        @Override
        public String getMsg(Integer status) {
            return status == 1 ? "授权书签署成功" : "授权书未签署";
        }
    };






    public String getMsg(Integer status){
        return "未知";
    }


}
