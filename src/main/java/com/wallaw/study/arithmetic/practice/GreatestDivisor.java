package com.wallaw.study.arithmetic.practice;

/**
 * @author: zhangzb
 * @date: 2020/3/3 17:23
 * @description:
 */
public class GreatestDivisor {

    public static void main(String[] args) {

        System.out.println("greatest divisor:" + printGreatestDivisor(5, 9));
        System.out.println("greatest divisor:" + printGreatestDivisor(3, 9));
        System.out.println("greatest divisor:" + printGreatestDivisor(6, 9));
        System.out.println("greatest divisor:" + printGreatestDivisor(1, 7));
    }

    public static int printGreatestDivisor(int a, int b) {
        while (true) {
            if (a%b>0) {
                a=a%b;
            } else if(b>a) {
                b=b%a;
            } else {
                return a;
            }
            System.out.println(a+","+b);
        }
    }

}
