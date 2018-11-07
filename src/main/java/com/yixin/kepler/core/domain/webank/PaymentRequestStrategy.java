package com.yixin.kepler.core.domain.webank;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yixin.common.exception.BzException;
import com.yixin.common.utils.InvokeResult;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.dsc.entity.order.DscSalesApplyCar;
import com.yixin.dsc.entity.order.DscSalesApplyCost;
import com.yixin.dsc.entity.order.DscSalesApplyCust;
import com.yixin.dsc.entity.order.DscSalesApplyFinancing;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.dsc.service.common.DscWbCommonService;
import com.yixin.dsc.util.PropertiesManager;
import com.yixin.kepler.common.RestTemplateUtil;
import com.yixin.kepler.common.UrlConstant;
import com.yixin.kepler.common.enums.AssetPhaseEnum;
import com.yixin.kepler.common.enums.AssetStateEnum;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.common.enums.WBCarTypeEnum;
import com.yixin.kepler.common.enums.WBTransCodeEnum;
import com.yixin.kepler.core.base.AbstractBaseDealDomain;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.core.domain.SysDIcMapped;
import com.yixin.kepler.core.domain.cmbc.util.CMBCUtil;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.dto.webank.WBLoanDTO;
import com.yixin.kepler.dto.webank.WBLoanResultRespDTO;
import com.yixin.kepler.dto.webank.WBPaymentFeeDTO;
import com.yixin.kepler.dto.webank.WBPaymentLoanReceiptDTO;
import com.yixin.kepler.dto.webank.WBPaymentReqDTO;
import com.yixin.kepler.dto.webank.WBPaymentRespDTO;
import com.yixin.kepler.enity.AssetBankTran;
import com.yixin.kepler.enity.AssetMainInfo;
import com.yixin.kepler.enity.AssetResultTask;
import com.yixin.kepler.enity.SysDict;

/**
 * 请款策略类--微众银行
 *
 * @author YixinCapital -- chenjiacheng
 *         2018年07月09日 15:48
 **/
@Service("WeBankPaymentRequestStrategy")
public class PaymentRequestStrategy extends AbstractBaseDealDomain<Object, BaseMsgDTO> {

    private static final Logger logger = LoggerFactory.getLogger(PaymentRequestStrategy.class);

    @Resource
    private DscWbCommonService dscWbCommonService;

    private ThreadLocal<AssetMainInfo> paymentThreadLocal = new ThreadLocal<>();

    @Resource
    private PropertiesManager propertiesManager;

    @Resource
    private MongoTemplate mongoTemplate;

    @Resource
    private SysDIcMapped sysDIcMapped;

    @Resource
    private WbCommonRequestToBank wbCommonRequestToBank;


    @Override
    protected void getData() throws BzException {
        String applyNo = (String) inputDto.get();
        logger.info("微众银行请款===开始组装数据====applyNo={}", applyNo);
        DscSalesApplyMain main = DscSalesApplyMain.getByApplyNo(applyNo);
        if (main == null) {
            throw new BzException("客户申请数据为空");
        }
        DscSalesApplyCust cust = DscSalesApplyCust.getApplyCostByMianId(main.getId());
        if (cust == null) {
            throw new BzException("客户信息不存在");
        }
        DscSalesApplyCar car = DscSalesApplyCar.getBymainId(main.getId());
        DscSalesApplyCost cost = DscSalesApplyCost.getByMainId(main.getId());

        AssetMainInfo assetMainInfo = AssetMainInfo.getByApplyNo(applyNo);
        
        //=========== add by wangwenlong on 2018-10-29 =====================
        if(StringUtils.isBlank(main.getCapitalId())){
    		throw new BzException("【微众银行请款】订单业务主表有效资方ID为空");
    	}
    	if(StringUtils.isBlank(assetMainInfo.getFinancialId())){
    		throw new BzException("【微众银行请款】银行交易主表有效资方ID为空");
    	}
    	if(!main.getCapitalId().equals(assetMainInfo.getFinancialId())){
    		throw new BzException("【微众银行请款】订单业务主表有效资方ID和银行交易主表有效资方ID不匹配");
    	}
    	
        if(AssetStateEnum.SUCCESS.getState().equals(assetMainInfo.getPaymentState())){ //请款已成功
        	throw new BzException("请款任务已完成，不可再次请款");
        }
        paymentThreadLocal.set(assetMainInfo);

        WBPaymentReqDTO reqDTO = new WBPaymentReqDTO();
        //头部信息
        reqDTO.setTxnId(WBTransCodeEnum.WB_PAYMENT.getTransCode()); //交易服务码
        reqDTO.setMerchantId(dscWbCommonService.getMerchantIdByCompanyCode(main.getRentingCompanyCode())); // 平台id
        reqDTO.setOpId(main.getTlrNo()); //操作员号
        reqDTO.setChannel(dscWbCommonService.getWbChannelByOrderSource(main.getOrderSource())); //渠道
        reqDTO.setReqTime(new Date()); //请求时间
        //订单业务主键
        reqDTO.setMerOrderNo(applyNo); //平台订单号
        reqDTO.setNbsOrderNo(assetMainInfo.getBankOrderNo()); //微众订单号
        reqDTO.setPsCode(dscWbCommonService.getPsCodeByApplyNo(applyNo)); //产品结构编号 【是否新车 是否贴息】
        reqDTO.setName(cust.getAkhxm()); //姓名
        reqDTO.setIdType(dscWbCommonService.codeConvert("azjlx", cust.getAzjlx())); //证件类型 默认01-身份证
        reqDTO.setIdNo(cust.getAzjhm()); //证件号
        //订单车辆主键
        assembleCar(reqDTO, car);
        //交易信息域
        reqDTO.setTxnCode("TC003");// 放款申请
        reqDTO.setTxnDesc("放款申请"); //交易描述
        reqDTO.setTxnDate(new Date()); //交易时间
        //
        assembleFee(reqDTO, main, car, cost);
        assembleGps(reqDTO, car);

        inputDto.set(reqDTO);
        logger.info("数据组装完毕,applyNo={},paymentDto={}", applyNo, JSON.toJSONString(reqDTO));
    }


