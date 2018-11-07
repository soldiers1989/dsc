package com.yixin.kepler.core.listener;

import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.common.utils.InvokeResult;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.dsc.dto.order.DscFileAttachmentDTO;
import com.yixin.dsc.service.impl.flow.DscFlowImpl;
import com.yixin.dsc.v1.common.enumpackage.NoticeBusiTypeEnum;
import com.yixin.dsc.v1.datapackage.dto.notice.DscAsyncMessageDto;
import com.yixin.dsc.v1.datapackage.dto.notice.DscNoticeBusinessDto;
import com.yixin.kepler.common.FileUtils;
import com.yixin.kepler.enity.AssetContractTask;
import com.yixin.kepler.enity.AssetMainInfo;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author sukang  on 2018/9/29.
 */
public class ContractSignEvent extends AbstractEvent{


    private final Logger logger = LoggerFactory.getLogger(getClass());

    public ContractSignEvent(Object source) {
        super(source);
        this.beanClass = getClass();
    }

    @Override
    protected void execute(Object source) {
        String venusApplyNo = String.valueOf(source);
        logger.info("异步通知处理结果-合同签署,借款唯一标识为{}",venusApplyNo);

        AssetMainInfo assetMainInfo = AssetMainInfo.getByVenusApplyNo(venusApplyNo);

        List<AssetContractTask> contractTask = AssetContractTask
                .getContractTask(venusApplyNo);

        if (CollectionUtils.isEmpty(contractTask)){
            return;
        }

        List<DscFileAttachmentDTO> arrayList = new ArrayList<>();
        for (AssetContractTask task : contractTask) {
            DscFileAttachmentDTO fileAttachmentDTO = new DscFileAttachmentDTO();
            fileAttachmentDTO.setFileName(task.getFileName());
            fileAttachmentDTO.setAttachSubClass(task.getAttachSubClass());
            fileAttachmentDTO.setFileType(task.getFileType());
            fileAttachmentDTO.setFileId(
                    FileUtils.getFileStoreUrl(task.getSignFileId()));
            arrayList.add(fileAttachmentDTO);
        }

        DscAsyncMessageDto<DscNoticeBusinessDto> asyncMessageDto =
                new DscAsyncMessageDto<>();

        DscNoticeBusinessDto dscNoticeBusinessDto = new DscNoticeBusinessDto();
        dscNoticeBusinessDto.setFileAttachmentDTOList(arrayList);

        asyncMessageDto.setApplyNo(assetMainInfo.getApplyNo());
        asyncMessageDto.setMessageType(NoticeBusiTypeEnum.CONTRACT_SIGNATURE.getBusiType());
        asyncMessageDto.setMessageBody(dscNoticeBusinessDto);





        logger.info("订单号{}，借款唯一标识{},发送合同结果参数为{}",
                assetMainInfo.getApplyNo(),venusApplyNo,
                JsonObjectUtils.objectToJson(asyncMessageDto));

        DscFlowImpl dscFlowImpl = SpringContextUtil.getApplicationContext()
                .getBean(DscFlowImpl.class);

        InvokeResult<DscAsyncMessageDto> invokeResult = dscFlowImpl.flowResultNoticeForAlix(asyncMessageDto);

        logger.info("订单号{},借款唯一标识{},合同通知结果为{}",
                assetMainInfo.getApplyNo(),venusApplyNo,
                JsonObjectUtils.objectToJson(invokeResult));
    }









}
