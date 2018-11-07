package com.yixin.kepler.dto.cmbc;

import com.yixin.kepler.dto.cmbc.CMBCLastTrialReserveDTO;

import ch.qos.logback.core.rolling.SizeAndTimeBasedRollingPolicy;

/**
 * 终审接口请求DTO
 * @author sukang
 * @date 2018-06-11 14:43:02
 */
public class CMBCLastTrialBodyDTO{
	

	
	
	/**
	 * 	贷款起息日 	false	
	 */
	private String intStartDt; 
	
	
	/**
	 *  合作商产品类型  	true
	 */
	private String cooprProdType;	
	
	/**
	 *   true
	 * 证件类型 
	 * ZR01 中华人民共和国居民身份证 
	 * ZR02 中华人民共和国临时居民身份证 
	 * ZR03 居民户口薄 ZR13 中华人民共和国护照 
	 * ZR09 香港通行证 ZR10 澳门通行证 ZR11 台湾通行证或有效旅行证件 
	 * ZR17 边民出入境通行证 ZR14 中华人民共和国外国人永久居留证 
	 * ZR08 外籍人员护照 ZC02 营业执照 ZC01 组织机构代码证 
	 * ZC04 国税登记证号码 ZC05 地税登记证号码"
	 */
	private String idType;
	
	/**
	 * 	证件号码 	true
	 */
	private String idNo; 
	
	
	/**
	 * 客户名称   true
	 */
	private String custName;	
	
	
	/**
	 * 1 男 2 女   true
	 */
	private String sex;
	
	/**
	 * true  实例  19900101
	 */
	private String dtOfBirth;
	
	
	/**
	 * 婚姻状况: 1 未婚 2 已婚 3 丧偶 4 离婚 5 其它
	 */
	private String maritalStatus;
	
	/**
	 * 教育水平 	false
	 * 
	 * "1 研究生 2 本科 3 专科 4 中专、技术学校或高中 5 初中及以下 6 未知"
	 */
	private String educationalLevel;	
	
	/**
	 * 年收入 	true 	普通参数 	string 	15 		提供年薪数据
	 */
	private String yearIncome;
	
	/**
	 * 居住状况  	false 			
	 * 1 在本地租房居住 2 在本地购房居住(无负债) 3 在本地购房居住(有负债)
	 * 4 在外地租房居住 5 在外地购房居住(无负债) 6 在外地购房居住(有负债)
	 */
	private String familySts;
	
	
	/**
	 * 居住年限  	false 
	 */
	private String familyYear;
	
	/**
	 * 居住地址 	true 
	 */
	private String homeAddr; 
	
	/**
	 *单位属性 	true 
	 *1 国家机关、事业单位、社会团体 
	 *2 中央企业 3 国内外上市公司 4 非上市大型企业
	 *5 非上市中小型企业 6 微型企业 7 自由职业者" 
	 */
	private String emprProp; 
	
	/**
	 * 工作单位 	false
	 */
	private String workCorpName; 	
	
	/**
	 * 部门 	true 
	 */
	private String department; 	
	
	/**职务  	true  	
	 * 1 副科（含）以下或一般员工 2 科-副处（含）或初级管理者 
	 * 3 处-副局（含）或中级管理者 4 局以上或高级管理者 5 未知
	 * 
	 */
	private String post;
	
	
	/**
	 *职位 	true   
	 */
	private String position; 
	
	/**
	 * 职业 	true 
	 * 01 国家机关、党群组织、企业、事业单位负责人 
	 * 02 科研人员、律师、教师、记者、医务工作者、文艺工作者、社会工作者
	 * 03 国家机关、党群组织、社会团体、企事业单位办事人员
	 * 04 金融业、商业、服务业工作人员 05 农、林、牧、渔、水利业生产人员 
	 * 06 生产工人、运输工人和有关人员 07 公安干警、交通干警、武装警察(武警)、军人等 08 个体工商户、小企业主、自由职业者、不便分类的其他劳动者等 
	 * 09 未知"
	 */
	private String occupation; 
	
	
	/**
	 *单位地址 	false
	 */
	private String cropAddress; 
	
	/**
	 * 单位邮编 	false 		
	 */
	private String cropPostcode;
	
	/**
	 * 手机号 	true 	
	 */
	private String phoneNo;
	
	/**
	 * 卡号 	true
	 */
	private String tacNo;
	
	/**
	 * 住宅电话 	false
	 */
	private String homePhoneNo; 
	
	/**
	 *单位电话 	false  
	 */
	private String workPhoneNo; 
	
