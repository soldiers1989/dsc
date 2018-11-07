package com.yixin.dsc.service.impl.common;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.google.common.collect.Lists;
import com.yixin.common.system.domain.BaseEntity;
import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.dsc.assembler.DscMainInfoAssembler;
import com.yixin.dsc.common.exception.BzException;
import com.yixin.dsc.dto.*;
import com.yixin.dsc.dto.order.DscFileAttachmentDTO;
import com.yixin.dsc.dto.query.DscBankInteractiveResultDto;
import com.yixin.dsc.dto.query.DscInteractiveDto;
import com.yixin.dsc.dto.query.DscMainInfoDto;
import com.yixin.dsc.dto.query.DscPaymentErrorDto;
import com.yixin.dsc.dto.rule.DscContractDto;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.dsc.enumpackage.DscAdmittanceEnum;
import com.yixin.dsc.service.common.DscQueryService;
import com.yixin.kepler.common.enums.AssetPhaseEnum;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.core.domain.LoanDomain;
import com.yixin.kepler.enity.*;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.weaver.bcel.AtAjAttributes;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 * 获取资方支持的数据信息
 *
 * @author YixinCapital -- chenjiacheng
 *         2018年07月05日 17:37
 **/
@Service("dscQueryService")
public class DscQueryServiceImpl implements DscQueryService {

	private static final Logger logger = LoggerFactory.getLogger(DscQueryServiceImpl.class);

	/**
	 * 配置的数据信息前缀
	 */
	private final String prefix = "dscCapticalSupportData_";

	/**
	 * 根据数据类型获取资方支持的数据信息
	 *
	 * @param queryParam 入参
	 * @return 结果 还款卡列表/合同列表
	 * @author YixinCapital -- chenjiacheng
	 * 2018/7/5 17:43
	 */
	@Override
	public DscCapitalSupportDto getCapitalSupportData(DscAdmittanceDto queryParam) {
		DscCapitalSupportDto result = new DscCapitalSupportDto();
		String capitalCode;
		if (StringUtils.isNotBlank(queryParam.getCapitalCode())) {
			capitalCode = queryParam.getCapitalCode();
		} else {
			AssetMainInfo assetMainInfo = AssetMainInfo.getByApplyNo(queryParam.getApplyNo());
			if (assetMainInfo == null) {
				logger.info("通过申请编号查询订单记录出错===无此记录===applyNo={}", queryParam.getApplyNo());
				throw new BzException("此订单不存在,请确认申请编号");
			}
			capitalCode = assetMainInfo.getFinancialCode();
		}
		String key = "";
		if (DscAdmittanceEnum.BANK_CARD_LIST.getValue().equals(queryParam.getDataType())) {
			key = prefix + capitalCode + "_bankList";
			ConstantConfig config = ConstantConfig.getConstantConfigByKey(key);
			if (config == null) {
				logger.info("通过key查找对应的配置项结果为空,applyNo={},key={}", queryParam.getApplyNo(), key);
				return new DscCapitalSupportDto();
			}
			List<DscBankCardDto> bankCardList = JSONArray.parseArray(config.getValueString(), DscBankCardDto.class);
			result.setBankCardList(bankCardList);
		} else if (DscAdmittanceEnum.ZX_CONTRACT_LIST.getValue().equals(queryParam.getDataType())) {
			key = prefix + capitalCode + "_ZX_ContractList";
			ConstantConfig config = ConstantConfig.getConstantConfigByKey(key);
			if (config == null) {
				logger.info("通过key查找对应的配置项结果为空,applyNo={},key={}", queryParam.getApplyNo(), key);
				return new DscCapitalSupportDto();
			}
			List<DscContractDto> contractList = JSONArray.parseArray(config.getValueString(), DscContractDto.class);
			result.setContractList(contractList);
		} else if (DscAdmittanceEnum.DZQ_CONTRACT_LIST.getValue().equals(queryParam.getDataType())) {
			key = prefix + capitalCode + "_DZQ_ContractList";
			ConstantConfig config = ConstantConfig.getConstantConfigByKey(key);
			if (config == null) {
				logger.info("通过key查找对应的配置项结果为空,applyNo={},key={}", queryParam.getApplyNo(), key);
				return new DscCapitalSupportDto();
			}
			List<DscContractDto> contractList = JSONArray.parseArray(config.getValueString(), DscContractDto.class);
			result.setContractList(contractList);
		}else if (Objects.equals(DscAdmittanceEnum.CONTRACT_SIGN_LIST.getValue(),
				queryParam.getDataType())) {
			
			List<AssetAttachmentRule> rules = AssetAttachmentRule.getRuleByFinancialAndPhase(queryParam.getCapitalCode(),
					"sign_contract");
			
			List<DscFileAttachmentDTO> arrayList = new ArrayList<>();
			
			rules.forEach(t -> {
				DscFileAttachmentDTO attachmentDTO = new DscFileAttachmentDTO();
				attachmentDTO.setAttachSubClass(t.getAttachMainType());
				attachmentDTO.setFileName(t.getAttachName());
				arrayList.add(attachmentDTO);
			});
			result.setSignContractList(arrayList);
		}

		DscCapitalDto dscCapitalDto = new DscCapitalDto();
		dscCapitalDto.setCapitalCode(queryParam.getCapitalCode());
		dscCapitalDto.setCapitalId(queryParam.getCapitalId());
		result.setDscCapitalDto(dscCapitalDto);
		return result;
	}

