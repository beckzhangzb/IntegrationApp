package com.job.cannal.datasync.handler;

/**
 * Created by haha on 2020/8/21.
 */
public interface Handler<T> {
    void doHandle(T message);
}
