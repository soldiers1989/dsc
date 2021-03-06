package com.yixin.web.controller;

import com.alibaba.fastjson.JSON;
import com.yixin.common.utils.BaseDTO;
import com.yixin.common.utils.InvokeResult;
import com.yixin.common.utils.Page;
import com.yixin.dsc.dto.own.RuleMaintainFieldDto;
import com.yixin.dsc.dto.own.RuleMaintainQueryDto;
import com.yixin.dsc.dto.own.RuleMaintainResultDto;
import com.yixin.dsc.dto.own.RuleMaintainRuleDetailDto;
import com.yixin.dsc.entity.rule.DscRuleDetail;
import com.yixin.dsc.service.common.DscMaintainCommonService;
import com.yixin.web.anocation.SysLog;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

/**
 * 规则维护控制器
 *
 * @author YixinCapital -- chenjiacheng
 *         2018年08月06日 14:38
 **/
@RestController
@RequestMapping(value = "/ruleMaintain")
public class RuleMaintainController {

	private static final Logger logger = LoggerFactory.getLogger(RuleMaintainController.class);

	@Resource
	private DscMaintainCommonService maintainCommonService;

	/**
	 * 规则维护查询列表
	 * @param queryDto 入参
	 * @return 查询列表页
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/6 14:51
	 */
	@RequestMapping(value = "/ruleQuery")
	public InvokeResult<Page<RuleMaintainResultDto>> ruleQuery(RuleMaintainQueryDto queryDto) {
		InvokeResult<Page<RuleMaintainResultDto>> invokeResult = new InvokeResult<>();
		logger.info("规则维护列表查询开始,params={}", JSON.toJSONString(queryDto));
		try {
			invokeResult.setData(maintainCommonService.ruleQuery(queryDto));
		} catch (Exception e) {
			logger.error("规则维护列表查询失败", e);
			return invokeResult.failure("规则维护列表查询失败");
		}
		logger.info("规则维护列表查询结束,result={}", JSON.toJSONString(invokeResult));
		return invokeResult;
	}

	/**
	 * 根据规则id获取规则详细信息
	 * @param ruleId 规则id
	 * @return 规则的详细信息
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/6 17:13
	 */
	@RequestMapping(value = "/getRuleDetails")
	public InvokeResult<Object> getRuleDetails(String ruleId) {
		InvokeResult<Object> invokeResult = new InvokeResult<>();
		if (StringUtils.isBlank(ruleId)) {
			return invokeResult.failure("规则id为空");
		}
		try {
			logger.info("根据规则id获取规则详细信息开始,ruleId={}",  ruleId);
			invokeResult.setData(maintainCommonService.getRuleDetails(ruleId));
		} catch (Exception e) {
			logger.error("根据规则id获取规则详细信息失败", e);
			return invokeResult.failure("根据规则id获取规则详细信息失败");
		}
		logger.info("根据规则id获取规则详细信息完成,ruleId={},result={}", ruleId, JSON.toJSONString(invokeResult));
		return invokeResult;
	}


	/**
	 * 根据规则id更新规则信息
	 * @param dto 入参
	 * @return 操作结果
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/10 10:27
	 */
	@SysLog(action = "根据规则id更新规则信息")
	@RequestMapping(value = "/saveOrUpdateRule")
	public InvokeResult<Object> saveOrUpdateRule( RuleMaintainQueryDto dto) {
		InvokeResult<Object> invokeResult = new InvokeResult<>();
		if (dto == null || StringUtils.isBlank(dto.getRuleId())) {
			return invokeResult.failure("入参为空");
		}
		try {
			invokeResult.setData(maintainCommonService.saveOrUpdateRule(dto));
		} catch (Exception e) {
			logger.error("根据规则id更新规则信息失败,ruleId={}", dto.getRuleId(), e);
			invokeResult.failure("更新规则信息失败,err:" + e.getMessage());
		}
		return invokeResult;
	}

