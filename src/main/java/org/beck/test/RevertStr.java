package org.beck.test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: zhangzb
 * @date: 2020/10/28 19:48
 * @description:
 */
public class RevertStr {

    public static void main(String[] args) {

        String test1 = "adc.wer...rew.er";
        System.out.println("outPut:" + revertStr(test1));
    }

    public static String revertStr(String s) {
        if (s == null || s.trim().equals("")) return null;

        char[] chars = s.toCharArray();

        char dot = '.', start = chars[0];
        boolean isDot = false;
        if (start == dot) {
            isDot = true;
        }

        List<String> lists = new ArrayList();
        StringBuffer sbuf = new StringBuffer();
        sbuf.append(start);
        for (int i = 1; i < chars.length; i++) {
            if (isDot) {
                if (chars[i] == dot) {
                    sbuf.append(dot);
                } else {
                    lists.add(sbuf.toString());
                    sbuf = new StringBuffer();
                    sbuf.append(chars[i]);
                    isDot = false;
                }
            } else {
                if (chars[i] == dot) {
                    lists.add(sbuf.toString());
                    sbuf = new StringBuffer();
                    sbuf.append(chars[i]);

                    isDot = true;
                } else {
                    sbuf.append(chars[i]);
                }
            }
        }
        lists.add(sbuf.toString());

        StringBuffer newBuf = new StringBuffer();
        for (int i = 0; i < lists.size(); i++) {
            String str = lists.get(i);

            newBuf.append(revertSubStr(str));
        }


        return newBuf.toString();
    }

    private static String revertSubStr(String str) {
        char[] chars = str.toCharArray();
        int j = chars.length - 1;
        for (int i = 0; i < j; i++, j--) {
            char temp = chars[j];
            chars[j] = chars[i];
            chars[i] = temp;

        }
        return String.valueOf(chars);
    }

}
