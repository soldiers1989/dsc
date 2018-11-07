package com.yixin.dsc.v1.service.capital.yntrust;

import java.util.Map;

import com.yixin.kepler.v1.datapackage.dto.other.LoanInfoDTO;

/**
 * 云南信托 公共service
 * Package : com.yixin.dsc.v1.service.capital.yntrust
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年9月19日 上午11:42:32
 *
 */
public interface YTCommonService {

	/**
	 * 获取云南信托接口中 标示请求唯一性的值
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年9月19日 上午11:43:26
	 */
	String getRequestId();

	/**
	 * 获取UniqueId
	 * @return
	 */
	String getUniqueId();
	
	/**
	 * 解析报文
	 * @param responseJson
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年9月19日 上午11:46:32
	 */
	String parseResponse(String responseJson);


	String getYnTrustFileType(String fileType);

	/**
	 * 通过alix居住城市code获取对应的城市code，若匹配不到，默认返回310115
	 * @param code alix传递的城市code
	 * @return 城市code
	 * @author YixinCapital -- chenjiacheng
	 *             2018/9/25 15:59
	 */
	String parseCityCode(String code);

	/**
	 * 通过四要素签约银行code映射云信需要的银行code/name
	 * @param bankCode code
	 * @return  云信需要的code ，匹配不到返回空map
	 * @author YixinCapital -- chenjiacheng
	 *             2018/9/26 14:08
	 */
	Map<String ,String> parseBankCode(String bankCode);

	/**
	 * 向结算推送订单放款信息
	 * @param loanInfo 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年10月16日 上午10:18:49
	 */
	void pushSettleLoanInfo(LoanInfoDTO loanInfo);
}
