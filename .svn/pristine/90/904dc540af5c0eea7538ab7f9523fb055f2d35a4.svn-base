package com.yixin.kepler.v1.mq;
import com.yixin.kepler.enity.AssetMainInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import com.yixin.kepler.core.mqmessage.AbstractMqMsgHandlerStrategy;
import com.yixin.kepler.core.mqmessage.MqMsgDealResult;
import com.yixin.kepler.enity.MQMsg;
import com.yixin.kepler.enity.OsbFileLog;
import com.yixin.kepler.v1.common.core.compress.CompressHandleEvent;
import com.yixin.kepler.v1.datapackage.dto.CompressHandleDTO;
import com.yixin.kepler.v1.utils.JacksonUtils;

import net.sf.json.JSONArray;
import net.sf.json.JSONObject;


/**
 * 压缩消息处理
 * Package : com.yixin.kepler.v1.mq
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年9月27日 下午6:13:46
 */
@Service
public class CompressMsgHandlerStrategy extends AbstractMqMsgHandlerStrategy {
	
	
    private Logger logger = LoggerFactory.getLogger(CompressMsgHandlerStrategy.class);

    @Value("${rabbitmq.compress.queue}")
    private String queue;
    
    @Autowired
    private ApplicationContext ioc;

    
	@Override
	protected void process(MQMsg msg) throws IllegalArgumentException {
		 logger.info("[压缩消息处理]处理mq消息开始，bzId：{}， MQMsg:{}", msg.getBzId(), JacksonUtils.fromObjectToJson(msg));
	     // 从队列获取osbFileLog
	     String compressTranNo = msg.getBzId();
	     OsbFileLog osbFileLog = OsbFileLog.getByCompressTranNo(compressTranNo);

	     if (osbFileLog == null) {
	     	return;
		 }

	     // 获取目标数据
	     String compressMsg = msg.getMsg();
	     String fileJson = osbFileLog.getFileJson();
	     // 转换目标数据
	     fileJson = replaceFileIds(fileJson, compressMsg);
	     // 更新数据入库
	     osbFileLog.setCompressFileJson(fileJson);
	     osbFileLog.update(); 
		 logger.info("[压缩消息处理]处理mq消息完成，bzId：{}", msg.getBzId());
		 // 对压缩后的文件进行处理
		 logger.info("[压缩处理]对压缩后的文件进行处理， bzId：{}, compressFile：{}", msg.getBzId(), fileJson);
		 asyncHandleResult(osbFileLog);
	}

	
	/**
     * 将原osbFileLog中的fileJson的fileId转换为消息里的targetFileId
     * eg：{
     * 		"originFileId":"group1/M01/2E/3B/wKiRIlrxSzLNZuMeAABQMz0rvxE184.jpg",
     * 		"targetFileId":"group1/M01/2E/3B/wKiRIlrxTUSpx45kAABQMz0rvxE256.jpg",
     * 		"compressInfo":null
     * 	   }
     * @param files 原fileJson
     * @param mqMsg 压缩服务返回消息
     * @return String
     * 				替换成压缩后的fileId的fileJson
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月27日 下午6:14:13
	 */
    private static String replaceFileIds(String files, String mqMsg) {
        if (StringUtils.isBlank(files)) return "";
        if (StringUtils.isBlank(mqMsg)) return files;
        JSONArray filesArray = JSONArray.fromObject(files);
        JSONObject mqMsgObject = JSONObject.fromObject(mqMsg);
        JSONArray mqMsgArray = (JSONArray) mqMsgObject.get("items");
        for (Object aFilesArray : filesArray) {
            JSONObject file = (JSONObject) aFilesArray;
            String fileId = file.getString("fileId");
            for (Object aMqMsgArray : mqMsgArray) {
                JSONObject msg = (JSONObject) aMqMsgArray;
                String originFileId = msg.getString("originFileId");
                String targetFileId = msg.getString("targetFileId");
                if (StringUtils.equals(fileId, originFileId)) {
                    file.put("fileId", targetFileId);
                    break;
                }
            }
        }
        return filesArray.toString();
    }
	
	
	@Override
	protected MqMsgDealResult dealWorkflow(MQMsg msg) {
		return null;
	}

	
	@Override
	public String getQueue() {
		logger.info("[压缩消息处理]获取队列名称， queue:{}", queue);
        return queue;
	}
	
	
	/**
	 * 异步处理压缩结果逻辑
	 * @param osbFileLog 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月27日 下午8:39:52
	 */
	private void asyncHandleResult(OsbFileLog osbFileLog) {
		CompressHandleDTO handleDTO = new CompressHandleDTO();

		AssetMainInfo mainInfo = AssetMainInfo.getByApplyNo(osbFileLog.getBzId());

		handleDTO.setAssetNo(mainInfo.getFinancialCode());
		handleDTO.setCompressFileJson(osbFileLog.getCompressFileJson());
		handleDTO.setCompressTranNo(osbFileLog.getCompressTranNo());
		handleDTO.setApplyNo(mainInfo.getApplyNo());
		handleDTO.setOsbFileLogId(osbFileLog.getId());
        ioc.publishEvent(new CompressHandleEvent(handleDTO));
	}
}
