package com.yixin.dsc.entity.order;

import java.math.BigDecimal;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yixin.common.system.domain.BZBaseEntiy;

/**
 * 合同申请保险类别明细
 * Package : com.yixin.dsc.entity
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月4日 下午2:15:38
 *
 */
@Entity
@Table(name = "dsc_sales_insure_financing")
public class DscSalesInsureFinancing extends BZBaseEntiy {
	
	private static final long serialVersionUID = -1210400886704222974L;

	/**
	 * 订单主表ID
	 */
	@Column(name = "main_id", columnDefinition = "varchar(50) comment '订单主表ID'")
	private String mainId;

	/**
	 * 保险信息表ID
	 */
	@Column(name = "insure_id", columnDefinition = "varchar(50) comment '保险信息表ID'")
	private String insureId;



	/**
	 * gmnf 选择年份
	 */
	@Column(name = "gmnf", columnDefinition = "varchar(64) comment '选择年份'")
	private String gmnf;
	
	
	
	/**
	 * div_1_jpx_total_amt_input	交强险第一年
	 */
	@Column(name = "div_1_jqx_total_amt_input", columnDefinition = "decimal(19,2) comment '交强险第一年'")
	private BigDecimal div1JqxTotalAMtInput;
	/**
	 * div_2_jpx_total_amt_input	交强险第二年
	 */
	@Column(name = "div_2_jpx_total_amt_input", columnDefinition = "decimal(19,2) comment '交强险第二年'")
	private BigDecimal div2JqxTotalAMtInput;
	/**
	 * div_3_jpx_total_amt_input	交强险第三年
	 */
	@Column(name = "div_3_jpx_total_amt_input", columnDefinition = "decimal(19,2) comment '	交强险第三年'")
	private BigDecimal div3JqxTotalAMtInput;
	/**
	 *  div_4_jpx_total_amt_input	交强险第四年
	 */
	@Column(name = "div_4_jpx_total_amt_input", columnDefinition = "decimal(19,2) comment '交强险第四年'")
	private BigDecimal div4JqxTotalAMtInput;
	/**
	 * div_5_jpx_total_amt_input	交强险第五年
	 */
	@Column(name = "div_5_jpx_total_amt_input", columnDefinition = "decimal(19,2) comment '交强险第五年'")
	private BigDecimal div5JqxTotalAMtInput;
	/**
	 * div_hj_jpx_total_amt_input	交强险合计
	 */
	@Column(name = "div_hj_jpx_total_amt_input", columnDefinition = "decimal(19,2) comment '交强险合计'")
	private BigDecimal divHjJqxTotalAmtInput;
		
