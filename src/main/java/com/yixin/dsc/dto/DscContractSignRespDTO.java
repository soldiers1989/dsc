package com.yixin.dsc.dto;

import com.yixin.dsc.dto.order.DscFileAttachmentDTO;
import com.yixin.dsc.entity.order.DscFileAttachment;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.dto.BaseMsgDTO;

import java.util.ArrayList;
import java.util.List;

/**
 * @author sukang
 */
public class DscContractSignRespDTO extends BaseMsgDTO{

    private List<DscFileAttachmentDTO> fileAttachmentDTOList;


    public  DscContractSignRespDTO success(String message){
        this.setCode(CommonConstant.SUCCESS);
        this.setMessage(message);
        return this;
    }

    public  DscContractSignRespDTO failure(String message){
        this.setCode(CommonConstant.FAILURE);
        this.setMessage(message);
        return this;
    }

    public List<DscFileAttachmentDTO> getFileAttachmentDTOList() {
        return fileAttachmentDTOList;
    }

    public void setFileAttachmentDTOList(List<DscFileAttachmentDTO> fileAttachmentDTOList) {
        this.fileAttachmentDTOList = fileAttachmentDTOList;
    }
}
