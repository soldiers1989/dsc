package com.yixin.dsc.service.common;

import com.yixin.dsc.dto.DscAdmittanceDto;
import com.yixin.dsc.dto.DscCapitalSupportDto;
import com.yixin.dsc.dto.query.DscBankInteractiveResultDto;
import com.yixin.dsc.dto.query.DscMainInfoDto;
import com.yixin.dsc.dto.query.DscPaymentErrorDto;
import com.yixin.kepler.enity.SysDict;

import java.util.List;
import java.util.Map;

/**
 * 获取资方支持的数据信息service
 * @author YixinCapital -- chenjiacheng
 *         2018年07月05日 17:37
 **/
public interface DscQueryService {

	/**
	 * 根据数据类型获取资方支持的数据信息
	 * @param queryParam 入参
	 * @return 结果
	 * @author YixinCapital -- chenjiacheng
	 *             2018/7/5 17:43
	 */
	DscCapitalSupportDto getCapitalSupportData(DscAdmittanceDto queryParam);
	
	/**
	 * 银行放款状态查询
	 * @param applyNo 申请编号
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年7月30日 下午1:25:42
	 */
	Object loanStatusQuery(String applyNo);

	/**
	 * 根据code批量查询字典项
	 * 由alixCode->bankCode
	 * @param codes 入参，
	 *       Map<String,String> : key对应字典码类中的alixFiled,value对应字典码类中的alixCode,value可为空，表示只按照alixCode进行查询
	 * @param financeCode 资方,不用资方查询的结果集不同
	 * @return 字典项银行code
	 * @author YixinCapital -- chenjiacheng
	 *             2018/7/31 10:23
	 */
	List<SysDict> getItemCodes(List<Map<String,String>> codes, String financeCode);

	/**
	 * 通过alix字段值、code值获取对应的银行字典项
	 * @param dictList 字典项集合
	 * @param alixCode alix码值
	 * @param alixField alix字段值
	 * @return 对应的银行码值
	 * @author YixinCapital -- chenjiacheng
	 *             2018/7/31 12:35
	 */
	String getBankCode(List<SysDict> dictList, String alixCode, String alixField);

	/**
	 * 查询订单银行交互信息
	 * @param applyNo 申请编号
	 * @param phaseType 阶段
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年7月31日 下午1:39:22
	 */
	DscBankInteractiveResultDto queryBankInteractive(String applyNo, String phaseType);

	/**
	 * 统计一段时间内的订单量
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @return 订单量
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/23 14:25
	 */
	Integer countOrderNums(String beginTime, String endTime);

	/**
	 * 统计一段时间内的订单详情
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @return 订单详情
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/23 14:46
	 */
	List<DscMainInfoDto> queryMainInfoList(String beginTime, String endTime);

	/**
	 * 统计一段时间内请款失败的订单
	 * @param beginTime 开始时间
	 * @param endTime 结束时间
	 * @return 请款失败的订单详情
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/23 16:39
	 */
	List<DscPaymentErrorDto> queryPaymentErrorList(String beginTime, String endTime);

	/**
	 * 银行放款状态查询
	 * @param applyNo 申请编号
	 * @return
	 * @author YixinCapital -- wangwenlong
	 *	       2018年7月30日 下午1:25:42
	 */
	String getVenusNoByApplyNo(String applyNo);
}
