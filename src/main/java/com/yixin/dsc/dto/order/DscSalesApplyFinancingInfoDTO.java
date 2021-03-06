package com.yixin.dsc.dto.order;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * 融资明细信息
 * 
 * Package : com.yixin.dsc.entity
 * 
 * @author YixinCapital -- wangxulong
 *		   2018年6月4日 下午5:42:31
 *
 */
public class DscSalesApplyFinancingInfoDTO implements Serializable{

	private static final long serialVersionUID = -2745579954227515598L;
	
	/**
	 * F010：车款
	 * F011：平台管理费
	 * F012：加装费
	 * F013：易鑫总服务费
	 * F014：责信保正常类型
	 * F015：责信保二档类型
	 * F016：责信保一档类型
	 * F017：责信保三档类型
	 * F020：加装
	 * F030：延保
	 * F040：服务费
	 * F050：购置税
	 * F060：GPS费用
	 * F070：GPS服务费
	 * F080：3G智能屏（GPS云服务）
	 * F091：交强险第一年
	 * F092：交强险第二年
	 * F093：交强险第三年
	 * F094：交强险第四年
	 * F095：交强险第五年
	 * F099：后服务
	 * F101：商业险第一年
	 * F102：商业险第二年
	 * F103：商业险第三年
	 * F104：商业险第四年
	 * F105：商业险第五年
	 * F111：无忧盗抢险第一年
	 * F112：无忧盗抢险第二年
	 * F113：无忧盗抢险第三年
	 * F114：无忧盗抢险第四年
	 * F115：无忧盗抢险第五年
	 * F117：责信保
	 * F118：易鑫信息服务费 add by wangwenlong on 2018-09-05
	 * F121：车船税第一年
	 * F122：车船税第二年
	 * F123：车船税第三年
	 * F124：车船税第四年
	 * F125：车船税第五年
	 * F130：车牌
	 * F140：延保费  -- add by wangwenlong on 2018-08-11
     * F0601：GPS基础价
     * F0602：GPS加价
     * F119：盗抢责任险
     * F066:GPS升级项目
	 */
	private BigDecimal f010;
	private BigDecimal f011;
	private BigDecimal f012;
	private BigDecimal f013;
	private BigDecimal f014;
	private BigDecimal f015;
	private BigDecimal f016;
	private BigDecimal f017;
	private BigDecimal f020;
	private BigDecimal f030;
	private BigDecimal f040;
	private BigDecimal f050;
	private BigDecimal f060;
	private BigDecimal f070;
	private BigDecimal f080;
	private BigDecimal f091;
	private BigDecimal f092;
	private BigDecimal f093;
	private BigDecimal f094;
	private BigDecimal f095;
	private BigDecimal f099;
	private BigDecimal f101;
	private BigDecimal f102;
	private BigDecimal f103;
	private BigDecimal f104;
	private BigDecimal f105;
	private BigDecimal f111;
	private BigDecimal f112;
	private BigDecimal f113;
	private BigDecimal f114;
	private BigDecimal f115;
	private BigDecimal f117;
	private BigDecimal f118;
	private BigDecimal f121;
	private BigDecimal f122;
	private BigDecimal f123;
	private BigDecimal f124;
	private BigDecimal f125;
	private BigDecimal f130;
	private BigDecimal f140;
	private BigDecimal F0601;
	private BigDecimal F0602;
	private BigDecimal F119;
	private BigDecimal f066;
	
