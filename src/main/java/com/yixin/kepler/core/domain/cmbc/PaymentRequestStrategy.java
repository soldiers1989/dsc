package com.yixin.kepler.core.domain.cmbc;


import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yixin.common.exception.BzException;
import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.common.utils.DateUitls;
import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.entity.order.DscSalesApplyCar;
import com.yixin.dsc.entity.order.DscSalesApplyCost;
import com.yixin.dsc.entity.order.DscSalesApplyCust;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.dsc.entity.order.DscSalesApplyPayee;
import com.yixin.dsc.util.DateUtil;
import com.yixin.kepler.common.RestTemplateUtil;
import com.yixin.kepler.common.enums.AssetPhaseEnum;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.common.enums.CMBCTransCodeEnum;
import com.yixin.kepler.core.base.AbstractBaseDealDomain;
import com.yixin.kepler.core.config.CMBCConfig;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.core.domain.SysDIcMapped;
import com.yixin.kepler.core.domain.cmbc.util.CMBCUtil;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.dto.cmbc.CMBCPaymentDTO;
import com.yixin.kepler.dto.cmbc.CMBCPaymentReserveDTO;
import com.yixin.kepler.dto.cmbc.CMBCReceiveMsgDTO;
import com.yixin.kepler.dto.cmbc.CMBCRequestDTO;
import com.yixin.kepler.enity.AssetBankTran;
import com.yixin.kepler.enity.AssetMainInfo;
import com.yixin.kepler.enity.AssetResultTask;

import net.sf.json.JSONObject;


/**
 * 请款策略类,对应民生银行 贷款发放
 * @author sukang
 * @date 2018-06-11 15:57:35
 */
@Service("CMBCPaymentRequestStrategy")
public class PaymentRequestStrategy extends AbstractBaseDealDomain<Object, BaseMsgDTO> {
	
	
	
	private final  Logger logger = LoggerFactory.getLogger(getClass());
    /**
     * cmbc相关配置类
     */
    @Autowired
    private CMBCConfig cmbcConfig;
    
    @Resource 
    private SysDIcMapped sysDIcMapped;
    
    
    private ThreadLocal<AssetMainInfo> paymentThreadLocal = new ThreadLocal<>();
    
    
    @Override
    protected void getData() throws BzException {
    	
    	//申请编号
    	String applyNo = String.valueOf(inputDto.get());
    	logger.info("申请编号为{}的开始组装银行请款参数",applyNo);

		AssetMainInfo assetMainInfo = AssetMainInfo.getByApplyNo(applyNo);

		CMBCRequestDTO cmbcInstance = CMBCRequestDTO.getCMBCInstance(
				CMBCTransCodeEnum.PAYMENT);
		cmbcInstance.setMerchantNum(cmbcConfig.getMerchantNum());
	    cmbcInstance.setSystemCode(cmbcConfig.getSystemCode());
	    cmbcInstance.setTransType("000010");
	    cmbcInstance.setReqSeq(CMBCUtil.createReqSeq());
		cmbcInstance.setBody(getCmbcPaymentDTO(assetMainInfo));

		paymentThreadLocal.set(assetMainInfo);
    	inputDto.set(cmbcInstance);
    	
    }

   

	@Override
    protected void assembler() throws BzException {

    }

