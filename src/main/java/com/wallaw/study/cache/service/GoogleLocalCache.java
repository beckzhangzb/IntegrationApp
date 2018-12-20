package com.wallaw.study.cache.service;

import java.util.List;

/**
 * @author zhangzb
 * @since 2018/3/2 11:13
 */
public interface GoogleLocalCache {
    /**
     * 根据key,获取Redis缓存值(对象)
     *
     * @param key
     * @param valueClass
     * @return
     */
    <T> T getValue(String key, Class<T> valueClass);

    /**
     * 根据key, 获取Redis缓存值(列表)
     *
     * @param key
     * @param valueClass
     * @return
     */
    <T> List<T> getListValue(String key, Class<T> valueClass);

    /**
     * 设置Redis缓存值
     *
     * @param key
     * @param value
     */
    void setValue(String key, String value);

    /**
     * 从缓存中获取值
     * @param key
     * @return
     */
    String getValue(String key);
}
