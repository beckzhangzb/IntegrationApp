package com.wallaw.study.pattern.factory;

public class SmsSender implements Sender {
	@Override
	public void send() {
		System.out.println("已调用短信发送接口！");
	}
}
