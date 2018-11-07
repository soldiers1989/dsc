package com.yixin.kepler.v1.datapackage.dto.icbc;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 工行初审DTO
 * Package : com.yixin.kapler_v1.datapackage.dto
 *
 * @author YixinCapital -- gumanxue
 *		   2018年9月7日 下午4:50:56
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class ICBCFirstTrialDataDTO  implements Serializable {

	/**
	 *
	 * @author YixinCapital -- changyy
	 *		   2017年8月7日 下午3:18:22
	 *
	 */
	private static final long serialVersionUID = -8428414411303791803L;

	/**
	 *
	 */
	private String businesstype;
	/**
	 * 抵押模式
	 */
	private String mortgage;
	/**
	 * 抵放模式
	 */
	private String dfmode;

	/**
	 * 	易鑫服务费融资额
	 */
	private String yxservamt;
	/**
	 * 	购置税融资额
	 */
	private String purchasetax;

	/**
	 * 	账户管理费融资额
	 */
	private String accountmgfee;
	/**
	 * 	精品加装融资额
	 */
	private String decoratefee;
	/**
	 * 	GPS融资额
	 */
	private String gpsfee;
	/**
	 * 	保险融资额
	 */
	private String insurance;
	/**
	 * 	责信保融资额
	 */
	private String zxbinsurance;
	/**
	 *	延保融资额
	 */
	private String exinsurance;
	/**
	 * 	其他融资额
	 */
	private String otherfee;
	/**
	 * 	车辆估价
	 */
	private String carassessment;

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
	 * 业务订单号
	 */
	private String orderno;


	/**
	 * 紧急标示
	 */
	private String urgentflag;

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
	 * 分期期数
	 */
	private String planterm;

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
	 * 证件类型
	 */
	private String custsort;

	/**
	 * 证件号码
	 */
	private String custcode;

	/**
	 * 证件是否长期有效(易鑫暂无)
	 */
	private String timeless;

	/**
	 * 证件有效期(易鑫暂无)
	 */
	private String statdate;

	/**
	 * 性别
	 */
	private String sex;

	/**
	 * 姓名
	 */
	private String chnsname;

	/**
	 * 姓名拼音或英文名（易鑫暂无）
	 */
	private String engname;

	/**
	 * 婚姻状况
	 */
	private String mrtlstat;

	/**
	 * 受教育程度
	 */
	private String edulvl;

	/**
	 * 住宅状况
	 */
	private String homestat;

	/**
	 * 住宅地址省份
	 */
	private String hprovince;

	/**
	 * 住宅地址市
	 */
	private String hcity;

	/**
	 * 住宅地址县(区)
	 */
	private String hcounty;

	/**
	 * 住宅地址
	 */
	private String haddress;

	/**
	 * 住宅邮编
	 */
	private String homezip;

	/**
	 * 何时入住现址（易鑫暂无）
	 */
	private String indate;

	/**
	 * 住宅电话区号（易鑫暂无）
	 */
	private String hphonzono;

	/**
	 * 住宅电话号码（易鑫暂无）
	 */
	private String hphoneno;

	/**
	 * 手机号码
	 */
	private String mvblno;

	/**
	 * 电子邮件（易鑫暂无）
	 */
	private String email;

	/**
	 * 工作单位名称
	 */
	private String unitname;

	/**
	 * 职务
	 */
	private String duty;

	/**
	 * 单位电话区号（易鑫暂无）
	 */
	private String cophozono;

	/**
	 * 单位电话号码（易鑫暂无）
	 */
	private String cophoneno;

	/**
	 * 单位地址省份
	 */
	private String cprovince;

	/**
	 * 单位地址市
	 */
	private String ccity;

	/**
	 * 单位地址县(区)（易鑫暂无）
	 */
	private String ccounty;

	/**
	 * 工作单位地址
	 */
	private String caddress;

	/**
	 * 单位邮编（易鑫暂无）
	 */
	private String corpzip;

	/**
	 * 进入现单位工作时间（易鑫暂无）
	 */
	private String joindate;

	/**
	 * 本人年收入
	 */
	private String yearincome;

	/**
	 * 单位性质
	 */
	private String modelcode;

	/**
	 * 职业及职级
	 */
	private String occptn;

	/**
	 * 联系人一姓名
	 */
	private String reltname1;

	/**
	 * 联系人一与主卡联系人关系
	 */
	private String reltship1;

	/**
	 * 联系人一联系电话区号
	 */
	private String reltphzon1;

	/**
	 * 联系人一联系电话号码
	 */
	private String relaphone1;

	/**
	 * 联系人一手机
	 */
	private String reltmobl1;

	/**
	 * 联系人二姓名
	 */
	private String reltname2;

	/**
	 * 联系人二与主卡联系人关系
	 */
	private String reltship2;

	/**
	 * 联系人二手机
	 */
	private String reltmobl2;

	/**
	 * 联系人二联系电话区号
	 */
	private String rtcophzn2;

	/**
	 * 联系人二联系电话号码
	 */
	private String rtcophon2;

	/**
	 * 国籍
	 */
	private String primnat;

	/**
	 * 还款人月均总收入
	 */
	private String monthincome;

	//无关联客户的时候可以非必输
	/**
	 * 关联客户与申请人关系
	 */
	private String relationship;

	/**
	 * 关联客户个人年收入
	 */
	private String yearincome1;

	//配偶信息（申请金额10万以下非必需）

	/**
	 * 姓名
	 */
	private String chnsname1;

	/**
	 * 与申请人关系
	 */
	private String relation1;

	/**
	 * 证件类型
	 */
	private String custsort1;

	/**
	 * 证件号码
	 */
	private String custcode1;

	/**
	 * 证件是否长期有效（易鑫暂无）
	 */
	private String timeless1;

	/**
	 * 证件有效期（易鑫暂无）
	 */
	private String statdate1;

	/**
	 * 联系电话
	 */
	private String mobile1;

	/**
	 * 出生日期
	 */
	private String birthdate1;

	/**
	 * 民族
	 */
	private String ethnic1;

	/**
	 * 学历
	 */
	private String education1;

	/**
	 * 居住地址
	 */
	private String address1;

	/**
	 * 现居住地年限（易鑫暂无）
	 */
	private String liveyears1;

	/**
	 * 居住状况
	 */
	private String livcondition1;

	/**
	 * 户籍地址
	 */
	private String regaddr1;

	/**
	 * 工作单位名称
	 */
	private String company1;

	/**
	 * 工作单位地址
	 */
	private String compaddr1;

	/**
	 * 工作单位类型
	 */
	private String comptype1;

	/**
	 * 现单位工作年限
	 */
	private String workyears1;

	/**
	 * 月收入（元）
	 */
	private String monthincome1;

	//共同申请人（无共同申请人非必输）
	/**
	 * 姓名
	 */
	private String chnsname2;

	/**
	 * 婚姻状况
	 */
	private String marrstate2;

	/**
	 * 性别
	 */
	private String sex2;

	/**
	 * 与申请人关系
	 */
	private String relation2;

	/**
	 * 证件类型
	 */
	private String custsort2;

	/**
	 * 证件号码
	 */
	private String custcode2;

	/**
	 * 证件是否长期有效（易鑫暂无）
	 */
	private String timeless2;

	/**
	 * 证件有效期（易鑫暂无）
	 */
	private String statdate2;

	/**
	 * 家庭人数（易鑫暂无）
	 */
	private String householdnum2;

	/**
	 * 学历
	 */
	private String education2;

	/**
	 * 移动电话
	 */
	private String mobile2;

	/**
	 * 邮编
	 */
	private String zipcode2;

	/**
	 * 居住地址
	 */
	private String address2;

	/**
	 * 现单位工作年限
	 */
	private String workyears2;

	/**
	 * 工作单位名称
	 */
	private String company2;

	/**
	 * 单位电话（易鑫暂无）
	 */
	private String comptel2;

	/**
	 * 工作单位地址
	 */
	private String compaddr2;

	/**
	 * 单位性质
	 */
	private String comptype2;

	/**
	 * 主要收入来源（易鑫暂无）
	 */
	private String incomesour2;

	//共同申请人配偶情况（共同申请人无配偶的时候非必输）
	/**
	 * 姓名
	 */
	private String chnsname3;

	/**
	 * 证件类型
	 */
	private String custsort3;

	/**
	 * 证件号码
	 */
	private String custcode3;

	/**
	 * 证件是否长期有效（易鑫暂无）
	 */
	private String timeless3;

	/**
	 * 证件有效期（易鑫暂无）
	 */
	private String statdate3;

	/**
	 * 月收入（元）
	 */
	private String monthincome3;

	/**
	 * 移动电话
	 */
	private String mobile3;

	/**
	 * 是否共同申请人
	 */
	private String relationflag3;

	/**
	 * 工作单位名称
	 */
	private String company3;

	/**
	 * 工作单位地址
	 */
	private String compaddr3;

	/**
	 * 工作单位类型
	 */
	private String comptype3;

	/**
	 * 城市
	 */
	private String citycode;

	/**
	 * 易鑫筛查结果
	 */
	private String yxstatus;

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

	public String getLoanamt() {
		return loanamt;
	}

	public void setLoanamt(String loanamt) {
		this.loanamt = loanamt;
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

	public String getPlanterm() {
		return planterm;
	}

	public void setPlanterm(String planterm) {
		this.planterm = planterm;
	}

	public String getCustsort() {
		return custsort;
	}

	public void setCustsort(String custsort) {
		this.custsort = custsort;
	}

	public String getCustcode() {
		return custcode;
	}

	public void setCustcode(String custcode) {
		this.custcode = custcode;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getChnsname() {
		return chnsname;
	}

	public void setChnsname(String chnsname) {
		this.chnsname = chnsname;
	}

	public String getMrtlstat() {
		return mrtlstat;
	}

	public void setMrtlstat(String mrtlstat) {
		this.mrtlstat = mrtlstat;
	}

	public String getEdulvl() {
		return edulvl;
	}

	public void setEdulvl(String edulvl) {
		this.edulvl = edulvl;
	}

	public String getHomestat() {
		return homestat;
	}

	public void setHomestat(String homestat) {
		this.homestat = homestat;
	}

	public String getHprovince() {
		return hprovince;
	}

	public void setHprovince(String hprovince) {
		this.hprovince = hprovince;
	}

	public String getHcity() {
		return hcity;
	}

	public void setHcity(String hcity) {
		this.hcity = hcity;
	}

	public String getHcounty() {
		return hcounty;
	}

	public void setHcounty(String hcounty) {
		this.hcounty = hcounty;
	}

	public String getHaddress() {
		return haddress;
	}

	public void setHaddress(String haddress) {
		this.haddress = haddress;
	}

	public String getHomezip() {
		return homezip;
	}

	public void setHomezip(String homezip) {
		this.homezip = homezip;
	}

	public String getMvblno() {
		return mvblno;
	}

	public void setMvblno(String mvblno) {
		this.mvblno = mvblno;
	}

	public String getUnitname() {
		return unitname;
	}

	public void setUnitname(String unitname) {
		this.unitname = unitname;
	}

	public String getDuty() {
		return duty;
	}

	public void setDuty(String duty) {
		this.duty = duty;
	}

	public String getCprovince() {
		return cprovince;
	}

	public void setCprovince(String cprovince) {
		this.cprovince = cprovince;
	}

	public String getCcity() {
		return ccity;
	}

	public void setCcity(String ccity) {
		this.ccity = ccity;
	}

	public String getCaddress() {
		return caddress;
	}

	public void setCaddress(String caddress) {
		this.caddress = caddress;
	}

	public String getYearincome() {
		return yearincome;
	}

	public void setYearincome(String yearincome) {
		this.yearincome = yearincome;
	}

	public String getModelcode() {
		return modelcode;
	}

	public void setModelcode(String modelcode) {
		this.modelcode = modelcode;
	}

	public String getOccptn() {
		return occptn;
	}

	public void setOccptn(String occptn) {
		this.occptn = occptn;
	}

	public String getReltname1() {
		return reltname1;
	}

	public void setReltname1(String reltname1) {
		this.reltname1 = reltname1;
	}

	public String getReltship1() {
		return reltship1;
	}

	public void setReltship1(String reltship1) {
		this.reltship1 = reltship1;
	}

	public String getReltphzon1() {
		return reltphzon1;
	}

	public void setReltphzon1(String reltphzon1) {
		this.reltphzon1 = reltphzon1;
	}

	public String getRelaphone1() {
		return relaphone1;
	}

	public void setRelaphone1(String relaphone1) {
		this.relaphone1 = relaphone1;
	}

	public String getReltmobl1() {
		return reltmobl1;
	}

	public void setReltmobl1(String reltmobl1) {
		this.reltmobl1 = reltmobl1;
	}

	public String getReltname2() {
		return reltname2;
	}

	public void setReltname2(String reltname2) {
		this.reltname2 = reltname2;
	}

	public String getReltship2() {
		return reltship2;
	}

	public void setReltship2(String reltship2) {
		this.reltship2 = reltship2;
	}

	public String getReltmobl2() {
		return reltmobl2;
	}

	public void setReltmobl2(String reltmobl2) {
		this.reltmobl2 = reltmobl2;
	}

	public String getRtcophzn2() {
		return rtcophzn2;
	}

	public void setRtcophzn2(String rtcophzn2) {
		this.rtcophzn2 = rtcophzn2;
	}

	public String getRtcophon2() {
		return rtcophon2;
	}

	public void setRtcophon2(String rtcophon2) {
		this.rtcophon2 = rtcophon2;
	}

	public String getPrimnat() {
		return primnat;
	}

	public void setPrimnat(String primnat) {
		this.primnat = primnat;
	}

	public String getMonthincome() {
		return monthincome;
	}

	public void setMonthincome(String monthincome) {
		this.monthincome = monthincome;
	}

	public String getRelationship() {
		return relationship;
	}

	public void setRelationship(String relationship) {
		this.relationship = relationship;
	}

	public String getYearincome1() {
		return yearincome1;
	}

	public void setYearincome1(String yearincome1) {
		this.yearincome1 = yearincome1;
	}

	public String getChnsname1() {
		return chnsname1;
	}

	public void setChnsname1(String chnsname1) {
		this.chnsname1 = chnsname1;
	}

	public String getRelation1() {
		return relation1;
	}

	public void setRelation1(String relation1) {
		this.relation1 = relation1;
	}

	public String getCustsort1() {
		return custsort1;
	}

	public void setCustsort1(String custsort1) {
		this.custsort1 = custsort1;
	}

	public String getCustcode1() {
		return custcode1;
	}

	public void setCustcode1(String custcode1) {
		this.custcode1 = custcode1;
	}

	public String getMobile1() {
		return mobile1;
	}

	public void setMobile1(String mobile1) {
		this.mobile1 = mobile1;
	}

	public String getBirthdate1() {
		return birthdate1;
	}

	public void setBirthdate1(String birthdate1) {
		this.birthdate1 = birthdate1;
	}

	public String getEthnic1() {
		return ethnic1;
	}

	public void setEthnic1(String ethnic1) {
		this.ethnic1 = ethnic1;
	}

	public String getEducation1() {
		return education1;
	}

	public void setEducation1(String education1) {
		this.education1 = education1;
	}

	public String getAddress1() {
		return address1;
	}

	public void setAddress1(String address1) {
		this.address1 = address1;
	}

	public String getLivcondition1() {
		return livcondition1;
	}

	public void setLivcondition1(String livcondition1) {
		this.livcondition1 = livcondition1;
	}

	public String getRegaddr1() {
		return regaddr1;
	}

	public void setRegaddr1(String regaddr1) {
		this.regaddr1 = regaddr1;
	}

	public String getCompany1() {
		return company1;
	}

	public void setCompany1(String company1) {
		this.company1 = company1;
	}

	public String getCompaddr1() {
		return compaddr1;
	}

	public void setCompaddr1(String compaddr1) {
		this.compaddr1 = compaddr1;
	}

	public String getComptype1() {
		return comptype1;
	}

	public void setComptype1(String comptype1) {
		this.comptype1 = comptype1;
	}

	public String getWorkyears1() {
		return workyears1;
	}

	public void setWorkyears1(String workyears1) {
		this.workyears1 = workyears1;
	}

	public String getMonthincome1() {
		return monthincome1;
	}

	public void setMonthincome1(String monthincome1) {
		this.monthincome1 = monthincome1;
	}

	public String getChnsname2() {
		return chnsname2;
	}

	public void setChnsname2(String chnsname2) {
		this.chnsname2 = chnsname2;
	}

	public String getMarrstate2() {
		return marrstate2;
	}

	public void setMarrstate2(String marrstate2) {
		this.marrstate2 = marrstate2;
	}

	public String getSex2() {
		return sex2;
	}

	public void setSex2(String sex2) {
		this.sex2 = sex2;
	}

	public String getRelation2() {
		return relation2;
	}

	public void setRelation2(String relation2) {
		this.relation2 = relation2;
	}

	public String getCustsort2() {
		return custsort2;
	}

	public void setCustsort2(String custsort2) {
		this.custsort2 = custsort2;
	}

	public String getCustcode2() {
		return custcode2;
	}

	public void setCustcode2(String custcode2) {
		this.custcode2 = custcode2;
	}

	public String getEducation2() {
		return education2;
	}

	public void setEducation2(String education2) {
		this.education2 = education2;
	}

	public String getMobile2() {
		return mobile2;
	}

	public void setMobile2(String mobile2) {
		this.mobile2 = mobile2;
	}

	public String getZipcode2() {
		return zipcode2;
	}

	public void setZipcode2(String zipcode2) {
		this.zipcode2 = zipcode2;
	}

	public String getAddress2() {
		return address2;
	}

	public void setAddress2(String address2) {
		this.address2 = address2;
	}

	public String getWorkyears2() {
		return workyears2;
	}

	public void setWorkyears2(String workyears2) {
		this.workyears2 = workyears2;
	}

	public String getCompany2() {
		return company2;
	}

	public void setCompany2(String company2) {
		this.company2 = company2;
	}

	public String getCompaddr2() {
		return compaddr2;
	}

	public void setCompaddr2(String compaddr2) {
		this.compaddr2 = compaddr2;
	}

	public String getComptype2() {
		return comptype2;
	}

	public void setComptype2(String comptype2) {
		this.comptype2 = comptype2;
	}

	public String getIncomesour2() {
		return incomesour2;
	}

	public void setIncomesour2(String incomesour2) {
		this.incomesour2 = incomesour2;
	}

	public String getChnsname3() {
		return chnsname3;
	}

	public void setChnsname3(String chnsname3) {
		this.chnsname3 = chnsname3;
	}

	public String getCustsort3() {
		return custsort3;
	}

	public void setCustsort3(String custsort3) {
		this.custsort3 = custsort3;
	}

	public String getCustcode3() {
		return custcode3;
	}

	public void setCustcode3(String custcode3) {
		this.custcode3 = custcode3;
	}

	public String getMonthincome3() {
		return monthincome3;
	}

	public void setMonthincome3(String monthincome3) {
		this.monthincome3 = monthincome3;
	}

	public String getMobile3() {
		return mobile3;
	}

	public void setMobile3(String mobile3) {
		this.mobile3 = mobile3;
	}

	public String getRelationflag3() {
		return relationflag3;
	}

	public void setRelationflag3(String relationflag3) {
		this.relationflag3 = relationflag3;
	}

	public String getCompany3() {
		return company3;
	}

	public void setCompany3(String company3) {
		this.company3 = company3;
	}

	public String getCompaddr3() {
		return compaddr3;
	}

	public void setCompaddr3(String compaddr3) {
		this.compaddr3 = compaddr3;
	}

	public String getComptype3() {
		return comptype3;
	}

	public void setComptype3(String comptype3) {
		this.comptype3 = comptype3;
	}

	public String getCitycode() {
		return citycode;
	}

	public void setCitycode(String citycode) {
		this.citycode = citycode;
	}

	public String getYxstatus() {
		return yxstatus;
	}

	public void setYxstatus(String yxstatus) {
		this.yxstatus = yxstatus;
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

	public String getTimeless() {
		return timeless;
	}

	public void setTimeless(String timeless) {
		this.timeless = timeless;
	}

	public String getStatdate() {
		return statdate;
	}

	public void setStatdate(String statdate) {
		this.statdate = statdate;
	}

	public String getEngname() {
		return engname;
	}

	public void setEngname(String engname) {
		this.engname = engname;
	}

	public String getIndate() {
		return indate;
	}

	public void setIndate(String indate) {
		this.indate = indate;
	}

	public String getHphonzono() {
		return hphonzono;
	}

	public void setHphonzono(String hphonzono) {
		this.hphonzono = hphonzono;
	}

	public String getHphoneno() {
		return hphoneno;
	}

	public void setHphoneno(String hphoneno) {
		this.hphoneno = hphoneno;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCophozono() {
		return cophozono;
	}

	public void setCophozono(String cophozono) {
		this.cophozono = cophozono;
	}

	public String getCophoneno() {
		return cophoneno;
	}

	public void setCophoneno(String cophoneno) {
		this.cophoneno = cophoneno;
	}

	public String getCcounty() {
		return ccounty;
	}

	public void setCcounty(String ccounty) {
		this.ccounty = ccounty;
	}

	public String getCorpzip() {
		return corpzip;
	}

	public void setCorpzip(String corpzip) {
		this.corpzip = corpzip;
	}

	public String getJoindate() {
		return joindate;
	}

	public void setJoindate(String joindate) {
		this.joindate = joindate;
	}

	public String getTimeless1() {
		return timeless1;
	}

	public void setTimeless1(String timeless1) {
		this.timeless1 = timeless1;
	}

	public String getStatdate1() {
		return statdate1;
	}

	public void setStatdate1(String statdate1) {
		this.statdate1 = statdate1;
	}

	public String getLiveyears1() {
		return liveyears1;
	}

	public void setLiveyears1(String liveyears1) {
		this.liveyears1 = liveyears1;
	}

	public String getTimeless2() {
		return timeless2;
	}

	public void setTimeless2(String timeless2) {
		this.timeless2 = timeless2;
	}

	public String getStatdate2() {
		return statdate2;
	}

	public void setStatdate2(String statdate2) {
		this.statdate2 = statdate2;
	}

	public String getHouseholdnum2() {
		return householdnum2;
	}

	public void setHouseholdnum2(String householdnum2) {
		this.householdnum2 = householdnum2;
	}

	public String getComptel2() {
		return comptel2;
	}

	public void setComptel2(String comptel2) {
		this.comptel2 = comptel2;
	}

	public String getTimeless3() {
		return timeless3;
	}

	public void setTimeless3(String timeless3) {
		this.timeless3 = timeless3;
	}

	public String getStatdate3() {
		return statdate3;
	}

	public void setStatdate3(String statdate3) {
		this.statdate3 = statdate3;
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

	public String getYxservamt() {
		return yxservamt;
	}

	public void setYxservamt(String yxservamt) {
		this.yxservamt = yxservamt;
	}

	public String getPurchasetax() {
		return purchasetax;
	}

	public void setPurchasetax(String purchasetax) {
		this.purchasetax = purchasetax;
	}

	public String getAccountmgfee() {
		return accountmgfee;
	}

	public void setAccountmgfee(String accountmgfee) {
		this.accountmgfee = accountmgfee;
	}

	public String getDecoratefee() {
		return decoratefee;
	}

	public void setDecoratefee(String decoratefee) {
		this.decoratefee = decoratefee;
	}

	public String getGpsfee() {
		return gpsfee;
	}

	public void setGpsfee(String gpsfee) {
		this.gpsfee = gpsfee;
	}

	public String getInsurance() {
		return insurance;
	}

	public void setInsurance(String insurance) {
		this.insurance = insurance;
	}

	public String getZxbinsurance() {
		return zxbinsurance;
	}

	public void setZxbinsurance(String zxbinsurance) {
		this.zxbinsurance = zxbinsurance;
	}

	public String getExinsurance() {
		return exinsurance;
	}

	public void setExinsurance(String exinsurance) {
		this.exinsurance = exinsurance;
	}

	public String getOtherfee() {
		return otherfee;
	}

	public void setOtherfee(String otherfee) {
		this.otherfee = otherfee;
	}

	public String getCarassessment() {
		return carassessment;
	}

	public void setCarassessment(String carassessment) {
		this.carassessment = carassessment;
	}

	public String getBusinesstype() {
		return businesstype;
	}

	public void setBusinesstype(String businesstype) {
		this.businesstype = businesstype;
	}
}
