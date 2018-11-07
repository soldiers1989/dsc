package com.yixin.dsc.dto.rule;

import java.io.Serializable;

/**
 * 合同信息dto
 * @author YixinCapital -- chenjiacheng
 *         2018年07月05日 19:08
 **/

public class DscContractDto implements Serializable {

	/**
	 * 合同id
	 */
	private String contractId;

	/**
	 * 合同名称
	 */
	private String contractName;

	/**
	 * 合同版本号
	 */
	private String contractVersion;

	/**
	 * 合同文件id
	 */
	private String contractFileId;

	/**
	 * 合同中文名称
	 */
	private String contractCHName;


	public String getContractId() {
		return contractId;
	}

	public void setContractId(String contractId) {
		this.contractId = contractId;
	}

	public String getContractName() {
		return contractName;
	}

	public void setContractName(String contractName) {
		this.contractName = contractName;
	}

	public String getContractVersion() {
		return contractVersion;
	}

	public void setContractVersion(String contractVersion) {
		this.contractVersion = contractVersion;
	}

	public String getContractFileId() {
		return contractFileId;
	}

	public void setContractFileId(String contractFileId) {
		this.contractFileId = contractFileId;
	}

	public String getContractCHName() {
		return contractCHName;
	}

	public void setContractCHName(String contractCHName) {
		this.contractCHName = contractCHName;
	}
}
