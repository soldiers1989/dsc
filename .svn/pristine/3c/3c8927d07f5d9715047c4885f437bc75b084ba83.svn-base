package com.yixin.web.dto;



import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;

import java.io.Serializable;
import java.util.Date;

/**
 * @author sukang
 */
public class ConditionBaseDto implements Serializable{
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private String applyNo;

	private String status;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date startDate;

	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss",timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date endDate;
	
	private Integer currentPage;


	public Integer getCurrentPage() {
		return currentPage == null || currentPage <= 0 ? 0:currentPage - 1;
	}

	public String getApplyNo() {
		return applyNo == null ? null : applyNo.trim();
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status == null ? null : status.trim();
	}

	public Date getStartDate() {
		return startDate;
	}

	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}

	public Date getEndDate() {
		return endDate;
	}

	public void setEndDate(Date endDate) {
		this.endDate = endDate;
	}

	public void setCurrentPage(Integer currentPage) {
		this.currentPage = currentPage == null || currentPage < 0 ? 0 : currentPage;
	}

}
