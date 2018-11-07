package com.yixin.kepler.enity;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.common.system.util.Label;

/**
 * <B>功能简述</B><br>
 * KAssetFinanceInfo实体类
 *
 * @author liuhongshen
 * @date 2018年06月06日 14:14:54
 */
@Entity
@Table(name = "k_asset_finance_info")
public class AssetFinanceInfo extends BZBaseEntiy{

	private static final long serialVersionUID = 5807404297511615555L;
	
	/**
     * 地址
     */
    @Label(name = "地址")
    @Column(name = "address", length = 255)
    private String address;
    /**
     * 联系人、联系方式
     */
    @Label(name = "联系人、联系方式")
    @Column(name = "mobile", length = 64)
    private String mobile;
    /**
     * 1放款账户，2结算账户，3保证金账户，4代偿账户
     */
    @Label(name = "1放款账户，2结算账户，3保证金账户，4代偿账户")
    @Column(name = "account_type", length = 64)
    private String accountType;
    /**
     * 合作协议号
     */
    @Label(name = "合作协议号")
    @Column(name = "pact_num", length = 64)
    private String pactNum;
    /**
     * 合作周期（开始日期）
     */
    @Label(name = "合作周期（开始日期）")
    @Column(name = "start_time", length = 19)
    private Date startTime;
    /**
     * 合作周期（结束日期）
     */
    @Label(name = "合作周期（结束日期）")
    @Column(name = "end_time", length = 19)
    private Date endTime;
    /**
     * 资金成本
     */
    @Label(name = "资金成本")
    @Column(name = "capital_cost", columnDefinition = "decimal(19,2) comment '资金成本'")
    private BigDecimal capitalCost;


    /**
     * 资金成本-新车  微众银行新车二手车不一样
     */
    @Label(name = "资金成本-新车")
    @Column(name = "capital_cost_new_car", columnDefinition = "decimal(19,2) comment '资金成本-新车'")
    private BigDecimal capitalCostNewCar;

    /**
     * 资金成本-二手车  微众银行新车二手车不一样
     */
    @Label(name = "资金成本-新车")
    @Column(name = "capital_cost_used_car", columnDefinition = "decimal(19,2) comment '资金成本-二手车'")
    private BigDecimal capitalCostUsedCar;


    
    /**
	 * 浮动利率/费率
	 */
	@Column(name = "floating_rate", columnDefinition = "decimal(19,2) comment '浮动利率/费率'")
	private BigDecimal floatingRate;
	
    /**
     * 付款类型：1垫付，2代付
     */
    @Label(name = "付款类型：1垫付，2代付")
    @Column(name = "pay_type", length = 16)
    private String payType;
    /**
     * 征信签约方式：1电子，2纸质
     */
    @Label(name = "征信签约方式：1电子，2纸质")
    @Column(name = "credit_type", length = 16)
    private String creditType;
    /**
     * 合同签约方式：1电子，2纸质
     */
    @Label(name = "合同签约方式：1电子，2纸质")
    @Column(name = "contract_type", length = 16)
    private String contractType;
    /**
     * 保证金比例
     */
    @Label(name = "保证金比例")
    @Column(name = "deposit_rate", columnDefinition = "decimal(21,4)")
    private BigDecimal depositRate;
    /**
     * 保证金类型：1全额，2在贷款
     */
    @Label(name = "保证金类型：1全额，2在贷款")
    @Column(name = "deposit_type", length = 16)
    private String depositType;
    /**
     * 业务类型：1消费贷款，2车抵贷
     */
    @Label(name = "业务类型：1消费贷款，2车抵贷")
    @Column(name = "busi_type", length = 16)
    private String busiType;
    /**
     * 融资模式：1贷款，2信用卡
     */
    @Label(name = "融资模式：1贷款，2信用卡")
    @Column(name = "finance_pattern", length = 16)
    private String financePattern;
    /**
     * 业务范围：1新车，2二手车
     */
    @Label(name = "业务范围：1新车，2二手车")
    @Column(name = "busi_range", length = 64)
    private String busiRange;
    /**
     * 是否贴息
     */
    @Label(name = "是否贴息")
    @Column(name = "is_discount", length = 1)
    private Integer isDiscount;
    /**
     * 是否超融
     */
    @Label(name = "是否超融")
    @Column(name = "is_exceed_finance", length = 1)
    private Integer isExceedFinance;
    /**
     * 超融范围
     */
    @Label(name = "超融范围")
    @Column(name = "exceed_finance_range", length = 64)
    private String exceedFinanceRange;
    /**
     * 超融项目公式
     */
    @Label(name = "超融项目公式")
    @Column(name = "exceed_finance_formula", length = 64)
    private String exceedFinanceFormula;
    /**
     * 资方code
     */
    @Label(name = "资方code")
    @Column(name = "code", length = 64)
    private String code;
    
    
    /**
     * 资方名称
     */
    @Label(name = "资方名称")
    @Column(name = "name", length = 64)
    private String name;
    
    
    /**
     * 分润模式:一次性、固定周期
     */
    @Label(name = "分润模式:一次性、固定周期")
    @Column(name = "profit_pattern", length = 64)
    private String profitPattern;
    /**
     * 分润周期：固定周期，配置月、季
     */
    @Label(name = "分润周期：固定周期，配置月、季")
    @Column(name = "profit_period", length = 64)
    private String profitPeriod;



    public BigDecimal getFloatingRate() {
		return floatingRate;
	}

