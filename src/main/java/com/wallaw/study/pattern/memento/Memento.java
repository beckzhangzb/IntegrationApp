package com.wallaw.study.pattern.memento;

import java.io.File;
import java.io.Serializable;

public class Memento implements Serializable {
	private static final long serialVersionUID = 1L;
	private int number;
	private File file = null;
	
	public Memento(Originator o) {
		this.number = o.getNumber();
		this.file = o.getFile();
	}

	public int getNumber() {
		return number;
	}
	
	public File getFile() {
		return file;
	}
}
