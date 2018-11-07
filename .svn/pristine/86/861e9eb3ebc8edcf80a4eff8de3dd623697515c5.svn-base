package com.yixin.dsc.service.impl.common;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import javax.annotation.Resource;

import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.assertj.core.util.Lists;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import com.alibaba.fastjson.JSON;
import com.yixin.basemessage.dto.mail.YxMailMessage;
import com.yixin.common.utils.InvokeResult;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.dsc.common.DscContant;
import com.yixin.dsc.common.exception.BzException;
import com.yixin.dsc.dto.DscActionResultDTO;
import com.yixin.dsc.dto.DscComputerParamDto;
import com.yixin.dsc.dto.DscComputerResultDto;
import com.yixin.dsc.dto.DscElecCreditEvidenceDto;
import com.yixin.dsc.dto.DscElecSignDto;
import com.yixin.dsc.dto.DscFeeScheduleDto;
import com.yixin.dsc.dto.DscReverseFlowForAlixDto;
import com.yixin.dsc.dto.DscSupplyAttachmentsDto;
import com.yixin.dsc.dto.DscSupplyDto;
import com.yixin.dsc.dto.DscSupplyFieldsDto;
import com.yixin.dsc.dto.http.DscKeplerDto;
import com.yixin.dsc.entity.order.DscSalesApplyCust;
import com.yixin.dsc.entity.order.DscSalesApplyMain;
import com.yixin.dsc.service.common.DscKeplerCommonService;
import com.yixin.dsc.service.common.DscWbCommonService;
import com.yixin.dsc.util.MailMessageUtils;
import com.yixin.dsc.util.PropertiesManager;
import com.yixin.kepler.api.KeplerOpenAPI;
import com.yixin.kepler.common.enums.AssetPhaseEnum;
import com.yixin.kepler.common.enums.AssetStateEnum;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.common.enums.WBTransCodeEnum;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.core.domain.AsyncTask;
import com.yixin.kepler.core.domain.webank.WBEleContractRequest;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.dto.ControlReqDTO;
import com.yixin.kepler.dto.OsbAttachmentDTO;
import com.yixin.kepler.dto.PretrialDTO;
import com.yixin.kepler.dto.RespMessageDTO;
import com.yixin.kepler.dto.StatusDTO;
import com.yixin.kepler.dto.webank.WBComputerRespDTO;
import com.yixin.kepler.dto.webank.WBComputerSchedulesDTO;
import com.yixin.kepler.enity.AssetElecCreditInfo;
import com.yixin.kepler.enity.AssetMainInfo;

/**
 * 调用贷前/资管 服务
 * Package : com.yixin.dsc.common.service.impl
 * 
 * @author YixinCapital -- wangwenlong
 *		   2018年6月6日 下午7:05:48
 *
 */
@Service("dscKeplerCommonService")
public class DscKeplerCommonImpl implements DscKeplerCommonService {

	private static final Logger LOGGER = LoggerFactory.getLogger(DscKeplerCommonImpl.class);

	/**
	 * 配置文件统一管理类
	 */
	@Resource
	private PropertiesManager propertiesManager;

	@Autowired
	private KeplerOpenAPI keplerOpenAPI;

	@Autowired
	private AsyncTask asyncTask;

	@Autowired
	private WBEleContractRequest wbEleContractRequest;

	@Resource
	private DscWbCommonService dscWbCommonService;

