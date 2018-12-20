package com.test;

import java.util.AbstractMap;

/**
 * @author zhangzb
 * @since 2018/3/27 11:03
 */
public class TestCase {

    private static TestCase tc = new TestCase();

    public TestCase() {
        System.out.println("TestCase");
    }

    static {
        System.out.println("TestCase static");
    }

    public static void main(String[] args) throws InterruptedException {
        TestCase tc = new TestCase();
        ThreadLocal threadLocal = new ThreadLocal();
        ThreadLocal threadLocal2 = new ThreadLocal();
        tc.wait();

        threadLocal.set(12);
        threadLocal2.set(456);

        System.out.println(threadLocal.get());
        System.out.println(threadLocal2.get());


    }
}
