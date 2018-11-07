package com.yixin.dsc.v1.service.impl.yntrust;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.yixin.dsc.common.exception.BzException;
import com.yixin.dsc.v1.service.capital.yntrust.YTCommonService;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.core.domain.SysDIcMapped;
import com.yixin.kepler.core.listener.SettleOrderEvent;
import com.yixin.kepler.enity.AssetAttachmentRule;
import com.yixin.kepler.enity.ConstantConfig;
import com.yixin.kepler.v1.datapackage.dto.other.LoanInfoDTO;

/**
 * 云南信托 公共service
 * Package : com.yixin.dsc.v1.service.capital.yntrust
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年9月19日 上午11:42:32
 *
 */
@Service("YTCommonService")
public class YTCommonServiceImpl implements YTCommonService{

	public final Logger logger = LoggerFactory.getLogger(YTCommonServiceImpl.class);
	
	@Autowired
	private ApplicationContext ioc;
	
	@Resource
	private SysDIcMapped sysDIcMapped;
	
	/**
	 * 获取云南信托接口中 标示请求唯一性的值
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年9月19日 上午11:43:26
	 */
	@Override
	public String getRequestId() {
		return UUID.randomUUID().toString().replace("-", "");
	}

	@Override
	public String getUniqueId() {
		//uniqueid 校验规则 Pattern UUID = Pattern.compile("([0-9a-f]{8}[0-9a-f]{4}[1-5][0-9a-f]{3}[89ab][0-9a-f]{3}[0-9a-f]{12})");
		return UUID.randomUUID().toString().replace("-", "");
	}

	/**
	 * 解析报文
	 * @param responseJson
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年9月19日 上午11:46:32
	 */
	@Override
	public String parseResponse(String responseJson) {
		return responseJson;
	}


	@Override
	public String getYnTrustFileType(String fileType) {
		AssetAttachmentRule attrRuleByType = AssetAttachmentRule.getAttrRuleByType(
				fileType, CommonConstant.BankName.YNTRUST_BANK);

		Assert.notNull(attrRuleByType,fileType
				+"云信附件类规则未找到");
		String nameFormat = attrRuleByType.getNameFormat();
		int index = nameFormat.lastIndexOf("_");
		return nameFormat.substring(index - 1,index);
	}

	@Override
	public String parseCityCode(String code) {
		String defaultCityCode = "310115"; //使用固定的上海市浦东区编码310115（不能全部写310115）（报征信项目需如实填写）
		if (StringUtils.isBlank(code)) {
			return defaultCityCode;
		}
		String standardCode = sysDIcMapped.getCityStandardCode(code);
		return standardCode == null ? defaultCityCode : standardCode;
	}

	@Override
	public Map<String,String> parseBankCode(String bankCode) {
		if (StringUtils.isBlank(bankCode)) {
			throw new BzException("银行code为空");
		}
		ConstantConfig config = ConstantConfig.getConstantConfigByKey("dscCapticalSupportData_YNTRUST_bankList");
		if (config == null) {
			throw new BzException("未找到银行列表配置项");
		}

		JSONArray jsonArray = JSONObject.parseArray(config.getValueString());
		for (int i=0,j=jsonArray.size();i<j;i++) {
			JSONObject jsonObject = jsonArray.getJSONObject(i);
			if (bankCode.equals(jsonObject.getString("code"))) {
				Map<String, String> result = new HashMap<>();
				result.put("bankCode", jsonObject.getString("bankCode"));
				result.put("bankName", jsonObject.getString("name"));
				return result;
			}
		}
		return new HashMap<>();
	}

	/**
	 * 向结算推送订单放款信息
	 * @param loanInfo 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年10月16日 上午10:18:49
	 */
	public void pushSettleLoanInfo(LoanInfoDTO loanInfo) {
		try {
			logger.info("云南信托向结算推送订单放款信息,订单编号：{},解析云信放款信息:{}",loanInfo.getApplyNo(),JSON.toJSONString(loanInfo));
			ioc.publishEvent(new SettleOrderEvent(loanInfo));
		} catch (Exception e) {
			logger.error("云南信托向结算推送订单放款信息异常，订单编号:{}",loanInfo.getApplyNo(), e);
		}
	}

}
