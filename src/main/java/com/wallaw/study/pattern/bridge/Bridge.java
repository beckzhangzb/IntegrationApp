package com.wallaw.study.pattern.bridge;

public abstract class Bridge {
	private DataBase dataBase;
	
	public void createConnection(){
		dataBase.createConnection();
	}
	
	public void setDataBase(DataBase dataBase) {
		this.dataBase = dataBase;
	}
	
	public DataBase getDataBase() {
		return dataBase;
	}
}
