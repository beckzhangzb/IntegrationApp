package com.wallaw.study.cache.aop;

import com.alibaba.fastjson.JSON;
import com.wallaw.study.cache.annotation.RedisCache;
import com.wallaw.util.GsonUtil;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.MethodSignature;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import redis.clients.jedis.JedisCluster;

import java.util.logging.Logger;

@Aspect
@Component
public class ConfigAspect {
    public static final Logger infoLog = Logger.getLogger("RedisCacheAspect.class");

    @Autowired
	private JedisCluster jedisCluster;

   	public void before(JoinPoint joinPoint){
   		System.out.println("aspect before config:");
   	}
   	
   	//配置后置通知,使用在方法aspect()上注册的切入点
   	public void after(JoinPoint joinPoint){
   		System.out.println("aspect after config:");
   	}
   	
    
    /**
     * 环绕通知
     * @param jp
     * @return
     * @throws Throwable
     */
    public Object around(final ProceedingJoinPoint jp) throws Throwable {
    	System.out.println("aspect around  config 1:");
        // 得到类名、方法名和参数
        String clazzName = jp.getTarget().getClass().getName();
        String methodName = jp.getSignature().getName();
        Object[] args = jp.getArgs();
        
        // 根据类名，方法名和参数生成key
        String key = genKey(clazzName, methodName, args);
        System.out.println("key:"+key);
        
        // 得到被代理的方法
        java.lang.reflect.Method me = ((MethodSignature) jp.getSignature()).getMethod();
        RedisCache redisCache = me.getAnnotation(RedisCache.class);
		Class redisCacheType = redisCache.type();
        
        // result是方法的最终返回结果
        Object result = null;
        
        // 检查redis中是否有缓存
     	String value = jedisCluster.hget(key, redisCacheType.getName());
     	
     	try{
     		if (null == value) {
                infoLog.info("缓存未命中");

                // 调用数据库查询方法
                result = jp.proceed(args);
                
               // String json = serialize(result);
                String json = GsonUtil.toJsonWithTransientFeild(result, redisCacheType);
                jedisCluster.hset(key, redisCacheType.getName(), json);
            } else {
                // 缓存命中
                infoLog.info("缓存命中, value = {"+value+"}");
                Class returnType = ((MethodSignature) jp.getSignature()).getReturnType();
                //result = deserialize(value, returnType);
                result = GsonUtil.getObjectWithTransientFeild(value, returnType);

                infoLog.info("反序列化结果 = "+result);
            }
            
    		System.out.println("时间:"+redisCache.expireTime());
    		jedisCluster.expire(key, redisCache.expireTime());   //用此方法也可以存
     	}catch(Exception e){
     		System.out.println("Exception happen!");
     		e.printStackTrace();
     		jedisCluster.close();
     	}
     	System.out.println("aspect around 2:");
        return result;
    }
    
	//配置后置返回通知,使用在方法aspect()上注册的切入点
	public void afterReturn(JoinPoint joinPoint){
		System.out.println("aspect afterReturn  config:");
	}
	
	//配置抛出异常后通知,使用在方法aspect()上注册的切入点
	public void afterThrow(JoinPoint joinPoint, Exception ex){
		System.out.println("aspect afterThrow  config:");
	}
    
    /**
     * 根据类名、方法名和参数生成key
     * @param clazzName
     * @param methodName
     * @param args 方法参数
     * @return
     */
    private String genKey(String clazzName, String methodName, Object[] args) {
        StringBuilder sb = new StringBuilder(clazzName);
        sb.append(".");
        sb.append(methodName);
        sb.append(".");

        if(args!=null&&args.length>0){
          sb.append(args[0].toString());
          sb.append(".");
        }

        return sb.toString();
    }

    private String serialize(Object target) {
        return JSON.toJSONString(target);
    }

    @SuppressWarnings("unchecked")
    private Object deserialize(String jsonString, Class clazz) {
        // 序列化结果是普通对象
        return JSON.parseObject(jsonString, clazz);
    }
}