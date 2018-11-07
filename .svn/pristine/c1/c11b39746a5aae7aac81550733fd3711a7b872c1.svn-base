package com.yixin.kepler.enity;


import java.math.BigDecimal;
import java.util.Map;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

import org.apache.commons.lang3.StringUtils;

import com.google.common.collect.Maps;
import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.kepler.common.enums.ConstantKeyEnum;
import com.yixin.kepler.core.constant.CommonConstant;

/**
 * 静态变量数据表
 * Package : com.yixin.nssp.core.unionloan
 *
 * @author YixinCapital -- wangwenlong
 *		   2017年11月8日 下午5:01:19
 *
 */
@Entity
@Table(name = "constant_config")
public class ConstantConfig extends BZBaseEntiy {

    private static final long serialVersionUID = 2070568003974499038L;

    /**
     * 配置key,查看 SspConstantKeyEnum 枚举
     */
    @Column(name = "`key`", columnDefinition="varchar(128) NOT NULL COMMENT '配置key,查看 SspConstantKeyEnum 枚举'")
    private String key;

    /**
     * 配置字符串 value
     */
    @Column(name = "value_string", columnDefinition="varchar(128) COMMENT '配置字符串 value'")
    private String valueString;

    /**
     * 配置名称
     */
    @Column(name = "name", columnDefinition="varchar(128) COMMENT '配置名称'")
    private String name;

    /**
     * 配置数字类型 value
     */
    @Column(name = "value_bigDecimal", columnDefinition="decimal(19,2) COMMENT '配置数字类型 value'")
    private BigDecimal valueBigDecimal;

    /**
     * 配置描述
     */
    @Column(name = "description",columnDefinition="varchar(1024) COMMENT '配置描述'")
    private String description;


    /**
     * 根据配置KEY获取字符串类型的value
     * @param key
     * @return
     * @author YixinCapital -- wangwenlong
     *	       2017年11月9日 下午1:39:00
     */
    public static String getStringValeByKey(String key){
        ConstantConfig config = ConstantConfig.findFirstByProperty(ConstantConfig.class, "key", key);
        //没有记录默认值是 0 即开关是关闭，调用真实服务
        return config == null?"0":config.getValueString();
    }

    /**
     * 根据配置KEY获取字符串类型的value
     * @param key
     * @return
     * @author YixinCapital -- wangwenlong
     *	       2017年11月9日 下午1:39:00
     */
    public static BigDecimal getBigDecimalValeByKey(String key){
        ConstantConfig config = ConstantConfig.findFirstByProperty(ConstantConfig.class, "key", key);
        return config == null?null:config.getValueBigDecimal();
    }

    /**
     * 根据value值获取对应的name
     * @param key 配置KEY
     * @param value
     * @return
     * @author YixinCapital -- wangwenlong
     *	       2017年11月14日 下午8:17:27
     */
    public static String getNameByValue(String key,String value){
        Map<String,Object> propValues = Maps.newConcurrentMap();
        propValues.put("key", key);
        propValues.put("valueString", value);
        ConstantConfig config = ConstantConfig.findFirstByProperties(ConstantConfig.class, propValues);
        return config == null?"":config.getName();
    }

    /**
     * 根据key获取配置对象
     * @param key
     * @return
     * @author YixinCapital -- wangwenlong
     *	       2017年12月11日 下午5:53:12
     */
    public static ConstantConfig getConstantConfigByKey(String key){
        ConstantConfig config = ConstantConfig.findFirstByProperty(ConstantConfig.class, "key", key);
        return config;
    }

    /**
     * 根据name，key获取配置对象
     * @param key
     * @return
     * @author YixinCapital -- gumanxue
     *	       2018年9月14日 下午5:53:12
     */
    public static ConstantConfig getConstantConfigByNameKey(String name,String key){
        Map<String,Object> propValues = Maps.newConcurrentMap();
        propValues.put("name", name);
        propValues.put("key", key);
        ConstantConfig config = ConstantConfig.findFirstByProperties(ConstantConfig.class,propValues);
        return config;
    }
    
    /**
     * 是否需要资方停止对外服务
     * @param financialCode 资方编码
     * @return 
     * @author YixinCapital -- wangwenlong
     *	       2018年10月18日 下午3:09:45
     */
    public static Boolean getStopServiceFlag(String financialCode){
    	Boolean isStop = false;
    	 Map<String,Object> propValues = Maps.newConcurrentMap();
         propValues.put("key", ConstantKeyEnum.STOP_SERVICE_FLAG.getKey().concat(financialCode));
         propValues.put("deleted", false);
         ConstantConfig config = ConstantConfig.findFirstByProperties(ConstantConfig.class,propValues);
         if(config != null && StringUtils.isNotBlank(config.getValueString())){
        	 return CommonConstant.ONE.equals(config.getValueString());
         }
    	return isStop;
    }
    public String getKey() {
        return key;
    }

    public void setKey(String key) {
        this.key = key;
    }

    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }

    public BigDecimal getValueBigDecimal() {
        return valueBigDecimal;
    }

    public void setValueBigDecimal(BigDecimal valueBigDecimal) {
        this.valueBigDecimal = valueBigDecimal;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
}
