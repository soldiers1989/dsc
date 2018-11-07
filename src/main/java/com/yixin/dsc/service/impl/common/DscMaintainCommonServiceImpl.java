package com.yixin.dsc.service.impl.common;

import com.alibaba.fastjson.JSON;
import com.yixin.common.system.domain.BaseEntity;
import com.yixin.common.system.domain.SqlQuery;
import com.yixin.common.system.querychannel.QueryChannelService;
import com.yixin.common.utils.BaseDTO;
import com.yixin.common.utils.InvokeResult;
import com.yixin.common.utils.Page;
import com.yixin.dsc.assembler.DscRuleMaintainAssembler;
import com.yixin.dsc.assembler.DscSalesApplyFinancingInfoAssembler;
import com.yixin.dsc.assembler.DscSalesApplyRiskAssembler;
import com.yixin.dsc.dto.DscCapitalDto;
import com.yixin.dsc.dto.order.DscSalesApplyFinancingInfoDTO;
import com.yixin.dsc.dto.order.DscSalesApplyRiskInfoDTO;
import com.yixin.dsc.dto.own.RuleMaintainFieldDto;
import com.yixin.dsc.dto.own.RuleMaintainQueryDto;
import com.yixin.dsc.dto.own.RuleMaintainResultDto;
import com.yixin.dsc.dto.own.RuleMaintainRuleDetailDto;
import com.yixin.dsc.dto.rule.DscRuleDto;
import com.yixin.dsc.dto.rule.engine.BatchMatchResult;
import com.yixin.dsc.entity.capital.DscCapitalRuleRelation;
import com.yixin.dsc.entity.field.DscField;
import com.yixin.dsc.entity.order.*;
import com.yixin.dsc.entity.rule.DscRule;
import com.yixin.dsc.entity.rule.DscRuleDetail;
import com.yixin.dsc.entity.rule.DscRuleFieldRelation;
import com.yixin.dsc.enumpackage.DscRuleTypeEnum;
import com.yixin.dsc.service.common.DscMaintainCommonService;
import com.yixin.dsc.service.rule.engine.RuleService;
import com.yixin.kepler.enity.AssetProductFinancialRel;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.*;

/**
 * dsc系统维护通用实现类
 * @author YixinCapital -- chenjiacheng
 *         2018年08月06日 15:49
 **/
@Service
public class DscMaintainCommonServiceImpl implements DscMaintainCommonService {

	private static final Logger logger = LoggerFactory.getLogger(DscMaintainCommonServiceImpl.class);

	@Resource
	private QueryChannelService queryChannelService;

	@Resource
	private RuleService ruleService;

