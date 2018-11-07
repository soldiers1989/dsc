package com.yixin.kepler.core.domain.cmbc.util;

import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.common.utils.DateUitls;
import com.yixin.kepler.core.config.CMBCConfig;


import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Random;


public class CMBCUtil {

    /**
     * 机器号
     */
    private static final String MACHINE_NO = "YIX";
   

    /**
     * 日期格式
     */
    private static final String DATE_PATTERN = "yyyyMMddHHmmss";
    
    
    private static Map<String, Integer> statusMap = null;
    
    private static Map<String, String> statusMsgMap = null;
    
    static {
    	statusMap = new HashMap<String,Integer>(14){{
    		put("02", 0);
    		put("03", 1);
    		put("04", 0);
    		put("06", 0);
    		put("07", 0);
    		put("09", 0);
    		put("11", 0);
    		put("12", 3);//预审通过,终审中
    		put("13", 3);//终审中
    		put("17", 0);
    	}};
    	
    	statusMsgMap = new HashMap<String,String>(14){{
    		put("02", "合规未通过");
    		put("03", "终审已通过");
    		put("04", "连接异常");
    		put("06", "系统异常");
    		put("07", "弱担保失败");
    		put("09", "经销商不在白名单中");
    		put("11", "经销商状态异常");
    		put("13", "终审进行中");
    		put("17", "未签署征信");
    	}};
    }
    
    
    /**
     * 格式化金额
     */
    public static String formatAmt(String format,BigDecimal amount){
    	DecimalFormat decimalFormat = new DecimalFormat(format);
    	return decimalFormat.format(amount);
    }
    
    
    public static String getStatusMsg(String code){
    	if (!statusMsgMap.containsKey(code)) {
			return "未查询到状态错误描述";
    	}
    	return statusMsgMap.get(code);
    }
  
    public static int getStatus(String code){
    	if (!statusMap.containsKey(code)) {
			return 0;
    	}
    	return statusMap.get(code);
    }
    

    /**
     * <B>功能简述</B><br>
     * 生成请求方流水号
     * <p>
     * 要求全程唯一，用于后继交易确认、撤单、状态查询、结果通知、明细对账等场合（可以使用业务订单号），
     * 规则为：7位商户号+14交易时间（yyyyMMddHHmmss）+3位机器号+8位客户自定义序号
     *
     * @return
     * @date 2018/6/15 上午10:45
     * @author liuhongshen
     */
    public static synchronized String createReqSeq() {
    	
    	CMBCConfig cmbcConfig = SpringContextUtil.getApplicationContext().getBean(CMBCConfig.class);
    	
        return  cmbcConfig.getMerchantNum()+ DateUitls.dateToStr(new Date(), DATE_PATTERN)
                + MACHINE_NO + getRandom(8);
    }

    
    /**
     * 	1.流水号位数必须为32位，格式为23位+9位
	   	2.每一次请求流水号前23位必须唯一
		3.前23位从第6位开始到第13位为年月日，格式为yyyyMMdd。
		4.对于渠道方前13位遵循636YXyyyyMMdd标准(636YX是我行分配给渠道的标识)，
		后10位由渠道方保证序列号不重复(后续我行会增加校验)
		5.最后9位由渠道安方自行规划(对于我行后9位是用来区分一笔交易经过不同的行内系统的)
     */
    public static synchronized String getTradeNo() {
        return "636YX" + DateUitls.dateToStr(new Date(), "yyyyMMdd")
        	+ DateUitls.dateToStr(new Date(), "yyyyMMddHH")
        	+ getRandom(9);
    }
    
    
    
    public static String getRandom(int length){
    	Random random = new Random();
    	
    	StringBuilder sb = new StringBuilder();
    	
    	for (int i = 0; i < length; i++) {
    		sb.append(random.nextInt(10));
		}
    	return sb.toString();
    }
    
}
