package com.yixin.kepler.enity;

import com.google.common.collect.Lists;
import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.common.system.util.Label;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;


/**
 * <B>功能简述</B><br>
 * KAssetBankTran实体类
 *
 * @author liuhongshen
 * @date 2018年06月06日 14:14:54
 */
@Entity
@Table(name = "k_asset_bank_tran")
public class AssetBankTran extends BZBaseEntiy {
    /**
     * 交易编号
     */
    @Label(name = "交易编号")
    @Column(name = "tran_no", length = 64)
    private String tranNo;
    /**
     * 资产id
     */
    @Label(name = "资产id")
    @Column(name = "asset_id", length = 32)
    private String assetId;
    /**
     * 资产编号
     */
    @Label(name = "资产编号")
    @Column(name = "asset_no", length = 64)
    private String assetNo;
    /**
     * 申请编号
     */
    @Label(name = "申请编号")
    @Column(name = "apply_no", length = 64)
    private String applyNo;
    /**
     * 银行申请编号
     */
    @Label(name = "银行订单编号，每次交易必须唯一，一个申请编号可能对应多个订单编号")
    @Column(name = "bank_order_no", length = 64)
    private String bankOrderNo;
    /**
     * 请求报文
     */
    @Label(name = "请求报文")
    @Column(name = "req_body", columnDefinition = "LONGTEXT")
    private String reqBody;
    /**
     * 返回报文
     */
    @Label(name = "返回报文")
    @Column(name = "rep_body", columnDefinition = "LONGTEXT")
    private String repBody;
    /**
     * 接口标识
     */
    @Label(name = "接口标识")
    @Column(name = "api_code", length = 64)
    private String apiCode;
    /**
     * 所属阶段
     */
    @Label(name = "所属阶段")
    @Column(name = "phase", length = 64)
    private String phase;
    /**
     * 交易结果码值
     */
    @Label(name = "交易结果码值")
    @Column(name = "approval_code", length = 32)
    private String approvalCode;
    /**
     * 交易结果描述
     */
    @Label(name = "交易结果描述")
    @Column(name = "approval_desc", length = 255)
    private String approvalDesc;
    /**
     * 备注
     */
    @Label(name = "备注")
    @Column(name = "remark", length = 255)
    private String remark;
    
    /**
     * 发送方标志1：易鑫，2：银行
     */
    @Label(name = "发送方标志1：易鑫，2：银行")
    @Column(name = "sender", length = 10)
    private Integer sender;

    /**
     * 请求的url地址
     */
    @Label(name = "请求的url地址")
    @Column(name = "req_url", length = 255)
    private String reqUrl;

    
    public String getBankOrderNo() {
		return bankOrderNo;
	}

	public void setBankOrderNo(String bankOrderNo) {
		this.bankOrderNo = bankOrderNo;
	}

	public String getReqUrl() {
        return reqUrl;
    }

    public void setReqUrl(String reqUrl) {
        this.reqUrl = reqUrl;
    }

    public String getTranNo() {
        return this.tranNo;
    }

    public void setTranNo(String tranNo) {
        this.tranNo = tranNo;
    }

    public String getAssetId() {
        return this.assetId;
    }

    public void setAssetId(String assetId) {
        this.assetId = assetId;
    }

    public String getAssetNo() {
        return this.assetNo;
    }

    public void setAssetNo(String assetNo) {
        this.assetNo = assetNo;
    }

