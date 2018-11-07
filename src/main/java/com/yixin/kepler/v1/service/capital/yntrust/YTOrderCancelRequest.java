package com.yixin.kepler.v1.service.capital.yntrust;

import javax.annotation.Resource;

import com.yixin.kepler.enity.AssetContractTask;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSONObject;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.dsc.common.exception.BzException;
import com.yixin.dsc.util.PropertiesManager;
import com.yixin.dsc.v1.service.capital.yntrust.YTCommonService;
import com.yixin.kepler.common.RestTemplateUtil;
import com.yixin.kepler.common.UrlConstant;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.enity.AssetBankTran;
import com.yixin.kepler.v1.common.enumpackage.YNTrustUrlEnum;
import com.yixin.kepler.v1.datapackage.dto.yntrust.YTCommonResponseDTO;
import com.yixin.kepler.v1.datapackage.dto.yntrust.YTOrderCancelDto;

import java.util.List;

/**
 * 云信取消订单
 * @author YixinCapital -- chenjiacheng
 *         2018年09月20日 9:56
 **/
@Service
public class YTOrderCancelRequest {


	private static final Logger logger = LoggerFactory.getLogger(YTOrderCancelRequest.class);


	@Resource
	private YTCommonService ytCommonService;

	@Resource
	private PropertiesManager propertiesManager;

	/**
	 * 云信订单取消
	 * @param assetMainInfo 主表信息
	 * @return 取消结果
	 * @author YixinCapital -- chenjiacheng
	 *             2018/9/20 10:01
	 */
	public BaseMsgDTO sendResult(String venusApplyNo,String applyNo,String assetMainId) {
		try {
			YTOrderCancelDto orderCancelDto = new YTOrderCancelDto();
			orderCancelDto.setRequestId(ytCommonService.getRequestId());
			orderCancelDto.setUniqueId(venusApplyNo);
			orderCancelDto.setUrl(YNTrustUrlEnum.CANCEL_LOAN.getUrl());
			String osbUrl = this.propertiesManager.getOsbEnvironment() + UrlConstant.OsbSystemUrl.ynTrustInterface;
			
			//================ 银行交互记录 ===================================
			AssetBankTran assetBankTran = new AssetBankTran();
			assetBankTran.setReqBody(JsonObjectUtils.objectToJson(orderCancelDto));
			assetBankTran.setApplyNo(applyNo);
			assetBankTran.setAssetId(assetMainId);
			assetBankTran.setAssetNo(CommonConstant.BankName.YNTRUST_BANK);
			assetBankTran.setPhase(BankPhaseEnum.ORDER_CANCEL.getPhase());
			assetBankTran.setReqUrl(osbUrl);
			assetBankTran.setApiCode(orderCancelDto.getUrl());
			assetBankTran.setSender(CommonConstant.SenderType.YIXIN);
			assetBankTran.setTranNo(orderCancelDto.getRequestId());
			assetBankTran.setBankOrderNo(venusApplyNo);
			assetBankTran.create();
			
			logger.info("云信取消订单开始,applyNo={},url={},params={}", applyNo, osbUrl, assetBankTran.getReqBody());
			String result = RestTemplateUtil.sendRequest(osbUrl, assetBankTran.getReqBody(),CommonConstant.BankName.YNTRUST_BANK);
			logger.info("云信取消订单结束,applyNo={},result={}", applyNo, result);
			if(StringUtils.isBlank(result)){
				throw new BzException("云信订单取消失败");
			}
			assetBankTran.setRepBody(result);
			YTCommonResponseDTO responseDTO = JSONObject.parseObject(ytCommonService.parseResponse(result), YTCommonResponseDTO.class);
			if (responseDTO == null || responseDTO.getStatus() == null || Boolean.FALSE.equals(responseDTO.getStatus().getIsSuccess())) {
				assetBankTran.setApprovalCode((responseDTO == null || responseDTO.getStatus() == null) ? "null" : responseDTO.getStatus().getResponseCode());
				assetBankTran.setApprovalDesc((responseDTO == null || responseDTO.getStatus() == null) ? "null" : responseDTO.getStatus().getResponseMessage());
				assetBankTran.update();
				return new BaseMsgDTO(CommonConstant.FAILURE,"云信取消订单失败");
			}
			assetBankTran.setApprovalCode(responseDTO.getStatus().getResponseCode());
			assetBankTran.setApprovalDesc(responseDTO.getStatus().getResponseMessage());
			assetBankTran.update();

			//@autor sukang 如果存在，将获取合同任务取消
			List<AssetContractTask> contractTasks = AssetContractTask.getContractTask(venusApplyNo);
			if (CollectionUtils.isNotEmpty(contractTasks)){
				contractTasks.forEach(t -> {
					t.setDeleted(true);
					t.update();
				});
			}
		} catch (Exception e) {
			throw new BzException(e);
		}
		return new BaseMsgDTO(CommonConstant.SUCCESS,"云信取消订单成功");
	}
}
