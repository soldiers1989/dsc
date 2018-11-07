package com.yixin.kepler.v1.datapackage.dto.icbc;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 收妥应答返回data数据
 * Package : com.yixin.kapler_v1.datapackage.dto
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年9月12日 上午10:22:21
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IcbcAckDataDTO implements Serializable{
	
    /**
	 * 
	 * @author YixinCapital -- chen.lin
	 *		   2018年9月12日 上午10:22:07
	 *
	 */
	private static final long serialVersionUID = -8581112179652264439L;

	/**
     * 业务订单号
     */
    private String orderno;

    /**
     * 返回代码
     */
    private String procflag;

    /**
     * 错误说明
     */
    private String errmsg;


    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getProcflag() {
        return procflag;
    }

    public void setProcflag(String procflag) {
        this.procflag = procflag;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
