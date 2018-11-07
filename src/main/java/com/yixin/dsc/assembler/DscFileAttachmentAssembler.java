package com.yixin.dsc.assembler;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;

import com.yixin.common.system.util.BaseAssembler;
import com.yixin.dsc.dto.order.DscFileAttachmentDTO;
import com.yixin.dsc.entity.order.DscFileAttachment;

/**
 * 附件信息装配类
 * Package : com.yixin.dsc.assembler
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月6日 下午3:56:06
 *
 */
public class DscFileAttachmentAssembler extends BaseAssembler {

	/**
	 * 组装附件实体
	 * @param fileList
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年6月6日 下午4:09:43
	 */
	public static List<DscFileAttachment> attachmentDtoListTOEntity(List<DscFileAttachmentDTO> fileList) {
		if(CollectionUtils.isEmpty(fileList)){
			return null;
		}
		List<DscFileAttachment> attachmentList = new ArrayList<>();
		DscFileAttachment attachment = null;
		for(DscFileAttachmentDTO fileDto:fileList){
			attachment = new DscFileAttachment();
			attachment.setAttachSubClass(fileDto.getAttachSubClass()); //附件小类
			attachment.setFileId(fileDto.getFileId()); //文件ID
			attachment.setFileName(fileDto.getFileName()); //文件名
			attachment.setFileType(fileDto.getFileType()); //文件类型
			attachment.setUpDateTime(new Date());
			attachmentList.add(attachment);
		}
		return attachmentList;
	}



	public static DscFileAttachment dto2Entity(DscFileAttachmentDTO dscFileAttachmentDTO) {
		if(dscFileAttachmentDTO==null) {
			return null;
		}
		DscFileAttachment result = new DscFileAttachment();
		mapObjWithoutNull(dscFileAttachmentDTO, result);
		result.setUpDateTime(new Date());
		result.setId(null);
		return result;
	}




	public static DscFileAttachmentDTO entity2Dto(DscFileAttachment dscFileAttachment) {
		if(dscFileAttachment==null) {
			return null;
		}
		DscFileAttachmentDTO result = new DscFileAttachmentDTO();
		mapObj(dscFileAttachment, result);
		return result;
	}
}
