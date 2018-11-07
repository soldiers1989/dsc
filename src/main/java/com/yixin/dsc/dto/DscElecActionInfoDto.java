package com.yixin.dsc.dto;

import java.io.Serializable;
import java.util.List;

/**
 * 电子签约信息
 * Package : com.yixin.dsc.dto
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月4日 下午5:20:44
 *
 */
public class DscElecActionInfoDto implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 2975328827945365978L;
	
	/**
	 * 合同协议
	 */
	private List<DscSignContractDto> signContractList;

	/**
	 * 用户行为
	 */
	private DscUserActionDto userActionDto;
	
	/**
	 * 操作环境
	 */
	private DscOperateEnvDto operateEnvDto;

	public List<DscSignContractDto> getSignContractList() {
		return signContractList;
	}

	public void setSignContractList(List<DscSignContractDto> signContractList) {
		this.signContractList = signContractList;
	}

	public DscUserActionDto getUserActionDto() {
		return userActionDto;
	}

	public void setUserActionDto(DscUserActionDto userActionDto) {
		this.userActionDto = userActionDto;
	}

	public DscOperateEnvDto getOperateEnvDto() {
		return operateEnvDto;
	}

	public void setOperateEnvDto(DscOperateEnvDto operateEnvDto) {
		this.operateEnvDto = operateEnvDto;
	}
}
