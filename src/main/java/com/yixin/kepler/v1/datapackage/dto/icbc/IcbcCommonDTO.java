package com.yixin.kepler.v1.datapackage.dto.icbc;
import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 工行接口common域
 * Package : com.yixin.kapler_v1.datapackage.dto
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年9月11日 下午2:50:17
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IcbcCommonDTO implements Serializable {


    /**
	 * 
	 * @author YixinCapital -- chen.lin
	 *		   2018年9月11日 下午2:50:58
	 *
	 */
	private static final long serialVersionUID = 3783777387836769357L;

	/**
     * 合作机构代码
     */
    private String unitcode;

    /**
     * 接口代码
     */
    private String trxcode;

    /**
     * 业务订单号
     */
    private String orderno;

    /**
     * 报文发送时间
     */
    private String sendtime;

    /**
     * 交易流水号
     */
    private String sendserno;

    /**
     * 业务发起渠道
     */
    private String channel;

    /**
     * 请求方标识码
     */
    private String sendcode;

    /**
     * 数字签名域
     */
    private String signcode;

    
    public String getUnitcode() {
        return unitcode;
    }

    public void setUnitcode(String unitcode) {
        this.unitcode = unitcode;
    }

    public String getTrxcode() {
        return trxcode;
    }

    public void setTrxcode(String trxcode) {
        this.trxcode = trxcode;
    }

    public String getOrderno() {
        return orderno;
    }

    public void setOrderno(String orderno) {
        this.orderno = orderno;
    }

    public String getSendtime() {
        return sendtime;
    }

    public void setSendtime(String sendtime) {
        this.sendtime = sendtime;
    }

    public String getSendserno() {
        return sendserno;
    }

    public void setSendserno(String sendserno) {
        this.sendserno = sendserno;
    }

    public String getChannel() {
        return channel;
    }

    public void setChannel(String channel) {
        this.channel = channel;
    }

    public String getSendcode() {
        return sendcode;
    }

    public void setSendcode(String sendcode) {
        this.sendcode = sendcode;
    }

    public String getSigncode() {
        return signcode;
    }

    public void setSigncode(String signcode) {
        this.signcode = signcode;
    }

}
