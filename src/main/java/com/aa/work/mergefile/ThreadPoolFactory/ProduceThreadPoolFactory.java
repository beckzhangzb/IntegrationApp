package com.aa.work.mergefile.ThreadPoolFactory;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class ProduceThreadPoolFactory implements PoolFactory {
    private ExecutorService executor;

    @Override
    public ExecutorService buildExecutePool(int size) {
        executor = Executors.newFixedThreadPool(size);
        return executor;
    }
}
