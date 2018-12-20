package com.ali.work.ticketlock;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author zhangzb
 * @since 2018/9/7 17:09
 */
public class TicketLock {

    /**
     * 服务号
     */
    private AtomicInteger serviceNum = new AtomicInteger();

    /**
     * 排队号
     */
    private AtomicInteger ticketNum = new AtomicInteger();

    /**
     * 存储每个线程的排队号
     */
    private ThreadLocal<Integer> ticketNumHolder = new ThreadLocal<Integer>();

    public void lock() {
        int currentTicketNum = ticketNum.incrementAndGet();

        // 保存当前取锁线程的排队号
        ticketNumHolder.set(currentTicketNum);
        while (currentTicketNum != serviceNum.get()) {
        }
    }

    public void unlock() {
        // 释放锁
        Integer currentTickNum = ticketNumHolder.get();
        serviceNum.compareAndSet(currentTickNum, currentTickNum + 1);
    }
}
