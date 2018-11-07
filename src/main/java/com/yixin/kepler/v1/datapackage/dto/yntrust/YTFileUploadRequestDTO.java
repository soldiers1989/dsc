package com.yixin.kepler.v1.datapackage.dto.yntrust;



import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 文件上传
 * @author sukang
 */
public class YTFileUploadRequestDTO extends YTCommonRequestDTO{

    @JsonProperty(value = "IDCardNo")
    private String idCardNo;

    @JsonProperty(value = "LicenseNo")
    private String licenseNo;

    @JsonProperty(value = "UniqueId")
    private String uniqueId;

    @JsonProperty(value = "ImageContent")
    private List<YTImageContentDTO> imageContent;

    @JsonProperty(value = "Remark")
    private String remark;

    public String getIdCardNo() {
        return idCardNo;
    }

    public void setIdCardNo(String idCardNo) {
        this.idCardNo = idCardNo;
    }

    public String getLicenseNo() {
        return licenseNo;
    }

    public void setLicenseNo(String licenseNo) {
        this.licenseNo = licenseNo;
    }

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public List<YTImageContentDTO> getImageContent() {
        return imageContent;
    }

    public void setImageContent(List<YTImageContentDTO> imageContent) {
        this.imageContent = imageContent;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}
