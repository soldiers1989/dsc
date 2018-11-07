package com.yixin.dsc.common.exception;

/**
 * 基础异常类
 * Package : com.yixin.dsc.common.exception
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月6日 下午4:28:20
 *
 */
public class BzException extends RuntimeException {

	/**
	 * 
	 */
	private static final long serialVersionUID = 3998181746365111082L;

	public BzException() {
	}

	public BzException(String message) {
		super(message);
	}

	public BzException(Throwable cause) {
		super(cause);
	}

	public BzException(String message, Throwable cause) {
		super(message, cause);
	}
}
