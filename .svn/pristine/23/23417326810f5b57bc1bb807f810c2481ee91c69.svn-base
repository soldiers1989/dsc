package com.yixin.kepler.v1.service.capital.icbc;

import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yixin.common.exception.BzException;
import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.common.DscContant;
import com.yixin.dsc.dto.DscAdmittanceDto;
import com.yixin.dsc.dto.DscBankCardDto;
import com.yixin.dsc.dto.DscCapitalSupportDto;
import com.yixin.dsc.dto.DscFlowResultForAlixDto;
import com.yixin.dsc.entity.order.DscSalesApplyCust;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.dsc.enumpackage.DscAdmittanceEnum;
import com.yixin.dsc.enumpackage.DscAlixLinkEnum;
import com.yixin.dsc.service.common.DscQueryService;
import com.yixin.kepler.common.enums.AssetPhaseEnum;
import com.yixin.kepler.common.enums.AssetStateEnum;
import com.yixin.kepler.common.enums.CreditfrontResultEnum;
import com.yixin.kepler.core.base.AbstractBaseDealDomain;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.core.listener.ResultNotifyEvent;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.dto.webank.WBMongoDTO;
import com.yixin.kepler.enity.AssetMainInfo;
import com.yixin.kepler.enity.SysCardBin;

/**
 * 工行一审前置处理
 * Package : com.yixin.kepler.v1.service.capital.icbc
 *
 * @author YixinCapital -- guamnxue
 *		   2018年10月10日 下午1:13:20
 *
 */
@Service("ICBCFirstTrialRequestPrepose")
public class FirstTrialRequestPrepose extends AbstractBaseDealDomain<Object, BaseMsgDTO>{

	public static final Logger LOGGER = LoggerFactory.getLogger(FirstTrialRequestPrepose.class);

	@Autowired
	private MongoTemplate mongoTemplate;

	@Resource
	private DscQueryService dscQueryService;

	@Autowired
	private ApplicationContext ioc;

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
	 * 银行请求前置
	 * @param applyNo 申请编号
	 * @param financeCode 资方编码
	 * @return
	 * @author YixinCapital -- wangwenlong
	 *	       2018年7月16日 下午7:18:17
	 */
	@Override
	public BaseMsgDTO requestPrepose(String applyNo,String financeCode){
		DscSalesApplyMain mainInfo = DscSalesApplyMain.getByApplyNo(applyNo);
		if(mainInfo == null){
			LOGGER.error("申请单号为{},申请编号错误，查询不到订单信息",applyNo);
			return new BaseMsgDTO(DscContant.KeplerCode.FAILD, "申请编号错误，查询不到订单信息");
		}
		LOGGER.info("申请单号为{},一审前置提交信审校验,直接跳转二审",applyNo);
		return new BaseMsgDTO(CommonConstant.PreposeResultCode.SKIP, "跳转");
	}
	private DscFlowResultForAlixDto createForAliDto(String applyNo,CreditfrontResultEnum resultEnum) {

		DscFlowResultForAlixDto alixDto = new DscFlowResultForAlixDto();
		alixDto.setApplyNo(applyNo);
		alixDto.setCode(resultEnum.getCode());
		alixDto.setLink(DscAlixLinkEnum.CREDITFRONT.getCode());
		alixDto.setMessage(resultEnum.getMsg());
		return alixDto;
	}
}
