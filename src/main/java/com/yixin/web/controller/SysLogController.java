package com.yixin.web.controller;

import com.yixin.common.system.domain.BaseEntity;
import com.yixin.common.system.querychannel.QueryChannelService;
import com.yixin.common.utils.InvokeResult;
import com.yixin.common.utils.Page;
import com.yixin.web.dto.AttachmentConditionDto;
import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Map;

/**
 * @author sukang
 */

@RestController
@RequestMapping(value = "/sys/log")
public class SysLogController {

    private final Logger logger = LoggerFactory.getLogger(getClass());


    @PostMapping(value = "/get-all")
    public InvokeResult<Page<Map<String, Object>>> getSysLogs(
            @RequestBody AttachmentConditionDto conditionDto){
        InvokeResult<Page<Map<String, Object>>> invokeResult = new InvokeResult<>();

        List<Object>  parameters = new ArrayList<>();

        StringBuilder hql = new StringBuilder(
                " SELECT new map(abt.action as action,"
                        + " DATE_FORMAT(abt.createTime,'%Y-%m-%d %H:%i:%s') AS createTime, "
                        + " abt.method AS method, abt.params AS params,"
                        + " abt.name AS name,abt.ipAddr AS ipAddr)"
                        + " FROM DscSysLog AS abt WHERE 1=1");
        int index = 0;
        if (conditionDto.getStartDate() != null) {
            hql.append(" AND abt.createTime >= ?".concat(String.valueOf(++index)));
            parameters.add(conditionDto.getStartDate());
        }
        if (conditionDto.getEndDate() != null) {
            hql.append(" AND abt.createTime <= ?".concat(String.valueOf(++index)));
            parameters.add(conditionDto.getEndDate());
        }
        hql.append(" ORDER BY abt.createTime DESC");
        String sql = hql.toString();
        logger.debug("查询银行交易记录sql为{}",sql);

        QueryChannelService queryChannel = BaseEntity.getQueryChannel();
        @SuppressWarnings("unchecked")
        Page<Map<String,Object>> pagedList = queryChannel.createJpqlQuery(sql)
                .setParameters(parameters)
                .setPage(conditionDto.getCurrentPage(),10)
                .pagedList();
        invokeResult.success().setData(pagedList);
        return invokeResult;
    }


}