	/**
	 *  获取订单各种类型的状态
	 * @param applyNo 订单编号
	 * @param type 类型
	 * @return
	 * @author YixinCapital -- wangwenlong
	 *	       2018年6月6日 下午7:46:42
	 */
	@Override
	public DscKeplerDto getApplyNoStatus(String applyNo, String type) throws BzException,Exception {
		if(StringUtils.isBlank(applyNo) || StringUtils.isBlank(type)){
			throw new BzException("查询订单状态失败，参数为空");
		}

		LOGGER.info("调用资管服务获取订单各种类型的状态 入参:{}，{}",applyNo,type);
		InvokeResult<StatusDTO> statusRet = keplerOpenAPI.loanStatus(applyNo,type);
		if(statusRet==null){
			LOGGER.error("调用资管服务获取订单各种类型的状态，订单编号：{}，获返回值为空",applyNo);
			throw new BzException(String.format("调用资管服务获取订单各种类型的状态，订单编号：%s，获返回值为空",applyNo));
		}
		LOGGER.info("调用资管服务获取订单各种类型的状态，订单编号：{},返回结果：{}",applyNo,JsonObjectUtils.objectToJson(statusRet));

		if(statusRet.isHasErrors()){
			LOGGER.error("调用资管服务获取订单各种类型的状态，订单编号：{},synJson.getString('hasErrors')为false",applyNo);
			throw new BzException(statusRet.getErrorMessage());
		}
		StatusDTO statusDTO = (StatusDTO)statusRet.getData();
		if(statusDTO==null){
			LOGGER.error("调用资管服务获取订单各种类型的状态，订单编号：{}，返回值synJson.optString('data')为null或者为空",applyNo);
			throw new BzException("查询订单状态失败");
		}
		DscKeplerDto result = new DscKeplerDto();
		result.setStatus(statusDTO.getStatus()+"");
		result.setType(statusDTO.getType());
		LOGGER.info("调用资管服务获取订单各种类型的状态，订单编号：{},查询结果：{}",applyNo,result);
		return result;
	}

	


	/**
	 * 取消、提回、退回、拒绝订单
	 * @param applyNo
	 * @param ctype
	 * @return
	 */
	@Override
	public DscReverseFlowForAlixDto keplerControl(String applyNo, String ctype) throws BzException,Exception {

		if(StringUtils.isBlank(applyNo)){
			throw new BzException("操作失败，申请编号为空");
		}
		if(StringUtils.isBlank(ctype)){
			throw new BzException("取消、提回、退回、拒绝订单失败，参数为空");
		}

		ControlReqDTO controlReqDTO = new ControlReqDTO();
		controlReqDTO.setCtype(ctype);
		controlReqDTO.setApplyNo(applyNo);
		LOGGER.info("取消、提回、退回、拒绝订单,调用资管 入参:{},{}",applyNo,ctype);
		InvokeResult<BaseMsgDTO> controlRet = keplerOpenAPI.loanControl(controlReqDTO);
		if(controlRet == null) {
			LOGGER.info("取消、提回、退回、拒绝订单,调用资管 返回为空");
			throw new Exception("调用资管 返回为空.");
		}
		LOGGER.info("取消、提回、退回、拒绝订单,调用资管,返回结果：{}",JsonObjectUtils.objectToJson(controlRet));

		if(controlRet.isHasErrors()) {
			LOGGER.info("取消、提回、退回、拒绝订单返回错误：{}",controlRet.getErrorMessage());
			throw new BzException(controlRet.getErrorMessage());
		}

		BaseMsgDTO msgDTO = (BaseMsgDTO) controlRet.getData();
		if(msgDTO==null){
			LOGGER.error("取消、提回、退回、拒绝订单,调用资管,返回结果为空",applyNo);
			throw new BzException("调用资管 返回为空");
		}
		DscReverseFlowForAlixDto dto = new DscReverseFlowForAlixDto();
		dto.setCode(msgDTO.getCode());
		dto.setMessage(msgDTO.getMessage());
		dto.setCtype(ctype);
		dto.setApplyNo(applyNo);
		return dto;


	}

