package com.yixin.kepler.v1.service.capital.yillion;

import com.yixin.common.exception.BzException;
import com.yixin.common.utils.InvokeResult;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.dsc.entity.order.*;
import com.yixin.dsc.util.DateUtil;
import com.yixin.dsc.util.PropertiesManager;
import com.yixin.dsc.util.StrUtil;
import com.yixin.kepler.common.UrlConstant;
import com.yixin.kepler.common.enums.AssetPhaseEnum;
import com.yixin.kepler.common.enums.AssetStateEnum;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.core.base.AbstractBaseDealDomain;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.core.domain.SysDIcMapped;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.enity.AssetFinanceInfo;
import com.yixin.kepler.enity.AssetMainInfo;
import com.yixin.kepler.v1.common.constants.BankRequestConstant;
import com.yixin.kepler.v1.common.constants.IcbcConstant;
import com.yixin.kepler.v1.common.constants.YILLIONConstant;
import com.yixin.kepler.v1.common.enumpackage.YILLIONUrlEnum;
import com.yixin.kepler.v1.common.util.SerialNumberUtil;
import com.yixin.kepler.v1.datapackage.dto.yillion.YILLIONLastTrialDTO;
import com.yixin.kepler.v1.datapackage.entity.AssetBankRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

/**
 * @description:
 * @date: 2018-11-01 14:52
 */
@Service("YILLIONLastTrialRequestStrategy")
public class LastTrialRequestStrategy extends AbstractBaseDealDomain<Object, BaseMsgDTO> {

    private final Logger logger = LoggerFactory.getLogger(getClass());

    private ThreadLocal<YILLIONLastTrialDTO> lastTrailDataThreadLocal = new ThreadLocal<>();

    private ThreadLocal<AssetMainInfo> mainInfoThreadLocal = new ThreadLocal<>();

    @Resource
    private SerialNumberUtil serialNumberUtil;
    @Resource
    private SysDIcMapped sysDIcMapped;
    @Resource
    private PropertiesManager propertiesManager;

