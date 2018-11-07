package com.yixin.dsc.util;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yixin.kepler.core.constant.CommonConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StringUtils;

import java.util.HashMap;
import java.util.Map;

/**
 * 文件上传工具类
 *
 * @author YixinCapital -- chenjiacheng
 *         2018年08月23日 15:38
 **/

public class FileUploadUtils {


	private static final Logger logger = LoggerFactory.getLogger(FileUploadUtils.class);

	/**
	 * 上传文件至文件服务器
	 * @param array 字节数组
	 * @param fileName 文件名称
	 * @param fileUploadUrl 上传路径
	 * @return 文件id
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/23 15:43
	 */
	public static String upload(byte[] array, String fileName, String fileUploadUrl) {
		logger.info("开始进行上传,fileName={}", fileName);
		String fileId = null;
		try {
			Map<String, Object> map = new HashMap<>();
			map.put("fileSource", array);
			map.put("sourceFileName", fileName);
			String result = HttpUtils.sendPost(fileUploadUrl, JSON.toJSONString(map), CommonConstant.APPLICATION_CONTENTTYPE);
			logger.info("上传文件结束,result={}", result);
			if (StringUtils.hasText(result)) {
				JSONObject jsonObject = JSONObject.parseObject(result);
				fileId = JSONObject.parseObject(jsonObject.getString("data")).getString("fileId");
			}
		} catch (Exception e) {
			throw new RuntimeException(e);
		}
		return fileId;
	}

}
