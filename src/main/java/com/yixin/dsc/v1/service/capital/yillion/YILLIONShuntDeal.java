package com.yixin.dsc.v1.service.capital.yillion;

import com.yixin.dsc.dto.DscCapitalDto;
import com.yixin.dsc.entity.order.DscSalesApplyCust;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.dsc.v1.service.capital.AfterShuntDeal;
import com.yixin.kepler.common.JacksonUtil;
import com.yixin.kepler.common.RestUtil;
import com.yixin.kepler.enity.AssetMainInfo;
import com.yixin.kepler.enity.AssetResultTask;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 亿联准入规则校验
 * Created with IDEA
 * author:xy
 * Date:2018/11/1
 * Time:18:31
 **/
@Component("yILLIONAfterShuntDeal")
public class YILLIONShuntDeal extends AfterShuntDeal {

    private static final Logger logger = LoggerFactory.getLogger(YILLIONShuntDeal.class);


    private String ylId="40281e8363e42a820163e424c5123333";

    @Override
    public DscCapitalDto deal(DscCapitalDto dscCapitalDto) {
        logger.info("亿联准入规则校验");
        //申请人一个贷款周期内仅可申请一笔车贷业务
        // 同一个申请人在易鑫是否有同银行未结清的订单


        //不再匹配该银行（取消或提回的订单） (根据身份证号)
        DscSalesApplyMain dscSalesApplyMain = threadLocalApplyMain.get();

        String azjhm = threadLocalApplyCust.get().getAzjhm();
        String akhxm = threadLocalApplyCust.get().getAkhxm();
        HashMap<String, Object> map = new HashMap<>();
        List<DscSalesApplyCust>  applyCusts= DscSalesApplyCust.findByProperties(DscSalesApplyCust.class, map);

        //TODO 40281e8363e42a820163e424c5123333 亿联id
        //TODO 准入暂时写成这样, 后续增加条件
        //判断是否有订单已进入过银行系统,并取消体会,如取消提回过, 则不允许进入系统
        List<AssetMainInfo> assetByIDNo = AssetMainInfo.getAssetByIDNo(azjhm, ylId);
        if (assetByIDNo != null && !assetByIDNo.isEmpty()){
            for (AssetMainInfo assetResultTask : assetByIDNo) {
                if(assetResultTask.getLastState()!=null && assetResultTask.getLastState()==1){
                    return isFalseStatus(dscCapitalDto,"该客户已在亿联存有订单,不允许准入亿联");
                }
            }
        }
        //调用贷后接口查询用户是否有未结清订单
        //TODO 贷后提供接口后  再具体实现
       /* String url = "union/faUionQueryRPCService/query";
        Map params =new HashMap<String ,Object>();
        params.put("certNum",azjhm);
        params.put("certName",akhxm);

        String paramsJson = JacksonUtil.fromObjectToJson(params);

        logger.info("亿联准入规则校验,查询客户是否存在未结清订单,入参:{}",params);
        String results = RestUtil.postJson(url, paramsJson);
        logger.info("亿联准入规则校验,查询客户是否存在未结清订单,出参:{}",params);
        //返回不为空则有未结清订单
        if (results != null && !"".equals(results) && !"[]".equals(results)) {
            List<Object> listFromJson = JacksonUtil.getListFromJson(results, Object.class);
            if(null!=listFromJson && !listFromJson.isEmpty()){
                return isFalseStatus(dscCapitalDto,"该客户还有未结清订单");
            }
        }*/

        dscCapitalDto.setPretrialResult(true);
        return dscCapitalDto;
    }
    /**
     *   不允许准入亿联dto
     *
     * @author xy
     * @date 2018/11/5 10:20
     * @return com.yixin.dsc.dto.DscCapitalDto
     */
    private DscCapitalDto isFalseStatus(DscCapitalDto dscCapitalDto,String pretrialMsg){
        dscCapitalDto.setPretrialResult(false);
        dscCapitalDto.setPretrialMsg(pretrialMsg);
        return dscCapitalDto;
    }

}