	/**
	 * 为规则添加资方
	 * @param ruleId 规则id
	 * @param capitalId 资方id，多个资方以逗号相连  1,2,3
	 * @return 添加结果
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/7 9:55
	 */
	@SysLog(action = " 为规则添加资方")
	@RequestMapping(value = "/addCapital")
	public InvokeResult<Object> addCapital(String ruleId, String capitalId) {
		InvokeResult<Object> invokeResult = new InvokeResult<>();
		if (StringUtils.isBlank(ruleId) || StringUtils.isBlank(capitalId)) {
			return invokeResult.failure("规则id或资方id为空");
		}
		logger.info("为规则添加资方,ruleId={},capitalId={}", ruleId, capitalId);
		try {
			Boolean result = maintainCommonService.addCapital(ruleId, capitalId);
			invokeResult.setData(result);
			logger.info("为规则添加资方,ruleId={},capitalId={},result={}", ruleId, capitalId, result);
		} catch (Exception e) {
			logger.error("添加资方出错", e);
			return invokeResult.failure("添加资方出错");
		}
		return invokeResult;
	}

	/**
	 * 删除规则绑定资方
	 * @param ruleId 规则id
	 * @param capitalId 资方id
	 * @return 操作结果
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/7 11:10
	 */
	@SysLog(action = " 删除规则绑定资方")
	@RequestMapping(value = "/delCapital")
	public InvokeResult<Object> delCapital(String ruleId, String capitalId) {
		InvokeResult<Object> invokeResult = new InvokeResult<>();
		if (StringUtils.isBlank(ruleId) || StringUtils.isBlank(capitalId)) {
			return invokeResult.failure("规则id或资方id为空");
		}
		logger.info("删除规则绑定的资方,ruleId={},capitalId={}", ruleId, capitalId);
		try {
			Boolean result = maintainCommonService.delCapital(ruleId, capitalId);
			invokeResult.setData(result);
			logger.info("删除规则绑定的资方,ruleId={},capitalId={},result={}", ruleId, capitalId, result);
		} catch (Exception e) {
			logger.info("删除规则绑定的资方出错", e);
			invokeResult.failure("删除规则绑定的资方出错");
		}
		return invokeResult;
	}

	/**
	 * 获取field列表
	 * @return field列表
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/7 11:44
	 */
	@RequestMapping(value = "/getFieldList")
	public InvokeResult<Page<RuleMaintainFieldDto>> getFieldList(BaseDTO baseDTO) {
		InvokeResult<Page<RuleMaintainFieldDto>> invokeResult = new InvokeResult<>();
		try {
			invokeResult.setData(maintainCommonService.getFieldList(baseDTO));
		} catch (Exception e) {
			logger.error("获取field列表失败", e);
			invokeResult.failure("获取字段列表失败");
		}
		return invokeResult;
	}

	/**
	 * 绑定字段
	 * @param ruleId 规则id
	 * @param fields 字段id，多个字段以逗号相连
	 * @return 绑定结果
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/7 14:17
	 */
	@SysLog(action = "绑定字段")
	@RequestMapping(value = "/addField")
	public InvokeResult<Object> addField(String ruleId, String fields) {
		InvokeResult<Object> invokeResult = new InvokeResult<>();
		if (StringUtils.isBlank(ruleId) || StringUtils.isBlank(fields)) {
			return invokeResult.failure("规则id或字段id为空");
		}
		try {
			logger.info("绑定字段开始,ruleId={},fields={}", ruleId, fields);
			invokeResult.setData(maintainCommonService.addField(ruleId, fields));
		} catch (Exception e) {
			logger.error("绑定字段出错", e);
			invokeResult.failure("绑定字段出错");
		}
		return invokeResult;
	}

	/**
	 * 删除规则绑定的字段
	 * @param ruleId 规则id
	 * @param relationId 规则字段关系表id
	 * @return 操作结果
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/7 14:40
	 */

	@SysLog(action = "删除规则绑定的字段")
	@RequestMapping(value = "/delField")
	public InvokeResult<Object> delField(String ruleId,String relationId) {
		InvokeResult<Object> invokeResult = new InvokeResult<>();
		if (StringUtils.isBlank(relationId)) {
			return invokeResult.failure("规则id或字段id为空");
		}
		try {
			logger.info("删除规则绑定的字段,relationId={},ruleId={}", relationId, ruleId);
			invokeResult.setData(maintainCommonService.delField(ruleId,relationId));
		} catch (Exception e) {
			logger.error("删除规则绑定的字段出错", e);
			invokeResult.failure("删除规则绑定的字段出错");
		}
		return invokeResult;
	}

