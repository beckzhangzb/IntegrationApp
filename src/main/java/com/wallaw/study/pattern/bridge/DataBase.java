package com.wallaw.study.pattern.bridge;

import java.sql.Connection;

public interface DataBase {
	public Connection createConnection();
}
