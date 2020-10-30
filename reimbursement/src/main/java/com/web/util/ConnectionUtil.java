package com.web.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionUtil {

	{
		try {
			Class.forName("org.postgresql.Driver");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
	}

	// jdbc:postgresql://carlo-db-instance.cfrkice9i0us.us-east-1.rds.amazonaws.com:5432/myDatabase?currentSchema=reimbursement

//	private String url = System.getenv("url");
//	private String username = System.getenv("username");
//	private String password = System.getenv("password");
	private String url = "jdbc:postgresql://carlo-db-instance.cfrkice9i0us.us-east-1.rds.amazonaws.com:5432/myDatabase?currentSchema=reimbursement";
	private String username = "burm_man";
	private String password = "banana";
	private static ConnectionUtil instance;

	public ConnectionUtil() {
	}

	public static ConnectionUtil getInstance() {
		if (instance == null) {
			instance = new ConnectionUtil();
		}
		return instance;
	}

	public Connection getConnection() throws SQLException {
		return DriverManager.getConnection(url, username, password);
	}

}
