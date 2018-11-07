package com.yixin.dsc.service.impl.match;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.dsc.assembler.match.DscOrderTrackAssembler;
import com.yixin.dsc.assembler.match.DscRuleMatchRecordAssembler;
import com.yixin.dsc.dto.match.DscMatchData2AlixDto;
import com.yixin.dsc.dto.match.DscMatchFailRecord2AlixDto;
import com.yixin.dsc.dto.match.DscMatchResultDetail2AlixDTO;
import com.yixin.dsc.dto.match.DscOrderTrackDto;
import com.yixin.dsc.dto.match.DscRuleMatchRecordDto;
import com.yixin.dsc.entity.capital.DscCapital;
import com.yixin.dsc.entity.kepler.DscOrderTrack;
import com.yixin.dsc.entity.kepler.DscRuleMatchRecord;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.dsc.enumpackage.DscRuleTypeEnum;
import com.yixin.dsc.enumpackage.OrderTrackTypeEnum;
import com.yixin.dsc.enumpackage.ShuntRefuseTypeEnum;
import com.yixin.dsc.service.match.MatchRecordService;
import com.yixin.dsc.util.DateUtil;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.enity.AssetBankTran;
import com.yixin.kepler.enity.AssetFinanceInfo;
import com.yixin.kepler.enity.AssetMainInfo;
import com.yixin.kepler.enity.AssetProductFinancialRel;
import com.yixin.web.service.base.BaseService;

import net.sf.json.JSONObject;


/**
 * 
 * Package : com.yixin.web.service.impl
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年9月25日 下午2:56:55
 *
 */
@Service("matchRecordService")
public class MatchRecordServiceImpl extends BaseService implements MatchRecordService{
	
	private static Logger logger = LoggerFactory.getLogger(MatchRecordServiceImpl.class);
	
	

	@Override
	public List<DscOrderTrackDto> getMatchListByApplyNo(String applyNo) {
		return DscOrderTrackAssembler.ListEntity2Dto(DscOrderTrack.getTrackLis(applyNo, OrderTrackTypeEnum.SHUNT.getType()));
	}

	@Override
	public List<DscRuleMatchRecordDto> getDistinctCapitalListByApplyNo(String applyNo, String matchTrackId) {
		Map<String, Object> propValues = new HashMap<>();
		propValues.put("applyNo", applyNo);
		propValues.put("matchTrackId", matchTrackId);
		List<DscRuleMatchRecord> lis = DscRuleMatchRecord.findByProperties(DscRuleMatchRecord.class, propValues);
		Map<String,DscRuleMatchRecordDto> map = new HashMap<>();
		for (DscRuleMatchRecord dscRuleMatchRecord : lis) {
			if(map.get(dscRuleMatchRecord.getCapitalId())==null) {
				DscRuleMatchRecordDto dscRuleMatchRecordDto = new DscRuleMatchRecordDto();
				dscRuleMatchRecordDto.setApplyNo(dscRuleMatchRecord.getApplyNo());
				dscRuleMatchRecordDto.setMatchTime(dscRuleMatchRecord.getMatchTime());
				dscRuleMatchRecordDto.setMatchFlag(dscRuleMatchRecord.getMatchFlag());
				dscRuleMatchRecordDto.setCapitalCode(dscRuleMatchRecord.getCapitalCode());
				dscRuleMatchRecordDto.setCapitalId(dscRuleMatchRecord.getCapitalId());
				map.put(dscRuleMatchRecord.getCapitalId(),dscRuleMatchRecordDto);
			}else {
				DscRuleMatchRecordDto dscRuleMatchRecordDto = map.get(dscRuleMatchRecord.getCapitalId());
				if(dscRuleMatchRecordDto.getMatchFlag()) {
					dscRuleMatchRecordDto.setMatchFlag(dscRuleMatchRecord.getMatchFlag());
				}
			}
		}
		List<DscRuleMatchRecordDto> retList = new ArrayList(map.values());
		return retList;
	}

