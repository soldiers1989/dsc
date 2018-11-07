package com.yixin.kepler.v1.datapackage.dto.icbc;/**
 * Created by liushuai2 on 2017/8/21.
 */

import java.io.Serializable;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

/**
 * 工行共用申请的DTO
 * Package : com.yixin.kapler_v1.datapackage.dto
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年9月11日 下午2:52:28
 *
 */
@JsonIgnoreProperties(ignoreUnknown = true)
public class IcbcApplyDTO<T> implements Serializable {

    /**
	 * 
	 * @author YixinCapital -- chen.lin
	 *		   2018年9月11日 下午2:52:18
	 *
	 */
	private static final long serialVersionUID = -7274530297035128132L;

	private IcbcCommonDTO comm;

    private T data;
    
    public IcbcApplyDTO(){

    }
    
    public IcbcApplyDTO(IcbcCommonDTO comm, T data) {
        this.comm = comm;
        this.data = data;
    }

    public IcbcCommonDTO getComm() {
        return comm;
    }

    public void setComm(IcbcCommonDTO comm) {
        this.comm = comm;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
