package com.yixin.kepler.v1.service.capital.yntrust;

import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.enity.AssetContractTask;

/**
 * @author sukang
 */
public interface YnContractTaskService {

    /**
     * 合同文件上传
     * @param assetContractTask 合同任务
     * @return BaseMsgDTO
     */
     void uploadContractFile(AssetContractTask assetContractTask);


    /**
     * 获取合同文件
     * @param assetContractTask 合同任务
     * @return BaseMsgDTO
     */
    void getContractFile(AssetContractTask assetContractTask);

}
