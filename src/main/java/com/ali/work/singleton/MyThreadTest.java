package com.ali.work.singleton;

import java.util.concurrent.ExecutorService;

import static java.util.concurrent.Executors.*;

/**
 * @author zhangzb
 * @since 2018/9/7 15:03
 */
public class MyThreadTest {

    public static void main(String[] args) {
        int n = 10;
        ExecutorService es = newFixedThreadPool(n);
        for(int i = 0 ; i < n ; i++){
            es.execute(new Runnable() {
                @Override
                public void run() {
                    System.out.println(MySingleton.getInstance().hashCode());
                }
            });
        }
    }

}
