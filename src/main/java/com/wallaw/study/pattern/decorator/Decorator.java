package com.wallaw.study.pattern.decorator;

public class Decorator implements People {
	private People people;
	
	Decorator(People people){
		this.people = people;
	}
	
	public void walk() {
		this.run();
		people.walk();
		System.out.println("the decorator end!");
	}
	/***新增run行为，即添加新装饰run***/
	public void run(){
		System.out.println("this is new run method!");
	}
	
	public static void main(String[] args) {
		Man man = new Man();
		Decorator dec = new Decorator(man);
		dec.walk();
	}
}