	/**
	 * div_1_syx_total_amt_input	商业险第一年
	 */
	@Column(name = "div_1_syx_total_amt_input", columnDefinition = "decimal(19,2) comment '商业险第一年'")
	private BigDecimal div1syx_total_amtInput ;
	/**
	 * div_2_syx_total_amt_input 商业险第二年
	 */
	@Column(name = "div_2_syx_total_amt_input", columnDefinition = "decimal(19,2) comment '商业险第二年'")
	private BigDecimal div2syx_total_amtInput ;
	/**
	 * div_3_syx_total_amt_input 商业险第三年
	 */
	@Column(name = "div_3_syx_total_amt_input", columnDefinition = "decimal(19,2) comment '商业险第三年'")
	private BigDecimal div3syx_total_amtInput ;
	/**
	 * div_4_syx_total_amt_input	商业险第四年
	 */
	@Column(name = "div_4_syx_total_amt_input", columnDefinition = "decimal(19,2) comment '商业险第四年'")
	private BigDecimal div4syx_total_amtInput ;
	/**
	 * div_5_syx_total_amt_input	商业险第五年
	 */
	@Column(name = "div_5_syx_total_amt_input", columnDefinition = "decimal(19,2) comment '商业险第五年'")
	private BigDecimal div5syx_total_amtInput ;
	/**
	 * div_hj_syx_total_amt_input	商业险合计
	 */
	@Column(name = "", columnDefinition = "decimal(19,2) comment ''")
	private BigDecimal divHjsyx_total_amtInput ;
	
	
	
	
	/**
	 * div_1_ccs_total_amt_input	车船税第一年
	 */
	@Column(name = "div_1_ccs_total_amt_input", columnDefinition = "decimal(19,2) comment '车船税第一年'")
	private BigDecimal div1ccs_total_amtInput;
	/**
	 * div_2_ccs_total_amt_input	车船税第二年
	 */
	@Column(name = "div_2_ccs_total_amt_input", columnDefinition = "decimal(19,2) comment '车船税第二年'")
	private BigDecimal div2ccs_total_amtInput;
	/**
	 * div_3_ccs_total_amt_input	车船税第三年
	 */
	@Column(name = "div_3_ccs_total_amt_input", columnDefinition = "decimal(19,2) comment '车船税第三年'")
	private BigDecimal div3ccs_total_amtInput;
	/**
	 * div_4_ccs_total_amt_input	车船税第四年
	 */
	@Column(name = "div_4_ccs_total_amt_input", columnDefinition = "decimal(19,2) comment '车船税第四年'")
	private BigDecimal div4ccs_total_amtInput;
	/**
	 * div_5_ccs_total_amt_input  车船税第五年
	 */
	@Column(name = "div_5_ccs_total_amt_input", columnDefinition = "decimal(19,2) comment '车船税第五年'")
	private BigDecimal div5ccs_total_amtInput;
	/**
	 * div_hj_ccs_total_amt_input  车船税合计
	 */
	@Column(name = "div_hj_ccs_total_amt_input", columnDefinition = "decimal(19,2) comment '车船税合计'")
	private BigDecimal divHjccs_total_amtInput;


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
	public BigDecimal getDiv1syx_total_amtInput() {
		return div1syx_total_amtInput;
	}
	public void setDiv1syx_total_amtInput(BigDecimal div1syx_total_amtInput) {
		this.div1syx_total_amtInput = div1syx_total_amtInput;
	}
	public BigDecimal getDiv2syx_total_amtInput() {
		return div2syx_total_amtInput;
	}
	public void setDiv2syx_total_amtInput(BigDecimal div2syx_total_amtInput) {
		this.div2syx_total_amtInput = div2syx_total_amtInput;
	}
	public BigDecimal getDiv3syx_total_amtInput() {
		return div3syx_total_amtInput;
	}
	public void setDiv3syx_total_amtInput(BigDecimal div3syx_total_amtInput) {
		this.div3syx_total_amtInput = div3syx_total_amtInput;
	}
	public BigDecimal getDiv4syx_total_amtInput() {
		return div4syx_total_amtInput;
	}
	public void setDiv4syx_total_amtInput(BigDecimal div4syx_total_amtInput) {
		this.div4syx_total_amtInput = div4syx_total_amtInput;
	}
	public BigDecimal getDiv5syx_total_amtInput() {
		return div5syx_total_amtInput;
	}
	public void setDiv5syx_total_amtInput(BigDecimal div5syx_total_amtInput) {
		this.div5syx_total_amtInput = div5syx_total_amtInput;
	}
	public BigDecimal getDivHjsyx_total_amtInput() {
		return divHjsyx_total_amtInput;
	}
	public void setDivHjsyx_total_amtInput(BigDecimal divHjsyx_total_amtInput) {
		this.divHjsyx_total_amtInput = divHjsyx_total_amtInput;
	}
	public BigDecimal getDiv1ccs_total_amtInput() {
		return div1ccs_total_amtInput;
	}
	public void setDiv1ccs_total_amtInput(BigDecimal div1ccs_total_amtInput) {
		this.div1ccs_total_amtInput = div1ccs_total_amtInput;
	}
	public BigDecimal getDiv2ccs_total_amtInput() {
		return div2ccs_total_amtInput;
	}
	public void setDiv2ccs_total_amtInput(BigDecimal div2ccs_total_amtInput) {
		this.div2ccs_total_amtInput = div2ccs_total_amtInput;
	}
	public BigDecimal getDiv3ccs_total_amtInput() {
		return div3ccs_total_amtInput;
	}
	public void setDiv3ccs_total_amtInput(BigDecimal div3ccs_total_amtInput) {
		this.div3ccs_total_amtInput = div3ccs_total_amtInput;
	}
	public BigDecimal getDiv4ccs_total_amtInput() {
		return div4ccs_total_amtInput;
	}
	public void setDiv4ccs_total_amtInput(BigDecimal div4ccs_total_amtInput) {
		this.div4ccs_total_amtInput = div4ccs_total_amtInput;
	}
	public BigDecimal getDiv5ccs_total_amtInput() {
		return div5ccs_total_amtInput;
	}
	public void setDiv5ccs_total_amtInput(BigDecimal div5ccs_total_amtInput) {
		this.div5ccs_total_amtInput = div5ccs_total_amtInput;
	}
	public BigDecimal getDivHjccs_total_amtInput() {
		return divHjccs_total_amtInput;
	}
	public void setDivHjccs_total_amtInput(BigDecimal divHjccs_total_amtInput) {
		this.divHjccs_total_amtInput = divHjccs_total_amtInput;
	}


	/**
	 * 通过订单主表ID删除
	 * @param mainId
	 */
	public static void deleteBymainId(String mainId){
		List<DscSalesInsureFinancing> lis = DscSalesInsureFinancing.findByProperty(DscSalesInsureFinancing.class, "mainId", mainId);
		if(lis!=null && !lis.isEmpty()) {
			for (DscSalesInsureFinancing att : lis) {
				att.remove();
			}
		}
	}
}