    @Override
    protected void getData() throws BzException {

        String applyNo = (String) inputDto.get();

        logger.info("申请单号为{},开始封装银行请求参数", applyNo);
        DscSalesApplyMain applyMain = DscSalesApplyMain.getByApplyNo(applyNo);
        if (applyMain == null) {
            logger.error("申请单号为{},申请编号错误，申请信息表", applyNo);
            throw new BzException("申请编号错误，申请信息不存在");
        }
        String mainId = applyMain.getId();
        DscSalesApplyCust customer = DscSalesApplyCust.getOneByMainId(mainId);
        if (customer == null) {
            logger.error("申请单号为{},申请编号错误，客户信息不存在", applyNo);
            throw new BzException("申请编号错误，客户信息不存在");
        }
        DscSalesApplyCar car = DscSalesApplyCar.getBymainId(mainId);
        if (car == null) {
            logger.error("申请单号为{},申请编号错误，车辆信息不存在", applyNo);
            throw new BzException("申请编号错误，车辆信息不存在");
        }
        DscSalesApplyCost cost = DscSalesApplyCost.getByMainId(mainId);
        if (cost == null) {
            logger.error("申请单号为{},申请编号错误，合同申请费用信息表", applyNo);
            throw new BzException("申请编号错误，合同申请费用信息不存在");
        }
        DscSalesApplyPayee payee = DscSalesApplyPayee.getByMainId(mainId);
        if (payee == null) {
            logger.error("申请单号为{},申请编号错误，合同申请收款信息表", applyNo);
            throw new BzException("申请编号错误，合同申请收款信息不存在");
        }
        DscSalesInsureInfo insureInfo = DscSalesInsureInfo.getByMainId(mainId);
        if (insureInfo == null) {
            logger.error("申请单号为{},申请编号错误，合同申请保险信息表", applyNo);
            throw new BzException("申请编号错误，合同申请保险信息不存在");
        }

        AssetMainInfo mainInfo = AssetMainInfo.getByApplyNo(applyNo);
        if (mainInfo == null) {
            mainInfo = createMainInfo(applyNo, applyMain);
        } else {
            mainInfo.setVenusApplyNo(serialNumberUtil.getTranNo4Idfactory(IcbcConstant.TRAN_BIZCODE, IcbcConstant.TRAN_SYSCODE));
            mainInfo.update();
        }

        String venusApplyNo = mainInfo.getVenusApplyNo();

        YILLIONLastTrialDTO lastTrialInfo = new YILLIONLastTrialDTO();
        lastTrailDataThreadLocal.set(lastTrialInfo);

        //订单编号
        lastTrialInfo.setOrder_no(YILLIONConstant.ORDER_NO_PREFIX + venusApplyNo);
        //申请人姓名
        lastTrialInfo.setName(customer.getAkhxm());
        //证件类型
        lastTrialInfo.setId_type(codeConvert("azjlx", customer.getAzjlx()));
        //证件号码
        lastTrialInfo.setId_no(customer.getAzjhm());
        //银行预留手机号
        lastTrialInfo.setCellphone(applyMain.getAckrsjhm());
        //住房状况
        lastTrialInfo.setHouse_condition(codeConvert("ajzzk", customer.getAjzzk()));
        //居住地省份
        lastTrialInfo.setAbode_state(customer.getAxjzdsf());
        //居住地城市
        lastTrialInfo.setAbode_city(customer.getAxjzdcs());
        //现居住区/县
        lastTrialInfo.setAbode_zone(customer.getAxjzdqx());
        //现居住地详细地址
        lastTrialInfo.setAbode_detail(customer.getAxjzddz());
        //申请人学历
        lastTrialInfo.setQualification(codeConvert("asqrxl", customer.getAsqrxl()));
        //申请人职业
        lastTrialInfo.setCareer_type(codeConvert("asqrzy", customer.getAsqrzy()));
        //单位名称
        lastTrialInfo.setEmp_unit_name(customer.getAdwmc());
        //申请人职务
        lastTrialInfo.setEmp_post(codeConvert("asqrzw", customer.getAsqrzw()));
        //单位所在省份
        lastTrialInfo.setEmp_state(customer.getAdwszsf());
        //单位所在城市
        lastTrialInfo.setEmp_city(customer.getAdwszcs());
        //单位所在区/县
        lastTrialInfo.setEmp_zone(customer.getAdwszqx());
        //单位详细地址
        lastTrialInfo.setEmp_address(customer.getAdwxxdz());
        //银行行号
        lastTrialInfo.setBank_code(applyMain.getAkhrkhh());
        //借记卡帐号
        lastTrialInfo.setBank_card_no(applyMain.getAhkrjjkzh());
        //银行预留手机号码
        lastTrialInfo.setBank_reserve_phone_number(applyMain.getAckrsjhm());

        // 联系人
        assemblerContacts(mainId, lastTrialInfo);

        //产品代码
        lastTrialInfo.setProduct_cd(codeConvert("rentingCompanyCode", applyMain.getRentingCompanyCode()));
        //融资总额
        lastTrialInfo.setApp_lmt(cost.getFrze());
        //申请额度
        lastTrialInfo.setLoan_approval_lmt(cost.getFrze());
        //融资期限
        lastTrialInfo.setLoan_term(applyMain.getArzqx());

        //进件提报日期
        Date submitDate = applyMain.getAtbrq();
        lastTrialInfo.setLoan_date(DateUtil.dateToString(submitDate, "yyyy-MM-dd HH:mm:ss"));
        //易鑫信审通过时间
        lastTrialInfo.setApply_date(DateUtil.dateToString(applyMain.getAyxxstgsj(), "yyyy-MM-dd HH:mm:ss"));
        //贷款用途
        lastTrialInfo.setLoan_purpose("PL08");
        //学位
        lastTrialInfo.setDegree(codeConvert("asqrxw", customer.getAsqrxw()));
        //有无房产
        lastTrialInfo.setHouse_property(codeConvert("aywfc", customer.getAywfc()));
        //是否有社保
        lastTrialInfo.setSocial_security("1");
        //归属门店代码
        lastTrialInfo.setBelong_shop_code(applyMain.getDealerChannelCode());
        //归属门店名称
        lastTrialInfo.setBelong_shop_name(applyMain.getDealerChannelName());
        //归属门店地址
        lastTrialInfo.setBelong_shop_address(applyMain.getAdmdz());
        //还款频率
        lastTrialInfo.setLoanhz(codeConvert("ahkpl", applyMain.getAhkpl()));
        //还款方式
        lastTrialInfo.setLoantype(codeConvert("ahkfs", applyMain.getAhkfs()));
        //月费率/利率(对客利率)
        lastTrialInfo.setAnnual_interest_rate(StrUtil.fixNull(applyMain.getAdklv()) + "%");
        //交强险公司名称
        lastTrialInfo.setComp_compulsory_insurance(insureInfo.getAbxgsName());
        //被保险人
        lastTrialInfo.setInsured(customer.getAkhxm());
        //交强险单号
        lastTrialInfo.setCompulsory_insurance_no(insureInfo.getAbdhjq());
        //交强险保费
        lastTrialInfo.setCompulsory_insurance_amount(insureInfo.getFbdjejq());
        //交强险生效日
        lastTrialInfo.setCompulsory_insurance_effect_date(DateUtil.dateToString(insureInfo.getDbdqsrjq(), "yyyy-MM-dd"));
        //交强险失效日
        Date insuranceExpiryDate = insureInfo.getDbdsxrjq();
        lastTrialInfo.setCompulsory_insurance_expiry_date(DateUtil.dateToString(insuranceExpiryDate, "yyyy-MM-dd"));

        // 交强险失效日-申请日期>31天
        int days = DateUtil.daysBetween(submitDate, insuranceExpiryDate);
        if (days <= 31) {
            throw new BzException("交强险失效日-申请日期需要大于31天");
        }

        //车辆类型
        lastTrialInfo.setCart_type(codeConvert("acllx", car.getAcllx()));
        //制造商
        lastTrialInfo.setMaker(car.getAzcs());
        //品牌
        lastTrialInfo.setTrademark(car.getAppName());
        //车型
        lastTrialInfo.setDemio(car.getAcxName());
        //车龄(月)
        lastTrialInfo.setVehicle_age(car.getIcl());
        //新车指导价
        lastTrialInfo.setGuide_price(car.getFzdj());
        //公里数，alix为万公里
        BigDecimal mileage = car.getFescgls();
        mileage = mileage.multiply(new BigDecimal("10000"));
        lastTrialInfo.setMileage(mileage);
        //出厂日期
        lastTrialInfo.setProduction_date(DateUtil.dateToString(car.getDescccrq(), "yyyy-MM-dd"));
        //过户日期
        Date carPassDate = car.getCarPassDate();
        lastTrialInfo.setTransfer_date(DateUtil.dateToString(carPassDate, "yyyy-MM-dd"));

        //车龄、车龄校验  月为单位、整数、四舍五入
        int vehicleAge = DateUtil.monthsBetween(carPassDate, new Date());
        if (vehicleAge < 2) {
            throw new BzException("当前日期-过户日期大于2个月");
        }

        lastTrialInfo.setVehicle_age(vehicleAge);
        //车架号
        lastTrialInfo.setFrame_number(car.getAcjh());
        //车牌号
        lastTrialInfo.setLicense_number(car.getAcarnojc());
        //车辆评估价格
        lastTrialInfo.setCar_evaluated_price(car.getAppraisalprice());
        //车辆所有权人
        lastTrialInfo.setCar_ownership(customer.getAkhxm());
        //过户次数
        lastTrialInfo.setTransfer_number(car.getXfrTimes());
        //上牌车管所
        lastTrialInfo.setLicense_office("0");
        //申请人职称
        lastTrialInfo.setPostionName(codeConvert("asqrzc", customer.getAsqrzc()));
    }


