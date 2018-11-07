package com.yixin.kepler.v1.service.capital.icbc;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.kepler.core.attachment.OsbFileDomain;
import com.yixin.kepler.core.attachment.OsbFileUrl;
import com.yixin.kepler.core.attachment.SftpConfig;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.enity.OsbFileLog;
import com.yixin.kepler.v1.common.core.compress.CompressHandle;
import com.yixin.kepler.v1.datapackage.dto.CompressHandleDTO;

/**
 * 工行压缩结果处理
 * Package : com.yixin.kepler.v1.job
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年9月17日 下午5:31:21
 *
 */
@Service("ICBCCompressHandle")
public class ICBCCompressHandle implements CompressHandle {

	
	private static final Logger logger = LoggerFactory.getLogger(ICBCCompressHandle.class);
	
	
    @Autowired
    private OsbFileDomain osbFileDomain;
    
    
    @Autowired
    private SftpConfig sftpConfig;
	

	@Override
	public BaseMsgDTO handle(CompressHandleDTO handleDTO) {		
		logger.info("工行压缩结果处理开始，入参{}", JsonObjectUtils.objectToJson(handleDTO));
		try {
			OsbFileLog osbFileLog = OsbFileLog.get(OsbFileLog.class, handleDTO.getOsbFileLogId());
			String url = sftpConfig.getSftp().get(OsbFileDomain.ICBC_OSB_UPLOAD_HOST) + OsbFileUrl.ICBC_OSB_UPLOAD_URI;
			osbFileDomain.uploadOsbData(osbFileLog, osbFileDomain.getUploadData(osbFileLog), url);
			logger.info("工行压缩结果处理结束，调用osb服务-上传打包信息完成，applyNo：{}", handleDTO.getApplyNo());
		} catch (Exception e) {
			logger.info("工行压缩结果处理异常{}", e);
		}
	    return BaseMsgDTO.successData("执行成功");
	}
}
