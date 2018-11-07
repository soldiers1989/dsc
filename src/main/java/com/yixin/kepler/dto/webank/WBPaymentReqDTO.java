package com.yixin.kepler.dto.webank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 微众银行请款dto
 * @author YixinCapital -- chenjiacheng
 *         2018年07月09日 15:58
 **/

public class WBPaymentReqDTO extends WBCommonReqDTO {

	//-------订单业务主键 开始
	/**
	 * 平台订单号
	 */
	@JsonProperty("MER_ORDER_NO")
	private String merOrderNo;

	/**
	 * 微众订单号
	 */
	@JsonProperty("NBS_ORDER_NO")
	private String nbsOrderNo;

	/**
	 * 产品结构编号
	 */
	@JsonProperty("PS_CODE")
	private String psCode;

	/**
	 * 姓名
	 */
	@JsonProperty("NAME")
	private String name;

	/**
	 * 证件提交类型
	 */
	@JsonProperty("ID_TYPE")
	private String idType;

	/**
	 * 证件号码
	 */
	@JsonProperty("ID_NO")
	private String idNo;
	//-------订单业务主键 结束



	//-------当单车辆主键 开始
	/**
	 * 车辆ID
	 */
	@JsonProperty("CAR_ID")
	private String carId;

	/**
	 * 机动车架号/VIN
	 */
	@JsonProperty("VEHICLE_ID")
	private String vehicleId;

	/**
	 * 车商ID
	 */
	@JsonProperty("DEALER_ID")
	private String dealerId;

	/**
	 * 发动机号
	 */
	@JsonProperty("ENGINE_CODE")
	private String engineCode;

	/**
	 * 车牌号
	 */
	@JsonProperty("PLATE_NUMBER")
	private String plateNumber;

	//-------当单车辆主键 结束

	//-------交易信息域 开始
	/**
	 * 交易码
	 */
	@JsonProperty("TXN_CODE")
	private String txnCode;

	/**
	 * 交易描述
	 */
	@JsonProperty("TXN_DESC")
	private String txnDesc;

	/**
	 * 交易时间
	 */
	@JsonProperty("TXN_DATE")
	@JsonFormat(pattern = "yyyyMMddHHmmss",timezone="GMT+8")
	private Date txnDate;

	//-------交易信息域 结束

	//--------------------
	/**
	 * 借款金额
	 */
	@JsonProperty("LOAN_INIT_PRIN")
	private BigDecimal loanInitPrin;

	/**
	 * 费用
	 */
	@JsonProperty("FEE")
	private List<WBPaymentFeeDTO> fee;

	/**
	 * 首付总金额
	 */
	@JsonProperty("DOWN_PAYMENT")
	private BigDecimal downPayment;

	/**
	 * 首付比例
	 */
	@JsonProperty("DOWN_PAYMENT_RATIO")
	private BigDecimal downPaymentRatio;

	/**
	 * 贷款期数
	 */
	@JsonProperty("LOAN_TERM")
	private Integer loanTerm;

	/**
	 * 年化利率
	 */
	@JsonProperty("INTEREST_RATE")
	private BigDecimal interestRate;

	/**
	 * 利息收取方式
	 */
	@JsonProperty("INTEREST_RATE_TYPE")
	private String interestRateType;

	/**
	 * 预收利息
	 */
	@JsonProperty("UNEARNED_INTEREST")
	private BigDecimal unearnedInterest;

	/**
	 * 保证金比例
	 */
	@JsonProperty("CASH_DEPOSIT_RATIO")
	private BigDecimal cashDepositRatio;

	/**
	 * 是否新车
	 */
	@JsonProperty("IS_NEW_CAR")
	private String isNewCar;

	/**
	 * 是否阶段性担保车商
	 */
	@JsonProperty("IS_STAGE_GUARANTEE_DEALER")
	private String isStageGuaranteeDealer;

	/**
	 * 平台起息日
	 */
	@JsonProperty("MER_INT_START")
	@JsonFormat(pattern = "yyyyMMdd")
	private Date merIntStart;

	/**
	 * 平台账单日
	 */
	@JsonProperty("MER_CYCLE_DAY")
	private Integer merCycleDay;

	/**
	 * 平台到期日
	 */
	@JsonProperty("MER_EXPIRE_DATE")
	@JsonFormat(pattern = "yyyyMMdd")
	private Date merExpireDate;

	/**
	 * 上牌日期
	 */
	@JsonProperty("REGISTRATE_TIME")
	@JsonFormat(pattern = "yyyyMMdd")
	private Date registrateTime;

	/**
	 * 实际发票价格
	 */
	@JsonProperty("INVOICE_PRICE")
	private BigDecimal invoicePrice;

	/**
	 * 发票开具日期
	 */
	@JsonProperty("INVOICE_DATE")
	@JsonFormat(pattern = "yyyyMMdd")
	private Date invoiceDate;

