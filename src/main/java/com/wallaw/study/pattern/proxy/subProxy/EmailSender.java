package com.wallaw.study.pattern.proxy.subProxy;

public class EmailSender implements ISender {
	public void sender() {
		System.out.println("Send email!");
	}
}