	/**
	 * 信审
	 */
	@Override
	public DscActionResultDTO keplerApply(String applyNo) throws BzException,Exception {
		if(StringUtils.isBlank(applyNo)){
			throw new BzException("信审申请失败，申请编号为空");
		}
		LOGGER.info("信审申请,调用资管 ,调用资管 入参:{}",applyNo);
		InvokeResult<BaseMsgDTO> applyRet = keplerOpenAPI.loanApply(applyNo);
		if(applyRet==null) {
			LOGGER.info("信审申请,调用资管 返回为空");
			throw new Exception("信审申请 调用资管 返回为空.");
		}
		LOGGER.info("信审申请,调用资管 ,调用资管,返回结果：{}",JsonObjectUtils.objectToJson(applyRet));

		if(applyRet.isHasErrors()) {
			LOGGER.info("信审申请,调用资管 异常:{}",applyRet.getErrorMessage());
			throw new BzException(applyRet.getErrorMessage());
		}


		BaseMsgDTO msgDTO = (BaseMsgDTO) applyRet.getData();
		if(msgDTO==null) {
			LOGGER.info("信审申请,调用资管 返回为空.");
			throw new Exception("信审申请 调用资管 返回为空");
		}

		DscActionResultDTO dto = new DscActionResultDTO();
		dto.setResultCode(msgDTO.getCode());
		dto.setResultMessage(msgDTO.getMessage());
		return dto;


	}

	/**
	 * 请款
	 */
	@Override
	public DscActionResultDTO keplerRequest(String applyNo) throws BzException,Exception {
		if(StringUtils.isBlank(applyNo)){
			throw new BzException("请款申请失败，申请编号为空");
		}
		LOGGER.info("请款申请,调用资管 入参:{}",applyNo);
		RespMessageDTO<BaseMsgDTO> requestRet = keplerOpenAPI.paymentRequest(applyNo);
		if(requestRet==null) {
			LOGGER.info("请款申请,调用资管 返回为空");
			throw new Exception("请款申请 调用资管 返回为空.");
		}
		LOGGER.info("请款申请,调用资管 出参:{}",JsonObjectUtils.objectToJson(requestRet));
		if(requestRet.isHasErrors()) {
			LOGGER.info("请款申请,调用资管 异常:{}",requestRet.getErrorMessage());
			throw new BzException(requestRet.getErrorMessage());
		}

		BaseMsgDTO msgDTO = requestRet.getData();
		if(msgDTO==null) {
			LOGGER.info("请款申请,调用资管 返回为空.");
			throw new Exception("请款申请 调用资管 返回为空");
		}
		DscActionResultDTO dto = new DscActionResultDTO();
		dto.setResultCode(msgDTO.getCode());
		dto.setResultMessage(msgDTO.getMessage());
		return dto;


	}


	/**
	 * 确认订单各种状态是否完成
	 * @param applyNo 订单编号
	 * @param type 类型
	 * @return
	 * @author YixinCapital -- wangwenlong
	 *	       2018年6月6日 下午7:46:42
	 */
	@Override
	public Boolean confirmApplyNoStatus(String applyNo, String type) {
		try{
			DscKeplerDto result = this.getApplyNoStatus(applyNo, type);
			if(result == null){
				throw new BzException("未确认到订单状态");
			}
			return DscContant.KeplerStatus.OK.equals(result.getStatus());
		}catch (BzException e){
			LOGGER.error("确认订单各种状态是否完成异常{}",e.getMessage());
		}catch (Exception e){
			LOGGER.error("确认订单各种状态是否完成异常.",e);
		}
		return false;
	}