	@SuppressWarnings("unchecked")
	@Override
	public Page<RuleMaintainResultDto> ruleQuery(RuleMaintainQueryDto queryDto) {
		StringBuilder sql = new StringBuilder();
		//sql.append("select rule.id id,rule.rule_name ruleName,rule.rule_type ruleType,rule.rule_matching_type matchType,");
		//sql.append("capital.capitalName,rule.is_deleted deleted,capital.financeCodeNums,details.detailNums ");
		//sql.append("from dsc_rule rule,dsc_rule_detail ruleDetail,");
		//sql.append("(select GROUP_CONCAT(finance.`financial_code`) capitalName,relation.rule_id,COUNT(finance.`financial_code`) financeCodeNums ");
		//sql.append(" from dsc_capital_rule_relation relation,k_asset_settle_info finance");
		//sql.append(" where relation.capital_id = finance.financial_id AND relation.is_deleted=0 AND finance.is_deleted=0 GROUP BY relation.rule_id");
		//sql.append(") capital,");
		//sql.append("(select count(detail.id) detailNums,rules.id idb from dsc_rule_detail detail,dsc_rule rules ");
		//sql.append(" where detail.rule_id = rules.id AND detail.is_deleted=0 AND rules.is_deleted=0 GROUP BY detail.rule_id");
		//sql.append(") details ");
		//sql.append("where rule.id = ruleDetail.rule_id and rule.id = capital.rule_id and details.idb = rule.id ");
		//sql.append(" AND rule.is_deleted=0 AND ruleDetail.is_deleted=0 ");
		sql.append("SELECT rule.id id,rule.rule_name ruleName, rule.rule_type ruleType, rule.rule_matching_type matchType," +
				" capital.capitalName, rule.is_deleted deleted, capital.financeCodeNums, details.detailNums " +
				" FROM dsc_rule rule " +
				" LEFT JOIN dsc_rule_detail ruleDetail ON rule.id = ruleDetail.rule_id " +
				" AND ruleDetail.is_deleted = 0 " +
				" LEFT JOIN (" +
				"   SELECT GROUP_CONCAT(finance.`financial_code`) capitalName," +
				"   relation.rule_id, COUNT(finance.`financial_code`) financeCodeNums " +
				"   FROM dsc_capital_rule_relation relation, k_asset_settle_info finance " +
				"   WHERE relation.capital_id = finance.financial_id " +
				"   AND relation.is_deleted = 0 AND finance.is_deleted = 0 " +
				"   GROUP BY relation.rule_id " +
				" ) capital ON rule.id = capital.rule_id " +
				" LEFT JOIN ( " +
				"   SELECT count(detail.id) detailNums, rules.id idb " +
				"   FROM  dsc_rule_detail detail, dsc_rule rules" +
				"   WHERE  detail.rule_id = rules.id " +
				"   AND detail.is_deleted = 0 AND rules.is_deleted = 0 " +
				"   GROUP BY detail.rule_id" +
				" ) details ON details.idb = rule.id " +
				" WHERE 1=1 ");
		if (StringUtils.isNotBlank(queryDto.getRuleName())) {
			sql.append(" and rule.rule_name like '%").append(queryDto.getRuleName()).append("%' ");
		}
		if (StringUtils.isNotBlank(queryDto.getRuleType())) {
			sql.append(" and rule.rule_type = '").append(queryDto.getRuleType()).append("' ");
		}
		if (StringUtils.isNotBlank(queryDto.getMatchType())) {
			sql.append(" and rule.rule_matching_type = '").append(queryDto.getMatchType()).append("' ");
		}
		if (StringUtils.isNotBlank(queryDto.getRuleId())) {
			sql.append(" and rule.id = '").append(queryDto.getRuleId()).append("' ");
		}
		if (StringUtils.isNotBlank(queryDto.getCapitalName())) {
			sql.append(" and capital.capitalName = '").append(queryDto.getCapitalName()).append("' ");
		}
		if (StringUtils.isNotBlank(queryDto.getIsValid())) {
			sql.append(" and rule.is_deleted = ").append(queryDto.getIsValid());
		}
		logger.info("规则维护列表查询,sql={}", sql.toString());
		Page<Object[]> page = queryChannelService.createSqlQuery(sql.toString()).setPage(queryDto.getCurrent() - 1, queryDto.getRowCount()).pagedList();
		Page<RuleMaintainResultDto> resultDtoPage = new Page(page.getStart(), page.getResultCount(), page.getPageSize(), DscRuleMaintainAssembler.toRuleList(page.getData()));
		return resultDtoPage;
	}


