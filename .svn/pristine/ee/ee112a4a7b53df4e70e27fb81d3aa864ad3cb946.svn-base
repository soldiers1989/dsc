package com.yixin.kepler.enity;

import java.util.ArrayList;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import com.yixin.common.system.domain.BZBaseEntiy;

@Entity
@Table(name = "sys_card_bin")
public class SysCardBin extends BZBaseEntiy{

	private static final long serialVersionUID = -7915688988650836992L;

	/**
	 * BIN
	 */
	@Column(name = "bin", columnDefinition = "varchar(16) comment '卡BIN'")
	private String bin;
	
	/**
	 * BIN长度
	 */
	@Column(name = "bin_length", columnDefinition = "int(11) comment '卡BIN长度'")
	private Integer binLength;
	
	/**
	 * 银行名称
	 */
	@Column(name = "card_name", columnDefinition = "varchar(64) comment '银行名称'")
	private String cardName;
	
	/**
	 * 银行名称
	 */
	@Column(name = "card_code", columnDefinition = "varchar(64) comment '银行名称'")
	private String cardCode;
	
	/**
	 * 联行号
	 */
	@Column(name = "card_number", columnDefinition = "varchar(64) comment '联行号'")
	private String cardNumber;
	
	/**
	 * 数据来源
	 */
	@Column(name = "data_source", columnDefinition = "varchar(16) comment '数据来源'")
	private String dataSource;

	
	public String getCardCode() {
		return cardCode;
	}

	public void setCardCode(String cardCode) {
		this.cardCode = cardCode;
	}

	public String getBin() {
		return bin;
	}

	public void setBin(String bin) {
		this.bin = bin;
	}

	public Integer getBinLength() {
		return binLength;
	}

	public void setBinLength(Integer binLength) {
		this.binLength = binLength;
	}

	public String getCardName() {
		return cardName;
	}

	public void setCardName(String cardName) {
		this.cardName = cardName;
	}

	public String getCardNumber() {
		return cardNumber;
	}

	public void setCardNumber(String cardNumber) {
		this.cardNumber = cardNumber;
	}

	public String getDataSource() {
		return dataSource;
	}

	public void setDataSource(String dataSource) {
		this.dataSource = dataSource;
	}
	
	public static SysCardBin getCardBin(String account,String financeCode){
		String hql = " SELECT bin FROM SysCardBin bin WHERE ?1 LIKE CONCAT(bin.bin,'%') and bin.dataSource = ?2 ";
		
		ArrayList<Object> params = new ArrayList<Object>(3){{
    		add(account);
    		add(financeCode);
    	}};
    	
    	return getRepository().createJpqlQuery(hql).setParameters(params).singleResult();
	}
}