	/**
     * 预审
     * @param applyNo 订单编号
     * @param pretrialParam 预审入参
     * @return 
     * @author YixinCapital -- wangwenlong
     *	       2018年6月8日 下午6:08:28
     */
	@Override
	public DscKeplerDto keplerPretrial(String applyNo,PretrialDTO pretrialDTO) throws BzException,Exception {
		if(StringUtils.isBlank(applyNo)){
			throw new BzException("预审失败，参数为空");
		}
		LOGGER.info("调用资管服务借款预审 入参:{}，{}",applyNo,JsonObjectUtils.objectToJson(pretrialDTO));
		pretrialDTO.setApplyNo(applyNo);//add 2018-06-15
		InvokeResult<BaseMsgDTO> pretrialRet = keplerOpenAPI.pretrialRequest(pretrialDTO);
		if(pretrialRet==null){
			LOGGER.error("调用资管服务借款预审，订单编号：{}，获返回值为空",applyNo);
			throw new BzException("预审失败");
		}
		LOGGER.info("调用资管服务借款预审，订单编号：{},返回结果：{}",applyNo,JsonObjectUtils.objectToJson(pretrialRet));

		if(pretrialRet.isHasErrors()){
			LOGGER.error("调用资管服务借款预审，订单编号：{},synJson.optString('hasErrors')为true",applyNo);
			throw new BzException(pretrialRet.getErrorMessage());
		}
		BaseMsgDTO msgDTO = (BaseMsgDTO)pretrialRet.getData();
		if(msgDTO == null){
			LOGGER.error("调用资管服务借款预审，订单编号：{}，返回值synJson.optString('data')为null或者为空",applyNo);
			throw new BzException("预审失败");
		}

		DscKeplerDto result = new DscKeplerDto();
		result.setCode(msgDTO.getCode());
		result.setMessage(msgDTO.getMessage());
		LOGGER.info("调用资管服务借款预审，订单编号：{},查询结果：{}",applyNo,result);
		return result;
	}


	 /**
     * 根据资金方id获取补充信息
     * @param capitalId 资金方id
     * @return 
     * @author YixinCapital -- wangwenlong
     *	       2018年6月11日 下午4:52:18
     */
	@Override
	public DscSupplyDto keplerSupplyByCapitalId(String capitalId) {
		if(StringUtils.isBlank(capitalId)){
			return null;
		}

		LOGGER.info("根据资金方id获取补充信息 capitalId:{}",capitalId);
		RespMessageDTO<com.yixin.kepler.dto.RespFinancialDTO> financialInfoRet = keplerOpenAPI.getFinancialInfo(capitalId);

		if(financialInfoRet==null){
			LOGGER.error("根据资金方id获取补充信息，资金方id：{}，获返回值为空",capitalId);
			throw new BzException("获取补充信息失败");
		}
		LOGGER.info("根据资金方id获取补充信息，资金方id：{},返回结果：{}",capitalId,JsonObjectUtils.objectToJson(financialInfoRet));


		if(financialInfoRet.isHasErrors()){
			LOGGER.error("根据资金方id获取补充信息，资金方id：{},synJson.getString('hasErrors')为true",capitalId);
			throw new BzException("获取补充信息失败");
		}

		com.yixin.kepler.dto.RespFinancialDTO dto = financialInfoRet.getData();
		if(dto==null){
			LOGGER.error("根据资金方id获取补充信息，资金方id：{}，返回值synJson.getString('data')为null或者为空",capitalId);
			throw new BzException("获取补充信息失败");
		}

		DscSupplyDto result = new DscSupplyDto();
		//========== 补充字段 ================
		if(CollectionUtils.isNotEmpty(dto.getColumns())){
			List<DscSupplyFieldsDto> fieldList = Lists.newArrayList();
			for(com.yixin.kepler.dto.RespFinancialColumn column : dto.getColumns()){
				DscSupplyFieldsDto fileldDto = new DscSupplyFieldsDto();
				fieldList.add(fileldDto);
			}
			result.setFieldList(fieldList);
		}
		//========== 补充材料 ================
		if(CollectionUtils.isNotEmpty(dto.getFiles())){
			List<DscSupplyAttachmentsDto> attList = Lists.newArrayList();
			for(com.yixin.kepler.dto.RespFinancialFile file : dto.getFiles()){
				DscSupplyAttachmentsDto fileDto = new DscSupplyAttachmentsDto();
				attList.add(fileDto);
			}
			result.setAttList(attList);
		}
		LOGGER.info("根据资金方id获取补充信息，资金方id：{},查询结果：{}",capitalId,JsonObjectUtils.objectToJson(result));
		return result;
	}


	@Async
	public void applyAttachmentTar(String applyNo){
		LOGGER.info("准入规则校验结束，异步打包开始。 订单编号:{}",applyNo);
		OsbAttachmentDTO attachmentDTO = new OsbAttachmentDTO();
		attachmentDTO.setBzId(applyNo);
		attachmentDTO.setBzType(BankPhaseEnum.LAST_TRIAL.getPhase());
		asyncTask.uploadAttachment(attachmentDTO);
		LOGGER.info("准入规则校验结束，异步打包结束。 订单编号:{}",applyNo);
	}