	/**
	 * 银行放款状态查询
	 *
	 * @param applyNo 申请编号
	 * @return
	 * @author YixinCapital -- wangwenlong
	 * 2018年7月30日 下午1:25:42
	 */
	@Override
	public Object loanStatusQuery(String applyNo) {
		LoanDomain loanDomain = SpringContextUtil.getApplicationContext().getBean(LoanDomain.class);
		return loanDomain.loanStatusQuery(applyNo);
	}


	/**
	 * 根据code批量查询字典项
	 * 由alixCode->bankCode
	 *
	 * @param codes       入参，
	 *                    Map<String,String> : key对应字典码类中的alixField,value对应字典码类中的alixCode,value可为空，表示只按照alixCode进行查询
	 * @param financeCode 资方,不用资方查询的结果集不同
	 * @return 对应的银行字典码
	 * @author YixinCapital -- chenjiacheng
	 * 2018/7/31 10:33
	 */
	@Override
	public List<SysDict> getItemCodes(List<Map<String, String>> codes, String financeCode) {
		if (codes == null || codes.isEmpty() || StringUtils.isBlank(financeCode)) {
			throw new BzException("查询字典项入参为空");
		}
		StringBuilder hql = new StringBuilder("select dict from SysDict dict where 1=1 and ( 1=0 ");
		List<Object> conditions = new ArrayList<>();
		int i = 1;
		for (int k = 0; k < codes.size(); k++) {
			Map<String, String> tempMap = codes.get(k);
			for (Map.Entry<String, String> map : tempMap.entrySet()) {
				hql.append(" or (dict.filedCode=?").append(i);
				conditions.add(map.getKey());
				if (StringUtils.isNotBlank(map.getValue())) {
					hql.append(" and dict.dicCode=?").append(++i);
					conditions.add(map.getValue());
				}
				hql.append(")");
				i++;
			}
		}
		hql.append(")");

		if (StringUtils.isNotBlank(financeCode)) {
			hql.append(" and dict.financialCode=?").append(i);
			conditions.add(financeCode);
		}
		logger.info("批量查询字典项开始,sql={},conditions={}", hql.toString(), JSON.toJSONString(conditions));
		List<SysDict> list = SysDict.getRepository().createJpqlQuery(hql.toString()).setParameters(conditions).list();
		logger.info("批量查询字典项结束,result={}", JSON.toJSONString(list));
		if (CollectionUtils.isEmpty(list)) {
			logger.info("批量查询字典项为空,返回空对象");
			return null;
		}
		return list;
	}

	/**
	 * 通过alix字段值、code值获取对应的银行字典项
	 *
	 * @param dictList  字典项集合
	 * @param alixCode  alix码值
	 * @param alixField alix字段值
	 * @return 对应的银行码值
	 * @author YixinCapital -- chenjiacheng
	 * 2018/7/31 12:35
	 */
	@Override
	public String getBankCode(List<SysDict> dictList, String alixCode, String alixField) {
		if (CollectionUtils.isEmpty(dictList)) {
			return null;
		}
		for (SysDict dict : dictList) {
			if (StringUtils.isNotBlank(alixCode) && StringUtils.isNotBlank(alixField)) {
				if (alixCode.equals(dict.getDicCode()) && alixField.equals(dict.getFiledCode())) {
					return dict.getBankCode();
				}
			} else if (StringUtils.isBlank(alixCode) && StringUtils.isNotBlank(alixField)) {
				if (alixField.equals(dict.getFiledCode())) {
					return dict.getBankCode();
				}
			} else if (StringUtils.isNotBlank(alixCode) && StringUtils.isBlank(alixField)) {
				if (alixCode.equals(dict.getDicCode())) {
					return dict.getBankCode();
				}
			}
		}
		return null;
	}

