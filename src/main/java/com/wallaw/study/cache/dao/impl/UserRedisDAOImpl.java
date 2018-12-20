//package com.yunyao.cache.dao.impl;
//
//import java.io.ByteArrayOutputStream;
//import java.io.IOException;
//import java.io.ObjectOutputStream;
//import java.io.Serializable;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.dao.DataAccessException;
//import org.springframework.data.redis.connection.RedisConnection;
//import org.springframework.data.redis.core.RedisCallback;
//import org.springframework.data.redis.core.RedisTemplate;
//
//import com.yunyao.cache.dao.UserRedisDAO;
//import com.yunyao.cache.model.User;
//
//public class UserRedisDAOImpl implements UserRedisDAO {
//
//    @Autowired
//    protected RedisTemplate<Serializable, Serializable> redisTemplate;
//
//    public void saveUser(final User user) {
//        redisTemplate.execute(new RedisCallback<Object>() {
//
//            @Override
//            public Object doInRedis(RedisConnection connection) throws DataAccessException {
//                connection.getSet(redisTemplate.getStringSerializer().serialize("user.uid." + user.getId()),
//                                  redisTemplate.getStringSerializer().serialize(user.getName())
//                               );
//                
////            	byte[] key_ = getBytesFromObject("user.uid." + user.getId());  
////                byte[] value_ = getBytesFromObject(user);  
////                connection.set(key_, value_); 
//                return true;
//            }
//        });
//    }
//
//    @Override
//    public User getUser(final long id) {
//        return redisTemplate.execute(new RedisCallback<User>() {
//            @Override
//            public User doInRedis(RedisConnection connection) throws DataAccessException {
//                byte[] key = redisTemplate.getStringSerializer().serialize("user.uid." + id);
//                if (connection.exists(key)) {
//                    byte[] value = connection.get(key);
//                    String name = redisTemplate.getStringSerializer().deserialize(value);
//                    User user = new User();
//                    user.setName(name);
//                    user.setId(id);
//                    return user;
//                }
//                return null;
//            }
//        });
//    }
//    
//    private byte[] getBytesFromObject(Object obj){
//    	byte[] bytes = null;      
//        ByteArrayOutputStream bos = new ByteArrayOutputStream();      
//        try {        
//            ObjectOutputStream oos = new ObjectOutputStream(bos);         
//            oos.writeObject(obj);        
//            oos.flush();         
//            bytes = bos.toByteArray ();      
//            oos.close();         
//            bos.close();        
//        } catch (IOException ex) {        
//            ex.printStackTrace();   
//        }      
//        return bytes;    
//    }
//}