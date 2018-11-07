package com.yixin.dsc.v1.service.impl.common;

import java.util.List;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yixin.dsc.entity.order.DscFileAttachment;
import com.yixin.dsc.v1.service.common.VenusRescueJobService;

/**
 * Venus 补救定时任务实现
 * Package : com.yixin.dsc.v1.service.common
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年10月22日 下午3:41:08
 *
 */
@Service
public class VenusRescueJobServiceImpl implements VenusRescueJobService {

	private static final Logger LOGGER = LoggerFactory.getLogger(VenusRescueJobServiceImpl.class);
	
	/**
	 * 清理附件
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年10月22日 下午3:56:01
	 */
	@Override
	public Boolean clearAttachment() {
		LOGGER.info("================定时清理附件开始==========================");
		while(true){
			List<DscFileAttachment> attachmentList = DscFileAttachment.getlogicAttachmentList();
			LOGGER.info("================定时清理附件，清理附件条数:{}==========================",attachmentList==null?0:attachmentList.size());
			if(CollectionUtils.isEmpty(attachmentList)){
				break;
			}
			for(DscFileAttachment attach:attachmentList){
				attach.remove();
			}
		}
		LOGGER.info("================定时清理附件结束==========================");
		return true;
	}

}
