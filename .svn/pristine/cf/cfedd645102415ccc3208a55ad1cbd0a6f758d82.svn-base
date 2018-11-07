package com.yixin.kepler.core.domain.cmbc;

import com.yixin.common.exception.BzException;
import com.yixin.common.utils.DateUitls;
import com.yixin.common.utils.InvokeResult;
import com.yixin.dsc.entity.order.DscSalesApplyCar;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.kepler.common.RestTemplateUtil;
import com.yixin.kepler.common.enums.AssetPhaseEnum;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.common.enums.CMBCTransCodeEnum;
import com.yixin.kepler.core.base.AbstractBaseDealDomain;
import com.yixin.kepler.core.config.CMBCConfig;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.core.domain.cmbc.util.CMBCUtil;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.dto.cmbc.CMBCAfterLoanDataSyncDTO;
import com.yixin.kepler.dto.cmbc.CMBCRequestDTO;
import com.yixin.kepler.enity.AssetBankTran;
import com.yixin.kepler.enity.AssetMainInfo;
import com.yixin.kepler.enity.OsbFileLog;
import com.yixin.kepler.enity.SysDict;
import net.sf.json.JSONObject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

/**
 * 请求银行贷后资料同步策略实现类
 *
 * @author sukang
 * @date 2018-06-09 15:48:21
 */
@Service("CMBCAfterLoanRequestStrategy")
public class AfterLoanRequestStrategy extends AbstractBaseDealDomain<Object, BaseMsgDTO>  {
    private static final Logger logger = LoggerFactory.getLogger(AfterLoanRequestStrategy.class);

    /**
     * cmbc相关配置类
     */
    @Autowired
    private CMBCConfig cmbcConfig;

    private ThreadLocal<AssetMainInfo> threadLocal = new ThreadLocal<>();

    @Override
    protected void getData() throws BzException {


        String applyNo = String.valueOf(inputDto.get());
        logger.info("请求银行贷后资料同步 开始：{}",applyNo);

        AssetMainInfo assetMainInfo = AssetMainInfo.getByApplyNo(applyNo);

        CMBCRequestDTO cmbcInstance = CMBCRequestDTO.getCMBCInstance(
                CMBCTransCodeEnum.AFTER_LOAN);
        cmbcInstance.setMerchantNum(cmbcConfig.getMerchantNum());
        cmbcInstance.setSystemCode(cmbcConfig.getSystemCode());
        cmbcInstance.setTransType("000010");
        cmbcInstance.setReqSeq(CMBCUtil.createReqSeq());
        cmbcInstance.setBody(getCmbcAfterLoanBody(assetMainInfo));
        threadLocal.set(assetMainInfo);

        inputDto.set(cmbcInstance);
    }

    @Override
    protected void assembler() throws BzException {

    }

