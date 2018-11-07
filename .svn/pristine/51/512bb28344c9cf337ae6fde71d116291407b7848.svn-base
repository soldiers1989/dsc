package com.yixin.dsc.service.impl.common;

import com.alibaba.fastjson.JSONObject;
import com.yixin.common.exception.BzException;
import com.yixin.dsc.common.DscContant;
import com.yixin.dsc.entity.order.DscSalesApplyCar;
import com.yixin.dsc.entity.order.DscSalesApplyCost;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.dsc.service.common.DscWbCommonService;
import com.yixin.kepler.common.enums.WBCarTypeEnum;
import com.yixin.kepler.common.enums.WBPsCodeEnum;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.core.domain.SysDIcMapped;
import com.yixin.kepler.enity.AssetAttachmentRule;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.net.InetAddress;
import java.util.List;

/**
 * @author YixinCapital -- chenjiacheng
 *         2018年07月09日 17:34
 **/

@Service
public class DscWbCommonServiceImpl implements DscWbCommonService {

	private static final Logger logger = LoggerFactory.getLogger(DscWbCommonServiceImpl.class);

	@Resource
	private SysDIcMapped sysDIcMapped;

	/**
	 * 微众银行-通过申请编号获取该订单所对应的产品结构编号
	 *
	 * @param applyNo 申请编号
	 * @return 产品结构编号
	 * @author YixinCapital -- chenjiacheng
	 *              2018/7/9 17:37
	 */
	@Override
	public String getPsCodeByApplyNo(String applyNo) {
		logger.info("通过申请编号获取产品结构编号开始,applyNo={}", applyNo);
		String psCode = "";
		DscSalesApplyMain main = DscSalesApplyMain.getByApplyNo(applyNo);
		if (main == null) {
			logger.error("获取产品结构编号,主申请线索不存在,applyNo={}", applyNo);
			throw new BzException("该订单不存在");
		}
		DscSalesApplyCar car = DscSalesApplyCar.getBymainId(main.getId());
		if (car == null) {
			logger.error("获取产品接口编号,车辆信息不存在,applyNo={}", applyNo);
			throw new BzException("车辆信息不存在");
		}
		DscSalesApplyCost cost = DscSalesApplyCost.getByMainId(main.getId());
		if (cost == null) {
			logger.error("获取产品结构编号,费用信息不存在,applyNo={}", applyNo);
			throw new BzException("费用信息不存在");
		}
		if (WBCarTypeEnum.NEW_CAR.getValue().equals(car.getAcllx()) && CommonConstant.DiscountType.DISCOUNT_YES.equals(cost.getAtxfs())) {
			//新车贴息
			psCode = WBPsCodeEnum.NEW_CAR_DISCOUNT.getValue();
		} else if (WBCarTypeEnum.NEW_CAR.getValue().equals(car.getAcllx()) && CommonConstant.DiscountType.DISCOUNT_NO.equals(cost.getAtxfs())) {
			//新车不贴息
			psCode = WBPsCodeEnum.NEW_CAR_NOT_DICOUNT.getValue();
		} else if (WBCarTypeEnum.USED_CAR.getValue().equals(car.getAcllx()) && CommonConstant.DiscountType.DISCOUNT_NO.equals(cost.getAtxfs())) {
			//二手车不贴息
			psCode = WBPsCodeEnum.USED_CAR_NOT_DISCOUNT.getValue();
		} else {
			logger.error("通过申请编号获取产品结构编号出错,未匹配到正确结果,applyNo={}", applyNo);
			return psCode;
		}
		logger.info("通过申请编号获取产品结构编号结束,applyNo={},psCode={}", applyNo, psCode);
		return psCode;
	}

	/**
	 * 通过订单来源获取对应的银行渠道字典码
	 *
	 * @param orderSource 订单来源  APP/PC
	 * @return 对应的银行渠道字典码   APP:04  PC:01
	 * @author YixinCapital -- chenjiacheng
	 *              2018/7/12 9:37
	 */
	@Override
	public String getWbChannelByOrderSource(String orderSource) {
		String result = "01";
		if (StringUtils.isBlank(orderSource)) {
			result = "01";
		}
		if ("APP".equals(orderSource)) {
			result = "04";
		}
		logger.info("通过订单来源获取对应的银行渠道字典码,orderSource={},bankChannel={}", orderSource, result);
		return result;
	}

	/**
	 * 通过融资公司code获取银行对应的平台id
	 *
	 * @param companyCode 融资公司code
	 * @return 银行平台id
	 * @author YixinCapital -- chenjiacheng
	 *              2018/7/12 9:48
	 */
	@Override
	public String getMerchantIdByCompanyCode(String companyCode) {
		if (StringUtils.isBlank(companyCode)) {
			throw new BzException("融资公司code为空");
		}
		String result = null;
		if (DscContant.CompanyCode.SALES_AZLGSDM_XJGH.equals(companyCode)) {
			result = DscContant.MerchantId.YX;
		} else if (DscContant.CompanyCode.SALES_AZLGSDM_TJYX.equals(companyCode)) {
			result = DscContant.MerchantId.TJHT;
		}
		logger.info("通过融资公司code获取银行对应的平台id,code={},merchantId={}", companyCode, result);
		return result;
	}