    /**
     * 联系人信息
     *
     * @param mainId
     * @param lastTrialInfo
     */
    private void assemblerContacts(String mainId, YILLIONLastTrialDTO lastTrialInfo) {

        List<DscSalesApplyContact> contactList = DscSalesApplyContact.getListByParms(mainId);
        if (contactList == null || contactList.size() <= 0) {
            throw new BzException("申请编号错误，联系人信息不存在");
        }

        DscSalesApplyContact contact1 = null;
        DscSalesApplyContact contact2 = null;


        for (DscSalesApplyContact contact : contactList) {
            String ayjkrjtgx = contact.getAyjkrjtgx();

            boolean isDirectRelatives = isDirectRelatives(ayjkrjtgx);

            if ((isDirectRelatives) && contact1 == null) {
                //联系人1需要是直系亲属
                contact1 = contact;
                continue;
            }

            if (contact2 != null) {
                contact2 = contact;
            }
        }

        if (contact1 == null || contact2 == null) {
            throw new BzException("缺少联系人");
        }


        lastTrialInfo.setContact_name_1(contact1.getAlxrxm());
        lastTrialInfo.setContact_mobile_1(contact1.getAsjhm());
        lastTrialInfo.setContact_relation_1(codeConvert("ayjkrjtgx", contact1.getAyjkrjtgx()));

        lastTrialInfo.setContact_name_2(contact2.getAlxrxm());
        lastTrialInfo.setContact_mobile_2(contact2.getAsjhm());
        lastTrialInfo.setContact_relation_2(codeConvert("ayjkrjtgx", contact2.getAyjkrjtgx()));

    }

