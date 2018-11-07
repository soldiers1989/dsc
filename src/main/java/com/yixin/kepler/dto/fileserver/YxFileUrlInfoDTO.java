package com.yixin.kepler.dto.fileserver;/**
 * Created by liushuai2 on 2018/6/12.
 */

import java.io.Serializable;

/**
 * Package : com.yixin.kepler.dto.fileserver
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月12日 17:23
 */
public class YxFileUrlInfoDTO implements Serializable{
    private String fileName;
    private String fileUrl;

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getFileUrl() {
        return fileUrl;
    }

    public void setFileUrl(String fileUrl) {
        this.fileUrl = fileUrl;
    }
}
