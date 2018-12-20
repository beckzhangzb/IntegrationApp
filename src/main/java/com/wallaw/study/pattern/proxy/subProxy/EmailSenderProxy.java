package com.wallaw.study.pattern.proxy.subProxy;

public class EmailSenderProxy implements ISender {
	private ISender sender;

	public EmailSenderProxy(ISender sender) {
		this.sender = sender;
	}
	
	public void sender() {
		System.out.println("加入发送前的逻辑");
		this.sender.sender();
		System.out.println("加入发送之后的处理");
	}
	
	public static void main(String[] args) {
		ISender sender = new EmailSender();
		ISender proxy = new EmailSenderProxy(sender);
		proxy.sender();
	}
}
