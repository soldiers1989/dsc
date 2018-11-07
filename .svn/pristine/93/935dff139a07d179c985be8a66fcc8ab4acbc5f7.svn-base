package com.yixin.kepler.core.job;

import com.alibaba.fastjson.JSON;
import com.yixin.basemessage.dto.mail.MailFile;
import com.yixin.basemessage.dto.mail.YxMailMessage;
import com.yixin.common.system.job.JobExecutor;
import com.yixin.common.utils.JobParamDTO;
import com.yixin.dsc.dto.query.DscMainInfoDto;
import com.yixin.dsc.dto.query.DscPaymentErrorDto;
import com.yixin.dsc.service.common.DscQueryService;
import com.yixin.dsc.util.ExcelUtils;
import com.yixin.dsc.util.FileUploadUtils;
import com.yixin.dsc.util.MailMessageUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

/**
 * 定时扫描系统数据
 * @author YixinCapital -- chenjiacheng
 *         2018年08月23日 13:51
 **/
@Component
public class CollectDataJobScheduler implements JobExecutor {

	private static final Logger logger = LoggerFactory.getLogger(CollectDataJobScheduler.class);

	@Resource
	private DscQueryService dscQueryService;

	@Value("${spring.fileserver.http.upload.url}")
	private String fileHttpUpload;

	@Value("${spring.filestore.url}")
	private String fileDownUrl;


	@Override
	public JobParamDTO execute(JobParamDTO jobParamDTO) {
		logger.info("开始执行数据收集任务,参数:{}", JSON.toJSONString(jobParamDTO));
		JobParamDTO result = new JobParamDTO();
		try {
			String beginTime = LocalDateTime.now().minusDays(1).format(DateTimeFormatter.ofPattern("yyyy-MM-dd 08:00:00"));
			String endTime = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd 08:00:00"));
			if (jobParamDTO != null && StringUtils.isNotBlank(jobParamDTO.getAgrs())) {
				String args = jobParamDTO.getAgrs();
				beginTime = LocalDateTime.now().minusDays(Integer.parseInt(args)).format(DateTimeFormatter.ofPattern("yyyy-MM-dd 08:00:00"));
			}
			Integer nums = dscQueryService.countOrderNums(beginTime, endTime);
			List<DscMainInfoDto> dataList = dscQueryService.queryMainInfoList(beginTime, endTime);
			List<DscPaymentErrorDto> paymentErrorDtoList = dscQueryService.queryPaymentErrorList(beginTime, endTime);

			//生成excel
			byte[] array = ExcelUtils.createExcelForMainInfo(dataList,paymentErrorDtoList);
			//上传文件至服务器
			String fileId = FileUploadUtils.upload(array, "资管系统进件详情.xlsx", fileHttpUpload);
			//准备发送邮件
			YxMailMessage mailMessage = new YxMailMessage();
			mailMessage.setMailSubject("资管系统进件详情");
			List<MailFile> mailFileList = new ArrayList<>();
			MailFile mailFile0 = new MailFile();
			mailFile0.setFileUrl(fileDownUrl + fileId);
			mailFile0.setFileSourceName("资管系统进件详情.xlsx");
			mailFileList.add(mailFile0);
			mailMessage.setMailFiles(mailFileList);

			List<String> fileNameList = new ArrayList<>();
			fileNameList.add("附件-资管系统进件详情");
			mailMessage.setMailFileName(fileNameList);

			mailMessage.setMailText("从" + beginTime + "至" + endTime + ",共进 " + nums + " 单。");

			MailMessageUtils.sendMail(mailMessage);

			logger.info("数据收集任务执行完毕");
		} catch (NumberFormatException e) {
			result.setResultCode("400");
			result.setResultContent("扫描系统数据出错");
			logger.error("扫描系统数据出错", e);
			return result;
		}
		result.setResultCode("200");
		result.setResultCode("请款任务执行结束");
		return result;
	}
}