	/**
	 * 借据计算
	 *
	 * @param paramDto 申请编号
	 * @return 试算结果
	 * @author YixinCapital -- chenjiacheng
	 *          2018/7/6 9:48
	 */
	@Override
	public DscComputerResultDto keplerComputer(DscComputerParamDto paramDto) {
		LOGGER.info("通过申请编号获取客户信息,applyNo={}", paramDto.getApplyNo());
		DscSalesApplyMain main = DscSalesApplyMain.getByApplyNo(paramDto.getApplyNo());
		if (main == null) {
			LOGGER.error("通过申请编号获取申请信息为空,applyNo={}", paramDto.getApplyNo());
			throw new BzException("申请信息不存在");
		}
		DscSalesApplyCust cust = DscSalesApplyCust.getApplyCostByMianId(main.getId());
		if (cust == null) {
			LOGGER.error("通过主表id获取客户信息为空,applyNo={},mainId={}", paramDto.getApplyNo(), main.getId());
			throw new BzException("客户信息不存在");
		}
		AssetMainInfo mainInfo = AssetMainInfo.getByApplyNo(paramDto.getApplyNo());

		Map<String, Object> paramMap = new HashMap<>();
		paramMap.put("applyNo", paramDto.getApplyNo()); //申请编号
		paramMap.put("txnId", WBTransCodeEnum.WB_COMPUTER.getTransCode()); //借据试算交易服务码
		paramMap.put("merchantId", dscWbCommonService.getMerchantIdByCompanyCode(main.getRentingCompanyCode())); //平台id
		paramMap.put("channel", dscWbCommonService.getWbChannelByOrderSource(main.getOrderSource())); // 银行渠道
		paramMap.put("opId", main.getTlrNo()); //操作员号
		paramMap.put("reqTime", new Date()); //请求时间
		paramMap.put("merOrderNo", main.getApplyNo()); //平台订单号
		paramMap.put("nbsOrderNo", mainInfo.getBankOrderNo()); //微众订单号
		paramMap.put("psCode", dscWbCommonService.getPsCodeByApplyNo(main.getApplyNo())); //产品结构编号
		paramMap.put("name", cust.getAkhxm()); //申请人姓名
		paramMap.put("idType", dscWbCommonService.codeConvert("azjlx", cust.getAzjlx())); //证件类型
		paramMap.put("idNo", cust.getAzjhm()); //证件号码
		//paramMap.put("dealerId", ""); //车商id 车商管理模式必填

		InvokeResult<WBComputerRespDTO> result = keplerOpenAPI.computer(paramDto.getApplyNo(), paramMap);
		if (result.isHasErrors()) {
			throw new BzException(result.getErrorMessage());
		}
		//数据转换 返回前端需要的数据
		DscComputerResultDto resultDto = new DscComputerResultDto();
		List<DscFeeScheduleDto> scheduleList = new ArrayList<>();
		resultDto.setSchedules(scheduleList);
		WBComputerRespDTO respDTO = (WBComputerRespDTO) result.getData();
		resultDto.setApplyNo(paramDto.getApplyNo()); // 申请编号
		resultDto.setFrze(respDTO.getLoanInitPrin()); //借款金额
		if (CollectionUtils.isNotEmpty(respDTO.getSchedules())) {
			for (WBComputerSchedulesDTO schedulesDTO : respDTO.getSchedules()) {
				DscFeeScheduleDto dto = new DscFeeScheduleDto();
				dto.setPmtTerm(schedulesDTO.getPmtTerm());
				dto.setPmtDuePrin(schedulesDTO.getPmtDuePrin());
				dto.setPmtDueInt(schedulesDTO.getPmtDueInt());
				dto.setPmtDueFee(schedulesDTO.getPmtDueFee());
				dto.setPmtDueAmt(schedulesDTO.getPmtDueAmt());
				dto.setPmtDueDate(schedulesDTO.getPmtDueDate());
				scheduleList.add(dto);
			}
		}
		LOGGER.info("根据申请编号获取借据试算最终返回结果,applyNo={},result={}", paramDto.getApplyNo(), JSON.toJSONString(resultDto));
		return resultDto;
	}



