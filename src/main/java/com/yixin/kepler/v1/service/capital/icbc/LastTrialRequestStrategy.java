package com.yixin.kepler.v1.service.capital.icbc;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;

import com.yixin.dsc.v1.service.capital.icbc.ICBCAfterShuntDeal;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yixin.common.exception.BzException;
import com.yixin.dsc.entity.order.DscSalesApplyCar;
import com.yixin.dsc.entity.order.DscSalesApplyContact;
import com.yixin.dsc.entity.order.DscSalesApplyCost;
import com.yixin.dsc.entity.order.DscSalesApplyCust;
import com.yixin.dsc.entity.order.DscSalesApplyFinancing;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.dsc.entity.order.DscSalesApplyPayee;
import com.yixin.dsc.service.common.DscWbCommonService;
import com.yixin.dsc.util.PropertiesManager;
import com.yixin.kepler.common.enums.AssetStateEnum;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.core.domain.SysDIcMapped;
import com.yixin.kepler.enity.AssetMainInfo;
import com.yixin.kepler.v1.common.constants.IcbcConstant;
import com.yixin.kepler.v1.common.enumpackage.BankPhaseEnum;
import com.yixin.kepler.v1.common.enumpackage.CarTypeEnum;
import com.yixin.kepler.v1.datapackage.dto.icbc.ICBCFirstTrialDataDTO;
import com.yixin.kepler.v1.utils.JacksonUtils;

/**
 * 请求银行信审 --工行  贷款申请接口
 * Package : com.yixin.kapler_v1.service.capital.icbc
 * 
 * @author YixinCapital -- gumanxue
 *		   2018年9月7日 下午4:50:56
 *
 */
@Service("ICBCLastTrialRequestStrategy")
public class LastTrialRequestStrategy  extends CommonRequest4Icbc<ICBCFirstTrialDataDTO> {
	
	public static final Logger logger = LoggerFactory.getLogger(LastTrialRequestStrategy.class);

	@Resource
	private PropertiesManager propertiesManager;

	@Resource
	private DscWbCommonService commonService;
	
	@Resource
    private SysDIcMapped sysDIcMapped;

    private ThreadLocal<AssetMainInfo> assetMainInfoThreadLocal = new ThreadLocal<>();

    private ThreadLocal<DscSalesApplyMain> applyMainInfoThreadLocal = new ThreadLocal<>();


	@Override
	protected void assembler() throws BzException {

    }

