package com.yixin.dsc.enumpackage;

/**
 * ssp mongo 集合枚举
 * Package : com.yixin.nssp.enumpackage.clue
 * 
 * @author YixinCapital -- huguoxing
 *		   2016年11月24日 下午7:36:43
 *
 */
public enum MongoCollectionEnum {

	APPLY_MAIN("apply_main","订单主表");


	public String code;
	public String name;


	private MongoCollectionEnum(String code,String name) {
		this.code= code;
		this.name = name;
	}

}
