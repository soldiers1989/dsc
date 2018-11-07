package com.yixin.kepler.v1.datapackage.dto.yntrust;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * 云南信托查询还款计划dto
 * Package : com.yixin.kepler.v1.datapackage.dto.yntrust
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年9月27日 下午8:37:13
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class YTQueryRepayScheduleDTO extends YTCommonResponseDTO {

	private static final long serialVersionUID = 5500960674124217639L;

	/**
     * 还款计划所属合同集合
     */
	@JsonProperty("RepayScheduleList")
    private List<YTRepaySchedulesDTO> repaySchedules;

	public List<YTRepaySchedulesDTO> getRepaySchedules() {
		return repaySchedules;
	}

	public void setRepaySchedules(List<YTRepaySchedulesDTO> repaySchedules) {
		this.repaySchedules = repaySchedules;
	}
	
	
}
