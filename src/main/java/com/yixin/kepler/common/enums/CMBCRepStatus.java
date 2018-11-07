package com.yixin.kepler.common.enums;

public enum CMBCRepStatus {

    // 民生申请状态
    COMP("COMP"), // 可放款状态
    CNCL("CNCL"), // 客户取消贷款
    REJT("REJT"), // 拒绝申请
    AUTO("AUTO"), // 终审通过
    REGR("REGR"), // 审批中
    WWSL("WWSL"); // 等待电子签约

    private String status;

    CMBCRepStatus(String status) {
        this.status = status;
    }

    public String getStatus() {
        return status;
    }
}
