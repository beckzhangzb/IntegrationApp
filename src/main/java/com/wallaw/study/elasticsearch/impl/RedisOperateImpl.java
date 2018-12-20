package com.wallaw.study.elasticsearch.impl;

import com.alibaba.fastjson.JSONObject;
import com.wallaw.study.cache.model.OrderDetail;
import com.wallaw.study.elasticsearch.RedisOperate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.data.redis.serializer.StringRedisSerializer;
import org.springframework.stereotype.Service;

/**
 * redis操作实现类
 * @author zhangzb
 * @since 2018/7/12 17:18
 */
@Service
public class RedisOperateImpl implements RedisOperate{

    private RedisTemplate redisTemplate;

    private StringRedisSerializer stringRedisSerializer;

    @Override
    public void redisCacheForListBLPOP(String key, Object object) {
        redisTemplate.opsForList().leftPush(key, JSONObject.toJSONString(object));
        System.out.println(redisTemplate.opsForList().range(key, 0, redisTemplate.opsForList().size(key)));
    }

    @Override
    public void redisCacheForPubSub(String key, OrderDetail orderDetail) {
        redisTemplate.convertAndSend(key, JSONObject.toJSONString(orderDetail));
    }

    public RedisTemplate getRedisTemplate() {
        return redisTemplate;
    }

    public void setRedisTemplate(RedisTemplate redisTemplate) {
        this.redisTemplate = redisTemplate;
    }

    public StringRedisSerializer getStringRedisSerializer() {
        return stringRedisSerializer;
    }

    public void setStringRedisSerializer(StringRedisSerializer stringRedisSerializer) {
        this.stringRedisSerializer = stringRedisSerializer;
    }
}