	@Override
	public List<DscRuleMatchRecordDto> getDscRuleMatchRecordListByApplyNo(String applyNo, String matchTrackId,
			String capitalId) {
		Map<String, Object> propValues = new HashMap<>();
		propValues.put("applyNo", applyNo);
		propValues.put("matchTrackId", matchTrackId);
		propValues.put("capitalId", capitalId);
		List<DscRuleMatchRecord> lis = DscRuleMatchRecord.findByProperties(DscRuleMatchRecord.class, propValues);
		return DscRuleMatchRecordAssembler.ListEntity2Dto(lis);
	}

	
	/**
	 * 自营--该订单最后一次准入记录
	 * 	准入成功及不成功的资方List
	 * 
	 * @param applyNo
	 * @return 
	 * @author YixinCapital -- wangxulong
	 *	       2018年10月25日 下午5:31:05
	 */
	@Override
	public DscMatchData2AlixDto findMatchDataByApplyNo(String applyNo) {
		if(StringUtils.isBlank(applyNo)) {
			return null;
		}
		DscMatchData2AlixDto resultDto = new DscMatchData2AlixDto();
		resultDto.setApplyNo(applyNo);
		/**查询最后一次准入批次*/
		DscOrderTrack track = DscOrderTrack.getLastTrack(applyNo,DscRuleTypeEnum.SHUNT.getType());
		if(track==null) {
			return resultDto;
		}
		resultDto.setAccessTime(DateUtil.dateToString(track.getCreateTime(), DateUtil.DEFAULT_DATE_TIME_FORMAT));
		resultDto.setBatchId(track.getId());
		/**准入 入参的 资方code*/
		resultDto.setBringCapitalCode(track.getCapitalCode());
		/**当时产品关联资方code*/
		DscSalesApplyMain dscMain = DscSalesApplyMain.findFirstByProperty(DscSalesApplyMain.class,"applyNo" ,applyNo);
		if(StringUtils.isNotBlank(track.getAffiliatedCapital())) {
			resultDto.setAffiliatedCapital(track.getAffiliatedCapital());
		}else {
			/** 订单信息 */
			if(dscMain!=null) {
				resultDto.setAffiliatedCapital(AssetProductFinancialRel.getCapitalCodesByProductCode(dscMain.getProductNo()));
			}
		}
		
		JSONObject json = JSONObject.fromObject(track.getMessage());
		if(json.get("shuntRefuseType")!=null) {
			String shuntRefuseType = json.get("shuntRefuseType").toString();
			if(ShuntRefuseTypeEnum.MATCH_SUCCESS.getType().equals(shuntRefuseType)) {
				String capitalCode = json.get("capitalCode").toString();
				resultDto.setCapitalCode(capitalCode);
				resultDto.setCapitalName(DscCapital.getCapitalNameByCapitalCode(capitalCode));
			}
			resultDto.setAssesResult(shuntRefuseType);
			resultDto.setAssesResultName(ShuntRefuseTypeEnum.getEnumNameByType(shuntRefuseType));
		}else {
			if(dscMain!=null) {
				if(StringUtils.isNotBlank(dscMain.getCapitalId())) {
					AssetFinanceInfo info = AssetFinanceInfo.getById(dscMain.getCapitalId());
					if(info!=null) {
						resultDto.setCapitalCode(info.getCode());
						resultDto.setCapitalName(info.getName());
					}
					resultDto.setAssesResult(ShuntRefuseTypeEnum.MATCH_SUCCESS.getType());
					resultDto.setAssesResultName(ShuntRefuseTypeEnum.MATCH_SUCCESS.getName());
				}else {
					resultDto.setAssesResult(ShuntRefuseTypeEnum.RULE_NO_MATCH.getType());
					resultDto.setAssesResultName(ShuntRefuseTypeEnum.RULE_NO_MATCH.getName());
				}
			}
		}
		resultDto.setMatchResultDetailLis(getDscMatchResultDetail2AlixDTOLis(track.getId(), applyNo));
		return resultDto;
	}
	
	
	/**
	 * 准入结果 及 不通过明细
	 * 
	 * @param batchId
	 * @param applyNo
	 * @return 
	 * @author YixinCapital -- wangxulong
	 *	       2018年10月25日 下午7:55:33
	 */
	private List<DscMatchResultDetail2AlixDTO> getDscMatchResultDetail2AlixDTOLis(String batchId,String applyNo){
		Map<String, Object> propValues = new HashMap<>();
		propValues.put("applyNo", applyNo);
		propValues.put("matchTrackId", batchId);
		/**获取本批次所有 准入明细*/
		List<DscRuleMatchRecord> lis = DscRuleMatchRecord.findByProperties(DscRuleMatchRecord.class, propValues);
		Map<String,DscMatchResultDetail2AlixDTO> map = new HashMap<>();
		Map<String,List<DscMatchFailRecord2AlixDto>> failLisMap = new HashMap<>();
		for (DscRuleMatchRecord dscRuleMatchRecord : lis) {
			/**获取所有 资方及对应 准入结果*/
			if(map.get(dscRuleMatchRecord.getCapitalId())==null) {
				DscMatchResultDetail2AlixDTO matchResultDetail = new DscMatchResultDetail2AlixDTO();
				matchResultDetail.setSuccessFlag(dscRuleMatchRecord.getMatchFlag());
				matchResultDetail.setCapitalCode(dscRuleMatchRecord.getCapitalCode());
				map.put(dscRuleMatchRecord.getCapitalId(),matchResultDetail);
			}else {
				DscMatchResultDetail2AlixDTO matchResultDetail = map.get(dscRuleMatchRecord.getCapitalId());
				if(matchResultDetail.getSuccessFlag()) {
					matchResultDetail.setSuccessFlag(dscRuleMatchRecord.getMatchFlag());
				}
			}
			/**获取准入不通过明细list*/
			if(!dscRuleMatchRecord.getMatchFlag()) {
				DscMatchFailRecord2AlixDto matchFailRecord = new DscMatchFailRecord2AlixDto();
				matchFailRecord.setRuleId(dscRuleMatchRecord.getRuleId());
				matchFailRecord.setRuleMessage(dscRuleMatchRecord.getRuleMessage());
				matchFailRecord.setFieldValues(dscRuleMatchRecord.getFieldValues());
				if(failLisMap.get(dscRuleMatchRecord.getCapitalId())==null) {
					List<DscMatchFailRecord2AlixDto> failLis = new ArrayList<>();
					failLis.add(matchFailRecord);
					failLisMap.put(dscRuleMatchRecord.getCapitalId(),failLis);
				}else {
					failLisMap.get(dscRuleMatchRecord.getCapitalId()).add(matchFailRecord);
				}
			}
			
		}
		/**整理数据*/
		List<DscMatchResultDetail2AlixDTO> retLis= new ArrayList<>();
		for (String capitalId:map.keySet()) {
			DscMatchResultDetail2AlixDTO matchResultDetail = map.get(capitalId);
			matchResultDetail.setCapitalName(DscCapital.getCapitalNameByCapitalCode(matchResultDetail.getCapitalCode()));
			matchResultDetail.setMatchFailRecordLis(failLisMap.get(capitalId));
			//TODO 民生准入如果是不通过。需要查对应批次 的 预审结果
			if(CommonConstant.BankName.CMBC.equals(matchResultDetail.getCapitalCode()) && !matchResultDetail.getSuccessFlag()) {
				AssetBankTran bankTran = AssetBankTran.getByApplNoAndType(applyNo,  BankPhaseEnum.PRE_TRIAL.getPhase());
				if(bankTran!=null) {
					DscMatchFailRecord2AlixDto matchFailRecord = new DscMatchFailRecord2AlixDto();
					//通过 交易结果码值 和 交易结果描述 判断 预审是否成功
					matchFailRecord.setRuleId("preState");
					if(CommonConstant.ApprovalCode.equals(bankTran.getApprovalCode()) && CommonConstant.ApprovalDesc.equals(bankTran.getApprovalDesc())) {
						matchFailRecord.setRuleMessage("预审通过");
					}else {
						matchFailRecord.setRuleMessage("预审不通过");
					}
					matchResultDetail.getMatchFailRecordLis().add(matchFailRecord);
				}
			}
			retLis.add(matchResultDetail);
		}
		
		
    	return retLis;
	}
	
	
}
