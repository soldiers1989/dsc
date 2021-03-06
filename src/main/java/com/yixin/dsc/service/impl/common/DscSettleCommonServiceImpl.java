package com.yixin.dsc.service.impl.common;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Map;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.yixin.common.utils.InvokeResult;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.dsc.common.exception.BzException;
import com.yixin.dsc.dto.DscContractCancelDto;
import com.yixin.dsc.dto.query.DscFundsQueryDto;
import com.yixin.dsc.dto.query.DscSettleCalculationParamDto;
import com.yixin.dsc.dto.query.DscSettleCalculationResultDto;
import com.yixin.dsc.dto.query.DscSettleLoanInfoDto;
import com.yixin.dsc.service.common.DscSettleCommonService;
import com.yixin.dsc.util.DateUtil;
import com.yixin.dsc.util.PropertiesManager;
import com.yixin.kepler.common.RestTemplateUtil;
import com.yixin.kepler.common.UrlConstant;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.enity.AssetBankTran;
import com.yixin.kepler.enity.AssetFinanceInfo;
import com.yixin.kepler.enity.AssetMainInfo;
import com.yixin.kepler.v1.datapackage.dto.other.SettleFkDTO;

/**
 * @author YixinCapital -- chenjiacheng
 *         2018年09月10日 10:12
 **/
@Service("dscSettleCommonService")
public class DscSettleCommonServiceImpl implements DscSettleCommonService {

    private static final Logger logger = LoggerFactory.getLogger(DscSettleCommonServiceImpl.class);

    @Resource
    private PropertiesManager propertiesManager;


    @Override
    public DscSettleCalculationResultDto settleComputer(String applyNo, Date apptdt, String bankSymbol) {
        if (StringUtils.isBlank(applyNo)) {
            throw new BzException("申请编号不可为空");
        }
        if (apptdt == null) {
            throw new BzException("试算申请日期不可为空");
        }
        if (StringUtils.isBlank(bankSymbol)) {
            throw new BzException("银行标志不可为空");
        }

        DscSettleCalculationParamDto paramDto = new DscSettleCalculationParamDto();
        paramDto.setApplyNo(applyNo);
        paramDto.setApptdt(DateUtil.dateToString(apptdt, "yyyyMMdd"));

        paramDto.setBankSymbol(bankSymbol);
        if (CommonConstant.BankName.WB_BANK.equals(bankSymbol)) { //微众
            paramDto.setBusinessFlag(CommonConstant.SettleVariable.VALET); //业务标识
        }

        String url = propertiesManager.getSettleWebEnvironment() + UrlConstant.SettleSystemUrl.preRepaymentTrial;
        String respData = "";
        try {
            logger.info("请求结算系统退车试算接口,url={},params={}", url, JSON.toJSONString(paramDto));
            respData = RestTemplateUtil.sendRequest(url, paramDto, null);
            logger.info("请求结算系统退车试算接口,url={},params={},返回报文:{}", url, JSON.toJSONString(paramDto), respData);
        } catch (Exception e) {
            logger.error("请求结算系统退车试算接口异常，订单编号:{}", applyNo, e);
            throw new BzException("退车试算出错");
        }
        if (StringUtils.isBlank(respData)) {
            logger.error("请求结算系统退车试算接口，订单编号：{}，获返回值为空", applyNo);
            throw new BzException("退车试算出错");
        }
        JSONObject synJson = JSONObject.parseObject(respData);
        if (!"true".equals(synJson.getString("success"))) {
            logger.error("请求结算系统退车试算接口，申请编号：{},synJson.optString('success')为false", applyNo);
            throw new BzException("退车试算出错");
        }
        if (StringUtils.isBlank(synJson.getString("data"))) {
            logger.error("请求结算系统退车试算接口，申请编号：{}，返回值synJson.optString('data')为null或者为空", applyNo);
            throw new BzException("退车试算出错");
        }
        DscSettleCalculationResultDto result = JSON.parseObject(synJson.getString("data"), DscSettleCalculationResultDto.class);
        logger.info("请求结算系统退车试算接口,url={},订单编号:{},解析后dto:{}", url, applyNo, JSON.toJSONString(result));
        return result;
    }


