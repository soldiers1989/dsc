package com.yixin.dsc.service.impl.settle;


import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.dsc.common.exception.BzException;
import com.yixin.dsc.dto.order.DscComputeDTO;
import com.yixin.dsc.dto.order.DscSalesApplyFinancingInfoDTO;
import com.yixin.dsc.entity.order.DscSalesApplyCar;
import com.yixin.dsc.entity.order.DscSalesApplyCost;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.dsc.service.impl.shunt.DscShuntImpl;
import com.yixin.dsc.service.settle.SettleService;
import com.yixin.dsc.util.PropertiesManager;
import com.yixin.kepler.common.RestUtil;
import com.yixin.kepler.common.UrlConstant;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.dto.settle.CalculationServiceFeeParam;
import com.yixin.kepler.enity.AssetBankTran;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * Package : com.yixin.dsc.service.impl.settle
 *
 * @author YixinCapital -- wanghonglin
 * 2018/10/25 13:07
 */
@Service("settleService")
public class SettleServiceImpl implements SettleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(SettleServiceImpl.class);

    /**
     * 调用结算计算服务费
     * @param sourceMap
     * @param applyNo
     * @return
     */
    @Override
    public DscComputeDTO computeServiceFee(Map<String, Object> sourceMap, String applyNo) {
        /**
         * 调用结算计算服务费
         */
        CalculationServiceFeeParam param = new CalculationServiceFeeParam();
        BigDecimal serviceFee = BigDecimal.ZERO;
        DscComputeDTO dto = new DscComputeDTO();
        try{
            DscSalesApplyFinancingInfoDTO financingInfoDTO  = (DscSalesApplyFinancingInfoDTO) sourceMap.get
                    (DscSalesApplyFinancingInfoDTO.class.getName());
            DscSalesApplyCar car  = (DscSalesApplyCar) sourceMap.get(DscSalesApplyCar.class.getName());
            DscSalesApplyMain main = (DscSalesApplyMain) sourceMap.get(DscSalesApplyMain.class.getName());
            DscSalesApplyCost cost = (DscSalesApplyCost) sourceMap.get(DscSalesApplyCost.class.getName());

            param.setTotalServiceFee(financingInfoDTO.getF013() == null ? "0":financingInfoDTO.getF013().toString());//总服务费
            param.setCarInterestRate(car.getAcllx() == "1" ? "0.065":"0.07");//车辆利率
            param.setFinancingMaturity(main.getArzqx());//融资期限arzqx
            param.setCustFinaceAmount(cost.getFrze().toString());//客户融资总金额
            param.setSettleInterestRate(cost.getFcsll().toString());//结算利率
            String paramStr = JSONObject.fromObject(param).toString();
            PropertiesManager propertiesManager = SpringContextUtil.getBean(PropertiesManager.class);
            String url = propertiesManager.getSettleWebEnvironment() + UrlConstant.SettleSystemUrl.computeServiceFee;
            //发送接口请求
            LOGGER.info("订单号{}请求结算计算服务费请求url为：{},推送数据:{}",applyNo,url,paramStr);
            String jsonResult = RestUtil.postJson(url,paramStr);
            LOGGER.info("订单号{}请求结算计算服务费响应报文：{}",applyNo, JSONObject.fromObject(jsonResult));
            //返回处理
            JSONObject restResult = JSONObject.fromObject(jsonResult);
            dto.setSettleComputeFee(new BigDecimal(restResult.getString("data")));
        }catch (BzException bz){
            LOGGER.error("请求结算计算服务费失败，订单编号:{}",applyNo,bz);
        }catch (Exception e){
            LOGGER.error("请求结算计算服务费失败，订单编号:{}",applyNo,e);
        }

        return dto;
    }
}