	/**
	 * 获取规则明细详情
	 * @param detailId 详情id
	 * @return 规则信息
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/7 15:08
	 */
	@RequestMapping(value = "/getRuleDetail")
	public InvokeResult<RuleMaintainRuleDetailDto> getRuleDetail(String detailId) {
		InvokeResult<RuleMaintainRuleDetailDto> invokeResult = new InvokeResult<>();
		if (StringUtils.isBlank(detailId)) {
			return invokeResult.failure("详情id为空");
		}
		try {
			DscRuleDetail detail = DscRuleDetail.get(DscRuleDetail.class, detailId);
			RuleMaintainRuleDetailDto detailDto = new RuleMaintainRuleDetailDto();
			detailDto.setId(detail.getId());
			detailDto.setCreateTime(detail.getCreateTime());
			detailDto.setUpdateTime(detail.getUpdateTime());
			detailDto.setMessage(detail.getMessage());
			detailDto.setRuleFormula(detail.getRuleFormula());
			detailDto.setRuleId(detail.getRuleId());
			invokeResult.setData(detailDto);
		} catch (Exception e) {
			logger.error("获取规则明细出错,detailId={}", detailId, e);
			invokeResult.failure("获取规则明细出错");
		}
		return invokeResult;
	}

	/**
	 * 添加或更新规则明细
	 * @param detailDto 入参
	 * @return 操作结果
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/7 15:32
	 */
	@SysLog(action = "添加或更新规则明细")
	@RequestMapping(value = "/saveOrUpdateRuleDetail")
	public InvokeResult<Object> saveOrUpdateRuleDetail(RuleMaintainRuleDetailDto detailDto) {
		InvokeResult<Object> invokeResult = new InvokeResult<>();
		try {
			logger.info("添加或更新规则明细,params={}", JSON.toJSONString(detailDto));
			invokeResult = maintainCommonService.saveOrUpdateRuleDetail(detailDto);
			logger.info("添加或更新规则明细,result={}", JSON.toJSONString(invokeResult));
		} catch (Exception e) {
			logger.error("添加或更新规则明细出错", e);
			invokeResult.failure("添加或更新规则明细出错");
		}
		return invokeResult;
	}

	/**
	 * 同步数据
	 * @param applyNo 申请编号
	 * @param values 字段值
	 * @return 同步结果
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/7 16:40
	 */
	@SysLog(action = "同步数据")
	@RequestMapping(value = "/syncData")
	public InvokeResult<Object> syncData(String applyNo, String values) {
		InvokeResult<Object> invokeResult = new InvokeResult<>();
		if (StringUtils.isBlank(applyNo) || StringUtils.isBlank(values)) {
			return invokeResult.failure("入参为空");
		}
		try {
			logger.info("同步字段数据,applyNo={},values={}", applyNo, values);
			invokeResult = maintainCommonService.syncData(applyNo, values);
		} catch (Exception e) {
			logger.error("同步数据出错", e);
			invokeResult.failure("同步数据出错");
		}
		return invokeResult;
	}

	/**
	 * 校验规则
	 * @param applyNo 申请编号
	 * @param ruleId 规则id
	 * @param ruleDetailId 规则明细id
	 * @param capitalId 资方id
	 * @return 校验结果
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/7 17:25
	 */
	@RequestMapping(value = "/checkRule")
	public InvokeResult<Object> checkRule(String applyNo, String ruleId, String ruleDetailId,String capitalId) {
		InvokeResult<Object> invokeResult = new InvokeResult<>();
		try {
			invokeResult = maintainCommonService.checkRule(applyNo, ruleId, ruleDetailId,capitalId);
		} catch (Exception e) {
			logger.error("校验规则失败,applyNo={},ruleId={}", applyNo, ruleId, e);
			invokeResult.failure(String.format("校验规则失败，原因:%s", e.getMessage()));
		}
		return invokeResult;
	}

}
