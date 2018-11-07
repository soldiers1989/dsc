package com.yixin.kepler.dto.webank;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.List;

/**
 * 借据试算结果返回dto
 * @author YixinCapital -- chenjiacheng
 *         2018年07月09日 11:01
 **/
@JsonIgnoreProperties(ignoreUnknown = true)
public class WBComputerRespDTO extends WBCommonRespDTO{


	/**
	 * 证件类型
	 */
	@JsonProperty("ID_TYPE")
	private String idType;

	/**
	 * 证件号码
	 */
	@JsonProperty("ID_NO")
	private String idNo;

	/**
	 * 姓名
	 */
	@JsonProperty("NAME")
	private String name;


	/**
	 * 借款金额
	 */
	@JsonProperty("LOAN_INIT_PRIN")
	private BigDecimal loanInitPrin;

	@JsonProperty("SCHEDULES")
	private List<WBComputerSchedulesDTO> schedules;

	public List<WBComputerSchedulesDTO> getSchedules() {
		return schedules;
	}

	public void setSchedules(List<WBComputerSchedulesDTO> schedules) {
		this.schedules = schedules;
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

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public BigDecimal getLoanInitPrin() {
		return loanInitPrin;
	}

	public void setLoanInitPrin(BigDecimal loanInitPrin) {
		this.loanInitPrin = loanInitPrin;
	}


}
