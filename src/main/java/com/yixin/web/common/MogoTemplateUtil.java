package com.yixin.web.common;

import com.yixin.common.system.ioc.SpringContextUtil;
import com.yixin.dsc.dto.order.DscSyncDTO;
import com.yixin.dsc.dto.rule.DscMatchResultDto;
import com.yixin.web.dto.ConditionBaseDto;
import org.springframework.data.domain.Sort;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;

import java.util.List;

/**
 * mogo工具类
 * @author sukang
 */
public class MogoTemplateUtil {


    private static MongoTemplate mongoTemplate;

    static {
        if (mongoTemplate == null){
            mongoTemplate = SpringContextUtil.getBean(MongoTemplate.class);
        }
    }


    public static List<DscSyncDTO> getInfo(ConditionBaseDto conditionBaseDto){
        Query mogoQuery = new Query(Criteria.where("applyNo").is(conditionBaseDto.getApplyNo()));
        return mongoTemplate.find(mogoQuery, DscSyncDTO.class);
    }


    public static List<DscMatchResultDto> getMatchResults(ConditionBaseDto conditionBaseDto){
        Query mogoQuery = new Query(Criteria.where("applyNo").is(conditionBaseDto.getApplyNo()));
        mogoQuery.with(Sort.by(new Sort.Order(Sort.Direction.DESC,"createTime")));
        return mongoTemplate.find(mogoQuery, DscMatchResultDto.class);
    }



}
