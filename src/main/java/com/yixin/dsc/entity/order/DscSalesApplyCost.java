package com.yixin.dsc.entity.order;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yixin.common.system.domain.BZBaseEntiy;

/**
 * 合同申请费用信息表
 * 融资信息
 * 
 * Package : com.yixin.dsc.entity
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月4日 下午5:42:31
 *
 */
@Entity
@Table(name = "dsc_sales_apply_cost")
public class DscSalesApplyCost extends BZBaseEntiy {
	
	private static final long serialVersionUID = -2816462346360117161L;


	/**
	 * 	是否融保险
	 */
	@Column(name = "asfrbx", columnDefinition = "varchar(64) comment '是否融保险'")
	private String asfrbx;
	
	
	/**
	 * 	AGDZLX	融资项固定类型
	 */
	@Column(name = "agdzlx", columnDefinition = "varchar(64) comment '融资项固定类型'")
	private String agdzlx;

	/**
	 * 	FRZE	客户融资额
	 */
	@Column(name = "frze", columnDefinition = "decimal(19,2) comment '客户融资额'")
	private BigDecimal frze;

	/**
	 * 	FMQKHZJ	每期租金
	 */
	@Column(name = "fmqkhzj", columnDefinition = "decimal(19,2) comment '每期租金'")
	private BigDecimal fmqkhzj;

	/**
	 * 	FSFBL	首付比例
	 */
	@Column(name = "fsfbl", columnDefinition = "decimal(19,2) comment '首付比例'")
	private BigDecimal fsfbl;

	/**
	 * 	FSFJE	首付金额
	 */
	@Column(name = "fsfje", columnDefinition = "decimal(19,2) comment '首付金额'")
	private BigDecimal fsfje;

	/**
	 * 	FBZJDSFJE	保证金抵首付金额
	 */
	@Column(name = "fbzjdsfje", columnDefinition = "decimal(19,2) comment '保证金抵首付金额'")
	private BigDecimal fbzjdsfje;

	/**
	 * 	FWFBL	尾付比例
	 */
	@Column(name = "fwfbl", columnDefinition = "decimal(19,2) comment '尾付比例'")
	private BigDecimal fwfbl;
	
	/**
	 * 	FSFJE	尾付金额
	 */
	@Column(name = "fwfje", columnDefinition = "decimal(19,2) comment '尾付金额'")
	private BigDecimal fwfje;

	/**
	 * 	ASXFKKFS	手续费收款方式
	 */
	@Column(name = "asxfkkfs", columnDefinition = "varchar(64) comment '手续费收款方式'")
	private String asxfkkfs;

	/**
	 * 	FSXF	手续费总额
	 */
	@Column(name = "fsxf", columnDefinition = "decimal(19,2) comment '手续费总额'")
	private BigDecimal fsxf;

	/**
	 * 	ABZJCDFS	保证金冲抵
	 */
	@Column(name = "abzjcdfs", columnDefinition = "varchar(64) comment '保证金冲抵'")
	private String abzjcdfs;

	/**
	 * 	FBZJCDQS	保证金冲抵期数
	 */
	@Column(name = "fbzjcdqs", columnDefinition = "decimal(19,2) comment '保证金冲抵期数'")
	private BigDecimal fbzjcdqs;

	/**
	 * 	FBZJL	保证金比例
	 */
	@Column(name = "fbzjl", columnDefinition = "decimal(19,2) comment '保证金比例'")
	private BigDecimal fbzjl;

	/**
	 * 	FBZJ	保证金
	 */
	@Column(name = "fbzj", columnDefinition = "decimal(19,2) comment '保证金'")
	private BigDecimal fbzj;

	/**
	 * 	ABZJGDZLX	保证金固定值类型
	 */
	@Column(name = "abzjgdzlx", columnDefinition = "varchar(64) comment '保证金固定值类型'")
	private String abzjgdzlx;

