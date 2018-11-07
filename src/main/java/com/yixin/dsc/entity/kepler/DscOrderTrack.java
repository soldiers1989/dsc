package com.yixin.dsc.entity.kepler;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.dsc.enumpackage.OrderTrackTypeEnum;

/**
 * 订单痕迹表
 * Package : com.yixin.dsc.entity.kepler
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年8月24日 下午4:17:19
 *
 */
@Entity
@Table(name = "dsc_order_track")
public class DscOrderTrack extends BZBaseEntiy{

	private static final long serialVersionUID = 8739935215333357592L;
	
	private final static Logger LOGGER = LoggerFactory.getLogger(DscOrderTrack.class);

	@Column(name = "apply_no", nullable = false,columnDefinition = "varchar(64) comment '订单编号'")
	private String applyNo;
	
	/**
	 * 参考 OrderTrackTypeEnum
	 */
	@Column(name = "track_type", nullable = false,columnDefinition = "varchar(64) comment '类型'")
	private String trackType;
	
	@Column(name = "track_name", nullable = false,columnDefinition = "varchar(64) comment '名称'")
	private String trackName;
	
	@Column(name = "success_flag", nullable = false,columnDefinition = "bit(1) comment '是否成功'")
	private Boolean successFlag;
	
	@Column(name = "message", columnDefinition = "varchar(1024) comment '消息'")
	private String message;
	
	@Column(name = "sign", columnDefinition = "varchar(64) comment '标记'")
	private String sign;

	@Column(name = "capital_code", columnDefinition = "varchar(64) comment '本次准入所带的资方CODE'")
	private String capitalCode;

	@Column(name = "affiliated_capital", columnDefinition = "varchar(64) comment '当前产品关联的资方'")
	private String affiliatedCapital;
	/**
	 * 记录订单痕迹表
	 * @param applyNo 订单编号
	 * @param typeEnum 痕迹类型枚举
	 * @param sign 标记
	 * @return 
	 * @author YixinCapital -- wangwenlong
	 *	       2018年8月27日 下午6:15:15
	 */
	public static String recordTrack(String applyNo,OrderTrackTypeEnum typeEnum,String sign,String capitalCode){
		try {
			DscOrderTrack track = new DscOrderTrack();
			track.setApplyNo(applyNo);
			track.setTrackType(typeEnum.getType());
			track.setTrackName(typeEnum.getName());
			track.setSuccessFlag(true);
			track.setSign(sign);
			track.setCapitalCode(capitalCode);
			return track.create();
		} catch (Exception e) {
			LOGGER.error("记录订单痕迹表异常，订单编号:{},痕迹类型:{}",applyNo,typeEnum.getName(), e);
			return UUID.randomUUID().toString();
		}
	}
	
	/**
	 * 更新痕迹表结果
	 * @param trackId 痕迹表ID
	 * @author YixinCapital -- wangwenlong
	 *	       2018年8月28日 上午10:44:12
	 */
	public static void updateTrackSuccessResult(String trackId,String message,String affiliatedCapital){
		if(StringUtils.isNotBlank(trackId)){
			try {
				DscOrderTrack track = DscOrderTrack.get(DscOrderTrack.class, trackId);
				if(track != null){
					track.setAffiliatedCapital(affiliatedCapital);
					track.setSuccessFlag(true);
					track.setMessage(message);
					track.update();
				}
			} catch (Exception e) {
				LOGGER.error("更新订单痕迹表异常，trackId:{}",trackId,e);
			}
		}
	}
	
	
	
	/**
	 * 根据申请编号查询订单 准入痕迹表 最后一条
	 * 
	 * @param applyNo
	 * @return 
	 * @author YixinCapital -- wangxulong
	 *	       2018年10月25日 下午8:08:26
	 */
	public static DscOrderTrack getLastTrack(String applyNo,String trackType){
		if(StringUtils.isBlank(applyNo)) {
			return null;
		}
		try {
			StringBuilder jpql = new StringBuilder();
			jpql.append("from DscOrderTrack where applyNo = ?1 and trackType = ?2 ORDER BY createTime DESC");
			@SuppressWarnings("serial")
			List<Object> params = new ArrayList<Object>(2) {{
	            add(applyNo);
	            add(trackType);
	        }};
			List<DscOrderTrack> list =  getRepository().createJpqlQuery(jpql.toString()).setParameters(params).list();
			if(CollectionUtils.isNotEmpty(list)) {
				return list.get(0);
			}
		} catch (Exception e) {
			LOGGER.error("根据申请编号查询订单 准入痕迹表 最后一条异常 applyNo:{}",applyNo, e);
		}
		return null;
	}
	
	
	
	/**
	 * 根据申请编号查询订单 准入痕迹表 倒序排列
	 * 
	 * @param applyNo
	 * @return 
	 * @author YixinCapital -- wangxulong
	 *	       2018年10月25日 下午8:08:26
	 */
	public static List<DscOrderTrack> getTrackLis(String applyNo,String trackType){
		if(StringUtils.isBlank(applyNo)) {
			return null;
		}
		try {
			StringBuilder jpql = new StringBuilder();
			jpql.append("from DscOrderTrack where applyNo = ?1 and trackType = ?2 ORDER BY createTime DESC");
			@SuppressWarnings("serial")
			List<Object> params = new ArrayList<Object>(2) {{
				add(applyNo);
				add(trackType);
			}};
			return getRepository().createJpqlQuery(jpql.toString()).setParameters(params).list();
		} catch (Exception e) {
			LOGGER.error("根据申请编号查询订单 准入痕迹表 最后一条异常 applyNo:{}",applyNo, e);
			return null;
		}
	}
	
	
	
	
	
	
	
	
	
	
	
	
	

	
	
	
	
	
	
	
	
	
	
	
	
	public String getApplyNo() {
		return applyNo;
	}

	public void setApplyNo(String applyNo) {
		this.applyNo = applyNo;
	}

	public String getTrackType() {
		return trackType;
	}

	public void setTrackType(String trackType) {
		this.trackType = trackType;
	}

	public String getTrackName() {
		return trackName;
	}

	public void setTrackName(String trackName) {
		this.trackName = trackName;
	}

	public Boolean getSuccessFlag() {
		return successFlag;
	}

	public void setSuccessFlag(Boolean successFlag) {
		this.successFlag = successFlag;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getSign() {
		return sign;
	}

	public void setSign(String sign) {
		this.sign = sign;
	}

	public String getCapitalCode() {
		return capitalCode;
	}

	public void setCapitalCode(String capitalCode) {
		this.capitalCode = capitalCode;
	}

	public String getAffiliatedCapital() {
		return affiliatedCapital;
	}

	public void setAffiliatedCapital(String affiliatedCapital) {
		this.affiliatedCapital = affiliatedCapital;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}
}
