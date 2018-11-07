package com.yixin.dsc.v1.service.common;

import com.yixin.dsc.dto.DscFlowResultForAlixDto;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.kepler.v1.datapackage.dto.icbc.IcbcApplyDTO;
import com.yixin.kepler.v1.datapackage.dto.icbc.IcbcBackDataDTO;

/**
 * 工行回调后处理通知提报端基类(可处理各个阶段的回调，得到统一的回调通知提报端对象)，目前只实现了工行-20180930
 * 如果后续实现类需要设置新的参数，参照applyMain的方式，并在调用出set即可，这样可以不影响其余实现类
 */
public abstract class NoticeApplyDeal {

    protected ThreadLocal<DscSalesApplyMain> threadLocalApplyMain = new ThreadLocal<>();
    protected ThreadLocal<IcbcApplyDTO<IcbcBackDataDTO>> threadLocalICBCFeedBack = new ThreadLocal<>();

    public NoticeApplyDeal setApplyMain(DscSalesApplyMain applyMain){
        this.threadLocalApplyMain.set(applyMain);
        return this;
    }

    public NoticeApplyDeal setICBCFeedBack(IcbcApplyDTO<IcbcBackDataDTO> feedBack){
        this.threadLocalICBCFeedBack.set(feedBack);
        return this;
    }

    /**
     * 信审后处理
     * @return
     */
    public abstract DscFlowResultForAlixDto deal();
}
