package com.aa.work.mergefile.ThreadPoolFactory;

import java.util.concurrent.ExecutorService;

/**
 * 线程池工厂接口
 */
public interface PoolFactory {

    /**
     * 构建池
     * @param size
     */
    ExecutorService buildExecutePool(int size);
}
