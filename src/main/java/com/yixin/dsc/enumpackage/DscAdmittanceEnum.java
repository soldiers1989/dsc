package com.yixin.dsc.enumpackage;

/**
 * 资方支持 数据信息枚举类
 * @author YixinCapital -- chenjiacheng
 *         2018年07月05日 17:24
 **/
public enum DscAdmittanceEnum {

	BANK_CARD_LIST("还款卡","1"),
	ZX_CONTRACT_LIST("电子征信合同","2"),
	DZQ_CONTRACT_LIST("电子签合同","3"),
	CONTRACT_SIGN_LIST("签章合同类型集合","4");


	private String name;
	private String value;

	DscAdmittanceEnum(String name, String value) {
		this.name = name;
		this.value = value;
	}

	public String getName() {
		return name;
	}

	public String getValue() {
		return value;
	}
}
