package com.yixin.dsc;

import com.alibaba.fastjson.JSON;
import com.yixin.basemessage.dto.mail.MailFile;
import com.yixin.basemessage.dto.mail.YxMailMessage;
import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.common.system.util.BaseMessageUtil;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.dsc.dto.query.DscMainInfoDto;
import com.yixin.dsc.dto.query.DscPaymentErrorDto;
import com.yixin.dsc.entity.order.DscSalesApplyCost;
import com.yixin.dsc.entity.order.DscSalesApplyCust;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.dsc.service.common.DscQueryService;
import com.yixin.dsc.util.ExcelUtils;
import com.yixin.dsc.util.FileUploadUtils;
import com.yixin.dsc.util.MailMessageUtils;
import com.yixin.dsc.v1.service.capital.yntrust.YTCommonService;
import com.yixin.kepler.common.enums.AssetPhaseEnum;
import com.yixin.kepler.common.enums.AssetStateEnum;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.core.domain.SysDIcMapped;
import com.yixin.kepler.enity.AssetFinanceInfo;
import com.yixin.kepler.enity.AssetMainInfo;
import com.yixin.kepler.enity.ConstantConfig;
import com.yixin.kepler.enity.SysDict;
import com.yixin.kepler.v1.common.util.SerialNumberUtil;
import com.yixin.kepler.v1.datapackage.dto.yntrust.YTCreateOrderBorrowerDto;
import com.yixin.kepler.v1.datapackage.dto.yntrust.YTCreateOrderDto;
import com.yixin.kepler.v1.datapackage.dto.yntrust.YTCreateOrderLoanDto;
import com.yixin.kepler.v1.service.capital.yntrust.DscCommonService;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@ContextConfiguration
public class DemoTest {


	private static final Logger logger = LoggerFactory.getLogger(DemoTest.class);

	@Resource
	private DscQueryService dscQueryService;
	@Value("${spring.fileserver.http.upload.url}")
	private String fileHttpUpload;

	@Value("${spring.filestore.url}")
	private String fileDownUrl;

	@Resource
	private SysDIcMapped sysDIcMapped;

	@Resource
	private YTCommonService ytCommonService;

	@Resource
	private DscCommonService dscCommonService;

	@Resource
	private SerialNumberUtil serialNumberUtil;

	@Test
	public void main1(){
		DscQueryService dscQueryService = SpringContextUtil.getApplicationContext().getBean(DscQueryService.class);
		Map<String, String> map = new HashMap<>();
		map.put("finance", "F010");
		map.put("asqrxl", "9");



		Map<String, String> map2 = new HashMap<>();
		map2.put("finance", "F121");
		List<Map<String, String>> lists = new ArrayList<>();

		lists.add(map);
		lists.add(map2);

		lists.sort(Comparator.comparing(o -> o.get(0)));

		List<SysDict> codes = dscQueryService.getItemCodes(lists, "WeBank");
		System.out.println(JSON.toJSONString(codes));


		System.out.println(dscQueryService.getBankCode(codes,"9","asqrxl"));


	}



	@Test
	public void test2(){
		String applyNo = "13";
		logger.error("给结算推送数据出错,applyNo={}", applyNo);
		ConstantConfig config = ConstantConfig.findFirstByProperty(ConstantConfig.class, "name", "toMail");
		if (config == null) {
			logger.warn("未配置邮箱发送人，无法发送邮件，订单:{}", applyNo);
			return;
		}
		YxMailMessage mailMessage = new YxMailMessage();
		mailMessage.setMailFrom(config.getKey());
		mailMessage.setMailTo(config.getValueString());
		mailMessage.setMailSubject("订单【" + applyNo + "】推送结算数据失败，请关注");
		mailMessage.setMailText("订单【" + applyNo +"】于"
				+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
				+"推送结算数据失败，异常信息："
				//+ e.getMessage()
		);
		BaseMessageUtil.sendMail(mailMessage);

	}

