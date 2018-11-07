package com.yixin.kepler.core.attachment;/**
 * Created by liushuai2 on 2018/6/15.
 */

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Package : com.yixin.kepler.core.attachment
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月15日 10:12
 */
@Configuration
@ConfigurationProperties(prefix = "spring")
@Component
public class SftpConfig {
    private Map<String, String> sftp;


    public Map<String, String> getSftp() {
        return sftp;
    }

    public void setSftp(Map<String, String> sftp) {
        this.sftp = sftp;
    }
}
