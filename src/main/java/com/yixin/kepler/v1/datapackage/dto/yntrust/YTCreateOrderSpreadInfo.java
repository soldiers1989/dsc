package com.yixin.kepler.v1.datapackage.dto.yntrust;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 创建订单-扩展字段信息
 * @author YixinCapital -- chenjiacheng
 *         2018年09月28日 15:02
 **/

public class YTCreateOrderSpreadInfo implements Serializable{

	private static final long serialVersionUID = -3747792340614994454L;

	/**
	 * 首付金额
	 */
	@JsonProperty("Downpayment")
	private String downpayment;

	/**
	 * 首付比例
	 */
	@JsonProperty("DownpaymentRatio")
	private String downpaymentRatio;

	/**
	 * 尾付金额
	 */
	@JsonProperty("Tailpayment")
	private String tailpayment;

	/**
	 * 尾付比例
	 */
	@JsonProperty("TailpaymentRatio")
	private String tailpaymentRatio;

	/**
	 * 手续费
	 */
	@JsonProperty("Poundage")
	private String poundage;

	/**
	 * 手续费利率
	 */
	@JsonProperty("PoundageRatio")
	private String poundageRatio;

	/**
	 * 手续费扣款方式 1：一次性  2：分期扣款  固定1
	 */
	@JsonProperty("PoundagePayType")
	private int poundagePayType;

	/**
	 * 结算手续费率
	 */
	@JsonProperty("SettlementPoundageRatio")
	private String settlementPoundageRatio;

	/**
	 * 补贴手续费
	 */
	@JsonProperty("SubsidyPoundage")
	private String subsidyPoundage;

	/**
	 * 贴息金额
	 */
	@JsonProperty("Discount")
	private String discount;

	/**
	 * 保证金比例
	 */
	@JsonProperty("DepositRatio")
	private String depositRatio;

	/**
	 * 保证金
	 */
	@JsonProperty("Deposit")
	private String deposit;

	/**
	 * 保证金冲抵方式  1：不冲抵  2：冲抵  规定1
	 */
	@JsonProperty("DepositOffsetType")
	private int depositOffsetType;

	/**
	 * 账户管理费
	 */
	@JsonProperty("ManagementFee")
	private String managementFee;

	/**
	 * 店面省份
	 */
	@JsonProperty("ShopProvince")
	private String shopProvince;

	/**
	 * 店面城市
	 */
	@JsonProperty("ShopCity")
	private String shopCity;

	/**
	 * 年收入
	 */
	@JsonProperty("BorrowerSalary")
	private String borrowerSalary;


	//担保人相关
	/**
	 * 担保人类型
	 */
	@JsonProperty("ContactRoleType")
	private int contactRoleType;

	/**
	 * 担保人姓名
	 */
	@JsonProperty("ContactName")
	private String contactName;

	/**
	 * 担保人身份证号
	 */
	@JsonProperty("ContactIDCardNo")
	private String contactIDCardNo;

	/**
	 * 担保人手机号
	 */
	@JsonProperty("ContactTelephoneNo")
	private String contactTelephoneNo;

	/**
	 * 与借款人关系
	 */
	@JsonProperty("ContactRelationType")
	private int contactRelationType;

	/**
	 * 关联人职业
	 */
	@JsonProperty("ContactJob")
	private String contactJob;

	/**
	 * 关联人年收入
	 */
	@JsonProperty("ContactSalary")
	private String contactSalary;

	/**
	 * 关联人通讯地址
	 */
	@JsonProperty("ContactPostalAddress")
	private String contactPostalAddress;

	/**
	 * 关联人单位地址
	 */
	@JsonProperty("ContactCompanyAddress")
	private String contactCompanyAddress;

	/**
	 * 关联人单位联系方式
	 */
	@JsonProperty("ContactCompanyTelephoneNo")
	private String contactCompanyTelephoneNo;

	/**
	 * 抵押物系统编号
	 */
	@JsonProperty("MortgageNumber")
	private String mortgageNumber;

	/**
	 * 抵押办理状态
	 */
	@JsonProperty("CarMortgageState")
	private int carMortgageState;

	/**
	 * 车辆性质
	 */
	@JsonProperty("CarNatureType")
	private int carNatureType;

	/**
	 * 融资方式
	 */
	@JsonProperty("FinancingType")
	private int financingType;

	/**
	 * 担保方式
	 */
	@JsonProperty("GuaranteeType")
	private int guaranteeType;

	/**
	 * 二手车评估价
	 */
	@JsonProperty("CarPrice")
	private String carPrice;

	/**
	 * 提报预估价
	 */
	@JsonProperty("CarSalePrice")
	private String carSalePrice;

	/**
	 * 二手车评估价
	 */
	@JsonProperty("CarMarketPrice")
	private String carMarketPrice;

	/**
	 * 客户融资额
	 */
	@JsonProperty("CarTotalPrice")
	private String carTotalPrice;

