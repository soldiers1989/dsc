package com.yixin.kepler.v1.datapackage.dto;
import java.io.Serializable;

/**
 * 压缩请求项DTO
 * Package : com.yixin.kepler.v1.datapackage.dto
 * 
 * @author YixinCapital -- chen.lin
 *		   2018年9月28日 上午11:56:50
 *
 */
public class CompressReqItemDTO implements Serializable{
   
	/**
	 * 
	 * @author YixinCapital -- chen.lin
	 *		   2018年9月28日 上午11:56:26
	 *
	 */
	private static final long serialVersionUID = 2076320430343578771L;

	/**
     * 源文件id
     */
    private String fileId;
    
    /**
     * 缩放
     */
    private double scale;
    
    /**
     * 文件最大值
     */
    private long maxSize;


    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public double getScale() {
        return scale;
    }

    public void setScale(double scale) {
        this.scale = scale;
    }

    public long getMaxSize() {
        return maxSize;
    }

    public void setMaxSize(long maxSize) {
        this.maxSize = maxSize;
    }
}
