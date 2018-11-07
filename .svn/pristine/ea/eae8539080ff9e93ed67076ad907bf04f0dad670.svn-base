package com.yixin.dsc.assembler;

import com.yixin.common.system.util.BaseAssembler;
import com.yixin.dsc.dto.query.DscMainInfoDto;
import com.yixin.dsc.dto.query.DscPaymentErrorDto;
import com.yixin.kepler.common.enums.AssetPhaseEnum;
import com.yixin.kepler.common.enums.AssetStateEnum;
import com.yixin.kepler.enity.AssetMainInfo;
import org.apache.commons.collections.CollectionUtils;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

/**
 * @author YixinCapital -- chenjiacheng
 *         2018年08月23日 15:23
 **/

public class DscMainInfoAssembler extends BaseAssembler {


	/**
	 * 数据转换
	 * @param dataList
	 * @return
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/23 15:24
	 */
	public static List<DscMainInfoDto> toDataList(List<AssetMainInfo> dataList) {
		if (CollectionUtils.isEmpty(dataList)) {
			return null;
		}
		List<DscMainInfoDto> list = new ArrayList<>();
		for (AssetMainInfo mainInfo : dataList) {
			DscMainInfoDto dto = new DscMainInfoDto();
			dto.setApplyNo(mainInfo.getApplyNo());
			dto.setFinancialName(mainInfo.getFinancialCode());
			dto.setCreateTime(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(mainInfo.getCreateTime()));
			dto.setAssetPhase(AssetPhaseEnum.getPhaseName(mainInfo.getAssetPhase()));
			dto.setCreditSignState("1".equals(String.valueOf(mainInfo.getCreditSignState())) ? "通过" : "未通过");
			dto.setContractSignState("1".equals(String.valueOf(mainInfo.getContractSignState())) ? "通过" : "未通过");
			dto.setPreState(mainInfo.getPreState() == null ? "初始化中" : AssetStateEnum.getNameByState(mainInfo.getPreState()));
			dto.setFirstState(mainInfo.getFirstState() == null ? "初始化中" : AssetStateEnum.getNameByState(mainInfo.getFirstState()));
			dto.setLastState(mainInfo.getLastState() == null ? "初始化中" : AssetStateEnum.getNameByState(mainInfo.getLastState()));
			dto.setPaymentState(mainInfo.getPaymentState()==null ? "初始化中" : AssetStateEnum.getNameByState(mainInfo.getPaymentState()));
			dto.setAfterLoanState(mainInfo.getAfterLoanState()==null ? "初始化中" : AssetStateEnum.getNameByState(mainInfo.getAfterLoanState()));
			list.add(dto);
		}

		return list;
	}

	/**
	 * 转换数据
	 * @param list
	 * @return
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/23 16:50
	 */
	public static List<DscPaymentErrorDto> toPaymentErrorList(List<Object[]> list) {
		if (CollectionUtils.isEmpty(list)) {
			return null;
		}
		List<DscPaymentErrorDto> dataList = new ArrayList<>();
		for (Object[] obj : list) {
			DscPaymentErrorDto paymentErrorDto = new DscPaymentErrorDto();
			paymentErrorDto.setApplyNo(obj[0] == null ? "" : obj[0].toString());
			paymentErrorDto.setFinancialCode(obj[1] == null ? "" : obj[1].toString());
			paymentErrorDto.setDesc(obj[2] == null ? "" : obj[2].toString());
			paymentErrorDto.setCreateTime(obj[3] == null ? "" : obj[3].toString());

			dataList.add(paymentErrorDto);
		}
		return dataList;
	}
}