	@Override
	protected void getData() throws BzException {
		String applyNo = String.valueOf(inputDto.get());
        logger.info("申请单号为{},开始封装银行请求参数",applyNo);
        DscSalesApplyMain mainInfo = DscSalesApplyMain.getByApplyNo(applyNo);
		if(mainInfo == null){
			logger.error("申请单号为{},申请编号错误，申请信息表",applyNo);
			throw new BzException("申请编号错误，申请信息不存在");
		}
        DscSalesApplyCust applyCust = DscSalesApplyCust.getOneByMainId(mainInfo.getId());
        if(applyCust == null){
        	logger.error("申请单号为{},申请编号错误，客户信息不存在",applyNo);
        	throw new BzException("申请编号错误，客户信息不存在");
        }
        DscSalesApplyCar applyCar = DscSalesApplyCar.getBymainId(mainInfo.getId());
        if(applyCar == null){
        	logger.error("申请单号为{},申请编号错误，车辆信息不存在",applyNo);
        	throw new BzException("申请编号错误，车辆信息不存在");
        }
		DscSalesApplyCost applyCost = DscSalesApplyCost.getByMainId(mainInfo.getId());
		if(applyCost == null){
			logger.error("申请单号为{},申请编号错误，合同申请费用信息表",applyNo);
			throw new BzException("申请编号错误，合同申请费用信息不存在");
		}
		DscSalesApplyPayee dscSalesApplyPayee= DscSalesApplyPayee.getByMainId(mainInfo.getId());
		if(dscSalesApplyPayee == null){
			logger.error("申请单号为{},申请编号错误，合同申请收款信息表",applyNo);
			throw new BzException("申请编号错误，合同申请收款信息不存在");
		}
		logger.info("申请单号为{},一审,获取银行交互主表",applyNo);
		AssetMainInfo assetMainInfo = AssetMainInfo.getByApplyNo(applyNo);
		if(assetMainInfo == null){
			assetMainInfo = this.createAssertMainInfo(mainInfo);
		}
		applyMainInfoThreadLocal.set(mainInfo);;
		assetMainInfoThreadLocal.set(assetMainInfo);

		Map<String, DscSalesApplyFinancing> itemMap  = DscSalesApplyFinancing.get(mainInfo.getId());
		final ICBCFirstTrialDataDTO dataDTO = new ICBCFirstTrialDataDTO();
		this.dataThreadLocal.get().setData(dataDTO);//组装申请的DTO

		//===============申请人基本信息域 开始=====================
		// 业务订单号
		dataDTO.setOrderno(this.ordernoLocal.get());
		//业务类型(需要转银行码)
		dataDTO.setBusinesstype(codeConvert("businesstype", mainInfo.getBusinesstype()));
		//抵押权
		dataDTO.setMortgage(codeConvert("mortgage",mainInfo.getMortgage()));
		//抵放模式
		dataDTO.setDfmode(codeConvert("dfmode",mainInfo.getDfmode()));
		// 计算补息金额：贴息金额、客户费率、银行资金成本费率、客户融资额
		BigDecimal txamt=null;
		//获取贴息标识
		String atxfs=applyCost.getAtxfs();
		if("1".equals(atxfs)){
			try {
				txamt=ICBCAfterShuntDeal.calculateInterestRate(applyCost.getFcstxze().add(applyCost.getFdlstxze()), applyCost.getFkhfl(), applyCost.getFyhcbfl(), applyCost.getFrze());
			}catch (BzException e){
				logger.error("计算补息金额异常,{},{}", applyNo, e.getMessage());
			}
			if (BigDecimal.ZERO.compareTo(txamt) == 0) {
				//补息标识
				dataDTO.setTxflag(IcbcConstant.TXFLAG_0);
			}else {
				//补息标识
				dataDTO.setTxflag(IcbcConstant.TXFLAG_1);
				dataDTO.setTxamt(txamt.toString());
			}
		}else{
			//补息标识
			dataDTO.setTxflag(IcbcConstant.TXFLAG_0);
		}
		//补息方案
		dataDTO.setTxplan(mainInfo.getProductNo());

		// 紧急标识
		dataDTO.setUrgentflag(IcbcConstant.ICBC_FIRST_DEFAULT);

		dataDTO.setProducer(applyCar.getAzcs());// 生产厂商
		dataDTO.setCarbrand(applyCar.getAppName());// 汽车品牌
		dataDTO.setModel(applyCar.getAcxName());// 款式规格
		dataDTO.setCarprice(applyCar.getFxsj().toString());// 车价
		dataDTO.setVin(applyCar.getAcjh());//车架号
		dataDTO.setEngineno(applyCar.getAfdjh());//发动机号

		// 贷款金额（车辆分期金额）
		BigDecimal loanAmt = getFincanceItemAmt(CommonConstant.FinanceType.F010,itemMap).subtract(applyCost.getFsfje());
		// 购置税融资额
		BigDecimal purchasetax = getFincanceItemAmt(CommonConstant.FinanceType.F050, itemMap);
		// 账户管理费
		BigDecimal accountmgfee = getFincanceItemAmt(CommonConstant.FinanceType.F011, itemMap);
		//延保
		BigDecimal exInsurance = getFincanceItemAmt(CommonConstant.FinanceType.F030, itemMap);
		//精品加装费
		BigDecimal decoratefee = getFincanceItemAmt(CommonConstant.FinanceType.F012, itemMap);
		// gps融资
		BigDecimal gpsfee = getFincanceItemAmt(CommonConstant.FinanceType.F060, itemMap);
		// 保险融资 = 各个保险项目的总额
		BigDecimal insurance = getInsurance(itemMap);
		// 其他融资项（担保费）
		BigDecimal otherfee = getFincanceItemAmt(CommonConstant.FinanceType.F117, itemMap);
		//易鑫服务费
		BigDecimal yxServiceAmt = getFincanceItemAmt(CommonConstant.FinanceType.F013, itemMap);
		// 客户融资总额
		BigDecimal allamt = applyCost.getFrze();
		//责信保正常类型
		BigDecimal zxbinsurance1 = getFincanceItemAmt(CommonConstant.FinanceType.F014, itemMap);
		//责信保二档类型
		BigDecimal zxbinsurance2 = getFincanceItemAmt(CommonConstant.FinanceType.F015, itemMap);
		//责信保一档类型
		BigDecimal zxbinsurance3 = getFincanceItemAmt(CommonConstant.FinanceType.F016, itemMap);
		//责信保三档类型
		BigDecimal zxbinsurance4 = getFincanceItemAmt(CommonConstant.FinanceType.F017, itemMap);
		//责信宝
		BigDecimal zxbinsurance = defaultZero(zxbinsurance1)
				.add(defaultZero(zxbinsurance2))
				.add(defaultZero(zxbinsurance3))
				.add(defaultZero(zxbinsurance4));
			/*
				yxservamt
				易鑫服务费融资额 = 易鑫服务费融资额
								+ 购置税融资额
								+ 账户管理费融资额
								+ 精品加装融资额
					 			+ GPS融资额
					 			+ 保险融资额
					 			+ 责信保融资额
					 			+ 延保融资额
					 			+ 其他融资额（本期没有，可预留，但不能作为必传项）
			 */

		BigDecimal servamt = defaultZero(yxServiceAmt)
				.add(defaultZero(purchasetax))
				.add(defaultZero(accountmgfee))
				.add(defaultZero(exInsurance))
				.add(defaultZero(decoratefee))
				.add(defaultZero(gpsfee))
				.add(defaultZero(insurance))
				.add(defaultZero(zxbinsurance)
						.add(defaultZero(otherfee)));
		logger.info("[工行信审]贷款金额（车辆分期部分）：{}", loanAmt);
		logger.info("[工行信审]服务费(总和)：{}", servamt);
		logger.info("[工行信审]总金额(客户融资总额)：{}", allamt);
		logger.info("[工行信审]易鑫服务费融资额(易鑫手续费)：{}", yxServiceAmt);
		logger.info("[工行信审]购置税融资额：{}", purchasetax);
		logger.info("[工行信审]账户管理费融资额：{}", accountmgfee);
		logger.info("[工行信审]延保融资额：{}", exInsurance);
		logger.info("[工行信审]精品加装融资额：{}", decoratefee);
		logger.info("[工行信审]GPS融资额：{}", gpsfee);
		logger.info("[工行信审]保险融资额：{}",insurance);
		logger.info("[工行信审]责信保融资额：{}", zxbinsurance);
		logger.info("[工行信审]其他融资额：{}", otherfee);
		dataDTO.setLoanamt(loanAmt.toString());
		dataDTO.setYxservamt(defaultZero(yxServiceAmt).toString());
		dataDTO.setServamt(defaultZero(servamt).toString());
		dataDTO.setGpsfee(defaultZero(gpsfee).toString());
		dataDTO.setDecoratefee(defaultZero(decoratefee).toString());
		dataDTO.setAccountmgfee(defaultZero(accountmgfee).toString());
		dataDTO.setPurchasetax(defaultZero(purchasetax).toString());
		dataDTO.setZxbinsurance(defaultZero(zxbinsurance).toString());
		dataDTO.setExinsurance(defaultZero(exInsurance).toString());
		dataDTO.setOtherfee(defaultZero(otherfee).toString());
		// 保险融资额需要汇总
		dataDTO.setInsurance(defaultZero(insurance).toString());
		// 总金额(贷款金额+服务费)
		dataDTO.setAllamt(String.valueOf(allamt));
		// 车辆评估价
		if(CarTypeEnum.NEW_CAR.getValue().equals(codeConvert("businesstype", mainInfo.getBusinesstype()))){
			logger.info("[工行信审审]新车不传车辆评估价");
		}else{
			dataDTO.setCarassessment(applyCar.getAppraisalprice().toString());
			logger.info("[工行初审]车辆评估价：{}", dataDTO.getCarassessment());
		}
		
 		if(!allamt.equals(defaultZero(loanAmt).add(defaultZero(servamt)))){
        	logger.error("申请编号错误，贷款金额+服务费!=客户融资总额，mainId={}", applyCost.getMainId());
        	throw new BzException("申请编号错误，贷款金额+服务费!=客户融资总额");
 		}
		
        // 首付金额
        dataDTO.setOwnpayamt(applyCost.getFsfje().toString());
        // 分期期数
        dataDTO.setPlanterm(mainInfo.getArzqx());
		// 贷款成数
		dataDTO.setLoancen((new BigDecimal("100.00").subtract(applyCost.getFsfbl()).setScale(2, RoundingMode.HALF_UP)).toString());

		// 证件类型
		dataDTO.setCustsort(IcbcConstant.ICBC_FIRST_DEFAULT);
		// 证件号码
		dataDTO.setCustcode(applyCust.getAzjhm());
		//性别
		dataDTO.setSex(codeConvert("akhxb", applyCust.getAkhxb()));
		// 姓名
		dataDTO.setChnsname(applyCust.getAkhxm());
		//转码 婚姻状况
		dataDTO.setMrtlstat(codeConvert("ahyzk",applyCust.getAhyzk()));
		//教育程度
		dataDTO.setEdulvl(codeConvert("asqrxl",applyCust.getAsqrxl()));
        //住宅状况
		dataDTO.setHomestat(codeConvert("ajzzk",applyCust.getAjzzk()));
		// 住宅地址省份
		dataDTO.setHprovince(sysDIcMapped.getProName(applyCust.getAxjzdsf()));
		// 住宅地址市
		dataDTO.setHcity(sysDIcMapped.getCityName(applyCust.getAxjzdcs()));
		dataDTO.setHaddress(applyCust.getAxjzddz());// 住宅地址
		dataDTO.setMvblno(applyCust.getAsjhm());// 手机号码
		dataDTO.setUnitname(applyCust.getAdwmc());// 工作单位名称
		dataDTO.setCprovince(sysDIcMapped.getProName(applyCust.getAdwszsf()));// 单位地址省份
		dataDTO.setCcity(sysDIcMapped.getCityName(applyCust.getAdwszcs()));// 单位地址市
		dataDTO.setCaddress(applyCust.getAdwxxdz());// 工作单位地址
		dataDTO.setModelcode(codeConvert("adwqyxz",applyCust.getAdwqyxz()));// 单位性质
		dataDTO.setOccptn(applyCust.getAsqrzw());// 职业及职级
		dataDTO.setDuty(IcbcConstant.ICBC_FIRST_DEFAULT);// 职务
		dataDTO.setYearincome(applyCust.getFshnx().toString());// 本人年收入
		// 联系人
		List<DscSalesApplyContact> contactList = DscSalesApplyContact.getListByParms(mainInfo.getId());
		if(CollectionUtils.isNotEmpty(contactList)){
			DscSalesApplyContact contactList1=contactList.get(0);
			DscSalesApplyContact contactList2=contactList.get(1);
			dataDTO.setReltname1(contactList1.getAlxrxm());// 联系人一姓名
			dataDTO.setReltship1(codeConvert("ayjkrgx",contactList1.getAyjkrgx())); //联系人关系/与申请人关系
			dataDTO.setReltmobl1(contactList1.getAsjhm());//联系人一手机

			dataDTO.setReltname2(contactList2.getAlxrxm());// 联系人一姓名
			dataDTO.setReltship2(codeConvert("ayjkrgx",contactList2.getAyjkrgx())); //联系人关系/与申请人关系
			dataDTO.setReltmobl2(contactList2.getAsjhm());//联系人一手机
		}
		dataDTO.setPrimnat(IcbcConstant.ICBC_FIRST_COUNTRY);// 国籍
		dataDTO.setMonthincome(applyCust.getFshnx().divide(new BigDecimal("12"), 2, BigDecimal.ROUND_HALF_UP).multiply(new BigDecimal("10000")).toString());// 还款人月均总收入
		BigDecimal amount = new BigDecimal(200000.00);
		logger.info("贷款金额为:{}",allamt);
		// 配偶信息
		if (allamt.compareTo(amount) == 1) {
			if("0".equals(applyCust.getAhyzk())){ //已婚,传配偶
				dataDTO.setChnsname1(applyCust.getApoxm());// 姓名
				dataDTO.setRelation1(IcbcConstant.ICBC_FIRST_DEFAULT);// 与申请人关系
				dataDTO.setCustsort1(IcbcConstant.ICBC_FIRST_DEFAULT);// 证件类型
				dataDTO.setCustcode1(applyCust.getApozjhm());// 证件号码
				dataDTO.setMobile1(applyCust.getAposjhm());// 联系电话
				dataDTO.setBirthdate1(applyCust.getApozjhm().substring(6, 14));// 出生日期
				dataDTO.setEthnic1(applyCust.getEthnic1());// 民族
				dataDTO.setEducation1(codeConvert("education1",applyCust.getEducation1()));// 教育程度
				dataDTO.setAddress1(applyCust.getAxjzddz());// 居住地址
				dataDTO.setLivcondition1(codeConvert("ajzzk",applyCust.getAjzzk()));// 居住状况
				dataDTO.setRegaddr1(applyCust.getRegaddr1());// 户籍地址
				dataDTO.setCompany1(applyCust.getApogzdw());// 工作单位名称

				dataDTO.setComptype1(IcbcConstant.COMPTYPE1);// 工作单位类型
				dataDTO.setWorkyears1(IcbcConstant.WORKYEARS1);//工作单位年限
			}
		}
		dataDTO.setCitycode(mainInfo.getAcsName());// 进件城市 申请城市
		dataDTO.setYxstatus(IcbcConstant.ICBC_SCREEN_RESULT);// 易鑫筛查结果
		dataDTO.setShopid(mainInfo.getDealerChannelCode());// 4S车商代码 渠道id
		dataDTO.setShopname(mainInfo.getDealerChannelName());// 4S车商名称 渠道名称
		dataDTO.setDealer(dscSalesApplyPayee.getAsqdmmc());//汽车厂商经销商名称（全称）
	}
	@Override
	protected void setTxtCode() {
		logger.info("[工商银行]信审设置交易码值applyNo：{}", this.inputDto.get());
		//获取该订单的当前阶段
		AssetMainInfo assetMainInfo = AssetMainInfo.getByApplyNo(this.inputDto.get().toString());
		if(assetMainInfo==null){
			logger.info("订单号{}",IcbcConstant.TRXCODE_10101);
			this.dataThreadLocal.get().getComm().setTrxcode(IcbcConstant.TRXCODE_10101);
		}else{
			logger.info("订单号{},阶段为{},获取到的任务执行结果为{}",assetMainInfo.getApplyNo()
					,assetMainInfo.getAssetPhase(), net.sf.json.JSONObject.fromObject(assetMainInfo).toString());
			//判断是初审还是驳回后再次初审
			if(assetMainInfo.getAssetPhase().equals(BankPhaseEnum.LAST_TRIAL.getPhase())){//信审接口
				this.dataThreadLocal.get().getComm().setTrxcode(IcbcConstant.TRXCODE_10101);
			} else if(assetMainInfo.getAssetPhase().equals(BankPhaseEnum.LAST_TRIAL_REJECT.getPhase())){
				this.dataThreadLocal.get().getComm().setTrxcode(IcbcConstant.TRXCODE_10181);//信审驳回接口代码
			}
		}
		logger.info("[工商银行]信审设置交易码值applyNo：{}，trxCode：{}", this.inputDto.get(),
				this.dataThreadLocal.get().getComm().getTrxcode());
	}
	private String codeConvert(String code,String value){
		return sysDIcMapped.getMappingValue(code,value, CommonConstant.BankName.ICBC_BANK);
	}

