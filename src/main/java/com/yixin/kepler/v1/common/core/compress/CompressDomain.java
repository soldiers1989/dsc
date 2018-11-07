package com.yixin.kepler.v1.common.core.compress;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import com.google.common.collect.Lists;
import com.yixin.common.exception.BzException;
import com.yixin.common.utils.InvokeResult;
import com.yixin.kepler.common.JacksonUtil;
import com.yixin.kepler.common.RestTemplateUtil;
import com.yixin.kepler.common.RestUtil;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.dto.fileserver.YxFileUrlInfoDTO;
import com.yixin.kepler.enity.OsbFileLog;
import com.yixin.kepler.v1.common.util.SerialNumberUtil;
import com.yixin.kepler.v1.datapackage.dto.CompressHandleDTO;
import com.yixin.kepler.v1.datapackage.dto.CompressReqItemDTO;
import com.yixin.kepler.v1.utils.JacksonUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
/**
 * 
 * Package : com.yixin.kepler.v1.common.core.compress
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年10月9日 下午2:09:43
 *
 */
@Component
public class CompressDomain {
	
    private static Logger logger = LoggerFactory.getLogger(CompressDomain.class);

    static final String FILEID = "fileId";
    
    static final String FILENAME = "fileName";
    
    /**
     * 文件服务地址
     */
    @Value("${spring.fileserver.urlupload}")
    private String FILES_ERVER_URL;
    
    /**
     * 压缩服务地址
     */
    @Value("${compress.url}")
    private String compressUrl;
    
    /**
     * 压缩服务mq，routingkey
     */
    @Value("${rabbitmq.compress.routing.key}")
    private String compressRoutingKey;

    
    /**
     * [执行压缩]对于原文件url进行压缩
     * 步骤：
     * 			1.上传文件服务
     * 			2.获取fileId后组装数据
     * 			3.开始压缩
     * 			4.更新OsbfileLog表，FileJson、压缩tranNo
     * @param rawData  原文件数据
     * @param dto [applyNo 申请编号，osbFileLogId osbFileLog的ID]
     * @param scale	   压缩比
     * @param maxSize  压缩后大小 [单位KB]
     * @return 
     * 		受理成功：返回压缩流水	受理失败：返回“100001”
     * @author YixinCapital -- chen.lin
     *	       2018年9月26日 下午6:16:25
     */
    public String compressExe4Raw(List<YxFileUrlInfoDTO> rawData, CompressHandleDTO dto, double scale, long maxSize) {
        try {
        	logger.info("[执行压缩]对于原文件url进行压缩applyNo：{}，fileLogId：{}， scale：{}， maxSize：{}", dto.getApplyNo(), dto.getOsbFileLogId(), scale, maxSize);
        	// 获取OsbfileLog信息
        	OsbFileLog log = OsbFileLog.getByFileLogId(dto.getOsbFileLogId());
    		if (log == null) {
            	logger.error("获取OsbfileLog信息为空，applyNo={}", dto.getApplyNo());
            	throw new BzException("申请编号错误，获取OsbfileLog信息为空");
    		}
        	// 校验数据
    		checkParmData(dto, scale, maxSize);
        	// 上传文件服务
        	String fileData = JSONArray.fromObject(rawData).toString();
        	logger.info("[上传文件]请求路径为：{}， 数据为：{}", FILES_ERVER_URL, fileData);
        	String fileResult = RestUtil.postJson(FILES_ERVER_URL, fileData);
        	// 组装数据
        	List<CompressReqItemDTO> items = new ArrayList<>();
        	List<Map<String, String>> fileLogList = Lists.newArrayList();
        	InvokeResult<List<HashMap<String, Object>>> invokeResult = (InvokeResult<List<HashMap<String, Object>>>) JacksonUtil.fromJsonToObject(fileResult, InvokeResult.class);
        	List<HashMap<String, Object>> attList = (List<HashMap<String, Object>>) invokeResult.getData();
        	for (int i = 0; i < attList.size(); i++) {
        		CompressReqItemDTO itemDTO = new CompressReqItemDTO();
                Map<String, String> fileLog = new HashMap<>();
        		// 获取fileName
        		YxFileUrlInfoDTO fileInfo = rawData.get(i);
                String fileName = fileInfo.getFileName();
        		// 获取fileId
        		HashMap<String, Object> map = attList.get(i);
        		String fileId = (String) map.get("fileId");
        		// 组装fileLogJosn
        		fileLog.put(FILEID, fileId);
        		fileLog.put(FILENAME, fileName);
        		fileLogList.add(fileLog);
        		// 组装压缩请求项
        		itemDTO.setFileId(fileId);
        		itemDTO.setMaxSize(maxSize);
        		itemDTO.setScale(scale);
        		items.add(itemDTO);
        	}
        	JSONObject data = new JSONObject();
        	data.put("applyNo", dto.getApplyNo());
        	data.put("items", JSONArray.fromObject(items).toString()); // rap文档[(id: 3958)]错误  fileIds 修改为 items
        	data.put("routingKey", compressRoutingKey);
        	String tranNo = SerialNumberUtil.getTranNo20();
        	data.put("tranNo", tranNo);
        	// 开始压缩
        	logger.info("[压缩处理]REST请求压缩服务，applyNo：{}, data：{}", dto.getApplyNo(), JacksonUtils.fromObjectToJson(data));
        	String result = RestTemplateUtil.post(compressUrl, data);
        	logger.info("[压缩处理]REST请求压缩服务返回，applyNo：{}, result：{}", dto.getApplyNo(), JacksonUtils.fromObjectToJson(result));
        	// 返回压缩受理结果
        	if (!StringUtils.isEmpty(result)) {
        		JSONObject resultJsonObj = JSONObject.fromObject(result);
        		if(resultJsonObj.getBoolean("success")){
        			log.setFileJson(JSONArray.fromObject(fileLogList).toString());
        			log.setCompressTranNo(tranNo);
        			log.update();
        			return tranNo;
        		}
        	}
        	return CommonConstant.FAILURE;
		} catch (Exception e) {
        	logger.error("[执行压缩]对于原文件url进行压缩异常applyNo：{}, e：{}", dto.getApplyNo(), e);
        	return CommonConstant.FAILURE;
		}
    }
  

