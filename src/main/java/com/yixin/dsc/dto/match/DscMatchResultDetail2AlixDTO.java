package com.yixin.dsc.dto.match;

import java.util.List;

/**
 * 订单痕迹表
 * Package : com.yixin.dsc.entity.kepler
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年8月24日 下午4:17:19
 *
 */
public class DscMatchResultDetail2AlixDTO{

	/**
	 * 资方Code
	 */
	private String capitalCode;
	 
	 /**
	  * 资方name
	  */
	private String capitalName;

	 /**
	  * 是否准入成功
	  */
	private Boolean successFlag;
	
	/**
	 * 触碰资方规则List
	 */
	List<DscMatchFailRecord2AlixDto> matchFailRecordLis;

	
	
	
	public String getCapitalCode() {
		return capitalCode;
	}

	public void setCapitalCode(String capitalCode) {
		this.capitalCode = capitalCode;
	}

	public String getCapitalName() {
		return capitalName;
	}

	public void setCapitalName(String capitalName) {
		this.capitalName = capitalName;
	}

	public Boolean getSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(Boolean successFlag) {
		this.successFlag = successFlag;
	}

	public List<DscMatchFailRecord2AlixDto> getMatchFailRecordLis() {
		return matchFailRecordLis;
	}

	public void setMatchFailRecordLis(List<DscMatchFailRecord2AlixDto> matchFailRecordLis) {
		this.matchFailRecordLis = matchFailRecordLis;
	}

			
}
