package com.yixin.kepler.common;

import java.util.Map;

import org.springframework.stereotype.Component;


/**
 * Package : com.yixin.creditfront.test.api.file
 *
 *      注意：如果需要引入新的properties文件，需要新增变量，并在base-context.xml中PropertiesFactory配置
 *           然后对外提供一个静态的get方法
 *
 *
 * @author YixinCapital -- liushuai2
 *         2017年08月08日 14:43
 */
@Component
public class PropertiesFactory {
    /**
     * ftp和sftp相关的配置信息，对应的文件sftp-server.properties
     */
	private static Map<String, String> sftpServerProperties;
    /**
     * 对应文件rest-prefix.properties
     */
    private static Map<String, String> restPrefixProperties;

    private static Map<String, String> rabbitmqProperties;



    public static Map<String, String> getSftpServerProperties() {
        return sftpServerProperties;
    }

    public void setSftpServerProperties(Map<String, String> sftpServerProperties) {
		PropertiesFactory.sftpServerProperties = sftpServerProperties;
	}


    public void setRestPrefixProperties(Map<String, String> restPrefixProperties) {
        PropertiesFactory.restPrefixProperties = restPrefixProperties;
    }


    public static Map<String, String> getRestPrefixProperties() {
        return restPrefixProperties;
    }


    public static Map<String, String> getRabbitmqProperties() {
        return rabbitmqProperties;
    }

    public void setRabbitmqProperties(Map<String, String> rabbitmqProperties) {
        PropertiesFactory.rabbitmqProperties = rabbitmqProperties;
    }
}