	/**
	 * 	FBZJ	保证金剩余金
	 */
	@Column(name = "fbzjsyje", columnDefinition = "decimal(19,2) comment '保证金剩余金'")
	private BigDecimal fbzjsyje;

	/**
	 * 	FKHLL	客户利率
	 */
	@Column(name = "fkhll", columnDefinition = "decimal(19,2) comment '客户利率'")
	private BigDecimal fkhll;

	/**
	 * 	FJSLL	结算利率
	 */
	@Column(name = "fjsll", columnDefinition = "decimal(19,2) comment '结算利率'")
	private BigDecimal fjsll;

	/**
	 * 	FSXFL	手续费率
	 */
	@Column(name = "fsxfl", columnDefinition = "decimal(19,2) comment '手续费率'")
	private BigDecimal fsxfl;

	/**
	 * 	FJSSXFL	结算手续费率
	 */
	@Column(name = "fjssxfl", columnDefinition = "decimal(19,2) comment '结算手续费率'")
	private BigDecimal fjssxfl;

	/**
	 * 	ATXFS	是否贴息  1:贴息  0：不贴息
	 */
	@Column(name = "atxfs", columnDefinition = "varchar(64) comment '是否贴息 '")
	private String atxfs;

	/**
	 * 	ATXGDZLX	贴息固定类型
	 */
	@Column(name = "atxgdzlx", columnDefinition = "varchar(64) comment '贴息固定类型'")
	private String atxgdzlx;

	/**
	 * 	FTXZE	贴息总金额
	 */
	@Column(name = "ftxze", columnDefinition = "decimal(19,2) comment '贴息总金额'")
	private BigDecimal ftxze;

	/**
	 * 	FBTSXF	贴手续费总金额
	 */
	@Column(name = "fbtsxf", columnDefinition = "decimal(19,2) comment '贴手续费总金额'")
	private BigDecimal fbtsxf;

	/**
	 * 	FCSTXZE	
	 * 厂家贴息金额
	 */
	@Column(name = "fcstxze", columnDefinition = "decimal(19,2) comment '厂家贴息金额'")
	private BigDecimal fcstxze;

	/**
	 * 	经销商贴息金额
	 */
	@Column(name = "fdlstxze", columnDefinition = "decimal(19,2) comment '经销商贴息金额'")
	private BigDecimal fdlstxze;	

	/**
	 * 	实际贴息金额
	 */
	@Column(name = "sjftxje", columnDefinition = "decimal(19,2) comment '实际贴息金额'")
	private BigDecimal sjftxje	;

	/**
	 * 	额外贴息金额	
	 */
	@Column(name = "extraftxje", columnDefinition = "decimal(19,2) comment '额外贴息金额'")
	private BigDecimal extraftxje;

	/**
	 * 	实际贴手续费金额
	 */
	@Column(name = "sjfbtsxf", columnDefinition = "decimal(19,2) comment '实际贴手续费金额'")
	private BigDecimal sjfbtsxf;

	/**
	 * 	额外贴手续费金额
	 */
	@Column(name = "extrafbtsxf", columnDefinition = "decimal(19,2) comment '额外贴手续费金额'")
	private BigDecimal extRafbtsxf ;	

	/**
	 * 	投资总额
	 */
	@Column(name = "ftzze", columnDefinition = "decimal(19,2) comment '投资总额'")
	private BigDecimal ftzze;

	/**
	 * 	风控融资额
	 */
	@Column(name = "ffxrze2", columnDefinition = "decimal(19,2) comment '风控融资额'")
	private BigDecimal ffxrze2;	

	/**
	 * 	风险融资额
	 */
	@Column(name = "ffxrze", columnDefinition = "decimal(19,2) comment '风险融资额'")
	private BigDecimal ffxrze;

	/**
	 * 订单主表ID
	 */
	@Column(name = "main_id", columnDefinition = "varchar(50) comment '订单主表ID'")
	private String mainId;

