package com.wallaw.study.cache.service;

import com.wallaw.study.cache.model.Demo;
import com.wallaw.study.cache.model.User;
import org.springframework.cache.annotation.Cacheable;

public interface DemoService {

	public String getNameById(Integer id);
	
	public User getUserById(Integer id);
	
	public String queryAddressById(int id);
	
	public Integer insertDemo(Demo demo);
	
	public Integer updateAddressById(int id, String address);
	
	/**
	 * 仅用于spring cache
	 * @return
	 */
	@Cacheable(value = "myUser", key = "#name")
	public User findUser(int id, String name, String address);
	
	/**
	 * 仅用于spring cache
	 * @return
	 */
	@Cacheable(value = "myUser", condition = "#id < 200")
	public User findUserById(int id, String name, String address);
}