	public void setFloatingRate(BigDecimal floatingRate) {
		this.floatingRate = floatingRate;
	}

	public void setCapitalCost(BigDecimal capitalCost) {
		this.capitalCost = capitalCost;
	}

	public String getAddress() {
        return this.address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getMobile() {
        return this.mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getAccountType() {
        return this.accountType;
    }

    public BigDecimal getCapitalCost() {
		return capitalCost;
	}

	public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    public String getPactNum() {
        return this.pactNum;
    }

    public void setPactNum(String pactNum) {
        this.pactNum = pactNum;
    }

    public Date getStartTime() {
        return this.startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return this.endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    public String getPayType() {
        return this.payType;
    }

    public void setPayType(String payType) {
        this.payType = payType;
    }

    public String getCreditType() {
        return this.creditType;
    }

    public void setCreditType(String creditType) {
        this.creditType = creditType;
    }

    public String getContractType() {
        return this.contractType;
    }

    public void setContractType(String contractType) {
        this.contractType = contractType;
    }

    public BigDecimal getDepositRate() {
        return this.depositRate;
    }

    public void setDepositRate(BigDecimal depositRate) {
        this.depositRate = depositRate;
    }

    public String getDepositType() {
        return this.depositType;
    }

    public void setDepositType(String depositType) {
        this.depositType = depositType;
    }

    public String getBusiType() {
        return this.busiType;
    }

    public void setBusiType(String busiType) {
        this.busiType = busiType;
    }

    public String getFinancePattern() {
        return this.financePattern;
    }

    public void setFinancePattern(String financePattern) {
        this.financePattern = financePattern;
    }

    public String getBusiRange() {
        return this.busiRange;
    }

    public void setBusiRange(String busiRange) {
        this.busiRange = busiRange;
    }

    public Integer getIsDiscount() {
        return this.isDiscount;
    }

    public void setIsDiscount(Integer isDiscount) {
        this.isDiscount = isDiscount;
    }

    public Integer getIsExceedFinance() {
        return this.isExceedFinance;
    }

    public void setIsExceedFinance(Integer isExceedFinance) {
        this.isExceedFinance = isExceedFinance;
    }

    public String getExceedFinanceRange() {
        return this.exceedFinanceRange;
    }

    public void setExceedFinanceRange(String exceedFinanceRange) {
        this.exceedFinanceRange = exceedFinanceRange;
    }

    public String getExceedFinanceFormula() {
        return this.exceedFinanceFormula;
    }

    public void setExceedFinanceFormula(String exceedFinanceFormula) {
        this.exceedFinanceFormula = exceedFinanceFormula;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getProfitPattern() {
        return this.profitPattern;
    }

    public void setProfitPattern(String profitPattern) {
        this.profitPattern = profitPattern;
    }

    public String getProfitPeriod() {
        return this.profitPeriod;
    }

    public void setProfitPeriod(String profitPeriod) {
        this.profitPeriod = profitPeriod;
    }

    public BigDecimal getCapitalCostNewCar() {
        return capitalCostNewCar;
    }

    public void setCapitalCostNewCar(BigDecimal capitalCostNewCar) {
        this.capitalCostNewCar = capitalCostNewCar;
    }

    public BigDecimal getCapitalCostUsedCar() {
        return capitalCostUsedCar;
    }

    public void setCapitalCostUsedCar(BigDecimal capitalCostUsedCar) {
        this.capitalCostUsedCar = capitalCostUsedCar;
    }

    
    public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	/**
     * 无参构造
     */
    public AssetFinanceInfo() {

    }

    public static AssetFinanceInfo getByCode(String code){
    	
        String jpql = "SELECT afi FROM AssetFinanceInfo AS afi WHERE afi.code = ?1 and afi.deleted =?2";

        List<Object> filter = new ArrayList<Object>() {{
            add(code);
            add(false);
        }};
        return getRepository().createJpqlQuery(jpql).setParameters(filter)
                .singleResult();
    }

    /**
     * 判断资方是否存在
     */
    public static boolean isExitConditionId(String id){
    	AssetFinanceInfo assetFinanceInfo = new AssetFinanceInfo();
    	assetFinanceInfo.setId(id);
    	return  assetFinanceInfo.existed();
    }

    public static AssetFinanceInfo getById(String id) {
        String jpql = "SELECT afi FROM AssetFinanceInfo AS afi WHERE afi.id = ?1";

        List<Object> filter = new ArrayList<Object>() {{
            add(id);
        }};
        return getRepository().createJpqlQuery(jpql).setParameters(filter)
                .singleResult();

    }
    
    
    /**
     * 根据主键ID获取资方名称
     * @param id 主键ID
     * @return 
     * @author YixinCapital -- wangwenlong
     *	       2018年10月15日 下午1:58:44
     */
    public static String getFinanceNameById(String id){
    	AssetFinanceInfo financeInfo = AssetFinanceInfo.get(AssetFinanceInfo.class, id);
    	return financeInfo == null?"":financeInfo.getName();
    }
    
    
    /**
     * 根据主键ID获取资方CODE
     * @param id 主键ID
     * @return 
     * @author YixinCapital -- wangwenlong
     *	       2018年10月15日 下午1:58:44
     */
    public static String getFinanceCodeById(String id){
    	AssetFinanceInfo financeInfo = AssetFinanceInfo.get(AssetFinanceInfo.class, id);
    	return financeInfo == null?null:financeInfo.getCode();
    }
} 