	/**
	 * 证件有效期起
	 */
	@Column(name = "azjyxqq", columnDefinition = "datetime comment '证件有效期起'")
	private Date azjyxqq;

	/**
	 * 证件有效期止
	 */
	@Column(name = "azjyxqz", columnDefinition = "datetime comment '证件有效期止'")
	private Date azjyxqz;
	/**
	 * 税前年收入(元)
	 */
	@Column(name = "asqnsr", columnDefinition = "decimal(19,2) comment '税前年收入(元)'")
	private BigDecimal asqnsr;

	/**
	 * 	初始融资额
	 */
	@Column(name = "yxfrze", columnDefinition = "decimal(19,2) comment '初始融资额'")
	private BigDecimal yxfrze;

	/**
	 * 	初始结算利率（%）
	 */
	@Column(name = "fcsll", columnDefinition = "decimal(19,2) comment '初始结算利率（%）'")
	private BigDecimal fcsll;

	/**
	 * 是否厂家贴息
	 */
	@Column(name = "cstx_flag", columnDefinition = "varchar(20) comment '是否厂家贴息'")
	private String cstxFlag;

	/**
	 * 是否经销商贴息
	 */
	@Column(name = "jxstx_flag", columnDefinition = "varchar(20) comment '是否经销商贴息'")
	private String jxstxFlag;

	// ==================================20180929-xjt-工行新增字段开始
	@Column(name = "fkhfl", columnDefinition = "decimal(19,2) comment '客户费率（%）'")
	private BigDecimal fkhfl;

	@Column(name = "fjsfl", columnDefinition = "decimal(19,2) comment '结算费率（%）'")
	private BigDecimal fjsfl;

	@Column(name = "fyhcbfl", columnDefinition = "decimal(19,2) comment '银行成本费率（%）'")
	private BigDecimal fyhcbfl;

	@Column(name = "fyxdbfl", columnDefinition = "decimal(19,2) comment '担保费率（%）'")
	private BigDecimal fyxdbfl;

	@Column(name = "fyxdbf", columnDefinition = "decimal(19,2) comment '担保费（%）'")
	private BigDecimal fyxdbf;
	// ==================================20180929-xjt-工行新增字段结束

	public String getCstxFlag() {
		return cstxFlag;
	}

	public void setCstxFlag(String cstxFlag) {
		this.cstxFlag = cstxFlag;
	}

	public String getJxstxFlag() {
		return jxstxFlag;
	}

	public void setJxstxFlag(String jxstxFlag) {
		this.jxstxFlag = jxstxFlag;
	}

	public String getMainId() {
		return mainId;
	}

	public void setMainId(String mainId) {
		this.mainId = mainId;
	}

	public String getAsfrbx() {
		return asfrbx;
	}

	public void setAsfrbx(String asfrbx) {
		this.asfrbx = asfrbx;
	}

	public String getAgdzlx() {
		return agdzlx;
	}

	public void setAgdzlx(String agdzlx) {
		this.agdzlx = agdzlx;
	}

	public BigDecimal getFrze() {
		return frze;
	}

	public void setFrze(BigDecimal frze) {
		this.frze = frze;
	}

	public BigDecimal getFmqkhzj() {
		return fmqkhzj;
	}

	public void setFmqkhzj(BigDecimal fmqkhzj) {
		this.fmqkhzj = fmqkhzj;
	}

	public BigDecimal getFsfbl() {
		return fsfbl;
	}

	public void setFsfbl(BigDecimal fsfbl) {
		this.fsfbl = fsfbl;
	}

	public BigDecimal getFsfje() {
		return fsfje;
	}

	public void setFsfje(BigDecimal fsfje) {
		this.fsfje = fsfje;
	}

	public BigDecimal getFbzjdsfje() {
		return fbzjdsfje;
	}

	public void setFbzjdsfje(BigDecimal fbzjdsfje) {
		this.fbzjdsfje = fbzjdsfje;
	}

