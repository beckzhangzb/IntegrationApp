package com.wallaw.study.arithmetic.practice;

/**
 * @author: zhangzb
 * @date: 2020/3/3 17:23
 * @description: 求最大公约数
 *  好的思路，好的算法能够轻而易举 几行代码实现功能，【“算法才是王道”】
 */
public class GreatestDivisor {

    public static void main(String[] args) {
        System.out.println("greatest divisor:" + printGreatestDivisor(5, 9));
        System.out.println("greatest divisor:" + printGreatestDivisor(3, 9));
        System.out.println("greatest divisor:" + printGreatestDivisor(6, 9));
        System.out.println("greatest divisor:" + printGreatestDivisor(1, 7));
        System.out.println("greatest divisor:" + printGreatestDivisor(2, 9));
        System.out.println("greatest divisor:" + printGreatestDivisor(7, 9));
    }

    public static int printGreatestDivisor(int a, int b) {
        while (true) {
            if ((a = a % b) == 0) {
                System.out.println("最大公约数：" + b);
                return b;
            }
            if ((b = b % a) == 0) {
                System.out.println("最大公约数：" + a);
                return a;
            }
        }
    }
}
