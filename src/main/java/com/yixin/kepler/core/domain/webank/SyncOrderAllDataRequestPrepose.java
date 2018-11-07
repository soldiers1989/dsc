package com.yixin.kepler.core.domain.webank;

import org.springframework.stereotype.Service;

import com.yixin.common.exception.BzException;
import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.common.DscContant;
import com.yixin.kepler.common.enums.AssetPhaseEnum;
import com.yixin.kepler.common.enums.AssetStateEnum;
import com.yixin.kepler.core.base.AbstractBaseDealDomain;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.enity.AssetMainInfo;

@Service("WeBankSyncOrderAllDataRequestPrepose")
public class SyncOrderAllDataRequestPrepose extends AbstractBaseDealDomain<Object, BaseMsgDTO>{

	@Override
	protected void getData() throws BzException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected void assembler() throws BzException {
		// TODO Auto-generated method stub
		
	}

	@Override
	protected InvokeResult<BaseMsgDTO> message() throws BzException {
		// TODO Auto-generated method stub
		return null;
	}

	/**
	 * 全量同步请求前置
	 * @param applyNo 申请编号
	 * @param financeCode 资方编码
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年7月16日 下午7:18:17
	 */
	public BaseMsgDTO requestPrepose(String applyNo,String financeCode){
		AssetMainInfo assetMainInfo = AssetMainInfo.getByApplyNo(applyNo);
		if(AssetStateEnum.DOING.getState().equals(assetMainInfo.getFirstState())){ //一审进行中
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "正在信审中,不可再次进行全量同步");
		}
		
		if(AssetPhaseEnum.PAYMENT_TRIAL.getPhase().equals(assetMainInfo.getAssetPhase())){ //请款阶段
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "已完成信审,不可再次进行全量同步");
		}
		
		return new BaseMsgDTO(DscContant.KeplerCode.SUCCESS, "SUCCESS");
	}
}
