package com.wallaw.study.pattern.observer;

public interface Subject {
	public void add(Observer o);
	public void delete(Observer o);
	public void notifyObservers();
	public void operation();
}
