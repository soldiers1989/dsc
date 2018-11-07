package com.yixin.dsc.service.common;

import java.math.BigDecimal;

/**
 * 微众银行通用方法处理接口
 * @author YixinCapital -- chenjiacheng
 *         2018年07月09日 17:34
 **/
public interface DscWbCommonService {


	/**
	 * 微众银行-通过申请编号获取该订单所对应的产品结构编号
	 * @param applyNo 申请编号
	 * @return 产品结构编号
	 * @author YixinCapital -- chenjiacheng
	 *             2018/7/9 17:37
	 */
	String getPsCodeByApplyNo(String applyNo);

	/**
	 * 通过订单来源获取对应的银行渠道字典码
	 * @param orderSource 订单来源  APP/PC
	 * @return 对应的银行渠道字典码   APP:04  PC:01
	 * @author YixinCapital -- chenjiacheng
	 *             2018/7/12 9:37
	 */
	String getWbChannelByOrderSource(String orderSource);

	/**
	 * 通过融资公司code获取银行对应的平台id
	 * @param companyCode 融资公司code
	 * @return 银行平台id
	 * @author YixinCapital -- chenjiacheng
	 *             2018/7/12 9:48
	 */
	String getMerchantIdByCompanyCode(String companyCode);
	
	/**
	 * 获取服务器IP
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年7月12日 下午1:45:44
	 */
	String getServerIp();
	
	/**
	 * 解析返回报文
	 * @param responseJson
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年7月12日 下午2:41:05
	 */
	String parseResponse(String responseJson);

	/**
	 * 获取微众银行对应的附件类型
	 * @param subClass dsc保存的材料类型
	 * @return 微众银行的附件类型
	 * @author YixinCapital -- chenjiacheng
	 *             2018/7/16 16:19
	 */
	String getAttrCode(String subClass);

	/**
	 * 获取alix与银行的码值映射
	 * @param code alix字段code
	 * @param value  alix字段码值
	 * @return 银行code
	 * @author YixinCapital -- chenjiacheng
	 *             2018/7/19 11:26
	 */
	String codeConvert(String code,String value);

	/**
	 * 将alix传来的值转换成银行需要的小数值
	 * eg: 30 -> 0.3,  11.88->0.1188
	 * @param source 原值
	 * @param type 类型
	 * @return 银行需要的小数值
	 * @author YixinCapital -- chenjiacheng
	 *             2018/7/19 11:29
	 */
	BigDecimal convertBigDecimal(BigDecimal source,String type);


	/**
	 * 对alix传来的车辆id进行切割
	 * @param catId 车辆id  alix传来的是四级  eg:1_2_3_4
	 * @return 4
	 * @author YixinCapital -- chenjiacheng
	 *             2018/7/20 16:58
	 */
	String getSpiltCarId(String catId);
}
