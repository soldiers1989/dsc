package com.yixin.dsc.api;

import java.util.concurrent.TimeUnit;

import javax.annotation.Resource;

import com.yixin.web.anocation.InterfaceMonitor;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSONObject;
import com.yixin.common.utils.InvokeResult;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.dsc.annotation.DscAlixCallTimeLog;
import com.yixin.dsc.common.exception.BzException;
import com.yixin.dsc.dto.DscAdmittanceDto;
import com.yixin.dsc.dto.DscAdmittanceReturnDto;
import com.yixin.dsc.service.shunt.DscShuntService;
import com.yixin.kepler.component.RedisDistributedLock;
import com.yixin.kepler.enity.AssetFinanceInfo;

/**
 * 分流api
 *
 * Package : com.yixin.dsc.api
 *
 * @author YixinCapital -- huguoxing
 *		   2018年6月4日 下午3:01:51
 *
 */
@RestController
@RequestMapping(value="/api/dscShuntApi")
public class DscShuntApi {

	private static final Logger LOGGER = LoggerFactory.getLogger(DscShuntApi.class);

	@Resource
	private DscShuntService dscShuntService;

	@Resource
	private RedisDistributedLock redisDistributedLock;

	/**
	 * 资方准入校验
	 *
	 * @param dscAdmittanceDto
	 * @return
	 * @author YixinCapital -- huguoxing
	 *	       2018年6月4日 下午3:03:46
	 */
	@InterfaceMonitor(errorMsg = "资方准入校验失败", keyParam = {"applyNo::0_applyNo"})
	@DscAlixCallTimeLog(methodType = "alixRequest")
	@PostMapping("/capitalAccessCheck")
	public InvokeResult<DscAdmittanceReturnDto> capitalAccessCheck(@RequestBody DscAdmittanceDto dscAdmittanceDto){
		InvokeResult<DscAdmittanceReturnDto> iResult = new InvokeResult<>();
		LOGGER.info("DscShuntApi资方准入校验入参：{}",JsonObjectUtils.objectToJson(dscAdmittanceDto));
		if(dscAdmittanceDto == null){
			return iResult.failure("资方准入校验入参为空");
		}
		if(StringUtils.isBlank(dscAdmittanceDto.getApplyNo())){
			return iResult.failure("资方准入校验订单编号为空");
		}
		String applyNo = dscAdmittanceDto.getApplyNo(); //订单编号
		String capitalCode = dscAdmittanceDto.getCapitalCode(); //资方Code
		String capitalId = null;
		if(StringUtils.isNotBlank(capitalCode)){
			AssetFinanceInfo assInfo = AssetFinanceInfo.getByCode(capitalCode);
			if(assInfo==null || StringUtils.isBlank(assInfo.getId())){
				return iResult.failure("资方准入校验资方Code错误");
			}
			capitalId = assInfo.getId();
		}
		LOGGER.info("DscShuntApi资方准入校验 订单编号：{} ,资方ID:{}",applyNo,capitalId);

		String lockKey = "capitalAccessCheck".concat(applyNo);
//		try {
			//根据订单获取锁
			Boolean lock = redisDistributedLock.lock(lockKey, TimeUnit.MINUTES, 2);

			if (lock) {
				try {
					iResult.setData(this.dscShuntService.capitalAccessCheck(applyNo, capitalId,capitalCode));
				}catch (Exception e) {
					throw e;
				} finally {
					redisDistributedLock.unLock(lockKey);
				}
			}else {
				iResult.failure(String.format("订单%s请勿重复提交", applyNo));
			}

//		} catch (BzException e) {
//			LOGGER.error("DscShuntApi资方准入校验 失败，订单编号:{},错误：{}",applyNo, e.getMessage());
//			return iResult.failure(e.getMessage());
//		} catch (Exception e) {
//			LOGGER.error("DscShuntApi资方准入校验 异常, 订单编号:{},",applyNo, e);
//			return iResult.failure("资方准入校验失败");
//		}
		LOGGER.info("DscShuntApi资方准入校验，订单编号：{},返参:{}", applyNo,JSONObject.toJSONString(iResult));
		return iResult;
	}


}
