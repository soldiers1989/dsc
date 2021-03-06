package com.yixin.dsc.dto.order;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yixin.common.utils.serializer.JsonDateDeserializer;
import com.yixin.common.utils.serializer.JsonDateTimeSerializer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 申请车辆信息
 * Package : com.yixin.dsc.entity
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月4日 下午2:15:38
		*
		*/
public class DscSalesApplyCarDTO implements Serializable{
	
	
	private static final long serialVersionUID = -7551550957300096006L;

	
    private String id;

	/**
	 * 订单主表ID
	 */
	private String mainId;


	/**
	 * 	制造商
	 */
	private String azcs;
	/**
	 * 	品牌
	 */
	private String appName;
	/**
	 * 	车系
	 */
	private String acxiName;
	/**
	 * 	车型
	 */
	private String acxName;
	/**
	 * 	新车指导价
	 */
	private BigDecimal fzdj;
	/**
	 * 	实际销售价
	 */
	private BigDecimal fxsj;
	/**
	 * 	购车目的
	 */
	private String agcmd;
	/**
	 * 	预计上牌省份
	 */
	private String ayjspsfName;
	/**
	 * 	预计上牌城市
	 */
	private String ayjspcsName;
	/**
	 * 	是否送拍照
	 */
	private String asfspz;
	/**
	 * 	车辆开票单位
	 */
	private String axhdwName;
	/**
	 * 	客户来源
	 */
	private String clientSource;
	/**
	 * 	网点名称
	 */
	private String branchName;
	/**
	 * 	公司三级销售管理
	 */
	private String salesmans;
	/**
	 * 	分公司金融经理
	 */
	private String financialManagerName;
	
	
	
	/**
	 * 	公里数(万公里)
	 */
	private BigDecimal fescgls;
	/**
	 * 	提报预估价
	 */
	private BigDecimal fescpgjebg;
	/**
	 * 	车架号（VIN码）
	 */
	private String acjh;

	/**
	 * 发动机号
	 */
	private String afdjh;


	/**
	 * 	出厂日期
	 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date descccrq;
	/**
	 * 	初登日期
	 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date desccdrq;
	/**
	 * 	当前牌照
	 */
	private String acarnojc;
	/**
	 * 	二手车评估机构
	 */
	private String oldcarcompanyName;
	
	/**
	 * 	座位数
	 */
	private Integer izws;
	/**
	 * 	排量
	 */
	private String aclpl;
	/**
	 * 	车龄(月)
	 */
	private Integer icl;


	/**
	 * 车辆用途 aclyt
	 */
	private String aclyt;

	/**
	 * 	车辆开票金额（fclfpje）
	 */
	private BigDecimal fclfpje;
	/**
	 * 	车辆开票日期（dclkprq）
	 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date dclkprq;
	/**
	 * 是否是新能源车
	 */
	private String isXnyc;


	/**
	 * 是否是LCV
	 */
	private String isLCV;
	/**
	 *保证金剩余金
	 */
	private BigDecimal fbzj;
	/**
	 * 尾付金额
	 */
	private BigDecimal fsfje;
	/**
	 * 车辆类型 ---新车、资源车、二手车
	 */
	private String acllx;

	/**
	 * 二手车评估价格
	 */
	private BigDecimal appraisalprice;
	
	
	//============== 2018/07/11 新增字段  =============================
	/**
	 * 二手车评估机构  1:穗圣、2:精真估、3:看车网、0:免评估、4:线上评估、 5:人人车
	 */
	private String oldcarcompany;
	
	/**
	 * alix拿到评估报告的时间  0:免评估时 没有评估报告，就没有评估时间
	 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date assessTime;
	
	/**
	 * 车辆ID，四级 1111_2222_3333_4444
	 */
	private String carId;
	
	/**
	 * 二手车保险到日期（二手车才有）
	 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date nsuranceMaturityDate;
	
	/**
	 * 车辆类型"新车-Y二手车-N"
	 */
	private String isNewCar;
	
	/**
	 * 车辆性质 01：乘用车&微面  02：轻卡  03：LCV（皮卡、微卡）
	 */
	private String vehiclePro;
	
	/**
	 * 过户次数,新车为0
	 */
	private Integer xfrTimes;
	
	/**
	 * （LCV用途）0:非商用  1:商用 
	 */
	private String purposeofusingforlcv;
	//============== 2018/07/11 新增字段  =============================
	
