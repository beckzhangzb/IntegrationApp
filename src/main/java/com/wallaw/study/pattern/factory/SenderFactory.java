package com.wallaw.study.pattern.factory;

public class SenderFactory {
	public Sender produceSms(){
		return new SmsSender();
	}
	
	public Sender productEmail(){
		return new EmailSender();
	}
}
