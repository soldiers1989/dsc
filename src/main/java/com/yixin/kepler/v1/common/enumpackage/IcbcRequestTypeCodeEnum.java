package com.yixin.kepler.v1.common.enumpackage;

import java.util.ArrayList;
import java.util.List;

/**
 * 合作机构的码值枚举类
 * Package : com.yixin.kapler_v1.common.enumpackage
 * 
 * @author YixinCapital -- jiayu
 *		   2016年12月23日 上午9:26:16
 *
 */
public enum IcbcRequestTypeCodeEnum {
	CF_REQUEST_AYFIRST_CODE("ICBC_10101", "进件申请资料初审"),
	ICBC_REQUEST_AYFIRST_AUDIT_CODE("ICBC_10201", "工行初审结果反馈"),
	CF_REQUEST_AYFIRST_AGAIN_CODE("ICBC_10181", "再次进件申请资料审核"),
	CF_REQUESTFUNDS_CODE("ICBC_40101", "请款申请"),
	ICBC_REQUESTFUNDS_AUDIT_CODE("ICBC_40201", "请款申请反馈"),
	CF_REQUESTFUNDS_AGAIN_CODE("ICBC_40181", "再次请款申请"),
	CF_REQUEST_REJECTAdVOCE_CODE("ICBC_20101", "风控电核信息通知"),
	CF_REQUEST_FACEPHOTO_CODE("ICBC_30101", "面签结果通知");

	private String value;
	private String name;
	private boolean selected;

	private IcbcRequestTypeCodeEnum() {
	}

	private IcbcRequestTypeCodeEnum(String value, String name) {
		this.value = value;
		this.name = name;
	}

	private IcbcRequestTypeCodeEnum(String value, String name, boolean selected) {
		this.value = value;
		this.name = name;
		this.selected = selected;
	}
	
	/**
	 * @return the value
	 */
	public String getValue() {
		return value;
	}

	/**
	 * @param value the value to set
	 */
	public void setValue(String value) {
		this.value = value;
	}

	/**
	 * @return the name
	 */
	public String getName() {
		return name;
	}

	/**
	 * @param name the name to set
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * @return the selected
	 */
	public boolean getSelected() {
		return selected;
	}

	/**
	 * @param selected the selected to set
	 */
	public void setSelected(boolean selected) {
		this.selected = selected;
	}

	/**
	 * @param index 索引
	 * 根据key获取value
	 */
	public static String getDisplayNameByIndex(String index){
		for(IcbcRequestTypeCodeEnum status : IcbcRequestTypeCodeEnum.values()){
			if(index.equals(status.getValue())){
				return status.getName();
			}
		}
		return "";
	}
	/**
	 * @param index 索引
	 * 根据key获取枚举对象
	 */
	public static IcbcRequestTypeCodeEnum getQuitaStatusByValue(String index) {
        for (IcbcRequestTypeCodeEnum status : IcbcRequestTypeCodeEnum.values()) {
            if (index.equals(status.getValue())) {
                return status;
            }
        }
        return null;
    }
	private static List<IcbcRequestTypeCodeEnum> list;

    static {
        list = new ArrayList<IcbcRequestTypeCodeEnum>();
        IcbcRequestTypeCodeEnum[] queueStates = IcbcRequestTypeCodeEnum.values();
        for (IcbcRequestTypeCodeEnum state : queueStates) {
            list.add(state);
        }
    }

    public static List<IcbcRequestTypeCodeEnum> getDataList() {
        return list;
    }
}
