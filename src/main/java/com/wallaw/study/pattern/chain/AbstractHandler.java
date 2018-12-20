package com.wallaw.study.pattern.chain;

public abstract class AbstractHandler implements Handler {
    private MyHandler handler;

	public MyHandler getHandler() {
		return handler;
	}

	public void setHandler(MyHandler handler) {
		this.handler = handler;
	}
}
