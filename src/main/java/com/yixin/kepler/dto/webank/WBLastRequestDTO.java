package com.yixin.kepler.dto.webank;

import java.util.Date;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 二审DTO
 * Package : com.yixin.kepler.dto.webank
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月9日 下午8:10:55
 *
 */
public class WBLastRequestDTO extends WBCommonReqDTO {
 
	private static final long serialVersionUID = 4824832192827200759L;

	/**
	 * 用户平台id/身份证号码
	 */
	@JsonProperty("REG_USERID")
	private String azjhm;
	
	/**
	 * 申请编号 / 申请书编号 二审回传关联一审异步回调的APP_NO
	 */
	@JsonProperty("APP_NO")
	private String appNo;
	
	/**
	 * 姓名/申请人姓名
	 */
	@JsonProperty("NAME")
	private String akhxm;
	
	/**
	 * 证件提交类型/证件类型
	 */
	@JsonProperty("ID_TYPE")
	private String azjlx;
	
	/**
	 * 证件号码/身份证号码
	 */
	@JsonProperty("ID_NO")
	private String azjhm2;
	
	/**
	 * 性别 / 申请人性别
	 */
	@JsonProperty("GENDER")
	private String akhxb;
	
	/**
	 * 户口类别  本市农业/本市非农业/非本市农业/非本市非农业
	 */
	@JsonProperty("RESIDENCE_TYPE")
	private String residenceTypeDesc;
	
	/**
	 * 户籍所在城市  / 户籍地城市 中文
	 */
	@JsonProperty("RESIDENCE_CITY")
	private String ahjszcs;
	
	/**
	 * 申请人学历
	 */
	@JsonProperty("DEGREE")
	private String asqrxl;
	
	/**
	 * 婚姻
	 */
	@JsonProperty("MARRIAGE_INFO")
	private String ahyzk;

	/**
	 * 联系人信息数组
	 */
	@JsonProperty("CONTACT_NODE")
	private List<WBContactDTO> contactList;
	
	/**
	 * 单位名称
	 */
	@JsonProperty("COMPANY_NAME")
	private String adwmc;
	
	/**
	 * 单位联系电话
	 */
	@JsonProperty("COMPANY_PHONE")
	private String adwxxdh;
	
	/**
	 * 单位联系地址
	 */
	@JsonProperty("COMPANY_ADDR")
	private String adwxxdz;

	/**
	 * 居住地址城市
	 * 现居住地省份 现居住地城市   中文
	 */
	@JsonProperty("RESIDENT_CITY")
	private String residentCity;
	
	/**
	 * 现居住地详细地址
	 */
	@JsonProperty("RESIDENT_ADDR")
	private String axjzddz;
	
	/**
	 * 申请人职业
	 */
	@JsonProperty("OCCUPATION")
	private String asqrzy;
	
	/**
	 * 申请人职务
	 */
	@JsonProperty("TITLE")
	private String asqrzw;
	
	/**
	 * 每月平均收入进账 /税后年薪(万元)/12
	 */
	@JsonProperty("SALARY_AVE_MTLY")
	private String fshnx;
	
	/**
	 * 有无担保人或共申人 联合贷根据alix是否传担保人信息判断有无
	 */
	@JsonProperty("IS_GUARANTOR")
	private String isGuarantor;
	
	/**
	 * 房产信息 / 居住状况
	 */
	@JsonProperty("HOUSE_INFO")
	private String ajzzk;
	
	/**
	 * 车辆评估价格 / 新车实际销售价/二手车评估价
	 */
	@JsonProperty("ASSESS_AMOUNT")
	private String carPrice;
	
	/**
	 * 车辆评估时间 /alix拿到评估报告的时间
	 */
	@JsonProperty("ASSESS_TIME")
	@JsonFormat(pattern = "yyyyMMdd")
	private Date assessTime;
	
	/**
	 * 车辆检测级别 新车无、二手车传A
	 */
	@JsonProperty("ASSESS_CLASS")
	private String assessClass;
	
	/**
	 * 平台审批结果
	 * Y 通过
	 * N 拒绝
	 */
	@JsonProperty("MER_APPLY_RESULT")
	private String merApplyResult = "Y";
	
	/**
	 * 费用
	 */
	@JsonProperty("FEE")
	private List<WBPaymentFeeDTO> feeList;
	
	/**
	 * 首付成分
	 */
	@JsonProperty("DOWN_PAYMENT_COMPONENT")
	private List<WBDownPaymentDTO> downPaymentList;
	
