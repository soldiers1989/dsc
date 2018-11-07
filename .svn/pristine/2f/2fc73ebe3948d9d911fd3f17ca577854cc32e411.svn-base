package com.yixin.kepler.dto.webank;

import java.io.Serializable;

import com.alibaba.fastjson.annotation.JSONField;
import com.yixin.kepler.common.enums.WBCallbackEnum;

/**
 * 微众回调DTO
 * Package : com.yixin.kepler.dto.webank
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月20日 上午11:02:17
 *
 */
public class WBCallbackRepDTO implements Serializable{

	private static final long serialVersionUID = 1696605049145317688L;
	
	@JSONField(name = "CODE")
	private String code;
	
	@JSONField(name = "DESC")
	private String desc;
	
	public WBCallbackRepDTO() {
		super();
	}

	public WBCallbackRepDTO(WBCallbackEnum codeEnum) {
		this.code = codeEnum.getCode();
		this.desc = codeEnum.getDesc();
	}
	
	public WBCallbackRepDTO(String code, String desc) {
		super();
		this.code = code;
		this.desc = desc;
	}
	
	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public String getDesc() {
		return desc;
	}

	public void setDesc(String desc) {
		this.desc = desc;
	}

	@Override
	public String toString() {
		return "WBCallbackRepDTO [code=" + code + ", desc=" + desc + "]";
	}
}
