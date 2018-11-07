package com.yixin.kepler.v1.common.constants;

/**
 * 银行请求公共码值
 * Package : com.yixin.kapler_v1.common.constants
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年9月17日 上午11:26:20
 *
 */
public class BankRequestConstant {

    //***********************异步请求银行：资管———>银行常量开始 chenlin***********************//
	/**
	 * 异步请求银行标识		0-未发起
	 */
    public static final String UN_REQ_BANK = "0";
    
	/**
	 * 异步请求银行标识		1-处理中
	 */
    public static final String REQ_BANK_INHAND = "1";
    
	/**
	 * 异步请求银行标识		2-发起成功
	 */
    public static final String REQ_BANK_SUCCESS = "2";
    
	/**
	 * 异步请求银行标识		3-发起失败[银行返回未收妥] 
	 */
    public static final String REQ_BANK_FAIL = "3";
    
	/**
	 * 异步请求银行标识		4-发起失败后可重试[通讯等问题响应空]
	 */
    public static final String RETRY_REQ_BANK = "4";
    
	/**
	 * 异步请求银行标识		5-处理结果时发生异常
	 */
    public static final String RETRY_REQ_EXCEP = "5";
    
    /**
     * 异步请求银行重试初始化次数	
     */
    public static final int RETRY_INIT_COUNT = 0;
    
    /**
     * 异步请求银行重试最大次数	
     */
    public static final int RETRY_MAX_COUNT = 4;
    
	/**
	 * 异步请求银行osb处理状态		1-成功
	 */
    public static final String OSB_SUCCESS = "1";
    
	/**
	 * 终审集合
	 */
    public static final String[] LAST_TRIAL = {"last_trial","last_trial_reject"};
    
	/**
	 * 请款集合
	 */
    public static final String[] PAY_MENT = {"payment","payment_reject"};
    
    //***********************异步请求银行：资管———>银行常量结束 chenlin***********************//
}