	/**
	 * 客户融资额
	 */
	@JsonProperty("LOAN_INIT_PRIN")
	private String frze;	
	
	/**
	 * 融资期限
	 */
	@JsonProperty("LOAN_TERM")
	private String arzqx;	
	
	/**
	 * 客户利率
	 */
	@JsonProperty("INTEREST_RATE")
	private String fkhll;
	
	/**
	 * 贴息前利率 / 结算利率
	 */
	@JsonProperty("BF_DISCOUNT_INTEREST_RATE")
	private String fjsll;
	
	/**
	 * 贴息总金额 / 贴息总金额
	 */
	@JsonProperty("DISCOUNT_AMT")
	private String ftxze;

	/**
	 * 利息收取方式
	 *  01:标准
	 *  02:CUTINTEREST
	 */
	@JsonProperty("INTEREST_RATE_TYPE")
	private String interestRateType = "01";
	
	/**
	 * 是否融资分段
	 * Y--是； N--否
	 */
	@JsonProperty("IS_FINANCING_STAGE")
	private String isFinancingStage = "N";
	
	/**
	 * 平台订单号 / 合作方的订单编号
	 */
	@JsonProperty("MER_ORDER_NO")
	private String applyNo;
	
	/**
	 * 订单生成时间
	 */
	@JsonProperty("MER_ORDER_BUILD_DATE")
	@JsonFormat(pattern = "yyyyMMddHHmmss",timezone="GMT+8")
	private Date merOrderBuildDate;
	
	/**
	 * 产品结构编号
	 */
	@JsonProperty("PS_CODE")
	private String psCode;
	
	/**
	 * 车商省份/经销商渠道所在省
	 */
	@JsonProperty("DEALER_PROV")
	private String dealerChannelProvince;
	
	
	/**
	 * 车商城市/经销商渠道所在市
	 */
	@JsonProperty("DEALER_CITY")
	private String dealerChannelCity;
	
	/**
	 * 车商地址/经销商渠道详细地址
	 */
	@JsonProperty("DEALER_ADRR")
	private String dealerChannelDetailedAddress;
	
	/**
	 * 网点/车商营业执照号/ 组织机构号 alix存量，联合贷补充默认值
	 */
	@JsonProperty("NETWORK_LICENSES")
	private String networkLicenses;
	
	/**
	 * 是否阶段性担保车商 Y:是；N：否
	 */
	@JsonProperty("IS_STAGE_GUARANTEE_DEALER")
	private String isStageGuaranteeDealer = "N";
	
	/**
	 * 	品牌
	 */
	@JsonProperty("CAR_BRAND")
	private String appName;
	
	/**
	 * 车辆名称/车型描述 / 车型
	 */
	@JsonProperty("MOTORCYCLE_TYPE")
	private String acxName;	
	
	/**
	 *  车辆ID/sku
	 */
	@JsonProperty("CAR_ID")
	private String carId;

	/**
	 *  车架号/VIN
	 */
	@JsonProperty("VEHICLE_ID")
	private String acjh;
	
	/**
	 *  发动机号
	 */
	@JsonProperty("ENGINE_NO")
	private String afdjh;
	
	/**
	 * 是否新车 新车-Y 二手车-N
	 */
	@JsonProperty("IS_NEW_CAR")
	private String acllx;
	
	/**
	 * 上牌日期
	 * 二手车（IS_NEW_CAR=N）必填：首次上牌日期YYYYMMDD
	 */
	@JsonProperty("REGISTRATE_TIME")
	@JsonFormat(pattern = "yyyyMMdd")
	private Date desccdrq;
	
	/**
	 * 车龄 系统日期-初登日期
	 */
	@JsonProperty("COTY")
	private String coty;
	
	/**
	 * 行驶里程 新车传0，里程以公里为单位 /  公里数(万公里)需要转化单位
	 */
	@JsonProperty("MILEAGE")
	private String fescgls;
	
	/**
	 * 过户次数
	 */
	@JsonProperty("XFR_TIMES")
	private String xfrTimes;
	
	/**
	 * 厂商指导价格 / 新车（IS_NEW_CAR=Y）必填 新车指导价
	 */
	@JsonProperty("GUIDANCE_PRICE")
	private String fzdj;
	
	/**
	 * 车辆实际成交价  车辆销售价（车辆发票价，如为二手车，则为车辆实际成交价格） / 实际销售价
	 */
	@JsonProperty("CAR_ACTUAL_PRICE")
	private String fxsj;
	
