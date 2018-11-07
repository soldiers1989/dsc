package com.yixin.kepler.v1.datapackage.dto.yntrust;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * 创建订单-借款人信息
 * @author YixinCapital -- chenjiacheng
 *         2018年09月19日 10:23
 **/

public class YTCreateOrderBorrowerDto {

	/**
	 * 姓名
	 */
	@JsonProperty("ShortName")
	private String shortName;

	/**
	 * 银行开户预留手机号-如果不知道预留手机可以用手机号字段填充
	 */
	@JsonProperty("BankReservedPhoneNo")
	private String bankReservedPhoneNo;

	/**
	 * 身份证
	 */
	@JsonProperty("IDCardNo")
	private String idCardNo;

	/**
	 * 身份证有效截至日期  格式yyyy-MM-dddd hh:mm:ss
	 */
	@JsonProperty("IDCardValidatedEndDate")
	private String idCardValidatedEndDate;

	/**
	 *   10	未婚
		 20	已婚
		 21	初婚
		 22	再婚
		 23	复婚
		 30	丧偶
		 40	离婚
		 90 未说明的婚姻状况

	 */
	@JsonProperty("MaritalStatus")
	private Integer maritalStatus;

	/**
	 * 手机号
	 */
	@JsonProperty("TelephoneNo")
	private String telephoneNo;

	/**
	 * 居住城市  传递有效的省市区三级的地区code(查看附件4.3)
	 */
	@JsonProperty("City")
	private String city;

	/**
	 * 居住地址
	 */
	@JsonProperty("Address")
	private String address;

	/**
	 * 邮编
	 */
	@JsonProperty("ZipCode")
	private String zipCode;

	/**
	 * 通讯地址
	 */
	@JsonProperty("CommunicationAddress")
	private String communicationAddress;

	/**
	 * 通讯地址邮编
	 */
	@JsonProperty("CommunicationAddressZipCode")
	private String communicationAddressZipCode;

	/**
	 * 职业分类   传对应数字，
	 * 1=政府部门，2=教科文，3=金融，4=商贸，
	 * 5=房地产，6=制造业，7=自由职业，8=其他

	 */
	@JsonProperty("JobType")
	private String jobType;

	/**
	 * 还款银行卡账号
	 */
	@JsonProperty("AccountNo")
	private String accountNo;

	/**
	 * 借款人开户银行名称
	 */
	@JsonProperty("BankCode")
	private String bankCode;

	/**
	 * 借款人开户支行名称
	 */
	@JsonProperty("BranchBankName")
	private String branchBankName;

	/**
	 * 银行卡归属地
	 */
	@JsonProperty("BankCardAttribution")
	private String bankCardAttribution;


	/**
	 * 紧急联系人  不能与借款人为同一人
	 */
	@JsonProperty("EmergencyContactPerson")
	private String emergencyContactPerson;

	/**
	 * 紧急联系方式
	 */
	@JsonProperty("EmergencyContact")
	private String emergencyContact;

	/**
	 * 产品代码
	 */
	@JsonProperty("ProductCode")
	private String productCode;

	/**
	 * 角色分类  贷款借款人=1；其他投融资交易对手=2；抵质押人担保人贷款类=3；抵质押人担保人非贷款类=4；委托方=5；其他对手方等付费对象=6
	 */
	@JsonProperty("RoleType")
	private String roleType;

	/**
	 * 附加资产信息
	 * 车贷信息（车辆品牌、车型、车牌号、租赁资产五级分类、车辆VIN码、厂商指导价、债权转让日、首付比例、首付金额、期满回购价）；
	 * 房贷信息。（要求JSON字符串格式）附录4.3部分
	 */
	@JsonProperty("AssetInfo")
	private String assetInfo;

	/**
	 * 最高学历
	 * 10 研究生
		 20 大学本科
		 30 大学专科和专科学校
		 40 中等专业学校或中等技术学校
		 50 技术学校
		 60 高中
		 70 初中
		 80 小学
		 90 文盲或半文盲
		 99 未知（报征信项目该项必填）

	 */
	@JsonProperty("HigestEducation")
	private Integer higestEducation;

	/**
	 * 最高学位
	 * 0 其他
	 1 名誉博士
	 2 博士
	 3 硕士
	 4 学士
	 9 未知（报征信项目该项必填）

	 */
	@JsonProperty("HigestDegree")
	private Integer higestDegree;

	/**
	 * 公司所属行业
	 */
	@JsonProperty("CompanyType")
	private Integer companyType;

	/**
	 * 职务
	 */
	@JsonProperty("JobDuty")
	private Integer jobDuty;

	/**
	 * 职称
	 */
	@JsonProperty("JobTitle")
	private Integer jobTitle;

	/**
	 * 居住状况
	 */
	@JsonProperty("LivingConditions")
	private Integer livingConditions;

	/**
	 * 邮件地址
	 */
	@JsonProperty("EmailAddress")
	private String emailAddress;

	/**
	 * 住宅电话
	 */
	@JsonProperty("ResidenceTelephone")
	private String residenceTelephone;

	/**
	 * 单位名称
	 */
	@JsonProperty("CompanyName")
	private String companyName;