	/**
	 * 从map中获取到对用的融资项目金额
	 *
	 * @param itemCode
	 *            融资项目编码
	 * @param itemMap
	 *            融资项目map
	 * @return
	 */
	public BigDecimal getFincanceItemAmt(String itemCode,
										 Map<String, DscSalesApplyFinancing> itemMap) {
		logger.info("[工行信审]根据融资项目编号获取对应的融资金额 itemCode:{}", itemCode);
		DscSalesApplyFinancing financeItem = itemMap.get(itemCode);
		logger.info("[工行信审]查询到的融资项目信息 itemCode:{}，item：{}", itemCode,
				JacksonUtils.fromObjectToJson(financeItem));
		BigDecimal financeTotalAmt = null;
		if (null != financeItem) {
			financeTotalAmt = financeItem.getFkhrzje();
		}
		logger.info("[工行信审]根据申请编号获取指定融资项目的融资额，itemCode：{}，totalAmt：{}", itemCode,
				financeTotalAmt == null ? "" : financeTotalAmt);
		return financeTotalAmt;
	}
	/**
	 * 获取传递给工行的融资项目中的保险信息
	 *
	 * @param itemMap
	 * @return
	 */
	public BigDecimal getInsurance(Map<String, DscSalesApplyFinancing> itemMap) {
		// 交强险第一年 + 商业险第一年 + 车船税第一年
		// 交强险第一年
		BigDecimal jqxsn = getFincanceItemAmt(CommonConstant.FinanceType.F091, itemMap);
		// 商业险第一年
		BigDecimal syxsn = getFincanceItemAmt(CommonConstant.FinanceType.F101, itemMap);
		// 车船税第一年
		BigDecimal ccssn = getFincanceItemAmt(CommonConstant.FinanceType.F121, itemMap);
		//盗抢责任险融资项
		BigDecimal theft = getFincanceItemAmt(CommonConstant.FinanceType.F111, itemMap);

		logger.info("[工行信审]获取到商业险第一年融资金额syxsn：{}", syxsn);
		logger.info("[工行信审]获取到交强险第一年融资金额jqxsn：{}", jqxsn);
		logger.info("[工行信审]获取到车船税第一年融资金额sccssn：{}", ccssn);
		logger.info("[工行信审]获取盗抢责任险融资项金额theft：{}", theft);
		BigDecimal insuranceAmt = null;
		insuranceAmt = defaultZero(jqxsn).add(defaultZero(syxsn)).add(defaultZero(ccssn)).add(defaultZero(theft));
		logger.info("[工行信审]获取到保险融资金额insuranceAmt：{}", insuranceAmt);
		return insuranceAmt;
	}
	public BigDecimal defaultZero(BigDecimal val) {
		if (val == null) {
			return new BigDecimal(0).setScale(2);
		} else {
			return val;
		}
	}

 
    
