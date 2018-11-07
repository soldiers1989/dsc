package com.yixin.kepler.v1.datapackage.dto;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 回调数据集DTO
 * Package : com.yixin.kepler.v1.datapackage.dto
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年9月25日 上午11:43:15
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class CallBackDTO implements Serializable{
	

	/**
	 * 
	 * @author YixinCapital -- chen.lin
	 *		   2018年9月25日 上午11:43:06
	 *
	 */
	private static final long serialVersionUID = 1666082794600432356L;

	/**
     * 回调类型
     */
    private String callBackType;

	/**
	 * 资方来源
	 */
    private String sourceCode;

    /**
     * 资方申请编号 = venus申请编号
     */
    private String capitalOrderNO;

    /**
     * 业务数据
     */
    private Object businessData;
    

	public String getCallBackType() {
		return callBackType;
	}

	public void setCallBackType(String callBackType) {
		this.callBackType = callBackType;
	}

	public String getSourceCode() {
		return sourceCode;
	}

	public void setSourceCode(String sourceCode) {
		this.sourceCode = sourceCode;
	}

	public String getCapitalOrderNO() {
		return capitalOrderNO;
	}

	public void setCapitalOrderNO(String capitalOrderNO) {
		this.capitalOrderNO = capitalOrderNO;
	}

	public Object getBusinessData() {
		return businessData;
	}

	public void setBusinessData(Object businessData) {
		this.businessData = businessData;
	}
}
