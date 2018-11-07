package com.yixin.kepler.common;/**
 * Created by liushuai2 on 2018/6/7.
 */

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import java.util.Map;
import java.util.regex.Pattern;

/**
 * Package : com.yixin.kepler.common
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月07日 10:43
 */
public class RegexUtil {
    private static final Logger logger = LoggerFactory.getLogger(RegexUtil.class);

    public static boolean pattern(String pattern, Object val){
        Assert.notNull(val, "val is empty");
        Assert.notNull(pattern, "pattern is empty");

        String str = val.toString();
        boolean isMatch = Pattern.matches(pattern, str);
        return isMatch;
    }
}
