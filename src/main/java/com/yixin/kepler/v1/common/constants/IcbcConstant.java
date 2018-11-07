package com.yixin.kepler.v1.common.constants;

/**
 * 工商银行常量代码类
 * 
 * Package : com.yixin.kapler_v1.common.constants
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年9月11日 上午9:53:01
 *
 */
public class IcbcConstant {

    //***********************工行交易申请：资管———>银行常量开始 chenlin***********************//
    /**
     * 合作机构代码
     */
    public static final String UNITCODE = "100001";
    
    /**
     * 业务渠道代码--PC端
     */
    public static final String CHANNEL_PC = "pc";
    
    /**
     * 请求方标识码--工行
     */
    public static final String SENDCODE_YXCD = "yxcd";
    
    /**
     * 数字签名域
     */
    public static final String SIGNCODE = "";
    
    /**
     * 工行交易流水识别
     */
    public static final String TRAN_BIZCODE = "006";
    
    /**
     * 工行交易流水识别
     */
    public static final String TRAN_SYSCODE = "creditfront";
    
    /**
     * 工行交易申请编号识别
     */
    public static final String ORDERNO_BIZCODE = "001";
    
    /**
     * 工行交易申请编号识别
     */
    public static final String ORDERNO_SYSCODE = "NSSP";
    
    /**
     * 紧急标识  普通
     */
    public static final String ICBC_URGENTFLAG_NORMAL = "0";
    
    /**
     * 文件传输标志  1 文件传送
     */
    public static final String ICBC_FILETRANS_YES = "1";
    
    /**
     * 接口代码--信审
     */
    public static final String TRXCODE_10101 = "10101";
    public static final String TRXCODE_10201 = "10201";
    public static final String TRXCODE_10181 = "10181";
    
    /**
     * 接口代码--面签
     */
    public static final String TRXCODE_30101 = "30101";
    
    /**
     * 接口代码 --请款
     */
    public static final String TRXCODE_40101 = "40101";
    public static final String TRXCODE_40201 = "40201";
    public static final String TRXCODE_40181 = "40181";
    
    /**
     * 处理结果代码 --审批通过
     */
    public static final String APPROVAL_CODE_SUCCESS = "00000000";
    
    /**
     * 处理结果代码 --驳回 
     */
    public static final String APPROVAL_CODE_SYS_AUTO_REVERSE = "2000XXXX";//本阶段退回代码：系统判断自动退回
    public static final String APPROVAL_CODE_MANUAL_REVERSE = "3000XXXX";//本阶段退回代码：人工判断退回
    
    
    /**
     * 处理结果代码 --合同拒绝
     */
    public static final String APPROVAL_CODE_CONTRACT_REJECT = "00001001";
    //***********************工行交易申请：资管———>银行常量结束 chenlin***********************//
    
    //***********************工行交易申请：银行———>资管常量开始 chenlin***********************//
    
    /**
     * 收妥---返回结果代码 --收妥
     */
    public static final String PROCFLAG_CODE_SUCCESS = "00000000";
    
    /**
     * 收妥---返回结果代码 --其他错误
     */
    public static final String PROCFLAG_CODE_OTHER_ERROR = "1000XXXX";

    /**
     * 收妥---返回结果代码 --文件内容异常
     */
    public static final String PROCFLAG_CODE_FILE_CONTENT_ERROR = "10000001";
    
    /**
     * 收妥---返回结果代码 --文件解压错误
     */
    public static final String PROCFLAG_CODE_FILE_UNZIP_ERROR = "10000002";
    
    //***********************工行交易申请：银行———>资管常量结束 chenlin***********************//
   
    //***********************osb文件常量开始 chenlin***********************//
    public static final String LAST_APPLY_PHASE = "last_trial";

    public static final String REQUEST_FUNDS_APPLY_PHASE = "requestFundsApply";
    
    public static final String ICBC_FILE_ZIP = ".zip";
    
    public static final String ICBC_FILE_PASSWORD = "e1212e1";//文件加密密码-自定义
    
    public static final String ICBC_FILE_NAME = "11111.zip";//打包文件名-自定义
    
    public static final String ICBC_FILE_SIZE = "500";//文件字节数-自定义
    //***********************osb文件常量结束 chenlin***********************//
    