	/**
	 * 发票价格  新车（IS_NEW_CAR=Y）必填 / 实际销售价
	 */
	@JsonProperty("CAR_INVOICE_PRICE")
	private String fxsj2;
	
	/**
	 * 首付总金额
	 */
	@JsonProperty("DOWN_PAYMENT")
	private String fsfje;
	
	/**
	 * 上牌地点 / 预计上牌城市
	 */
	@JsonProperty("REGISTRATE_PLACE")
	private String ayjspcsName;
	
	/**
	 *  车辆性质
	 */
	@JsonProperty("VEHICLE_PRO")
	private String vehiclePro;
	
	/**
	 * 营运方式 01：营运    02：非营运 / 营运方式
	 */
	@JsonProperty("OPERATION_MANNER")
	private String operationManner = "02";
	
	/**
	 * 车辆交易城市 / 经销商渠道所在市名称
	 */
	@JsonProperty("TXN_CITY")
	private String dealerChannelCityName;
	
	/**
	 * 放款提交类型   新车-1  二手车-4
	 * 0-票前放款
	 * 1-票后放款
	 * 2-抵押后放款
	 * 3-过户前放款
	 * 4-过户后放款
	 */
	@JsonProperty("ORDER_TYPE")
	private String orderType;
	
	/**
	 * 是否电核标记  Y-是  N-否
	 */
	@JsonProperty("TEL_VERIFICATION")
	private String telVerification;
	
	/**
	 * 合作主要渠道类型
	 */
	@JsonProperty("COP_CHANNEL_TYPE")
	private String copChannelType;
	
	/**
	 * 销售人员ID / 提报人域账号
	 */
	@JsonProperty("SALER_ID")
	private String salesDomainAccount;
	
	
	/**
	 * 	销售人员姓名 / 分公司金融经理
	 */
	@JsonProperty("SALER_NAME")
	private String financialManagerName;
	
	/**
	 * 销售人员手机号 / 提报人电话
	 */
	@JsonProperty("SALER_PHONE")
	private String salesPhone;
	
	/**
	 * 金融专员人员ID / 提报人域账号
	 */
	@JsonProperty("FINANCER_ID")
	private String salesDomainAccount2;
	
	/**
	 * 交易处理码  == 提交类型 默认填0
	 */
	@JsonProperty("TXN_TYPE")
	private String txnType = "0";
	
	/**
	 * 平台流水
	 */
	@JsonProperty("MER_BIZ_NO")
	private String merBizNo ;

	public String getMerBizNo() {
		return merBizNo;
	}

	public void setMerBizNo(String merBizNo) {
		this.merBizNo = merBizNo;
	}

	public String getAzjhm() {
		return azjhm;
	}

