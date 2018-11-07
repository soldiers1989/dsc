package com.yixin.kepler.v1.job;

import java.util.List;
import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import com.yixin.common.exception.BzException;
import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.common.system.job.JobExecutor;
import com.yixin.common.utils.JobParamDTO;
import com.yixin.dsc.util.CommonUtil;
import com.yixin.dsc.util.MailMessageUtils;
import com.yixin.kepler.common.RestTemplateUtil;
import com.yixin.kepler.component.RedisDistributedLock;
import com.yixin.kepler.v1.common.constants.BankRequestConstant;
import com.yixin.kepler.v1.common.core.bankReq.BankRespResultHandle;
import com.yixin.kepler.v1.common.core.bankReq.BankRespResultHandleEvent;
import com.yixin.kepler.v1.datapackage.dto.BankRespResultHandleDTO;
import com.yixin.kepler.v1.datapackage.entity.AssetBankRequest;

/**
 * 资产向发起银行请求任务
 * Package : com.yixin.kepler.v1.job
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年9月17日 下午3:46:34
 *
 */
@Component
public class BankRequestJobScheduler implements JobExecutor{

	private static final Logger logger = LoggerFactory.getLogger(BankRequestJobScheduler.class);

	@Resource
	private RedisDistributedLock redisDistributedLock;

    @Autowired
    private ApplicationContext ioc;

    /**
     * 异步请求银行重试最大次数	
     */
    private static final int NUM = 15;
    
	
    /**
     * 任务入口
     * @param jobParamDTO
     * @return 
     * @author YixinCapital -- chen.lin
     *	       2018年9月27日 下午6:20:08
     */
	@Override
	public JobParamDTO execute(JobParamDTO jobParamDTO) {
		JobParamDTO result = new JobParamDTO();
		logger.info("----------开始执行资产发起银行请求任务-----------");
		Boolean lock = redisDistributedLock.lock("Job-"+getClass().getSimpleName(), TimeUnit.MINUTES, 5);
		if (lock){
			List<String> reqTasks = AssetBankRequest.getLegalRunGroup4Num(NUM);
			logger.info("----------执行资产发起银行请求准备就绪，共{}条", reqTasks.size());
			String applyNo = "";
			try {
				if(reqTasks != null && reqTasks.size() > 0){
					for (String taskId : reqTasks) {
						AssetBankRequest task = AssetBankRequest.getById(taskId);
						applyNo = task.getApplyNo();
						logger.info("执行资产发起银行请求，applyNo={}", applyNo);
						taskHandle(task);
					}
				}
			} catch (BzException e) {
				logger.error("资产发起银行请求执行异常",e);
				sendEmail(e, applyNo);
			} finally {
				redisDistributedLock.unLock("Job-"+getClass().getSimpleName());
			}
		}else {
			logger.info("{}未获取到锁", "Job-"+getClass().getSimpleName());
		}
		logger.info("----------执行资产发起银行请求任务结束-----------");
		result.setResultCode("200");
		result.setResultContent("资产发起银行请求任务执行结束");
		return result;
	}
		
		
	/**
	 * 异常后发送邮件
	 * @param e
	 * @param applyNo 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月17日 下午6:08:39
	 */
	private void sendEmail(Exception e, String applyNo) {
		MailMessageUtils.sendException(e, "订单号(" + applyNo+ ")资产发起银行请求任务执行异常，请关注");
	}	
		
		
	/**
	 * 执行资产发起银行请求
	 * @param task 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月17日 下午6:11:09
	 */
	@Transactional
	private void taskHandle(AssetBankRequest task) throws BzException {
		try {
			// 对请求报文做最后转换
			String reqBody = requestBeforeHandle(task);
			if(reqBody == null){
				reqBody = task.getAssetReqBody();
			} else{
				task.setAssetReqBody(reqBody);
			}
			// 发送银行请求
			logger.info("[上送"+task.getAssetNo()+"银行]，请求阶段:[{}]，请求开始，申请编号:{}，参数:{}，url:{}", task.getPhase(),task.getApplyNo(), reqBody, task.getReqUrl());
			String result = RestTemplateUtil.sendRequest(task.getReqUrl(), task.getAssetReqBody(), task.getAssetNo());
			logger.info("[上送"+task.getAssetNo()+"银行]，请求阶段:[{}]，请求结束，申请编号:{}，返回:{}", task.getPhase(),task.getApplyNo(), result);
			if(null != result && CommonUtil.isJson(result)){
				logger.info("[{}]银行，请求阶段:[{}]，申请编号:{}，请求成功后进行结果处理", task.getAssetNo(), task.getPhase(), task.getApplyNo());
				task.setBankRepBody(result);
				task.setReqState(BankRequestConstant.REQ_BANK_INHAND);//报文是json默认发起成功，后续处理
				task.update();
				// --------------调用异步处理银行结果
				asyncHandleResult(task);
			}else{
				logger.error("[{}]银行，请求阶段:[{}]，申请编号:{}，返回报文为空或非JSON格式，任务进行下次重试处理", task.getAssetNo(), task.getPhase(), task.getApplyNo());
				task.setReqState(BankRequestConstant.RETRY_REQ_BANK);
				int retryMark = task.getRetryMark();
				logger.info("[{}]银行，请求阶段:[{}]，申请编号:{}，重试次数{}", task.getAssetNo(), task.getPhase(), task.getApplyNo(), retryMark);
				task.setRetryMark(++retryMark);
				task.update();
			}
		} catch (Exception e) {
			task.setReqState(BankRequestConstant.RETRY_REQ_BANK);
			int retryMark = task.getRetryMark();
			logger.info("[{}]银行，请求阶段:[{}]，申请编号:{}，重试次数{}", task.getAssetNo(), task.getPhase(), task.getApplyNo(), retryMark);
			task.setRetryMark(++retryMark);
			task.update();
			logger.error("[{}]银行，请求阶段:[{}]，申请编号:{}，资产发起银行请求执行异常", task.getAssetNo(), task.getPhase(), task.getApplyNo(), e);
			sendEmail(e, task.getApplyNo());
		}
	}
	
	
	/**
	 * 对请求报文做最后转换
	 * @param task
	 * @return 
	 * @author YixinCapital -- chen.lin
	 *	       2018年10月9日 上午10:38:26
	 */
	private String requestBeforeHandle(AssetBankRequest task){
		// 路由到具体的实现
		BankRespResultHandle bean = SpringContextUtil.getBean(
				task.getAssetNo().concat(BankRespResultHandleEvent.SUFFIX), BankRespResultHandle.class);
		return bean.requestBeforeHandle(task);
	}

	
	/**
	 * 异步处理结果的业务逻辑-不影响跑批
	 * @param task 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月17日 下午6:22:35
	 */
	private void asyncHandleResult(AssetBankRequest task) {
		BankRespResultHandleDTO handleDTO = new BankRespResultHandleDTO();
		handleDTO.setBankReqId(task.getId());
		handleDTO.setApplyNo(task.getApplyNo());
		handleDTO.setTranNo(task.getTranNo());
		handleDTO.setAssetNo(task.getAssetNo());
		handleDTO.setVenusRespJosn(task.getAssetReqBody());
		handleDTO.setBankResultJosn(task.getBankRepBody());
		
		handleDTO.setPhase(task.getPhase());
        ioc.publishEvent(new BankRespResultHandleEvent(handleDTO));
	}
}