    //***********************银行回调码值开始 chenlin***********************//
    public static final String letterReview = "letterReview";//信审
    public static final String faceTackReview = "faceTackReview";//面签
    public static final String requestFundsReview = "requestFundsReview";//请款
    //***********************银行回调码值结束 chenlin***********************//
    
    //***********************压缩常量开始 chenlin***********************//
    public static final double scale = 0.8;//默认压缩比例
    public static final long maxSize = 1024;//默认的最大压缩值
    //***********************压缩常量结束 chenlin***********************//

    
    


    /**
     * 请求方标识码--工行
     */
    public static final String SENDCODE_ICBC = "icbc";
    /**
     * 接口代码 --风控电核
     */
    public static final String TRXCODE_20101 = "20101";


    /**
     * 处理结果代码 --征信不通过
     */
    public static final String APPROVAL_CODE_CREDIT_UNPASS_REJECT = "90000001";
    
    /**
     * 处理结果代码 --重电话核实不通过
     */
    public static final String APPROVAL_PHONE_UNPASS_REJECT = "90000002";
    
    /**
     * 处理结果代码 --面签非本人
     */
    public static final String APPROVAL_INTERVIEW_UNPASS_REJECT = "90000003";
    
    /**
     * 处理结果代码 --客户所述内容与申请资料不符
     */
    public static final String APPROVAL_CUST_UNPASS_REJECT = "90000004";
    
    /**
     * 处理结果代码 --重查人行客户资质劣变
     */
    public static final String APPROVAL_CODE_CUST_UNPASS_REJECT = "90000005";

    /**
     * 处理结果代码 --命中黑名单
     */
    public static final String APPROVAL_CODE_BLACKLIST_UNPASS_CODE = "90000006";
    
    /**
     * 处理结果代码 --命中灰名单
     */
    public static final String APPROVAL_CODE_GREYLIST_UNPASS_CODE = "90000007";
    
    /**
     * 处理结果代码 --反欺诈不通过
     */
    public static final String APPROVAL_CODE_ANTIFRAUD_UNPASS_CODE = "90000008";
    
    /**
     * 处理结果代码 --反洗钱不通过
     */
    public static final String APPROVAL_CODE_ANTIMONEYLAUNDERING_UNPASS_CODE = "90000009";

    /**
     * 工行请求类型--资料申请
     */
    public static final String ICBC_REQUEST_FIRST_TRAIL_TYPE = "ICBC_10201";
    
    /**
     * 工行请求类型--请款
     */
    public static final String ICBC_REQUEST_FUND_TYPE = "ICBC_40201";

    //工行初审字段--国籍码值-中国
    public static final String ICBC_FIRST_COUNTRY = "156";
    /**
     * 工行初审业务字段默认值  0
     */
    public static final String ICBC_FIRST_DEFAULT = "0";
    /**
     * 筛查结果(易鑫)
     */
    public static final String ICBC_SCREEN_RESULT = "1";
    //modified by gumanxue 201721013  通知风控电核结果
    /**
     * 风控电核结果 --客户取消
     */
    public static final String ICBC_CHECK_RESULT_ALLSTATUS_DE = "2";
    /**
     * 风控电核结果 --提回取消
     */
    public static final String ICBC_CHECK_RESULT_ALLSTATUS_DG = "3";
    /**
     * 风控电核结果 --风控拒绝
     */
    public static final String ICBC_CHECK_RESULT_ALLSTATUS_AT = "4";
    /**
     * 风控电核结果 --其他取消
     */
    public static final String ICBC_CHECK_RESULT = "5";
    /**
     * 	补息标识 	0-不补息  1-补息
     */
    public static final String NEED_BX = "1";
    public static final String UN_NEED_BX = "0";
    /**
     * 	现单位工作年限，默认3年
     */
    public static final String WORKYEARS1 = "3";
    /**
     * 	工作单位类型，默认110(民营)
     */
    public static final String COMPTYPE1 = "110";
    /**
     * 	1-补息
     */
    public static final String TXFLAG_1 = "1";
    /**
     * 	0-不补息
     */
    public static final String TXFLAG_0 = "0";

}
