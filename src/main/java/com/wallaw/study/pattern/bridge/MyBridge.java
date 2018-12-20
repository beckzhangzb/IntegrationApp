package com.wallaw.study.pattern.bridge;

public class MyBridge extends Bridge {
	public void createConnection(){
		getDataBase().createConnection();
	}
	
	/***通过MyBridge 桥接类，可以根据需要，获得对应驱动mysql或oracle的连接***/
	public static void main(String[] args) {
		Bridge bridge = new MyBridge();
		DataBase mysqlDB = new MysqlDB();
		bridge.setDataBase(mysqlDB);
		bridge.createConnection();
		
		DataBase oracleDB = new OracleDB();
		bridge.setDataBase(oracleDB);
		bridge.createConnection();
		/***要得到不同的数据库连接，还需要知道具体的实现，这样不合理，可以结合工厂模式，进一步优化***/
	}
}
