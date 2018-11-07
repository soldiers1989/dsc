package com.yixin.kepler.v1.datapackage.entity;

import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.common.system.util.Label;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.kepler.v1.common.constants.BankRequestConstant;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * 资产发起银行请求表（任务）
 * Package : com.yixin.kapler_v1.datapackage.entity
 *
 * @author YixinCapital -- chen.lin
 *         2018年9月17日 下午2:01:46
 */
@Entity
@Table(name = "k_asset_bank_request")
public class AssetBankRequest extends BZBaseEntiy {

    /**
     * @author YixinCapital -- chen.lin
     * 2018年9月17日 下午1:55:23
     */
    private static final long serialVersionUID = -7338832285517236882L;

    /**
     * 申请编号
     */
    @Label(name = "申请编号")
    @Column(name = "apply_no", length = 64)
    private String applyNo;

    /**
     * 银行申请编号
     */
    @Label(name = "银行申请编号，一个申请编号可能对于多个银行申请编号")
    @Column(name = "bank_order_no", length = 64)
    private String bankOrderNo;

    /**
     * 流水号
     */
    @Label(name = "流水号")
    @Column(name = "tran_no", length = 64)
    private String tranNo;

    /**
     * 资产编号
     */
    @Label(name = "资产编号")
    @Column(name = "asset_no", length = 64)
    private String assetNo;

    /**
     * 资管请求报文
     */
    @Label(name = "资管请求报文")
    @Column(name = "asset_req_body", columnDefinition = "LONGTEXT")
    private String assetReqBody;

    /**
     * 银行返回报文
     */
    @Label(name = "银行返回报文")
    @Column(name = "bank_rep_body", columnDefinition = "LONGTEXT")
    private String bankRepBody;

    /**
     * 所属阶段
     */
    @Label(name = "所属阶段")
    @Column(name = "phase", length = 64)
    private String phase;

    /**
     * 请求的url地址
     */
    @Label(name = "请求的url地址")
    @Column(name = "req_url", length = 255)
    private String reqUrl;

    /**
     * 向银行发起请求状态
     * 0-未发起    1-处理中    2-发起成功[银行收妥]  3-发起失败[银行返回未收妥]  4-发起失败后重试[通讯等问题响应空]  5-处理结果时发生异常
     */
    @Label(name = "向银行发起请求状态")
    @Column(name = "req_state", length = 50)
    private String reqState;

    /**
     * 请求重试标记(默认最大重试4次)
     */
    @Label(name = "请求重试标记")
    @Column(name = "retry_mark ", length = 2)
    private int retryMark;

    /**
     * osb处理状态
     * 0-失败   1-成功
     */
    @Label(name = "向银行发起请求状态")
    @Column(name = "osb_state", length = 50)
    private String osbState;


    /**
     * 获取合法跑批数据
     *
     * @return
     * @author YixinCapital -- chen.lin
     * 2018年9月17日 下午1:53:34
     */
    @SuppressWarnings("serial")
    public static List<AssetBankRequest> getLegalRunGroup() {
        StringBuilder jpql = new StringBuilder("SELECT cl FROM AssetBankRequest AS cl WHERE 1=1 AND ");
        jpql.append(" (cl.reqState=?1 AND cl.osbState=?2) OR ");
        jpql.append(" (cl.reqState=?3 AND cl.osbState=?4 AND cl.retryMark<=?5) ORDER BY cl.createTime ASC  ");
        List<Object> filter = new ArrayList<Object>(1) {{
            add(BankRequestConstant.UN_REQ_BANK);
            add(BankRequestConstant.OSB_SUCCESS);
            add(BankRequestConstant.RETRY_REQ_BANK);
            add(BankRequestConstant.OSB_SUCCESS);
            add(BankRequestConstant.RETRY_MAX_COUNT);
        }};
        logger.info("获取合法跑批数据jpql：{}", jpql.toString());
        List<AssetBankRequest> result = getRepository().createJpqlQuery(jpql.toString()).setParameters(filter).list();
        return result;
    }


