package com.wallaw.study.pattern.proxy;

public class SourceProxy implements Sourceable {
	private Source source;//针对实现类source 进行的代理
	private int perm;
	
	public void setPerm(int perm) {
		this.perm = perm;
	}
	public SourceProxy(Source source, int perm){
		this.source = source;
		this.perm = perm;
	}
	/***在代理类中做额外的处理，根据权限来判断是否可以关闭机器***/
	public void shutDownMachine() {
		if(Permission.COMSTON_ADMIN==this.perm||Permission.SUPER_ADMIN==this.perm){
			source.shutDownMachine();
			System.out.println("shoudown successfully!");//还可以同时增加其他修饰行为，跟装饰器就有点同职了
			return;
		}
		System.out.println("无权限关机！");
	}

	public void startMachine() {
		if(Permission.SUPER_ADMIN==this.perm){
			source.startMachine();
			System.out.println("startup OK!");
			return;
		}
		System.out.println("无权限开机！");
	}

	public static void main(String[] args) {
		Source source = new Source();
		SourceProxy proxy = new SourceProxy(source, Permission.COMSTON_USR);
		proxy.shutDownMachine();
		proxy.startMachine();
		
		proxy.setPerm(Permission.COMSTON_ADMIN);
		proxy.shutDownMachine();
		proxy.startMachine();
		
		proxy.setPerm(Permission.SUPER_ADMIN);
		proxy.shutDownMachine();
		proxy.startMachine();
	}
}