    @Override
    protected void assembler() throws BzException {
    }


    @Override
    protected InvokeResult<BaseMsgDTO> message() throws BzException {
        AssetMainInfo assetMainInfo = paymentThreadLocal.get();

        String applyNo = assetMainInfo.getApplyNo();

        WBPaymentReqDTO reqDTO = (WBPaymentReqDTO) inputDto.get();
        paymentThreadLocal.remove();
        inputDto.remove();
        AssetBankTran assetBankTran = new AssetBankTran();
        String payMentOsbUrl = propertiesManager.getOsbEnvironment() + UrlConstant.OsbSystemUrl.weBankInterface + WBTransCodeEnum.WB_PAYMENT.getTransCode();
        //添加银行交易信息
        assetBankTran.setReqBody(JsonObjectUtils.objectToJson(reqDTO));
        assetBankTran.setApplyNo(assetMainInfo.getApplyNo());
        assetBankTran.setAssetId(assetMainInfo.getId());

        //资产编号对应银行交易流水
        assetBankTran.setAssetNo(assetMainInfo.getAssetNo());
        assetBankTran.setReqUrl(payMentOsbUrl);
        assetBankTran.setPhase(BankPhaseEnum.PAYMENT.getPhase());
        assetBankTran.setTranNo(CMBCUtil.getTradeNo());
        assetBankTran.setSender(CommonConstant.SenderType.YIXIN);
        assetBankTran.create();

        String resp = "";
		try {
			logger.info("向银行发起请款操作开始,本地参数={},给银行传参={}", JSON.toJSONString(reqDTO), assetBankTran.getReqBody());
			resp = RestTemplateUtil.sendRequest(payMentOsbUrl, reqDTO, assetMainInfo.getFinancialCode());
			logger.info("向银行发起请款操作结束,申请编号:{},result={}", assetMainInfo.getApplyNo(), resp);
		} catch (Exception e) {
			logger.error("微众银行发起请款操作请求异常,进行重试处理，订单编号:{}",assetMainInfo.getApplyNo(),e);
			return new InvokeResult<>(new BaseMsgDTO(CommonConstant.PROCESSING, "进行中"));
		}
		if(StringUtils.isBlank(resp)){
			logger.error("微众银行发起请款操作返回报文为空,进行重试处理，订单编号:{}",assetMainInfo.getApplyNo());
			return new InvokeResult<>(new BaseMsgDTO(CommonConstant.PROCESSING, "进行中"));
		}
        assetBankTran.setRepBody(resp);
        assetMainInfo.setAssetPhase(AssetPhaseEnum.PAYMENT_TRIAL.getPhase());

        JSONObject resultObject = JSONObject.parseObject(resp);
        if (resultObject != null) {
            String respCode = resultObject.getString("code");
            String respMsg = resultObject.getString("msg");

            WBPaymentRespDTO respDTO = new WBPaymentRespDTO();
            if (respCode.contains(CommonConstant.WB_RESP_CODE)) {
                respDTO = (WBPaymentRespDTO) JsonObjectUtils.jsonToObject(resultObject.getString("jsonData"), respDTO);

                //请款成功后的处理
                return loanSuccess(assetMainInfo, assetBankTran, respDTO);
            } else if (respCode.contains(CommonConstant.WeBankErrorCode.TICKET_NONE) || respCode.contains(CommonConstant.WeBankErrorCode.TICKET_INCORRECT)) {

                logger.error("和银行请款返回结果，ticket不存在或不正确,单号：{}", assetMainInfo.getApplyNo());
                return new InvokeResult<>(new BaseMsgDTO(CommonConstant.PROCESSING, "进行中"));
            } else if("66510114".equals(respCode)) { //业务规则引擎检查不通过，命中规则见ERR_CODES
            	logger.error("和银行请款返回结果，66510114/业务规则引擎检查不通过，命中规则见ERR_CODES,处理为请款失败,单号：{}", assetMainInfo.getApplyNo());
                //请款失败后的处理
                return loanFailed(assetMainInfo, assetBankTran, respMsg, respCode);
            } else {
            	//发起请款不成功但也不是明确失败，后查询放款结果
                return loanTradeResultQuery(applyNo, assetMainInfo, assetBankTran, respMsg, respCode);
            }
        }
        assetBankTran.update();
        return new InvokeResult<>(new BaseMsgDTO(CommonConstant.PROCESSING, "进行中"));
    }


