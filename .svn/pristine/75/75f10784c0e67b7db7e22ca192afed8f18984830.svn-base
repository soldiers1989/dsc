package com.yixin.kepler.v1.datapackage.dto.yntrust;

/**
 * @author sukang
 */
public class YTContractRespDTO extends YTCommonResponseDTO {


    /**
     * 文件内容，字节数组，格式统一为pdf
     */
    private String ContractFile;

    /**
     * 签约状态：
     未生成合同=1,
     生成合同成功=2,
     生成合同失败=3,
     合同签章成功=4,
     合同签章失败=5,
     发送放款指令成功=6,
     发送放款指令失败=7,
     签章处理中=8,
     签章人工处理=9,
     若该接口返回『503服务不可用』，则请关闭推单。

     */
    private String SignStatus;

    public String getContractFile() {
        return ContractFile;
    }

    public void setContractFile(String contractFile) {
        ContractFile = contractFile;
    }

    public String getSignStatus() {
        return SignStatus;
    }

    public void setSignStatus(String signStatus) {
        SignStatus = signStatus;
    }
}