    /**
     * 获取合法跑批数据--根据条数
     *
     * @param num 设置跑批条数
     * @return
     * @author YixinCapital -- chen.lin
     * 2018年10月22日 下午4:42:59
     */
    @SuppressWarnings({"serial", "unchecked"})
    public static List<String> getLegalRunGroup4Num(int num) {
        StringBuilder sql = new StringBuilder("SELECT id FROM k_asset_bank_request WHERE 1=1 AND ");
        sql.append(" (req_state=?1 AND osb_state=?2) OR ");
        sql.append(" (req_state=?3 AND osb_state=?4 AND retry_mark<=?5) ORDER BY create_time ASC LIMIT " + num);
        List<Object> filter = new ArrayList<Object>(1) {{
            add(BankRequestConstant.UN_REQ_BANK);
            add(BankRequestConstant.OSB_SUCCESS);
            add(BankRequestConstant.RETRY_REQ_BANK);
            add(BankRequestConstant.OSB_SUCCESS);
            add(BankRequestConstant.RETRY_MAX_COUNT);
        }};
        logger.info("获取合法跑批数据sql：{}", sql.toString());
        List<String> result = getQueryChannel().createSqlQuery(sql.toString()).setParameters(filter).list();
        logger.info("本次跑批条数为：{}，list：{}", result.toString(), result.size());
        return result;
    }


    /**
     * 通过申请编号、阶段获取申请在途数据（一定是唯一一条）
     *
     * @return
     * @author YixinCapital -- chen.lin
     * 2018年9月17日 下午1:53:34
     */
    @SuppressWarnings("serial")
    public static AssetBankRequest getOnlyApplyOnWay(String applyNo, String phase) {
        logger.info("通过申请编号、阶段获取申请在途数据，applyNo={}，phase={}", applyNo, phase);
        StringBuilder jpql = new StringBuilder("SELECT cl FROM AssetBankRequest AS cl WHERE 1=1 AND ");
        jpql.append(" (cl.reqState=?1 OR cl.reqState=?2 OR (cl.reqState=?3 AND cl.retryMark<=?4)) ");
        jpql.append("  AND cl.applyNo=?5 AND cl.phase=?6 ");
        List<Object> filter = new ArrayList<Object>(1) {{
            add(BankRequestConstant.UN_REQ_BANK);
            add(BankRequestConstant.REQ_BANK_INHAND);
            add(BankRequestConstant.RETRY_REQ_BANK);
            add(BankRequestConstant.RETRY_MAX_COUNT);
            add(applyNo);
            add(phase);
        }};
        AssetBankRequest result = getRepository().createJpqlQuery(jpql.toString()).setParameters(filter).singleResult();
        logger.info("通过申请编号、阶段获取申请在途数据，result={}，jpql={}", JsonObjectUtils.objectToJson(result), jpql.toString());
        return result;
    }


    /**
     * 根据id获取资产发起银行请求信息
     *
     * @param id
     * @return
     * @author YixinCapital -- chen.lin
     * 2018年9月18日 上午10:57:05
     */
    @SuppressWarnings("serial")
    public static AssetBankRequest getById(String id) {
        logger.info("根据id获取资产发起银行请求信息，id={}", id);
        StringBuilder jpql = new StringBuilder("SELECT cl FROM AssetBankRequest AS cl WHERE 1=1 AND cl.id=?1 ");
        List<Object> filter = new ArrayList<Object>(1) {{
            add(id);
        }};
        AssetBankRequest result = getRepository().createJpqlQuery(jpql.toString()).setParameters(filter).singleResult();
        logger.info("根据id获取资产发起银行请求信息，result{}，jpql={}", JsonObjectUtils.objectToJson(result), jpql.toString());
        return result;
    }



    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getBankOrderNo() {
        return bankOrderNo;
    }

    public void setBankOrderNo(String bankOrderNo) {
        this.bankOrderNo = bankOrderNo;
    }

    public String getTranNo() {
        return tranNo;
    }

    public void setTranNo(String tranNo) {
        this.tranNo = tranNo;
    }

    public String getAssetNo() {
        return assetNo;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
    }

    public String getAssetReqBody() {
        return assetReqBody;
    }

    public void setAssetReqBody(String assetReqBody) {
        this.assetReqBody = assetReqBody;
    }

    public String getBankRepBody() {
        return bankRepBody;
    }

    public void setBankRepBody(String bankRepBody) {
        this.bankRepBody = bankRepBody;
    }

    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getReqUrl() {
        return reqUrl;
    }

    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
    }

    public String getReqState() {
        return reqState;
    }

    public void setReqState(String reqState) {
        this.reqState = reqState;
    }

    public int getRetryMark() {
        return retryMark;
    }

    public void setRetryMark(int retryMark) {
        this.retryMark = retryMark;
    }

    public String getOsbState() {
        return osbState;
    }

    public void setOsbState(String osbState) {
        this.osbState = osbState;
    }


}