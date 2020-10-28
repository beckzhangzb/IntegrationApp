package com.wallaw.concurrent.muti;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

/**
 * @author: zhangzb
 * @date: 2020/10/27 14:40
 * @description: 多个线程分别依次打印
 */
public class MutiThreadPrintOrder {
    private static Semaphore firstSemaphore = new Semaphore(0);
    // 此处信号量里面的permits必须要为0，就是一开始使线程阻塞
    private static Semaphore secordSemaphore = new Semaphore(0);
    private static Semaphore thirdSemaphore = new Semaphore(0);

    public void first() throws InterruptedException {
        firstSemaphore.acquire();
        System.out.print("first ");
        secordSemaphore.release();
    }

    public void second() throws InterruptedException {
        secordSemaphore.acquire();
        System.out.print("second ");
        thirdSemaphore.release();
    }

    public void third() throws InterruptedException {
        thirdSemaphore.acquire();
        System.out.println("third");
        firstSemaphore.release();
    }

    public static void main(String[] args) {
        MutiThreadPrintOrder printOrder = new MutiThreadPrintOrder();
        Task t1 = new Task(printOrder, 1);
        Task t2 = new Task(printOrder, 2);
        Task t3 = new Task(printOrder, 3);

        ExecutorService es = Executors.newFixedThreadPool(3);
        for (int i = 0; i<5; i++) {
            es.submit(t3);
            es.submit(t2);
            es.submit(t1);
        }
    }
}

class Task implements Runnable {
    private MutiThreadPrintOrder printOrder;
    private int index;

    public Task(MutiThreadPrintOrder printOrder, int index) {
        this.printOrder = printOrder;
        this.index = index;
    }

    @Override
    public void run() {
        try {
            if (index == 1) {
                printOrder.first();
            } else if (index == 2) {
                printOrder.second();
            } else {
                printOrder.third();
            }
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}