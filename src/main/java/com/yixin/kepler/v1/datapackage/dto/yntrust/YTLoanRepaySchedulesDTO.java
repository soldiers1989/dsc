package com.yixin.kepler.v1.datapackage.dto.yntrust;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * @author sukang
 */
public class YTLoanRepaySchedulesDTO {

    /**
     * 合同唯一编号
     */
	@JsonProperty("UniqueId")
    private String uniqueId;


    /**
     * 还款计划所属合同集合
     */
	@JsonProperty("RepaySchedules")
    private List<YTRepaySchedulesDTO> repaySchedules;

    public String getUniqueId() {
        return uniqueId;
    }

    public void setUniqueId(String uniqueId) {
        this.uniqueId = uniqueId;
    }

    public List<YTRepaySchedulesDTO> getRepaySchedules() {
        return repaySchedules;
    }

    public void setRepaySchedules(List<YTRepaySchedulesDTO> repaySchedules) {
        this.repaySchedules = repaySchedules;
    }
}
