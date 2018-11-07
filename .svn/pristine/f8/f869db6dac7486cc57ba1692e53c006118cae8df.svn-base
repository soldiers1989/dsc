package com.yixin.kepler.core.service.impl;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

import com.yixin.kepler.core.service.FinancialService;
import com.yixin.kepler.dto.RespFinancialColumn;
import com.yixin.kepler.dto.RespFinancialDTO;
import com.yixin.kepler.dto.RespFinancialFile;
import com.yixin.kepler.enity.AssetAttachmentRule;
import com.yixin.kepler.enity.AssetFieldRule;
import com.yixin.kepler.enity.AssetFinanceInfo;

/**
 * 
 * @author sukang
 * @date 2018-06-08 11:45:41
 */

@Service
public class FinancialServiceImpl implements FinancialService{

	@Override
	public boolean isExist(String id) {
		return AssetFinanceInfo.isExitConditionId(id);
	}

	@Override
	public RespFinancialDTO getRuleAndInfo(String id) {
		
		RespFinancialDTO respFinancialDTO = new RespFinancialDTO();
		respFinancialDTO.setAction(Arrays.asList());
		ArrayList<RespFinancialColumn> fields = new ArrayList<>();
		ArrayList<RespFinancialFile> files = new ArrayList<>();
		
		//根据资方id获取资产属性规则
		List<AssetFieldRule> assetRuleByFinancialId = AssetFieldRule.getAssetRuleByFinancialId(id);
		
		if (!assetRuleByFinancialId.isEmpty()) {
			for (AssetFieldRule assetFieldRule : assetRuleByFinancialId) {
				
				RespFinancialColumn respFinancialColumn = new RespFinancialColumn(
						assetFieldRule.getFields(), assetFieldRule.getRule(),assetFieldRule.getRuleType(),
						assetFieldRule.getScript());
				fields.add(respFinancialColumn);
				//非准入，获取补充资料信息
				if (assetFieldRule.getIsAccess() == false) {
					
					//获取files 字段值
					List<AssetAttachmentRule> assetAttachmentRules = AssetAttachmentRule.getAttachmentRuleByFinancialId(id);
					if ( !assetAttachmentRules.isEmpty()) {
						for (AssetAttachmentRule assetAttachmentRule : assetAttachmentRules) {
							RespFinancialFile respFinancialFile = new RespFinancialFile
									(assetAttachmentRule.getFields(),
									assetAttachmentRule.getAttachMainType(),
									assetAttachmentRule.getAttachSubType(),
									assetAttachmentRule.getRule(),
									assetAttachmentRule.getRuleType(), 
									assetAttachmentRule.getScript(),
									assetAttachmentRule.getFields());

							files.add(respFinancialFile);
						}
					}
					
					
				}
			}
		}
		
		respFinancialDTO.setColumns(fields);
		respFinancialDTO.setFiles(files);
		return respFinancialDTO;
	}

}
