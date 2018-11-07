package com.yixin.kepler.common.enums;


/**
 * 银行交易记录，交易类型枚举 {@link com.yixin.kepler.enity.AssetBankTran phase}
 * @author sukang
 * @date 2018-09-28 10:44:42
 */
public enum AssetBankTransEnum {



    /**
     * 云信
     */
    YT_IMPORT_PROTOCOL("yt_import_protocol","云信签约导入"),
    YT_MODIFY_REPAY("yt_modify_repay","云信修改还款信息"),
    YT_FILE_UPLOAD("yt_file_upload","云信文件上传"),
    YT_GET_CONTRACT_FILE("yt_get_contract_file","获取云信签章合同文件");





    private String phase;
    private String name;

    AssetBankTransEnum(String phase,String name) {
        this.phase = phase;
        this.name = name;
    }


    public static BankPhaseEnum get(String val){
        BankPhaseEnum[] values = BankPhaseEnum.values();

        for (BankPhaseEnum bankPhaseEnum : values) {
            if (val.equals(bankPhaseEnum.getPhase())) {
                return bankPhaseEnum;
            }
        }
        return null;
    }

    public String getPhase() {
        return phase;
    }

    public String getName() {
        return name;
    }

}
