package com.wallaw.study.pattern.bridge;

import java.sql.Connection;
import java.sql.DriverManager;

public class MysqlDB implements DataBase{
	public Connection createConnection() {
		try {
			Class.forName("mysql.driver");
			String url = "http://";
			Connection con = DriverManager.getConnection(url, "admin", "123");
			System.out.println("get mysql connection successfully!");
			return con;
		} catch (Exception e) {
			System.out.println("get mysql connection!");
		} 
		return null;
	}
}
