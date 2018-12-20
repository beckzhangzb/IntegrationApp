package com.wallaw.study.cache.service.impl;

import com.wallaw.study.cache.dao.DemoDAO;
import com.wallaw.study.cache.model.Demo;
import com.wallaw.study.cache.model.User;
import com.wallaw.study.cache.service.DemoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class DemoServiceImpl implements DemoService {

	@Autowired 
	private DemoDAO demoDao;

	public Integer insertDemo(Demo demo){
		return demoDao.insertDemo(demo);
	}
	
	public String getNameById(Integer id) {
		return demoDao.getNameById(id);
	}
	
	public User getUserById(Integer id){
		return demoDao.getUserById(id);
	}

	public String queryAddressById(int id) {
		return demoDao.queryAddressById(id);
	}
	
	public Integer updateAddressById(int id, String address){
		return demoDao.updateAddressById(id, address);
	}
	
	/**
	 * 仅用于spring cache
	 * @return
	 */
	@Cacheable(value = "userCache", key = "#name")
	public User findUser(int id, String name, String address){
		return new User(id, name, "新建?:"+address);
	}
	
	/**
	 * 仅用于spring cache
	 * @return
	 */
	@Cacheable(value = "userCache", condition = "#id < 200")
	public User findUserById(int id, String name, String address){
		return new User(id, name, "新建 one:"+address);
	}
}