    /**
     * 请款成功后的处理
     *
     * @param assetMainInfo
     * @param assetBankTran
     * @param respDTO
     */
    private InvokeResult loanSuccess(AssetMainInfo assetMainInfo, AssetBankTran assetBankTran, WBPaymentRespDTO respDTO) {
        assetMainInfo.setPaymentState(AssetStateEnum.SUCCESS.getState());
        assetMainInfo.update();

        assetBankTran.setApprovalDesc("S");
        assetBankTran.setApprovalCode(respDTO.getCode());
        assetBankTran.update();

        //========== 请款成功 添加放款查询任务表  add by wangwenlong on 2018-09-04================
        AssetResultTask assetResultTask = new AssetResultTask();
        assetResultTask.setBzId(BankPhaseEnum.PAYMENT.getPhase());
        assetResultTask.setApplyNo(assetMainInfo.getApplyNo());
        assetResultTask.setAssetNo(assetMainInfo.getAssetNo());
        assetResultTask.setTranNo(assetBankTran.getTranNo());
        assetResultTask.setTranId(assetBankTran.getId());
        assetResultTask.setExecState(0);
        assetResultTask.setExecTimes(0);
        assetResultTask.setIsEnd(0);
        assetResultTask.setNextTime(new Date());
        assetResultTask.create();

        //将返回结果存mongo一份
        Query query = new Query();
        Criteria criteria = Criteria.where("merOrderNo").is(assetMainInfo.getApplyNo());
        query.addCriteria(criteria);
        mongoTemplate.findAllAndRemove(query, WBPaymentRespDTO.class);
        if (StringUtils.isBlank(respDTO.getMerOrderNo())) {
            respDTO.setMerOrderNo(assetMainInfo.getApplyNo());
        }
        logger.info("将银行返回的请款结果存至mongo,data={}", JSON.toJSONString(respDTO));
        mongoTemplate.save(respDTO);


        return new InvokeResult<>(new BaseMsgDTO(CommonConstant.SUCCESS, "success"));
    }


    /**
     * 请款失败后的处理
     *
     * @param assetMainInfo
     * @param assetBankTran
     * @param approvalDesc
     * @param approvalCode
     */
    private InvokeResult loanFailed(AssetMainInfo assetMainInfo, AssetBankTran assetBankTran, String approvalDesc, String approvalCode) {
        assetBankTran.setApprovalDesc(approvalDesc);
        assetBankTran.setApprovalCode(approvalCode);
        assetBankTran.update();
        assetMainInfo.setPaymentState(AssetStateEnum.FAILD.getState());
        assetMainInfo.update();

        return new InvokeResult<>(new BaseMsgDTO(CommonConstant.FAILURE, assetBankTran.getApprovalDesc()));
    }


