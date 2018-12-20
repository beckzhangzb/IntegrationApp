package com.wallaw.study.pattern.memento;

import java.io.File;

public class Originator {
	private int number;
	private File file = null;

	public Originator(int number, File file){
		this.number = number;
		this.file = file;
	}
	
	public Memento buildMemento(){
		return new Memento(this);
	}
	
	public void setMemento(Memento m){
		this.number = m.getNumber();
		this.file = m.getFile();
	}

	public int getNumber() {
		return number;
	}

	public File getFile() {
		return file;
	}
	
	public void setNumber(int number) {
		this.number = number;
	}

	public void setFile(File file) {
		this.file = file;
	}

	public static void main(String[] args) {
		Originator originator = new Originator(101, new File("F:\\temp\\temp.txt"));
		Memento memento = originator.buildMemento();
		
		originator.setNumber(102);
		originator.setFile(new File("F:\\temp\\new.txt"));
		System.out.println("number:"+originator.getNumber()+", file:"+originator.getFile().getAbsolutePath());
		
		originator.setMemento(memento);
		System.out.println("number:"+originator.getNumber()+", file:"+originator.getFile().getAbsolutePath());
	}
}
