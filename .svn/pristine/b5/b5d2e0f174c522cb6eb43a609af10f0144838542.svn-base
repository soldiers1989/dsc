package com.yixin.dsc.entity.capital;

import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.kepler.enity.AssetMainInfo;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Package : com.yixin.dsc.entity.capital
 *
 * @author YixinCapital -- wanghonglin
 * 2018/7/11 17:58
 */
@Entity
@Table(name = "dsc_capital_config")
public class DscCapitalConfig  extends BZBaseEntiy {

    /**
     * 资方Id
     */
    @Column(name = "capital_id", columnDefinition = "varchar(64) comment '资方id'")
    private String capitalId;
    /**
     * 是否需要预审
     */
    @Column(name = "is_pretrial", columnDefinition = "bit(1) comment '是否需要预审'")
    private Boolean isPretrial;
    /**
     * 资方所属银行
     */
    @Column(name = "bank_name", columnDefinition = "varchar(20) comment '资方所属银行'")
    private String bankCode;

    public String getCapitalId() {
        return capitalId;
    }

    public void setCapitalId(String capitalId) {
        this.capitalId = capitalId;
    }

    public Boolean getPretrial() {
        return isPretrial;
    }

    public void setPretrial(Boolean pretrial) {
        isPretrial = pretrial;
    }

    public String getBankCode() {
        return bankCode;
    }

    public void setBankCode(String bankCode) {
        this.bankCode = bankCode;
    }


    /**
     * 根据applyNo获取信审通过的订单信息
     *
     * @return
     */
    public static DscCapitalConfig getBycapitalId(String capitalId) {
        String jpql = "SELECT dcc FROM DscCapitalConfig AS dcc WHERE"
                + " dcc.deleted = 0 AND dcc.capitalId = ?1";

        List<Object> params = new ArrayList<Object>(1) {{
            add(capitalId);
        }};
        return getRepository().createJpqlQuery(jpql.toString()).setParameters(params).singleResult();
    }

    /**
     * 通过资方ID判断是需要预审
     * @param capitalId 资方ID
     * @return 
     * @author YixinCapital -- wangwenlong
     *	       2018年7月17日 下午8:06:16
     */
    public static Boolean isNeedPretrial(String capitalId){
    	DscCapitalConfig config = getBycapitalId(capitalId);
    	if(config == null){ //配置为空，需要预审
    		return true;
    	}
    	return config.getPretrial();
    }

}
