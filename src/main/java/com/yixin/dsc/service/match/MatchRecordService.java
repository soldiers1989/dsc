package com.yixin.dsc.service.match;

import java.util.List;

import com.yixin.dsc.dto.match.DscMatchData2AlixDto;
import com.yixin.dsc.dto.match.DscOrderTrackDto;
import com.yixin.dsc.dto.match.DscRuleMatchRecordDto;

/**
 * 
 * Package : com.yixin.web.service
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年9月25日 下午2:56:19
 *
 */
public interface MatchRecordService {
	
	/**
	 * 准入记录list
	 * 
	 * @param applyNo
	 * @return 
	 * @author YixinCapital -- wangxulong
	 *	       2018年9月25日 下午2:59:29
	 */
	List<DscOrderTrackDto> getMatchListByApplyNo(String applyNo);
	
	
	/**
	 * 资方 准入明细
	 * 
	 * @param applyNo
	 * @param matchTrackId  准入记录ID
	 * @return 
	 * @author YixinCapital -- wangxulong
	 *	       2018年9月25日 下午4:01:37
	 */
	List<DscRuleMatchRecordDto> getDistinctCapitalListByApplyNo(String applyNo,String matchTrackId);
	
	
	/**
	 * 准入  规则明细
	 * 
	 * @param applyNo
	 * @return 
	 * @author YixinCapital -- wangxulong
	 *	       2018年9月25日 下午4:02:03
	 */
	List<DscRuleMatchRecordDto> getDscRuleMatchRecordListByApplyNo(String applyNo,String matchTrackId,String capitalId);
	
	
	/**
	 * 自营--该订单最后一次准入记录
	 * 	准入成功及不成功的资方List
	 * 
	 * @param applyNo
	 * @return 
	 * @author YixinCapital -- wangxulong
	 *	       2018年10月25日 下午5:29:47
	 */
	DscMatchData2AlixDto findMatchDataByApplyNo(String applyNo);
	
}
