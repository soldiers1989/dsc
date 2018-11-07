package com.yixin.kepler.enity;/**
 * Created by liushuai2 on 2018/6/7.
 */

import com.yixin.common.system.domain.BZBaseEntiy;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

/**
 * Package : com.yixin.kepler.enity
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月07日 10:03
 */
@Entity
@Table(name = "k_asset_field")
public class AssetField extends BZBaseEntiy{

    @Column(name = "field_name", length = 64)
    private String fieldName;

    @Column(name = "field_code", length = 64)
    private String fieldCode;

    public String getFieldName() {
        return fieldName;
    }

    public void setFieldName(String fieldName) {
        this.fieldName = fieldName;
    }

    public String getFieldCode() {
        return fieldCode;
    }

    public void setFieldCode(String fieldCode) {
        this.fieldCode = fieldCode;
    }
}