    public String getApplyNo() {
        return this.applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public String getReqBody() {
        return this.reqBody;
    }

    public void setReqBody(String reqBody) {
        this.reqBody = reqBody;
    }

    public String getRepBody() {
        return this.repBody;
    }

    public void setRepBody(String repBody) {
        this.repBody = repBody;
    }

    public String getApiCode() {
        return this.apiCode;
    }

    public void setApiCode(String apiCode) {
        this.apiCode = apiCode;
    }

    public String getPhase() {
        return this.phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public String getApprovalCode() {
        return this.approvalCode;
    }

    public void setApprovalCode(String approvalCode) {
        this.approvalCode = approvalCode;
    }

    public String getApprovalDesc() {
        return this.approvalDesc;
    }

    public void setApprovalDesc(String approvalDesc) {
        this.approvalDesc = approvalDesc;
    }

    public String getRemark() {
        return this.remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getSender() {
        return this.sender;
    }

    public void setSender(Integer sender) {
        this.sender = sender;
    }


    /**
     * 无参构造
     */
    public AssetBankTran() {

    }

    /**
     * 根据applyNo获取实体
     *
     * @param applyNo
     * @return
     */
    public static List<AssetBankTran> getByApplyNo(String applyNo,String phase) {
    	  String jpql = "SELECT bank FROM AssetBankTran AS bank WHERE bank.applyNo = ?1 AND bank.phase=?2 AND bank.deleted = false";
          List<Object> filter = new ArrayList<Object>(1) {{
              add(applyNo);
              add(phase);
          }};
          return  getRepository().createJpqlQuery(jpql).setParameters(filter).list();
    }
    
    /**
     * 获取待执行的交易信息
     * @return
     */
	public static List<AssetBankTran> getToBeExecute() {
		String jpql = "SELECT abt FROM AssetBankTran AS abt"
				+ " WHERE abt.approvalCode is not null"
				+ " and abt.approvalDesc is not null ";
		
		return getRepository().createJpqlQuery(jpql).list();
	}

    /**
     *
     * 根据申请编号返回交易list
     * @param applyNo
     * @return
     */
    public static List<AssetBankTran> getByApplyNo(String applyNo){
        String jpql = "SELECT bank FROM AssetBankTran AS bank WHERE bank.applyNo = ?1 AND bank.deleted = false";
        List<Object> filter = new ArrayList<Object>(1) {{
            add(applyNo);
        }};
        return  getQueryChannel().createJpqlQuery(jpql).setParameters(filter).list();

    }

    /**
     * 根据申请编号和阶段查询最新请求银行
     * @param applyNo
     * @param type
     * @return
     */
    public static AssetBankTran getByApplNoAndType(String applyNo,String type){
        StringBuilder sql = new StringBuilder("select bank from AssetBankTran bank where bank.applyNo = ?1 and bank.phase = ?2 and bank.deleted is false order by bank.createTime desc");
        List<Object> params = Lists.newArrayList();
        params.add(applyNo);
        params.add(type);
        AssetBankTran bankTran = getRepository().createJpqlQuery(sql.toString()).setParameters(params).singleResult();
        return bankTran;

    }
    
    /**
     * 根据交易编号 获取
     * @param tranNo
     * @param sender
     * @return
     */
    public static AssetBankTran getByTranNo(String tranNo,Integer sender){
        StringBuilder sql = new StringBuilder("select bank from AssetBankTran bank where bank.tranNo = ?1 and bank.sender = ?2 and bank.deleted is false order by bank.createTime desc");
        List<Object> params = Lists.newArrayList();
        params.add(tranNo);
        params.add(sender);
        AssetBankTran bankTran = getRepository().createJpqlQuery(sql.toString()).setParameters(params).singleResult();
        return bankTran;

    }

    /**
     * 查询给结算推数失败的数据
     * @return
     * @author YixinCapital -- chenjiacheng
     *             2018/8/20 16:03
     */
    public static List<String> getPushFileIsFailedData() {
        String sql = "SELECT DISTINCT tran.apply_no FROM k_asset_bank_tran tran " +
                " WHERE tran.is_deleted = 0 AND tran.approval_code IS NULL AND tran.phase = 'push_file' " +
                " and tran.apply_no not in( select t.apply_no from k_asset_bank_tran t " +
                " where t.phase = 'push_file' and t.approval_code='true' ) limit 10 ";
        return getRepository().createSqlQuery(sql).list();
    }
    
    
    /**
     * 查询给微众贷后提车失败的数据--- 临时方法-目的解决线上单子
     * @return 
     * @author YixinCapital -- wangwenlong
     *	       2018年9月28日 下午4:44:35
     */
    public static List<String> getWBAfterloanFailed() {
        String sql = "SELECT DISTINCT tran.apply_no FROM k_asset_bank_tran tran"
        		+ " WHERE tran.is_deleted = 0 "
        		+ " AND tran.approval_code LIKE '6651%' "
        		+ " AND tran.approval_code != '66510000' "
        		+ " AND tran.phase = 'after_loan' "
        		+ " AND tran.apply_no NOT IN "
        		+ " ( "
        		+ " 	SELECT t.apply_no FROM k_asset_bank_tran t "
        		+ "     WHERE t.approval_code LIKE '6651%' "
        		+ "     AND t.phase = 'after_loan'"
        		+ "  	AND t.approval_code = '66510000'"
        		+ " )"
        		+ "	ORDER BY tran.create_time DESC "
        		+ " LIMIT 50";
        return getRepository().createSqlQuery(sql).list();
    }

    /**
     * 查询资方最后一次审核的的记录
     *
     * @param applyNo
     * @return
     */
    public static AssetBankTran getLastBankAuditRecord(String applyNo) {

        StringBuilder sql = new StringBuilder("select bank from AssetBankTran bank where bank.applyNo = ?1 and bank.phase = 'last_trial' order by bank.createTime desc");
        List<Object> params = Lists.newArrayList();
        params.add(applyNo);

        AssetBankTran bankTran = getRepository().createJpqlQuery(sql.toString()).setParameters(params).singleResult();
        return bankTran;

    }
}