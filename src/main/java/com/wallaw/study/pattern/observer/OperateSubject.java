package com.wallaw.study.pattern.observer;

import java.util.HashSet;
import java.util.Set;

public class OperateSubject implements Subject {
	Set<Observer> set = new HashSet<Observer>();
	public void add(Observer o) {
		set.add(o);
	}

	public void delete(Observer o) {
		set.remove(o);
	}

	public void notifyObservers() {
		for(Observer s:set){
			s.update();
		}
	}

	public void operation() {
		System.out.println("start update!");
		notifyObservers();
	}

	public static void main(String[] args) {
		Subject subject = new OperateSubject();
		subject.add(new Observer1());
		subject.add(new Observer2());
		
		subject.operation();
	}
}
