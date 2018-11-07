package com.yixin.kepler.v1.job;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.yixin.common.system.job.JobExecutor;
import com.yixin.common.utils.JobParamDTO;
import com.yixin.dsc.service.async.AsyncTaskManagerService;
import com.yixin.kepler.component.RedisDistributedLock;
import com.yixin.kepler.core.constant.CommonConstant;

/**
 * Venus 定时补救任务
 * Package : com.yixin.kepler.v1.job
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年10月22日 下午3:25:53
 *
 */
@Component
public class VenusRescueJobScheduler implements JobExecutor{

	private static final Logger LOGGER = LoggerFactory.getLogger(BankRequestJobScheduler.class);
	
	@Resource
	private RedisDistributedLock redisDistributedLock;
	
	@Resource(name = "asyncTaskManagerSpringImpl")
	private AsyncTaskManagerService asyncTaskManagerService;
	
	@Override
	public JobParamDTO execute(JobParamDTO jobParamDTO) {
		JobParamDTO result = new JobParamDTO();
		result.setResultCode("200");
		LOGGER.info("----------开始执行Venus定时补救任务-----------");
		Boolean lock = redisDistributedLock.lock("Job-"+getClass().getSimpleName(), TimeUnit.MINUTES, 1);
		if (lock){
			if(jobParamDTO == null || StringUtils.isBlank(jobParamDTO.getAgrs())){
				LOGGER.info("执行Venus定时补救任务未执行，直接结束");
				result.setResultContent("执行Venus定时补救任务未执行，直接结束");
				return result;
			}
			JSONObject jsonObject = null;
			try {
				jsonObject = JSON.parseObject(jobParamDTO.getAgrs());
			} catch (Exception e) {
				LOGGER.error("执行Venus定时补救任务解析任务参数异常",e);
				result.setResultContent("执行Venus定时补救任务解析任务参数异常");
				return result;
			}
			if(jsonObject.getBooleanValue("clearAttachment")){
				//=============== 定时清理附件 ===========================================
				this.asyncTaskManagerService.venusRescueJob(CommonConstant.ZERO);
			}
			
		} else {
			LOGGER.info("{}未获取到锁", "Job-"+getClass().getSimpleName());
		}
		LOGGER.info("----------Venus定时补救任务执行结束-----------");
		result.setResultContent("执行Venus定时补救任务结束");
		return result;
	}

}