    @Override
    protected InvokeResult<BaseMsgDTO> message() throws BzException {
    	logger.info("开始执行");
		CMBCRequestDTO reqData = (CMBCRequestDTO) inputDto.get();
		AssetMainInfo assetMainInfo = paymentThreadLocal.get();
		paymentThreadLocal.remove();
		inputDto.remove();
		sysDIcMapped.removeFinancing();

		
		AssetBankTran assetBankTran = new AssetBankTran();
		//添加银行交易信息
		assetBankTran.setReqBody(JSONObject.fromObject(reqData).toString());
		assetBankTran.setApplyNo(assetMainInfo.getApplyNo());
		assetBankTran.setAssetId(assetMainInfo.getId());

		//资产编号对应银行交易流水
		assetBankTran.setAssetNo(assetMainInfo.getAssetNo());
		assetBankTran.setReqUrl(cmbcConfig.getOsbReqUrl());
		assetBankTran.setPhase(BankPhaseEnum.PAYMENT.getPhase());
		CMBCPaymentDTO paymentDTO = (CMBCPaymentDTO) reqData.getBody();
		assetBankTran.setTranNo(
				JSONObject.fromObject(paymentDTO.getReceiveMg()).getString("chnlTxNo")
				);
		assetBankTran.setSender(1);
		assetBankTran.create();

		//请求银行
		logger.info("\n单号{}发送请款请求的参数为{}",assetMainInfo.getApplyNo(),
				assetBankTran.getReqBody());
		String result = RestTemplateUtil.sendRequestV2(cmbcConfig.getOsbReqUrl(),
				reqData);
		
		assetBankTran.setRepBody(result);
		assetMainInfo.setAssetPhase(AssetPhaseEnum.PAYMENT_TRIAL.getPhase());
		logger.info("\n请款请求的返回参数为{}",result);
		
		//银行请款申请成功创建请求结果查询任务
		
		JSONObject body = null;
        assetBankTran.setRepBody(result);
        if (result != null && (body = JSONObject.fromObject(result))
        		.containsKey("body")) {
        	JSONObject returnMsg = JSONObject.fromObject(body.getString("body"))
        			.getJSONObject("returnMg");
        	
        	if ("S".equals(returnMsg.getString("rtnSts"))) {
        		assetMainInfo.setPaymentState(3);
        		assetMainInfo.update();
        		
        		assetBankTran.setApprovalDesc("S");
        		assetBankTran.setApprovalCode(returnMsg.getString("rtnCode"));
            	assetBankTran.update();
        		
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
    			return new InvokeResult<>(new BaseMsgDTO(CommonConstant.SUCCESS,"success"));
        	}else {
        		assetBankTran.setApprovalDesc(returnMsg.getString("rtnMsg"));
        		assetBankTran.update();
        		assetMainInfo.setPaymentState(2);
        		assetMainInfo.update();
        		return new InvokeResult<>(new BaseMsgDTO(CommonConstant.FAILURE,
        				assetBankTran.getApprovalDesc()));
			}
        }
        assetBankTran.update();
		return new InvokeResult<>(new BaseMsgDTO(CommonConstant.PROCESSING,"进行中"));
    }
    
    
    private CMBCPaymentDTO getCmbcPaymentDTO(AssetMainInfo assetMainInfo) {
    	
    	DscSalesApplyMain salesApplyMain = DscSalesApplyMain.getByApplyNo(assetMainInfo.getApplyNo());
         
    	DscSalesApplyCust dscSalesApplyCust = DscSalesApplyCust.getApplyCostByMianId(
                salesApplyMain.getId());
    	DscSalesApplyCost salesApplyCost = DscSalesApplyCost.getByMainId(salesApplyMain.getId());
    	
    	DscSalesApplyCar salesApplyCar = DscSalesApplyCar.getBymainId(salesApplyMain.getId());
    	
    	//=========== add by wangwenlong on 2018-10-29 =====================
    	if(StringUtils.isBlank(salesApplyMain.getCapitalId())){
    		throw new BzException("【民生银行请款】订单业务主表有效资方ID为空");
    	}
    	if(StringUtils.isBlank(assetMainInfo.getFinancialId())){
    		throw new BzException("【民生银行请款】银行交易主表有效资方ID为空");
    	}
    	if(!salesApplyMain.getCapitalId().equals(assetMainInfo.getFinancialId())){
    		throw new BzException("【民生银行请款】订单业务主表有效资方ID和银行交易主表有效资方ID不匹配");
    	}
    	CMBCPaymentDTO cmbcPaymentDTO = new CMBCPaymentDTO();
    	cmbcPaymentDTO.setLoanNo(assetMainInfo.getCmbcLoanNo());
    	cmbcPaymentDTO.setApplyNo(assetMainInfo.getCmbcApplyNo());
    	cmbcPaymentDTO.setEacNo("");
    	
    	cmbcPaymentDTO.setIdType(
    			sysDIcMapped.getCmbcMappingValue("azjlx",
    	        		dscSalesApplyCust.getAzjlx()));
    	
    	cmbcPaymentDTO.setCustName(dscSalesApplyCust.getAkhxm());
    	cmbcPaymentDTO.setIdNo(dscSalesApplyCust.getAzjhm());
    	//01~24
    	int dayOfMonth = LocalDate.now().getDayOfMonth();
    	cmbcPaymentDTO.setAtrsDueDay( dayOfMonth >= 24 ?
    			"24" :String.format("%02d",dayOfMonth));
    	cmbcPaymentDTO.setReceiveMg(getReceiveMg());
    	cmbcPaymentDTO.setReserve1(getReserve(salesApplyMain,salesApplyCost,salesApplyCar));
		return cmbcPaymentDTO;
	}
    