	/**
	 * 保存电子征信信息
	 * @param creditDto
	 * @return
	 */
	@Override
	public Boolean saveElecCreditInfo(DscElecCreditEvidenceDto creditDto) {
		LOGGER.info("保存客户电子征信信息开始,applyNo={}", creditDto.getApplyNo());
		AssetElecCreditInfo creditInfo = new AssetElecCreditInfo();
		creditInfo.setApplyNo(creditDto.getApplyNo());//申请编号
		creditInfo.setCreditInfo(JSON.toJSONString(creditDto.getCreditInfo()));//征信信息

		creditInfo.setCreditResult(creditDto.getCreditResult()); //征信结果
		creditInfo.setCreditType(creditDto.getCreditType());//征信类型
		creditInfo.setType(creditDto.getType());//0/电子征信 1/电子合同签约
		LOGGER.info("保存的客户信息:{}",JsonObjectUtils.objectToJson(creditInfo));
		
		AssetMainInfo.createWBTrialCreditAssetMainInfo(creditDto.getApplyNo());

		String flag = creditInfo.create();
		return Objects.equals(flag, "");
	}
	/**
	 * 保存电子合同信息
	 * @param signDto
	 * @return
	 */
	@Override
	public Boolean saveEleContractInfo(DscElecSignDto signDto) {
		InvokeResult<BaseMsgDTO> result = null;
		YxMailMessage mailMessage = null;
		try {
			LOGGER.info("保存客户电子征信信息开始,applyNo={}", signDto.getApplyNo());
			AssetElecCreditInfo creditInfo = new AssetElecCreditInfo();
			creditInfo.setType(signDto.getType());
			creditInfo.setApplyNo(signDto.getApplyNo());
			creditInfo.setCreditInfo(JSON.toJSONString(signDto.getSignInfo()));
			creditInfo.create();//存档电子合同信息
			result = wbEleContractRequest.sendResult(signDto);
			BaseMsgDTO baseMsgDTO = result == null ? null : (BaseMsgDTO) result.getData();
			if (baseMsgDTO == null || CommonConstant.FAILURE.equals(baseMsgDTO.getCode())) {
				mailMessage = new YxMailMessage();
				mailMessage.setMailText("订单【" + signDto.getApplyNo() + "】于"
						+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
						+ "签署合同失败，原因：" + (baseMsgDTO == null ? null : baseMsgDTO.getMessage())
				);
			}
		} catch (com.yixin.common.exception.BzException e) {
			LOGGER.error("微众签署合同失败,applyNo={},错误信息:{}", signDto.getApplyNo(), e.getMessage());
			throw e; //向上跑出BzException 
		} catch (Exception e) {
			mailMessage = new YxMailMessage();
			mailMessage.setMailText("订单【" + signDto.getApplyNo() + "】于"
					+ LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"))
					+ "签署合同失败，原因：" + e.getMessage()
			);
			LOGGER.error("签署合同失败,applyNo={}", signDto.getApplyNo(), e);
		} finally {
			if (mailMessage != null) {
				mailMessage.setMailSubject("####:订单【" + signDto.getApplyNo() + "】签署合同失败，请关注");
				MailMessageUtils.sendMail(mailMessage);
			}
		}
		LOGGER.info("请求银行电子签约存正处理返回,result={}",JSON.toJSONString(result));
		return Boolean.TRUE;
	}

	
	/**
     * 免预审处理
     * @param applyNo 订单编号
     * @param pretrialDTO 预审入参
     * @return 
     * @author YixinCapital -- wangwenlong
     *	       2018年7月12日 上午10:36:44
     */
	@Override
	public DscKeplerDto noPretrialHandle(String applyNo,PretrialDTO pretrialDTO){
		if(StringUtils.isBlank(applyNo)){
			throw new BzException("免预审处理失败，参数为空");
		}
		LOGGER.info("调用资管服务免预审处理  入参:{}，{}",applyNo,JsonObjectUtils.objectToJson(pretrialDTO));
		pretrialDTO.setApplyNo(applyNo);
		InvokeResult<BaseMsgDTO> pretrialRet = keplerOpenAPI.noPretrialHandle(pretrialDTO);
		if(pretrialRet==null){
			LOGGER.error("调用资管服务免预审处理，订单编号：{}，获返回值为空",applyNo);
			throw new BzException("免预审处理失败");
		}
		LOGGER.info("调用资管服务免预审处理，订单编号：{},返回结果：{}",applyNo,JsonObjectUtils.objectToJson(pretrialRet));

		if(pretrialRet.isHasErrors()){
			LOGGER.error("调用资管服务免预审处理失败，订单编号：{}",applyNo);
			throw new BzException(pretrialRet.getErrorMessage());
		}
		BaseMsgDTO msgDTO = (BaseMsgDTO)pretrialRet.getData();
		if(msgDTO == null){
			LOGGER.error("调用资管服务免预审处理失败，订单编号：{} BaseMsgDTO 为空",applyNo);
			throw new BzException("免预审处理失败");
		}

		DscKeplerDto result = new DscKeplerDto();
		result.setCode(msgDTO.getCode());
		result.setMessage(msgDTO.getMessage());
		LOGGER.info("调用资管服务免预审处理，订单编号：{},查询结果：{}",applyNo,result);
		return result;
	}

