package com.yixin.kepler.core.domain.webank;

import com.yixin.common.exception.BzException;
import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.entity.order.DscFileAttachment;
import com.yixin.dsc.entity.order.DscSalesApplyCar;
import com.yixin.dsc.entity.order.DscSalesApplyCust;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.kepler.common.enums.WBCarTypeEnum;
import com.yixin.kepler.core.base.AbstractBaseDealDomain;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.dto.webank.WBPickUpCarProofsDTO;
import com.yixin.kepler.enity.AssetAttachmentRule;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * 同步贷后资料时，对附件进行校验
 * @author YixinCapital -- chenjiacheng
 *         2018年07月30日 17:32
 **/
@Service("WeBankAfterLoanCheckRequestPrepose")
public class WBAfterLoanCheckRequestPrepose extends AbstractBaseDealDomain<Object, BaseMsgDTO> {

	private static final Logger logger = LoggerFactory.getLogger(WBAfterLoanCheckRequestPrepose.class);

	@Override
	protected void getData() throws BzException {

	}

	@Override
	protected void assembler() throws BzException {

	}

	@Override
	protected InvokeResult<BaseMsgDTO> message() throws BzException {
		return null;
	}


	/**
	 * 对贷后同步资料进行校验
	 * @param applyNo 申请编号
	 * @param financeCode 资方编码
	 * @return 校验结果
	 * @author YixinCapital -- chenjiacheng
	 *             2018/7/30 17:40
	 */
	public BaseMsgDTO requestPrepose(String applyNo,String financeCode){


		/*logger.info("贷后同步资料附件校验开始,applyNo={}", applyNo);
		BaseMsgDTO checkFile = CommonDomainUtil.checkAttachmentFile(
				applyNo, BankPhaseEnum.AFTER_LOAN);
		logger.info("贷后同步资料附件校验结束,applyNo={}，result={}", applyNo,
				JSONObject.fromObject(checkFile).toString());*/

		//对必需字段进行校验
		DscSalesApplyMain main = DscSalesApplyMain.getByApplyNo(applyNo);
		if (main == null) {
			return BaseMsgDTO.failureData("该订单无记录");
		}
		DscSalesApplyCust cust = DscSalesApplyCust.getApplyCostByMianId(main.getId());
		if (cust == null) {
			return BaseMsgDTO.failureData("客户信息不存在");
		}
		if (StringUtils.isBlank(cust.getAkhxm())) {
			return BaseMsgDTO.failureData("客户名称不能为空");
		}
		if (StringUtils.isBlank(cust.getAzjlx())) {
			return BaseMsgDTO.failureData("证件类型不能为空");
		}
		if (StringUtils.isBlank(cust.getAzjhm())) {
			return BaseMsgDTO.failureData("证件号码不能为空");
		}
		DscSalesApplyCar car = DscSalesApplyCar.getBymainId(main.getId());
		if (car == null) {
			return BaseMsgDTO.failureData("车辆信息为空");
		}
		if (StringUtils.isBlank(car.getCarId())) {
			return BaseMsgDTO.failureData("车辆id不能为空");
		}
		if (StringUtils.isBlank(car.getAcjh())) {
			return BaseMsgDTO.failureData("车架号不能为空");
		}
		if (StringUtils.isBlank(main.getAdyqr())) {
			return BaseMsgDTO.failureData("押权所有人不能为空");
		}
		if (StringUtils.isBlank(main.getAcldycs())) {
			return BaseMsgDTO.failureData("抵押办理城市不能为空");
		}
		if (WBCarTypeEnum.NEW_CAR.getValue().equals(car.getAcllx())) {
			if (car.getRegistrateTime() == null) {
				return BaseMsgDTO.failureData("新车上牌日期不能为空");
			}
		}
		if (StringUtils.isBlank(car.getAcarnojc())) {
			return BaseMsgDTO.failureData("车牌号不能为空");
		}
		if (car.getMortgageRegDate() == null) {
			return BaseMsgDTO.failureData("抵押登记日期不能为空");
		}
		if (StringUtils.isBlank(car.getAfdjh())) {
			return BaseMsgDTO.failureData("发动机号不能为空");
		}


		List<DscFileAttachment> attachmentList = DscFileAttachment.lists(main.getId());
		List<AssetAttachmentRule> ruleList = AssetAttachmentRule.getAttrRule(CommonConstant.BankName.WB_BANK);
		if (CollectionUtils.isEmpty(attachmentList)) {
			return BaseMsgDTO.failureData("无法找到附件信息，请确认附件是否已上传");
		}
		if (CollectionUtils.isEmpty(ruleList)) {
			return BaseMsgDTO.failureData("附件校验规则为空");
		}
		List<WBPickUpCarProofsDTO> proofsDTOList = new ArrayList<>();
		for (DscFileAttachment attachment : attachmentList) {
			for (AssetAttachmentRule rule : ruleList) {
				WBPickUpCarProofsDTO proofsDTO = new WBPickUpCarProofsDTO();
				if (attachment.getAttachSubClass().equals(rule.getAttachMainType())) {
					proofsDTO.setProofType(rule.getNameFormat()==null? null : rule.getNameFormat().substring(3,6)); //材料类型
					proofsDTO.setProofId(rule.getNameFormat()==null? null : rule.getNameFormat().substring(3,6));//材料编号
					proofsDTOList.add(proofsDTO);
					break;
				}
			}
		}
		if (CollectionUtils.isEmpty(proofsDTOList)) {
			return BaseMsgDTO.failureData("附件信息为空，请确认附件是否已上传");
		}

		return BaseMsgDTO.successData("success");
	}


}