	@Test
	public void test3() throws Exception {

		String beginTime = "2018-08-01 08:00:00";
		String endTime = "2018-08-23 08:00:00";

		Integer nums = dscQueryService.countOrderNums(beginTime, endTime);
		List<DscMainInfoDto> dataList = dscQueryService.queryMainInfoList(beginTime, endTime);

		List<DscPaymentErrorDto> paymentErrorDtoList = dscQueryService.queryPaymentErrorList(beginTime, endTime);

		//byte[] array = ExcelUtils.createExcelForMainInfo(dataList,paymentErrorDtoList);
		//
		//FileOutputStream fileOutputStream = new FileOutputStream(new File("d:/a.xlsx"));
		//fileOutputStream.write(array);



		//生成excel
		byte[] array = ExcelUtils.createExcelForMainInfo(dataList,paymentErrorDtoList);
		//上传文件至服务器
		String fileId = FileUploadUtils.upload(array, "资管系统进件详情.xlsx", fileHttpUpload);
		//准备发送邮件
		YxMailMessage mailMessage = new YxMailMessage();
		mailMessage.setMailSubject("资管系统进件详情");
		List<MailFile> mailFileList = new ArrayList<>();
		MailFile mailFile0 = new MailFile();
		mailFile0.setFileUrl(fileDownUrl + fileId);
		mailFile0.setFileSourceName("资管系统进件详情.xlsx");
		mailFileList.add(mailFile0);
		mailMessage.setMailFiles(mailFileList);

		List<String> fileNameList = new ArrayList<>();
		fileNameList.add("附件-资管系统进件详情");
		mailMessage.setMailFileName(fileNameList);

		mailMessage.setMailText("从" + beginTime + "至" + endTime + ",共进 " + nums + " 单。");

		MailMessageUtils.sendMail(mailMessage);


	}

