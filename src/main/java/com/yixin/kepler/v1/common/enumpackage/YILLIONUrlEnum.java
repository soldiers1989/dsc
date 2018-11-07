package com.yixin.kepler.v1.common.enumpackage;

/**
 * @description:
 * @date: 2018-11-01 17:30
 */
public enum YILLIONUrlEnum {

    ENTRY_APPLY("/entryApply"),
    ENTRY_RESULT("/queryEntryResult"),
    LOAN_APPLY("/loanApply"),
    LOAN_RESULT("/queryLoanResult");


    private String url;

    public String getUrl() {
        return url;
    }

    public void setUrl(String url) {
        this.url = url;
    }

    YILLIONUrlEnum(String url) {
        this.url = url;
    }
}
