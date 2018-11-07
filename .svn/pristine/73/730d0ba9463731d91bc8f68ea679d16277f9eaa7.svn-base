package com.yixin.kepler.core.domain.webank;

import com.yixin.common.exception.BzException;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.dsc.entity.order.DscSalesApplyCar;
import com.yixin.dsc.entity.order.DscSalesApplyCust;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.dsc.service.common.DscWbCommonService;
import com.yixin.dsc.util.PropertiesManager;
import com.yixin.kepler.common.RestTemplateUtil;
import com.yixin.kepler.common.UrlConstant;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.common.enums.WBTransCodeEnum;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.core.domain.SysDIcMapped;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.dto.webank.WBCommonRespDTO;
import com.yixin.kepler.dto.webank.WBLastRequestCancleDTO;
import com.yixin.kepler.dto.webank.WBOrderCancelDTO;
import com.yixin.kepler.enity.AssetBankTran;
import com.yixin.kepler.enity.AssetMainInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;


/**
 * 取消订单
 * Package : com.yixin.kepler.core.domain.webank
 *
 * @author YixinCapital -- wanghonglin
 * 2018/7/9 18:28
 */
@Service
public class WBOrderCancelRequest {
    private static final Logger logger = LoggerFactory.getLogger(WBOrderCancelRequest.class);
    @Resource
    private PropertiesManager propertiesManager;

    @Resource
    private DscWbCommonService dscWbCommonService;

    @Resource
    private DscWbCommonService commonService;

    @Resource
    private SysDIcMapped sysDIcMapped;

