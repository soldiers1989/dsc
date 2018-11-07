package com.yixin.dsc.dto.order;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * Package : com.yixin.dsc.dto.order
 *
 * @author YixinCapital -- wanghonglin
 * 2018/10/25 11:36
 */
public class DscComputeDTO implements Serializable {
    private static final long serialVersionUID = -375878699957214178L;

    /**
     * 服务费
     */
    private BigDecimal settleComputeFee;

	public BigDecimal getSettleComputeFee() {
		return settleComputeFee;
	}

	public void setSettleComputeFee(BigDecimal settleComputeFee) {
		this.settleComputeFee = settleComputeFee;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

    
}