	/**
	 * 交易城市
	 */
	@JsonProperty("TXN_CITY")
	private String txnCity;

	/**
	 * 车辆过户完成日期
	 */
	@JsonProperty("CAR_PASS_DATE")
	@JsonFormat(pattern = "yyyyMMdd")
	private Date carPassDate;

	/**
	 * 上牌城市编码
	 */
	@JsonProperty("CITY_CODE")
	private String cityCode;

	/**
	 * 保险到期日
	 */
	@JsonProperty("INS_EXPIRE_DATE")
	@JsonFormat(pattern = "yyyyMMdd")
	private Date insExpireDate;

	/**
	 * 购置税实际金额
	 */
	@JsonProperty("TAX_PRICE")
	private BigDecimal taxPrice;

	/**
	 * 合作方放款流水号列表
	 */
	@JsonProperty("MER_LOAN_SEQ_LIST")
	private List<WBPaymentMerLoanSeqListDTO> merLoanSeqList;


	//--------------------

	//--------收款账户信息 开始

	/**
	 * 户名
	 */
	@JsonProperty("ACCT_NAME")
	private String acctName;

	/**
	 * 账号
	 */
	@JsonProperty("ACCT_NO")
	private String acctNo;

	/**
	 * 开户行号
	 */
	@JsonProperty("BRANCH_NO")
	private String branchNo;

	/**
	 * 开户行名
	 */
	@JsonProperty("BRANCH_NAME")
	private String branchName;

	//--------收款账户信息 结束

	//--------车商信息  开始
	/**
	 * 车商名称
	 */
	@JsonProperty("DEALER_NAME")
	private String dealerName;

	/**
	 * 车商营业执照号
	 */
	@JsonProperty("DEALER_LICENSES")
	private String dealerLicenses;

	//--------车商信息  结束

	//--------gps信息 开始

	/**
	 * GPS是否激活
	 */
	@JsonProperty("IS_GPS_ACTIVE")
	private String isGpsActive;

	/**
	 * GPS激活时间
	 */
	@JsonProperty("GPS_ACTIVE_TIME")
	@JsonFormat(pattern = "yyyyMMddHHmmss",timezone="GMT+8")
	private Date gpsActiveTime;

	/**
	 * GPS设备码
	 */
	@JsonProperty("GPS_CODE")
	private String gpsCode;

	/**
	 * 经度
	 */
	@JsonProperty("LONGITUDE")
	private String longitude;

	/**
	 * 维度
	 */
	@JsonProperty("LATITUDE")
	private String latitude;

	//--------gps信息 结束


	public String getMerOrderNo() {
		return merOrderNo;
	}

	public void setMerOrderNo(String merOrderNo) {
		this.merOrderNo = merOrderNo;
	}

	public String getNbsOrderNo() {
		return nbsOrderNo;
	}

	public void setNbsOrderNo(String nbsOrderNo) {
		this.nbsOrderNo = nbsOrderNo;
	}

	public String getPsCode() {
		return psCode;
	}

