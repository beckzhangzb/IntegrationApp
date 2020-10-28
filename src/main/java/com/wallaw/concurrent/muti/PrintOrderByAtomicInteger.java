package com.wallaw.concurrent.muti;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zhangzb
 * @date: 2020/10/27 15:13
 * @description: AtomicInteger 实现循环顺序打印
 */
public class PrintOrderByAtomicInteger {
    private final AtomicInteger count = new AtomicInteger(0);
    private final AtomicInteger num = new AtomicInteger(0);
    public void first() {
        while (count.get() != 0) {}
        System.out.println("first " + num.incrementAndGet());
        count.incrementAndGet();
    }

    public void second() {
        while (count.get() != 1) {}
        System.out.println("second " + num.incrementAndGet());
        count.incrementAndGet();
    }

    public void third() {
        while (count.get() != 2) {}
        System.out.println("third " + num.incrementAndGet());
        count.set(0);
    }

    public static void main(String[] args) throws InterruptedException {
        PrintOrderByAtomicInteger printOrder = new PrintOrderByAtomicInteger();
        Task2 t1 = new Task2(printOrder, 1);
        Task2 t2 = new Task2(printOrder, 2);
        Task2 t3 = new Task2(printOrder, 3);
        ExecutorService es = Executors.newFixedThreadPool(3);
        for (int i = 0; i < 5; i++) {
            es.submit(t3);
            es.submit(t2);
            es.submit(t1);
            Thread.sleep(100);
        }
    }
}

class Task2 implements Runnable {
    private PrintOrderByAtomicInteger printOrder;
    private int index;

    public Task2(PrintOrderByAtomicInteger printOrder, int index) {
        this.printOrder = printOrder;
        this.index = index;
    }

    @Override
    public void run() {
        if (index == 1) {
            printOrder.first();
        } else if (index == 2) {
            printOrder.second();
        } else {
            printOrder.third();
        }
    }
}
