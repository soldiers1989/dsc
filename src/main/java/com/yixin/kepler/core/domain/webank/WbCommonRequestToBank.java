package com.yixin.kepler.core.domain.webank;

import com.alibaba.fastjson.JSON;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.dsc.common.exception.BzException;
import com.yixin.dsc.entity.order.DscSalesApplyCust;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.dsc.service.common.DscWbCommonService;
import com.yixin.dsc.util.PropertiesManager;
import com.yixin.kepler.common.RestTemplateUtil;
import com.yixin.kepler.common.UrlConstant;
import com.yixin.kepler.common.enums.AssetStateEnum;
import com.yixin.kepler.common.enums.WBTransCodeEnum;
import com.yixin.kepler.dto.webank.WBComputerReqDTO;
import com.yixin.kepler.dto.webank.WBLoanResultReqDTO;
import com.yixin.kepler.dto.webank.WBLoanResultRespDTO;
import com.yixin.kepler.enity.AssetMainInfo;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.Date;
import java.util.Map;

/**
 * 微众银行通用请求接口
 *
 * @author YixinCapital -- chenjiacheng
 *         2018年07月06日 11:00
 **/
@Service
public class WbCommonRequestToBank {

    private final Logger logger = LoggerFactory.getLogger(WbCommonRequestToBank.class);


    @Resource
    private PropertiesManager propertiesManager;

    @Resource
    private DscWbCommonService dscWbCommonService;

    /**
     * 调用银行接口获取借据试算结果
     *
     * @param paramMap 入参
     * @return 试算结果
     * @author YixinCapital -- chenjiacheng
     * 2018/7/6 13:54
     */
    public String computerRequest(Map<String, Object> paramMap) {
        String applyNo = paramMap.get("applyNo").toString();
        WBComputerReqDTO reqDTO = JSON.parseObject(JSON.toJSONString(paramMap), WBComputerReqDTO.class);
        checkParams(reqDTO);
        AssetMainInfo mainInfo = AssetMainInfo.getByApplyNo(applyNo);
        logger.info("请求银行借据试算接口开始,本地参数params={},给银行传参:{}", JSON.toJSONString(reqDTO), JsonObjectUtils.objectToJson(reqDTO));
        String osbUrl = propertiesManager.getOsbEnvironment() + UrlConstant.OsbSystemUrl.weBankInterface + WBTransCodeEnum.WB_COMPUTER.getTransCode();
        logger.info("请求的osb试算接口:{}", osbUrl);
        String result = RestTemplateUtil.sendRequest(osbUrl, reqDTO, mainInfo.getFinancialCode());
        logger.info("请求银行借据试算接口结束,result={}", result);
        return result;
    }

    /**
     * 微众银行放款结果查询
     *
     * @param applyNo
     * @return
     * @author YixinCapital -- wangwenlong
     * 2018年7月30日 下午1:18:20
     */
    public WBLoanResultRespDTO loanStatusQuery(String applyNo) {
        DscSalesApplyMain main = DscSalesApplyMain.getByApplyNo(applyNo);
        if (main == null) {
            throw new BzException("未查询到订单信息");
        }
        DscSalesApplyCust cust = DscSalesApplyCust.getApplyCostByMianId(main.getId());
        if (cust == null) {
            throw new BzException("客户信息不存在");
        }
        AssetMainInfo assetMainInfo = AssetMainInfo.getByApplyNo(applyNo);
        if (assetMainInfo == null || StringUtils.isBlank(assetMainInfo.getBankOrderNo())) {
            throw new BzException("未查询到微众订单号");
        }


        String resp = queryLoanResult(applyNo, main, cust, assetMainInfo);

        String jsonResponse = dscWbCommonService.parseResponse(resp);

        WBLoanResultRespDTO response = null;
        try {
            response = (WBLoanResultRespDTO) JsonObjectUtils.jsonToObject(jsonResponse, new WBLoanResultRespDTO());
        } catch (Exception e) {
            logger.error("向银行发起放款结果查询操作，解析返回异常,申请编号:{}", applyNo, e);
        }
        return response;
    }


    private String queryLoanResult(String applyNo, DscSalesApplyMain main, DscSalesApplyCust cust, AssetMainInfo assetMainInfo) {
        WBLoanResultReqDTO reqDTO = new WBLoanResultReqDTO();
        //头部信息
        reqDTO.setTxnId(WBTransCodeEnum.WB_LOAN_RESULT.getTransCode()); //交易服务码
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
        checkParams(reqDTO);

        String payMentOsbUrl = propertiesManager.getOsbEnvironment() + UrlConstant.OsbSystemUrl.weBankInterface + WBTransCodeEnum.WB_LOAN_RESULT.getTransCode();
        String paramJson = JsonObjectUtils.objectToJson(reqDTO);
        logger.info("向银行发起放款结果查询操作开始,申请编号:{},url:{},给银行传参={}", applyNo, payMentOsbUrl, paramJson);
        String resp = RestTemplateUtil.sendRequest(payMentOsbUrl, reqDTO, assetMainInfo.getFinancialCode());
        logger.info("向银行发起放款结果查询操作结束,申请编号:{},result={}", applyNo, resp);

        return resp;
    }


    /**
     * 发起银行请求前，对必填参数进行校验
     *
     * @param obj 入参
     * @author YixinCapital -- chenjiacheng
     * 2018/7/16 14:09
     */
    private void checkParams(Object obj) {
        if (obj instanceof WBComputerReqDTO) {
            WBComputerReqDTO computerReq = (WBComputerReqDTO) obj;
            //试算接口字段校验
            if (StringUtils.isBlank(computerReq.getTxnId())) {
                throw new BzException("交易服务码为空");
            }
            if (StringUtils.isBlank(computerReq.getMerchantId())) {
                throw new BzException("平台ID为空");
            }
            if (StringUtils.isBlank(computerReq.getOpId())) {
                throw new BzException("操作员号为空");
            }
            if (StringUtils.isBlank(computerReq.getChannel())) {
                throw new BzException("渠道为空");
            }
            if (computerReq.getReqTime() == null) {
                throw new BzException("请求时间为空");
            }
            if (StringUtils.isBlank(computerReq.getMerOrderNo())) {
                throw new BzException("平台订单号为空");
            }
            if (StringUtils.isBlank(computerReq.getNbsOrderNo())) {
                throw new BzException("微众订单号为空");
            }
            if (StringUtils.isBlank(computerReq.getPsCode())) {
                throw new BzException("产品结构编号为空");
            }
            if (StringUtils.isBlank(computerReq.getName())) {
                throw new BzException("姓名为空");
            }
            if (StringUtils.isBlank(computerReq.getIdType())) {
                throw new BzException("证件类型为空");
            }
            if (StringUtils.isBlank(computerReq.getIdNo())) {
                throw new BzException("证件号码为空");
            }
        }

	}

}
