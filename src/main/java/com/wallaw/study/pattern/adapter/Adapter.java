package com.wallaw.study.pattern.adapter;

public class Adapter extends Car{
	private Bus bus;
	Adapter(Bus bus){
		this.bus = bus;
	}
	
	public void printInfo(String str){
		bus.printInfo(str);
		System.out.println(str+" in car!");
	}

	/***其他外部调用，通过使用适配器，在轿车中使用汽车东西***/
	public static void main(String[] args) {
		Bus bus = new Bus();
		Adapter adapter = new Adapter(bus);
		adapter.printInfo("data");
	}
}