    /**
     * 判断是否是直系亲属
     *
     * @param ayjkrjtgx
     * @return
     */
    private boolean isDirectRelatives(String ayjkrjtgx) {
        return "00".equals(ayjkrjtgx) || "01".equals(ayjkrjtgx) || "02".equals(ayjkrjtgx) || "03".equals(ayjkrjtgx);
    }


    /**
     * 字典码转换
     *
     * @param code
     * @param value
     * @return
     */
    private String codeConvert(String code, String value) {
        return sysDIcMapped.getMappingValue(code, value, CommonConstant.BankName.YILLION_BANK);
    }

    private AssetMainInfo createMainInfo(String applyNo, DscSalesApplyMain main) {
        AssetMainInfo mainInfo = new AssetMainInfo();
        mainInfo.setApplyNo(applyNo);
        mainInfo.setAssetPhase(AssetPhaseEnum.TRIAL.getPhase());
        //电子征信默认为成功
        mainInfo.setCreditSignState(AssetStateEnum.SUCCESS.getState());
        mainInfo.setContractSignState(AssetStateEnum.INIT.getState());
        mainInfo.setFirstState(AssetStateEnum.INIT.getState());
        mainInfo.setLastState(AssetStateEnum.INIT.getState());
        mainInfo.setPreState(null);
        mainInfo.setPaymentState(AssetStateEnum.INIT.getState());
        //渠道编号
        mainInfo.setChannelCode(main.getDealerChannelCode());
        // 渠道名称
        mainInfo.setChannelName(main.getDealerChannelName());
        //发起融资公司编号
        mainInfo.setCompanyCode(main.getRentingCompanyCode());
        mainInfo.setFinancialId(main.getCapitalId());
        mainInfo.setFinancialCode(AssetFinanceInfo.getById(main.getCapitalId()).getCode());
        //生成唯一标识
        mainInfo.setVenusApplyNo(serialNumberUtil.getTranNo4Idfactory(IcbcConstant.TRAN_BIZCODE, IcbcConstant.TRAN_SYSCODE));
        mainInfo.create();
        return mainInfo;
    }

    @Override
    protected void assembler() throws BzException {

    }

    @Override
    protected InvokeResult<BaseMsgDTO> message() throws BzException {

        AssetMainInfo mainInfo = mainInfoThreadLocal.get();
        YILLIONLastTrialDTO lastTrialInfo = lastTrailDataThreadLocal.get();

        inputDto.remove();
        mainInfoThreadLocal.remove();
        lastTrailDataThreadLocal.remove();

        //订单编号
        String applyNo = mainInfo.getApplyNo();
        String osbUrl = propertiesManager.getOsbEnvironment() + UrlConstant.OsbSystemUrl.yillionInterface + YILLIONUrlEnum.ENTRY_APPLY.getUrl();

        logger.info("亿联(终审)组装报文创建任务记录开始,申请编号:{}", applyNo);
        AssetBankRequest request = new AssetBankRequest();
        //申请编号
        request.setApplyNo(applyNo);
        //银行申请编号
        request.setBankOrderNo(lastTrialInfo.getOrder_no());
        //资方编号
        request.setAssetNo(mainInfo.getFinancialCode());
        //资管请求报文
        request.setAssetReqBody(JsonObjectUtils.objectToJson(lastTrialInfo));
        //所属阶段-信审
        request.setPhase(BankPhaseEnum.LAST_TRIAL.getPhase());
        //请求的url地址
        request.setReqUrl(osbUrl);
        //向银行发起请求状态 0-未发起
        request.setReqState(BankRequestConstant.UN_REQ_BANK);
        //请求重试标记初始化
        request.setRetryMark(BankRequestConstant.RETRY_INIT_COUNT);
        request.setBzId(mainInfo.getId());
        String taskId = request.create();
        logger.info("亿联发起信审任务记录结束,申请编号:{},任务表ID:{}", applyNo, taskId);
        //预审默认初始化
        mainInfo.setPreState(AssetStateEnum.INIT.getState());
        //终审进行中
        mainInfo.setLastState(AssetStateEnum.DOING.getState());
        mainInfo.setBankOrderNo(lastTrialInfo.getOrder_no());
        mainInfo.update();
        logger.info("亿联发起信审创建任务记录成功后更新终审状态为处理中,申请编号:{}", applyNo);
        return new InvokeResult<>(new BaseMsgDTO(CommonConstant.SUCCESS, "SUCCESS"));
    }
}
