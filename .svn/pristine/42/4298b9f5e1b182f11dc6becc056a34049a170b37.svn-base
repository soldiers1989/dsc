package com.yixin.kepler.dto;

import java.io.Serializable;
/**
 * 接口返回值对象
 * @author sukang
 *
 * @param <T>
 */
public class RespMessageDTO<T extends Object> implements Serializable{
	
	private static final long serialVersionUID = 1L;
	
	private T data;
	
	private String errorMessage;
	
	private boolean hasErrors;
	
	
	public static <T> RespMessageDTO<T> getInstance(Class<T> clazz){
		return new RespMessageDTO<T>();
	}
	
	public RespMessageDTO<T> success(){
		this.setHasErrors(false);
		this.setErrorMessage("success");
		return this;
	}
	
	public RespMessageDTO<T> fail(String failMsg){
		this.setHasErrors(true);
		this.setErrorMessage(failMsg);
		return this;
	}
	
	
	public RespMessageDTO<T> hashErrors(boolean hasError){
		this.setHasErrors(hasError);
		return this;
	}
	
	public RespMessageDTO<T> errorMessage(String errorMessage){
		this.setErrorMessage(errorMessage);
		return this;
	}
	
	public RespMessageDTO<T> data(T data){
		this.setData(data);
		return this;
	}
	
	
	private RespMessageDTO(){}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}

	public String getErrorMessage() {
		return errorMessage;
	}

	public void setErrorMessage(String errorMessage) {
		this.errorMessage = errorMessage;
	}

	public boolean isHasErrors() {
		return hasErrors;
	}

	public void setHasErrors(boolean hasErrors) {
		this.hasErrors = hasErrors;
	}
		
	
	@Override
	public String toString() {
		return "RespMessageDTO [data=" + data + ", errorMessage=" + errorMessage + ", hasErrors=" + hasErrors + "]";
	}
}
