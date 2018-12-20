package com.wallaw.study.pattern.chain;

public class MyHandler extends AbstractHandler {
	private String des;

	public MyHandler(String des) {
		this.des = des;
	}
	
	public void operation() {
		System.out.println(des+" 处理中！");
		if(getHandler()!=null){
			getHandler().operation();
		}
	}
	
	public static void main(String[] args) {
		MyHandler handler1 = new MyHandler("handler1");
		MyHandler handler2 = new MyHandler("handler2");
		MyHandler handler3 = new MyHandler("handler3");
		
		handler1.setHandler(handler2);
		handler2.setHandler(handler3);
		handler1.operation();
	}
}