	public BigDecimal getFwfbl() {
		return fwfbl;
	}

	public void setFwfbl(BigDecimal fwfbl) {
		this.fwfbl = fwfbl;
	}

	public String getAsxfkkfs() {
		return asxfkkfs;
	}

	public void setAsxfkkfs(String asxfkkfs) {
		this.asxfkkfs = asxfkkfs;
	}

	public BigDecimal getFsxf() {
		return fsxf;
	}

	public void setFsxf(BigDecimal fsxf) {
		this.fsxf = fsxf;
	}

	public String getAbzjcdfs() {
		return abzjcdfs;
	}

	public void setAbzjcdfs(String abzjcdfs) {
		this.abzjcdfs = abzjcdfs;
	}

	public BigDecimal getFbzjcdqs() {
		return fbzjcdqs;
	}

	public void setFbzjcdqs(BigDecimal fbzjcdqs) {
		this.fbzjcdqs = fbzjcdqs;
	}

	public BigDecimal getFbzjl() {
		return fbzjl;
	}

	public void setFbzjl(BigDecimal fbzjl) {
		this.fbzjl = fbzjl;
	}

	public BigDecimal getFbzj() {
		return fbzj;
	}

	public void setFbzj(BigDecimal fbzj) {
		this.fbzj = fbzj;
	}

	public String getAbzjgdzlx() {
		return abzjgdzlx;
	}

	public void setAbzjgdzlx(String abzjgdzlx) {
		this.abzjgdzlx = abzjgdzlx;
	}

	public BigDecimal getFkhll() {
		return fkhll;
	}

	public void setFkhll(BigDecimal fkhll) {
		this.fkhll = fkhll;
	}

	public BigDecimal getFjsll() {
		return fjsll;
	}

	public void setFjsll(BigDecimal fjsll) {
		this.fjsll = fjsll;
	}

	public BigDecimal getFsxfl() {
		return fsxfl;
	}

	public void setFsxfl(BigDecimal fsxfl) {
		this.fsxfl = fsxfl;
	}

	public BigDecimal getFjssxfl() {
		return fjssxfl;
	}

	public void setFjssxfl(BigDecimal fjssxfl) {
		this.fjssxfl = fjssxfl;
	}

	public String getAtxfs() {
		return atxfs;
	}

	public void setAtxfs(String atxfs) {
		this.atxfs = atxfs;
	}

	public String getAtxgdzlx() {
		return atxgdzlx;
	}

	public void setAtxgdzlx(String atxgdzlx) {
		this.atxgdzlx = atxgdzlx;
	}

	public BigDecimal getFtxze() {
		return ftxze;
	}

	public void setFtxze(BigDecimal ftxze) {
		this.ftxze = ftxze;
	}

	public BigDecimal getFbtsxf() {
		return fbtsxf;
	}

	public void setFbtsxf(BigDecimal fbtsxf) {
		this.fbtsxf = fbtsxf;
	}

	public BigDecimal getFcstxze() {
		return fcstxze;
	}

	public void setFcstxze(BigDecimal fcstxze) {
		this.fcstxze = fcstxze;
	}

	public BigDecimal getFdlstxze() {
		return fdlstxze;
	}

	public void setFdlstxze(BigDecimal fdlstxze) {
		this.fdlstxze = fdlstxze;
	}

	public BigDecimal getSjftxje() {
		return sjftxje;
	}

	public void setSjftxje(BigDecimal sjftxje) {
		this.sjftxje = sjftxje;
	}

	public BigDecimal getExtraftxje() {
		return extraftxje;
	}

	public void setExtraftxje(BigDecimal extraftxje) {
		this.extraftxje = extraftxje;
	}

	public BigDecimal getSjfbtsxf() {
		return sjfbtsxf;
	}

	public void setSjfbtsxf(BigDecimal sjfbtsxf) {
		this.sjfbtsxf = sjfbtsxf;
	}