    /**
     * 发起请款不成功但也不是明确失败，后查询放款结果
     *
     * @param applyNo
     * @param assetMainInfo
     * @param assetBankTran
     * @return
     */
    private InvokeResult loanTradeResultQuery(String applyNo, AssetMainInfo assetMainInfo, AssetBankTran assetBankTran, String approvalDesc, String approvalCode) {

        logger.info("发起请款不成功但也不是明确失败，后查询放款结果，单号：{}", applyNo);
        WBLoanResultRespDTO response = wbCommonRequestToBank.loanStatusQuery(applyNo);

        boolean loanSuccess = isLoanSuccess(response);

        if (loanSuccess) {

            // WBLoanResultRespDTO 转换成 WBPaymentRespDTO
            WBPaymentRespDTO respDTO = new WBPaymentRespDTO();
            respDTO.setCode(response.getCode());
            respDTO.setDesc(response.getDesc());
            respDTO.setTxnId(response.getTxnId());
            respDTO.setErrCodes(response.getErrCodes());
            respDTO.setMerchantId(response.getMerchantId());
            respDTO.setNbsOrderNo(response.getNbsOrderNo());
            respDTO.setMerOrderNo(response.getMerOrderNo());

            List<WBLoanDTO> loanList = response.getLoanList();
            if (loanList != null && loanList.size() > 0) {
                List<WBPaymentLoanReceiptDTO> loanReceiptNbrList = Lists.newArrayList();
                respDTO.setLoanReceiptNbrList(loanReceiptNbrList);

                for (WBLoanDTO loanInfo : loanList) {
                    WBPaymentLoanReceiptDTO loanReceipt = new WBPaymentLoanReceiptDTO();
                    loanReceipt.setLoanReceiptNbr(loanInfo.getLoanReceiptNbr());
                    loanReceipt.setLoanReceiptType(loanInfo.getLoanType());
                    loanReceiptNbrList.add(loanReceipt);
                }
            }

            return loanSuccess(assetMainInfo, assetBankTran, respDTO);
        } else {
        	return new InvokeResult<>(new BaseMsgDTO(CommonConstant.PROCESSING, "进行中"));
        }
    }
    

    /**
     * 根据放款查询结果判断是否放款成功
     *
     * @param response
     * @return
     */
    private boolean isLoanSuccess(WBLoanResultRespDTO response) {
        if (response != null && CollectionUtils.isNotEmpty(response.getLoanList())) {
            for (WBLoanDTO wbLoanDTO : response.getLoanList()) {
                if ("S".equals(wbLoanDTO.getLoanStatus())) { //放款成功
                    return true;
                }
            }
        }

        return false;
    }


    private void assembleGps(WBPaymentReqDTO reqDTO, DscSalesApplyCar car) {
        reqDTO.setIsGpsActive(car.getIsGpsActive()); //gps是否激活
        reqDTO.setGpsActiveTime(car.getGpsActiveTime()); //gps激活时间
        reqDTO.setGpsCode(car.getGpsCode1() + "_" + car.getGpsCode2() + "_" + car.getGpsCode3()); //gps设备码
        reqDTO.setLongitude("0"); //经度
        reqDTO.setLatitude("0"); //纬度
    }

