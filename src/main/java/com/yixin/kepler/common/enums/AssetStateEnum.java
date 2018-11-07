package com.yixin.kepler.common.enums;

/**
 * 资产状态枚举
 * Package : com.yixin.kepler.common.enums
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年7月10日 下午3:26:39
 *
 */
public enum AssetStateEnum {
	
    INIT(0), //初始化
    SUCCESS(1), //成功
    FAILD(2),//失败
    DOING(3),//进行中
    REJECT(4),//驳回 
    ACCEPT_FAILD(5);//受理失败

   private Integer state;

	private AssetStateEnum(Integer state){
	    this.state = state;
	}

	public Integer getState() {
		return state;
	}


	/**
	 * 获取当前状态的中文名称
	 * @param state 状态
	 * @return 对应的名称
	 * @author YixinCapital -- chenjiacheng
	 *             2018/7/19 17:20
	 */
	public static String getNameByState(Integer state) {
		if (state == null) {
			return null;
		}
		if (0 == state) {
			return "初始化中";
		} else if (1 == state) {
			return "成功";
		} else if (2 == state) {
			return "失败";
		} else if (3 == state) {
			return "进行中";
		} else if (4 == state) {
			return "驳回";
		} else {
			return "未知";
		}
	}
}
