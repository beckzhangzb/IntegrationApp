package com.wallaw.study.pattern.singleton;

public class Singleton {

	private Singleton(){
	}
	public static Singleton getInstance() {
		return SingletonFactory.instance;
	}
	
	private static class SingletonFactory{
		private static Singleton instance = new Singleton();
	}
	
	private static Singleton instance = null;
	
	public static synchronized Singleton getInstance2(){
		if(instance==null){
			instance = new Singleton();
		}
		return instance;
	}
	
	public static Singleton getInstance3() {
		if (instance == null) {
			synchronized (instance) {
				if (instance == null) {
					instance = new Singleton();
				}
			}
		}
		return instance;
	}
	
}
