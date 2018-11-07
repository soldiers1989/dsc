package com.yixin.kepler.dto;

import java.io.Serializable;

import com.yixin.kepler.core.constant.CommonConstant;
import org.apache.commons.lang.builder.ToStringBuilder;

/**
 * @author sukang
 * @date 2018-08-28 13:10:43
 */
public class BaseMsgDTO implements Serializable {

	private static final long serialVersionUID = 7230484378011436192L;

	private String code;

    private String message;

    public BaseMsgDTO() {
		super();
	}


	public boolean successed(){
        return CommonConstant.SUCCESS.equals(this.getCode());
    }
    
    public static BaseMsgDTO failureData(String failureMsg){
    	return new BaseMsgDTO(CommonConstant.FAILURE, failureMsg);
    }
    
    public static BaseMsgDTO processData(String processMsg){
    	return new BaseMsgDTO(CommonConstant.PROCESSING, processMsg);
    }
    
    public static BaseMsgDTO successData(String successMsg){
    	return new BaseMsgDTO(CommonConstant.SUCCESS, successMsg);
    }
    
    public static BaseMsgDTO untreatedReq(){
    	return new BaseMsgDTO(CommonConstant.UNTREATED, "该订单未处理");
    }

	public BaseMsgDTO(String code, String message) {
		super();
		this.code = code;
		this.message = message;
	}

	public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("BaseMsgDTO{");
        sb.append("code='").append(code).append('\'');
        sb.append(", message='").append(message).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
