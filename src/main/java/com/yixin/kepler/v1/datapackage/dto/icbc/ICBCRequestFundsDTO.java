package com.yixin.kepler.v1.datapackage.dto.icbc;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 工行请款DTO
 * Package : com.yixin.kapler_v1.datapackage.dto
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年9月11日 上午9:50:11
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ICBCRequestFundsDTO implements Serializable {
	
	
    /**
	 * 
	 * @author YixinCapital -- chen.lin
	 *		   2018年9月11日 下午2:38:01
	 *
	 */
	private static final long serialVersionUID = -4993328403300823640L;

	/***
     * 业务订单号
     * 作为一笔分期业务的唯一标识
     * 易鑫产生
     * 规则   YYYYMMDD+7位序号
     */
    private String orderno;
	
    /**
     * 紧急标识
     * 0 普通 1 紧急
     */
    private String urgentflag;

    /**
     * 抵放模式
     */
    private String dfmode;
    
    /**
	 * 	补息标识 	0-不补息  1-补息
	 */
	private String txflag;
    
	/**
	 * 	补息方案  产品编号
	 */
	private String txplan;
	
	/**
	 * 	补息金额
	 */
	private String txamt;
	
    /**
     * 生产厂商
     */
    private String producer;

    /**
     * 汽车品牌
     */
    private String carbrand;

    /**
     * 款式规格
     */
    private String model;

    /**
     * 车辆价格
     */
    private String carprice;
	
    /**
     * 汽车厂商经销商名称（全称）
     */
    private String dealer;
    
    /**
     * 购车年月
     */
    private String buydate;
    
    /**
     * 车牌号码
     */
    private String licplatenum;
    
    /**
     * 车架号
     */
    private String vin;
    
    /**
     * 发动机号
     */
    private String engineno;
    
    /**
     * 贷款金额（车辆分期金额）
     */
    private String loanamt;
    
    /**
     * 服务费
     */
    private String servamt;

    /**
     * 总金额(贷款金额+服务费)
     */
    private String allamt;
    
    /**
     * 首付金额
     */
    private String ownpayamt;

    /**
     * 贷款成数
     */
    private String loancen;
    
    /**
     * 易鑫服务费融资额	String 	17 是
     */
    private String yxservamt;

    /**
     * 账户管理费融资额	String 	17 	是
     */
    private String accountmgfee;

    /**
     * 其他融资额	String 	17	否
     */
    private String otherfee;

    /**
     * 分期期数
     */
    private String planterm;

    /**
     * 4S车商代码
     */
    private String shopid;

    /**
     * 4S车商名称
     */
    private String shopname;

    /**
     * 文件传输标志
     */
    private String filetrans;

    /**
     * 打包文件名
     */
    private String filename;

    /**
     * 文件加密密码
     */
    private String filepasswd;

    /**
     * 文件字节数
     */
    private String filesize;
    

	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getUrgentflag() {
		return urgentflag;
	}

	public void setUrgentflag(String urgentflag) {
		this.urgentflag = urgentflag;
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

	public String getTxplan() {
		return txplan;
	}

	public void setTxplan(String txplan) {
		this.txplan = txplan;
	}

	public String getTxamt() {
		return txamt;
	}

	public void setTxamt(String txamt) {
		this.txamt = txamt;
	}

	public String getProducer() {
		return producer;
	}

	public void setProducer(String producer) {
		this.producer = producer;
	}

	public String getCarbrand() {
		return carbrand;
	}

	public void setCarbrand(String carbrand) {
		this.carbrand = carbrand;
	}

	public String getModel() {
		return model;
	}

	public void setModel(String model) {
		this.model = model;
	}

	public String getCarprice() {
		return carprice;
	}

	public void setCarprice(String carprice) {
		this.carprice = carprice;
	}

	public String getDealer() {
		return dealer;
	}

	public void setDealer(String dealer) {
		this.dealer = dealer;
	}

	public String getBuydate() {
		return buydate;
	}

	public void setBuydate(String buydate) {
		this.buydate = buydate;
	}

	public String getLicplatenum() {
		return licplatenum;
	}

	public void setLicplatenum(String licplatenum) {
		this.licplatenum = licplatenum;
	}

	public String getVin() {
		return vin;
	}

	public void setVin(String vin) {
		this.vin = vin;
	}

	public String getEngineno() {
		return engineno;
	}

	public void setEngineno(String engineno) {
		this.engineno = engineno;
	}

	public String getLoanamt() {
		return loanamt;
	}

	public void setLoanamt(String loanamt) {
		this.loanamt = loanamt;
	}

	public String getServamt() {
		return servamt;
	}

	public void setServamt(String servamt) {
		this.servamt = servamt;
	}

	public String getAllamt() {
		return allamt;
	}

	public void setAllamt(String allamt) {
		this.allamt = allamt;
	}

	public String getOwnpayamt() {
		return ownpayamt;
	}

	public void setOwnpayamt(String ownpayamt) {
		this.ownpayamt = ownpayamt;
	}

	public String getLoancen() {
		return loancen;
	}

	public void setLoancen(String loancen) {
		this.loancen = loancen;
	}

	public String getYxservamt() {
		return yxservamt;
	}

	public void setYxservamt(String yxservamt) {
		this.yxservamt = yxservamt;
	}

	public String getAccountmgfee() {
		return accountmgfee;
	}

	public void setAccountmgfee(String accountmgfee) {
		this.accountmgfee = accountmgfee;
	}

	public String getOtherfee() {
		return otherfee;
	}

	public void setOtherfee(String otherfee) {
		this.otherfee = otherfee;
	}

	public String getPlanterm() {
		return planterm;
	}

	public void setPlanterm(String planterm) {
		this.planterm = planterm;
	}

	public String getShopid() {
		return shopid;
	}

	public void setShopid(String shopid) {
		this.shopid = shopid;
	}

	public String getShopname() {
		return shopname;
	}

	public void setShopname(String shopname) {
		this.shopname = shopname;
	}

	public String getFiletrans() {
		return filetrans;
	}

	public void setFiletrans(String filetrans) {
		this.filetrans = filetrans;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepasswd() {
		return filepasswd;
	}

	public void setFilepasswd(String filepasswd) {
		this.filepasswd = filepasswd;
	}

	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}
}
