package com.yixin.dsc.dto.order;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yixin.common.utils.serializer.JsonDateDeserializer;
import com.yixin.common.utils.serializer.JsonDateTimeSerializer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

public class DscSalesApplyMainDTO implements Serializable {
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

    private String id;

	/**
	 * 申请编号
	 */
	private String applyNo;

	/**
	 * 产品编号
	 */
	private String productNo;
	
	/**
	 * 经销商渠道编码
	 */
	private String dealerChannelCode;
	
	/**
	 * 经销商渠道名称
	 */
	private String dealerChannelName;

	/**
	 * 发起融租公司编号
	 */
	private String rentingCompanyCode;

	/**
	 * 资方id
	 */
	private String capitalId;

	/**
	 * 还款卡归属人
	 */
	private String ahkkgsr;
	
	/**
	 * 持卡人身份证号码
	 */
	private String ackrzjhm;

	/**
	 * 还款卡账户类型
	 */
	private String ahkrkzhlx;

	/**
	 * 企业开户支行
	 */
	private String akhrkhhzh;

	/**
	 * 借记卡帐号
	 */
	private String ahkrjjkzh;

	/**
	 * 开户名
	 */
	private String ahkrkhm;

	/**
	 * 对公收款方式
	 */
	private String adgskfs;

	/**
	 * 提报信息备注
	 */
	private String aremark;
	/**
	 * 还款频率
	 */
	private String ahkpl;	
	/**
	 * 还款方式
	 */
	private String ahkfs;	
	/**
	 * 利率类型
	 */
	private String allfs;
	/**
	 * GPS价格类型
	 */
	private String agpsjglx; 
	/**
	 * 融资期限
	 */
	private String arzqx;
	/**
	 * 抵扣类型
	 */
	private String addlxlx;

	/**
	 * 合作商产品类型
	 */
	private String acplx;

	/**
	 * 还款卡
	 */
	private String afjid;

	/**
	 * 合作渠道一级科目
	 */
	private String admsx;
	/**
	 * 是否融责信宝
	 */
	private String asfrzxb;
	/**
	 * 抵押权人
	 */
	private String adyqr;
	/**
	 * 车辆抵押城市
	 */
	private String acldycs;

	/***
	 * 经销商收款额
	 */
	private BigDecimal dealerCollectAmount;
	/**
	 *alix放款日期
	 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date alixLoanDate;

	//============== 2018/07/11 新增字段  =============================
	
	/**
	 *	所属机构/用户所在机构
	 */
	private String affiliatedInstitutions;
	
	/**
	 *	易鑫验证四要素银行预留手机号码
	 */
	private String ackrsjhm;
	
	/**
	 *	经销商渠道所在市
	 */
	private String acsCode;
	
	/**
	 *	经销商渠道所在市名称
	 */
	private String acsName;
	
	/**
	 *	经销商渠道详细地址
	 */
	private String admdz;
	
	/**
	 *	经销商渠道id
	 */
	private String aid;
	
	/**
	 *	经销商渠道所在省
	 */
	private String asfCode;
	
	/**
	 *	经销商渠道所在省名称
	 */
	private String asfName;
	
	/**
	 *	订单来源(APP,PC)
	 */
	private String orderSource;
	
	/**
	 *	提报人姓名
	 */
	private String tlrName;
	
	/**
	 *	提报人域账号
	 */
	private String tlrNo;
	
	/**
	 *	提报人电话
	 */
	private String phone;
	
	/**
	 *	是否电核标记Y-是N-否
	 */
	private String telVerification;

	/**
	 * 租赁类型
	 */
	private String leaseType;
	
	//============== 2018/07/11 新增字段  =============================

	//============== 2018/08/30  新增字段  =============================
	/**
	 * GPS方案是否对私 0/否 1/是
	 */
	private String gpsWhetherPrivate;

	//============== 2018/09/05  新增字段开始  add by wangwenlong  =============================
	/**
	 * 提报日期
	 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date atbrq; 
	//============== 2018/09/05  新增字段结束  =============================
	
	//============== 2018/09/07  新增字段开始  add by wangwenlong  =============================
	/**
	 * 分公司代码
	 */
	private String afgsdm;
	
	/**
	 * 分公司名称
	 */
	private String afgsmc;
	
	/**
	 * 分公司名称
	 */
	private String apqdm;
	//============== 2018/09/07  新增字段结束  =============================


	//============== 2018/09/13  工行新增字段开始  =============================
	// 业务类型
	private String businesstype;

	// 抵押权
	private String mortgage;

	// 抵放模式
	private String dfmode;

	// 贴息标识
	private String txflag;

