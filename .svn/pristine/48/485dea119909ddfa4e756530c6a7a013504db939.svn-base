package com.yixin.web.service;

import com.yixin.common.utils.Page;
import com.yixin.dsc.dto.rule.DscMatchResultDto;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.web.dto.AssetMainInfoDto;
import com.yixin.web.dto.ConditionBaseDto;

import java.util.List;
import java.util.Map;

/**
 * @author sukang
 */
public interface AssetMainInfoService {

    /**
     * 获取所有的订单
     * @param assetMainInfoDto 入参
     * @return page集合
     */

    Page<Map<String,Object>> getAssetMainList(AssetMainInfoDto assetMainInfoDto);

    /**
     * 获取订单详细信息
     * @param applyNo  订单编号
     * @return 详细信息
     */
    List<Map<String,Object>> getAssetMainDetail(String applyNo);

    /**
     * 获取订单的附件信息
     * @param conditionBaseDto 条件实体
     * @return 附件信息集合
     */
    Page<Map<String,Object>> getAttachmentInfo(ConditionBaseDto conditionBaseDto);

    /**
     *  获取订单同步记录
     * @param conditionBaseDto 申请编号
     * @return 内容集合
     */
    List<Map<String,Object>> getSynLogInfo(ConditionBaseDto conditionBaseDto);

    /**
     * 获取订单的准入记录
     * @param conditionBaseDto 申请编号
     * @return 准入记录集合
     */
    List<DscMatchResultDto> getMatchResults(ConditionBaseDto conditionBaseDto);

    /**
     * 根据表名查询表内容结果集
     * @param applyNo 申请编号
     * @param tableInfo 表名
     * @return 结果集
     */
    List<Object> getInfoByTable(String applyNo, String tableInfo);


    /**
     * 资料补传接口
     * @return 结果集
     * @param conditionBaseDto 条件dto
     */
    BaseMsgDTO supplyAttachment(ConditionBaseDto conditionBaseDto);

    /**
     * 资料补传通知银行接口
     * @param conditionBaseDto 条件dto
     * @return 执行结果集
     */
    List<BaseMsgDTO> supplyAttachmentBank(ConditionBaseDto conditionBaseDto);

    /**
     * 查询银行请款通过,但是贷后资料未传给银行信息列表
     * @param applyNo
     * @param financialId
     * @param currentPage
     * @return 分页List
     */
    Page<List<Map<String, Object>>> getLoanDataNotSynList(String applyNo,String financialId, String currentPage);

    /**
     * 查询不同阶段审核结果
     * @param applyNo
     * @param phase
     * @param currentPage
     * @return
     */
    Page<List<Map<String, Object>>> getPhaseQueryResultsList(String applyNo, String phase, String currentPage);


}
