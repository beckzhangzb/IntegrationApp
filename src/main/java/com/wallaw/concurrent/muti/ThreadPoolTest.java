package com.wallaw.concurrent.muti;

import com.google.common.util.concurrent.ThreadFactoryBuilder;

import java.util.concurrent.*;

/**
 * 线程池测试
 *
 * @author zhangzb
 * @since 2019/2/27 17:35
 */
public class ThreadPoolTest {
    public static void main(String[] args) throws InterruptedException {
        ThreadFactory threadFactory = new ThreadFactoryBuilder().
                setNameFormat("demo-pool-%d").build();

        /**
         * 定义一个：核心线程为5， max为10， 队列大小为20的线程池
         */
        ExecutorService pool = new ThreadPoolExecutor(5, 10, 5L,
                TimeUnit.SECONDS,
                new LinkedBlockingDeque<Runnable>(20),
                threadFactory,
                new ThreadPoolExecutor.AbortPolicy());
        /**
         * 线程数 Tnum
         */
        for (int i = 0; i < 40; i++) {
            pool.execute(new MyTask());
        }

        pool.shutdown();
    }
}

class MyTask implements Runnable {
    @Override
    public void run() {
        try {
            Thread.sleep(2000);
            System.out.println(Thread.currentThread().getName() + " @ "
                    + Thread.currentThread().getId() + " @ " +this.hashCode());
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}