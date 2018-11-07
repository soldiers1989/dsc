package com.yixin.kepler.core.config;/**
 * Created by liushuai2 on 2018/6/10.
 */

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Package : com.yixin.kepler.core.config
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月10日 12:39
 */
@Configuration
@ConfigurationProperties(prefix = "cmbc")
public class CMBCConfig {
	
	/**
	 * 民生银行osb请求路径
	 */
    private String osbReqUrl;
    
    /**
     * 
     */
    private String productCode;
    
    
    /**
     * 商户号
     */
    private String merchantNum;
    
    private String systemCode;


	public void setOsbReqUrl(String osbReqUrl) {
		this.osbReqUrl = osbReqUrl;
	}



	public void setProductCode(String productCode) {
		this.productCode = productCode;
	}



	public void setMerchantNum(String merchantNum) {
		this.merchantNum = merchantNum;
	}



	public String getOsbReqUrl() {
		return osbReqUrl;
	}



	public String getProductCode() {
		return productCode;
	}



	public String getMerchantNum() {
		return merchantNum;
	}



	public String getSystemCode() {
		return systemCode;
	}



	public void setSystemCode(String systemCode) {
		this.systemCode = systemCode;
	}
    

    
    
    
    
    
    
    
    
    
    
    
    
    
    
    
  
}
