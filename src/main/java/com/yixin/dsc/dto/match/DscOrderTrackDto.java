package com.yixin.dsc.dto.match;

import com.yixin.common.utils.BaseDTO;

/**
 * 订单痕迹表
 * Package : com.yixin.dsc.entity.kepler
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年8月24日 下午4:17:19
 *
 */
public class DscOrderTrackDto extends BaseDTO{

	private static final long serialVersionUID = 3516244056900593859L;

	private String applyNo;
	
	/**
	 * 参考 OrderTrackTypeEnum
	 */
	private String trackType;
	
	private String trackName;
	
	private Boolean successFlag;
	
	private String message;
	
	private String sign;
	
	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getTrackType() {
		return trackType;
	}

	public void setTrackType(String trackType) {
		this.trackType = trackType;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public Boolean getSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(Boolean successFlag) {
		this.successFlag = successFlag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}
}
