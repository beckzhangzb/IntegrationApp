package com.wallaw.study.cache.dao;

import com.wallaw.study.cache.annotation.RedisCache;
import com.wallaw.study.cache.annotation.RedisEvict;
import com.wallaw.study.cache.model.Demo;
import com.wallaw.study.cache.model.User;

public interface DemoDAO {

	/***缓存数据有时间限制***/
	@RedisCache(type = String.class, expireTime= 10)
	public String getNameById(int id);
	
	@RedisCache(type = User.class, expireTime= 40)
	public User getUserById(Integer id);

	/***缓存数据有时间限制***/
	@RedisCache(type= String.class, expireTime= 10)
	public String queryAddressById(int id);
	
	/***更新数据***/
	@RedisEvict(type= Integer.class)
	public Integer updateAddressById(int id, String address);
	
	/***插入对象数据***/
	public Integer insertDemo(Demo demo);
}
