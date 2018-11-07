package com.yixin.kepler.v1.datapackage.dto.yntrust;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sukang
 */
public class YTConTractFileDTO extends YTCommonRequestDTO{

    /**
     * 贷款唯一标示
     */
    @JsonProperty(value = "UniqueId")
    private String uniqueId;

    /**
     * 获取文件类型
     * 1、如果项目有多个文件类型需要做电子签章，此字段必填；
       2、如只有贷款合同需电子签章，此字段选填，默认拉取贷款合同文件；
     */
    @JsonProperty(value = "Type")
    private String type;

    /**
     * 是否获取合同文件内容	填写数字0代表不获取合同内容只是查看文件签章状态
     */
    @JsonProperty(value = "IsGetFileContent")
    private String isGetFileContent;


    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getIsGetFileContent() {
        return isGetFileContent;
    }

    public void setIsGetFileContent(String isGetFileContent) {
        this.isGetFileContent = isGetFileContent;
    }
}
