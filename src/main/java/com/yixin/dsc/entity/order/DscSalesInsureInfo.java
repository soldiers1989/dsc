package com.yixin.dsc.entity.order;

import com.yixin.common.system.domain.BZBaseEntiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * 合同申请保险信息
 * Package : com.yixin.dsc.entity
 *
 * @author YixinCapital -- wangwenlong
 *		   2018年6月4日 下午2:15:38
 *
 */
@Entity
@Table(name = "dsc_sales_insure_info")
public class DscSalesInsureInfo extends BZBaseEntiy {

	private static final long serialVersionUID = -6026512016311729115L;

	/**
	 * 订单主表ID
	 */
	@Column(name = "main_id", columnDefinition = "varchar(50) comment '订单主表ID'")
	private String mainId;

	/**
	 * 	是否融保险
	 */
	@Column(name = "asfrbx", columnDefinition = "varchar(64) comment '是否融保险'")
	private String asfrbx;

	/**
	 * 	是否报销
	 */
	@Column(name = "ais_bx", columnDefinition = "varchar(5) comment '是否报销'")
	private String aisBx;

	/**
	 * 	保险公司
	 */
	@Column(name = "abxgs", columnDefinition = "varchar(64) comment '保险公司名称'")
	private String abxgs;
	/**
	 * 	保险公司名称
	 */
	@Column(name = "abxgs_name", columnDefinition = "varchar(64) comment '保险公司名称'")
	private String abxgsName;
	/**
	 * 	交强险出单地
	 */
	@Column(name = "ajqxcdd", columnDefinition = "varchar(64) comment '交强险出单地'")
	private String ajqxcdd;
	/**
	 * 	交强险出单地
	 */
	@Column(name = "ajqxcdd_name", columnDefinition = "varchar(64) comment '交强险出单地'")
	private String ajqxcddName;
	/**
	 * 	商业险出单地
	 */
	@Column(name = "asyxcdd", columnDefinition = "varchar(64) comment '商业险出单地'")
	private String asyxcdd;
	/**
	 * 	商业险出单地
	 */
	@Column(name = "asyxcdd_name", columnDefinition = "varchar(64) comment '商业险出单地'")
	private String asyxcddName;
	/**
	 * 	商业险出单方式
	 */
	@Column(name = "asyxcdfs", columnDefinition = "varchar(64) comment '商业险出单方式'")
	private String asyxcdfs;
	/**
	 * 	商业险出单方式
	 */
	@Column(name = "asyxcdfs_name", columnDefinition = "varchar(64) comment '商业险出单方式'")
	private String asyxcdfsName;
	/**
	 * 	交强险出单方式
	 */
	@Column(name = "ajqxcdfs", columnDefinition = "varchar(64) comment '交强险出单方式'")
	private String ajqxcdfs;
	/**
	 * 	交强险出单方式
	 */
	@Column(name = "ajqxcdfs_name", columnDefinition = "varchar(64) comment '交强险出单方式'")
	private String ajqxcdfsName;

    @Column(name = "bbxr", columnDefinition = "varchar(128) comment '被保险人'")
    private String bbxr;

    @Column(name = "abdhjq", columnDefinition = "varchar(64) comment '交强险单号'")
    private String abdhjq;

    @Column(name = "fbdjejq", columnDefinition = "decimal(19,2) comment '交强险保费'")
    private BigDecimal fbdjejq;

    @Column(name = "dbdqsrjq", columnDefinition = "datetime comment '交强险生效日'")
    private Date dbdqsrjq;

    @Column(name = "dbdsxrjq", columnDefinition = "datetime comment '交强险失效日'")
    private Date dbdsxrjq;



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
	public String getAbxgs() {
		return abxgs;
	}
	public void setAbxgs(String abxgs) {
		this.abxgs = abxgs;
	}
	public String getAbxgsName() {
		return abxgsName;
	}
	public void setAbxgsName(String abxgsName) {
		this.abxgsName = abxgsName;
	}
	public String getAjqxcdd() {
		return ajqxcdd;
	}
	public void setAjqxcdd(String ajqxcdd) {
		this.ajqxcdd = ajqxcdd;
	}
	public String getAjqxcddName() {
		return ajqxcddName;
	}
	public void setAjqxcddName(String ajqxcddName) {
		this.ajqxcddName = ajqxcddName;
	}
	public String getAsyxcdd() {
		return asyxcdd;
	}
	public void setAsyxcdd(String asyxcdd) {
		this.asyxcdd = asyxcdd;
	}
	public String getAsyxcddName() {
		return asyxcddName;
	}
	public void setAsyxcddName(String asyxcddName) {
		this.asyxcddName = asyxcddName;
	}
	public String getAsyxcdfs() {
		return asyxcdfs;
	}
	public void setAsyxcdfs(String asyxcdfs) {
		this.asyxcdfs = asyxcdfs;
	}
	public String getAsyxcdfsName() {
		return asyxcdfsName;
	}
	public void setAsyxcdfsName(String asyxcdfsName) {
		this.asyxcdfsName = asyxcdfsName;
	}
	public String getAjqxcdfs() {
		return ajqxcdfs;
	}
	public void setAjqxcdfs(String ajqxcdfs) {
		this.ajqxcdfs = ajqxcdfs;
	}
	public String getAjqxcdfsName() {
		return ajqxcdfsName;
	}
	public void setAjqxcdfsName(String ajqxcdfsName) {
		this.ajqxcdfsName = ajqxcdfsName;
	}
	public String getAisBx() {
		return aisBx;
	}

	public void setAisBx(String aisBx) {
		this.aisBx = aisBx;
	}

    public String getBbxr() {
        return bbxr;
    }

    public void setBbxr(String bbxr) {
        this.bbxr = bbxr;
    }

    public String getAbdhjq() {
        return abdhjq;
    }

    public void setAbdhjq(String abdhjq) {
        this.abdhjq = abdhjq;
    }

    public BigDecimal getFbdjejq() {
        return fbdjejq;
    }

    public void setFbdjejq(BigDecimal fbdjejq) {
        this.fbdjejq = fbdjejq;
    }

    public Date getDbdqsrjq() {
        return dbdqsrjq;
    }

    public void setDbdqsrjq(Date dbdqsrjq) {
        this.dbdqsrjq = dbdqsrjq;
    }

    public Date getDbdsxrjq() {
        return dbdsxrjq;
    }

    public void setDbdsxrjq(Date dbdsxrjq) {
        this.dbdsxrjq = dbdsxrjq;
    }


    /**
	 * 通过订单主表ID删除
	 * @param mainId
	 */
	public static void deleteBymainId(String mainId){
		List<DscSalesInsureInfo> lis = DscSalesInsureInfo.findByProperty(DscSalesInsureInfo.class, "mainId", mainId);
		if(lis!=null && !lis.isEmpty()) {
			for (DscSalesInsureInfo att : lis) {
				att.remove();
			}
		}
	}

	public static DscSalesInsureInfo getByMainId(String mainId) {

		StringBuilder jpql = new StringBuilder("select dsac from DscSalesInsureInfo dsac where dsac.mainId =?1");
		List<Object> filter = new ArrayList<Object>() {{
			add(mainId);
		}};
		return getRepository().createJpqlQuery(jpql.toString()).setParameters(filter).singleResult();
	}
}