	@SuppressWarnings("unchecked")
	@Override
	public Map<String, Object> getRuleDetails(String ruleId) {
		Map<String, Object> dataMap = new LinkedHashMap<>();
		//=========规则信息=========== //
		DscRule rule = DscRule.get(DscRule.class, ruleId);
		dataMap.put("ruleDetail", DscRuleMaintainAssembler.toRuleDto(rule));

		//=========资方列表=========== //
		List<DscCapitalRuleRelation> capitalRuleRelationList = DscCapitalRuleRelation.getListByParms(ruleId, null);
		dataMap.put("capitalList", DscRuleMaintainAssembler.toCapitalList(capitalRuleRelationList));

		//=========字段列表=========== //
		StringBuilder fieldSql = new StringBuilder();
		fieldSql.append("select f.id,f.field_code,f.field_name,f.field_get_method,f.field_type,f.field_source,relation.sequence,relation.id relationId");
		fieldSql.append(" from dsc_field f,dsc_rule_field_relation relation ");
		fieldSql.append(" where relation.is_deleted=0 and f.is_deleted=0 and f.field_code = relation.field_code and relation.rule_id='")
				.append(ruleId)
				.append("' ");
		fieldSql.append(" order by relation.rule_id,relation.sequence");
		List<Object[]> fieldList = queryChannelService.createSqlQuery(fieldSql.toString()).list();
		dataMap.put("fieldList", DscRuleMaintainAssembler.toFieldList(fieldList));

		//=========明细列表=========== //
		List<DscRuleDetail> ruleDetailList = DscRuleDetail.getListByRuleId(ruleId);
		dataMap.put("detailList", DscRuleMaintainAssembler.toRuleDetailList(ruleDetailList));

		logger.info("根据规则id查询规则明细,ruleId={},result={}", ruleId, JSON.toJSONString(dataMap));
		return dataMap;
	}