	private String getReserve(DscSalesApplyMain salesApplyMain,
			DscSalesApplyCost salesApplyCost, DscSalesApplyCar salesApplyCar) {
		
		DscSalesApplyPayee dscSalesApplyPayee = DscSalesApplyPayee.getByMainId(salesApplyMain.getId());
		CMBCPaymentReserveDTO paymentReserveDTO = new CMBCPaymentReserveDTO();
		paymentReserveDTO.setAsqbh(salesApplyMain.getApplyNo());
		paymentReserveDTO.setTransTime(DateUitls.dateToStr(new Date(), "HHmmss"));
		paymentReserveDTO.setLoanTime(DateUitls.dateToStr(new Date(), "yyyyMMddHHmmss"));
		
		//融资总额
		paymentReserveDTO.setFizze(String.valueOf(salesApplyCost.getFrze()));
		//车款融资额
		paymentReserveDTO.setFckrze(getFckrze(salesApplyMain,salesApplyCost.getFsfje()));
		
		//购置税融资额
		paymentReserveDTO.setTaxFckrze(sysDIcMapped.getDscApplyFinancing("F050",
				salesApplyMain.getId()));
		//保险融资额
		paymentReserveDTO.setInsuFckrze(getFbxrze(salesApplyMain.getId()));
		//精品加装
		paymentReserveDTO.setDecoAmt(getDecoAmt(salesApplyMain));
		//gps基础价
		paymentReserveDTO.setGpsBasicAmt(getGpsBasePrice(salesApplyMain));
		//gps加价
		paymentReserveDTO.setGpsAddAmt(getGpsAddPrice(salesApplyMain));
		
		//服务费
		paymentReserveDTO.setFee(sysDIcMapped.getDscApplyFinancing("F040",
				salesApplyMain.getId()));
		//经销商收款额
		paymentReserveDTO.setDealerAmt(
				String.valueOf(salesApplyMain.getDealerCollectAmount())
				);
		
		if ("1".equals(salesApplyCar.getAcllx())){
			paymentReserveDTO.setEngiNo(salesApplyCar.getAfdjh());
		}
		
		paymentReserveDTO.setLoanAcctNo(dscSalesApplyPayee.getAskfzh());
		paymentReserveDTO.setIsLoan("Y");
		return com.alibaba.fastjson.JSONObject.toJSONString(paymentReserveDTO);
	}
	
	
	/**
	 * 保险融资额 计算
	 * @param id
	 * @return
	 */
	private String getFbxrze(String id) {
		
		BigDecimal result = BigDecimal.ZERO;
		
		ArrayList<String> arrayList = new ArrayList<String>(20){{
			add("F091");add("F092");add("F093");add("F094");add("F095");
			add("F101");add("F102");add("F103");add("F104");add("F105");
			add("F111");add("F112");add("F113");add("F114");add("F115");
			add("F121");add("F122");add("F123");add("F124");add("F125");
		}};
		
		for (String code : arrayList) {
			result = result.add(
					new BigDecimal(sysDIcMapped.getDscApplyFinancing(code, id)));
		}
		return String.valueOf(result);
	}



	private String getGpsAddPrice(DscSalesApplyMain salesApplyMain) {
		
		LastTrialRequestStrategy bean = SpringContextUtil.getBean(
				"CMBCLastTrialRequestStrategy",LastTrialRequestStrategy.class);
		return bean.getGpsAddPrice(salesApplyMain);
	}


	private String getGpsBasePrice(DscSalesApplyMain salesApplyMain) {
		
		LastTrialRequestStrategy bean = SpringContextUtil.getBean(
				"CMBCLastTrialRequestStrategy",LastTrialRequestStrategy.class);
		return bean.getGpsBasePrice(salesApplyMain);
	}
	
	
	
	private String getDecoAmt(DscSalesApplyMain salesApplyMain) {
		
		String f012 = sysDIcMapped.getDscApplyFinancing("F012", salesApplyMain.getId());
		String f099 = sysDIcMapped.getDscApplyFinancing("F099", salesApplyMain.getId());
		
		return String.valueOf(new BigDecimal(f012).add(new BigDecimal(f099)));
	}



	private CMBCReceiveMsgDTO getReceiveMg() {
		CMBCReceiveMsgDTO cmbcReceiveMsgDTO = new CMBCReceiveMsgDTO();
		cmbcReceiveMsgDTO.setTxDt(DateUitls.dateToStr(new Date(),"yyyyMMdd"));
		cmbcReceiveMsgDTO.setTxTm(DateUtil.dateToString(new Date(), "HHmmss"));
		cmbcReceiveMsgDTO.setChnlTxNo(CMBCUtil.getTradeNo());
		return cmbcReceiveMsgDTO;
	}
	
	
	/**
	 * @param salesApplyCost 
	 * 获取首付金额（车款*首付比例）
	 */
	private String getDpayAm(DscSalesApplyMain salesApplyMain,
			DscSalesApplyCost salesApplyCost) {
		
		String f010 = sysDIcMapped.getDscApplyFinancing("F010",
				salesApplyMain.getId());
		BigDecimal fsfbl = salesApplyCost.getFsfbl();
		
		return String.valueOf(new BigDecimal(f010)
				.multiply(fsfbl)
				.divide(new BigDecimal(100),2,BigDecimal.ROUND_HALF_UP)) ;
	}
	
	
	/**
	 * 
	 * @param salesApplyMain
	 * @param sfje
	 * @return
	 */
	private String getFckrze(DscSalesApplyMain salesApplyMain,BigDecimal sfje) {
		String f010 = sysDIcMapped.getDscApplyFinancing("F010",
				salesApplyMain.getId());
		return String.valueOf(new BigDecimal(f010).subtract(sfje));
	}
}
