package com.wallaw.study.arithmetic.deepSearch;

/**
 * 用枚举完成数字的全排列，里面的思想和点点还是值得学习
 * @author zhangzb
 * @since 2018/1/23 10:55
 */
public class TotalOrderByEnum {

    public static void main(String[] args){
        printTotalOrder();
    }

    private static void printTotalOrder() {
        int num = 5;
        int[] book = new int[num + 1];
        int[] a = new int[num + 1];
        for (a[1] = 1; a[1] <= num; a[1]++) {
            for (a[2] = 1; a[2] <= num; a[2]++) {
                for (a[3] = 1; a[3] <= num; a[3]++) {
                    for (a[4] = 1; a[4] <= num; a[4]++) {
                        for (a[5] = 1; a[5] <= num; a[5]++)
                        {
                            for (int i = 1; i <= num; i++) {
                                book[i] = 0; // 初始化为0
                            }
                            StringBuffer sb = new StringBuffer();
                            for (int i=1;i<=num;i++){
                                book[a[i]] = 1;
                                sb.append(a[i]);
                            }

                            int sum = 0;
                            for (int i = 1; i <= num; i++) {
                                sum += book[i]; // 标记求和
                            }
                            if(sum == num){
                                System.out.println(sb.toString());
                            }
                        }
                    }
                }
            }
        }
    }
}
