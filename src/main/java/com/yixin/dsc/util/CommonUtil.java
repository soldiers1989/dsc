package com.yixin.dsc.util;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.apache.commons.lang3.StringUtils;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import net.sf.json.JSONObject;

/**
 * 常用工具类 Package : com.yixin.common.cc.common.util
 *
 * @author YixinCapital -- chenyuan1 2016年3月4日 上午9:54:41
 *
 */
public class CommonUtil {

    private CommonUtil() {
    }

    private static ThreadLocal<SimpleDateFormat> sdfThread = new ThreadLocal<SimpleDateFormat>() {
        @Override
        protected SimpleDateFormat initialValue() {
            return new SimpleDateFormat("yyyy-MM-dd");
        }

        @Override
        public SimpleDateFormat get() {
            // TODO Auto-generated method stub
            return super.get();
        }

        @Override
        public void set(SimpleDateFormat value) {
            super.set(value);
        }

        @Override
        public void remove() {
            // TODO Auto-generated method stub
            super.remove();
        }

    };

    public static String formatDate(Date date, String pattern) {
        SimpleDateFormat value = new SimpleDateFormat(pattern);
        sdfThread.set(value);
        SimpleDateFormat format = sdfThread.get();
        return format.format(date);
    }

    public static Date parseDate(String date, String pattern) throws ParseException {
        SimpleDateFormat value = new SimpleDateFormat(pattern);
        sdfThread.set(value);
        SimpleDateFormat format = sdfThread.get();
        return format.parse(date);
    }

    /**
     * 计算两个日期之间相差的天数 (精确到天)
     *
     * @param date1
     * @param date2
     * @return
     */
    public static int daysBetween1(Date date1, Date date2) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date1);
        int i = 0;
        while (cal.getTime().before(date2)) {
            cal.add(Calendar.DAY_OF_YEAR, 1);
            i++;
        }

        return --i;
    }

    /**
     * 转换List为in的字符串('a','b')
     *
     * @param strList
     * @return
     * @author YixinCapital -- sfei 2016年4月9日 下午12:21:27
     */
    public static String transferListStringToIn(List<String> strList) {

        if (strList == null || strList.size() == 0)
            return "";

        String Str = "(";
        for (int i = 0; i < strList.size(); i++) {

            Str = Str + "'" + strList.get(i) + "'";
            if (i == strList.size() - 1) {
                Str = Str + ")";
            } else {
                Str = Str + ",";
            }

        }

        return Str;

    }

    /**
     * 将json 转化为对象
     *
     * @param json
     * @param obj
     * @return
     * @author YixinCapital -- chenyuan1 2016年3月2日 上午10:30:47
     * @throws IOException
     * @throws JsonMappingException
     * @throws JsonParseException
     */
    public static Object jsonToObject(String json, Object obj) throws IOException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.readValue(json, obj.getClass());
    }

    /**
     * 将对象转化为json
     *
     * @param obj
     * @return
     * @author YixinCapital -- chenyuan1 2016年3月2日 上午10:33:02
     * @throws JsonProcessingException
     */
    public static String objectToJson(Object obj) throws JsonProcessingException {
        ObjectMapper objectMapper = new ObjectMapper();
        return objectMapper.writeValueAsString(obj);
    }

	/**
	 * 判断字符串是不是json
	 * @param content
	 * @return 
	 * @author YixinCapital -- chen.lin
	 *	       2018年9月17日 下午4:35:44
	 */
	public static boolean isJson(String content) {
        try {
            JSONObject.fromObject(content);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * 根据资方code以及指定后缀获取处理类名(首字母小写)
     * @param financialCode
     * @param suffix
     * @return
     * xjt-20180928
     */
    public static String getBeanNameByFinancialCode(final String financialCode, final String suffix) {
        StringBuilder sb = new StringBuilder();
        if (Character.isLowerCase(financialCode.charAt(0))) {
            sb.append(financialCode);
        }else {
            sb.append(Character.toLowerCase(financialCode.charAt(0)))
                    .append(financialCode.substring(1));
        }
        return sb.toString().concat(suffix);
    }
}
