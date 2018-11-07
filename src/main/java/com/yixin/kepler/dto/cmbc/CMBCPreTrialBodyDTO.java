package com.yixin.kepler.dto.cmbc;


/**
 * cmbc预审请求实体DTO (鉴权)
 *
 * @author sukang
 * @date 2018-06-11 10:32:00
 */

public class CMBCPreTrialBodyDTO {

    /**
     * 接收信息
     *
     * @see CMBCReceiveMsgDTO
     */
    private CMBCReceiveMsgDTO receiveMg;

    public void setReceiveMg(CMBCReceiveMsgDTO receiveMg) {
        this.receiveMg = receiveMg;
    }

    /**
     * @see CMBCReserve1DTO
     */
    private String reserve1;
    /**
     * ZR01 中华人民共和国居民身份证
     * ZR02 中华人民共和国临时居民身份证
     * ZR03 居民户口薄
     * ZR13 中华人民共和国护照
     * ZR09 香港通行证
     * ZR10 澳门通行证
     * ZR11 台湾通行证或有效旅行证件
     * ZR17 边民出入境通行证
     * ZR14 中华人民共和国外国人永久居留证
     * ZR08 外籍人员护照
     * ZC02 营业执照
     * ZC01 组织机构代码证
     * ZC04 国税登记证号码
     * ZC05 地税登记证号码"
     */
    private String idType;
    /**
     * 客户名称
     */
    private String custName;
    /**
     * 证件号码
     */
    private String idNo;
    /**
     * 手机号
     */
    private String phoneNo;
    /**
     * 卡号
     */
    private String tacNo;
    /**
     * 申请流水号
     */
    private String serialNo;
    /**
     * CNCL：取消贷款；COMP：审批通过
     */
    private String notifyType;

    private String applyNo;
    /**
     * 商户产品编号
     */
    private String merchantNum;

    public String getReserve1() {
        return reserve1;
    }

    public void setReserve1(String reserve1) {
        this.reserve1 = reserve1;
    }

    public CMBCReceiveMsgDTO getReceiveMg() {
        return receiveMg;
    }

    public String getTacNo() {
        return tacNo;
    }

    public void setTacNo(String tacNo) {
        this.tacNo = tacNo;
    }

    public String getIdNo() {
        return idNo;
    }

    public void setIdNo(String idNo) {
        this.idNo = idNo;
    }

    public String getIdType() {
        return idType;
    }

    public void setIdType(String idType) {
        this.idType = idType;
    }

    public String getPhoneNo() {
        return phoneNo;
    }

    public void setPhoneNo(String phoneNo) {
        this.phoneNo = phoneNo;
    }

    public String getCustName() {
        return custName;
    }

    public void setCustName(String custName) {
        this.custName = custName;
    }

    public String getSerialNo() {
        return serialNo;
    }

    public void setSerialNo(String serialNo) {
        this.serialNo = serialNo;
    }

    public String getNotifyType() {
        return notifyType;
    }

    public void setNotifyType(String notifyType) {
        this.notifyType = notifyType;
    }

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getMerchantNum() {
        return merchantNum;
    }

    public void setMerchantNum(String merchantNum) {
        this.merchantNum = merchantNum;
    }
}
