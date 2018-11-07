package com.yixin.dsc.entity.order;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.jpa.repository.Modifying;

import com.alibaba.dubbo.common.utils.StringUtils;
import com.google.common.collect.Lists;
import com.yixin.common.system.domain.BZBaseEntiy;

/**
 * 申请提报附件表
 * Package : com.yixin.dsc.entity
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月4日 下午2:15:38
 *
 */
@Entity
@Table(name = "DSC_TFILE_ATTACHMENT")
public class DscFileAttachment extends BZBaseEntiy{

	private static final long serialVersionUID = 2125125264995590319L;
	
	@Column(name = "ATATTACHSUBCLASS", columnDefinition = "varchar(64) comment '附件小类'")
	private String attachSubClass;

	@Column(name = "AFILENAME", columnDefinition = "varchar(128) comment '文件名'")
	private String fileName;
	
	@Column(name = "AFILETYPE", columnDefinition = "varchar(64) comment '文件类型'")
	private String fileType;

	@Column(name = "up_date_time", columnDefinition = "datetime comment '文件上传时间'")
	private Date upDateTime;

	/**
	 * 附件ID，文件服务器上的唯一标识
	 */
	@Column(name = "FILE_ID", columnDefinition = "varchar(128) comment '附件ID，文件服务器上的唯一标识'")
	private String fileId;


	/**
	 *  订单主表ID
	 */
	@Column(name = "main_id", columnDefinition = "varchar(50) comment '订单主表ID'")
	private String mainId;

	@Column(name = "file_url", length = 256)
	private String fileUrl;


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

	public String getFileUrl() {
		return fileUrl;
	}

	public void setFileUrl(String fileUrl) {
		this.fileUrl = fileUrl;
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


	/**
	 * 通过订单主表ID删除
	 * @param mainId
	 */
	public static void deleteBymainId(String mainId){
		List<DscFileAttachment> lis = DscFileAttachment.findByProperty(DscFileAttachment.class, "mainId", mainId);
		if(lis!=null && !lis.isEmpty()) {
			for (DscFileAttachment att : lis) {
				att.remove();
			}
		}
	}



	public static List<DscFileAttachment> getByIdAndAttachClass(String id,
																String attachClass){
		String jpql = "SELECT dfa FROM DscFileAttachment AS dfa WHERE"
				+ " dfa.deleted = ?1 AND dfa.mainId = ?2 AND dfa.attachSubClass = ?3 " +
				" ORDER BY dfa.createTime DESC ";

		List<Object> filter = new ArrayList<Object>() {{
			add(false);
			add(id);
			add(attachClass);
		}};
		return getRepository().createJpqlQuery(jpql).setParameters(filter)
				.list();
	}




	public static List<DscFileAttachment> lists(String mainId){
		Map<String,Object> param = new HashMap<>();
		param.put("mainId", mainId);
		param.put("deleted", false);
		return DscFileAttachment.findByProperties(DscFileAttachment.class, param);
	}

	public static Map<String, List<DscFileAttachment>> get(String mainId){
		List<DscFileAttachment> list = lists(mainId);
		Map<String, List<DscFileAttachment>> attMap = new HashMap<>();
		
		if (!list.isEmpty()) {
			
			for (DscFileAttachment dscFileAttachment : list) {
				if (!attMap.containsKey(dscFileAttachment.getAttachSubClass())) {
					attMap.put(dscFileAttachment.getAttachSubClass(),
							new ArrayList<DscFileAttachment>(4){{
								add(dscFileAttachment);
							}});
				}else {
					attMap.get(dscFileAttachment.getAttachSubClass()).add(dscFileAttachment);
				}
			}
		}
		
		
		
		
		/*if(list != null && list.size() > 0){
			for(DscFileAttachment attachment : list){
				List<DscFileAttachment> typeAtts = attMap.get(attachment.getFileType());
				if(typeAtts == null){
					typeAtts = Lists.newArrayList();
					attMap.put(attachment.getAttachSubClass(), typeAtts);
				}
				typeAtts.add(attachment);

			}

		}*/
		return attMap;
	}

	/**
	 * 逻辑删除附件
	 * @param mainId 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年9月21日 下午2:13:06
	 */
	@Modifying
	public static void logicDelete(String mainId,String attachSubClass,String fileUrl,String applyNo){
		if(StringUtils.isBlank(mainId) || StringUtils.isBlank(attachSubClass) || StringUtils.isBlank(fileUrl)){
			return ;
		}
		try {
			StringBuffer sql = new StringBuffer("UPDATE dsc_tfile_attachment SET is_deleted = TRUE "
					+ " WHERE main_id = ?1 AND atattachsubclass = ?2 AND file_url = ?3 ");
			List<Object> parameters = Lists.newArrayList();
			parameters.add(mainId);
			parameters.add(attachSubClass);
			parameters.add(fileUrl);
			getRepository().createSqlQuery(sql.toString()).setParameters(parameters).executeUpdate();
		} catch (Exception e) {
			logger.error("逻辑删除附件异常，订单编号:{}, mainId:{},attachSubClass:{},fileUrl:{}",applyNo,mainId,attachSubClass,fileUrl,e);
		}
	}
	
	public static List<DscFileAttachment> getlogicAttachmentList(){
		String sql = "SELECT id FROM dsc_tfile_attachment WHERE is_deleted = TRUE ORDER BY create_time ASC LIMIT 1000 ";
		
		List<Object> resultList = getRepository().createSqlQuery(sql).list();
		if(CollectionUtils.isEmpty(resultList)){
			return Lists.newArrayList();
		}
		List<DscFileAttachment> fileList = Lists.newArrayList();
		for(Object result:resultList){
			String id = (String)result; //主键
			if(id == null){
				continue;
			}
			fileList.add(DscFileAttachment.get(DscFileAttachment.class, id));
		}
		return fileList;
	}
}
