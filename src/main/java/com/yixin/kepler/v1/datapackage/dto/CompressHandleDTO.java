package com.yixin.kepler.v1.datapackage.dto;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

/**
 * 压缩处理DTO
 * Package : com.yixin.kepler.v1.datapackage.dto
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年9月27日 下午8:28:30
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CompressHandleDTO implements Serializable{


	/**
	 * 
	 * @author YixinCapital -- chen.lin
	 *		   2018年9月27日 下午8:19:21
	 *
	 */
	private static final long serialVersionUID = 5147769008743396823L;
	
	/**
	 * 申请编号
	 */
    private String applyNo;
	
	/**
	 * osbFileLog的ID
	 */
    private String osbFileLogId;
	
	/**
     * 压缩流水号
     */
    private String compressTranNo;
    
	/**
	 * 资方code
	 */
    private String assetNo;
    
    /**
     * 压缩后的报文
     */
    private String compressFileJson;

    
    
	public String getCompressTranNo() {
		return compressTranNo;
	}

	public void setCompressTranNo(String compressTranNo) {
		this.compressTranNo = compressTranNo;
	}

	public String getAssetNo() {
		return assetNo;
	}

	public void setAssetNo(String assetNo) {
		this.assetNo = assetNo;
	}

	public String getCompressFileJson() {
		return compressFileJson;
	}

	public void setCompressFileJson(String compressFileJson) {
		this.compressFileJson = compressFileJson;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public String getOsbFileLogId() {
		return osbFileLogId;
	}

	public void setOsbFileLogId(String osbFileLogId) {
		this.osbFileLogId = osbFileLogId;
	}

	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}
}