    /**
     * [执行压缩]对OsbfileLog表已经存入对应的FileJson进行压缩
     * 步骤：
     * 			1.获取OsbfileLog的FileJson
     * 			2.开始压缩
     * 			3.更新OsbfileLog表，压缩tranNo
     * @param dto [applyNo 申请编号，osbFileLogId osbFileLog的ID]
     * @param applyNo  申请编号
     * @param scale	   压缩比
     * @param maxSize  压缩后大小 [单位KB]
     * @return 
     * 		受理成功：返回压缩流水	受理失败：返回“100001”
     * @author YixinCapital -- chen.lin
     *	       2018年9月26日 下午6:16:25
     */
    public String compressExe4FileId(CompressHandleDTO dto, double scale, long maxSize) {
        try {
        	logger.info("[执行压缩]对OsbfileLog表已经存入对应的FileJson进行压缩applyNo：{}，fileLogId：{}， scale：{}， maxSize：{}", dto.getApplyNo(), dto.getOsbFileLogId(), scale, maxSize);
        	// 获取OsbfileLog信息
        	OsbFileLog log = OsbFileLog.getByFileLogId(dto.getOsbFileLogId());
    		if (log == null || log.getFileJson() == null) {
            	logger.error("获取OsbfileLog信息为空，applyNo={}", dto.getApplyNo());
            	throw new BzException("申请编号错误，获取OsbfileLog信息为空");
    		}
        	// 校验数据
    		this.checkParmData(dto, scale, maxSize);
    		// 组装数据
        	List<CompressReqItemDTO> items = new ArrayList<>();
        	JSONArray filesArray = JSONArray.fromObject(log.getFileJson());
        	for (Object aMqMsgArray : filesArray) {
        		CompressReqItemDTO itemDTO = new CompressReqItemDTO();
                JSONObject file = (JSONObject) aMqMsgArray;
                String fileId = file.getString(FILEID);
                String fileName = file.getString(FILENAME);
        		// 组装压缩请求项
        		itemDTO.setFileId(fileId);
        		itemDTO.setMaxSize(maxSize);
        		itemDTO.setScale(scale);
        		items.add(itemDTO);
			}
        	JSONObject data = new JSONObject();
        	data.put("applyNo", dto.getApplyNo());
        	data.put("items", JSONArray.fromObject(items).toString()); // rap文档[(id: 3958)]错误  fileIds 修改为 items
        	data.put("routingKey", compressRoutingKey);
        	String tranNo = SerialNumberUtil.getTranNo20();
        	data.put("tranNo", tranNo);
        	// 开始压缩
        	logger.info("[压缩处理]REST请求压缩服务，applyNo：{}, data：{}", dto.getApplyNo(), JacksonUtils.fromObjectToJson(data));
        	String result = RestTemplateUtil.post(compressUrl, data);
        	logger.info("[压缩处理]REST请求压缩服务返回，applyNo：{}, result：{}", dto.getApplyNo(), JacksonUtils.fromObjectToJson(result));
        	// 返回压缩受理结果
        	if (!StringUtils.isEmpty(result)) {
        		JSONObject resultJsonObj = JSONObject.fromObject(result);
        		if(resultJsonObj.getBoolean("success")){
        			log.setCompressTranNo(tranNo);
        			log.update();
        			return tranNo;
        		}
        	}
        	return CommonConstant.FAILURE;
		} catch (Exception e) {
        	logger.error("[执行压缩]对OsbfileLog表已经存入对应的FileJson进行压缩applyNo：{}, e：{}", dto.getApplyNo(), e);
        	return CommonConstant.FAILURE;
		}
    }


    /**
     * 校验入参数据
     * @param dto [applyNo 申请编号，osbFileLogId osbFileLog的ID]
     * @param scale	   压缩比
     * @param maxSize  压缩后最大值
     * @author YixinCapital -- chen.lin
     *	       2018年9月29日 下午3:23:35
     */
	private void checkParmData(CompressHandleDTO dto, double scale, long maxSize) {
		if (StringUtils.isEmpty(dto.getApplyNo())) {
        	logger.error("[压缩校验]校验失败：申请编号为空");
        	throw new BzException("[压缩校验]校验失败：申请编号为空");
		}
		if (StringUtils.isEmpty(dto.getOsbFileLogId())) {
        	logger.error("[压缩校验]校验失败：osbFileLog的ID为空，applyNo={}", dto.getApplyNo());
        	throw new BzException("[压缩校验]校验失败：osbFileLog的ID为空");
		}
		if (StringUtils.isEmpty(scale)) {
        	logger.error("[压缩校验]校验失败：压缩比为空，applyNo={}", dto.getApplyNo());
        	throw new BzException("[压缩校验]校验失败：压缩比为空");
		}
		if (StringUtils.isEmpty(maxSize)) {
        	logger.error("[压缩校验]校验失败：压缩后最大值为空，applyNo={}", dto.getApplyNo());
        	throw new BzException("[压缩校验]校验失败：压缩后最大值为空");
		}
	}
    
}