	/**
	 * 查询订单银行交互信息
	 *
	 * @param applyNo   申请编号
	 * @param phaseType 阶段
	 * @return
	 * @author YixinCapital -- wangwenlong
	 * 2018年7月31日 下午1:39:22
	 */
	@Override
	public DscBankInteractiveResultDto queryBankInteractive(String applyNo, String phaseType) {
		DscBankInteractiveResultDto resultDto = new DscBankInteractiveResultDto();
		DscSalesApplyMain applyMain = DscSalesApplyMain.getByApplyNo(applyNo);
		if (applyMain == null) {
			throw new BzException("未查询到订单信息");
		}
		if (StringUtils.isBlank(applyMain.getCapitalId())) {
			throw new BzException("该订单未准入");
		}
		AssetFinanceInfo financeInfo = AssetFinanceInfo.get(AssetFinanceInfo.class, applyMain.getCapitalId());
		if (financeInfo == null) {
			throw new BzException("订单匹配资方错误");
		}
		AssetMainInfo mainInfo = AssetMainInfo.getByApplyNo(applyNo);

		resultDto.setApplyNo(applyNo); //申请编号
		resultDto.setCapitalId(financeInfo.getId()); //资方ID
		resultDto.setCapitalCode(financeInfo.getCode()); //资方CODE
		resultDto.setOrderPhase(mainInfo == null ? "" : mainInfo.getAssetPhase());//阶段
		resultDto.setOrderPhaseDesc(mainInfo == null ? "" : AssetPhaseEnum.getPhaseName(mainInfo.getAssetPhase()));//阶段描述

		StringBuffer hql = new StringBuffer("select tran from AssetBankTran tran "
				+ "	where tran.deleted=false and tran.applyNo=?1 and tran.sender =?2 ");

		List<Object> paramList = Lists.newArrayList(applyNo, CommonConstant.SenderType.YIXIN);
		if (StringUtils.isNotBlank(phaseType)) {
			hql.append(" and tran.phase =?3 ");
			paramList.add(phaseType);
		}
		hql.append(" order by tran.createTime desc ");
		List<AssetBankTran> bankTranList = AssetBankTran.getRepository().createJpqlQuery(hql.toString()).setParameters(paramList).list();
		if (CollectionUtils.isNotEmpty(bankTranList)) {
			DscInteractiveDto detailDto = null;
			for (AssetBankTran bankTran : bankTranList) {
				detailDto = new DscInteractiveDto();
				detailDto.setCreateTime(bankTran.getCreateTime());
				detailDto.setApiCode(bankTran.getApiCode());
				detailDto.setApprovalCode(bankTran.getApprovalCode());
				detailDto.setApprovalDesc(bankTran.getApprovalDesc());
				detailDto.setPhase(bankTran.getPhase());
				detailDto.setRepBody(bankTran.getRepBody());

				resultDto.getDetail().add(detailDto);
			}
		}
		return resultDto;
	}

	@Override
	public Integer countOrderNums(String beginTime, String endTime) {
		String sql = "select count(*) from k_asset_main_info main where main.is_deleted=0 and main.create_time>'" + beginTime + "' and main.create_time<'" + endTime + "'";
		BigInteger num = AssetMainInfo.getRepository().createSqlQuery(sql).singleResult();
		return num.intValue();
	}

	@Override
	public List<DscMainInfoDto> queryMainInfoList(String beginTime, String endTime) {
		String hql = "select main from AssetMainInfo main where main.deleted=false and main.createTime> ?1 and main.createTime< ?2";
		List<Object> conditions = Lists.newArrayList();
		try {
			conditions.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(beginTime));
			conditions.add(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(endTime));
		} catch (ParseException e) {
			logger.error("转换日期异常", e);
		}
		List<AssetMainInfo> dataList = AssetMainInfo.getRepository().createJpqlQuery(hql).setParameters(conditions).list();

		return DscMainInfoAssembler.toDataList(dataList);
	}

	@Override
	public List<DscPaymentErrorDto> queryPaymentErrorList(String beginTime, String endTime) {
		String sql = "select main.apply_no,main.financial_code,tran.approval_desc,tran.create_time " +
				" from k_asset_main_info main LEFT JOIN k_asset_bank_tran tran ON main.apply_no = tran.apply_no " +
				" AND tran.phase = 'payment' WHERE main.is_deleted = 0 AND main.payment_state = 2 " +
				" AND main.create_time > '" + beginTime + "' " +
				" AND main.create_time < '" + endTime + "' " +
				" ORDER BY main.apply_no, tran.create_time DESC ";
		logger.info("查询请款失败的订单，sql={}", sql);
		List<Object[]> list = BaseEntity.getRepository().createSqlQuery(sql).list();
		return DscMainInfoAssembler.toPaymentErrorList(list);
	}

	/**
	 *  获取venus业务订单号对应的申请编号
	 *
	 * @param applyNo 申请编号
	 * @return
	 * @author YixinCapital -- gumanxue
	 * 2018年9月25日 下午1:25:42
	 */
	@Override
	public String getVenusNoByApplyNo(String applyNo) {
		AssetMainInfo mainInfo = AssetMainInfo.getByApplyNo(applyNo);
		if(mainInfo==null){
			throw new BzException("该订单未查到匹配的申请编号");
		}
		String venusNo=mainInfo.getVenusApplyNo(); //编号
		return venusNo;
	}
}
