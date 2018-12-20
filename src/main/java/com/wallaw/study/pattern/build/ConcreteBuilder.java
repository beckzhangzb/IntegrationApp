package com.wallaw.study.pattern.build;

public class ConcreteBuilder implements Builder {
	private Product product = new Product();
	public void buildPartA() {
		System.out.println("build A!");
	}

	public void buildPartB() {
		System.out.println("build B!");
	}

	public void buildPartC() {
		System.out.println("build C!");
	}

	public Product getResult() {
		// A,B,C 都被build了，然后放在product中
		product.doSomeThing();
		System.out.println("返回构建而成的一个Product对象！");
		return product;
	}
}
