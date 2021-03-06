package com.yixin.kepler.v1.service.capital.yntrust;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.yixin.common.exception.BzException;
import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.service.async.AsyncTaskManagerService;
import com.yixin.kepler.core.base.AbstractBaseDealDomain;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.enity.AssetMainInfo;

/**
 * 请款前置类
 * @author YixinCapital -- chenjiacheng
 *         2018年09月26日 10:10
 **/
@Service("YNTRUSTPaymentTrialCheckRequestPrepose")
public class PaymentRequestPrepose extends AbstractBaseDealDomain<Object, BaseMsgDTO> {

	private static final Logger logger = LoggerFactory.getLogger(PaymentRequestPrepose.class);

	@Resource
	private YTRequestDataHandle requestDataHandle;
	
	@Resource(name = "asyncTaskManagerSpringImpl")
	private AsyncTaskManagerService asyncTaskManagerService;



	@Override
	protected void getData() throws BzException {

	}

	@Override
	protected void assembler() throws BzException {

	}

	@Override
	protected InvokeResult<BaseMsgDTO> message() throws BzException {
		return null;
	}



	@Override
	public BaseMsgDTO requestPrepose(String applyNo, String financeCode) {
		AssetMainInfo mainInfo = AssetMainInfo.getByApplyNo(applyNo);
		//================== 异步签约导入 ============================
		logger.info("云南信托青款前置处理-异步签约导入,订单编号:{}",applyNo);
		this.asyncTaskManagerService.YTImportProtocol(mainInfo);
		
		//================== 查询还款计划废弃- 查还款计划是查的贷后的数据，贷前再放款成功并还款计划上传成功才会导入资产包到贷后，还款计划才可查  ============================
		/*try {
			List<YTRepaySchedulesDTO> repayList = this.requestDataHandle.queryRepanPlan(mainInfo);
			if(CollectionUtils.isEmpty(repayList)){
				return BaseMsgDTO.failureData("还款计划未同步，请同步还款计划"); 
			}
		} catch (BzException e) {
			return BaseMsgDTO.failureData(e.getMessage()); 
		} catch (Exception e) {
			logger.error("订单编号:{},请款前置-查询还款计划异常", mainInfo.getApplyNo(),e);
			return BaseMsgDTO.failureData("查询还款计划异常");
		}*/
		/**
		 * 继续走原来的老的定时任务
		 */
		return BaseMsgDTO.successData("success");
	}
	
}
