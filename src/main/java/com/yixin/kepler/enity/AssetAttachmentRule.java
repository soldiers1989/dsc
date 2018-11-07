package com.yixin.kepler.enity;

import com.google.common.collect.Maps;
import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.common.system.util.Label;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import org.assertj.core.util.Lists;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


/**
 * <B>功能简述</B><br>
 * KAssetAttachmentRule实体类
 *
 * @author liuhongshen
 * @date 2018年06月06日 14:14:53
 */
@Entity
@Table(name = "k_asset_attachment_rule")
public class AssetAttachmentRule extends BZBaseEntiy {
    /**
     * 附件名称 大类-小类
     */
    @Label(name = "附件名称 大类-小类")
    @Column(name = "attach_name", length = 64)
    private String attachName;
    /**
     * 附件大类
     */
    @Label(name = "附件大类")
    @Column(name = "attach_main_type", length = 16)
    private String attachMainType;


    @Label(name = "合并alix的文件类型")
    @Column(name = "merger_attach_type", length = 512)
    private String mergerAttachType;
    /**
     * 附件小类
     */
    @Label(name = "附件小类")
    @Column(name = "attach_sub_type", length = 16)
    private String attachSubType;

    /**
     * 资方code
     */
    @Label(name = "资方code")
    @Column(name = "financial_id", length = 32)
    private String financialId;
    /**
     * 资方id
     */
    @Label(name = "资方id")
    @Column(name = "financial_code", length = 64)
    private String financialCode;
    /**
     * 字段列表
     */
    @Label(name = "字段列表")
    @Column(name = "fields", length = 255)
    private String fields;

    /**
     * 规则
     */
    @Label(name = "规则")
    @Column(name = "rule", length = 255)
    protected String rule;
    /**
     * 规则类型
     */
    @Label(name = "规则类型")
    @Column(name = "rule_type", length = 16)
    protected String ruleType;
    /**
     * 输出类脚本
     */
    @Label(name = "输出类脚本")
    @Column(name = "script", columnDefinition = "LONGTEXT")
    protected String script;

    @Label(name = "阶段")
    @Column(name = "phase", length = 32)
    private String phase;

    @Label(name = "文件重命名格式")
    @Column(name = "name_format", length = 32)
    private String nameFormat;

    public String getRule() {
        return rule;
    }

    public void setRule(String rule) {
        this.rule = rule;
    }

    public String getRuleType() {
        return ruleType;
    }

    public void setRuleType(String ruleType) {
        this.ruleType = ruleType;
    }

    public String getScript() {
        return script;
    }

    public void setScript(String script) {
        this.script = script;
    }

    public String getFields() {
        return fields;
    }

    public void setFields(String fields) {
        this.fields = fields;
    }

    public String getAttachName() {
        return this.attachName;
    }

    public void setAttachName(String attachName) {
        this.attachName = attachName;
    }

    public String getAttachMainType() {
        return this.attachMainType;
    }

    public void setAttachMainType(String attachMainType) {
        this.attachMainType = attachMainType;
    }

    public String getAttachSubType() {
        return this.attachSubType;
    }

    public void setAttachSubType(String attachSubType) {
        this.attachSubType = attachSubType;
    }


    public String getFinancialId() {
        return this.financialId;
    }

    public void setFinancialId(String financialId) {
        this.financialId = financialId;
    }

    public String getFinancialCode() {
        return this.financialCode;
    }

    public void setFinancialCode(String financialCode) {
        this.financialCode = financialCode;
    }


    public String getNameFormat() {
        return nameFormat;
    }

    public void setNameFormat(String nameFormat) {
        this.nameFormat = nameFormat;
    }


    public String getMergerAttachType() {
        return mergerAttachType;
    }

    public void setMergerAttachType(String mergerAttachType) {
        this.mergerAttachType = mergerAttachType;
    }

    /**
     * 无参构造
     */
    public AssetAttachmentRule() {

    }


