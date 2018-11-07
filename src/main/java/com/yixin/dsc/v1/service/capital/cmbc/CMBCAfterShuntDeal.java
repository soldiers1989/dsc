package com.yixin.dsc.v1.service.capital.cmbc;

import com.yixin.dsc.common.DscContant;
import com.yixin.dsc.common.exception.BzException;
import com.yixin.dsc.dto.DscCapitalDto;
import com.yixin.dsc.dto.http.DscKeplerDto;
import com.yixin.dsc.service.common.DscKeplerCommonService;
import com.yixin.dsc.v1.service.capital.AfterShuntDeal;
import com.yixin.kepler.dto.PretrialDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;

/**
 * 民生预审处理实现类
 * @author YixinCapital -- xjt
 *		   2018年9月28日 下午2:53:40
 */
@Component("cMBCAfterShuntDeal")
public class CMBCAfterShuntDeal extends AfterShuntDeal {

    private static final Logger LOGGER = LoggerFactory.getLogger(CMBCAfterShuntDeal.class);

    @Resource
    private DscKeplerCommonService dscKeplerCommonService;

    @Override
    public DscCapitalDto deal(DscCapitalDto dscCapitalDto) {
        //------- 民生组装预审信息 ------------
        PretrialDTO PretrialParamDto = new PretrialDTO();
        PretrialParamDto.setCompanyCode(threadLocalApplyMain.get().getRentingCompanyCode()); //发起融资公司编号
        PretrialParamDto.setChannelCode(threadLocalApplyMain.get().getDealerChannelCode()); //经销商渠道编号
        PretrialParamDto.setChannelName(threadLocalApplyMain.get().getDealerChannelName()); //经销商渠道名称
        PretrialParamDto.setTacNo(threadLocalApplyMain.get().getAhkrjjkzh()); //卡号
        PretrialParamDto.setCustName(threadLocalApplyCust.get().getAkhxm()); //客户姓名
        PretrialParamDto.setIdType(threadLocalApplyCust.get().getAzjlx()); //证件类型
        PretrialParamDto.setIdNo(threadLocalApplyCust.get().getAzjhm()); //证件号码
        PretrialParamDto.setPhoneNo(threadLocalApplyCust.get().getAsjhm()); //手机号

        //发起预审
        try{
            PretrialParamDto.setCapitalId(dscCapitalDto.getCapitalId()); //资方ID
            dscCapitalDto.setPretrialParam(PretrialParamDto);
            DscKeplerDto dscKeplerDto = dscKeplerCommonService.keplerPretrial(threadLocalApplyMain.get().getApplyNo(), PretrialParamDto);
            if(dscKeplerDto != null && DscContant.KeplerCode.SUCCESS.equals(dscKeplerDto.getCode())){
                dscCapitalDto.setPretrialResult(true); //发起预审成功
                dscCapitalDto.setPretrialMsg(dscKeplerDto.getMessage());
                return dscCapitalDto;
            } else if(dscKeplerDto != null && DscContant.KeplerCode.NO_THROUGH.equals(dscKeplerDto.getCode())){
                dscCapitalDto.setPretrialResult(false); //发起预审失败
                dscCapitalDto.setPretrialMsg(dscKeplerDto.getMessage());
                return dscCapitalDto;
            } else {
                LOGGER.info("资方预审未能正确匹配返回值,按照失败处理,{},{}", threadLocalApplyMain.get().getApplyNo(), dscKeplerDto.getCode());
                dscCapitalDto.setPretrialResult(false); //发起预审失败
                dscCapitalDto.setPretrialMsg(dscKeplerDto.getMessage());
                return dscCapitalDto;
            }
        }catch (BzException e){
            LOGGER.error("资方预审异常,{},{}", threadLocalApplyMain.get().getApplyNo(), e.getMessage());
            dscCapitalDto.setPretrialResult(false); //发起预审失败
            dscCapitalDto.setPretrialMsg("资方预审异常");
            return dscCapitalDto;
        }catch (Exception e){
            LOGGER.error("资方预审异常,{}", threadLocalApplyMain.get().getApplyNo(), e);
            dscCapitalDto.setPretrialResult(false); //发起预审失败
            dscCapitalDto.setPretrialMsg("资方预审异常");
            return dscCapitalDto;
        }
    }
}
