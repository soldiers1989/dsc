package com.yixin.kepler.v1.common.util;

import java.util.Date;
import java.util.Random;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import com.yixin.common.exception.BzException;
import com.yixin.common.utils.DateUitls;
import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.util.PropertiesManager;
import com.yixin.kepler.common.RestTemplateUtil;
import com.yixin.kepler.v1.datapackage.dto.QueryDTO;
import com.yixin.kepler.v1.utils.JacksonUtils;

@Component("serialNumberUtil")
public class SerialNumberUtil {


	private static final Logger logger = LoggerFactory.getLogger(SerialNumberUtil.class);
	
    /**
     * 日期格式-精确到微秒
     */
    private static final String DATE_FORMAT = "yyyyMMddHHmmsss";
    
    /**
     * 生产申请编号流水编号服务url
     */
    private static final String serialUrl = "/api/serialNumberService/generate";
    
    
	@Resource
	private PropertiesManager propertiesManager;


    
    /**
     * 生成20位流水号
     * 格式为：yyyyMMddHHmmsss + [5位任意数]
     * @return 
     * @author YixinCapital -- chen.lin
     *	       2018年9月11日 下午3:58:58
     */
    public static synchronized String getTranNo20() {
        String tranNo = DateUitls.dateToStr(new Date(), SerialNumberUtil.DATE_FORMAT) + "" + getRandom(5); 	 
    	logger.info("[Generate 20 bit serial numbers]tranNo={}", tranNo);
    	return tranNo;
    }

    
    /**
     * 获取生成的随机数
     * @param 要生成的位数
     * @return 生成后的字符串
     * @author YixinCapital -- chen.lin
     *	       2018年9月11日 下午3:41:11
     */
    public static String getRandom(int length){
    	Random random = new Random();
    	StringBuilder sb = new StringBuilder();
    	for (int i = 0; i < length; i++) {
    		sb.append(random.nextInt(10));
		}
    	logger.info("Random digit number is:{}, Random number：{}", length, sb.toString());
    	return sb.toString();
    }
    
    
    /**
     * 调用基础获取流水号
     * @param bizCode 
     * @param sysCode
     * @return
     * @throws BzException 
     * @author YixinCapital -- chen.lin
     *	       2018年9月11日 下午4:54:02
     */
    @SuppressWarnings("unchecked")
	public String getTranNo4Idfactory(String bizCode, String sysCode) throws BzException {
		// 交易流水号
		String tranNo = System.nanoTime() + "";
		try {
			Assert.notNull(bizCode, " bizCode is empty");
			Assert.notNull(sysCode, " sysCode is empty");
			QueryDTO queryDTO = new QueryDTO(bizCode, sysCode);
			String url = this.propertiesManager.getIdfactoryUrl() + serialUrl;
			logger.info("[调用基础获取申请编号流水号]请求开始，bizCode={}，sysCode={}，url={}", bizCode, sysCode, url);
			String response = RestTemplateUtil.post(url, queryDTO);
			InvokeResult<String> result =  (InvokeResult<String>) JacksonUtils.getObjectFromJson(response, InvokeResult.class, String.class);
			if (result == null || result.isHasErrors()) {
				logger.error("[调用基础获取申请编号流水号]失败，开始使用纳秒作为交易流水号tranNo：{}", tranNo);
			} else {
				tranNo = result.getData().toString();
				logger.info("[调用基础获取申请编号流水号]成功，tranNo：{}", tranNo);
			}
			return tranNo;
		} catch (Exception e) {
			logger.error("[调用基础获取申请编号流水号]失败，开始使用纳秒作为交易流水号tranNo：{}", tranNo);
		}
		return tranNo;
	}
}

