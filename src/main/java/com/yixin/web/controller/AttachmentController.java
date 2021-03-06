package com.yixin.web.controller;






import java.io.File;
import java.io.IOException;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.io.FileUtils;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.yixin.common.utils.InvokeResult;
import com.yixin.common.utils.JsonObjectUtils;
import com.yixin.common.utils.Page;
import com.yixin.dsc.dto.ExportProductInfoDTO;
import com.yixin.dsc.util.ExcelUtils;
import com.yixin.kepler.common.enums.BankPhaseEnum;
import com.yixin.kepler.core.constant.CommonConstant;
import com.yixin.kepler.dto.BaseMsgDTO;
import com.yixin.kepler.enity.AssetFinanceInfo;
import com.yixin.kepler.enity.AssetProductFinancialRel;
import com.yixin.kepler.enity.ProductInfoOperationLog;
import com.yixin.web.anocation.SysLog;
import com.yixin.web.dto.AttachmentConditionDto;
import com.yixin.web.dto.ConditionBaseDto;
import com.yixin.web.service.impl.AttachmentServiceImpl;
import com.yixin.web.service.impl.BankTransLogServiceImpl;
import com.yixin.web.service.impl.ProductServiceImpl;

import net.sf.json.JSONObject;


/**
 * 
 * @author sukang
 * @date 2018-08-06 17:55:10
 */
@RestController
@RequestMapping("sys/")
public class AttachmentController {
	
	private final Logger logger = LoggerFactory.getLogger(getClass());

	@Resource
	private AttachmentServiceImpl attachmentServiceImpl;

	@Resource
	private BankTransLogServiceImpl bankTransLogServiceImpl;

	@Resource
	private ProductServiceImpl productServiceImpl;



	@ExceptionHandler
	@ResponseBody
	public InvokeResult<Object> exception(Exception e, HttpServletRequest request){

		logger.error("请求路径为{},程序异常",request.getRequestURL(),e);
		InvokeResult<Object> objectInvokeResult = new InvokeResult<>();
		objectInvokeResult.failure("程序异常");
		return objectInvokeResult;
	}




	@PostMapping(value = "/bank-trans/list")
	public InvokeResult<Page<Map<String, Object>>> getBankTransList(
			@RequestBody AttachmentConditionDto aConditionDto){
		InvokeResult<Page<Map<String, Object>>> invokeResult = new InvokeResult<>();

		Page<Map<String, Object>> bankTransList = bankTransLogServiceImpl.getBankTransList(aConditionDto);
		invokeResult.success().setData(bankTransList);
		return invokeResult;
	}
	

	/**
	 * 获取所有订单的文件上传记录
	 */
	@RequestMapping(value = "/attachment/get-all",method = RequestMethod.POST)
	public InvokeResult<Page<Map<String, Object>>> getOsbLogs(
			@RequestBody AttachmentConditionDto aConditionDto){

		InvokeResult<Page<Map<String, Object>>> invokeResult = new InvokeResult<>();
		Page<Map<String, Object>> all = attachmentServiceImpl.getAll(aConditionDto);
		invokeResult.success().setData(all);
		return invokeResult;
	}


	@SysLog(action = "文件重新上传")
	@RequestMapping(value = "/attachment/file-upload/{applyNo}/{phase}",method = RequestMethod.POST)
	public InvokeResult<BaseMsgDTO> attachmentUpload(
			@PathVariable("applyNo") String applyNo,
			@PathVariable("phase") String phase){
		
		InvokeResult<BaseMsgDTO> invokeResult = new InvokeResult<>();
    	
		BankPhaseEnum bankPhaseEnum = BankPhaseEnum.get(phase);
		if (bankPhaseEnum == null) {
			return invokeResult.failure("未知的参数类型");
		}
		attachmentServiceImpl.attachmentUpload(applyNo,bankPhaseEnum.getPhase());
		return invokeResult.success();
	}
	
	
	/**
	 * 获取推送结算失败的记录
	 */
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/settle-failure",method = RequestMethod.POST)
	public InvokeResult<Page<Map<String, Object>>> getSettleFailure(
			@RequestBody ConditionBaseDto conditionBaseDto){

		InvokeResult<Page<Map<String, Object>>> invokeResult = new InvokeResult<>();
		Page<Map<String, Object>> settleFailure = bankTransLogServiceImpl.getSettleFailureV1(conditionBaseDto);
		invokeResult.success().setData(settleFailure);
		return invokeResult;
	}
	
	
	/**
	 * 重新发送结算数据
	 */
	@SysLog(action = "重发结算数据")
	@RequestMapping(value = "/settle-push/{applyNo}",method = RequestMethod.POST)
	public InvokeResult<Boolean> pushSettle(
			@PathVariable("applyNo") String applyNo){

		InvokeResult<Boolean> result = new InvokeResult<>();
        if(StringUtils.isBlank(applyNo)){
            return result.failure("申请编号参数不合法");
        }
        return bankTransLogServiceImpl.pushSettle(applyNo);
	}


	@SysLog(action = "产品信息修改")
	@RequestMapping(value = "/product-update",method = RequestMethod.POST)
	public InvokeResult<Boolean> updateFinancialProduct(
			@RequestBody AssetProductFinancialRel aRel,HttpServletRequest req){
		// add by zongzhiping 2018年10月29日11:07:16
		ProductInfoOperationLog.createLog(aRel,"修改产品信息,停用/启用", aRel.getProductCode());
		return productServiceImpl.updateFinancialProduct(aRel);
	}
	