	@Test
	public void test4(){
		String applyNo = "1000136562";
		YTCreateOrderDto createOrderDto = new YTCreateOrderDto();
		YTCreateOrderBorrowerDto createOrderBorrowerDto = new YTCreateOrderBorrowerDto();
		YTCreateOrderLoanDto createOrderLoanDto = new YTCreateOrderLoanDto();
		createOrderDto.setBorrower(createOrderBorrowerDto);
		createOrderDto.setLoan(createOrderLoanDto);

		DscSalesApplyMain main = DscSalesApplyMain.getByApplyNo(applyNo);
		DscSalesApplyCust cust = DscSalesApplyCust.getApplyCostByMianId(main.getId());
		DscSalesApplyCost cost = DscSalesApplyCost.getByMainId(main.getId());
		// ---借款人信息
		createOrderBorrowerDto.setShortName("易鑫"); //固定名称-易鑫
		createOrderBorrowerDto.setBankReservedPhoneNo(main.getAckrsjhm()); //银行开户预留手机号
		createOrderBorrowerDto.setIdCardNo(cust.getAzjhm()); //身份证号码
		createOrderBorrowerDto.setMaritalStatus(10); //婚姻状况--需要转码
		createOrderBorrowerDto.setTelephoneNo(cust.getAsjhm()); //手机号码
		createOrderBorrowerDto.setCity("1101"); //居住城市
		createOrderBorrowerDto.setAddress(cust.getAxjzddz()); //现居住地详细地址
		createOrderBorrowerDto.setZipCode(cust.getAxjzdyb()); //邮编
		createOrderBorrowerDto.setCommunicationAddress(cust.getAxjzddz()); //现居住详细地址
		createOrderBorrowerDto.setCommunicationAddressZipCode(cust.getAxjzdyb()); //现居住地邮编
		createOrderBorrowerDto.setJobType("1"); //职业分类
		createOrderBorrowerDto.setAccountNo(main.getAhkrjjkzh()); //借记卡账号
		createOrderBorrowerDto.setBankCode("6"); //借款人开户银行
		createOrderBorrowerDto.setBranchBankName("建设银行"); //银行名称
		createOrderBorrowerDto.setBankCardAttribution("1101"); //同居住城市

		createOrderBorrowerDto.setRoleType("1"); //固定传1 贷款借款人
		createOrderBorrowerDto.setAssetInfo("{\\\"车型\\\":\\\"奥迪\\\"}"); //附加资产信息
		createOrderBorrowerDto.setHigestEducation(10); //最高学历 ?
		//createOrderBorrowerDto.setHigestDegree(Integer.parseInt(codeConvert("asqrxw", cust.getAsqrxw()))); // 最高学历 z转码
		//createOrderBorrowerDto.setCompanyType(Integer.parseInt(codeConvert("asqrzy",cust.getAsqrzy()))); //所属行业
		//createOrderBorrowerDto.setJobDuty(Integer.parseInt(codeConvert("asqrzw",cust.getAsqrzw()))); //职务
		//createOrderBorrowerDto.setJobTitle(Integer.parseInt(codeConvert("asqrzc",cust.getAsqrzc()))); //职称
		//createOrderBorrowerDto.setLivingConditions(Integer.parseInt(codeConvert("ajzzk",cust.getAjzzk()))); //居住状况
		//createOrderBorrowerDto.setCompanyName(cust.getApogzdw()); //单位名称

		// --合同信息
		createOrderLoanDto.setLoanContractNumber("ZQ2016071524826"); //待云信提供
		createOrderLoanDto.setContractAmount(cost.getFrze().subtract(cost.getFsfje())); //客户融资额
		createOrderLoanDto.setSignDate(main.getAtbrq()); //提报日期
		Date beginDate = new Date();
		beginDate.setTime(main.getAtbrq().getTime() + 1000*60L);
		createOrderLoanDto.setBeginDate(beginDate); //开始日期
		createOrderLoanDto.setSignRate(dscCommonService.convertBigDecimal(cost.getFkhll(), CommonConstant.TO_POIOT_FOUR));
		createOrderLoanDto.setRepaymentCycle("1"); //1:按月结算
		createOrderLoanDto.setRepaymentMode("1"); //1：等额本息
		createOrderLoanDto.setRepaymentPeriod(Integer.parseInt(main.getArzqx())); //还款期数
		createOrderLoanDto.setLoanUsage("车抵贷"); //贷款用途

		AssetMainInfo mainInfo = AssetMainInfo.getByApplyNo(applyNo);
		if (mainInfo == null) {
			mainInfo = createMainInfo(applyNo);
		} else {
			mainInfo.setBankOrderNo(serialNumberUtil.getTranNo4Idfactory("001","NSSP"));
		}
		createOrderDto.setUniqueId(mainInfo.getBankOrderNo());
		createOrderDto.setRequestId(UUID.randomUUID().toString().replace("-",""));
		logger.info("a:{}", JsonObjectUtils.objectToJson(createOrderDto));
		System.out.println(JsonObjectUtils.objectToJson(createOrderDto));
	}

	private String codeConvert(String code,String value){
		return sysDIcMapped.getMappingValue(code,value, CommonConstant.BankName.YNTRUST_BANK);
	}

	private AssetMainInfo createMainInfo(String applyNo) {
		DscSalesApplyMain main = DscSalesApplyMain.getByApplyNo(applyNo);
		AssetMainInfo mainInfo = new AssetMainInfo();
		mainInfo.setApplyNo(applyNo);
		mainInfo.setAssetPhase(AssetPhaseEnum.TRIAL.getPhase());
		mainInfo.setCreditSignState(AssetStateEnum.INIT.getState());
		mainInfo.setContractSignState(AssetStateEnum.INIT.getState());
		mainInfo.setFirstState(AssetStateEnum.INIT.getState());
		mainInfo.setLastState(AssetStateEnum.INIT.getState());
		mainInfo.setPreState(null);
		mainInfo.setPaymentState(AssetStateEnum.INIT.getState());
		mainInfo.setChannelCode(main.getDealerChannelCode()); // 渠道编号
		mainInfo.setChannelName(main.getDealerChannelName()); // 渠道名称
		mainInfo.setCompanyCode(main.getRentingCompanyCode()); // 发起融资公司编号
		mainInfo.setFinancialId(main.getCapitalId());
		mainInfo.setFinancialCode(AssetFinanceInfo.getById(main.getCapitalId()).getCode());
		mainInfo.setBankOrderNo(serialNumberUtil.getTranNo4Idfactory("001","NSSP")); //生成唯一标识
		mainInfo.create();
		return mainInfo;
	}

}
