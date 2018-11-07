package com.yixin.kepler.dto;

import java.io.Serializable;

public class AccessRulesDTO implements Serializable {

	private static final long serialVersionUID = 1L;
	
	private boolean last_req_time;

	public boolean isLast_req_time() {
		return last_req_time;
	}

	public void setLast_req_time(boolean last_req_time) {
		this.last_req_time = last_req_time;
	}

}
