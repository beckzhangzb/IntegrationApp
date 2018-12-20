package com.wallaw.study.pattern.proxy;

public class Source implements Sourceable {
	public void shutDownMachine() {
		System.out.println("成功关闭机器！");
	}

	public void startMachine() {
		System.out.println("启动机器！");
	}
}