	/**
	 * 获取服务器IP
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年7月12日 下午1:45:44
	 */
	@Override
	public String getServerIp() {
		try {
			InetAddress addr = InetAddress.getLocalHost();
			return addr.getHostAddress();//获取本机IP
		} catch (Exception e) {
			logger.error("获取服务器IP异常",e);
			return "";
		}
	}

	/**
	 * 解析返回报文
	 * @param responseJson
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年7月12日 下午2:41:05
	 */
	@Override
	public String parseResponse(String responseJson) {
		if(StringUtils.isBlank(responseJson)){
			throw new BzException("报文为空");
		}
		/**
		 * { 
		 * "BizSeqNo": "23584462869882334698177803729161", 
		 * "ConsumerSeqNo": "20170911100904727419000550615401", 
		 * "transactionTime":"20151022044027", 
		 * "code": "66590000", 
		 * "msg": "交易成功"， 
		 * "jsonData : "{"CODE":"66590000","DESC":"交易成功"}" 
		 * }
		 */
		JSONObject synJson = JSONObject.parseObject(responseJson);
		if("60841017".equals(synJson.getString("code"))){
			throw new BzException(" ticket 不存在或已失效，需重新获取 ticket凭据");
		} else if("60841018".equals(synJson.getString("code"))){
			throw new BzException("参与签名的 ticket 不正确。 ");
		}
//		else if(StringUtils.equals(synJson.getString("code"),CommonConstant.WB_RESP_CODE)){
//			String jsonData = synJson.getString("jsonData");
//			if(StringUtils.isBlank(jsonData)){
//				throw new BzException("业务报文为空");
//			}
//			return jsonData;
//		}
		String jsonData = synJson.getString("jsonData");
		if(StringUtils.isBlank(jsonData)){
			throw new BzException("业务报文为空");
		}
		return jsonData;
	}

	/**
	 * 获取微众银行对应的附件类型
	 *
	 * @param subClass dsc保存的材料类型
	 * @return 微众银行的附件类型
	 * @author YixinCapital -- chenjiacheng
	 *              2018/7/16 16:19
	 */
	@Override
	public String getAttrCode(String subClass) {
		if (StringUtils.isBlank(subClass)) {
			return null;
		}
		List<AssetAttachmentRule> ruleList = AssetAttachmentRule.getAttrRule(CommonConstant.BankName.WB_BANK);
		if (CollectionUtils.isNotEmpty(ruleList)) {
			for (AssetAttachmentRule rule : ruleList) {
				if (subClass.equals(rule.getAttachMainType())) {
					logger.info("获取微众银行对应的附件类型,subClass:{},attrCode:{}", subClass, rule.getAttachName());
					return rule.getAttachName();
				}
			}
		}
		return null;
	}

	/**
	 * 获取alix与银行的码值映射
	 *
	 * @param code  alix字段code
	 * @param value alix字段码值
	 * @return 银行code
	 * @author YixinCapital -- chenjiacheng
	 *          2018/7/19 11:26
	 */
	@Override
	public String codeConvert(String code, String value) {
		return sysDIcMapped.getMappingValue(code,value, CommonConstant.BankName.WB_BANK);
	}

	/**
	 * 将alix传来的值转换成银行需要的小数值
	 * eg: 30 -> 0.3,  11.88->0.1188
	 *
	 * @param source 原值
	 * @param type 类型
	 * @return 银行需要的小数值
	 * @author YixinCapital -- chenjiacheng
	 * 2018/7/19 11:29
	 */
	@Override
	public BigDecimal convertBigDecimal(BigDecimal source,String type) {
		if (source == null) throw new BzException("数据为空");
		switch (type) {
			case CommonConstant.TO_POIOT_TWO:
				return source.setScale(2, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal("100"),BigDecimal.ROUND_HALF_UP);
			case CommonConstant.TO_POIOT_FOUR:
				return source.setScale(4, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal("100"),BigDecimal.ROUND_HALF_UP);

		}

		return source.setScale(4, BigDecimal.ROUND_HALF_UP).divide(new BigDecimal("100"),BigDecimal.ROUND_HALF_UP);
	}

	@Override
	public String getSpiltCarId(String carId) {
		if(StringUtils.isBlank(carId)){
			return "";
		}
		if(carId.contains("_")){
			return carId.substring(carId.lastIndexOf("_")+1);
		}
		return carId;
	}
}