	/**
	 * 购置金额
	 */
	@JsonProperty("CarPurchaseTax")
	private String carPurchaseTax;

	/**
	 * 汽车保险总费用
	 */
	@JsonProperty("CarTotalPremium")
	private String carTotalPremium;

	/**
	 * 累计车辆过户次数
	 */
	@JsonProperty("CarTransferTotalCount")
	private int carTransferTotalCount;

	/**
	 * 一年内车辆过户次数
	 */
	@JsonProperty("CarTransferYearCount")
	private int carTransferYearCount;

	/**
	 * 车辆类型 2
	 */
	@JsonProperty("CarType")
	private int carType;

	/**
	 * 车架号
	 */
	@JsonProperty("CarFrameNumber")
	private String carFrameNumber;

	/**
	 * 发动机号
	 */
	@JsonProperty("CarEngineNumber")
	private String carEngineNumber;

	/**
	 * gps费用
	 */
	@JsonProperty("GPSFee")
	private String gpsFee;

	/**
	 * 车牌号码
	 */
	@JsonProperty("CarNumber")
	private String carNumber;

	/**
	 * 车辆品牌
	 */
	@JsonProperty("CarBrand")
	private String carBrand;

	/**
	 * 车系
	 */
	@JsonProperty("CarSeries")
	private String carSeries;

	/**
	 * 车型
	 */
	@JsonProperty("CarModel")
	private String carModel;

	/**
	 * 车龄
	 */
	@JsonProperty("CarAge")
	private String carAge;

	public String getDownpayment() {
		return downpayment;
	}

	public void setDownpayment(String downpayment) {
		this.downpayment = downpayment;
	}

	public String getDownpaymentRatio() {
		return downpaymentRatio;
	}

	public void setDownpaymentRatio(String downpaymentRatio) {
		this.downpaymentRatio = downpaymentRatio;
	}

	public String getTailpayment() {
		return tailpayment;
	}

	public void setTailpayment(String tailpayment) {
		this.tailpayment = tailpayment;
	}

	public String getTailpaymentRatio() {
		return tailpaymentRatio;
	}

	public void setTailpaymentRatio(String tailpaymentRatio) {
		this.tailpaymentRatio = tailpaymentRatio;
	}

	public String getPoundage() {
		return poundage;
	}

	public void setPoundage(String poundage) {
		this.poundage = poundage;
	}

	public String getPoundageRatio() {
		return poundageRatio;
	}

	public void setPoundageRatio(String poundageRatio) {
		this.poundageRatio = poundageRatio;
	}

	public int getPoundagePayType() {
		return poundagePayType;
	}

	public void setPoundagePayType(int poundagePayType) {
		this.poundagePayType = poundagePayType;
	}

	public String getSettlementPoundageRatio() {
		return settlementPoundageRatio;
	}

	public void setSettlementPoundageRatio(String settlementPoundageRatio) {
		this.settlementPoundageRatio = settlementPoundageRatio;
	}

	public String getSubsidyPoundage() {
		return subsidyPoundage;
	}

	public void setSubsidyPoundage(String subsidyPoundage) {
		this.subsidyPoundage = subsidyPoundage;
	}

	public String getDiscount() {
		return discount;
	}

	public void setDiscount(String discount) {
		this.discount = discount;
	}

	public String getDepositRatio() {
		return depositRatio;
	}

	public void setDepositRatio(String depositRatio) {
		this.depositRatio = depositRatio;
	}

	public String getDeposit() {
		return deposit;
	}

	public void setDeposit(String deposit) {
		this.deposit = deposit;
	}

	public int getDepositOffsetType() {
		return depositOffsetType;
	}

	public void setDepositOffsetType(int depositOffsetType) {
		this.depositOffsetType = depositOffsetType;
	}

	public String getManagementFee() {
		return managementFee;
	}

	public void setManagementFee(String managementFee) {
		this.managementFee = managementFee;
	}

	public String getShopProvince() {
		return shopProvince;
	}

	public void setShopProvince(String shopProvince) {
		this.shopProvince = shopProvince;
	}

	public String getShopCity() {
		return shopCity;
	}

	public void setShopCity(String shopCity) {
		this.shopCity = shopCity;
	}

	public String getBorrowerSalary() {
		return borrowerSalary;
	}

	public void setBorrowerSalary(String borrowerSalary) {
		this.borrowerSalary = borrowerSalary;
	}

	public int getContactRoleType() {
		return contactRoleType;
	}

	public void setContactRoleType(int contactRoleType) {
		this.contactRoleType = contactRoleType;
	}

	public String getContactName() {
		return contactName;
	}

	public void setContactName(String contactName) {
		this.contactName = contactName;
	}

	public String getContactIDCardNo() {
		return contactIDCardNo;
	}

	public void setContactIDCardNo(String contactIDCardNo) {
		this.contactIDCardNo = contactIDCardNo;
	}

	public String getContactTelephoneNo() {
		return contactTelephoneNo;
	}

