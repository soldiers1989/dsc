package com.yixin.dsc.dto.order;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.yixin.common.utils.serializer.JsonDateDeserializer;
import com.yixin.common.utils.serializer.JsonDateTimeSerializer;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * 合同申请保险信息
 * Package : com.yixin.dsc.entity
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月4日 下午2:15:38
 *
 */
public class DscSalesInsureInfoDTO implements Serializable{
	
	private static final long serialVersionUID = 5446695203364983607L;

    private String id;

	/**
	 * 订单主表ID
	 */
	private String mainId;


	/**
	 * 	是否融保险
	 */
	private String asfrbx;

	/**
	 * 	是否报销
	 */
	private String aisBx;

	/**
	 * 	保险公司
	 */
	private String abxgs;
	/**
	 * 	保险公司名称
	 */
	private String abxgsName;
	/**
	 * 	交强险出单地
	 */
	private String ajqxcdd;
	/**
	 * 	交强险出单地
	 */
	private String ajqxcddName;
	/**
	 * 	商业险出单地
	 */
	private String asyxcdd;
	/**
	 * 	商业险出单地
	 */
	private String asyxcddName;
	/**
	 * 	商业险出单方式
	 */
	private String asyxcdfs;
	/**
	 * 	商业险出单方式
	 */
	private String asyxcdfsName;
	/**
	 * 	交强险出单方式
	 */
	private String ajqxcdfs;
	/**
	 * 	交强险出单方式
	 */
	private String ajqxcdfsName;

	/**
	 * 被保险人
	 */
	private String bbxr;
	/**
	 * 交强险单号
	 */
	private String abdhjq;
	/**
	 * 交强险保费
	 */
	private BigDecimal fbdjejq;
	/**
	 * 交强险生效日
	 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date dbdqsrjq;
	/**
	 * 交强险失效日
	 */
	@JsonSerialize(using = JsonDateTimeSerializer.class)
	@JsonDeserialize(using = JsonDateDeserializer.class)
	private Date dbdsxrjq;

	public String getBbxr() {
		return bbxr;
	}

	public void setBbxr(String bbxr) {
		this.bbxr = bbxr;
	}

	public String getAbdhjq() {
		return abdhjq;
	}

	public void setAbdhjq(String abdhjq) {
		this.abdhjq = abdhjq;
	}

	public BigDecimal getFbdjejq() {
		return fbdjejq;
	}

	public void setFbdjejq(BigDecimal fbdjejq) {
		this.fbdjejq = fbdjejq;
	}

	public Date getDbdqsrjq() {
		return dbdqsrjq;
	}

	public void setDbdqsrjq(Date dbdqsrjq) {
		this.dbdqsrjq = dbdqsrjq;
	}

	public Date getDbdsxrjq() {
		return dbdsxrjq;
	}

	public void setDbdsxrjq(Date dbdsxrjq) {
		this.dbdsxrjq = dbdsxrjq;
	}

	public String getId() {
		return id;
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

	public String getAsfrbx() {
		return asfrbx;
	}
	public void setAsfrbx(String asfrbx) {
		this.asfrbx = asfrbx;
	}
	public String getAbxgs() {
		return abxgs;
	}
	public void setAbxgs(String abxgs) {
		this.abxgs = abxgs;
	}
	public String getAbxgsName() {
		return abxgsName;
	}
	public void setAbxgsName(String abxgsName) {
		this.abxgsName = abxgsName;
	}
	public String getAjqxcdd() {
		return ajqxcdd;
	}
	public void setAjqxcdd(String ajqxcdd) {
		this.ajqxcdd = ajqxcdd;
	}
	public String getAjqxcddName() {
		return ajqxcddName;
	}
	public void setAjqxcddName(String ajqxcddName) {
		this.ajqxcddName = ajqxcddName;
	}
	public String getAsyxcdd() {
		return asyxcdd;
	}
	public void setAsyxcdd(String asyxcdd) {
		this.asyxcdd = asyxcdd;
	}
	public String getAsyxcddName() {
		return asyxcddName;
	}
	public void setAsyxcddName(String asyxcddName) {
		this.asyxcddName = asyxcddName;
	}
	public String getAsyxcdfs() {
		return asyxcdfs;
	}
	public void setAsyxcdfs(String asyxcdfs) {
		this.asyxcdfs = asyxcdfs;
	}
	public String getAsyxcdfsName() {
		return asyxcdfsName;
	}
	public void setAsyxcdfsName(String asyxcdfsName) {
		this.asyxcdfsName = asyxcdfsName;
	}
	public String getAjqxcdfs() {
		return ajqxcdfs;
	}
	public void setAjqxcdfs(String ajqxcdfs) {
		this.ajqxcdfs = ajqxcdfs;
	}
	public String getAjqxcdfsName() {
		return ajqxcdfsName;
	}
	public void setAjqxcdfsName(String ajqxcdfsName) {
		this.ajqxcdfsName = ajqxcdfsName;
	}
	public String getAisBx() {
		return aisBx;
	}
	public void setAisBx(String aisBx) {
		this.aisBx = aisBx;
	}
}
