package com.wallaw.study.pattern.build;

public interface Builder { 
	public void buildPartA();
	public void buildPartB();
	public void buildPartC();

	/***返回最后组装成品结果，成品组装过程转移到Director类中进行***/
	Product getResult(); 
} 