	// 补息金额
	private BigDecimal txamt;

	//============== 2018/09/13  工行新增字段结束  =============================

	//===============2018-09-26 新增字段开始===================
	/**
	 * 协议签约编号
	 */
	private String signNo;

	/**
	 * 银行编码
	 */
	private String bankCode;

	//===============2018-09-26 新增字段结束===================
	/**
	 * 银行行号
	 */
	private String akhrkhh;
	/**
	 * 审核时间
	 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date ayxxstgsj;
	/**
	 * 对客利率
	 */
	private BigDecimal adklv;

	public String getAkhrkhh() {
		return akhrkhh;
	}

	public void setAkhrkhh(String akhrkhh) {
		this.akhrkhh = akhrkhh;
	}

	public Date getAyxxstgsj() {
		return ayxxstgsj;
	}

	public void setAyxxstgsj(Date ayxxstgsj) {
		this.ayxxstgsj = ayxxstgsj;
	}

	public BigDecimal getAdklv() {
		return adklv;
	}

	public void setAdklv(BigDecimal adklv) {
		this.adklv = adklv;
	}

	public String getSignNo() {
		return signNo;
	}

	public void setSignNo(String signNo) {
		this.signNo = signNo;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getGpsWhetherPrivate() {
		return gpsWhetherPrivate;
	}

	public String getAfgsdm() {
		return afgsdm;
	}

	public void setAfgsdm(String afgsdm) {
		this.afgsdm = afgsdm;
	}

	public String getAfgsmc() {
		return afgsmc;
	}

	public void setAfgsmc(String afgsmc) {
		this.afgsmc = afgsmc;
	}

	public String getApqdm() {
		return apqdm;
	}

	public void setApqdm(String apqdm) {
		this.apqdm = apqdm;
	}

	public Date getAtbrq() {
		return atbrq;
	}

	public void setAtbrq(Date atbrq) {
		this.atbrq = atbrq;
	}

	public void setGpsWhetherPrivate(String gpsWhetherPrivate) {
		this.gpsWhetherPrivate = gpsWhetherPrivate;
	}

	public String getLeaseType() {
		return leaseType;
	}

	public void setLeaseType(String leaseType) {
		this.leaseType = leaseType;
	}

	public String getId() {
		return id;
	}

	public String getAffiliatedInstitutions() {
		return affiliatedInstitutions;
	}

	public void setAffiliatedInstitutions(String affiliatedInstitutions) {
		this.affiliatedInstitutions = affiliatedInstitutions;
	}

	public String getAckrsjhm() {
		return ackrsjhm;
	}

	public void setAckrsjhm(String ackrsjhm) {
		this.ackrsjhm = ackrsjhm;
	}

	public String getAcsCode() {
		return acsCode;
	}

	public void setAcsCode(String acsCode) {
		this.acsCode = acsCode;
	}

	public String getAcsName() {
		return acsName;
	}

	public void setAcsName(String acsName) {
		this.acsName = acsName;
	}

	public String getAdmdz() {
		return admdz;
	}

	public void setAdmdz(String admdz) {
		this.admdz = admdz;
	}

	public String getAid() {
		return aid;
	}

	public void setAid(String aid) {
		this.aid = aid;
	}

	public String getAsfCode() {
		return asfCode;
	}

	public void setAsfCode(String asfCode) {
		this.asfCode = asfCode;
	}

	public String getAsfName() {
		return asfName;
	}

	public void setAsfName(String asfName) {
		this.asfName = asfName;
	}

	public String getOrderSource() {
		return orderSource;
	}

	public void setOrderSource(String orderSource) {
		this.orderSource = orderSource;
	}

	public String getTlrName() {
		return tlrName;
	}

	public void setTlrName(String tlrName) {
		this.tlrName = tlrName;
	}

	public String getTlrNo() {
		return tlrNo;
	}

	public void setTlrNo(String tlrNo) {
		this.tlrNo = tlrNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getTelVerification() {
		return telVerification;
	}

	public void setTelVerification(String telVerification) {
		this.telVerification = telVerification;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getProductNo() {
		return productNo;
	}

	public void setProductNo(String productNo) {
		this.productNo = productNo;
	}

	public String getCapitalId() {
		return capitalId;
	}

	public void setCapitalId(String capitalId) {
		this.capitalId = capitalId;
	}

	public String getAhkkgsr() {
		return ahkkgsr;
	}

	public void setAhkkgsr(String ahkkgsr) {
		this.ahkkgsr = ahkkgsr;
	}

	public String getAckrzjhm() {
		return ackrzjhm;
	}

	public void setAckrzjhm(String ackrzjhm) {
		this.ackrzjhm = ackrzjhm;
	}

	public String getAhkrkzhlx() {
		return ahkrkzhlx;
	}

	public void setAhkrkzhlx(String ahkrkzhlx) {
		this.ahkrkzhlx = ahkrkzhlx;
	}

	public String getAkhrkhhzh() {
		return akhrkhhzh;
	}

	public void setAkhrkhhzh(String akhrkhhzh) {
		this.akhrkhhzh = akhrkhhzh;
	}

	public String getAhkrjjkzh() {
		return ahkrjjkzh;
	}

	public void setAhkrjjkzh(String ahkrjjkzh) {
		this.ahkrjjkzh = ahkrjjkzh;
	}

	public String getAhkrkhm() {
		return ahkrkhm;
	}

	public void setAhkrkhm(String ahkrkhm) {
		this.ahkrkhm = ahkrkhm;
	}

	public String getAdgskfs() {
		return adgskfs;
	}

	public void setAdgskfs(String adgskfs) {
		this.adgskfs = adgskfs;
	}

	public String getAremark() {
		return aremark;
	}

	public void setAremark(String aremark) {
		this.aremark = aremark;
	}

	public String getAhkpl() {
		return ahkpl;
	}

	public String getDealerChannelCode() {
		return dealerChannelCode;
	}

	public void setDealerChannelCode(String dealerChannelCode) {
		this.dealerChannelCode = dealerChannelCode;
	}

	public String getDealerChannelName() {
		return dealerChannelName;
	}

	public void setDealerChannelName(String dealerChannelName) {
		this.dealerChannelName = dealerChannelName;
	}

	public String getRentingCompanyCode() {
		return rentingCompanyCode;
	}

	public void setRentingCompanyCode(String rentingCompanyCode) {
		this.rentingCompanyCode = rentingCompanyCode;
	}

	public void setAhkpl(String ahkpl) {
		this.ahkpl = ahkpl;
	}

	public String getAhkfs() {
		return ahkfs;
	}

	public void setAhkfs(String ahkfs) {
		this.ahkfs = ahkfs;
	}

	public String getAllfs() {
		return allfs;
	}

	public void setAllfs(String allfs) {
		this.allfs = allfs;
	}

	public String getAgpsjglx() {
		return agpsjglx;
	}

	public void setAgpsjglx(String agpsjglx) {
		this.agpsjglx = agpsjglx;
	}

	public String getArzqx() {
		return arzqx;
	}

	public void setArzqx(String arzqx) {
		this.arzqx = arzqx;
	}

	public String getAddlxlx() {
		return addlxlx;
	}

	public void setAddlxlx(String addlxlx) {
		this.addlxlx = addlxlx;
	}

	public String getAcplx() {
		return acplx;
	}

	public void setAcplx(String acplx) {
		this.acplx = acplx;
	}

	public String getAfjid() {
		return afjid;
	}

	public void setAfjid(String afjid) {
		this.afjid = afjid;
	}

	public String getAdmsx() {
		return admsx;
	}

	public void setAdmsx(String admsx) {
		this.admsx = admsx;
	}

	public String getAsfrzxb() {
		return asfrzxb;
	}

	public void setAsfrzxb(String asfrzxb) {
		this.asfrzxb = asfrzxb;
	}

	public String getAdyqr() {
		return adyqr;
	}

	public void setAdyqr(String adyqr) {
		this.adyqr = adyqr;
	}

	public String getAcldycs() {
		return acldycs;
	}

	public void setAcldycs(String acldycs) {
		this.acldycs = acldycs;
	}

	public BigDecimal getDealerCollectAmount() {
		return dealerCollectAmount;
	}

	public void setDealerCollectAmount(BigDecimal dealerCollectAmount) {
		this.dealerCollectAmount = dealerCollectAmount;
	}

	public Date getAlixLoanDate() {
		return alixLoanDate;
	}

	public void setAlixLoanDate(Date alixLoanDate) {
		this.alixLoanDate = alixLoanDate;
	}

	public String getBusinesstype() {
		return businesstype;
	}

	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}

	public String getMortgage() {
		return mortgage;
	}

	public void setMortgage(String mortgage) {
		this.mortgage = mortgage;
	}

	public String getDfmode() {
		return dfmode;
	}

	public void setDfmode(String dfmode) {
		this.dfmode = dfmode;
	}

	public String getTxflag() {
		return txflag;
	}

	public void setTxflag(String txflag) {
		this.txflag = txflag;
	}

	public BigDecimal getTxamt() {
		return txamt;
	}

	public void setTxamt(BigDecimal txamt) {
		this.txamt = txamt;
	}

}