	public void setAzjhm(String azjhm) {
		this.azjhm = azjhm;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getAkhxm() {
		return akhxm;
	}

	public void setAkhxm(String akhxm) {
		this.akhxm = akhxm;
	}

	public String getAzjlx() {
		return azjlx;
	}

	public void setAzjlx(String azjlx) {
		this.azjlx = azjlx;
	}

	public String getAzjhm2() {
		return azjhm2;
	}

	public void setAzjhm2(String azjhm2) {
		this.azjhm2 = azjhm2;
	}

	public String getAkhxb() {
		return akhxb;
	}

	public void setAkhxb(String akhxb) {
		this.akhxb = akhxb;
	}

	public String getResidenceTypeDesc() {
		return residenceTypeDesc;
	}

	public void setResidenceTypeDesc(String residenceTypeDesc) {
		this.residenceTypeDesc = residenceTypeDesc;
	}

	public String getAhjszcs() {
		return ahjszcs;
	}

	public void setAhjszcs(String ahjszcs) {
		this.ahjszcs = ahjszcs;
	}

	public String getAsqrxl() {
		return asqrxl;
	}

	public void setAsqrxl(String asqrxl) {
		this.asqrxl = asqrxl;
	}

	public String getAhyzk() {
		return ahyzk;
	}

	public void setAhyzk(String ahyzk) {
		this.ahyzk = ahyzk;
	}

	public List<WBContactDTO> getContactList() {
		return contactList;
	}

	public void setContactList(List<WBContactDTO> contactList) {
		this.contactList = contactList;
	}

	public String getAdwmc() {
		return adwmc;
	}

	public void setAdwmc(String adwmc) {
		this.adwmc = adwmc;
	}

	public String getAdwxxdh() {
		return adwxxdh;
	}

	public void setAdwxxdh(String adwxxdh) {
		this.adwxxdh = adwxxdh;
	}

	public String getAdwxxdz() {
		return adwxxdz;
	}

	public void setAdwxxdz(String adwxxdz) {
		this.adwxxdz = adwxxdz;
	}

	public String getResidentCity() {
		return residentCity;
	}

	public void setResidentCity(String residentCity) {
		this.residentCity = residentCity;
	}

	public String getAxjzddz() {
		return axjzddz;
	}

	public void setAxjzddz(String axjzddz) {
		this.axjzddz = axjzddz;
	}

	public String getAsqrzy() {
		return asqrzy;
	}

	public void setAsqrzy(String asqrzy) {
		this.asqrzy = asqrzy;
	}

	public String getAsqrzw() {
		return asqrzw;
	}

	public Date getMerOrderBuildDate() {
		return merOrderBuildDate;
	}

	public void setMerOrderBuildDate(Date merOrderBuildDate) {
		this.merOrderBuildDate = merOrderBuildDate;
	}

	public void setAsqrzw(String asqrzw) {
		this.asqrzw = asqrzw;
	}

	public String getFshnx() {
		return fshnx;
	}

	public void setFshnx(String fshnx) {
		this.fshnx = fshnx;
	}

	public String getIsGuarantor() {
		return isGuarantor;
	}

	public void setIsGuarantor(String isGuarantor) {
		this.isGuarantor = isGuarantor;
	}

	public String getAjzzk() {
		return ajzzk;
	}

	public void setAjzzk(String ajzzk) {
		this.ajzzk = ajzzk;
	}

	public String getCarPrice() {
		return carPrice;
	}

	public void setCarPrice(String carPrice) {
		this.carPrice = carPrice;
	}

	public Date getAssessTime() {
		return assessTime;
	}

	public void setAssessTime(Date assessTime) {
		this.assessTime = assessTime;
	}

	public String getAssessClass() {
		return assessClass;
	}

	public void setAssessClass(String assessClass) {
		this.assessClass = assessClass;
	}

	public String getMerApplyResult() {
		return merApplyResult;
	}

	public void setMerApplyResult(String merApplyResult) {
		this.merApplyResult = merApplyResult;
	}

	public List<WBPaymentFeeDTO> getFeeList() {
		return feeList;
	}

	public void setFeeList(List<WBPaymentFeeDTO> feeList) {
		this.feeList = feeList;
	}

	public List<WBDownPaymentDTO> getDownPaymentList() {
		return downPaymentList;
	}

	public void setDownPaymentList(List<WBDownPaymentDTO> downPaymentList) {
		this.downPaymentList = downPaymentList;
	}

	public String getFrze() {
		return frze;
	}

	public void setFrze(String frze) {
		this.frze = frze;
	}

	public String getArzqx() {
		return arzqx;
	}

	public void setArzqx(String arzqx) {
		this.arzqx = arzqx;
	}

	public String getFkhll() {
		return fkhll;
	}

	public void setFkhll(String fkhll) {
		this.fkhll = fkhll;
	}

	public String getInterestRateType() {
		return interestRateType;
	}

	public void setInterestRateType(String interestRateType) {
		this.interestRateType = interestRateType;
	}

	public String getIsFinancingStage() {
		return isFinancingStage;
	}

	public void setIsFinancingStage(String isFinancingStage) {
		this.isFinancingStage = isFinancingStage;
	}

	public String getAppNo() {
		return appNo;
	}

	public void setAppNo(String appNo) {
		this.appNo = appNo;
	}

	public String getPsCode() {
		return psCode;
	}

	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}

	public String getDealerChannelProvince() {
		return dealerChannelProvince;
	}

	public void setDealerChannelProvince(String dealerChannelProvince) {
		this.dealerChannelProvince = dealerChannelProvince;
	}

	public String getDealerChannelCity() {
		return dealerChannelCity;
	}

	public void setDealerChannelCity(String dealerChannelCity) {
		this.dealerChannelCity = dealerChannelCity;
	}

	public String getDealerChannelDetailedAddress() {
		return dealerChannelDetailedAddress;
	}

	public void setDealerChannelDetailedAddress(String dealerChannelDetailedAddress) {
		this.dealerChannelDetailedAddress = dealerChannelDetailedAddress;
	}

