package com.yixin.kepler.v1.service.capital.yntrust;

import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.core.attachment.AttachmentDomain;
import com.yixin.kepler.core.attachment.UploadAttachmentDomain;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.enity.AssetContractTask;
import com.yixin.kepler.enity.OsbFileLog;
import com.yixin.kepler.v1.common.core.compress.CompressHandle;
import com.yixin.kepler.v1.datapackage.dto.CompressHandleDTO;
import com.yixin.kepler.v1.job.YnContractJobScheduler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author sukang
 *
 */

@Component(value = "YNTRUSTCompressHandle")
public class YnCompressHandle implements CompressHandle{


    private final Logger logger = LoggerFactory.getLogger(getClass());



    @Override
    public BaseMsgDTO handle(CompressHandleDTO handleDTO) {

        logger.info("云信获取压缩后的文件结果为{}",
                JsonObjectUtils.objectToJson(handleDTO));

        OsbFileLog osbFileLog = OsbFileLog.get(OsbFileLog.class, handleDTO.getOsbFileLogId());

        String bzType = osbFileLog.getBzType();


        //说明是合同文件
        if (Objects.equals(BankPhaseEnum.PAYMENT_QUERY.getPhase(),bzType)){
            YnContractService bean = SpringContextUtil.getBean(
                    "YNTRUSTContractService", YnContractService.class);
            BaseMsgDTO baseMsgDTO = bean.compressFileAndUpload(osbFileLog);
            logger.info("压缩文件后续处理获取的结果为{}",baseMsgDTO);
        }else {
            YNTRUSTUploadAttachment attachment = SpringContextUtil.getBean(
                    "yNTRUSTUploadAttachment", YNTRUSTUploadAttachment.class);
            attachment.upLoadTask(handleDTO.getCompressTranNo());

        }
        return BaseMsgDTO.successData("执行成功");
    }
}
