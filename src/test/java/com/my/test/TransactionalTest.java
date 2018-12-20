package com.my.test;

import org.junit.Test;

import java.util.ArrayList;
import java.util.List;

/**
 * @author zhangzb
 * @since 2017/11/25 10:02
 */
public class TransactionalTest extends TestBase{

    @Test
    public void testPrint(){
        int i=15;
        String val = i%5==0?"buzz":i%3==0?"fizz":String.valueOf(i);
        System.out.println("OUT PRINT" + val);
    }

    public void subSum(int n) {
       List<Integer> numbers = new ArrayList<Integer>();
       for (int i=1;i <= n;i++){
           numbers.add(i);
       }

      // 大小即为子集合的最大元素个数
    }
}
