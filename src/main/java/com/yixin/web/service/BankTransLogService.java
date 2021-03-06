package com.yixin.web.service;

import com.yixin.common.utils.InvokeResult;
import com.yixin.common.utils.Page;
import com.yixin.web.dto.AttachmentConditionDto;
import com.yixin.web.dto.ConditionBaseDto;

import java.util.List;
import java.util.Map;

/**
 * @author sukang
 */
public interface BankTransLogService {

    /**
     * 获取银行交易记录
     *
     * @param aConditionDto 条件参数
     * @return map集合
     */
    Page<Map<String, Object>> getBankTransList(AttachmentConditionDto aConditionDto);

    /**
     * 获取推送结算失败记录
     * @param conditionBaseDto 条件参数
     * @return list-map集合
     */
    Page<Map<String, Object>> getSettleFailure(ConditionBaseDto conditionBaseDto);

    /**
     * 获取推送结算失败记录
     * @param conditionBaseDto 条件参数
     * @return list-map集合
     * @author YixinCapital -- wangshuaiqiang
     *	       2018/10/26  18:54
     */
    Page<Map<String, Object>> getSettleFailureV1(ConditionBaseDto conditionBaseDto);

    /**
     * 重新推送结算
     * @param applyNo 申请编号
     * @return 是否推送成功
     */
    InvokeResult<Boolean> pushSettle(String applyNo);


}
