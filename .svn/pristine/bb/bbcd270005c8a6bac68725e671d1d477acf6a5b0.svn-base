package com.yixin.kepler.core.attachment;/**
 * Created by liushuai2 on 2017/11/9.
 */

import java.util.HashMap;
import java.util.List;

import com.yixin.dsc.dto.DscSupplyDto;
import com.yixin.kepler.dto.BaseMsgDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;

import com.google.common.collect.Lists;
import com.yixin.common.exception.BzException;
import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.entity.order.DscFileAttachment;
import com.yixin.kepler.common.JacksonUtil;
import com.yixin.kepler.common.RestUtil;
import com.yixin.kepler.dto.CustomerDTO;
import com.yixin.kepler.dto.OsbAttachmentDTO;
import com.yixin.kepler.dto.fileserver.YxFileInfoDTO;
import com.yixin.kepler.dto.fileserver.YxFileUrlInfoDTO;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;



/**
 * Package : com.yixin.creditfront.application.domain.attachment
 *
 * @author YixinCapital -- liushuai2
 *         2017年11月09日 13:00
 */
public abstract class AttachmentDomain<T> {

    public static final Logger logger = LoggerFactory.getLogger(AttachmentDomain.class);
    /**
     * 文件服务通过url进行呢上传的地址
     */
    @Value("${spring.fileserver.urlupload}")
    public String FILESERVER_URLUPLOAD;
    protected ThreadLocal<T> inputDto = new ThreadLocal<>();

    public ThreadLocal<List<Attachment>> attachments = new ThreadLocal<>();

    public ThreadLocal<CustomerDTO> customerInfo = new ThreadLocal<>();
    public ThreadLocal<?> applyBase = new ThreadLocal<>();


    protected static final String FILEID = "fileId";
    protected static final String FILENAME = "fileName";

    String bankCode = "";

    /**
     * 获取BaseDTO信息
     *
     * @author YixinCapital -- wangdianxiang
     * 2016年12月5日 下午3:22:21
     */
    protected abstract void getData() throws BzException;

    public abstract void doIt(T inputDto) throws BzException;

    public abstract String getBankCode();
    
    public abstract InvokeResult<DscSupplyDto> checkAttachmentFile(OsbAttachmentDTO osbAttachmentDTO);

    /**
     * 通过url上传到文件平台，并按照顺序返回
     * @param attachments
     * @return
     */
    public List<DscFileAttachment> uploadFileByUrl(List<DscFileAttachment> attachments) {
        logger.info("将文件上传并重新设置");
        List<YxFileUrlInfoDTO> yxFileUrlInfoDTOS = Lists.newArrayList();
        for (DscFileAttachment attachment : attachments) {
            YxFileUrlInfoDTO yxFileUrlInfoDTO = new YxFileUrlInfoDTO();
            yxFileUrlInfoDTO.setFileName(attachment.getFileName());
            // TODO 这里使用新的数据结构
            yxFileUrlInfoDTO.setFileUrl(attachment.getFileUrl());
            yxFileUrlInfoDTOS.add(yxFileUrlInfoDTO);
        }

        String data = JSONArray.fromObject(yxFileUrlInfoDTOS).toString();
        
        logger.info("上传文件到文件平台的请求路径为{},数据为{}",FILESERVER_URLUPLOAD,data);
        String result = RestUtil.postJson(FILESERVER_URLUPLOAD, data);
        logger.info("上传文件到文件平台获取到的信息为{}",result);
        
        
        InvokeResult<List<HashMap<String, Object>>> invokeResult = (InvokeResult<List<HashMap<String, Object>>>) JacksonUtil.fromJsonToObject(result, InvokeResult.class);
        List<HashMap<String, Object>> fileInfoDTOS = (List<HashMap<String, Object>>) invokeResult.getData();
        for (int i = 0; i < fileInfoDTOS.size(); i++) {
            DscFileAttachment dscTfileAttachment = attachments.get(i);
            HashMap<String, Object> map = fileInfoDTOS.get(i);
            String fileId = (String) map.get("fileId");
            String attachmentName = dscTfileAttachment.getFileName();
            logger.info("源文件名称{},上传后的文件id：{}", attachmentName,fileId);
            dscTfileAttachment.setFileId(fileId);
            dscTfileAttachment.update();
        }
        return attachments;
    }
}