	/**
	 * 申请币种 	true
	 */
	private String applyCurrency; 	
	
	/**
	 * 申请金额 	true
	 */
	private String applyAmt;	 	
	
	public String getReserve1() {
		return reserve1;
	}

	/**
	 * 申请日期 	true yyyyMMdd
	 */
	private String applyDate;
	
	/**
	 * 还款方式 	true
	 * 
	 * 0 利随本清 2 按期付息到期还本 3 按期等额本息 4 按期等额本金 
	 * 4 等额本金 5 按期付息不规则还本 6 组合还款" , 一期默认为：3
	
	 */
	private String paymTyp;
	/**
	 * 还款日 	true
	 */
	private String atrsDueDay;
	
	/**
	 * 利率模式 	true 
	 * 
	 * FX-固定 RV-浮动
	 */
	private String rateMode;		
	/**
	 * 利率调整方式 	true 
	 * 
	 * FXX 固定不变 IMM立即调整 NNR还款周期调整 DDA满一年调整 NYF每年一月一号调整 FIX固定周期调整
	 *  利率计算模式为指定利率时，必须为FXX"
	 */
	private String nextRepcOption;
	
	/**
	 * 是否固定利率 	false
	 * 
	 * 输入N（Y是，N否）
	 */
	private String fixRateInd;
	
	/**
	 * 正常利率上浮比例  true  如RATE_MODE选浮动，则必输
	 */
	private String intAdjPct; 	
	
	/**
	 * 	申请用途 	true   “消费”
	 */
	private String loanPurpost; 		
	
	/**
	 * 备用字段
	 */
	private String reserve1;	
	
	
	

	private CMBCReceiveMsgDTO receiveMg;
	
	
	private String reserve2;			
	private String reserve3;	
	private String reserve4;	
	private String reserve5;		
	
	/**
	 * 证件有效期自 	true  yyyMMdd(当地监管要求)
	 */
	private String idValidStart;
	
	/**
	 * 证件有效期至 	true  yyyyMMdd(当地监管要求)
	 */
	private String idValidEnd; 
	
	/**
	 * 发证国家 	true 默认CN , "US 美国 EN 英国 CN 中国"
	 */
	private String issCtry; 		
	
	/**
	 * 影像资料 	false
	 */
	private String applyImgList;			
	/**
	 * 是否有影像文件 	true  Y - 有 N - 无
	 */
	private String hasFile;			
	
	/**
	 * 申请期限 	true  0-36
	 */
	private String applNosInst;
	
	
	
	



	

	public void setReserve1(String reserve1) {
		this.reserve1 = reserve1;
	}

	public String getIntStartDt() {
		return intStartDt;
	}

	public void setIntStartDt(String intStartDt) {
		this.intStartDt = intStartDt;
	}

	public String getCooprProdType() {
		return cooprProdType;
	}