	public String getNetworkLicenses() {
		return networkLicenses;
	}

	public void setNetworkLicenses(String networkLicenses) {
		this.networkLicenses = networkLicenses;
	}

	public String getIsStageGuaranteeDealer() {
		return isStageGuaranteeDealer;
	}

	public void setIsStageGuaranteeDealer(String isStageGuaranteeDealer) {
		this.isStageGuaranteeDealer = isStageGuaranteeDealer;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAcxName() {
		return acxName;
	}

	public void setAcxName(String acxName) {
		this.acxName = acxName;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getAcjh() {
		return acjh;
	}

	public void setAcjh(String acjh) {
		this.acjh = acjh;
	}

	public String getAfdjh() {
		return afdjh;
	}

	public void setAfdjh(String afdjh) {
		this.afdjh = afdjh;
	}

	public String getAcllx() {
		return acllx;
	}

	public void setAcllx(String acllx) {
		this.acllx = acllx;
	}

	public Date getDesccdrq() {
		return desccdrq;
	}

	public void setDesccdrq(Date desccdrq) {
		this.desccdrq = desccdrq;
	}

	public String getCoty() {
		return coty;
	}

	public void setCoty(String coty) {
		this.coty = coty;
	}

	public String getFescgls() {
		return fescgls;
	}

	public void setFescgls(String fescgls) {
		this.fescgls = fescgls;
	}

	public String getXfrTimes() {
		return xfrTimes;
	}

	public void setXfrTimes(String xfrTimes) {
		this.xfrTimes = xfrTimes;
	}

	public String getFzdj() {
		return fzdj;
	}

	public void setFzdj(String fzdj) {
		this.fzdj = fzdj;
	}

	public String getFxsj() {
		return fxsj;
	}

	public void setFxsj(String fxsj) {
		this.fxsj = fxsj;
	}

	public String getFxsj2() {
		return fxsj2;
	}

	public void setFxsj2(String fxsj2) {
		this.fxsj2 = fxsj2;
	}

	public String getFsfje() {
		return fsfje;
	}

	public void setFsfje(String fsfje) {
		this.fsfje = fsfje;
	}

	public String getAyjspcsName() {
		return ayjspcsName;
	}

	public void setAyjspcsName(String ayjspcsName) {
		this.ayjspcsName = ayjspcsName;
	}

	public String getVehiclePro() {
		return vehiclePro;
	}

	public void setVehiclePro(String vehiclePro) {
		this.vehiclePro = vehiclePro;
	}

	public String getOperationManner() {
		return operationManner;
	}

	public void setOperationManner(String operationManner) {
		this.operationManner = operationManner;
	}

	public String getDealerChannelCityName() {
		return dealerChannelCityName;
	}

	public void setDealerChannelCityName(String dealerChannelCityName) {
		this.dealerChannelCityName = dealerChannelCityName;
	}

	public String getOrderType() {
		return orderType;
	}

	public void setOrderType(String orderType) {
		this.orderType = orderType;
	}

	public String getTelVerification() {
		return telVerification;
	}

	public void setTelVerification(String telVerification) {
		this.telVerification = telVerification;
	}

	public String getCopChannelType() {
		return copChannelType;
	}

	public void setCopChannelType(String copChannelType) {
		this.copChannelType = copChannelType;
	}

	public String getSalesDomainAccount() {
		return salesDomainAccount;
	}

	public void setSalesDomainAccount(String salesDomainAccount) {
		this.salesDomainAccount = salesDomainAccount;
	}

	public String getFinancialManagerName() {
		return financialManagerName;
	}

	public void setFinancialManagerName(String financialManagerName) {
		this.financialManagerName = financialManagerName;
	}

	public String getSalesPhone() {
		return salesPhone;
	}

	public void setSalesPhone(String salesPhone) {
		this.salesPhone = salesPhone;
	}

	public String getSalesDomainAccount2() {
		return salesDomainAccount2;
	}

	public void setSalesDomainAccount2(String salesDomainAccount2) {
		this.salesDomainAccount2 = salesDomainAccount2;
	}

	public String getTxnType() {
		return txnType;
	}

	public void setTxnType(String txnType) {
		this.txnType = txnType;
	}

	public String getFjsll() {
		return fjsll;
	}

	public void setFjsll(String fjsll) {
		this.fjsll = fjsll;
	}

	public String getFtxze() {
		return ftxze;
	}

	public void setFtxze(String ftxze) {
		this.ftxze = ftxze;
	}
}
