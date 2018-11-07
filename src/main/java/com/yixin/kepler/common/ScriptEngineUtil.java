package com.yixin.kepler.common;/**
 * Created by liushuai2 on 2017/8/16.
 */

import java.util.Iterator;
import java.util.Map;

import javax.script.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.Assert;

import com.google.common.collect.Maps;

/**
 *
 * 解析script表达式
 *
 *
 * Package : com.yixin.creditfront.utils
 *
 * @author YixinCapital -- liushuai2
 *         2017年08月16日 11:54
 */
public class ScriptEngineUtil {
    private static final Logger logger = LoggerFactory.getLogger(ScriptEngineUtil.class);

    public static Object eval(String elStr){
        Assert.notNull(elStr);
        return eval(elStr, null);
    }

    /**
     * 计算表达式的值
     * @param vals      内容值  key value
     * @param script
     * @return
     */
    public static Object eval(String script, Map<String, Object> vals){
        Assert.notNull(script, "执行脚本不能为空");
        //根式必须为${}
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        if(vals != null && vals.size() > 0){
            Iterator<Map.Entry<String, Object>> it = vals.entrySet().iterator();
            while(it.hasNext()){
                Map.Entry<String, Object> entry = it.next();
                engine.put(entry.getKey(), entry.getValue());
            }
        }
        Object eval = null;
        try {
            eval = engine.eval(script);
        } catch (ScriptException e) {
            //计算错误的话
            logger.error("执行js脚本异常，异常信息为",e);
        }
        return eval;
    }

    public static Object eval(String script, String function, Map<String, Object> vals){

        Assert.notNull(script, "script is empty");
        Assert.notNull(function, "function is empty");
        try{
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");
            if(vals != null && vals.size() > 0){
                Iterator<Map.Entry<String, Object>> it = vals.entrySet().iterator();
                while(it.hasNext()){
                    Map.Entry<String, Object> entry = it.next();
                    engine.put(entry.getKey(), entry.getValue());
                }
            }
            engine.eval(script);
            Invocable invocable = (Invocable) engine;
            Object result = (Object) invocable.invokeFunction(function);
            return result;
        }catch (Exception e){
            logger.error("执行js脚本方法失败", e);
        }
        return null;

    }

    public static Object eval(String script, String function, Object... args){

        Assert.notNull(script, "script is empty");
        Assert.notNull(function, "function is empty");
        try{
            ScriptEngineManager manager = new ScriptEngineManager();
            ScriptEngine engine = manager.getEngineByName("js");
            engine.eval(script);
            Invocable invocable = (Invocable) engine;
            Object result = (Object) invocable.invokeFunction(function, args);
            return result;
        }catch (Exception e){
            logger.error("执行js脚本方法失败", e);
        }
        return null;

    }
}
