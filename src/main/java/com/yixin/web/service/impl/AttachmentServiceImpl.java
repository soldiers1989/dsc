package com.yixin.web.service.impl;


import com.yixin.common.utils.Page;
import com.yixin.kepler.core.domain.AsyncTask;
import com.yixin.kepler.dto.OsbAttachmentDTO;
import com.yixin.web.dto.AttachmentConditionDto;
import com.yixin.web.service.AttachmentService;
import com.yixin.web.service.base.BaseService;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Map;

/**
 * @author sukang
 * DATE_FORMAT(ofl.createTime,'%Y-%m-%d %H:%i:%s')
 */

@Service
public class AttachmentServiceImpl extends BaseService implements AttachmentService{

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @Resource
    private AsyncTask asyncTask;

    @Override
    public Page<Map<String,Object>> getAll(AttachmentConditionDto aConditionDto) {

        ArrayList<Object> parameters = new ArrayList<>(6);

        StringBuilder hql = new StringBuilder(
                "SELECT new map(ami.applyNo as applyNo,ami.financialCode as bankCode,"
                        + " ofl.bzType as phase,ofl.dealStatus as dealStatus,"
                        + " CASE ofl.dealStatus WHEN 0 THEN '未处理' WHEN 1 THEN '处理中' WHEN 2 THEN"
                        + " '处理成功' WHEN 4 THEN '处理失败' ELSE '未知' END as statusInfo,"
                        + " DATE_FORMAT(ofl.createTime,'%Y-%m-%d %H:%i:%s') as createTime)"
                        + " FROM OsbFileLog ofl,AssetMainInfo ami"
                        + " WHERE ofl.deleted = false AND ami.deleted =false and ami.applyNo = ofl.bzId ");

        int index = 0;
        if (StringUtils.isNotEmpty(aConditionDto.getApplyNo())) {
            hql.append(" AND ofl.bzId = ?".concat(String.valueOf(++index)));
            parameters.add(aConditionDto.getApplyNo().trim());
        }
        if (StringUtils.isNotEmpty(aConditionDto.getFinancialCode())) {
            hql.append(" AND ami.financialCode = ?".concat(String.valueOf(++index)));
            parameters.add(aConditionDto.getFinancialCode());
        }
        if (StringUtils.isNotEmpty(aConditionDto.getPhase())) {
            hql.append(" AND ofl.bzType = ?".concat(String.valueOf(++index)));
            parameters.add(aConditionDto.getPhase());
        }
        if (aConditionDto.getStartDate() != null) {
            hql.append(" AND ofl.createTime >= ?".concat(String.valueOf(++index)));
            parameters.add(aConditionDto.getStartDate());
        }
        if (aConditionDto.getEndDate() != null) {
            hql.append(" AND ofl.createTime <= ?".concat(String.valueOf(++index)));
            parameters.add(aConditionDto.getEndDate());
        }

        if (StringUtils.isNotEmpty(aConditionDto.getStatus())) {
            hql.append(" AND ofl.dealStatus = ?".concat(String.valueOf(++index)));
            parameters.add(Integer.valueOf(aConditionDto.getStatus()));
        }
        hql.append(" ORDER BY ofl.createTime DESC");
        String sql = hql.toString();

        logger.debug("查询文件上传的结果sql为{}",sql);
		Page<Map<String,Object>> pagedList = queryChannel.createJpqlQuery(sql)
                .setParameters(parameters)
                .setPage(aConditionDto.getCurrentPage(),10)
                .pagedList();
        List<Map<String, Object>> data = pagedList.getData();
        //logger.info("影像件推送查询结果：result={}",JsonObjectUtils.objectToJson(data));
        data.sort(new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                int e = ((String) o2.get("applyNo")).compareTo((String) o1.get("applyNo"));
                //int e1 = e == 0 ? ((Integer)o1.get("dealStatus")).toString().compareTo(((Integer)o2.get("dealStatus")).toString()) : e;
                return e==0?((String) o2.get("createTime")).compareTo((String) o1.get("createTime")):e;
            }
        });
        String canone = "retry";
        for (Map<String, Object> map : data) {
            if(!canone.equals(map.get("applyNo")) && ("0".equals(((Integer)map.get("dealStatus")).toString()) || "4".equals(((Integer)map.get("dealStatus")).toString()) ) ){
                map.put("retry",true);
                canone=(String)map.get("applyNo");
            }else{
                map.put("retry",false);
            }
        }
        data.sort(new Comparator<Map<String, Object>>() {
            @Override
            public int compare(Map<String, Object> o1, Map<String, Object> o2) {
                int e = ((String) o2.get("createTime")).compareTo((String) o1.get("createTime"));
                return e;
            }
        });

        return pagedList;
    }


    @Override
    public void attachmentUpload(String applyNo, String phase) {
        OsbAttachmentDTO osbAttachmentDTO = new OsbAttachmentDTO();
        osbAttachmentDTO.setBzId(applyNo);
        osbAttachmentDTO.setBzType(phase);
        asyncTask.uploadAttachment(osbAttachmentDTO);
    }
}