	public BigDecimal getExtRafbtsxf() {
		return extRafbtsxf;
	}

	public void setExtRafbtsxf(BigDecimal extRafbtsxf) {
		this.extRafbtsxf = extRafbtsxf;
	}

	public BigDecimal getFtzze() {
		return ftzze;
	}

	public void setFtzze(BigDecimal ftzze) {
		this.ftzze = ftzze;
	}

	public BigDecimal getFfxrze2() {
		return ffxrze2;
	}

	public void setFfxrze2(BigDecimal ffxrze2) {
		this.ffxrze2 = ffxrze2;
	}

	public BigDecimal getFfxrze() {
		return ffxrze;
	}

	public void setFfxrze(BigDecimal ffxrze) {
		this.ffxrze = ffxrze;
	}

	public BigDecimal getFwfje() {
		return fwfje;
	}

	public void setFwfje(BigDecimal fwfje) {
		this.fwfje = fwfje;
	}

	public BigDecimal getFbzjsyje() {
		return fbzjsyje;
	}

	public void setFbzjsyje(BigDecimal fbzjsyje) {
		this.fbzjsyje = fbzjsyje;
	}

	public Date getAzjyxqq() {
		return azjyxqq;
	}

	public void setAzjyxqq(Date azjyxqq) {
		this.azjyxqq = azjyxqq;
	}

	public Date getAzjyxqz() {
		return azjyxqz;
	}

	public void setAzjyxqz(Date azjyxqz) {
		this.azjyxqz = azjyxqz;
	}

	public BigDecimal getAsqnsr() {
		return asqnsr;
	}

	public void setAsqnsr(BigDecimal asqnsr) {
		this.asqnsr = asqnsr;
	}

	public BigDecimal getYxfrze() {
		return yxfrze;
	}

	public void setYxfrze(BigDecimal yxfrze) {
		this.yxfrze = yxfrze;
	}

	public BigDecimal getFcsll() {
		return fcsll;
	}

	public void setFcsll(BigDecimal fcsll) {
		this.fcsll = fcsll;
	}

	public BigDecimal getFkhfl() {
		return fkhfl;
	}

	public void setFkhfl(BigDecimal fkhfl) {
		this.fkhfl = fkhfl;
	}

	public BigDecimal getFjsfl() {
		return fjsfl;
	}

	public void setFjsfl(BigDecimal fjsfl) {
		this.fjsfl = fjsfl;
	}

	public BigDecimal getFyhcbfl() {
		return fyhcbfl;
	}

	public void setFyhcbfl(BigDecimal fyhcbfl) {
		this.fyhcbfl = fyhcbfl;
	}

	public BigDecimal getFyxdbfl() {
		return fyxdbfl;
	}

	public void setFyxdbfl(BigDecimal fyxdbfl) {
		this.fyxdbfl = fyxdbfl;
	}

	public BigDecimal getFyxdbf() {
		return fyxdbf;
	}

	public void setFyxdbf(BigDecimal fyxdbf) {
		this.fyxdbf = fyxdbf;
	}

	/**
	 * 通过订单主表ID删除
	 * @param mainId
	 */
	public static void deleteBymainId(String mainId){
		List<DscSalesApplyCost> lis = DscSalesApplyCost.findByProperty(DscSalesApplyCost.class, "mainId", mainId);
		if(lis!=null && !lis.isEmpty()) {
			for (DscSalesApplyCost att : lis) {
				att.remove();
			}
		}
	}
	
	 public static DscSalesApplyCost getByMainId(String mainId) {

	        StringBuilder jpql = new StringBuilder("select dsac from DscSalesApplyCost dsac where dsac.mainId =?1");
	        List<Object> filter = new ArrayList<Object>() {{
	            add(mainId);
	        }};
	        return getRepository().createJpqlQuery(jpql.toString()).setParameters(filter).singleResult();
	 }
}
