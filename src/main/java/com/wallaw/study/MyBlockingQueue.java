package com.wallaw.study;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;

/**
 * @author: zhangzb
 * @date: 2020/10/26 14:28
 * @description:  自主实现一个阻塞队列
 */
public class MyBlockingQueue {
    int capacity;
    AtomicInteger size = new AtomicInteger(0);
    List list;
    Object[] arr;
    Object addLock = new Object();
    Object tackLock = new Object();
    public MyBlockingQueue(int capacity) {
        this.capacity = capacity;
        this.list = new ArrayList(capacity);
    }

    void init(){
        Object[] arr = new Object[capacity];
    }
    public void put(Object o) throws InterruptedException {
        synchronized (addLock) {
            if (capacity == size.intValue()) {
                addLock.wait();
            }

            this.list.add(o);
            size.incrementAndGet();
        }

        if (size.intValue() > 0) {
            synchronized (tackLock) {
                tackLock.notifyAll();
            }
        }
    }

    public Object take() throws InterruptedException {
        Object o;
        synchronized (tackLock) {
            if (size.intValue() == 0) {
                tackLock.wait();
            }

            o = list.get(0);
            list.remove(0);
            size.decrementAndGet();
        }

        if (size.intValue() < capacity) {
            synchronized (addLock) {
                addLock.notifyAll();
            }
        }

        return o;
    }

    int getSize() {
        return size.intValue();
    }
}