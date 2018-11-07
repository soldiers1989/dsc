package com.yixin.kepler.core.domain;/**
 * Created by liushuai2 on 2018/6/10.
 */

import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.yixin.dsc.enumpackage.RequestPreposeEnum;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.core.base.IDealDomain;

/**
 * Package : com.yixin.kepler.core.domain
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月10日 13:01
 */
@Service
public class BankReqDomainFactory {

    private Logger logger = LoggerFactory.getLogger(BankReqDomainFactory.class);

    private static final String SUFFIX = "RequestStrategy";
    
    private static final String RequestPrepose = "RequestPrepose";
    
    
    @Autowired
    Map<String, IDealDomain> dealDomainMap;
    
    /**
     * 根据银行的不同状态获取对应的处理策略
     * @param code
     * @param bankPhaseEnum
     * @return
     */
    public IDealDomain getDealDomain(String code, BankPhaseEnum bankPhaseEnum){
        logger.info("获取银行交易处理类code：{}，bankPhaseEnum：{}",code, bankPhaseEnum);
        String key = code + getCamalNameWithFirstUpper(bankPhaseEnum.name()) + SUFFIX;
        logger.info("需要获取的银行实体信息类key：{}", key);
        return dealDomainMap.get(key);
    }
    
    /**
     * 获取不同请求的前置实现类
     * @param code
     * @param preposeEnum
     * @return 
     * @author YixinCapital -- wangwenlong
     *	       2018年7月19日 下午12:59:05
     */
    public IDealDomain getRequestPreposeDomain(String code, RequestPreposeEnum preposeEnum){
        logger.info("获取银行交易处理前置类code：{}，bankPhaseEnum：{}",code, preposeEnum);
        String key = code + preposeEnum.getPrepose() + RequestPrepose;
        logger.info("需要获取的银行前置实体信息类key：{}", key);
        return dealDomainMap.get(key);
    }


    /**
     * 获取驼峰配置的类名
     * @param val
     * @return
     */
    public static String getCamalName(String val) {
        StringBuilder className = new StringBuilder();
        boolean isUpper = false;
        char[] tbChars = val.toLowerCase().toCharArray();
        for (int i = 0; i < tbChars.length; i++) {
            String clas = String.valueOf(tbChars[i]);
            if (clas.equals("_")) {
                isUpper = true;
            } else {
                className.append(isUpper ? clas.toUpperCase() : clas);
                isUpper = false;
            }
        }
        return className.toString();
    }

    public static String getCamalNameWithFirstUpper(String val){
        String camalName = getCamalName(val);
        String upper = camalName.substring(0,1).toUpperCase() + camalName.substring(1, camalName.length());
        return upper;
    }
}
