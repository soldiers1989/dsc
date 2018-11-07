package com.yixin.kepler.component;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.yixin.kepler.common.RedisClientUtil;
import com.yixin.kepler.enity.SysDict;

@Component
public class RedisCacheUtil {

	@Autowired
	private RedisClientUtil redisClientUtil;
	
	public static final  String  SysDictKey= "SysDictKey-";
	
	/**
	 * 初始化table：sys_dict 数据
	 * 
	 * 根据不同的银行定义不同的hash表，例如 SysDictKey-CMBC,SysDictKey-WeBank
	 * 
	 * filedName: alix的字段类型-alix的字段值
	 * filedValue: 银行所需的值
	 * 
	 * 
	 * HMSET SysDictKey-CMBC asqrzw-1 1;
	 * HGET SysDictKey-CMBC asqrzw-1  "1"
	 * 
	 */
	public void initSysDictData(){
		
		//资方列表
		List<String> financialCodes = SysDict.getAllDictBankCode();
		
		for (String financialCode : financialCodes) {
			List<SysDict> sysDicts = SysDict.getSysDicByBank(financialCode);
			String key = SysDictKey.concat(financialCode);
			Map<String, String> value = new HashMap<>(200);
			sysDicts.forEach( t -> {
				value.put(t.getFiledCode().concat("-").concat(t.getDicCode()), 
						t.getBankCode());
			}); 
			
			redisClientUtil.setFieldValue(key, value);
		}
	}

}
