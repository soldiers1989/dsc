package com.yixin.dsc.v1.service.capital;

import com.yixin.dsc.dto.DscCapitalDto;
import com.yixin.dsc.entity.order.DscSalesApplyCar;
import com.yixin.dsc.entity.order.DscSalesApplyCost;
import com.yixin.dsc.entity.order.DscSalesApplyCust;
import com.yixin.dsc.entity.order.DscSalesApplyMain;

/**
 * 准入后处理基类，目前只实现了工行、微众-20180928
 * 如果后续实现类需要设置新的参数，参照applyMain的方式，并在调用出set即可，这样可以不影响其余实现类
 *
 * @author YixinCapital -- xjt
 *		   2018年9月28日 下午2:53:40
 */
public abstract class AfterShuntDeal {

    protected ThreadLocal<DscSalesApplyMain> threadLocalApplyMain = new ThreadLocal<>();
    protected ThreadLocal<DscSalesApplyCust> threadLocalApplyCust = new ThreadLocal<>();
    protected ThreadLocal<DscSalesApplyCost> threadLocalApplyCost = new ThreadLocal<>();
    protected ThreadLocal<DscSalesApplyCar> threadLocalApplyCar = new ThreadLocal<>();

    public AfterShuntDeal setApplyMain(DscSalesApplyMain applyMain){
        this.threadLocalApplyMain.set(applyMain);
        return this;
    }

    public AfterShuntDeal setApplyCust(DscSalesApplyCust applyCust){
        this.threadLocalApplyCust.set(applyCust);
        return this;
    }

    public AfterShuntDeal setApplyCost(DscSalesApplyCost applyCost){
        this.threadLocalApplyCost.set(applyCost);
        return this;
    }

    public AfterShuntDeal setApplyCar(DscSalesApplyCar applyCar){
        this.threadLocalApplyCar.set(applyCar);
        return this;
    }


    /**
     * 预审处理
     * ps：直接基于入参进行处理并返回，在处理过程中出现任何异常，都需要设置入参预审false
     * @param dscCapitalDto
     * @return
     */
    public abstract DscCapitalDto deal(DscCapitalDto dscCapitalDto);
}
