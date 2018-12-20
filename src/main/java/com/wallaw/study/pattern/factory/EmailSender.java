package com.wallaw.study.pattern.factory;

public class EmailSender implements Sender {
	@Override
	public void send() {
		System.out.println("已调用邮件发送接口！");
	}
}
