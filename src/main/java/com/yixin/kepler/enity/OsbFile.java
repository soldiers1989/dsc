package com.yixin.kepler.enity;/**
 * Created by liushuai2 on 2017/8/8.
 */

import com.google.common.collect.Lists;
import com.yixin.common.exception.BzException;
import com.yixin.common.system.domain.BZBaseEntiy;
import com.yixin.common.system.util.Label;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * Package : com.yixin.creditfront.core.file
 *
 * @author YixinCapital -- liushuai2
 * 2017年08月08日 14:00
 */

@Entity
@Table(name = "k_osb_file")
public class OsbFile extends BZBaseEntiy {
    /**
     * TRAN_NO	交易流水	varchar(128)
     * FILE_ID	文件ID	varchar(32)
     * FILE_NAME	文件名称	varchar(64)
     * MD5	md5值	varchar(64)
     * FILE_SIZE	文件大小，单位bit	int(32)
     * SOURCE_FILE	是否是源文件，0：不是，1：是，默认0	BIT(1)
     */

    @Label(name = "交易流水号id")
    @Column(name = "tran_no", length = 128, nullable = false)
    private String tranNo;

    @Label(name = "文件id")
    @Column(name = "file_id", length = 128)
    private String fileId;


    @Label(name = "文件名称")
    @Column(name = "file_name", length = 256)
    private String fileName;

    @Label(name = "文件md5")
    @Column(name = "md5", length = 64)
    private String md5;

    @Label(name = "文件大小")
    @Column(name = "file_size", length = 32)
    private int fileSize;

    @Label(name = "是否是源文件0：不是，1：是，默认0")
    @Column(name = "source_file", length = 1)
    private Boolean sourceFile = Boolean.valueOf(false);
    ;

    public String getTranNo() {
        return tranNo;
    }

    public void setTranNo(String tranNo) {
        this.tranNo = tranNo;
    }

    public String getFileId() {
        return fileId;
    }

    public void setFileId(String fileId) {
        this.fileId = fileId;
    }

    public String getFileName() {
        return fileName;
    }

    public void setFileName(String fileName) {
        this.fileName = fileName;
    }

    public String getMd5() {
        return md5;
    }

    public void setMd5(String md5) {
        this.md5 = md5;
    }

    public int getFileSize() {
        return fileSize;
    }

    public void setFileSize(int fileSize) {
        this.fileSize = fileSize;
    }

    public Boolean getSourceFile() {
        return sourceFile;
    }

    public void setSourceFile(Boolean sourceFile) {
        this.sourceFile = sourceFile;
    }


    public OsbFile() {
    }

    public OsbFile(String tranNo, String fileId, String fileName, String md5, int fileSize, boolean sourceFile) {
        this.tranNo = tranNo;
        this.fileId = fileId;
        this.fileName = fileName;
        this.md5 = md5;
        this.fileSize = fileSize;
        this.sourceFile = sourceFile;
    }

    /**
     * 根据交易流水号获取对应的文件信息
     *
     * @param tranNo
     * @return
     */
    public static List<OsbFile> getFiles(String tranNo) {
        return findByProperty(OsbFile.class, "tranNo", tranNo);
    }


    /**
     * 通过流水号查询最新的文件信息数据
     *
     * @param tranNo
     * @return
     * @throws BzException
     */
    public static OsbFile getOsbFilesByTranNo(String tranNo) throws BzException {
        List<OsbFile> list = null;
        List<Object> conditions = Lists.newArrayList();
        try {
            StringBuilder jpql = new StringBuilder(
                    "select file from OsbFile file where file.tranNo= ?1 and file.sourceFile is false order by UPDATE_TIME DESC");
            conditions.add(tranNo);
            list = getRepository().createJpqlQuery(jpql.toString()).setParameters(conditions).list();
        } catch (Exception e) {
            logger.error("通过流水号查询文件信息表出错", e);
            throw new BzException("");
        }
        if (null != list && list.size() > 0) {
            return list.get(0);
        } else {
            return null;
        }
    }


    @Override
    public String toString() {
        return "OsbFile{" +
                "tranNo='" + tranNo + '\'' +
                ", fileId='" + fileId + '\'' +
                ", fileName='" + fileName + '\'' +
                ", md5='" + md5 + '\'' +
                ", fileSize=" + fileSize +
                ", sourceFile='" + sourceFile + '\'' +
                '}';
    }

    public static OsbFile getByApplyNoAndName(String fileName, String applyNo) {
        String hql = " SELECT file FROM OsbFile file WHERE " +
                " file.bzId = ?1 AND file.fileName = ?2" +
                " and file.deleted is false order by file.createTime desc ";

        List<Object> params = new ArrayList<>(2);
        params.add(applyNo);
        params.add(fileName);

        return getRepository().createJpqlQuery(hql).setParameters(params).singleResult();
    }
}
