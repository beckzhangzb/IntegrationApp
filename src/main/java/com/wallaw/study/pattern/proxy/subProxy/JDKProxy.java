package com.wallaw.study.pattern.proxy.subProxy;

import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;

public class JDKProxy implements InvocationHandler {
	private Object beProxyObj;

	public Object build(Object beProxyObj){
		this.beProxyObj = beProxyObj;
		return Proxy.newProxyInstance(beProxyObj.getClass().getClassLoader(), 
				beProxyObj.getClass().getInterfaces(), this);
	}
	
	@Override
	public Object invoke(Object proxy, Method method, Object[] args)
			throws Throwable {
		System.out.println("加入发送前的逻辑");
		Object result = null;
		try{
			result = method.invoke(beProxyObj, args);
		}catch(Exception e){}
		
		System.out.println("加入发送之后的处理");
		return result;
	}
	
	public static void main(String[] args) {
		ISender emailSender = new EmailSender();
		ISender proxy = (ISender) new JDKProxy().build(emailSender);
		proxy.sender();
	}
}
