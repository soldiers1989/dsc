package com.yixin.web.utils;

import java.io.IOException;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.log4j.Log4j;


/**
 * 描述：Json 转换
 * author wangshuang   2016/9/6 13:56.
 */
@Log4j
public class Json {

    /**
     * 功能描述：将对象转为JSON字符串
     * @author wangshuang
     * @date 2016/9/6  14:53
     * @param object
     */
    public static String toJsonString (Object object) throws JsonProcessingException {
        ObjectMapper mapper = new ObjectMapper();
        return mapper.writeValueAsString(object);
    }


    /**
     * 功能描述：JSON转Object
     * @author wangshuang
     * @date 2016/9/6  14:53
     * @param clazz
     * @param jsonString
     */
    public static Object toObject (String jsonString, Class<?> clazz) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        Object obj = mapper.readValue(jsonString, clazz);
        return obj;
    }


    public static JsonNode toJsonNode (String jsonString) throws IOException{
        ObjectMapper mapper = new ObjectMapper();
        JsonNode jsonNode = mapper.readTree(jsonString);
        return jsonNode;
    }


}