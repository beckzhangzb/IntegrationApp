package com.wallaw.util;


import org.apache.commons.lang3.StringUtils;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * 工具类
 * @author zhangzb
 * @since 2017/9/6 16:18
 */
public class PhoneUtils {

    private static final int     DEFAUL_PHONE_LENGTH = 9; //默认座机号长度
    private static       Pattern mobilePattern       = Pattern.compile("^[1][3,4,5,7,8][0-9]{9}$"); // 验证手机号
    private static       Pattern phoneWithArea       = Pattern.compile("^[0][1-9]{2,3}-[0-9]{5,10}$");  // 验证带区号的
    private static       Pattern phoneWithoutArea    = Pattern.compile("^[1-9]{1}[0-9]{5,8}$");    // 验证没有区号的

    /**
     * 手机号验证
     * @param  str
     * @return 验证通过返回true
     */
    public static boolean isMobile(final String str) {
        boolean b = false;
        if (StringUtils.isNotBlank(str)) {
            Matcher m = mobilePattern.matcher(str);
            b = m.matches();
        }

        return b;
    }

    /**
     * 电话号码验证
     * @param  str
     * @return 验证通过返回true
     */
    public static boolean isPhone(final String str) {
        Matcher m;
        boolean b;
        if (str.length() > DEFAUL_PHONE_LENGTH) {
            m = phoneWithArea.matcher(str);
            b = m.matches();
        } else {
            m = phoneWithoutArea.matcher(str);
            b = m.matches();
        }
        return b;
    }

    public static void main(String[] args) {
        String phone = "13900442200";
        String phone2 = "021-88889999";
        String phone3 = "88889999";
        String phone4 = "1111111111";
        //测试1
        if (isPhone(phone) || isMobile(phone)) {
            System.out.println("1这是符合的");
        }
        //测试2
        if (isPhone(phone2) || isMobile(phone2)) {
            System.out.println("2这是符合的");
        }
        //测试3
        if (isPhone(phone3) || isMobile(phone3)) {
            System.out.println("3这是符合的");
        }
        //测试4
        if (isPhone(phone4) || isMobile(phone4)) {
            System.out.println("4这是符合的");
        } else {
            System.out.println("不符合");
        }
    }
}
