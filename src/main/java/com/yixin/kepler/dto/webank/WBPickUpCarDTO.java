package com.yixin.kepler.dto.webank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * 提车
 * @author YixinCapital -- chenjiacheng
 *         2018年07月10日 15:27
 **/

public class WBPickUpCarDTO extends WBCommonReqDTO{

	//订单业务主键  开始

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

	//订单业务主键  结束

	//订单车辆主键  开始

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

	//订单车辆主键  结束

	//交易信息域 开始

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

	//交易信息域 结束

	//抵押信息域 开始

	/**
	 * 押权所有人
	 */
	@JsonProperty("MORTGAGE_OWNER")
	private String mortgageOwner;

	/**
	 * 抵押办理城市
	 */
	@JsonProperty("MORTGAGE_CITY")
	private String mortgageCity;

	/**
	 * 上牌日期
	 */
	@JsonProperty("REGISTRATE_TIME")
	@JsonFormat(pattern = "yyyyMMdd")
	private Date registrateTime;

	/**
	 * 车牌号
	 */
	@JsonProperty("PLATE_NUMBER")
	private String plateNumber;

	/**
	 * 抵押登记日期
	 */
	@JsonProperty("MORTGAGE_REG_DATE")
	@JsonFormat(pattern = "yyyyMMdd")
	private Date mortgageRegDate;

	/**
	 * 抵押登记完成指令  Y 是  N 否
	 */
	@JsonProperty("IS_MORTGAGE_REG")
	private String isMortgageReg;

	/**
	 * 发动机号
	 */
	@JsonProperty("ENGINE_CODE")
	private String engineCode;

	//抵押信息域 结束

	//发票信息域 开始

	/**
	 * 发票开票金额
	 */
	@JsonProperty("INVOICE_PRICE")
	private BigDecimal invoicePrice;

	/**
	 * 发票开票日期
	 */
	@JsonProperty("INVOICE_DATE")
	@JsonFormat(pattern = "yyyyMMdd")
	private Date invoiceDate;

	/**
	 * 购置税实际金额
	 */
	@JsonProperty("TAX_PRICE")
	private BigDecimal taxPrice;

	/**
	 * 保单实际金额
	 */
	@JsonProperty("INS_PRICE")
	private BigDecimal insPrice;

	/**
	 * GPS合同实际金额
	 */
	@JsonProperty("GPS_PRICE")
	private BigDecimal gpsPrice;

	/**
	 * 保险到期日
	 */
	@JsonProperty("INS_EXPIRE_DATE")
	@JsonFormat(pattern = "yyyyMMdd")
	private Date insExpireDate;

	//发票信息域 结束

	//提车域开始

	/**
	 * 材料清单
	 */
	@JsonProperty("PROOFS")
	private List<WBPickUpCarProofsDTO> proofs;

	//提车域结束


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

	public String getMortgageOwner() {
		return mortgageOwner;
	}

	public void setMortgageOwner(String mortgageOwner) {
		this.mortgageOwner = mortgageOwner;
	}

	public String getMortgageCity() {
		return mortgageCity;
	}

	public void setMortgageCity(String mortgageCity) {
		this.mortgageCity = mortgageCity;
	}

	public Date getRegistrateTime() {
		return registrateTime;
	}

	public void setRegistrateTime(Date registrateTime) {
		this.registrateTime = registrateTime;
	}

	public String getPlateNumber() {
		return plateNumber;
	}

	public void setPlateNumber(String plateNumber) {
		this.plateNumber = plateNumber;
	}

	public Date getMortgageRegDate() {
		return mortgageRegDate;
	}

	public void setMortgageRegDate(Date mortgageRegDate) {
		this.mortgageRegDate = mortgageRegDate;
	}

	public String getIsMortgageReg() {
		return isMortgageReg;
	}

	public void setIsMortgageReg(String isMortgageReg) {
		this.isMortgageReg = isMortgageReg;
	}

	public String getEngineCode() {
		return engineCode;
	}

	public void setEngineCode(String engineCode) {
		this.engineCode = engineCode;
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

	public BigDecimal getTaxPrice() {
		return taxPrice;
	}

	public void setTaxPrice(BigDecimal taxPrice) {
		this.taxPrice = taxPrice;
	}

	public BigDecimal getInsPrice() {
		return insPrice;
	}

	public void setInsPrice(BigDecimal insPrice) {
		this.insPrice = insPrice;
	}

	public BigDecimal getGpsPrice() {
		return gpsPrice;
	}

	public void setGpsPrice(BigDecimal gpsPrice) {
		this.gpsPrice = gpsPrice;
	}

	public Date getInsExpireDate() {
		return insExpireDate;
	}

	public void setInsExpireDate(Date insExpireDate) {
		this.insExpireDate = insExpireDate;
	}

	public List<WBPickUpCarProofsDTO> getProofs() {
		return proofs;
	}

	public void setProofs(List<WBPickUpCarProofsDTO> proofs) {
		this.proofs = proofs;
	}
}
