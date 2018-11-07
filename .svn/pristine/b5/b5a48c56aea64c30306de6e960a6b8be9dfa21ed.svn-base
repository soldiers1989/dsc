package com.yixin.kepler.dto.webank;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.Date;

/**
 * 合同协议
 * Package : com.yixin.kepler.dto.webank
 *
 * @author YixinCapital -- wanghonglin
 * 2018/7/9 15:45
 */
public class WBContractDTO {
    /**
     *合同名称
     */
    @JsonProperty("CONTRACT_NAME")
    private String contractName;
    /**
     *合同版本号
     */
    @JsonProperty("CONTRACT_VER")
    private String contractVer;
    /**
     *勾选合同时间
     */
    @JsonProperty("CHECK_TIME")
    @JsonFormat(pattern = "yyyyMMddHHmmss",timezone="GMT+8")
    private Date checkTime;
    
    public WBContractDTO() {
		super();
	}

	public WBContractDTO(String contractName, String contractVer, Date checkTime) {
		super();
		this.contractName = contractName;
		this.contractVer = contractVer;
		this.checkTime = checkTime;
	}

	public String getContractName() {
        return contractName;
    }

    public void setContractName(String contractName) {
        this.contractName = contractName;
    }

    public String getContractVer() {
        return contractVer;
    }

    public void setContractVer(String contractVer) {
        this.contractVer = contractVer;
    }

    public Date getCheckTime() {
        return checkTime;
    }

    public void setCheckTime(Date checkTime) {
        this.checkTime = checkTime;
    }
}
