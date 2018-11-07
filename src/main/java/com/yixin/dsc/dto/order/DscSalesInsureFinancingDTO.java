package com.yixin.dsc.dto.order;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * 合同申请保险类别明细
 * Package : com.yixin.dsc.entity
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月4日 下午2:15:38
 *
 */
public class DscSalesInsureFinancingDTO implements Serializable{
	
	private static final long serialVersionUID = 5088286407262241156L;


    private String id;

	/**
	 * 订单主表ID
	 */
	private String mainId;

	/**
	 * 保险信息表ID
	 */
	private String insureId;
	
	/**
	 * gmnf 选择年份
	 */
	private String gmnf;
	
	
	
	/**
	 * div_1_jqxTotalAmt_input	交强险第一年
	 */
	private BigDecimal div1JqxTotalAMtInput;
	/**
	 * div_2_jqxTotalAmt_input	交强险第二年
	 */
	private BigDecimal div2JqxTotalAMtInput;
	/**
	 * div_3_jqxTotalAmt_input	交强险第三年
	 */
	private BigDecimal div3JqxTotalAMtInput;
	/**
	 *  div_4_jqxTotalAmt_input	交强险第四年
	 */
	private BigDecimal div4JqxTotalAMtInput;
	/**
	 * div_5_jqxTotalAmt_input	交强险第五年
	 */
	private BigDecimal div5JqxTotalAMtInput;
	/**
	 * div_hj_jqxTotalAmt_input	交强险合计
	 */
	private BigDecimal divHjJqxTotalAmtInput;
		
