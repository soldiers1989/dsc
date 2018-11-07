package com.yixin.kepler.core.domain;


import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.enity.AssetAttachmentRule;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.dto.DscSupplyDto;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.dto.OsbAttachmentDTO;

/**
 * 公共工具类，获取bean方式以ioc为主
 * @author sukang
 */

public class CommonDomainUtil {

	private  final static Logger logger = LoggerFactory.getLogger(CommonDomainUtil.class);

    public static InvokeResult<DscSupplyDto> checkAttachmentFile(String applyNo,BankPhaseEnum phaseEnum){
        try {
            AsyncTask asyncTask = SpringContextUtil.getBean(AsyncTask.class);
            OsbAttachmentDTO osbAttachmentDTO = new OsbAttachmentDTO();
            osbAttachmentDTO.setBzId(applyNo);
            osbAttachmentDTO.setBzType(phaseEnum.getPhase());
            return asyncTask.checkAttachmentFile(osbAttachmentDTO);
        }catch (Exception e){
            logger.error("检查附件上传是否完善异常,订单编号:{}",applyNo, e);
            InvokeResult<DscSupplyDto> result = new InvokeResult<>();
            return result.failure(e.getMessage() == null 
            		? Exception.class.getSimpleName() : e.getMessage()); 
        }
    }

}
