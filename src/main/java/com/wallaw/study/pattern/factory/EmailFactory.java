package com.wallaw.study.pattern.factory;

public class EmailFactory implements Factory {
	@Override
	public Sender produce() {
		return new EmailSender();
	}
}