	public BigDecimal getF118() {
		return f118;
	}
	public void setF118(BigDecimal f118) {
		this.f118 = f118;
	}
	public BigDecimal getF140() {
		return f140;
	}
	public void setF140(BigDecimal f140) {
		this.f140 = f140;
	}
	public BigDecimal getF010() {
		return f010;
	}
	public void setF010(BigDecimal f010) {
		this.f010 = f010;
	}
	public BigDecimal getF011() {
		return f011;
	}
	public void setF011(BigDecimal f011) {
		this.f011 = f011;
	}
	public BigDecimal getF012() {
		return f012;
	}
	public void setF012(BigDecimal f012) {
		this.f012 = f012;
	}
	public BigDecimal getF013() {
		return f013;
	}
	public void setF013(BigDecimal f013) {
		this.f013 = f013;
	}
	public BigDecimal getF014() {
		return f014;
	}
	public void setF014(BigDecimal f014) {
		this.f014 = f014;
	}
	public BigDecimal getF015() {
		return f015;
	}
	public void setF015(BigDecimal f015) {
		this.f015 = f015;
	}
	public BigDecimal getF016() {
		return f016;
	}
	public void setF016(BigDecimal f016) {
		this.f016 = f016;
	}
	public BigDecimal getF017() {
		return f017;
	}
	public void setF017(BigDecimal f017) {
		this.f017 = f017;
	}
	public BigDecimal getF020() {
		return f020;
	}
	public void setF020(BigDecimal f020) {
		this.f020 = f020;
	}
	public BigDecimal getF030() {
		return f030;
	}
	public void setF030(BigDecimal f030) {
		this.f030 = f030;
	}
	public BigDecimal getF040() {
		return f040;
	}
	public void setF040(BigDecimal f040) {
		this.f040 = f040;
	}
	public BigDecimal getF050() {
		return f050;
	}
	public void setF050(BigDecimal f050) {
		this.f050 = f050;
	}
	public BigDecimal getF060() {
		return f060;
	}
	public void setF060(BigDecimal f060) {
		this.f060 = f060;
	}
	public BigDecimal getF070() {
		return f070;
	}
	public void setF070(BigDecimal f070) {
		this.f070 = f070;
	}
	public BigDecimal getF080() {
		return f080;
	}
	public void setF080(BigDecimal f080) {
		this.f080 = f080;
	}
	public BigDecimal getF091() {
		return f091;
	}
	public void setF091(BigDecimal f091) {
		this.f091 = f091;
	}
	public BigDecimal getF092() {
		return f092;
	}
	public void setF092(BigDecimal f092) {
		this.f092 = f092;
	}
	public BigDecimal getF093() {
		return f093;
	}
	public void setF093(BigDecimal f093) {
		this.f093 = f093;
	}
	public BigDecimal getF094() {
		return f094;
	}
	public void setF094(BigDecimal f094) {
		this.f094 = f094;
	}
	public BigDecimal getF095() {
		return f095;
	}
	public void setF095(BigDecimal f095) {
		this.f095 = f095;
	}
	public BigDecimal getF099() {
		return f099;
	}
	public void setF099(BigDecimal f099) {
		this.f099 = f099;
	}
	public BigDecimal getF101() {
		return f101;
	}
	public void setF101(BigDecimal f101) {
		this.f101 = f101;
	}
	public BigDecimal getF102() {
		return f102;
	}
	public void setF102(BigDecimal f102) {
		this.f102 = f102;
	}
	public BigDecimal getF103() {
		return f103;
	}
	public void setF103(BigDecimal f103) {
		this.f103 = f103;
	}
	public BigDecimal getF104() {
		return f104;
	}
	public void setF104(BigDecimal f104) {
		this.f104 = f104;
	}
	public BigDecimal getF105() {
		return f105;
	}
	public void setF105(BigDecimal f105) {
		this.f105 = f105;
	}
	public BigDecimal getF111() {
		return f111;
	}
	public void setF111(BigDecimal f111) {
		this.f111 = f111;
	}
	public BigDecimal getF112() {
		return f112;
	}
	public void setF112(BigDecimal f112) {
		this.f112 = f112;
	}
	public BigDecimal getF113() {
		return f113;
	}
	public void setF113(BigDecimal f113) {
		this.f113 = f113;
	}
	public BigDecimal getF114() {
		return f114;
	}
	public void setF114(BigDecimal f114) {
		this.f114 = f114;
	}
	public BigDecimal getF115() {
		return f115;
	}
	public void setF115(BigDecimal f115) {
		this.f115 = f115;
	}
	public BigDecimal getF121() {
		return f121;
	}
	public void setF121(BigDecimal f121) {
		this.f121 = f121;
	}
	public BigDecimal getF122() {
		return f122;
	}
	public void setF122(BigDecimal f122) {
		this.f122 = f122;
	}
	public BigDecimal getF123() {
		return f123;
	}
	public void setF123(BigDecimal f123) {
		this.f123 = f123;
	}
	public BigDecimal getF124() {
		return f124;
	}
	public void setF124(BigDecimal f124) {
		this.f124 = f124;
	}
	public BigDecimal getF125() {
		return f125;
	}
	public void setF125(BigDecimal f125) {
		this.f125 = f125;
	}
	public BigDecimal getF130() {
		return f130;
	}
	public void setF130(BigDecimal f130) {
		this.f130 = f130;
	}

	public BigDecimal getF0601() {
		return F0601;
	}

	public void setF0601(BigDecimal f0601) {
		F0601 = f0601;
	}

	public BigDecimal getF0602() {
		return F0602;
	}

	public void setF0602(BigDecimal f0602) {
		F0602 = f0602;
	}

	public BigDecimal getF117() {
		return f117;
	}

	public void setF117(BigDecimal f117) {
		this.f117 = f117;
	}
	public BigDecimal getF119() {
		return F119;
	}
	public void setF119(BigDecimal f119) {
		F119 = f119;
	}
	public BigDecimal getF066() {
		return f066;
	}
	public void setF066(BigDecimal f066) {
		this.f066 = f066;
	}
}
