package com.yixin.dsc.dto.order;

import java.io.Serializable;
import java.util.Date;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yixin.common.utils.serializer.JsonDateDeserializer;
import com.yixin.common.utils.serializer.JsonDateTimeSerializer;

/**
 * 申请提报附件表
 * Package : com.yixin.dsc.entity
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月4日 下午2:15:38
 *
 */
public class DscFileAttachmentDTO implements Serializable{


	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String id;

	/**
	 *  订单主表ID
	 */
	private String mainId;

	/**
     * 附件小类
     */
	private String attachSubClass;

	/**
     * 文件名
     */
	private String fileName;
	
	/**
     * 文件类型
     */
	private String fileType;

	/**
	 * 文件上传时间
	 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date upDateTime;

	/**
	 * 附件ID，文件服务器上的唯一标识
	 */
	private String fileId;


	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	public String getFileId() {
		return fileId;
	}

	public void setFileId(String fileId) {
		this.fileId = fileId;
	}

	public String getAttachSubClass() {
		return attachSubClass;
	}

	public void setAttachSubClass(String attachSubClass) {
		this.attachSubClass = attachSubClass;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getFileType() {
		return fileType;
	}

	public void setFileType(String fileType) {
		this.fileType = fileType;
	}

	public Date getUpDateTime() {
		return upDateTime;
	}

	public void setUpDateTime(Date upDateTime) {
		this.upDateTime = upDateTime;
	}
}