	//============== 2018/09/05  新增字段开始  add by wangwenlong  =============================
	/**
	 * 是否返贷 0/否  1/是
	 */
	private String asffd; 
	
	/**
	 * 车辆过户完成日期
	 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date carPassDate; 
	//============== 2018/09/05  新增字段结束  =============================
	/**
	 * 购车年月--工行项目新增字段（20180925）
	 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date buyCarTime;

	//============== 2018/10/17  工行新增字段开始  add by gumanxue  =============================
	/**
	 * 意向银行码值 0 ：可做所有银行 1.工行优先（没匹配上不做工行，走自营）  2.不做工行
	 */
	private String ayxzryh;
	/**
	 * 是否是平行进口车
	 */
	private String asfwpxjkc;


	public String getAsfwpxjkc() {
		return asfwpxjkc;
	}

	public void setAsfwpxjkc(String asfwpxjkc) {
		this.asfwpxjkc = asfwpxjkc;
	}

	public Date getBuyCarTime() {
		return buyCarTime;
	}

	public void setBuyCarTime(Date buyCarTime) {
		this.buyCarTime = buyCarTime;
	}

	public String getId() {
		return id;
	}

	public String getAsffd() {
		return asffd;
	}

	public void setAsffd(String asffd) {
		this.asffd = asffd;
	}

	public Date getCarPassDate() {
		return carPassDate;
	}

	public void setCarPassDate(Date carPassDate) {
		this.carPassDate = carPassDate;
	}

	public String getPurposeofusingforlcv() {
		return purposeofusingforlcv;
	}

	public void setPurposeofusingforlcv(String purposeofusingforlcv) {
		this.purposeofusingforlcv = purposeofusingforlcv;
	}

	public String getOldcarcompany() {
		return oldcarcompany;
	}

	public void setOldcarcompany(String oldcarcompany) {
		this.oldcarcompany = oldcarcompany;
	}

	public Date getAssessTime() {
		return assessTime;
	}

	public void setAssessTime(Date assessTime) {
		this.assessTime = assessTime;
	}

	public String getCarId() {
		return carId;
	}

	public void setCarId(String carId) {
		this.carId = carId;
	}

	public Date getNsuranceMaturityDate() {
		return nsuranceMaturityDate;
	}

	public void setNsuranceMaturityDate(Date nsuranceMaturityDate) {
		this.nsuranceMaturityDate = nsuranceMaturityDate;
	}

	public String getIsNewCar() {
		return isNewCar;
	}

	public void setIsNewCar(String isNewCar) {
		this.isNewCar = isNewCar;
	}

	public String getVehiclePro() {
		return vehiclePro;
	}

	public void setVehiclePro(String vehiclePro) {
		this.vehiclePro = vehiclePro;
	}

	public Integer getXfrTimes() {
		return xfrTimes;
	}

	public void setXfrTimes(Integer xfrTimes) {
		this.xfrTimes = xfrTimes;
	}

	public void setId(String id) {
		this.id = id;
	}

	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	public String getAzcs() {
		return azcs;
	}

	public void setAzcs(String azcs) {
		this.azcs = azcs;
	}

	public String getAppName() {
		return appName;
	}

	public void setAppName(String appName) {
		this.appName = appName;
	}

	public String getAcxiName() {
		return acxiName;
	}

	public void setAcxiName(String acxiName) {
		this.acxiName = acxiName;
	}

	public String getAcxName() {
		return acxName;
	}

	public void setAcxName(String acxName) {
		this.acxName = acxName;
	}

	public BigDecimal getFzdj() {
		return fzdj;
	}

	public void setFzdj(BigDecimal fzdj) {
		this.fzdj = fzdj;
	}

	public BigDecimal getFxsj() {
		return fxsj;
	}

	public void setFxsj(BigDecimal fxsj) {
		this.fxsj = fxsj;
	}

	public String getAgcmd() {
		return agcmd;
	}

	public void setAgcmd(String agcmd) {
		this.agcmd = agcmd;
	}

	public String getAyjspsfName() {
		return ayjspsfName;
	}

	public void setAyjspsfName(String ayjspsfName) {
		this.ayjspsfName = ayjspsfName;
	}

	public String getAyjspcsName() {
		return ayjspcsName;
	}