    @Override
    protected InvokeResult<BaseMsgDTO> message() throws BzException {
        CMBCRequestDTO reqData = (CMBCRequestDTO) inputDto.get();
        AssetMainInfo assetMainInfo = threadLocal.get();
        threadLocal.remove();


        AssetBankTran assetBankTran = new AssetBankTran();
        //添加银行交易信息
        assetBankTran.setReqBody(com.alibaba.fastjson.JSONObject.toJSONString(reqData));
        assetBankTran.setApplyNo(assetMainInfo.getApplyNo());
        assetBankTran.setAssetId(assetMainInfo.getId());

        //资产编号对应银行交易流水
        assetBankTran.setAssetNo(assetMainInfo.getAssetNo());
        assetBankTran.setReqUrl(cmbcConfig.getOsbReqUrl());
        assetBankTran.setPhase(BankPhaseEnum.AFTER_LOAN.getPhase());
        assetBankTran.setTranNo(reqData.getReqSeq());
        assetBankTran.setSender(1);
        assetBankTran.create();
        assetMainInfo.setAssetPhase(AssetPhaseEnum.AFTER_LOAN.getPhase());
        //请求银行
        logger.info("\n发送贷后请求的参数为{}",assetBankTran.getReqBody());
        String result = RestTemplateUtil.sendRequestV2(cmbcConfig.getOsbReqUrl(),
                reqData);
        assetBankTran.setRepBody(result);

        logger.info("\n贷后请求的返回参数为{}",result);

        //银行请款申请成功创建请求结果查询任务
        assetBankTran.setRepBody(result);

        JSONObject resultJson = JSONObject.fromObject(result);
        JSONObject body;
        //业务执行
        if (!resultJson.isNullObject() && resultJson.containsKey("body")) {
            body = JSONObject.fromObject(resultJson.getString("body"));
        	
        	if (body.containsKey("appvStatus") && "S".equals(body.getString("appvStatus")) ) {
        		assetBankTran.setApprovalDesc(body.getString("appvResult"));
        		assetBankTran.setApprovalCode(body.getString("appvCode"));
        		assetMainInfo.setAfterLoanState(1);
			}else {
				assetBankTran.setApprovalDesc(body.getString("appvResult"));
				assetBankTran.setApprovalCode(body.getString("appvCode"));
				assetMainInfo.setAfterLoanState(2);
            }
            assetMainInfo.update();
            assetBankTran.update();
            return new InvokeResult<>(new BaseMsgDTO(CommonConstant.SUCCESS,"success"));
        }
        //接口执行
        assetMainInfo.setAfterLoanState(3);
        assetBankTran.setApprovalDesc(resultJson.getString("respDesc"));
        assetBankTran.setApprovalCode(resultJson.getString("respStatus"));
        assetMainInfo.update();
        assetBankTran.update();
        return new InvokeResult<>(new BaseMsgDTO(CommonConstant.PROCESSING,"贷后请求失败"));
    }


    private CMBCAfterLoanDataSyncDTO getCmbcAfterLoanBody(AssetMainInfo assetMainInfo) {
        CMBCAfterLoanDataSyncDTO dataSyncDTO = new CMBCAfterLoanDataSyncDTO();
        OsbFileLog osbFileLog = OsbFileLog.getOk(assetMainInfo.getApplyNo(),
                BankPhaseEnum.AFTER_LOAN.getPhase());

        dataSyncDTO.setApplyNo(assetMainInfo.getCmbcApplyNo());
        dataSyncDTO.setFileName(osbFileLog.getCompressName().concat(".").concat(
                osbFileLog.getCompressFormat()));
        dataSyncDTO.setReserve(getReceive(assetMainInfo.getApplyNo()));
        dataSyncDTO.setFileDate(DateUitls.dateToStr(osbFileLog.getCreateTime(),"yyyyMMdd"));
        dataSyncDTO.setHasFile("Y");

        return dataSyncDTO;
    }


    /**
     * 根据是否是二手车进行组装receive1参数
     * @param applyNo
     * @return
     */
    private String getReceive(String applyNo) {

        DscSalesApplyMain main = DscSalesApplyMain.getOneByApplyNo(applyNo);
        Map<String,Object> map = new HashMap<>(5);
        DscSalesApplyCar car = DscSalesApplyCar.getBymainId(main.getId());
        AssetBankTran paymentBankTrans = AssetBankTran.getByApplNoAndType(applyNo,BankPhaseEnum.PAYMENT.getPhase());
        //发起请款提交民生时间
        map.put("transTime", DateUitls.dateToStr(paymentBankTrans.getCreateTime(),
        		"HHmmss"));
        map.put("asqbh", main.getApplyNo());
        //车牌号
        map.put("regsNo",car.getAcarnojc() == null ? "":car.getAcarnojc());
        //抵押权人
        map.put("gtrePerson",main.getAdyqr() == null ? "":main.getAdyqr());
        //车辆抵押城市
        String cityPro = SysDict.getCityPro(main.getAcldycs(),"2");
        map.put("gtreCity", cityPro == null ? "":cityPro);

        return JSONObject.fromObject(map).toString();

    }


}
