package com.yixin.dsc.util;


import org.apache.commons.lang3.StringUtils;

/**
 * 隐秘数据工具类
 *
 * Package : com.yixin.dsc.util
 * @author YixinCapital -- wangshuaiqiang
 *	       2018/10/18  10:50
 */
public class HideDataUtil {
    /**
     * 隐藏身份证号中间的字符串（使用*号），显示前一后一
     *
     * @param idNo
     * @return
     * @author YixinCapital -- wangshuaiqiang
     *	       2018/10/18  10:55
     */
    public static String hideIdNo(String idNo) {
        if(StringUtils.isBlank(idNo)) {
            return idNo;
        }

        int beforeLength = 1;
        int afterLength = 1;
        return hideData(idNo, beforeLength, afterLength);
    }


    /**
     * 隐藏银行卡号中间的字符串（使用*号），显示前四后四
     *
     * @param cardNo
     * @return
     * @author YixinCapital -- wangshuaiqiang
     *	       2018/10/18  10:55
     */
    public static String hideCardNo(String cardNo) {
        if(StringUtils.isBlank(cardNo)) {
            return cardNo;
        }

        int beforeLength = 4;
        int afterLength = 4;
        return hideData(cardNo, beforeLength, afterLength);
    }


    /**
     * 隐藏手机号中间位置字符，显示前三后四个字符
     * @param phoneNo
     * @return
     * @author YixinCapital -- wangshuaiqiang
     *	       2018/10/18  10:54
     */
    public static String hidePhoneNo(String phoneNo) {
        if(StringUtils.isBlank(phoneNo)) {
            return phoneNo;
        }

        int length = phoneNo.length();
        int beforeLength = 3;
        int afterLength = 4;
        //替换字符串，当前使用“*”
        String replaceSymbol = "*";
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<length; i++) {
            if(i < beforeLength || i >= (length - afterLength)) {
                sb.append(phoneNo.charAt(i));
            } else {
                sb.append(replaceSymbol);
            }
        }

        return sb.toString();
    }    

    /**
     * 隐藏数据
     * @param Str
     * @param beforeLength 前显示位数
     * @param afterLength 后显示位数
     * @return
     * @author YixinCapital -- wangshuaiqiang
     *	       2018/10/18  10:54
     */
    public static String hideData(String Str, int beforeLength,
			int afterLength) {
		int length = Str.length();
        //替换字符串，当前使用“*”
        String replaceSymbol = "*";
        StringBuffer sb = new StringBuffer();
        for(int i=0; i<length; i++) {
            if(i < beforeLength || i >= (length - afterLength)) {
                sb.append(Str.charAt(i));
            } else {
                sb.append(replaceSymbol);
            }
        }

        return sb.toString();
	}

    public static void main(String[] args) {
        System.out.println(hideIdNo("4127281974392187381395"));
        System.out.println(hideCardNo("132452345234532"));
        System.out.println(hidePhoneNo("15321370269"));
        System.out.println(hideData("19327492874",0,2));
    }
}