	public void setContactTelephoneNo(String contactTelephoneNo) {
		this.contactTelephoneNo = contactTelephoneNo;
	}

	public int getContactRelationType() {
		return contactRelationType;
	}

	public void setContactRelationType(int contactRelationType) {
		this.contactRelationType = contactRelationType;
	}

	public String getContactJob() {
		return contactJob;
	}

	public void setContactJob(String contactJob) {
		this.contactJob = contactJob;
	}

	public String getContactSalary() {
		return contactSalary;
	}

	public void setContactSalary(String contactSalary) {
		this.contactSalary = contactSalary;
	}

	public String getContactPostalAddress() {
		return contactPostalAddress;
	}

	public void setContactPostalAddress(String contactPostalAddress) {
		this.contactPostalAddress = contactPostalAddress;
	}

	public String getContactCompanyAddress() {
		return contactCompanyAddress;
	}

	public void setContactCompanyAddress(String contactCompanyAddress) {
		this.contactCompanyAddress = contactCompanyAddress;
	}

	public String getContactCompanyTelephoneNo() {
		return contactCompanyTelephoneNo;
	}

	public void setContactCompanyTelephoneNo(String contactCompanyTelephoneNo) {
		this.contactCompanyTelephoneNo = contactCompanyTelephoneNo;
	}

	public String getMortgageNumber() {
		return mortgageNumber;
	}

	public void setMortgageNumber(String mortgageNumber) {
		this.mortgageNumber = mortgageNumber;
	}

	public int getCarMortgageState() {
		return carMortgageState;
	}

	public void setCarMortgageState(int carMortgageState) {
		this.carMortgageState = carMortgageState;
	}

	public int getCarNatureType() {
		return carNatureType;
	}

	public void setCarNatureType(int carNatureType) {
		this.carNatureType = carNatureType;
	}

	public int getFinancingType() {
		return financingType;
	}

	public void setFinancingType(int financingType) {
		this.financingType = financingType;
	}

	public int getGuaranteeType() {
		return guaranteeType;
	}

	public void setGuaranteeType(int guaranteeType) {
		this.guaranteeType = guaranteeType;
	}

	public String getCarPrice() {
		return carPrice;
	}

	public void setCarPrice(String carPrice) {
		this.carPrice = carPrice;
	}

	public String getCarSalePrice() {
		return carSalePrice;
	}

	public void setCarSalePrice(String carSalePrice) {
		this.carSalePrice = carSalePrice;
	}

	public String getCarMarketPrice() {
		return carMarketPrice;
	}

	public void setCarMarketPrice(String carMarketPrice) {
		this.carMarketPrice = carMarketPrice;
	}

	public String getCarTotalPrice() {
		return carTotalPrice;
	}

	public void setCarTotalPrice(String carTotalPrice) {
		this.carTotalPrice = carTotalPrice;
	}

	public String getCarPurchaseTax() {
		return carPurchaseTax;
	}

	public void setCarPurchaseTax(String carPurchaseTax) {
		this.carPurchaseTax = carPurchaseTax;
	}

	public String getCarTotalPremium() {
		return carTotalPremium;
	}

	public void setCarTotalPremium(String carTotalPremium) {
		this.carTotalPremium = carTotalPremium;
	}

	public int getCarTransferTotalCount() {
		return carTransferTotalCount;
	}

	public void setCarTransferTotalCount(int carTransferTotalCount) {
		this.carTransferTotalCount = carTransferTotalCount;
	}

	public int getCarTransferYearCount() {
		return carTransferYearCount;
	}

	public void setCarTransferYearCount(int carTransferYearCount) {
		this.carTransferYearCount = carTransferYearCount;
	}

	public int getCarType() {
		return carType;
	}

	public void setCarType(int carType) {
		this.carType = carType;
	}

	public String getCarFrameNumber() {
		return carFrameNumber;
	}

	public void setCarFrameNumber(String carFrameNumber) {
		this.carFrameNumber = carFrameNumber;
	}

	public String getCarEngineNumber() {
		return carEngineNumber;
	}

	public void setCarEngineNumber(String carEngineNumber) {
		this.carEngineNumber = carEngineNumber;
	}

	public String getGpsFee() {
		return gpsFee;
	}

	public void setGpsFee(String gpsFee) {
		this.gpsFee = gpsFee;
	}

	public String getCarNumber() {
		return carNumber;
	}

	public void setCarNumber(String carNumber) {
		this.carNumber = carNumber;
	}

	public String getCarBrand() {
		return carBrand;
	}

	public void setCarBrand(String carBrand) {
		this.carBrand = carBrand;
	}

	public String getCarSeries() {
		return carSeries;
	}

	public void setCarSeries(String carSeries) {
		this.carSeries = carSeries;
	}

	public String getCarModel() {
		return carModel;
	}

	public void setCarModel(String carModel) {
		this.carModel = carModel;
	}

	public String getCarAge() {
		return carAge;
	}

	public void setCarAge(String carAge) {
		this.carAge = carAge;
	}

}
