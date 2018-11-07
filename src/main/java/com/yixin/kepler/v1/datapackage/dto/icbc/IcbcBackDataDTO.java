package com.yixin.kepler.v1.datapackage.dto.icbc;

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 银行返回DTO
 * Package : com.yixin.kapler_v1.datapackage.dto
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年9月12日 上午10:14:11
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IcbcBackDataDTO implements Serializable{
	
    /**
	 * 
	 * @author YixinCapital -- chen.lin
	 *		   2018年9月12日 上午10:14:03
	 */
	private static final long serialVersionUID = 1L;
	
	/**
     * 业务订单号
     */
    private String orderno;
    
    /**
     * 处理结果--代码
     */
    private String approvalcode;
    
    /**
     * 处理结果--信息
     */
    private String approvalmsg;
    
    /**
     * 放款时间-合同生效时间
     */
    private String lendate;
    
    /**
     * 文件传输标志
     */
    private String filetrans;
    
    /**
     * 打包文件名
     */
    private String filename;
    
    /**
     * 文件加密密码
     */
    private String filepasswd;
    
    /**
     * 文件字节数
     */
    private String filesize;
    
    /**
     * 姓名--面签专用
     */
    private String name;

    /**
     * 面签说明--面签专用
     */
    private String reason;

    
	public String getOrderno() {
		return orderno;
	}

	public void setOrderno(String orderno) {
		this.orderno = orderno;
	}

	public String getApprovalcode() {
		return approvalcode;
	}

	public void setApprovalcode(String approvalcode) {
		this.approvalcode = approvalcode;
	}

	public String getApprovalmsg() {
		return approvalmsg;
	}

	public void setApprovalmsg(String approvalmsg) {
		this.approvalmsg = approvalmsg;
	}

	public String getLendate() {
		return lendate;
	}

	public void setLendate(String lendate) {
		this.lendate = lendate;
	}

	public String getFiletrans() {
		return filetrans;
	}

	public void setFiletrans(String filetrans) {
		this.filetrans = filetrans;
	}

	public String getFilename() {
		return filename;
	}

	public void setFilename(String filename) {
		this.filename = filename;
	}

	public String getFilepasswd() {
		return filepasswd;
	}

	public void setFilepasswd(String filepasswd) {
		this.filepasswd = filepasswd;
	}

	public String getFilesize() {
		return filesize;
	}

	public void setFilesize(String filesize) {
		this.filesize = filesize;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
