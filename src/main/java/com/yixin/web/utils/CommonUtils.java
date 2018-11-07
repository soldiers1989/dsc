package com.yixin.web.utils;

import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;

/**
 *
 *@Package ：com.yixin.utils
 *
 *@author YixinCapital--huangbowen
 *       2017/12/13 10:32
 *
 */

public class CommonUtils {

    public static HttpHeaders getHttpJSONHeaders(){
        HttpHeaders headers = new HttpHeaders();
        MediaType type = MediaType.parseMediaType("application/json; charset=UTF-8");
        headers.setContentType(type);
        headers.add("Accept", MediaType.APPLICATION_JSON.toString());
        return headers;
    }
}
