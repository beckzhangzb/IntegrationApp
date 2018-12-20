package com.wallaw.study.pattern.factory;

public class SmsFactory implements Factory {
	@Override
	public Sender produce() {
		return new SmsSender();
	}
}