	/**
	 * 单位号码
	 */
	@JsonProperty("CompanyTelephone")
	private String companyTelephone;

	/**
	 * 其他紧急联系人
	 */
	@JsonProperty("MoreEmergencyContacts")
	private List<YTCreateOrderMergencyContactDto>  moreEmergencyContacts;


	public String getShortName() {
		return shortName;
	}

	public void setShortName(String shortName) {
		this.shortName = shortName;
	}

	public String getBankReservedPhoneNo() {
		return bankReservedPhoneNo;
	}

	public void setBankReservedPhoneNo(String bankReservedPhoneNo) {
		this.bankReservedPhoneNo = bankReservedPhoneNo;
	}

	public String getIdCardNo() {
		return idCardNo;
	}

	public void setIdCardNo(String idCardNo) {
		this.idCardNo = idCardNo;
	}

	public String getIdCardValidatedEndDate() {
		return idCardValidatedEndDate;
	}

	public void setIdCardValidatedEndDate(String idCardValidatedEndDate) {
		this.idCardValidatedEndDate = idCardValidatedEndDate;
	}

	public Integer getMaritalStatus() {
		return maritalStatus;
	}

	public void setMaritalStatus(Integer maritalStatus) {
		this.maritalStatus = maritalStatus;
	}

	public String getTelephoneNo() {
		return telephoneNo;
	}

	public void setTelephoneNo(String telephoneNo) {
		this.telephoneNo = telephoneNo;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getZipCode() {
		return zipCode;
	}

	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}

	public String getCommunicationAddress() {
		return communicationAddress;
	}

	public void setCommunicationAddress(String communicationAddress) {
		this.communicationAddress = communicationAddress;
	}

	public String getCommunicationAddressZipCode() {
		return communicationAddressZipCode;
	}

	public void setCommunicationAddressZipCode(String communicationAddressZipCode) {
		this.communicationAddressZipCode = communicationAddressZipCode;
	}

	public String getJobType() {
		return jobType;
	}

	public void setJobType(String jobType) {
		this.jobType = jobType;
	}

	public String getAccountNo() {
		return accountNo;
	}

	public void setAccountNo(String accountNo) {
		this.accountNo = accountNo;
	}

	public String getBankCode() {
		return bankCode;
	}

	public void setBankCode(String bankCode) {
		this.bankCode = bankCode;
	}

	public String getBranchBankName() {
		return branchBankName;
	}

	public void setBranchBankName(String branchBankName) {
		this.branchBankName = branchBankName;
	}

	public String getBankCardAttribution() {
		return bankCardAttribution;
	}

	public void setBankCardAttribution(String bankCardAttribution) {
		this.bankCardAttribution = bankCardAttribution;
	}

	public String getEmergencyContactPerson() {
		return emergencyContactPerson;
	}

	public void setEmergencyContactPerson(String emergencyContactPerson) {
		this.emergencyContactPerson = emergencyContactPerson;
	}

	public String getEmergencyContact() {
		return emergencyContact;
	}

	public void setEmergencyContact(String emergencyContact) {
		this.emergencyContact = emergencyContact;
	}

	public String getProductCode() {
		return productCode;
	}

	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}

	public String getRoleType() {
		return roleType;
	}

	public void setRoleType(String roleType) {
		this.roleType = roleType;
	}

	public String getAssetInfo() {
		return assetInfo;
	}

	public void setAssetInfo(String assetInfo) {
		this.assetInfo = assetInfo;
	}

	public Integer getHigestEducation() {
		return higestEducation;
	}

	public void setHigestEducation(Integer higestEducation) {
		this.higestEducation = higestEducation;
	}

	public Integer getHigestDegree() {
		return higestDegree;
	}

	public void setHigestDegree(Integer higestDegree) {
		this.higestDegree = higestDegree;
	}

	public Integer getCompanyType() {
		return companyType;
	}

	public void setCompanyType(Integer companyType) {
		this.companyType = companyType;
	}

	public Integer getJobDuty() {
		return jobDuty;
	}

	public void setJobDuty(Integer jobDuty) {
		this.jobDuty = jobDuty;
	}

	public Integer getJobTitle() {
		return jobTitle;
	}

	public void setJobTitle(Integer jobTitle) {
		this.jobTitle = jobTitle;
	}

	public Integer getLivingConditions() {
		return livingConditions;
	}

	public void setLivingConditions(Integer livingConditions) {
		this.livingConditions = livingConditions;
	}

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getResidenceTelephone() {
		return residenceTelephone;
	}

	public void setResidenceTelephone(String residenceTelephone) {
		this.residenceTelephone = residenceTelephone;
	}

	public String getCompanyName() {
		return companyName;
	}

	public void setCompanyName(String companyName) {
		this.companyName = companyName;
	}

	public String getCompanyTelephone() {
		return companyTelephone;
	}

	public void setCompanyTelephone(String companyTelephone) {
		this.companyTelephone = companyTelephone;
	}

	public List<YTCreateOrderMergencyContactDto> getMoreEmergencyContacts() {
		return moreEmergencyContacts;
	}

	public void setMoreEmergencyContacts(List<YTCreateOrderMergencyContactDto> moreEmergencyContacts) {
		this.moreEmergencyContacts = moreEmergencyContacts;
	}
}
