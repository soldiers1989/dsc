package com.yixin.dsc.service.common;

import com.yixin.common.utils.BaseDTO;
import com.yixin.common.utils.InvokeResult;
import com.yixin.common.utils.Page;
import com.yixin.dsc.dto.own.RuleMaintainFieldDto;
import com.yixin.dsc.dto.own.RuleMaintainQueryDto;
import com.yixin.dsc.dto.own.RuleMaintainResultDto;
import com.yixin.dsc.dto.own.RuleMaintainRuleDetailDto;

import java.util.Map;

/**
 * dsc系统维护通用service
 * @author YixinCapital -- chenjiacheng
 *         2018年08月06日 14:41
 **/
public interface DscMaintainCommonService {

	/**
	 * 规则维护列表查询
	 * @param queryDto 入参
	 * @return 查询结果
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/6 15:48
	 */
	Page<RuleMaintainResultDto> ruleQuery(RuleMaintainQueryDto queryDto);


	/**
	 * 根据规则id获取规则详细信息
	 * @param ruleId 规则id
	 * @return 规则详细信息
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/6 17:20
	 */
	Map<String,Object> getRuleDetails(String ruleId);

	/**
	 * 添加资方
	 * @param ruleId 规则id
	 * @param capitalId 资方id
	 * @return 添加结果
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/7 10:02
	 */
	Boolean addCapital(String ruleId, String capitalId);

	/**
	 * 删除规则绑定的资方
	 * @param ruleId   规则id
	 * @param capitalId 资方id
	 * @return 操作结果
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/7 11:19
	 */
	Boolean delCapital(String ruleId, String capitalId);

	/**
	 * 分页获取字段列表
	 * @return 字段列表
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/7 11:48
	 */
	Page<RuleMaintainFieldDto> getFieldList(BaseDTO baseDTO);

	/**
	 * 绑定字段
	 * @param ruleId 规则id
	 * @param fields 字段id，多个字段以逗号相连
	 * @return 绑定结果
	 *  @author YixinCapital -- chenjiacheng
	 *             2018/8/7 14:18
	 */
	Boolean addField(String ruleId, String fields);

	/**
	 * 删除规则绑定的字段
	 * @param ruleId 规则id
	 * @param relationId 规则字段关系表id
	 * @return 操作结果
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/7 14:46
	 */
	Boolean delField(String ruleId, String relationId);

	/**
	 * 添加或更新规则明细
	 * @param detailDto 入参
	 * @return 操作结果
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/7 15:37
	 */
	InvokeResult<Object> saveOrUpdateRuleDetail(RuleMaintainRuleDetailDto detailDto);

	/**
	 * 同步字段数据
	 * @param applyNo 申请编号
	 * @param values 字段值
	 * @return 同步结果
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/7 16:48
	 */
	InvokeResult<Object> syncData(String applyNo, String values);

	/**
	 * 校验规则
	 * @param applyNo 申请编号
	 * @param ruleId 规则id
	 * @param ruleDetailId  规则详细id
	 * @return 校验结果
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/7 17:28
	 */
	InvokeResult<Object> checkRule(String applyNo, String ruleId, String ruleDetailId,String capitalId);

	/**
	 * 根据规则id更新规则信息
	 * @param dto 入参
	 * @return 更新结果
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/10 10:29
	 */
	Boolean saveOrUpdateRule(RuleMaintainQueryDto dto);
}