	@SysLog(action = "产品信息添加")
	@RequestMapping(value = "/product-create",method = RequestMethod.POST)
	public InvokeResult<Boolean> createFinancialProduct(
			@RequestBody AssetProductFinancialRel aRel,HttpServletRequest request){
		logger.info("product-create获取到当前登录信息:{}",JSON.toJSON(aRel));
		InvokeResult<Boolean> invokeResult = new InvokeResult<>();

		if (StringUtils.isBlank(aRel.getProductCode())) {
			return invokeResult.failure("产品编号为空");
		}
		if (StringUtils.isBlank(aRel.getFinancialCode())) {
			return invokeResult.failure("资方编号为空");
		}
		if (StringUtils.isBlank(aRel.getProductVersion())) {
			return invokeResult.failure("产品版本号为空");
		}
		if (StringUtils.isBlank(aRel.getProductName())) {
			return invokeResult.failure("产品名称为空");
		}
		AssetFinanceInfo financeInfo = AssetFinanceInfo.getByCode(aRel.getFinancialCode());
		if (financeInfo == null){
			return invokeResult.failure("未找到资方");
		}
		aRel.setFinancialId(financeInfo.getId());
		//add by zongzhiping  2018年10月29日11:06:59
		ProductInfoOperationLog.createLog(aRel, "新增产品", aRel.getProductCode());
		productServiceImpl.createFinancialProduct(aRel); 
		
		return invokeResult.success();
	}
	
	
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/product-all",method = RequestMethod.POST)
	public InvokeResult<Page<List<Map<String, Object>>>> getFinancialProduct(
			@RequestParam(value = "productCode",required = false) String productCode,
			@RequestParam(value = "financialCode",required = false) String financialCode,
			@RequestParam(value = "enable",required = false) String enable,
			@RequestParam(value = "currentPage",defaultValue = "1",required = false) String currentPage){

		InvokeResult<Page<List<Map<String, Object>>>> invokeResult = new InvokeResult<>();
		Page<List<Map<String, Object>>> financialProduct = productServiceImpl.getFinancialProduct(productCode, financialCode, enable, currentPage);
		invokeResult.setData(financialProduct);
		return invokeResult;
	}



	@PostMapping(value = "/select-list")
	public InvokeResult<Object> getSelectList(){
		InvokeResult<Object> invokeResult = new InvokeResult<>();
		Map<String, JSONObject> hashMap = new HashMap<>(2);
		JSONObject phaseJson = new JSONObject();
		phaseJson.put("初审",BankPhaseEnum.FIRST_TRIAL.getPhase());
		phaseJson.put("终审",BankPhaseEnum.LAST_TRIAL.getPhase());
		phaseJson.put("贷后",BankPhaseEnum.AFTER_LOAN.getPhase());
		phaseJson.put("请款", BankPhaseEnum.PAYMENT.getPhase());
		phaseJson.put("推送结算",BankPhaseEnum.PUSHFILE.getPhase());
		hashMap.put("phase", phaseJson);

		JSONObject osbStatus = new JSONObject();
		osbStatus.put("已处理",CommonConstant.DEAL_STATUS_DEALED);
		osbStatus.put("处理中",CommonConstant.DEAL_STATUS_DEALING);
		osbStatus.put("无效流程",CommonConstant.DEAL_STATUS_INVALID);
		osbStatus.put("失败",CommonConstant.DEAL_STATUS_OSB_FAILURE);
		osbStatus.put("未处理",CommonConstant.DEAL_STATUS_UNDEAL);
		hashMap.put("osbStatus", osbStatus);

		invokeResult.success().setData(hashMap);
		return invokeResult;
	}
	/**
	 * 导出选择的产品列表
	 * 
	 * @param productCode
	 * @param financialCode
	 * @param enable
	 * @param response
	 * @return 
	 * @author YixinCapital -- zongzhiping
	 *	       2018年10月26日 上午10:28:57
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/exportSelectedProductList", method = RequestMethod.GET)
	public void exportSelectedProductList(
			@RequestParam(value = "productCode", required = false) String productCode,
			@RequestParam(value = "financialCode", required = false) String financialCode,
			@RequestParam(value = "enable", required = false) String enable, HttpServletResponse response) {
		OutputStream  bos = null;
		logger.info("导入产品数据入参:productCode:{},financialCode:{},enable:{}",productCode,financialCode,enable);
		List<ExportProductInfoDTO> selectFinancialProduct = productServiceImpl.getSelectFinancialProduct(productCode,financialCode, enable);
		String[] title = { "产品编号", "产品名称", "关联资方code"};
		byte[] data = ExcelUtils.export("资方产品关系维护表", title, selectFinancialProduct);
		try {
			response.setHeader("Content-Disposition", "attachment;Filename=" +URLEncoder.encode("资方产品关系维护表", "UTF-8")+".xls");
			bos = response.getOutputStream();
			bos.write(data);
			bos.flush();
		} catch (IOException ex) {
			logger.info("导出数据异常:{}", ex);
		} finally{
			try {
				if (bos != null) {
					bos.close();
				}
			} catch (IOException e) {
				logger.error(e.getMessage(), e);
			}
		}
	}
	
}
