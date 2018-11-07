package com.yixin.dsc.v1.service.capital.icbc;

import java.math.BigDecimal;

/**
 * 工商银行通用方法处理接口
 * Package : com.yixin.dsc_v1.service.capital.icbc
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年9月11日 上午10:02:48
 *
 */
public interface DscICBCCommonService {


	/**
	 * 工商银行-获取服务器IP
	 * @return 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月11日 上午10:04:29
	 */
	String getServerIp();
	
	
	/**
	 * 工商银行-解析报文
	 * @param applyNo
	 * @return 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月11日 上午10:03:16
	 */
	String parseResponse(String responseJson);
	
	
	/**
	 * 工商银行-获取调用方与银行的码值映射
	 * @param code
	 * @param value
	 * @return 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月11日 上午10:06:26
	 */
	String codeConvert(String code, String value);
	

	/**
	 * 工商银行-通过订单来源获取对应的字典码
	 * @param orderSource
	 * @return 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月11日 上午10:07:18
	 */
	String getWbChannelByOrderSource(String orderSource);


	/**
	 * 工商银行-获取附件
	 * @param subClass
	 * @return 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月11日 上午10:07:58
	 */
	String getAttrCode(String subClass);


	/** 工商银行-数值转换
	 * eg: 第三方传来的值转换成银行需要的小数值:30->0.3, 11.88->0.1188
	 * @param source
	 * @param type
	 * @return 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月11日 上午10:08:30
	 */
	BigDecimal convertBigDecimal(BigDecimal source, String type);
}
