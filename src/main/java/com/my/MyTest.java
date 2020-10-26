package com.my;

/**
 * @author: zhangzb
 * @date: 2020-10-26 10:53:13
 * @description:  3、3、3、6、9、12、18、27、39、57、84……  求第20位数是多少
 */
public class MyTest {



    public static void main(String[] args) {

        System.out.println(getIndexNum(4));
        System.out.println(getIndexNum(5));
        System.out.println(getIndexNum(20));
    }


    public static int getIndexNum(int index) {
        if (index < 1) {
            return 0;
        }

        if (index == 1 || index == 2 || index == 3) {
            return 3;
        }

        int data = getIndexNum(index - 1) + getIndexNum(index - 3);
        return data;
    }
}
