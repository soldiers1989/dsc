package com.yixin.kepler.dto.webank;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.yixin.dsc.util.DateUtil;

/**
 * 微众银行放款结果返回DTO
 * Package : com.yixin.kepler.dto.webank
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月30日 上午10:59:36
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class WBLoanResultRespDTO extends WBCommonRespDTO{
	
	private static final long serialVersionUID = -3751778347541981616L;
	
	@JsonProperty("LOAN_LIST")
	private List<WBLoanDTO> loanList;  //借据列表
	
	
	private String carLoanDesc;
	
	private String feeLoanDesc;
	
	/** 车贷 */
	public final String CAR = "MORTGAGELOAN";
	
	/** 费用贷 */
	public final String FEE = "FEELOAN";
	
	public List<WBLoanDTO> getLoanList() {
		return loanList;
	}

	public void setLoanList(List<WBLoanDTO> loanList) {
		this.loanList = loanList;
	}

	public String getCarLoanDesc() {
		if(StringUtils.isBlank(carLoanDesc) && CollectionUtils.isNotEmpty(loanList)){
			for(WBLoanDTO loanDto:loanList){
				if(CAR.equals(loanDto.getLoanType())){ //车贷
					StringBuffer desc = new StringBuffer("车贷  【");
					desc.append("放款结果:").append(loanDto.getLoanResultDesc());
					desc.append(" 放款时间:").append(loanDto.getUpdateTime()==null?"":DateUtil.dateToString(loanDto.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
					desc.append(" 放款金额:").append(loanDto.getLoanInitPrin() == null?"":loanDto.getLoanInitPrin().setScale(2, BigDecimal.ROUND_HALF_UP));
					desc.append("】");
					
					carLoanDesc = desc.toString();
				}
			}
		}
		return carLoanDesc;
	}

	public String getFeeLoanDesc() {
		if(StringUtils.isBlank(feeLoanDesc) && CollectionUtils.isNotEmpty(loanList)){
			for(WBLoanDTO loanDto:loanList){
				if(FEE.equals(loanDto.getLoanType())){ //费用贷
					StringBuffer desc = new StringBuffer("费用贷  【");
					desc.append("放款结果:").append(loanDto.getLoanResultDesc());
					desc.append(" 放款时间:").append(loanDto.getUpdateTime()==null?"":DateUtil.dateToString(loanDto.getUpdateTime(), "yyyy-MM-dd HH:mm:ss"));
					desc.append(" 放款金额:").append(loanDto.getLoanInitPrin() == null?"":loanDto.getLoanInitPrin().setScale(2, BigDecimal.ROUND_HALF_UP));
					desc.append("】");
					
					feeLoanDesc = desc.toString();
				}
			}
		}
		return feeLoanDesc;
	}
}
