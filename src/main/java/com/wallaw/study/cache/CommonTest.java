package com.wallaw.study.cache;

import com.wallaw.study.cache.model.User;
import com.wallaw.study.cache.service.DemoService;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;


public class CommonTest {
	
	public static void main(String[] args) {
		ApplicationContext Factory = new ClassPathXmlApplicationContext("applicationContext.xml");
		DemoService demoService = (DemoService) Factory.getBean("demoService");
		
		//测试 aop 注解方式
//		String address = demoService.queryAddressById(1);
//		System.out.println("address:  " + address);
		
		
		
		//测试aop 配置文件方式
//		String name = demoService.getNameById(1);
//		System.out.println("name:  " + name);
		
		User user = demoService.getUserById(1);
		if(user!=null){
			System.out.println("id:"+user.getId());
			System.out.println("name:"+user.getName());
			System.out.println("address:"+user.getAddress());
		}else{
			System.out.println("no data!");
		}
		//System.out.println((0x00000008&0x00000008)!=0);
	}
	
	
	
	
	
	
	
	
	
	
//	public static void main11(String[] args) {
//		ApplicationContext Factory = new ClassPathXmlApplicationContext("applicationContext.xml");
//
//		DemoService demoService = (DemoService) Factory.getBean("demoService");
//		String address = demoService.queryAddressById(1);
//		System.out.println("address:  " + address);
		
//		String name = demoService.getNameById(1);
//		System.out.println("name:  " + name);
		
//		String newAddress = "aa环球";
//		int num = demoService.updateAddressById(1, newAddress);
//		System.out.println("num:"+num);
		
//		Demo demo = new Demo();
//		demo.setName("kitty");
//		demo.setAge(32);
//		demo.setAddress("天府广场");
//		demo.setPhone("13545874563");
//		System.out.println("insert:"+demoService.insertDemo(demo));
//	}
	
	public static void main22(String[] args) {
        ApplicationContext Factory =  new ClassPathXmlApplicationContext("applicationContext.xml");
        DemoService demoService = (DemoService) Factory.getBean("demoService");
		
        User user1 = demoService.findUser(101,"wangwu", "环球E1");
        User user2 = demoService.findUser(102,"wangwu", "环球S1");
        System.out.println("user1==user2?"+(user1==user2));
        printUserInfo(user1);
        printUserInfo(user2);
        System.out.println("============================");
        
        User user3 =  demoService.findUserById(103,"wangwu", "环球S111111111");
        System.out.println("user3==user2?"+(user3==user2));
        printUserInfo(user3);
        System.out.println("============================");
        
        User user4 =  demoService.findUserById(104,"张三", "环球S222222222");
        System.out.println("user3==user4?"+(user3==user4));
        printUserInfo(user4);
        System.out.println("============================");
    }
	
	private static void printUserInfo(User user){
		System.out.println("id:"+user.getId());
		System.out.println("name:"+user.getName());
		System.out.println("address:"+user.getAddress());
	}
 }
