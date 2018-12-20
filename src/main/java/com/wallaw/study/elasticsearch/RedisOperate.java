package com.wallaw.study.elasticsearch;

import com.wallaw.study.cache.model.OrderDetail;

/**
 * redis操作接口
 * @author zhangzb
 * @since 2018/7/12 17:17
 */
public interface RedisOperate {
    /**
     * redis cache
     * @param object
     */
    void redisCacheForListBLPOP(String key, Object object);

    /**
     * 发布订阅消息
     * @param key
     * @param orderDetail
     */
    void redisCacheForPubSub(String key, OrderDetail orderDetail);
}
