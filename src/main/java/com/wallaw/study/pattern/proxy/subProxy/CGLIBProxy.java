package com.wallaw.study.pattern.proxy.subProxy;

import net.sf.cglib.proxy.Enhancer;
import net.sf.cglib.proxy.MethodInterceptor;
import net.sf.cglib.proxy.MethodProxy;

import java.lang.reflect.Method;

public class CGLIBProxy implements MethodInterceptor{

	private Object beProxyObj;
	public Object build(Object beProxyObj){
		this.beProxyObj = beProxyObj;
		
		Enhancer hancer = new Enhancer();
		hancer.setSuperclass(beProxyObj.getClass());
		hancer.setCallback(this);
		return hancer.create();
	}
	
	@Override
	public Object intercept(Object obj, Method method, Object[] args,
			MethodProxy proxy) throws Throwable {
		System.out.println("加入发送前的逻辑");
		Object result = proxy.invoke(this.beProxyObj, args);
		System.out.println("加入发送之后的处理");
		
		return result;
	}

	/**
	 * @param args
	 */
	public static void main(String[] args) {
		EmailSender sender = new EmailSender();
		EmailSender cglibSender = (EmailSender) new CGLIBProxy().build(sender);
		cglibSender.sender();
	}
}