	public void setAyjspcsName(String ayjspcsName) {
		this.ayjspcsName = ayjspcsName;
	}

	public String getAsfspz() {
		return asfspz;
	}

	public void setAsfspz(String asfspz) {
		this.asfspz = asfspz;
	}

	public String getAxhdwName() {
		return axhdwName;
	}

	public void setAxhdwName(String axhdwName) {
		this.axhdwName = axhdwName;
	}

	public String getClientSource() {
		return clientSource;
	}

	public void setClientSource(String clientSource) {
		this.clientSource = clientSource;
	}

	public String getBranchName() {
		return branchName;
	}

	public void setBranchName(String branchName) {
		this.branchName = branchName;
	}

	public String getSalesmans() {
		return salesmans;
	}

	public void setSalesmans(String salesmans) {
		this.salesmans = salesmans;
	}

	public String getFinancialManagerName() {
		return financialManagerName;
	}

	public void setFinancialManagerName(String financialManagerName) {
		this.financialManagerName = financialManagerName;
	}

	public BigDecimal getFescgls() {
		return fescgls;
	}

	public void setFescgls(BigDecimal fescgls) {
		this.fescgls = fescgls;
	}

	public BigDecimal getFescpgjebg() {
		return fescpgjebg;
	}

	public void setFescpgjebg(BigDecimal fescpgjebg) {
		this.fescpgjebg = fescpgjebg;
	}

	public String getAcjh() {
		return acjh;
	}

	public void setAcjh(String acjh) {
		this.acjh = acjh;
	}

	public Date getDescccrq() {
		return descccrq;
	}

	public void setDescccrq(Date descccrq) {
		this.descccrq = descccrq;
	}

	public Date getDesccdrq() {
		return desccdrq;
	}

	public void setDesccdrq(Date desccdrq) {
		this.desccdrq = desccdrq;
	}

	public String getAcarnojc() {
		return acarnojc;
	}

	public void setAcarnojc(String acarnojc) {
		this.acarnojc = acarnojc;
	}

	public String getOldcarcompanyName() {
		return oldcarcompanyName;
	}

	public void setOldcarcompanyName(String oldcarcompanyName) {
		this.oldcarcompanyName = oldcarcompanyName;
	}

	public Integer getIzws() {
		return izws;
	}

	public void setIzws(Integer izws) {
		this.izws = izws;
	}

	public String getAclpl() {
		return aclpl;
	}

	public void setAclpl(String aclpl) {
		this.aclpl = aclpl;
	}

	public Integer getIcl() {
		return icl;
	}

	public void setIcl(Integer icl) {
		this.icl = icl;
	}

	public String getAclyt() {
		return aclyt;
	}

	public void setAclyt(String aclyt) {
		this.aclyt = aclyt;
	}

	public BigDecimal getFclfpje() {
		return fclfpje;
	}

	public void setFclfpje(BigDecimal fclfpje) {
		this.fclfpje = fclfpje;
	}

	public Date getDclkprq() {
		return dclkprq;
	}

	public void setDclkprq(Date dclkprq) {
		this.dclkprq = dclkprq;
	}

	public String getIsXnyc() {
		return isXnyc;
	}

	public void setIsXnyc(String isXnyc) {
		this.isXnyc = isXnyc;
	}

	public String getIsLCV() {
		return isLCV;
	}

	public void setIsLCV(String isLCV) {
		this.isLCV = isLCV;
	}

	public BigDecimal getFbzj() {
		return fbzj;
	}

	public void setFbzj(BigDecimal fbzj) {
		this.fbzj = fbzj;
	}

	public BigDecimal getFsfje() {
		return fsfje;
	}

	public void setFsfje(BigDecimal fsfje) {
		this.fsfje = fsfje;
	}

	public String getAcllx() {
		return acllx;
	}

	public void setAcllx(String acllx) {
		this.acllx = acllx;
	}

	public BigDecimal getAppraisalprice() {
		return appraisalprice;
	}

	public void setAppraisalprice(BigDecimal appraisalprice) {
		this.appraisalprice = appraisalprice;
	}

	public String getAfdjh() {
		return afdjh;
	}

	public void setAfdjh(String afdjh) {
		this.afdjh = afdjh;
	}

	public String getAyxzryh() {
		return ayxzryh;
	}

	public void setAyxzryh(String ayxzryh) {
		this.ayxzryh = ayxzryh;
	}
}
