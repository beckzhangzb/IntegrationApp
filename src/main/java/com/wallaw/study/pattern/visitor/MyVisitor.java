package com.wallaw.study.pattern.visitor;

public class MyVisitor implements Visitor {
	public void visit(Subject subject) {
		System.out.println("content:"+subject.getSubject());
	}
	
	public static void main(String[] args) {
		Visitor visitor = new MyVisitor();
		Subject subject = new MySubject();
		
		visitor.visit(subject);
	}
}