	/**
	 * div_1_syxTotalAmt_input	商业险第一年
	 */
	private BigDecimal div1SyxTotalAMtInput ;
	/**
	 * div_2_syxTotalAmt_input 商业险第二年
	 */
	private BigDecimal div2SyxTotalAMtInput ;
	/**
	 * div_3_syxTotalAmt_input 商业险第三年
	 */
	private BigDecimal div3SyxTotalAMtInput ;
	/**
	 * div_4_syxTotalAmt_input	商业险第四年
	 */
	private BigDecimal div4SyxTotalAMtInput ;
	/**
	 * div_5_syxTotalAmt_input	商业险第五年
	 */
	private BigDecimal div5SyxTotalAMtInput ;
	/**
	 * div_hj_syxTotalAmt_input	商业险合计
	 */
	private BigDecimal divHjSyxTotalAmtInput ;
	
	
	
	
	/**
	 * div_1_ccsTotalAmt_input	车船税第一年
	 */
	private BigDecimal div1CcsTotalAMtInput;
	/**
	 * div_2_ccsTotalAmt_input	车船税第二年
	 */
	private BigDecimal div2CcsTotalAMtInput;
	/**
	 * div_3_ccsTotalAmt_input	车船税第三年
	 */
	private BigDecimal div3CcsTotalAMtInput;
	/**
	 * div_4_ccsTotalAmt_input	车船税第四年
	 */
	private BigDecimal div4CcsTotalAMtInput;
	/**
	 * div_5_ccsTotalAmt_input  车船税第五年
	 */
	private BigDecimal div5CcsTotalAMtInput;
	/**
	 * div_hj_ccsTotalAmt_input  车船税合计
	 */
	private BigDecimal divHjCcsTotalAmtInput;
	
	
	
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}

	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	public String getInsureId() {
		return insureId;
	}

	public void setInsureId(String insureId) {
		this.insureId = insureId;
	}

	public String getGmnf() {
		return gmnf;
	}
	public void setGmnf(String gmnf) {
		this.gmnf = gmnf;
	}
	public BigDecimal getDiv1JqxTotalAMtInput() {
		return div1JqxTotalAMtInput;
	}
	public void setDiv1JqxTotalAMtInput(BigDecimal div1JqxTotalAMtInput) {
		this.div1JqxTotalAMtInput = div1JqxTotalAMtInput;
	}
	public BigDecimal getDiv2JqxTotalAMtInput() {
		return div2JqxTotalAMtInput;
	}
	public void setDiv2JqxTotalAMtInput(BigDecimal div2JqxTotalAMtInput) {
		this.div2JqxTotalAMtInput = div2JqxTotalAMtInput;
	}
	public BigDecimal getDiv3JqxTotalAMtInput() {
		return div3JqxTotalAMtInput;
	}
	public void setDiv3JqxTotalAMtInput(BigDecimal div3JqxTotalAMtInput) {
		this.div3JqxTotalAMtInput = div3JqxTotalAMtInput;
	}
	public BigDecimal getDiv4JqxTotalAMtInput() {
		return div4JqxTotalAMtInput;
	}
	public void setDiv4JqxTotalAMtInput(BigDecimal div4JqxTotalAMtInput) {
		this.div4JqxTotalAMtInput = div4JqxTotalAMtInput;
	}
	public BigDecimal getDiv5JqxTotalAMtInput() {
		return div5JqxTotalAMtInput;
	}
	public void setDiv5JqxTotalAMtInput(BigDecimal div5JqxTotalAMtInput) {
		this.div5JqxTotalAMtInput = div5JqxTotalAMtInput;
	}
	public BigDecimal getDivHjJqxTotalAmtInput() {
		return divHjJqxTotalAmtInput;
	}
	public void setDivHjJqxTotalAmtInput(BigDecimal divHjJqxTotalAmtInput) {
		this.divHjJqxTotalAmtInput = divHjJqxTotalAmtInput;
	}
	public BigDecimal getDiv1SyxTotalAMtInput() {
		return div1SyxTotalAMtInput;
	}
	public void setDiv1SyxTotalAMtInput(BigDecimal div1SyxTotalAMtInput) {
		this.div1SyxTotalAMtInput = div1SyxTotalAMtInput;
	}
	public BigDecimal getDiv2SyxTotalAMtInput() {
		return div2SyxTotalAMtInput;
	}
	public void setDiv2SyxTotalAMtInput(BigDecimal div2SyxTotalAMtInput) {
		this.div2SyxTotalAMtInput = div2SyxTotalAMtInput;
	}
	public BigDecimal getDiv3SyxTotalAMtInput() {
		return div3SyxTotalAMtInput;
	}
	public void setDiv3SyxTotalAMtInput(BigDecimal div3SyxTotalAMtInput) {
		this.div3SyxTotalAMtInput = div3SyxTotalAMtInput;
	}
	public BigDecimal getDiv4SyxTotalAMtInput() {
		return div4SyxTotalAMtInput;
	}
	public void setDiv4SyxTotalAMtInput(BigDecimal div4SyxTotalAMtInput) {
		this.div4SyxTotalAMtInput = div4SyxTotalAMtInput;
	}
	public BigDecimal getDiv5SyxTotalAMtInput() {
		return div5SyxTotalAMtInput;
	}
	public void setDiv5SyxTotalAMtInput(BigDecimal div5SyxTotalAMtInput) {
		this.div5SyxTotalAMtInput = div5SyxTotalAMtInput;
	}
	public BigDecimal getDivHjSyxTotalAmtInput() {
		return divHjSyxTotalAmtInput;
	}
	public void setDivHjSyxTotalAmtInput(BigDecimal divHjSyxTotalAmtInput) {
		this.divHjSyxTotalAmtInput = divHjSyxTotalAmtInput;
	}
	public BigDecimal getDiv1CcsTotalAMtInput() {
		return div1CcsTotalAMtInput;
	}
	public void setDiv1CcsTotalAMtInput(BigDecimal div1CcsTotalAMtInput) {
		this.div1CcsTotalAMtInput = div1CcsTotalAMtInput;
	}
	public BigDecimal getDiv2CcsTotalAMtInput() {
		return div2CcsTotalAMtInput;
	}
	public void setDiv2CcsTotalAMtInput(BigDecimal div2CcsTotalAMtInput) {
		this.div2CcsTotalAMtInput = div2CcsTotalAMtInput;
	}
	public BigDecimal getDiv3CcsTotalAMtInput() {
		return div3CcsTotalAMtInput;
	}
	public void setDiv3CcsTotalAMtInput(BigDecimal div3CcsTotalAMtInput) {
		this.div3CcsTotalAMtInput = div3CcsTotalAMtInput;
	}
	public BigDecimal getDiv4CcsTotalAMtInput() {
		return div4CcsTotalAMtInput;
	}
	public void setDiv4CcsTotalAMtInput(BigDecimal div4CcsTotalAMtInput) {
		this.div4CcsTotalAMtInput = div4CcsTotalAMtInput;
	}
	public BigDecimal getDiv5CcsTotalAMtInput() {
		return div5CcsTotalAMtInput;
	}
	public void setDiv5CcsTotalAMtInput(BigDecimal div5CcsTotalAMtInput) {
		this.div5CcsTotalAMtInput = div5CcsTotalAMtInput;
	}
	public BigDecimal getDivHjCcsTotalAmtInput() {
		return divHjCcsTotalAmtInput;
	}
	public void setDivHjCcsTotalAmtInput(BigDecimal divHjCcsTotalAmtInput) {
		this.divHjCcsTotalAmtInput = divHjCcsTotalAmtInput;
	}


}
