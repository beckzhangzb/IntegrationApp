package com.wallaw.study.pattern.factory;

public class FactoryTest {
	
	
	public static void main(String[] args) {
		Factory factory = new SmsFactory();
		Sender sender = factory.produce();
		sender.send();
	}
}