	/**
     * 同步全量数据校验
     * @param applyNo 申请编号
     * @return 
     * @author YixinCapital -- wangwenlong
     *	       2018年7月19日 下午1:04:50
     */
	@Override
	public Boolean syncAllDataVerify(String applyNo) {
		AssetMainInfo assetMainInfo = AssetMainInfo.getByApplyNo(applyNo);
		if(assetMainInfo == null || StringUtils.isBlank(assetMainInfo.getFinancialCode())){ 
			//没有向银行发起过请求，或者操作过取消，提回
			return true;
		}
		if(AssetStateEnum.DOING.getState().equals(assetMainInfo.getFirstState())
				|| AssetStateEnum.DOING.getState().equals(assetMainInfo.getLastState())){ //信审进行中
			LOGGER.error("正在信审中,不可再次进行全量同步,单号:{}", applyNo);
			throw new BzException("正在信审中,不可再次进行全量同步");
		}
		if (AssetStateEnum.SUCCESS.getState().equals(assetMainInfo.getLastState())) {
			LOGGER.error("信审已完成,不可再次进行全量同步,单号:{}", applyNo);
			throw new BzException("信审已完成,不可再次进行全量同步");
		}
		if(AssetPhaseEnum.PAYMENT_TRIAL.getPhase().equals(assetMainInfo.getAssetPhase())){ //请款阶段
			LOGGER.error("订单已处于请款阶段,不可再次进行全量同步,单号:{}", applyNo);
			throw new BzException("订单已处于请款阶段,不可再次进行全量同步");
		}
		if(AssetPhaseEnum.AFTER_LOAN.getPhase().equals(assetMainInfo.getAssetPhase())){ //贷后阶段
			LOGGER.error("订单已处于贷后阶段,不可再次进行全量同步,单号:{}", applyNo);
			throw new BzException("订单已处于贷后阶段,不可再次进行全量同步");
		}
		//BaseMsgDTO preposeMsg = asyncTask.requestPrepose(applyNo, assetMainInfo.getFinancialCode(), RequestPreposeEnum.SYNC_ORDER_ALL_DATA);
		//if(DscContant.KeplerCode.FAILD.equals(preposeMsg.getCode())){
		//	throw new BzException(preposeMsg.getMessage());
		//}
		return true;
	}
}
