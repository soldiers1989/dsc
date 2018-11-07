package com.yixin.kepler.core.service.impl.webank;

import java.util.Date;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yixin.dsc.common.exception.BzException;
import com.yixin.dsc.util.DateUtil;
import com.yixin.kepler.core.domain.webank.WbCommonRequestToBank;
import com.yixin.kepler.core.service.BankResultService;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.dto.webank.WBLoanDTO;
import com.yixin.kepler.dto.webank.WBLoanResultRespDTO;
import com.yixin.kepler.enity.AssetOrderFlow;
import com.yixin.kepler.enity.AssetResultTask;

/**
 * @author sukang  on 2018/8/31.
 */
@Service("WeBankResultService")
public class WeBankResultServiceImpl implements BankResultService {
	
	private final Logger logger = LoggerFactory.getLogger(WeBankResultServiceImpl.class);
	
	@Resource
    private WbCommonRequestToBank wbCommonRequestToBank;
	


    @Override
    public BaseMsgDTO selectResult(AssetResultTask assetResultTask) {
    	BaseMsgDTO result = BaseMsgDTO.processData("进行中");
    	WBLoanResultRespDTO resultPespDto = null;
    	String applyNo = assetResultTask.getApplyNo();
    	try {
			resultPespDto = wbCommonRequestToBank.loanStatusQuery(applyNo);
		} catch(BzException e){
			logger.error("【微众银行】查询放款状态失败，订单编号:{},错误信息：{}",applyNo,e.getMessage());
			return BaseMsgDTO.failureData(e.getMessage());
		} catch (Exception e) {
			logger.error("【微众银行】查询放款状态失败，订单编号:{}",applyNo,e);
		}
    	
    	if(resultPespDto != null && CollectionUtils.isNotEmpty(resultPespDto.getLoanList())){
    		Date loanTime = null;
    		for(WBLoanDTO wbLoanDTO:resultPespDto.getLoanList()){
        		if("S".equals(wbLoanDTO.getLoanStatus())){ //放款成功
        			if(loanTime == null){
        				loanTime = wbLoanDTO.getUpdateTime();
        			} else {
        				loanTime = loanTime.after(wbLoanDTO.getUpdateTime())?loanTime:wbLoanDTO.getUpdateTime();
        			}
        			result = BaseMsgDTO.successData("查询成功");
        		}
        	}
    		logger.info("【微众银行】查询放款状态，记录放款时间，订单编号：{},放款时间：{},借据列表：{}",applyNo
    				,loanTime == null?"":DateUtil.dateToString(loanTime,DateUtil.DEFAULT_TIMESTAMP_FORMAT)
    				,JSON.toJSONString(resultPespDto.getLoanList()));
    		AssetOrderFlow.recordLoanInfo(applyNo, resultPespDto.getLoanList(), loanTime);
    	}
    	
        return result;
    }
}
