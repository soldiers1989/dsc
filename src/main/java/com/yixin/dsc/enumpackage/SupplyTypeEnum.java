package com.yixin.dsc.enumpackage;


public enum SupplyTypeEnum {


	FIELD("field"), //字段
	ATTACHMENT("att"), //附件
	ACTION("action");//动作

    private String type;
    private SupplyTypeEnum(String type){
        this.type = type;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
}