    /**
     * 工行创建资产信息--工行指定唯一入口
     * @param applyMainInfo
     * @return 
     * @author YixinCapital -- chen.lin
     *	       2018年9月12日 下午5:42:20
     */
	private AssetMainInfo createAssertMainInfo(DscSalesApplyMain applyMainInfo) {
		AssetMainInfo mainInfo = new AssetMainInfo();
		mainInfo.setApplyNo(applyMainInfo.getApplyNo()); // 申请编号
		mainInfo.setVenusApplyNo(this.ordernoLocal.get()); // Venus向银行请求申请编号
		mainInfo.setBankOrderNo(this.ordernoLocal.get()); // 银行返回申请编号
		mainInfo.setAssetPhase(BankPhaseEnum.LAST_TRIAL.getPhase());
		mainInfo.setCreditSignState(AssetStateEnum.INIT.getState());
		mainInfo.setContractSignState(AssetStateEnum.INIT.getState());
		mainInfo.setFirstState(AssetStateEnum.INIT.getState());
		mainInfo.setLastState(AssetStateEnum.INIT.getState());
		mainInfo.setPaymentState(AssetStateEnum.INIT.getState());
		mainInfo.setChannelCode(applyMainInfo.getDealerChannelCode()); // 渠道编号
		mainInfo.setChannelName(applyMainInfo.getDealerChannelName()); // 渠道名称
		mainInfo.setCompanyCode(applyMainInfo.getRentingCompanyCode()); // 发起融资公司编号
		mainInfo.setFinancialCode(CommonConstant.BankName.ICBC_BANK); //微众
		mainInfo.setFinancialId(applyMainInfo.getCapitalId());
		mainInfo.create();
		return mainInfo;
	}
}