    /**
     * 获取贷款信息
     *
     * @param applyNo 申请编号
     * @return
     * @author YixinCapital -- wangwenlong
     * 2018年9月10日 下午7:51:04
     */
    @Override
    public DscSettleLoanInfoDto getLoanInfo(String applyNo) {
        if (StringUtils.isBlank(applyNo)) {
            throw new BzException("申请编号不可为空");
        }
        Map<String, String> params = Maps.newHashMap();
        params.put("applyNo", applyNo);
        String url = propertiesManager.getSettleWebEnvironment() + UrlConstant.SettleSystemUrl.getLoanInfo;
        String respData = "";
        try {
            logger.info("请求结算系统获取贷款信息接口,url={},params={}", url, JSON.toJSONString(params));
            respData = RestTemplateUtil.sendRequest(url, params, null);
            logger.info("请求结算系统获取贷款信息接口,url={},params={},返回报文:{}", url, JSON.toJSONString(params), respData);
        } catch (Exception e) {
            logger.error("请求结算系统获取贷款信息接口异常，订单编号:{}", applyNo, e);
            throw new BzException("获取贷款信息出错");
        }
        if (StringUtils.isBlank(respData)) {
            logger.error("请求结算系统退车试算接口，订单编号：{}，获返回值为空", applyNo);
            throw new BzException("获取贷款信息出错");
        }
        JSONObject synJson = JSONObject.parseObject(respData);
        if (!"true".equals(synJson.getString("success"))) {
            logger.error("请求结算系统退车试算接口，申请编号：{},synJson.optString('success')为false", applyNo);
            throw new BzException("获取贷款信息出错");
        }
        if (StringUtils.isBlank(synJson.getString("data"))) {
            logger.error("请求结算系统退车试算接口，申请编号：{}，返回值synJson.optString('data')为null或者为空", applyNo);
            throw new BzException("获取贷款信息出错");
        }
        DscSettleLoanInfoDto result = JSON.parseObject(synJson.getString("data"), DscSettleLoanInfoDto.class);
        if (StringUtils.isNotBlank(result.getFirstRepayDate())) {
            try {
                Date FirstRepayDateTime = DateUtil.StringToDate(result.getFirstRepayDate(), DateUtil.DEFAULT_DATE_SMALL);
                result.setFirstRepayDateTime(FirstRepayDateTime);
            } catch (Exception e) {
                logger.error("请求结算系统退车试算接口，申请编号：{}，转换第一还款到期日异常：{}", applyNo, result.getFirstRepayDate(), e);
            }
        }
        logger.info("请求结算系统获取贷款信息接口,url={},订单编号:{},解析后dto:{}", url, applyNo, JSON.toJSONString(result));
        return result;
    }

    /**
     * 获取取消标识
     *
     * @param applyNo     申请编号
     * @param financeCode 资方编码
     * @return
     * @author YixinCapital -- wangwenlong
     * 2018年9月10日 下午8:09:04
     */
    @Override
    public DscContractCancelDto getCancelInfo(String applyNo, String financeCode) {
        DscContractCancelDto cancelDto = new DscContractCancelDto();
        Date currentDate = new Date(); //当前时间
        DscSettleCalculationResultDto calResult = null;
        try {
            calResult = this.settleComputer(applyNo, currentDate, financeCode);
        } catch (BzException e) {
            logger.error("退车试算接口失败,applyNo:{},错误信息:{}", applyNo, e.getMessage());
        } catch (Exception e) {
            logger.error("退车试算接口失败,applyNo{}", applyNo, e);
        }
        if (calResult != null && StringUtils.isNotBlank(calResult.getInterest())) {
            try {
                cancelDto.setBankInterest(new BigDecimal(calResult.getInterest()));  //利息
            } catch (Exception e) {
                logger.error("退车试算接口失败,利息转换BigDecimal异常,applyNo{}", applyNo, e);
            }
        }
        DscSettleLoanInfoDto loanInfoDto = null;
        try {
            loanInfoDto = this.getLoanInfo(applyNo);
        } catch (BzException e) {
            logger.error("获取贷款信息失败,applyNo:{},错误信息:{}", applyNo, e.getMessage());
        } catch (Exception e) {
            logger.error("获取贷款信息失败,applyNo{}", applyNo, e);
        }
        if (loanInfoDto != null) {
            cancelDto.setFirstRepayDate(loanInfoDto.getFirstRepayDateTime()); //第一还款到期日
            cancelDto.setCurrentDate(currentDate); //当前日期
            cancelDto.setIsLoan(loanInfoDto.getIsLoan()); //放款是否成功
            cancelDto.setIsAllowed(loanInfoDto.getIsAllowed()); //是否支持推车
        }
        return cancelDto;
    }


