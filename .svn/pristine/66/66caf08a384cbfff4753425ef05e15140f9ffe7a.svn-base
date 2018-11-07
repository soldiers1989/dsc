package com.yixin.kepler.dto.webank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;
import java.util.Date;

/**
 * 订单取消交易DTO
 * Package : com.yixin.kepler.dto.webank
 *
 * @author YixinCapital -- wanghonglin
 * 2018/7/9 15:03
 */
public class WBOrderCancelDTO extends WBCommonReqDTO implements Serializable {

    /**
     *平台订单号
     */
    @JsonProperty("MER_ORDER_NO")
    private String merOrderNo;
    /**
     *微众订单号
     */
    @JsonProperty("NBS_ORDER_NO")
    private String nbsOrderNo;
    /**
     *产品结构编号
     */
    @JsonProperty("PS_CODE")
    private String psCode;
    /**
     *用户姓名
     */
    @JsonProperty("NAME")
    private String name;
    /**
         *证件提交类型
     */
    @JsonProperty("ID_TYPE")
    private String idType;
    /**
     *证件号码
     */
    @JsonProperty("ID_NO")
    private String idNo;
    /**
     *车辆ID
     */
    @JsonProperty("CAR_ID")
    private String catrId;
    /**
     *机动车架号/VIN
     */
    @JsonProperty("VEHICLE_ID")
    private String vehicleId;
    /**
     *车商ID
     */
    @JsonProperty("DEALER_ID")
    private String dealerId;
    /**
     *订单撤销TC001
     退车-抵押失败TC004
     提前结清-提前还款给平台TC006
     提前结清-逾期85天强制提前结清TC007
     提前结清-来账结清TC014
     提前结清-质量问题退车TC018
     */
    @JsonProperty("TXN_CODE")
    private String txnCode;
    /**
     *交易描述(可与交易码的文字描述部分保持一致)
     */
    @JsonProperty("TXN_DESC")
    private String txnDesc;
    /**
     *交易时间
     */
    @JsonProperty("TXN_DATE")
    @JsonFormat(pattern = "yyyyMMddHHmmss",timezone="GMT+8")
    private Date txnDate;

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

    public String getCatrId() {
        return catrId;
    }

    public void setCatrId(String catrId) {
        this.catrId = catrId;
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

    @Override
    public String toString() {
        return "WBOrderCancelDTO{" +
                "merOrderNo='" + merOrderNo + '\'' +
                ", nbsOrderNo='" + nbsOrderNo + '\'' +
                ", psCode='" + psCode + '\'' +
                ", name='" + name + '\'' +
                ", idType='" + idType + '\'' +
                ", idNo='" + idNo + '\'' +
                ", catrId='" + catrId + '\'' +
                ", vehicleId='" + vehicleId + '\'' +
                ", dealerId='" + dealerId + '\'' +
                ", txnCode='" + txnCode + '\'' +
                ", txnDesc='" + txnDesc + '\'' +
                ", txnDate='" + txnDate + '\'' +
                '}';
    }
}
