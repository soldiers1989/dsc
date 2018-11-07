package com.yixin.kepler.dto.cmbc;

/**
 * cmbc影像资料以及其他信息补传 DTO
 */
public class CMBCAfterLoanDataSyncDTO {

	/**
	 * 申请书号 	参数是否必须：true
	 */
	private String applyNo; 	 
	
	/**
	 * 文件名称 	参数是否必须：true
	 */
	private String fileName;
	
	/**
	 * 备注 	参数是否必须：true
	 */
	private String reserve;

	/**
	 *  文件上送日期
	 */
	private String fileDate;

	/**
	 * 是否包含影像
	 */
	private String hasFile;
	
	/**
	 * 阶段码值为01,02,03，不传默认为03 01-申请 02-放款 03-贷后
	 */
	private String stage;
	


	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getFileName() {
		return fileName;
	}

	public void setFileName(String fileName) {
		this.fileName = fileName;
	}

	public String getReserve() {
		return reserve;
	}

	public void setReserve(String reserve) {
		this.reserve = reserve;
	}

	public String getFileDate() {
		return fileDate;
	}

	public void setFileDate(String fileDate) {
		this.fileDate = fileDate;
	}

	public String getHasFile() {
		return hasFile;
	}

	public void setHasFile(String hasFile) {
		this.hasFile = hasFile;
	}

	public String getStage() {
		return stage;
	}

	public void setStage(String stage) {
		this.stage = stage;
	}
}