    @Override
    public InvokeResult queryBankFunds(DscFundsQueryDto queryParam) {

        InvokeResult result = new InvokeResult();

        String url = propertiesManager.getSettleWebEnvironment() + UrlConstant.SettleSystemUrl.queryBankFunds;

        logger.info("请求结算系统查询扣补息款、退回补息款、分润信息接口,url={},params={}", url, JSON.toJSONString(queryParam));
        String respData = RestTemplateUtil.sendRequestNoBaffle(url, queryParam);
        logger.info("请求结算系统查询扣补息款、退回补息款、分润信息接口,url={},params={},返回报文:{}", url, JSON.toJSONString(queryParam), respData);

        if (StringUtils.isBlank(respData)) {
            logger.error("请求结算系统查询扣补息款、退回补息款、分润信息接口，获返回值为空");
            throw new BzException("查询银行款项信息失败");
        }

        JSONObject synJson = JSONObject.parseObject(respData);
        if (!"true".equals(synJson.getString("success"))) {
            logger.error("请求结算系统查询扣补息款、退回补息款、分润信息接口，synJson.optString('success')为false");
            throw new BzException("查询银行款项信息失败");
        }

        Object data = synJson.get("data");

        result.success();
        result.setData(data);
        return result;
    }

    /**
	 * 向结算推送放款信息
	 * @param applyNo 订单编号
	 * @param bankSeq 银行流水
	 * @param loanDime 放款时间
	 * @param loanAmount 放款金额
 	 * @param loanSuccess 是否放款成功
	 * @return 是否推送成功
	 * @author YixinCapital -- wangwenlong
	 *	       2018年10月15日 下午1:34:41
	 */
	public Boolean pushFk(String applyNo, String bankSeq, Date loanDime, BigDecimal loanAmount, Boolean loanSuccess) {
		SettleFkDTO fkDto = new SettleFkDTO();
		fkDto.setApplyNo(applyNo); //订单编号
		fkDto.setBankSeq(bankSeq); //银行流水
		fkDto.setTranAm(loanAmount); //放款金额
		fkDto.setLoanDt(DateUtil.dateToString(loanDime, DateUtil.DEFAULT_DATE_SMALL));
		fkDto.setAppvResult(loanSuccess?"S":"E");
		
		AssetMainInfo mainInfo = AssetMainInfo.getByApplyNo(applyNo);
		if(mainInfo != null && StringUtils.isNotBlank(mainInfo.getFinancialId())){
			fkDto.setBankName(AssetFinanceInfo.getFinanceNameById(mainInfo.getFinancialId())); //资方名称
		}
		String url = propertiesManager.getSettleWebEnvironment() + UrlConstant.SettleSystemUrl.pushFk;
		
		//记录交易信息
        AssetBankTran assetBankTran = new AssetBankTran();
        assetBankTran.setApplyNo(applyNo);
        assetBankTran.setReqBody(JsonObjectUtils.objectToJson(fkDto));
        assetBankTran.setReqUrl(url);
        assetBankTran.setPhase(BankPhaseEnum.PUSHFILE.getPhase());
        assetBankTran.create();
        
		String respData = "";
		try {
            logger.info("请求结算系统推送订单放款信息接口,url={},订单编号:{},params={}", url, applyNo,assetBankTran.getReqBody());
            respData = RestTemplateUtil.sendRequest(url, assetBankTran.getReqBody(), null);
            logger.info("请求结算系统推送订单放款信息接口,url={},订单编号:{},返回报文:{}", url,  applyNo, respData);
        } catch (Exception e) {
            logger.error("请求结算系统推送订单放款信息接口异常，订单编号:{}", applyNo, e);
            assetBankTran.setApprovalCode("请求结算系统推送订单放款信息接口异常");
            assetBankTran.setApprovalDesc("请求结算系统推送订单放款信息接口异常");
            assetBankTran.update();
            throw new BzException("推送订单放款信息出错");
        }
		assetBankTran.setRepBody(respData);
        if (StringUtils.isBlank(respData)) {
            logger.error("请求结算系统推送订单放款信息接口，订单编号：{}，获返回值为空", applyNo);
            assetBankTran.setApprovalCode("请求结算系统推送订单放款信息接口获返回值为空");
            assetBankTran.setApprovalDesc("请求结算系统推送订单放款信息接口获返回值为空");
            assetBankTran.update();
            throw new BzException("推送订单放款信息出错");
        }
        JSONObject synJson = JSONObject.parseObject(respData);
        if (!"true".equals(synJson.getString("success"))) {
            logger.error("请求结算系统推送订单放款信息接口，申请编号：{},synJson.optString('success')为false", applyNo);
            assetBankTran.setApprovalCode(synJson.getString("success"));
            assetBankTran.setApprovalDesc(synJson.getString("errorMessage"));
            assetBankTran.update();
            throw new BzException("推送订单放款信息出错");
        }
        assetBankTran.setApprovalCode(synJson.getString("success"));
        assetBankTran.setApprovalDesc(synJson.getString("errorMessage"));
        assetBankTran.update();
		return true;
	}
}