	public void setCooprProdType(String cooprProdType) {
		this.cooprProdType = cooprProdType;
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

	public String getCustName() {
		return custName;
	}

	public void setCustName(String custName) {
		this.custName = custName;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	public String getDtOfBirth() {
		return dtOfBirth;
	}

	public void setDtOfBirth(String dtOfBirth) {
		this.dtOfBirth = dtOfBirth;
	}

	public String getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(String maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getEducationalLevel() {
		return educationalLevel;
	}

	public void setEducationalLevel(String educationalLevel) {
		this.educationalLevel = educationalLevel;
	}

	public String getYearIncome() {
		return yearIncome;
	}

	public void setYearIncome(String yearIncome) {
		this.yearIncome = yearIncome;
	}

	public String getFamilySts() {
		return familySts;
	}

	public void setFamilySts(String familySts) {
		this.familySts = familySts;
	}

	public String getFamilyYear() {
		return familyYear;
	}

	public void setFamilyYear(String familyYear) {
		this.familyYear = familyYear;
	}

	public String getHomeAddr() {
		return homeAddr;
	}

	public void setHomeAddr(String homeAddr) {
		this.homeAddr = homeAddr;
	}

	public String getEmprProp() {
		return emprProp;
	}

	public void setEmprProp(String emprProp) {
		this.emprProp = emprProp;
	}

	public String getWorkCorpName() {
		return workCorpName;
	}

	public void setWorkCorpName(String workCorpName) {
		this.workCorpName = workCorpName;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public String getPost() {
		return post;
	}

	public void setPost(String post) {
		this.post = post;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getOccupation() {
		return occupation;
	}

	public void setOccupation(String occupation) {
		this.occupation = occupation;
	}

	public String getCropAddress() {
		return cropAddress;
	}

	public void setCropAddress(String cropAddress) {
		this.cropAddress = cropAddress;
	}

	public String getCropPostcode() {
		return cropPostcode;
	}

	public void setCropPostcode(String cropPostcode) {
		this.cropPostcode = cropPostcode;
	}

	public String getPhoneNo() {
		return phoneNo;
	}

	public void setPhoneNo(String phoneNo) {
		this.phoneNo = phoneNo;
	}

	public String getTacNo() {
		return tacNo;
	}

	public void setTacNo(String tacNo) {
		this.tacNo = tacNo;
	}

	public String getHomePhoneNo() {
		return homePhoneNo;
	}

	public void setHomePhoneNo(String homePhoneNo) {
		this.homePhoneNo = homePhoneNo;
	}

	public String getWorkPhoneNo() {
		return workPhoneNo;
	}

	public void setWorkPhoneNo(String workPhoneNo) {
		this.workPhoneNo = workPhoneNo;
	}

	public String getApplyCurrency() {
		return applyCurrency;
	}

	public void setApplyCurrency(String applyCurrency) {
		this.applyCurrency = applyCurrency;
	}

	public String getApplyAmt() {
		return applyAmt;
	}

	public void setApplyAmt(String applyAmt) {
		this.applyAmt = applyAmt;
	}

	public String getApplyDate() {
		return applyDate;
	}

	public void setApplyDate(String applyDate) {
		this.applyDate = applyDate;
	}

	public String getPaymTyp() {
		return paymTyp;
	}

	public void setPaymTyp(String paymTyp) {
		this.paymTyp = paymTyp;
	}

	public String getAtrsDueDay() {
		return atrsDueDay;
	}

	public void setAtrsDueDay(String atrsDueDay) {
		this.atrsDueDay = atrsDueDay;
	}

	public String getRateMode() {
		return rateMode;
	}

	public void setRateMode(String rateMode) {
		this.rateMode = rateMode;
	}

	public String getNextRepcOption() {
		return nextRepcOption;
	}

	public void setNextRepcOption(String nextRepcOption) {
		this.nextRepcOption = nextRepcOption;
	}

	public String getFixRateInd() {
		return fixRateInd;
	}

	public void setFixRateInd(String fixRateInd) {
		this.fixRateInd = fixRateInd;
	}

	public String getIntAdjPct() {
		return intAdjPct;
	}

	public void setIntAdjPct(String intAdjPct) {
		this.intAdjPct = intAdjPct;
	}

	public String getLoanPurpost() {
		return loanPurpost;
	}

	public void setLoanPurpost(String loanPurpost) {
		this.loanPurpost = loanPurpost;
	}

	

	public String getReserve2() {
		return reserve2;
	}

	public void setReserve2(String reserve2) {
		this.reserve2 = reserve2;
	}

	public String getReserve3() {
		return reserve3;
	}

	public void setReserve3(String reserve3) {
		this.reserve3 = reserve3;
	}

	public String getReserve4() {
		return reserve4;
	}

	public void setReserve4(String reserve4) {
		this.reserve4 = reserve4;
	}

	public String getReserve5() {
		return reserve5;
	}

	public void setReserve5(String reserve5) {
		this.reserve5 = reserve5;
	}

	public String getIdValidStart() {
		return idValidStart;
	}

	public void setIdValidStart(String idValidStart) {
		this.idValidStart = idValidStart;
	}

	public String getIdValidEnd() {
		return idValidEnd;
	}

	public void setIdValidEnd(String idValidEnd) {
		this.idValidEnd = idValidEnd;
	}

	public String getIssCtry() {
		return issCtry;
	}

	public void setIssCtry(String issCtry) {
		this.issCtry = issCtry;
	}

	public String getApplyImgList() {
		return applyImgList;
	}

	public void setApplyImgList(String applyImgList) {
		this.applyImgList = applyImgList;
	}

	public String getHasFile() {
		return hasFile;
	}

	public void setHasFile(String hasFile) {
		this.hasFile = hasFile;
	}

	public String getApplNosInst() {
		return applNosInst;
	}

	public void setApplNosInst(String applNosInst) {
		this.applNosInst = applNosInst;
	}

	public CMBCReceiveMsgDTO getReceiveMg() {
		return receiveMg;
	}

	public void setReceiveMg(CMBCReceiveMsgDTO receiveMg) {
		this.receiveMg = receiveMg;
	}

		
	
	
	
	

}