    public String getPhase() {
        return phase;
    }

    public void setPhase(String phase) {
        this.phase = phase;
    }

    public static List<AssetAttachmentRule> getAttachmentRuleByFinancialId(String id) {

        String hql = " SELECT aar FROM AssetAttachmentRule AS aar"
                + " WHERE deleted = ?1 and financialId = ?2  and phase is not null";

        ArrayList<Object> params = new ArrayList<Object>() {{
            add(false);
            add(id);
        }};

        return getRepository().createJpqlQuery(hql).setParameters(params).list();
    }

    public static List<AssetAttachmentRule> getByFinancialCodeAndPhase(String financialCode, String phase) {

        String hql = " SELECT aar FROM AssetAttachmentRule AS aar"
                + " WHERE deleted = ?1 and financialCode = ?2  and phase is = ?3";

        List<Object> params = Lists.newArrayList(false, financialCode, phase);

        return getRepository().createJpqlQuery(hql).setParameters(params).list();
    }

    public static List<AssetAttachmentRule> getRuleByFinancialAndPhase(
            String financialCode, String phase) {

        String hql = " SELECT aar FROM AssetAttachmentRule AS aar"
                + " WHERE deleted = ?1 and financialCode = ?2  and phase = ?3";

        ArrayList<Object> params = new ArrayList<Object>() {{
            add(false);
            add(financialCode);
            add(phase);
        }};

        return getRepository().createJpqlQuery(hql).setParameters(params).list();
    }


    /**
     * 根据资方id获取到所有的文件校验规则
     *
     * @param financialId 资方id
     * @return Map<BankPhaseEnum, Map<String, AssetAttachmentRule>>   key：阶段  value：各阶段需要的附件和校验规则
     * Map<String, AssetAttachmentRule>  字典code对应的附件
     */
    public static Map<BankPhaseEnum, Map<String, AssetAttachmentRule>> getRule(String financialId) {

        List<AssetAttachmentRule> rules = AssetAttachmentRule
                .getAttachmentRuleByFinancialId(financialId);

        Map<BankPhaseEnum, Map<String, AssetAttachmentRule>> ruleMap = new HashMap<>();

        if (!CollectionUtils.isEmpty(rules)) {
            for (AssetAttachmentRule rule : rules) {
                String phase = rule.getPhase();
                String mainType = rule.getAttachMainType();
                BankPhaseEnum phaseEnum = BankPhaseEnum.get(phase);
                Map<String, AssetAttachmentRule> phaseMap = ruleMap.get(phaseEnum);
                if (phaseMap == null) {
                    phaseMap = Maps.newHashMap();
                    ruleMap.put(phaseEnum, phaseMap);
                }
                phaseMap.put(mainType, rule);
            }
        }
        return ruleMap;
    }

    /**
     * 根据资方code获取对应的文件规则
     *
     * @param financialCode 资方code
     * @return 文件规则列表
     * @author YixinCapital -- chenjiacheng
     * 2018/7/16 16:22
     */
    public static List<AssetAttachmentRule> getAttrRule(String financialCode) {
        if (StringUtils.isEmpty(financialCode)) {
            return null;
        }
        Map<String, Object> paramMap = new HashMap<>();
        paramMap.put("financialCode", financialCode);
        paramMap.put("deleted", false);
        return AssetAttachmentRule.findByProperties(AssetAttachmentRule.class, paramMap);
    }


    public static AssetAttachmentRule getAttrRuleByType(
            String type, String financialCode) {

        String hql = " SELECT aar FROM AssetAttachmentRule AS aar"
                + " WHERE aar.deleted = ?1 and aar.financialCode = ?2 " +
                " and aar.attachMainType = ?3 ";

        ArrayList<Object> params = new ArrayList<Object>() {{
            add(false);
            add(financialCode);
            add(type);
        }};

        return getRepository().createJpqlQuery(hql).setParameters(params).singleResult();
    }

} 