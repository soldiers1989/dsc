package com.yixin.web.controller;

import com.alibaba.fastjson.JSON;
import com.yixin.common.utils.InvokeResult;
import com.yixin.kepler.enity.AssetFinanceInfo;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.lang.reflect.Method;
import java.util.*;

/**
 * dsc内部运维公共控制器
 * @author YixinCapital -- chenjiacheng
 *         2018年08月06日 11:59
 **/

@RestController
@RequestMapping(value = "/common")
public class CommonDefaultController {


	private final static Logger logger = LoggerFactory.getLogger(CommonDefaultController.class);

	/**
	 * 根据入参查询对应的下拉列表
	 * @param source 入参，要查询的枚举类的包路径
	 * @return 下拉列表
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/6 12:06
	 */
	@SuppressWarnings("unchecked")
	@RequestMapping(value = "/getOptionGroups")
	public InvokeResult<Object> getOptionGroups(String source) {
		InvokeResult<Object> invokeResult = new InvokeResult<>();
		if (StringUtils.isBlank(source)) {
			return invokeResult.failure("入参为空");
		}
		if (logger.isDebugEnabled()) {
			logger.debug("根据入参开始查询对应的下拉列表,params={}", source);
		}
		
		try {
			Class clazz = Class.forName(source);
			Method method = clazz.getDeclaredMethod("values");
			Enum[] list = (Enum[]) method.invoke(clazz);
			Map<String, Object> resultMap = new LinkedHashMap<>();
			for (Enum enums : list) {
				Method nameMethod = enums.getClass().getDeclaredMethod("getName");
				Method typeMethod = enums.getClass().getDeclaredMethod("getType");
				resultMap.put(nameMethod.invoke(enums).toString(), typeMethod.invoke(enums));
			}
			invokeResult.setData(resultMap);
		} catch (Exception e) {
			logger.error("查询下拉列表出错,params={}", source, e);
			return invokeResult.failure("查询下拉列表出错,请检查入参");
		}
		if (logger.isDebugEnabled()){
			logger.debug("根据入参查询对应的下拉列表结束,result={}", JSON.toJSONString(invokeResult));
		}
		return invokeResult;
	}

	/**
	 * 获取资方列表
	 * @return 资方列表
	 * @author YixinCapital -- chenjiacheng
	 *             2018/8/6 14:33
	 */
	@RequestMapping(value = "/getCapitalList")
	public InvokeResult<Object> getCapitalList() {
		InvokeResult<Object> invokeResult = new InvokeResult<>();
		List<AssetFinanceInfo> config = AssetFinanceInfo.findAll(AssetFinanceInfo.class);
		List<Map<String, String>> dataList = new ArrayList<>();
		if (CollectionUtils.isNotEmpty(config)) {
			for (AssetFinanceInfo info : config) {
				Map<String, String> dataMap = new HashMap<>(3);
				dataMap.put("name", info.getCode());
				dataMap.put("code", info.getCode());
				dataMap.put("id", info.getId());
				dataList.add(dataMap);
			}
		}
		invokeResult.setData(dataList);
		return invokeResult;
	}






}
