package com.yixin.kepler.core.job;

import com.yixin.common.exception.BzException;
import com.yixin.common.system.job.JobExecutor;
import com.yixin.common.utils.InvokeResult;
import com.yixin.common.utils.JobParamDTO;
import com.yixin.dsc.util.MailMessageUtils;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.component.RedisDistributedLock;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.core.domain.AsyncTask;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.enity.AssetAfterLoanTask;
import com.yixin.kepler.enity.AssetPaymentTask;
import com.yixin.kepler.enity.OsbFileLog;
import net.sf.json.JSONObject;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.List;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;


/**
 * @author sukang
 */
@Component
public class AfterLoanJobScheduler implements JobExecutor{

	private static final Logger logger = LoggerFactory.getLogger(AfterLoanJobScheduler.class);

	@Resource
	private RedisDistributedLock redisDistributedLock;


	private final static int num = 6;

	@Resource
	private AsyncTask asyncTask;

	@Override
	public JobParamDTO execute(JobParamDTO jobParamDTO) {
		JobParamDTO result = new JobParamDTO();

		logger.info("--------开始执行贷后任务-----------");
		String applyNo = "";

		Boolean lock = redisDistributedLock.lock(getClass().getSimpleName(),
				TimeUnit.MINUTES, 5);
		if (lock){
			List<AssetAfterLoanTask> currentTask = AssetAfterLoanTask.getCurrentTask();

			try {
				if (CollectionUtils.isNotEmpty(currentTask)){
                    List<String> collect = currentTask.stream().map((t) ->
                            t.getApplyNo()).collect(Collectors.toList());
                    logger.info("开始执行贷后任务{}", collect);

                    for (AssetAfterLoanTask task:currentTask){
                    	applyNo = task.getApplyNo();
                        OsbFileLog ok = OsbFileLog.getOk(task.getApplyNo(), BankPhaseEnum.AFTER_LOAN.getPhase());
                        if (ok == null ) {continue;}


                        //单条记录重试次数超过8次不再重试
                        if (task.getExecTimes() > num){continue;}

                        InvokeResult<BaseMsgDTO> requestBank = asyncTask.requestBank(
                                task.getApplyNo(), BankPhaseEnum.AFTER_LOAN);

						logger.info("订单号{}贷后调用获取的执行结果为{}", task.getApplyNo(),
								JSONObject.fromObject(requestBank).toString());

						BaseMsgDTO baseMsgDTO = (BaseMsgDTO)requestBank.getData();

						if (baseMsgDTO.successed()){
							task.setIsSuccess(true);
							task.setIsEnd(true);
						}else if(CommonConstant.FAILURE.equals(baseMsgDTO.getCode())){
							task.setIsSuccess(false);
							logger.error("调用银行贷后接口失败，失败原因为:{}",
									baseMsgDTO.getMessage());
						}
						
						task.setExecTimes(task.getExecTimes() + 1);
						task.setExecState(1);
						task.update();
                    }
                }
			} catch (BzException e) {
				logger.error("贷后定时任务执行异常",e);
				sendEmail(e,applyNo);
			} finally {
				redisDistributedLock.unLock(getClass().getSimpleName());
			}
		}else {
			logger.info("{}未获取到锁",getClass().getSimpleName());
		}
		result.setResultCode("200");
		result.setResultCode("贷后任务执行结束");
		return result;
	}

	private void sendEmail(BzException e, String applyNo) {
		MailMessageUtils.sendException(e,"订单号(" + applyNo+ ")贷后任务执行异常，请关注");

	}

}