	public void setPsCode(String psCode) {
		this.psCode = psCode;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getIdType() {
		return idType;
	}

	public void setIdType(String idType) {
		this.idType = idType;
	}

	public String getIdNo() {
		return idNo;
	}

	public void setIdNo(String idNo) {
		this.idNo = idNo;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public String getVehicleId() {
		return vehicleId;
	}

	public void setVehicleId(String vehicleId) {
		this.vehicleId = vehicleId;
	}

	public String getDealerId() {
		return dealerId;
	}

	public void setDealerId(String dealerId) {
		this.dealerId = dealerId;
	}

	public String getEngineCode() {
		return engineCode;
	}

	public void setEngineCode(String engineCode) {
		this.engineCode = engineCode;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public String getTxnCode() {
		return txnCode;
	}

	public void setTxnCode(String txnCode) {
		this.txnCode = txnCode;
	}

	public String getTxnDesc() {
		return txnDesc;
	}

	public void setTxnDesc(String txnDesc) {
		this.txnDesc = txnDesc;
	}

	public Date getTxnDate() {
		return txnDate;
	}

	public void setTxnDate(Date txnDate) {
		this.txnDate = txnDate;
	}

	public BigDecimal getLoanInitPrin() {
		return loanInitPrin;
	}

	public void setLoanInitPrin(BigDecimal loanInitPrin) {
		this.loanInitPrin = loanInitPrin;
	}

	public List<WBPaymentFeeDTO> getFee() {
		return fee;
	}

	public void setFee(List<WBPaymentFeeDTO> fee) {
		this.fee = fee;
	}

	public BigDecimal getDownPayment() {
		return downPayment;
	}

	public void setDownPayment(BigDecimal downPayment) {
		this.downPayment = downPayment;
	}

	public BigDecimal getDownPaymentRatio() {
		return downPaymentRatio;
	}

	public void setDownPaymentRatio(BigDecimal downPaymentRatio) {
		this.downPaymentRatio = downPaymentRatio;
	}

	public Integer getLoanTerm() {
		return loanTerm;
	}

	public void setLoanTerm(Integer loanTerm) {
		this.loanTerm = loanTerm;
	}

	public BigDecimal getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(BigDecimal interestRate) {
		this.interestRate = interestRate;
	}

	public String getInterestRateType() {
		return interestRateType;
	}

	public void setInterestRateType(String interestRateType) {
		this.interestRateType = interestRateType;
	}

	public BigDecimal getUnearnedInterest() {
		return unearnedInterest;
	}

	public void setUnearnedInterest(BigDecimal unearnedInterest) {
		this.unearnedInterest = unearnedInterest;
	}

	public BigDecimal getCashDepositRatio() {
		return cashDepositRatio;
	}

	public void setCashDepositRatio(BigDecimal cashDepositRatio) {
		this.cashDepositRatio = cashDepositRatio;
	}

	public String getIsNewCar() {
		return isNewCar;
	}

	public void setIsNewCar(String isNewCar) {
		this.isNewCar = isNewCar;
	}

	public String getIsStageGuaranteeDealer() {
		return isStageGuaranteeDealer;
	}

	public void setIsStageGuaranteeDealer(String isStageGuaranteeDealer) {
		this.isStageGuaranteeDealer = isStageGuaranteeDealer;
	}

	public Date getMerIntStart() {
		return merIntStart;
	}

	public void setMerIntStart(Date merIntStart) {
		this.merIntStart = merIntStart;
	}

	public Integer getMerCycleDay() {
		return merCycleDay;
	}

	public void setMerCycleDay(Integer merCycleDay) {
		this.merCycleDay = merCycleDay;
	}

	public Date getMerExpireDate() {
		return merExpireDate;
	}

	public void setMerExpireDate(Date merExpireDate) {
		this.merExpireDate = merExpireDate;
	}

	public Date getRegistrateTime() {
		return registrateTime;
	}

	public void setRegistrateTime(Date registrateTime) {
		this.registrateTime = registrateTime;
	}

	public BigDecimal getInvoicePrice() {
		return invoicePrice;
	}

	public void setInvoicePrice(BigDecimal invoicePrice) {
		this.invoicePrice = invoicePrice;
	}

	public Date getInvoiceDate() {
		return invoiceDate;
	}

	public void setInvoiceDate(Date invoiceDate) {
		this.invoiceDate = invoiceDate;
	}

	public String getTxnCity() {
		return txnCity;
	}

	public void setTxnCity(String txnCity) {
		this.txnCity = txnCity;
	}

	public Date getCarPassDate() {
		return carPassDate;
	}

	public void setCarPassDate(Date carPassDate) {
		this.carPassDate = carPassDate;
	}

	public String getCityCode() {
		return cityCode;
	}

	public void setCityCode(String cityCode) {
		this.cityCode = cityCode;
	}

	public Date getInsExpireDate() {
		return insExpireDate;
	}

	public void setInsExpireDate(Date insExpireDate) {
		this.insExpireDate = insExpireDate;
	}

	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	public List<WBPaymentMerLoanSeqListDTO> getMerLoanSeqList() {
		return merLoanSeqList;
	}

	public void setMerLoanSeqList(List<WBPaymentMerLoanSeqListDTO> merLoanSeqList) {
		this.merLoanSeqList = merLoanSeqList;
	}

	public String getAcctName() {
		return acctName;
	}

	public void setAcctName(String acctName) {
		this.acctName = acctName;
	}

	public String getAcctNo() {
		return acctNo;
	}

	public void setAcctNo(String acctNo) {
		this.acctNo = acctNo;
	}

	public String getBranchNo() {
		return branchNo;
	}

	public void setBranchNo(String branchNo) {
		this.branchNo = branchNo;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getDealerName() {
		return dealerName;
	}

	public void setDealerName(String dealerName) {
		this.dealerName = dealerName;
	}

	public String getDealerLicenses() {
		return dealerLicenses;
	}

	public void setDealerLicenses(String dealerLicenses) {
		this.dealerLicenses = dealerLicenses;
	}

	public String getIsGpsActive() {
		return isGpsActive;
	}

	public void setIsGpsActive(String isGpsActive) {
		this.isGpsActive = isGpsActive;
	}

	public Date getGpsActiveTime() {
		return gpsActiveTime;
	}

	public void setGpsActiveTime(Date gpsActiveTime) {
		this.gpsActiveTime = gpsActiveTime;
	}

	public String getGpsCode() {
		return gpsCode;
	}

	public void setGpsCode(String gpsCode) {
		this.gpsCode = gpsCode;
	}

	public String getLongitude() {
		return longitude;
	}

	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}

	public String getLatitude() {
		return latitude;
	}

	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
}
