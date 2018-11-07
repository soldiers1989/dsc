package com.yixin.dsc.dto;

import com.yixin.dsc.dto.order.DscFileAttachmentDTO;

import javax.annotation.ManagedBean;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.util.List;

/**
 * @author sukang
 */
public class DscContractSignFileDTO {

    @NotBlank(message = "applyNo(申请编号)不能为空")
    private String applyNo;

    private List<DscFileAttachmentDTO> fileAttachmentDTOList;

    @NotBlank(message = "type(类型)不能为空,取值为0~1")
    @Max(value = 1,message = "type选值范围为0~1")
    @Min(value = 0,message = "type选值范围为0~1")
    private String type;

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public List<DscFileAttachmentDTO> getFileAttachmentDTOList() {
        return fileAttachmentDTOList;
    }

    public void setFileAttachmentDTOList(List<DscFileAttachmentDTO> fileAttachmentDTOList) {
        this.fileAttachmentDTOList = fileAttachmentDTOList;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