    private void assembleFee(WBPaymentReqDTO reqDTO, DscSalesApplyMain main, DscSalesApplyCar car, DscSalesApplyCost cost) {
        reqDTO.setLoanInitPrin(cost.getFrze()); //借款金融
        reqDTO.setDownPayment(cost.getFsfje()); //首付总金额
        reqDTO.setDownPaymentRatio(dscWbCommonService.convertBigDecimal(cost.getFsfbl(), CommonConstant.TO_POIOT_TWO)); //首付比例
        reqDTO.setLoanTerm(Integer.parseInt(main.getArzqx())); //贷款期数
        reqDTO.setInterestRate(dscWbCommonService.convertBigDecimal(cost.getFkhll(), CommonConstant.TO_POIOT_FOUR)); //年化利率
        reqDTO.setInterestRateType("01"); //利息收取方式 默认01
        if ("02".equals(reqDTO.getInterestRateType())) {
            reqDTO.setUnearnedInterest(null); //预收利息, 当INTEREST_RATE_TYPE=02 时必填
        }
        reqDTO.setTxnCity(main.getAcsName()); //交易城市
        if (WBCarTypeEnum.NEW_CAR.getValue().equals(car.getAcllx())) {
            //新车alix没有过户日期，但是银行需要，银行只需此字段有值
            reqDTO.setCarPassDate(new Date());
        } else {
            reqDTO.setCarPassDate(car.getCarPassDate()); //车辆过户完成日期
        }

        List<DscSalesApplyFinancing> financingList = DscSalesApplyFinancing.getByMainId(main.getId());
        if (CollectionUtils.isEmpty(financingList)) {
            logger.error("根据申请主表id查找融资项失败,融资项为空,applyNo={},mainId={}", main.getApplyNo(), main.getId());
            throw new BzException("融资信息为空");
        }
        List<WBPaymentFeeDTO> feeDTOList = new ArrayList<>();
        List<SysDict> dictList = SysDict.getFinanceCode(CommonConstant.BankName.WB_BANK, CommonConstant.FINANCE_CODE);
        if (CollectionUtils.isNotEmpty(dictList)) {
            BigDecimal insAmount = BigDecimal.ZERO;
            BigDecimal gpsAmount = BigDecimal.ZERO;//Gps费用
            for (DscSalesApplyFinancing financing : financingList) {
                //过滤融资额小于等于0的融资项
                if (financing.getFkhrzje().compareTo(BigDecimal.ZERO) > 0) {
                    for (SysDict dict : dictList) {
                        if (financing.getArzxmid().equals(dict.getDicCode())) {
                            if (CommonConstant.FinanceType.F010.equals(dict.getDicCode())) {
                                WBPaymentFeeDTO feeDTO = new WBPaymentFeeDTO();
                                feeDTO.setFeeType(dict.getBankCode());
                                feeDTO.setFeeAmt(financing.getFkhrzje().subtract(reqDTO.getDownPayment())); //alix传过来的车款不是融资金额，而是车的总价
                                feeDTOList.add(feeDTO);
                            } else if (CommonConstant.FinanceType.F091.equals(financing.getArzxmid()) || CommonConstant.FinanceType.F101.equals((financing.getArzxmid()))
                                    || CommonConstant.FinanceType.F121.equals(financing.getArzxmid())) {
                                insAmount = insAmount.add(financing.getFkhrzje());
                            } else if (CommonConstant.FinanceType.F011.equals(dict.getDicCode())) {
                                //账户管理费
                                WBPaymentFeeDTO feeDTO = new WBPaymentFeeDTO();
                                feeDTO.setFeeType(dict.getBankCode());
                                feeDTO.setFeeAmt(financing.getFkhrzje());
                                feeDTOList.add(feeDTO);
                            } else if (CommonConstant.FinanceType.F060.equals(financing.getArzxmid()) || CommonConstant.FinanceType.F014.equals((financing.getArzxmid()))
                                    || CommonConstant.FinanceType.F015.equals(financing.getArzxmid()) || CommonConstant.FinanceType.F016.equals(financing.getArzxmid())
                                    || CommonConstant.FinanceType.F017.equals(financing.getArzxmid()) || CommonConstant.FinanceType.F119.equals(financing.getArzxmid())){
                                /**
                                 * F060 GPS费用
                                 * +
                                 * F014：责信保正常类型
                                 * F015：责信保二档类型
                                 * F016：责信保一档类型
                                 * F017：责信保三档类型
                                 * +
                                 * F119：盗抢险
                                 */
                                gpsAmount = gpsAmount.add(financing.getFkhrzje());
                            }else {
                                WBPaymentFeeDTO feeDTO = new WBPaymentFeeDTO();
                                feeDTO.setFeeType(dict.getBankCode());
                                feeDTO.setFeeAmt(financing.getFkhrzje());
                                feeDTOList.add(feeDTO);
                            }
                            break;
                        }
                    }
                }
            }
            if (insAmount.compareTo(BigDecimal.ZERO) > 0) {
                WBPaymentFeeDTO feeDTO = new WBPaymentFeeDTO();
                feeDTO.setFeeType(CommonConstant.FinanceType.INS);
                feeDTO.setFeeAmt(insAmount);
                feeDTOList.add(feeDTO);
            }
            if(gpsAmount.compareTo(BigDecimal.ZERO) > 0){
                WBPaymentFeeDTO feeDTO = new WBPaymentFeeDTO();
                feeDTO.setFeeType(CommonConstant.FinanceType.GPS);
                feeDTO.setFeeAmt(gpsAmount);
                feeDTOList.add(feeDTO);
            }
        } else {
            logger.info("申请编号:{},获取alix与银行的融资项对应关系失败！！！", main.getApplyNo());
        }

        reqDTO.setFee(feeDTOList);
    }

    private void assembleCar(WBPaymentReqDTO reqDTO, DscSalesApplyCar car) {
        reqDTO.setCarId(dscWbCommonService.getSpiltCarId(car.getCarId())); //车辆id
        reqDTO.setVehicleId(car.getAcjh()); //车架号
        //reqDTO.setDealerId(""); //车商id
        //reqDTO.setEngineCode(car.getAfdjh()); //发动机号
        reqDTO.setIsNewCar(car.getIsNewCar());
        if (WBCarTypeEnum.USED_CAR.getValue().equals(car.getAcllx())) {
            reqDTO.setPlateNumber(car.getAcarnojc()); // 车牌号 二手车必填
        }
    }


}