	@Override
	@Transactional
	public Boolean addCapital(String ruleId, String capitalId) {
		String[] capitalIds = capitalId.split(",");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("deleted", false);
		paramMap.put("ruleId", ruleId);
		for (String str : capitalIds) {
			paramMap.put("capitalId", str);
			DscCapitalRuleRelation relation = DscCapitalRuleRelation.findFirstByProperties(DscCapitalRuleRelation.class, paramMap);
			if (relation == null) {
				DscCapitalRuleRelation newRelation = new DscCapitalRuleRelation();
				newRelation.setCapitalId(str);
				newRelation.setRuleId(ruleId);
				newRelation.create();
			}

		}
		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public Boolean delCapital(String ruleId, String capitalId) {
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("deleted", false);
		paramMap.put("ruleId", ruleId);
		paramMap.put("capitalId", capitalId);
		DscCapitalRuleRelation relation = DscCapitalRuleRelation.findFirstByProperties(DscCapitalRuleRelation.class, paramMap);
		if (relation != null) {
			relation.logicRemove();
		}
		return Boolean.TRUE;
	}

	@SuppressWarnings("unchecked")
	@Override
	public Page<RuleMaintainFieldDto> getFieldList(BaseDTO baseDTO) {
		Page<DscField> page = queryChannelService.createJpqlQuery("from DscField field where field.deleted=?1")
				.setParameters(false)
				.setPage(baseDTO.getCurrent()-1,baseDTO.getRowCount())
				.pagedList();
		return new Page<>(page.getStart(), page.getResultCount(), page.getPageSize(), DscRuleMaintainAssembler.toFieldList2(page.getData()));
	}

	@Override
	@Transactional
	public Boolean addField(String ruleId, String fields) {
		String[] field = fields.split(",");
		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("deleted", false);
		paramMap.put("ruleId", ruleId);
		for (String str : field) {
			DscField dscField = DscField.get(DscField.class, str);
			if (dscField == null) continue;
			paramMap.put("fieldCode", dscField.getFieldCode());
			DscRuleFieldRelation relation = DscRuleFieldRelation.findFirstByProperties(DscRuleFieldRelation.class, paramMap);
			if (relation == null) {
				relation = new DscRuleFieldRelation();
				Integer max = DscRuleFieldRelation.getRepository()
						.getSingleResult(new SqlQuery(DscRuleFieldRelation.getRepository(),
								"select max(r.sequence) from dsc_rule_field_relation r where r.is_deleted=0 and r.rule_id='"+ ruleId +"'"
						));
				relation.setFieldCode(dscField.getFieldCode());
				relation.setRuleId(ruleId);
				relation.setSequence(max == null ? 1 : max + 1);
				relation.create();
			}
		}
		return Boolean.TRUE;
	}

	@SuppressWarnings("unchecked")
	@Override
	@Transactional
	public Boolean delField(String ruleId, String relationId) {
		DscRuleFieldRelation relation = DscRuleFieldRelation.get(DscRuleFieldRelation.class, relationId);
		relation.logicRemove();
		//对剩下的字段进行重新排序
		StringBuilder jpql = new StringBuilder("select relation from DscRuleFieldRelation relation where relation.deleted=false and ");
		jpql.append(" relation.ruleId=?1 and relation.sequence>= ?2 ");
		List<Object> conditions = new ArrayList<>();
		conditions.add(relation.getRuleId());
		conditions.add(relation.getSequence());
		List<DscRuleFieldRelation> dataList = queryChannelService.createJpqlQuery(jpql.toString()).setParameters(conditions).list();
		if (CollectionUtils.isNotEmpty(dataList)) {
			for (DscRuleFieldRelation fieldRelation : dataList) {
				fieldRelation.setSequence(fieldRelation.getSequence() - 1);
				fieldRelation.update();
			}
		}

		return Boolean.TRUE;
	}

	@Override
	@Transactional
	public InvokeResult<Object> saveOrUpdateRuleDetail(RuleMaintainRuleDetailDto detailDto) {
		InvokeResult<Object> invokeResult = new InvokeResult<>();
		if (StringUtils.isBlank(detailDto.getId())) {
			//新增
			if (StringUtils.isBlank(detailDto.getRuleId())) {
				return invokeResult.failure("规则id为空");
			}
			if (StringUtils.isBlank(detailDto.getMessage())) {
				return invokeResult.failure("message为空");
			}
			if (StringUtils.isBlank(detailDto.getRuleFormula())) {
				return invokeResult.failure("规则信息为空");
			}
			DscRuleDetail detail = new DscRuleDetail();
			detail.setMessage(detailDto.getMessage());
			detail.setRuleFormula(detailDto.getRuleFormula());
			detail.setRuleId(detailDto.getRuleId());
			detail.create();
		} else {
			//更新
			DscRuleDetail detail = DscRuleDetail.get(DscRuleDetail.class, detailDto.getId());
			if (detail != null) {
				if (StringUtils.isNotBlank(detailDto.getMessage())) {
					detail.setMessage(detailDto.getMessage());
				}
				if (StringUtils.isNotBlank(detailDto.getRuleFormula())) {
					detail.setRuleFormula(detailDto.getRuleFormula());
				}
				detail.update();
			}
		}
		return invokeResult.success();
	}


	@Override
	@Transactional
	public InvokeResult<Object> syncData(String applyNo, String values) {
		InvokeResult<Object> invokeResult = new InvokeResult<>();
		String[] valuesArray = values.split(",");
		for (String everyValue : valuesArray) {
			if (!everyValue.matches("^\\w+##\\w+##\\w+$")) {
				return invokeResult.failure("字段值格式不正确，无法解析");
			}
		}
		DscSalesApplyMain main = DscSalesApplyMain.getByApplyNo(applyNo);
		for (String val : valuesArray) {
			String[] data = val.split("##");
			StringBuilder sql = new StringBuilder();
			if (data[0].contains("main")) {
				sql.append("update ").append(data[0]).append(" c set c.").append(data[1])
						.append(" = '").append(data[2]).append("' ")
						.append(" where c.id= '").append(main.getId()).append("'");
				logger.info("同步字段数据,执行更新,sql={}", sql.toString());
				BaseEntity.getRepository().executeUpdate(new SqlQuery(BaseEntity.getRepository(), sql.toString()));
			} else {
				sql.append("update ").append(data[0]).append(" c set c.").append(data[1])
						.append(" = '").append(data[2]).append("' ")
						.append(" where c.main_id='").append(main.getId()).append("'");
				logger.info("同步字段数据,执行更新,sql={}", sql.toString());
				BaseEntity.getRepository().executeUpdate(new SqlQuery(BaseEntity.getRepository(), sql.toString()));
			}
		}
		invokeResult.setData("同步成功");
		return invokeResult;
	}

	@Override
	public InvokeResult<Object> checkRule(String applyNo, String ruleId, String ruleDetailId,String capitalId) {
		InvokeResult<Object> invokeResult = new InvokeResult<>();
		logger.info("规则验证接口-遍历资方列表准入规则匹配-开始。 订单编号:{}",applyNo);
		Map<String,Object> sourceMap = this.getOrderDataSource(applyNo); //属性取值来源
		BatchMatchResult batchMatchResult = this.capitalShuntMatch(ruleId,ruleDetailId,capitalId,sourceMap);
		logger.info("规则验证接口-遍历资方列表准入规则匹配-结束。 订单编号:{}，匹配结果：{}", applyNo, JSON.toJSONString(batchMatchResult));
		if (batchMatchResult != null && batchMatchResult.isFlag()) {
			return invokeResult.success();
		} else {
			return invokeResult.failure(JSON.toJSONString(batchMatchResult));
		}
	}

	@Override
	@Transactional
	public Boolean saveOrUpdateRule(RuleMaintainQueryDto dto) {
		DscRule rule = DscRule.get(DscRule.class, dto.getRuleId());
		if (rule == null) {
			//新增
			StringBuilder sql = new StringBuilder();
			sql.append(" insert into dsc_rule(id,create_time,creator_id,rule_matching_type,rule_name,rule_type,version,is_deleted)");
			sql.append(" values( ").append("'").append(dto.getRuleId()).append("',").append("sysdate(),");
			sql.append("'").append(dto.getCurrentUser() == null ? "" : dto.getCurrentUser().getUserAccount()).append("',");
			sql.append("'").append(StringUtils.isBlank(dto.getMatchType()) ? "" : dto.getMatchType()).append("',");
			sql.append("'").append(StringUtils.isBlank(dto.getRuleName()) ? "" : dto.getRuleName()).append("',");
			sql.append("'").append(StringUtils.isBlank(dto.getRuleType()) ? "" : dto.getRuleType()).append("',");
			sql.append("0,0)");
			logger.info("添加规则,sql={}", sql.toString());
			BaseEntity.getRepository().executeUpdate(new SqlQuery(BaseEntity.getRepository(), sql.toString()));
			return Boolean.TRUE;
		}
		if (StringUtils.isNotBlank(dto.getRuleName())) {
			rule.setRuleName(dto.getRuleName());
		}
		if (StringUtils.isNotBlank(dto.getRuleType())) {
			rule.setRuleType(dto.getRuleType());
		}
		if (StringUtils.isNotBlank(dto.getMatchType())) {
			rule.setRuleMatchingType(dto.getMatchType());
		}
		if (StringUtils.isNotBlank(dto.getIsValid())) {
			rule.setDeleted("0".equals(dto.getIsValid())?false:true);
		}
		rule.update();
		return Boolean.TRUE;
	}

	private List<DscCapitalDto> getCapitalListByApplyNo(String applyNo){
		List<DscCapitalDto> capitalDtoList;
		/**
		 * 通过订单编号查询出产品code,然后再通过产品code查询出资方列表
		 */
		DscSalesApplyMain applyMain = DscSalesApplyMain.getOneByApplyNo(applyNo);
		String productNo = applyMain.getProductNo(); //产品编号
		//通过产品code 获取资方列表
		capitalDtoList = AssetProductFinancialRel.getCapitalListByProductCode(productNo);
		//capitalDtoList.add(new DscCapitalDto("101110000100010","民生银行","CMBC"));
		return capitalDtoList;
	}

	private Map<String, Object> getOrderDataSource(String applyNo) {
		Map<String,Object> sourceMap = new HashMap<>(); //属性取值来源
		//------- 申请信息主表 ----
		DscSalesApplyMain applyMain = DscSalesApplyMain.getOneByApplyNo(applyNo);
		sourceMap.put(applyMain.getClass().getName(), applyMain);
		//-------- 客户信息 ----------
		DscSalesApplyCust applyCust = DscSalesApplyCust.getOneByMainId(applyMain.getId());
		sourceMap.put(applyCust.getClass().getName(), applyCust);
		//-------- 车辆信息 ----------
		DscSalesApplyCar applyCar = DscSalesApplyCar.getBymainId(applyMain.getId());
		sourceMap.put(applyCar.getClass().getName(), applyCar);
		//-------- 融资信息 ----------
		DscSalesApplyCost applyCost = DscSalesApplyCost.getByMainId(applyMain.getId());
		sourceMap.put(applyCost.getClass().getName(), applyCost);
		//-------- 保险信息 ----------
		DscSalesInsureInfo insureInfo = DscSalesInsureInfo.getByMainId(applyMain.getId());
		sourceMap.put(insureInfo.getClass().getName(), insureInfo);
		//-------- 融资项目信息 ----------
		List<DscSalesApplyFinancing> applyFinancingLis = DscSalesApplyFinancing.getByMainId(applyMain.getId());
		if(applyFinancingLis!=null && !applyFinancingLis.isEmpty()){
			DscSalesApplyFinancingInfoDTO applyFinancingInfoDto = DscSalesApplyFinancingInfoAssembler.FinancingList2InfoDto(applyFinancingLis);
			sourceMap.put(applyFinancingInfoDto.getClass().getName(), applyFinancingInfoDto);
		}
		//-------- risk信息 ----------
		List<DscSalesApplyRisk> riskList = DscSalesApplyRisk.getByMainId(applyMain.getId());
		if(riskList!=null && !riskList.isEmpty()){
			DscSalesApplyRiskInfoDTO riskDTO = DscSalesApplyRiskAssembler.FinancingList2InfoDto(riskList);
			sourceMap.put(DscSalesApplyRiskInfoDTO.class.getName(), riskDTO);
		}else{
			sourceMap.put(DscSalesApplyRisk.class.getName(),new DscSalesApplyRisk());
		}
		List<DscSalesApplyBondsman> bondsmenList = DscSalesApplyBondsman.getListByParms(applyMain.getId());
		if(bondsmenList != null  && !bondsmenList.isEmpty()){
			sourceMap.put(DscSalesApplyBondsman.class.getName(),bondsmenList.get(0));
		}else{
			sourceMap.put(DscSalesApplyBondsman.class.getName(),new DscSalesApplyBondsman());
		}
		return sourceMap;
	}


	private BatchMatchResult capitalShuntMatch(String ruleId,String ruleDetailId,String capitalId, Map<String, Object> sourceMap){
		List<DscRuleDto> ruleDtoList = new ArrayList<>();
		String type = DscRuleTypeEnum.SHUNT.getType();
		if (StringUtils.isNotBlank(ruleId)) {
			DscRule rule = DscRule.get(DscRule.class, ruleId);
			type = rule.getRuleType();
			ruleDtoList = DscRule.getListByCapitalId(null,ruleId, null,type);
		} else if (StringUtils.isNotBlank(ruleDetailId)) {
			DscRuleDetail detail = DscRuleDetail.get(DscRuleDetail.class, ruleDetailId);
			DscRule rule = DscRule.get(DscRule.class, detail.getRuleId());
			type = rule.getRuleType();
			ruleDtoList = DscRule.getListByCapitalId(null,rule.getId(), ruleDetailId,type);
		} else if (StringUtils.isNotBlank(capitalId)){
			ruleDtoList = DscRule.getListByCapitalId(capitalId,null, null,type);
		}
		return ruleService.shuntRuleMatch(ruleDtoList, sourceMap,type);
	}



}
