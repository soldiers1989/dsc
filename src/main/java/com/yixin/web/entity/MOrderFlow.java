package com.yixin.web.entity;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.common.system.util.Label;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @description:
 * @date: 2018-10-11 10:56
 */
@Entity
@Table(name = "m_order_flow")
public class MOrderFlow extends BZBaseEntiy {


    @Label(name = "申请编号")
    @Column(name = "apply_no", length = 64)
    private String applyNo;

    @Label(name = "申请单状态")
    @Column(name = "status", length = 11)
    private Integer status;

    @Label(name = "备注")
    @Column(name = "remark", length = 128)
    private String remark;

    @Label(name = "是否最新一条记录")
    @Column(name = "latest", length = 11)
    private Integer latest;

    @Label(name = "前一条操作的id")
    @Column(name = "previous_id", length = 50)
    private String previousId;

    public String getApplyNo() {
        return applyNo;
    }

    public void setApplyNo(String applyNo) {
        this.applyNo = applyNo;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }


    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }

    public Integer getLatest() {
        return latest;
    }

    public void setLatest(Integer latest) {
        this.latest = latest;
    }

    public String getPreviousId() {
        return previousId;
    }

    public void setPreviousId(String previousId) {
        this.previousId = previousId;
    }


    /**
     * 查询两个状态的间隔时间
     *
     * @param statusBefore
     * @param statusAfter
     * @param startDate
     * @param endDate
     * @return
     */
    public static List<Map<String, Date>> queryOperateTimeBetweenStatus(int statusBefore, int statusAfter, Date startDate, Date endDate) {


        String sql = "SELECT a.create_time date_after,b.create_time date_before FROM m_order_flow a JOIN m_order_flow b ON a.previous_id=b.id WHERE a.`status`= ? AND b.`status`= ? AND a.create_time BETWEEN ? AND ?";

        List<Object> conditions = Lists.newArrayList();
        conditions.add(statusAfter);
        conditions.add(statusBefore);
        conditions.add(startDate);
        conditions.add(endDate);

        List<Object[]> dataList = getQueryChannel().createSqlQuery(sql.toString()).setParameters(conditions).list();

        if (dataList == null || dataList.size() <= 0) {
            return null;
        }

        List<Map<String, Date>> result = Lists.newArrayList();
        for (Object[] dataItem : dataList) {
            Date date1 = (Date) dataItem[0];
            Date date2 = (Date) dataItem[1];
            Map<String, Date> data = Maps.newHashMap();
            data.put("dateAfter", date1);
            data.put("dateBefore", date2);
            result.add(data);
        }
        return result;
    }


}
