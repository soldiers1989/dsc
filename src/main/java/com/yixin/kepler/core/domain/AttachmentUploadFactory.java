package com.yixin.kepler.core.domain;

import org.springframework.stereotype.Component;

import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.kepler.core.attachment.AttachmentDomain;
import com.yixin.kepler.dto.OsbAttachmentDTO;
import com.yixin.kepler.enity.AssetFinanceInfo;

@Component
public class AttachmentUploadFactory {
	
	private static final String SUFFIX = "UploadAttachment";
	
	/**
	 * 根据资方Code获取不同的文件处理实现类
	 * @return
	 */
	public AttachmentDomain getAttachmentDomain(OsbAttachmentDTO osbAttachmentDTO){
		//申请编号
		String applyNo = osbAttachmentDTO.getBzId();
		
		DscSalesApplyMain applyMain = DscSalesApplyMain.getByApplyNo(applyNo);
		AssetFinanceInfo financeInfo = AssetFinanceInfo.get(AssetFinanceInfo.class, applyMain.getCapitalId());
		String name = getBeanName(financeInfo.getCode());
		AttachmentDomain bean = SpringContextUtil.getApplicationContext()
					.getBean(name, AttachmentDomain.class);
		return bean;
	}

	private static String getBeanName(String financialCode) {
		
		StringBuilder sb = new StringBuilder();
		
		if (Character.isLowerCase(financialCode.charAt(0))) {
			sb.append(financialCode);
		}else {
			sb.append(Character.toLowerCase(financialCode.charAt(0)))
			  .append(financialCode.substring(1));
		}
		return sb.toString().concat(SUFFIX);
	}
}
