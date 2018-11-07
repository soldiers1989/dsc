package com.yixin.kepler.core.mqmessage;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yixin.dsc.common.exception.BzException;
import com.yixin.dsc.dto.DscElecCreditEvidenceDto;
import com.yixin.dsc.dto.DscElecSignDto;
import com.yixin.dsc.service.common.DscKeplerCommonService;
import com.yixin.kepler.core.domain.webank.WBEleContractRequest;
import com.yixin.kepler.enity.MQMsg;

/**
 * 电子征信存档MQ
 * Package : com.yixin.kepler.core.mqmessage
 *
 * @author YixinCapital -- wanghonglin
 * 2018/7/10 14:41
 */
@Service
public class WBEleContractMsgHandler extends AbstractMqMsgHandlerStrategy {
    private Logger logger = LoggerFactory.getLogger(WBEleContractMsgHandler.class);

    @Value("${spring.rabitmq.electricsign.queue}")
    private String queue;
    @Autowired
    private DscKeplerCommonService dscKeplerCommonService;
    @Autowired
    private WBEleContractRequest wbEleContractRequest;


    @Transactional
    @Override
    protected void process(MQMsg msg) throws IllegalArgumentException {
        logger.info("[电子征信存档及电子合同存证mq消息] 电子征信存档及电子合同存证处理 params:{}", JSON.toJSONString(msg));
        try {
            net.sf.json.JSONObject msgresult = net.sf.json.JSONObject.fromObject(msg.getMsg());
            String type = msgresult.getString("type");
            if(StringUtils.equals(type,"0")){//征信存档
                logger.info("[电子征信存档mq消息] 电子征信存档处理 params:{}", JSON.toJSONString(msg));
                DscElecCreditEvidenceDto elecCreditDto = JSONObject.parseObject(msg.getMsg(),DscElecCreditEvidenceDto.class);
                Boolean flag = dscKeplerCommonService.saveElecCreditInfo(elecCreditDto);
                logger.info("微众电子征信存档处理{}",flag);
            }else if(StringUtils.equals(type,"1")){//电子签约存正
                logger.info("[电子合同存证mq消息] 电子合同存证处理 params:{}", JSON.toJSONString(msg));
                DscElecSignDto signDto = JSONObject.parseObject(msg.getMsg(),DscElecSignDto.class);
                dscKeplerCommonService.saveEleContractInfo(signDto);
            }

        } catch (BzException e) {
            logger.error("电子征信存档及电子合同存证 失败，业务流水号ID:{},错误：{}",msg.getBzId(), e.getMessage());
            throw e; //向上跑出BzException
        } catch (Exception e) {
            logger.error("电子征信存档及电子合同存证  异常, 业务流水号ID:{},",msg.getBzId(), e);
        }
        logger.info("[处理mq消息] 电子征信存档及电子合同存证处理成功");
    }

    @Override
    protected MqMsgDealResult dealWorkflow(MQMsg msg) {
        return null;
    }

    @Override
    public String getQueue() {
        return queue;
    }
}
