package com.wallaw.study.cache.annotation;

import java.lang.annotation.*;

@Retention(RetentionPolicy.RUNTIME)
@Target(ElementType.METHOD)
@Documented
public @interface RedisCache {
    Class type();
    public int expireTime() default 0;//缓存多少秒,默认无限期
}