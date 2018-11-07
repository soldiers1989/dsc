package com.yixin.dsc.service.shunt;

import java.util.List;
import java.util.Map;

import com.yixin.dsc.dto.DscAdmittanceReturnDto;
import com.yixin.dsc.dto.DscCapitalDto;
import com.yixin.dsc.dto.own.DscRuleVerifyResultDto;
import com.yixin.dsc.enumpackage.DscRuleTypeEnum;

/**
 * 准入规则校验
 * Package : com.yixin.dsc.service.shunt
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月7日 下午2:53:01
 *
 */
public interface DscShuntService {

	
	/**
	 * 准入规则校验
	 * @param applyNo 订单编号
	 * @param capitalId 资方ID
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年6月7日 下午3:08:31
	 */
	DscAdmittanceReturnDto capitalAccessCheck(String applyNo,String capitalId,String capitalCode);



	List<DscCapitalDto> capitalShuntMatch(List<DscCapitalDto> capitalList, String applyNo,Map<String,Object> sourceMap,String shuntId);

	Boolean capitalSupplyRuleMatch(DscCapitalDto dscCapitalDto, String applyNo, String ruleType,Map<String,Object> sourceMap);

	void capitalSupplyRuleMatch(DscAdmittanceReturnDto admittanceReturnDto, DscCapitalDto dscCapitalDto, String applyNo, String ruleType, Map<String,Object> sourceMap);


	/**
	 * 获取订单数据的来源
	 * @param applyNo 申请编号
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年7月23日 下午9:27:59
	 */
	Map<String,Object> getOrderDataSource(String applyNo);

	/**
	 * 资方规则校验
	 * @param capitalId 资方ID
	 * @param applyNo 申请编号
	 * @param ruleTypeEnum 规则类型
	 * @param sourceMap 数据来源
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年7月23日 下午9:25:26
	 */
	Boolean capitalRuleMatch(String capitalId, String applyNo, DscRuleTypeEnum ruleTypeEnum,Map<String,Object> sourceMap);
	
	
	/**
	 * 规则验证接口
	 * @param applyNo
	 * @param capitalCode
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年8月2日 下午5:09:05
	 */
	DscRuleVerifyResultDto ruleVerify(String applyNo,String capitalCode);
}