    /**
     * 请求银行取消订单
     * @param assetMainInfo
     * @param notifyType
     * @return
     * @throws BzException
     */
    public BaseMsgDTO sendResult(AssetMainInfo assetMainInfo, String notifyType) throws BzException {
        WBOrderCancelDTO dto = new WBOrderCancelDTO();

        DscSalesApplyMain mainInfo = DscSalesApplyMain.getByApplyNo(assetMainInfo.getApplyNo());
        if(mainInfo == null){
            throw new BzException("申请编号错误，查询不到订单信息");
        }
        DscSalesApplyCust applyCust = DscSalesApplyCust.getOneByMainId(mainInfo.getId());
        if(applyCust == null){
            throw new BzException("客户信息不存在");
        }
        DscSalesApplyCar salesApplyCar = DscSalesApplyCar.getBymainId(mainInfo.getId());
        if(salesApplyCar == null){
            throw new BzException("客户车辆信息不存在");
        }
        dto.setTxnId(WBTransCodeEnum.WB_ORDER_CANCEL.getTransCode()); //交易服务码
        dto.setMerchantId(commonService.getMerchantIdByCompanyCode(mainInfo.getRentingCompanyCode())); //平台id
        dto.setOpId(mainInfo.getCreatorId());//操作员号，请求方发起交易操作员标识号（如：柜员号，客服操作员号）
        dto.setChannel(dscWbCommonService.getWbChannelByOrderSource(mainInfo.getOrderSource()));//渠道
        dto.setReqTime(new Date()); //请求时间

        dto.setMerOrderNo(assetMainInfo.getApplyNo());//平台订单号
        dto.setNbsOrderNo(assetMainInfo.getBankOrderNo());//微众订单号
        dto.setPsCode(dscWbCommonService.getPsCodeByApplyNo(assetMainInfo.getApplyNo()));//产品结构编号
        dto.setName(applyCust.getAkhxm());//用户姓名
        dto.setIdType(codeConvert("azjlx", applyCust.getAzjlx()));//证件提交类型
        dto.setIdNo(applyCust.getAzjhm());//证件号码
        dto.setCatrId(dscWbCommonService.getSpiltCarId(salesApplyCar.getCarId())); //车辆id
        dto.setVehicleId(salesApplyCar.getAcjh());//机动车架号/VINacjh
        //dto.setDealerId("");//车商ID
        dto.setTxnCode(notifyType);//订单撤销TC001
        dto.setTxnDesc(CommonConstant.TC001_DSC);//交易描述(可与交易码的文字描述部分保持一致)
        dto.setTxnDate(new Date());//交易时间
        /**==========================请求银行存成银行交易信息============================*/
        String osbUrl = this.propertiesManager.getOsbEnvironment()+UrlConstant.WB_ORDERCANCEL_URL;
        //存储银行交易信息
        AssetBankTran assetBankTran = new AssetBankTran();
        assetBankTran.setApplyNo(assetMainInfo.getApplyNo());
        assetBankTran.setApiCode(dto.getTxnId()); //
        assetBankTran.setAssetId(assetMainInfo.getId());
        assetBankTran.setReqBody(JsonObjectUtils.objectToJson(dto));
//        assetBankTran.setAssetNo(chnlTxNo);
        assetBankTran.setPhase(BankPhaseEnum.WB_ORDER_CANCEL.getPhase());
        assetBankTran.setReqUrl(osbUrl);
        assetBankTran.setTranNo(WBTransCodeEnum.WB_ORDER_CANCEL.getTransCode());
        assetBankTran.setSender(CommonConstant.SenderType.YIXIN);
        assetBankTran.create();
        logger.info("微众订单撤销银行请求报文为:{}", assetBankTran.getReqBody());
        String jsonResult = RestTemplateUtil.sendRequest(osbUrl,dto,CommonConstant.BankName.WB_BANK);
        logger.info("微众订单撤销银行返回报文为:{}", jsonResult);
        assetBankTran.setRepBody(jsonResult);
        if(StringUtils.isBlank(jsonResult)){
            assetBankTran.setApprovalCode(""); //错误码
            assetBankTran.setApprovalDesc("银行未返回信息"); //错误描述
            assetBankTran.update();
            return new BaseMsgDTO(CommonConstant.FAILURE,"微众撤销订单失败");
        }
        String jsonResponse = commonService.parseResponse(jsonResult);
        WBCommonRespDTO response = (WBCommonRespDTO) JsonObjectUtils.jsonToObject(jsonResponse, new WBCommonRespDTO());
        assetBankTran.setApprovalCode(response.getCode()); //错误码
        assetBankTran.setApprovalDesc(response.getDesc()); //错误描述
        assetBankTran.update();
        if(response.getCode().contains(CommonConstant.WB_RESP_CODE)){
            return new BaseMsgDTO(CommonConstant.SUCCESS,response.getDesc());
        }
        return new BaseMsgDTO(CommonConstant.FAILURE,response.getDesc());
    }
    
    
    /**
     * 90032 复审平台审批结果回传(4)
     * @param assetMainId
     * @param mainId
     * @param wbAppNo
     * @return 
     * @author YixinCapital -- wangwenlong
     *	       2018年7月10日 下午10:30:38
     */
    public BaseMsgDTO lastRequestCancle(String assetMainId,String mainId,String wbAppNo) {
    	DscSalesApplyCust applyCust = DscSalesApplyCust.getOneByMainId(mainId);
        if(applyCust == null){
            throw new BzException("客户信息不存在");
        }
        DscSalesApplyMain main = DscSalesApplyMain.get(DscSalesApplyMain.class, mainId);

    	WBLastRequestCancleDTO cancleDto = new WBLastRequestCancleDTO();
    	cancleDto.setTxnId(WBTransCodeEnum.LAST_TRIAL.getTransCode());
    	cancleDto.setMerchantId(commonService.getMerchantIdByCompanyCode(main.getRentingCompanyCode()));
    	cancleDto.setOpId(main.getTlrNo());
    	cancleDto.setChannel(dscWbCommonService.getWbChannelByOrderSource(main.getOrderSource()));
    	cancleDto.setReqTime(new Date());
    	cancleDto.setAzjhm(applyCust.getAzjhm());//用户平台id/身份证号码
    	cancleDto.setAppNo(wbAppNo); //申请编号 / 申请书编号 二审回传关联一审异步回调的APP_NO
    	cancleDto.setAkhxm(applyCust.getAkhxm()); //姓名/申请人姓名
    	cancleDto.setAzjlx(codeConvert("azjlx", applyCust.getAzjlx())); //证件提交类型/证件类型
    	cancleDto.setAzjhm2(applyCust.getAzjhm());//证件号码/身份证号码

    	/**==========================请求银行存成银行交易信息============================*/
        String osbUrl = this.propertiesManager.getOsbEnvironment() + UrlConstant.OsbSystemUrl.weBankInterface + WBTransCodeEnum.LAST_TRIAL;
        //存储银行交易信息
        AssetBankTran assetBankTran = new AssetBankTran();
        assetBankTran.setAssetId(assetMainId);
        assetBankTran.setReqBody(JsonObjectUtils.objectToJson(cancleDto));
        assetBankTran.setAssetNo("90032 复审平台审批结果回传(4)");
        assetBankTran.setPhase(BankPhaseEnum.LAST_TRIAL.getPhase()); //二审阶段
        assetBankTran.setReqUrl(osbUrl);
        assetBankTran.setTranNo(WBTransCodeEnum.LAST_TRIAL.getTransCode());
        assetBankTran.setApiCode(WBTransCodeEnum.LAST_TRIAL.getTransCode() + "_4");
        assetBankTran.setSender(CommonConstant.SenderType.YIXIN);
        assetBankTran.create();
        logger.info("微众订单一审通过降额取消银行请求报文为:{}", assetBankTran.getReqBody());
        String jsonResult = RestTemplateUtil.sendRequest(osbUrl,cancleDto,CommonConstant.BankName.WB_BANK);
        logger.info("微众订单一审通过降额取消银行返回报文为:{}", jsonResult);
        assetBankTran.setRepBody(jsonResult);
        if(StringUtils.isBlank(jsonResult)){
            return new BaseMsgDTO(CommonConstant.FAILURE,"微众撤销订单失败");
        }
        net.sf.json.JSONObject results = net.sf.json.JSONObject.fromObject(jsonResult);
        WBCommonRespDTO response = com.alibaba.fastjson.JSONObject.parseObject(results.getString("jsonData"), WBCommonRespDTO.class);
        assetBankTran.setApprovalCode(response.getCode()); //错误码
        assetBankTran.setApprovalDesc(response.getDesc()); //错误描述
        assetBankTran.update();
        if(response.getCode().contains(CommonConstant.WB_RESP_CODE)){
            return new BaseMsgDTO(CommonConstant.SUCCESS,response.getDesc());
        }
        return new BaseMsgDTO(CommonConstant.FAILURE,response.getDesc());
    }

    private String codeConvert(String code,String value){
        return sysDIcMapped.getMappingValue(code,value, CommonConstant.BankName.WB_BANK);
    }
}
