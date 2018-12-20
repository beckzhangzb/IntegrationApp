package com.wallaw.study.cache.dao.impl;

import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import javax.annotation.Resource;
import javax.sql.DataSource;

import com.wallaw.study.cache.annotation.RedisCache;
import com.wallaw.study.cache.dao.DemoDAO;
import com.wallaw.study.cache.model.Demo;
import com.wallaw.study.cache.model.User;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DemoDAOImpl implements DemoDAO {

	private JdbcTemplate jdbcTemplate;  
    
    @Resource(name="dataSource")  
    public void setDataSource(DataSource dataSource) {  
        this.jdbcTemplate = new JdbcTemplate(dataSource);  
    }  

    /***存入在map中，无时间限制***/
    @RedisCache(type = User.class, expireTime= 40)
	public String getNameById(int id) {
		/*String name = jdbcTemplate.queryForObject("SELECT name FROM demo where id="+id, String.class);
		System.out.println("demoDaoImpl name:"+name);
		return name;*/
		return null;
	}
    
    @RedisCache(type = User.class, expireTime= 40)
	public User getUserById(Integer id){
    	System.out.println("getUserById1 id:"+id);
    	Map map = jdbcTemplate.queryForMap("SELECT * FROM user where id="+id);
    	Iterator it = map.entrySet().iterator();
    	
    	User user = new User();
    	while(it.hasNext()){
    		Map.Entry<String, String> entry = (Entry<String, String>) it.next();
    		String key = entry.getKey();
    		
    		if("id".equals(key)){
    			user.setId((Integer)map.get(key));
    		}else if("name".equals(key)){
    			user.setName((String)map.get(key));
    		}else if("address".equals(key)){
    			user.setAddress((String)map.get(key));
    		}
    	}
    	
    	System.out.println("getUserById2 id:"+id);
		return user;
    }
	
	/***缓存数据有时间限制***/
    @RedisCache(type= String.class, expireTime= 20)
	public String queryAddressById(int id) {
		/*String address = jdbcTemplate.queryForObject("SELECT address FROM demo where id="+id, String.class);
		System.out.println("demoDaoImpl address:"+address);
		return address;*/
		return null;
	}

	@Override
	public Integer updateAddressById(int id, String address) {
		/*String sql = "UPDATE demo SET Address=? WHERE id=?";
        int number = jdbcTemplate.update(sql, address, id);
		return number;*/
		return null;
	}
	
	public Integer insertDemo(Demo demo){
		/*String sql = "INSERT INTO demo (name, age, address, phone) values (?, ?, ?, ?)";
		int number = jdbcTemplate.update(sql, demo.getName(), demo.getAge(), demo.getAddress(), demo.getPhone());
		return number;*/
		return null;
	}
	
}
