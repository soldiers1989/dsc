package com.yixin.kepler.common;/**
 * Created by liushuai2 on 2018/6/6.
 */


/**
 * Package : com.yixin.kepler.depends
 *
 * @author YixinCapital -- liushuai2
 *         2018年06月06日 17:14
 */
public class SerialNumberUtil {

    /**
     * 生成序列号
     * @return
     */
    public synchronized static String generate(){
        return System.nanoTime() + "";
    }

}
