package com.wallaw.study.cache.service.impl;

import com.alibaba.fastjson.JSON;
import com.google.common.cache.Cache;
import com.google.common.cache.CacheBuilder;
import com.wallaw.study.cache.service.GoogleLocalCache;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.concurrent.TimeUnit;

/**
 * @author zhangzb
 * @since 2018/3/2 11:15
 */
@Service
public class GoogleLocalCacheImpl implements GoogleLocalCache{
    // 缓存时间值
    private static final int                 CACHE_KEY_EXPIRE = 5;
    /**
     * 根据过期时间缓存到本地
     */
    private static final Cache<String, String> LOCAL_CACHE      = CacheBuilder.newBuilder().expireAfterWrite(
            CACHE_KEY_EXPIRE, TimeUnit.MINUTES).build();

    @Override
    public <T> T getValue(String key, Class<T> valueClass) {
        String value = getValue(key);
        return JSON.parseObject(value, valueClass);
    }

    @Override
    public <T> List<T> getListValue(String key, Class<T> valueClass) {
        String value = getValue(key);
        return JSON.parseArray(value, valueClass);
    }

    @Override
    public void setValue(String key, String value) {
        LOCAL_CACHE.put(key, value);
    }

    @Override
    public String getValue(String key) {
        return LOCAL_CACHE.getIfPresent(key);
    }
}
